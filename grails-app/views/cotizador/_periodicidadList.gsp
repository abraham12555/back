<div class="payChoice">
    <g:each in="${periodicidadList}" var="periodicidad" status="i">
        <div class="col6 floatLeft marginBottom10">
            <div class="paddingAside5">
                <p data-frecuencia="${periodicidad.nombre.toLowerCase()}" class="width350 cotizador-box frecuencia">${periodicidad.nombre}</p>
            </div>
        </div>
    </g:each>
</div>

<g:if test="${configuracion.aplicacionVariable}">
    <h1 class="dark-blue-titles font20 fontWeight400 letterspacing1 marginBottom20 clearFloat"><span id="pagosleyenda">A cuantos meses</span> pagarás tu auto?</h1>
    <div class="marginTop52">
        <div class="marginBottom25 clearFix loading-bar-container inner marginBottom40">
            <div id="listaDeFrecuencias" class="loading-bar-line blueButton autoMargin opacity05 ">
            </div>
            <p class="floatLeft marginLeft12 font14 fontWeight500 gray opacity05 paddingTop5">MIN</p>
            <p class="floatRight marginRight12 font14 fontWeight500 gray opacity05 paddingTop5">MAX</p>
            <div class="clearFloat"></div>
        </div>
    </div>
    <p data-id="0" class="marginAuto width350 nextAction cotizador-box">Siguiente</p>
    </g:if>
        <g:else>    
            <h1 class="dark-blue-titles font20 fontWeight400 letterspacing1 marginBottom20 clearFloat"><span id="pagosleyenda">A cuantos meses</span> pagarás tu auto?</h1>
            <div id="listaDeFrecuencias"></div>
            <script>
                var frecbtn = '';
                for(i=0; i<4; i++){
    frecbtn += '<div class="col3 col6-tab floatLeft marginBottom10">';
    frecbtn += '<div class="paddingAside5">';
    frecbtn += '<div class="cotizador-box small frecuenciaOpt nextAction">';
    frecbtn += '<p class="font18 opacity08 ">';
    frecbtn += '<span class="frecuenciaTotal"></span><br/>';
    frecbtn += '<span class="frecuenciaTipo"></span>';
    frecbtn += '</p>';
    frecbtn += '<p class="font14 opacity08 frecuenciaCantidad"></p>';
    frecbtn += '</div>';
    frecbtn += '</div>';
    frecbtn += '</div>';
                }
                $('#listaDeFrecuencias').html(frecbtn);
            </script>
        </g:else>