<div class="idLightbox hide" id="modalAltaDeUsuario">
    <div class="overlay"></div>
    <div class="whiteContainer lightboxPos altaUsuario darkBluetitle font14 fontWeight400 justify">
        <g:urlContextAware value="/dashboard/guardarUsuario" var="urlFormAltaUsuario"/>
        <form id="formAltaUsuario" action="${urlFormAltaUsuario}" method="POST" class="loginForm gray font14">
            <input type="hidden" id="userId" name="id"/>
            <div class="clearFix width990 autoMargin">
                <center><h1 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10 textUpper" id="operation-title"></h1></center>
            </div>
            <div class="col12 col12-mob floatLeft clearFix marginTop25 marginBottom25 paddingBottom20 borderGrayBottom">
                <div class="col12 col12-mob floatLeft">
                    <div class="col5 col5-mob floatLeft paddingTop15">
                    </div>
                    <div class="col7 col7-mob floatLeft paddingTop15">
                        <div id="name-control" class="marginRight25 letterspacing1"></div>
                    </div>
                    <div class="col5 col5-mob floatLeft">
                        <div class="paddingTop7"></div>
                        <div class="marginLeft20">
                            <p class="gray font14 fontWeight500 letterspacing1">Nombre(s)</p>
                        </div>
                    </div>
                    <div class="col7 col7-mob floatLeft">
                        <div class="paddingTop5">
                            <input class="block cameraBox col11 height30" type="text" name="nombre" id="name">
                        </div>
                    </div>
                </div>
                <div class="col12 col12-mob floatLeft">
                    <div class="col5 col5-mob floatLeft paddingTop15">
                    </div>
                    <div class="col7 col7-mob floatLeft paddingTop15">
                        <div id="apPaterno-control" class="marginRight25 letterspacing1"></div>
                    </div>
                    <div class="col5 col5-mob floatLeft">
                        <div class="paddingTop7"></div>
                        <div class="marginLeft20">
                            <p class="gray font14 fontWeight500 letterspacing1">Apellido paterno</p>
                        </div>
                    </div>
                    <div class="col7 col7-mob floatLeft">
                        <div class="paddingTop5">
                            <input class="block cameraBox col11 height30" type="text" name="apellidoPaterno" id="apPaterno">
                        </div>
                    </div>
                </div>
                <div class="col12 col12-mob floatLeft">
                    <div class="col5 col5-mob floatLeft paddingTop15">
                    </div>
                    <div class="col7 col7-mob floatLeft paddingTop15">
                        <div id="apMaterno-control" class="marginRight25 letterspacing1"></div>
                    </div>
                    <div class="col5 col5-mob floatLeft">
                        <div class="paddingTop7"></div>
                        <div class="marginLeft20">
                            <p class="gray font14 fontWeight500 letterspacing1">Apellido materno</p>
                        </div>
                    </div>
                    <div class="col7 col7-mob floatLeft">
                        <div class="paddingTop5">
                            <input class="block cameraBox col11 height30" type="text" name="apellidoMaterno" id="apMaterno">
                        </div>
                    </div>
                </div>
                <div class="col12 col12-mob floatLeft">
                    <div class="col5 col5-mob floatLeft paddingTop15">
                    </div>
                    <div class="col7 col7-mob floatLeft paddingTop15">
                        <div id="username-control" class="marginRight25 letterspacing1"></div>
                    </div>
                    <div class="col5 col5-mob floatLeft">
                        <div class="paddingTop7"></div>
                        <div class="marginLeft20">
                            <p class="gray font14 fontWeight500 letterspacing1">Nombre de usuario</p>
                        </div>
                    </div>
                    <div class="col7 col7-mob floatLeft">
                        <div class="paddingTop5">
                            <input class="block cameraBox col11 height30" type="text" name="username" id="username">
                        </div>
                    </div>
                </div>
                <div class="col12 col12-mob floatLeft" id="password-div">
                    <div class="col5 col5-mob floatLeft paddingTop15">
                    </div>
                    <div class="col7 col7-mob floatLeft paddingTop15">
                        <div id="password-control" class="marginRight25 letterspacing1"></div>
                    </div>
                    <div class="col5 col5-mob floatLeft">
                        <div class="paddingTop7"></div>
                        <div class="marginLeft20">
                            <p class="gray font14 fontWeight500 letterspacing1">Contraseña</p>
                        </div>
                    </div>
                    <div class="col7 col7-mob floatLeft">
                        <div class="paddingTop5">
                            <input class="block cameraBox col11 height30" type="password" name="password" id="password">
                        </div>
                    </div>
                </div>
                <div class="col12 col12-mob floatLeft">
                    <div class="col5 col5-mob floatLeft paddingTop15">
                    </div>
                    <div class="col7 col7-mob floatLeft paddingTop15">
                        <div id="email-control" class="marginRight25 letterspacing1"></div>
                    </div>
                    <div class="col5 col5-mob floatLeft">
                        <div class="paddingTop7"></div>
                        <div class="marginLeft20">
                            <p class="gray font14 fontWeight500 letterspacing1">Correo electrónico</p>
                        </div>
                    </div>
                    <div class="col7 col7-mob floatLeft">
                        <div class="paddingTop5">
                            <input class="block cameraBox col11 height30" type="text" name="email" id="email">
                        </div>
                    </div>
                </div>
                <div class="col12 col12-mob floatLeft">
                    <div class="col5 col5-mob floatLeft paddingTop15">
                    </div>
                    <div class="col7 col7-mob floatLeft paddingTop15">
                        <div id="sucursal-control" class="marginRight25 letterspacing1"></div>
                    </div>
                    <div class="col5 col5-mob floatLeft">
                        <div class="paddingTop7"></div>
                        <div class="marginLeft20">
                            <p class="gray font14 fontWeight500 letterspacing1">Sucursal</p>
                        </div>
                    </div>
                    <div class="col7 col7-mob floatLeft">
                        <div class="paddingTop5">
                            <g:select id="sucursal" name="sucursal.id" class="cameraBox col11 block height30" from="${listaSucursales}" optionKey="id" optionValue="nombre" noSelection="['':'Seleccione una sucursal']" value="" />
                        </div>
                    </div>
                </div>
                <div class="col12 col12-mob floatLeft">
                    <div class="col5 col5-mob floatLeft paddingTop15">
                    </div>
                    <div class="col7 col7-mob floatLeft paddingTop15">
                        <div id="noEmpleado-control" class="marginRight25 letterspacing1"></div>
                    </div>
                    <div class="col5 col5-mob floatLeft">
                        <div class="paddingTop7"></div>
                        <div class="marginLeft20">
                            <p class="gray font14 fontWeight500 letterspacing1">Número de empleado</p>
                        </div>
                    </div>
                    <div class="col7 col7-mob floatLeft">
                        <div class="paddingTop5">
                            <input class="block cameraBox col11 height30" type="text" name="numeroDeEmpleado" id="noEmpleado">
                        </div>
                    </div>
                </div>
                <div class="col12 col12-mob floatLeft">
                    <div class="col5 col5-mob floatLeft paddingTop15">
                    </div>
                    <div class="col7 col7-mob floatLeft paddingTop15">
                        <div id="rol-control" class="marginRight25 letterspacing1"></div>
                    </div>
                    <div class="col5 col5-mob floatLeft">
                        <div class="paddingTop7"></div>
                        <div class="marginLeft20">
                            <p class="gray font14 fontWeight500 letterspacing1">Rol</p>
                        </div>
                    </div>
                    <div class="col7 col7-mob floatLeft">
                        <div class="paddingTop5">
                            <g:select id="rol" name="roles" class="cameraBox col11 block height30" from="${listaDeRoles}" optionKey="id" optionValue="authority" noSelection="['':'Seleccione un rol']" value="" />
                        </div>
                    </div>
                </div>
                <div class="col12 col12-mob floatLeft" id="lock-div">
                    <div class="col5 col5-mob floatLeft paddingTop15">
                    </div>
                    <div class="col7 col7-mob floatLeft paddingTop15">
                        <div id="accountLocked-control" class="marginRight25 letterspacing1"></div>
                    </div>
                    <div class="col5 col5-mob floatLeft">
                        <div class="paddingTop7"></div>
                        <div class="marginLeft20">
                            <p class="gray font14 fontWeight500 letterspacing1">Bloquear acceso</p>
                        </div>
                    </div>
                    <div class="col7 col7-mob floatLeft">
                        <div class="paddingTop5">
                            <g:radio name="accountLocked" value="true"/> <span class="marginRight20">Sí</span>
                            <g:radio name="accountLocked" value="false" checked="true"/> <span class="marginRight20">No</span>
                        </div>
                    </div>
                </div>
                <div class="col12 col12-mob floatLeft" id="enabled-div">
                    <div class="col5 col5-mob floatLeft paddingTop15">
                    </div>
                    <div class="col7 col7-mob floatLeft paddingTop15">
                        <div id="enabled-control" class="marginRight25 letterspacing1"></div>
                    </div>
                    <div class="col5 col5-mob floatLeft">
                        <div class="paddingTop7"></div>
                        <div class="marginLeft20">
                            <p class="gray font14 fontWeight500 letterspacing1">Activo</p>
                        </div>
                    </div>
                    <div class="col7 col7-mob floatLeft">
                        <div class="paddingTop5">
                            <g:radio name="enabled" value="true"/> <span class="marginRight20">Sí</span>
                            <g:radio name="enabled" value="false" checked="true"/> <span class="marginRight20">No</span>
                        </div>
                    </div>
                </div>
                <div class="col12 col12-mob floatLeft paddingTop15" id="resetPassword-div">
                    <div class="col5 col5-mob floatLeft">
                        <div class="paddingTop7"></div>
                        <div class="marginLeft20">
                            <p class="gray font14 fontWeight500 letterspacing1">Restablecer contraseña</p>
                        </div>
                    </div>
                    <div class="col7 col7-mob floatLeft">
                        <div class="paddingTop5">
                            <g:radio name="resetPassword" value="true"/> <span class="marginRight20">Sí</span>
                            <g:radio name="resetPassword" value="false" checked="true"/> <span class="marginRight20">No</span>
                        </div>
                    </div>
                </div>
                <div class="col12 col12-mob floatLeft" id="newPassword-div">
                    <div class="col5 col5-mob floatLeft paddingTop15">
                    </div>
                    <div class="col7 col7-mob floatLeft paddingTop15">
                        <div id="newPassword-control" class="marginRight25 letterspacing1"></div>
                    </div>
                    <div class="col5 col5-mob floatLeft">
                        <div class="paddingTop7"></div>
                        <div class="marginLeft20">
                            <p class="gray font14 fontWeight500 letterspacing1">Nueva contraseña</p>
                        </div>
                    </div>
                    <div class="col7 col7-mob floatLeft">
                        <div class="paddingTop5">
                            <input class="block cameraBox col11 height30" type="password" name="newPassword" id="newPassword">
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
