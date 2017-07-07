<div class="solicitudLightbox hide" style="overflow: scroll;" id="modalAltaGarantiaProducto">
    <div class="overlay"></div>
    <div class="visitaContainerSmall">
        <div class="dashBordBox">
            <form id="altaGarantiaProductoForm">
            <div class="loginForm gray font14">
                <p class="loginTitle font25 fontWeight500 darkBluetitle textUpper letterspacing1 center paddingAside15 marginBottom25">GARANTÍA PRODUCTO</p>
                <div class="solicitudTitle autoMargin" style="width: 100%;">
                    <p class="center font14 lightGray">Alta Garantía Producto</p>
                </div>
                <div class="formContainer">
           <div class='col12' style='display:inline-block; position:relative;'>
                        
       <div class=" col12-mob floatLeft clearFix marginBottom25">
                <input type="hidden"  name="idProducto" value="${producto.id}">

                <div class="col6 floatLeft">
                    <div class="paddingTop20">
                        <p class="gray font12 fontWeight500 latterspacing1 center">CAMPOS</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">(Cantidad Maxima)</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">(Cantidad Minima)</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">(Descripcion)</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">(Tipo de Garantía)</p>
                    </div>
                </div>
                <div class="col6 floatLeft">
                    <div class="paddingTop20">
                        <p class="gray font12 fontWeight500 latterspacing1">TEXTO</p>
                    </div>
                    <div class="paddingTop15">
                        <input class="block cameraBox width150 height30" type="text"   name="cantidadMaxima" value="">
                    </div>
                    <div class="paddingTop5">
                        <input class="block cameraBox width150 height30" type="text"  name="cantidadMinima" value="">
                    </div>
                    <div class="paddingTop10">
                        <input class="block cameraBox width150 height30" type="text"   name="descripcion" value="">
                    </div>
                    <div class="paddingTop10">
                        <select class="block cameraBox width150 height30"  name="tipoDeGarantiaId" >
                          
                           <g:each var="garantia" in="${la.kosmos.app.TipoDeGarantia.findAll()}">
                                <option value=${garantia.id}> ${garantia.nombre}</option>
                            </g:each>
                        </select>
                    </div>
                </div>
            </div>    
                        
                        
                    </div>
                    <div class="col12" style="text-align: center;">
                         <div class="col4" style="display: inline-block;">
                            <button type="button" id="" onclick="registrarGarantiaProducto();" class="loginButton letterspacing2 font14 pointer">Actualizar</button>
                        </div>
                        <div class="col4" style="display: inline-block;">
                            <button type="button" onclick="cerrarModal('modalAltaGarantiaProducto');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">ATRAS</button>
                        </div>
                    </div>
                </div>
            </div>
            </form>
        </div>
    </div>
</div>