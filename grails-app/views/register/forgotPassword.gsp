<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <title>Restablecer contraseña</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
        <meta charset="utf-8">
        <g:external dir="css" file="bootstrap.min.css" title="text/css" rel="stylesheet" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,700italic,500,500italic" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet" type="text/css">
        <g:external dir="css" file="bootstrap.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="formulario.css" title="text/css" rel="stylesheet" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>-->
        <g:external dir="js" file="jquery-3.0.0.min.js" />
        <g:external dir="js" file="validaciones.min.js" />
        <g:external dir="js" file="register/register.min.js" />
    </head>
    <body class="loginBody">
        <div class="loginContainer" style="width: 750px;">
            <div class="loginBox">
                <br/>
                <div class="alert alert-success center hidden" id="successMessage-div"></div>
                <div class="alert alert-danger center hidden" id="errorMessage-div"></div>
                <form class="loginForm gray font14" id="recoverPassword" method="POST" autocomplete="off">
                    <p class="loginTitle darkBluetitle textUpper letterspacing1 center fontWeight700">Restablecer contraseña</p>

                    <div class="formContainer">
                        <p class="marginBottom20">Proporciona el correo electrónico asociado a tu cuenta para iniciar el proceso de recuperación de contraseña</p>
                        <label class="formLabel letterspacing1 emailLabel">CORREO ELECTRÓNICO</label>
                        <div class="control">
                            <input class="inputs emailInput lightGray letterspacing1 font14" type="text" placeholder="Escribe tu correo electrónico" id="email"/>
                        </div>
                        <button type="button" class="loginButton blueButton letterspacing2 font14" id="recoveryPassword-btn">ACEPTAR</button>
                        <button type="button" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);" id="cancelPasswordRecovery-btn">CANCELAR</button>

                    </div>
                </form>
            </div>
            <div class="loginShadow"></div>
        </div>
    </body>
</html>
