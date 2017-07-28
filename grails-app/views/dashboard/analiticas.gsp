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
        <g:urlContextAware value="/" var="urlContextAware"/>
        <script type="text/javascript">
            $.contextAwarePathJS = "${urlContextAware}";
        </script>
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

            cargarGraficas(1, null, null);
            });
        </script>
    </head>
    <body>
        <input type="hidden" id="opcionMenu" value="4">
    <section class="container marginBottom28">
        <li class="  floatLeft paddingLeft5 paddingRight5">
            <a title="ANALITICAS" class="displayInline font24 fontWeight700 darkBluetitle paddingTop17 paddingBottom17 paddingLeft20 paddingRight20 pointer">ANALITICAS</a>
        </li>
    </section>
    <section class="regular slider container marginBottom28">
        <div>   
            <li id="informeAnaliticasButton" class="opcInformes floatLeft marginLeft40">
                <a onclick="mostrarApartado('opcInformes','informesTab','estadisticas');" title="Ver Estadísticas"  class="displayInline font20 fontWeight500 darkBluetitle padding20 pointer">Estadísticas</a>
            </li>
        </div>
        <div>
            <li id="informeAnaliticasButton" class="opcInformes floatLeft marginLeft40">
                <a onclick="mostrarApartado('opcInformes','informesTab','analiticas');" title="Ver Solicitudes Formales y Temporales"  class="displayInline font20 fontWeight500 darkBluetitle padding20 pointer">Analiticas Producto</a>
            </li>
        </div>
        <div>
            <li id="informeIndicesButton" class="opcInformes floatLeft marginLeft40">
                <a onclick="mostrarApartado('opcInformes','informesTab','indices');" title="Ver Reporte Mitek " class="displayInline font20 fontWeight500 darkBluetitle  padding20 pointer">Ìndices</a>
            </li>
        </div>
        <div>
            <li id="informeEficienciaButton" class="opcInformes floatLeft marginLeft40">
                <a onclick="mostrarApartado('opcInformes','informesTab','eficiencia');" title="Ver Contacto de Clientes" class="displayInline font20 fontWeight500 darkBluetitle  padding20 pointer">Eficiencia</a>
            </li>
        </div>
    </section>
    <section class="container width990">
            <div class="col12 clearFix radius2 solicitudBox">
                <div class="lightGrayBG marginTop12">
                    <ul class="clearFix solicitude_submenu">
                        <li class="floatLeft">
                            <a id="subMenuOpc1" onclick="genererarEstadisticasPor(1);" title="Dia" class="elementoSubMenu displayInline blueButton font16 fontweight300  paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Día</a>
                        </li>
                        <li class="floatLeft">
                            <a id="subMenuOpc7" onclick="genererarEstadisticasPor(7);" title="Semana" class="elementoSubMenu displayInline gray font16 fontweight300  paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Semana</a>
                        </li>
                        <li class="floatLeft">
                            <a id="subMenuOpc31" onclick="genererarEstadisticasPor(31);" title="Mes" class="elementoSubMenu displayInline gray font16 fontweight300  paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Mes</a>
                        </li>
                        <li class="floatLeft">
                            <a id="subMenuOpc365" onclick="genererarEstadisticasPor(365);" title="Año" class="elementoSubMenu displayInline gray font16 fontweight300  paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Año</a>
                        </li>
                        <li class="floatLeft">
                            <a id="subMenuOpc5" onclick="genererarEstadisticasPor(5);" title="Por Fecha" class="elementoSubMenu displayInline gray font16 fontweight300  paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Por Fecha</a>
                        </li>
                    </ul>
                </div>
            </div>
        </section>
        <div class="informesTab" id="estadisticas">
            <g:render template="estadisticas"/>
        </div>
        <div class="informesTab" id="analiticas"  style="display: none;">
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
