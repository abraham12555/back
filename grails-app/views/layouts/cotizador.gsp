<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kosmos - Cotizador</title>
        <title>Cotizador</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
        <meta charset="utf-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <g:external dir="css" file="bootstrap.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="formulario.css" title="text/css" rel="stylesheet" />
        <g:external dir="js" file="jquery-3.0.0.min.js" />
        <g:layoutHead/>
    </head>
    <body >
        <section class="containerWrapper height100P">
            <div class="cotizador-header">
                <div class="container paddingTop20 paddingBottom20">
                    <img src="${resource(dir:'images', file:'kosmos-logo.png')}" alt="Kosmos Logo">
                </div>
            </div>
            <g:layoutBody/>
        </section>
    </body>
</html>
