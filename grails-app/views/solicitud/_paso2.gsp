<section class="container paddingTop20 paddingBottom20 clearFix contentHeight">
    <div class="defaultBubble">
        <div class="container clearFix relative autoMargin width920">
            <div class="infoBox  desktop">
                <div style="width: 695px;" class="width670 autoMargin">
                    <p class="displayInline textUpper floatLeft center letterspacing0.5 font13 paddingTop20 paddingRight10 paddingBottom10">LLENA ESTA SECCION EN  MENOS DE 1 MINUTO TOMA UNA FOTO DE TU </p>
                    <a id="paso2CompDom">
                        <div style="width: 225px;" class="blueBox floatRight marginTop10">
                            <p class="textUpper colorWhite font13 paddingTop10 paddingRight10 paddingLeft15 paddingBottom5">comprobante de domicilio</p>
                        </div>
                    </a>
                </div>
            </div>
            <div class="line18 "></div>
            <div class="crosCircle ">
                <p class="center cross"><i class="fa fa-times" aria-hidden="true"></i></p>
            </div>
        </div>
    </div>
    <div class="successBubble hide">
        <div class="container clearFix relative autoMargin width920">
            <div class="infoBoxGreen floatLeft">
                <div class="infoContainer4c">
                    <p class="center letterspacing0.5 font13 paddingLeft15 paddingTop15 paddingBottom10 colorWhite marginTop5">TUS DATOS SE PRE-LLENARON EXITOSAMENTE, PORFAVOR VERIFICA QUE TU INFORMACIÓN SEA CORRECTA</p>
                </div>
            </div>
            <div class="line18 colorGreen floatLeft"></div>
            <div class="crosCircle floatLeft colorGreen">
                <p class="center marginTop5 font12 colorWhite"><i class="fa fa-times" aria-hidden="true"></i></p>
            </div>
        </div>
    </div>
    <div class="padding20 clearFix formStep lastStep">
        <p class="font35 marginTop30 letterspacing1 formTitleColor lineHeight60">
            <span class="showOnFill">Mi dirección es
                <input type="text" style="text-align:center;" class="inputsFormulario formValues width200" id="calle" name="calle" placeholder="Calle">,
                <input type="text" style="text-align:center;" class="inputsFormulario formValues width200" id="noExterior" name="noExterior" placeholder="No. Exterior">,
                <input type="text" style="text-align:center;" class="inputsFormulario formValues width200" id="noInterior" name="noInterior" placeholder="No. Interior">,
            </span>  
            <span class="hide showOnFill">
                Colonia
                <input type="text" style="text-align:center;" class="inputsFormulario formValues width200" name="colonia" placeholder="Colonia" />, 
            </span>
            <span class="hide showOnFill" id="cpRemote"> C. P.
                <input type="text" style="text-align:center;" class="inputsFormulario formValues width120 typeahead tt-input" name="codigoPostal" id="codigoPostal" placeholder="00000"/>.
            </span> 
            <span class="hide showOnFill"> Mi
                <span class="width220 inline selectWrap">
                    <select class="formulariOptions gray formValues notEmpty headingColor" name="tipoDelegacion">
                        <option selected>Tipo</option>
                        <option value="delegacion">Delegación</option>
                        <option value="municipio">Municipio</option>
                    </select>
                </span>
                <span class="afterSelect">
                    <i class="fa fa-caret-down" aria-hidden="true"></i>
                </span>es
                <span class="width290 inline selectWrap">
                    <g:select noSelection="['':'Delegación/Municipio']" class="formulariOptions gray formValues" optionKey="id" optionValue="nombre" name="delegacion" from="${municipioList}" />
                </span>
                en el estado de
                <span class="width290 inline selectWrap">
                    <g:select noSelection="['':'Estado']" class="formulariOptions gray formValues"  optionKey="id" optionValue="nombre" name="estado" from="${estadoList}" />
                </span>.
            </span>
            <span class="hide showOnFill"> La vivienda es
                <span class="width120 inline selectWrap">
                    <g:select noSelection="['':'Elija']" class="formulariOptions gray formValues"  optionKey="id" optionValue="nombre" name="tipoDeVivienda" from="${tiposDeVivienda}" />
                </span>
            </span>
            <span class="hide showOnFill"> y vivo en ella desde hace
                <span class="width80 inline selectWrap">
                    <g:select id="tiempo" name="tiempo" class="formulariOptions width100 gray formValues"  from="${1..80}" noSelection="['':'0']"/>
                </span>
                <span class="width120 inline selectWrap">
                    <g:select noSelection="['':'Elija']" class="formulariOptions gray formValues"  optionKey="id" optionValue="nombre" name="temporalidad" from="${temporalidadList}" />
                </span>.
            </span>
        </p>

        <div class="confirmDiv hide col7 col12-tab floatRight marginTop28 clearFix">
            <div class="floatLeft marginBottom20">
                <p class="font25 marginTop5 headingColor marginRight10"> Mi información es correcta</p>
            </div>
            <div class="clearFloat mobile"></div>
            <div class="buttonM mobileAuto lightBlueBg floatLeft colorWhite textUpper letterspacing0.8 radius100">
                confirmar
            </div>
        </div>
    </div>
</section>
<footer class="footerContainer">
    <form class="sendValues" name="formPaso2" id="formPaso2" method="post">
        <input type="hidden" name="siguientePaso" id="siguientePaso" value="3">
    </form>
    <div class="width600 clearFix desktop tablet">
        <div class="grayCircle center floatLeft">
            <p class="paddingTop5 footerTextColor font18">1</p>
        </div>
        <div class="floatLeft line20"></div>
        <div class="blueCircle center floatLeft">
            <p class="colorWhite font18 paddingTop10">2</p>
        </div>
        <div class="line20 floatLeft"></div>
        <div class="rectangle250 center floatLeft nextBtn">
            <p class="textUpper footerTextColor font18 paddingTop10">ir al paso 3</p>
        </div>
        <div class="line20 floatLeft"></div>
        <div class="line20 floatLeft"></div>
        <div class="grayCircle center floatLeft">
            <p class="paddingTop5 footerTextColor font18">4</p>
        </div>
        <div class="line20 floatLeft"></div>
        <div class="grayCircle center floatLeft">
            <p class="paddingTop5 footerTextColor font18">5</p>
        </div>
        <div class="line20 floatLeft"></div>
        <div class="grayCircle center floatLeft">
            <p class="paddingTop5 footerTextColor font18">6</p>
        </div>
    </div>
    <div class="mobile">
        <div class="paddingAside15 clearFix">
            <div class="grayrectangle floatLeft marginRight10">Atras</div>
            <div class="grayrectangle floatLeft nextBtn">Ir al paso 3</div>
        </div>
    </div>
</footer>
<style type="text/css">
    .typeahead, .tt-query, .tt-hint {
    border-bottom: 2px solid #9EA1BE;
    padding: 8px 12px;
    }
    .typeahead {
    background-color: #F1F3FA;
    }
    .typeahead:focus {
    border: 2px solid #0097CF;
    }
    .tt-query {
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
    }
    .tt-hint {
    color: #999999;
    }
    .tt-menu {
    background-color: #F1F3FA;
    border: 1px solid rgba(0, 0, 0, 0.2);
    border-radius: 8px;
    box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
    margin-top: 12px;
    padding: 8px 0;
    }
    .tt-suggestion {
    line-height: 24px;
    padding: 3px 20px;
    }
    .tt-suggestion.tt-is-under-cursor {
    background-color: #0097CF;
    color: #FFFFFF;
    }
    .tt-suggestion p {
    margin: 0;
    }
</style>