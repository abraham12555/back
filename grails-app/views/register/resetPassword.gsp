<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <title>Cambiar contraseña</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
        <meta charset="utf-8">
        <g:external dir="css" file="bootstrap.min.css" title="text/css" rel="stylesheet" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,700italic,500,500italic" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet" type="text/css">
        <g:external dir="css" file="bootstrap.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="formulario.css" title="text/css" rel="stylesheet" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
        <g:external dir="js" file="validaciones.min.js" />
    </head>
    <body class="loginBody">
        <div class="loginContainer" style="width: 750px;">
            <div class="loginBox">
                <g:if test='${message}'>
                    <br/>
                    <div class="alert alert-danger center">
                        ${message}
                    </div>
                </g:if>
                <form class="loginForm gray font14" action="/register/resetPassword" id="updatePassword" method="POST" autocomplete="off">
                    <g:hiddenField name='t' value='${token}'/>
                    <p class="loginTitle darkBluetitle textUpper letterspacing1 center fontWeight700">Restablecimiento de contraseña</p>

                    <div class="formContainer">
                        <p>La nueva contraseña debe cumplir con los siguientes requisitos:</p>
                        <ul class="marginBottom20">
                            <li>Tener longitud entre 8 y 12 caracteres.</li>
                            <li>Tener al menos una letra mayúscula, una letra minúscula, un número y un caracter especial</li>
                        </ul>
                        <label class="formLabel letterspacing1 emailLabel">NUEVA CONTRASEÑA</label>
                        <div class="control">
                            <input class="inputs passwordInput lightGray letterspacing1 font14" type="password" placeholder="Escribe tu nueva contraseña" name="password" id="password"/>
                        </div>
                        <label class="formLabel letterspacing1 passwordLabel">CONFIRMAR CONTRASEÑA</label>
                        <div class="control">
                            <input class="inputs passwordInput lightGray letterspacing1 font14" type="password" placeholder="Confirma tu contraseña" id="confirm-password"/>
                        </div>
                        <button type="button" class="loginButton blueButton letterspacing2 font14" id="updatePassword-btn">RESTABLECER CONTRASEÑA</button>

                    </div>
                </form>
            </div>
            <div class="loginShadow"></div>
        </div>
    </body>
</html>
