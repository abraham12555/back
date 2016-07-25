<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="cotizador"/>
        <script type="text/javascript">
            $(function(){

                //Se carga la pagina
                $('.elige-un-producto1').hide();
                $('.elige-un-modelo0').hide();

                //Se pasa el curso sobre un boton de producto
                $('.productElement').hover(
                    function(){
                        //Todos los botones que no son grises
                        $('.productElement.blueButton').addClass("cotizador-box");
                        $('.productElement.blueButton').addClass("lightGray");
                        $('.productElement.blueButton').removeClass("blueButton");

                        $(this).addClass('blueButton');
                        $(this).removeClass("cotizador-box");
                        $(this).removeClass("lightGray");
                    },
                    function(){
                        $('.productElement.blueButton').addClass("cotizador-box");
                        $('.productElement.blueButton').addClass("lightGray");
                        $('.productElement.blueButton').removeClass("blueButton");
                    }
                );

                //Se selecciona un producto
                $('.productElement').click(
                    function(){
                        //$('.productElement.blueButton').removeClass("blueButton");
                        //$('.productElement.cotizador-box').addClass("cotizador-box");
                        //$('.productElement.lightGray').addClass("lightGray");

                        $('.productElement.blueButton').addClass('blueButton');
                        $('.productElement.blueButton').removeClass("cotizador-box");
                        $('.productElement.blueButton').removeClass("lightGray");
                        $('.elige-un-modelo').hide();
                        $('.elige-un-producto0').hide();
                        $('.elige-un-producto1').fadeIn();
                        $('.elige-un-modelo0').fadeIn();
                    }
                );

                //Se elige cambiar el producto
                $('.cambiar-producto').click(
                    function(){
                        $('.elige-un-modelo0').hide();
                        $('.elige-un-modelo').fadeIn();
                        $('.elige-un-producto0').fadeIn();
                        $('.elige-un-producto1').hide();
                    }
                );
            });
        </script>
    </head>
    <body>       
        <div class="container clearFix">
            <div class="col6 col12-mob coti floatLeft">
                <h1 class="headingColor font35 fontWeight800 letterspacing1">NISSAN MAXIMA 2016</h1>
                <h3 class="darkBluetitle font30 fontWeight400 marginTop10">$250,000.00</h3>
                <div class="cotizador-bg"></div>
                <div class="width90p">
                    <p class="darkBluetitle font14 fontWeight400 letterspacing1 justify">Fusce vehicula dolor arcu, sit amet blandit dolor mollis nec. Donec viverra eleifend lacus, vitae ullamcorper metus. Sed sollicitudin ipsum quis nunc sollicitudin ultrices. Donec euismod scelerisque ligula. Maecenas eu varius risus, eu aliquet arcu. Curabitur fermentum suscipit est, tincidunt mattis lorem luctus id.</p>
                </div>
            </div>
            <div class="col6 col12-mob floatLeft">
                <div class="cotizador-rightside">
                    <h1 class="darkBluetitle font30 fontWeight400 marginBottom95">Cotiza tu Auto</h1>
                    <!-- PRODUCTO -->
                    <div class="elige-un-producto0 cotizador-box-container padding20">
                        <h1 class="darkBluetitle font20 fontWeight400 letterspacing1 marginBottom20">Elige un Auto</h1>
                        <div class="cotizador-p1-buttons clearFix">
                            <g:each in="${productos}" var="producto" status="i">
                                <div class="col6 floatLeft marginBottom10">
                                    <p id="productElement${i}" class="productElement width234 marginRight3 center cotizador-box lightGray paddingTop20 paddingBottom20 font18 fontWeight300 letterspacing1">${producto.nombre}</p>
                                </div>
                            </g:each>
                        </div>
                        <div class="marker">
                            <p>1</p>
                        </div>
                    </div>
                    <div class="elige-un-producto1 borderGrayTop clearFix elige-un-modelo">
                        <div class="col6  col12-mob floatLeft">
                            <div class="floatLeft paddingTop18 marginLeft24">
                                <p class="formTitleColor font12 paddingBottom12 fontWeight500">1. Elige un auto</p>
                                <p class="formTitleColor font19 fontWeight500">NISSAN MAXIMA</p>
                            </div>
                        </div>
                        <div class="cambiar-producto col6 col12-mob floatRight">
                            <div class="floatRight paddingTop18 marginBottom16">
                                <div class="borderGrayButton marginBottom16">
                                    <p class="center paddingTop9 paddingBottom9">Cambiar</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- MODELO -->
                <div class="elige-un-modelo borderGrayBottom borderGrayTop">
                    <h1 class="lightGray letterspacing1 font20 fontWeight400 marginLeft15 marginTop15 marginBottom25">Elige un Modelo</h1>
                    <div class="width478 autoMargin marginBottom25 clearFix">
                        <div class="width234 floatLeft">
                            <p class="cotizador-box center lightGray paddingTop18 paddingBottom18">2015 Regular SP</p>
                        </div>
                        <div class="width234 floatRight">
                            <p class="cotizador-box center lightGray paddingTop18 paddingBottom18">2015 Sport SP</p>
                        </div>
                    </div>
                </div>
                <div class="elige-un-modelo0 cotizador-box-container padding20 borderGrayTop">
                    <h1 class="darkBluetitle font20 fontWeight400 letterspacing1 marginBottom20">Elige un modelo</h1>
                    <div class="cotizador-p1-buttons clearFix">
                        <div class="col6 floatLeft marginBottom10">
                            <p class="width234 marginRight3 center blueButton paddingTop20 paddingBottom20 font18 fontWeight300 letterspacing1">2015 Regular SP</p>
                        </div>
                        <div class="col6 floatLeft marginBottom10">
                            <p class="width234 marginLeft3 center cotizador-box lightGray paddingTop20 paddingBottom20 font18 fontWeight300 letterspacing1">2015 Sport SP</p>
                        </div>
                        <div class="col6 floatLeft">
                            <p class="width234 marginRight3 center cotizador-box lightGray paddingTop20 paddingBottom20 font18 fontWeight300 letterspacing1">2016 Pro SP</p>
                        </div>
                        <div class="col6 floatLeft">
                            <p class="width234 marginLeft3 center cotizador-box lightGray paddingTop20 paddingBottom20 font18 fontWeight300 letterspacing1">2016 Sport SP</p>
                        </div>
                    </div>
                    <div class="marker">
                        <p>2</p>
                    </div>
                </div>
                <div class="elige-un-color borderGrayBottom">
                    <h1 class="opacity03 lightGray letterspacing1 font20 fontWeight400 marginLeft15 marginTop15 marginBottom25">Elige un color</h1>
                    <div class="width478 autoMargin marginBottom25 clearFix">
                        <div class="col4 floatLeft">
                            <div class="width150 autoMargin cotizador-box">
                                <p class="opacity03 center lightGray paddingTop18 paddingBottom18"><span class="cobalto oval1"></span> Cobalto</p>
                            </div>
                        </div>
                        <div class="col4 floatLeft">
                            <div class="width150 autoMargin cotizador-box">
                                <p class="opacity03 center lightGray paddingTop18 paddingBottom18"><span class="negro oval2"></span> Negro</p>
                            </div>
                        </div>
                        <div class="col4 floatRight">
                            <div class="width150 autoMargin cotizador-box">
                                <p class="opacity03 center lightGray paddingTop18 paddingBottom18"><span class="plata oval3"></span> Plata</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>

</html>
