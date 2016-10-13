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

            $('#graficaGeneral').highcharts({
            chart: {
            zoomType: 'xy'
            },
            title: {
            text: ''
            },
            xAxis: [{
            categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
            'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
            crosshair: true
            }],
            yAxis: [{ // Primary yAxis
            labels: {
            format: '$ {value}',
            style: {
            color: Highcharts.getOptions().colors[1]
            }
            },
            title: {
            text: '',
            style: {
            color: Highcharts.getOptions().colors[1]
            }
            }
            }, { // Secondary yAxis
            title: {
            text: '',
            style: {
            color: Highcharts.getOptions().colors[0]
            }
            },
            labels: {
            format: '-',
            style: {
            color: Highcharts.getOptions().colors[0]
            }
            },
            opposite: true
            }],
            tooltip: {
            shared: true
            },
            legend: {
            layout: 'vertical',
            align: 'right',
            x: 120,
            verticalAlign: 'top',
            y: 100,
            floating: true,
            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
            },
            series: [{
            name: 'Rainfall',
            type: 'column',
            yAxis: 1,
            data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4],
            tooltip: {
            valuePrefix: '$ '
            }

            }, {
            name: 'Temperature',
            type: 'line',
            data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4],
            tooltip: {
            valuePrefix: '$ '
            }
            }]
            });

            $('#graficaEstatus').highcharts({
            chart: {
            type: 'funnel',
            marginRight: 100
            },
            title: {
            text: '',
            x: -50
            },
            plotOptions: {
            series: {
            dataLabels: {
            enabled: true,
                    format: '<b>{point.name}</b> ({point.y:,.0f})',
            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black',
            softConnector: true
            },
            neckWidth: '20%',
            neckHeight: '35%',
            width: '50%'

            //-- Other available options
            // height: pixels or percent
            // width: pixels or percent
            }
            },
            legend: {
            enabled: false
            },
            series: [{
            name: 'Solicitudes por Estatus',
            data: [
            ['Llenado de Solicitud', 15654],
            ['Recabar Documentos', 4064],
            ['Obtención de Datos', 1987],
            ['Análisis', 976],
            ['Autorizado', 846],
            ['Datos extraordinarios', 846],
            ['Rechazado', 846],
            ['Visita Ocular', 846]
            ]
            }]
            });

            $('#graficaOrigen').highcharts({
            chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
            },
            title: {
            text: ''
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
            pie: {
            allowPointSelect: true,
            cursor: 'pointer',
            dataLabels: {
            enabled: false
            },
            showInLegend: true
            }
            },
            series: [{
            name: 'Origen',
            colorByPoint: true,
            data: [{
            name: 'Origen A',
            y: 56.33
            }, {
            name: 'Origen B',
            y: 24.03
            }, {
            name: 'Origen C',
            y: 10.38
            }, {
            name: 'Origen D',
            y: 4.77
            }, {
            name: 'Origen E',
            y: 0.91
            }, {
            name: 'Origen F',
            y: 0.2
            }]
            }]
            });

            $('#graficaPDV').highcharts({
            chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
            },
            title: {
            text: ''
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
            pie: {
            allowPointSelect: true,
            cursor: 'pointer',
            dataLabels: {
            enabled: false
            },
            showInLegend: true
            }
            },
            series: [{
            name: 'Origen',
            colorByPoint: true,
            data: [{
            name: 'PDV A',
            y: 56.33
            }, {
            name: 'PDV B',
            y: 24.03
            }, {
            name: 'PDV C',
            y: 19.64
            }]
            }]
            });

            $('#graficaIvsP').highcharts({
            chart: {
            type: 'column'
            },
            title: {
            text: ''
            },
            subtitle: {
            text: ''
            },
            xAxis: {
            type: 'category'
            },
            yAxis: {
            title: {
            text: ''
            }

            },
            legend: {
            enabled: false
            },
            plotOptions: {
            series: {
            borderWidth: 0,
            dataLabels: {
            enabled: true,
            format: '$ {point.y}'
            }
            }
            },

            tooltip: {
            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>$ {point.y}</b> of total<br/>'
            },

            series: [{
            name: 'Brands',
            colorByPoint: true,
            data: [{
            name: 'Ingresos',
            y: 349235,
            }, {
            name: 'Pagos',
            y: 500234
            }]
            }]
            });

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
                            <a id="subMenuOpc2" onclick="genererarEstadisticasPor(2);" title="Semana" class="elementoSubMenu displayInline gray font16 fontweight300  paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Semana</a>
                        </li>
                        <li class="floatLeft">
                            <a id="subMenuOpc3" onclick="genererarEstadisticasPor(3);" title="Mes" class="elementoSubMenu displayInline gray font16 fontweight300  paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Mes</a>
                        </li>
                        <li class="floatLeft">
                            <a id="subMenuOpc4" onclick="genererarEstadisticasPor(4);" title="Año" class="elementoSubMenu displayInline gray font16 fontweight300  paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Año</a>
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
                                <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10">INGRESOS VS PAGOS</p>
                            </div>
                            <div id="graficaIvsP" style="min-width: 310px; height: 200px; margin: 0 auto"></div>
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
