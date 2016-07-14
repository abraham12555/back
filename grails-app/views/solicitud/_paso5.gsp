<section class="pasoBanner clearFix">
    <div class="container">
        <div class="floatLeft borderGrayRight">
            <div class="marginTop14 marginBottom14 marginRight95">
                <p class="gray textUpper letterspacing1 font16">paso 5</p>
                <p class="headingColor textUpper letterspacing1.5 font25 marginRight80 paddingBottom8">HISTORIAL CREDITICIO</p>
            </div>
        </div>
        <div class="floatRight">
            <p class="progresoTitle textUpper marginLeft10 floatLeft">tu progreso en la aplicación</p>
            <div class="floatLeft clearFix">
                <div class="width324 borderBlue marginTop38 floatLeft"></div>
                <div class="width36 gradientBlue radius100 paddingTop2 paddingBottom2 paddingRight2 paddingLeft1 marginTop27 floatLeft center">
                    <p class="colorWhite displayInline center paddingTop2 paddingRight5 paddingBottom2 paddingLeft5 font12">95%</p>
                </div>
                <div class="width36 borderGray marginTop38 floatLeft"></div>
                <div class="circle floatLeft"></div>
            </div>
        </div>
    </div>
</section>
<section class="container marginTop28 marginBottom64 clearFix">
    <div class="width950 marginTop69">
        <div class="clearFix marginBottom30">
            <div id="tarjeta" class="floatLeft">
                <p class="gray font14 letterspacing1.1">¿TIENES UNA TARJETA DE CRÉDITO?</p>
                <div id="tarjetaSi" class="hvr-overline-from-center correctaBox marginTop9 floatLeft">
                    <input type="radio" style="display: none !important;" value="SI" name="tCredito" id="radioTSi" onclick="seleccionarRespuesta('tarjeta','Si');">
                    <label class="label_item" for="radioTSi">
                        <p class="center paddingTop20 paddingBottom20 lightGray">SI</p>
                    </label>
                </div>
                <div id="tarjetaNo" class="hvr-overline-from-center correctaBox marginTop9 floatLeft">
                    <input type="radio" style="display: none !important;" value="NO" name="tCredito" id="radioTNo" onclick="seleccionarRespuesta('tarjeta','No');">
                    <label class="label_item" for="radioTNo">
                        <p class="center paddingTop20 paddingBottom20 lightGray">NO</p>
                    </label>
                </div>
            </div>
            <div class="floatLeft marginLeft24">
                <p class="gray font14 letterspacing1.1">ÚLTIMOS CUATRO DÍGITOS</p>
                <div class="marginTop9 floatLeft">
                    <input type="text" maxlength="4" id="numeroTarjeta" style="height: 21px;" class="whiteBox width120 paddingTop20 paddingBottom20 paddingLeft15 headingColor" placeholder="0000" disabled/>
                </div>
            </div>
            <div class="rectangle407 floatLeft marginLeft24 marginTop27">
                <p class="font11 letterspacing0.5 paddingTop15 paddingBottom15 center">LAS TARJETAS DE DÉBITO Y TIENDAS DEPARTAMENTALES NO SON CONSIDERADAS TARJETAS DE CRÉDITO</p>
            </div>
        </div>
        <div class="clearFix marginBottom30">
            <div id="hipoteca" class="floatLeft">
                <p class="gray font14 letterspacing1.1">¿ERES TÍTULAR DE UN</p>
                <p class="gray font14 letterspacing1.1"> CREDITO HIPOTECARIO?</p>
                <div id="hipotecaSi" class="hvr-overline-from-center correctaBox marginTop9 floatLeft">
                    <input type="radio" style="display: none !important;" value="SI" name="creditoH" id="radioHSi" onclick="seleccionarRespuesta('hipoteca','Si');">
                    <label class="label_item" for="radioHSi">
                        <p class="center paddingTop20 paddingBottom20 lightGray">SI</p>
                    </label>
                </div>
                <div id="hipotecaNo" class="hvr-overline-from-center correctaBox marginTop9 floatLeft">
                    <input type="radio" style="display: none !important;" value="NO" name="creditoH" id="radioHNo" onclick="seleccionarRespuesta('hipoteca','No');">
                    <label class="label_item" for="radioHNo">
                        <p class="center paddingTop20 paddingBottom20 lightGray">NO</p>
                    </label>
                </div>
            </div>
            <div id="creditoAutomotriz" class="floatLeft marginLeft24">
                <p class="gray font14 letterspacing1.1">¿HAZ SIDO TÍTULAR DE UN CREDITO</p>
                <p class="gray font14 letterspacing1.1">AUTOMOTRIZ EN LOS ÚLTIMOS 24 MESES?</p>
                <div id="creditoAutomotrizSi" class="hvr-overline-from-center correctaBox marginTop9 floatLeft">
                    <input type="radio" style="display: none !important;" value="SI" name="creditoA" id="radioASi" onclick="seleccionarRespuesta('creditoAutomotriz','Si');">
                    <label class="label_item" for="radioASi">
                        <p class="center paddingTop20 paddingBottom20 lightGray">SI</p>
                    </label>
                </div>
                <div id="creditoAutomotrizNo" class="hvr-overline-from-center correctaBox marginTop9 floatLeft">
                    <input type="radio" style="display: none !important;" value="NO" name="creditoA" id="radioANo" onclick="seleccionarRespuesta('creditoAutomotriz','No');">
                    <label class="label_item" for="radioANo">
                        <p class="center paddingTop20 paddingBottom20 lightGray">NO</p>
                    </label>
                </div>
            </div>
        </div>
        <div id="accionesNormales" class="clearFix">
            <div class="marginLeft40 floatLeft">
                <button type="button" class="colorGreen width354 radius100 font16 colorWhite letterspacing1.5 center paddingTop10 paddingBottom10" onclick="consultarBuro();">CONSULTAR MI BURÓ DE CRÉDITO</button>
            </div>
            <p class="gray floatLeft marginLeft33 marginTop15">Ó</p>
            <div class="marginLeft33 floatLeft">
                <button type="button" class="darkGray width354 radius100 font16 colorWhite letterspacing1.5 center paddingTop10 paddingBottom10">SUBIR MI PDF DE BURÓ DE CRÉDITO</button>
            </div>
            <div id="progresoConsulta" style="display: none;" class="floatLeft loadingContainer clearFloat">
                <div class="loadingBar meter animate">
                    <span style="width: 100%"><span></span></span>
                </div>
                <span style="color: #dfe2f4;">Conectando tu buró de crédito...</span>
            </div>
        </div>
        <div id="accionesError" class="clearFix" style="display: none;">
            <div class="buttonOrange width354 radius100 marginLeft40 floatLeft">
                <p class="font16 colorWhite letterspacing1.5 center paddingTop10 paddingBottom10">CONSULTA FALLIDA</p>
            </div>
            <div class="floatLeft marginLeft40">
                <img class="floatLeft" src="${resource(dir:'images', file:'phone.png')}" alt="contact" title="contact"/>
                <div class="floatLeft marginLeft10">
                    <p class="formTitleColor font16 letterspacing1.5">NOS COMUNICAREMOS</p>
                    <p class="formTitleColor font16 letterspacing1.5">CONTIGO VIA TELEFONICA EN:</p>
                </div>
            </div>
            <div class="width116 colorGreen radius100 floatLeft marginLeft24">
                <p class="colorWhite center textUpper paddingTop10 paddingBottom10 letterspacing1.5">2:00 min</p>
            </div>
        </div>
        <div id="accionesSuccess" class="clearFix" style="display: none;">
            <div class="width440Container">
                <div class="colorGreen width354 radius100 marginLeft40 floatLeft center">
                    <p class="font16 colorWhite letterspacing1.5 center paddingTop10 paddingBottom10">CONSULTA EXITOSA</p>
                </div>
            </div>
        </div>
    </div>
</section>
<footer class="footerContainer">
    <div class="width600 clearFix">
        <div class="grayCircle center floatLeft">
            <p class="paddingTop5 footerTextColor font18">1</p>
        </div>
        <div class="floatLeft line20"></div>
        <div class="grayCircle center floatLeft">
            <p class="paddingTop5 footerTextColor font18">2</p>
        </div>
        <div class="line20 floatLeft"></div>
        <div class="grayCircle center floatLeft">
            <p class="paddingTop5 footerTextColor font18">3</p>
        </div>
        <div class="line20 floatLeft"></div>
        <div class="grayCircle center floatLeft">
            <p class="paddingTop5 footerTextColor font18">4</p>
        </div>
        <div class="line20 floatLeft"></div>
        <div class="blueCircle center floatLeft">
            <p class="colorWhite font18 paddingTop10">5</p>
        </div>
        <div class="line20 floatLeft"></div>
        <div class="rectangle250  center floatLeft">
            <button id="siguientePaso" type="button" class="rectangle250  center textUpper footerTextColor font18" onclick="avanzarPaso(6);" disabled>ir al paso 6</button>
        </div>
    </div>
</footer>
