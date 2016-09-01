<section class="container paddingTop30 paddingBottom20 clearFix contentHeight">
    <div class="padding20">
        <div class="clearFix marginBottom30">
            <div class="floatLeft col4 col6-tab col12-mob clearFix marginBottom20 ccInfo">
                <p class="correctaBoxLabel">¿TIENES UNA TARJETA DE CRÉDITO?</p>
                <div class="correctaBox floatLeft hasCc">
                    <p class="center paddingTop15 paddingBottom15 ">SI</p>
                </div>
                <div class="floatLeft correctaBox">
                    <p class="center paddingTop15 paddingBottom15 lightGray">NO</p>
                </div>
                <input type="hidden" name="tCredito" id="tCredito">
            </div>
            <div class="floatLeft col3 col6-tab col12-mob">
                <p class="gray font14 letterspacing1.1">ÚLTIMOS CUATRO DÍGITOS</p>
                <input class="inPuts4a marginTop15 headingColor" type="text" disabled id="numeroTarjeta" name="numeroTarjeta" placeholder="0000"/>
            </div>

            <div class="col5 col12-tab floatLeft marginTop30">
                <div class="rectangleRound font11 letterspacing0.5 center">LAS TARJETAS DE DÉBITO Y TIENDAS DEPARTAMENTALES NO SON CONSIDERADAS TARJETAS DE CRÉDITO</div>
            </div>
        </div>
        <div class="clearFix marginBottom30">
            <div class="floatLeft col4 col6-tab col12-mob clearFix marginBottom20 ccInfo">
                <p class="correctaBoxLabel">¿ERES TÍTULAR DE UN CREDITO HIPOTECARIO?</p>
                <div class="correctaBox floatLeft hasCc">
                    <p class="center paddingTop15 paddingBottom15 lightGray">SI</p>
                </div>
                <div class="floatLeft correctaBox">
                    <p class="center paddingTop15 paddingBottom15 lightGray">NO</p>
                </div>
                <input type="hidden" name="creditoH" id="creditoH">
            </div>
            <div class="floatLeft col4 col6-tab col12-mob clearFix marginBottom20 ccInfo">
                <p class="correctaBoxLabel">¿HAZ SIDO TÍTULAR DE UN CREDITO AUTOMOTRIZ EN LOS ÚLTIMOS 24 MESES?</p>
                <div class="correctaBox floatLeft hasCc">
                    <p class="center paddingTop15 paddingBottom15 lightGray">SI</p>
                </div>
                <div class="floatLeft correctaBox">
                    <p class="center paddingTop15 paddingBottom15 lightGray">NO</p>
                </div>
                <input type="hidden" name="creditoA" id="creditoA">
            </div>
        </div>
        <div id="accionesNormales" class="creditBtns">
            <div class="col5half col12-mob floatLeft">
                <a id="consultarBuroBtn">
                    <div class="colorGreen buttonM radius100 font16 colorWhite letterspacing1.5 mobileAuto consultarBc" style="cursor:pointer;">
                        CONSULTAR MI BURÓ DE CRÉDITO
                    </div>
                </a>
            </div>
            <div class="col1 col12-mob center gray floatLeft padingAside20 marginTop15 marginBottom15 block">Ó</div>
            <div class="col5half col12-mob floatLeft">
                <div class="buttonM darkGray radius100 font16 colorWhite letterspacing1.5 mobileAuto" style="cursor:pointer;">
                    SUBIR MI PDF DE BURÓ DE CRÉDITO
                </div>
            </div>
            <div class="loadingContainer clearFix clearFloat">
                <div class="loadingBar marginTop50 hide">
                    <div class="loadingActive"></div>
                    <p class="mAuto lightGray font14 marginTop10">CONSULTANDO TU BURÓ DE CRÉDITO</p>
                </div>
            </div>
        </div>
        <div id="accionesSuccess" style="display: none;" class="clearFix clearFloat">
            <div class="colorGreen buttonM radius100 font16 colorWhite letterspacing1.5 center blockAuto">
                CONSULTA EXITOSA
            </div>
        </div>
        <div id="accionesError" style="display: none;" class="clearFix">
            <div class="col5 col12-tab floatLeft">
                <div class="buttonOrange buttonM radius100 font16 colorWhite letterspacing1.5 blockAuto">CONSULTA FALLIDA</div>
            </div>
            <div class="clearFloat tablet mobile"></div>
            <div class="phoneStaus col6 col8-tab col12-mob blockAuto floatRight clearFix">
                <div class="col9  col8-mob floatLeft">
                    <div class="clearFix paddingAside15">
                        <img class="floatLeft" src="${resource(dir:'images', file:'phone.png')}" alt="contact" title="contact"/>
                        <div class="floatLeft marginLeft10">
                            <p class="formTitleColor font16 letterspacing1.5">NOS COMUNICAREMOS</p>
                            <p class="formTitleColor font16 letterspacing1.5">CONTIGO VIA TELEFONICA EN:</p>
                        </div>
                    </div>
                </div>
                <div class="col3 col4-mob floatLeft">
                    <p class="buttonM colorGreen radius100 colorWhite letterspacing1.5">2:00 min</p>
                </div>
            </div>

        </div>
    </div>
</section>
<footer class="footerContainer">
    <g:render template="stepBar"/>
    <div class="mobile">
        <div class="paddingAside15 clearFix">
            <div class="grayrectangle floatLeft marginRight10">Atras</div>
            <div class="greenrectangle floatLeft nextBtn">Ir al paso 6</div>
        </div>
    </div>
</footer>