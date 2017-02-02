<section class="container paddingTop30 paddingBottom20 contentHeight">
    <input type="hidden" id="tipoDePaso" value="${pasoActual?.tipoDePaso?.nombre}">
    <input type="hidden" id="tituloDelPaso" value="${pasoActual?.titulo}"/>
    <form method="POST" name="formFormulario" id="formFormulario">
        <input type="hidden" name="siguientePaso" id="siguientePaso">
        <input type="hidden" name="pasoAnterior" id="pasoAnterior" value="${pasoActual?.numeroDePaso}">
        <div class="padding20">
            <img class="happines" src="${resource(dir:'images', file:'happines.png')}" alt="happines" title="happines"/>
            <p class="center marginLeft24 font25 formTitleColor letterspacing2.3">¡FELICIDADES!</p>
            <p class="center marginLeft24 font16 formTitleColor letterspacing1.5 marginTop12">Has terminado tu solicitúd, estás a un click de saber tu resultado.</p>
            <p class="center marginLeft24 font16 colorblue letterspacing1.5 marginTop12">Solo te falta subir tu documentación, toma una foto de tus documentos</p>
            <p class="center marginLeft24 font16 colorblue letterspacing1.5 marginTop12">desde tu smartphone.</p>
            <div class="marginTop50 clearFix">
                <div class="floatLeft col5 col6-tab col12-mob">
                    <div class="paddingAside15">
                        <div id="paso6IdOf" class="colorWhite <g:if test="${documentosSubidos?.identificacion}"> colorGreen </g:if><g:else> darkGray </g:else> radius100 buttonM mobileAuto greenClick pointer">SUBE TU IDENTIFICACIÓN OFICIAL</div>
                        </div>
                    </div>
                    <div class="floatRight col5 col6-tab col12-mob">
                        <div class="paddingAside15">
                                <div id="paso6CompDom" class="colorWhite <g:if test="${documentosSubidos?.comprobanteDeDomicilio}"> colorGreen </g:if><g:else> darkGray </g:else> radius100 buttonM mobileAuto greenClick pointer">SUBE TU COMPROBANTE DE DOMICILIO</div>
                        </div>
                    </div>
                </div>
                <div class="marginTop50 clearFix">
                    <center>
                        <div class="col5 col6-tab col12-mob">
                            <div class="paddingAside15">
                                    <div id="paso6Docto" class="colorWhite darkGray radius100 buttonM mobileAuto greenClick pointer">SUBE TU ${productoSolicitud?.documentoElegido?.nombre?.toUpperCase()}</div>
                        </div>
                    </div>
                </center>
            </div>
        </div>
    </form>
</section>
<footer class="footerContainer">
    <g:render template="stepBarTest"/>
</footer>
<g:render template="modalDocumentos"/>
<div class="solicitudLightbox hide" id="resumen_solicitud">
    <div class="overlay"></div>
    <div class="resumenSolicitud lightboxPos">
        <div class="padding20">
            <h2 class="fontWeight500 darkBluetitle font25 center paddingTop10 paddingBottom10">RESUMEN DE SOLICITUD</h2>
            <div class="borderLine marginTop12 marginBottom25"></div>
            <div class="padding20">
                <div class="fontWeight500 font18 darkBluetitle">
                    <p>ESTÁS SOLICITANDO UN CRÉDITO 
                        <g:if test="${productoSolicitud?.rubroDeAplicacion}">
                            ${productoSolicitud.producto.nombreDelProducto.toUpperCase()}
                        </g:if>
                        <g:else>
                            DE <span class="headingColor"> ${productoSolicitud.producto.nombreDelProducto.toUpperCase()} </span>
                        </g:else>
                        <g:if test="${productoSolicitud?.rubroDeAplicacion}">
                            POR
                            <span class="headingColor"><g:formatNumber number="${productoSolicitud?.montoDelCredito + productoSolicitud?.montoDelSeguroDeDeuda}" format="\044###,###,###.##" /> MXN</span>
                            , PARA UTILIZARLO EN 
                            <span class="headingColor"> ${productoSolicitud.rubroDeAplicacion.nombre.toUpperCase()} </span>.
                        </g:if>
                        <g:else>
                            POR
                            <span class="headingColor"><g:formatNumber number="${productoSolicitud?.montoDelCredito}" format="\044###,###,###.##" /> MXN</span>.
                        </g:else>    
                    </p>
                    <p class="paddingTop20">A UNA TAZA ORDINARIA DEL
                        <span class="headingColor"> ${((productoSolicitud.producto.tasaDeInteres * 12) * 100).round(2)} % ANUAL </span>
                    </p>
                    <p class="paddingTop20">TUS PAGOS SERÁN ${(productoSolicitud.periodicidad.nombre + "es").toUpperCase()} POR </p>
                    <p> 
                        <span class="headingColor"> <g:formatNumber number="${productoSolicitud?.montoDelPago}" format="\044###,###,###.##" /> MXN </span>
                    </p>
                    <p class="paddingTop20">
                        POR UN PERIODO DE
                        <span class="headingColor"> ${productoSolicitud?.plazos} ${(productoSolicitud.periodicidad.nomenclatura).toUpperCase()}.</span>
                    </p>
                </div>
                <div class="greenTitle paddingTop20">
                    <p>Es posible que más adelante se te solicite compartir esta</p>
                    <p>solicitud con un aval para minimizar el riesgo y ofrecerte</p>
                    <p>mejores condiciones </p>
                </div>

            </div>
            <div class="paddingBottom10" style="width: 70%;margin: auto;">
                <div class="fontWeight500 font14 darkBluetitle">
                    <p>¿POR QUÉ MEDIO TE ENTERASTE DE NOSOTROS?</p>
                    <p>
                        <g:select noSelection="['':'Elije una Opción...']" class="modalSelect" name="medioDeContacto" id="medioDeContacto" from="${mediosDeContacto}" optionKey="id" />
                    </p>
                    <p>
                        <select class="modalSelect" id="opcionMedioDeContacto" name="opcionMedioDeContacto" noSelection="['':'Elija primero el medio...']">
                        </select>
                    </p>
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
                <div data-numero-de-paso="${(pasoActual.numeroDePaso + 1)}" id="terminarSolicitud" class="width350 blockAuto rectangleRound marginTop30 center font20 letterspacing1.8 textUpper enviarSolicitud">
                    enviar mi solicitud
                </div>
            </div>
        </div>
    </div>
</div>