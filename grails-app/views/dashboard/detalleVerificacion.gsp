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
        <title>Sample title</title>
    </head>
    <body>
        <input type="hidden" id="opcionMenu" value="4">
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
                            <a onclick="mostrarApartado('opcVerificacion','verificacionTab','mapa');" title="Ver mapa" class="displayInline font20 fontWeight500 darkBluetitle padding20 pointer">Ver mapa</a>
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
            <section class="container">
                <div class="wrapper width990 autoMargin solicitudWhiteBox clearFix borderGrayBottom ">
                    <div class="clearFix">
                        <div class="floatLeft">
                            <table class="borderGrayBottom ">
                                <tr>
                                    <td class="borderGrayRight padding10">
                                        <p class="font12 fontWeight300 gray">FOLIO</p>
                                        <p class="font14 fontWeight400 tableDescriptionColor">00021</p>
                                    </td>
                                    <td class="borderGrayRight padding10">
                                        <p class="font12 fontWeight300 gray">PROSPECTO</p>
                                        <p class="font14 fontWeight400 tableDescriptionColor">Mariana Hernandez F.</p>
                                    </td>
                                    <td class="clearFix borderGrayRight padding10">
                                        <div class="floatLeft">
                                            <p class="font12 fontWeight300 gray">DIRECCIÓN</p>
                                            <p class="font14 fontWeight400 tableDescriptionColor">Orizaba 234. Int. 23 Col. Roma Norte. Cuauhtem…</p>
                                        </div>
                                        <div class="floatLeft">
                                            <img src="${resource(dir:'images', file:'mapicon.svg')}" alt="Map">
                                        </div>
                                    </td>
                                    <td class="borderGrayRight padding10">
                                        <p class="font12 fontWeight300 gray">HORARIO</p>
                                        <p class="font14 fontWeight400 tableDescriptionColor">9:40 AM</p>
                                    </td>
                                    <td class="clearFix padding10">
                                        <div class="floatLeft">
                                            <p class="font12 fontWeight300 gray">ESTATUS</p>
                                            <p class="font14 fontWeight400 tableDescriptionColor">A TIEMPO</p>
                                        </div>
                                        <div class="floatLeft">
                                            <i class="fa fa-angle-down" aria-hidden="true"></i>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                            <table>
                                <tr>
                                    <td class=" borderGrayRight padding10">
                                        <p class="font12 fontWeight300 gray">SERVICIO</p>
                                        <p class="font14 fontWeight400 tableDescriptionColor">CRÉDITO AUTOMOTRIZ</p>
                                    </td>
                                    <td class=" borderGrayRight padding10">
                                        <p class="font12 fontWeight300 gray">MONTO</p>
                                        <p class="font14 fontWeight400 tableDescriptionColor">$350,000.00</p>
                                    </td>
                                    <td class="width478 padding10">
                                        <p class="font12 fontWeight300 gray">PRODUCTO</p>
                                        <p class="font14 fontWeight400 tableDescriptionColor">NISSAN MÁXIMA 2016</p>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="floatRight center borderGrayLeft padding10">
                            <a hreftitle="LLAMAR" class="block colorGreen colorWhite radius4 paddingTop12 paddingBottom12 paddingLeft5 paddingRight5 marginBottom15" style='width: 155px;'>LLAMAR</a>
                            <a id='iniciarVisitaBtn' onclick='iniciarVisita();'>
                                <p class="blueButton radius4 paddingTop12 paddingBottom12 paddingLeft5 paddingRight5">COMENZAR VISITA</p>
                            </a>
                        </div>
                    </div>
                </div>
            </section>
        </div>
        <div class="verificacionTab" id="mapa" style="display: none;">
            <section class="container marginBottom84">
                <div class="width990 autoMargin clearFix">
                    <div class="solicitudWhiteBox radius2 marginBottom30">
                        <div class="darkBlueBG radius2">
                            <p class="paddingLeft32 colorWhite letterspacing2 fontWeight600 font18 paddingTop16 paddingBottom11">MAPA DEL PROSPECTOS</p>
                        </div>
                        <div class="padding15">
                            <div class="height477 overflowHide autoMargin">
                                <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d481726.8873951675!2d-99.42381479170352!3d19.391166885849092!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1sen!2sca!4v1467138517694" width="100%" height="100%" frameborder="0" style="border:0" allowfullscreen></iframe>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
        <div id="verificacionFormulario" style='display: none;'>
            <section class="container clearFix width990">
                <div class="col12">
                    <div class="marginTop20 marginLeft20 marginBottom20">
                        <h1 class="font18 letterspacing2 darkBluetitle fontWeight600">1. VERIFICACIÓN DE DOMICILIO.</h1>
                    </div>
                    <div class="width990 autoMargin solicitudWhiteBox">
                        <div class="">
                            <p class="paddingTop15 paddingLeft20 letterspacing1 fontWeight500 font14 gray paddingBottom12">TOMAR 3 FOTOS DE LA FACHADA DE LA VIVIENDA</p>
                        </div>
                        <div class="clearFix">
                            <div class="col1fifth col4-tab col12-mob paddingBottom18 floatLeft">
                                <div class="width170 cameraBox radius2 boxMargins">
                                    <div class="autoMargin block width33">
                                        <img class="width33 paddingTop15 paddingBottom12" src="${resource(dir:'images', file:'1467236565_camera.png')}" alt="camera" />
                                    </div>
                                    <p class="font12 gray center paddingBottom10">FACHADA - FRENTE</p>
                                </div>
                            </div>
                            <div class="col1fifth  col4-tab col12-mob paddingBottom18 floatLeft">
                                <div class="width170 cameraBox radius2 boxMargins">
                                    <div class="autoMargin block width33">
                                        <img class="width33 paddingTop15 paddingBottom12" src="${resource(dir:'images', file:'1467236565_camera.png')}" alt="camera" />
                                    </div>
                                    <p class="font12 gray center paddingBottom10">FACHADA - IZQUIERDA</p>
                                </div>
                            </div>
                            <div class="col1fifth  col4-tab col12-mob paddingBottom18 floatLeft">
                                <div class="width170 cameraBox radius2 boxMargins">
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
                                    <p class="paddingLeft35 font18 fontWeight500 darkBluetitle">Av. Orizaba</p>
                                </div>
                                <div class="lightGrayBG  marginTop5">
                                    <p class=" paddingLeft35 paddingTop10 font16 fontWeight500 gray">NÚMERO EXTERIOR</p>
                                    <p class=" paddingLeft35 font18 fontWeight500 darkBluetitle">234</p>
                                </div>
                                <div class=" whiteBg">
                                    <p class="paddingLeft35 paddingTop10 font16 fontWeight500 gray">NÚMERO INTERIOR</p>
                                    <p class="paddingLeft35 font18 fontWeight500 darkBluetitle">123</p>
                                </div>
                                <div class="lightGrayBG  marginTop5">
                                    <p class=" paddingLeft35 paddingTop10 font16 fontWeight500 gray">COLONIA</p>
                                    <p class=" paddingLeft35 font18 fontWeight500 darkBluetitle">Hipódromo</p>
                                </div>
                                <div class=" whiteBg">
                                    <p class="paddingLeft35 paddingTop10 font16 fontWeight500 gray">DELEGACIÓN O MUNICIPIO</p>
                                    <p class="paddingLeft35 font18 fontWeight500 darkBluetitle">Cuauhtemoc</p>
                                </div>
                                <div class="lightGrayBG  marginTop5">
                                    <p class=" paddingLeft35 paddingTop10 font16 fontWeight500 gray">CÓDIGO POSTAL</p>
                                    <p class=" paddingLeft35 font18 fontWeight500 darkBluetitle">06100</p>
                                </div>
                                <div class=" whiteBg paddingBottom15">
                                    <p class="paddingLeft35 paddingTop10 font16 fontWeight500 gray">ESTADO</p>
                                    <p class="paddingLeft35 font18 fontWeight500 darkBluetitle">Distrito Federal</p>
                                </div>
                            </div>
                        </div>

                        <div class="col4 floatLeft">
                            <div class="paddingTop20">
                                <div class="gradientColor radius2">
                                    <p class="colorWhite paddingTop15 center paddingBottom10 font18 fontWeight600 letterspacing2">VERIFICACIÓN</p>
                                </div>
                                <div class="lightGrayBG">
                                    <div class="clearFix">
                                        <div id='direccionVerificar' class="paddingTop5 paddingLeft15 clearFix paddingTop10">
                                            <div id='direccionSi' class="checkVerificacion width70 whiteBox radius1.6 floatLeft">
                                                <a onclick="verificarDatos('direccion','Si');" title="SI" class="block font14 gray center paddingTop15 paddingBottom15">SI</a>
                                            </div>
                                            <div id='direccionNo' class="checkVerificacion width70 whiteBox radius1.6 floatLeft">
                                                <a onclick="verificarDatos('direccion','No');" title="NO" class="block font14 gray center paddingTop15 paddingBottom15">NO</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="clearFix">
                                        <div id='numExtVerificar' class="paddingTop5 paddingLeft15 clearFix paddingTop10">
                                            <div id='numExtSi' class="checkVerificacion width70 whiteBox radius1.6 floatLeft">
                                                <a onclick="verificarDatos('numExt','Si');" title="SI" class="block font14 gray center paddingTop15 paddingBottom15">SI</a>
                                            </div>
                                            <div id='numExtNo' class="checkVerificacion width70 whiteBox radius1.6 floatLeft">
                                                <a onclick="verificarDatos('numExt','No');" title="NO" class="block font14 gray center paddingTop15 paddingBottom15">NO</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="clearFix">
                                        <div id='numIntVerificar' class="paddingTop5 paddingLeft15 clearFix paddingTop10">
                                            <div id='numIntSi' class="checkVerificacion width70 whiteBox radius1.6 floatLeft">
                                                <a onclick="verificarDatos('numInt','Si');" title="SI" class="block font14 gray center paddingTop15 paddingBottom15">SI</a>
                                            </div>
                                            <div id='numIntNo' class="checkVerificacion width70 whiteBox radius1.6 floatLeft">
                                                <a onclick="verificarDatos('numInt','No');" title="NO" class="block font14 gray center paddingTop15 paddingBottom15">NO</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="clearFix">
                                        <div id='coloniaVerificar' class="paddingTop5 paddingLeft15 clearFix paddingTop10">
                                            <div id='coloniaSi' class="checkVerificacion width70 whiteBox radius1.6 floatLeft">
                                                <a onclick="verificarDatos('colonia','Si');" title="SI" class="block font14 gray center paddingTop15 paddingBottom15">SI</a>
                                            </div>
                                            <div id='coloniaNo' class="checkVerificacion width70 whiteBox radius1.6 floatLeft">
                                                <a onclick="verificarDatos('colonia','No');" title="NO" class="block font14 gray center paddingTop15 paddingBottom15">NO</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="clearFix">
                                        <div id='delegacionVerificar' class="paddingTop5 paddingLeft15 clearFix paddingTop10">
                                            <div id='delegacionSi' class="checkVerificacion width70 whiteBox radius1.6 floatLeft">
                                                <a onclick="verificarDatos('delegacion','Si');" title="SI" class="block font14 gray center paddingTop15 paddingBottom15">SI</a>
                                            </div>
                                            <div id='delegacionNo' class="checkVerificacion width70 whiteBox radius1.6 floatLeft">
                                                <a onclick="verificarDatos('delegacion','No');" title="NO" class="block font14 gray center paddingTop15 paddingBottom15">NO</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="clearFix">
                                        <div id='codigoPostalVerificar' class="paddingTop5 paddingLeft15 clearFix paddingTop10">
                                            <div id='codigoPostalSi' class="checkVerificacion width70 whiteBox radius1.6 floatLeft">
                                                <a onclick="verificarDatos('codigoPostal','Si');" title="SI" class="block font14 gray center paddingTop15 paddingBottom15">SI</a>
                                            </div>
                                            <div id='codigoPostalNo' class="checkVerificacion width70 whiteBox radius1.6 floatLeft">
                                                <a onclick="verificarDatos('codigoPostal','No');" title="NO" class="block font14 gray center paddingTop15 paddingBottom15">NO</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="clearFix">
                                        <div id='estadoVerificar' class="paddingTop5 paddingLeft15 clearFix paddingTop10">
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
                    <p class="lightGray paddingLeft15 font14 paddingTop20 paddingBottom20">ACERCATE A LA ENTRADA Y PRESENTATE CON <span class="colorblue fontWeight500 font14">JUAN PEREZ PEREZ</span></p>
                    <div class="col6 floatLeft">
                        <div class="paddingAside15">
                            <p class="font14 fontWeight500 lightGray letterspacing1 paddingBottom15">1. MUESTRA TU GAFÉTE DE LA EMPRESA</p>
                            <div class="col10 col12-mob whiteBox">
                                <a href="#" title="LISTO" class="block paddingTop15 paddingBottom15 center lightGray font14 letterspacing1 fontWeight500">LISTO</a>
                            </div>
                        </div>
                    </div>
                    <div class="col6 floatLeft">
                        <div class="paddingAside15">
                            <p class="font14 fontWeight500 lightGray letterspacing1 paddingBottom15">2. MECIONA TU CITA DE HOY PARA LAS 10:00 AM</p>
                            <div class="col10 col12-mob whiteBox">
                                <a href="#" title="LISTO" class="block paddingTop15 paddingBottom15 center lightGray font14 letterspacing1 fontWeight500">LISTO</a>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

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
                                <div class="paddingAside15">
                                    <div class="width170 autoCenter block cameraBox radius2">
                                        <div class="autoMargin block width33">
                                            <img class="width33 paddingTop15 paddingBottom12" src="${resource(dir:'images', file:'1467236565_camera.png')}" alt="camera" />
                                        </div>
                                        <p class="font12 lightGray center paddingBottom10">FRENTE</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col6 paddingBottom18 floatLeft">
                                <div class="paddingAside15">
                                    <div class="width170 autoCenter block cameraBox radius2">
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
                            <div class="button marginTop20">
                                <div class="whiteBox width350 autoCenter block">
                                    <a href="#" title="NO CUENTA CON EL DOCUMENTO" class="block font14 letterspacing1 fontWeight500 lightGray paddingTop15 paddingBottom15 center">NO CUENTA CON EL DOCUMENTO</a>
                                </div>
                                <div class="whiteBox width350 marginTop10 autoCenter block">
                                    <a href="#" title="DOCUMENTO ALTERADO" class="block font14 letterspacing1 fontWeight500 lightGray paddingTop15 paddingBottom15 center">DOCUMENTO ALTERADO</a>
                                </div>
                                <div class="whiteBox width350 marginTop10 autoCenter block">
                                    <a href="#" title="NO ES EL MISMO DOCUMENTO" class="block font14 letterspacing1 fontWeight500 lightGray paddingTop15 paddingBottom15 center">NO ES EL MISMO DOCUMENTO</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
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
                                <div class="paddingAside15">
                                    <div class="width170 autoCenter block cameraBox radius2">
                                        <div class="autoMargin block width33">
                                            <img class="width33 paddingTop15 paddingBottom12" src="${resource(dir:'images', file:'1467236565_camera.png')}" alt="camera" />
                                        </div>
                                        <p class="font12 lightGray center paddingBottom10">FRENTE</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col6 paddingBottom18 floatLeft">
                                <div class="paddingAside15">
                                    <div class="width170 autoCenter block cameraBox radius2">
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
                            <div class="button marginTop20">
                                <div class="whiteBox width350 autoCenter block">
                                    <p class="font14 letterspacing1 fontWeight500 lightGray paddingTop15 paddingBottom15 center">NO CUENTA CON EL DOCUMENTO</p>
                                </div>
                                <div class="whiteBox width350 marginTop10 autoCenter block">
                                    <p class="font14 letterspacing1 fontWeight500 lightGray paddingTop15 paddingBottom15 center">DOCUMENTO ALTERADO</p>
                                </div>
                                <div class="whiteBox width350 marginTop10 autoCenter block">
                                    <p class="font14 letterspacing1 fontWeight500 lightGray paddingTop15 paddingBottom15 center">NO ES EL MISMO DOCUMENTO</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <section class="container width990">
                <div class="clearFix autoMargin padding20">
                    <h3 class="floatLeft darkBluetitle">4. PREGUNTAS ESPECIFICAS PARA EL PROSPECTO</h3>
                </div>
            </section>

            <section class="container width990">
                <div class="wrapper autoMargin solicitudWhiteBox fullInputs clearFix padding20">
                    <div>
                        <label for="atrazo" class="block font14 gray marginBottom10 ">¿A QUÉ SE DEBE SU ATRAZO EN SU TARJETA DE CRÉDITO?</label>
                        <div>
                        </div>
                        <input type="text" placeholder="Respuesta" class="whiteBox autoCenter block">
                    </div>
                    <div>
                        <label for="ingresos" class="block font14 gray marginTop15 marginBottom10 ">¿DE DONDE VIENEN SUS INGRESOS?</label>
                        <div>
                        </div>
                        <input type="text" placeholder="Respuesta" class="whiteBox autoCenter block">
                    </div>
                    <div class="col5half col12-mob floatLeft">
                        <div>
                            <label for="domicilio" class="block font14 gray marginTop15 marginBottom10 ">¿CUANTAS PERSONAS HABITAN EN EL DOMICILIO?</label>
                        </div>
                        <div>
                            <input type="text" placeholder="Respuesta" class="whiteBox autoCenter block">
                        </div>
                    </div>
                    <div class="col5half col12-mob floatRight">
                        <div>
                            <label for="dependientes" class="block font14 gray marginTop15 marginBottom10 ">¿CUANTOS DEPENDIENTES ECONÓMICOS TIENE?</label>
                        </div>
                        <div>
                            <input type="text" placeholder="Respuesta" class="whiteBox autoCenter block">
                        </div>
                    </div>
                </div>
            </section>

            <section class="container clearFix marginTop30">
                <div class="width990 autoMargin solicitudWhiteBox clearFix paddingBottom20">
                    <div class="paddingLeft30">
                        <p class="gray font14 fontWeight500 letterspacing1 paddingTop20 paddingBottom15">TOMAR 3 FOTOS DEL INTERIOR DE LA VIVIENDA</p>
                        <div class="paddingTop30 clearFix">
                            <div class="width170 cameraBox radius2 floatLeft marginRight50 marginBottom20">
                                <div class="autoMargin block width33">
                                    <img class="width33 paddingTop15 paddingBottom12" src="${resource(dir:'images', file:'1467236565_camera.png')}" alt="camera" />
                                </div>
                                <p class="font12 lightGray center paddingBottom10">INTERIOR 1</p>
                            </div>
                            <div class="width170 cameraBox radius2 floatLeft marginRight50 marginBottom20">
                                <div class="autoMargin block width33">
                                    <img class="width33 paddingTop15 paddingBottom12" src="${resource(dir:'images', file:'1467236565_camera.png')}" alt="camera" />
                                </div>
                                <p class="font12 lightGray center paddingBottom10">INTERIOR 2</p>
                            </div>
                            <div class="width170 cameraBox radius2 floatLeft marginBottom20">
                                <div class="autoMargin block width33">
                                    <img class="width33 paddingTop15 paddingBottom12" src="${resource(dir:'images', file:'1467236565_camera.png')}" alt="camera" />
                                </div>
                                <p class="font12 lightGray center paddingBottom10">INTERIOR 3</p>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

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
                                <a href="#" title="EMPEZAR ENCUESTA" class="block colorWhite center font14 fontWeight500 letterspacing1 paddingTop15 paddingBottom15">EMPEZAR ENCUESTA</a>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <section class="container marginTop10 marginBottom10">
                <div class="clearFix width990 autoMargin">
                    <h3 class="floatLeft darkBluetitle marginLeft20 marginTop10">6. COMENTARIOS ADICIONALES</h3>
                </div>
            </section>

            <section class="container marginBottom40 width990">
                <div class="wrapper autoMargin solicitudWhiteBox padding20 paddingBottom35">
                    <h1 class="darkBluetitle font14 fontWeight400 marginBottom15">SI HAY ALGUN COMENTARIO IMPORTANTE PUEDE SER ANOTADO EN ESTE ESPACIO.</h1>
                    <div class="fullInputs">
                        <textarea name="name" class="whiteBox block height180autoMargin font14 paddingTop10 paddingBottom10 paddingLeft20"></textarea>
                    </div>
                </div>
            </section>
        </div>
    </body>
</html>
