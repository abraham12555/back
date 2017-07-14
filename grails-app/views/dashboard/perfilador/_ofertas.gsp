<g:external dir="css" file="perfilador.css" title="text/css" rel="stylesheet" />
<br />
<h2>Ofertas disponibles</h2>
<g:if test="${ofertas.size() > 1}">
    <div class="promos" style="height: 1100px;">
</g:if>
<g:else>
    <div class="promos" style="height: 500px;">
    </g:else>
    <div id="wrapper_bu">
        <div id="listaDeOfertasDiv"></div>
    </div>
</div>
<input type="hidden" id="ofertasJSON" value="${ofertas as grails.converters.JSON}">
<g:if test="${ofertas.size() < 1}">
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
                        <div data-numero-de-paso="${pasoActual.numeroDePaso + 1}" id="circuloPaso${pasoActual.numeroDePaso + 1}" class="botonCambioDePaso grayrectangle floatLeft nextBtn">Ir al paso ${pasoActual?.numeroDePaso + 1}</div>
                    </div>
                </g:else>
            </g:if>
        </div>
    </footer>
</g:if>