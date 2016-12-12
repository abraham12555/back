<section class="container paddingTop20 paddingBottom20 clearFix contentHeight serializeForm">
    <input type="hidden" id="pasoPrellenado" value="${generales?.llenadoPrevio}"/>
    <input type="hidden" id="tituloDelPaso" value="${pasoActual?.titulo}"/>
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
        <p class="headingColor font35 letterspacing1" style="padding: 20px 15px 0 15px;">${pasoActual?.subtitulo}</p>
    <input type="hidden" id="tipoDePaso" value="${pasoActual?.tipoDePaso?.nombre}">
    <form method="POST" name="formFormulario" id="formFormulario">
        <input type="hidden" name="siguientePaso" id="siguientePaso">
        <input type="hidden" name="pasoAnterior" id="pasoAnterior" value="${pasoActual?.numeroDePaso}">
        <div class="centerLink slideStep" style="display: none;">
            <ul style="list-style-type: none;">
                <li style="display: inline;"><a class="prev" onclick="plusSlides(-1)"><i class="fa fa-chevron-up"></i></a></li>
                <li style="display: inline;"><a class="next" onclick="plusSlides(1)"><i class="fa fa-chevron-down"></i></a></li>
            </ul>
        </div>
        <g:each status="p" in="${parrafos.keySet()}" var="parrafo">
            <fieldset class="step1 formStep<g:if test="${ p > 0 }"> hide</g:if><g:if test="${ (p + 1) == (parrafos.keySet().size()) }"> lastStep</g:if>" style="padding: 0px 15px;">
                    <div class="font35 marginTop30 letterspacing1 formTitleColor lineHeight60">
                    <g:if test="${pasoActual.modoDeDespliegue == "NARRATIVA"}">
                        <g:each status="i" in="${parrafos[parrafo]}" var="campo">
                            <span data-id="${i}" <g:if test="${campo.dependeDe}"> data-depende-de="${campo.dependeDe}" data-valor-dependencia="${campo.valorDeDependencia}" </g:if> class="showOnFill <g:if test="${ campo.mostrarAlInicio == false }"> hide </g:if> <g:if test="${ campo.obligatorio }"> required </g:if><g:else> nonRequired </g:else>">${campo.textoAnterior}
                                <g:if test="${campo.campo.tipoDeCampo.elementoDeEntrada.equals("TEXTFIELD")}">
                                    <input type="text" style="text-align:center; width: ${campo.longitudDelCampo};" class="inputsFormulario formValues headingColor <g:if test="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"}"> notEmpty headingColor </g:if>" id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" placeholder="${campo.placeholder}" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"}"> 
                                </g:if>
                                <g:if test="${campo.campo.tipoDeCampo.elementoDeEntrada.equals("TEXTAREA")}">
                                    <textArea class="inputsFormulario formValues <g:if test="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"}"> notEmpty headingColor </g:if>" style="width: 100%; height: 20%; text-align: center;" id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" placeholder="${campo.placeholder}">${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"}</textArea>
                                    </g:if>
                                    <g:if test="${campo.campo.tipoDeCampo.elementoDeEntrada.equals("SELECT")}">
                                        <% 
def listaDeElementos = []
if(campo.campo.catalogo){
    listaDeElementos = (Class.forName(campo.campo.claseCatalogo)).list()
} else{
    listaDeElementos = (Class.forName(campo.campo.claseAsociada.className)).constraints."$campo.campo.nombreDelCampo".inList
}
%>
                            <span class="inline selectWrap" style="text-align:center; width: ${campo.longitudDelCampo};">
                                        <g:if test="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"}">
                                            <g:select id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" class="formulariOptions width100 gray formValues notEmpty headingColor"  from="${listaDeElementos}" optionKey="id" noSelection="${Eval.me(campo.placeholder)}" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"}"/>
                                        </g:if>
                                        <g:else>
                                            <g:select id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" class="formulariOptions width100 gray formValues"  from="${listaDeElementos}" optionKey="id" noSelection="${Eval.me(campo.placeholder)}" />
                                        </g:else>
                            </span>
                            <span class="afterSelect">
                                <i class="fa fa-caret-down" aria-hidden="true"></i>
                            </span>
                                </g:if>
                                <g:if test="${campo.campo.tipoDeCampo.elementoDeEntrada.equals("FECHA")}">
                            <span class="width70 inline selectWrap">
                                        <g:if test="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"?.dia}">
                                            <g:select id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_dia"}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_dia"}" class="formulariOptions formValues notEmpty headingColor"  from="${1..31}" noSelection="['':'Día']" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"?.dia}"/>
                                        </g:if>
                                        <g:else>
                                            <g:select id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_dia"}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_dia"}" class="formulariOptions gray formValues "  from="${1..31}" noSelection="['':'Día']" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"?.dia}"/>
                                        </g:else>
                            </span>
                            <span class="afterSelect">
                                <i class="fa fa-caret-down" aria-hidden="true"></i>
                            </span>
                            <!--<span class="hide showOnFill">-->
                                    de&nbsp;
                                <span class="width200 inline selectWrap">
                                        <g:if test="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"?.mes}">
                                            <g:select id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_mes"}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_mes"}" class="formulariOptions formValues notEmpty headingColor" from="${[[id: 1, mes: 'Enero'],[id: 2, mes: 'Febrero'],[id: 3, mes: 'Marzo'],[id: 4, mes: 'Abril'],[id: 5, mes: 'Mayo'],[id: 6, mes: 'Junio'],[id: 7, mes: 'Julio'],[id: 8, mes: 'Agosto'],[id: 9, mes: 'Septiembre'],[id: 10, mes: 'Octubre'],[id: 11, mes: 'Noviembre'],[id: 12, mes: 'Diciembre']]}" optionKey="id" optionValue="mes" noSelection="['':'Mes']" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"?.mes}"/>
                                        </g:if>
                                        <g:else>
                                            <g:select id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_mes"}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_mes"}" class="formulariOptions gray formValues " from="${[[id: 1, mes: 'Enero'],[id: 2, mes: 'Febrero'],[id: 3, mes: 'Marzo'],[id: 4, mes: 'Abril'],[id: 5, mes: 'Mayo'],[id: 6, mes: 'Junio'],[id: 7, mes: 'Julio'],[id: 8, mes: 'Agosto'],[id: 9, mes: 'Septiembre'],[id: 10, mes: 'Octubre'],[id: 11, mes: 'Noviembre'],[id: 12, mes: 'Diciembre']]}" optionKey="id" optionValue="mes" noSelection="['':'Mes']" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"?.mes}"/>
                                        </g:else>
                                </span>
                                <span class="afterSelect">
                                    <i class="fa fa-caret-down" aria-hidden="true"></i>
                                </span>
                        <!--</span>-->
                                    <% def anioActual = Calendar.getInstance().get(Calendar.YEAR) %>
                            <!--<span class="hide showOnFill">-->
                                    de&nbsp;
                                <span class="width100 inline selectWrap">
                                        <g:if test="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"?.anio}">
                                            <g:select id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_anio"}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_anio"}" class="formulariOptions width100 formValues notEmpty headingColor"  from="${(anioActual-18)..(anioActual-120)}" noSelection="['':'Año']" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"?.anio}"/>
                                        </g:if>
                                        <g:else>
                                            <g:select id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_anio"}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_anio"}" class="formulariOptions width100 gray formValues"  from="${(anioActual-18)..(anioActual-120)}" noSelection="['':'Año']" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"?.anio}"/>
                                        </g:else>
                                </span>
                                <span class="afterSelect">
                                    <i class="fa fa-caret-down" aria-hidden="true"></i>
                                </span>
                        <!--</span>-->
                                </g:if>
                                <g:if test="${campo.campo.tipoDeCampo.elementoDeEntrada.equals("MESANIO")}">
                                <span class="width200 inline selectWrap">
                                        <g:if test="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"?.mes}">
                                            <g:select id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_mes"}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_mes"}" class="formulariOptions formValues notEmpty headingColor" from="${[[id: 1, mes: 'Enero'],[id: 2, mes: 'Febrero'],[id: 3, mes: 'Marzo'],[id: 4, mes: 'Abril'],[id: 5, mes: 'Mayo'],[id: 6, mes: 'Junio'],[id: 7, mes: 'Julio'],[id: 8, mes: 'Agosto'],[id: 9, mes: 'Septiembre'],[id: 10, mes: 'Octubre'],[id: 11, mes: 'Noviembre'],[id: 12, mes: 'Diciembre']]}" optionKey="id" optionValue="mes" noSelection="['':'Mes']" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"?.mes}"/>
                                        </g:if>
                                        <g:else>
                                            <g:select id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_mes"}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_mes"}" class="formulariOptions gray formValues " from="${[[id: 1, mes: 'Enero'],[id: 2, mes: 'Febrero'],[id: 3, mes: 'Marzo'],[id: 4, mes: 'Abril'],[id: 5, mes: 'Mayo'],[id: 6, mes: 'Junio'],[id: 7, mes: 'Julio'],[id: 8, mes: 'Agosto'],[id: 9, mes: 'Septiembre'],[id: 10, mes: 'Octubre'],[id: 11, mes: 'Noviembre'],[id: 12, mes: 'Diciembre']]}" optionKey="id" optionValue="mes" noSelection="['':'Mes']" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"?.mes}"/>
                                        </g:else>
                                </span>
                                <span class="afterSelect">
                                    <i class="fa fa-caret-down" aria-hidden="true"></i>
                                </span>
                                    <% def anioActualParcial = Calendar.getInstance().get(Calendar.YEAR) %>
                                    de&nbsp;
                                <span class="width100 inline selectWrap">
                                        <g:if test="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"?.anio}">
                                            <g:select id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_anio"}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_anio"}" class="formulariOptions width100 formValues notEmpty headingColor"  from="${(anioActualParcial)..(anioActualParcial-120)}" noSelection="['':'Año']" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"?.anio}"/>
                                        </g:if>
                                        <g:else>
                                            <g:select id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_anio"}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_anio"}" class="formulariOptions width100 gray formValues"  from="${(anioActualParcial)..(anioActualParcial-120)}" noSelection="['':'Año']" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"?.anio}"/>
                                        </g:else>
                                </span>
                                <span class="afterSelect">
                                    <i class="fa fa-caret-down" aria-hidden="true"></i>
                                </span>
                                </g:if>
                                <g:if test="${campo.campo.tipoDeCampo.elementoDeEntrada.equals("PERIODO")}">
                                    <span class="width80 inline selectWrap">
                                        <g:if test="${generales?."$campo.campo.claseAsociada"?.tiempo}">
                                            <g:select id="${campo.campo.claseAsociada.nombre + "_tiempo"}" name="${campo.campo.claseAsociada.nombre + "_tiempo"}" class="formulariOptions width100 gray formValues notEmpty headingColor"  from="${1..80}" noSelection="['':'0']" value="${generales?."$campo.campo.claseAsociada"?.tiempo}"/>
                                        </g:if>
                                        <g:else>
                                            <g:select id="${campo.campo.claseAsociada.nombre + "_tiempo"}" name="${campo.campo.claseAsociada.nombre + "_tiempo"}" class="formulariOptions width100 gray formValues"  from="${1..80}" noSelection="['':'0']"/>
                                        </g:else>
                                    </span>
                                    <span class="width120 inline selectWrap">
                                        <g:if test="${generales?."$campo.campo.claseAsociada"?.temporalidad}">
                                            <g:select noSelection="['':'Elija']" class="formulariOptions formValues gray notEmpty headingColor"  optionKey="id" optionValue="nombre" name="${campo.campo.claseAsociada.nombre + "_temporalidad"}" from="${la.kosmos.app.Temporalidad.list()}" value="${generales?."$campo.campo.claseAsociada"?.temporalidad}" />
                                        </g:if>
                                        <g:else>
                                            <g:select noSelection="['':'Elija']" class="formulariOptions gray formValues notEmpty headingColor"  optionKey="id" optionValue="nombre" name="${campo.campo.claseAsociada.nombre + "_temporalidad"}" from="${la.kosmos.app.Temporalidad.list()}" value="3"/>
                                        </g:else>
                                    </span>.
                                </g:if>
                                <g:if test="${campo.campo.tipoDeCampo.elementoDeEntrada.equals("SERIE")}">
                                            <span class="inline selectWrap" style="text-align:center; width: ${campo.longitudDelCampo};">
                                        <g:if test="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"}">
                                            <g:select id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" class="formulariOptions gray formValues notEmpty headingColor"  from="${0..20}" noSelection="${Eval.me(campo.placeholder)}" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"}" />
                                        </g:if>
                                        <g:else>
                                            <g:select id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" class="formulariOptions gray formValues" from="${0..20}" noSelection="${Eval.me(campo.placeholder)}"/>
                                        </g:else>
                </span>
                <span class="afterSelect">
                    <i class="fa fa-caret-down" aria-hidden="true"></i>
                </span>
                                </g:if>
                                <g:if test="${campo.campo.tipoDeCampo.elementoDeEntrada.equals("AUTOCOMPLETE")}">
                                    <span id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_remote"}">
                                        <input type="text" style="text-align:center;" class="inputsFormulario formValues width120 typeahead tt-input <g:if test="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"}"> notEmpty headingColor </g:if>" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" placeholder="${campo.placeholder}" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"}"/>
                                    </span> 
                                </g:if>
                                <g:if test="${campo.campo.tipoDeCampo.elementoDeEntrada.equals("NUMERICO")}">
                            <input type="text" style="text-align:center; width: ${campo.longitudDelCampo};" class="inputsFormulario formValues headingColor <g:if test="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"}"> notEmpty headingColor </g:if>" id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" placeholder="${campo.placeholder}" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"}" onKeyPress="return numbersonly(this, event)"> 
                                </g:if>
                                <g:if test="${campo.campo.tipoDeCampo.elementoDeEntrada.equals("TELEFONO")}">
                                    <input type="text" style="text-align:center; width: ${campo.longitudDelCampo};" class="inputsFormulario formValues headingColor <g:if test="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"}"> notEmpty headingColor </g:if>" id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" placeholder="${campo.placeholder}" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"}" data-mask="99-99-99-99-99"> 
                                </g:if>
                                ${campo.textoPosterior}
                    </span>
                            <g:if test="${campo.saltoDeLineaAlFinal}">
                        <br/>
                            </g:if>    
                        </g:each>
                    </g:if>
        </div>
        <div class="confirmDiv hide col7 col12-tab floatRight marginTop28 clearFix">
            <div class="floatLeft marginBottom20">
                <p class="font25 marginTop5 headingColor marginRight10"></p>
            </div>
            <div class="clearFloat mobile"></div>
            <div class="buttonM mobileAuto lightBlueBg floatLeft colorWhite textUpper letterspacing0.8 radius100">
                        Continuar
            </div>
        </div>
    </fieldset>
        </g:each>
    </form>
</section>
<footer class="footerContainer">
    <g:render template="stepBarTest"/>
    <div class="mobile">
        <div class="paddingAside15 clearFix">
            <div class="grayrectangle floatLeft marginRight10">Atras</div>
            <div class="grayrectangle floatLeft nextBtn">Ir al paso 2</div>
        </div>
    </div>
</footer>
<g:if test="${!logueado}">
    <g:render template="login"/>
</g:if>
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