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
        <input type="hidden" id="opcionMenu" value="8">
        <section class="container marginBottom28">
            <div class="width990 reporteBox autoMargin">
                <div class="">
                    <ul class="clearFix">
                        <li class="floatLeft paddingLeft5 paddingRight5">
                            <a href="#" title="REPORTES" class="displayInline font24 fontWeight700 darkBluetitle paddingTop20 paddingBottom15 paddingLeft20 paddingRight20">REPORTES</a>
                        </li>
                        <sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_CENTRO_DE_CONTACTO,ROLE_DIRECTOR'>
                            <li id="reporteSolicitudesButton" class="opcReportes floatLeft lightGrayBG paddingLeft5 paddingRight5">
                                <a onclick="mostrarApartado('opcReportes','reportesTab','reporteSolicitudes');" title="Ver Reporte Centro de Contacto"  class="displayInline font20 fontWeight500 darkBluetitle padding20 pointer">CENTRO DE CONTACTO</a>
                            </li>
                        </sec:ifAnyGranted>
                        <sec:ifAnyGranted roles='ROLE_ADMIN'>
                            <li id="reporteMitekButton" class="opcReportes floatLeft paddingLeft5 paddingRight5">
                                <a onclick="mostrarApartado('opcReportes','reportesTab','reporteMitek');" title="Ver Reporte Mitek " class="displayInline font20 fontWeight500 darkBluetitle padding20 pointer">MITEK</a>
                            </li>
                        </sec:ifAnyGranted>
                        <sec:ifAnyGranted roles='ROLE_ADMIN'>
                            <li id="reporteMitekButton" class="opcReportes floatLeft paddingLeft5 paddingRight5">
                                <a onclick="mostrarApartado('opcReportes','reportesTab','reporteBitacoraMitek');" title="Ver Reporte Mitek " class="displayInline font20 fontWeight500 darkBluetitle padding20 pointer">BITACORA MITEK</a>
                            </li>
                        </sec:ifAnyGranted>
                        <sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_DIRECTOR'>
                            <!--<li id="contactoClientesButton" class="opcReportes floatLeft paddingLeft5 paddingRight5">
                                <a onclick="mostrarApartado('opcReportes','reportesTab','contactoClientes');" title="Ver Contacto de Clientes" class="displayInline font20 fontWeight500 darkBluetitle opacity05 padding20 pointer">CONTACTO CLIENTES</a>
                            </li>-->
                        </sec:ifAnyGranted>
                         <sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_RIESGOS,ROLE_MERCADOTECNIA,ROLE_DIRECTOR'>
                            <li id="operacionesButton" class="opcReportes floatLeft lightGrayBG paddingLeft5 paddingRight5">
                                <a onclick="mostrarApartado('opcReportes','reportesTab','operaciones');" title="Ver Operaciones" class="displayInline font20 fontWeight500 darkBluetitle padding20 pointer">OPERACIONES</a>
                            </li>
                        </sec:ifAnyGranted>
                    </ul>
                </div>

            </div>
        </section>
        <g:if test="${flash.error}">
        <center>
            <div class='container clearFix relative autoMargin width920'>
                <div class='errorBoxRed floatLeft'>
                    <div class='infoContainer4c'>
                        <p class='center letterspacing0.5 font13 paddingLeft15 paddingTop15 paddingBottom10 colorWhite marginTop5'>${flash.error}</p>
                    </div>
                </div>
                <div class='buttonOrange line18 floatLeft' style='background-color: #fb5e48;'></div>
                <div class='buttonOrange crosCircle floatLeft' style='background-color: #fb5e48;'>
                    <p class='center marginTop5 font12 colorWhite'><i class='fa fa-times' aria-hidden='true'></i></p>
                </div>
            </div>
        </center>
    </g:if> 
    <sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_CENTRO_DE_CONTACTO,ROLE_DIRECTOR'>
        <div class="reportesTab" id="reporteSolicitudes" style="display: none;">
            <g:render template="reporteSolicitudes"/>
        </div>
    </sec:ifAnyGranted>
    
    <sec:ifAnyGranted roles='ROLE_ADMIN'>
        <div class="reportesTab" id="reporteMitek" style="display: none;">
            <g:render template="reporteMitek"/>
        </div>
    </sec:ifAnyGranted>
    
    <sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_DIRECTOR'>
        <!--<div class="reportesTab" id="contactoClientes" style="display: none;" >
            <g:render template="contactoClientes"/>
        </div>-->
    </sec:ifAnyGranted>
    
    <sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_RIESGOS,ROLE_MERCADOTECNIA,ROLE_DIRECTOR'>
        <div class="reportesTab" id="operaciones" style="display: none;" >
            <g:render template="operaciones"/>
        </div>
    </sec:ifAnyGranted>
    
    <sec:ifAnyGranted roles='ROLE_ADMIN'>
        <div class="reportesTab" id="reporteBitacoraMitek" style="display: none;" >
            <g:render template="reporteBitacoraMitek"/>
        </div>
    </sec:ifAnyGranted>

</body>
</html>
