<div class="idLightbox hide" id="modalDetalleProducto">
    <div class="overlay"></div>
    <div class="whiteContainer detalleProducto lightboxPos darkBluetitle font14 fontWeight400 letterspacing1 justify">
        <div class="width990 autoMargin solicitudWhiteBox clearFix paddingBottom20">
            <div class="clearFix width990 autoMargin borderGrayBottom">
                <center><h1 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">DETALLE DEL PRODUCTO: ${producto.nombreDelProducto.toUpperCase()}</h1></center>
            </div>
            <div class="clearFix width990 autoMargin ">
                <center><h2 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">Generales</h2></center>
            </div>
            <div class="col6 floatLeft marginTop20">
                <div class="col12 floatLeft">
                    <div class="col6 floatLeft">
                        <p class="gray font14 fontWeight500 letterspacing1 marginLeft20"><strong>Clave del Producto</strong></p>
                    </div>
                    <div class="col6 floatLeft">
                        <p class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;">${producto.claveDeProducto}</p>
                    </div>
                </div>
                <div class="col12 floatLeft">
                    <div class="col6 floatLeft">
                        <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Nombre del Producto</strong></p>
                    </div>
                    <div class="col6 floatLeft paddingTop8">
                        <p class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;">${producto.nombreDelProducto}</p>
                    </div>
                </div>
                <div class="col12 floatLeft">
                    <div class="col6 floatLeft">
                        <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Marca</strong></p>
                    </div>
                    <div class="col6 floatLeft paddingTop8">
                        <p class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;">${producto.marca}</p>
                    </div>
                </div>
                <div class="col12 floatLeft">
                    <div class="col6 floatLeft">
                        <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Tipo de Producto</strong></p>
                    </div>
                    <div class="col6 floatLeft paddingTop8">
                        <p class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;">${producto.tipoDeProducto}</p>
                    </div>
                </div>
            </div>
            <div class="col6 floatLeft marginTop20">
                <div class="col12 floatLeft">
                    <div class="col6 floatLeft">
                        <p class="gray font14 fontWeight500 letterspacing1 marginLeft20"><strong>Descripcion</strong></p>
                    </div>
                    <div class="col6 floatLeft">
                        <p class="block cameraBox col11 radius2 font14 lightGray" style="text-align: center; height: 72px; overflow-y: auto;">${producto.descripcion}</p>
                    </div>
                </div>
                <div class="col12 floatLeft">
                    <div class="col6 floatLeft">
                        <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Esquema</strong></p>
                    </div>
                    <div class="col6 floatLeft paddingTop8">
                        <p class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;">${producto.esquema}</p>
                    </div>
                </div>
                <div class="col12 floatLeft">
                    <div class="col6 floatLeft">
                        <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Activo</strong></p>
                    </div>
                    <div class="col6 floatLeft paddingTop8">
                        <p class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;">${(producto.activo) ? "SI" : "NO"}</p>
                    </div>
                </div>
            </div>
            <br /> &nbsp;
            <div class="clearFix width990 autoMargin ">
                <center><h2 class="font18 fontWeight600 darkBluetitle marginLeft20">Presentación</h2></center>
            </div>
            <div class="col6 floatLeft marginTop20">
                <div class="col12 floatLeft">
                    <p class="gray font14 fontWeight500 letterspacing1 marginLeft20"><strong>Imagen</strong></p>
                    <p class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;">${producto.rutaImagenDefault}</p>
                </div>
            </div>
            <div class="col6 floatLeft marginTop20">
                <div class="col12 floatLeft">
                    <div class="col6 floatLeft">
                        <p class="gray font14 fontWeight500 letterspacing1 marginLeft20"><strong>Titulo en Cotizador</strong></p>
                    </div>
                    <div class="col6 floatLeft">
                        <p class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;">${producto.tituloEnCotizador}</p>
                    </div>
                </div>
                <div class="col12 floatLeft">
                    <div class="col6 floatLeft">
                        <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Icono</strong></p>
                    </div>
                    <div class="col6 floatLeft paddingTop8">
                        <p class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;">
                            <g:if test="${producto.claseIconoPaso}">
                                <i class="${producto.claseIconoPaso}"></i> (${producto.claseIconoPaso})
                            </g:if>
                            <g:else>
                                Sin Icono
                            </g:else>
                        </p>
                    </div>
                </div>
            </div>
            <br /> &nbsp;
            <div class="clearFix width990 autoMargin ">
                <center><h2 class="font18 fontWeight600 darkBluetitle marginLeft20">Montos y Plazos</h2></center>
            </div>
        </div>
    </div>
</div>

