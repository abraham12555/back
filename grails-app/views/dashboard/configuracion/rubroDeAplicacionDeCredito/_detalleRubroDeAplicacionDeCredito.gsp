<div class="idLightbox hide" id="modalDetalleRubroDeAplicacionDeCredito">  
    <div class="overlay"></div>
    <div class="visitaContainer">

        <div class="dashBordBox">
            <div class="loginForm gray font14" class="tabcontent">
                <form id="rubroDeAplicacionDeCreditoForm" class="loginForm gray font14">

                    <div class="formContainer">
                        <div class='col12' style='display:inline-block; position:relative;'>

                            <div class="width990 autoMargin solicitudWhiteBox clearFix paddingBottom20">
                                <input type="hidden" id="opcionRubro" value="5">
                                <input type="hidden" id="tipoDeDocumento">

                                <input  name="idRubroDeAplicacionDeCredito" id="idRubroDeAplicacionDeCredito" type="hidden" value="${rubroDeAplicacionDeCredito?.id}"/>

                                <div class="clearFix width990 autoMargin borderGrayBottom">
                                    <center><h1 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">DETALLE DEL RUBRO: ${rubroDeAplicacionDeCredito.nombre.toUpperCase()}</h1></center>
                                </div>
                                <div class="clearFix width990 autoMargin ">
                                    <center><h2 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">Generales</h2></center>
                                </div>
                                <div class="col6 floatLeft marginTop20">
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 marginLeft20"><strong>Nombre del Rubro</strong></p>
                                        </div>
                                        <div class="col6 floatLeft">
                                            <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="nombreRubro" value="${rubroDeAplicacionDeCredito.nombre}">
                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Posición</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8">
                                            <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="posicion" value="${rubroDeAplicacionDeCredito.posicion}"/>
                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Descripción</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8">
                                            <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="descripcion" value="${rubroDeAplicacionDeCredito.descripcion}"/>
                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Icono</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8">

                                            <input onfocus="verListaIconos('detalleIconosDetalleRubroDeAplicacionDeCredito','claseIconoPasoDetalleRubroDeAplicacionDeCredito');" class="block cameraBox width150 height30" type="text" id="claseIconoPasoDetalleRubroDeAplicacionDeCredito" name="claseIconoPaso" value="${rubroDeAplicacionDeCredito.claseIconoPaso}">

                                        </div>
                                    </div>

                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Tooltip</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8">
                                            <g:if test="${rubroDeAplicacionDeCredito.tooltip == true}">
                                                <div class="col2 floatLeft">
                                                    <div class="paddingTop15">
                                                        <label>Si</label>
                                                        <input class="block cameraBox width150 height30 " onclick="mostrarCampo('activo');" type="radio"  name="tooltip"  value="true" checked>

                                                    </div>

                                                </div>
                                                <div class="col2 floatLeft">
                                                    <div class="paddingTop15">
                                                        <label>No</label>
                                                        <input class="block cameraBox width150 height30 " onclick="ocultarCampo('activo');" type="radio"  name="tooltip"  value="false">

                                                    </div>

                                                </div>
                                            </g:if>
                                            <g:if test="${rubroDeAplicacionDeCredito.tooltip == false}">
                                                <div class="col2 floatLeft">
                                                    <div class="paddingTop15">
                                                        <label>Si</label>
                                                        <input class="block cameraBox width150 height30 " onclick="mostrarCampo('activo');" type="radio"  name="tooltip"  value="true" >

                                                    </div>

                                                </div>
                                                <div class="col2 floatLeft">
                                                    <div class="paddingTop15">
                                                        <label>No</label>
                                                        <input class="block cameraBox width150 height30 " onclick="ocultarCampo('activo');" type="radio"  name="tooltip"  value="false" checked>

                                                    </div>

                                                </div>
                                            </g:if>

                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft activo" >
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Texto Tooltip</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8 activo" >
                                            <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="textoTooltip" value="${rubroDeAplicacionDeCredito.textoTooltip}"/>
                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Activo</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8">
                                            <g:if test="${rubroDeAplicacionDeCredito.activo == true}">
                                            <div class="col2 floatLeft">
                                                <div class="paddingTop15">
                                                    <label>Si</label>
                                                    <input class="block cameraBox width150 height30 "  type="radio"  name="activo"  value="true" checked>

                                                </div>
                                            </div>
                                            <div class="col2 floatLeft">
                                                <div class="paddingTop15">
                                                    <label>No</label>
                                                    <input class="block cameraBox width150 height30 " type="radio"  name="activo"  value="false">

                                                </div>

                                            </div>
                                        </g:if>
                                            
                                        <g:if test="${rubroDeAplicacionDeCredito.activo == false}">
                                            <div class="col2 floatLeft">
                                                <div class="paddingTop15">
                                                    <label>Si</label>
                                                    <input class="block cameraBox width150 height30 "  type="radio"  name="activo"  value="true" >

                                                </div>
                                            </div>
                                            <div class="col2 floatLeft">
                                                <div class="paddingTop15">
                                                    <label>No</label>
                                                    <input class="block cameraBox width150 height30 " type="radio"  name="activo"  value="false" checked>

                                                </div>

                                            </div>
                                        </g:if>
                                            
                                            
                                            
                                        </div>
                                        
                                        
                                        
                                    </div>

                                </div>
                                <div class="col6 floatLeft marginTop20">
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 marginLeft20"><strong>Imagen</strong></p>
                                        </div>
                                        <div class="col6 floatLeft">

                                            <section class="container marginBottom20">
                                                <div class="solicitudBox width480 autoMargin radius2 ">
                                                    <div id="urlImagenRubro" class="clearFix paddingTop15 paddingBottom15 paddingLeft25 paddingRight25">
                                                        <div  class="rubro-bg" style="background-image: url('data:image/${urlImagen?.extension};base64,${urlImagen?.base64}');">
                                                        </div>
                                                        <div id="divDropzone2"  class="floatLeft subirImagen">
                                                            <a id="subirImagenRubro" title="BUSCAR EN MI PC" class="block whiteBox center gray2 paddingTop15 paddingBottom15 paddingLeft80 paddingRight80 pointer">BUSCAR EN MI PC</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </section>
                                        </div>
                                    </div>
                                </div>
                                <br /> &nbsp;
                                <br /> &nbsp;
                                <br /> &nbsp;
                                <br /> &nbsp;
                                <br /> &nbsp;
                                <div class="clearFix width990 autoMargin ">
                                    <center><h2 class="font18 fontWeight600 darkBluetitle marginLeft20">Documentos Asociados al Rubro</h2></center>
                                </div>
                                <div class="col12 floatLeft">
                                    <div class="col6 floatLeft">
                                        <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Nombre Documento</strong></p>
                                    </div>

                                    <div class="col6 floatLeft paddingTop8">

                                        <select class="js-example-basic-multiple"  multiple="multiple" name="tipoDeDocumentoId">
                                            <g:each var="documento" in="${rubroDeAplicacionTipoDeDocumento}">
                                                <option value=${documento.tipoDeDocumento.id} selected="selected" >${documento.tipoDeDocumento.nombre}</option>
                                            </g:each>
                                            <g:each var="faltan" in="${documentosFaltantes}">
                                                <option value=${faltan.id}  >${faltan.nombre}</option>
                                            </g:each>
                                        </select>    

                                    </div>
                                </div>
                                <br /> &nbsp;
                                <div class="clearFix width990 autoMargin ">
                                    <center><h2 class="font18 fontWeight600 darkBluetitle marginLeft20">Vistas Asociadas al Rubro</h2></center>
                                </div>
                                <div class="col12 floatLeft">
                                    <div class="col6 floatLeft">
                                        <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Nombre del Paso</strong></p>
                                    </div>
                                    <div class="col6 floatLeft paddingTop8">
                                        <select class="js-example-basic-multiple"  multiple="multiple" name="pasoId">
                                            <g:each var="paso" in="${rubroDeAplicacionPasoCotizador}">
                                                <option value=${paso.paso.id} selected="selected" >${paso.paso.tituloResumen}</option>
                                            </g:each>
                                            <g:each var="faltanVista" in="${faltanVistas}">
                                                <option value=${faltanVista.id}  >${faltanVista.tituloResumen}</option>
                                            </g:each>
                                        </select>   
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="formContainer">
                            <button type="button" onclick="actualizarRubro();" class="loginButton blueButton letterspacing2 font14 pointer">ACTUALIZAR</button>
                            <button type="button" onclick="cerrarModal('modalDetalleRubroDeAplicacionDeCredito');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>

<div id="detalleIconosDetalleRubroDeAplicacionDeCredito"></div>
