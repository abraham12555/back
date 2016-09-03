<section id="consultaBancaria" class="container paddingTop30 paddingBottom20 clearFix contentHeight">
    <div class="defaultBubble">
        <div class="container clearFix relative autoMargin width920">
            <div class="infoBox  desktop ">
                <div class="width850 autoMargin">
                    <p class="displayInline width570 floatLeft letterspacing0.5 font11 paddingLeft15 paddingTop15 paddingBottom10">CONECTAR TU CUENTA DE BANCO NOS PERMITE <span class="headingColor">APROBAR TU SOLICITUD EN MINUTOS</span>  O PUEDES SUBIR TUS ESTADOS DE CUENTA Y ESPERAR A QUE EL EQUIPO DE KOSMOS LO REVISÉ LO MAS RÁPIDO POSIBLE  </p>
                    <div class="blueBox1 width235 floatRight marginTop12">
                        <p class="textUpper colorWhite center font11 paddingTop10 paddingBottom10">SUBIR MI ESTADO DE CUENTA</p>
                    </div>
                </div>
            </div>
            <div class="line18 floatLeft"></div>
            <div class="crosCircle floatLeft">
                <p class="center cross"><i class="fa fa-times" aria-hidden="true"></i></p>
            </div>
        </div>
    </div>
    <div class="successBubble hide">
        <div class="container clearFix relative autoMargin width920">
            <div class="infoBoxGreen floatLeft">
                <div class="infoContainer4c">
                    <p class="center letterspacing0.5 font13 paddingLeft15 paddingTop15 paddingBottom10 colorWhite marginTop5">TUS DATOS BANCARIOS SE RECIBIERON EXITOSAMENTE, POR FAVOR VERIFICA QUE TU INFORMACIÓN SEA CORRECTA</p>
                </div>
            </div>
            <div class="line18 colorGreen floatLeft"></div>
            <div class="crosCircle colorGreen floatLeft">
                <p class="center cross colorWhite"><i class="fa fa-times colorWhite" aria-hidden="true"></i></p>
            </div>
        </div>
    </div>

    <div class="box4B marginBottom30 marginTop30">
        <div id="consultarInfo" class="bankStep">
            <p class="gray font14 paddingTop20 paddingLeft20 marginLeft15 marginBottom10">ELIGE TU BANCO</p>
            <div class="clearFix padding10">
                <div class="width160 brandingBox  floatLeft bankButton">
                    <img class="width120 blockAuto paddingTop20" src="${resource(dir:'images', file:'banamex.png')}" data-hover="banamex"/>
                </div>
                <div class="width160 brandingBox floatLeft bankButton">
                    <img class="width120 blockAuto paddingTop20" src="${resource(dir:'images', file:'bancomer.png')}" data-hover="bancomer"/>
                </div>
                <div class="width160 brandingBox floatLeft bankButton">
					<img class="width80 blockAuto"
						src="${resource(dir:'images', file:'american_express.png')}" data-hover="american_express" />
				</div>
                <div class="width160 brandingBox floatLeft bankButton">
                    <img class="width120 blockAuto paddingTop20" src="${resource(dir:'images', file:'santander.png')}" data-hover="santander"/>
                </div>
                <div class="width160 brandingBox floatLeft bankButton">
                    <img class="width120 blockAuto paddingTop20" src="${resource(dir:'images', file:'banorte.png')}" data-hover="banorte"/>
                </div>
		<!-- div class="width66 brandingBox floatLeft">
                    <p class="center font14 paddingTop30">OTRO</p>
                </div> -->
                <input type="hidden" name="banco" value="" class="bankChoice formValues"/>
            </div>

            <div class="clearFix paddingLeft10">
                <input type="hidden" id="intentos" value="0"/>
                <div class="floatLeft paddingTop20 col6 col6-tab col12-mob">
                    <p class=" marginBottom15 gray font14">NÚMERO DE CLIENTE</p>
                    <input class="inPuts4a formValues textUpper headingColor" type="text" id="login" name="login" placeholder="no. de cliente"/>
                </div>
                <div class="floatLeft paddingTop20 col6 col6-tab col12-mob">
                    <p class=" marginBottom15 gray font14">CLAVE</p>
                    <input class="inPuts4a formValues headingColor" type="password" id="password" name="password" placeholder="·······"/>
                </div>
                
                <!-- div class="floatLeft paddingTop20 col4 col6-tab col12-mob">
                    <p class=" marginBottom15 gray font14">TOKEN O PIN</p>
                    <input class="inPuts4a formValues headingColor" type="text" id="tokenNo" name="tokenNo" placeholder="000000"/>
                </div-->
            </div>

            <div id="accionesNormal" class="paddingTop20 clearFix">
                <div class="floatLeft col6 col12-mob">
                    <div class="padding10">
                        <div class="consultarBox marginLeft15 center colorWhite letterspacing1 font16">
                            CONSULTAR DE FORMA SEGURA
                        </div>
                    </div>
                </div>
                <div class="floatLeft col6 col12-mob">
                    <div class="padding10">
                        <div class="floatLeft loadingBar hide marginTop50 ">
                            <div class="loadingActive"></div>
							<center>
								<span style="color: #dfe2f4;">Conectando con tu banco...</span>
							</center>
                        </div>
                    </div>
                </div>
            </div>

            <div id="accionesError" style="padding-top: 50px;" class="padding10 clearFix marginTop15 hide">
                <div class="col6 col12-tab col12-mob floatLeft">
                    <div class="paddingAside10">
                        <div class="coneccionFallida center colorWhite .letterspacing1.5 font16">CONEXIÓN FALLIDA</div>
                    </div>
                </div>
                <div class="col6 col12-tab col12-mob floatLeft clearFix">
                    <div class="col5 col12-mob floatLeft">
                        <div class="paddingAside10">
                            <a id="reintentarBtn">
                                <div class="darkGray buttonM radius100 colorWhite font16 .letterspacing1.5 mobileAuto">reintentar</div>
                            </a>
                        </div>
                    </div>
                    <div class="col7 col12-mob floatLeft">
                        <div class="paddingAside10">
                            <a id="subirEstadosDeCuentaBtn">
                                <div class="colorGreen radius100 buttonM colorWhite font16 .letterspacing1.5 mobileAuto">subir estados de cuenta</div>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="confirmarConsulta" style="display:none;" class="bankStep hide">
            <p class="gray font14 paddingTop20 paddingLeft20 center marginBottom10">DATOS RECIBIDOS</p>

            <div class="clearFix padding15">

                <div class="floatLeft paddingTop20 col4 col12-mob">
                    <p class="marginBottom15 gray font14">DEPOSITOS PROMEDIO (90 DÍAS)</p>
                    <input type="hidden" class="inputsFormulario  formValues" name="customer_id" id="customer_id" value="${customer_id}"/>
                	<input type="hidden" class="inputsFormulario  formValues" name="login_id" id="login_id" value="${generales?.login_id}"/>
                    <input class="inPuts4B paddingLeft15 headingColor formValues" type="text" id="dep90" name="depositos" placeholder="$55,000.00 MXN" value="${generales?.depositoPromedio}"/>
                    <div class="paddingTop20 clearFix confirmInfo">
                        <p class="marginBottom15 gray font14">¿ESTA INFORMACIÓN ES CORRECTA?</p>
                        <div id="deposito_correcto_si" class="correctaBox floatLeft">
                            <p class="center paddingTop15 paddingBottom15 lightGray">SI</p>
                        </div>
                        <div id="deposito_correcto_no" class="floatLeft correctaBox">
                            <p class="center paddingTop15 paddingBottom15 lightGray">NO</p>
                        </div>
                    </div>
                </div>

                <div class="floatLeft paddingTop20 col4 col12-mob">
                    <p class="marginBottom15 gray font14">RETIROS PROMEDIO (90 DÍAS)</p>
                     <input class="inPuts4B paddingLeft15 headingColor formValues" type="text" id="ret90" name="retiros" placeholder="$55,000.00 MXN" value="${generales?.retiroPromedio}"/>
                 
                    <div class="paddingTop20 clearFix confirmInfo">
                        <p class="marginBottom15 gray font14">¿ESTA INFORMACIÓN ES CORRECTA?</p>
                        <div id="retiro_correcto_si" class="correctaBox floatLeft">
                            <p class="center paddingTop15 paddingBottom15 lightGray">SI</p>
                        </div>
                        <div id="retiro_correcto_no" class="floatLeft correctaBox">
                            <p class="center paddingTop15 paddingBottom15 lightGray">NO</p>
                        </div>
                    </div>
                </div>

                <div class="floatLeft paddingTop20 col4 col12-mob ">
                    <p class="marginBottom15 gray font14">SALDO PROMEDIO (90 DÍAS)</p>
                    <input class="inPuts4B paddingLeft15 headingColor formValues" type="text" id="saldo90" name="saldo" placeholder="$55,000.00 MXN" value="${generales?.saldoPromedio}"/>
                    <input class="formValues" type="hidden" id="saldoCorrecto" name="saldoCorrecto"  value="${generales?.saldoCorrecto}"/>
                    <input class="formValues" type="hidden" id="retiroCorrecto" name="retiroCorrecto" value="${generales?.retiroCorrecto}"/>
                    <input class="formValues" type="hidden" id="depositoCorrecto" name="depositoCorrecto" value="${generales?.depositoCorrecto}"/>
                    
                    <div class="paddingTop20 clearFix confirmInfo">
                        <p class="marginBottom15 gray font14">¿ESTA INFORMACIÓN ES CORRECTA?</p>
                        <div id="saldo_correcto_si" class="correctaBox floatLeft">
                            <p class="center paddingTop15 paddingBottom15 lightGray">SI</p>
                        </div>
                        <div id="saldo_correcto_no" class="floatLeft correctaBox">
                            <p class="center paddingTop15 paddingBottom15 lightGray">NO</p>
                        </div>
                    </div>
                </div>

                <div class="paddingTop20 clearFix">
                    <div class="floatLeft col6 col12-mob">
                        <div class="paddingAside15">
                            <div class="consultarBox exito marginLeft15 center colorWhite letterspacing1 font16" style="cursor:default">
                                CONEXIÓN EXITOSA
                            </div>
                        </div>
                    </div>
                    <div class="floatLeft col6 col12-mob">
                        <div class="paddingAside15">
                            <div class="confirmDb buttonM hide mobileAuto pointer colorGreen radius100 marginTop35 colorWhite letterspacing1 font16">
                                CONFIRMAR MI INFORMACIÓN
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="padding20 width920 clearFix">
        <div class="floatLeft col5 col12-mob clearFix paddingBottom50">
            <div class="floatLeft">
                <img alt="Protection" src="${resource(dir:'images', file:'protection.png')}"/>
            </div>
            <div class="floatRight marginLeft10 paddingTop5 rightTxt">
                <p class="formTitleColor font16 letterspacing1">PROTEGEMOS TU INFORMACIÓN</p>
                <p class="colorRed font14">Kosmos no guarda ningún dato bancario.</p>
            </div>
        </div>
        <div class="floatRight  col7 col12-mob clearFix paddingBottom50">
            <div class="floatLeft">
                <img alt="Key" src="${resource(dir:'images', file:'key.png')}"/>
            </div>
            <div class="floatLeft marginLeft10 paddingTop5 rightTxt">
                <p class="formTitleColor font16 letterspacing1">SOMOS MÁS SEGUROS QUE LOS BANCOS</p>
                <p class="colorRed font14">Todos tus datos están 100% protegidos con la mejor seguridad en la red</p>
            </div>
        </div>
    </div>
</section>
<section id="recibosUpload" style="display: none;" class="container paddingTop30 paddingBottom20 clearFix contentHeight">
    <div class="box4B padding20 clearFix">
        <p class="center font14 letterspacing1.1 gray paddingTop36 paddingBottom38">SUBE TUS ÚLTIMOS 4 RECIBOS DE NÓMINA O TUS 3 ULTIMOS ESTADOS DE CUENTA</p>
        <div class="col3 col6-tab col12-mob floatLeft">
            <div class="paddingAside15">
                <div class="folderContainer marginTop15 mobileAuto ">
                    <img class="folderImage" src="${resource(dir:'images', file:'folder.png')}" alt="folder" title="folder"/>
                    <p class="center letterspacing1.4 gray">Mes 1</p>
                </div>
                <div class="colorGreen radius100 buttonM marginTop20 colorWhite font16 mobileAuto ">
                    subir
                </div>
            </div>
        </div>
        <div class="col3 col6-tab col12-mob floatLeft">
            <div class="paddingAside15">
                <div class="folderContainer marginTop15 mobileAuto ">
                    <img class="folderImage" src="${resource(dir:'images', file:'folder.png')}" alt="folder" title="folder"/>
                    <p class="center letterspacing1.4 gray">Mes 2</p>
                </div>
                <div class="colorGreen radius100 buttonM marginTop20 colorWhite font16 mobileAuto ">
                    subir
                </div>
            </div>
        </div>
        <div class="col3 col6-tab col12-mob floatLeft">
            <div class="paddingAside15">
                <div class="folderContainer marginTop15 mobileAuto ">
                    <img class="folderImage" src="${resource(dir:'images', file:'folder.png')}" alt="folder" title="folder"/>
                    <p class="center letterspacing1.4 gray">Mes 3</p>
                </div>
                <div class="colorGreen radius100 buttonM marginTop20 colorWhite font16 mobileAuto ">
                    subir
                </div>
            </div>
        </div>
        <div class="col3 col6-tab col12-mob floatLeft">
            <div class="paddingAside15">
                <div class="folderContainer marginTop15 mobileAuto ">
                    <img class="folderImage" src="${resource(dir:'images', file:'folder.png')}" alt="folder" title="folder"/>
                    <p class="center letterspacing1.4 gray">Mes 4</p>
                </div>
                <div class="colorGreen radius100 buttonM marginTop20 colorWhite font16 mobileAuto ">
                    subir
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
            <div class="greenrectangle floatLeft nextBtn">Ir al paso 4</div>
        </div>
    </div>
</footer>
<div id="modalloginBank" class="modal fade" style="display:none;">
        <g:render template="/templates/solicitud/paso4/loginBank"/>
</div>

