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
    <body class="marginBottom150" style="border-top: solid 5px ${configuracion?.colorBordeSuperior}; background-image: url('${resource(dir:'images', file: 'cajaLibertad/2modificado2.png')}'); background-repeat: no-repeat; background-position: center 130px; background-size: cover;">
        <section class="containerWrapper height100P" >
            <div class="cotizador-header"  style="background-color: ${configuracion?.colorEncabezado};">
                <div class="container paddingTop20 paddingBottom20">
                    <img style="margin-left: 150px;"src="${resource(dir:'images', file: configuracion?.rutaLogotipo)}" alt="Logo">
                    <div style="float:left; margin-right:21px; margin-left: 21px;" class="block">
                            <table cellspacing="0">
                                <tbody>
                                    <tr>
                                        <td class='font14 fontWhite fontBolder'colspan="3" style=" padding-bottom: 10px;" >¿Ya cuentas con una solicitud registrada? ingresa tu folio y continua</td>
                                    </tr>
                                    <tr class="floatRight">
                                        <td class='fontWhite font15 paddingFolio'>Folio</td>
                                        <td><input type="text" name="folioFormulario" id="folioFormulario" style="max-height: 30px; max-width: 150px; font-size: 1.0em;" maxlength="7" onkeypress="return event.keyCode != 13;"/></td>
                                        <td><input type="button" value="Continuar Solicitud" onclick="compareFolio('formulario');" style="padding-bottom: 5px;" class=" floatRight block font15 pointer letterspacing1 blueButton2 blue-shadow2 padding10 width400 center autoMargin" id="btnContSol" /></td>
                                    </tr>
                                    <tr id="filaErrorFolioFormulario" class="hide"><td colspan="3" class="font14 textAlignCenter "><a id='folioFormularioError' style="color:#ffcb00; float:right;"></a></td></tr>
                                    <tr>
                                        <td id="leyendaFolioSms"colspan="3" style='padding-top: 10px;'class="font14  textAlignCenter"><a  class="pointer floatRight" onclick="abrirModalFolioFormulario();" style="color:#b4b8d4;">¿No recuerdas tu folio ? da clic aquí te lo enviaremos por sms</a></td>
                                    </tr>
                                </tbody>
                            </table>
                    </div>-
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
