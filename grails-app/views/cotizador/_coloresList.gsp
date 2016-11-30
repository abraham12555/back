<g:each in="${coloresList}" var="color">
    <div class="col6 floatLeft marginBottom10 modelOption">
        <div class="paddingAside5">
            <p data-id="${color.id}" data-color="${color.nombre}" class="width350 cotizador-box nextAction">${color.nombre}</p>
        </div>
    </div>
</g:each>