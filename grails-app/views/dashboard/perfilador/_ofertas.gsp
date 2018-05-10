<g:external dir="css" file="swiper.min.css" title="text/css" rel="stylesheet" />
<g:external dir="css" file="ofertas.css" title="text/css" rel="stylesheet" />
<g:external dir="js" file="swiper.min.js" />
<br />
<h2>Ofertas disponibles</h2>

<g:if test="${ofertas && ofertas.size() > 0}">
    <input type="hidden" id="ofertasJSON" value="${ofertas as grails.converters.JSON}">
    <div class="col12 floatLeft stepsContainer" style="height: 1100px;">
</g:if>
<g:else>
    <g:if test= "${motivoRechazo}">
        <input type="hidden" id="motivoRechazo" value="${motivoRechazo}">
    </g:if>
    <div class="col12 floatLeft stepsContainer" style="height: 500px;">
</g:else>
    <div id="wrapper_bu">
        <div id="listaDeOfertasDiv" class="credit-proposals swiper-container"></div>
    </div>
</div>
<g:if test="${!ofertas || (ofertas && ofertas.size() == 0)}">
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
                        <div data-numero-de-paso="${pasoActual.numeroDePaso}" id="circuloPaso${pasoActual.numeroDePaso}" class="botonCambioDePaso grayrectangle floatLeft nextBtn sendBtn mobileChange">Ir al paso ${pasoActual?.numeroDePaso}</div>
                    </div>
                </g:else>
            </g:if>
        </div>
    </footer>
</g:if>
