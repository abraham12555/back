function cargarGraficas(periodoTiempo, fechaInicio, fechaFinal) {
    cargarGraficaGeneral(periodoTiempo, fechaInicio, fechaFinal);
    cargarGraficaEstatus(periodoTiempo, fechaInicio, fechaFinal);
    cargarGraficaOrigen(periodoTiempo, fechaInicio, fechaFinal);
    cargarGraficaPDV(periodoTiempo, fechaInicio, fechaFinal);
    cargarGraficaProductos(periodoTiempo, fechaInicio, fechaFinal);
}


function cargarGraficaGeneral(periodoTiempo, fechaInicio, fechaFinal) {

    var options = {
        chart: {
            type: 'column'
        },
        title: {
            text: ''
        },
        xAxis: [],
        yAxis: [{// Secondary yAxis
                title: {
                    text: 'Solicitudes Registradas',
                    style: {
                        color: Highcharts.getOptions().colors[0], fontWeight: 600
                    }
                },
                labels: {
                    format: '{value}',
                    style: {
                        color: Highcharts.getOptions().colors[0]
                    }
                }
            }],
        tooltip: {
            shared: true
        }, plotOptions: {
            series: {
                dataLabels: {
                    enabled: true,
                    color: '#ffffff',
                    style: {fontWeight: 'bolder', textShadow: false},
                    formatter: function () {
                        return this.y
                    },
                    inside: true,
                    rotation: 0
                },
                pointPadding: 0.1,
                groupPadding: 0
            }
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
        series: []
    };

    $.getJSON("/dashboard/getEstadisticas",
            {temporalidad: periodoTiempo, grafica: 'general', fechaInicio: fechaInicio, fechaFinal: fechaFinal},
            function (data) {
                if (data.estadisticas.length > 0) {
                    options.xAxis = data.periodos;
                    options.series = data.estadisticas;
                    $('#graficaGeneral').highcharts(options);
                } else {
                    $('#graficaGeneral').html("No Datos que Mostrar");
                }
            });
}

function cargarGraficaEstatus(periodoTiempo, fechaInicio, fechaFinal) {

    var options = {
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
            }
        },
        legend: {
            enabled: false
        },
        series: []
    };

    $.getJSON("/dashboard/getEstadisticas",
            {temporalidad: periodoTiempo, grafica: 'status', fechaInicio: fechaInicio, fechaFinal: fechaFinal},
            function (data) {
                if (data.estadisticas.length > 0) {
                    options.series = data.estadisticas;
                    $('#graficaEstatus').highcharts(options);
                } else {
                    $('#graficaEstatus').html("No Datos que Mostrar");
                }
            });

}

function cargarGraficaOrigen(periodoTiempo, fechaInicio, fechaFinal) {

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
}

function cargarGraficaPDV(periodoTiempo, fechaInicio, fechaFinal) {

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
}

function cargarGraficaProductos(periodoTiempo, fechaInicio, fechaFinal) {

    var options = {
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
        series: []
    };

    $.getJSON("/dashboard/getEstadisticas",
            {temporalidad: periodoTiempo, grafica: 'productos', fechaInicio: fechaInicio, fechaFinal: fechaFinal},
            function (data) {
                if (data.estadisticas.length > 0) {
                    options.series = data.estadisticas;
                    $('#graficaProductos').highcharts(options);
                } else {
                    $('#graficaProductos').html("No Datos que Mostrar");
                }
            });
}