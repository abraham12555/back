<div id="configuracionPasosSolicitud">

<form id='configuracionPasoSolicitudForm'>
    <section class="container ">
        <div class="col12 clearFix">
            <div class="autoMargin radius2 solicitudWhiteBox">
                <div class="solicitudDetailHeader">
                    <ul class="clearFix">
                        <li class="floatLeft paddingTop7 paddingLeft5 paddingRight5">
                            <a href="#" title="SOLICITUDES" class="displayInline font24 fontWeight700 darkBluetitle paddingTop17 paddingBottom17 paddingLeft20">${pasoActual?.titulo}</a>
                        </li>
                        <li class="floatLeft paddingLeft5 paddingRight5">
                            <span class="slash">/</span>
                            <a title="Usuario" class="displayInline font20 fontWeight500 darkBluetitle">${pasoActual?.subtitulo}</a>
                        </li>
                        <li class="floatLeft paddingLeft5 paddingRight5">
                            <span class="slash">/</span>
                            <a title="Valor" class="displayInline font20 fontWeight500 darkBluetitle">${pasoActual?.modoDeDespliegue}</a>
                        </li>
                        <li class="floatLeft paddingLeft5 paddingRight5">
                            <span class="slash">/</span>
                            <a title="Producto" class="displayInline font20 fontWeight500 darkBluetitle">Número de Paso: ${pasoActual?.numeroDePaso}</a>
                        </li>
                    </ul>
                </div>
                <div class="radius2 lightGrayBG autoMargin" style='position: static;'>
                    <ul class="clearFix marginLeft10 solicitude_submenu">
                        <li class="floatLeft marginLeft8">
                            <a id="datosGeneralesButton" href='#datosGenerales' onclick="atras();" class="ancla opcionMenuSolicitud displayInline font14 fontWeight300 gray paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Atrás</a>
                        </li>
                        <li class="floatLeft">
                            <a id="capacidadDePagoButton" href='#' onclick="registrarConfiguracionPasoSolicitud(${pasoActual?.id});" class="ancla opcionMenuSolicitud displayInline font14 fontWeight300   blueButton paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Guardar Configuracion</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </section>

    <section class="container width990">
        <div class="padding20" ondrop="dropClear(event);"  ondragover="allowDrop(event);">
            <h1 class="darkBluetitle font18 fontWeight600 letterspacing2">${pasoActual?.titulo} </h1>
            <img src="${resource(dir:'images', file:'delete.png')}" alt="expand" title="expand" style="height:10%;"/>
        </div>
        <div class="col12 clearFix borderGray">
            <div class="col4 col4-tab col12-mob floatLeft " id="divIzquierdo">
                <div class="radius2 paddingBottom10 marginBottom15 mobileAside10 borderGrayRight ">
                    <div class="clearFix">
                        <div class="marginRight20 clearFix">
                            <div class="floatLeft paddingLeft15">
                                <p class="font14 fontWeight500 letterspacing1 gray paddingTop20">Selecciona un Elemento de la Lista</p>
                            </div>
                        </div>
                        <div class="marginRight20 clearFix">
                            <div class="paddingLeft15">
                                <select class="js-example-basic-multiple textUpper" onchange="selecciona();" id="idTipoDeCampo" >
                                    <g:each in="${listaTipoDeCampo}" var="tipodecampo">
                                        <option value="${tipodecampo.id}" > ${tipodecampo.nombreComun?.toUpperCase()}</option>
                                    </g:each>
                                    <span class="afterSelect">
                                        <i class="fa fa-caret-down" aria-hidden="true"></i>
                                    </span>
                                </select>
                            </div>
                        </div>

                    </div>
                    <div class="marginTop19 paddingAside15 elementosFormularioRender borderGrayTop borderGrayBottom borderGrayRight">
                        <div class="autoMargin clearFix" style="height: 200px;">
                            <div id="elementosFormulario"></div>
                        </div>
                    </div>

                </div>

            </div>
            <div id="divDerecho" ondrop="drop(event);"  ondragover="allowDrop(event);" class="col8 col8-tab col12-mob floatLeft clearFix solicitudWhiteBox " style="height:700px;">
                <div class="col12 clearFix">
                    <div class="clearFix">
                        <div class="marginRight20 clearFix">
                            <div class="floatLeft paddingLeft15">
                                <p class="font14 fontWeight500 letterspacing1 gray paddingTop20">Ver en Tamaño Original</p>
                            </div>
                            <div class="floatRight paddingTop15 pointer">
                                <img onclick="expander('divIzquierdo','divDerecho');"src="${resource(dir:'images', file:'expand.png')}" alt="expand" title="expand"/>
                            </div>
                        </div>
                    </div>

<div id="campoFormularioAgregado">
                            <fieldset class="step1 formStep" style="padding: 0px 15px;">
                    <div class="font35 marginTop30 letterspacing1 formTitleColor lineHeight60">
                        <g:each  in="${campoFormulario}" var="campo">

                            <span id="${campo.id}"  class="showOnFill nonRequired " draggable='true' ondragstart='drag(event);' onclick='modificarCampoFormulario(${campo.id});'>${campo.textoAnterior}
                                <g:if test="${campo.tipoDeCampo.elementoDeEntrada.equals('TEXTFIELD')}">
                                    
                                    <input type="text" title="${campo.textoAyuda}" style="text-align:center; width: ${campo.longitudDelCampo};" class="inputsFormulario formValues filledColor "  placeholder="${campo.placeholder}" > 
                                </g:if>
  <g:if test="${campo.tipoDeCampo.elementoDeEntrada.equals("TEXTAREA")}">
                                    <textArea title="${campo.textoAyuda}" class="inputsFormulario formValues" style="width: 100%; height: 20%; text-align: center;" placeholder="${campo.placeholder}"></textArea>
                                    </g:if>
  <g:if test="${campo.tipoDeCampo.elementoDeEntrada.equals("SELECT")}">

                            <span class="inline selectWrap" style="text-align:center; width: ${campo.longitudDelCampo};">
                            
                                            <g:if test="${campo.aplicarFiltro}">
                                                <g:select title="${campo.textoAyuda}"  class="formulariOptions gray formValues select2" name="option"    from="${1..31}" noSelection="${Eval.me(campo.placeholder)}" />
                                            </g:if>
                                            <g:else>
                                                <g:select title="${campo.textoAyuda}"  class="formulariOptions gray formValues"  name="option"  from="${1..31}" noSelection="${Eval.me(campo.placeholder)}" />
                                            </g:else>
                            </span>
                            <span class="afterSelect">
                                <i class="fa fa-caret-down" aria-hidden="true"></i>
                            </span>
                                </g:if>
<g:if test="${campo.tipoDeCampo.elementoDeEntrada.equals("DYNAMICSELECT")}">
                            <span class="inline selectWrap" style="text-align:center; width: ${campo.longitudDelCampo};">
                                     <select title="${campo.textoAyuda}"  class="formulariOptions gray formValues">
                                                <option value=''>Elija una opción...</option>
                                   
                                     </select>
                            </span>
                            <span class="afterSelect">
                                <i class="fa fa-caret-down" aria-hidden="true"></i>
                            </span>
                                </g:if>
<g:if test="${campo.tipoDeCampo.elementoDeEntrada.equals("FECHA")}">
                            <span class="width70 inline selectWrap">
                                 
                                            <g:select title="${campo.textoAyuda}"  class="formulariOptions gray formValues " name="option"  from="${1..31}" noSelection="['':'Día']" />
                            </span>
                            <span class="afterSelect">
                                <i class="fa fa-caret-down" aria-hidden="true"></i>
                            </span>
                            <!--<span class="hide showOnFill">-->
                                    de&nbsp;
                                <span class="width200 inline selectWrap">

                                            <g:select title="${campo.textoAyuda}" class="formulariOptions gray formValues " name="option" from="${[[id: 1, mes: 'Enero'],[id: 2, mes: 'Febrero'],[id: 3, mes: 'Marzo'],[id: 4, mes: 'Abril'],[id: 5, mes: 'Mayo'],[id: 6, mes: 'Junio'],[id: 7, mes: 'Julio'],[id: 8, mes: 'Agosto'],[id: 9, mes: 'Septiembre'],[id: 10, mes: 'Octubre'],[id: 11, mes: 'Noviembre'],[id: 12, mes: 'Diciembre']]}"  noSelection="['':'Mes']" />
                                </span>
                                <span class="afterSelect">
                                    <i class="fa fa-caret-down" aria-hidden="true"></i>
                                </span>
                        <!--</span>-->
                                    <% def anioActual = Calendar.getInstance().get(Calendar.YEAR) %>
                            <!--<span class="hide showOnFill">-->
                                    de&nbsp;
                                <span class="width100 inline selectWrap">

                                            <g:select title="${campo.textoAyuda}" class="formulariOptions width100 gray formValues" name="option" from="${(anioActual-18)..(anioActual-68)}" noSelection="['':'Año']" />
                                </span>
                                <span class="afterSelect">
                                    <i class="fa fa-caret-down" aria-hidden="true"></i>
                                </span>
                        <!--</span>-->
                                </g:if>
                                <g:if test="${campo.tipoDeCampo.elementoDeEntrada.equals("MESANIO")}">
                                <span class="width200 inline selectWrap">
                                    
                                            <g:select title="${campo.textoAyuda}" class="formulariOptions gray formValues " name="option"  from="${[[id: 1, mes: 'Enero'],[id: 2, mes: 'Febrero'],[id: 3, mes: 'Marzo'],[id: 4, mes: 'Abril'],[id: 5, mes: 'Mayo'],[id: 6, mes: 'Junio'],[id: 7, mes: 'Julio'],[id: 8, mes: 'Agosto'],[id: 9, mes: 'Septiembre'],[id: 10, mes: 'Octubre'],[id: 11, mes: 'Noviembre'],[id: 12, mes: 'Diciembre']]}" noSelection="['':'Mes']" />
                                </span>
                                <span class="afterSelect">
                                    <i class="fa fa-caret-down" aria-hidden="true"></i>
                                </span>
                                    <% def anioActualParcial = Calendar.getInstance().get(Calendar.YEAR) %>
                                    de&nbsp;
                                <span class="width100 inline selectWrap">
                                   
                                            <g:select title="${campo.textoAyuda}"  class="formulariOptions width100 gray formValues" name="option"  from="${(anioActualParcial)..(anioActualParcial-68)}" noSelection="['':'Año']" />
                                </span>
                                <span class="afterSelect">
                                    <i class="fa fa-caret-down" aria-hidden="true"></i>
                                </span>
                                </g:if>
                                <g:if test="${campo.tipoDeCampo.elementoDeEntrada.equals("PERIODO")}">
                                    <span class="width80 inline selectWrap">
                                   
                                            <g:select title="${campo.textoAyuda}"  class="formulariOptions width100 gray formValues" name="option" from="${1..80}" noSelection="['':'0']"/>
                                    </span>
                                    <span class="width120 inline selectWrap">
                                     
                                            <g:select title="${campo.textoAyuda}" noSelection="['':'Elija']" class="formulariOptions gray formValues notEmpty headingColor"  name="option" from="${la.kosmos.app.Temporalidad.list()}" />
                                    </span>.
                                </g:if>
                                <g:if test="${campo.tipoDeCampo.elementoDeEntrada.equals("SERIE")}">
                                            <span class="inline selectWrap" style="text-align:center; width: ${campo.longitudDelCampo};">
                                     
                                            <g:select title="${campo.textoAyuda}"  class="formulariOptions gray formValues" name="option" from="${0..20}" noSelection="${Eval.me(campo.placeholder)}"/>
                </span>
                <span class="afterSelect">
                    <i class="fa fa-caret-down" aria-hidden="true"></i>
                </span>
                                </g:if>
                                <g:if test="${campo.tipoDeCampo.elementoDeEntrada.equals("AUTOCOMPLETE")}">
                                    <input type="text" title="${campo.textoAyuda}" style="text-align:center;" class="inputsFormulario formValues width120 " placeholder="${campo.placeholder}" />
                                </g:if>
                                <g:if test="${campo.tipoDeCampo.elementoDeEntrada.equals("NUMERICO")}">
                                    <input type="text" title="${campo.textoAyuda}" style="text-align:center; width: ${campo.longitudDelCampo};" class="inputsFormulario formValues filledColor "  placeholder="${campo.placeholder}"  onKeyPress="return numbersonly(this, event)"> 
                                </g:if>
                                <g:if test="${campo.tipoDeCampo.elementoDeEntrada.equals("TELEFONO")}">
                                    <input type="text" title="${campo.textoAyuda}" style="text-align:center; width: ${campo.longitudDelCampo};" class="inputsFormulario formValues filledColor"  placeholder="${campo.placeholder}" data-mask="99-99-99-99-99"> 
                                </g:if>
                                <g:if test="${campo.tipoDeCampo.elementoDeEntrada.equals("MAPASUCURSALES")}">
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
                   
        </div>
    </fieldset>
</div>

                   
                </div>

            </div>

        </div>
    </section>

</form>
</div>
<g:render template="configuracion/pasoSolicitudEntidadFinanciera/altaPasoSolicitudEntidadFinanciera"/>

<div id="altaCampoFormulario"></div>
<div id="detalleCampoFormulario"></div>
