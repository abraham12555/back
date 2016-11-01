<section class="container paddingTop30 paddingBottom20 contentHeight">
    <div class="container clearFix relative autoMargin width920">
        <div class="infoBox  desktop">
            <div class="width600 autoMargin">
                <p class="displayInline textUpper floatLeft center letterspacing0.5 font13 paddingTop20 paddingRight10 paddingBottom10">LLENA ESTA SECCION EN  MENOS DE 1 MINUTO, CONECTA TU CUENTA DE </p>
                <div class="blueBoxLinkedIn floatRight marginTop10">
                    <p class="textUpper colorWhite center font13 paddingTop10 paddingBottom5">LINKED IN </p>
                </div>
            </div>
        </div>
        <div class="line18 floatLeft"></div>
        <div class="crosCircle floatLeft">
            <p class="center cross"><i class="fa fa-times" aria-hidden="true"></i></p>
        </div>
    </div>
    <div class="padding20 formStep">
        <p class="font35 marginTop28 letterspacing1 formTitleColor lineHeight60">
            <span class="showOnFill">Mi profesión es
                <input type="text" class="inputsFormulario formValues <g:if test="${generales?.profesion}"> notEmpty headingColor </g:if>" style="width: 50%; text-align: center;" name="profesion" placeholder="Profesión" value="${generales?.profesion}">
                </span>
                <span class="hide showOnFill"> y trabajo en
                <input type="text" class="inputsFormulario formValues <g:if test="${generales?.empresa}"> notEmpty headingColor </g:if>" style="width: 35%; text-align: center;" name="empresa" placeholder="Empresa" value="${generales?.empresa}">
                </span>
                <span class="hide showOnFill">,
                y mi puesto es
                <input type="text" class="inputsFormulario formValues <g:if test="${generales?.puesto}"> notEmpty headingColor </g:if>" style="width: 35%; text-align: center;" name="puesto" placeholder="Puesto" value="${generales?.puesto}">
                </span>
                <span class="showOnFill hide">, laboro en esta empresa desde hace
                <span class="width40 inline selectWrap">
                    <g:if test="${generales?.periodo}">
                        <g:select id="noPeriodo" name="noPeriodo" class="formulariOptions width100 gray formValues notEmpty headingColor"  from="${1..30}" noSelection="['':'0']" value="${generales?.periodo}"/>
                    </g:if>
                    <g:else>
                        <g:select id="noPeriodo" name="noPeriodo" class="formulariOptions width100 gray formValues"  from="${1..30}" noSelection="['':'0']"/>
                    </g:else>
                </span>
                <span class="width140 inline selectWrap">
                    <g:if test="${generales?.plazo}">
                        <g:select id="plazo" name="plazo" class="formulariOptions gray formValues notEmpty headingColor" from="${temporalidadList}" optionKey="id" optionValue="nombre" noSelection="['':'Tipo']" value="${generales?.plazo}"/>
                    </g:if>
                    <g:else>
                        <g:select id="plazo" name="plazo" class="formulariOptions gray formValues notEmpty headingColor"  from="${temporalidadList}" optionKey="id" optionValue="nombre" noSelection="['':'Tipo']" value="3"/>
                    </g:else>
                </span>
                <span class="afterSelect">
                    <i class="fa fa-caret-down" aria-hidden="true"></i>
                </span>
            </span> 
            <span class="showOnFill hide">y mi tipo de contrato es
                <span class="width280 inline selectWrap">
                    <g:if test="${generales?.contrato}">
                        <g:select style="text-align:center;" class="formulariOptions gray formValues notEmpty headingColor" name="contrato" from="${tipoDeContratoList}" optionKey="id" noSelection="['':'Tipo de Contrato']" value="${generales?.contrato}"/>
                    </g:if>
                    <g:else>
                        <g:select style="text-align:center;" class="formulariOptions gray formValues" name="contrato" from="${tipoDeContratoList}" optionKey="id" noSelection="['':'Tipo de Contrato']"/>
                    </g:else>
                    <span class="afterSelect">
                        <i class="fa fa-caret-down" aria-hidden="true"></i>
                    </span>.
                </span>
            </span>.
            <span class="showOnFill hide">Mi Jefe inmediato es
                <input type="text" class="inputsFormulario formValues <g:if test="${generales?.jefeInmediato}"> notEmpty headingColor </g:if>" style="width: 50%; text-align: center;" name="jefeInmediato" placeholder="Nombre del Jefe Inmediato" value="${generales?.jefeInmediato}">
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
    <div class="padding20 hide formStep">
        <p class="font35 marginTop28 letterspacing1 formTitleColor lineHeight60">
            <span class="showOnFill">El giro de la empresa es
                <span class="width280 inline selectWrap">
                    <g:if test="${generales?.giroEmpresarial}">
                        <g:select style="text-align:center;" class="formulariOptions gray formValues notEmpty headingColor" name="giroEmpresarial" from="${giroEmpresarialList}" optionKey="id" noSelection="['':'Giro Empresarial']" value="${generales?.giroEmpresarial}"/>
                    </g:if>
                    <g:else>
                        <g:select style="text-align:center;" class="formulariOptions gray formValues" name="giroEmpresarial" from="${giroEmpresarialList}" optionKey="id" noSelection="['':'Giro Empresarial']"/>
                    </g:else>
                    <span class="afterSelect">
                        <i class="fa fa-caret-down" aria-hidden="true"></i>
                    </span>.
                </span>
            </span>
            <span class="showOnFill hide">, la actividad que realizo es 
                <input type="text" class="inputsFormulario formValues <g:if test="${generales?.actividad}"> notEmpty headingColor </g:if>" style="width: 50%; text-align: center;" name="actividad" placeholder="Actividad" value="${generales?.actividad}">
                </span>
                <span class="hide showOnFill"> y consiste en: 
                <textArea class="inputsFormulario formValues <g:if test="${generales?.explicacionActividad}"> notEmpty headingColor </g:if>" style="width: 100%; height: 20%; text-align: center;" name="explicacionActividad" placeholder="Explique la Actividad">${generales?.explicacionActividad}</textArea>
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
    <div class="padding20 clearFix hide formStep">
        <p class="font35 marginTop30 letterspacing1 formTitleColor lineHeight60">
            <span class="showOnFill">Está ubicada en
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
                        <span class="hide showOnFill"> El número telefónico es el
                <input type="text" class="inputsFormulario width210 headingColor formValues <g:if test="${generales?.telefono}"> notEmpty headingColor </g:if>" name="telefono" id="telefono" maxlength="10" minlength="10" min placeholder="(55)4185 2233)" value="${generales?.telefono}" onKeyPress="return numbersonly(this, event)">
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
    <div class="padding20 clearFix hide formStep">
         <p class="headingColor font35 marginTop5 letterspacing1">Referencias Personal 1 (Familiar)</p>
        <p class="font35 marginTop30 letterspacing1 formTitleColor lineHeight60">
                <span class="showOnFill">Nombre Completo
                <input type="text" style="text-align:center; width: 65%;" class="inputsFormulario width200 formValues headingColor <g:if test="${generales?.referencia1NombreCompleto}"> notEmpty headingColor </g:if>" id="referencia1NombreCompleto" name="referencia1NombreCompleto" placeholder="Nombre Completo del Familiar" value="${generales?.referencia1NombreCompleto}">
                </span>
                <span class="showOnFill">Email
                <input type="text" style="text-align:center; width: 35%;" class="inputsFormulario width200 formValues headingColor <g:if test="${generales?.referencia1Email}"> notEmpty headingColor </g:if>" id="referencia1Email" name="referencia1Email" placeholder="mimail@dominio.com" value="${generales?.referencia1Email}">
                </span>
                <span class="showOnFill">Teléfono Celular
                <input type="text" style="text-align:center; width: 23%;" class="inputsFormulario width200 formValues headingColor <g:if test="${generales?.referencia1Celular}"> notEmpty headingColor </g:if>" id="referencia1Celular" name="referencia1Celular" maxlength="10" minlength="10" placeholder="(55)4185 2233)" value="${generales?.referencia1Celular}">
                </span>
                <span class="showOnFill">Teléfono Particular
                <input type="text" style="text-align:center; width: 23%;" class="inputsFormulario width200 formValues headingColor <g:if test="${generales?.referencia1Particular}"> notEmpty headingColor </g:if>" id="referencia1Particular" name="referencia1Particular" maxlength="10" minlength="10" placeholder="(55)4185 2233)" value="${generales?.referencia1Particular}">
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
        <div class="padding20 clearFix hide formStep">
         <p class="headingColor font35 marginTop5 letterspacing1">Referencias Personal 2 (No Familiar)</p>
        <p class="font35 marginTop30 letterspacing1 formTitleColor lineHeight60">
                <span class="showOnFill">Nombre Completo
                <input type="text" style="text-align:center; width: 65%;" class="inputsFormulario width200 formValues headingColor <g:if test="${generales?.referencia2NombreCompleto}"> notEmpty headingColor </g:if>" id="referencia2NombreCompleto" name="referencia2NombreCompleto" placeholder="Nombre Completo" value="${generales?.referencia2NombreCompleto}">
                </span>
                <span class="showOnFill">Email
                <input type="text" style="text-align:center; width: 35%;" class="inputsFormulario width200 formValues headingColor <g:if test="${generales?.referencia2Email}"> notEmpty headingColor </g:if>" id="referencia2Email" name="referencia2Email" placeholder="mimail@dominio.com" value="${generales?.referencia2Email}">
                </span>
                <span class="showOnFill">Teléfono Celular
                <input type="text" style="text-align:center; width: 23%;" class="inputsFormulario width200 formValues headingColor <g:if test="${generales?.referencia2Celular}"> notEmpty headingColor </g:if>" id="referencia2Celular" name="referencia2Celular" maxlength="10" minlength="10" placeholder="(55)4185 2233)" value="${generales?.referencia2Celular}">
                </span>
                <span class="showOnFill">Teléfono Particular
                <input type="text" style="text-align:center; width: 23%;" class="inputsFormulario width200 formValues headingColor <g:if test="${generales?.referencia2Particular}"> notEmpty headingColor </g:if>" id="referencia2Particular" name="referencia2Particular" maxlength="10" minlength="10" placeholder="(55)4185 2233)" value="${generales?.referencia2Particular}">
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
        <div class="padding20 clearFix hide formStep lastStep">
         <p class="headingColor font35 marginTop5 letterspacing1">Referencias Personal 3 (No Familiar)</p>
        <p class="font35 marginTop30 letterspacing1 formTitleColor lineHeight60">
                <span class="showOnFill">Nombre Completo
                <input type="text" style="text-align:center; width: 65%;" class="inputsFormulario width200 formValues headingColor <g:if test="${generales?.referencia3NombreCompleto}"> notEmpty headingColor </g:if>" id="referencia3NombreCompleto" name="referencia3NombreCompleto" placeholder="Nombre Completo" value="${generales?.referencia3NombreCompleto}">
                </span>
                <span class="showOnFill">Email
                <input type="text" style="text-align:center; width: 35%;" class="inputsFormulario width200 formValues headingColor <g:if test="${generales?.referencia3Email}"> notEmpty headingColor </g:if>" id="referencia3Email" name="referencia3Email" placeholder="mimail@dominio.com" value="${generales?.referencia3Email}">
                </span>
                <span class="showOnFill">Teléfono Celular
                <input type="text" style="text-align:center; width: 23%;" class="inputsFormulario width200 formValues headingColor <g:if test="${generales?.referencia3Celular}"> notEmpty headingColor </g:if>" id="referencia3Celular" name="referencia3Celular" maxlength="10" minlength="10" placeholder="(55)4185 2233)" value="${generales?.referencia3Celular}">
                </span>
                <span class="showOnFill">Teléfono Particular
                <input type="text" style="text-align:center; width: 23%;" class="inputsFormulario width200 formValues headingColor <g:if test="${generales?.referencia3Particular}"> notEmpty headingColor </g:if>" id="referencia3Particular" name="referencia3Particular" maxlength="10" minlength="10" placeholder="(55)4185 2233)" value="${generales?.referencia3Particular}">
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
            <div class="greenrectangle floatLeft nextBtn">Ir al paso 4</div>
        </div>
    </div>
</footer>