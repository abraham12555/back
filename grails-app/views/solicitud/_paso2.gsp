<section class="container paddingTop20 paddingBottom20 clearFix contentHeight">
    <input type="hidden" id="pasoPrellenado" value="${generales?.llenadoPrevio}"/>
    <div class="defaultBubble <g:if test="${generales?.llenadoPrevio && (generales?.direccion || generales?.calle || generales?.municipio || generales?.estado)}"> hide </g:if> ">
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
        <div class="successBubble <g:if test="${!generales?.llenadoPrevio}"> hide </g:if> ">
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
                <input type="text" style="text-align:center; width: 700px;" class="inputsFormulario formValues <g:if test="${generales?.calle ?: generales?.direccion}"> notEmpty headingColor </g:if>" id="calle" name="calle" placeholder="Calle" value="${generales?.calle ?: generales?.direccion}">,
                <input type="text" style="text-align:center;" class="inputsFormulario formValues width200 <g:if test="${generales?.noExterior}"> notEmpty headingColor </g:if>" id="noExterior" name="noExterior" placeholder="No. Exterior" value="${generales?.noExterior}">,
                <input type="text" style="text-align:center;" class="inputsFormulario formValues width200 <g:if test="${generales?.noInterior}"> notEmpty headingColor </g:if>" id="noInterior" name="noInterior" placeholder="No. Interior" value="${generales?.noInterior}">,
                </span>
                <span class="hide showOnFill" id="cpRemote"> C. P.
                <input type="text" style="text-align:center;" class="inputsFormulario formValues width120 typeahead tt-input <g:if test="${generales?.codigoPostal}"> notEmpty headingColor </g:if>" name="codigoPostal" id="codigoPostal" placeholder="00000" value="${generales?.codigoPostal}"/>,
                </span> 
                <span class="hide showOnFill">
                Colonia
                <input type="text" style="text-align:center; width: 385px;" class="inputsFormulario formValues <g:if test="${generales?.colonia}"> notEmpty headingColor </g:if>" name="colonia" placeholder="Colonia" value="${generales?.colonia}"/>, en
                </span>
                <span class="hide showOnFill">
                    <span style="width: 450px;" class="inline selectWrap">
                    <g:if test="${generales?.municipio}">
                        <g:select noSelection="['':'Delegación/Municipio']" class="formulariOptions gray formValues notEmpty headingColor" optionKey="id" optionValue="nombre" name="municipio" from="${municipioList}" value="${generales?.municipio}" />
                    </g:if>
                    <g:else>
                        <g:select noSelection="['':'Delegación/Municipio']" class="formulariOptions gray formValues" optionKey="id" optionValue="nombre" name="municipio" from="${municipioList}" />
                    </g:else>
                </span>
                en el estado de
                <span style="width: 450px;" class="inline selectWrap">
                    <g:if test="${generales?.estado}">
                        <g:select noSelection="['':'Estado']" class="formulariOptions formValues gray notEmpty headingColor"  optionKey="id" optionValue="nombre" name="estado" from="${estadoList}" value="${generales?.estado}"/>
                    </g:if>
                    <g:else>
                        <g:select noSelection="['':'Estado']" class="formulariOptions gray formValues"  optionKey="id" optionValue="nombre" name="estado" from="${estadoList}" />
                    </g:else>
                </span>.
            </span>
            <span class="hide showOnFill"> La vivienda es
                <span class="width120 inline selectWrap">
                    <g:if test="${generales?.tipoDeVivienda}">
                        <g:select noSelection="['':'Elija']" class="formulariOptions formValues gray notEmpty headingColor"  optionKey="id" optionValue="nombre" name="tipoDeVivienda" from="${tiposDeVivienda}" value="${generales?.tipoDeVivienda}"/>
                    </g:if>
                    <g:else>
                        <g:select noSelection="['':'Elija']" class="formulariOptions gray formValues"  optionKey="id" optionValue="nombre" name="tipoDeVivienda" from="${tiposDeVivienda}" />
                    </g:else>
                </span>
            </span>
            <span class="hide showOnFill"> y vivo en ella desde hace
                <span class="width80 inline selectWrap">
                    <g:if test="${generales?.tiempo}">
                        <g:select id="tiempo" name="tiempo" class="formulariOptions width100 gray formValues notEmpty headingColor"  from="${1..80}" noSelection="['':'0']" value="${generales?.tiempo}"/>
                    </g:if>
                    <g:else>
                        <g:select id="tiempo" name="tiempo" class="formulariOptions width100 gray formValues"  from="${1..80}" noSelection="['':'0']"/>
                    </g:else>
                </span>
                <span class="width120 inline selectWrap">
                    <g:if test="${generales?.temporalidad}">
                        <g:select noSelection="['':'Elija']" class="formulariOptions formValues gray notEmpty headingColor"  optionKey="id" optionValue="nombre" name="temporalidad" from="${temporalidadList}" value="${generales?.temporalidad}" />
                    </g:if>
                    <g:else>
                        <g:select noSelection="['':'Elija']" class="formulariOptions gray formValues notEmpty headingColor"  optionKey="id" optionValue="nombre" name="temporalidad" from="${temporalidadList}" value="3"/>
                    </g:else>
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
    <g:render template="stepBar"/>
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