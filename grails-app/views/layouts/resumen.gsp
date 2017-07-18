<!DOCTYPE html>
<html lang="en">
    <head>
        <title>${(configuracion?.htmlTitle ? (configuracion?.htmlTitle + " - " )  : "" )}Solicitud</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
        <meta charset="utf-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
        <script src = "https://plus.google.com/js/client:platform.js" async defer></script>
        <script src = "https://apis.google.com/js/api:client.js"></script>
        <g:external dir="css" file="bootstrap.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="formulario.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="progressbar.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="jquery-ui.min.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="sweetalert.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="jquery.modal.min.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="animate.min.css" title="text/css" rel="stylesheet" />
        <g:if test="${configuracion?.rutaCss}">
            <link rel="stylesheet" href="${configuracion?.rutaCss}">
        </g:if>
        <g:external dir="js" file="jquery-3.0.0.min.js" />
        <g:urlContextAware value="/" var="urlContextAware"/>
        <script type="text/javascript">
            $.contextAwarePathJS = "${urlContextAware}";
        </script>
        <g:external dir="js" file="jquery-ui.min.js" />
        <g:external dir="js" file="typeahead.js" />
        <g:external dir="js" file="jquery.validate.min.js" />
        <g:external dir="js" file="sweetalert.min.js" />
        <g:external dir="js" file="jquery.modal.min.js" />
        <g:external dir="js" file="photobooth_min.js" />
        <g:external dir="js" file="dropzone.js" />
        <g:external dir="js" file="formularioTest.js" />
        <g:external dir="js" file="jquery.countdown.min.js" />
        <g:external dir="js" file="curp.js" />
        <g:external dir="js" file="numbersOnly.js" />
        <g:external dir="js" file="google.js" />
        <g:external dir="js" file="facebook.js" />
        <g:external dir="js" file="jasny-bootstrap.min.js" />
        <g:external dir="js" file="jquery-ui-touch-punch.js" />
        <g:external dir="js" file="googleanalytics.js" />
    </head>
    <body>
        <header class="topHeader">
            <div class="formularioHeader clearFix" style="border-top: solid 5px ${configuracion?.colorBordeSuperior}; background-color: ${configuracion?.colorEncabezado};">
                <div class="container clearFix">
                    <center><img class="logo desktop" src="${resource(dir:'images', file: configuracion?.rutaLogotipo)}" alt="Logo" title="Logo" /></center>
                    <img class="logoMobile floatLeft mobile tablet" src="${resource(dir:'images', file:'kosmos-mobile-logo.png')}" alt="Logo" title="Logo" />
                    <div class="floatingHeader floatRight clearFix">
                    </div>
                </div>
            </div>
        </header>
        <g:layoutBody/>
    </body>
</html>
