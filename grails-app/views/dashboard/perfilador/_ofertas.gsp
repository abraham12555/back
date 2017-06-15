<h2>Ofertas disponibles</h2>
<div class="promos">
    <div id="wrapper_bu">
        <g:each status="i" in="${ofertas}" var="oferta">
            <div id="bu${i+1}">
                <div class="promo">
                    <h4>${oferta.producto}</h4>
                    <ul class="features">
                        <li class="brief"><i class="fa fa-male"></i></li>
                        <li id="monto_${oferta.producto.id}" class="price"><g:formatNumber number="${oferta.listaDeOpciones[0].montoMaximo}" format="\044###,###,###.##"/>
                            <div class="flat-slider" id="flat-slider-${oferta.producto.id}"></div>
                            <p class="floatLeft marginLeft12 font14 fontWeight500 gray opacity05 paddingTop5">MIN</p>
                            <p class="floatRight marginRight12 font14 fontWeight500 gray opacity05 paddingTop5">MAX</p>
                        </li>
                        <li id="plazo_${oferta.producto.id}">
                            <h6>PLAZO</h6> ${oferta.listaDeOpciones[0].plazos} ${oferta.listaDeOpciones[0].periodicidad.nomenclatura.toUpperCase()}</li>
                        <li id="pago_${oferta.producto.id}">
                            <h6>PAGO ${oferta.listaDeOpciones[0].periodicidad.nombre.toUpperCase()}</h6> <g:formatNumber number="${oferta.listaDeOpciones[0].cuota}" format="\044###,###,###.##"/></li>
                        <li>
                            <h6>REQUISITOS</h6></li>
                        <li class="requisitos">1. IDENTIFICACIÓN OFICIAL</li>
                        <li class="requisitos">2. COMPROBANTE DE DOMICILIO</li>
                        <li class="requisitos">3. COMPROBANTE DE INGRESOS</li>
                        <li class="requisitos">4. AVAL: 1</li>
                        <input type="hidden" id="plazoSeleccionado_${oferta.producto.id}">
                        <input type="hidden" id="periodicidadSeleccionada_${oferta.producto.id}">
                        <input type="hidden" id="montoSeleccionado_${oferta.producto.id}">
                        <input type="hidden" id="pagoSeleccionado_${oferta.producto.id}">
                        <li class="buy"><button onclick="confirmarSeleccion(${i},'${oferta.producto.id}');">APLICAR</button></li>
                    </ul>
                </div>
            </div>
        </g:each>
    </div>
</div>