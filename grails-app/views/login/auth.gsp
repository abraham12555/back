<g:set var='securityConfig' value='${applicationContext.springSecurityService.securityConfig}'/>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Login</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
        <meta charset="utf-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,700italic,500,500italic" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet" type="text/css">
        <g:external dir="css" file="bootstrap.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="formulario.css" title="text/css" rel="stylesheet" />    
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    </head>
    <body class="loginBody">
        <div class="loginContainer">
            <div class="loginlogoBox center">
                <img class="loginlogo" src="${resource(dir:'images', file:'kosmos-logo.png')}" title="Logo" alt="Logo">
            </div>
            <div class="loginBox">
                <g:if test='${flash.message}'>
                    <br/>
                    <div class="alert alert-danger">
                        El usuario y/o la contraseña no son válidos.
                    </div>
                </g:if>
                <form class="loginForm gray font14" action="/j_spring_security_check" method="post" name="loginForm" id="loginForm" autocomplete="off">
                    <p class="loginTitle gray textUpper letterspacing1 center">NÚMERO DE CLIENTE</p>
                    <div class="formContainer">
                        <label class="formLabel letterspacing1 emailLabel">CORREO</label>
                        <input class="inputs emailInput lightGray letterspacing1 font14" type="text" placeholder="Escribe tu correo" name="${securityConfig.apf.usernameParameter}" id="username"/>
                        <label class="formLabel letterspacing1 passwordLabel">CONTRASEÑA</label>
                        <input class="inputs passwordInput lightGray letterspacing1 font14" type="password" placeholder="Escribe tu contraseña" name="${securityConfig.apf.passwordParameter}" id="password"/>
                        <button type="submit" class="loginButton blueButton letterspacing2 font14" value="${message(code: 'springSecurity.login.button')}">INGRESAR</button>
                        <p class="letterspacing1 lightGray font14 center">¿Olvidaste tu cuenta? <a href="#" class="gray">Da click acá.</a></p>
                    </div>
                </form>
            </div>
            <div class="loginShadow"></div>
        </div>
    </body>
</html>