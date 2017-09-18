<div class=" <g:if test="${pasoActual?.ultimoPaso}"> width850 </g:if><g:else> width600 </g:else> clearFix desktop tablet" <g:if test="${ofertas != null || motivoRechazo}"> style="width: 490px;"</g:if>>
    <g:each status="i" in="${pasosDeSolicitud}" var="paso">
        <g:if test="${paso.mostrarEnBarra}">
            <g:if test="${ofertas != null || motivoRechazo}">
                <div data-numero-de-paso="${paso.numeroDePaso}" id="circuloPaso${paso.numeroDePaso}" class="botonCambioDePaso <g:if test="${paso.numeroDePaso <= pasoActual.numeroDePaso}"> blueCircle colorWhite </g:if><g:elseif test="${paso.numeroDePaso == pasoActual.pasoSiguiente}"> grayCircle sendBtn nextBtn </g:elseif><g:else>grayCircle</g:else> center floatLeft" <g:if test="${paso.numeroDePaso == pasoActual.numeroDePaso}"> disabled </g:if><g:elseif test="${paso.numeroDePaso == pasoActual.pasoSiguiente}"> style="width: 250px; height: 45px; marginTop: 0px" </g:elseif> >
                    <p id="pPaso${paso.numeroDePaso}" class="<g:if test="${paso.numeroDePaso <= pasoActual.numeroDePaso}"> paddingTop10 </g:if><g:else> paddingTop5 footerTextColor </g:else>" style="font-size:17px;">${ ((paso.numeroDePaso == pasoActual.pasoSiguiente) ? "IR AL PASO " : "") + paso.numeroDePaso}</p>
                    </div>
            </g:if>
            <g:else>
                <div data-numero-de-paso="${paso.numeroDePaso}" id="circuloPaso${paso.numeroDePaso}" class="botonCambioDePaso <g:if test="${paso.numeroDePaso <= pasoActual.numeroDePaso}"> blueCircle colorWhite </g:if><g:else>grayCircle</g:else> center floatLeft <g:if test="${configuracion.navegacionLibre}">freeNav</g:if><g:elseif test="${(paso.numeroDePaso < pasoActual.numeroDePaso) && (paso.numeroDePaso < configuracion.pasoLimiteNavegacionLibre) && (!reporteBuroCredito || errorConsulta)}">freeNav</g:elseif>" <g:if test="${paso.numeroDePaso == pasoActual.numeroDePaso}"> disabled </g:if> >
                    <p id="pPaso${paso.numeroDePaso}" class="<g:if test="${paso.numeroDePaso <= pasoActual.numeroDePaso}"> paddingTop10 </g:if><g:else> paddingTop5 footerTextColor </g:else> font18">${paso.numeroDePaso}</p>
                    </div>
            </g:else>
            <g:if test="${!paso?.ultimoPaso}">
                <div class="line20 floatLeft"></div>
            </g:if>
        </g:if>
    </g:each>
    <g:if test="${pasoActual?.ultimoPaso}">
        <div class="line20 floatLeft"></div>
        <div class="center floatLeft width250  solicitud_modal <g:if test="${(documentosSubidos?.comprobanteDeDomicilio && documentosSubidos?.identificacion) || configuracion.subirDocumentosOpcional }"> blueButton colorWhite pointer </g:if>" style="width: 250px; height: 45px; background-color: #d4dbe6; border-radius: 50px;">
                <p class="paddingTop10 colorWhite font18">
                ENVIAR MI SOLICITUD
            </p>
        </div>
    </g:if>
</div>
