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
        <g:external dir="js" file="maps.js" />
        <script src="https://maps.google.com.mx/maps/api/js?language=es&key=AIzaSyA1CPLFAC5mFixfxBFB5OYnirzijeTopRI" async defer></script>
    </head>
    <body>
        <input type="hidden" id="opcionMenu" value="4">
        <section class="container marginBottom20">
            <div class="width990 solicitudBox autoMargin">
                <div class="clearFix">
                    <ul class="floatLeft clearFix">
                        <li class="floatLeft paddingLeft5 paddingRight5">
                            <a title="VERIFICACIONES" class="displayInline font24 fontWeight700 darkBluetitle paddingTop20 paddingBottom15 paddingLeft20 paddingRight20">VERIFICACIONES</a>
                        </li>
                        <li id="agendaButton" class="opcVerificacion floatLeft lightGrayBG paddingLeft5 paddingRight5">
                            <a onclick="mostrarApartado('opcVerificacion','verificacionTab','agenda');" title="Ver agenda" class="displayInline font20 fontWeight500 darkBluetitle padding20 pointer">Ver agenda</a>
                        </li>
                        <li id="mapaButton" class="opcVerificacion floatLeft paddingLeft5 paddingRight5">
                            <a onclick="mostrarApartado('opcVerificacion','verificacionTab','mapa'); cargarDireccioneEnMapa();" title="Ver mapa" class="displayInline font20 fontWeight500 darkBluetitle padding20 pointer">Ver mapa</a>
                        </li>
                    </ul>
                    <div class="floatRight whiteBox clearFix marginTop15 marginRight10">
                        <i class="fa fa-search gray2 paddingTop10 paddingBottom10 paddingLeft15 paddingRight10" aria-hidden="true"></i>
                        <input type="text" placeholder="Buscar" class="font14 fontweight500 gray2 paddingTop10 paddingBottom10 paddingLeft5">
                    </div>
                </div>
            </div>
        </section>
        <div class="verificacionTab" id="agenda">
            <g:render template="solicitudesPorVerificar" />
        </div>
        <div class="verificacionTab" id="mapa" style="display: none;">
            <section class="container marginBottom84">
                <div class="width990 autoMargin clearFix">
                    <div class="solicitudWhiteBox radius2 marginBottom30">
                        <div class="darkBlueBG radius2">
                            <p class="paddingLeft32 colorWhite letterspacing2 fontWeight600 font18 paddingTop16 paddingBottom11">MAPA DE PROSPECTOS</p>
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
        <script>
            $(document).ready(function () {
            registrarSolicitudes('${raw(solicitudesJSON as String)}');
            });
        </script>
    </body>
</html>
