package la.kosmos.app

import grails.gorm.DetachedCriteria
import grails.plugin.springsecurity.ui.RegistrationCode
import grails.plugin.springsecurity.ui.SpringSecurityUiService
import grails.plugin.springsecurity.ui.strategy.MailStrategy
import grails.transaction.Transactional
import java.nio.file.Files
import java.nio.file.NoSuchFileException
import java.util.Calendar
import java.util.HashSet
import javax.xml.bind.DatatypeConverter
import la.kosmos.app.bo.Pager
import la.kosmos.app.bo.ProfilePicture
import la.kosmos.app.bo.User
import la.kosmos.app.exception.BusinessException
import la.kosmos.app.exception.MultipleSelectionException
import la.kosmos.app.vo.Constants
import maxmoto1702.excel.ExcelBuilder
import org.apache.commons.codec.binary.Base64
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.hibernate.transform.Transformers
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.Authentication
import org.springframework.transaction.annotation.Propagation
import org.apache.commons.lang.RandomStringUtils

@Transactional
class UserService {

    def userDetailsService
    def preAuthenticationChecks
    def postAuthenticationChecks
    def passwordEncoder
    def grailsApplication
    def springSecurityUiService
    def uiMailStrategy
    def uiRegistrationCodeStrategy
    def emailService

    def changePassword(String username, String password){
        if(username == null){
            throw new Exception("Operación inválida. Los datos del usuario han expirado")
        }

        Usuario user = Usuario.findByUsername(username)

        if(!user.passwordExpired){
            throw new BusinessException("Operación inválida. La contraseña ya ha sido cambiada")
        }

        return updatePassword(user, password)
    }

    private Authentication updatePassword(Usuario user, String password){
        //Validating new password
        checkPasswordRecord(user, password)

        //Saving current password
        UsuarioPasswords userPassword = new UsuarioPasswords(user, Calendar.getInstance().getTime());

        //Updating password
        user.password = password
        user.passwordExpired = Boolean.FALSE
        user.fechaPassword = Calendar.getInstance().getTime()
        user.addToUserPasswords(userPassword);

        if(!user.save(validate: Boolean.FALSE, flush: Boolean.TRUE, failOnError: Boolean.TRUE)) {
            throw new BusinessException("Ocurrió un error. Inténtalo más tarde")
        }

        //Adding UserLoginDetailsService functionality
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.username)

        //Adding PreAuthenticationChecks functionality
        preAuthenticationChecks.check(userDetails)

        try {
            //Adding PostAuthenticationChecks functionality
            postAuthenticationChecks.check(userDetails)

            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, password == null ? userDetails.getPassword() : password, userDetails.getAuthorities()));

            return SecurityContextHolder.context.authentication
        }   catch(MultipleSelectionException e){
            return null
        }
    }

    private void checkPasswordRecord(Usuario user, String password){
        //Validating new password
        if(passwordEncoder.isPasswordValid(user.password, password, null)){
            throw new BusinessException("Operación inválida. La contraseña ya ha sido usada con anterioridad")
        } else {
            if(!user.userPasswords?.empty){
                user.userPasswords.each {
                    if(passwordEncoder.isPasswordValid(it.password, password, null)){
                        throw new BusinessException("Operación inválida. La contraseña ya ha sido usada con anterioridad")
                    }
                }
            }
        }
    }

    def passwordExpiration() {
        def criteria = ConfiguracionEntidadFinanciera.createCriteria()
        def list = criteria.list {
            createAlias('entidadFinanciera', 'ef')
            eq ('ef.activa', Boolean.TRUE)
            isNotNull("expirarPasswords")

            projections {
                property('entidadFinanciera', 'entidadFinanciera')
                property('expirarPasswords', 'expirarPasswords')
            }

            resultTransformer(Transformers.aliasToBean(ConfiguracionEntidadFinanciera.class))
        }

        list.each {
            def entidad = it.entidadFinanciera
            def days = (it.expirarPasswords) * -1

            Calendar calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_MONTH, days)
            calendar.set(Calendar.HOUR_OF_DAY, 23)
            calendar.set(Calendar.MINUTE, 59)
            calendar.set(Calendar.SECOND, 59)

            Date fecha = calendar.getTime()
            criteria = new DetachedCriteria(Usuario).build {
                eq ('entidadFinanciera', entidad)
                eq ('passwordExpired', Boolean.FALSE)
                lt ('fechaPassword', fecha)
            }
            criteria.updateAll(passwordExpired:Boolean.TRUE)
        }
    }

    def getUsers(EntidadFinanciera entidadFinanciera, Pager pager){
        pager.rowsPerPage = Constants.TOTAL_ROWS
        def userList = []
        def criteria = Usuario.createCriteria()
        def results = criteria.list (max: pager.rowsPerPage, offset: pager.firstRow) {
            eq ('entidadFinanciera', entidadFinanciera)
            order("username", "asc")
        }

        pager.totalRows = results.totalCount

        results.each {
            User u = new User()
            u.id = it.id
            u.username = it.username
            u.fullName = it.toString()
            u.email = it.email
            u.authorities = it.authorities
            userList << u
        }

        return userList
    }

    def getUserDetails(id){
        Usuario usuario = Usuario.get(id)
        User user = new User()
        user.id = usuario.id
        user.username = usuario.username
        user.email = usuario.email
        user.authorities = usuario.authorities
        user.nombre = usuario.nombre
        user.apellidoPaterno = usuario.apellidoPaterno
        user.apellidoMaterno = usuario.apellidoMaterno
        user.enabled = usuario.enabled
        user.accountLocked = usuario.accountLocked
        user.sucursal = usuario.sucursal
        user.noEmpleado = usuario.numeroDeEmpleado

        return user
    }

    def validEmail(params){
        Usuario user = Usuario.findByEmail(params.email)
        return (user != null)
    }

    def validatePasswordRecoveryRequest(String token){
        def response = [:]

        def registrationCode = token ? RegistrationCode.findByToken(token) : null
        if (!registrationCode) {
            response.error = Boolean.TRUE
            response.message = "La solicitud de restablecimiento de contraseña es inválida"
            return response
        }

        Calendar requestDate = Calendar.getInstance();
        requestDate.setTime(registrationCode.dateCreated);
        requestDate.add(Calendar.MINUTE, grailsApplication.config.password.recovery);

        Calendar now = new GregorianCalendar();
        if (requestDate.compareTo(now) < 0) {
            deleteRegistrationCode(token)

            response.error = Boolean.TRUE
            response.message = "Ha excedido el tiempo permitido para hacer el cambio de contraseña"
            return response;
        }

        response.registrationCode = registrationCode
        return response
    }

    def passwordRecovery(String username, String password){
        Usuario user = Usuario.findByUsername(username)

        if(user == null){
            throw new BusinessException("Error. Los datos del usuario son inválidos")
        }

        return updatePassword(user, password)
    }

    def deleteRegistrationCode(String token){
        def registrationCode = RegistrationCode.findByToken(token)
        if (registrationCode != null){
            uiRegistrationCodeStrategy.deleteRegistrationCode(registrationCode)
        }
    }

    def validUsername(User usuario){
        Usuario user = Usuario.findByUsername(usuario.username)
        if(user == null) {
            return Boolean.TRUE
        } else {
            def id = (usuario.id).toBigInteger()
            if (BigInteger.valueOf(user.id).compareTo(id) == 0) {
                return Boolean.TRUE
            } else {
                return Boolean.FALSE
            }
        }
    }

    def validEmail(User usuario){
        Usuario user = Usuario.findByEmail(usuario.email)
        if(user == null) {
            return Boolean.TRUE
        } else {
            def id = (usuario.id).toBigInteger()
            if (BigInteger.valueOf(user.id).compareTo(id) == 0) {
                return Boolean.TRUE
            } else {
                return Boolean.FALSE
            }
        }
    }

    def userAuthentication(Usuario usuario, params){
        return (passwordEncoder.isPasswordValid(usuario.password, params.password, null))
    }

    def passwordRecoveryMessage() {
        def builder = new StringBuilder();
        builder.append("Estimado usuario").append(" ").append("\$user.username").append("<br/>").append("<br/>");
        builder.append("Se ha registrado una solicitud de \"Restablecimiento de contraseña\" de su cuenta de usuario. ");
        builder.append("Para continuar con el proceso de click").append(" ")
        builder.append("<a href=\"\$url\">aquí</a> y proporcione una nueva contraseña de acceso.").append("<br/>").append("<br/>");
        builder.append("Por razones de seguridad tiene que proporcionar una nueva contraseña en los próximos 30 minutos.").append("<br/>");

        return builder.toString();
    }

    @Transactional(propagation = Propagation.MANDATORY)
    def addUserInformationLog(Usuario usuario, Usuario targetUser, BigInteger movimiento, String descripcion){
        BitacoraAdministracionUsuarios bitacora = new BitacoraAdministracionUsuarios()
        bitacora.usuario = usuario
        bitacora.fechaHora = Calendar.getInstance().getTime()
        bitacora.targetUser = targetUser
        bitacora.bitacoraMovimientos = BitacoraMovimientos.get(movimiento)
        bitacora.descripcion = descripcion

        bitacora.save(insert:Boolean.TRUE, failOnError: Boolean.TRUE)
    }

    def getEntidadesFinancieras(){
        def criteria = EntidadFinanciera.createCriteria()
        def list = criteria.list {
            ne ('id', Constants.ENTIDAD_FINANCIERA_ROOT)
            eq ('activa', Boolean.TRUE)
        }
        return list
    }

    def addWorkspace (String username, params){
        String token = params.t

        if(username == null && token == null && token == "") {
            throw new Exception("Operación inválida. Los datos del usuario han expirado")
        } else if(token != null && token != "") {
            def registrationCode = RegistrationCode.findByToken(token)
            if (!registrationCode) {
                throw new Exception("Operación no permitida. Los datos de la operación han expirado")
            }

            username = registrationCode.username
            this.deleteRegistrationCode(token)
        }

        if(username == null) {
            throw new Exception("Operación no permitida. Los datos del usuario son inválidos")
        }

        Usuario user = Usuario.findByUsername(username)

        if(user == null){
            throw new BusinessException("Error. Los datos del usuario son inválidos")
        }

        def idEntidadFinanciera = (params.id).toLong()
        EntidadFinanciera entidadFinanciera = EntidadFinanciera.get(idEntidadFinanciera)

        if(entidadFinanciera == null){
            throw new BusinessException("Error. Los datos de la entidad financiera son inválidos")
        }

        //Adding UserLoginDetailsService functionality
        UserDetails userDetails = userDetailsService.loadUserByUsername(user, entidadFinanciera)

        //Adding PreAuthenticationChecks functionality
        preAuthenticationChecks.check(userDetails)
        //Adding PostAuthenticationChecks functionality
        postAuthenticationChecks.check(userDetails)

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities()));

        return SecurityContextHolder.context.authentication
    }

    def deleteUserSessions(){
        def criteria = ConfiguracionEntidadFinanciera.createCriteria()
        def list = criteria.list {
            createAlias('entidadFinanciera', 'ef')
            eq ('ef.activa', Boolean.TRUE)
            isNotNull("registroSesiones")

            projections {
                property('entidadFinanciera', 'entidadFinanciera')
                property('registroSesiones', 'registroSesiones')
            }

            resultTransformer(Transformers.aliasToBean(ConfiguracionEntidadFinanciera.class))
        }

        list.each {
            def entidad = it.entidadFinanciera
            def days = (it.registroSesiones) * -1

            Calendar calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_MONTH, days)
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)

            Date fecha = calendar.getTime()
            criteria = new DetachedCriteria(BitacoraSesionesUsuario).build {
                eq ('entidadFinanciera', entidad)
                lt ('fecha', fecha)
            }
            criteria.deleteAll()
        }
    }

    def validNoEmpleado(User usuario, EntidadFinanciera entidadFinanciera){
        Usuario user = Usuario.where{numeroDeEmpleado == usuario.noEmpleado && entidadFinanciera == entidadFinanciera}.get()
        if(user == null) {
            return Boolean.TRUE
        } else {
            def id = (usuario.id).toBigInteger()
            if (BigInteger.valueOf(user.id).compareTo(id) == 0) {
                return Boolean.TRUE
            } else {
                return Boolean.FALSE
            }
        }
    }

    RegistrationCode sendForgotPasswordMail(String username, String emailAddress, Closure emailBodyGenerator) {

        RegistrationCode registrationCode = springSecurityUiService.save(username: username, RegistrationCode, 'sendForgotPasswordMail', transactionStatus)
        if (!registrationCode.hasErrors()) {
            String body = emailBodyGenerator(registrationCode.token)

            uiMailStrategy.sendForgotPasswordMail(to: emailAddress, from: springSecurityUiService.forgotPasswordEmailFrom,
                subject: springSecurityUiService.forgotPasswordEmailSubject, html: body)
        }

        registrationCode
    }

    def saveProfile(Usuario usuario, params){
        def respuesta = [:]

        Usuario u = Usuario.get(usuario.id)
        u.username = params.username
        u.nombre = params.nombre
        u.apellidoPaterno = params.apellidoPaterno
        u.apellidoMaterno = params.apellidoMaterno
        u.email = params.email

        if(!u.save(validate: Boolean.FALSE, flush: Boolean.TRUE, failOnError: Boolean.TRUE)) {
            respuesta.error = Boolean.TRUE
            respuesta.mensaje = "Ocurrió un error al actualizar la información. Intente nuevamente más tarde."
            return respuesta
        }

        return respuesta
    }

    def updateProfilePassword(Usuario usuario, params){
        def respuesta = [:]
        String password = params.password

        Usuario user = Usuario.get(usuario.id)

        try {
            checkPasswordRecord(user, password)
        } catch (BusinessException ex){
            respuesta.error = Boolean.TRUE
            respuesta.mensaje = ex.getMessage()
            return respuesta
        }

        //Saving current password
        UsuarioPasswords userPassword = new UsuarioPasswords(user, Calendar.getInstance().getTime());

        //Updating password
        user.password = password
        user.fechaPassword = Calendar.getInstance().getTime()
        user.addToUserPasswords(userPassword);

        if(!user.save(validate: Boolean.FALSE, flush: Boolean.TRUE, failOnError: Boolean.TRUE)) {
            throw new BusinessException("Ocurrió un error. Inténtalo más tarde")
        }

        return respuesta
    }

    def exportUserList(EntidadFinanciera entidadFinanciera){
        def criteria = Usuario.createCriteria()
        def usuarios = criteria.list {
            eq ('entidadFinanciera', entidadFinanciera)
            order("nombre", "asc")
        }

        def workbook
        def builder = new ExcelBuilder()
        builder.config {
            font('negrita') { font ->
                font.bold = true
            }
            style('titulo') { cellStyle ->
                cellStyle.font = font('negrita')
                cellStyle.alignment = CellStyle.ALIGN_LEFT
                cellStyle.verticalAlignment = CellStyle.VERTICAL_CENTER
            }
            style('contenido') { cellStyle ->
                cellStyle.alignment = CellStyle.ALIGN_LEFT
                cellStyle.verticalAlignment = CellStyle.VERTICAL_CENTER
            }
        }
        workbook = builder.build {
            sheet(name: "Usuarios", widthColumns: [20, 20, 20, 20, 20, 20, 20, 35, 20, 20]) {
                row (style: 'titulo') {
                    cell { "Nombre" }
                    cell { "Apellido Paterno" }
                    cell { "Apellido Materno" }
                    cell { "Nombre de usuario" }
                    cell { "Correo electrónico" }
                    cell { "Sucursal" }
                    cell { "Número de empleado" }
                    cell { "Rol" }
                    cell { "Activo" }
                    cell { "Bloqueado" }
                }
                usuarios.each { fila ->
                    row (style: 'contenido') {
                        cell { fila.nombre }
                        cell { fila.apellidoPaterno }
                        cell { fila.apellidoMaterno }
                        cell { fila.username }
                        cell { fila.email }
                        cell { fila.sucursal?.nombre ?: "" }
                        cell { fila.numeroDeEmpleado ?: ""}
                        cell { fila.authorities.authority.join(", ") ?: ""}
                        cell { (fila.enabled) ? "SI" : "NO"}
                        cell { (fila.accountLocked) ? "SI" : "NO"}
                    }
                }
            }
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream()
        workbook.write(bos)
        bos.close()
        byte[] bytes = bos.toByteArray()

        builder = null
        workbook.dispose()
        workbook = null
        return bytes
    }

    @Transactional(rollbackFor=[IOException.class, NoSuchFileException.class])
    def saveProfilePicture(Usuario usuario, params){
        def respuesta = [:]

        Usuario u = Usuario.get(usuario.id)
        String rutaFotoPerfilUsuario = this.rutaFotoPerfilUsuario(u.entidadFinanciera)

        if(rutaFotoPerfilUsuario == null) {
            respuesta.error = Boolean.TRUE
            respuesta.message = "Error. No se ha configurado el almacenamiento de fotos de perfil de usuarios"
            return respuesta
        }

        String data = params.base64
        def parts = data.tokenize(",")
        def typeParts = (params.type).tokenize("/")
        def imageString = parts[1]
        byte[] imageByte = DatatypeConverter.parseBase64Binary(imageString)
        def name = params.name
        def directory = rutaFotoPerfilUsuario + u.id
        def relativePath = u.id + "/"
        def path = rutaFotoPerfilUsuario + relativePath + name

        if(usuario.fotoPerfilUsuario == null){
            FotoPerfilUsuario fotoPerfil = new FotoPerfilUsuario()
            fotoPerfil.nombre = name
            fotoPerfil.tipo = "." + typeParts[1]
            fotoPerfil.path = relativePath
            fotoPerfil.usuario = u

            u.fotoPerfilUsuario = fotoPerfil

            if(!u.save(validate: Boolean.FALSE, flush: Boolean.TRUE, failOnError: Boolean.TRUE)) {
                respuesta.error = Boolean.TRUE
                respuesta.mensaje = "Ocurrió un error al guardar la foto de perfil. Intente nuevamente más tarde."
                return respuesta
            } else {
                if(FileUtils.makeDirectory(directory)){
                    FileUtils.saveFile(path, imageByte)
                }
            }
        } else {
            String formerFile = rutaFotoPerfilUsuario + relativePath + u.fotoPerfilUsuario.nombre

            FotoPerfilUsuario fotoPerfil = u.fotoPerfilUsuario
            fotoPerfil.nombre = name
            fotoPerfil.tipo = "." + typeParts[1]
            fotoPerfil.path = relativePath
            fotoPerfil.usuario = u

            if(!u.save(validate: Boolean.FALSE, flush: Boolean.TRUE, failOnError: Boolean.TRUE)) {
                respuesta.error = Boolean.TRUE
                respuesta.mensaje = "Ocurrió un error al actualizar la foto de perfil. Intente nuevamente más tarde."
                return respuesta
            } else {
                FileUtils.deleteFile(formerFile)
                FileUtils.saveFile(path, imageByte)
            }
        }

        return respuesta
    }

    def getProfilePicture(Usuario usuario) {
        def response = [:]
        try {
            Usuario u = Usuario.get(usuario.id)
            String rutaFotoPerfilUsuario = this.rutaFotoPerfilUsuario(u.entidadFinanciera)

            if(rutaFotoPerfilUsuario == null || u.fotoPerfilUsuario == null){
                response.empty = Boolean.TRUE
                return response
            }

            def relativePath = u.fotoPerfilUsuario.path
            def name = u.fotoPerfilUsuario.nombre
            def path = rutaFotoPerfilUsuario + relativePath + name

            byte[] array = Files.readAllBytes(new File(path).toPath())
            def base64 = Base64.encodeBase64String(array)
            def type = u.fotoPerfilUsuario.tipo
            def typeParts = (type).tokenize(".")

            return new ProfilePicture(base64, typeParts)
        } catch (Exception ex){
            log.error("Ocurrio un error al cargar la foto de perfil del usuario " + usuario.id , ex)
            response.empty = Boolean.TRUE
            return response
        }
    }

    @Transactional(rollbackFor=[IOException.class, NoSuchFileException.class])
    def deleteProfilePicture(Usuario usuario){
        Usuario u = Usuario.get(usuario.id)
        String rutaFotoPerfilUsuario = this.rutaFotoPerfilUsuario(u.entidadFinanciera)

        def relativePath = u.fotoPerfilUsuario.path
        def name = u.fotoPerfilUsuario.nombre
        def path = rutaFotoPerfilUsuario + relativePath

        u.fotoPerfilUsuario.delete(failOnError : Boolean.TRUE)
        u.fotoPerfilUsuario = null

        FileUtils.deleteDirectory(path)
    }

    private String rutaFotoPerfilUsuario (EntidadFinanciera entidadFinanciera){
        def criteria = ConfiguracionEntidadFinanciera.createCriteria()
        ConfiguracionEntidadFinanciera ce = criteria.get {
            createAlias('entidadFinanciera', 'ef')
            eq ('ef.id', entidadFinanciera.id)

            projections {
                property('rutaFotoPerfilUsuario', 'rutaFotoPerfilUsuario')
            }

            resultTransformer(Transformers.aliasToBean(ConfiguracionEntidadFinanciera.class))
        }

        return ce.rutaFotoPerfilUsuario
    }

    def getProfilePictureSize(){
        return grailsApplication.config.profilePicture.size
    }

    def getProfilePictureContentTypes(){
        return grailsApplication.config.profilePicture.contentTypes
    }

    def validNewPassword (User user) {
        def respuesta = [:]
        String password = user.password

        def id = (user.id).toBigInteger()
        Usuario usuario = Usuario.get(id)

        try {
            checkPasswordRecord(usuario, password)
        } catch (BusinessException ex){
            respuesta.error = Boolean.TRUE
            respuesta.mensaje = ex.getMessage()
            return respuesta
        }

        return respuesta
    }
    def getUsersBusqueda(EntidadFinanciera entidadFinanciera, Pager pager, nombreUsuarioBusqueda,usernameUsuarioBusqueda){
        pager.rowsPerPage = Constants.TOTAL_ROWS
        def userList = []
        def criteria = Usuario.createCriteria()
        def results = criteria.list (max: pager.rowsPerPage, offset: pager.firstRow) {
            eq ('entidadFinanciera', entidadFinanciera)
            ilike("nombre","%"+nombreUsuarioBusqueda+"%")
            ilike("username","%"+usernameUsuarioBusqueda+"%")
            order("nombre", "asc")
        }

        pager.totalRows = results.totalCount

        results.each {
            User u = new User()
            u.id = it.id
            u.username = it.username
            u.fullName = it.toString()
            u.email = it.email
            u.authorities = it.authorities
            userList << u
        }

        return userList
    }
    def loginRest (params){
        def respuesta = [:]
        def usuario = Usuario.findByUsername(params.username)
        if(!usuario || !passwordEncoder.isPasswordValid(usuario?.password, params.password, null)){
            respuesta.error = true
            respuesta.mensaje = "El usuario o password no coinciden"
            return respuesta
        }
        if(usuario && passwordEncoder.isPasswordValid(usuario.password, params.password, null)){
            respuesta.id = usuario.id
            respuesta.username = usuario.username
            respuesta.fullName = usuario.toString()
            respuesta.email = usuario.email
            respuesta.authorities = usuario.authorities
            respuesta.exito = true
            respuesta.mensaje = "Login success"
            usuario.idDispositivo = params.idDispositivo
        }
        if(usuario?.passwordExpired){
            respuesta.exito = false
            respuesta.error = true
            respuesta.mensaje = "El password ha expirado"
            return respuesta
        }
        else if(!usuario?.enabled){
            respuesta.exito = false
            respuesta.error = true
            respuesta.mensaje = "El usuario esta desactivado"
            return respuesta
        }
        else if(usuario?.accountLocked){
            respuesta.exito = false
            respuesta.error = true
            respuesta.mensaje = "El usuario tiene la cuenta bloqueada"
            return respuesta
        }
        return respuesta
        
    }
    def checarDispositivo(params){
        def respuesta = [:]
        println params
        def usuario = Usuario.findByIdDispositivo(params)
        if(!usuario){
            respuesta.error = true
            respuesta.mensaje = "No hay usuario asociado a este dispositivo"
            return respuesta
        }
        if(usuario){
            respuesta.id = usuario.id
            respuesta.username = usuario.username
            respuesta.fullName = usuario.toString()
            respuesta.email = usuario.email
            respuesta.authorities = usuario.authorities
            respuesta.exito = true
            respuesta.mensaje = "Login success"
        }
        if(usuario?.passwordExpired){
            respuesta.exito = false
            respuesta.error = true
            respuesta.mensaje = "El password ha expirado"
            return respuesta
        }
        else if(!usuario?.enabled){
            respuesta.exito = false
            respuesta.error = true
            respuesta.mensaje = "El usuario esta desactivado"
            return respuesta
        }
        else if(usuario?.accountLocked){
            respuesta.exito = false
            respuesta.error = true
            respuesta.mensaje = "El usuario tiene la cuenta bloqueada"
            return respuesta
        }
        return respuesta
    }
    def forgotPassword(params) {

        Usuario user = Usuario.findByEmail(params.email)
        if(user == null){
            flash.message = "Error. Los datos del usuario son invalidos"
            return
        }

        String email = user.email
        def response = [:]

        try {
            def codigo = getRandomCode()
            def registrationCode = new RegistrationCode ()
            registrationCode.dateCreated = new Date()
            registrationCode.token = codigo
            registrationCode.username = user.username
            if(registrationCode.save(flush:true)){
                def texto = ''
                texto += "Estimado usuario $user.username <br/> <br/>"
                texto += "Se ha registrado una solicitud de \"Restablecimiento de contraseña\" de su cuenta de usuario. "
                texto += "Para continuar con el proceso ingresa el siguiente código $codigo "
                texto += "y proporcione una nueva contraseña de acceso. <br/> <br/>"
                texto += "Por razones de seguridad tiene que proporcionar una nueva contraseña en los próximos 30 minutos. <br/>"
                def configuracion = ConfiguracionEntidadFinanciera.get(1)
                uiMailStrategy.sendForgotPasswordMail(to: email, from: springSecurityUiService.forgotPasswordEmailFrom,
                subject: "Restablecimiento de contraseña", html: texto.toString())
             }
            response.usuario = user.username
            response.exito = true
            response.message = "Se ha enviado un mensaje a su cuenta de correo electrónico con las instrucciones que debe seguir para continuar con el proceso de restablecimiento de contraseña."
        } catch (Exception e){
            log.error ("Ocurrio un error al enviar el correo electronico", e)
            response.error = Boolean.TRUE
            response.message = "Ha ocurrido un error al enviar el correo electrónico. Por favor inténtalo más tarde."
        }

        return response 
    }
    def verificarCodigo(params){
        def response = [:]
        
        def registrationCode = params.codigo ? RegistrationCode.findWhere(token:params.codigo,username:params.username) : null
        if (!registrationCode) {
            response.error = Boolean.TRUE
            response.message = "La solicitud de restablecimiento de contraseña es inválida"
            return response
        }
        
        Calendar requestDate = Calendar.getInstance();
        requestDate.setTime(registrationCode.dateCreated);
        requestDate.add(Calendar.MINUTE, grailsApplication.config.password.recovery);
        
        Calendar now = new GregorianCalendar();
        if (requestDate.compareTo(now) < 0) {
            deleteRegistrationCode(params.codigo)
            
            response.error = Boolean.TRUE
            response.message = "Ha excedido el tiempo permitido para hacer el cambio de contraseña"
            return response;
        }
        
        response.registrationCode = registrationCode
        response.exito = true
        return response
        
    }
    
    def passwordRecoveryRest(String username, String password){
        def respuesta = [:]
        Usuario user = Usuario.findByUsername(username)
        
        if(user == null){
            throw new BusinessException("Error. Los datos del usuario son inválidos")
        }
        def validar = checkPasswordRecordRest(user, password)
        if(!validar){
            respuesta.exito = true
            respuesta.mensaje = "La contraseña ha sido actualizada correctamente"
            respuesta.usuario = user
            //Saving current password
            UsuarioPasswords userPassword = new UsuarioPasswords(user, Calendar.getInstance().getTime());
            
            //Updating password
            user.password = password
            user.passwordExpired = Boolean.FALSE
            user.fechaPassword = Calendar.getInstance().getTime()
            user.addToUserPasswords(userPassword);
            
            if(!user.save(validate: Boolean.FALSE, flush: Boolean.TRUE, failOnError: Boolean.TRUE)) {
                respuesta.exito = false
                respuesta.mensaje = "Ocurrió un error. Inténtalo más tarde"
            }
        }else{
            respuesta.exito = false
            respuesta.mensaje = "La contraseña ya ha sido utilizada"
        }
        return respuesta
    }
    
    private boolean checkPasswordRecordRest(Usuario user, String password){
        //Validating new password
        def respuesta = false
        if(passwordEncoder.isPasswordValid(user.password, password, null)){
            respuesta = true
        } else {
            if(!user.userPasswords?.empty){
                user.userPasswords.each {
                    if(passwordEncoder.isPasswordValid(it.password, password, null)){
                        respuesta = true
                    }
                }
            }
        }
        return respuesta
    }
    private String getRandomCode() {
        //def result = RandomStringUtils.randomAlphanumeric(5).toUpperCase()
        def result = RandomStringUtils.randomNumeric(5).toUpperCase()
        result
    }
    
    def getUltimoAcceso(Usuario usuario){
        def userList = []
        def count = 0
        def criteria = BitacoraSesionesUsuario.createCriteria()
        def list = criteria.list(max:2) {
            eq ('usuario',usuario)
            order("fecha", "desc")
        }
     
        if(list.size() == 1 ){
            count = 1
        }
        list.each {
            if(count == 1){
                def mapa = [:]
                mapa.id = it.id
                mapa.fecha = it.fecha
                
                userList << mapa
            }
            count ++
        }
        return userList
    }

}
