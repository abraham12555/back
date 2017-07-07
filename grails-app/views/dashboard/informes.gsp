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
        <g:external dir="js" file="highchart/highcharts.js" />
        <g:external dir="js" file="highchart/highcharts-more.js" />
        <g:external dir="js" file="highchart/modules/funnel.js" />
        <g:external dir="js" file="highchart/modules/exporting.js" />
        <g:external dir="js" file="graficas.js" />
       
       <script>
            $(function () {

            Highcharts.setOptions({
            colors: ['#53c1fb', '#fd977e', '#62e4a7', '#a1afec', '#ed89a1', '#d4dbe6', '#e59a3d','#f0ea66', '#79b4d8']
            });

            Highcharts.getOptions().colors = Highcharts.map(Highcharts.getOptions().colors, function (color) {
            return {
            radialGradient: {
            cx: 0.5,
            cy: 0.3,
            r: 0.7
            },
            stops: [
            [0, color],
            [1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken
            ]
            };
            });

            //cargarGraficas(1, null, null);
            });
        </script>
    </head>
    <body>
        <section class="container width990">
            <div class="col12 clearFix radius2 solicitudBox">
                <div class="paddingTop15 paddingLeft15">
                    <ul class="clearFix">
                        <li class="floatLeft">
                            <a class="block font24 fontWeight700 darkBluetitle paddingRight14" href="#">INFORMES  </a>
                        </li>
                        <li id="informeAnaliticasButton" class="opcInformes floatLeft marginLeft40">
                            <a onclick="mostrarApartado('opcInformes','informesTab','analiticas');" title="Ver Solicitudes Formales y Temporales"  class="displayInline font20 fontWeight500 darkBluetitle padding20 pointer">Analiticas Producto</a>
                        </li>
                        <li id="informeIndicesButton" class="opcInformes floatLeft marginLeft40">
                            <a onclick="mostrarApartado('opcInformes','informesTab','indices');" title="Ver Reporte Mitek " class="displayInline font20 fontWeight500 darkBluetitle opacity05 padding20 pointer">Ìndices</a>
                        </li>
                        <li id="informeEficienciaButton" class="opcInformes floatLeft marginLeft40">
                            <a onclick="mostrarApartado('opcInformes','informesTab','eficiencia');" title="Ver Contacto de Clientes" class="displayInline font20 fontWeight500 darkBluetitle opacity05 padding20 pointer">Eficiencia</a>
                        </li>
                    </ul>
                </div>
                <div class="lightGrayBG">
                    <ul class="clearFix paddingLeft30 solicitude_submenu">
                        <li class="floatLeft">
                            <a id="subMenuOpc1" onclick="genererarInformesPor(1);" title="Dia" class=" elementoSubMenu displayInline blueButton font16 fontweight300  paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Día</a>
                        </li>
                        <li class="floatLeft">
                            <a id="subMenuOpc2" onclick="genererarInformesPor(7);" title="Semana" class="elementoSubMenu displayInline gray font16 fontweight300  paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Semana</a>
                        </li>
                        <li class="floatLeft">
                            <a id="subMenuOpc3" onclick="genererarInformesPor(31);" title="Mes" class="elementoSubMenu displayInline gray font16 fontweight300  paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Mes</a>
                        </li>
                        <li class="floatLeft">
                            <a id="subMenuOpc4" onclick="genererarInformesPor(365);" title="Año" class="elementoSubMenu displayInline gray font16 fontweight300  paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Año</a>
                        </li>
                        <li class="floatLeft">
                            <a id="subMenuOpc5" onclick="genererarInformesPor(5);" title="Por Fecha" class="elementoSubMenu displayInline gray font16 fontweight300 paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Por Fecha</a>
                        </li>
                    </ul>
                </div>
            </div>
        </section>
        <section class="container marginBottom28" id="rangoDeFechas" style="display: none;">
            <div class="width990 solicitudBox autoMargin" style="height: 10%;">
                <div class="lightGrayBG" style="height: 100%;">
                    <div class="col12 floatLeft" style="padding: 15px;">
                        <div class="col4 floatLeft">
                            <div class="col3 floatLeft">
                                <p class="gray font14 fontWeight500 letterspacing1 marginLeft20" style="padding: 5px;"><strong>Desde</strong></p>
                            </div>
                            <div class="col9 floatLeft">
                                <input class="block cameraBox col11 height30" style="text-align: center;" readonly type="text" id="from" name="from">
                            </div>
                        </div>
                        <div class="col4 floatLeft">
                            <div class="col3 floatLeft">
                                <p class="gray font14 fontWeight500 letterspacing1 marginLeft20" style="padding: 5px;"><strong>a</strong></p>
                            </div>
                            <div class="col9 floatLeft">
                                <input class="block cameraBox col11 height30" style="text-align: center;" readonly type="text" id="to" name="to">
                            </div>
                        </div>
                        <div class="col4 floatLeft">
                            <center>
                                <li>
                                    <a onclick="genererarInformesPor(0);" title="Buscar" class="displayInline blueButton font16 fontweight300  paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Buscar</a>
                                </li>
                            </center>
                        </div>
                    </div>
                </div>
            </div>
        </section>
         
        <div class="informesTab" id="analiticas">
            <g:render template="analiticas"/>
        </div>
        <div class="informesTab" id="indices" style="display: none;">
            <g:render template="indices"/>
        </div>
        <div class="informesTab" id="eficiencia" style="display: none;">
           <g:render template="eficiencia"/>
        </div>
    </body>
</html>
