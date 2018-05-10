<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <title>Entidad Financiera</title>
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
    </head>
    <body class="loginBody">
        <div class="loginContainer">
            <div class="loginBox">
                <g:if test='${message}'>
                    <br/>
                    <div class="alert alert-danger center">
                        ${message}
                    </div>
                </g:if>
                <div class="loginForm gray font14">
                    <p class="loginTitle darkBluetitle textUpper letterspacing1 center fontWeight700">Bienvenido</p>
                    <div class="formContainer">
                        <p class="marginBottom20">Selecciona la entidad financiera con la que deseas trabajar</p>
                        <div id="entidades-div">
                            <table class="applicationContainers workspace_table">
                                <tbody>
                                    <g:each in="${entidadesList}" var="item">
                                        <tr>
                                            <td>
                                                <a href="${createLink(controller: 'login', action: 'customizeSelection', params: [id : item.id, t: t])}" class="gray">${item.nombre}</a>
                                            </td>
                                        </tr>
                                    </g:each>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="loginShadow"></div>
        </div>
    </body>
</html>
