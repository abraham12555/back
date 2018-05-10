<!DOCTYPE html>
<html lang="es">
    <head>
        <title>${(configuracion?.htmlTitle ? (configuracion?.htmlTitle + " - " )  : "" )}Solicitud</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
        <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
        <meta http-equiv="Pragma" content="no-cache" />
        <meta http-equiv="Expires" content="0" />
        <meta name="format-detection" content="telephone=no" />
        <meta charset="utf-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
        <g:external dir="css" file="bootstrap.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="formulario.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="progressbar.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="jquery-ui-kosmos.css" />
        <g:external dir="css" file="sweetalert.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="jquery.modal.min.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="animate.min.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="jquery.mloading.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="select2.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="vanilla-notify-solicitud.css" title="text/css" rel="stylesheet" />
        <g:if test="${configuracion?.rutaCss}">
            <link rel="stylesheet" href="${configuracion?.rutaCss}">
        </g:if>
        <g:external dir="js" file="jquery-3.0.0.min.js" />
        <g:urlContextAware value="/" var="urlContextAware"/>
        <script type="text/javascript">
            $.contextAwarePathJS = "${urlContextAware}";
        </script>
    </head>
    <body>
        <header class="topHeader">
            <div class="formularioHeader clearFix" style="border-top: solid 5px ${configuracion?.colorBordeSuperior}; background-color: ${configuracion?.colorEncabezado};">
                <div class="headerContainer clearFix">
                    <img class="logo floatLeft desktop" src="${resource(dir:'images', file: configuracion?.rutaLogotipo)}" alt="Logo" />
                    <img class="logoMobile floatLeft mobile tablet" src="${resource(dir:'images', file:'libertad_mobile.png')}" alt="Logo" />
                    <div class="floatingHeader floatRight clearFix">
                        <div class="floatRight clearFix">
                            <div id="imagenUsuario" class="floatLeft clearFix" >
                                <img  class="userPicture floatLeft" src="${resource(dir:'images', file:'profile.png')}"/>
                            </div>
                            <p id="nombreCliente" class="userName marginTop20 paddingRight5 marginBottom27 floatLeft">¡ Hola ${generales?.cliente?.nombre ? generales.cliente.nombre.toLowerCase().capitalize() : (personales?.nombre ?: generales?.nombrePersona)}!</p>
                            <div class="dropBox floatLeft marginTop28 marginLeft5 desktop">
                                <i class="fa fa-angle-down paddingTop2 paddingRight2 paddingLeft5" aria-hidden="true"></i>
                            </div>
                            <span class="mobile tablet floatLeft mobMenu"><i class="fa fa-bars" aria-hidden="true"></i></span>
                        </div>
                        <div class="floatRight width337 borderGrayRight borderGrayLeft paddingBottom18 paddingTop19  desktop center" style="width:350px">
                            <div class="urlBox autoMargin " style= "display: inline-block;">
                                <p class="letterspacing0.8 font14 paddingTop10 paddingRight36 paddingBottom10 center">TU URL: <strong>${session.shortUrl}</strong></p>
                            </div>
                             <div class="urlBox autoMargin" style= "display: inline-block;">
                                 <p id='folioUserId' class="letterspacing0.8 font14 paddingTop10 paddingRight36 paddingBottom10 center">TU FOLIO: <strong>${session.folio}</strong></p>
                            </div>
                        </div>
                        <div class="salvadoConatiner floatRight desktop">
                            <p class="salvadoTitle floatLeft paddingTop20 paddingBottom15">salvado automático</p>
                            <img class="floatLeft paddingTop20 paddingLeft16 paddingRight10 paddingBottom15" src="${resource(dir:'images', file:'cloud.png')}" alt="cloud"/>
                        </div>
                    </div>
                </div>
            </div>
            <section class="pasoBanner">
                <div class="container clearFix">
                    <div class="floatLeft col4 col6-tab col10-mob">
                        <div class="paddingTop15 paddingBottom15 borderGrayRight">
                            <p class="gray textUpper letterspacing1 font16 pasoTitle"> PASO ${pasoActual?.numeroDePaso}</p>
                            <p class="headingColor textUpper letterspacing1.5 font25 paddingRight15 stepTitle">${pasoActual?.titulo}</p>
                        </div>
                    </div>
                    <div class="floatRight col8 col6-tab col2-mob center">
                        <p class="progresoTitle textUpper desktop floatLeft">tu progreso en la aplicación</p>
                        <div class="floatLeft progressWrap clearFix desktop tablet">

                            <div class="progressBar floatLeft">
                                <div class="activeProgress">
                                    <div class="progressTile gradientBlue radius100 colorWhite progressPerc"></div>
                                </div>
                            </div>
                            <div class="circle floatLeft"></div>
                        </div>
                        <div class="progressTile gradientBlue radius100 colorWhite mobile progressPerc"></div>
                    </div>
                </div>
            </section>
        </header>
        <g:layoutBody/>
        <div class="fixed-action-btn toolbar">
            <a class="btn-help btn-large left">
                <i class="fa fa-question" aria-hidden="true"></i> <span>Ayuda &nbsp;&nbsp;&nbsp;&nbsp; 01800 714 02 74</span>
            </a>
        </div>
    </body>
    <script src = "https://plus.google.com/js/client:platform.js" async defer></script>
    <script src = "https://apis.google.com/js/api:client.js"></script>
    <script src="https://maps.google.com.mx/maps/api/js?language=es&key=AIzaSyA1CPLFAC5mFixfxBFB5OYnirzijeTopRI" async defer></script>
    <g:external dir="js" file="jquery-ui-kosmos.js" />
    <g:external dir="js" file="typeahead.js" />
    <g:external dir="js" file="jquery.validate.min.js" />
    <g:external dir="js" file="sweetalert.min.js" />
    <g:external dir="js" file="jquery.modal.min.js" />
    <g:external dir="js" file="photobooth_min.js" />
    <g:external dir="js" file="dropzone.js" />
    <g:external dir="js" file="formularioTest.js" />
    <g:external dir="js" file="jquery.countdown.min.js" />
    <g:external dir="js" file="curp.js" />
    <g:external dir="js" file="rfc.js" />
    <g:external dir="js" file="numbersOnly.js" />
    <g:external dir="js" file="google.js" />
    <g:external dir="js" file="facebook.js" />
    <g:external dir="js" file="jasny-bootstrap.min.js" />
    <g:external dir="js" file="jquery-ui-touch-punch.js" />
    <g:external dir="js" file="jquery.mloading.js" />
    <g:external dir="js" file="select2.js" />
    <g:external dir="js" file="maps.js" />
    <g:external dir="js" file="googleanalyticsSolicitud.js" />
    <g:external dir="js" file="vanilla-notify.min.js" />
</html>
