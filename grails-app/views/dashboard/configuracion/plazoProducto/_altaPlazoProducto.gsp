<div class="solicitudLightbox hide" style="overflow: scroll;" id="modalAltaPlazoProducto">
    <div class="overlay"></div>
    <div class="visitaContainerSmall">
        <div class="dashBordBox">
            <form id="plazoProductoForm">
            <div class="loginForm gray font14">
                <p class="loginTitle font25 fontWeight500 darkBluetitle textUpper letterspacing1 center paddingAside15 marginBottom25">PLAZOS</p>
                <div class="solicitudTitle autoMargin" style="width: 100%;">
                    <p class="center font14 lightGray">PlAZOS PARA</p>
                </div>
                <div class="formContainer">
                  
           <div class='col12' style='display:inline-block; position:relative;'>
             <input  type="hidden"  name="idProducto"  value="${producto?.id}">

            <div class="col12-mob floatLeft clearFix marginTop25 marginBottom25">
                <div class="col6 floatLeft">
          <div class="paddingTop20">
                        <p class="gray font12 fontWeight500 latterspacing1 center">CAMPOS</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">(Usar Lista de Plazos)</p>
                    </div>
                     <div class="paddingTop30">
                        <p class="gray font14 fontWeight500 latterspacing1 center">(Importe Maximo)</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">(Importe Minimo)</p>
                    </div>
                    <div class="paddingTop20 plazoMaximo hide">
                        <p class="gray font14 fontWeight500 latterspacing1 center">(Plazo Maximo)</p>
                    </div>
                    <div class="paddingTop20 plazoMinimo hide">
                        <p class="gray font14 fontWeight500 latterspacing1 center">(Plazo Minimo)</p>
                    </div>
                    <div class="paddingTop20 plazosPermitidos">
                        <p class="gray font14 fontWeight500 latterspacing1 center">(Plazos Permitidos)</p>
                    </div>
                    <div class="paddingTop20 hide saltoSlider">
                        <p class="gray font14 fontWeight500 latterspacing1 center">(Salto Slider)</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">(Periodicidad)</p>
                    </div>
                </div>
                <div class="col6 floatLeft">
                    <div class="paddingTop20">
                        <p class="gray font12 fontWeight500 latterspacing1">TEXTO</p>
                    </div>
                    <div class="paddingTop5">
                        <div class="col2 floatLeft">
                            <div class="paddingTop15">
                                <label>Si</label>
                                <input class="block cameraBox width150 height30 "  onclick="ocultarCampo('saltoSlider','mostrar');" type="radio"  name="usarListaDePlazos"  value="true" checked>
                            </div>
                        </div>
                        <div class="col2 floatLeft">
                            <div class="paddingTop15">
                                <label>No</label>
                                <input class="block cameraBox width150 height30 " onclick="mostrarCampo('saltoSlider','ocultar');" type="radio"  name="usarListaDePlazos"  value="false" >
                            </div>
                        </div>
                    </div>
                    
                    
                    <div class="paddingTop15">
                        <input class="block cameraBox width150 height30" type="text"  id="importeMaximo" name="importeMaximo" value="">
                    </div>
                    <div class="paddingTop5">
                        <input class="block cameraBox width150 height30" type="text" id="importeMinimo" name="importeMinimo" value="">
                    </div>
                    <div class="paddingTop10  plazoMaximo hide">
                        <input class="block cameraBox width150 height30 " type="text" id="plazoMaximo"  name="plazoMaximo" value="">
                    </div>
                    <div class="paddingTop10  plazoMinimo hide">
                        <input class="block cameraBox width150 height30" type="text" id="plazoMinimo" name="plazoMinimo" value="">
                    </div>
                    <div class="paddingTop10  plazosPermitidos " >
                        <input class="block cameraBox width150 height30" type="text" id="plazosPermitidos22" name="plazosPermitidos" value="">
                    </div>
                    <div class="paddingTop10 hide saltoSlider">
                        <input class="block cameraBox width150 height30" type="text" id="saltoSlider" name="saltoSlider" value="">
                    </div>
                    <div class="paddingTop10">
                        <select class="block cameraBox width150 height30" id="periodicidadId" name="periodicidadId" value="">
                          
                            <g:each var="periodicidad" in="${la.kosmos.app.Periodicidad.findAll()}">
                                <option value=${periodicidad.id}> ${periodicidad.nombre}</option>
                            </g:each>
                        </select>
                    </div>
                </div>
            </div>    
                    </div>
      
                     <div class="col12" style="text-align: center;">
                        <div class="col4" style="display: inline-block;">
                            <button type="button" id="" onclick="registrarPlazoProducto();" class="loginButton letterspacing2 font14 pointer">REGISTRAR</button>
                        </div>
                        <div class="col4" style="display: inline-block;">
                            <button type="button" onclick="cerrarModal('modalAltaPlazoProducto');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
                        </div>
                    </div>
                </div>
            </div>
            </form>
        </div>
    </div>
</div>