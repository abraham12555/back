<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Cotizador</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
        <meta charset="utf-8">

        <g:external dir="css" file="font-awesome.min.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="bootstrap.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="formulario.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="jquery-ui.css" />
        <g:external dir="css" file="sweetalert.css" title="text/css" rel="stylesheet" />
        <g:external dir="js" file="sweetalert.min.js" />
        <g:external dir="js" file="jquery-3.0.0.min.js" />
        <g:external dir="js" file="cotizador.js" />
        <g:external dir="js" file="jquery-ui.js" />

        <g:layoutHead/>
    </head>
    <body class="marginBottom150">
        <section class="containerWrapper height100P" style="border-top: solid 5px ${configuracion?.colorBordeSuperior}; background-color: ${configuracion?.colorFondo};">
            <div class="cotizador-header"  style="background-color: ${configuracion?.colorEncabezado};">
                <div class="container paddingTop20 paddingBottom20">
                    <img src="${resource(dir:'images', file: configuracion?.rutaLogotipo)}" alt="Logo">
                </div>
            </div>
            <g:layoutBody/>
        </section>
    </body>
</html>