<section class="container paddingTop30 paddingBottom20 contentHeight">
    <div class="padding20">
        <img class="happines" src="${resource(dir:'images', file:'happines.png')}" alt="happines" title="happines"/>
        <p class="center marginLeft24 font25 formTitleColor letterspacing2.3">¡FELICIDADES!</p>
        <p class="center marginLeft24 font16 formTitleColor letterspacing1.5 marginTop12">Has terminado tu solicitúd, estás a un click de saber tu resultado.</p>
        <p class="center marginLeft24 font16 colorblue letterspacing1.5 marginTop12">Solo te falta subir tu documentación, toma una foto de tus documentos</p>
        <p class="center marginLeft24 font16 colorblue letterspacing1.5 marginTop12">desde tu smartphone.</p>
        <div class="marginTop50 clearFix">
            <div class="floatLeft col5 col6-tab col12-mob">
                <div class="paddingAside15">
                    <div class="colorWhite darkGray radius100 buttonM mobileAuto greenClick pointer">SUBE TU IDENTIFICACIÓN OFICIAL</div>
                </div>
            </div>
            <div class="floatRight col5 col6-tab col12-mob">
                <div class="paddingAside15">
                    <div class="colorWhite darkGray radius100 buttonM mobileAuto greenClick pointer">SUBE TU COMPROBANTE DE DOMICILIO</div>
                </div>
            </div>
        </div>
    </div>
</section>
<footer class="footerContainer">
    <div class="rectangleRound autoMargin GrayButton radius100 gray font20 letterspacing1.8 center width350 solicitud_modal">
        ENVIAR MI SOLICITUD
    </div>
    <form class="sendValues" name="formPaso6" id="formPaso6" method="post">
        <input type="hidden" name="siguientePaso" id="siguientePaso" value="8">
    </form>
</footer>


<div class="idLightbox hide" id="identification_oficial">
    <div class="overlay"></div>
    <div class="whiteContainer lightboxPos identificationLb">
        <h2 class="floatLeft lbHeader headingColor fontWeight300 letterspacing1.5">SUBE TU IDENTIFICACIÓN OFICIAL</h2>
        <span class="closeModal floatRight borderGray radius100 marginTop15 marginRight15">
            <p class="textUpper gray paddingTop7 paddingBottom5 font18 center paddingLeft20 paddingRight20"><span class="notMobile paddingRight5">cerrar</span><i class="fa fa-times " aria-hidden="true"></i></p>
        </span>

        <div class="idView">
            <div class="border1 clearFloat"></div>
            <div class="clearFix">
                <div class="col4 col12-mob floatLeft">
                    <p class="menuTile active_blue font16 ">PASO 1 /  TIPO DE IDENTIFICACIÓN</p>
                </div>
                <div class="col4 col12-mob floatLeft ">
                    <p class="menuTile gray font16 ">PASO 2 /  CAPTURA FRENTE</p>
                </div>
                <div class="col4 col12-mob floatLeft">
                    <p class="menuTile paddingBottom15 gray font16">PASO 3 /  CAPTURA VUELTA</p>
                </div>
            </div>
            <div class="border1"></div>

            <div class="padding20">
                <div class="col9 col12-tab autoMargin clearFix paddingTop50 paddingBottom50 fontWeight500">

                    <div class="col6 col12-mob floatLeft">
                        <div class="paddingAside10">
                            <div class="idType iconButton whiteBox pointer">
                                <img class="icon40" src="${resource(dir:'images', file:'passport.png')}" alt="passport" title="passport"/>
                                <p class="gray font18 letterspacing1.1  iconButtonTxt">PASAPORTE</p>
                            </div>
                        </div>
                    </div>

                    <div class="col6 col12-mob floatLeft">
                        <div class="paddingAside10">
                            <div class="idType iconButton whiteBox pointer">
                                <img class="icon40 " src="${resource(dir:'images', file:'identification.png')}" alt="identification" title="identification"/>
                                <p class="gray font18 letterspacing1.1 iconButtonTxt">IDENTIFICACIÓN (IFE)</p>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <div class="idView hide">
            <div class="border1 clearFloat"></div>
            <div class="clearFix">
                <div class="col4 col12-mob floatLeft">
                    <p class="menuTile font16 ">PASO 1 /  TIPO DE IDENTIFICACIÓN</p>
                </div>
                <div class="col4 col12-mob floatLeft ">
                    <p class="menuTile active_blue  gray font16 ">PASO 2 /  CAPTURA FRENTE</p>
                </div>
                <div class="col4 col12-mob floatLeft">
                    <p class="menuTile paddingBottom15 gray font16">PASO 3 /  CAPTURA VUELTA</p>
                </div>
            </div>
            <div class="border1"></div>

            <div class="padding20">

                <div class="col10 autoMargin">
                    <div class="paddingTop20 paddingBottom20">
                        <h1 class="font16 gray letterspacing1">Te damos algunos tips para saber como subir el <span class="headingColor">frente de tu identificación o pasaporte</span></h1>
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
                            <div class="camCapture iconButton whiteBox upldsBBottom width350 mobileAuto pointer">
                                <img class="icon40" src="${resource(dir:'images', file:'camera-icon.svg')}" alt="Camera">
                                <p class="gray font18 letterspacing1.1 iconButtonTxt">Capturar con tu webcam</p>
                            </div>
                        </div>
                    </div>

                    <div class="col4 col12-tab floatLeft">
                        <div class="paddingAside10">
                            <div class="iconButton whiteBox upldsBBottom width350 mobileAuto pointer">
                                <img class="icon40" src="${resource(dir:'images', file:'file-icon.svg')}" alt="File">
                                <p class="gray font18 letterspacing1.1 iconButtonTxt">Subir desde tu PC</p>
                            </div>
                        </div>
                    </div>
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

        <div class="webcam_capture" style="display: none;">

            <div class="border1 clearFloat"></div>
            <div class="clearFix">
                <div class="col4 col12-mob floatLeft">
                    <p class="menuTile font16 ">PASO 1 /  TIPO DE IDENTIFICACIÓN</p>
                </div>
                <div class="col4 col12-mob floatLeft ">
                    <p class="menuTile active_blue gray font16 ">PASO 2 /  CAPTURA FRENTE</p>
                </div>
                <div class="col4 col12-mob floatLeft">
                    <p class="menuTile paddingBottom15 gray font16">PASO 3 /  CAPTURA VUELTA</p>
                </div>
            </div>
            <div class="border1"></div>
            <div id="fotoFrente" class="col12 clearFix">
                <div class="clearFix padding20">
                    <div class="col6 col12-mob floatLeft mobileDiv">
                        <div class="camPreview">
                            <div class="marginLeft21">
                                <p class="upldsTitles gray font16 fontWeight600 marginTop22 marginBottom19">
                                    Captura el <span class="headingColor">frente </span> de tu identificación con tu webcam
                                </p>
                            </div>
                            <div style="min-height: 200px; min-width: 200px; height:320px;width: 400px;" class="marginLeft21">
                                <input type="hidden" id="imagenCapturadaFrente" name="imagenCapturadaFrente" value="" />
                                <div id="webcamFrente" style="height:320px;width: 400px; margin-top: 5%; text-align: center;"></div>
                            </div>
                        </div>
                    </div>
                    <div class="col6 col12-mob floatLeft mobileDiv2">
                        <div class="padding20">
                            <p class="marginTop30 upldsTitles gray font16 fontWeight500 latterspacing1">
                                COMPARA LOS EJEMPLOS
                            </p>

                            <div class="width350">
                                <img class="upldsWebimage imgResponsive" src="${resource(dir:'images', file:'ids.png')}" alt="ids" />
                            </div>
                            <div class="marginLeft30">
                                <p class=" upldsTitles colorRed font16 latterspacing1">
                                    Tu documento no puede tener tachaduras o
                                </p>
                                <p class="upldsTitles colorRed font16 latterspacing1">
                                    enmendaduras y debe ser legible y claro
                                </p>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col9 col11-tab col12-mob camControls">
                    <div class="paddingAside20 clearFix">
                        <div class="floatLeft col4 col12-mob">
                            <div class="buttonOrange width210 marginLeft15 radius100 marginBottom20 mobileAuto">
                                <button type="button" id="repetirFotoFrente" style="background: #fb5e48;" class="buttonOrange radius100 mobileAuto width210 colorWhite font16 fontWeight600 paddingTop9 paddingBottom9 center letterspacing1">
                                    REPETIR FOTO
                                </button>
                            </div>
                        </div>
                        <div class="floatLeft col6  col12-mob">
                            <a id="guardarFoto">
                                <div class="colorGreen width210 marginLeft15 radius100 marginBottom20 mobileAuto">
                                    <button type="button" id="guardarFotoFrente" class="colorGreen width210 radius100 mobileAuto colorWhite font16 fontWeight600 paddingTop9 paddingBottom9 center letterspacing1">
                                        GUARDAR FOTO
                                    </button>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="clearFloat"></div>
            </div>
            <div id="fotoVuelta" class="col12 clearFix" style="display: none;">
                <div class="clearFix padding20">
                    <div class="col6 col12-mob floatLeft mobileDiv">
                        <div class="camPreview">
                            <div class="marginLeft21">
                                <p class="upldsTitles gray font16 fontWeight600 marginTop22 marginBottom19">
                                    Captura la <span class="headingColor">vuelta </span> de tu identificación con tu webcam
                                </p>
                            </div>
                            <div style="min-height: 200px; min-width: 200px; height:320px;width: 400px;" class="marginLeft21">
                                <input type="hidden" id="imagenCapturadaVuelta" name="imagenCapturadaVuelta" value="" />
                                <div id="webcamVuelta" style="height:320px;width: 400px; margin-top: 5%; text-align: center;"></div>
                            </div>
                        </div>
                    </div>
                    <div class="col6 col12-mob floatLeft mobileDiv2">
                        <div class="padding20">
                            <p class="marginTop30 upldsTitles gray font16 fontWeight500 latterspacing1">
                                COMPARA LOS EJEMPLOS
                            </p>

                            <div class="width350">
                                <img class="upldsWebimage imgResponsive" src="${resource(dir:'images', file:'ids.png')}" alt="ids" />
                            </div>
                            <div class="marginLeft30">
                                <p class=" upldsTitles colorRed font16 latterspacing1">
                                    Tu documento no puede tener tachaduras o
                                </p>
                                <p class="upldsTitles colorRed font16 latterspacing1">
                                    enmendaduras y debe ser legible y claro
                                </p>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col9 col11-tab col12-mob camControls">
                    <div class="paddingAside20 clearFix">
                        <div class="floatLeft col4 col12-mob">
                            <div class="buttonOrange width210 marginLeft15 radius100 marginBottom20 mobileAuto">
                                <button type="button" id="repetirFotoVuelta" style="background: #fb5e48;" class="buttonOrange radius100 mobileAuto width210 colorWhite font16 fontWeight600 paddingTop9 paddingBottom9 center letterspacing1">
                                    REPETIR FOTO
                                </button>
                            </div>
                        </div>
                        <div class="floatLeft col6  col12-mob">
                            <a id="guardarFoto">
                                <div class="colorGreen width210 marginLeft15 radius100 marginBottom20 mobileAuto">
                                    <button type="button" id="guardarFotoVuelta" class="colorGreen width210 radius100 mobileAuto colorWhite font16 fontWeight600 paddingTop9 paddingBottom9 center letterspacing1">
                                        GUARDAR FOTO
                                    </button>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="clearFloat"></div>
            </div>
        </div>
        <div class="idLastStep hide">
        </div>
    </div>
</div>
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

        <div class="docStep hide">
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
                            <div class="paddingAside10">
                                <div class="whiteBox marginTop35 marginBottom15">
                                    <div class="width85 autoMargin">
                                        <img class="cameraImage imgResponsive" src="${resource(dir:'images', file:'camera-icon.svg')}" alt="webpage" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="takePicture hide">
                            <div class="marginLeft21">
                                <p class="upldsTitles gray font16 fontWeight600 marginTop22 marginBottom19">
                                    Captura el <span class="headingColor">frente </span> de tu identificación con tu webcam
                                </p>
                            </div>
                            <div class="width85p marginLeft21">
                                <img class="upldsWebimage marginRight15  marginTop10 imgResponsive" src="${resource(dir:'images', file:'paper.png')}" alt="webpage" />
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

                    <div class="clearFloat col6 col12-mob floatLeft mobileDiv2 marginBottom15">
                        <div class="paddingAside10">
                            <div class="width350 autoMargin colorGreen radius100 center paddingTop10 paddingBottom10 tomarFoto">
                                <p class="colorWhite font16 fontWeight300 letterspacing1">TOMAR FOTO</p>
                            </div>
                        </div>
                    </div>

                    <div class="col9 col11-tab col12-mob clearFloat docControls hide">
                        <div class="paddingAside20 clearFix">
                            <div class="floatLeft col3 col6-mob">
                                <div class="whiteBox marginLeft20 marginBottom10">
                                    <p class="gray font16 fontWeight600 paddingTop9 paddingBottom9 center letterspacing1">
                                        + ACERCAR
                                    </p>
                                </div>
                            </div>
                            <div class="floatLeft col3  col6-mob">
                                <div class="whiteBox marginBottom10 marginLeft20">
                                    <p class="gray font16 fontWeight600 paddingTop9 paddingBottom9 center letterspacing1">
                                        - ALEJAR
                                    </p>
                                </div>
                            </div>
                            <div class="floatLeft col6  col12-mob">
                                <div class="colorGreen width350 marginLeft20 radius100 marginBottom20 mobileAuto">
                                    <p class="colorWhite font16 fontWeight600 paddingTop9 paddingBottom9 center letterspacing1">
                                        TOMAR FOTO
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

    </div>
</div>


<div class="solicitudLightbox hide" id="resumen_solicitud">
    <div class="overlay"></div>
    <div class="resumenSolicitud lightboxPos">
        <div class="padding20">
            <h2 class="fontWeight500 darkBluetitle font25 center paddingTop10 paddingBottom10">RESUMEN DE SOLICITUD</h2>
            <div class="borderLine marginTop12 marginBottom25"></div>
            <div class="padding20">
                <div class="fontWeight500 font18 darkBluetitle">
                    <p>ESTÁS SOLICITANDO UN CRÉDITO DE
                        <span class="headingColor">NISSAN MAXIMA </span>
                        POR
                        <span class="headingColor">$350,000.00.MX.</span>
                    </p>
                    <p class="paddingTop20 ">A UNA TAZA ORDINARIA DEL
                        <span class="headingColor"> 7% ANUAL </span>
                    </p>
                    <p class="paddingTop20 ">TUS PAGOS SERÁN MENSUALES</p>
                    <p> POR
                        <span class="headingColor"> $10,500.00 MXN </span>
                        A TU CUENTA
                        <span class="headingColor"> BBVA</span>
                    </p>
                    <p>
                        <span class="headingColor">TERMINACIÓN 4435 </span>
                        POR UN PERIODO DE
                        <span class="headingColor"> 24 MESES.</span>
                    </p>
                </div>
                <div class="greenTitle paddingTop20">
                    <p>Es posible que más adelante se te solicite compartir esta</p>
                    <p>solicitud con un aval para minimizar el riesgo y ofrecerte</p>
                    <p>mejores condiciones </p>
                </div>

            </div>
            <div class="borderLine marginTop12 marginBottom25"></div>
            <div class="padding20">
                <div class=" clearFix marginBottom10">
                    <div class="checkBox whiteCheckBox">
                        <i class="fa fa-check colorWhite hide" aria-hidden="true"></i>
                    </div>
                    <div class="checkboxLabel">
                        <p class="gray font12 paddingLeft20">ACEPTO TERMINOS Y CONDICIONES</p>
                    </div>
                </div>
                <div class="clearFix marginBottom10">
                    <div class="checkBox whiteCheckBox">
                        <i class="fa fa-check colorWhite hide" aria-hidden="true"></i>
                    </div>
                    <div class="checkboxLabel">
                        <p class="gray font12 paddingLeft20">AUTORIZO EL MANEJO CONFIDENCIAL DE MIS DATOS </p>
                    </div>
                </div>
            </div>
            <div class="marginBottom25">
                <div class="width350 blockAuto rectangleRound marginTop30 center font20 letterspacing1.8 textUpper enviarSolicitud">
                    enviar mi solicitud
                </div>
            </div>
        </div>
    </div>
</div>