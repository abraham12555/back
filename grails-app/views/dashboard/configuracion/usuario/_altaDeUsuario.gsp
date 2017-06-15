<div class="idLightbox hide" id="modalAltaDeUsuario">
    <div class="overlay"></div>
    <div class="whiteContainer lightboxPos altaUsuario darkBluetitle font14 fontWeight400 letterspacing1 justify">
        <form id="formAltaUsuario" action="/dashboard/guardarUsuario" method="POST" class="loginForm gray font14">
            <input type="hidden" id="userId" name="id"/>
            <div class="clearFix width990 autoMargin">
                <center><h1 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10 textUpper" id="operation-title"></h1></center>
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
                    <div class="paddingTop20" id="password-div">
                        <p class="gray font14 fontWeight500 latterspacing1 center">Contraseña</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">E-mail</p>
                    </div>
                    <div class="paddingTop25">
                        <p class="gray font14 fontWeight500 latterspacing1 center">Sucursal</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">Número empleado</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">Rol</p>
                    </div>
                    <div class="paddingTop20" id="lock-div">
                        <p class="gray font14 fontWeight500 latterspacing1 center">Bloquear acceso</p>
                    </div>
                    <div class="paddingTop20" id="enabled-div">
                        <p class="gray font14 fontWeight500 latterspacing1 center">Activo</p>
                    </div>
                </div>
                <div class="col8 floatLeft">
                    <div class="paddingTop15">
                        <div class="control">
                            <input class="block cameraBox col11 height30" type="text" name="nombre" id="name">
                        </div>
                    </div>
                    <div class="paddingTop10">
                        <div class="control">
                            <input class="block cameraBox col11 height30" type="text" name="apellidoPaterno" id="apPaterno">
                        </div>
                    </div>
                    <div class="paddingTop10">
                        <div class="control">
                            <input class="block cameraBox col11 height30" type="text" name="apellidoMaterno" id="apMaterno">
                        </div>
                    </div>
                    <div class="paddingTop10">
                        <div class="control">
                            <input class="block cameraBox col11 height30" type="text" name="username" id="userName">
                        </div>
                    </div>
                    <div class="paddingTop10" id="password-field">
                        <div class="control">
                            <input class="block cameraBox col11 height30" type="password" name="password" id="password">
                        </div>
                    </div>
                    <div class="paddingTop10">
                        <div class="control">
                            <input class="block cameraBox col11 height30" type="text" name="email" id="email">
                        </div>
                    </div>
                    <div class="paddingTop10">
                        <div class="control">
                            <g:select id="sucursal" name="sucursal.id" class="cameraBox col11 block height30" from="${listaSucursales}" optionKey="id" optionValue="nombre" noSelection="['':'Seleccione una sucursal']" value="" />
                        </div>
                    </div>
                    <div class="paddingTop10">
                        <div class="control">
                            <input class="block cameraBox col11 height30" type="text" name="numeroDeEmpleado" id="noEmpleado">
                        </div>
                    </div>
                    <div class="paddingTop10">
                        <div class="control">
                            <g:select id="rol" name="roles" class="cameraBox col11 block height30" from="${listaDeRoles}" optionKey="id" optionValue="authority" noSelection="['':'Seleccione un rol']" value="" />
                        </div>
                    </div>
                    <div class="paddingTop20" id="lock-field">
                        <div class="control">
                            <g:radio name="accountLocked" value="true"/> <span class="marginRight20">Sí</span>
                            <g:radio name="accountLocked" value="false" checked="true"/> <span class="marginRight20">No</span>
                        </div>
                    </div>
                    <div class="paddingTop20" id="enabled-field">
                        <div class="control">
                            <g:radio name="enabled" value="true"/> <span class="marginRight20">Sí</span>
                            <g:radio name="enabled" value="false" checked="true"/> <span class="marginRight20">No</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="formContainer">
                <button type="button" class="loginButton blueButton letterspacing2 font14 pointer textUpper" id="saveUser-btn">GUARDAR</button>
                <button type="button" onclick="cerrarModal('modalAltaDeUsuario');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
            </div>
        </form>
    </div>
</div>