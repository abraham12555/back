<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta name="layout" content="dashboard"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <g:external dir="css" file="prettyPhoto.css" />
        <g:external dir="js" file="jquery.prettyPhoto.js" />
        <script src="https://maps.google.com.mx/maps/api/js?language=es&key=AIzaSyA1CPLFAC5mFixfxBFB5OYnirzijeTopRI" async defer></script>
        <g:external dir="js" file="maps.js" />
        <style>
            .block{
            display: block;
            }

            .width928{
            width: 928px;
            }

            .width94{
            width: 94px;
            }

            .marginLeft8{
            margin-left: 8px;
            }

            .slash{
            font-size: 30px;
            }

            a.back-to-top {
            display: none;
            width: 60px;
            height: 60px;
            text-indent: -9999px;
            position: fixed;
            z-index: 999;
            right: 20px;
            bottom: 20px;
            background: #2d91f6 url("../images/up-arrow.png") no-repeat center 43%;
            box-shadow: 0 0 7px 0 #298df5;
            -webkit-border-radius: 30px;
            -moz-border-radius: 30px;
            border-radius: 30px;
            cursor: pointer;
            }
        </style>
        <title>Sample title</title>
    </head>
    <body>
        <input type="hidden" id="opcionMenu" value="5">
        <input type="hidden" id="tipoDeDocumento">
        <section class="container marginBottom28">
            <div class="width990 solicitudBox autoMargin">
                <div class="">
                    <ul class="clearFix">
                        <li class="floatLeft paddingLeft5 paddingRight5">
                            <a title="VERIFICACION / DETALLE" class="displayInline font24 fontWeight700 darkBluetitle paddingTop20 paddingBottom15 paddingLeft20 paddingRight20">VERIFICACION / DETALLE</a>
                        </li>
                        <li id="agendaButton" class="opcVerificacion floatLeft lightGrayBG paddingLeft5 paddingRight5">
                            <a onclick="mostrarApartado('opcVerificacion','verificacionTab','agenda');" title="Ver agenda" class="displayInline font20 fontWeight500 darkBluetitle padding20 pointer">Ver agenda</a>
                        </li>
                        <li id="mapaButton" class="opcVerificacion floatLeft paddingLeft5 paddingRight5">
                            <a onclick="mostrarApartado('opcVerificacion','verificacionTab','mapa'); cargarDireccioneEnMapa();" title="Ver mapa" class="displayInline font20 fontWeight500 darkBluetitle padding20 pointer">Ver mapa</a>
                        </li>
                    </ul>
                </div>
                <div class="lightGrayBG">
                    <ul class="clearFix paddingLeft30">
                        <li class="floatLeft">
                            <a href="#" title="Reportar contratiempo" class="displayInline font14 fontweight300 colorRed paddingTop10 paddingBottom10 paddingLeft20 paddingRight20">Reportar contratiempo</a>
                        </li>
                        <li class="floatLeft paddingTop10 paddingBottom10 paddingLeft20 paddingRight20">
                            <a href="#" title="Reportar imprevisto" class="displayInline colorRed font14 fontweight300">Reportar imprevisto</a>
                        </li>
                    </ul>
                </div>
            </div>
        </section>
        <div class="verificacionTab" id="agenda">
            <g:form method="POST" action="registrarVisitaOcular">
                <input type="hidden" id="solicitudId" name="solicitudId" value="${respuesta.productoSolicitud?.solicitud?.id}">
                <section class="container">
                    <div class="wrapper width990 autoMargin solicitudWhiteBox clearFix borderGrayBottom ">
                        <div class="clearFix">
                            <div class="floatLeft">
                                <table class="borderGrayBottom">
                                    <thead>
                                        <tr>
                                            <th class="borderGrayRight"><p class="font12 fontWeight300 gray"><strong>FOLIO</strong></p></th>
                                            <th class="borderGrayRight"><p class="font12 fontWeight300 gray"><strong>PROSPECTO</strong></p></th>
                                            <th class="borderGrayRight"><p class="font12 fontWeight300 gray"><strong>DIRECCIÓN</strong></p></th>
                                            <th class="borderGrayRight"><p class="font12 fontWeight300 gray"><strong>HORARIO</strong></p></th>
                                            <th class="borderGrayRight"><p class="font12 fontWeight300 gray"><strong>ESTATUS</strong></p></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td class="borderGrayRight borderGrayBottom padding10">
                                                <p class="font14 fontWeight400 tableDescriptionColor">${respuesta.productoSolicitud?.solicitud?.folio}</p>
                                            </td>
                                            <td class="borderGrayRight borderGrayBottom padding10">
                                                <p class="font14 fontWeight400 tableDescriptionColor">${respuesta.productoSolicitud?.solicitud?.cliente?.toString()}</p>
                                            </td>
                                            <td class="clearFix borderGrayRight borderGrayBottom padding10">
                                                <div class="floatLeft">
                                                    <p class="font14 fontWeight400 tableDescriptionColor">${respuesta.direccion}</p>
                                                </div>
                                            </td>
                                            <td class="borderGrayRight borderGrayBottom padding10">
                                                <p class="font14 fontWeight400 tableDescriptionColor">9:40 AM</p>
                                            </td>
                                            <td class="borderGrayRight borderGrayBottom padding10">
                                                <div class="floatLeft">
                                                    <p class="font14 fontWeight400 tableDescriptionColor">A TIEMPO</p>
                                                </div>
                                                <div class="floatLeft">
                                                    <i class="fa fa-angle-down" aria-hidden="true"></i>
                                                </div>
                                            </td>
                                            <td rowspan="4" class="clearFix borderGrayRight center padding10">
                                                <a hreftitle="LLAMAR" class="block colorGreen colorWhite radius4 paddingTop12 paddingBottom12 paddingLeft5 paddingRight5 marginBottom15 pointer" style='width: 155px;'>LLAMAR</a>
                                                <a id='iniciarVisitaBtn' onclick='iniciarVisita();'>
                                                    <p class="blueButton radius4 paddingTop12 paddingBottom12 paddingLeft5 paddingRight5 pointer">COMENZAR VISITA</p>
                                                </a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th colspan="2" class="borderGrayRight"><p class="font12 fontWeight300 gray"><strong>MONTO</strong></p></th>
                                            <th class="borderGrayRight"><p class="font12 fontWeight300 gray"><strong>PRODUCTO</strong></p></th>
                                            <th colspan="2" class="borderGrayRight"><p class="font12 fontWeight300 gray"><strong>SERVICIO</strong></p></th>
                                        </tr>
                                        <tr>
                                            <td colspan="2" class=" borderGrayRight padding10">
                                                <p class="font14 fontWeight400 tableDescriptionColor"><g:formatNumber number="${respuesta.productoSolicitud?.montoDelCredito}" format="\044###,###,###.##"/></p>
                                            </td>
                                            <td class=" borderGrayRight padding10">
                                                <p class="font14 fontWeight400 tableDescriptionColor">${respuesta.productoSolicitud?.producto?.nombreDelProducto}</p>
                                            </td>
                                            <td colspan="2" class="borderGrayRight padding10">
                                                <p class="font14 fontWeight400 tableDescriptionColor">${respuesta.productoSolicitud?.producto?.tipoDeProducto}</p>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </section>
                <div id="verificacionFormulario" style='display: none;'>
                    <div id="paso1Verificacion">
                        <section class="container clearFix width990">
                            <div class="col12" id="divDropzone">
                                <div class="marginTop20 marginLeft20 marginBottom20">
                                    <h1 class="font18 letterspacing2 darkBluetitle fontWeight600">1. VERIFICACIÓN DE DOMICILIO.</h1>
                                </div>
                                <div class="width990 autoMargin solicitudWhiteBox">
                                    <div class="">
                                        <p class="paddingTop15 paddingLeft20 letterspacing1 fontWeight500 font14 gray paddingBottom12">TOMAR 3 FOTOS DE LA FACHADA DE LA VIVIENDA</p>
                                    </div>
                                    <div class="clearFix">
                                        <div id="fachadaFrenteDiv" class="datoRequerido col1fifth col4-tab col12-mob paddingBottom18 floatLeft">
                                            <div  data-tipo-de-imagen="fachadaFrente" class="width170 cameraBox radius2 boxMargins pointer">
                                                <div class="autoMargin block width33">
                                                    <img class="width33 paddingTop15 paddingBottom12" src="${resource(dir:'images', file:'1467236565_camera.png')}" alt="camera" />
                                                </div>
                                                <p class="font12 gray center paddingBottom10">FACHADA - FRENTE</p>
                                            </div>
                                        </div>
                                        <div id="fachadaIzquierdaDiv" class="datoRequerido col1fifth  col4-tab col12-mob paddingBottom18 floatLeft">
                                            <div  data-tipo-de-imagen="fachadaIzquierda" class="width170 cameraBox radius2 boxMargins pointer">
                                                <div class="autoMargin block width33">
                                                    <img class="width33 paddingTop15 paddingBottom12" src="${resource(dir:'images', file:'1467236565_camera.png')}" alt="camera" />
                                                </div>
                                                <p class="font12 gray center paddingBottom10">FACHADA - IZQUIERDA</p>
                                            </div>
                                        </div>
                                        <div id="fachadaDerechaDiv" class="datoRequerido col1fifth  col4-tab col12-mob paddingBottom18 floatLeft">
                                            <div  data-tipo-de-imagen="fachadaDerecha" class="width170 cameraBox radius2 boxMargins pointer">
                                                <div class="autoMargin block width33">
                                                    <img class="width33 paddingTop15 paddingBottom12" src="${resource(dir:'images', file:'1467236565_camera.png')}" alt="camera" />
                                                </div>
                                                <p class="font12 gray center paddingBottom10">FACHADA - DERECHA</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>

                        <section class="container clearFix marginTop20">
                            <div class="width990 autoMargin solicitudWhiteBox clearFix paddingBottom20">
                                <div class="col7 floatLeft clearFix">
                                    <div class="col8 floatLeft">
                                        <div class="marginLeft20 marginTop20 solicitudWhiteBox">
                                            <div class="navyBg radius2">
                                                <p class="marginLeft35 colorWhite paddingTop15 paddingBottom10 font18 fontWeight600 letterspacing2">DOMICILIO</p>
                                            </div>
                                            <div class="whiteBg ">
                                                <p class="paddingLeft35 paddingTop10 font16 fontWeight500 gray">DIRECCIÓN</p>
                                                <p class="paddingLeft35 font18 fontWeight500 darkBluetitle">${respuesta.direccion?.calle}</p>
                                            </div>
                                            <div class="lightGrayBG  marginTop5">
                                                <p class=" paddingLeft35 paddingTop10 font16 fontWeight500 gray">NÚMERO EXTERIOR</p>
                                                <p class=" paddingLeft35 font18 fontWeight500 darkBluetitle">${respuesta.direccion?.numeroExterior}</p>
                                            </div>
                                            <div class=" whiteBg">
                                                <p class="paddingLeft35 paddingTop10 font16 fontWeight500 gray">NÚMERO INTERIOR</p>
                                                <p class="paddingLeft35 font18 fontWeight500 darkBluetitle">${respuesta.direccion?.numeroInterior}</p>
                                            </div>
                                            <div class="lightGrayBG  marginTop5">
                                                <p class=" paddingLeft35 paddingTop10 font16 fontWeight500 gray">COLONIA</p>
                                                <p class=" paddingLeft35 font18 fontWeight500 darkBluetitle">${respuesta.direccion?.colonia}</p>
                                            </div>
                                            <div class=" whiteBg">
                                                <p class="paddingLeft35 paddingTop10 font16 fontWeight500 gray">DELEGACIÓN O MUNICIPIO</p>
                                                <p class="paddingLeft35 font18 fontWeight500 darkBluetitle">${respuesta.direccion?.codigoPostal?.municipio}</p>
                                            </div>
                                            <div class="lightGrayBG  marginTop5">
                                                <p class=" paddingLeft35 paddingTop10 font16 fontWeight500 gray">CÓDIGO POSTAL</p>
                                                <p class=" paddingLeft35 font18 fontWeight500 darkBluetitle">${respuesta.direccion?.codigoPostal}</p>
                                            </div>
                                            <div class=" whiteBg paddingBottom15">
                                                <p class="paddingLeft35 paddingTop10 font16 fontWeight500 gray">ESTADO</p>
                                                <p class="paddingLeft35 font18 fontWeight500 darkBluetitle">${respuesta.direccion?.codigoPostal?.municipio?.estado}</p>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col4 floatLeft">
                                        <div class="paddingTop20">
                                            <div class="gradientColor radius2">
                                                <p class="colorWhite paddingTop15 center paddingBottom10 font18 fontWeight600 letterspacing2">VERIFICACIÓN</p>
                                            </div>
                                            <div class="lightGrayBG">
                                                <input type="hidden" name="resultadoVerificacionDireccion" id="resultadoVerificacionDireccion">
                                                <div class="clearFix">
                                                    <div id='direccionVerificar' class="datoRequerido paddingTop5 paddingLeft15 clearFix paddingTop10">
                                                        <div id='direccionSi' class="checkVerificacion width70 whiteBox radius1.6 floatLeft">
                                                            <a onclick="verificarDatos('direccion','Si');" title="SI" class="block font14 gray center paddingTop15 paddingBottom15">SI</a>
                                                        </div>
                                                        <div id='direccionNo' class="checkVerificacion width70 whiteBox radius1.6 floatLeft">
                                                            <a onclick="verificarDatos('direccion','No');" title="NO" class="block font14 gray center paddingTop15 paddingBottom15">NO</a>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="clearFix">
                                                    <div id='numExtVerificar' class="datoRequerido paddingTop5 paddingLeft15 clearFix paddingTop10">
                                                        <div id='numExtSi' class="checkVerificacion width70 whiteBox radius1.6 floatLeft">
                                                            <a onclick="verificarDatos('numExt','Si');" title="SI" class="block font14 gray center paddingTop15 paddingBottom15">SI</a>
                                                        </div>
                                                        <div id='numExtNo' class="checkVerificacion width70 whiteBox radius1.6 floatLeft">
                                                            <a onclick="verificarDatos('numExt','No');" title="NO" class="block font14 gray center paddingTop15 paddingBottom15">NO</a>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="clearFix">
                                                    <div id='numIntVerificar' class="datoRequerido paddingTop5 paddingLeft15 clearFix paddingTop10">
                                                        <div id='numIntSi' class="checkVerificacion width70 whiteBox radius1.6 floatLeft">
                                                            <a onclick="verificarDatos('numInt','Si');" title="SI" class="block font14 gray center paddingTop15 paddingBottom15">SI</a>
                                                        </div>
                                                        <div id='numIntNo' class="checkVerificacion width70 whiteBox radius1.6 floatLeft">
                                                            <a onclick="verificarDatos('numInt','No');" title="NO" class="block font14 gray center paddingTop15 paddingBottom15">NO</a>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="clearFix">
                                                    <div id='coloniaVerificar' class="datoRequerido paddingTop5 paddingLeft15 clearFix paddingTop10">
                                                        <div id='coloniaSi' class="checkVerificacion width70 whiteBox radius1.6 floatLeft">
                                                            <a onclick="verificarDatos('colonia','Si');" title="SI" class="block font14 gray center paddingTop15 paddingBottom15">SI</a>
                                                        </div>
                                                        <div id='coloniaNo' class="checkVerificacion width70 whiteBox radius1.6 floatLeft">
                                                            <a onclick="verificarDatos('colonia','No');" title="NO" class="block font14 gray center paddingTop15 paddingBottom15">NO</a>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="clearFix">
                                                    <div id='delegacionVerificar' class="datoRequerido paddingTop5 paddingLeft15 clearFix paddingTop10">
                                                        <div id='delegacionSi' class="checkVerificacion width70 whiteBox radius1.6 floatLeft">
                                                            <a onclick="verificarDatos('delegacion','Si');" title="SI" class="block font14 gray center paddingTop15 paddingBottom15">SI</a>
                                                        </div>
                                                        <div id='delegacionNo' class="checkVerificacion width70 whiteBox radius1.6 floatLeft">
                                                            <a onclick="verificarDatos('delegacion','No');" title="NO" class="block font14 gray center paddingTop15 paddingBottom15">NO</a>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="clearFix">
                                                    <div id='codigoPostalVerificar' class="datoRequerido paddingTop5 paddingLeft15 clearFix paddingTop10">
                                                        <div id='codigoPostalSi' class="checkVerificacion width70 whiteBox radius1.6 floatLeft">
                                                            <a onclick="verificarDatos('codigoPostal','Si');" title="SI" class="block font14 gray center paddingTop15 paddingBottom15">SI</a>
                                                        </div>
                                                        <div id='codigoPostalNo' class="checkVerificacion width70 whiteBox radius1.6 floatLeft">
                                                            <a onclick="verificarDatos('codigoPostal','No');" title="NO" class="block font14 gray center paddingTop15 paddingBottom15">NO</a>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="clearFix">
                                                    <div id='estadoVerificar' class="datoRequerido paddingTop5 paddingLeft15 clearFix paddingTop10">
                                                        <div id='estadoSi' class="checkVerificacion width70 whiteBox radius1.6 floatLeft">
                                                            <a onclick="verificarDatos('estado','Si');" title="SI" class="block font14 gray center paddingTop15 paddingBottom15">SI</a>
                                                        </div>
                                                        <div id='estadoNo' class="checkVerificacion width70 whiteBox radius1.6 floatLeft">
                                                            <a onclick="verificarDatos('estado','No');" title="NO" class="block font14 gray center paddingTop15 paddingBottom15">NO</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col5 floatLeft">
                                    <div class="gradientColor radius2 marginTop20">
                                        <p class="colorWhite paddingLeft25  paddingTop15 paddingBottom10 font18 fontWeight600 letterspacing2">DATOS CORRECTOS</p>
                                    </div>
                                    <div class="marginLeft10 marginRight10 paddingBottom5 paddingTop10 fullInputs">
                                        <input type="text" id="direccion" name="direccion" placeholder="Dirección" class="whiteBox block lightGray paddingTop15 paddingBottom15 font14 fontWeight500 letterspacing1 paddingLeft20" disabled>
                                    </div>
                                    <div class="lightGrayBG marginLeft10 marginRight10 paddingBottom5 paddingTop10 fullInputs">
                                        <input type="text" id="numExt" name="numExt" placeholder="Número Exterior" class="whiteBox block lightGray paddingTop15 paddingBottom15 font14 fontWeight500 letterspacing1 paddingLeft20" disabled>
                                    </div>
                                    <div class="marginLeft10 marginRight10 paddingBottom5 paddingTop10 fullInputs">
                                        <input type="text" id="numInt" name="numInt" placeholder="Número Interior" class="whiteBox block lightGray paddingTop15 paddingBottom15 font14 fontWeight500 letterspacing1 paddingLeft20" disabled>
                                    </div>
                                    <div class="lightGrayBG marginLeft10 marginRight10 paddingBottom5 paddingTop10 fullInputs">
                                        <input type="text" id="colonia" name="colonia" placeholder="Colonia" class="whiteBox block lightGray paddingTop15 paddingBottom15 font14 fontWeight500 letterspacing1 paddingLeft20" disabled>
                                    </div>
                                    <div class="marginLeft10 marginRight10 paddingBottom5 paddingTop10 fullInputs">
                                        <input type="text" id="delegacion" name="delegacion" placeholder="Delegación/Municipio" class="whiteBox block lightGray paddingTop15 paddingBottom15 font14 fontWeight500 letterspacing1 paddingLeft20" disabled>
                                    </div>
                                    <div class="lightGrayBG marginLeft10 marginRight10 paddingBottom5 paddingTop10 fullInputs">
                                        <input type="text" id="codigoPostal" name="codigoPostal" placeholder="Código Postal" class="whiteBox block lightGray paddingTop15 paddingBottom15 font14 fontWeight500 letterspacing1 paddingLeft20" disabled>
                                    </div>
                                    <div class="marginLeft10 marginRight10 paddingBottom5 paddingTop10 fullInputs">
                                        <input type="text" id="estado" name="estado" placeholder="Estado" class="whiteBox block lightGray paddingTop15 paddingBottom15 font14 fontWeight500 letterspacing1 paddingLeft20" disabled>
                                    </div>
                                </div>
                            </div>
                        </section>

                        <section class="container clearFix marginTop20">
                            <div class="width990 autoMargin solicitudWhiteBox paddingBottom25 clearFix">
                                <p class="lightGray paddingLeft15 font14 paddingTop20 paddingBottom20">ACERCATE A LA ENTRADA Y PRESENTATE CON <span class="colorblue fontWeight500 font14">${respuesta.productoSolicitud?.solicitud?.cliente?.toString()?.toUpperCase()}</span></p>
                                <div id="mostrarGafete" class="col6 floatLeft">
                                    <div class="paddingAside15">
                                        <p class="font14 fontWeight500 lightGray letterspacing1 paddingBottom15">1. MUESTRA TU GAFÉTE DE LA EMPRESA</p>
                                        <div id="mostrarGafeteListo" class="datoRequerido checkVerificacion col10 col12-mob whiteBox">
                                            <a onclick="confirmarAccion('mostrarGafete','Listo');" title="LISTO" class=" block paddingTop15 paddingBottom15 center lightGray font14 letterspacing1 fontWeight500">LISTO</a>
                                        </div>
                                    </div>
                                </div>
                                <div id="confirmarCita" class="col6 floatLeft">
                                    <div class="paddingAside15">
                                        <p class="font14 fontWeight500 lightGray letterspacing1 paddingBottom15">2. MENCIONA TU CITA DE HOY PARA LAS 10:00 AM</p>
                                        <div id="confirmarCitaListo" class="datoRequerido checkVerificacion col10 col12-mob whiteBox">
                                            <a <a onclick="confirmarAccion('confirmarCita','Listo');" title="LISTO" class="block paddingTop15 paddingBottom15 center lightGray font14 letterspacing1 fontWeight500">LISTO</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                    </div>
                    <div id="paso2Verificacion" style="display:none;">
                        <section class="container marginTop10 marginBottom10">
                            <div class="clearFix width990 autoMargin">
                                <h3 class="floatLeft darkBluetitle marginLeft20 marginTop10">2. VERIFICACIÓN DE IDENTIDAD / CREDENCIAL DE ELECTOR</h3>
                            </div>
                        </section>
                        <section class="container clearFix">
                            <div class="width990 autoMargin solicitudWhiteBox clearFix">
                                <div class="col6 col12-mob floatLeft">
                                    <div class="padding15">
                                        <div class="">
                                            <div class="grayBG-visita radius10 height260 autoMargin clearFix">
                                                <div class="profileBG floatLeft marginLeft17 marginTop65">
                                                    <img class="imgResponsive" src="${resource(dir:'images', file:'user.png')}" alt="user">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="marginTop19 marginBottom14">
                                            <div class="grayBG-visita radius10 height260 autoMargin">
                                                <div class="paddingTop150">
                                                    <div class="height47 colorGrayBG"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col6 col12-mob floatLeft">
                                    <div class="paddingTop20 marginLeft30">
                                        <p class="gray font14 fontWeight500 letterspacing1">COTEJO DE ID EXITOSO</p>
                                    </div>
                                    <div class="clearFix marginTop65 borderGrayBottom paddingBottom75 marginRight20">
                                        <div class="col6 paddingBottom18 floatLeft">
                                            <div id="identificacionFrenteDiv" class="datoRequerido paddingAside15">
                                                <div data-tipo-de-imagen="identificacionFrente" class="width170 autoCenter block cameraBox radius2 pointer">
                                                    <div class="autoMargin block width33">
                                                        <img class="width33 paddingTop15 paddingBottom12" src="${resource(dir:'images', file:'1467236565_camera.png')}" alt="camera" />
                                                    </div>
                                                    <p class="font12 lightGray center paddingBottom10">FRENTE</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col6 paddingBottom18 floatLeft">
                                            <div id="identificacionVueltaDiv" class="datoRequerido paddingAside15">
                                                <div data-tipo-de-imagen="identificacionVuelta" class="width170 autoCenter block cameraBox radius2 pointer">
                                                    <div class="autoMargin block width33">
                                                        <img class="width33 paddingTop15 paddingBottom12" src="${resource(dir:'images', file:'1467236565_camera.png')}" alt="camera" />
                                                    </div>
                                                    <p class="font12 lightGray center paddingBottom10">VUELTA</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="marginTop30 paddingBottom20">
                                        <p class="font14 fontWeight500 letterspacing1 lightGray">COTEJO NO EXITOSO</p>
                                        <div id="cotejoIdentificacion" class="button marginTop20">
                                            <input type="hidden" id="cotejoIdentificacionResultado" name="cotejoIdentificacionResultado" />
                                            <div id="cotejoIdentificacionNoCuenta" class="checkVerificacion whiteBox width350 autoCenter block">
                                                <a onclick="confirmarAccion('cotejoIdentificacion','NoCuenta');" title="NO CUENTA CON EL DOCUMENTO" class="block font14 letterspacing1 fontWeight500 lightGray paddingTop15 paddingBottom15 center">NO CUENTA CON EL DOCUMENTO</a>
                                            </div>
                                            <div id="cotejoIdentificacionAlterado" class="checkVerificacion whiteBox width350 marginTop10 autoCenter block">
                                                <a onclick="confirmarAccion('cotejoIdentificacion','Alterado');" title="DOCUMENTO ALTERADO" class="block font14 letterspacing1 fontWeight500 lightGray paddingTop15 paddingBottom15 center">DOCUMENTO ALTERADO</a>
                                            </div>
                                            <div id="cotejoIdentificacionDiferente" class="checkVerificacion whiteBox width350 marginTop10 autoCenter block">
                                                <a onclick="confirmarAccion('cotejoIdentificacion','Diferente');" title="NO ES EL MISMO DOCUMENTO" class="block font14 letterspacing1 fontWeight500 lightGray paddingTop15 paddingBottom15 center">NO ES EL MISMO DOCUMENTO</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                    </div>

                    <div id="paso3Verificacion" style="display:none;">
                        <section class="container marginTop10 marginBottom10">
                            <div class="clearFix width990 autoMargin">
                                <h3 class="floatLeft darkBluetitle marginLeft20 marginTop10">3. VERIFICACIÓN DE COMPROBANTE DE DOMICILIO / CFE</h3>
                            </div>
                        </section>
                        <section class="container clearFix">
                            <div class="width990 autoMargin solicitudWhiteBox clearFix paddingBottom20">
                                <div class="col6 col12-mob  floatLeft">
                                    <div class="padding15">
                                        <div class="grayBG-visita radius10 height535 autoMargin clearFix">
                                        </div>
                                    </div>
                                </div>
                                <div class="col6 col12-mob  floatLeft">
                                    <div class="paddingTop20 marginLeft30">
                                        <p class="lightGray font14 fontWeight500 letterspacing1">COTEJO DE ID EXITOSO</p>
                                    </div>
                                    <div class="clearFix marginTop65 borderGrayBottom paddingBottom75 marginRight20">
                                        <div class="col6 paddingBottom18 floatLeft">
                                            <div id="comprobanteFrenteDiv" class="datoRequerido paddingAside15">
                                                <div data-tipo-de-imagen="comprobanteFrente" class="width170 autoCenter block cameraBox radius2 pointer">
                                                    <div class="autoMargin block width33">
                                                        <img class="width33 paddingTop15 paddingBottom12" src="${resource(dir:'images', file:'1467236565_camera.png')}" alt="camera" />
                                                    </div>
                                                    <p class="font12 lightGray center paddingBottom10">FRENTE</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col6 paddingBottom18 floatLeft">
                                            <div id="comprobanteVueltaDiv" class="datoRequerido paddingAside15">
                                                <div data-tipo-de-imagen="comprobanteVuelta" class="width170 autoCenter block cameraBox radius2 pointer">
                                                    <div class="autoMargin block width33">
                                                        <img class="width33 paddingTop15 paddingBottom12" src="${resource(dir:'images', file:'1467236565_camera.png')}" alt="camera" />
                                                    </div>
                                                    <p class="font12 lightGray center paddingBottom10">VUELTA</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="marginTop30">
                                        <p class="font14 fontWeight500 letterspacing1 lightGray">COTEJO NO EXITOSO</p>
                                        <div id="cotejoComprobante" class="button marginTop20">
                                            <input type="hidden" id="cotejoComprobanteResultado" name="cotejoComprobanteResultado" />
                                            <div id="cotejoComprobanteNoCuenta" class="checkVerificacion whiteBox width350 autoCenter block">
                                                <a onclick="confirmarAccion('cotejoComprobante','NoCuenta');" id="cotejoComprobanteNoCuenta" title="NO CUENTA CON EL DOCUMENTO" class="block font14 letterspacing1 fontWeight500 lightGray paddingTop15 paddingBottom15 center">NO CUENTA CON EL DOCUMENTO</a>
                                            </div>
                                            <div id="cotejoComprobanteAlterado" class="checkVerificacion whiteBox width350 marginTop10 autoCenter block">
                                                <a onclick="confirmarAccion('cotejoComprobante','Alterado');" id="cotejoComprobanteAlterado" title="DOCUMENTO ALTERADO" class="block font14 letterspacing1 fontWeight500 lightGray paddingTop15 paddingBottom15 center">DOCUMENTO ALTERADO</a>
                                            </div>
                                            <div id="cotejoComprobanteDiferente" class="checkVerificacion whiteBox width350 marginTop10 autoCenter block">
                                                <a onclick="confirmarAccion('cotejoComprobante','Diferente');" id="cotejoComprobanteDiferente" title="NO ES EL MISMO DOCUMENTO" class="block font14 letterspacing1 fontWeight500 lightGray paddingTop15 paddingBottom15 center">NO ES EL MISMO DOCUMENTO</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                    </div>

                    <div id="paso4Verificacion" style="display:none;">
                        <section class="container width990">
                            <div class="clearFix autoMargin padding20">
                                <h3 class="floatLeft darkBluetitle">4. PREGUNTAS ESPECIFICAS PARA EL PROSPECTO</h3>
                            </div>
                        </section>

                        <section class="container width990">
                            <div class="wrapper autoMargin solicitudWhiteBox fullInputs clearFix padding20">
                                <g:each var='pregunta' in='${preguntas}' status="i">
                                    <div>
                                        <label for="pregunta${i}" class="block font14 gray marginTop15 marginBottom10 ">${pregunta.pregunta.toUpperCase()}</label>
                                        <div>
                                        </div>
                                        <input type="text" id="pregunta${i}}" name="pregunta${pregunta.id}" placeholder="Respuesta" class="datoRequerido pregunta whiteBox autoCenter block">
                                    </div>
                                </g:each>
                            </div>
                        </section>

                        <section class="container clearFix marginTop30">
                            <div class="width990 autoMargin solicitudWhiteBox clearFix paddingBottom20">
                                <div class="paddingLeft30">
                                    <p class="gray font14 fontWeight500 letterspacing1 paddingTop20 paddingBottom15">TOMAR 3 FOTOS DEL INTERIOR DE LA VIVIENDA</p>
                                    <div class="paddingTop30 clearFix">
                                        <div id="interiorUnoDiv" class="datoRequerido col1fifth  col4-tab col12-mob paddingBottom18 floatLeft">
                                            <div data-tipo-de-imagen="interiorUno" class="width170 cameraBox radius2 floatLeft marginRight50 marginBottom20 pointer">
                                                <div class="autoMargin block width33">
                                                    <img class="width33 paddingTop15 paddingBottom12" src="${resource(dir:'images', file:'1467236565_camera.png')}" alt="camera" />
                                                </div>
                                                <p class="font12 lightGray center paddingBottom10">INTERIOR 1</p>
                                            </div>
                                        </div>
                                        <div id="interiorDosDiv" class="datoRequerido col1fifth  col4-tab col12-mob paddingBottom18 floatLeft">
                                            <div data-tipo-de-imagen="interiorDos" class="width170 cameraBox radius2 floatLeft marginRight50 marginBottom20 pointer">
                                                <div class="autoMargin block width33">
                                                    <img class="width33 paddingTop15 paddingBottom12" src="${resource(dir:'images', file:'1467236565_camera.png')}" alt="camera" />
                                                </div>
                                                <p class="font12 lightGray center paddingBottom10">INTERIOR 2</p>
                                            </div>
                                        </div>
                                        <div id="interiorTresDiv" class="datoRequerido col1fifth  col4-tab col12-mob paddingBottom18 floatLeft">
                                            <div data-tipo-de-imagen="interiorTres" class="width170 cameraBox radius2 floatLeft marginBottom20 pointer">
                                                <div class="autoMargin block width33">
                                                    <img class="width33 paddingTop15 paddingBottom12" src="${resource(dir:'images', file:'1467236565_camera.png')}" alt="camera" />
                                                </div>
                                                <p class="font12 lightGray center paddingBottom10">INTERIOR 3</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                    </div>

                    <div id="paso5Verificacion" style="display:none;">
                        <section class="container marginTop10 marginBottom10">
                            <div class="clearFix width990 autoMargin">
                                <h3 class="floatLeft darkBluetitle marginLeft20 marginTop10">5. DESPEDIDA Y ENCUESTA</h3>
                            </div>
                        </section>

                        <section class="container clearFix">
                            <div class="width990 autoMargin solicitudWhiteBox clearFix paddingBottom20">
                                <div class="floatLeft col6 col12-mob">
                                    <p class="marginTop50 marginLeft30 darkBluetitle font16 fontWeight500">Agradecer su atención y solicitar contestar una breve encuesta de calidad</p>
                                </div>
                                <div class="col5 col12-mob floatLeft">
                                    <div class="paddingAside15">
                                        <div class="blueButton width350 marginTop50 marginBottom50">
                                            <a title="EMPEZAR ENCUESTA" id="btnEnviarEncuesta" class="block colorWhite center font14 fontWeight500 letterspacing1 paddingTop15 paddingBottom15">ENVIAR ENCUESTA</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                    </div>
                    <div id="paso6Verificacion" style="display:none;">
                        <section class="container marginTop10 marginBottom10">
                            <div class="clearFix width990 autoMargin">
                                <h3 class="floatLeft darkBluetitle marginLeft20 marginTop10">6. COMENTARIOS ADICIONALES</h3>
                            </div>
                        </section>

                        <section class="container marginBottom40 width990">
                            <div class="wrapper autoMargin solicitudWhiteBox padding20 paddingBottom35">
                                <h1 class="darkBluetitle font14 fontWeight400 marginBottom15">SI HAY ALGUN COMENTARIO IMPORTANTE PUEDE SER ANOTADO EN ESTE ESPACIO.</h1>
                                <div class="fullInputs">
                                    <textarea name="observaciones" class="whiteBox block height180autoMargin font14 paddingTop10 paddingBottom10 paddingLeft20"></textarea>
                                </div>
                            </div>
                        </section>

                        <section class="container clearFix">
                            <div class="width990 autoMargin clearFix paddingBottom20">
                                <div class="col12 col12-mob floatLeft">
                                    <div class="paddingAside15">
                                        <div class="formContainer width350" style="margin: auto;">
                                            <button type="submit" title="GUARDAR DATOS DE LA VISITA" class="loginButton padding20 blueButton letterspacing2 font14 pointer">GUARDAR DATOS DE LA VISITA</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                    </div>
                </div>
            </g:form>
        </div>
        <div class="verificacionTab" id="mapa" style="display: none;">
            <section class="container marginBottom84">
                <div class="width990 autoMargin clearFix">
                    <div class="solicitudWhiteBox radius2 marginBottom30">
                        <div class="darkBlueBG radius2">
                            <p class="paddingLeft32 colorWhite letterspacing2 fontWeight600 font18 paddingTop16 paddingBottom11">MAPA DEL PROSPECTOS</p>
                        </div>
                        <div class="padding15">
                            <div id="map" class="height477 overflowHide autoMargin">
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <section class="container marginBottom84" id="instruccionesGoogle" style="display: none;">
                <div class="width990 autoMargin clearFix">
                    <div class="solicitudWhiteBox radius2 marginBottom30">
                        <div class="darkBlueBG radius2">
                            <p class="paddingLeft32 colorWhite letterspacing2 fontWeight600 font18 paddingTop16 paddingBottom11">INSTRUCCIONES</p>
                        </div>
                        <div class="padding15">
                            <div id="listaDeInstrucciones" class="height477 overflowAuto autoMargin">
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
        <a class="back-to-top">Back to Top</a>
        <script>

            $(document).ready(function(){
            registrarSolicitudes('${raw((respuesta.datosMapa as grails.converters.JSON) as String)}');
            var amountScrolled = 300;
            $(window).scroll(function() {
	if ( $(window).scrollTop() > amountScrolled ) {
            $('a.back-to-top').fadeIn('slow');
            } else {
            $('a.back-to-top').fadeOut('slow');
            }
            });

            $('.ancla').click(function(e){
            e.preventDefault();
            enlace  = $(this).attr('href');
            $('html, body').animate({
            scrollTop: $(enlace).offset().top
            }, 1000);
            });

            $('a.back-to-top').click(function() {
            $('html, body').animate({
            scrollTop: 0
            }, 1000);
            return false;
            });
            });
        </script>
    </body>
</html>
