<section class="container paddingTop20 paddingBottom20 clearFix contentHeight serializeForm">
    <input type="hidden" id="pasoPrellenado" value="${generales?.llenadoPrevio}"/>
    <div class="defaultBubble <g:if test="${generales?.llenadoPrevio && (generales?.nombre || generales?.apellidoPaterno || generales?.apellidoMaterno || generales?.nombreCompleto || generales?.apellidos || generales?.sexo || generales?.curp || generales?.rfc)}"> hide </g:if> ">
        <div class="container clearFix relative autoMargin width920">
            <div class="infoBox floatLeft desktop">
                <div class="width529 autoMargin">
                    <p class="displayInline textUpper floatLeft center letterspacing0.5 font13 paddingTop20 paddingRight10 paddingBottom10">llena tu formulario en menos de 1 minuto,</p>
                    <div class="blueBox floatRight marginTop10">
                        <p class="textUpper colorWhite font13 paddingTop10 paddingRight10 paddingLeft15 paddingBottom5">conecta tu facebook</p>
                    </div>
                </div>
            </div>
            <div class="line18 floatLeft desktop "></div>
            <div class="crosCircle floatLeft desktop ">
                <p class="center cross"><i class="fa fa-times" aria-hidden="true"></i></p>
            </div>
        </div>
    </div>
    <div class="successBubble <g:if test="${!generales?.llenadoPrevio}"> hide </g:if>">
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
    <div class="padding15 step1 formStep">
        <p class="headingColor font35 marginTop5 letterspacing1">Cuentanos tu historia:</p>
        <p class="font35 marginTop30 letterspacing1 formTitleColor lineHeight60">
            <span class="showOnFill">Mi nombre es
                <input type="text" class="inputsFormulario width200 formValues headingColor <g:if test="${generales?.nombre ?: generales?.nombrePersona}"> notEmpty headingColor </g:if>" style="text-align:center;" id="nombre" name="nombre" placeholder="Jorge" value="${generales?.nombre ?: generales?.nombrePersona}"> <input type="text" style="text-align:center;" class="inputsFormulario width200 formValues headingColor <g:if test="${generales?.apellidoPaterno}"> notEmpty headingColor </g:if>" id="apellidoPaterno" name="apellidoPaterno" placeholder="Perez" value="${generales?.apellidoPaterno}"> <input type="text" style="text-align:center;" class="inputsFormulario width200 formValues headingColor <g:if test="${generales?.apellidoMaterno}"> notEmpty headingColor </g:if>" id="apellidoMaterno" name="apellidoMaterno" placeholder="Lopez" value="${generales?.apellidoMaterno}">
                </span>
                        <span class="hide showOnFill">, soy
                <span class="width160 inline selectWrap">
                    <g:if test="${generales?.sexo}">
                        <g:select style="text-align:center;" noSelection="['':'Sexo']" class="formulariOptions formValues notEmpty headingColor" name="sexo" id="sexo" from="${generoList}" optionKey="id" value="${generales?.sexo}"/>
                    </g:if>
                    <g:else>
                        <g:select style="text-align:center;" noSelection="['':'Sexo']" class="formulariOptions gray formValues" name="sexo" id="sexo" from="${generoList}" optionKey="id" value="${generales?.sexo}"/>
                    </g:else>
                </span>
            </span>
            <span class="hide showOnFill">y nací el&nbsp;

                <span class="width70 inline selectWrap">
                    <g:if test="${generales?.dia}">
                        <g:select id="dia" name="dia" class="formulariOptions formValues notEmpty headingColor"  from="${1..31}" noSelection="['':'Día']" value="${generales?.dia}"/>
                    </g:if>
                    <g:else>
                        <g:select id="dia" name="dia" class="formulariOptions gray formValues "  from="${1..31}" noSelection="['':'Día']" value="${generales?.dia}"/>
                    </g:else>
                    </span>
                    <span class="afterSelect">
                        <i class="fa fa-caret-down" aria-hidden="true"></i>
                    </span>
                    <span class="hide showOnFill">
                    de&nbsp;
                    <span class="width200 inline selectWrap">
                    <g:if test="${generales?.mes}">
                        <g:select id="mes" name="mes" class="formulariOptions formValues notEmpty headingColor"  from="${['Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre']}" noSelection="['':'Mes']" value="${generales?.mes}"/>
                    </g:if>
                    <g:else>
                        <g:select id="mes" name="mes" class="formulariOptions gray formValues "  from="${['Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre']}" noSelection="['':'Mes']" value="${generales?.mes}"/>
                    </g:else>
                        </span>
                        <span class="afterSelect">
                            <i class="fa fa-caret-down" aria-hidden="true"></i>
                        </span>
                    </span>
                <% def anioActual = Calendar.getInstance().get(Calendar.YEAR) %>
                <span class="hide showOnFill">
                    de&nbsp;
                    <span class="width100 inline selectWrap">
                        <g:if test="${generales?.anio}">
                            <g:select id="anio" name="anio" class="formulariOptions width100 formValues notEmpty headingColor"  from="${(anioActual-18)..(anioActual-120)}" noSelection="['':'Año']" value="${generales?.anio}"/>
                        </g:if>
                        <g:else>
                            <g:select id="anio" name="anio" class="formulariOptions width100 gray formValues"  from="${(anioActual-18)..(anioActual-120)}" noSelection="['':'Año']" value="${generales?.anio}"/>
                        </g:else>
                    </span>
                    <span class="afterSelect">
                        <i class="fa fa-caret-down" aria-hidden="true"></i>
                    </span>
                </span>
            </span>
            <br/>
            <span class="hide showOnFill show2"> en
                <span class="width354 inline selectWrap">
                    <g:if test="${generales?.entidad}">
                        <g:select style="text-align:center;" noSelection="['':'Estado']" class="formulariOptions formValues notEmpty headingColor" name="estado" from="${estadoList}" optionKey="id" value="${generales?.entidad}" />
                    </g:if>
                    <g:else>
                        <g:select style="text-align:center;" noSelection="['':'Estado']" class="formulariOptions gray formValues" name="estado" from="${estadoList}" optionKey="id" value="${generales?.entidad}" />
                    </g:else>    
                </span>
                <span class="afterSelect">
                    <i class="fa fa-caret-down" aria-hidden="true"></i>
                </span>
            </span>
            <span class="hide showOnFill show2">    
                , mi estado civil es&nbsp;
                <span class="width220 inline selectWrap">
                    <g:if test="${generales?.entidad}">
                        <g:select style="text-align:center;" noSelection="['':'Estado Civil']" class="formulariOptions formValues notEmpty headingColor" name="estadoCivil" id="estadoCivil" from="${estadoCivilList}" optionKey="id" value="${generales?.estadoCivil}" />
                    </g:if>
                    <g:else>
                        <g:select style="text-align:center;" noSelection="['':'Estado Civil']" class="formulariOptions gray formValues" name="estadoCivil" id="estadoCivil" from="${estadoCivilList}" optionKey="id" value="${generales?.estadoCivil}" />
                    </g:else>
                </span>
                <span class="afterSelect">
                    <i class="fa fa-caret-down" aria-hidden="true"></i>
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

    <div class="padding15 formStep hide lastStep">
        <p class="font35 marginTop28 letterspacing1 formTitleColor lineHeight60">
            <span class="hide showOnFill">Mi nivel escolar es
                <span class="width240 inline selectWrap">
                    <g:select id="nivelEscolar" name="nivelEscolar" class="formulariOptions width100 gray formValues"  from="${escolaridadList}" optionKey="id" noSelection="['':'Nivel Escolar']" value="${generales?.nivelEscolar}"/>
                </span>
                <span class="afterSelect">
                    <i class="fa fa-caret-down" aria-hidden="true"></i>
                </span>
            </span>
            <span class="hide showOnFill"> y mi número telefónico es el
                <input type="text" class="inputsFormulario width210 headingColor formValues" name="telefono" maxlength="10" minlength="10" min placeholder="(55)4185 2233)" value="${generales?.numeroCasa}">
            </span>
            <span class="hide showOnFill"> y mi celular es el
                <input type="text" class="inputsFormulario width210 headingColor formValues" name="celular" maxlength="10" minlength="10" placeholder="(55)2345 2345)" value="${generales?.numeroCelular}">.
            </span>
            <span class="hide showOnFill"> Mi RFC es
                <input type="text" class="inputsFormulario width210 headingColor formValues <g:if test="${generales?.rfc} "> notEmpty headingColor </g:if>" name="rfc" maxlength="13" placeholder="AAAA000000AZ0" value="${generales?.rfc}">
            </span>
            <span class="hide showOnFill"> y mi CURP es
                <input type="text" class="inputsFormulario width300 headingColor formValues <g:if test="${generales?.curp} "> notEmpty headingColor </g:if>" name="curp" maxlength="18" placeholder="CURP" value="${generales?.curp}">.
            </span>
            <br/>
            <span class="hide showOnFill"> Mi conyugue es
                <input type="text" class="inputsFormulario width500 headingColor formValues" name="nombreConyugue" placeholder="Maria Hernández Pérez" ${generales?.conyugue}>
            </span>
            <span class="hide showOnFill">
                , de mi dependen&nbsp;
                <span class="width80 inline selectWrap">
                    <g:select id="dependientes" name="dependientes" class="formulariOptions width80 gray formValues"  from="${1..20}" noSelection="['':'0']" value="${generales?.dependientes}"/>
                </span>
                <span class="afterSelect">
                    <i class="fa fa-caret-down" aria-hidden="true"></i>
                </span> persona(s).
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
            <div class="grayrectangle floatLeft nextBtn">Ir al paso 2</div>
        </div>
    </div>
</footer>
<script>
    $(document).ready(function() {
        if("${tipoLogin}" == 'FB'){
            fillFB("${datosLogin.encodeAsJSON()}");
        }else if("${tipoLogin}" == 'Google'){
            fillGoogle("${datosLogin.encodeAsJSON()}");
    }
    });
</script>