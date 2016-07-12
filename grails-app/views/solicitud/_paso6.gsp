<section class="pasoBanner clearFix">
    <div class="container">
        <div class="floatLeft borderGrayRight">
            <div class="marginTop14 marginBottom14 marginRight95">
                <p class="gray textUpper letterspacing1 font16">paso 6</p>
                <p class="headingColor textUpper letterspacing1.5 font25 marginRight80 paddingBottom8">documentos</p>
            </div>
        </div>
        <div class="floatRight">
            <p class="progresoTitle textUpper marginLeft10 floatLeft">tu progreso en la aplicación</p>
            <div class="floatLeft clearFix">
                <div class="width400 borderBlue marginTop38 floatLeft"></div>
                <div class="width36 gradientBlue radius100 paddingTop2 paddingBottom2 paddingRight2 paddingLeft1 marginTop27 floatLeft center">
                    <p class="colorWhite displayInline center paddingTop2 paddingRight5 paddingBottom2 paddingLeft5 font12">99%</p>
                </div>
                <div class="borderGray marginTop38 floatLeft"></div>
                <div class="circle floatLeft"></div>
            </div>
        </div>
    </div>
</section>
<section class="container marginTop28 marginBottom64 clearFix">
    <div class="width800 marginTop69">
        <img class="happines" src="${resource(dir:'images', file:'happines.png')}" alt="happines" title="happines"/>
        <p class="center marginLeft24 font25 formTitleColor letterspacing2.3">¡FELICIDADES!</p>
        <p class="center marginLeft24 font16 formTitleColor letterspacing1.5 marginTop12">Has terminado tu solicitúd, estás a un click de saber tu resultado.</p>
        <p class="center marginLeft24 font16 colorblue letterspacing1.5 marginTop12">Solo te falta subir tu documentación, toma una foto de tus documentos</p>
        <p class="center marginLeft24 font16 colorblue letterspacing1.5 marginTop12">desde tu smartphone.</p>
        <div class="marginTop50">
            <div class="floatLeft marginLeft40">
                <button id="idOficial" type="button" class="basic darkGray width354 radius100 colorWhite center paddingTop10 paddingBottom10" onclick="abrirModal('modalIds');">SUBE TU IDENTIFICACIÓN OFICIAL</button>
            </div>
            <div class="floatRight marginLeft40">
                <button id="compDomicilio" type="button" class="darkGray width354 radius100 colorWhite center paddingTop10 paddingBottom10" onclick="abrirModal('modalComp');">SUBE TU COMPROBANTE DE DOMICILIO</button>
            </div>
        </div>
    </div>
</section>
<footer class="footerContainer">
    <div class="center autoMargin">
        <button type="button" class="width337 GrayButton radius100 gray center paddingTop15 paddingBottom15 font20 letterspacing1.8" onclick="abrirModal('modalPaso7');">ENVIAR MI SOLICITUD</button>
    </div>
</footer>
<div class="modal" id="modalIds" style="display:none;">
    <p>
        <g:render template="/templates/solicitud/paso4/cargaDeIdentificaciones"/>
    </p>
</div>
<div class="modal" id="modalComp" style="display:none;">
    <p>
        <g:render template="/templates/solicitud/paso4/cargaDeComprobanteDomiciliario"/>
    </p>
</div>
<div class="modal" id="modalPaso7" style="display:none; vertical-align: middle;">
    <p>
        <g:render template="paso7"/>
    </p>
</div>
