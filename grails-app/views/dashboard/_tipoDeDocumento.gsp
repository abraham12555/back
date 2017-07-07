<g:if test="tipoDeDocumento">
    <g:each var="tipoDeDocumentos" in="${tipoDeDocumento}">
        <section class="container marginBottom20">
            <div class="width990 solicitudBox autoMargin radius2">
                <div class="clearFix">
                    <div class="col1fifth col2-tab col12-mob floatLeft">
                        <div class="borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10">
                            <p class="font12 gray2 marginBottom5"><strong>CODIGO</strong></p>
                            <p class="font14 gray2" style='text-align: center;margin-left: -30px;'>${tipoDeDocumentos.codigo}</p>
                        </div>
                    </div>
                    <div class="col1fifth col3-tab col12-mob floatLeft">
                        <div class="borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10">
                            <p class="font12 gray2 marginBottom5"><strong>NOMBRE</strong></p>
                            <p class="font14 gray2">${tipoDeDocumentos.nombre}</p>
                        </div>
                    </div>
                    <div class="col1fifth col3-tab col12-mob floatLeft">
                        <div class="borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10">
                            <p class="font12 gray2 marginBottom5"><strong>FORMATOS PERMITIDOS</strong></p>
                            <p class="font14 gray2">${tipoDeDocumentos.formatosPermitidos}</p>
                        </div>
                    </div>
                    <div class="col1fifth col6-tab col12-mob floatLeft">
                        <div class="marginLeft30 paddingTop15 paddingBottom10">
                            <p class="font12 gray2 marginBottom5"><strong>NOMBRE MAPEO</strong></p>
                            <p class="font14 gray2">${tipoDeDocumentos.nombreMapeo}</p>
                        </div>
                    </div>
                    <div class="col1fifth col12-tab col12-mob floatLeft">
                        <div class="marginTop10 marginBottom10 clearFix paddingAside10">
                            <a title="Ver Detalle" onclick="mostrarDetalleTipoDeDocumento(${tipoDeDocumentos.id});" class="tabNoFloat floatRight marginLeft20 block width115 blueButton center radius4 paddingTop10 paddingBottom10 pointer">VER DETALLE</a>
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
                        <p class="font12 gray2 marginBottom5">NO HAY PRODUCTOS REGISTRADOS</p>
                    </div>
                </div>
            </div>
        </div>
    </section>
</g:else>
<g:render template="configuracion/tipoDeDocumento/altaTipoDeDocumento"/>
<div id="detalleTipoDeDocumento"></div>