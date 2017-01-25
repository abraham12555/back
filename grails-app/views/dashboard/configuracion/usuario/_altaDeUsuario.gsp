<div class="idLightbox hide" id="modalAltaDeUsuario">
    <div class="overlay"></div>
    <div class="whiteContainer lightboxPos altaUsuario darkBluetitle font14 fontWeight400 letterspacing1 justify">
        <form id="formAltaUsuario" action="/dashboard/registrarUsuario" method="POST" class="loginForm gray font14">
            <div class="clearFix width990 autoMargin">
                <center><h1 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">ALTA DE USUARIO</h1></center>
            </div>
            <div class="col12 col12-mob floatLeft clearFix marginTop25 marginBottom25 paddingBottom20 borderGrayBottom">
                <div class="col4 floatLeft">
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">Nombre(s)</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">Apellido Paterno</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">Apellido Materno</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">Username</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">Contraseña</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">E-mail</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">Rol</p>
                    </div>
                </div>
                <div class="col8 floatLeft">
                    <div class="paddingTop15">
                        <input class="block cameraBox col11 height30" type="text" name="nombre" value="">
                    </div>
                    <div class="paddingTop8">
                        <input class="block cameraBox col11 height30" type="text" name="apellidoPaterno" value="">
                    </div>
                    <div class="paddingTop8">
                        <input class="block cameraBox col11 height30" type="text" name="apellidoMaterno" value="">
                    </div>
                    <div class="paddingTop8">
                        <input class="block cameraBox col11 height30" type="text" name="username" value="">
                    </div>
                    <div class="paddingTop8">
                        <input class="block cameraBox col11 height30" type="password" name="password" value="">
                    </div>
                    <div class="paddingTop8">
                        <input class="block cameraBox col11 height30" type="text" name="email" value="">
                    </div>
                    <div class="paddingTop8">
                        <g:select id="rol" name="rol.id" class="cameraBox col11 block height30"  from="${listaDeRoles}" optionKey="id" optionValue="authority" noSelection="['':'Seleccione un rol']" value="" />
                    </div>
                </div>
            </div>
            <div class="formContainer">
                <button type="submit" class="loginButton blueButton letterspacing2 font14 pointer">REGISTRAR USUARIO</button>
                <button type="button" onclick="cerrarModal('modalAltaDeUsuario');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
            </div>
        </form>
    </div>
</div>