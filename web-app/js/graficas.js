/* global Highcharts */

function cargarGraficas(periodoTiempo, fechaInicio, fechaFinal) {
    cargarGraficaGeneral(periodoTiempo, fechaInicio, fechaFinal);
    cargarGraficaEstatus(periodoTiempo, fechaInicio, fechaFinal);
    cargarGraficaOrigen(periodoTiempo, fechaInicio, fechaFinal);
    cargarGraficaPDV(periodoTiempo, fechaInicio, fechaFinal);
    cargarGraficaProductos(periodoTiempo, fechaInicio, fechaFinal);
}

function cargarReportesAnaliticas(periodoTiempo, fechaInicio, fechaFinal) {
    cargarGraficaUsoCredito(periodoTiempo, fechaInicio, fechaFinal);
    cargarGraficaProductosSolicitados(periodoTiempo, fechaInicio, fechaFinal);
    cargarGraficaComprobanteIngresos(periodoTiempo, fechaInicio, fechaFinal);
    cargarGraficaVolumenDia(periodoTiempo, fechaInicio, fechaFinal);
    cargarGraficaDictamenStatus(periodoTiempo, fechaInicio, fechaFinal);
    cargarGraficaVolumenHora(periodoTiempo, fechaInicio, fechaFinal);
    cargarGraficaResidenciaClientes(periodoTiempo, fechaInicio, fechaFinal);
    cargarReporteMPGI(periodoTiempo, fechaInicio, fechaFinal);
    cargarReporteIMPEG(periodoTiempo, fechaInicio, fechaFinal);
    cargarReporteProductosDiscriminados(periodoTiempo, fechaInicio, fechaFinal);
    cargarReporteMPSProducto(periodoTiempo, fechaInicio, fechaFinal);
    cargarReporteContacto(periodoTiempo, fechaInicio, fechaFinal);
    cargarReporteAbandono(periodoTiempo, fechaInicio, fechaFinal);
    cargarReporteDictamenStatus(periodoTiempo, fechaInicio, fechaFinal);
    cargarReporteDFvsDP(periodoTiempo, fechaInicio, fechaFinal);
    cargarReporteCausaRechazo(periodoTiempo, fechaInicio, fechaFinal);
    cargarReporteConsultasBC(periodoTiempo, fechaInicio, fechaFinal);
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

function cargarGraficaUsoCredito(periodoTiempo, fechaInicio, fechaFinal) {
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
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    },
                    connectorColor: 'silver'
                }
            }
        },
        series: []
    };
    $.getJSON("/dashboard/getInformes",
            {temporalidad: periodoTiempo, grafica: 'usoCredito', fechaInicio: fechaInicio, fechaFinal: fechaFinal},
            function (data) {
                if (data.estadisticas.length > 0) {
                    options.series = data.estadisticas;
                    $('#graficaUsoCredito').highcharts(options);
                } else {
                    $('#graficaUsoCredito').html("No Datos que Mostrar");
                }
            });

}

function cargarGraficaProductosSolicitados(periodoTiempo, fechaInicio, fechaFinal) {

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
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    },
                    connectorColor: 'silver'
                }
            }
        },
        series: []
    };
    $.getJSON("/dashboard/getInformes",
            {temporalidad: periodoTiempo, grafica: 'productosSolicitados', fechaInicio: fechaInicio, fechaFinal: fechaFinal},
            function (data) {
                if (data.estadisticas.length > 0) {
                    options.series = data.estadisticas;
                    $('#graficaProductosSolicitados').highcharts(options);
                } else {
                    $('#graficaProductosSolicitados').html("No Datos que Mostrar");
                }
            });

}

function cargarGraficaComprobanteIngresos(periodoTiempo, fechaInicio, fechaFinal) {
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
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    },
                    connectorColor: 'silver'
                }
            }
        },
        series: []
    };
    $.getJSON("/dashboard/getInformes",
            {temporalidad: periodoTiempo, grafica: 'comprobanteIngresos', fechaInicio: fechaInicio, fechaFinal: fechaFinal},
            function (data) {
                if (data.estadisticas.length > 0) {
                    options.series = data.estadisticas;
                    $('#graficaComprobanteIngresos').highcharts(options);
                } else {
                    $('#graficaComprobanteIngresos').html("No Datos que Mostrar");
                }
            });

}

function cargarGraficaProductosDiscriminados(periodoTiempo, fechaInicio, fechaFinal) {

    $('#graficaProductosDiscriminados').highcharts({
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
                        name: 'PD A',
                        y: 25.00
                    }, {
                        name: 'PD D',
                        y: 25.00
                    }, {
                        name: 'PD R',
                        y: 25.00
                    }, {
                        name: 'PD SD',
                        y: 25.00
                    }]
            }]
    });
    $.getJSON("/dashboard/getInformes",
            {temporalidad: periodoTiempo, grafica: 'productosDiscriminados', fechaInicio: fechaInicio, fechaFinal: fechaFinal},
            function (data) {
                if (data.estadisticas.length > 0) {
                    options.xAxis = data.periodos;
                    options.series = data.estadisticas;
                    $('#graficaProductosDiscriminados').highcharts(options);
                } else {
                    $('#graficaProductosDiscriminados').html("No Datos que Mostrar");
                }
            });
}

function cargarGraficaVolumenDia(periodoTiempo, fechaInicio, fechaFinal) {
    var options = {
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
            type: 'category',
            labels: {
                rotation: -45,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif'
                }
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Volumen por Dìa'
            }
        },
        tooltip: {
            pointFormat: 'Solicitudes <b>{point.y:.0f} </b>'
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

    $.getJSON("/dashboard/getInformes",
            {temporalidad: periodoTiempo, grafica: 'volumenDia', fechaInicio: fechaInicio, fechaFinal: fechaFinal},
            function (data) {
                if (data.estadisticas.length > 0) {
                    options.xAxis = data.periodos;
                    options.series = data.estadisticas;
                    $('#graficaVolumenDia').highcharts(options);
                } else {
                    $('#graficaVolumenDia').html("No Datos que Mostrar");
                }
            });
}

function cargarGraficaDictamenStatus(periodoTiempo, fechaInicio, fechaFinal) {
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
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    },
                    connectorColor: 'silver'
                }
            }
        },
        series: []
    };
    $.getJSON("/dashboard/getInformes",
            {temporalidad: periodoTiempo, grafica: 'dictamenStatus', fechaInicio: fechaInicio, fechaFinal: fechaFinal},
            function (data) {
                if (data.estadisticas.length > 0) {
                    options.series = data.estadisticas;
                    $('#graficaDictamenStatus').highcharts(options);
                } else {
                    $('#graficaDictamenStatus').html("No Datos que Mostrar");
                }
            });

}

function cargarGraficaVolumenHora(periodoTiempo, fechaInicio, fechaFinal) {
    var options = {
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
            type: 'category',
            labels: {
                rotation: -45,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif'
                }
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Volumen por Hora'
            }
        },
        tooltip: {
            pointFormat: 'Solicitudes <b>{point.y:.0f} </b>'
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

    $.getJSON("/dashboard/getInformes",
            {temporalidad: periodoTiempo, grafica: 'volumenHora', fechaInicio: fechaInicio, fechaFinal: fechaFinal},
            function (data) {
                if (data.estadisticas.length > 0) {
                    options.xAxis = data.periodos;
                    options.series = data.estadisticas;
                    $('#graficaVolumenHora').highcharts(options);
                } else {
                    $('#graficaVolumenHora').html("No Datos que Mostrar");
                }
            });
}

function cargarGraficaResidenciaClientes(periodoTiempo, fechaInicio, fechaFinal) {
    var options = {
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
            type: 'category',
            labels: {
                rotation: -45,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif'
                }
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: ' '
            }
        },
        tooltip: {
            pointFormat: 'Solicitudes <b>{point.y:.0f} </b>'
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

    $.getJSON("/dashboard/getInformes",
            {temporalidad: periodoTiempo, grafica: 'residenciaClientes', fechaInicio: fechaInicio, fechaFinal: fechaFinal},
            function (data) {
                if (data.estadisticas.length > 0) {
                    options.xAxis = data.periodos;
                    options.series = data.estadisticas;
                    $('#graficaResidenciaClientes').highcharts(options);
                } else {
                    $('#graficaResidenciaClientes').html("No Datos que Mostrar");
                }
            });
}

function cargarReporteMPGI(periodoTiempo, fechaInicio, fechaFinal){
     var complemento = "";
    if (fechaInicio && fechaFinal) {
        complemento += "&fechaInicio=" + fechaInicio + "&fechaFinal=" + fechaFinal;
    }
    jQuery.ajax({
        type: 'POST',
 
        data: 'periodoTiempo=' + periodoTiempo + "&template=" + "mPGI"+ "&fechaInicio="+ fechaInicio+ "&fechaFinal="+ fechaFinal ,
        url: 'consultarInformes',
        success: function (data, textStatus) {
            $('#graficaMPGI' ).html(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });

    
}

function cargarReporteIMPEG(periodoTiempo, fechaInicio, fechaFinal){
     var complemento = "";
    if (fechaInicio && fechaFinal) {
        complemento += "&fechaInicio=" + fechaInicio + "&fechaFinal=" + fechaFinal;
    }
    jQuery.ajax({
        type: 'POST',
        data: 'periodoTiempo=' + periodoTiempo + "&template=" + "iMPEG"+ "&fechaInicio="+ fechaInicio+ "&fechaFinal="+ fechaFinal ,
        url: 'consultarInformes',
        success: function (data, textStatus) {
            $('#graficaIMPEG' ).html(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    }); 

   
}

function cargarReporteProductosDiscriminados(periodoTiempo, fechaInicio, fechaFinal ){
    
var complemento = "";
    if (fechaInicio && fechaFinal) {
        complemento += "&fechaInicio=" + fechaInicio + "&fechaFinal=" + fechaFinal;
    }
    jQuery.ajax({
        type: 'POST',
        data: 'periodoTiempo=' + periodoTiempo + "&template=" + "productosDiscriminados"+ "&fechaInicio="+ fechaInicio+ "&fechaFinal="+ fechaFinal ,
        url: 'consultarInformes',
        success: function (data, textStatus) {
            $('#graficaProductosDiscriminados' ).html(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });

   

}

function cargarReporteMPSProducto(periodoTiempo, fechaInicio, fechaFinal){
    var complemento = "";
    if (fechaInicio && fechaFinal) {
        complemento += "&fechaInicio=" + fechaInicio + "&fechaFinal=" + fechaFinal;
    }
    jQuery.ajax({
        type: 'POST',
        data: 'periodoTiempo=' + periodoTiempo + "&template=" + "mPSProducto"+ "&fechaInicio="+ fechaInicio+ "&fechaFinal="+ fechaFinal ,
        url: 'consultarInformes',
        success: function (data, textStatus) {
            $('#graficaMPSProducto' ).html(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });

}

function cargarReporteContacto(periodoTiempo, fechaInicio, fechaFinal){
    var complemento = "";
    if (fechaInicio && fechaFinal) {
        complemento += "&fechaInicio=" + fechaInicio + "&fechaFinal=" + fechaFinal;
    }
    jQuery.ajax({
        type: 'POST',
        data: 'periodoTiempo=' + periodoTiempo + "&template=" + "contacto"+ "&fechaInicio="+ fechaInicio+ "&fechaFinal="+ fechaFinal ,
        url: 'consultarInformes',
        success: function (data, textStatus) {
            $('#graficaContacto' ).html(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });


}

function cargarReporteAbandono(periodoTiempo, fechaInicio, fechaFinal){
 var options = {
    chart: {
        type: 'column'
    },
    title: {
        text: ''
    },
    xAxis: {
        type: 'category',
        labels: {
            rotation: -45,
            style: {
                fontSize: '13px',
                fontFamily: 'Verdana, sans-serif'
            }
        }
    },
    yAxis: {
        min: 0,
        title: {
            text: 'No. Solicitudes'
        },
        stackLabels: {
            enabled: true,
            style: {
                fontWeight: 'bold',
                color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
            }
        }
    },
    legend: {
        align: 'right',
        x: -30,
        verticalAlign: 'top',
        y: 25,
        floating: true,
        backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
        borderColor: '#CCC',
        borderWidth: 1,
        shadow: false
    },
    tooltip: {
        headerFormat: '<b>{point.x}</b><br/>',
        pointFormat: '{series.name}: {point.y}<br/>'
    },
    plotOptions: {
        column: {
            stacking: 'normal',
            dataLabels: {
                enabled: true,
                color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
            }
        }
    },
        series: []
      
    };
    
    $.getJSON("/dashboard/getInformes",
            {temporalidad: periodoTiempo, grafica: 'abandono', fechaInicio: fechaInicio, fechaFinal: fechaFinal},
            function (data) {
                if (data.estadisticas.length > 0) {
                   options.xAxis = data.periodos;
                    options.series = data.estadisticas;
                    $('#graficaAbandono').highcharts(options);
                } else {
                    $('#graficaAbandono').html("No Datos que Mostrar");
                }
            });

    
}

function cargarReporteDictamenStatus(periodoTiempo, fechaInicio, fechaFinal){
    var complemento = "";
    if (fechaInicio && fechaFinal) {
        complemento += "&fechaInicio=" + fechaInicio + "&fechaFinal=" + fechaFinal;
    }
    jQuery.ajax({
        type: 'POST',
        data: 'periodoTiempo=' + periodoTiempo + "&template=" + "mPSProducto"+ "&fechaInicio="+ fechaInicio+ "&fechaFinal="+ fechaFinal ,
        url: 'consultarInformes',
        success: function (data, textStatus) {
            $('#graficaMPSProducto' ).html(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });

    
}

function cargarReporteDFvsDP(periodoTiempo, fechaInicio, fechaFinal){
    var complemento = "";
    if (fechaInicio && fechaFinal) {
        complemento += "&fechaInicio=" + fechaInicio + "&fechaFinal=" + fechaFinal;
    }
    jQuery.ajax({
        type: 'POST',
        data: 'periodoTiempo=' + periodoTiempo + "&template=" + "dFvsDP"+ "&fechaInicio="+ fechaInicio+ "&fechaFinal="+ fechaFinal ,
        url: 'consultarInformes',
        success: function (data, textStatus) {
            $('#graficaDFvsDP' ).html(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}

function cargarReporteCausaRechazo(periodoTiempo, fechaInicio, fechaFinal){
    var complemento = "";
    if (fechaInicio && fechaFinal) {
        complemento += "&fechaInicio=" + fechaInicio + "&fechaFinal=" + fechaFinal;
    }
    jQuery.ajax({
        type: 'POST',
        data: 'periodoTiempo=' + periodoTiempo + "&template=" + "causaRechazo"+ "&fechaInicio="+ fechaInicio+ "&fechaFinal="+ fechaFinal ,
        url: 'consultarInformes',
        success: function (data, textStatus) {
            $('#graficaCausaRechazo' ).html(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
   
}

function cargarReporteConsultasBC(periodoTiempo, fechaInicio, fechaFinal){
    var complemento = "";
    if (fechaInicio && fechaFinal) {
        complemento += "&fechaInicio=" + fechaInicio + "&fechaFinal=" + fechaFinal;
    }
    jQuery.ajax({
        type: 'POST',
        data: 'periodoTiempo=' + periodoTiempo + "&template=" + "consultasBC"+ "&fechaInicio="+ fechaInicio+ "&fechaFinal="+ fechaFinal ,
        url: 'consultarInformes',
        success: function (data, textStatus) {
            $('#graficaConsultasBC' ).html(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });

    
}

