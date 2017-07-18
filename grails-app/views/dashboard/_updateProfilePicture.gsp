<%@ page contentType="text/html;charset=UTF-8" %>

<div class="idLightbox hide" id="updateProfilePicture">
    <div class="overlay"></div>
    <div class="lightboxPos altaUsuario darkBluetitle font14 fontWeight400 justify">
        <g:urlContextAware value="/dashboard/saveProfilePicture" var="urlForm"/>
        <form action="${urlForm}" method="POST" class="loginForm gray font14" id="updateProfilePicture-form">
            <div class="clearFix width990 autoMargin">
                <center><h1 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10 textUpper">Cambiar foto de perfil</h1></center>
            </div>
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
