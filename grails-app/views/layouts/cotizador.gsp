<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>${(configuracion?.htmlTitle ? (configuracion?.htmlTitle + " - " )  : "" )}Cotizador</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
        <meta charset="utf-8">
        <meta property="og:title" content="${(configuracion?.htmlTitle ? (configuracion?.htmlTitle + " - " )  : "" )}Cotizador" />
        <meta property="og:description" content="¡Solicita tu Crédito Libertad y obtén tu Pre-Autorización de Crédito en menos de 5 minutos!" /> 
        <meta property="og:image" content="${assetPath(src: 'favicon.ico')}" /> 
        <meta property="og:url" content="http://www.micreditolibertad.com/" /> 
        <meta property="og:type" content="website" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
        <link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
        <!--<g:external dir="css" file="font-awesome.min.css" title="text/css" rel="stylesheet" />-->
        <g:external dir="css" file="bootstrap.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="formulario.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="registroDeCliente.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="jquery-ui-kosmos.css" />
        <g:external dir="css" file="sweetalert.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="jquery.mloading.css" title="text/css" rel="stylesheet" />
        <g:if test="${configuracion?.rutaCss}">
            <link rel="stylesheet" href="${configuracion?.rutaCss}">
        </g:if>
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
    <g:urlContextAware value="/" var="urlContextAware"/>
    <g:external dir="js" file="sweetalert.min.js" />
    <g:external dir="js" file="jquery-3.0.0.min.js" />
    <script type="text/javascript">
            $.contextAwarePathJS = "${urlContextAware}";
    </script>
    <g:external dir="js" file="cotizador.js" />
    <g:external dir="js" file="validaciones.js" />
    <g:external dir="js" file="registro.js" />
    <g:external dir="js" file="jquery-ui-kosmos.js" />
    <g:external dir="js" file="jquery-ui-touch-punch.js" />
    <g:external dir="js" file="jasny-bootstrap.min.js" />
    <g:external dir="js" file="jquery.mloading.js" />
    <g:external dir="js" file="googleanalytics.js" />
</html>
