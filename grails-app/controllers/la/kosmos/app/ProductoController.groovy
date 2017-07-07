package la.kosmos.app

import grails.converters.JSON
import groovy.json.*
import org.apache.commons.codec.binary.Base64
import java.nio.file.Files;
import grails.plugin.springsecurity.authentication.encoding.BCryptPasswordEncoder
class ProductoController {
    def springSecurityService

    def obtenerDetalleProducto(Producto producto){
        println params
        def documentosProducto
        def rubrosDeAplicacion
        def plazoProducto
        def garantiaProducto
        def limitePlazoProducto
        def modelos
        def colores
        def rutaImagenDefault
        def garantiasProducto
        if(session.configuracion?.aplicacionVariable){
            documentosProducto = DocumentoProducto.findAllWhere(producto: producto)
            rubrosDeAplicacion = RubroDeAplicacionProducto.findAllWhere(producto: producto)
            plazoProducto = PlazoProducto.findAllWhere(producto: producto)
            limitePlazoProducto = LimitePlazoProducto.findAllWhere(producto:producto)
            garantiasProducto = GarantiaProducto.findAllWhere(producto:producto)
            rutaImagenDefault = obtenerBase64Imagenes(producto.rutaImagenDefault)

            //garantiaProducto = GarantiaProducto.findAllWhere(producto: producto)
            
        } else {
            modelos = Modelo.findAllWhere(producto: producto)
            colores = ColorModelo.findAllByModeloInList(modelos)
        }
        
        render(template: "/dashboard/configuracion/producto/detalleProducto", 
            model: [producto: producto, documentosProducto: documentosProducto, 
                    rubrosDeAplicacion: rubrosDeAplicacion, plazoProducto: plazoProducto,
                    garantiaProducto: garantiaProducto, modelos: modelos, colores: 
                    colores,limitePlazoProducto:limitePlazoProducto,rutaImagenDefault:rutaImagenDefault,garantiasProducto:garantiasProducto])
    }
    
    def obtenerBase64Imagenes(def ruta){
        def respuesta = [:]
        if(ruta){
            byte[] array = Files.readAllBytes(new File(ruta).toPath());
            respuesta.base64 = Base64.encodeBase64String(array)
            respuesta.extension =  ruta.split("\\.")[-1]
        }
        return respuesta
    }
    
    
    
    def obtenerIconos(){
        println params
    def data =["fa-500px","fa-adjust","fa-adn","fa-align-center","fa-align-justify","fa-align-left","fa-align-right","fa-amazon","fa-ambulance","fa-anchor","fa-android","fa-angellist","fa-angle-double-down","fa-angle-double-left","fa-angle-double-right","fa-angle-double-up","fa-angle-down","fa-angle-left","fa-angle-right","fa-angle-up","fa-apple","fa-archive","fa-area-chart","fa-arrow-circle-down","fa-arrow-circle-left","fa-arrow-circle-o-down","fa-arrow-circle-o-left","fa-arrow-circle-o-right","fa-arrow-circle-o-up","fa-arrow-circle-right","fa-arrow-circle-up","fa-arrow-down","fa-arrow-left","fa-arrow-right","fa-arrow-up","fa-arrows","fa-arrows-alt","fa-arrows-h","fa-arrows-v","fa-asterisk","fa-at","fa-automobile","fa-backward","fa-balance-scale","fa-ban","fa-bank","fa-bar-chart","fa-bar-chart-o","fa-barcode","fa-bars","fa-battery-0","fa-battery-1","fa-battery-2","fa-battery-3","fa-battery-4","fa-battery-empty","fa-battery-full","fa-battery-half","fa-battery-quarter","fa-battery-three-quarters","fa-bed","fa-beer","fa-behance","fa-behance-square","fa-bell","fa-bell-o","fa-bell-slash","fa-bell-slash-o","fa-bicycle","fa-binoculars","fa-birthday-cake","fa-bitbucket","fa-bitbucket-square","fa-bitcoin","fa-black-tie","fa-bluetooth","fa-bluetooth-b","fa-bold","fa-bolt","fa-bomb","fa-book","fa-bookmark","fa-bookmark-o","fa-briefcase","fa-btc","fa-bug","fa-building","fa-building-o","fa-bullhorn","fa-bullseye","fa-bus","fa-buysellads","fa-cab","fa-calculator","fa-calendar","fa-calendar-check-o","fa-calendar-minus-o","fa-calendar-o","fa-calendar-plus-o","fa-calendar-times-o","fa-camera","fa-camera-retro","fa-car","fa-caret-down","fa-caret-left","fa-caret-right","fa-caret-square-o-down","fa-caret-square-o-left","fa-caret-square-o-right","fa-caret-square-o-up","fa-caret-up","fa-cart-arrow-down","fa-cart-plus","fa-cc","fa-cc-amex","fa-cc-diners-club","fa-cc-discover","fa-cc-jcb","fa-cc-mastercard","fa-cc-paypal","fa-cc-stripe","fa-cc-visa","fa-certificate","fa-chain","fa-chain-broken","fa-check","fa-check-circle","fa-check-circle-o","fa-check-square","fa-check-square-o","fa-chevron-circle-down","fa-chevron-circle-left","fa-chevron-circle-right","fa-chevron-circle-up","fa-chevron-down","fa-chevron-left","fa-chevron-right","fa-chevron-up","fa-child","fa-chrome","fa-circle","fa-circle-o","fa-circle-o-notch","fa-circle-thin","fa-clipboard","fa-clock-o","fa-clone","fa-close","fa-cloud","fa-cloud-download","fa-cloud-upload","fa-cny","fa-code","fa-code-fork","fa-codepen","fa-codiepie","fa-coffee","fa-cog","fa-cogs","fa-columns","fa-comment","fa-comment-o","fa-commenting","fa-commenting-o","fa-comments","fa-comments-o","fa-compass","fa-compress","fa-connectdevelop","fa-contao","fa-copy","fa-copyright","fa-creative-commons","fa-credit-card","fa-credit-card-alt","fa-crop","fa-crosshairs","fa-css3","fa-cube","fa-cubes","fa-cut","fa-cutlery","fa-dashboard","fa-dashcube","fa-database","fa-dedent","fa-delicious","fa-desktop","fa-deviantart","fa-diamond","fa-digg","fa-dollar","fa-dot-circle-o","fa-download","fa-dribbble","fa-dropbox","fa-drupal","fa-edge","fa-edit","fa-eject","fa-ellipsis-h","fa-ellipsis-v","fa-empire","fa-envelope","fa-envelope-o","fa-envelope-square","fa-eraser","fa-eur","fa-euro","fa-exchange","fa-exclamation","fa-exclamation-circle","fa-exclamation-triangle","fa-expand","fa-expeditedssl","fa-external-link","fa-external-link-square","fa-eye","fa-eye-slash","fa-eyedropper","fa-facebook","fa-facebook-f","fa-facebook-official","fa-facebook-square","fa-fast-backward","fa-fast-forward","fa-fax","fa-feed","fa-female","fa-fighter-jet","fa-file","fa-file-archive-o","fa-file-audio-o","fa-file-code-o","fa-file-excel-o","fa-file-image-o","fa-file-movie-o","fa-file-o","fa-file-pdf-o","fa-file-photo-o","fa-file-picture-o","fa-file-powerpoint-o","fa-file-sound-o","fa-file-text","fa-file-text-o","fa-file-video-o","fa-file-word-o","fa-file-zip-o","fa-files-o","fa-film","fa-filter","fa-fire","fa-fire-extinguisher","fa-firefox","fa-flag","fa-flag-checkered","fa-flag-o","fa-flash","fa-flask","fa-flickr","fa-floppy-o","fa-folder","fa-folder-o","fa-folder-open","fa-folder-open-o","fa-font","fa-fonticons","fa-fort-awesome","fa-forumbee","fa-forward","fa-foursquare","fa-frown-o","fa-futbol-o","fa-gamepad","fa-gavel","fa-gbp","fa-ge","fa-gear","fa-gears","fa-genderless","fa-get-pocket","fa-gg","fa-gg-circle","fa-gift","fa-git","fa-git-square","fa-github","fa-github-alt","fa-github-square","fa-gittip","fa-glass","fa-globe","fa-google","fa-google-plus","fa-google-plus-square","fa-google-wallet","fa-graduation-cap","fa-gratipay","fa-group","fa-h-square","fa-hacker-news","fa-hand-grab-o","fa-hand-lizard-o","fa-hand-o-down","fa-hand-o-left","fa-hand-o-right","fa-hand-o-up","fa-hand-paper-o","fa-hand-peace-o","fa-hand-pointer-o","fa-hand-rock-o","fa-hand-scissors-o","fa-hand-spock-o","fa-hand-stop-o","fa-hashtag","fa-hdd-o","fa-header","fa-headphones","fa-heart","fa-heart-o","fa-heartbeat","fa-history","fa-home","fa-hospital-o","fa-hotel","fa-hourglass","fa-hourglass-1","fa-hourglass-2","fa-hourglass-3","fa-hourglass-end","fa-hourglass-half","fa-hourglass-o","fa-hourglass-start","fa-houzz","fa-html5","fa-i-cursor","fa-ils","fa-image","fa-inbox","fa-indent","fa-industry","fa-info","fa-info-circle","fa-inr","fa-instagram","fa-institution","fa-internet-explorer","fa-intersex","fa-ioxhost","fa-italic","fa-joomla","fa-jpy","fa-jsfiddle","fa-key","fa-keyboard-o","fa-krw","fa-language","fa-laptop","fa-lastfm","fa-lastfm-square","fa-leaf","fa-leanpub","fa-legal","fa-lemon-o","fa-level-down","fa-level-up","fa-life-bouy","fa-life-buoy","fa-life-ring","fa-life-saver","fa-lightbulb-o","fa-line-chart","fa-link","fa-linkedin","fa-linkedin-square","fa-linux","fa-list","fa-list-alt","fa-list-ol","fa-list-ul","fa-location-arrow","fa-lock","fa-long-arrow-down","fa-long-arrow-left","fa-long-arrow-right","fa-long-arrow-up","fa-magic","fa-magnet","fa-mail-forward","fa-mail-reply","fa-mail-reply-all","fa-male","fa-map","fa-map-marker","fa-map-o","fa-map-pin","fa-map-signs","fa-mars","fa-mars-double","fa-mars-stroke","fa-mars-stroke-h","fa-mars-stroke-v","fa-maxcdn","fa-meanpath","fa-medium","fa-medkit","fa-meh-o","fa-mercury","fa-microphone","fa-microphone-slash","fa-minus","fa-minus-circle","fa-minus-square","fa-minus-square-o","fa-mixcloud","fa-mobile","fa-mobile-phone","fa-modx","fa-money","fa-moon-o","fa-mortar-board","fa-motorcycle","fa-mouse-pointer","fa-music","fa-navicon","fa-neuter","fa-newspaper-o","fa-object-group","fa-object-ungroup","fa-odnoklassniki","fa-odnoklassniki-square","fa-opencart","fa-openid","fa-opera","fa-optin-monster","fa-outdent","fa-pagelines","fa-paint-brush","fa-paper-plane","fa-paper-plane-o","fa-paperclip","fa-paragraph","fa-paste","fa-pause","fa-pause-circle","fa-pause-circle-o","fa-paw","fa-paypal","fa-pencil","fa-pencil-square","fa-pencil-square-o","fa-percent","fa-phone","fa-phone-square","fa-photo","fa-picture-o","fa-pie-chart","fa-pied-piper","fa-pied-piper-alt","fa-pinterest","fa-pinterest-p","fa-pinterest-square","fa-plane","fa-play","fa-play-circle","fa-play-circle-o","fa-plug","fa-plus","fa-plus-circle","fa-plus-square","fa-plus-square-o","fa-power-off","fa-print","fa-product-hunt","fa-puzzle-piece","fa-qq","fa-qrcode","fa-question","fa-question-circle","fa-quote-left","fa-quote-right","fa-ra","fa-random","fa-rebel","fa-recycle","fa-reddit","fa-reddit-alien","fa-reddit-square","fa-refresh","fa-registered","fa-remove","fa-renren","fa-reorder","fa-repeat","fa-reply","fa-reply-all","fa-retweet","fa-rmb","fa-road","fa-rocket","fa-rotate-left","fa-rotate-right","fa-rouble","fa-rss","fa-rss-square","fa-rub","fa-ruble","fa-rupee","fa-safari","fa-save","fa-scissors","fa-scribd","fa-search","fa-search-minus","fa-search-plus","fa-sellsy","fa-send","fa-send-o","fa-server","fa-share","fa-share-alt","fa-share-alt-square","fa-share-square","fa-share-square-o","fa-shekel","fa-sheqel","fa-shield","fa-ship","fa-shirtsinbulk","fa-shopping-bag","fa-shopping-basket","fa-shopping-cart","fa-sign-in","fa-sign-out","fa-signal","fa-simplybuilt","fa-sitemap","fa-skyatlas","fa-skype","fa-slack","fa-sliders","fa-slideshare","fa-smile-o","fa-soccer-ball-o","fa-sort","fa-sort-alpha-asc","fa-sort-alpha-desc","fa-sort-amount-asc","fa-sort-amount-desc","fa-sort-asc","fa-sort-desc","fa-sort-down","fa-sort-numeric-asc","fa-sort-numeric-desc","fa-sort-up","fa-soundcloud","fa-space-shuttle","fa-spinner","fa-spoon","fa-spotify","fa-square","fa-square-o","fa-stack-exchange","fa-stack-overflow","fa-star","fa-star-half","fa-star-half-empty","fa-star-half-full","fa-star-half-o","fa-star-o","fa-steam","fa-steam-square","fa-step-backward","fa-step-forward","fa-stethoscope","fa-sticky-note","fa-sticky-note-o","fa-stop","fa-stop-circle","fa-stop-circle-o","fa-street-view","fa-strikethrough","fa-stumbleupon","fa-stumbleupon-circle","fa-subscript","fa-subway","fa-suitcase","fa-sun-o","fa-superscript","fa-support","fa-table","fa-tablet","fa-tachometer","fa-tag","fa-tags","fa-tasks","fa-taxi","fa-television","fa-tencent-weibo","fa-terminal","fa-text-height","fa-text-width","fa-th","fa-th-large","fa-th-list","fa-thumb-tack","fa-thumbs-down","fa-thumbs-o-down","fa-thumbs-o-up","fa-thumbs-up","fa-ticket","fa-times","fa-times-circle","fa-times-circle-o","fa-tint","fa-toggle-down","fa-toggle-left","fa-toggle-off","fa-toggle-on","fa-toggle-right","fa-toggle-up","fa-trademark","fa-train","fa-transgender","fa-transgender-alt","fa-trash","fa-trash-o","fa-tree","fa-trello","fa-tripadvisor","fa-trophy","fa-truck","fa-try","fa-tty","fa-tumblr","fa-tumblr-square","fa-turkish-lira","fa-tv","fa-twitch","fa-twitter","fa-twitter-square","fa-umbrella","fa-underline","fa-undo","fa-university","fa-unlink","fa-unlock","fa-unlock-alt","fa-unsorted","fa-upload","fa-usb","fa-usd","fa-user","fa-user-md","fa-user-plus","fa-user-secret","fa-user-times","fa-users","fa-venus","fa-venus-double","fa-venus-mars","fa-viacoin","fa-video-camera","fa-vimeo","fa-vimeo-square","fa-vine","fa-vk","fa-volume-down","fa-volume-off","fa-volume-up","fa-warning","fa-wechat","fa-weibo","fa-weixin","fa-whatsapp","fa-wheelchair","fa-wifi","fa-wikipedia-w","fa-windows","fa-won","fa-wordpress","fa-wrench","fa-xing","fa-xing-square","fa-y-combinator","fa-y-combinator-square","fa-yahoo","fa-yc","fa-yc-square","fa-yelp","fa-yen","fa-youtube","fa-youtube-play","fa-youtube-square"]
        render(template: "/dashboard/configuracion/producto/iconos", model: [data:data,idCampo:params.idCampo])
    }
    def altaCampoFormulario (){
        println params
        def campoFormulario = CampoFormulario.get(params.idCampoFormulario as long);
        println campoFormulario
        render(template: "/dashboard/configuracion/producto/altaCampoFormulario",model: [campoFormulario:campoFormulario])
    }
    def altaDocumentoProducto(){
        println params
        def producto = Producto.get(params.idProducto as long)
        render(template: "/dashboard/configuracion/documentoProducto/altaDocumentoProducto",model: [producto:producto])
    }
      def altaPlazoProducto(){
        println params
        def producto = Producto.get(params.idProducto as long)
        render(template: "/dashboard/configuracion/plazoProducto/altaPlazoProducto",model: [producto:producto])
    }
      def altaLimitePlazoProducto(){
        println params
        def producto = Producto.get(params.idProducto as long)
        render(template: "/dashboard/configuracion/limiteProducto/altaLimiteProducto",model: [producto:producto])
    }
      def altaGarantiaProducto(){
        println params
        def producto = Producto.get(params.idProducto as long)
        render(template: "/dashboard/configuracion/garantiaProducto/altaGarantiaProducto",model: [producto:producto])
    }
      def registrarDocumentoProducto(){
        println params
        def respuesta = [:]
        def documentoProducto  = new DocumentoProducto ()
        def tipoDeDocumento = TipoDeDocumento.get(params.documentoId as long)
        def producto = Producto.get(params.idProducto as long)
         documentoProducto.activo = params.activo.toBoolean()
         documentoProducto.obligatorio = params.obligatorio.toBoolean()
         documentoProducto.tipoDeDocumento = tipoDeDocumento
         documentoProducto.producto = producto
        if(documentoProducto.save(flush:true)){
            respuesta.exito = true
            respuesta.mensaje = "Documento Asignado"
            respuesta.documentoProducto = documentoProducto
            respuesta.tipoDeDocumento = tipoDeDocumento
            respuesta.producto = producto
        }
        println respuesta
        render respuesta as JSON
  
    }
    
      def registrarPlazoProducto(){
        println params
        def respuesta = [:]
        def plazoProducto  = new PlazoProducto ()
        def periodicidad = Periodicidad.get(params.periodicidadId as long)
        def producto = Producto.get(params.idProducto as long)
        if (params.usarListaDePlazos.toBoolean() == true){
                plazoProducto.usarListaDePlazos = params.usarListaDePlazos.toBoolean()
                plazoProducto.plazosPermitidos = params.plazosPermitidos.toString()
                plazoProducto.plazoMaximo = 0
                plazoProducto.plazoMinimo = 0
                plazoProducto.saltoSlider = 0 as long
            }else {
                 plazoProducto.usarListaDePlazos = params.usarListaDePlazos.toBoolean()
                 plazoProducto.saltoSlider = params.saltoSlider as long
                 plazoProducto.plazosPermitidos = 0
                 plazoProducto.plazoMaximo = params.plazoMaximo as long
                 plazoProducto.plazoMinimo = params.plazoMinimo as long
            }
         plazoProducto.importeMaximo = params.importeMaximo as float
         plazoProducto.importeMinimo = params.importeMinimo as float
         plazoProducto.periodicidad = periodicidad 
         plazoProducto.usarListaDePlazos = true
         plazoProducto.usarEnPerfilador = false
         plazoProducto.producto = producto
        if(plazoProducto.save(flush:true)){
            respuesta.exito = true
            respuesta.mensaje = "Plazo  Asignado Correctamente."
            respuesta.plazoProducto = plazoProducto
            respuesta.periodicidad = periodicidad
        }
        println respuesta
        render respuesta as JSON
  
    }

        def registrarLimitePlazoProducto(){
        println params
        def respuesta = [:]
        def limitePlazoProducto  = new LimitePlazoProducto ()
        def periodicidad = Periodicidad.get(params.periodicidadId as long)
        def producto = Producto.get(params.idProducto as long)
         limitePlazoProducto.limiteMaximo = params.limiteMaximo as float
         limitePlazoProducto.limiteMinimo = params.limiteMinimo as float
         limitePlazoProducto.plazo = params.plazo as long
         limitePlazoProducto.periodicidad = periodicidad
         limitePlazoProducto.producto = producto
        if(limitePlazoProducto.save(flush:true)){
            respuesta.exito = true
            respuesta.mensaje = "Limite asignado correctamente."
            respuesta.limitePlazoProducto = limitePlazoProducto
            respuesta.periodicidad = limitePlazoProducto.periodicidad
        }
        render respuesta as JSON
  
    }
    
    
    def registrarGarantiaProducto(){
        println params
        def respuesta = [:]
        def garantiaProducto  = new GarantiaProducto ()
        def tipoDeGarantia = TipoDeGarantia.get(params.tipoDeGarantiaId as long)
        def producto = Producto.get(params.idProducto as long)
         garantiaProducto.cantidadMaxima = params.cantidadMaxima as float
         garantiaProducto.cantidadMinima = params.cantidadMinima as float
         garantiaProducto.descripcion = params.descripcion
         garantiaProducto.tipoDeGarantia = tipoDeGarantia
         garantiaProducto.producto = producto
        if(garantiaProducto.save(flush:true)){
            respuesta.exito = true
            respuesta.mensaje = "Garantia asignada correctamente."
            respuesta.garantiaProducto = garantiaProducto
            respuesta.tipoDeGarantia = garantiaProducto.tipoDeGarantia
        }
        render respuesta as JSON
  
    }
    
    
    
    
    def eliminarDocumentoProducto(){
        println params
        def respuesta = [:]
        //def producto = DocumentoProducto.findAllWhere(producto:documentoProducto.producto)
        def documentoProducto = DocumentoProducto.get(params.idDocumentoProducto as long)
        def producto = documentoProducto.producto
        documentoProducto.delete(flush:true)
        println "eliminado"
        def documentoProductoTemp = DocumentoProducto.findAllWhere(producto:producto)
        respuesta.ok = true 
        respuesta.mensaje ="bien eliminado"
        respuesta.documentoProducto = documentoProductoTemp
        respuesta.tipoDeDocumento = documentoProductoTemp.tipoDeDocumento
        println respuesta
        render respuesta as JSON
    }
      def eliminarPlazoProducto(){
        println params
        def respuesta = [:]
        def plazoProducto = PlazoProducto.get(params.idPlazoProducto as long)
        def producto = plazoProducto.producto
        plazoProducto.delete(flush:true)
        def plazoProductoTemp = PlazoProducto.findAllWhere(producto:producto)
        respuesta.ok = true
        respuesta.mensaje ="Plazo eliminado Correctamente."
        respuesta.plazoProducto = plazoProductoTemp
        respuesta.periodicidad = plazoProductoTemp.periodicidad
        
        println respuesta
        render respuesta as JSON
    }
       def eliminarLimitePlazoProducto(){
        println params
        def respuesta = [:]
        def limitePlazoProducto = LimitePlazoProducto.get(params.idLimiteProducto as long)
        def producto = limitePlazoProducto.producto
        println limitePlazoProducto
        limitePlazoProducto.delete(flush:true)
        def limitePlazoProductoTemp = LimitePlazoProducto.findAllWhere(producto:producto)
        println "eliminado"
        respuesta.ok = true
        respuesta.mensaje ="Limite eliminado Correctamente."
        respuesta.limitePlazoProducto = limitePlazoProductoTemp
        respuesta.periodicidad = limitePlazoProductoTemp.periodicidad
       
        render respuesta as JSON
    }
    
        def eliminarGarantiaProducto(){
        println params
        def respuesta = [:]
        def garantiaProducto = GarantiaProducto.get(params.idGarantiaProducto as long)
        def producto = garantiaProducto.producto
        println garantiaProducto
        garantiaProducto.delete(flush:true)
        def garantiaProductoTemp = GarantiaProducto.findAllWhere(producto:producto)
        println "eliminado"
        respuesta.ok = true
        respuesta.mensaje ="Garantía eliminada Correctamente."
        respuesta.garantiaProducto = garantiaProductoTemp
        respuesta.tipoDeGarantia = garantiaProductoTemp.tipoDeGarantia
       
        render respuesta as JSON
    }
    
    
    
    
    def save(){
        println params
        def respuesta = [:]    
        def respuesta2 = [:]  
        def producto = new Producto ()
        producto.nombreDelProducto = params.nombreDelProducto
        producto.tituloEnCotizador = params.tituloEnCotizador
        producto.claveDeProducto   = params.claveDeProducto 
        producto.descripcion = params.descripcion
        producto.montoMaximo = params.montoMaximo as float
        producto.montoMinimo = params.montoMinimo as float
        producto.tasaDeInteres = params.tasaDeInteres  as float
        producto.claseIconoPaso = params.claseIconoPaso 
        producto.entidadFinanciera = springSecurityService.currentUser.entidadFinanciera
        producto.cat = params.cat as float
        // producto.rutaImagenDefault = "/var/uploads/kosmos/config/"
        producto.usoEnPerfilador = params.usoEnPerfilador.toBoolean()
        producto.segundoCredito = params.segundoCredito.toBoolean()
        producto.activo = params.activo.toBoolean()
        
        def marca=Marca.get(params.marcaId as long)
        producto.marca = marca          
        
        def tipoDeProducto=TipoDeProducto.get(params.tipoDeProductoId as long)
        producto.tipoDeProducto = tipoDeProducto
        
        //filtrar por la lista enviada
        def tipoDeTasa=TipoDeTasaDeInteres.get(params.tipoDeTasaDeInteresId as long)
        producto.tipoDeTasa = tipoDeTasa
        
        //filtrar por la lista enviada
        def esquema=Esquema.get(params.esquemaId as long)
        producto.esquema = esquema
        
        def uploadedFile = params.rutaImagenDefault
        def fileLabel = ".${uploadedFile.originalFilename.split("\\.")[-1]}"
        InputStream inputStream = uploadedFile.inputStream
        println respuesta2.archivo = inputStream
        println respuesta2.nombreDelArchivo = uploadedFile.originalFilename
        println respuesta2.extension = fileLabel.toLowerCase()
        def usuario = springSecurityService.currentUser
        def nombreEmpresa = usuario.entidadFinanciera.nombre
            nombreEmpresa = nombreEmpresa.replaceAll("[^a-zA-Z0-9]+","")
        def ruta = "/var/uploads/kosmos/config/" + nombreEmpresa +"/imagenesCotizador"
        producto.rutaImagenDefault = ruta + "/" + respuesta2.nombreDelArchivo
        def subdir = new File(ruta)
        subdir.mkdir()
        println (producto.rutaImagenDefault)
        File file = new File(producto.rutaImagenDefault)
        if (file.exists() || file.createNewFile()) {
            file.withOutputStream{fos->
                fos << respuesta2.archivo
            }
        }
        if(producto.save(flush:true)){
            respuesta.ok = true
            respuesta.mensaje = "El producto se registro correctamente."
            respuesta.producto = producto
            respuesta.marca = producto.marca.nombre
        }
        else {
            if (producto.hasErrors()) {
                producto.errors.allErrors.each {
                    println it
                }
            }
            respuesta.error = true
            respuesta.mensaje = "Ocurrio un problema al guardar el Producto. Intente nuevamente más tarde."
        }
        println respuesta 
        render respuesta as JSON
        
    }
    
    
    
    def update(){
        println params
        def respuesta = [:]
        def producto = Producto.get(params.idProducto as long)
        producto.nombreDelProducto = params.nombreDelProducto
        producto.tituloEnCotizador = params.tituloEnCotizador
        producto.claveDeProducto   = params.claveDeProducto as long
        producto.descripcion = params.descripcion
        producto.montoMaximo = params.montoMaximo as float
        producto.montoMinimo = params.montoMinimo as float
        producto.tasaDeInteres = params.tasaDeInteres as float
        producto.claseIconoPaso = params.claseIconoPaso 
        producto.cat = params.cat as float
        //producto.esquema = params.nombreDelProducto
        //producto.marca = params.nombreDelProducto
        //producto.tipoDeProducto = params.nombreDelProducto
        //producto.tipoDeTasa = params.nombreDelProducto
        // producto.rutaImagenDefault = params.rutaImagenDefault
        producto.usoEnPerfilador = params.usoEnPerfilador.toBoolean()
        producto.segundoCredito = params.segundoCredito.toBoolean()
        producto.activo = params.activo.toBoolean()
        if(producto.save(flush:true)){
            println "Se modifico"
            respuesta.ok = true
            respuesta.mensaje = "Se Actualizo el Producto Correctamente."
        }
        else {
            if (producto.hasErrors()) {
                producto.errors.allErrors.each {
                    println it
                }
            }
            respuesta.error = true
            respuesta.mensaje = "Ocurrio un problema al guardar el Producto. Intente nuevamente más tarde."
        }
        println respuesta
        render respuesta as JSON
    }
        
    
    def modificarDocumentoproducto (){
        println params
        def documentoProducto = DocumentoProducto.get(params.idDocumentoProducto as long)
        def faltan = []
        println  documentoProducto
        def tipoDeDocumento = TipoDeDocumento.findAll()
        tipoDeDocumento.each{
           if (!documentoProducto.tipoDeDocumento.nombre.contains(it.nombre)){
               def respuesta = [:]
                respuesta.nombre = it.nombre
                respuesta.id = it.id
                faltan << respuesta
           }
         }
        render(template: "/dashboard/configuracion/producto/detalleDocumentoProducto", model: [documentoProducto:documentoProducto,faltan:faltan])
    }
    
       def modificarPlazoProducto (){
        println params
        def plazoProducto = PlazoProducto.get(params.idPlazoProducto as long)
        def faltan = []
        def periodicidad = Periodicidad.findAll()
        periodicidad.each{
           if (!plazoProducto.periodicidad.nombre.contains(it.nombre)){
               def respuesta = [:]
                respuesta.nombre = it.nombre
                respuesta.id = it.id
                faltan << respuesta
           }
         }
        println plazoProducto
        println faltan
        render(template: "/dashboard/configuracion/plazoProducto/detallePlazoProducto", model: [plazoProducto:plazoProducto,faltan:faltan])
    }
    
    
    def modificarLimiteProducto (){
        println params
        def limitePlazoProducto = LimitePlazoProducto.get(params.idLimiteProducto as long)
        def faltan = []
        def periodicidad = Periodicidad.findAll()
        periodicidad.each{
           if (!limitePlazoProducto.periodicidad.nombre.contains(it.nombre)){
               def respuesta = [:]
                respuesta.nombre = it.nombre
                respuesta.id = it.id
                faltan << respuesta
           }
         }
        println limitePlazoProducto
        println faltan
        render(template: "/dashboard/configuracion/limiteProducto/detalleLimiteProducto", model: [limitePlazoProducto:limitePlazoProducto,faltan:faltan])
    }
    
       def modificarGarantiaProducto (){
        println params
        def garantiaProducto = GarantiaProducto.get(params.idGarantiaProducto as long)
        def faltan = []
        def tipoDeGarantia = TipoDeGarantia.findAll()
        tipoDeGarantia.each{
           if (!garantiaProducto.tipoDeGarantia.nombre.contains(it.nombre)){
               def respuesta = [:]
                respuesta.nombre = it.nombre
                respuesta.id = it.id
                faltan << respuesta
           }
         }
        println garantiaProducto
        println faltan
        render(template: "/dashboard/configuracion/garantiaProducto/detalleGarantiaProducto", model: [garantiaProducto:garantiaProducto,faltan:faltan])
    }
    
    
    
    
    
    def updateDocumentoProducto (){
         println params
        def respuesta = [:]
        def documentoProducto = DocumentoProducto.get(params.idDocumentoProducto as long)
        def producto = documentoProducto.producto
        def tipoDeDocumento = TipoDeDocumento.get(params.documentoId as long)
        documentoProducto.obligatorio = params.obligatorio.toBoolean()
        documentoProducto.activo = params.activo.toBoolean()
        documentoProducto.tipoDeDocumento = tipoDeDocumento
        if(documentoProducto.save(flush:true)){
            respuesta.ok= true
            respuesta.mensaje = "El documento se ha Actualizado"
            def documentoProductoTemp = DocumentoProducto.findAllWhere(producto:producto)
            respuesta.documentoProducto = documentoProductoTemp
            respuesta.tipoDeDocumento = documentoProductoTemp.tipoDeDocumento
        }
        render respuesta as JSON
        
    }
       def updatePlazoProducto (){
         println params
        def respuesta = [:]
        def plazoProducto = PlazoProducto.get(params.idPlazoProducto as long)
        def periodicidad = Periodicidad.get(params.periodicidadId as long)
        def producto = plazoProducto.producto
            if (params.usarListaDePlazos.toBoolean() == true){
                plazoProducto.usarListaDePlazos = params.usarListaDePlazos.toBoolean()
                plazoProducto.plazosPermitidos = params.plazosPermitidos.toString()
                plazoProducto.plazoMaximo = 0
                plazoProducto.plazoMinimo = 0
                plazoProducto.saltoSlider = 0 as long
            }else {
                 plazoProducto.usarListaDePlazos = params.usarListaDePlazos.toBoolean()
                 plazoProducto.saltoSlider = params.saltoSlider as long
                 plazoProducto.plazosPermitidos = "0"
                 plazoProducto.plazoMaximo = params.plazoMaximo as long
                 plazoProducto.plazoMinimo = params.plazoMinimo as long
            }
        plazoProducto.importeMaximo = params.importeMaximo as float
        plazoProducto.importeMinimo = params.importeMinimo as float
        plazoProducto.periodicidad = periodicidad
        if(plazoProducto.save(flush:true)){
            respuesta.ok= true
            respuesta.mensaje = "El Plazo se ha Actualizado"
            def plazoProductoTemp = PlazoProducto.findAllWhere(producto:producto)
                respuesta.plazoProducto = plazoProductoTemp
                respuesta.periodicidad = plazoProductoTemp.periodicidad
        }else{
            respuesta.error = true
            respuesta.mensaje = "No se pudo Actualizar"
        }
        render respuesta as JSON
        
    }
    
        def updateLimitePlazoProducto (){
         println params
        def respuesta = [:]
        def limitePlazoProducto = LimitePlazoProducto.get(params.idLimitePlazoProducto as long)
        def periodicidad = Periodicidad.get(params.periodicidadId as long)
        def producto = limitePlazoProducto.producto
        limitePlazoProducto.limiteMaximo = params.limiteMaximo as float
        limitePlazoProducto.limiteMinimo = params.limiteMinimo as float
        limitePlazoProducto.plazo = params.plazo as long
        limitePlazoProducto.periodicidad = periodicidad
        if(limitePlazoProducto.save(flush:true)){
            respuesta.ok= true
            respuesta.mensaje = "El Limite se ha Actualizado"
            def limitePlazoProductoTemp = LimitePlazoProducto.findAllWhere(producto:producto)
            respuesta.limitePlazoProducto = limitePlazoProductoTemp
            respuesta.periodicidad = limitePlazoProductoTemp.periodicidad
        }
        println respuesta 
        render respuesta as JSON
        
    }
    
    
     def updateGarantiaProducto (){
         println params
        def respuesta = [:]
        def garantiaProducto = GarantiaProducto.get(params.idGarantiaProducto as long)
        def tipoDeGarantia = TipoDeGarantia.get(params.tipoDeGarantiaId as long)
        def producto = garantiaProducto.producto
        garantiaProducto.cantidadMaxima = params.cantidadMaxima as float
        garantiaProducto.cantidadMinima = params.cantidadMinima as float
        garantiaProducto.descripcion = params.descripcion
        garantiaProducto.tipoDeGarantia = tipoDeGarantia
        if(garantiaProducto.save(flush:true)){
            respuesta.ok= true
            respuesta.mensaje = "El Limite se ha Actualizado"
            def garantiaProductoTemp = GarantiaProducto.findAllWhere(producto:producto)
            respuesta.garantiaProducto = garantiaProductoTemp
            respuesta.tipoDeGarantia = garantiaProductoTemp.tipoDeGarantia
        }
        render respuesta as JSON
        
    }
    
}
