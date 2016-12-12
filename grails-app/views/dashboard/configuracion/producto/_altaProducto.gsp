<div class="idLightbox hide" id="modalAltaProducto">
    <div class="overlay"></div>
    <div class="whiteContainer lightboxPos altaUsuario darkBluetitle font14 fontWeight400 letterspacing1 justify">
        <form id="formAltaUsuario" action="/kosmos-app/dashboard/registrarUsuario" method="POST" class="loginForm gray font14">
            <div class="clearFix width990 autoMargin">
                <center><h1 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">NUEVO PRODUCTO</h1></center>
            </div>
            <div class="width990 autoMargin solicitudWhiteBox clearFix paddingBottom20">
                <div class="padding20 clearFix borderGrayBottom">
                    <p class="floatLeft font14 fontWeight500 letterspacing1 gray paddingTop5 marginRight25">NOMBRE DEL PRODUCTO</p>
                    <input class="floatLeft cameraBox width258 height30 font14 lightGray " type="text" name="NOMBRE DEL PRODUCTO" value="">
                </div>
                <div class="col6 floatLeft marginTop20">
                    <p class="gray font14 fontWeight500 letterspacing1 marginLeft20">Monto</p>
                    <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20">Plazos</p>
                    <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20">Periodicidad</p>
                </div>
                <div class="col6 blanks floatLeft paddingTop10">
                    <input class="block cameraBox width150 height30 radius2 font14 lightGray" type="text" name="Monto" value="">
                    <input class="block cameraBox width150 height30 radius2 font14 lightGray marginTop5" type="text" name="Plazos" value="">
                    <input class="block cameraBox width150 height30 radius2 font14 lightGray marginTop5" type="text" name="Periodicidad" value="">
                </div>
                <div class="col6 floatLeft marginTop20">
                    <p class="gray font14 fontWeight500 letterspacing1 marginLeft20">Enganche Mínimo</p>
                    <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20">Tasas</p>
                    <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20">Comisiones</p>
                </div>
                <div class="col6 blanks floatLeft paddingTop10">
                    <input class="block cameraBox width150 height30 radius2 font14 lightGray" type="text" name="Enganche Mínimo" value="">
                    <input class="block cameraBox width150 height30 radius2 font14 lightGray marginTop5" type="text" name="Tasas" value="">
                    <input class="block cameraBox width150 height30 radius2 font14 lightGray marginTop5" type="text" name="Comisiones" value="">
                </div>
            </div>
            <div class="formContainer">
                <button type="submit" class="loginButton blueButton letterspacing2 font14 pointer">REGISTRAR USUARIO</button>
                <button type="button" onclick="cerrarModal('modalAltaDeUsuario');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
            </div>
        </form>
    </div>
</div>