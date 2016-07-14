<div class="whiteContainer width95 paddingBottom60 ">
    <h1 class="headingColor marginLeft30 font25 letterspacing1.5 marginTop23 marginBottom14 displayInline">SUBE TU IDENTIFICACIÓN OFICIAL</h1>
    <div class="floatRight borderGray radius100 marginTop23 marginRight17">
        <button type="button" style="background: #f8f9ff;" class="borderGray radius100 textUpper gray paddingTop7 paddingBottom5 font18 paddingLeft24" onclick="cerrarModal();">cerrar<i class="fa fa-times paddingLeft5 paddingRight20" aria-hidden="true"></i></button>
    </div>
    <div class="col12 border1"></div>
    <div class="col12 clearFix">
        <div class="col4 floatLeft">
            <p id="labelPaso1" class="center paddingTop18 paddingBottom15 headingColor font16 borderGrayRight">PASO 1 /  TIPO DE IDENTIFICACIÓN</p>
        </div>
        <div class="col4 floatLeft ">
            <p id="labelPaso2" class="center paddingTop18 paddingBottom15 gray font16 borderGrayRight">PASO 2 /  CAPTURA FRENTE</p>
        </div>
        <div class="col4 floatLeft">
            <p id="labelPaso3" class="center paddingTop18 paddingBottom15 gray font16">PASO 3 /  CAPTURA VUELTA</p>
        </div>
    </div>
    <div id="paso1ModalIdentificaciones">
        <g:render template="/templates/solicitud/paso6/idPaso1"/>
    </div>
    <div id="paso2ModalIdentificaciones" style="display: none;">
        <g:render template="/templates/solicitud/paso6/idPaso2"/>
    </div>
    <div id="paso2WebcamModalIdentificaciones" style="display: none;">
        <g:render template="/templates/solicitud/paso6/idPaso2Webcam"/>
    </div>
    <div id="paso3ModalIdentificaciones" style="display: none;">
        <g:render template="/templates/solicitud/paso6/idPaso3"/>
    </div>
</div>
