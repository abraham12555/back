<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Kosmos - Solicitud</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
        <meta charset="utf-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <script src = "https://plus.google.com/js/client:platform.js" async defer></script>
        <script src = "https://apis.google.com/js/api:client.js"></script>
        <g:external dir="css" file="bootstrap.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="formulario.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="progressbar.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="jquery-ui.min.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="sweetalert.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="jquery.modal.min.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="animate.min.css" title="text/css" rel="stylesheet" />
        <g:external dir="js" file="jquery-3.0.0.min.js" />
        <g:external dir="js" file="jquery-ui.min.js" />
        <g:external dir="js" file="typeahead.js" />
        <g:external dir="js" file="jquery.validate.min.js" />
        <g:external dir="js" file="sweetalert.min.js" />
        <g:external dir="js" file="jquery.modal.min.js" />
        <g:external dir="js" file="photobooth_min.js" />
        <g:external dir="js" file="dropzone.js" />
        <g:external dir="js" file="formulario.js" />
        <g:external dir="js" file="curp.js" />
        <g:external dir="js" file="google.js" />
        <g:external dir="js" file="facebook.js" />
    </head>
    <body>
        <header class="topHeader">
            <div class="formularioHeader clearFix">
                <div class="container clearFix">
                    <img class="logo floatLeft desktop" src="${resource(dir:'images', file:'kosmos-logo.png')}" alt="Logo" title="Logo" />
                    <img class="logoMobile floatLeft mobile tablet" src="${resource(dir:'images', file:'kosmos-mobile-logo.png')}" alt="Logo" title="Logo" />
                    <div class="floatingHeader floatRight clearFix">
                        <div class="floatRight clearFix">
                            <div id="imagenUsuario" class="floatLeft clearFix" >
                                <img  class="userPicture floatLeft" src="${resource(dir:'images', file:'profile.png')}"/>
                            </div>
                            <p id="nombreCliente" class="userName marginTop28 paddingRight5 marginBottom27 floatLeft">¡ Hola ${generales?.nombre ? generales.nombre.toLowerCase().capitalize() : generales?.nombrePersona}!</p>
                            <div class="dropBox floatLeft marginTop28 marginLeft5 desktop">
                                <i class="fa fa-angle-down paddingTop2 paddingRight2 paddingLeft5" aria-hidden="true"></i>
                            </div>
                            <span class="mobile tablet floatLeft mobMenu"><i class="fa fa-bars" aria-hidden="true"></i></span>
                        </div>
                        <div class="floatRight width337 borderGrayRight borderGrayLeft paddingBottom18 paddingTop19  desktop">
                            <div class="urlBox autoMargin">
                                <p class="letterspacing0.8 font14 gray paddingTop10 paddingRight36 paddingBottom10 paddingLeft25 center ">TU URL: KSM.os/ER45</p>
                            </div>
                        </div>
                        <div class="salvadoConatiner floatRight desktop">
                            <p class="salvadoTitle floatLeft paddingTop20 paddingBottom15">salvado automático</p>
                            <img class="floatLeft paddingTop20 paddingLeft16 paddingRight10 paddingBottom15" src="${resource(dir:'images', file:'cloud.png')}" alt="cloud" title="cloud"/>
                        </div>
                    </div>
                </div>
            </div>
            <section class="pasoBanner">
                <div class="container clearFix">
                    <div class="floatLeft col4 col6-tab col10-mob">
                        <div class="paddingTop15 paddingBottom15 borderGrayRight">
                            <p class="gray textUpper letterspacing1 font16 pasoTitle"></p>
                            <p class="headingColor textUpper letterspacing1.5 font25 paddingRight15 stepTitle"></p>
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
    </body>
</html>