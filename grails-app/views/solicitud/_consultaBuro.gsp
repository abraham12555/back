<section id="pasoConsultaBuro" class="container paddingTop30 paddingBottom20 clearFix contentHeight">
    <input type="hidden" id="tipoDePaso" value="${pasoActual?.tipoDePaso?.nombre}">
    <input type="hidden" id="tituloDelPaso" value="${pasoActual?.titulo}"/>
    <form method="POST" name="formFormulario" id="formFormulario">
        <input type="hidden" name="siguientePaso" id="siguientePaso">
        <input type="hidden" name="pasoAnterior" id="pasoAnterior" value="${pasoActual?.numeroDePaso}">
        <div class="padding20">
            <input type="hidden" id="errorConsulta" value="${errorConsulta}" />
            <input type="hidden" id="reporteBuroCredito"  value="${reporteBuroCredito}" />

            <div class="clearFix marginBottom30">
                <div class="floatLeft col4 col6-tab col12-mob clearFix marginBottom20 ccInfo">
                    <p class="correctaBoxLabel">¿TIENES UNA TARJETA DE CRÉDITO?</p>
                    <div id="tarjeta_correcto_si" class=" correctaBox floatLeft hasCc">
                        <p class="center paddingTop15 paddingBottom15 ">SI</p>
                    </div>
                    <div id="tarjeta_correcto_no" class="floatLeft correctaBox">
                        <p class="center paddingTop15 paddingBottom15 lightGray">NO</p>
                    </div>
                    <input type="hidden" class="formValues textUpper" name="tCredito" id="tCredito" value="${generales?.tCredito}">
                </div>
                <div class="floatLeft col3 col6-tab col12-mob">
                    <p class="gray font14 letterspacing1.1">ÚLTIMOS CUATRO DÍGITOS</p>
                    <input class="inPuts4a marginTop15 headingColor" type="text" disabled id="numeroTarjeta" name="numeroTarjeta" placeholder="0000" maxlength="4" />
                    <div class="col7 col12-tab floatLeft marginTop30" id="bubbleMensajeTarjeta">
                        <div class="rectangleRound font11 letterspacing0.5 center">LAS
                            TARJETAS DE DÉBITO Y TIENDAS DEPARTAMENTALES NO SON CONSIDERADAS
                            TARJETAS DE CRÉDITO</div>
                    </div>
                    <input type="hidden" class="textUpper" disabled id="nombre" name="nombre" value="${personales?.nombre}" />
                    <input
                    type="hidden" class="textUpper" id="apellidoPaterno"
                    name="apellidoPaterno" value="${personales?.apellidoPaterno}" /> <input
                    type="hidden" class="textUpper" id="apellidoMaterno"
                    name="apellidoMaterno" value="${personales?.apellidoMaterno}" /> <input
                    type="hidden" class="textUpper" id="fechaNac" name="fechaNac"
                    value="${personales?.fechaDeNacimiento?.dia}-${personales?.fechaDeNacimiento?.mes}-${personales?.fechaDeNacimiento?.anio}" />
                    <input type="hidden" class="textUpper" id="rfc" name="rfc"
                    value="${personales?.rfc}" /> <input type="hidden"
                    class="textUpper" id="calle" name="calle"
                    value="${direccion?.calle}" /> <input type="hidden"
                    class="textUpper" id="noExterior" name="noExterior"
                    value="${direccion?.numeroExterior}" /> <input type="hidden"
                    class="textUpper" id="noInterior" name="noInterior"
                    value="${direccion?.numeroInterior}" /> <input type="hidden"
                    class="textUpper" id="colonia" name="colonia"
                    value="${direccion?.colonia}" /> <input type="hidden"
                    class="textUpper" id="municipio" name="municipio"
                    value="${direccion?.delegacion}" />
                </div>


            </div>
            <div class="clearFix marginBottom30" id="buroSegundaFila">
                <div
                class="floatLeft col4 col6-tab col12-mob clearFix marginBottom20 ccInfo">
                <p class="correctaBoxLabel">¿ERES TÍTULAR DE UN CREDITO HIPOTECARIO?</p>
                <div id="hipotecario_correcto_si" class="correctaBox floatLeft hasCc">
                    <p class="center paddingTop15 paddingBottom15 lightGray">SI</p>
                </div>
                <div id="hipotecario_correcto_no" class="floatLeft correctaBox">
                    <p class="center paddingTop15 paddingBottom15 lightGray">NO</p>
                </div>
                <input type="hidden" class="formValues textUpper" name="creditoH" id="creditoH" value="${generales?.creditoH}">
            </div>
            <div class="floatLeft col4 col6-tab col12-mob clearFix marginBottom20 ccInfo">
                <p class="correctaBoxLabel">¿HAZ SIDO TÍTULAR DE UN CREDITO AUTOMOTRIZ EN LOS ÚLTIMOS 24 MESES?</p>
                <div id="automotriz_correcto_si" class="correctaBox floatLeft hasCc">
                    <p class="center paddingTop15 paddingBottom15 lightGray">SI</p>
                </div>
                <div id="automotriz_correcto_no" class="floatLeft correctaBox">
                    <p class="center paddingTop15 paddingBottom15 lightGray">NO</p>
                </div>
                <input type="hidden" class="formValues textUpper" name="creditoA" id="creditoA" value="${generales?.creditoA}">
            </div>
        </div>
        <g:if env="test">
        <!-- TEMPORAL -->
        <div class="clearFix marginBottom30">
            <div class="floatLeft col12 col12-tab col12-mob clearFix marginBottom20">
                <p class="correctaBoxLabel">INTRODUZCA LA CADENA DE BURÓ DE CRÉDITO PARA EJECUTAR LA PRUEBA</p>
                <textarea class="datoPerfilador" style="width:100%; height: 200px;" name="cadenaBuroTest" id="cadenaBuroTest" ></textarea>
            </div>
        </div>
        <!-- TEMPORAL -->
       </g:if>
        <div id="divAutorizacionBuro" class="col12 col12-mob col floatLeft paddingTop20 paddingBottom20 ">
            <p class="font18 gray letterspacing1 justify">
                Hoy siendo <span id="fechaAutorizacionConsulta" class="headingColor">
                    ${fechaActual}
                </span>, Autoriza a <span id="razonSocial" class="headingColor"> ${razonSocial}
                </span> a consultar sus antecedentes crediticios por &uacute;nica
                ocasi&oacute;n ante las Sociedades de Informaci&oacute;n Crediticia
                que estime conveniente, declarando que conoce la naturaleza, alcance
                y uso que <span id="razonSocial" class="headingColor"> ${razonSocial}
                </span> har&aacute; de tal informaci&oacute;n?
            </p>
        </div>
        <div id="accionesNormales" class="creditBtns">
            <div class="col5half col12-mob floatLeft">
                <a id="consultarBuroBtn">
                    <div  class="blueButton buttonM radius100 font16 colorWhite letterspacing1.5 mobileAuto consultarBc" style="cursor: pointer;">AUTORIZÓ CONSULTAR MI BURÓ DE CRÉDITO</div>
                </a>
            </div>
            <!--<div class="col1 col12-mob center gray floatLeft padingAside20 marginTop15 marginBottom15 block">Ó</div>
        <div class="col5half col12-mob floatLeft">
            <div class="buttonM darkGray radius100 font16 colorWhite letterspacing1.5 mobileAuto" style="cursor: pointer;">SUBIR MI PDF DE BURÓ DE CRÉDITO</div>
        </div> -->
            <div class="loadingContainer clearFix clearFloat">
                <div class="loadingBar marginTop50 hide">
                    <div class="loadingActive"></div>
                    <p class="mAuto lightGray font14 marginTop10">CONSULTANDO TU BURÓ DE CRÉDITO</p>
                </div>
            </div>
        </div>
        <div id="accionesSuccess" style="display: none;" class="clearFix clearFloat">
            <div class="colorGreen buttonM avanzaBtn radius100 font16 colorWhite letterspacing1.5 center blockAuto" style="cursor: pointer;">CONSULTA EXITOSA</div>
        </div>
        <div id="accionesError" style="display: none;" class="clearFix">
            <div class="col5 col12-tab floatLeft">
                <div class="buttonOrange buttonM avanzaBtn radius100 font16 colorWhite letterspacing1.5 blockAuto" style="cursor: pointer;">CONSULTA FALLIDA</div>
            </div>
            <div class="clearFloat tablet mobile"></div>
            <div class="phoneStaus col6 col8-tab col12-mob blockAuto floatRight clearFix">
                <div class="col9  col8-mob floatLeft">
                    <div class="clearFix paddingAside15">
                        <img class="floatLeft" src="${resource(dir:'images', file:'phone.png')}" alt="contact" />
                        <div class="floatLeft marginLeft10">
                            <p class="formTitleColor font16 letterspacing1.5">NOS
                                COMUNICAREMOS</p>
                            <p class="formTitleColor font16 letterspacing1.5">CONTIGO.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</section>
<footer class="footerContainer">
    <g:render template="stepBarTest" />
    <div class="mobile">
        <g:if test="${pasoActual.mostrarEnBarra}">
            <g:if test="${pasoActual?.ultimoPaso}">
                <div class="paddingAside15 clearFix">
                    <div class="<g:if test="${documentosSubidos?.comprobanteDeDomicilio && documentosSubidos?.identificacion}"> blueButton colorWhite pointer </g:if> grayrectangle floatLeft solicitud_modal"> ENVIAR MI SOLICITUD </div>
                </div>
            </g:if>
            <g:else>
                <div class="paddingAside15 clearFix">
                    <div class="grayrectangle floatLeft marginRight10">Atras</div>
                    <div data-numero-de-paso="${pasoActual.numeroDePaso + 1}" id="circuloPaso${pasoActual.numeroDePaso + 1}" class="botonCambioDePaso grayrectangle floatLeft nextBtn mobileChange">Ir al paso ${pasoActual?.numeroDePaso + 1}</div>
                </div>
            </g:else>
        </g:if>
    </div>
</footer>

<div id="modalAutorizacion" class="modal fade" style="display: none;">
    <g:render template="/templates/solicitud/paso5/autorizacion" />
</div>
