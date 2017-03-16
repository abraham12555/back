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

            cargarGraficas(1, null, null);
            });
        </script>
    </head>
    <body>
        <input type="hidden" id="opcionMenu" value="3">
        <section class="container width990">
            <div class="col12 clearFix radius2 solicitudBox">
                <div class="paddingTop15 paddingLeft15">
                    <ul class="clearFix">
                        <li class="floatLeft">
                            <a class="block font24 fontWeight700 darkBluetitle paddingRight14" href="#">ESTADÍSTICAS</a>
                        </li>
                        <li class="floatLeft marginLeft40">
                            <a class="block font20 fontWeight500 darkBluetitle paddingTop5 paddingRight14" href="#">Exportar CVS</a>
                        </li>
                    </ul>
                </div>
                <div class="lightGrayBG marginTop12">
                    <ul class="clearFix solicitude_submenu">
                        <li class="floatLeft">
                            <a id="subMenuOpc1" onclick="genererarEstadisticasPor(1);" title="Dia" class="elementoSubMenu displayInline blueButton font16 fontweight300  paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Día</a>
                        </li>
                        <li class="floatLeft">
                            <a id="subMenuOpc2" onclick="genererarEstadisticasPor(7);" title="Semana" class="elementoSubMenu displayInline gray font16 fontweight300  paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Semana</a>
                        </li>
                        <li class="floatLeft">
                            <a id="subMenuOpc3" onclick="genererarEstadisticasPor(31);" title="Mes" class="elementoSubMenu displayInline gray font16 fontweight300  paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Mes</a>
                        </li>
                        <li class="floatLeft">
                            <a id="subMenuOpc4" onclick="genererarEstadisticasPor(365);" title="Año" class="elementoSubMenu displayInline gray font16 fontweight300  paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Año</a>
                        </li>
                        <li class="floatLeft">
                            <a id="subMenuOpc5" onclick="genererarEstadisticasPor(5);" title="Por Fecha" class="elementoSubMenu displayInline gray font16 fontweight300  paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Por Fecha</a>
                        </li>
                    </ul>
                </div>
            </div>
        </section>
        <section class="container width990">
            <div class="wrapper clearFix">
                <div class="col12 floatLeft clearFix marginBottom84 marginTop20">
                    <div class="mobileAside10 solicitudWhiteBox radius2 paddingBottom12 height243">
                        <div class="navyBg radius2">
                            <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10">GRÁFICA</p>
                        </div>
                        <div id="graficaGeneral" style="min-width: 310px; height: 200px; margin: 0 auto"></div>
                    </div>
                </div>
                <div class="clearFix">
                    <div class="col6 col12-mob floatLeft">
                        <div class="mobileAside10 solicitudWhiteBox height251 radius2 paddingBottom12 marginTop20">
                            <div class="navyBg radius2">
                                <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10">ESTATUS</p>
                            </div>
                            <div id="graficaEstatus" style="min-width: 310px; height: 200px; margin: 0 auto"></div>
                        </div>
                    </div>
                    <div class="col6 col12-mob floatLeft">
                        <div class="boxMargins solicitudWhiteBox height251 radius2 paddingBottom12 marginTop20">
                            <div class="navyBg radius2">
                                <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10">ORIGEN</p>
                            </div>
                            <div id="graficaOrigen" style="min-width: 310px; height: 200px; margin: 0 auto"></div>
                        </div>
                    </div>
                </div>

                <div class="clearFix marginBottom20">
                    <div class="col6 col12-mob floatLeft">
                        <div class="mobileAside10 solicitudWhiteBox height251 radius2 paddingBottom12 marginTop20">
                            <div class="navyBg radius2">
                                <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10">PUNTOS DE VENTA</p>
                            </div>
                            <div id="graficaPDV" style="min-width: 310px; height: 200px; margin: 0 auto"></div>
                        </div>
                    </div>
                    <div class="col6 col12-mob floatLeft">
                        <div class="boxMargins solicitudWhiteBox height251 radius2 paddingBottom12 marginTop20">
                            <div class="navyBg radius2">
                                <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10">PRODUCTOS</p>
                            </div>
                            <div id="graficaProductos" style="min-width: 310px; height: 200px; margin: 0 auto"></div>
                        </div>
                    </div>
                </div>

                <div class="clearFix">
                    <div class="col4 col12-mob floatLeft">
                        <div class="">
                            <div class="mobileAside10 solicitudWhiteBox radius2 paddingBottom12 marginBottom20">
                                <div class="navyBg radius2 center">
                                    <p class="colorWhite letterspacing1 fontWeight600 font18 paddingTop15 paddingBottom10">% DE CONVERSIÓN</p>
                                </div>
                                <div class="paddingLeft30 clearFix">
                                    <p class="font15 floatLeft darkBluetitle paddingTop15 paddingBottom15">SATELITE</p>
                                    <p class="font15 darkBluetitle floatRight borderLightGray-left paddingTop15 paddingBottom15 paddingRight25 paddingLeft25">23</p>
                                </div>
                                <div class="paddingLeft30 lightGrayBG clearFix">
                                    <p class="font15 floatLeft darkBluetitle paddingTop15 paddingBottom15">COYOACAN</p>
                                    <p class="font15 floatRight darkBluetitle borderLightGray-left paddingTop15 paddingBottom15 paddingRight25 paddingLeft25">46</p>
                                </div>
                                <div class="paddingLeft30 clearFix">
                                    <p class="font15 floatLeft darkBluetitle paddingTop15 paddingBottom15">CENTRO</p>
                                    <p class="font15 floatRight darkBluetitle borderLightGray-left paddingTop15 paddingBottom15 paddingRight25 paddingLeft25">16</p>
                                </div>
                                <div class="paddingLeft30 lightGrayBG clearFix">
                                    <p class="font15 floatLeft darkBluetitle paddingTop15 paddingBottom15">ROMA</p>
                                    <p class="font15 floatRight darkBluetitle borderLightGray-left paddingTop15 paddingBottom15 paddingRight25 paddingLeft25">14</p>
                                </div>
                                <div class="paddingLeft30 clearFix">
                                    <p class="font15 floatLeft darkBluetitle paddingTop15 paddingBottom15">CONDESA</p>
                                    <p class="font15 floatRight darkBluetitle borderLightGray-left paddingTop15 paddingBottom15 paddingRight25 paddingLeft25">46</p>
                                </div>
                                <div class="paddingLeft30 lightGrayBG clearFix">
                                    <p class="font15 floatLeft darkBluetitle paddingTop15 paddingBottom15">POLANCO</p>
                                    <p class="font15 floatRight darkBluetitle borderLightGray-left paddingTop15 paddingBottom15 paddingRight25 paddingLeft25">11</p>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col4 col12-mob floatLeft">
                        <div class="">
                            <div class="boxMargins solicitudWhiteBox radius2 paddingBottom12 marginBottom20">
                                <div class="navyBg radius2 center">
                                    <p class="colorWhite letterspacing1 fontWeight600 font18 paddingTop15 paddingBottom10">VOL DE TRANSACCIONES</p>
                                </div>
                                <div class="paddingLeft30 clearFix">
                                    <p class="font15 floatLeft darkBluetitle paddingTop15 paddingBottom15">SATELITE</p>
                                    <p class="font15 darkBluetitle floatRight borderLightGray-left paddingTop15 paddingBottom15 paddingRight25 paddingLeft25">23</p>
                                </div>
                                <div class="paddingLeft30 lightGrayBG clearFix">
                                    <p class="font15 floatLeft darkBluetitle paddingTop15 paddingBottom15">COYOACAN</p>
                                    <p class="font15 floatRight darkBluetitle borderLightGray-left paddingTop15 paddingBottom15 paddingRight25 paddingLeft25">46</p>
                                </div>
                                <div class="paddingLeft30 clearFix">
                                    <p class="font15 floatLeft darkBluetitle paddingTop15 paddingBottom15">CENTRO</p>
                                    <p class="font15 floatRight darkBluetitle borderLightGray-left paddingTop15 paddingBottom15 paddingRight25 paddingLeft25">16</p>
                                </div>
                                <div class="paddingLeft30 lightGrayBG clearFix">
                                    <p class="font15 floatLeft darkBluetitle paddingTop15 paddingBottom15">ROMA</p>
                                    <p class="font15 floatRight darkBluetitle borderLightGray-left paddingTop15 paddingBottom15 paddingRight25 paddingLeft25">14</p>
                                </div>
                                <div class="paddingLeft30 clearFix">
                                    <p class="font15 floatLeft darkBluetitle paddingTop15 paddingBottom15">CONDESA</p>
                                    <p class="font15 floatRight darkBluetitle borderLightGray-left paddingTop15 paddingBottom15 paddingRight25 paddingLeft25">46</p>
                                </div>
                                <div class="paddingLeft30 lightGrayBG clearFix">
                                    <p class="font15 floatLeft darkBluetitle paddingTop15 paddingBottom15">POLANCO</p>
                                    <p class="font15 floatRight darkBluetitle borderLightGray-left paddingTop15 paddingBottom15 paddingRight25 paddingLeft25">11</p>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col4 col12-mob floatRight">
                        <div class="">
                            <div class="boxMargins solicitudWhiteBox radius2 paddingBottom12 marginBottom20">
                                <div class="navyBg radius2 center">
                                    <p class="colorWhite letterspacing1 fontWeight600 font18 paddingTop15 paddingBottom10">MEDIANA DE TIEMPO</p>
                                </div>
                                <div class="paddingLeft30 clearFix">
                                    <p class="font15 floatLeft darkBluetitle paddingTop15 paddingBottom15">SATELITE</p>
                                    <p class="font15 darkBluetitle floatRight borderLightGray-left paddingTop15 paddingBottom15 paddingRight25 paddingLeft25">23</p>
                                </div>
                                <div class="paddingLeft30 lightGrayBG clearFix">
                                    <p class="font15 floatLeft darkBluetitle paddingTop15 paddingBottom15">COYOACAN</p>
                                    <p class="font15 floatRight darkBluetitle borderLightGray-left paddingTop15 paddingBottom15 paddingRight25 paddingLeft25">46</p>
                                </div>
                                <div class="paddingLeft30 clearFix">
                                    <p class="font15 floatLeft darkBluetitle paddingTop15 paddingBottom15">CENTRO</p>
                                    <p class="font15 floatRight darkBluetitle borderLightGray-left paddingTop15 paddingBottom15 paddingRight25 paddingLeft25">16</p>
                                </div>
                                <div class="paddingLeft30 lightGrayBG clearFix">
                                    <p class="font15 floatLeft darkBluetitle paddingTop15 paddingBottom15">ROMA</p>
                                    <p class="font15 floatRight darkBluetitle borderLightGray-left paddingTop15 paddingBottom15 paddingRight25 paddingLeft25">14</p>
                                </div>
                                <div class="paddingLeft30 clearFix">
                                    <p class="font15 floatLeft darkBluetitle paddingTop15 paddingBottom15">CONDESA</p>
                                    <p class="font15 floatRight darkBluetitle borderLightGray-left paddingTop15 paddingBottom15 paddingRight25 paddingLeft25">46</p>
                                </div>
                                <div class="paddingLeft30 lightGrayBG clearFix">
                                    <p class="font15 floatLeft darkBluetitle paddingTop15 paddingBottom15">POLANCO</p>
                                    <p class="font15 floatRight darkBluetitle borderLightGray-left paddingTop15 paddingBottom15 paddingRight25 paddingLeft25">11</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
