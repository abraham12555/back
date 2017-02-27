<div class="idLightbox hide" id="comprobante_domicilio">
    <div class="overlay"></div>
    <div class="whiteContainer lightboxPos identificationLb">
        <h2 class="floatLeft lbHeader headingColor fontWeight300 letterspacing1.5">SUBE TU COMPROBANTE DE DOMICILIO</h2>
        <span class="floatRight borderGray radius100 marginTop15 marginRight15 closeModal">
            <p class="textUpper gray paddingTop7 paddingBottom5 font18 center paddingLeft20 paddingRight20"><span class="notMobile paddingRight5">cerrar</span><i class="fa fa-times " aria-hidden="true"></i></p>
        </span>
        <div class="border1 clearFloat"></div>
        <div class="clearFix">
            <a id="paso1CompDom">
                <div class="col6 col12-mob floatLeft">
                    <p id="label1Comp" class="menuTile active_blue font16 " style="cursor:pointer;">PASO 1 /  TIPO DE COMPROBANTE</p>
                </div>
            </a>
            <a id="paso2CompDom">
                <div class="col6 col12-mob floatLeft ">
                    <p id="label2Comp" class="menuTile gray font16 " style="cursor:pointer;">PASO 2 /  CAPTURA FRENTE</p>
                </div>
            </a>
        </div>
        <div class="border1"></div>
        <div id="seleccionComprobante" class="docStep">

            <div class="col8 col10-tab col12-mob autoMargin marginTop80 marginBottom80">
                <div class="paddingTop20 paddingBottom20">
                    <div class="padding20 clearFix">
                        <div class="col6 col12-mob floatLeft">
                            <div class="paddingAside10">
                                <div class="iconButton whiteBox docChoice telmex pointer">
                                    <p class="gray font18 fontWeight600 paddingTop10" style="cursor:pointer;">TELMEX</p>
                                </div>
                            </div>
                        </div>
                        <div class="col6 col12-mob floatLeft">
                            <div class="paddingAside10">
                                <div class="iconButton whiteBox docChoice cfe pointer">
                                    <p class="gray font18 fontWeight600 paddingTop10" style="cursor:pointer;">CFE</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="metodoSubidaComprobante" class="idView docStep hide">

            <div class="padding20">

                <div class="col10 autoMargin">
                    <div class="paddingTop20 paddingBottom20">
                        <h1 class="font16 gray letterspacing1">Te damos algunos tips para saber como subir tu <span class="headingColor" id="nombreDelDocumento"></span></h1>
                    </div>
                    <div class="autoMargin clearFix">
                        <div class="col4 col12-mob floatLeft idDocs">
                            <h4 class="gray font16 fontWeight400 letterspacing1">LEGIBLE</h4>
                            <img class="imgResponsive" src="${resource(dir:'images', file:'id1.png')}" alt="id1"/>
                        </div>
                        <div class="col4 col12-mob floatLeft idDocs">
                            <h4 class="gray font16 fontWeight400 letterspacing1">BORROSO</h4>
                            <img class="imgResponsive" src="${resource(dir:'images', file:'id2.png')}" alt="id2"/>
                        </div>
                        <div class="col4 col12-mob floatRight idDocs">
                            <h4 class="gray font16 fontWeight400 letterspacing1">CON DEDOS</h4>
                            <img class="imgResponsive" src="${resource(dir:'images', file:'id3.png')}" alt="id3"/>
                        </div>
                    </div>
                </div>
                <!--<div class="col4 col8-mob col6-tab width354 autoMargin marginTop40 marginBottom40">
                    <div class="darkGray center radius100 paddingTop12 paddingBottom12">
                        <span class="font16 colorWhite center letterspacing1">VER MÁS TIPS</span>
                    </div>
                </div>-->
                <div class="clearFix">

                    <div class="col6 col12-tab floatLeft">
                        <div class="paddingAside10">
                            <div class="phoneCapture iconButton whiteBox upldsBBottom width350 mobileAuto pointer">
                                <img class="icon40" src="${resource(dir:'images', file:'phone-icon.svg')}" alt="Mobile">
                                <p class="gray font18 letterspacing1.1 iconButtonTxt">Capturar con tu smartphone</p>
                            </div>
                        </div>
                    </div>

                    <div id="divDropzoneComp" class="col6 col12-tab floatLeft">
                        <div class="paddingAside10">
                            <a id="subirComprobante">
                                <div class="iconButton whiteBox upldsBBottom width350 mobileAuto pointer">
                                    <img class="icon40" src="${resource(dir:'images', file:'file-icon.svg')}" alt="File">
                                    <p class="gray font18 letterspacing1.1 iconButtonTxt">Subir desde tu PC</p>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="capturaTelefonoComp" class="phone_capture hide">
            <div class="marginTop15 marginBottom15">
                <p class="center gray font16">
                    Sube la fotografía desde tu smartphone
                </p>
            </div>
            <div class="border1"></div>
            <div class="clearFix fontWeight500">
                <div class="col12 down800 col12-mob  floatLeft clearFix">
                    <div class="padding20">
                        <div class="paddingAside20 shortUrlAction">
                            <p class="gray center marginTop20 marginBottom30">
                                Da click en el siguiente botón y reciba la URL personalizada en tu smartphone.
                            </p>
                            <div class="clearFix marginBottom30">
                                <p class="headingColor textUpper letterspacing1.5 font25 paddingRight15 center"> ${session.cotizador?.telefonoCliente ?: generales?.telefonoCliente?.telefonoCelular} </p>
                            </div>
                            <div class="colorGreen width350 radius100 autoMargin goLastStep">
                                <p class="colorWhite center font16 paddingTop13 paddingBottom10 pointer">
                                    ENVIAR SMS
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="webcamCaptureComp" class="webcamCaptureComp docStep hide">

            <div class="col12 clearFix marginBottom40">
                <div class="padding20">
                    <p class="paddingAside10 upldsTitles gray font16 fontWeight600 marginTop22 marginBottom19">Captura el <span class="headingColor">frente </span> de tu comprobante de domicilio con tu webcam</p>

                    <div class="col6 col12-mob floatLeft mobileDiv">
                        <div class="defaultTP">
                            <div style="min-height: 200px; min-width: 200px; height:320px;width: 400px;" class="marginLeft21">
                                <input type="hidden" id="imagenCapturadaComprobante" name="imagenCapturadaComprobante" value="" />
                                <div id="webcamComprobante" style="height:320px;width: 400px; margin-top: 5%; text-align: center;"></div>
                            </div>
                        </div>
                    </div>

                    <div class="col6 col12-mob floatLeft mobileDiv2">
                        <div class="paddingAside10">
                            <div class="marginTop20">
                                <p class="upldsTitles gray font16 fontWeight600 latterspacing1">COMPARA LOS EJEMPLOS</p>
                            </div>
                            <div class="width350 mobileAuto marginBottom15">
                                <img class="upldsWebimage imgResponsive" src="${resource(dir:'images', file:'ids.png')}" alt="ids" />

                                <p class=" upldsTitles colorRed font16 latterspacing1">Tu documento no puede tener mas de 90 días de antiguedad, sin tachaduras o enmendaduras y debe ser legible y claro.</p>
                            </div>
                        </div>
                    </div>

                    <div class="col9 col11-tab col12-mob clearFloat docControls">
                        <div class="paddingAside20 clearFix">
                            <div class="floatLeft col4 col12-mob">
                                <div class="buttonOrange width210 marginLeft15 radius100 marginBottom20 mobileAuto">
                                    <button type="button" id="repetirFotoComprobante" style="background: #fb5e48;" class="buttonOrange radius100 mobileAuto width210 colorWhite font16 fontWeight600 paddingTop9 paddingBottom9 center letterspacing1">
                                        REPETIR FOTO
                                    </button>
                                </div>
                            </div>
                            <div class="floatLeft col6  col12-mob">
                                <a id="guardarFoto">
                                    <div class="colorGreen width210 marginLeft15 radius100 marginBottom20 mobileAuto">
                                        <button type="button" id="guardarFotoComprobante" class="colorGreen width210 radius100 mobileAuto colorWhite font16 fontWeight600 paddingTop9 paddingBottom9 center letterspacing1">
                                            GUARDAR FOTO
                                        </button>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <div id="progresoConsultaComp" style="display:none;margin-left: 25%;width: 50%;" class="floatLeft marginLeft20 barraProgresoComp">
            <div class="spinner">
                <div class="rect1"></div>
                <div class="rect2"></div>
                <div class="rect3"></div>
                <div class="rect4"></div>
                <div class="rect5"></div>
            </div>
            <center><span style="color: #71758d;">Estamos subiendo tu comprobante, espera por favor...</span></center>
        </div>
    </div>
</div>
