<g:if test="${pasosCotizador}">
    <g:each in="${pasosCotizador}" status="s" var="paso">
                                    <!-- BEGIN STEP-->
        <div class="cotizadorStep" data-step="${paso.numeroDePaso}" data-tipo-de-paso="${paso.tipoDePaso.nombre}">
            <div class="summaryDiv">
                <div class="borderGrayBottom paddingBottom15 clearFix elige-un-modelo">
                    <div class="col7 floatLeft">
                        <div class="floatLeft paddingTop18 marginLeft24">
                            <p class="formTitleColor font12 paddingBottom12">${paso.tituloResumen}</p>

                            <p id="${paso.variableValorSeleccionado}" class="formTitleColor font20 marginBottom15 fontWeight700 "></p>
                        </div>
                    </div>

                    <div class="col5 floatRight">
                        <div class="floatRight paddingTop18 marginBottom15">
                            <div class="borderBlueButton marginBottom15">
                                <p class="center fontWeight700 paddingTop10 paddingBottom10 cambiar pointer">Cambiar</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="cotizador-rightside actionsDiv">

                <div class="cotizador-box-container padding20">
                    <h1 class="darkBluetitle font20 fontWeight700 letterspacing1 marginBottom20">
                        <span class="visibleMob">${paso.numeroDePaso}.</span>${paso.tituloDelPaso} <g:if test="${paso.tieneAyuda}"><div class="help-tip" title="${paso.textoAyuda}"></div></g:if>
                        </h1>

                    <g:if test="${paso.tipoDePaso.nombre == "stepEnganche"}">
                        <p id="engancheElegido2" class="lightBlue floatRight font20"></p>
                        <div class="clearFloat"></div>
                    </g:if>
                    <g:elseif test="${paso.tipoDePaso.nombre == "stepMontoCredito"}">
                        <center>
                        <p id="montoElegido2" class="darkBluetitle floatRight font20"></p>
                        <div class="clearFloat"></div>
                        </center>
                    </g:elseif>

                    <div id="${paso.idListaAjax}" class="cotizador-p1-buttons clearFix">
                        <g:render template="${paso.idListaAjax}"/>
                    </div>

                    <div class="marker">
                        <p>${paso.numeroDePaso}</p>
                    </div>
                </div>
            </div>
            <div class="stepShadow"></div>
        </div>
        <!-- END STEP-->
        <g:if test="${pasosCotizador?.size() == (s+1)}">
            <g:hiddenField id="ultimoPaso" name="ultimoPaso" value="${paso.numeroDePaso}"/>
        </g:if>
    </g:each>
</g:if>
