<g:each in="${tiposDeProducto}" var="tipoDeProducto" status="i">
    <div class="col6 floatLeft marginBottom10">
        <div class="paddingAside5">
            <p data-id="${tipoDeProducto.id}" data-nombre="${tipoDeProducto.nombre}" class="width350 nextAction cotizador-box"> <g:if test="${pasosCotizador.claseIconoPaso}"> <i class="${pasosCotizador.claseIconoPaso}"></i> </g:if> ${tipoDeProducto.nombre}</p>
        </div>
    </div>
    <g:if test="${i%2 == 1}">
        <div class="clearFloat"></div>
    </g:if>
</g:each>