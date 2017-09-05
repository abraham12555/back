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
            background: #2d91f6 url("../images/up-arrow.png") no-repeat center 43%;
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

            iniciarPrettyPhoto();
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

            var mopResume = ${raw(segmentoHistorialDeCredito?.graficaMopJ) ?: []};
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

            var desgloseTipoCredito = ${raw(segmentoHistorialDeCredito?.graficaDesglocePrestamosPorTipoCreditoJ) ?: []};
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
            data: [ {name: 'PDV A',y: 0}, {name: 'PDV B',y: 0}, {name: 'PDV C',y: 0}]
            }]
            });




            var desglose = ${raw(segmentoHistorialDeCredito?.graficaDesglocePrestamosJ) ?: []};
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
            //data: [ {name: 'PDV A',y: 0}, {name: 'PDV B',y: 0}, {name: 'PDV C',y: 0}]
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
            data: [ {name: 'PDV A',y: 0}, {name: 'PDV B',y: 0}, {name: 'PDV C',y: 0}]
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
            y: 0
            }, {
            name: 'PDV B',
            y: 0
            }, {
            name: 'PDV C',
            y: 0
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
            y: 0,
            }, {
            name: 'Pagos',
            y: 0
            }]
            }]
            });
            var ingresoVsPagos = ${raw(segmentoHistorialDeCredito?.graficaIngvsPagosBuroJ) ?: []};
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
        <g:if test="${datosSolicitud?.solicitud}">
            <section class="container">
                <div class="col12 clearFix">
                    <div class="autoMargin radius2 solicitudWhiteBox">
                        <div class="radius2 lightGrayBG autoMargin" style='position: static;'>
                            <ul class="clearFix marginLeft10 solicitude_submenu">
                                <li class="floatLeft marginLeft8">
                                    <a id="datosGeneralesButton" href='#detalleProducto' onclick="mostrarTab('anclas');" class="ancla opcionMenuSolicitud displayInline font14 fontWeight300 blueButton paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Producto</a>
                                </li>
                                <li class="floatLeft">
                                    <a id="capacidadDePagoButton" href='#datosGenerales' onclick="mostrarTab('anclas');" class="ancla opcionMenuSolicitud displayInline font14 fontWeight300 gray paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Datos Generales </a>
                                </li>
                                <li class="floatLeft">
                                    <a id="historialCrediticioButton" href='#capacidadDePago ' onclick="mostrarTab('anclas');" class="ancla opcionMenuSolicitud displayInline font14 fontWeight300 gray paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Capacidad de Pago </a>
                                </li>
                                <li class="floatLeft">
                                    <a id="scoreButton" href='#historialCrediticio ' onclick="mostrarTab('anclas');" class="ancla opcionMenuSolicitud displayInline font14 fontWeight300 gray paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Historial Crediticio </a>
                                </li>
                                <li class="floatLeft">
                                    <a id="documentacionButton" onclick="mostrarTab('documentacion');" class="opcionMenuSolicitud displayInline font14 fontWeight300 gray paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Documentación </a>
                                </li>
                                <li class="floatLeft">
                                    <a id="detalleProductoButton" href='#score' onclick="mostrarTab('anclas');" class="ancla opcionMenuSolicitud displayInline font14 fontWeight300 gray paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer"> Score </a>
                                </li>
                                <g:if test="${datosSolicitud.verificacion}">
                                    <li class="floatLeft">
                                        <a id="visitaOcularButton" onclick="mostrarTab('visitaOcular'); iniciarPrettyPhoto();" class="opcionMenuSolicitud displayInline font14 fontWeight300 gray paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer"> Visita Ocular</a>
                                    </li>
                                </g:if>
                            </ul>
                        </div>
                    </div>
                </div>
            </section>
            <div class="solicitudTab" id='anclas'>
                <div id='detalleProducto'>
                    <section class="container width990">
                        <div class="padding20">
                            <h1 class="darkBluetitle font18 fontWeight600 letterspacing2">1. PRODUCTO SOLICITADO </h1>
                        </div>
                        <div class="col12 clearFix">
                            <div class="navyBg radius2">
                                <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10">DETALLE DEL PRODUCTO</p>
                            </div>
                            <div class="col12 col12-mob floatLeft">
                                <div class="marginLeft32 ">
                                    <p class="font12 fontWeight500 gray paddingTop10">FOLIO DE SOLICITUD</p>
                                    <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.solicitud?.folio}</p>
                                </div>
                                <div class="col6 col12-mob floatLeft">
                                    
                                    <div class="mobileAside10 solicitudWhiteBox height300 radius2 paddingBottom12 marginTop10 marginBottom1">
                                        
                                        <div class="paddingLeft30 ">
                                            <p class="font12 fontWeight500 gray paddingTop10">CLAVE DEL PRODUCTO</p>
                                            <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.productoSolicitud?.producto.claveDeProducto}</p>
                                        </div>
                                        <div class="marginLeft32 lightGrayBG">
                                            <p class="font12 fontWeight500 gray paddingTop10">PRODUCTO</p>
                                            <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.productoSolicitud?.producto}</p>
                                        </div>
                                        <div class="marginLeft32 ">
                                            <p class="font12 fontWeight500 gray paddingTop10">MONTO DEL CRÉDITO</p>
                                            <p class="font18 fontWeight500 darkBluetitle"><g:formatNumber number="${datosSolicitud.productoSolicitud?.montoDelCredito}" format="\044###,###,###.00"/></p>
                                        </div>
                                        <div class="marginLeft32 lightGrayBG ">
                                            <p class="font12 fontWeight500 gray paddingTop10">PERIODOS</p>
                                            <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.productoSolicitud?.plazos}</p>
                                        </div>
                                        <div class="marginLeft32 ">
                                            <p class="font12 fontWeight500 gray paddingTop10">  PERIODICIDAD</p>
                                            <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.productoSolicitud?.periodicidad.nombre.toUpperCase()}</p>
                                        </div>
                                        
                                    </div>
                                </div>    
                                <div class="col6 col12-mob floatLeft">
                                    <div class="mobileAside10 solicitudWhiteBox height300 radius2 paddingBottom12 marginTop10 marginBottom1">
                                        <div class="marginLeft32">
                                            <p class="font12 fontWeight500 gray paddingTop10">PAGO ${datosSolicitud.productoSolicitud?.periodicidad.nombre.toUpperCase()}</p>
                                            <p class="font18 fontWeight500 darkBluetitle"><g:formatNumber number="${datosSolicitud.productoSolicitud?.montoDelPago}" format="\044###,###,###.##"/></p>
                                        </div>
                                        <div class="marginLeft32 lightGrayBG">
                                            <p class="font12 fontWeight500 gray paddingTop10"> ${datosSolicitud.productoSolicitud?.producto.tipoDeTasa.nombre.toUpperCase()}</p>
                                            <p class="font18 fontWeight500 darkBluetitle"><g:formatNumber number="${datosSolicitud.productoSolicitud?.producto.tasaDeInteres}" format="0.## %"/> </p>
                                        </div>
                                        <div class="marginLeft32">
                                            <p class="font12 fontWeight500 gray paddingTop10">TASA DE INTERES ANUAL</p>
                                            <p class="font18 fontWeight500 darkBluetitle"><g:formatNumber number="${datosSolicitud.productoSolicitud?.producto.tasaDeInteresAnual}" format="0.## %"/></p>
                                        </div>
                                        <div class="marginLeft32 lightGrayBG">
                                            <p class="font12 fontWeight500 gray paddingTop10">MONTO DE SEGURO DE DEUDA</p>
                                            <p class="font18 fontWeight500 darkBluetitle"><g:formatNumber number="${datosSolicitud.productoSolicitud?.montoDelSeguroDeDeuda}" format="\044###,###,###.00"/></p> 
                                        </div>
                                        <div class="paddingLeft30 ">
                                            <p class="font12 fontWeight500 gray paddingTop10">MONTO SERVICIO DE ASISTENCIA</p>
                                            <p class="font18 fontWeight500 darkBluetitle"><g:formatNumber number="${datosSolicitud.productoSolicitud?.montoDeServicioDeAsistencia}" format="\044###,###,###.##"/></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>       
                <div id='datosGenerales'>
                    <section class="container width990">
                        <div class="padding20">
                            <h1 class="darkBluetitle font18 fontWeight600 letterspacing2">2. DATOS GENERALES</h1>
                        </div>
                        <div class="col12 clearFix">
                            <div class="col4  col4-tab col12-mob floatLeft">
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
                                    <% def credenciales = datosSolicitud.documentosSolicitud?.findAll { (it.tipoDeDocumento?.id == 2 || it.tipoDeDocumento?.id == 11)} %>
                                    <g:if test="${credenciales}">
                                        <g:each var='documento' in='${credenciales}' status='i'>
                                            <div class="marginTop19 paddingAside15">
                                                <div class="grayBox autoMargin clearFix">
                                                    <div class="lightGrayBG width67 floatLeft">
                                                        <a href="${createLink(controller: 'dashboard', action:'previsualizarDocumento', id: documento.id)}" rel="prettyPhoto[pp_gal]"><img src="${createLink(controller: 'dashboard', action:'previsualizarDocumento', id: documento.id)}" width="100%" height="100%" alt="${documento.tipoDeDocumento}" /></a>
                                                    </div>
                                                </div>
                                            </div>
                                        </g:each>
                                        <g:if test="${credenciales.size() == 1}">
                                            <div class="marginTop19 paddingAside15">
                                                <div class="grayBox autoMargin clearFix">
                                                    <div class="lightGrayBG width67 floatLeft marginLeft15 marginTop65">
                                                        <img class="userImage" src="${resource(dir:'images', file:'user.png')}" alt="user" />
                                                    </div>
                                                </div>
                                            </div>
                                        </g:if>
                                    </g:if>
                                    <g:else>
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
                                    </g:else>
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
                                    <% def comprobanteDomicilio = datosSolicitud.documentosSolicitud?.find { (it.tipoDeDocumento?.id == 1 || it.tipoDeDocumento?.id == 10) } %>
                                    <g:if test="${comprobanteDomicilio}">
                                        <div class="marginTop19 marginBottom14 paddingAside15">
                                            <div class="grayBox autoMargin clearFix height329">
                                                <g:if test="${(comprobanteDomicilio.rutaDelArchivo.substring(comprobanteDomicilio.rutaDelArchivo.lastIndexOf(".") + 1).toLowerCase() == "pdf")}">
                                                    <div class="floatLeft" style="margin: 30%;">
                                                        <g:link controller="dashboard" action="descargarArchivo" id="${comprobanteDomicilio.id}">
                                                            <img src="${resource(dir:'images', file:'pdf.png')}" height="40%" alt="${comprobanteDomicilio?.tipoDeDocumento?.nombre}">
                                                        </g:link>
                                                    </div>
                                                </g:if>
                                                <g:else>
                                                    <div class="floatLeft">
                                                        <a href="${createLink(controller: 'dashboard', action:'previsualizarDocumento', id: comprobanteDomicilio.id)}" rel="prettyPhoto[pp_gal]"><img src="${createLink(controller: 'dashboard', action:'previsualizarDocumento', id: comprobanteDomicilio.id)}" width="100%" height="100%" alt="${comprobanteDomicilio.tipoDeDocumento}" /></a>
                                                    </div>    
                                                </g:else>
                                            </div>
                                        </div>
                                    </g:if>
                                    <g:else>
                                        <div class="marginTop19 marginBottom14 paddingAside15">
                                            <div class="grayBox autoMargin clearFix height329">
                                                <div class="floatLeft marginLeft15 marginTop65">
                                                    <img class="phoneImage" src="${resource(dir:'images', file:'oldphone.png')}" alt="phone" />
                                                </div>
                                            </div>
                                        </div>
                                    </g:else>
                                </div>
                            </div>
                            <div class="col8 col8-tab col12-mob floatLeft clearFix">
                                <div class="col6 col12-tab floatLeft">
                                    <g:if test="${datosSolicitud.temporal}">
                                        <div class="boxMargins solicitudWhiteBox radius2 paddingBottom12 marginBottom20">
                                            <div class="navyBg radius2">
                                                <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10">INFORMACIÓN BÁSICA</p>
                                            </div>
                                            <div class="marginLeft32">
                                                <p class="font12 fontWeight500 gray paddingTop10">APLICANTE</p>
                                                <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.cliente}</p>
                                            </div>
                                            <div class="paddingLeft30 lightGrayBG">
                                                <p class="font12 fontWeight500 gray paddingTop10">MAIL</p>
                                                <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.emailCliente}</p>
                                            </div>
                                            <div class="marginLeft32">
                                                <p class="font12 fontWeight500 gray paddingTop10">FECHA DE NACIMIENTO</p>
                                                <p class="font18 fontWeight500 darkBluetitle">DATO NO CAPTURADO</p>
                                            </div>
                                            <div class="marginLeft32 lightGrayBG">
                                                <p class="font12 fontWeight500 gray paddingTop10">LUGAR DE NACIMIENTO</p>
                                                <p class="font18 fontWeight500 darkBluetitle">DATO NO CAPTURADO</p>
                                            </div>
                                            <div class="paddingLeft30 ">
                                                <p class="font12 fontWeight500 gray paddingTop10">NACIONALIDAD</p>
                                                <p class="font18 fontWeight500 darkBluetitle">DATO NO CAPTURADO</p>
                                            </div>
                                            <div class="marginLeft32 lightGrayBG">
                                                <p class="font12 fontWeight500 gray paddingTop10">EDAD</p>
                                                <p class="font18 fontWeight500 darkBluetitle">DATO NO CAPTURADO</p>
                                            </div>
                                            <div class="paddingLeft30">
                                                <p class="font12 fontWeight500 gray paddingTop10">GENERO</p>
                                                <p class="font18 fontWeight500 darkBluetitle">DATO NO CAPTURADO</p>
                                            </div>
                                            <div class="marginLeft32 lightGrayBG">
                                                <p class="font12 fontWeight500 gray paddingTop10">R.F.C.</p>
                                                <p class="font18 fontWeight500 darkBluetitle">DATO NO CAPTURADO</p>
                                            </div>
                                            <div class="paddingLeft30">
                                                <p class="font12 fontWeight500 gray paddingTop10">C.U.R.P.</p>
                                                <p class="font18 fontWeight500 darkBluetitle">DATO NO CAPTURADO</p>
                                            </div>
                                            <div class="paddingLeft30 lightGrayBG">
                                                <p class="font12 fontWeight500 gray paddingTop10">ESTADO CIVIL</p>
                                                <p class="font18 fontWeight500 darkBluetitle">DATO NO CAPTURADO</p>
                                            </div>
                                            <div class="paddingLeft30 lightGrayBG">
                                                <p class="font12 fontWeight500 gray paddingTop10">DEPENDIENTES ECONOMICOS</p>
                                                <p class="font18 fontWeight500 darkBluetitle">DATO NO CAPTURADO</p>
                                            </div>
                                        </div>
                                    </g:if>
                                    <g:else>
                                        <div class="boxMargins solicitudWhiteBox radius2 paddingBottom12 marginBottom20">
                                            <div class="navyBg radius2">
                                                <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10">INFORMACIÓN BÁSICA</p>
                                            </div>
                                            <div class="marginLeft32">
                                                <p class="font12 fontWeight500 gray paddingTop10">APLICANTE</p>
                                                <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.cliente}</p>
                                            </div>
                                            <div class="paddingLeft30 lightGrayBG">
                                                <p class="font12 fontWeight500 gray paddingTop10">MAIL</p>
                                                <p class="font18 fontWeight500 darkBluetitle">
                                                    <g:each var='mail' in='${datosSolicitud.emailCliente}' status='i'>
                                                        ${mail.direccionDeCorreo}
                                                    </g:each>
                                                </p>
                                            </div>
                                            <div class="marginLeft32">
                                                <p class="font12 fontWeight500 gray paddingTop10">FECHA DE NACIMIENTO</p>
                                                <p class="font18 fontWeight500 darkBluetitle"><g:formatDate format="dd / MM / yyyy" date="${datosSolicitud.cliente?.fechaDeNacimiento}"/></p>
                                            </div>
                                            <div class="marginLeft32 lightGrayBG">
                                                <p class="font12 fontWeight500 gray paddingTop10">LUGAR DE NACIMIENTO</p>
                                                <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.cliente.lugarDeNacimiento}</p>
                                            </div>
                                            <div class="paddingLeft30 ">
                                                <p class="font12 fontWeight500 gray paddingTop10">NACIONALIDAD</p>
                                                <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.cliente?.nacionalidad}</p>
                                            </div>
                                            <div class="marginLeft32 lightGrayBG">
                                                <p class="font12 fontWeight500 gray paddingTop10">EDAD</p>
                                                <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.edadCliente} Años</p>
                                            </div>
                                            <div class="paddingLeft30">
                                                <p class="font12 fontWeight500 gray paddingTop10">GENERO</p>
                                                <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.cliente.genero}</p>
                                            </div>
                                            <div class="marginLeft32 lightGrayBG">
                                                <p class="font12 fontWeight500 gray paddingTop10">R.F.C.</p>
                                                <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.cliente.rfc}</p>
                                            </div>
                                            <div class="paddingLeft30">
                                                <p class="font12 fontWeight500 gray paddingTop10">C.U.R.P.</p>
                                                <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.cliente.curp}</p>
                                            </div>
                                            <div class="paddingLeft30 lightGrayBG">
                                                <p class="font12 fontWeight500 gray paddingTop10">ESTADO CIVIL</p>
                                                <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.cliente.estadoCivil}</p>
                                            </div>
                                            <g:if test="${datosSolicitud.cliente.estadoCivil?.id == 2 || datosSolicitud.cliente.estadoCivil?.id == 3}">
                                                <div class="paddingLeft30">
                                                    <p class="font12 fontWeight500 gray paddingTop10">REGIMEN MATRIMONIAL</p>
                                                    <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.cliente.regimenMatrimonial}</p>
                                                </div>
                                            </g:if>
                                            <div class="paddingLeft30 lightGrayBG">
                                                <p class="font12 fontWeight500 gray paddingTop10">DEPENDIENTES ECONOMICOS</p>
                                                <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.cliente.dependientesEconomicos}</p>
                                            </div>
                                        </div>
                                    </g:else>
                                </div>
                                <div class="col6 col12-tab floatLeft">
                                    <div class="boxMargins solicitudWhiteBox radius2 paddingBottom12 marginBottom20">
                                        <div class="navyBg radius2">
                                            <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18  paddingTop15 paddingBottom10">DOMICILIO</p>
                                        </div>
                                        <div class="marginLeft32">
                                            <p class="font12 fontWeight500 gray paddingTop10">DIRECCIÓN</p>
                                            <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.direccionCliente?.calle}</p>
                                        </div>
                                        <div class="paddingLeft30 lightGrayBG">
                                            <p class="font12 fontWeight500 gray paddingTop10">NÚMERO EXTERIOR</p>
                                            <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.direccionCliente?.numeroExterior}</p>
                                        </div>
                                        <div class="marginLeft32">
                                            <p class="font12 fontWeight500 gray paddingTop10">NÚMERO INTERIOR</p>
                                            <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.direccionCliente?.numeroInterior}</p>
                                        </div>
                                        <div class="paddingLeft30 lightGrayBG">
                                            <p class="font12 fontWeight500 gray paddingTop10">COLONIA</p>
                                            <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.direccionCliente?.colonia}</p>
                                        </div>
                                        <div class="marginLeft32">
                                            <p class="font12 fontWeight500 gray paddingTop10">DELEGACIÓN O MUNICIPIO</p>
                                            <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.direccionCliente?.codigoPostal?.municipio}</p>
                                        </div>
                                        <div class="paddingLeft30 lightGrayBG">
                                            <p class="font12 fontWeight500 gray paddingTop10">CÓDIGO POSTAL</p>
                                            <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.direccionCliente?.codigoPostal?.codigo}</p>
                                        </div>
                                        <div class="marginLeft32">
                                            <p class="font12 fontWeight500 gray paddingTop10">ESTADO</p>
                                            <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.direccionCliente?.codigoPostal?.municipio?.estado}</p>
                                        </div>
                                        <div class="marginLeft32 lightGrayBG">
                                            <p class="font12 fontWeight500 gray paddingTop10">TIPO DE VIVIENDA</p>
                                            <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.direccionCliente?.tipoDeVivienda}</p>
                                        </div>
                                        <div class="marginLeft32">
                                            <p class="font12 fontWeight500 gray paddingTop10">MONTO DE LA RENTA</p>
                                            <p class="font18 fontWeight500 darkBluetitle">
                                                <g:if test="${datosSolicitud.direccionCliente?.tipoDeVivienda?.id == 1}">
                                                    <g:formatNumber number="${datosSolicitud.productoSolicitud?.montoDelCredito}" format="\044###,###,###.##"/>
                                                </g:if>
                                                <g:else>
                                                    N/A
                                                </g:else>
                                            </p>
                                        </div>
                                        <div class="marginLeft32 lightGrayBG">
                                            <p class="font12 fontWeight500 gray paddingTop10">VIVE EN ELLA DESDE</p>
                                            <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.direccionCliente?.tiempoDeVivienda}</p>
                                        </div>
                                        <div class="marginLeft32">
                                            <p class="font12 fontWeight500 gray paddingTop10">RESIDE EN LA CIUDAD DESDE</p>
                                            <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.direccionCliente?.tiempoDeEstadia}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col8 col8-tab col12-mob floatLeft clearFix">
                                <g:each var='referencia' in='${datosSolicitud.referenciasCliente}' status='i'>
                                    <div class="col6 col12-tab floatLeft">
                                        <div class="boxMargins solicitudWhiteBox radius2 paddingBottom12 marginBottom20">
                                            <div class="navyBg radius2">
                                                <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18  paddingTop15 paddingBottom10">REFERENCIA ${i+1}</p>
                                            </div>
                                            <div class="marginLeft32">
                                                <p class="font12 fontWeight500 gray paddingTop10">NOMBRE</p>
                                                <p class="font18 fontWeight500 darkBluetitle">${referencia.nombreCompleto}</p>
                                            </div>
                                            <div class="paddingLeft30 lightGrayBG">
                                                <p class="font12 fontWeight500 gray paddingTop10">TELÉFONO CELULAR</p>
                                                <p class="font18 fontWeight500 darkBluetitle">${referencia.telefonoCelular}</p>
                                            </div>
                                            <div class="marginLeft32">
                                                <p class="font12 fontWeight500 gray paddingTop10">RELACIÓN</p>
                                                <p class="font18 fontWeight500 darkBluetitle">${referencia.tipoDeReferencia?.nombre}</p>
                                            </div>
                                            <div class="paddingLeft30 lightGrayBG clearFix paddingTop10">
                                                <div id="calificacionReferencia${referencia.id}}" class="rateyo"></div>
                                            </div>
                                        </div>
                                    </div>
                                </g:each>
                                <g:if test="${!datosSolicitud.temporal}">
                                    <g:if test="${datosSolicitud.cliente.estadoCivil?.id == 2 || datosSolicitud.cliente.estadoCivil?.id == 3}">
                                        <div class="col6 col12-tab floatLeft">
                                            <div class="boxMargins solicitudWhiteBox radius2 paddingBottom12 marginBottom20">
                                                <div class="navyBg radius2">
                                                    <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10">DATOS DEL CONYUGUE</p>
                                                </div>
                                                <div class="marginLeft32">
                                                    <p class="font12 fontWeight500 gray paddingTop10">NOMBRE</p>
                                                    <p class="font18 fontWeight500 darkBluetitle">${ (datosSolicitud.cliente.nombreDelConyugue ?: "") + " " + (datosSolicitud.cliente.apellidoPaternoDelConyugue ?: "") + " " + (datosSolicitud.cliente.apellidoMaternoDelConyugue ?: "")}</p>
                                                </div>
                                                <div class="marginLeft32">
                                                    <p class="font12 fontWeight500 gray paddingTop10">FECHA DE NACIMIENTO</p>
                                                    <p class="font18 fontWeight500 darkBluetitle"><g:formatDate format="dd / MM / yyyy" date="${datosSolicitud.cliente?.fechaDeNacimientoDelConyugue}"/></p>
                                                </div>
                                                <div class="marginLeft32 lightGrayBG">
                                                    <p class="font12 fontWeight500 gray paddingTop10">LUGAR DE NACIMIENTO</p>
                                                    <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.cliente.lugarDeNacimientoDelConyugue}</p>
                                                </div>
                                                <div class="paddingLeft30 ">
                                                    <p class="font12 fontWeight500 gray paddingTop10">NACIONALIDAD</p>
                                                    <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.cliente?.nacionalidadDelConyugue}</p>
                                                </div>
                                                <div class="paddingLeft30">
                                                    <p class="font12 fontWeight500 gray paddingTop10">GENERO</p>
                                                    <p class="font18 fontWeight500 darkBluetitle">${((datosSolicitud.cliente.genero?.id == 1) ? "Mujer" : "Hombre")}</p>
                                                </div>
                                                <div class="marginLeft32 lightGrayBG">
                                                    <p class="font12 fontWeight500 gray paddingTop10">R.F.C.</p>
                                                    <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.cliente.rfcDelConyugue}</p>
                                                </div>
                                                <div class="paddingLeft30">
                                                    <p class="font12 fontWeight500 gray paddingTop10">C.U.R.P.</p>
                                                    <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.cliente.curpDelConyugue}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </g:if>
                                </g:if> 
                                <div class="col6 col12-tab floatLeft">
                                    <div class="boxMargins solicitudWhiteBox radius2 paddingBottom12 marginBottom20">
                                        <div class="navyBg radius2">
                                            <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18  paddingTop15 paddingBottom10">CONTACTO</p>
                                        </div>
                                        <g:each var='telefono' in='${datosSolicitud.telefonosCliente}' status='i'>
                                            <div class="marginLeft32 <g:if test="${i%2 != 0}"> lightGrayBG </g:if> ">
                                                <p class="font12 fontWeight500 gray paddingTop10">${telefono.tipoDeTelefono.nombre}</p>
                                                <p class="font18 fontWeight500 darkBluetitle">${telefono.numeroTelefonico}</p>
                                            </div>
                                        </g:each>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <div id='capacidadDePago'>
                    <section class="container">
                        <div class="padding20">
                            <h1 class="darkBluetitle font18 fontWeight600 letterspacing2">3. CAPACIDAD DE PAGO</h1>
                        </div>
                        <div class="col12 clearFix">
                            <div class="col4 col4-tab col12-mob floatLeft">
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
                                    <% def comprobanteDeIngresos = datosSolicitud.documentosSolicitud?.findAll { (it.tipoDeDocumento?.usoEnCotizador == true) } %>
                                    <g:if test="${comprobanteDeIngresos}">
                                        <g:each var='documento' in='${comprobanteDeIngresos}' status='i'>
                                            <div class="marginTop19 marginBottom14 paddingAside15">
                                                <div class="grayBox autoMargin clearFix height329">
                                                    <g:if test="${(documento.rutaDelArchivo.substring(documento.rutaDelArchivo.lastIndexOf(".") + 1).toLowerCase() == "pdf")}">
                                                        <div class="floatLeft" style="margin: 30%;">
                                                            <g:link controller="dashboard" action="descargarArchivo" id="${documento.id}">
                                                                <img src="${resource(dir:'images', file:'pdf.png')}" height="40%" alt="${documento?.tipoDeDocumento?.nombre}">
                                                            </g:link>
                                                        </div>
                                                    </g:if>
                                                    <g:else>
                                                        <div class="floatLeft">
                                                            <a href="${createLink(controller: 'dashboard', action:'previsualizarDocumento', id: documento.id)}" rel="prettyPhoto[pp_gal]"><img src="${createLink(controller: 'dashboard', action:'previsualizarDocumento', id: documento.id)}" width="100%" height="100%" alt="${documento.tipoDeDocumento}" /></a>
                                                        </div>
                                                    </g:else>
                                                </div>
                                            </div>
                                        </g:each>
                                    </g:if>
                                    <g:else>
                                        <div class="marginTop19 marginBottom14">
                                            <div class="grayBox autoMargin clearFix height403"></div>
                                        </div>
                                    </g:else>
                                </div>
                            </div>
                            <div class="col8 col8-tab col12-mob floatLeft clearFix">
                                <div class="col6 col12-tab floatLeft">
                                    <div class="boxMargins solicitudWhiteBox radius2 paddingBottom12">
                                        <div class="navyBg radius2">
                                            <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10">EMPLEO</p>
                                        </div>
                                        <div class="marginLeft32">
                                            <p class="font12 fontWeight500 gray paddingTop10">EMPRESA</p>
                                            <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.empleoCliente?.nombreDeLaEmpresa}</p>
                                        </div>
                                        <div class="marginLeft32 lightGrayBG">
                                            <p class="font12 fontWeight500 gray paddingTop10">OCUPACION</p>
                                            <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.empleoCliente?.ocupacion}</p>
                                        </div>
                                        <div class="marginLeft32">
                                            <p class="font12 fontWeight500 gray paddingTop10">PROFESION</p>
                                            <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.empleoCliente?.profesion}</p>
                                        </div>
                                        <div class="paddingLeft30 lightGrayBG">
                                            <p class="font12 fontWeight500 gray paddingTop10">PUESTO</p>
                                            <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.empleoCliente?.puesto}</p>
                                        </div>
                                        <div class="marginLeft32">
                                            <p class="font12 fontWeight500 gray paddingTop10">CONTRATO</p>
                                            <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.empleoCliente?.tipoDeContrato}</p>
                                        </div>
                                        <div class="paddingLeft30 lightGrayBG">
                                            <p class="font12 fontWeight500 gray paddingTop10">TIPO DE CONTRATO</p>
                                            <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.empleoCliente?.tipoDeContrato}</p>
                                        </div>
                                        <g:if test="${datosSolicitud.empleoCliente?.temporalidad}">
                                            <div class="paddingLeft30">
                                                <p class="font12 fontWeight500 gray paddingTop10">ANTIGUEDAD EN EL EMPLEO</p>
                                                <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.empleoCliente?.antiguedad} ${datosSolicitud.empleoCliente?.temporalidad}</p>
                                            </div>
                                        </g:if>
                                        <g:else>
                                            <div class="paddingLeft30">
                                                <p class="font12 fontWeight500 gray paddingTop10">LABORA DESDE</p>
                                                <p class="font18 fontWeight500 darkBluetitle">${datosSolicitud.empleoCliente?.fechaIngreso}</p>
                                            </div>
                                        </g:else>
                                    </div>
                                </div>
                                <div class="col6 col12-tab floatLeft">
                                    <div class="boxMargins solicitudWhiteBox radius2 paddingBottom12">
                                        <div class="navyBg radius2">
                                            <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10">INGRESOS</p>
                                        </div>
                                        <div class="marginLeft32">
                                            <p class="font12 fontWeight500 gray paddingTop10">INGRESOS FIJOS</p>
                                            <p class="font18 fontWeight500 darkBluetitle"><g:formatNumber number="${datosSolicitud.empleoCliente?.ingresosFijos}" format="\044###,###,###.##"/></p>
                                        </div>
                                        <div class="marginLeft32">
                                            <p class="font12 fontWeight500 gray paddingTop10">INGRESOS VARIABLES</p>
                                            <p class="font18 fontWeight500 darkBluetitle"><g:formatNumber number="${datosSolicitud.empleoCliente?.ingresosVariables}" format="\044###,###,###.##"/></p>
                                        </div>
                                        <div class="paddingLeft30 lightGrayBG">
                                            <p class="font12 fontWeight500 gray paddingTop10">TOTAL DE PERCEPCIÓN</p>
                                            <p class="font18 fontWeight500 darkBluetitle">  <g:formatNumber number="${ (datosSolicitud.empleoCliente?.ingresosFijos ?: 0) + (datosSolicitud.empleoCliente?.ingresosVariables ?: 0)}" format="\044###,###,###.##"/></p>
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
                            <h1 class="darkBluetitle font18 fontWeight600 letterspacing2">4. HISTORIAL DE CRÉDITO</h1>
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

<!-- div class="marginLeft32">
    <p class="font12 fontWeight500 gray paddingTop10">DESTINO</p>
    <p class="font18 fontWeight500 darkBluetitle">${segmentoHistorialDeCredito?.reporteBuro?.destino}</p>
</div-->
                                        <div class="paddingLeft30 ">
                                            <p class="font12 fontWeight500 gray paddingTop10">JUICIOS AP</p>
                                            <p class="font18 fontWeight500 darkBluetitle">${segmentoHistorialDeCredito?.reporteBuro?.juicios}</p>
                                        </div>
                                        <div class="marginLeft32 lightGrayBG">
                                            <p class="font12 fontWeight500 gray paddingTop10">SALDO ACTUAL</p>
                                            <p class="font18 fontWeight500 darkBluetitle">${segmentoHistorialDeCredito?.reporteBuro?.totalSaldoActual}</p>
                                        </div>
                                        <div class="paddingLeft30 ">
                                            <p class="font12 fontWeight500 gray paddingTop10">SALDO VENCIDO</p>
                                            <p class="font18 fontWeight500 darkBluetitle">${segmentoHistorialDeCredito?.reporteBuro?.totalSaldosVencidos}</p>
                                        </div>
                                        <div class="paddingLeft30 lightGrayBG">
                                            <p class="font12 fontWeight500 gray paddingTop10">MONTO A PAGAR EN BC</p>
                                            <p class="font18 fontWeight500 darkBluetitle">$ ${datosSolicitud.solicitud?.montoPagoBuro}</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col6 col12-tab floatLeft">
                                    <div class="boxMargins solicitudWhiteBox radius2 paddingBottom12">
                                        <div class="navyBg radius2 ">
                                            <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10">HISTORIAL CREDITICIO</p>
                                        </div>
                                        <div class="marginLeft32">
                                            <p class="font12 fontWeight500 gray paddingTop10">FECHA DEL ULTIMO ATRASO</p>
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
                                <h1 class="darkBluetitle font18 fontWeight600 letterspacing2">5. SCORE</h1>
                            </div>
                            <div class="col6 col12-mob floatLeft">
                                <div class="mobileAside10 solicitudWhiteBox radius2 paddingBottom12 marginBottom20">
                                    <div class="navyBg radius2 ">
                                        <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10">RESULTADO DEL MOTOR DE DECISIÓN</p>
                                    </div>
                                    <div class="marginLeft32 clearFix">
                                        <p class="font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft">PROBABILIDAD DE MORA</p>
                                        <div class="floatRight">
                                            <p class="font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft">${ datosSolicitud?.resultadoMotor ? datosSolicitud?.resultadoMotor?.probabilidadDeMora : "-"}</p>
                                        </div>
                                    </div>
                                    <div class="paddingLeft30 lightGrayBG clearFix">
                                        <p class="font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft">RAZON DE COBERTURA</p>
                                        <div class="floatRight">
                                            <p class="font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft">${ datosSolicitud?.resultadoMotor ? datosSolicitud?.resultadoMotor?.razonDeCobertura : "-"}</p>
                                        </div>
                                    </div>
                                    <div class="marginLeft32 clearFix">
                                        <p class="font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft">DICTAMEN DE CAPACIDAD DE PAGO</p>
                                        <div class="floatRight">
                                            <p class="font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft">${ datosSolicitud?.resultadoMotor ? datosSolicitud?.resultadoMotor?.dictamenCapacidadDePago : "-"}</p>
                                        </div>
                                    </div>
                                    <div class="paddingLeft30 lightGrayBG clearFix">
                                        <p class="font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft">DICTAMEN DE PERFIL</p>
                                        <div class="floatRight">
                                            <p class="font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft">${ datosSolicitud?.resultadoMotor ? datosSolicitud?.resultadoMotor?.dictamenDePerfil : "-"}</p>
                                        </div>
                                    </div>
                                    <div class="marginLeft32 clearFix">
                                        <p class="font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft">DICTAMEN DE POLITICAS</p>
                                        <div class="floatRight">
                                            <p class="font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft">${ datosSolicitud?.resultadoMotor ? datosSolicitud?.resultadoMotor?.dictamenDePoliticas : "-"}</p>
                                        </div>
                                    </div>
                                    <div class="paddingLeft30 lightGrayBG clearFix">
                                        <p class="font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft">DICTAMEN CONJUNTO</p>
                                        <div class="floatRight">
                                            <p class="font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft">${ datosSolicitud?.resultadoMotor ? datosSolicitud?.resultadoMotor?.dictamenConjunto : "-"}</p>
                                        </div>
                                    </div>
                                    <div class="paddingAside15">
                                        <g:if test="${datosSolicitud?.resultadoMotor?.dictamenFinal == "R"}">
                                            <div class="buttonOrange autoMargin marginTop12 radius4">
                                            </g:if>
                                            <g:elseif test="${!datosSolicitud?.resultadoMotor || datosSolicitud?.resultadoMotor?.dictamenFinal == "D"}">
                                                <div class="yellowButton autoMargin marginTop12 radius4">
                                                </g:elseif>
                                                <g:elseif test="${datosSolicitud?.resultadoMotor?.dictamenFinal == "A"}">
                                                    <div class="colorGreen autoMargin marginTop12 radius4">
                                                    </g:elseif>
                                                    <p class="colorWhite fontWeighht700 font25 center paddingTop15 paddingBottom15">DICTAMEN FINAL: ${ datosSolicitud?.resultadoMotor ? datosSolicitud?.resultadoMotor?.dictamenFinal : "D"}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                <sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_DIRECTOR,ROLE_ANALISTA'>
                                    <g:if test="${!datosSolicitud.temporal}">
                                        <g:if test="${datosSolicitud.solicitud?.statusDeSolicitud?.id != 5 && datosSolicitud.solicitud?.statusDeSolicitud?.id != 7}">
                                            <div id="opcionesScore" class="col6 col12-mob floatRight">
                                                <div class="mobileAside10">
                                                    <a onclick="mostrarModal('loginAutorizacion');" id="autorizarCredito">
                                                        <div class="width400 blueButton radius2 autoCenter marginBottom20">
                                                            <p class="autorizar_credito letterspacing2 font18 fontWeight600 paddingTop18 paddingBottom15 center pointer">AUTORIZAR</p>
                                                        </div>
                                                    </a>
                                                    <g:if test="${datosSolicitud.solicitud?.statusDeSolicitud.id != 8 && !datosSolicitud.verificacion}">
                                                        <a onclick="mostrarModal('modalPreguntas');" id="solicitarVisita">
                                                            <div class="width400 solicitudWhiteBox radius2 autoCenter marginBottom20">
                                                                <p class="letterspacing2 font18 fontWeight600 paddingTop18 paddingBottom15 center darkBluetitle pointer">SOLICITAR VISITA</p>
                                                            </div>
                                                        </a>
                                                    </g:if>
                                                    <a onclick="mostrarModal('modalComplemento');" id="solicitarComplemento">
                                                        <div class="width400 solicitudWhiteBox radius2 autoCenter marginBottom20">
                                                            <p class="letterspacing2 font18 fontWeight600 paddingTop18 paddingBottom15 center gray pointer">SOLICITAR COMPLEMENTO</p>
                                                        </div>
                                                    </a>
                                                    <a onclick="cambiarEstatus(7, ${datosSolicitud.solicitud?.id});" id="rechazar">
                                                        <div class="width400 solicitudWhiteBox radius2 autoCenter marginBottom20">
                                                            <p class="letterspacing2 font18 fontWeight600 paddingTop18 paddingBottom15 center colorRed pointer">RECHAZAR</p>
                                                        </div>
                                                    </a>
                                                </div>
                                            </div>
                                        </g:if>
                                    </g:if>
                              </sec:ifAnyGranted>
                                </div>
                                <div class="col12 clearFix">
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
                                </div>
                                </section>
                                <g:if test="${!datosSolicitud.temporal}">
                                    <g:if test="${datosSolicitud.solicitud?.statusDeSolicitud?.id != 5 && datosSolicitud.solicitud?.statusDeSolicitud?.id != 7}">
                                        <div class="solicitudLightbox hide" id="loginAutorizacion">
                                            <div class="overlay"></div>
                                            <div class="loginContainer creditLb">
                                                <div class="dashBordBox">
                                                    <g:urlContextAware value="/dashboard/autorizarSolicitud" var="urlForm"/>
                                                    <form action="${urlForm}" method="POST" class="loginForm gray font14">
                                                        <input type="hidden" name="solicitudId" value="${datosSolicitud.solicitud?.id}">
                                                        <p class="loginTitle font25 fontWeight500 darkBluetitle textUpper letterspacing1 center paddingAside15 marginBottom25">APROBACIÓN DE CRÉDITO</p>
                                                        <div class="solicitudTitle width166 autoMargin">
                                                            <p class="center font14 lightGray">Para autorizar este crédito ingresa tu contraseña</p>
                                                        </div>
                                                        <div class="formContainer">
                                                            <input class="inputs marginBottom30 lightGray letterspacing1 font14" name="claveUsuario" type="password" required placeholder="Contraseña"/>
                                                            <button type="submit" class="loginButton blueButton letterspacing2 font14 pointer">AUTORIZAR</button>
                                                            <button type="button" onclick="cerrarModal('loginAutorizacion');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
                                                        </div>
                                                    </form>
                                                </div>
                                                <div class="loginShadow"></div>
                                            </div>
                                        </div>
                                        <div class="solicitudLightbox hide" id="modalComplemento">
                                            <div class="overlay"></div>
                                            <div class="visitaContainer">
                                                <div class="dashBordBox">
                                                    <div class="loginForm gray font14">
                                                        <p class="loginTitle font25 fontWeight500 darkBluetitle textUpper letterspacing1 center paddingAside15 marginBottom25">DOCUMENTOS A SOLICITAR</p>
                                                        <div class="solicitudTitle autoMargin" style="width: 100%;">
                                                            <p class="center font14 lightGray">Indica los documentos que requiere proporcionar el cliente</p>
                                                        </div>
                                                        <div class="formContainer" style="width: 90%">
                                                            <div class='col12 marginBottom20' style='display:inline-block;'>
                                                                <center>
                                                                    <g:each var='documento' in='${documentos}' status='i'>
                                                                        <div class='col4 col12-mob' style='display:inline-block;' class="button marginTop20">
                                                                            <div id="documento${documento.id}" data-id-documento="${documento.id}" class="checkVerificacion whiteBox col11 marginTop10 autoCenter block" style="margin-left: 10px;margin-right: 10px;">
                                                                                <a onclick="confirmarAccion('documento',${documento.id});" title="${documento.nombre}" class="block font14 letterspacing1 fontWeight500 lightGray paddingTop15 paddingBottom15 center">${documento.nombre}</a>
                                                                            </div>
                                                                        </div>
                                                                    </g:each>
                                                                </center>
                                                            </div>
                                                            <div class="col12" style="text-align: center;">
                                                                <div class="col4" style="display: inline-block;">
                                                                    <button type="button" id="btnComplemento" onclick="cambiarEstatus(6, ${datosSolicitud.solicitud?.id});" class="loginButton letterspacing2 font14 pointer" disabled>ACEPTAR</button>
                                                                </div>
                                                                <div class="col4" style="display: inline-block;">
                                                                    <button type="button" onclick="cerrarModal('modalComplemento');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </g:if>
                                    <g:if test="${datosSolicitud.solicitud?.statusDeSolicitud?.id != 8}">
                                        <div class="solicitudLightbox hide" id="modalPreguntas">
                                            <div class="overlay"></div>
                                            <div class="visitaContainer">
                                                <div class="dashBordBox">
                                                    <div class="loginForm gray font14">
                                                        <input type="hidden" name="solicitudId" value="${datosSolicitud.solicitud?.id}">
                                                        <p class="loginTitle font25 fontWeight500 darkBluetitle textUpper letterspacing1 center paddingAside15 marginBottom25">PREGUNTAS PARA VISITA OCULAR</p>
                                                        <div class="solicitudTitle autoMargin" style="width: 100%;">
                                                            <p class="center font14 lightGray">Indica las preguntas a realizar durante la visita ocular</p>
                                                        </div>
                                                        <div class="formContainer">
                                                            <div class='col12' style='display:inline-block; position:relative;'>
                                                                <textarea rows='8' class="col12 inputs marginBottom30 lightGray letterspacing1 font14" id="textoPregunta" type="text" required placeholder="Pregunta a realizar"></textarea>
                                                                <button type="button" onclick="agregarPregunta();" class="loginButton blueButton letterspacing2 font14 pointer" style="width: 15%; position:absolute; bottom:10px; right:10px;">Agregar</button>
                                                            </div>
                                                            <div>
                                                                <table id='preguntasAgregadas' style="width: 100%; margin-bottom: 20px; border-bottom: 5px #369;">
                                                                    <thead>
                                                                        <tr>
                                                                            <th colspan="2" class="navyBg"><h1 class="graphHeading colorWhite letterspacing2 textUpper center">Preguntas Añadidas</h1></th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <tr>
                                                                            <td colspan="2" class="tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom">No se han agregado preguntas</td>
                                                                        </tr>
                                                                    </tbody>
                                                                </table>
                                                                <input type="hidden" id="cantidadDePreguntas">
                                                            </div>
                                                            <div class="col12" style="text-align: center;">
                                                                <div class="col4" style="display: inline-block;">
                                                                    <button type="button" id="btnVerificar" onclick="cambiarEstatus(8, ${datosSolicitud.solicitud?.id});" class="loginButton letterspacing2 font14 pointer" disabled>REGISTRAR PREGUNTAS</button>
                                                                </div>
                                                                <div class="col4" style="display: inline-block;">
                                                                    <button type="button" onclick="cerrarModal('modalPreguntas');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </g:if>
                                </g:if>
                            </div>     
                        <div class="solicitudTab" id='documentacion' style='margin-top: 30px; display: none;'>
                            <section class="container">
                                <div class="clearFix width928 autoMargin">
                                    <g:if test="${complementoSolicitado}">
                                        <div class="container clearFix">
                                            <div class="width990 autoMargin solicitudWhiteBox clearFix paddingBottom20">
                                                <div class="floatLeft col12 col12-mob">
                                                    <br/>
                                                    <p class="lightGrayColor font12 fontWeight300" style="padding-left: 25px;"><strong>Se han solicitado los siguientes documentos de manera complementanria: </strong></p>
                                                </div>
                                                <div class="col12 col12-mob floatLeft">
                                                    <div class="paddingAside15">
                                                        <div class="marginTop20 marginBottom20">
                                                            <g:each var='complemento' in='${complementoSolicitado}' status='c'>
                                                                <a class="block width94 grayBorderBox floatLeft center marginLeft10 marginRight10 marginBottom10 pointer" style="height: 73px;">
                                                                    <img src="${resource(dir:'images', file:'add_file.png')}" alt="Agregar Archivo">
                                                                    <p class="lightGrayColor font12 fontWeight300">${complemento?.documentoSolicitado?.nombre}</p>
                                                                </a>
                                                            </g:each>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </g:if>
                                    <div class="container clearFix marginTop10">
                                        <g:if test="${datosSolicitud.documentosSolicitud}">
                                            <g:each var='documento' in='${datosSolicitud.documentosSolicitud}' status='i'>
                                                <g:link controller="dashboard" action="descargarArchivo" id="${documento.id}" class="block width94 grayBorderBox floatLeft center marginLeft10 marginRight10 marginBottom10 pointer" style="height: 73px;">
                                                    <img src="${resource(dir:'images', file:'pdf.svg')}" alt="${documento?.tipoDeDocumento?.nombre}">
                                                    <p class="lightGrayColor font12 fontWeight300">${documento?.tipoDeDocumento?.nombre}</p>
                                                </g:link>
                                            </g:each>
                                        </g:if>
                                        <g:else>
                                            <p class="lightGrayColor font12 fontWeight300">No hay documentos disponibles</p>
                                        </g:else>
                                    </div>
                                </div>
                            </section>
                        </div>
                        <g:if test="${datosSolicitud.verificacion}">
                            <div class="solicitudTab" id='visitaOcular' style='display: none;'>
                                <section class="container marginTop10 marginBottom10">
                                    <div class="clearFix autoMargin">
                                        <h3 class="floatLeft darkBluetitle marginLeft30 marginTop10">1. VERIFICACIÓN DE DOMICILIO.</h3>
                                        <g:if test="${datosSolicitud.verificacion.resultadoPaso1}">
                                            <a title="VERIFICACIÓN EXITOSA" class="floatLeft block colorGreen radius100 colorWhite letterspacing1 paddingTop10 paddingRight30 paddingBottom10 paddingLeft30 marginLeft30">VERIFICACIÓN EXITOSA</a>
                                        </g:if>
                                        <g:else>
                                            <a  title="VERIFICACIÓN NO EXITOSA" class="floatLeft block buttonOrange2 radius100 colorWhite letterspacing1 paddingTop10 paddingRight30 paddingBottom10 paddingLeft30 marginLeft30">VERIFICACIÓN NO EXITOSA</a>
                                        </g:else>
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
                                                    <p class="font18 fontWeight500 darkBluetitle paddingAside15">${datosSolicitud.direccionCliente?.calle}</p>
                                                </div>
                                                <div class="lightGrayBG padding10">
                                                    <p class="font12 fontWeight500 gray paddingAside15">NÚMERO EXTERIOR</p>
                                                    <p class="font18 fontWeight500 darkBluetitle paddingAside15">${datosSolicitud.direccionCliente?.numeroExterior}</p>
                                                </div>
                                                <div class="padding10">
                                                    <p class="font12 fontWeight500 gray paddingAside15">NÚMERO INTERIOR</p>
                                                    <p class="font18 fontWeight500 darkBluetitle paddingAside15">${datosSolicitud.direccionCliente?.numeroInterior}</p>
                                                </div>
                                                <div class="lightGrayBG padding10">
                                                    <p class="font12 fontWeight500 gray paddingAside15">COLONIA</p>
                                                    <p class="font18 fontWeight500 darkBluetitle paddingAside15">${datosSolicitud.direccionCliente?.colonia}</p>
                                                </div>
                                                <div class="padding10">
                                                    <p class="font12 fontWeight500 gray paddingAside15">DELEGACIÓN O MUNICIPIO</p>
                                                    <p class="font18 fontWeight500 darkBluetitle paddingAside15">${datosSolicitud.direccionCliente?.codigoPostal?.municipio}</p>
                                                </div>
                                                <div class="lightGrayBG padding10">
                                                    <p class="font12 fontWeight500 gray paddingAside15">CÓDIGO POSTAL</p>
                                                    <p class="font18 fontWeight500 darkBluetitle paddingAside15">${datosSolicitud.direccionCliente?.codigoPostal?.codigo}</p>
                                                </div>
                                                <div class="padding10">
                                                    <p class="font12 fontWeight500 gray paddingAside15">ESTADO</p>
                                                    <p class="font18 fontWeight500 darkBluetitle paddingAside15">${datosSolicitud.direccionCliente?.codigoPostal?.municipio?.estado}</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col8 col12-mob floatLeft">
                                            <div class="marginTop25 marginBottom35  paddingBottom12">
                                                <div class="clearFix">
                                                    <h1 class="gray font16 fontWeight300 marginBottom15 paddingAside15">FOTOS DE LA FACHADA DE LA VIVIENDA</h1>
                                                    <g:each var='fotoFachada' in='${datosSolicitud.fotosFachada}'>
                                                        <div class="col4 col12-mob floatLeft">
                                                            <div class="paddingAside15 marginBottom20">
                                                                <div class="grayBorderBox center">
                                                                    <a href="${createLink(controller: 'dashboard', action:'mostrarFotografia', id: fotoFachada.id)}" rel="prettyPhoto[pp_gal]"><img src="${createLink(controller: 'dashboard', action:'mostrarFotografia', id: fotoFachada.id)}" width="88" height="88" alt="${fotoFachada.tipoDeFotografia}" /></a>
                                                                    <p class="gray2 font12 center">${fotoFachada.tipoDeFotografia}</p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </g:each>
                                                </div>
                                                <div class="clearFix">
                                                    <h1 class="gray font16 fontWeight300 marginTop30 paddingAside15 marginBottom15">FOTOS DE INTERIOR DE LA VIVIENDA</h1>
                                                    <g:each var='fotoInterior' in='${datosSolicitud.fotosInterior}'>
                                                        <div class="col4 col12-mob floatLeft">
                                                            <div class="paddingAside15 marginBottom20">
                                                                <div class="grayBorderBox center">
                                                                    <a href="${createLink(controller: 'dashboard', action:'mostrarFotografia', id: fotoInterior.id)}" rel="prettyPhoto[pp_gal]"><img src="${createLink(controller: 'dashboard', action:'mostrarFotografia', id: fotoInterior.id)}" width="88" height="88" alt="${fotoInterior.tipoDeFotografia}" /></a>
                                                                    <p class="gray2 font12 center">${fotoInterior.tipoDeFotografia}</p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </g:each>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </section>

                                <section class="container marginTop10 marginBottom10">
                                    <div class="clearFix autoMargin">
                                        <h3 class="floatLeft darkBluetitle marginLeft30 marginTop10">2. VERIFICACIÓN DE DOCUMENTOS</h3>
                                        <g:if test="${datosSolicitud.verificacion.resultadoPaso2 && datosSolicitud.verificacion.resultadoPaso3}">
                                            <a title="VERIFICACIÓN EXITOSA" class="floatLeft block colorGreen radius100 colorWhite letterspacing1 paddingTop10 paddingRight30 paddingBottom10 paddingLeft30 marginLeft30">VERIFICACIÓN EXITOSA</a>
                                        </g:if>
                                        <g:else>
                                            <a  title="VERIFICACIÓN NO EXITOSA" class="floatLeft block buttonOrange2 radius100 colorWhite letterspacing1 paddingTop10 paddingRight30 paddingBottom10 paddingLeft30 marginLeft30">VERIFICACIÓN NO EXITOSA</a>
                                        </g:else>
                                    </div>
                                </section>

                                <section class="container marginTop10">
                                    <div class="wrapper autoMargin solicitudWhiteBox paddingBottom35 clearFix">
                                        <div class="col6 col12-mob floatLeft">
                                            <div class="padding15">
                                                <h1 class="gray font16 fontWeight400 marginTop30 marginLeft25 marginBottom15">IDENTIFICACIÓN OFICIAL</h1>
                                                <div class="">
                                                    <div class="<g:if test="${datosSolicitud.verificacion.resultadoPaso2}">grayBG-visita</g:if><g:else>grayBG-visita2</g:else> radius10 height260 autoMargin clearFix BigGrayBox">
                                                            <div class="profileBG floatLeft marginLeft17 marginTop65">
                                                                    <img class="imgResponsive" src="${resource(dir:'images', file:'user.png')}" alt="user" />
                                                        </div>
                                                        <g:if test="${datosSolicitud.verificacion.resultadoPaso2 == false}">
                                                            <div class="clearFix alert-visita">
                                                                <img src="${resource(dir:'images', file:'info-red.svg')}" alt="Info-Red" class="floatLeft padding20" />
                                                                <p class="floatRight buttonOrange2 colorWhite radius2 alertError">El documento es diferente al que el prospecto subió originalmente.</p>
                                                            </div>
                                                        </g:if>
                                                    </div>
                                                </div>
                                                <div class="marginTop19 marginBottom14">
                                                    <div class="<g:if test="${datosSolicitud.verificacion.resultadoPaso2}">grayBG-visita</g:if><g:else>grayBG-visita2</g:else> radius10 height260 autoMargin BigGrayBox">
                                                            <div class="paddingTop150">
                                                                <div class="height47 colorGrayBG"></div>
                                                            </div>
                                                        <g:if test="${datosSolicitud.verificacion.resultadoPaso2 == false}">
                                                            <div class="clearFix alert-visita">
                                                                <img src="${resource(dir:'images', file:'info-red.svg')}" alt="Info-Red" class="floatLeft padding20" />
                                                                <p class="floatRight buttonOrange2 colorWhite radius2 alertError">El documento es diferente al que el prospecto subió originalmente.</p>
                                                            </div>
                                                        </g:if>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col6 col12-mob floatLeft">
                                            <div class="padding15">
                                                <h1 class="gray font16 fontWeight400 marginTop30 marginLeft10 marginBottom15">COMPROBANTE DE DOMICILIO</h1>
                                                <div class="<g:if test="${datosSolicitud.verificacion.resultadoPaso3}">grayBG-visita</g:if><g:else>grayBG-visita2</g:else> height535 autoMargin BigGrayBox">
                                                    <g:if test="${datosSolicitud.verificacion.resultadoPaso3 == false}">
                                                        <div class="clearFix alert-visita">
                                                            <img src="${resource(dir:'images', file:'info-red.svg')}" alt="Info-Red" class="floatLeft padding20">
                                                            <p class="floatRight buttonOrange2 colorWhite radius2 alertError">El documento es diferente al que el prospecto subió originalmente.</p>
                                                        </div>
                                                    </g:if>
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
                                        <g:each var='pregunta' in='${datosSolicitud.preguntas}' status="i">
                                            <div>
                                                <label for="ingresos" class="block font14 gray marginTop15 marginBottom10 ">${pregunta.pregunta.toUpperCase()}</label>
                                                <div>
                                                </div>
                                                <input type="text" placeholder="Respuesta" class="whiteBox blockAuto" readonly value="${pregunta.respuesta.toUpperCase()}">
                                            </div>
                                        </g:each>
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
                                            <textarea readonly class="whiteBox block height179 autoMargin font14 paddingTop10 paddingBottom10 paddingLeft20">${datosSolicitud.verificacion.observaciones}</textarea>
                                        </div>
                                    </div>
                                </section>
                            </div>
                        </g:if>
                        <a class="back-to-top">Back to Top</a>
                    </g:if>
                    <g:else>
                        <h1 class="darkBluetitle font14 fontWeight400 marginBottom15">LA SOLICITUD INDICADA NO HA SIDO ENCONTRADA, VERIFIQUE LOS DATOS E INTENTE NUEVAMENTE.</h1>
                    </g:else>
                    </body>
                    </html>