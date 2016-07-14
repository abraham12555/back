package la.kosmos.app

import grails.converters.JSON
import java.awt.image.BufferedImage
import java.text.SimpleDateFormat
import javax.imageio.ImageIO
import java.util.Random
import org.apache.commons.codec.binary.Base64
import javax.xml.bind.DatatypeConverter
//import org.apache.commons.io.FileUtils

class SolicitudController {
    
    Random rand = new Random() 

    def index() { }
	
    def login(){}


    def paso_1(){
        //params.datos_fb="{\"id\": \"1273758305972971\",\"name\": \"Jorge Medina\",\"birthday\": \"04/24/1985\",\"education\": [{\"school\": {\"id\": \"329234880539960\",\"name\": \"Instituto Tecnológico de Toluca\"},\"type\": \"High School\",\"year\": {\"id\": \"141778012509913\",\"name\": \"2008\"},\"id\": \"360611733954304\"},{\"concentration\": [{\"id\": \"149668418423502\",\"name\": \"Sistemas Computacionales\"}],\"degree\": {\"id\": \"195120407185348\",\"name\": \"13/02/2009\"},\"school\": {\"id\": \"113846665298870\",\"name\": \"Instituto Tecnologico de Toluca\"},\"type\": \"College\",\"id\": \"208314902517322\"}],\"email\": \"tazvoit@hotmail.com\",\"first_name\": \"Jorge\",\"gender\": \"male\",\"last_name\": \"Medina\",\"picture\": {\"data\": {\"is_silhouette\": false,\"url\": \"https://scontent.xx.fbcdn.net/v/t1.0-1/p50x50/13100952_1327839547231513_1803219328987116718_n.jpg?oh=3d8f6deb02926a473ac77bebcf2ddb85&oe=57F1E029\"}},\"relationship_status\": \"Casado\"}";
        def tipo_login;
        def datos_login;
        if(params.datos_fb.toString()!= 'null'){
            System.out.println(":: Se inicio Sesión con FB ::"+params.datos_fb.toString());
            datos_login = params.datos_fb.toString()
            tipo_login="FB";
        }else if(params.datos_google.toString()!= 'null'){
            System.out.println(":: Se inicio Sesión con Google ::"+params.datos_google.toString());
            datos_login = params.datos_google.toString();
            tipo_login="Google";
        }else{
            System.out.println(":: No se inicio Sesión ::");
            tipo_login="none";
        }

        [nacionalidadList:Nacionalidad.findAll(),
            estadoCivilList:EstadoCivil.findAll(),
            coloniaList:Colonia.findAll(),
            estadoList:Estado.findAll(),
            municipioList:Municipio.findAll(),
            temporalidadList:Temporalidad.findAll(),
            datos_login:datos_login,
            tipo_login:tipo_login]
    }
    
    def formulario(){

    }

    def paso1_1() {
        //DATOS DE USUARIO.
        Cliente cliente;
        if (session["cliente"] instanceof Cliente) {
            System.out.println("Variable en Sesion");
            cliente = session["cliente"]
        } else {
            System.out.println("Creando Variable");
            cliente = new Cliente();
        }

        String[] nombre = params.NOMBRE_USUARIO.toString().split(" ");
        int num = nombre.size()
        switch (num) {
        case 1:
            cliente.setNombre(nombre[0]);
            break;

        case 2:
            cliente.setNombre(nombre[0]);
            cliente.setApellidoPaterno(nombre[1]);
            break;

        case 3:
            cliente.setNombre(nombre[0]);
            cliente.setApellidoPaterno(nombre[1]);
            cliente.setApellidoMaterno(nombre[2]);
            break;

        }

        System.out.println("Nacionalidad" + params);
        String date_s = params.ANIO + "/" + params.MES + "/" + params.DIA;
        SimpleDateFormat dt = new SimpleDateFormat("yyyyy/mm/dd");
        Date fechaNacimiento = dt.parse(date_s);
        cliente.fechaDeNacimiento = fechaNacimiento;
        cliente.nacionalidad = Nacionalidad.findById(new Integer(params.nacionalidad))
        cliente.genero = Genero.findById(1);
        cliente.estadoCivil = EstadoCivil.findByNombre(params.ESTADO_CIVIL);
        session["cliente"] = cliente;

        render "paso1_1"
    }

    def paso1_2(){
        //DATOS COMPLEMENTARIOS CLIENTE
        ArrayList<TelefonoCliente> telefonos
        if( session["telefonosCliente"] instanceof ArrayList){
            telefonos = session["telefonosCliente"]
        }else{
            telefonos = new ArrayList<TelefonoCliente>();
        }

        TelefonoCliente  telefonoCliente = new TelefonoCliente()
        telefonoCliente.setNumeroTelefonico(params.telephone);
        telefonoCliente.setTipoDeTelefono(TipoDeTelefono.findById(1));
        //telefonoCliente.setCliente(cliente);
        telefonos.add(telefonoCliente);
        telefonoCliente = new TelefonoCliente();
        telefonoCliente.setNumeroTelefonico(params.cellphone);
        telefonoCliente.setTipoDeTelefono(TipoDeTelefono.findById(2));
        //telefonoCliente.setCliente(cliente);
        telefonos.add(telefonoCliente);

        session["telefonosCliente"] = telefonos;
        System.out.println("NUMEROS TELEFONICOS CLIENTE OK" +params);

        render "paso1_2"
    }

    def paso2_1(){
        //DIRECCION DEL CLIENTE
        DireccionCliente  direccion = new DireccionCliente();
        if( session["direccion"] instanceof DireccionCliente){
            System.out.println("Variable en Sesion");
            direccion =  session["direccion"];
        }else{
            direccion = new DireccionCliente();
        }

        direccion.setCalle(params.calle);
        direccion.setNumeroExterior(params.noexterior);
        direccion.setNumeroInterior(params.nointerior);
        direccion.setCodigoPostal(params.codigopostal);

        Colonia colonia=Colonia.findById(new Integer(params.colonia));
        Municipio municipio = colonia.getMunicipio()
        direccion.setColonia(colonia);
        direccion.setMunicipio(municipio)
        direccion.setEstado(municipio.getEstado())
        //direccion.cliente=cliente;

        session["direccion"] = direccion;

        render "paso2_1"
    }

    def paso3_1(){
        EmpleoCliente empleoCliente =  new EmpleoCliente();
        if( session["empleoCliente"] instanceof EmpleoCliente){
            System.out.println("Variable en Sesion");
            empleoCliente  = session["empleoCliente"]
        }else{
            empleoCliente =  new EmpleoCliente();
        }
        empleoCliente.nombreDeLaEmpresa=params.empresa;
        empleoCliente.puesto=params.puesto;
        empleoCliente.antiguedad=new Integer(params.periodo);
        empleoCliente.temporalidad=Temporalidad.findById(new Integer(params.contrato));

        session["empleoCliente"] = empleoCliente;
        System.out.println( "EMPLEO CLIENTE OK" +params)
        redirect(action: "guardarCliente" )


    }

    def guardarCliente(){
        if( session["cliente"] instanceof Cliente){
            System.out.println("Variable en Sesion Final ");

            Cliente cliente = session["cliente"]
            cliente.save(flush:true);
            System.out.println("REGISTROS cliente" + cliente.toString());
            EmpleoCliente empleoCliente = session["empleoCliente"]
            empleoCliente.cliente=cliente;
            empleoCliente.save(flush:true);
            System.out.println("REGISTROS empleoCliente" + empleoCliente.toString());
            DireccionCliente  direccion = session["direccion"]
            direccion.cliente=cliente;
            direccion.save(flush:true);
            System.out.println("REGISTROS direccion" + direccion.toString());
            ArrayList<TelefonoCliente> telefonos = session["telefonosCliente"]
            for(int i=0;i<telefonos.size();i++){
                TelefonoCliente tel = telefonos.get(i);
                tel.cliente=cliente;
                tel.save(flush:true);
                System.out.println("REGISTROS tel" + tel.toString());
            }
            System.out.println("REGISTROS GUARDADO" + cliente.toString());
            redirect(action: "paso4_1")

        }else{
            System.out.println("ERROR NO SE PUE GUARDAR")
            redirect(action: "error")
        }
        render "paso3_1"

    }

    def cambiarPaso(){
	println params
        if(params.siguientePaso){
            render(template: ("paso"+params.siguientePaso))
        }
    }
    
    def cargarControlDefault(){
        render(template: "/templates/solicitud/paso4/consultaBancariaDefault")
    }
    
    def consultaBancos(){
        println params
        def respuesta = [:]
        int max = 10  
        def test = rand.nextInt(max+1)
        sleep(3000)
        if(test % 2 == 0){
            respuesta.status = 200
            respuesta.depositosPromedio =  rand.nextInt(18000+1)
            respuesta.retirosPromedio =  rand.nextInt(10000+1)
            respuesta.saldoPromedio = rand.nextInt(8000+1)
            render respuesta as JSON
        } else {
            //render(template: "/templates/solicitud/paso4/errorConsultaBancaria", model: [intentos: (params.intentos ? ((params.intentos as int) + 1) : (0))])
            respuesta.error = 500
            respuesta.intentos = (params.intentos ? ((params.intentos as int) + 1) : (0))
            render respuesta as JSON
        }
    }
    
    def subirDocumento(){
        
    }
    
    def consultarEphesoft(){
        
    }
    
    def consultarBuroDeCredito(){
        println params
        def respuesta = [:]
        int max = 10  
        def test = rand.nextInt(max+1)
        sleep(3000)
        if(test % 2 == 0){
            respuesta.status = 200
            render respuesta as JSON
        } else {
            //render(template: "/templates/solicitud/paso4/errorConsultaBancaria", model: [intentos: (params.intentos ? ((params.intentos as int) + 1) : (0))])
            respuesta.error = 500
            respuesta.intentos = (params.intentos ? ((params.intentos as int) + 1) : (0))
            render respuesta as JSON
        }
    }
    
    def cargaDeArchivos(){
        render(template: "/templates/solicitud/paso4/cargaDeIdentificaciones")
    }
    
    def guardarFoto(){
        //println params
        def respuesta = [:]
        def imagenOrigen = params.img_data;
        println "Longitud de la cadena: " + params.img_data.length()
        def tokens = imagenOrigen.split(",");
        String cadenaBase64 = URLEncoder.encode(tokens[1], "UTF-8");
        int max = 1000  
        def test = rand.nextInt(max+1)
        BufferedImage imagen = null;
        byte[] imagenEnBytes;
        try{
            //println cadenaBase64
            imagenEnBytes = Base64.decodeBase64(cadenaBase64)
            File file = new File("/var/uploads/kosmos/fotos/image" + test + ".png");
            BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file));
            writer.write(imagenEnBytes);
            writer.flush();
            writer.close();
            /*ByteArrayInputStream bis = new ByteArrayInputStream(imagenEnBytes);
            imagen = ImageIO.read(bis);
            bis.close();
            File outputfile = new File("/var/uploads/kosmos/fotos/image" + test + ".png");
            ImageIO.write(imagen, "png", outputfile);*/
            respuesta.status = 200
        } catch(Exception e){
            respuesta.status = 500
            respuesta.error = "Error al guardar el archivo"
            e.printStackTrace();
        } finally {
            render respuesta as JSON
        }
    }
}
