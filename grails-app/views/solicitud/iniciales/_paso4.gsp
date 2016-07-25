<section class="pasoBanner clearFix">
    <div class="container">
        <div class="floatLeft borderGrayRight">
            <div class="marginTop14 marginBottom14 marginRight95">
                <p class="gray textUpper letterspacing1 font16">paso 4</p>
                <p class="headingColor textUpper letterspacing1.5 font25 paddingRight15 paddingBottom8">datos bancarios</p>
            </div>
        </div>
        <div class="floatRight">
            <p class="progresoTitle textUpper marginLeft10 floatLeft">tu progreso en la aplicación</p>
            <div class="floatLeft clearFix">
                <div class="width240 borderBlue marginTop38 floatLeft"></div>
                <div class="width36 gradientBlue radius100 paddingTop2 paddingBottom2 paddingRight2 paddingLeft1 marginTop27 floatLeft center">
                    <p class="colorWhite displayInline center paddingTop2 paddingRight5 paddingBottom2 paddingLeft5 font12">65%</p>
                </div>
                <div class="width225 borderGray marginTop38 floatLeft"></div>
                <div class="circle floatLeft"></div>
            </div>
        </div>
    </div>
</section>
<div id="consultarInfo">
    <section class="container clearFix">
        <div class="infoBox marginTop15 floatLeft">
            <div class="width850 autoMargin">
                <p class="displayInline width570 floatLeft letterspacing0.5 font11 paddingLeft15 paddingTop15 paddingBottom10">CONECTAR TU CUENTA DE BANCO NOS PERMITE <span class="headingColor">APROBAR TU SOLICITUD EN MINUTOS</span>  O PUEDES SUBIR TUS ESTADOS DE CUENTA Y ESPERAR A QUE EL EQUIPO DE KOSMOS LO REVISÉ LO MAS RÁPIDO POSIBLE  </p>
                <div class="width235 floatRight marginTop12">
                    <button type="button" class="file-upload blueBox1 width235 textUpper colorWhite center font11 paddingTop10 paddingBottom10"><input type="file" class="file-input">SUBIR MI ESTADO DE CUENTA</button>
                </div>
            </div>
        </div>
        <div class="line18 floatLeft"></div>
        <div class="crosCircle floatLeft">
            <p class="center cross"><i class="fa fa-times" aria-hidden="true"></i></p>
        </div>
    </section>
    <section class="container marginTop28 clearFix">
        <div class="box4B">
            <p class="gray font14 paddingTop20 paddingLeft20 marginLeft15 marginBottom10">ELIGE TU BANCO</p>
            <div id="divBanamex" class="hvr-overline-from-center width160 brandingBox marginRight10 marginLeft15 floatLeft">
                <input type="radio" style="display: none !important;" value="banamex" name="banco" id="radioBanamex" onclick="seleccionarBanco('Banamex');">
                <label class="label_item" for="radioBanamex">
                    <img class="width120 marginLeft15 paddingTop25" src="${resource(dir:'images', file:'banamex.png')}"/>
                </label>
            </div>
            <div id="divBancomer" class="hvr-overline-from-center width160 brandingBox marginRight15 floatLeft">
                <input type="radio" style="display: none !important;" value="bancomer" name="banco" id="radioBancomer" onclick="seleccionarBanco('Bancomer');">
                <label class="label_item" for="radioBancomer">
                    <img class="width120 marginLeft15 paddingTop25" src="${resource(dir:'images', file:'bancomer.png')}"/>
                </label>
            </div>
            <div id="divHsbc" class="hvr-overline-from-center width160 brandingBox marginRight15 floatLeft">
                <input type="radio" style="display: none !important;" value="hsbc" name="banco" id="radioHsbc" onclick="seleccionarBanco('Hsbc');">
                <label class="label_item" for="radioHsbc">
                    <img class="width120 marginLeft15 paddingTop25" src="${resource(dir:'images', file:'hsbc.png')}"/>
                </label>
            </div>
            <div id="divSantander" class="hvr-overline-from-center width160 brandingBox marginRight15 floatLeft">
                <input type="radio" style="display: none !important;" value="santander" name="banco" id="radioSantander" onclick="seleccionarBanco('Santander');">
                <label class="label_item" for="radioSantander">
                    <img class="width120 marginLeft15 paddingTop25" src="${resource(dir:'images', file:'santander.png')}"/>
                </label>
            </div>
            <div id="divBanorte" class="hvr-overline-from-center width160 brandingBox marginRight15 floatLeft">
                <input type="radio" style="display: none !important;" value="banorte" name="banco" id="radioBanorte" onclick="seleccionarBanco('Banorte');">
                <label class="label_item" for="radioBanorte">
                    <img class="width120 marginLeft15 paddingTop25" src="${resource(dir:'images', file:'banorte.png')}"/>
                </label>
            </div>
            <div  id="divOtro" class="hvr-overline-from-center width66 brandingBox floatLeft">
                <input type="radio" style="display: none !important;" value="banorte" name="banco" id="radioOtro" onclick="seleccionarBanco('Otro');">
                <label class="label_item" for="radioOtro">
                    <p class="center gray font14 paddingTop25">OTRO</p>
                </label>
            </div>
            <div class="clearFix">
                <input type="hidden" id="intentos" value="0"/>
                <div class="floatLeft paddingTop20 ">
                    <p class="marginLeft15 marginBottom15 gray font14">NÚMERO DE CLIENTE</p>
                    <input class="inPuts4a marginLeft15 paddingLeft15 headingColor textUpper" type="text" name="clientNo" id="clientNo" placeholder="no. de cliente"/>
                </div>
                <div class="floatLeft paddingTop20 ">
                    <p class="marginLeft15 marginBottom15 gray font14">CLAVE</p>
                    <input class="inPuts4a marginLeft15 paddingLeft15 headingColor" type="password" name="clave" id="clave" placeholder="·······"/>
                </div>
                <div class="floatLeft paddingTop20 ">
                    <p class="marginLeft15 marginBottom15 gray font14">TOKEN O PIN</p>
                    <input class="inPuts4a marginLeft15 paddingLeft15 headingColor" type="password" name="tokenNo" id="tokenNo" placeholder="000000"/>
                </div>
            </div>
            <div id="accionesNormal" class="paddingTop20 clearFix">
                <div class="marginLeft15 floatLeft ">
                    <button type="button" class="consultarBox center colorWhite letterspacing1 font16" onclick="consultarBancos();">CONSULTAR DE FORMA SEGURA <i class="fa fa-lock paddingLeft10" aria-hidden="true"></i></button>
                </div>
                <div id="progresoConsulta" style="display:none;" class="floatLeft marginLeft20">
                    <div class="loadingBar meter animate">
                        <span style="width: 100%"><span></span></span>
                    </div>
                    <center><span style="color: #dfe2f4;">Conectando con tu banco...</span></center>
                </div>
            </div>
            <div id="accionesError" style="display:none; padding-top: 50px;" class="clearFix">
                <div class=" center marginLeft20 floatLeft">
                    <button type="button" class="coneccionFallida radius100 center colorWhite paddingTop10 .letterspacing1.5 font16">CONEXIÓN FALLIDA</button>
                </div>
                <div class="width169  radius100 floatLeft marginLeft20">
                    <button type="button" class="darkGray width169 radius100 center colorWhite paddingTop10 paddingBottom10 textUpper font16 .letterspacing1.5" onclick="reintentar();">REINTENTAR</button>
                </div>
                <div class="width292 radius100 floatLeft marginLeft30 marginRight35 ">
                    <button type="button" class="file-upload colorGreen radius100 center colorWhite paddingTop10 paddingBottom10 textUpper font16 .letterspacing1.5 paddingLeft15 paddingRight15"><input type="file" class="file-input">SUBIR ESTADO DE CUENTA</button>
                </div>
            </div>
        </div>
    </section>
</div>
<div id="confirmarConsulta" style="display: none;">
    <section class="container clearFix">
        <div class="infoBoxGreen marginTop15 floatLeft">
            <div class="infoContainer4c">
                <p class="displayInline center floatLeft letterspacing0.5 font13 paddingLeft15 paddingTop15 paddingBottom10 colorWhite marginLeft20 marginTop10">TUS DATOS BANCARIOS SE RECIBIERON EXITOSAMENTE, POR FAVOR VERIFICA QUE TU INFORMACIÓN SEA CORRECTA</p>
            </div>
        </div>
        <div class="line18 colorGreen floatLeft"></div>
        <div class="crosCircle colorGreen floatLeft">
            <p class="center cross colorWhite"><i class="fa fa-times colorWhite" aria-hidden="true"></i></p>
        </div>
    </section>
    <section class="container marginTop28 clearFix">
        <div class="box4B">
            <p class="gray font14 paddingTop20 paddingLeft20 center marginBottom10">DATOS RECIBIDOS</p>
            <div class="clearFix">
                <div class="floatLeft paddingTop20 ">
                    <p class="marginLeft15 marginBottom15 gray font14">DEPOSITOS PROMEDIO (90 DÍAS)</p>
                    <input class="inPuts4a marginLeft15 paddingLeft15 headingColor" type="text" id="dep90" name="dep90" placeholder="$55,000.00 MXN"/>
                </div>
                <div class="floatLeft paddingTop20 ">
                    <p class="marginLeft15 marginBottom15 gray font14">RETIROS PROMEDIO (90 DÍAS)</p>
                    <input class="inPuts4a marginLeft15 paddingLeft15 headingColor" type="text" id="ret90" name="ret90"  placeholder="$55,000.00 MXN"/>
                </div>
                <div class="floatLeft paddingTop20 ">
                    <p class="marginLeft15 marginBottom15 gray font14">SALDO PROMEDIO (90 DÍAS)</p>
                    <input class="inPuts4a marginLeft15 paddingLeft15 headingColor" type="text" id="saldo90" name="saldo90"  placeholder="$55,000.00 MXN"/>
                </div>
                <div id="depositos" class="floatLeft paddingTop20">
                    <p class="marginLeft15 marginBottom15 gray font14">¿ESTA INFORMACIÓN ES CORRECTA?</p>
                    <div id="depositosSi" class="hvr-overline-from-center correctaBox marginLeft15 floatLeft">
                        <input type="radio" style="display: none !important;" value="SI" name="depositoP" id="radioDSi" onclick="seleccionarRespuesta('depositos','Si');">
                        <label class="label_item" for="radioDSi">
                            <p class="center paddingTop20 paddingBottom20 lightGray">SI</p>
                        </label>
                    </div>
                    <div id="depositosNo" class="hvr-overline-from-center floatLeft correctaBox">
                        <input type="radio" style="display: none !important;" value="NO" name="depositoP" id="radioDNo" onclick="seleccionarRespuesta('depositos','No');">
                        <label class="label_item" for="radioDNo">
                            <p class="center paddingTop20 paddingBottom20 lightGray">NO</p>
                        </label>
                    </div>
                </div>
                <div id="retiros" class="floatLeft paddingTop20 ">
                    <p class="marginLeft15 marginBottom15 gray font14">¿ESTA INFORMACIÓN ES CORRECTA?</p>
                    <div id="retirosSi" class="hvr-overline-from-center correctaBox marginLeft15 floatLeft">
                        <input type="radio" style="display: none !important;" value="SI" name="retiroP" id="radioRSi" onclick="seleccionarRespuesta('retiros','Si');">
                        <label class="label_item" for="radioRSi">
                            <p class="center paddingTop20 paddingBottom20 lightGray">SI</p>
                        </label>
                    </div>
                    <div id="retirosNo" class="hvr-overline-from-center floatLeft correctaBox">
                        <input type="radio" style="display: none !important;" value="NO" name="retiroP" id="radioRNo" onclick="seleccionarRespuesta('retiros','No');">
                        <label class="label_item" for="radioRNo">
                            <p class="center paddingTop20 paddingBottom20 lightGray">NO</p>
                        </label>
                    </div>
                </div>
                <div id="saldo" class="floatLeft paddingTop20 ">
                    <p class="marginLeft15 marginBottom15 gray font14">¿ESTA INFORMACIÓN ES CORRECTA?</p>
                    <div id="saldoSi" class="hvr-overline-from-center correctaBox marginLeft15 floatLeft">
                        <input type="radio" style="display: none !important;" value="SI" name="saldoP" id="radioSSi" onclick="seleccionarRespuesta('saldo','Si');">
                        <label class="label_item" for="radioSSi">
                            <p class="center paddingTop20 paddingBottom20 lightGray">SI</p>
                        </label>
                    </div>
                    <div id="saldoNo" class="hvr-overline-from-center floatLeft correctaBox">
                        <input type="radio" style="display: none !important;" value="NO" name="saldoP" id="radioSNo" onclick="seleccionarRespuesta('saldo','No');">
                        <label class="label_item" for="radioSNo">
                            <p class="center paddingTop20 paddingBottom20 lightGray">NO</p>
                        </label>
                    </div>
                </div>
            </div>
            <div class="paddingTop20 clearFix">
                <div class="width385 colorGreen radius100 marginLeft15 floatRight marginTop20 marginRight15">
                    <button type="button" id="confirmacionPaso4" class="width385 colorGreen radius100 center colorWhite paddingTop15 letterspacing1 paddingBottom15 font16" onclick="confirmarPaso4();">CONFIRMAR MI INFORMACIÓN</button>
                </div>
            </div>
        </div>
    </section>
</div>
<section style="margin-left: 17%; margin-bottom: 5%;">
    <div class="paddingTop20 width920">
        <div class="floatLeft">
            <div class="floatLeft">
                <img alt="Protection" src="${resource(dir:'images', file:'protection.png')}"/>
            </div>
            <div class="floatLeft marginLeft10 paddingTop5">
                <p class="formTitleColor font16 letterspacing1">PROTEGEMOS TU INFORMACIÓN</p>
                <p class="colorRed font14">Kosmos no guarda ningún dato bancario.</p>
            </div>
        </div>
        <div class="floatRight">
            <div class="floatLeft">
                <img alt="Key" src="${resource(dir:'images', file:'key.png')}"/>
            </div>
            <div class="floatLeft marginLeft10 paddingTop5">
                <p class="formTitleColor font16 letterspacing1">SOMOS MÁS SEGUROS QUE LOS BANCOS</p>
                <p class="colorRed font14">Todos tus datos están 100% protegidos con la mejor seguridad en la red</p>
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
        <div class="blueCircle center floatLeft">
            <p class="colorWhite font18 paddingTop10">4</p>
        </div>
        <div class="line20 floatLeft"></div>
        <div class="rectangle250 center floatLeft">
            <button id="siguientePaso" type="button" class="rectangle250 center textUpper footerTextColor font18" onclick="avanzarPaso(5);" disabled>ir al paso 5</button>
        </div>
        <div class="line20 floatLeft"></div>
        <div class="grayCircle center floatLeft">
            <p class="paddingTop5 footerTextColor font18">6</p>
        </div>
    </div>
</footer>
