<div class="idLightbox hide" id="login">
    <div class="overlay">
        <form id="formRedesSociales" method="POST" action="formulario">
            <input type="hidden" id="datosFb" name="datosFb"/>
            <input type="hidden" id="datosGoogle" name="datosGoogle"/>
            <input type="hidden" name="origen" value="login" />
            <input type="hidden" name="paso" value="1" />
            <input type="hidden" id="pasoInicial" value="0">
            <input type="hidden" id="tipoDeDocumento">
        </form>
        <div class="lightboxPos hide">
            <div class="marginTop20">  
                <div class="whiteBox width550 autoMargin marginTop13">
                    <div class="paddingAside20">
                        <div class="paddingTop35 marginBottom30">
                            <h1 class="center font25 fontWeight500 darkBluetitle">FACILITA TU SOLICITUD</h1>
                        </div>
                        <div class="autoMargin clearFix">
                            <div class="borderTopResumen clearFix">
                                <div class="col12 autoMargin">
                                    <div class="paddingTop18 marginBottom20">
                                        <p class="center formTitleColor font18">
                                            Tómale una foto a tu INE o Pasaporte y te ayudamos a prellenar tu formulario. ¡Es más Fácil y más Rápido!
                                        </p>
                                        <p class="center colorblue font18 marginBottom20">
                                            ¡Puedes Completar tu solicitud hasta en 3 minutos!
                                        </p>

                                        <div class="marginBottom20">
                                            <a id="loginIdOf">
                                                <div class="width350 boxGreen autoMargin pointer">
                                                    <p class="paddingTop20 paddingBottom15 center font14 colorWhite fontWeight500 letterspacing1">
                                                        SUBE TU IDENTIFICACIÓN OFICIAL
                                                    </p>
                                                </div>
                                            </a>
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
        </div>
    </div>
</div>
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
