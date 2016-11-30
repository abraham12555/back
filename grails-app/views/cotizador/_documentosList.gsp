<g:each in="${documentos}" var="documento" status="i">
    <div class="col6 floatLeft marginBottom10">
        <div class="paddingAside5">
            <p data-id="${documento.id}" class="width350 nextAction cotizador-box"> ${documento.nombre}</p>
        </div>
    </div>
    <g:if test="${i%2 == 1}">
        <div class="clearFloat"></div>
    </g:if>
</g:each>