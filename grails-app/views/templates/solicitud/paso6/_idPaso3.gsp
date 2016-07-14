<div class="col12 clearFix">
    <div class="col6 col12-mob floatLeft mobileDiv">
        <div class="marginLeft21">
            <p class="upldsTitles gray font16 fontWeight600 marginTop22 marginBottom19">
                Captura la <span class="headingColor">vuelta </span> de tu identificación con tu webcam
            </p>
        </div>
        <div class="marginLeft21">
            <input type="hidden" id="imagenCapturada" name="imagenCapturada" value="" />
            <div id="webcam" style="height:320px;width: 400px; margin-top: 5%; text-align: center;"></div>
        </div>
    </div>
    <div class="col6 col12-mob floatLeft mobileDiv2">
        <div class="marginTop80 marginLeft10 ">
            <p class="upldsTitles gray font16 fontWeight600 latterspacing1">
                COMPARA LOS EJEMPLOS
            </p>
        </div>
        <div class="width65p">
            <img class="upldsWebimage imgResponsive" src="${resource(dir:'images', file:'ids.png')}" alt="ids" />
        </div>
        <div class="marginLeft10">
            <p class=" upldsTitles colorRed font16 latterspacing1">
                Tu documento no puede tener tachaduras o
            </p>
            <p class="upldsTitles colorRed font16 latterspacing1">
                enmendaduras y debe ser legible y claro
            </p>
        </div>
    </div>
</div>
<div class="col8 col12-mob clearFix marginBottom40">
    <div class="floatLeft col3 col12-mob">
        <div class="center colorGreen width210 marginTop10 radius100 " style="margin-left: 5%;">
            <button type="button" id="repetirFoto" class="buttonOrange width210 radius100 colorWhite font16 fontWeight600 paddingTop9 paddingBottom9 center letterspacing1" onclick="inicializarCamara();">
                REPETIR FOTO
            </button>
        </div>
    </div>
    <div class="floatLeft col6 col12-mob">
        <div class="center colorGreen width210 marginTop10 radius100 " style="margin-left: 15%;">
            <button type="button" id="guardarFoto" class="colorGreen width210 radius100 colorWhite font16 fontWeight600 paddingTop9 paddingBottom9 center letterspacing1" onclick="guardarFoto();">
                GUARDAR FOTO
            </button>
        </div>
    </div>
</div>