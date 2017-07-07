<div class="solicitudLightbox hide" style="overflow: scroll;" id="modalDetalleDocumentoProducto">
    
    <div class="overlay"></div>
    <div class="visitaContainerSmall">
        <div class="dashBordBox">
        <form id="actualizarDocumentoProductoForm">
            <div class="loginForm gray font14" id="Paris" class="tabcontent">
                <input type="hidden"  name="idDocumentoProducto"  value="${documentoProducto?.id}">

                <p class="loginTitle font25 fontWeight500 darkBluetitle textUpper letterspacing1 center paddingAside15 marginBottom25">Documento Asociado al Producto</p>
                <div class="solicitudTitle autoMargin" style="width: 100%;">
                    <p class="center font14 lightGray">Modificar Documento del Producto ${documentoProducto.producto.nombreDelProducto}</p>
                </div>
                <div class="formContainer">

           <div class='col12' style='display:inline-block; position:relative;'>
                        
            <div class="col6 col12-mob floatLeft clearFix marginTop25 marginBottom25">
                <div class="col6 floatLeft">
                    <div class="paddingTop20">
                        <p class="gray font12 fontWeight500 latterspacing1 center">CAMPOS</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">(Nombre del Documento)</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">(Obligatorio)</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">(Activo)</p>
                    </div>
                </div>
                <div class="col6 floatLeft">
                    <div class="paddingTop20">
                        <p class="gray font12 fontWeight500 latterspacing1">TEXTO</p>
                    </div>
                    <div class="paddingTop15">
                        <select class="block cameraBox width150 height30" name="documentoId">
                            <g:each var="documento" in="${documentoProducto}">
                                <option value=${documento.tipoDeDocumento.id} selected="selected" >${documento.tipoDeDocumento.nombre}</option>
                            </g:each>
                            <g:each var="faltaDoc" in="${faltan}">
                                <option value=${faltaDoc.id}>${faltaDoc.nombre}</option>
                            </g:each>
                            
                        </select>
                    </div>
                    <div class="col5 paddingTop15">
                        <g:if test="${documentoProducto?.obligatorio == true}">
                        <div class="col5 floatLeft">
                                <label>Si</label>
                                <input class="block cameraBox width150 height30 "  type="radio"  name="obligatorio"  value="true" checked>
                        </div>
                        <div class="col5 floatLeft">
                                <label>No</label>
                                <input class="block cameraBox width150 height30 " type="radio"  name="obligatorio"  value="false">
                        </div>
                        </g:if>
                            
                        <g:if test="${documentoProducto?.obligatorio == false}">
                        <div class="col5 floatLeft">
                                <label>Si</label>
                                <input class="block cameraBox width150 height30 "  type="radio"  name="obligatorio"  value="true" >
                        </div>
                        <div class="col5 floatLeft">
                                <label>No</label>
                                <input class="block cameraBox width150 height30 " type="radio"  name="obligatorio"  value="false" checked>
                        </div>
                        </g:if>
                    </div>
                    
                    <div class=" col5 paddingTop15">
                      <g:if test="${documentoProducto?.activo == true}">
                         <div class="col5 floatLeft">
                                <label>Si</label>
                                <input class="block cameraBox width150 height30 "  type="radio"  name="activo"  value="true" checked>
                        </div>
                        <div class="col5 floatLeft">
                                <label>No</label>
                                <input class="block cameraBox width150 height30 " type="radio"  name="activo"  value="false">
                        </div>
                      </g:if>
                      <g:if test="${documentoProducto?.activo == false}">
                         <div class="col5 floatLeft">
                                <label>Si</label>
                                <input class="block cameraBox width150 height30 "  type="radio"  name="activo"  value="true" >
                        </div>
                        <div class="col5 floatLeft">
                                <label>No</label>
                                <input class="block cameraBox width150 height30 " type="radio"  name="activo"  value="false" checked>
                        </div>
                        </g:if>
                    </div>
                </div>
            </div>
                    </div>
      
                    <div class="col12" style="text-align: center;">
                        <div class="col4" style="display: inline-block;">
                            <button type="button" id="" onclick="actualizarDocumentoProducto();" class="loginButton letterspacing2 font14 pointer">Actualizar</button>
                        </div>
                        <div class="col4" style="display: inline-block;">
                            <button type="button" onclick="cerrarModal('modalDetalleDocumentoProducto');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
                        </div>
                    </div>
                </div>
            </div>
            </form>
         </div>
    </div>
</div>