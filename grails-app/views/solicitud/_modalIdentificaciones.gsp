<div class="idLightbox <g:if test="${!session.cargarImagen}">hide</g:if>" id="identification_oficial">
        <div class="overlay"></div>
        <div class="whiteContainer lightboxPos identificationLb">
            <h2 class="floatLeft lbHeader headingColor fontWeight300 letterspacing1.5">SUBE TU IDENTIFICACIÓN OFICIAL</h2>
            <span class="closeModal floatRight borderGray radius100 marginTop15 marginRight15">
                <p class="textUpper gray paddingTop7 paddingBottom5 font18 center paddingLeft20 paddingRight20"><span class="notMobile paddingRight5">cerrar</span><i class="fa fa-times " aria-hidden="true"></i></p>
            </span>

        <div class="border1 clearFloat"></div>
        <div class="clearFix">
            <a id="paso1Ids">
                <div class="col4 col12-mob floatLeft">
                    <p id="label1Ids" class="menuTile active_blue font16 " style="cursor:pointer;">PASO 1 /  TIPO DE IDENTIFICACIÓN</p>
                </div>
            </a>
            <a id="paso2Ids">
                <div class="col4 col12-mob floatLeft ">
                    <p id="label2Ids" class="menuTile gray font16 "  style="cursor:pointer;">PASO 2 /  CAPTURA FRENTE</p>
                </div>
            </a>
            <div class="col4 col12-mob floatLeft">
                <p id="label3Ids" class="menuTile paddingBottom15 gray font16"  style="cursor:pointer;">PASO 3 /  CAPTURA VUELTA</p>
            </div>
        </div>
        <div class="border1"></div>

        <div id="seleccionIdentificacion" class="idView">

            <div class="padding20">
                <div class="col9 col12-tab autoMargin clearFix paddingTop50 paddingBottom50 fontWeight500">

                    <div class="col6 col12-mob floatLeft">
                        <div class="paddingAside10">
                            <div class="idType pasaporteId iconButton whiteBox pointer">
                                <img class="icon40" src="${resource(dir:'images', file:'passport.png')}" alt="passport"/>
                                <p class="gray font18 letterspacing1.1  iconButtonTxt">PASAPORTE</p>
                            </div>
                        </div>
                    </div>

                    <div class="col6 col12-mob floatLeft">
                        <div class="paddingAside10">
                            <div class="idType ineId iconButton whiteBox pointer">
                                <img class="icon40 " src="${resource(dir:'images', file:'identification.png')}" alt="identification"/>
                                <p class="gray font18 letterspacing1.1 iconButtonTxt">IDENTIFICACIÓN (IFE)</p>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <div id="metodoSubidaIdentificacion" class="idView hide">

            <div class="padding20">

                <div class="col10 autoMargin">
                    <div class="paddingTop20 paddingBottom20">
                        <h1 class="font16 gray letterspacing1">Te damos algunos tips para saber como subir el <span id="nombreDeLaIdentificacion" class="headingColor"></span></h1>
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

                    <div id="divDropzoneIds" class="col4 col12-tab floatLeft">
                        <div class="paddingAside10">
                            <a id="subirIdentificacion">
                                <div class="iconButton whiteBox upldsBBottom width350 mobileAuto pointer">
                                    <img class="icon40 desktopCapture" src="${resource(dir:'images', file:'file-icon.svg')}" alt="File">
                                    <img class="icon40 mobileCapture" src="${resource(dir:'images', file:'phone-icon.svg')}" alt="Mobile">
                                    <p class="desktopCapture" class="gray font18 letterspacing1.1 iconButtonTxt">Subir desde tu PC</p>
                                    <p class="mobileCapture" class="gray font18 letterspacing1.1 iconButtonTxt">Capturar con tu smartphone</p>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="capturaTelefonoIds" class="phone_capture hide">
            <div class="border1 clearFloat"></div>
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
                            <g:if test="${session.cargarImagen}">
                                <svg class="checkmark" style="margin: 5% auto;" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 52 52"><circle class="checkmark__circle" cx="26" cy="26" r="25" fill="none"/><path class="checkmark__check" fill="none" d="M14.1 27.2l7.1 7.2 16.7-16.8"/></svg>
                                <p class="gray center marginTop20 marginBottom30"> ¡El mensaje fue enviado correctamente!</p>
                            </g:if>
                            <g:else>
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
                            </g:else>    
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="webcamCaptureIds" class="webcam_capture" style="display: none;">

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
        <div id="subirArchivoIds" class="file_uploads" style="display: none;">
            <div id="archivoFrente" class="col12 clearFix">
                <div class="clearFix padding20">
                    <div  id="divDropzoneIds" class="col6 col12-mob floatLeft mobileDiv" style="margin-top: 10%;">
                        <div id="uploadFrente" class="col folderContainer center span_4_of_4">
                            <img class="folderImage" src="${resource(dir:'images', file:'folder.png')}" alt="folder"/>
                            <p class="center letterspacing1.4 gray">Identificación (Frente)</p>
                            <div data-box="archivoFrente" class="colorGreen radius100 marginTop17 marginLeft60 foldersBox marginBottom20">
                                <p class="textUpper colorWhite font16 center paddingTop10 paddingBottom10 pointer">subir</p>
                            </div>
                        </div>
                    </div>
                    <div class="col6 col12-mob floatLeft mobileDiv2">
                        <div class="padding20">
                            <p class="marginTop30 upldsTitles gray font16 fontWeight500 latterspacing1">
                                VE LOS EJEMPLOS
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
                <div class="clearFloat"></div>
            </div>
            <div id="archivoVuelta" class="col12 clearFix" style="display: none;">
                <div class="clearFix padding20">
                    <div  id="divDropzoneIds" class="col6 col12-mob floatLeft mobileDiv" style="margin-top: 10%;">
                        <div id="uploadVuelta" class="col folderContainer center span_4_of_4">
                            <img class="folderImage" src="${resource(dir:'images', file:'folder.png')}" alt="folder"/>
                            <p class="center letterspacing1.4 gray">Identificación (Vuelta)</p>
                            <div data-box="archivoVuelta" class="colorGreen radius100 marginTop17 marginLeft60 foldersBox marginBottom20">
                                <p class="textUpper colorWhite font16 center paddingTop10 paddingBottom10 pointer">subir</p>
                            </div>
                        </div>
                    </div>
                    <div class="col6 col12-mob floatLeft mobileDiv2">
                        <div class="padding20">
                            <p class="marginTop30 upldsTitles gray font16 fontWeight500 latterspacing1">
                                VE LOS EJEMPLOS
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
                <div class="clearFloat"></div>
            </div>
        </div>
        <div id="progresoConsultaIds" style="display:none;margin-left: 25%;width: 50%;" class="floatLeft marginLeft20">
            <div class="spinner">
                <div class="rect1"></div>
                <div class="rect2"></div>
                <div class="rect3"></div>
                <div class="rect4"></div>
                <div class="rect5"></div>
            </div>
            <center><span style="color: #71758d;">Estamos subiendo tu identificación, espera por favor...</span></center>
        </div>
        <div class="idLastStep hide">
        </div>
    </div>
</div>
