<section class="container paddingTop20 paddingBottom20 clearFix contentHeight">
    <div class="container clearFix relative autoMargin width920">
        <div class="infoBox  desktop">
            <div style="width: 695px;" class="width670 autoMargin">
                <p class="displayInline textUpper floatLeft center letterspacing0.5 font13 paddingTop20 paddingRight10 paddingBottom10">LLENA ESTA SECCION EN  MENOS DE 1 MINUTO TOMA UNA FOTO DE TU </p>
                <div style="width: 225px;" class="blueBox floatRight marginTop10">
                    <p class="textUpper colorWhite font13 paddingTop10 paddingRight10 paddingLeft15 paddingBottom5">comprobante de domicilio</p>
                </div>
            </div>
        </div>
        <div class="line18 "></div>
        <div class="crosCircle ">
            <p class="center cross"><i class="fa fa-times" aria-hidden="true"></i></p>
        </div>
    </div>
    <div class="padding20 clearFix formStep lastStep">
        <p class="font35 marginTop30 letterspacing1 formTitleColor lineHeight60"><span class="showOnFill">Mi dirección es:
                <input type="text" class="inputsFormulario formValues width200" name="calle" placeholder="calle">,
                <input type="text" class="inputsFormulario formValues width200" name="noExterior" placeholder="No. Exterior">,
                <input type="text" class="inputsFormulario formValues width200" name="noInterior" placeholder="No. Interior">.</span> <span class="hide showOnFill">Mi código postal es
                <input type="text" class="inputsFormulario formValues width100" name="codigoPostal" placeholder="00001"> y está  ubicado en La Colonia
                <span class="width290 inline selectWrap">
                    <g:select noSelection="['':'Colonia...']" class="formulariOptions gray formValues" optionKey="id" optionValue="nombre" name="colonia" from="${coloniaList}" />
                </span>,
            </span><span class="hide showOnFill"> Mi
                <span class="width240 inline selectWrap">
                    <select class="formulariOptions gray formValues" name="tipoDelegacion">
                        <option value="delegacion">Delegación</option>
                        <option value="municipio">Municipio</option>
                    </select>
                </span>
                <span class="afterSelect">
                    <i class="fa fa-caret-down" aria-hidden="true"></i>
                </span>es
                <span class="width290 inline selectWrap">
                    <g:select noSelection="['':'Delegación/Municipio...']" class="formulariOptions gray formValues" optionKey="id" optionValue="nombre" name="delegacion" from="${municipioList}" />
                </span>
                y estoy en el estado de
                <span class="width290 inline selectWrap">
                    <g:select noSelection="['':'Estado...']" class="formulariOptions gray formValues"  optionKey="id" optionValue="nombre" name="estado" from="${estadoList}" />.
                </span>
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