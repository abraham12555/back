<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Kosmos - Solicitud Login</title>
        <meta name="layout" content="solicitud"/>
    </head>
    <body>
        <form id="formRedesSociales" method="POST" action="formulario">
            <input type="hidden" id="datosFb" name="datosFb"/>
            <input type="hidden" id="datosGoogle" name="datosGoogle"/>
            <input type="hidden" name="origen" value="login" />
            <input type="hidden" name="paso" value="1" />
        </form>
        <header class="formularioHeader clearFix">
            <div class="container">
                <div class="center">
                    <img class="logo" src="${resource(dir:'images', file:'kosmos-logo.png')}" alt="Logo" title="Logo" />
                </div>
            </div>
        </header>
        <section class="container">
            <div class="marginTop20">  
                <div class="whiteBox width550 autoMargin marginTop13">
                    <div class="paddingAside20">
                        <div class="paddingTop35 marginBottom30">
                            <h1 class="center font25 fontWeight500 darkBluetitle">CONECTA TU SOLICITUD</h1>
                        </div>
                        <div class="autoMargin clearFix">
                            <div class="borderTopResumen clearFix">
                                <div class="col10 col12-mob autoMargin">
                                    <div class="paddingTop18 marginBottom16">
                                        <p class="center formTitleColor marginBottom30 font18">
                                            Conecta tus redes sociales y te ayudamos <br/> a pre-llenar tu formulario. Es más fácil y mas rápido.
                                        </p>
                                        <a onclick="fb_login();">
                                            <div class="marginBottom20">
                                                <div class="width350 facebookColor autoMargin clearFix pointer">
                                                    <div class="floatLeft col2 col3-mob paddingTop5">
                                                        <img class="happines" src="${resource(dir:'images', file:'fb-icon.svg')}" alt="FB logo" title="FacebookLogo" />
                                                    </div>
                                                    <div class="floatLeft col10 col9-mob">
                                                        <p class="borderLeft paddingTop20 paddingBottom15 center font14 colorWhite fontWeight500 letterspacing1">
                                                            CONECTATE CON FACEBOOK
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </a>
                                        <a class="test" id="customBtn">
                                            <div class="width350 googleColor autoMargin clearFix pointer">
                                                <div class="floatLeft col2 col3-mob paddingTop18">
                                                    <img class="happines" src="${resource(dir:'images', file:'google-icon.svg')}" alt="Google+ logo" title="GoogleLogo" />
                                                </div>
                                                <div class="floatLeft col10 col9-mob">
                                                    <p class="borderLeft paddingTop20 paddingBottom15 center font14 colorWhite fontWeight500 letterspacing1">
                                                        CONECTATE CON GOOGLE
                                                    </p>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="clearFix">
                                <div class="col12 autoMargin">
                                    <div class="paddingTop18 marginBottom20">
                                        <p class="center formTitleColor font18">
                                            Tomale una foto a tus documentos y pre-llena tu formulario.
                                        </p>
                                        <p class="center colorblue font18 marginBottom20">
                                            ¡Puedes obtener tu crédito hasta en 10 minutos!
                                        </p>

                                        <div class="marginBottom20">
                                            <div class="width350 boxGreen autoMargin pointer">
                                                <p class="paddingTop20 paddingBottom15 center font14 colorWhite fontWeight500 letterspacing1">
                                                    SUBE TU IDENTIFICACIÓN OFICIAL
                                                </p>
                                            </div>
                                        </div>
                                        <div class="width350 boxGreen pointer autoMargin">
                                            <p class="paddingTop20 paddingBottom15 center font14 colorWhite fontWeight500 letterspacing1">
                                                SUBE TU COMPROBANTE DE DOMICILIO
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="borderTopResumen">
                                <div class="marginTop20 marginBottom20">
                                    <form id="formLogin" method="POST" action="formulario">
                                        <input type="hidden" name="origen" value="noLogin" />
                                        <input type="hidden" name="paso" value="1" />
                                    </form>
                                    <a id="submitLogin" class="blockAuto pointer width400 blueButton paddingTop15 paddingBottom15 center font25 colorWhite fontWeight500 letterspacing2">
                                        EMPEZAR MI SOLICITUD
                                    </a>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </section>
        <script type="text/javascript">
            $(document).ready(function() {
            initGoogle();
            initFB();
            console.log("Inicializando Api's");
            $('#submitLogin').click(function () {
            $('#formLogin').submit();
            });
            });
        </script>
    </body>
</html>
