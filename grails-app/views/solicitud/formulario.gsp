<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="formulario"/>
        <title>Kosmos - Formulario</title>
    </head>
    <body>
        <input type="hidden" id="pasoInicial" value="${paso}">
        <div id="pasoActual">
            <g:render template="paso${paso}"/>
        </div>
    </body>
</html>