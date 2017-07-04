<%@ page contentType="text/html;charset=UTF-8" %>

<div class="idLightbox hide" id="updateProfilePicture">
    <div class="overlay"></div>
    <div class="lightboxPos altaUsuario darkBluetitle font14 fontWeight400 justify">
        <form action="/dashboard/saveProfilePicture" method="POST" class="loginForm gray font14" id="updateProfilePicture-form">
            <p class="loginTitle font25 fontWeight500 darkBluetitle textUpper letterspacing1 center paddingAside15">Cambiar foto de perfil</p>
            <div class="formContainer">
                <div class="image-editor">
                    <div class="control">
                        <input type="file" class="cropit-image-input" id="profileFile">
                    </div>
                    <div class="cropit-preview"></div>
                    <div class="image-size-label">
                        Cambiar tamaño
                    </div>
                    <input type="range" class="cropit-image-zoom-input">
                    <input type="hidden" name="base64" class="hidden-image-data" />
                    <input type="hidden" id="nameFile" name="name"/>
                    <input type="hidden" id="typeFile" name="type"/>
                </div>
                <button type="button" class="loginButton blueButton letterspacing2 font14 marginTop30" id="changePicture-btn">CAMBIAR MI FOTO</button>
                <button type="button" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);" id="cancelChangePicture-btn">CANCELAR</button>
            </div>
        </form>
    </div>
</div>
