<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Kosmos - Solicitud</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
        <meta charset="utf-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <link href="../css/bootstrap.css" title="text/css" rel="stylesheet">
        <link href="../css/formulario.css" title="text/css" rel="stylesheet">
        <script src="../js/jquery-3.0.0.min.js"></script>
        <script src = "https://plus.google.com/js/client:platform.js" async defer></script>
        <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
        <script src = "https://apis.google.com/js/api:client.js"></script>
        <script src = "../js/google.js"></script>
        <script src = "../js/facebook.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
            initGoogle();
            initFB();
            console.log("Inicializando Api's");

            $('#button_empezar').hover(function(){
            $('#button_empezar').addClass('disabled');
            },
            function(){
            $('#button_empezar').removeClass('disabled');
            });
            });
        </script>
    </head>
    <body>
        <form id="form_paso1" method="POST" action="paso_1">
            <input type="hidden" id="datos_fb" name="datos_fb"/>
            <input type="hidden" id="datos_google" name="datos_google"/>
        </form>
        <header class="formularioHeader clearFix">
            <div class="container">
                <div class="center">
                    <img class="logo" src="../images/kosmos-logo.png" alt="Logo" title="Logo" />
                </div>
            </div>
        </header>
        <section class="container">
            <div class="conatinerWhite550 autoMargin marginTop13">
                <div class="paddingTop35 marginBottom30">
                    <h1 class="center font25 fontWeight500 darkBluetitle">CONECTA TU SOLICITUD</h1>
                </div>
                <div class="width505 autoMargin clearFix">
                    <div class="borderTopResumen clearFix">
                        <div class="col10 autoMargin">
                            <div class="paddingTop18 marginBottom16">
                                <p class="center formTitleColor marginBottom30 font18">
                                    Conecta tus redes sociales y te ayudamos <br/> a pre-llenar tu formulario. Es mas facil y mas rapido.
                                </p>
                                <a href="#" onclick="fb_login();">
                                    <div class="width350 facebookColor  marginBottom20 autoMargin clearFix">
                                        <div class="floatLeft width75 paddingTop5">
                                            <img class="happines" src="../images/fb-icon.svg" alt="FB logo" title="FacebookLogo" />
                                        </div>
                                        <div class="floatLeft borderLeft width274">
                                            <p class="paddingTop20 paddingBottom15 center font14 colorWhite fontWeight500 letterspacing1">
                                                CONECTATE CON FACEBOOK
                                            </p>
                                        </div>
                                    </div>
                                </a>
                                <br/>
                                <a href="#" class="test" id="customBtn">
                                    <div class="width350 googleColor  marginBottom30 marginTop15 autoMargin clearFix">
                                        <div class="floatLeft width75 paddingTop18">
                                            <img class="happines" src="../images/google-icon.svg" alt="Google+ logo" title="GoogleLogo" />
                                        </div>
                                        <div id="floatLeft borderLeft width274">
                                            <p class="paddingTop20 paddingBottom15 center font14 colorWhite fontWeight500 letterspacing1">
                                                CONECTATE CON GOOGLE
                                            </p>
                                            <div id="response" class="hide">
                                                <textarea id="responseContainer" style="width:100%; height:150px"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="clearFix">
                        <div class="col12 autoMargin">
                            <div class="paddingTop18 marginBottom16">
                                <p class="center formTitleColor font18">
                                    Tomale una foto a tus documentos y pre-llena tu formulario.
                                </p>
                                <p class="center colorblue font18">
                                    ¡Puedes obtener tu crédito hasta en 10 minutos!
                                </p>
                                <br/>
                                <div class="width350 boxGreen marginTop20 marginBottom20 marginTop20 autoMargin">
                                    <p class="paddingTop20 paddingBottom15 center font14 colorWhite fontWeight500 letterspacing1">
                                        SUBE TU IDENTIFICACIÓN OFICIAL
                                    </p>
                                </div>
                                <br/>
                                <div class="width350 boxGreen marginBottom30 autoMargin">
                                    <p class="paddingTop20 paddingBottom15 center font14 colorWhite fontWeight500 letterspacing1">
                                        SUBE TU COMPROBANTE DE DOMICILIO
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br/>
                    <div class="borderTopResumen">
                        <br/>
                        <g:link controller="solicitud" action="paso_1" class="ahref">
                            <div name="button_empezar" id="button_empezar" class="width388 bluebutton marginTop20 marginBottom20 autoMargin ">
                                <p class="paddingTop20 paddingBottom15 center font25 colorWhite fontWeight500 letterspacing2">
                                    EMPEZAR MI SOLICITUD
                                </p>
                            </div>
                        </g:link>
                    </div>
                    <br/>
                </div>
            </div>
        </section>
    </body>
</html>
