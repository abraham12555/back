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
        <g:external dir="css" file="jquery.rateyo.min.css" />
        <g:external dir="js" file="jquery.rateyo.min.js" />
        <g:external dir="js" file="highchart/highcharts.js" />
        <g:external dir="js" file="highchart/highcharts-more.js" />
        <g:external dir="js" file="highchart/modules/exporting.js" />
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
            background: #2d91f6 url("/kosmos-app/images/up-arrow.png") no-repeat center 43%;
            box-shadow: 0 0 7px 0 #298df5;
            -webkit-border-radius: 30px;
            -moz-border-radius: 30px;
            border-radius: 30px;
            cursor: pointer;
            }
        </style>
        <script>

            $(document).ready(function(){
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

            $(function () {
            $(".rateyo").rateYo({
            normalFill: "#d4dbe6",
            ratedFill: "#298df5",
            halfStar: true
            });
            });

            $(function (){

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

            $('#graficaSaldos').highcharts({
            title: {
            text: '',
            x: -20 //center
            },
            xAxis: {
            categories: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun',
            'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic']
            },
            yAxis: {
            title: {
            text: ''
            },
            plotLines: [{
            value: 0,
            width: 1,
            color: '#808080'
            }]
            },
            tooltip: {
            valuePrefix: '$'
            },
            legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
            },
            series: [{
            name: 'Uno',
            data: [7000, 6900, 9500, 14500, 18200, 21500, 25200, 26500, 23300, 18300, 13900, 9600]
            }, {
            name: 'Dos',
            data: [200, 800, 5700, 11300, 17000, 22000, 24800, 24100, 20100, 14100, 8600, 2500]
            }]
            });

            $('#graficaIvsB').highcharts({
            title: {
            text: '',
            x: -20 //center
            },
            xAxis: {
            categories: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun',
            'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic']
            },
            yAxis: {
            title: {
            text: ''
            },
            plotLines: [{
            value: 0,
            width: 1,
            color: '#808080'
            }]
            },
            tooltip: {
            valuePrefix: '$'
            },
            legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
            },
            series: [{
            name: 'Uno',
            data: [7000, 6900, 9500, 14500, 18200, 21500, 25200, 26500, 23300, 18300, 13900, 9600]
            }, {
            name: 'Dos',
            data: [200, 800, 5700, 11300, 17000, 22000, 24800, 24100, 20100, 14100, 8600, 2500]
            }]
            });

            var mopResume = ${raw(segmentoHistorialDeCredito.graficaMopJ)};
            $('#graficaMop').highcharts({
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
            name: 'Mop01',
            y: mopResume.porcMop01
            }, {
            name: 'Mop02',
            y: mopResume.porcMop02
            }, {
            name: 'Mop03',
            y: mopResume.porcMop03
            },
            {
            name: 'Mop04',
            y: mopResume.porcMop04
            }
            ,
            {
            name: 'Mop05',
            y: mopResume.porcMop05
            }
            ,
            {
            name: 'Mop06',
            y: mopResume.porcMop06
            }
            ,
            {
            name: 'Mop07',
            y: mopResume.porcMop07
            }
            ,
            {
            name: 'Mop96',
            y: mopResume.porcMop96
            }
            ,
            {
            name: 'Mop97',
            y: mopResume.porcMop97
            }
            ,
            {
            name: 'Mop99',
            y: mopResume.porcMop99
            }
            ]
            }]
            });

            var desgloseTipoCredito = ${raw(segmentoHistorialDeCredito.graficaDesglocePrestamosPorTipoCreditoJ)};
            $('#graficaPrestamosSeg').highcharts({
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
          //data: [ {name: 'PDV A',y: 56.33}, {name: 'PDV B',y: 24.03}, {name: 'PDV C',y: 19.64}]
            data: desgloseTipoCredito
            }]
            });
            $('#graficaDesglose').highcharts({
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
            data: [ {name: 'PDV A',y: 56.33}, {name: 'PDV B',y: 24.03}, {name: 'PDV C',y: 19.64}]
            }]
            });



            
            var desglose = ${raw(segmentoHistorialDeCredito.graficaDesglocePrestamosJ)};
            $('#graficaPrestamos').highcharts({
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
          //data: [ {name: 'PDV A',y: 56.33}, {name: 'PDV B',y: 24.03}, {name: 'PDV C',y: 19.64}]
            data: desglose
            }]
            });
            $('#graficaDesglose').highcharts({
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
            data: [ {name: 'PDV A',y: 56.33}, {name: 'PDV B',y: 24.03}, {name: 'PDV C',y: 19.64}]
            }]
            });

            $('#graficaCapacidad').highcharts({
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
            var ingresoVsPagos = ${raw(segmentoHistorialDeCredito.graficaIngvsPagosBuroJ)};
            $('#graficaIngPagos').highcharts({
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
                y: ingresoVsPagos.totalIngreso,
                }, {
                name: 'Pagos',
                y: ingresoVsPagos.totalPago
                }]
                }]
                });
                });

            
        </script>
        <title>Sample title</title>
    </head>
    <body>
        <input type="hidden" id="opcionMenu" value="2">
        <section class="container">
            <div class="col12 clearFix">
                <div class="autoMargin radius2 solicitudWhiteBox">
                    <div>
                        <ul class="clearFix">
                            <li class="floatLeft paddingTop7 paddingLeft5 paddingRight5">
                                <a href="#" title="SOLICITUDES" class="displayInline font24 fontWeight700 darkBluetitle paddingTop17 paddingBottom17 paddingLeft20">SOLICITUD</a>
                            </li>
                            <li class="floatLeft paddingLeft5 paddingRight5">
                                <span class="slash">/</span>
                                <a href="#" title="Usuario" class="displayInline font20 fontWeight500 darkBluetitle">JORGE PEREZ LOPEZ </a>
                            </li>
                            <li class="floatLeft paddingLeft5 paddingRight5">
                                <span class="slash">/</span>
                                <a href="#" title="Valor" class="displayInline font20 fontWeight500 darkBluetitle">NISSAN MÁXIMA - $350,000.00</a>
                            </li>
                        </ul>
                    </div>
                    <div class="radius2 lightGrayBG autoMargin" style='position: static;'>
                        <ul class="clearFix marginLeft10 solicitude_submenu">
                            <li class="floatLeft marginLeft8">
                                <a id="datosGeneralesButton" href='#datosGenerales' onclick="mostrarTab('anclas');" class="ancla opcionMenuSolicitud displayInline font14 fontWeight300 blueButton paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Datos Generales</a>
                            </li>
                            <li class="floatLeft">
                                <a id="capacidadDePagoButton" href='#capacidadDePago' onclick="mostrarTab('anclas');" class="ancla opcionMenuSolicitud displayInline font14 fontWeight300 gray paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Capacidad de Pago</a>
                            </li>
                            <li class="floatLeft">
                                <a id="historialCrediticioButton" href='#historialCrediticio' onclick="mostrarTab('anclas');" class="ancla opcionMenuSolicitud displayInline font14 fontWeight300 gray paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Historial Crediticio</a>
                            </li>
                            <li class="floatLeft">
                                <a id="scoreButton" href='#score' onclick="mostrarTab('anclas');" class="ancla opcionMenuSolicitud displayInline font14 fontWeight300 gray paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Score</a>
                            </li>
                            <li class="floatLeft">
                                <a id="documentacionButton" onclick="mostrarTab('documentacion');" class="opcionMenuSolicitud displayInline font14 fontWeight300 gray paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Documentación</a>
                            </li>
                            <li class="floatLeft">
                                <a id="visitaOcularButton" onclick="mostrarTab('visitaOcular');" class="opcionMenuSolicitud displayInline font14 fontWeight300 gray paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Visita Ocular</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </section>
        <div class="solicitudTab" id='anclas'>
            <div id='datosGenerales'>
                <section class="container width990">
                    <div class="padding20">
                        <h1 class="darkBluetitle font18 fontWeight600 letterspacing2">1. DATOS GENERALES</h1>
                    </div>
                    <div class="col12 clearFix">
                        <div class="col4  col6-tab col12-mob floatLeft">
                            <div class="solicitudWhiteBox radius2 paddingBottom10 marginBottom15 mobileAside10">
                                <div class="clearFix">
                                    <div class="marginRight20 clearFix">
                                        <div class="floatLeft paddingLeft15">
                                            <p class="font14 fontWeight500 letterspacing1 gray paddingTop20">IDENTIFICACIÓN OFICIAL</p>
                                        </div>
                                        <div class="floatRight paddingTop15">
                                            <img src="${resource(dir:'images', file:'expand.png')}" alt="expand" title="expand"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="marginTop19 paddingAside15">
                                    <div class="grayBox autoMargin clearFix">
                                        <div class="lightGrayBG width67 floatLeft marginLeft15 marginTop65">
                                            <img class="userImage" src="${resource(dir:'images', file:'user.png')}" alt="user" />
                                        </div>
                                    </div>
                                </div>
                                <div class="marginTop19 marginBottom14 paddingAside15">
                                    <div class="grayBox autoMargin">
                                        <div class="paddingTop110">
                                            <div class="height47 colorGrayBG"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="solicitudWhiteBox radius2 paddingBottom10 marginBottom20 mobileAside10">
                                <div class="clearFix">
                                    <div class="marginRight20 clearFix">
                                        <div class="floatLeft paddingLeft15">
                                            <p class="font14 fontWeight500 letterspacing1 gray paddingTop20">COMPROBANTE DE DOMICILIO</p>
                                        </div>
                                        <div class="floatRight paddingTop15">
                                            <img src="${resource(dir:'images', file:'expand.png')}" alt="expand" title="expand"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="marginTop19 marginBottom14 paddingAside15">
                                    <div class="grayBox autoMargin clearFix height329">
                                        <div class="floatLeft marginLeft15 marginTop65">
                                            <img class="phoneImage" src="${resource(dir:'images', file:'oldphone.png')}" alt="phone" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col8 col6-tab col12-mob floatLeft clearFix">
                            <div class="col6 col12-tab floatLeft">
                                <div class="boxMargins solicitudWhiteBox radius2 paddingBottom12 marginBottom20">
                                    <div class="navyBg radius2">
                                        <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10">INFORMACIÓN BÁSICA</p>
                                    </div>
                                    <div class="marginLeft32">
                                        <p class="font12 fontWeight500 gray paddingTop10">APLICANTE</p>
                                        <p class="font18 fontWeight500 darkBluetitle">Jorge Perez Martinez</p>
                                    </div>
                                    <div class="paddingLeft30 lightGrayBG">
                                        <p class="font12 fontWeight500 gray paddingTop10">MAIL</p>
                                        <p class="font18 fontWeight500 darkBluetitle">jperezm@gmail.com</p>
                                    </div>
                                    <div class="marginLeft32">
                                        <p class="font12 fontWeight500 gray paddingTop10">FECHA DE NACIMIENTO</p>
                                        <p class="font18 fontWeight500 darkBluetitle">12 / 12 / 1987</p>
                                    </div>
                                    <div class="paddingLeft30 lightGrayBG">
                                        <p class="font12 fontWeight500 gray paddingTop10">NACIONALIDAD</p>
                                        <p class="font18 fontWeight500 darkBluetitle">Mexicana</p>
                                    </div>
                                    <div class="marginLeft32">
                                        <p class="font12 fontWeight500 gray paddingTop10">EDAD</p>
                                        <p class="font18 fontWeight500 darkBluetitle">28 Años</p>
                                    </div>
                                    <div class="paddingLeft30 lightGrayBG">
                                        <p class="font12 fontWeight500 gray paddingTop10">ESTADO CIVIL</p>
                                        <p class="font18 fontWeight500 darkBluetitle">Soltero</p>
                                    </div>
                                    <div class="marginLeft32">
                                        <p class="font12 fontWeight500 gray paddingTop10">ESCOLARIDAD</p>
                                        <p class="font18 fontWeight500 darkBluetitle">Universitario</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col6 col12-tab floatLeft">
                                <div class="boxMargins solicitudWhiteBox radius2 paddingBottom12 marginBottom20">
                                    <div class="navyBg radius2">
                                        <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18  paddingTop15 paddingBottom10">DOMICILIO</p>
                                    </div>
                                    <div class="marginLeft32">
                                        <p class="font12 fontWeight500 gray paddingTop10">DIRECCIÓN</p>
                                        <p class="font18 fontWeight500 darkBluetitle">Av. Orizaba</p>
                                    </div>
                                    <div class="paddingLeft30 lightGrayBG">
                                        <p class="font12 fontWeight500 gray paddingTop10">NÚMERO EXTERIOR</p>
                                        <p class="font18 fontWeight500 darkBluetitle">234</p>
                                    </div>
                                    <div class="marginLeft32">
                                        <p class="font12 fontWeight500 gray paddingTop10">NÚMERO INTERIOR</p>
                                        <p class="font18 fontWeight500 darkBluetitle">123</p>
                                    </div>
                                    <div class="paddingLeft30 lightGrayBG">
                                        <p class="font12 fontWeight500 gray paddingTop10">COLONIA</p>
                                        <p class="font18 fontWeight500 darkBluetitle">Hipódromo</p>
                                    </div>
                                    <div class="marginLeft32">
                                        <p class="font12 fontWeight500 gray paddingTop10">DELEGACIÓN O MUNICIPIO</p>
                                        <p class="font18 fontWeight500 darkBluetitle">Cuauhtemoc</p>
                                    </div>
                                    <div class="paddingLeft30 lightGrayBG">
                                        <p class="font12 fontWeight500 gray paddingTop10">CÓDIGO POSTAL</p>
                                        <p class="font18 fontWeight500 darkBluetitle">06100</p>
                                    </div>
                                    <div class="marginLeft32">
                                        <p class="font12 fontWeight500 gray paddingTop10">ESTADO</p>
                                        <p class="font18 fontWeight500 darkBluetitle">Distrito Federal</p>
                                    </div>
                                </div>
                            </div>

                            <div class="col6 col12-tab floatLeft">
                                <div class="boxMargins solicitudWhiteBox radius2 paddingBottom12 marginBottom20">
                                    <div class="navyBg radius2">
                                        <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18  paddingTop15 paddingBottom10">REFERENCIA A</p>
                                    </div>
                                    <div class="marginLeft32">
                                        <p class="font12 fontWeight500 gray paddingTop10">NOMBRE</p>
                                        <p class="font18 fontWeight500 darkBluetitle">Joseph S.</p>
                                    </div>
                                    <div class="paddingLeft30 lightGrayBG">
                                        <p class="font12 fontWeight500 gray paddingTop10">TELÉFONO CELULAR</p>
                                        <p class="font18 fontWeight500 darkBluetitle">55 4123 1232</p>
                                    </div>
                                    <div class="marginLeft32">
                                        <p class="font12 fontWeight500 gray paddingTop10">RELACIÓN</p>
                                        <p class="font18 fontWeight500 darkBluetitle">FAMILIAR</p>
                                    </div>
                                    <div class="paddingLeft30 lightGrayBG clearFix paddingTop10">
                                        <div class="rateyo"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col6 col12-tab floatLeft">
                                <div class="boxMargins solicitudWhiteBox radius2 paddingBottom12 marginBottom20">
                                    <div class="navyBg radius2">
                                        <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18  paddingTop15 paddingBottom10">REFERENCIA B</p>
                                    </div>
                                    <div class="marginLeft32">
                                        <p class="font12 fontWeight500 gray paddingTop10">NOMBRE</p>
                                        <p class="font18 fontWeight500 darkBluetitle">Joseph S.</p>
                                    </div>
                                    <div class="paddingLeft30 lightGrayBG">
                                        <p class="font12 fontWeight500 gray paddingTop10">TELÉFONO CELULAR</p>
                                        <p class="font18 fontWeight500 darkBluetitle">55 4123 1232</p>
                                    </div>
                                    <div class="marginLeft32">
                                        <p class="font12 fontWeight500 gray paddingTop10">RELACIÓN</p>
                                        <p class="font18 fontWeight500 darkBluetitle">FAMILIAR</p>
                                    </div>
                                    <div class="paddingLeft30 lightGrayBG clearFix paddingTop10">
                                        <div class="rateyo"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col6 col12-tab floatLeft">
                                <div class="boxMargins solicitudWhiteBox radius2 paddingBottom12 marginBottom20">
                                    <div class="navyBg radius2">
                                        <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18  paddingTop15 paddingBottom10">REFERENCIA C</p>
                                    </div>
                                    <div class="marginLeft32">
                                        <p class="font12 fontWeight500 gray paddingTop10">NOMBRE</p>
                                        <p class="font18 fontWeight500 darkBluetitle">Joseph S.</p>
                                    </div>
                                    <div class="paddingLeft30 lightGrayBG">
                                        <p class="font12 fontWeight500 gray paddingTop10">TELÉFONO CELULAR</p>
                                        <p class="font18 fontWeight500 darkBluetitle">55 4123 1232</p>
                                    </div>
                                    <div class="marginLeft32">
                                        <p class="font12 fontWeight500 gray paddingTop10">RELACIÓN</p>
                                        <p class="font18 fontWeight500 darkBluetitle">FAMILIAR</p>
                                    </div>
                                    <div class="paddingLeft30 lightGrayBG clearFix paddingTop10">
                                        <div class="rateyo"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col6 col12-tab floatLeft">
                                <div class="boxMargins solicitudWhiteBox radius2 paddingBottom12 marginBottom20">
                                    <div class="navyBg radius2">
                                        <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18  paddingTop15 paddingBottom10">CONTACTO</p>
                                    </div>
                                    <div class="marginLeft32">
                                        <p class="font12 fontWeight500 gray paddingTop10">TELÉFONO FIJO</p>
                                        <p class="font18 fontWeight500 darkBluetitle">1231 1231</p>
                                    </div>
                                    <div class="paddingLeft30 lightGrayBG">
                                        <p class="font12 fontWeight500 gray paddingTop10">TELÉFONO CELULAR</p>
                                        <p class="font18 fontWeight500 darkBluetitle">55 4123 1232</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
            <div id='capacidadDePago'>
                <section class="container">
                    <div class="padding20">
                        <h1 class="darkBluetitle font18 fontWeight600 letterspacing2">2. CAPACIDAD DE PAGO</h1>
                    </div>
                    <div class="col12 clearFix">
                        <div class="col4 col6-tab col12-mob floatLeft">
                            <div class="solicitudWhiteBox radius2 mobileAside10 paddingBottom15 marginBottom20">
                                <div class="clearFix">
                                    <div class="marginRight20 clearFix">
                                        <div class="floatLeft paddingLeft15">
                                            <p class="font14 fontWeight500 letterspacing1 gray paddingTop20">COMPROBANTE DE INGRESOS</p>
                                        </div>
                                        <div class="floatRight paddingTop15">
                                            <img src="${resource(dir:'images', file:'expand.png')}" alt="expand" title="expand"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="marginTop19 marginBottom14">
                                    <div class="grayBox autoMargin clearFix height403"></div>
                                </div>
                            </div>
                        </div>
                        <div class="col8 col6-tab col12-mob floatLeft clearFix">
                            <div class="col6 col12-tab floatLeft">
                                <div class="boxMargins solicitudWhiteBox radius2 paddingBottom12">
                                    <div class="navyBg radius2">
                                        <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10">EMPLEO</p>
                                    </div>
                                    <div class="marginLeft32">
                                        <p class="font12 fontWeight500 gray paddingTop10">EMPRESA</p>
                                        <p class="font18 fontWeight500 darkBluetitle">Kosmos</p>
                                    </div>
                                    <div class="paddingLeft30 lightGrayBG">
                                        <p class="font12 fontWeight500 gray paddingTop10">PUESTO</p>
                                        <p class="font18 fontWeight500 darkBluetitle">Front-End Developer</p>
                                    </div>
                                    <div class="marginLeft32">
                                        <p class="font12 fontWeight500 gray paddingTop10">CONTRATO</p>
                                        <p class="font18 fontWeight500 darkBluetitle">Nómina</p>
                                    </div>
                                    <div class="paddingLeft30 lightGrayBG">
                                        <p class="font12 fontWeight500 gray paddingTop10">TIPO DE CONTRATO</p>
                                        <p class="font18 fontWeight500 darkBluetitle">Indefinido</p>
                                    </div>
                                    <div class="paddingLeft30">
                                        <p class="font12 fontWeight500 gray paddingTop10">ANTIGUEDAD EN EL EMPLEO</p>
                                        <p class="font18 fontWeight500 darkBluetitle">4 Años</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col6 col12-tab floatLeft">
                                <div class="boxMargins solicitudWhiteBox radius2 paddingBottom12">
                                    <div class="navyBg radius2">
                                        <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10">CAPACIDAD DE PAGO</p>
                                    </div>
                                    <div class="marginLeft32">
                                        <p class="font12 fontWeight500 gray paddingTop10">TOTAL DE PERCEPCIÓN</p>
                                        <p class="font18 fontWeight500 darkBluetitle">$12,000.00</p>
                                    </div>
                                    <div class="paddingLeft30 lightGrayBG">
                                        <p class="font12 fontWeight500 gray paddingTop10">TOTAL DE REDUCCIÓN</p>
                                        <p class="font18 fontWeight500 darkBluetitle">$12,000.00</p>
                                    </div>
                                    <div class="marginLeft32">
                                        <p class="font12 fontWeight500 gray paddingTop10">NETO A PAGAR</p>
                                        <p class="font18 fontWeight500 darkBluetitle">$12,000.00</p>
                                    </div>
                                    <div class="paddingLeft30 lightGrayBG">
                                        <p class="font12 fontWeight500 gray paddingTop10">NSS</p>
                                        <p class="font18 fontWeight500 darkBluetitle">$12,000.00</p>
                                    </div>
                                    <div class="marginLeft32">
                                        <p class="font12 fontWeight500 gray paddingTop10">-</p>
                                        <p class="font18 fontWeight500 darkBluetitle">-</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col6 col12-tab floatLeft">
                                <div class="boxMargins solicitudWhiteBox radius2 paddingBottom12 marginTop20">
                                    <div class="navyBg radius2">
                                        <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10">INGRESOS</p>
                                    </div>
                                    <div class="marginLeft32">
                                        <p class="font12 fontWeight500 gray paddingTop10">INGRESO NETO</p>
                                        <p class="font18 fontWeight500 darkBluetitle">$12,000.00</p>
                                    </div>
                                    <div class="paddingLeft30 lightGrayBG">
                                        <p class="font12 fontWeight500 gray paddingTop10">TOTAL DE PERCEPCIÓN</p>
                                        <p class="font18 fontWeight500 darkBluetitle">$12,000.00</p>
                                    </div>
                                </div>
                            </div>
                        </div>


                    </div>
                </section>
                <section class="container">
                    <div class="col12 clearFix">
                        <div class="col6 col12-mob  floatLeft">
                            <div class="mobileAside10 solicitudWhiteBox height251 radius2 paddingBottom12 marginBottom20 ">
                                <div class="navyBg radius2">
                                    <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10">INGRESOS VS PAGOS</p>
                                </div>
                                <div id="graficaIvsP" style="min-width: 310px; height: 200px; margin: 0 auto"></div>
                            </div>
                        </div>
                        <div class="col6 col12-mob floatLeft">
                            <div class="boxMargins solicitudWhiteBox height251 radius2 paddingBottom12 marginBottom20">
                                <div class="navyBg radius2">
                                    <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10">DESGLOSE DE GASTOS</p>
                                </div>
                                <div id="graficaDesglose" style="min-width: 310px; height: 200px; margin: 0 auto"></div>
                            </div>
                        </div>
                        <div class="col6 col12-mob floatLeft">
                            <div class="mobileAside10 solicitudWhiteBox height251 radius2 paddingBottom12 marginBottom20" >
                                <div class="navyBg radius2">
                                    <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10">MÁXIMA CAPACIDAD DE PAGO</p>
                                </div>
                                <div id="graficaCapacidad" style="min-width: 310px; height: 200px; margin: 0 auto"></div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
            <div id='historialCrediticio'>
                <section class="container">
                    <div class="padding20">
                        <h1 class="darkBluetitle font18 fontWeight600 letterspacing2">3. HISTORIAL DE CRÉDITO</h1>
                    </div>
                    <div class="col12 clearFix">
                        <div class="col4 col6-tab col12-mob floatLeft">
                            <div class="solicitudWhiteBox radius2 mobileAside10 paddingBottom15">
                                <div class="clearFix ">
                                    <div class="marginRight20 clearFix">
                                        <div class="floatLeft paddingLeft15">
                                            <p class="font14 fontWeight500 letterspacing1 gray paddingTop20">BURÓ DE CRÉDITO</p>
                                        </div>
                                        <div class="floatRight paddingTop15">
                                            <img src="${resource(dir:'images', file:'expand.png')}" alt="expand" title="expand"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="marginTop19 marginBottom14">
                                    <div class="grayBox autoMargin clearFix height403"></div>
                                </div>
                            </div>
                        </div>
                        <div class="col8 col6-tab col12-mob floatLeft clearFix">
                            <div class="col6 col12-tab floatLeft">
                                <div class="boxMargins solicitudWhiteBox radius2 paddingBottom12">
                                    <div class="navyBg radius2 ">
                                        <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10">HISTORIAL CREDITICIO</p>
                                    </div>
                                    <div class="marginLeft32">
                                        <p class="font12 fontWeight500 gray paddingTop10">SCORE</p>
                                        <p class="font18 fontWeight500 darkBluetitle">${segmentoHistorialDeCredito?.reporteBuro?.score}</p>
                                    </div>
                                    <div class="paddingLeft30 lightGrayBG">
                                        <p class="font12 fontWeight500 gray paddingTop10">MOP MAS ALTO</p>
                                        <p class="font18 fontWeight500 darkBluetitle">${segmentoHistorialDeCredito?.reporteBuro?.mopMasAltoDesc}</p>
                                    </div>
                                    <div class="marginLeft32">
                                        <p class="font12 fontWeight500 gray paddingTop10">DESTINO</p>
                                        <p class="font18 fontWeight500 darkBluetitle">${segmentoHistorialDeCredito?.reporteBuro?.destino}</p>
                                    </div>
                                    <div class="paddingLeft30 lightGrayBG">
                                        <p class="font12 fontWeight500 gray paddingTop10">JUICIOS AP</p>
                                        <p class="font18 fontWeight500 darkBluetitle">${segmentoHistorialDeCredito?.reporteBuro?.juicios}</p>
                                    </div>
                                    <div class="marginLeft32">
                                        <p class="font12 fontWeight500 gray paddingTop10">SALDO ACTUAL</p>
                                        <p class="font18 fontWeight500 darkBluetitle">${segmentoHistorialDeCredito?.reporteBuro?.totalSaldoActual}</p>
                                    </div>
                                    <div class="paddingLeft30 lightGrayBG">
                                        <p class="font12 fontWeight500 gray paddingTop10">SALDO VENCIDO</p>
                                        <p class="font18 fontWeight500 darkBluetitle">${segmentoHistorialDeCredito?.reporteBuro?.totalSaldosVencidos}</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col6 col12-tab floatLeft">
                                <div class="boxMargins solicitudWhiteBox radius2 paddingBottom12">
                                    <div class="navyBg radius2 ">
                                        <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10">HISTORIAL CREDITICIO</p>
                                    </div>
                                    <div class="marginLeft32">
                                        <p class="font12 fontWeight500 gray paddingTop10">FECHA DEL ULTIMO ATRAZO</p>
                                        <p class="font18 fontWeight500 darkBluetitle">00000000</p>
                                    </div>
                                    <div class="paddingLeft30 lightGrayBG">
                                        <p class="font12 fontWeight500 gray paddingTop10">MONTO DEL ULTIMO ATRAZO</p>
                                        <p class="font18 fontWeight500 darkBluetitle">00000000</p>
                                    </div>
                                    <div class="marginLeft32">
                                        <p class="font12 fontWeight500 gray paddingTop10">PAGO A REALIZAR</p>
                                        <p class="font18 fontWeight500 darkBluetitle">${segmentoHistorialDeCredito?.reporteBuro?.pagoARealizar}</p>
                                    </div>
                                    <div class="paddingLeft30 lightGrayBG">
                                        <p class="font12 fontWeight500 gray paddingTop10">CRÉDITO MÁS ALTO</p>
                                        <p class="font18 fontWeight500 darkBluetitle">${segmentoHistorialDeCredito?.reporteBuro?.creditoMasAlto}</p>
                                    </div>
                                    <div class="marginLeft32">
                                        <p class="font12 fontWeight500 gray paddingTop10">CUENTAS AP</p>
                                        <p class="font18 fontWeight500 darkBluetitle">${segmentoHistorialDeCredito?.reporteBuro?.numeroCuentas} </p>
                                    </div>
                                    <div class="paddingLeft30 lightGrayBG">
                                        <p class="font12 fontWeight500 gray paddingTop10">CONSULTAS</p>
                                        <p class="font18 fontWeight500 darkBluetitle">${segmentoHistorialDeCredito?.reporteBuro?.numeroSolicitudesInformeBuro}</p>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </section>
                <section class="container clearFix">
                    <div class="col12">
                        <div class="col6 col12-mob floatLeft">
                            <div class="mobileAside10 solicitudWhiteBox height251 radius2 paddingBottom12 marginTop10 marginBottom1">
                                <div class="navyBg radius2">
                                    <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10">DESGLOSE DE PRESTAMOS POR OTORGANTE</p>
                                </div>
                                <div id="graficaPrestamos" style="min-width: 310px; height: 200px; margin: 0 auto"></div>
                            </div>
                        </div>
                        <div class="col6 col12-mob floatLeft">
                            <div class="boxMargins solicitudWhiteBox height251 radius2 paddingBottom12 marginTop10 marginBottom15">
                                <div class="navyBg radius2">
                                    <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10">DESGLOSE DE PRESTAMOS POR TIPO DE CREDITO</p>
                                </div>
                                <div id="graficaPrestamosSeg" style="min-width: 310px; height: 200px; margin: 0 auto"></div>
                                
                            </div>
                        </div>
                        <div class="col6 col12-mob floatLeft">
                            <div class="mobileAside10 solicitudWhiteBox height251 radius2 paddingBottom12 marginTop10 marginBottom1">
                                <div class="navyBg radius2">
                                    <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10">INGRESOS VS. PAGOS EN BURÓ</p>
                                </div>
                                <div id="graficaIngPagos" style="min-width: 310px; height: 200px; margin: 0 auto"></div>
                            </div>
                        </div>
                        <div class="col6 col12-mob floatLeft">
                            <div class="boxMargins solicitudWhiteBox height251 radius2 paddingBottom12 marginTop10 marginBottom1">
                                <div class="navyBg radius2">
                                    <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10">% EN MOP</p>
                                </div>
                                <div id="graficaMop" style="min-width: 310px; height: 200px; margin: 0 auto"></div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
            <div id='score'>
                <section class="container marginTop12 clearFix">
                    <div class="col12 clearFix">
                        <div class="padding20">
                            <h1 class="darkBluetitle font18 fontWeight600 letterspacing2">4. SCORE</h1>
                        </div>
                        <div class="col6 col12-mob floatLeft">
                            <div class="mobileAside10 solicitudWhiteBox radius2 paddingBottom12 marginBottom20">
                                <div class="navyBg radius2 ">
                                    <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10">VERIFICACIÓN</p>
                                </div>
                                <div class="marginLeft32 clearFix">
                                    <p class="font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft">VERIFICACIÓN DE IDENTIDAD</p>
                                    <div class="floatRight">
                                        <img class="paddingTop10 paddingRight10" src="${resource(dir:'images', file:'green-icon.png')}" alt="green-icon"/>
                                    </div>
                                </div>
                                <div class="paddingLeft30 lightGrayBG clearFix">
                                    <p class="font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft">VERIFICACIÓN DE DOMICILIO</p>
                                    <div class="floatRight">
                                        <img class="paddingTop10 paddingRight10" src="${resource(dir:'images', file:'red-icon.png')}" alt="red-icon"/>
                                    </div>
                                </div>
                                <div class="marginLeft32 clearFix">
                                    <p class="font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft">CAPACIDAD DE PAGO</p>
                                    <div class="floatRight">
                                        <img class="paddingTop10 paddingRight10" src="${resource(dir:'images', file:'green-icon.png')}" alt="green-icon"/>
                                    </div>
                                </div>
                                <div class="paddingLeft30 lightGrayBG clearFix">
                                    <p class="font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft">HISTORIAL DE CRÉDITO</p>
                                    <div class="floatRight">
                                        <img class="paddingTop10 paddingRight10" src="${resource(dir:'images', file:'green-icon.png')}" alt="green-icon"/>
                                    </div>
                                </div>
                                <div class="marginLeft32 clearFix">
                                    <p class="font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft">SIN ALERTAS DE FRAUDE</p>
                                    <div class="floatRight">
                                        <img class="paddingTop10 paddingRight10" src="${resource(dir:'images', file:'green-icon.png')}" alt="green-icon"/>
                                    </div>
                                </div>
                                <div class="paddingLeft30 lightGrayBG clearFix">
                                    <p class="font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft">REFERENCIAS</p>
                                    <div class="floatRight">
                                        <img class="paddingTop10 paddingRight10" src="${resource(dir:'images', file:'green-icon.png')}" alt="green-icon"/>
                                    </div>
                                </div>
                                <div class="paddingAside15">
                                    <div class="colorGreen autoMargin marginTop12 radius4">
                                        <p class="colorWhite fontWeighht700 font25 center paddingTop15 paddingBottom15">SCORE 543</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col6 col12-mob floatRight">
                            <div class="mobileAside10">
                                <a onclick="mostrarModal('loginAutorizacion');" id="autorizarCredito">
                                    <div class="width400 blueButton radius2 autoCenter marginBottom20">
                                        <p class="autorizar_credito letterspacing2 font18 fontWeight600 paddingTop18 paddingBottom15 center pointer">AUTORIZAR</p>
                                    </div>
                                </a>
                                <a onclick="cambiarEstatus(8);" id="solicitarVisita">
                                    <div class="width400 solicitudWhiteBox radius2 autoCenter marginBottom20">
                                        <p class="letterspacing2 font18 fontWeight600 paddingTop18 paddingBottom15 center darkBluetitle pointer">SOLICITAR VISITA</p>
                                    </div>
                                </a>
                                <a onclick="cambiarEstatus(6);" id="solicitarComplemento">
                                    <div class="width400 solicitudWhiteBox radius2 autoCenter marginBottom20">
                                        <p class="letterspacing2 font18 fontWeight600 paddingTop18 paddingBottom15 center gray pointer">SOLICITAR COMPLEMENTO</p>
                                    </div>
                                </a>
                                <a onclick="cambiarEstatus(7);" id="rechazar">
                                    <div class="width400 solicitudWhiteBox radius2 autoCenter marginBottom20">
                                        <p class="letterspacing2 font18 fontWeight600 paddingTop18 paddingBottom15 center colorRed pointer">RECHAZAR</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </section>

                <div class="solicitudLightbox hide" id="loginAutorizacion">
                    <div class="overlay"></div>
                    <div class="loginContainer creditLb">
                        <div class="dashBordBox">
                            <form action="/kosmos-app/dashboard/autorizarSolicitud" method="POST" class="loginForm gray font14">
                                <input type="hidden" name="solicitudId" value="${solicitud?.id}">
                                <p class="loginTitle font25 fontWeight500 darkBluetitle textUpper letterspacing1 center paddingAside15 marginBottom25">APROBACIÓN DE CRÉDITO</p>
                                <div class="solicitudTitle width166 autoMargin">
                                    <p class="center font14 lightGray">Para autorizar este crédito ingresa tu contraseña</p>
                                </div>
                                <div class="formContainer">
                                    <input class="inputs marginBottom30 lightGray letterspacing1 font14" type="password" placeholder="Contraseña"/>
                                    <button type="submit" class="loginButton blueButton letterspacing2 font14 pointer">AUTORIZAR</button>
                                    <button type="button" onclick="cerrarModal('loginAutorizacion');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
                                </div>
                            </form>
                        </div>
                        <div class="loginShadow"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="solicitudTab" id='documentacion' style='margin-top: 30px; display: none;'>
            <section class="container">
                <div class="clearFix width928 autoMargin">
                    <a href="#" title="" class="block width94 grayBorderBox floatLeft center marginLeft10 marginRight10 marginBottom10">
                        <img src="${resource(dir:'images', file:'pdf.svg')}" alt="PDF File">
                        <p class="lightGrayColor font12 fontWeight300">Recibo Nómina</p>
                    </a>
                    <a href="#" title="" class="block width94 grayBorderBox floatLeft center marginLeft10 marginRight10 marginBottom10">
                        <img src="${resource(dir:'images', file:'pdf.svg')}" alt="PDF File">
                        <p class="lightGrayColor font12 fontWeight300">Contrato</p>
                    </a>
                    <a href="#" title="" class="block width94 grayBorderBox floatLeft center marginLeft10 marginRight10 marginBottom10">
                        <img src="${resource(dir:'images', file:'pdf.svg')}" alt="PDF File">
                        <p class="lightGrayColor font12 fontWeight300">Linked In</p>
                    </a>
                    <a href="#" title="" class="block width94 grayBorderBox floatLeft center marginLeft10 marginRight10 marginBottom10">
                        <img src="${resource(dir:'images', file:'pdf.svg')}" alt="PDF File">
                        <p class="lightGrayColor font12 fontWeight300">Recibo Nómina</p>
                    </a>
                    <a href="#" title="" class="block width94 grayBorderBox floatLeft center marginLeft10 marginRight10 marginBottom10">
                        <img src="${resource(dir:'images', file:'pdf.svg')}" alt="PDF File">
                        <p class="lightGrayColor font12 fontWeight300">Contrato</p>
                    </a>
                    <a href="#" title="" class="block width94 grayBorderBox floatLeft center marginLeft10 marginRight10 marginBottom10">
                        <img src="${resource(dir:'images', file:'pdf.svg')}" alt="PDF File">
                        <p class="lightGrayColor font12 fontWeight300">Linked In</p>
                    </a>
                    <a href="#" title="" class="block width94 grayBorderBox floatLeft center marginLeft10 marginRight10 marginBottom10">
                        <img src="${resource(dir:'images', file:'pdf.svg')}" alt="PDF File">
                        <p class="lightGrayColor font12 fontWeight300">Recibo Nómina</p>
                    </a>
                    <a href="#" title="" class="block width94 grayBorderBox floatLeft center marginLeft10 marginRight10 marginBottom10">
                        <img src="${resource(dir:'images', file:'pdf.svg')}" alt="PDF File">
                        <p class="lightGrayColor font12 fontWeight300">Contrato</p>
                    </a>
                    <a href="#" title="" class="block width94 grayBorderBox floatLeft center marginLeft10 marginRight10 marginBottom10">
                        <img src="${resource(dir:'images', file:'pdf.svg')}" alt="PDF File">
                        <p class="lightGrayColor font12 fontWeight300">Recibo Nómina</p>
                    </a>
                    <a href="#" title="" class="block width94 grayBorderBox floatLeft center marginLeft10 marginRight10 marginBottom10">
                        <img src="${resource(dir:'images', file:'pdf.svg')}" alt="PDF File">
                        <p class="lightGrayColor font12 fontWeight300">Contrato</p>
                    </a>
                    <a href="#" title="" class="block width94 grayBorderBox floatLeft center marginLeft10 marginRight10 marginBottom10">
                        <img src="${resource(dir:'images', file:'pdf.svg')}" alt="PDF File">
                        <p class="lightGrayColor font12 fontWeight300">Linked In</p>
                    </a>
                    <a href="#" title="" class="block width94 grayBorderBox floatLeft center marginLeft10 marginRight10 marginBottom10">
                        <img src="${resource(dir:'images', file:'pdf.svg')}" alt="PDF File">
                        <p class="lightGrayColor font12 fontWeight300">Recibo Nómina</p>
                    </a>
                    <a href="#" title="" class="block width94 grayBorderBox floatLeft center marginLeft10 marginRight10 marginBottom10">
                        <img src="${resource(dir:'images', file:'pdf.svg')}" alt="PDF File">
                        <p class="lightGrayColor font12 fontWeight300">Contrato</p>
                    </a>
                    <a href="#" title="" class="block width94 grayBorderBox floatLeft center marginLeft10 marginRight10 marginBottom10">
                        <img src="${resource(dir:'images', file:'pdf.svg')}" alt="PDF File">
                        <p class="lightGrayColor font12 fontWeight300">Linked In</p>
                    </a>
                </div>
            </section>
        </div>
        <div class="solicitudTab" id='visitaOcular' style='display: none;'>
            <section class="container marginTop10 marginBottom10">
                <div class="clearFix autoMargin">
                    <h3 class="floatLeft darkBluetitle marginLeft30 marginTop10">1. VERIFICACIÓN DE DOMICILIO.</h3>
                    <a href="#" title="VERIFICACIÓN EXITOSA" class="floatLeft block colorGreen radius100 colorWhite letterspacing1 paddingTop10 paddingRight30 paddingBottom10 paddingLeft30 marginLeft30">VERIFICACIÓN EXITOSA</a>
                </div>
            </section>

            <section class="container">
                <div class="wrapper autoMargin solicitudWhiteBox clearFix">
                    <div class="col4 col12-mob floatLeft">
                        <div class="marginTop25 marginRight25 marginBottom35 marginLeft25 solicitudWhiteBox radius2">
                            <div class="navyBg radius2">
                                <p class="colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom15 paddingLeft35">DOMICILIO</p>
                            </div>
                            <div class="padding10">
                                <p class="font12 fontWeight500 gray paddingAside15">DIRECCIÓN</p>
                                <p class="font18 fontWeight500 darkBluetitle paddingAside15">Av. Orizaba</p>
                            </div>
                            <div class="lightGrayBG padding10">
                                <p class="font12 fontWeight500 gray paddingAside15">NÚMERO EXTERIOR</p>
                                <p class="font18 fontWeight500 darkBluetitle paddingAside15">234</p>
                            </div>
                            <div class="padding10">
                                <p class="font12 fontWeight500 gray paddingAside15">NÚMERO INTERIOR</p>
                                <p class="font18 fontWeight500 darkBluetitle paddingAside15">123</p>
                            </div>
                            <div class="lightGrayBG padding10">
                                <p class="font12 fontWeight500 gray paddingAside15">COLONIA</p>
                                <p class="font18 fontWeight500 darkBluetitle paddingAside15">Hipódromo</p>
                            </div>
                            <div class="padding10">
                                <p class="font12 fontWeight500 gray paddingAside15">DELEGACIÓN O MUNICIPIO</p>
                                <p class="font18 fontWeight500 darkBluetitle paddingAside15">Cuauhtemoc</p>
                            </div>
                            <div class="lightGrayBG padding10">
                                <p class="font12 fontWeight500 gray paddingAside15">CÓDIGO POSTAL</p>
                                <p class="font18 fontWeight500 darkBluetitle paddingAside15">06100</p>
                            </div>
                            <div class="padding10">
                                <p class="font12 fontWeight500 gray paddingAside15">ESTADO</p>
                                <p class="font18 fontWeight500 darkBluetitle paddingAside15">Distrito Federal</p>
                            </div>
                        </div>
                    </div>
                    <div class="col8 col12-mob floatLeft">
                        <div class="marginTop25 marginBottom35  paddingBottom12">
                            <div class="clearFix">
                                <h1 class="gray font16 fontWeight300 marginBottom15 paddingAside15">FOTOS DE LA FACHADA DE LA VIVIENDA</h1>
                                <div class="col4 col12-mob floatLeft">
                                    <div class="paddingAside15 marginBottom20">
                                        <div class="grayBorderBox center">
                                            <img src="${resource(dir:'images', file:'photo.svg')}" alt="Photo" class="paddingTop7 paddingBottom10">
                                            <p class="gray2 font12 center">FACHADA - FRENTE</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col4 col12-mob floatLeft">
                                    <div class="paddingAside15 marginBottom20">
                                        <div class="grayBorderBox center">
                                            <img src="${resource(dir:'images', file:'photo.svg')}" alt="Photo" class="paddingTop7 paddingBottom10">
                                            <p class="gray2 font12 center">FACHADA - IZQUIERDA</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col4 col12-mob floatLeft">
                                    <div class="paddingAside15 marginBottom20">
                                        <div class="grayBorderBox center">
                                            <img src="${resource(dir:'images', file:'photo.svg')}" alt="Photo" class="paddingTop7 paddingBottom10">
                                            <p class="gray2 font12 center">FACHADA - DERECHA</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="clearFix">
                                <h1 class="gray font16 fontWeight300 marginTop30 paddingAside15 marginBottom15">FOTOS DE INTERIOR DE LA VIVIENDA</h1>
                                <div class="col4 col12-mob floatLeft">
                                    <div class="paddingAside15 marginBottom20">
                                        <div class="grayBorderBox center">
                                            <img src="${resource(dir:'images', file:'photo.svg')}" alt="Photo" class="paddingTop7 paddingBottom10">
                                            <p class="gray2 font12 center">INTERIOR 1</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col4 col12-mob floatLeft">
                                    <div class="paddingAside15 marginBottom20">
                                        <div class="grayBorderBox center">
                                            <img src="${resource(dir:'images', file:'photo.svg')}" alt="Photo" class="paddingTop7 paddingBottom10">
                                            <p class="gray2 font12 center">INTERIOR 2</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col4 col12-mob floatLeft">
                                    <div class="paddingAside15 marginBottom20">
                                        <div class="grayBorderBox center">
                                            <img src="${resource(dir:'images', file:'photo.svg')}" alt="Photo" class="paddingTop7 paddingBottom10">
                                            <p class="gray2 font12 center">INTERIOR 3</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <section class="container marginTop10 marginBottom10">
                <div class="clearFix autoMargin">
                    <h3 class="floatLeft darkBluetitle marginLeft30 marginTop10">2. VERIFICACIÓN DE DOCUMENTOS</h3>
                    <a href="#" title="VERIFICACIÓN EXITOSA" class="floatLeft block buttonOrange2 radius100 colorWhite letterspacing1 paddingTop10 paddingRight30 paddingBottom10 paddingLeft30 marginLeft30">VERIFICACIÓN NO EXITOSA</a>
                </div>
            </section>

            <section class="container marginTop10">
                <div class="wrapper autoMargin solicitudWhiteBox paddingBottom35 clearFix">
                    <div class="col6 col12-mob floatLeft">
                        <div class="padding15">
                            <h1 class="gray font16 fontWeight400 marginTop30 marginLeft25 marginBottom15">IDENTIFICACIÓN OFICIAL</h1>
                            <div class="">
                                <div class="grayBG-visita radius10 height260 autoMargin clearFix">
                                    <div class="profileBG floatLeft marginLeft17 marginTop65">
                                        <img class="imgResponsive" src="${resource(dir:'images', file:'user.png')}" alt="user" />
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
                        <div class="padding15">
                            <h1 class="gray font16 fontWeight400 marginTop30 marginLeft10 marginBottom15">COMPROBANTE DE DOMICILIO</h1>
                            <div class="grayBG-visita2 height535 autoMargin BigGrayBox">
                                <div class="clearFix alert-visita">
                                    <img src="${resource(dir:'images', file:'info-red.svg')}" alt="Info-Red" class="floatLeft padding20">
                                    <p class="floatRight buttonOrange2 colorWhite radius2 alertError">El documento es diferente al que el prospecto subió originalmente.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <section class="container">
                <div class="clearFix autoMargin padding20">
                    <h3 class="floatLeft darkBluetitle">3. PREGUNTAS ESPECIFICAS PARA EL PROSPECTO</h3>
                </div>
            </section>

            <section class="container">
                <div class="wrapper autoMargin solicitudWhiteBox fullInputs clearFix padding20">
                    <div>
                        <label for="atrazo" class="block font14 gray marginBottom10 ">¿A QUÉ SE DEBE SU ATRAZO EN SU TARJETA DE CRÉDITO?</label>
                        <div>
                        </div>
                        <input type="text" placeholder="Respuesta" class="whiteBox blockAuto">
                    </div>
                    <div>
                        <label for="ingresos" class="block font14 gray marginTop15 marginBottom10 ">¿DE DONDE VIENEN SUS INGRESOS?</label>
                        <div>
                        </div>
                        <input type="text" placeholder="Respuesta" class="whiteBox blockAuto">
                    </div>
                    <div class="col5half col12-mob floatLeft">
                        <div>
                            <label for="domicilio" class="block font14 gray marginTop15 marginBottom10 ">¿CUANTAS PERSONAS HABITAN EN EL DOMICILIO?</label>
                        </div>
                        <div>
                            <input type="text" placeholder="Respuesta" class="whiteBox blockAuto">
                        </div>
                    </div>
                    <div class="col5half col12-mob floatRight">
                        <div>
                            <label for="dependientes" class="block font14 gray marginTop15 marginBottom10 ">¿CUANTOS DEPENDIENTES ECONÓMICOS TIENE?</label>
                        </div>
                        <div>
                            <input type="text" placeholder="Respuesta" class="whiteBox blockAuto">
                        </div>
                    </div>
                </div>
            </section>

            <section class="container">
                <div class="clearFix autoMargin padding20">
                    <h3 class="floatLeft darkBluetitle">4. COMENTARIOS ADICIONALES</h3>
                </div>
            </section>

            <section class="container marginBottom40">
                <div class="wrapper autoMargin solicitudWhiteBox padding20 paddingBottom35">
                    <h1 class="darkBluetitle font14 fontWeight400 marginBottom15">SI HAY ALGUN COMENTARIO IMPORTANTE PUEDE SER ANOTADO EN ESTE ESPACIO.</h1>
                    <div class="fullInputs">
                        <textarea name="name" class="whiteBox block height179 autoMargin font14 paddingTop10 paddingBottom10 paddingLeft20"></textarea>
                    </div>
                </div>
            </section>
        </div>
        <a class="back-to-top">Back to Top</a>
    </body>
</html>
