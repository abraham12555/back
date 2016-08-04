<div class="idLightbox hide" id="comprobante_domicilio">
    <div class="overlay"></div>
    <div class="whiteContainer lightboxPos identificationLb">
        <h2 class="floatLeft lbHeader headingColor fontWeight300 letterspacing1.5">SUBE TU COMPROBANTE DE DOMICILIO</h2>
        <span class="floatRight borderGray radius100 marginTop15 marginRight15 closeModal">
            <p class="textUpper gray paddingTop7 paddingBottom5 font18 center paddingLeft20 paddingRight20"><span class="notMobile paddingRight5">cerrar</span><i class="fa fa-times " aria-hidden="true"></i></p>
        </span>

        <div class="docStep">
            <div class="border1 clearFloat"></div>
            <div class="clearFix">
                <div class="col6 col12-mob floatLeft">
                    <p class="menuTile active_blue font16 ">PASO 1 /  TIPO DE COMPROBANTE</p>
                </div>
                <div class="col6 col12-mob floatLeft ">
                    <p class="menuTile    gray font16 ">PASO 2 /  CAPTURA FRENTE</p>
                </div>
            </div>
            <div class="border1"></div>

            <div class="col8 col10-tab col12-mob autoMargin marginTop80 marginBottom80">
                <div class="paddingTop20 paddingBottom20">
                    <div class="padding20 clearFix">
                        <div class="col6 col12-mob floatLeft">
                            <div class="paddingAside10">
                                <div class="iconButton whiteBox docChoice pointer">
                                    <p class="gray font18 fontWeight600 paddingTop10">TELMEX</p>
                                </div>
                            </div>
                        </div>
                        <div class="col6 col12-mob floatLeft">
                            <div class="paddingAside10">
                                <div class="iconButton whiteBox docChoice pointer">
                                    <p class="gray font18 fontWeight600 paddingTop10">CFE</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="idView docStep hide">
            <div class="border1 clearFloat"></div>
            <div class="clearFix">
                <div class="col6 col12-mob floatLeft">
                    <p class="menuTile font16 ">PASO 1 /  TIPO DE COMPROBANTE</p>
                </div>
                <div class="col6 col12-mob floatLeft ">
                    <p class="menuTile active_blue  gray font16 ">PASO 2 /  CAPTURA FRENTE</p>
                </div>
            </div>
            <div class="border1"></div>

            <div class="padding20">

                <div class="col10 autoMargin">
                    <div class="paddingTop20 paddingBottom20">
                        <h1 class="font16 gray letterspacing1">Te damos algunos tips para saber como subir tu <span class="headingColor">comprobante de domicilio</span></h1>
                    </div>
                    <div class="autoMargin clearFix">
                        <div class="col4 col12-mob col floatLeft idDocs">
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
                <div class="col4 col8-mob col6-tab width354 autoMargin marginTop40 marginBottom40">
                    <div class="darkGray center radius100 paddingTop12 paddingBottom12">
                        <span class="font16 colorWhite center letterspacing1">VER MÁS TIPS</span>
                    </div>
                </div>
                <div class="clearFix">

                    <div class="col4 col12-tab floatLeft">
                        <div class="paddingAside10">
                            <div class="phoneCapture iconButton whiteBox upldsBBottom width350 mobileAuto pointer">
                                <img class="icon40" src="${resource(dir:'images', file:'phone-icon.svg')}" alt="Mobile">
                                <p class="gray font18 letterspacing1.1 iconButtonTxt">Capturar con tu smartphone</p>
                            </div>
                        </div>
                    </div>

                    <div class="col4 col12-tab floatLeft">
                        <div class="paddingAside10">
                            <div class="camCaptureComp iconButton whiteBox upldsBBottom width350 mobileAuto pointer">
                                <img class="icon40" src="${resource(dir:'images', file:'camera-icon.svg')}" alt="Camera">
                                <p class="gray font18 letterspacing1.1 iconButtonTxt">Capturar con tu webcam</p>
                            </div>
                        </div>
                    </div>

                    <div id="divDropzoneComp" class="col4 col12-tab floatLeft">
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
                <div id="progresoConsultaComp" style="display:none;margin-left: 25%;width: 50%;" class="floatLeft marginLeft20">
                    <div class="loadingBar meter animate">
                        <span style="width: 100%"><span></span></span>
                    </div>
                    <center><span style="color: #71758d;">Estamos subiendo tu comprobante, espera por favor...</span></center>
                </div>
            </div>
        </div>

        <div class="phone_capture hide">
            <div class="border1 clearFloat"></div>
            <div class="marginTop15 marginBottom15">
                <p class="center gray font16">
                    ¿Cómo quieres recibir el link en tu smartphone?
                </p>
            </div>
            <div class="border1"></div>
            <div class="clearFix fontWeight500">
                <div class="col6 down800 col12-mob  floatLeft clearFix">
                    <div class="padding20">
                        <div class="paddingAside20">
                            <p class="gray center marginTop20 marginBottom30">
                                Agrega tu número y te enviamos un SMS
                            </p>
                            <div class="clearFix marginBottom30">

                                <input class="whiteBox upldsInputCode font16 marginRight10" type="text" name="contrycode" placeholder="044">

                                <input class="whiteBox upldsInputPhone font16" type="text" name="phoneNumber" placeholder="(55)0000 - 000)">

                            </div>
                            <div class="colorGreen width350 radius100 autoMargin goLastStep">
                                <p class="colorWhite center font16 paddingTop13 paddingBottom10">
                                    ENVIAR SMS
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col6 down800 col12-mob floatLeft clearFix">
                    <div class="padding20 borderGrayLeft">
                        <div class="paddingAside20">
                            <p class="gray center marginTop20 marginBottom30">
                                O scanea este QR Code desde tu smartphone
                            </p>
                            <div class="center paddingBottom20">
                                <img src="${resource(dir:'images', file:'barcode1.png')}" alt="barcode" title="barcode" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="webcamCaptureComp docStep hide">
            <div class="border1 clearFloat"></div>
            <div class="clearFix">
                <div class="col6 col12-mob floatLeft">
                    <p class="menuTile font16 ">PASO 1 /  TIPO DE COMPROBANTE</p>
                </div>
                <div class="col6 col12-mob floatLeft ">
                    <p class="menuTile  active_blue  gray font16 ">PASO 2 /  CAPTURA FRENTE</p>
                </div>
            </div>
            <div class="border1"></div>

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
    </div>
</div>