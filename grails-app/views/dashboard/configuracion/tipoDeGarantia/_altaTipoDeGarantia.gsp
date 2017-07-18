<div class="idLightbox hide" id="modalAltaTipoDeGarantia">
    <div class="overlay"></div>
    <div class="whiteContainer lightboxPos altaUsuario darkBluetitle font14 fontWeight400 letterspacing1 justify">
        <g:urlContextAware value="/dashboard/registrarTipoDeGarantia" var="urlFormAltaUsuario"/>
        <form id="formAltaUsuario" action="${urlFormAltaUsuario}" method="POST" class="loginForm gray font14">
            <div class="clearFix width990 autoMargin">
                <center><h1 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">ALTA DE TIPO DE GARANTÍA</h1></center>
            </div>
            <div class="col12 col12-mob floatLeft clearFix marginTop25 marginBottom25 paddingBottom20 borderGrayBottom">
                <div class="col4 floatLeft">
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">NOMBRE</p>
                    </div>
                    
                </div>
                <div class="col8 floatLeft">
                    <div class="paddingTop15">
                        <input class="block cameraBox col11 height30" type="text" name="nombre" value="">
                    </div>
                                    </div>
            </div>
            <div class="formContainer">
                <button type="submit" class="loginButton blueButton letterspacing2 font14 pointer">REGISTRAR TIPO DE GARANTÍA</button>
                <button type="button" onclick="cerrarModal('modalAltaTipoDeGarantia');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
            </div>
        </form>
    </div>
</div>
