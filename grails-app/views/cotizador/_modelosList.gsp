<g:each in="${modelosList}" status="i" var="modelo">
    <div class="col6 floatLeft marginBottom10 modelOption">
        <div class="paddingAside5">
            <p data-id="${modelo.id}" data-price="${modelo.precio}" class="width350 cotizador-box nextAction">${modelo.nombre}</p>
        </div>
    </div>
</g:each>