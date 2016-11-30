<g:each in="${productos}" var="producto" status="i">
    <div class="col6 floatLeft marginBottom10">
        <div class="paddingAside5">
            <p data-id="${producto.id}" data-descripcion="${producto.descripcion}" data-nombre="${producto.nombreDelProducto}" class="width350 nextAction cotizador-box"> <g:if test="${paso?.claseIconoPaso}"> <i class="${paso?.claseIconoPaso}"></i> </g:if> ${producto.nombreDelProducto}</p>
        </div>
    </div>
    <g:if test="${i%2 == 1}">
        <div class="clearFloat"></div>
    </g:if>
</g:each>