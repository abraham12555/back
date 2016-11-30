<g:each in="${segurosList}" var="seguro" status="i">
    <div id="s${(i + 1)}" data-id="${seguro.id}" class="cotizador-box seguro large marginBottom10 nextAction">
        <div class="paddingAside10 clearFix">
            <div class="floatLeft col6 col12-mob left">
                <p class="fontWeight500 seguroNombre">${seguro.nombre}</p>

                <p class="paddingTop10 paddingBottom20 font11 fontWeight500">NIVEL DE PROTECCIÓN: ${seguro.cobertura}</p>
            </div>
            <div class="floatRight col6 col12-mob right ">
                <p class="fontWeight500 "><g:formatNumber number="${seguro.importe}" format="\044###,###,###.##"/></p>

                <p class="paddingTop10 font12 fontWeight500">${seguro.detalleImporte}</p>
            </div>
        </div>
    </div>
</g:each>