<div class=" <g:if test="${pasoActual?.ultimoPaso}"> width850 </g:if><g:else> width600 </g:else> clearFix desktop tablet">
    <g:each status="i" in="${pasosDeSolicitud}" var="paso">
        <g:if test="${paso.mostrarEnBarra}">
            <div data-numero-de-paso="${paso.numeroDePaso}" id="circuloPaso${paso.numeroDePaso}" class="botonCambioDePaso <g:if test="${paso.numeroDePaso <= pasoActual.numeroDePaso}"> blueCircle colorWhite </g:if><g:else>grayCircle</g:else> center floatLeft" <g:if test="${paso.numeroDePaso == pasoActual.numeroDePaso}"> disabled </g:if> >
                <p id="pPaso${paso.numeroDePaso}" class="<g:if test="${paso.numeroDePaso <= pasoActual.numeroDePaso}"> paddingTop10 </g:if><g:else> paddingTop5 footerTextColor </g:else> font18">${paso.numeroDePaso}</p>
                </div>
            <g:if test="${!paso?.ultimoPaso}">
                <div class="line20 floatLeft"></div>
            </g:if>
        </g:if>
    </g:each>
    <g:if test="${pasoActual?.ultimoPaso}">
        <div class="line20 floatLeft"></div>
        <div class="center floatLeft width250  solicitud_modal <g:if test="${documentosSubidos?.comprobanteDeDomicilio && documentosSubidos?.identificacion}"> blueButton colorWhite pointer </g:if>" style="width: 250px; height: 45px; background-color: #d4dbe6; border-radius: 50px;">
                <p class="paddingTop10 colorWhite font18">
                ENVIAR MI SOLICITUD
            </p>
        </div>
    </g:if>
</div>