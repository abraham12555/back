<g:if test="listaRubroDeAplicacionDeCredito">
    <g:each var="rubro" in="${listaRubroDeAplicacionDeCredito}">
        <section class="container marginBottom20">
            <div class="width990 solicitudBox autoMargin radius2">
                <div class="clearFix">
                    <div class="col1fifth col3-tab col12-mob floatLeft">
                        <div class="borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10">
                            <p class="font12 gray2 marginBottom5"><strong>NOMBRE</strong></p>
                            <p class="font14 gray2">${rubro.nombre}</p>
                        </div>
                    </div>
                    <div class="col1fifth col3-tab col12-mob floatLeft">
                        <div class="borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10">
                            <p class="font12 gray2 marginBottom5"><strong>POSICIÓN</strong></p>
                            <p class="font14 gray2">${rubro.posicion}</p>
                        </div>
                    </div>
                    <div class="col1fifth col3-tab col12-mob floatLeft">
                        <div class="borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10">
                            <p class="font12 gray2 marginBottom5"><strong>DESCRIPCIÓN</strong></p>
                            <p class="font14 gray2">${rubro.descripcion}</p>
                        </div>
                    </div>
                    <div class="col1fifth col3-tab col12-mob floatLeft">
                        <div class="borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10">
                            <p class="font12 gray2 marginBottom5"><strong>ICONO</strong></p>
                            <p class="font14 gray2">${rubro.claseIconoPaso}</p>
                        </div>
                    </div>
                    <div class="col1fifth col3-tab col12-mob floatLeft">
                        <div class="borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10">
                            <p class="font12 gray2 marginBottom5"><strong>TOOLTIP</strong></p>
                            <p class="font14 gray2">${rubro.tooltip}</p>
                        </div>
                    </div>
                    <div class="col1fifth col3-tab col12-mob floatLeft">
                        <div class="borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10">
                            <p class="font12 gray2 marginBottom5"><strong>TEXTO TOOLTIP</strong></p>
                            <p class="font14 gray2">${rubro.textoTooltip}</p>
                        </div>
                    </div>
                   
                      <div class="col1fifth col3-tab col12-mob floatLeft">
                        <div class="borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10">
                            <p class="font12 gray2 marginBottom5"><strong>ACTIVO</strong></p>
                            <p class="font14 gray2">${rubro.activo}</p>
                        </div>
                    </div>
                     <div class="col1fifth col12-tab col12-mob floatLeft">
                        <div class="marginTop10 marginBottom10 clearFix paddingAside10">
                            <a title="Editar" onclick="editarRubroDeAplicacionDeCredito(${rubro.id});" class="tabNoFloat floatRight marginLeft20 block width115 blueButton center radius4 paddingTop10 paddingBottom10 pointer">EDITAR</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </g:each>
    <section class="container">
        <div class="width480 autoMargin solicitudBox marginBottom84">
            <div class="autoMargin">
                <ul class="clearFix">
                    <li class="floatLeft">
                        <a href="#" title="Previous Page" class="font14 fontWeight600 displayInline pageMarker">
                            <i class="fa fa-angle-left" aria-hidden="true"></i>
                        </a>
                    </li>
                    <li class="floatLeft">
                        <a href="#" title="Page 1" class="font14 fontWeight400 displayInline pageMarker pageSelected">1</a>
                    </li>
                    <li class="floatLeft">
                        <a href="#" title="Page 2" class="font14 fontWeight400 displayInline pageMarker">2</a>
                    </li>
                    <li class="floatLeft">
                        <a href="#" title="Page 3" class="font14 fontWeight400 displayInline pageMarker">3</a>
                    </li>
                    <li class="floatLeft">
                        <a href="#" title="Page 4" class="font14 fontWeight400 displayInline pageMarker">4</a>
                    </li>
                    <li class="floatLeft">
                        <a href="#" title="..." class="font14 fontWeight400 displayInline pageMarker">...</a>
                    </li>
                    <li class="floatLeft">
                        <a href="#" title="Page 12" class="font14 fontWeight400 displayInline pageMarker">12</a>
                    </li>
                    <li class="floatLeft">
                        <a href="#" title="Next Page" class="font14 fontWeight600 displayInline pageMarker">
                            <i class="fa fa-angle-right" aria-hidden="true"></i>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </section>
</g:if>
<g:else>
    <section class="container marginBottom20">
        <div class="width990 solicitudBox autoMargin radius2">
            <div class="clearFix">
                <div class="col1fifth col6-tab col12-mob floatLeft">
                    <div class="borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10">
                        <p class="font12 gray2 marginBottom5">NO HAY SEGUROS SOBRE DEUDA REGISTRADOS</p>
                    </div>
                </div>
            </div>
        </div>
    </section>
</g:else>
<g:render template="configuracion/rubroDeAplicacionDeCredito/altaRubroDeAplicacionDeCredito"/>
<div id="detalleRubroDeAplicacionDeCredito"></div>
