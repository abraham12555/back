$(function(){

    //**********
    // CUANDO SE CARGA LA PAGINA POR PRIMERA VEZ
    //**********

    //Se carga la pagina
    $('.producto-form1').hide();

    $('.modelo-form1').hide();
    $('.modelo-form2').hide();

    $('.color-form1').hide();
    $('.color-form2').hide();

    $('.financiamiento-form0').hide();
    $('.financiamiento-form1').hide();
    $('.financiamiento-form2').hide();
    $('.financiamiento-form3').hide();

    $('.plazos-form0').hide();
    $('.plazos-form1').hide();
    $('.plazos-form2').hide();
    $('.plazos-form4').hide();

    $('.seguro-form0').hide();
    $('.seguro-form1').hide();
    $('.seguro-form2').hide();

    $('.solicitar-credito').hide();

    //**********
    // CUANDO SE ELIGE EL PRODUCTO
    //**********

    //Se pasa el curso sobre un boton de producto
    $('.productElement').hover(
        function(){
            //Todos los botones que no son grises
            $('.productElement.blueButton').addClass("lightGray");
            $('.productElement.blueButton').removeClass("blueButton");
            $(this).addClass('blueButton');
            $(this).removeClass("lightGray");
        },
        function(){
            $('.productElement.blueButton').addClass("lightGray");
            $('.productElement.blueButton').removeClass("blueButton");
        }
    );

    //Se selecciona un producto
    $('.productElement').click(
        function (){
            removerOpacity($('.producto-form1'));
            $('.productElement.blueButton').addClass('blueButton');
            $('.productElement.blueButton').removeClass("lightGray");
            $('.modelo-form0').hide();
            $('.producto-form0').hide();
            $('.producto-form1').fadeIn();
            $('.modelo-form1').fadeIn();
            $('.financiamiento-form0').show();
        }
    );

    //Se elige cambiar el producto
    $('.cambiar-producto').click(
        function(){
            clickCambiarProducto();
        }
    );

    //**********
    // CUANDO SE ELIGE EL MODELO
    //**********

    //Se pasa el cursor sobre un boton de modelo
    $('.modelElement').hover(
        function(){
            //Todos los botones que no son grises
            $('.modelElement.blueButton').addClass("lightGray");
            $('.modelElement.blueButton').removeClass("blueButton");
            $(this).addClass('blueButton');
            $(this).removeClass("lightGray");
        },
        function(){
            $('.modelElement.blueButton').addClass("lightGray");
            $('.modelElement.blueButton').removeClass("blueButton");
        }
    );

    //Se selecciona un modelo
    $('.modelElement').click(
        function(){
            $('.modelElement.blueButton').addClass('blueButton');
            $('.modelElement.blueButton').removeClass("lightGray");
            $('.modelo-form0').hide();
            $('.modelo-form1').hide();
            $('.modelo-form2').fadeIn();
            $('.color-form1').fadeIn();
            $('.color-form0').hide();
            $('.financiamiento-form0').hide();
            $('.financiamiento-form1').fadeIn();
            agregarOpacity($('.producto-form1'), $('.cambiar-producto'));
            $('.cambiar-producto').off('click');
        }
    );

    //Se elige cambiar el modelo
    $('.cambiar-modelo').click(
        function(){
            clickCambiarModelo();
        }
    );

    //**********
    // CUANDO SE ELIGE EL COLOR
    //**********

    $('.colorElement').hover(
        function(){
            //Todos los botones que no son grises
            $('.colorElement.blueButton').addClass("lightGray");
            $('.colorElement.blueButton').removeClass("blueButton");
            $(this).addClass('blueButton');
            $(this).removeClass("lightGray");
        },
        function(){
            $('.colorElement.blueButton').addClass("lightGray");
            $('.colorElement.blueButton').removeClass("blueButton");
        }
    );

    //Se selecciona un color
    $('.colorElement').click(
        function (){
            $('.producto-form0').hide();
            $('.producto-form1').hide();
            agregarOpacity($('.modelo-form2'), $('.cambiar-modelo'));
            $('.color-form0').hide();
            $('.color-form1').hide();
            $('.color-form2').fadeIn();
            $('.financiamiento-form0').hide();
            $('.financiamiento-form1').hide();
            $('.financiamiento-form2').fadeIn();
            $('.plazos-form1').fadeIn();
            $('.cambiar-modelo').off('click');
        }
    );

    //Se elige cambiar el color
    $('.cambiar-color').click(
        function(){
            clickCambiarColor();
        }
    );

    //**********
    // CUANDO SE ELIGE EL ENGANCHE
    //**********

    //Se selecciona un color
    $('.engancheElement').click(
        function () {
            $('.modelo-form2').hide();
            $('.financiamiento-form2').hide();
            $('.financiamiento-form3').fadeIn();
            agregarOpacity($('.color-form2'), $('.cambiar-color'));
            $('.cambiar-color').off('click');
            $('.plazos-form1').hide();
            $('.plazos-form2').fadeIn();
            $('.plazoElement.lightGray').off('click');
            $('.seguro-form0').fadeIn();
        }
    );

    //Se elige cambiar el enganche
    $('.cambiar-enganche').click(
        function(){
            clickCambiarEnganche();
        }
    );

    //**********
    // CUANDO SE ELIGE PERIODO Y PLAZO
    //**********

    // Cuando se da click sobre un boton de periodo
    $('.periodoElement').click(
        function (){
            $('.periodoElement.blueButton').addClass("lightGray");
            $('.periodoElement.blueButton').removeClass("blueButton");
            $(this).removeClass("lightGray");
            $(this).addClass('blueButton');
            $('.plazoElement.lightGray').on('click',
                function() {
                    $(this).removeClass("lightGray");
                    $(this).addClass('blueButton');
                    $('.color-form2').hide();
                    $('.plazoElement.blueButton').addClass("lightGray");
                    $('.plazoElement.blueButton').removeClass("blueButton");
                    agregarOpacity($('.financiamiento-form3'), $('.cambiar-enganche'));
                    $('.plazos-form4').fadeIn();
                    $('.plazos-form2').hide();
                    $('.seguro-form0').hide();
                    $('.seguro-form1').fadeIn();
                }
            );
        }
    );

    //Se elige cambiar el plazo
    $('.cambiar-plazo').click(
        function(){
            clickCambiarPlazo();
        }
    );

    //**********
    // CUANDO SE ELIGE SEGURO
    //**********

    $('.seguroElement').hover(
        function(){
            //Todos los botones que no son grises
            $('.seguroElement.blueButton').addClass("lightGray");
            $('.seguroElement.blueButton').removeClass("blueButton");
            $(this).addClass('blueButton');
            $(this).removeClass("lightGray");
        },
        function(){
            $('.seguroElement.blueButton').addClass("lightGray");
            $('.seguroElement.blueButton').removeClass("blueButton");
        }
    );

    $('.seguroElement').click(
        function (){
            $('.seguroElement.blueButton').addClass("lightGray");
            $('.seguroElement.blueButton').removeClass("blueButton");
            $(this).removeClass("lightGray");
            $(this).addClass('blueButton');
            $('.seguro-form0').hide();
            $('.seguro-form1').hide();
            showResumen();
        }
    );

    function showResumen() {

        $('.producto-form1').show();
        removerOpacity($('.producto-form1'));


        $('.modelo-form2').show();
        removerOpacity($('.modelo-form2'));
        $('.cambiar-producto').on('click',
            function() {
                clickCambiarProducto();
            }
        );

        $('.color-form2').show();
        removerOpacity($('.color-form2'));
        $('.cambiar-modelo').on('click',
            function() {
                clickCambiarModelo();
            }
        );

        $('.financiamiento-form3').show();
        removerOpacity($('.financiamiento-form3'));
        $('.cambiar-color').on('click',
            function() {
                clickCambiarColor();
            }
        );

        $('.plazos-form4').show();
        removerOpacity($('.plazos-form4'));
        $('.cambiar-enganche').on('click',
            function() {
                clickCambiarEnganche();
            }
        );

        $('.seguro-form2').show();
        $('.cambiar-plazo').on('click',
            function() {
                clickCambiarPlazo();
            }
        );

        $('.cambiar-seguro').on('click',
            function() {
                clickCambiarSeguro();
            }
        );

        $('.solicitar-credito').show();
    }

    //**********
    // COMUNES
    //**********

    //Código Comun para boton de cambiar producto
    function clickCambiarProducto() {

        $('.producto-form0').fadeIn();
        $('.producto-form1').hide();

        $('.modelo-form0').fadeIn();
        $('.modelo-form1').hide();
        $('.modelo-form2').hide();

        $('.color-form0').fadeIn();
        $('.color-form1').hide();
        $('.color-form2').hide()

        $('.financiamiento-form0').hide();
        $('.financiamiento-form0').hide();
        $('.financiamiento-form3').hide();

        $('.plazos-form4').hide();

        $('.seguro-form2').hide();

        $('.solicitar-credito').hide();
    }

    function clickCambiarModelo() {
        $('.cambiar-producto').on('click',
            function() {
                clickCambiarProducto();
            }
        );

        removerOpacity($('.producto-form1'));

        $('.productElement.blueButton').addClass('blueButton');
        $('.productElement.blueButton').removeClass("lightGray");

        $('.modelo-form0').hide();
        $('.modelo-form1').fadeIn();
        $('.modelo-form2').hide();

        $('.color-form0').fadeIn();
        $('.color-form1').hide();
        $('.color-form2').hide()

        $('.financiamiento-form0').fadeIn();
        $('.financiamiento-form1').hide();
        $('.financiamiento-form3').hide();

        $('.plazos-form0').hide();
        $('.plazos-form4').hide();

        $('.seguro-form2').hide();

        $('.solicitar-credito').hide();
    }

    function clickCambiarColor() {
        $('.cambiar-modelo').on('click',
            function() {
                clickCambiarModelo();
            }
        );
        agregarOpacity($('.producto-form1'), $('.cambiar-producto'));
        removerOpacity($('.modelo-form2'));

        $('.producto-form1').fadeIn();

        $('.modelo-form0').hide();
        $('.modelo-form1').hide();
        $('.modelo-form2').fadeIn();

        $('.color-form0').hide();
        $('.color-form1').fadeIn();
        $('.color-form2').hide();

        $('.financiamiento-form0').hide();
        $('.financiamiento-form1').fadeIn();
        $('.financiamiento-form2').hide();
        $('.financiamiento-form3').hide();

        $('.plazos-form0').hide();
        $('.plazos-form1').hide();
        $('.plazos-form4').hide();

        $('.seguro-form2').hide();

        $('.solicitar-credito').hide();
    }

    function clickCambiarEnganche() {
        $('.cambiar-color').on('click',
            function() {
                clickCambiarColor();
            }
        );
        removerOpacity($('.color-form2'));
        agregarOpacity($('.modelo-form2'), $('.cambiar-modelo'));

        $('.producto-form1').hide();

        $('.modelo-form2').fadeIn();

        $('.financiamiento-form0').hide();
        $('.financiamiento-form1').hide();
        $('.financiamiento-form2').fadeIn();
        $('.financiamiento-form3').hide();

        $('.plazos-form0').hide();
        $('.plazos-form1').fadeIn();
        $('.plazos-form2').hide();
        $('.plazos-form3').fadeIn();
        $('.plazos-form4').hide();

        $('.seguro-form0').hide();
        $('.seguro-form2').hide();

        $('.solicitar-credito').hide();
    }

    function clickCambiarPlazo() {
        $('.cambiar-enganche').on('click',
            function() {
                clickCambiarEnganche();
            }
        );
        agregarOpacity($('.color-form2'), $('.cambiar-color'));
        removerOpacity($('.financiamiento-form3'));

        $('.producto-form1').hide();

        $('.modelo-form2').hide();

        $('.color-form2').fadeIn();

        $('.financiamiento-form2').hide();
        $('.financiamiento-form3').fadeIn();

        $('.plazos-form1').hide();
        $('.plazos-form2').fadeIn();
        $('.plazos-form4').hide();

        $('.seguro-form0').fadeIn();
        $('.seguro-form1').hide();
        $('.seguro-form2').hide();

        $('.solicitar-credito').hide();
    }

    function clickCambiarSeguro() {
        $('.cambiar-plazo').on('click',
            function() {
                clickCambiarPlazo();
            }
        );
        agregarOpacity($('.financiamiento-form3'), $('.cambiar-enganche'));
        removerOpacity($('.plazos-form4'));

        $('.producto-form1').hide();

        $('.modelo-form2').hide();

        $('.color-form2').hide();

        $('.plazos-form1').hide();
        $('.plazos-form2').hide();
        $('.plazos-form4').fadeIn();

        $('.seguro-form1').fadeIn();
        $('.seguro-form2').hide();

        $('.solicitar-credito').hide();
    }

    function removerOpacity( form ) {
        form.removeClass('paddingBottom15');
        form.removeClass('opacity03');
    }

    function agregarOpacity( form, button ) {
        button.off('click');
        form.addClass('paddingBottom15');
        form.addClass('opacity03');
    }
});