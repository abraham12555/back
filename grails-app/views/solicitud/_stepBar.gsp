<form class="sendValues" name="formPaso" id="formPaso" method="post">
    <input type="hidden" name="siguientePaso" id="siguientePaso">
    <input type="hidden" name="pasoAnterior" id="pasoAnterior" value="${paso}">
</form>
<div class=" <g:if test="${paso == 6}"> width850 </g:if><g:else> width600 </g:else> clearFix desktop tablet">
    <div id="circuloPaso1" class="grayCircle center floatLeft">
        <p id="pPaso1" class="paddingTop5 footerTextColor font18">1</p>
    </div>
    <div class="line20 floatLeft"></div>
    <div id="circuloPaso2" class="grayCircle center floatLeft">
        <p id="pPaso2" class="paddingTop5 footerTextColor font18">2</p>
    </div>
    <div class="line20 floatLeft"></div>
    <div id="circuloPaso3" class="grayCircle center floatLeft">
        <p id="pPaso3" class="paddingTop5 footerTextColor font18">3</p>
    </div>
    <div class="line20 floatLeft"></div>
    <div id="circuloPaso4" class="grayCircle center floatLeft">
        <p id="pPaso4" class="paddingTop5 footerTextColor font18">4</p>
    </div>
    <div class="line20 floatLeft"></div>
    <div id="circuloPaso5" class="grayCircle center floatLeft">
        <p id="pPaso5" class="paddingTop5 footerTextColor font18">5</p>
    </div>
    <!--<div class="line20 floatLeft"></div>
    <div id="circuloPaso6" class="grayCircle center floatLeft">
        <p id="pPaso6" class="paddingTop5 footerTextColor font18">6</p>
    </div>-->
    <g:if test="${paso == 5}">
        <div class="line20 floatLeft"></div>
        <div class="center floatLeft width250  solicitud_modal <g:if test="${documentosSubidos?.comprobanteDeDomicilio && documentosSubidos?.identificacion}"> blueButton colorWhite pointer </g:if>" style="width: 250px; height: 45px; background-color: #d4dbe6; border-radius: 50px;">
            <p class="paddingTop10 colorWhite font18">
            ENVIAR MI SOLICITUD
            </p>
        </div>
    </g:if>
</div>