$(document).ready(function(){

    //$("#nombreDelProducto").html("" );
    //$("#precioDelProducto").html("" );
    //$("#descripcionDelProducto").html("" );

    $('#a1').hide();
    $('#a2').hide();
    $('#a3').hide();
    $('#a4').hide();
    $('#a5').hide();
    $('#a6').hide();
    $('#a7').hide();
    $('#a8').hide();

    $('.cotizadorStep').hide();
    $('.cotizadorStep').eq(0).show();

    $('.step1 .cotizador-p1-buttons .cotizador-box').click(function() {
        var productoId = this.id.toString().substr(1);
        $.ajax({
            type: 'POST',
            data: { id: productoId },
            url: "/kosmos-app/cotizador/obtenerProducto",
            success: function (jsonResponse, textStatus) {
                var data = checkIfJson(jsonResponse);

                // Sustituir el contenido necesario
                var productoElegido = data.marca.nombre.toUpperCase() + " " + data.nombreDelProducto.toUpperCase();
                $('#nombreDelProducto').html( productoElegido );
                $('#descripcionDelProducto').html( data.descripcion );
                $('#imagenDelProducto').css('background-image', 'url(images/nissan/' + data.rutaImagenDefault + ')');
                $('#productoElegido').html( productoElegido );
                $('#productoElegido2').html( productoElegido );
                $('#productoElegido3').html( productoElegido );

                // Ir al paso siguiente
                $('.step1 .cotizador-p1-buttons .cotizador-box').parent().parent().parent().parent().parent().parent().slideUp();
                $('.step1 .cotizador-p1-buttons .cotizador-box').parent().parent().parent().parent().parent().parent().next('.cotizadorStep').slideDown();

                modeloList(productoId);
                //Obtener Modelos
                /*$.ajax({
                    type: 'POST',
                    data: { productoId: productoId },
                    url: "/kosmos-app/cotizador/obtenerModelos",
                    success: function (response, textStatus) {
                        console.log(response);
                        console.log($('#modelosList'));
                        $('#modelosList').append(response);
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
                    }
                });*/
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
            }
        });
    });

/////STEP 2
    $('.step2 .cotizador-p1-buttons .cotizador-box').click(function(){
        var modeloId = this.id.toString().substr(1);
        precioDelModelo(modeloId);
        colorDelModelo(modeloId);
        $("#modeloElegido").html($("#m"+modeloId).html());
        $("#modeloElegido2").html($("#m"+modeloId).html());
        $("#modeloElegido3").html($("#m"+modeloId).html());
        $(this).parent().parent().parent().parent().parent().parent().slideUp();
        $(this).parent().parent().parent().parent().parent().parent().next('.cotizadorStep').slideDown();
    });


///STEP 3
    $('.step3 .cotizador-p1-buttons .cotizador-box').click(function(){
        var colorId = this.id.toString().substr(1);
        var color = $("#c"+colorId).html();
        $("#colorElegido").html(color);
        $("#colorElegido2").html(color);
        $("#colorElegido3").html(color);
        imagenDelColor(colorId);
        $(this).parent().parent().parent().parent().parent().parent().slideUp();
        $(this).parent().parent().parent().parent().parent().parent().next('.cotizadorStep').slideDown();
    });


///STEP 4
    var restante;
    $('#slider').mouseup(function(){
        var engancheElegido = "$"+$("#engancheElegido").html()+".00";
        var enganche= parseInt($("#engancheElegido").html());
        var precio = parseInt($("#precioDelProducto").html());
        restante = precio - enganche;
        $("#engancheElegido2").html(engancheElegido);
        $("#engancheElegido3").html(engancheElegido);
        $("#engancheElegido4").html(engancheElegido);
        $("#meses12").html((Math.round(restante / 12)));
        $("#meses24").html((Math.round(restante / 24)));
        $("#meses36").html((Math.round(restante / 36)));
        $("#meses48").html((Math.round(restante / 48)));
        $(this).parent().parent().parent().parent().parent().parent().slideUp();
        $(this).parent().parent().parent().parent().parent().parent().next('.cotizadorStep').slideDown();
    });


///STEP 5
    var payChoice;
    $('.step5 .payChoice .cotizador-box').click(function(){
        $('.step5 .payChoice .cotizador-box').removeClass('blueButton');
        $(this).addClass('blueButton');
        payChoice = this;
    });

    var leyenda;
    $('.step5 .cotizador-box.small').click(function(){
        var periodo = this.id.toString().substr(1);
        var monto;
        var leyenda;
        if (payChoice.id == "quincenal") {
            monto = Math.round(restante / (periodo*2));
            leyenda = "$" + monto + " | Quincenal | " + periodo + " meses";
        } else {
            monto = Math.round(restante / periodo);
            leyenda = "$" + monto + " | Mensual | " + periodo + " meses";
        }
        $("#pagoElegido").html(leyenda);
        $("#pagoElegido2").html(leyenda);
        $(this).parent().parent().parent().parent().parent().parent().slideUp();
        $(this).parent().parent().parent().parent().parent().parent().next('.cotizadorStep').slideDown();
    });

///STEP 6
    $('.step6 .cotizador-p1-buttons .cotizador-box').click(function(){
        var seguro = this.id.toString().substr(1);
        var descripcionSeguro = $("#seguro"+seguro).html();
        $("#seguroElegido").html(descripcionSeguro);
        $(this).parent().parent().parent().parent().slideUp();
        $(this).parent().parent().parent().parent().next('.cotizadorStep').slideDown();
    });

///STEP 7

    $('.step7 .borderGrayButton').click(function(){
        var thisIndex = $(this).index('.step7 .borderGrayButton');
        $('.cotizadorStep.step7').slideUp();
        $('.cotizadorStep').eq(thisIndex).slideDown();
    });

///////CHANGE PREVIOUS STEP

    $('.cotizadorStep').each(function( index ) {
        var thisStep = $(this);
        if(thisStep.hasClass('step7')){

        }else{

            $('.borderGrayButton',this).last().click(function(){
                $(this).parent().parent().parent().parent().parent().slideUp();
                $(this).parent().parent().parent().parent().parent().prev('.cotizadorStep').slideDown();
            });
        }
    });
});

function checkIfJson(data) {
    try {
        return JSON.parse(data);
    } catch (e) {
        return data;
    }
}

function modeloList(productoId) {
    if (productoId == 2) {
        $('#a1').show();
        $('#a2').show();
        $('#a3').hide();
        $('#a4').hide();
        $('#a5').hide();
        $('#a6').hide();
        $('#a7').hide();
        $('#a8').hide();
    } else if (productoId == 6) {
        $('#a1').hide();
        $('#a2').hide();
        $('#a3').show();
        $('#a4').show();
        $('#a5').hide();
        $('#a6').hide();
        $('#a7').hide();
        $('#a8').hide();
    } else if (productoId == 1) {
        $('#a1').hide();
        $('#a2').hide();
        $('#a3').hide();
        $('#a4').hide();
        $('#a5').show();
        $('#a6').show();
        $('#a7').hide();
        $('#a8').hide();
    } else if (productoId == 3) {
        $('#a1').hide();
        $('#a2').hide();
        $('#a3').hide();
        $('#a4').hide();
        $('#a5').hide();
        $('#a6').hide();
        $('#a7').show();
        $('#a8').show();
    }
}

function precioDelModelo(modeloId) {
    if (modeloId == 1) {
        $('#precioDelProducto').html( "346000" );
    } else if (modeloId == 2) {
        $('#precioDelProducto').html( "383000" );
    } else if (modeloId == 3) {
        $('#precioDelProducto').html( "415000");
    } else if (modeloId == 4) {
        $('#precioDelProducto').html( "480000" );
    } else if (modeloId == 5) {
        $('#precioDelProducto').html( "256000" );
    } else if (modeloId == 6) {
        $('#precioDelProducto').html( "288000" );
    } else if (modeloId == 7) {
        $('#precioDelProducto').html( "401000" );
    } else if (modeloId == 8) {
        $('#precioDelProducto').html( "460000" );
    }
}

function colorDelModelo(modeloId) {
    if (modeloId == 1) {
        $('#k1').show();
        $('#k2').show();
        $('#k3').hide();
        $('#k4').hide();
        $('#k5').hide();
        $('#k6').hide();
        $('#k7').hide();
        $('#k8').hide();
        $('#k9').hide();
        $('#k10').hide();
        $('#k11').hide();
        $('#k12').hide();
    } else if (modeloId == 2) {
        $('#k1').hide();
        $('#k2').hide();
        $('#k3').show();
        $('#k4').hide();
        $('#k5').hide();
        $('#k6').hide();
        $('#k7').hide();
        $('#k8').hide();
        $('#k9').hide();
        $('#k10').hide();
        $('#k11').hide();
        $('#k12').hide();
    } else if (modeloId == 3) {
        $('#k1').hide();
        $('#k2').hide();
        $('#k3').hide();
        $('#k4').show();
        $('#k5').hide();
        $('#k6').hide();
        $('#k7').hide();
        $('#k8').hide();
        $('#k9').hide();
        $('#k10').hide();
        $('#k11').hide();
        $('#k12').hide();
    } else if (modeloId == 4) {
        $('#k1').hide();
        $('#k2').hide();
        $('#k3').hide();
        $('#k4').hide();
        $('#k5').show();
        $('#k6').show();
        $('#k7').hide();
        $('#k8').hide();
        $('#k9').hide();
        $('#k10').hide();
        $('#k11').hide();
        $('#k12').hide();
    } else if (modeloId == 5) {
        $('#k1').hide();
        $('#k2').hide();
        $('#k3').hide();
        $('#k4').hide();
        $('#k5').hide();
        $('#k6').hide();
        $('#k7').show();
        $('#k8').show();
        $('#k9').hide();
        $('#k10').hide();
        $('#k11').hide();
        $('#k12').hide();
    } else if (modeloId == 6) {
        $('#k1').hide();
        $('#k2').hide();
        $('#k3').hide();
        $('#k4').hide();
        $('#k5').hide();
        $('#k6').hide();
        $('#k7').hide();
        $('#k8').hide();
        $('#k9').show();
        $('#k10').hide();
        $('#k11').hide();
        $('#k12').hide();
    } else if (modeloId == 7) {
        $('#k1').hide();
        $('#k2').hide();
        $('#k3').hide();
        $('#k4').hide();
        $('#k5').hide();
        $('#k6').hide();
        $('#k7').hide();
        $('#k8').hide();
        $('#k9').hide();
        $('#k10').show();
        $('#k11').show();
        $('#k12').hide();
    } else if (modeloId == 8) {
        $('#k1').hide();
        $('#k2').hide();
        $('#k3').hide();
        $('#k4').hide();
        $('#k5').hide();
        $('#k6').hide();
        $('#k7').hide();
        $('#k8').hide();
        $('#k9').hide();
        $('#k10').hide();
        $('#k11').hide();
        $('#k12').show();
    }
}

function imagenDelColor(colorId) {
    if (colorId == 1) {
        $('#imagenDelProducto').css('background-image', 'url(images/nissan/2016-altima-azul.png)');
    } else if (colorId == 2) {
        $('#imagenDelProducto').css('background-image', 'url(images/nissan/2016-altima-gris_2.png)');
    } else if (colorId == 3) {
        $('#imagenDelProducto').css('background-image', 'url(images/nissan/2016-altima-rojo.png)');
    } else if (colorId == 4) {
        $('#imagenDelProducto').css('background-image', 'url(images/nissan/2016-xtrail-blanca.png)');
    } else if (colorId == 5) {
        $('#imagenDelProducto').css('background-image', 'url(images/nissan/2016-xtrail-negra.png)');
    } else if (colorId == 6) {
        $('#imagenDelProducto').css('background-image', 'url(images/nissan/2016-xtrail-verde.png)');
    } else if (colorId == 7) {
        $('#imagenDelProducto').css('background-image', 'url(images/nissan/2016-maxima-azul.png)');
    } else if (colorId == 8) {
        $('#imagenDelProducto').css('background-image', 'url(images/nissan/2016-maxima-gris.png)');
    } else if (colorId == 9) {
        $('#imagenDelProducto').css('background-image', 'url(images/nissan/2016-maxima-rojo.png)');
    } else if (colorId == 10) {
        $('#imagenDelProducto').css('background-image', 'url(images/nissan/2016-sentra-blanco.png)');
    } else if (colorId == 11) {
        $('#imagenDelProducto').css('background-image', 'url(images/nissan/2016-sentra-gris.png)');
    } else if (colorId == 12) {
        $('#imagenDelProducto').css('background-image', 'url(images/nissan/2016-sentra-negro.png)');
    }

}