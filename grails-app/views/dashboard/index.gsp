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
        <g:external dir="css" file="highcharts.css" />
        <g:external dir="js" file="highchart/highcharts.js" />
        <g:external dir="js" file="highchart/modules/exporting.js" />
        <script type="text/javascript">
            $(function () {
            $('#grafica1').highcharts({
            title: {
            text: '',
            x: -20 //center
            },
            xAxis: {
            categories: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun',
            'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic']
            },
            yAxis: {
            labels: {
            format: '$ {value}',
            style: {
            color: Highcharts.getOptions().colors[1]
            }
            },
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
            });

            $(function () {
            $('#grafica2').highcharts({
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
            });
        </script>
        <g:urlContextAware value="/" var="urlContextAware"/>
        <script type="text/javascript">
            $.contextAwarePathJS = "${urlContextAware}";
        </script>
    </head>
    <body>

        <input type="hidden" id="opcionMenu" value="1">
        <section class="container clearFix">
            <div class="col6 col12-mob floatLeft clearFix">
                <div class="graphBox">
                    <div class="boxTitle">
                        <h1 class="graphHeading colorWhite letterspacing2 textUpper">volumen</h1>
                        <div class="grayDashboardBox floatRight">
                            <a href="${createLink(controller:'dashboard', action:'analiticas')}">
                                <p class="vermasLine center colorWhite">VER MÁS</p>
                            </a>
                        </div>
                        <div id="grafica1" style="min-width: 310px; height: 200px; margin: 0 auto"></div>
                    </div>
                </div>
            </div>
            <div class="col6 col12-mob floatLeft clearFix">
                <div class="graphBox">
                    <div class="boxTitle">
                        <h1 class="graphHeading colorWhite letterspacing2 textUpper">origen</h1>
                        <div class="grayDashboardBox floatRight">
                            <a href="${createLink(controller:'dashboard', action:'analiticas')}">
                                <p class="vermasLine center colorWhite">VER MÁS</p>
                            </a>
                        </div>
                        <div id="grafica2" style="min-width: 310px; height: 200px; margin: 0 auto"></div>
                    </div>
                </div>
            </div>
        </section>
        <section class="container paddingBottom38">
            <table id='listaDeSolicitudes' class="applicationContainers solicitudes_table dashboard">
          
                        <thead>
                <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_DIRECTOR, ROLE_ANALISTA'>
                    <th colspan="8" class="left navyBg">
                        <h1 class="graphHeading colorWhite letterspacing2 textUpper">Solicitudes</h1>
                    </th>
                    <th class="darkGray">
                        <a href="${createLink(controller:'dashboard', action:'solicitudes')}">
                            <p class=" vermasLine center colorWhite fontWeight300">VER TODAS</p>
                        </a>
                    </th>
                </sec:ifAnyGranted>
                <sec:ifAnyGranted roles='ROLE_ADMINISTRADOR, ROLE_EJECUTIVO, ROLE_SUCURSAL,ROLE_CAJERO,ROLE_MERCADOTECNIA,ROLE_CENTRO_DE_CONTACTO'>
                    <th colspan="9" class="left navyBg">
                        <h1 class="graphHeading colorWhite letterspacing2 textUpper">Solicitudes </h1>
                    </th>
                </sec:ifAnyGranted>
                    <tr class="lightGrayBG font15">
                        
                        <th class="gray pointer"><span>FOLIO</span> <i class="fa fa-caret-down" aria-hidden="true"></i></th>
                        <th class="gray pointer"><span>CLIENTE</span><i class="fa fa-caret-down" aria-hidden="true"></i></th>
                        <th class="gray pointer"><span>STATUS</span><i class="fa fa-caret-down" aria-hidden="true"></i></th>
                        <th class="gray pointer"><span>PDV</span><i class="fa fa-caret-down" aria-hidden="true"></i></th>
                        <th class="gray pointer"><span>FUENTE</span><i class="fa fa-caret-down" aria-hidden="true"></i></th>
                        <th class="gray pointer"><span>PRODUCTO</span><i class="fa fa-caret-down" aria-hidden="true"></i></th>
                        <th class="gray pointer"><span>FECHA</span><i class="fa fa-caret-down" aria-hidden="true"></i></th>
                        <th class="gray pointer"><span>MONTO</span><i class="fa fa-caret-down" aria-hidden="true"></i></th>

                    </tr>
                </thead>
                <tbody>
                   

                </tbody>
            </table>
        </section>
        <input type ="hidden" id="pagina" value ="index" />
        <section class="container">
            <div class="width480 autoMargin solicitudBox marginBottom84">
             <div class="autoMargin">
                <input type="hidden" id="currentPageSolicitudes"/>
                <ul class="clearFix" id="paginationSolicitudes">
                </ul>
            </div>
           </div>
        </section>
</body>
</html>