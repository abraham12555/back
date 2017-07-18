<g:form  autocomplete="on" method="post" enctype="multipart/form-data">
    <div class="solicitudLightbox hide" id="nuevaEntidad">
        <div class="overlay"></div>
        <div class="visitaContainer">
            <div class="dashBordBox">
                <p class="loginTitle font25 fontWeight500 darkBluetitle textUpper letterspacing1 center paddingAside15 marginBottom25">REGISTRAR NUEVA ENTIDAD FINANCIERA</p>
                <div style="width: 90%;" class="solicitudTitle autoMargin">
                    <p class="center font14 lightGray">Proporcione el nombre de la Entidad Financiera a Registrar</p>
                </div>
                <div class="formContainer">
                    <div class='col12' style='display:inline-block; position:relative;'>
                        <div class="col6 col12-mob floatLeft clearFix marginTop25 marginBottom25">
                            <div class="col6 floatLeft">
                                <div class="paddingTop20">
                                    <p class="gray font12 fontWeight500 latterspacing1 center">CAMPOS</p>
                                </div>
                                <div class="paddingTop20">
                                    <p class="gray font14 fontWeight500 latterspacing1 center">(Nombre Nueva Entidad Financiera)</p>
                                </div>
                                <div class="paddingTop20">
                                    <p class="gray font14 fontWeight500 latterspacing1 center">(Logo)</p>
                                </div>
                                <div class="paddingTop20">
                                    <p class="gray font14 fontWeight500 latterspacing1 center">(Landing)</p>
                                </div>
                                <div class="paddingTop20">
                                    <p class="gray font14 fontWeight500 latterspacing1 center">(Aplicación Variable)</p>
                                </div>
                            </div>
                            <div class="col6 floatLeft">
                                <div class="paddingTop20">
                                    <p class="gray font12 fontWeight500 latterspacing1">TEXTO</p>
                                </div>
                                <div class="paddingTop15">
                                    <input class="block cameraBox width150 height30" type="text" id="nombreEntidadFinanciera" name="nombreEntidadFinanciera"  value="">
                                </div>
                                <div class="paddingTop15">
                                    <input class="block cameraBox width150 height30" type="file" id="rutaLogotipo2" name="imagenDefault2">
                                </div>
                                <div class="paddingTop15">
                                    <input class="block cameraBox width150 height30" type="file"  id="imagenDefault2" name="rutaLogotipo2">
                                </div>
                                <div class="col2 floatLeft">
                                    <div class="paddingTop15">
                                        <label>Si</label>
                                        <input class="block cameraBox width150 height30 "  type="radio" id="aplicacionVariable"  name="aplicacionVariable"  value="true" checked>
                                    </div>
                                </div>
                                <div class="col2 floatLeft">
                                    <div class="paddingTop15">
                                        <label>No</label>
                                        <input class="block cameraBox width150 height30 " type="radio" id="aplicacionVariable" name="aplicacionVariable"  value="false" >
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col12" style="text-align: center;">
                        <div class="col4" style="display: inline-block;">
                            <button type="button" id="btnVerificar" onclick="guardarNuevaEntidad();" class="loginButton letterspacing2 font14 pointer">REGISTRAR</button>
                        </div>

                        <div class="col4" style="display: inline-block;">
                            <button type="button" onclick="cerrarModal('nuevaEntidad');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="loginShadow"></div>
        </div>
    </div>
</g:form>
