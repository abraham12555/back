<div class="solicitudLightbox hide" style="overflow: scroll;" id="modalAltaCampoFormulario">
    <div class="overlay"></div>
    <div class="visitaContainer">
        <div class="dashBordBox">
            <form id="campoFormularioForm" class="loginForm gray font14">

                <div class="formContainer">
                    <div class='col12' style='display:inline-block; position:relative;'>

                        <div class="width990 autoMargin solicitudWhiteBox clearFix paddingBottom20">
                            <input  name="idCampoFormulario" id="idCampoFormulario" type="hidden" value="${campoFormulario?.id}"/>
                            <div class="clearFix width990 autoMargin borderGrayBottom">
                                <center><h1 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">NOMBRE Y TIPO DE CAMPO: ${campoFormulario.nombreDelCampo.toUpperCase()} ${campoFormulario.tipoDeCampo.elementoDeEntrada.toUpperCase()}</h1></center>
                            </div>
                            <div class="clearFix width990 autoMargin ">
                                <center><h2 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">Generales</h2></center>
                            </div>
                            <div class="col6 floatLeft marginTop20">
                                <div class="col12 floatLeft">
                                    <div class="col6 floatLeft">
                                        <p class="gray font14 fontWeight500 letterspacing1 marginLeft20"><strong>Titulo <br>del Campo</strong></p>
                                    </div>
                                    <div class="col6 floatLeft">
                                        <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="tituloDelCampo" >
                                    </div>
                                </div>
                                <div class="col12 floatLeft">
                                    <div class="col6 floatLeft">
                                        <p class="gray font14 fontWeight500 letterspacing1 marginLeft20"><strong>Longitud del Campo</strong></p>
                                    </div>
                                    <div class="col6 floatLeft">
                                        <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="longitudDelCampo" />
                                    </div>
                                </div>
                                <div class="col12 floatLeft">
                                    <div class="col6 floatLeft">
                                        <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Numero de Campo</strong></p>
                                    </div>
                                    <div class="col6 floatLeft paddingTop8">
                                        <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="numeroDeCampo" />
                                    </div>
                                </div>
                                <div class="col12 floatLeft">
                                    <div class="col6 floatLeft">
                                        <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Placeholder</strong></p>
                                    </div>
                                    <div class="col6 floatLeft paddingTop8">
                                        <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="placeholder" />
                                    </div>
                                </div>
                                <div class="col12 floatLeft">
                                    <div class="col6 floatLeft">
                                        <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Texto Anterior</strong></p>
                                    </div>
                                    <div class="col6 floatLeft paddingTop8">
                                        <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="textoAnterior" />

                                    </div>
                                </div>
                                <div class="col12 floatLeft">
                                    <div class="col6 floatLeft">
                                        <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Texto Posterior</strong></p>
                                    </div>
                                    <div class="col6 floatLeft paddingTop8">
                                        <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="textoPosterior" />
                                    </div>
                                </div>
                                <div class="col12 floatLeft">
                                    <div class="col6 floatLeft">
                                        <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Tiene Ayuda</strong></p>
                                    </div>
                                    <div class="col6 floatLeft paddingTop8">
                                        <div class="col2 floatLeft">
                                            <div class="paddingTop15">
                                                <label>Si</label>
                                                <input class="block cameraBox width150 height30 "  type="radio"  name="tieneAyuda"  onclick="mostrarCampo('activo');"  value="true" checked>

                                            </div>
                                        </div>
                                        <div class="col2 floatLeft">
                                            <div class="paddingTop15">
                                                <label>No</label>
                                                <input class="block cameraBox width150 height30 " type="radio"  name="tieneAyuda"  onclick="ocultarCampo('activo');"  value="false">

                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <div class="col12 floatLeft activo">
                                    <div class="col6 floatLeft ">
                                        <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Texto Ayuda</strong></p>
                                    </div>
                                    <div class="col6 floatLeft paddingTop8">
                                        <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="textoAyuda" />
                                    </div>
                                </div>
                                <div class="col12 floatLeft">
                                    <div class="col6 floatLeft">
                                        <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Salto de Linea al Final</strong></p>
                                    </div>
                                    <div class="col6 floatLeft paddingTop8">
                                        <div class="col2 floatLeft">
                                            <div class="paddingTop15">
                                                <label>Si</label>
                                                <input class="block cameraBox width150 height30 "  type="radio"  name="saltoDeLineaAlFinal"  value="true" checked>

                                            </div>
                                        </div>
                                        <div class="col2 floatLeft">
                                            <div class="paddingTop15">
                                                <label>No</label>
                                                <input class="block cameraBox width150 height30 " type="radio"  name="saltoDeLineaAlFinal"  value="false">

                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col6 floatLeft marginTop20">
                                <div class="col12 floatLeft">
                                    <div class="col6 floatLeft">
                                        <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Mostrar al Inicio</strong></p>
                                    </div>
                                    <div class="col6 floatLeft paddingTop8">
                                        <div class="col2 floatLeft">
                                            <div class="paddingTop15">
                                                <label>Si</label>
                                                <input class="block cameraBox width150 height30 "  type="radio"  name="mostrarAlInicio"  value="true" checked>

                                            </div>
                                        </div>
                                        <div class="col2 floatLeft">
                                            <div class="paddingTop15">
                                                <label>No</label>
                                                <input class="block cameraBox width150 height30 " type="radio"  name="mostrarAlInicio"  value="false">

                                            </div>

                                        </div>

                                    </div>
                                </div>
                                <div class="col12 floatLeft">
                                    <div class="col6 floatLeft">
                                        <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Obligatorio</strong></p>
                                    </div>
                                    <div class="col6 floatLeft paddingTop8">
                                        <div class="col2 floatLeft">
                                            <div class="paddingTop15">
                                                <label>Si</label>
                                                <input class="block cameraBox width150 height30 "  type="radio"  name="obligatorio"  value="true" checked>

                                            </div>
                                        </div>
                                        <div class="col2 floatLeft">
                                            <div class="paddingTop15">
                                                <label>No</label>
                                                <input class="block cameraBox width150 height30 " type="radio"  name="obligatorio"  value="false">

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="formContainer">
                        <button type="button" onclick="agregarCampoFormulario();" class="loginButton blueButton letterspacing2 font14 pointer">REGISTRAR</button>
                        <button type="button" onclick="cerrarModal('modalAltaCampoFormulario');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>