<div class="solicitudLightbox hide" style="overflow: scroll;" id="modalAltaDocumentoProducto">
    
    <div class="overlay"></div>
    <div class="visitaContainerSmall">
        <div class="dashBordBox">
        <form id="altaDocumentoProductoForm">
            <div class="loginForm gray font14" id="Paris" class="tabcontent">
                <p class="loginTitle font25 fontWeight500 darkBluetitle textUpper letterspacing1 center paddingAside15 marginBottom25">Asociar Documento</p>
                <div class="solicitudTitle autoMargin" style="width: 100%;">
                    <p class="center font14 lightGray">Alta nuevo documento</p>
                </div>
                <div class="formContainer">

           <div class='col12' style='display:inline-block; position:relative;'>
            <input  type="text"  name="idProducto"  value="${producto?.id}">

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
                            <g:each var="documento" in="${la.kosmos.app.TipoDeDocumento.findAll()}">
                                <option value=${documento.id}>${documento.nombre}</option>
                            </g:each>
                        </select>
                    </div>
                    <div class="col5 paddingTop15">
                        <div class="col5 floatLeft">
                                <label>Si</label>
                                <input class="block cameraBox width150 height30 "  type="radio"  name="obligatorio"  value="true" checked>
                        </div>
                        <div class="col5 floatLeft">
                                <label>No</label>
                                <input class="block cameraBox width150 height30 " type="radio"  name="obligatorio"  value="false">
                        </div>
                    </div>
                    <div class="col5 paddingTop15">
                        <div class="col5 floatLeft">
                                <label>Si</label>
                                <input class="block cameraBox width150 height30 "  type="radio"  name="activo"  value="true" checked>
                        </div>
                        <div class="col5 floatLeft">
                                <label>No</label>
                                <input class="block cameraBox width150 height30 " type="radio"  name="activo"  value="false">
                        </div>
                    </div>
                </div>
            </div>
                    </div>
      
                    <div class="col12" style="text-align: center;">
                        <div class="col4" style="display: inline-block;">
                            <button type="button" id="" onclick="registrarDocumentoProducto();" class="loginButton letterspacing2 font14 pointer">REGISTRAR</button>
                        </div>
                        <div class="col4" style="display: inline-block;">
                            <button type="button" onclick="cerrarModal('modalAltaDocumentoProducto');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
                        </div>
                    </div>
                </div>
            </div>
            </form>
         </div>
    </div>
</div>
