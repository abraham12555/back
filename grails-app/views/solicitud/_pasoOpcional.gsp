<section id="pasoOpcional" class="container paddingTop30 paddingBottom20 clearFix contentHeight">
    <input type="hidden" id="tipoDePaso" value="${pasoActual?.tipoDePaso?.nombre}">
    <input type="hidden" id="tituloDelPaso" value="${pasoActual?.titulo}"/>
    <g:if test="${pasoActual}">
        <div class="col12 col12-mob col floatLeft paddingTop20 paddingBottom20 ">
            <p class="font18 gray letterspacing1 justify">
                Por el momento no es posible otorgar una pre-calificación de acuerdo a los criterios seleccionados, sin embargo te ofrecemos otras opciones personalizadas, por favor selecciona la que se ajuste mejor a tus necesidades:  
            </p>
        </div>
    </g:if>
    <div id="contenidoPasoOpcional"><g:render template="${nombreTemplate}" /></div>
    <form method="POST" name="formFormulario" id="formFormulario">
        <input type="hidden" id="siguientePaso" name="siguientePaso" value="${pasoActual?.pasoSiguiente}">
        <input type="hidden" id="pasoAnterior" name="pasoAnterior" value="0">
        <input type="hidden" name="opcional" value="true">
    </form>
</section>