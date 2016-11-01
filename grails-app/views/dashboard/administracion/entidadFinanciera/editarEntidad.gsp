<div class="overlay"></div>
<div class="loginContainer creditLb">
    <div class="dashBordBox">
        <form action="/kosmos-app/dashboard/registrar" method="POST" class="loginForm gray font14">
            <p class="loginTitle font25 fontWeight500 darkBluetitle textUpper letterspacing1 center paddingAside15 marginBottom25">REGISTRAR NUEVA ENTIDAD FINANCIERA</p>
            <div style="width: 90%;" class="solicitudTitle autoMargin">
                <p class="center font14 lightGray">Proporcione el nombre de la Entidad Financiera a Registrar</p>
            </div>
            <div class="formContainer">
                <input class="inputs marginBottom30 lightGray letterspacing1 font14" name="nombreEntidad" type="text" required placeholder="Nombre de la Entidad Financiera" value="${entidad.nombre}"/>
                <button type="submit" class="loginButton blueButton letterspacing2 font14 pointer">ACTUALIZAR</button>
                <button type="button" onclick="cerrarModal('nuevaEntidad');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
            </div>
        </form>
    </div>
    <div class="loginShadow"></div>
</div>