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
    <div class="padding20 formStep lastStep">
        <p class="font35 marginTop28 letterspacing1 formTitleColor lineHeight60">
            <span class="showOnFill">Trabajo en
                <input type="text" class="inputsFormulario width150 formValues <g:if test="${generales?.empresa}"> notEmpty headingColor </g:if>" name="empresa" placeholder="Empresa" value="${generales?.empresa}">
            </span>
            <span class="hide showOnFill">,
                y mi puesto es
                <input type="text" class="inputsFormulario width180 formValues <g:if test="${generales?.puesto}"> notEmpty headingColor </g:if>" name="puesto" placeholder="Puesto" value="${generales?.puesto}">
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
                        <g:select id="plazo" name="plazo" class="formulariOptions gray formValues notEmpty headingColor" from="${[[nombre: 'Día(s)', id: 'dias'],[nombre: 'Mes(es)', id: 'meses'],[nombre: 'Año(s)', id: 'anios']]}" optionKey="id" optionValue="nombre" noSelection="['':'Tipo']" value="${generales?.plazo}"/>
                    </g:if>
                    <g:else>
                        <g:select id="plazo" name="plazo" class="formulariOptions gray formValues"  from="${[[nombre: 'Día(s)', id: 'dias'],[nombre: 'Mes(es)', id: 'meses'],[nombre: 'Año(s)', id: 'anios']]}" optionKey="id" optionValue="nombre" noSelection="['':'Tipo']"/>
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