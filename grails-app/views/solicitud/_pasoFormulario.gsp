<section class="container paddingTop20 paddingBottom20 clearFix contentHeight serializeForm">
    <input type="hidden" id="pasoPrellenado" value="${generales?.llenadoPrevio}"/>
    <input type="hidden" id="tituloDelPaso" value="${pasoActual?.titulo}"/>
    <g:if test="${session.noChecarCamposLlenos}">
        <input type="hidden" id="revisionInicial" value="false"/>
    </g:if>
    <g:if test="${!session.tiposDeDocumento?.identificacion && !generales?.llenadoPrevio}">
        <div class="defaultBubble <g:if test="${pasoActual?.tipoDePaso?.nombre == "pasoFormulario" && generales?.llenadoPrevio && generales?.exito}"> hide </g:if> ">
                <div class="container clearFix relative autoMargin width920">
                    <div class="infoBox floatLeft desktop">
                        <div class="width630 autoMargin">
                            <p class="displayInline textUpper floatLeft center letterspacing0.5 font13 paddingTop20 paddingRight10 paddingBottom10">LLENA ESTA SECCIÓN EN MENOS DE 1 MINUTO, TOMA UNA FOTO A TU </p>
                            <div class="blueBox floatRight marginTop10" onclick="openModal('identification_oficial');">
                                <p class="textUpper colorWhite font13 paddingTop10 paddingRight10 paddingLeft15 paddingBottom5">IDENTIFICACIÓN OFICIAL</p>
                            </div>
                        </div>
                    </div>
                    <div class="line18 floatLeft desktop "></div>
                    <div class="crosCircle floatLeft desktop ">
                        <p class="center cross"><i class="fa fa-times" aria-hidden="true"></i></p>
                    </div>
                </div>
            </div>
    </g:if>    
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
        <div id="erroBubble" style="display:none;"></div>
        <p class="filledColor font35 letterspacing1" style="padding: 20px 15px 0 15px;">${pasoActual?.subtitulo}</p>
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
                            <span data-id="${i}" data-subpaso="${p}" <g:if test="${campo.dependeDe}"> data-depende-de="${campo.dependeDe}" data-valor-dependencia="${campo.valorDeDependencia}" </g:if> class="showOnFill <g:if test="${ campo.mostrarAlInicio == false }"> hide </g:if> <g:if test="${ campo.obligatorio }"> required </g:if><g:else> nonRequired </g:else>">${campo.textoAnterior}
                                <g:if test="${campo.campo.tipoDeCampo.elementoDeEntrada.equals("TEXTFIELD")}">
                                    <input type="text" title="${campo.textoAyuda}" style="text-align:center; width: ${campo.longitudDelCampo};" class="inputsFormulario formValues filledColor <g:if test="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"}"> notEmpty headingColor </g:if>" id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" placeholder="${campo.placeholder}" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"}"> 
                                </g:if>
                                <g:if test="${campo.campo.tipoDeCampo.elementoDeEntrada.equals("TEXTAREA")}">
                                    <textArea title="${campo.textoAyuda}" class="inputsFormulario formValues <g:if test="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"}"> notEmpty headingColor </g:if>" style="width: 100%; height: 20%; text-align: center;" id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" placeholder="${campo.placeholder}">${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"}</textArea>
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
                                            <g:if test="${campo.campo.aplicarFiltro}">
                                                <g:select title="${campo.textoAyuda}" id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" class="formulariOptions gray formValues notEmpty headingColor select2"  from="${listaDeElementos}" optionKey="id" noSelection="${Eval.me(campo.placeholder)}" value="${(generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo") as int}"/>
                                            </g:if>
                                            <g:else>
                                                <g:select title="${campo.textoAyuda}" id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" class="formulariOptions gray formValues notEmpty headingColor"  from="${listaDeElementos}" optionKey="id" noSelection="${Eval.me(campo.placeholder)}" value="${(generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo") as int}"/>
                                            </g:else>
                                        </g:if>
                                        <g:else>
                                            <g:if test="${campo.campo.aplicarFiltro}">
                                                <g:select title="${campo.textoAyuda}" id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" class="formulariOptions gray formValues select2"  from="${listaDeElementos}" optionKey="id" noSelection="${Eval.me(campo.placeholder)}" />
                                            </g:if>
                                            <g:else>
                                                <g:select title="${campo.textoAyuda}" id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" class="formulariOptions gray formValues"  from="${listaDeElementos}" optionKey="id" noSelection="${Eval.me(campo.placeholder)}" />
                                            </g:else>
                                        </g:else>
                            </span>
                            <span class="afterSelect">
                                <i class="fa fa-caret-down" aria-hidden="true"></i>
                            </span>
                                </g:if>
                                <g:if test="${campo.campo.tipoDeCampo.elementoDeEntrada.equals("DYNAMICSELECT")}">
                            <span class="inline selectWrap" style="text-align:center; width: ${campo.longitudDelCampo};">
                                        <g:if test="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"}">
                                            <select title="${campo.textoAyuda}" id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" class="formulariOptions gray formValues notEmpty headingColor" value="${(generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo")}">
                                                <option value=''>Elija una opción...</option>
                                                <g:each in="${catalogos?."$campo.campo.nombreDelCampo"}" var="valor">
                                                    <g:if test="${!(valor instanceof String) && valor?.id}">
                                                        <option value="${valor.id}" <g:if test="${valor.id == (generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo" as long)}"> selected</g:if>>${valor}</option>
                                                    </g:if>
                                                    <g:else>
                                                        <option value="${valor}" <g:if test="${valor == generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"}"> selected</g:if>>${valor}</option>
                                                    </g:else>
                                                </g:each>
                                            </select>
                                        </g:if>
                                        <g:else>
                                            <select title="${campo.textoAyuda}" id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" class="formulariOptions gray formValues">
                                                <option value=''>Elija una opción...</option>
                                                <g:each in="${catalogos?."$campo.campo.nombreDelCampo"}" var="valor">
                                                    <g:if test="${valor?.id}">
                                                        <option value="${valor.id}">${valor}</option>
                                                    </g:if>
                                                    <g:else>
                                                        <option value="${valor}">${valor}</option>
                                                    </g:else>
                                                </g:each>
                                            </select>
                                        </g:else>
                            </span>
                            <span class="afterSelect">
                                <i class="fa fa-caret-down" aria-hidden="true"></i>
                            </span>
                                </g:if>
                                <g:if test="${campo.campo.tipoDeCampo.elementoDeEntrada.equals("FECHA")}">
                            <span class="width70 inline selectWrap">
                                        <g:if test="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"?.dia}">
                                            <g:select title="${campo.textoAyuda}" id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_dia"}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_dia"}" class="formulariOptions formValues notEmpty headingColor"  from="${1..31}" noSelection="['':'Día']" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"?.dia}"/>
                                        </g:if>
                                        <g:else>
                                            <g:select title="${campo.textoAyuda}" id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_dia"}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_dia"}" class="formulariOptions gray formValues "  from="${1..31}" noSelection="['':'Día']" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"?.dia}"/>
                                        </g:else>
                            </span>
                            <span class="afterSelect">
                                <i class="fa fa-caret-down" aria-hidden="true"></i>
                            </span>
                            <!--<span class="hide showOnFill">-->
                                    de&nbsp;
                                <span class="width200 inline selectWrap">
                                        <g:if test="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"?.mes}">
                                            <g:select title="${campo.textoAyuda}" id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_mes"}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_mes"}" class="formulariOptions formValues notEmpty headingColor" from="${[[id: 1, mes: 'Enero'],[id: 2, mes: 'Febrero'],[id: 3, mes: 'Marzo'],[id: 4, mes: 'Abril'],[id: 5, mes: 'Mayo'],[id: 6, mes: 'Junio'],[id: 7, mes: 'Julio'],[id: 8, mes: 'Agosto'],[id: 9, mes: 'Septiembre'],[id: 10, mes: 'Octubre'],[id: 11, mes: 'Noviembre'],[id: 12, mes: 'Diciembre']]}" optionKey="id" optionValue="mes" noSelection="['':'Mes']" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"?.mes}"/>
                                        </g:if>
                                        <g:else>
                                            <g:select title="${campo.textoAyuda}" id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_mes"}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_mes"}" class="formulariOptions gray formValues " from="${[[id: 1, mes: 'Enero'],[id: 2, mes: 'Febrero'],[id: 3, mes: 'Marzo'],[id: 4, mes: 'Abril'],[id: 5, mes: 'Mayo'],[id: 6, mes: 'Junio'],[id: 7, mes: 'Julio'],[id: 8, mes: 'Agosto'],[id: 9, mes: 'Septiembre'],[id: 10, mes: 'Octubre'],[id: 11, mes: 'Noviembre'],[id: 12, mes: 'Diciembre']]}" optionKey="id" optionValue="mes" noSelection="['':'Mes']" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"?.mes}"/>
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
                                            <g:select title="${campo.textoAyuda}" id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_anio"}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_anio"}" class="formulariOptions width100 formValues notEmpty headingColor"  from="${(anioActual-18)..(anioActual-68)}" noSelection="['':'Año']" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"?.anio}"/>
                                        </g:if>
                                        <g:else>
                                            <g:select title="${campo.textoAyuda}" id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_anio"}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_anio"}" class="formulariOptions width100 gray formValues"  from="${(anioActual-18)..(anioActual-68)}" noSelection="['':'Año']" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"?.anio}"/>
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
                                            <g:select title="${campo.textoAyuda}" id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_mes"}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_mes"}" class="formulariOptions formValues notEmpty headingColor" from="${[[id: 1, mes: 'Enero'],[id: 2, mes: 'Febrero'],[id: 3, mes: 'Marzo'],[id: 4, mes: 'Abril'],[id: 5, mes: 'Mayo'],[id: 6, mes: 'Junio'],[id: 7, mes: 'Julio'],[id: 8, mes: 'Agosto'],[id: 9, mes: 'Septiembre'],[id: 10, mes: 'Octubre'],[id: 11, mes: 'Noviembre'],[id: 12, mes: 'Diciembre']]}" optionKey="id" optionValue="mes" noSelection="['':'Mes']" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"?.mes}"/>
                                        </g:if>
                                        <g:else>
                                            <g:select title="${campo.textoAyuda}" id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_mes"}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_mes"}" class="formulariOptions gray formValues " from="${[[id: 1, mes: 'Enero'],[id: 2, mes: 'Febrero'],[id: 3, mes: 'Marzo'],[id: 4, mes: 'Abril'],[id: 5, mes: 'Mayo'],[id: 6, mes: 'Junio'],[id: 7, mes: 'Julio'],[id: 8, mes: 'Agosto'],[id: 9, mes: 'Septiembre'],[id: 10, mes: 'Octubre'],[id: 11, mes: 'Noviembre'],[id: 12, mes: 'Diciembre']]}" optionKey="id" optionValue="mes" noSelection="['':'Mes']" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"?.mes}"/>
                                        </g:else>
                                </span>
                                <span class="afterSelect">
                                    <i class="fa fa-caret-down" aria-hidden="true"></i>
                                </span>
                                    <% def anioActualParcial = Calendar.getInstance().get(Calendar.YEAR) %>
                                    de&nbsp;
                                <span class="width100 inline selectWrap">
                                        <g:if test="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"?.anio}">
                                            <g:select title="${campo.textoAyuda}" id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_anio"}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_anio"}" class="formulariOptions width100 formValues notEmpty headingColor"  from="${(anioActualParcial)..(anioActualParcial-68)}" noSelection="['':'Año']" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"?.anio}"/>
                                        </g:if>
                                        <g:else>
                                            <g:select title="${campo.textoAyuda}" id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_anio"}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo + "_anio"}" class="formulariOptions width100 gray formValues"  from="${(anioActualParcial)..(anioActualParcial-68)}" noSelection="['':'Año']" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"?.anio}"/>
                                        </g:else>
                                </span>
                                <span class="afterSelect">
                                    <i class="fa fa-caret-down" aria-hidden="true"></i>
                                </span>
                                </g:if>
                                <g:if test="${campo.campo.tipoDeCampo.elementoDeEntrada.equals("PERIODO")}">
                                    <span class="width80 inline selectWrap">
                                        <g:if test="${generales?."$campo.campo.claseAsociada"?.tiempo}">
                                            <g:select title="${campo.textoAyuda}" id="${campo.campo.claseAsociada.nombre + "_tiempo"}" name="${campo.campo.claseAsociada.nombre + "_tiempo"}" class="formulariOptions width100 gray formValues notEmpty headingColor"  from="${1..80}" noSelection="['':'0']" value="${generales?."$campo.campo.claseAsociada"?.tiempo}"/>
                                        </g:if>
                                        <g:else>
                                            <g:select title="${campo.textoAyuda}" id="${campo.campo.claseAsociada.nombre + "_tiempo"}" name="${campo.campo.claseAsociada.nombre + "_tiempo"}" class="formulariOptions width100 gray formValues"  from="${1..80}" noSelection="['':'0']"/>
                                        </g:else>
                                    </span>
                                    <span class="width120 inline selectWrap">
                                        <g:if test="${generales?."$campo.campo.claseAsociada"?.temporalidad}">
                                            <g:select title="${campo.textoAyuda}" noSelection="['':'Elija']" class="formulariOptions formValues gray notEmpty headingColor"  optionKey="id" optionValue="nombre" name="${campo.campo.claseAsociada.nombre + "_temporalidad"}" from="${la.kosmos.app.Temporalidad.list()}" value="${generales?."$campo.campo.claseAsociada"?.temporalidad}" />
                                        </g:if>
                                        <g:else>
                                            <g:select title="${campo.textoAyuda}" noSelection="['':'Elija']" class="formulariOptions gray formValues notEmpty headingColor"  optionKey="id" optionValue="nombre" name="${campo.campo.claseAsociada.nombre + "_temporalidad"}" from="${la.kosmos.app.Temporalidad.list()}" value="3"/>
                                        </g:else>
                                    </span>.
                                </g:if>
                                <g:if test="${campo.campo.tipoDeCampo.elementoDeEntrada.equals("SERIE")}">
                                            <span class="inline selectWrap" style="text-align:center; width: ${campo.longitudDelCampo};">
                                        <g:if test="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo" != null}">
                                            <g:select title="${campo.textoAyuda}" id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" class="formulariOptions gray formValues notEmpty headingColor"  from="${0..20}" noSelection="${Eval.me(campo.placeholder)}" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"}" />
                                        </g:if>
                                        <g:else>
                                            <g:select title="${campo.textoAyuda}" id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" class="formulariOptions gray formValues" from="${0..20}" noSelection="${Eval.me(campo.placeholder)}"/>
                                        </g:else>
                </span>
                <span class="afterSelect">
                    <i class="fa fa-caret-down" aria-hidden="true"></i>
                </span>
                                </g:if>
                                <g:if test="${campo.campo.tipoDeCampo.elementoDeEntrada.equals("AUTOCOMPLETE")}">
                                    <input type="text" title="${campo.textoAyuda}" style="text-align:center;" class="inputsFormulario formValues width120 <g:if test="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"}"> notEmpty headingColor </g:if>" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" placeholder="${campo.placeholder}" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"}"/>
                                </g:if>
                                <g:if test="${campo.campo.tipoDeCampo.elementoDeEntrada.equals("NUMERICO")}">
                                    <input type="text" title="${campo.textoAyuda}" style="text-align:center; width: ${campo.longitudDelCampo};" class="inputsFormulario formValues filledColor <g:if test="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"}"> notEmpty headingColor </g:if>" id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" placeholder="${campo.placeholder}" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"}" onKeyPress="return numbersonly(this, event)"> 
                                </g:if>
                                <g:if test="${campo.campo.tipoDeCampo.elementoDeEntrada.equals("TELEFONO")}">
                                    <input type="text" title="${campo.textoAyuda}" style="text-align:center; width: ${campo.longitudDelCampo};" class="inputsFormulario formValues filledColor <g:if test="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"}"> notEmpty headingColor </g:if>" id="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" name="${campo.campo.claseAsociada.nombre + "_" + campo.campo.nombreDelCampo}" placeholder="${campo.placeholder}" value="${generales?."$campo.campo.claseAsociada"?."$campo.campo.nombreDelCampo"}" data-mask="99-99-99-99-99"> 
                                </g:if>
                                <g:if test="${campo.campo.tipoDeCampo.elementoDeEntrada.equals("MAPASUCURSALES")}">
                                    <div id="mapContainer" class="width990 autoMargin clearFix">
                                        <div class="solicitudWhiteBox radius2 marginBottom30">
                                            <div class="padding15">
                                                <div id="map" class="height477 overflowHide autoMargin">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </g:if>
                                ${campo.textoPosterior}
                    </span>
                            <g:if test="${campo.saltoDeLineaAlFinal}">
                        <br/>
                            </g:if> 
                        </g:each>
                    </g:if>
                    <g:elseif test="${pasoActual.modoDeDespliegue == "FORMULARIO"}">
                    </g:elseif>
        </div>
        <div id="accionesFinales${p}" class="filledColor font35 letterspacing1">
            <g:if test="${generales?.direccionCliente?.sucursal && p == 2}">
                <% def sucursal = catalogos?.sucursal?.find { it.id == generales?.direccionCliente?.sucursal } %>
                <g:if test="${sucursal}">
                    <p> Ubicada en: ${sucursal.ubicacion}</p>
                </g:if>
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
        <g:if test="${pasoActual.mostrarEnBarra}">
            <g:if test="${pasoActual?.ultimoPaso}">
                <div class="paddingAside15 clearFix">
                    <div class="<g:if test="${documentosSubidos?.comprobanteDeDomicilio && documentosSubidos?.identificacion}"> blueButton colorWhite pointer </g:if> grayrectangle floatLeft solicitud_modal"> ENVIAR MI SOLICITUD </div>
                </div>
            </g:if>
            <g:else>
                <div class="paddingAside15 clearFix">
                    <div class="grayrectangle floatLeft marginRight10">Atras</div>
                    <div data-numero-de-paso="${pasoActual.numeroDePaso + 1}" id="circuloPaso${pasoActual.numeroDePaso + 1}" class="botonCambioDePaso grayrectangle floatLeft nextBtn">Ir al paso ${pasoActual?.numeroDePaso + 1}</div>
                </div>
            </g:else>
        </g:if>
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
<style>
    .tooltip {
    position: relative;
    display: inline-block;
    border-bottom: 1px dotted black;
    }

    .tooltip .tooltiptext {
    visibility: hidden;
    width: 120px;
    background-color: black;
    color: #fff;
    text-align: center;
    border-radius: 6px;
    padding: 5px 0;
    position: absolute;
    z-index: 1;
    bottom: 150%;
    left: 50%;
    margin-left: -60px;
    }

    .tooltip .tooltiptext::after {
    content: "";
    position: absolute;
    top: 100%;
    left: 50%;
    margin-left: -5px;
    border-width: 5px;
    border-style: solid;
    border-color: black transparent transparent transparent;
    }

    .tooltip:hover .tooltiptext {
    visibility: visible;
    }
</style>
