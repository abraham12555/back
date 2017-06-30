<%@ page contentType="text/html;charset=UTF-8" %>

<div class="idLightbox hide" id="updatePassword">
    <div class="overlay"></div>
    <div class="loginContainer creditLb">
        <div class="dashBordBox">
            <form action="/dashboard/updatePassword" method="POST" class="loginForm gray font14" id="updatePassword-form">
                <p class="loginTitle font25 fontWeight500 darkBluetitle textUpper letterspacing1 center paddingAside15 marginBottom25">Cambiar contraseña</p>
                <div class="solicitudTitle autoMargin">
                    <p class="font14 marginLeft30">La nueva contraseña debe cumplir con los siguientes requisitos:</p>
                        <ul class="marginLeft30">
                            <li>Tener longitud entre 8 y 12 caracteres.</li>
                            <li>Tener al menos una letra mayúscula, una letra minúscula, un número y un caracter especial</li>
                        </ul>
                </div>
                <div class="formContainer">
                    <label class="formLabel letterspacing1 emailLabel">Nueva contraseña</label>
                    <div class="control">
                        <input class="inputs passwordInput lightGray letterspacing1 font14" type="password" placeholder="Escribe tu nueva contraseña" name="password" id="password"/>
                    </div>
                    <label class="formLabel letterspacing1 passwordLabel">Confirmar contraseña</label>
                    <div class="control">
                        <input class="inputs passwordInput lightGray letterspacing1 font14" type="password" placeholder="Confirma tu contraseña" id="confirm-password"/>
                    </div>
                    <button type="button" class="loginButton blueButton letterspacing2 font14" id="changePassword-btn">CAMBIAR MI CONTRASEÑA</button>
                    <button type="button" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);" id="cancelUpdatePassword-btn">CANCELAR</button>
                </div>
            </form>
        </div>
        <div class="loginShadow"></div>
    </div>
</div>
