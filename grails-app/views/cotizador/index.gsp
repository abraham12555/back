<!DOCTYPE html>
<html>
    <head>
        <title>Kosmos - Cotizador</title>
        <script>
            $( function() {
                $( "#slider" ).slider({
                    value:30,
                    min: 10,
                    max: 90,
                    step: 10,
                    slide: function( event, ui ) {
                        $( "#engancheElegido" ).html(parseInt($("#precioDelProducto").html()) * (ui.value/100));
                    }
                });
            } );
        </script>
    </head>

    <body>
        <div class="container clearFix">
            <div class="col6 col12-mob coti floatLeft down800">
                <div class="padding15">
                    <h1 id="nombreDelProducto" class="headingColor font35 fontWeight800 letterspacing1">
                        NISSAN MAXIMA 2016
                    </h1>
                    <h3 id="precioDelProducto" class="darkBluetitle font30 fontWeight400 marginBottom20">
                    </h3>
                    <div id="imagenDelProducto" class="cotizador-bg none800"></div>
                    <div class="paddingAside10">
                        <p id="descripcionDelProducto" class="darkBluetitle font14 fontWeight400 letterspacing1 justify">
                            Fusce vehicula dolor arcu, sit amet blandit dolor mollis nec.
                            Donec viverra eleifend lacus, vitae ullamcorper metus. Sed
                            sollicitudin ipsum quis nunc sollicitudin ultrices. Donec
                            euismod scelerisque ligula. Maecenas eu varius risus, eu
                            aliquet arcu. Curabitur fermentum suscipit est, tincidunt
                            mattis lorem luctus id.
                        </p>
                    </div>
                </div>
            </div>

            <div class="col6 col12-mob floatLeft down800">
                <g:form controller="cotizador" action="procesar">
                    <!-- BEGIN STEP1 -->
                    <div class="step1 cotizadorStep">
                        <div class="cotizador-rightside">
                            <h1 class="darkBluetitle font30 fontWeight400 marginBottom95">Cotiza tu Auto</h1>

                            <div class="cotizador-box-container padding20">
                                <h1 class="darkBluetitle font20 fontWeight400 letterspacing1 marginBottom20"><span
                                        class="visibleMob">1.</span>Elige un Auto</h1>

                                <div class="cotizador-p1-buttons clearFix">
                                    <g:each in="${productos}" var="producto" status="i">
                                        <div class="col6 floatLeft marginBottom10">
                                            <div class="paddingAside5">
                                                <p id="p${producto.id}" class="width350 cotizador-box">${producto.nombreDelProducto}</p>
                                            </div>
                                        </div>
                                    </g:each>
                                </div>

                                <div class="marker">
                                    <p>1</p>
                                </div>
                            </div>
                        </div>

                        <div class="elige-un-modelo borderGrayBottom borderGrayTop">
                            <h1 class="lightGray letterspacing1 font20 fontWeight400 marginLeft15 marginTop15 marginBottom25">Elige un Modelo</h1>

                            <div class="autoMargin marginBottom25 clearFix">
                                <div class="col6 floatLeft">
                                    <div class="paddingAside5">
                                        <div class="width350 cotizador-box ">2015 Regular SP</div>
                                    </div>
                                </div>

                                <div class="col6 floatLeft">
                                    <div class="paddingAside5">
                                        <div class="width350 cotizador-box ">2015 Sport SP</div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="elige-un-modelo borderGrayBottom borderGrayTop opacityCont">
                            <h1 class="lightGray letterspacing1 font20 fontWeight400 marginLeft15 marginTop15 marginBottom25">Elige un color</h1>

                            <div class="autoMargin marginBottom25 clearFix">
                                <div class="col4 floatLeft">
                                    <p class="width150 autoMargin cotizador-box"><span class="cobalto oval1"></span> Cobalto</p>
                                </div>

                                <div class="col4 floatLeft">
                                    <p class="width150 autoMargin cotizador-box"><span class="negro oval2"></span> Negro</p>
                                </div>

                                <div class="col4 floatRight">
                                    <p class="width150 autoMargin cotizador-box"><span class="plata oval3"></span> Plata</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- ENF STEP1 -->
                    <!-- BEGIN STEP2 -->
                    <div class="step2 cotizadorStep">
                        <div class="cotizador-rightside">
                            <h1 class="darkBluetitle font30 fontWeight400 marginBottom95">Cotiza tu Auto</h1>

                            <div class="borderGrayTop clearFix elige-un-modelo">
                                <div class="col6 floatLeft">
                                    <div class="floatLeft paddingTop18 marginLeft24">
                                        <p class="formTitleColor font12 paddingBottom12 fontWeight500">1. Elige un auto</p>

                                        <p id="productoElegido" class="formTitleColor font19 fontWeight500 marginBottom15"></p>
                                    </div>
                                </div>

                                <div class="col6 floatRight">
                                    <div class="floatRight paddingTop18 marginBottom16">
                                        <div class="borderGrayButton marginBottom15">
                                            <p class="center paddingTop9 paddingBottom9">Cambiar</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="cotizador-box-container padding20 borderGrayTop">
                                <h1 class="darkBluetitle font20 fontWeight400 letterspacing1 marginBottom20"><span
                                        class="visibleMob">2.</span>Elige un modelo</h1>

                                <div id="modelosList" class="cotizador-p1-buttons clearFix">

                                    <div id="a1" class="col6 floatLeft marginBottom10">
                                        <div class="paddingAside5">
                                            <p id="m1" class="width350 cotizador-box">SENSE 2.5L´17</p>
                                        </div>
                                    </div>
                                    <div id="a2"  class="col6 floatLeft marginBottom10">
                                        <div class="paddingAside5">
                                            <p id="m2" class="width350 cotizador-box">ADVANCE 2.5L'17</p>
                                        </div>
                                    </div>

                                    <div id="a3"  class="col6 floatLeft marginBottom10">
                                        <div class="paddingAside5">
                                            <p id="m3" class="width350 cotizador-box">ARMOR CVT '16</p>
                                        </div>
                                    </div>
                                    <div id="a4"  class="col6 floatLeft marginBottom10">
                                        <div class="paddingAside5">
                                            <p id="m4" class="width350 cotizador-box">ADVANCE 2 ROW ´17</p>
                                        </div>
                                    </div>

                                    <div id="a5"  class="col6 floatLeft marginBottom10">
                                        <div class="paddingAside5">
                                            <p id="m5" class="width350 cotizador-box">ADVANCE CVT 3.5LTS ´16</p>
                                        </div>
                                    </div>
                                    <div id="a6"  class="col6 floatLeft marginBottom10">
                                        <div class="paddingAside5">
                                            <p id="m6" class="width350 cotizador-box">EXCLUSIVE CVT 3.5LTS ´16</p>
                                        </div>
                                    </div>

                                    <div id="a7"  class="col6 floatLeft marginBottom10">
                                        <div class="paddingAside5">
                                            <p id="m7" class="width350 cotizador-box">EXCLUSIVE CVT MY´17</p>
                                        </div>
                                    </div>
                                    <div id="a8"  class="col6 floatLeft marginBottom10">
                                        <div class="paddingAside5">
                                            <p id="m8" class="width350 cotizador-box">SENSE MT MY´17</p>
                                        </div>
                                    </div>

                                </div>

                                <div class="marker">
                                    <p>2</p>
                                </div>
                            </div>
                        </div>

                        <div class="elige-un-modelo borderGrayBottom borderGrayTop opacityCont">
                            <h1 class="lightGray letterspacing1 font20 fontWeight400 marginLeft15 marginTop15 marginBottom25">Elige un color</h1>

                            <div class="autoMargin marginBottom25 clearFix">
                                <div class="col4 floatLeft">
                                    <p class="width150 autoMargin cotizador-box"><span class="cobalto oval1"></span> Cobalto</p>
                                </div>

                                <div class="col4 floatLeft">
                                    <p class="width150 autoMargin cotizador-box"><span class="negro oval2"></span> Negro</p>
                                </div>

                                <div class="col4 floatRight">
                                    <p class="width150 autoMargin cotizador-box"><span class="plata oval3"></span> Plata</p>
                                </div>
                            </div>
                        </div>

                        <div class="elige-un-modelo borderGrayBottom opacityCont">
                            <h1 class="lightGray letterspacing1 font20 fontWeight400 marginLeft15 marginTop15 marginBottom84">Elige un financiamiento</h1>
                        </div>
                    </div>
                    <!-- END STEP 2-->
                    <!-- BEGIN STEP 3-->
                    <div class="step3 cotizadorStep">
                        <div class="cotizador-rightside">
                            <h1 class="darkBluetitle font30 fontWeight400 marginBottom10">Cotiza tu Auto</h1>

                            <div class="borderGrayTop borderGrayBottom paddingBottom15 clearFix elige-un-modelo opacity03">
                                <div class="col6 floatLeft">
                                    <div class="floatLeft paddingTop18 marginLeft24">
                                        <p class="formTitleColor font12 paddingBottom12 fontWeight500">1. Elige un auto</p>

                                        <p id="productoElegido2" class="formTitleColor font19 fontWeight500 marginBottom15"></p>
                                    </div>
                                </div>

                                <div class="col6 floatRight">
                                    <div class="floatRight paddingTop18 marginBottom15">
                                        <div class="borderGrayButton marginBottom15">
                                            <p class="center paddingTop10 paddingBottom10">Cambiar</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="borderGrayBottom paddingBottom15 clearFix elige-un-modelo">
                                <div class="col6 floatLeft">
                                    <div class="floatLeft paddingTop18 marginLeft24">
                                        <p class="formTitleColor font12 paddingBottom12 fontWeight500">2. Elige un Modelo</p>

                                        <p id="modeloElegido" class="formTitleColor font19 fontWeight500 marginBottom15"></p>
                                    </div>
                                </div>

                                <div class="col6 floatRight">
                                    <div class="floatRight paddingTop18 marginBottom15">
                                        <div class="borderGrayButton marginBottom15">
                                            <p class="center paddingTop10 paddingBottom10">Cambiar</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="cotizador-box-container padding20">
                                <h1 class="darkBluetitle font20 fontWeight400 letterspacing1 marginBottom20"><span
                                        class="visibleMob">3.</span>Elige un Color</h1>

                                <div id="coloresList" class="cotizador-p1-buttons clearFix">
                                    <div id="k1" class="col6 floatLeft marginBottom10">
                                        <div class="paddingAside5">
                                            <p id="c1" class="width350 cotizador-box">Azul</p>
                                        </div>
                                    </div>
                                    <div id="k2" class="col6 floatLeft marginBottom10">
                                        <div class="paddingAside5">
                                            <p id="c2" class="width350 cotizador-box">Gris</p>
                                        </div>
                                    </div>
                                    <div id="k3" class="col6 floatLeft marginBottom10">
                                        <div class="paddingAside5">
                                            <p id="c3" class="width350 cotizador-box">Rojo</p>
                                        </div>
                                    </div>
                                    <div id="k4" class="col6 floatLeft marginBottom10">
                                        <div class="paddingAside5">
                                            <p id="c4" class="width350 cotizador-box">Blanca</p>
                                        </div>
                                    </div>
                                    <div id="k5" class="col6 floatLeft marginBottom10">
                                        <div class="paddingAside5">
                                            <p id="c5" class="width350 cotizador-box">Negra</p>
                                        </div>
                                    </div>
                                    <div id="k6" class="col6 floatLeft marginBottom10">
                                        <div class="paddingAside5">
                                            <p id="c6" class="width350 cotizador-box">Verde</p>
                                        </div>
                                    </div>
                                    <div id="k7" class="col6 floatLeft marginBottom10">
                                        <div class="paddingAside5">
                                            <p id="c7" class="width350 cotizador-box">Azul</p>
                                        </div>
                                    </div>
                                    <div id="k8" class="col6 floatLeft marginBottom10">
                                        <div class="paddingAside5">
                                            <p id="c8" class="width350 cotizador-box">Gris</p>
                                        </div>
                                    </div>
                                    <div id="k9" class="col6 floatLeft marginBottom10">
                                        <div class="paddingAside5">
                                            <p id="c9" class="width350 cotizador-box">Rojo</p>
                                        </div>
                                    </div>
                                    <div id="k10" class="col6 floatLeft marginBottom10">
                                        <div class="paddingAside5">
                                            <p id="c10" class="width350 cotizador-box">Blanco</p>
                                        </div>
                                    </div>
                                    <div id="k11" class="col6 floatLeft marginBottom10">
                                        <div class="paddingAside5">
                                            <p id="c11" class="width350 cotizador-box">Gris</p>
                                        </div>
                                    </div>
                                    <div id="k12" class="col6 floatLeft marginBottom10">
                                        <div class="paddingAside5">
                                            <p id="c12" class="width350 cotizador-box">Negro</p>
                                        </div>
                                    </div>
                                </div>

                                <div class="marker">
                                    <p>3</p>
                                </div>
                            </div>
                        </div>

                        <div class="elige-un-modelo borderGrayBottom borderGrayTop">
                            <h1 class="lightGray letterspacing1 font20 fontWeight400 marginLeft15 marginTop15 marginBottom40 opacity05">Elige tu enganche</h1>

                            <div class="autoMargin marginBottom25 clearFix loading-bar-container marginBottom40">
                                <div class="loading-bar-line autoMargin opacity05"></div>

                                <div class="loading-bar-box center">
                                    <p class="loading-bar-number paddingTop5 paddingBottom5 opacity05">20%</p>
                                </div>
                            </div>
                        </div>

                        <div class="elige-un-color">
                            <h1 class="opacity03 lightGray letterspacing1 font20 fontWeight400 marginLeft15 marginTop15 marginBottom25">Plazos y Pagos</h1>
                        </div>
                    </div>
                    <!-- END STEP 3 -->
                    <!-- BEGIN STEP 4 -->
                    <div class="step4 cotizadorStep">
                        <div class="cotizador-rightside">
                            <h1 class="darkBluetitle font30 fontWeight400 marginBottom10">Cotiza tu Auto</h1>

                            <div class="borderGrayBottom paddingBottom15 clearFix elige-un-modelo opacity03">
                                <div class="col6 floatLeft">
                                    <div class="floatLeft paddingTop18 marginLeft24">
                                        <p class="formTitleColor font12 paddingBottom12 fontWeight500 opacity05">2. Elige un Modelo</p>

                                        <p id="modeloElegido2" class="formTitleColor font19 fontWeight500 opacity05 marginBottom15"></p>
                                    </div>
                                </div>

                                <div class="col6 floatRight">
                                    <div class="floatRight paddingTop18 marginBottom15">
                                        <div class="borderGrayButton marginBottom15">
                                            <p class="center paddingTop10 paddingBottom10">Cambiar</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class=" paddingBottom15 clearFix elige-un-modelo">
                                <div class="col6 floatLeft">
                                    <div class="floatLeft paddingTop18 marginLeft24">
                                        <p class="formTitleColor font12 paddingBottom12 fontWeight500">3. Elige un Color</p>

                                        <div class="floatLeft">

                                            <p id="colorElegido" class="formTitleColor font19 fontWeight500 floatLeft marginBottom15"></p>
                                        </div>
                                    </div>
                                </div>

                                <div class="col6 floatRight">
                                    <div class="floatRight paddingTop18 marginBottom15">
                                        <div class="borderGrayButton marginBottom15">
                                            <p class="center paddingTop10 paddingBottom10">Cambiar</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="cotizador-box-container padding20">
                                <h1 class="darkBluetitle font20 fontWeight400 letterspacing1 marginBottom20 floatLeft"><span
                                        class="visibleMob">4.</span>Elige tu Enganche</h1>

                                <p id="engancheElegido" class="lightBlue floatRight font20 fontWeight500"></p>

                                <div class="clearFloat"></div>

                                <div class="cotizador-p1-buttons clearFix">
                                    <div class="marginTop52">

                                        <div class="marginBottom25 clearFix loading-bar-container inner marginBottom40">
                                            <div id="slider" class="loading-bar-line autoMargin opacity05">
                                            </div>

                                            <p class="floatLeft marginLeft12 font14 fontWeight500 gray opacity05 paddingTop5">MIN</p>

                                            <p class="floatRight marginRight12 font14 fontWeight500 gray opacity05 paddingTop5">MAX</p>

                                            <div class="clearFloat"></div>
                                        </div>
                                    </div>
                                </div>

                                <div class="marker">
                                    <p>4</p>
                                </div>
                            </div>
                        </div>

                        <div class="elige-un-color">
                            <h1 class="opacity05 lightGray letterspacing1 font20 fontWeight400 marginTop15 marginBottom25">Elige tus pagos y el plazo.</h1>

                            <div class="col6 floatLeft clearFix">
                                <div class="paddingAside5">
                                    <p class="paddingTop18 paddingBottom18 center gray width350 borderRadius2 borderGray opacity04">Quincenal</p>
                                </div>
                            </div>

                            <div class="col6 floatLeft clearFix">
                                <div class="paddingAside5">
                                    <p class="width350 borderRadius2 borderGray opacity04 paddingTop18 paddingBottom18 center gray">Mensual</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- END STEP 4 -->
                    <!-- BEGIN STEP 5 -->
                    <div class="step5 cotizadorStep">
                        <div class="cotizador-rightside">
                            <h1 class="dark-blue-titles font30 fontWeight400 marginBottom10">Cotiza tu Auto</h1>

                            <div class="borderGrayBottom paddingBottom15 clearFix elige-un-modelo opacity05">
                                <div class="col6 floatLeft">
                                    <div class="floatLeft paddingTop18 marginLeft24">
                                        <p class="formTitleColor font12 paddingBottom12 fontWeight500">3. Elige un Color</p>

                                        <p id="colorElegido2" class="formTitleColor font20 fontWeight400 marginBottom15"></p>
                                    </div>
                                </div>

                                <div class="col6 floatRight">
                                    <div class="floatRight paddingTop18 marginBottom15">
                                        <div class="borderGrayButton marginBottom15">
                                            <p class="center paddingTop10 paddingBottom10">Cambiar</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="borderGrayBottom paddingBottom15 clearFix elige-un-modelo">
                                <div class="col6 floatLeft">
                                    <div class="floatLeft paddingTop18 marginLeft24">
                                        <p class="formTitleColor font12 paddingBottom12 fontWeight500">4. Tu anganche</p>

                                        <p id="engancheElegido2" class="formTitleColor font20 fontWeight400 marginBottom15"></p>
                                    </div>
                                </div>

                                <div class="col6 floatRight">
                                    <div class="floatRight paddingTop18 marginBottom15">
                                        <div class="borderGrayButton marginBottom15">
                                            <p class="center paddingTop10 paddingBottom10">Cambiar</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="cotizador-box-container padding20">
                                <div class="clearFix">
                                    <h1 class="dark-blue-titles font20 fontWeight400 letterspacing1 marginBottom20 floatLeft"><span
                                            class="visibleMob">5.</span>Elige tus pagos y el plazo.</h1>

                                    <h1 class="headingColor font20 fontWeight400 letterspacing1 floatRight marginBottom20"></h1>
                                </div>

                                <div class="cotizador-p1-buttons clearFix">
                                    <div class="payChoice">
                                        <div class="col6 floatLeft marginBottom10">
                                            <div class="paddingAside5">
                                                <p id="quincenal" class="width350 cotizador-box blueButton">Quincenal</p>
                                            </div>
                                        </div>

                                        <div class="col6 floatLeft marginBottom10">
                                            <div class="paddingAside5">
                                                <p id="mensual" class="width350 cotizador-box ">Mensual</p>
                                            </div>
                                        </div>
                                    </div>

                                    <h1 class="dark-blue-titles font20 fontWeight400 letterspacing1 marginBottom20 clearFloat">A cuantos meses pagarás tu auto?</h1>

                                    <div class="col3 col6-tab floatLeft marginBottom10">
                                        <div class="paddingAside5">
                                            <div id="m12" class="cotizador-box small">
                                                <p class="font18 opacity08">12 meses</p>

                                                <p id="meses12" class="font14 opacity08"></p>
                                            </div>
                                        </div>

                                    </div>

                                    <div class="col3 col6-tab floatLeft marginBottom10">
                                        <div class="paddingAside5">
                                            <div id="m24" class="cotizador-box small">
                                                <p class="font18 opacity08">24 meses</p>

                                                <p id="meses24" class="font14 opacity08"></p>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col3 col6-tab floatLeft marginBottom10">
                                        <div class="paddingAside5">
                                            <div id="m36" class="cotizador-box small">
                                                <p class="font18 opacity08">36 meses</p>

                                                <p id="meses36" class="font14 opacity08"></p>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col3 col6-tab floatLeft marginBottom10">
                                        <div class="paddingAside5">
                                            <div id="m48" class="cotizador-box small">
                                                <p class="font18 opacity08">48 meses</p>

                                                <p id="meses48" class="font14 opacity08"></p>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="marker">
                                    <p>5</p>
                                </div>
                            </div>
                        </div>

                        <div class="elige-un-modelo borderGrayTop">
                            <h1 class="lightGray letterspacing1 font20 fontWeight400 marginLeft15 marginTop15 marginBottom10">Elige tu Seguro</h1>

                            <div class="col12 marginBottom15">
                                <div class="marginLeft3 center cotizador-box lightGray paddingTop15 paddingBottom15 font18 fontWeight300 letterspacing1">
                                    <div class="clearFix paddingBottom10">
                                        <p class="font18 fontWeight400 marginLeft25 opacity08 floatLeft">Básico</p>

                                        <p class="font18 fontWeight400 marginRight25 opacity08 floatRight">$620</p>
                                    </div>

                                    <div class="clearFix">
                                        <p class="font12 fontWeight300 marginLeft25 opacity08 floatLeft">NIVEL DE PROTECCIÓN: SUFICIENTE</p>

                                        <p class="font12 fontWeight300 marginRight25 opacity08 floatRight">MXN / MENSUAL</p>
                                    </div>
                                </div>
                            </div>

                            <div class="col12">
                                <div class="marginLeft3 center cotizador-box lightGray paddingTop15 paddingBottom15 font18 fontWeight300 letterspacing1">
                                    <div class="clearFix paddingBottom10">
                                        <p class="font18 fontWeight400 marginLeft25 opacity08 floatLeft">Esencial</p>

                                        <p class="font18 fontWeight400 marginRight25 opacity08 floatRight">$270</p>
                                    </div>

                                    <div class="clearFix">
                                        <p class="font12 fontWeight300 marginLeft25 opacity08 floatLeft">NIVEL DE PROTECCIÓN: BUENO</p>

                                        <p class="font12 fontWeight300 marginRight25 opacity08 floatRight">MXN / MENSUAL</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- END STEP 5 -->
                    <!-- BEGIN STEP 6 -->
                    <div class="step6 cotizadorStep">
                        <div class="cotizador-rightside">
                            <h1 class="darkBluetitle font30 fontWeight400 marginBottom10">Cotiza tu Auto</h1>

                            <div class="borderGrayBottom paddingBottom10 clearFix elige-un-modelo opacity05">
                                <div class="col6 floatLeft">
                                    <div class="floatLeft paddingTop18 marginLeft24">
                                        <p class="formTitleColor font12 paddingBottom12 fontWeight500">4. Tu enganche</p>

                                        <p id="engancheElegido3" class="formTitleColor font20 fontWeight500 marginBottom15"></p>
                                    </div>
                                </div>

                                <div class="col6 floatRight">
                                    <div class="floatRight paddingTop18 marginBottom15">
                                        <div class="borderGrayButton ">
                                            <p class="center paddingTop10 paddingBottom10">Cambiar</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="borderGrayBottom paddingBottom15 clearFix elige-un-modelo">
                                <div class="col6 floatLeft">
                                    <div class="floatLeft paddingTop18 marginLeft24">
                                        <p class="formTitleColor font12 paddingBottom12 fontWeight500">5. Tus Pagos</p>

                                        <p id="pagoElegido" class="formTitleColor font17 fontWeight500 marginBottom15">$7,000.00 | Mensual | 36 meses</p>
                                    </div>
                                </div>

                                <div class="col6 floatRight">
                                    <div class="floatRight paddingTop18 marginBottom15">
                                        <div class="borderGrayButton ">
                                            <p class="center paddingTop10 paddingBottom10">Cambiar</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="cotizador-box-container padding20 marginTop10">
                                <h1 class="darkBluetitle font20 fontWeight400 letterspacing1 marginBottom20"><span
                                        class="visibleMob">6.</span>Elíge tu seguro</h1>

                                <div class="cotizador-p1-buttons clearFix">

                                    <div id="s1"  class="cotizador-box large marginBottom10">
                                        <div class="paddingAside10 clearFix">
                                            <div class="floatLeft col6 col12-mob left">
                                                <p id="seguro1" class="fontWeight500 ">Amplio Plus</p>

                                                <p class="paddingTop10 paddingBottom20 font11 fontWeight500">NIVEL DE PROTECCIÓN: COMPLETO</p>
                                            </div>

                                            <div class="floatRight col6 col12-mob right ">
                                                <p class="fontWeight500 ">$1,400.00</p>

                                                <p class="paddingTop10 font12 fontWeight500">MXN / MENSUAL COMPLETO</p>
                                            </div>
                                        </div>
                                    </div>

                                    <div id="s2" class="cotizador-box large marginBottom10">
                                        <div class="paddingAside10 clearFix">
                                            <div class="floatLeft col6 col12-mob left">
                                                <p id="seguro2" class="fontWeight500 ">Básico</p>

                                                <p class="paddingTop10 paddingBottom20 font11 fontWeight500">NIVEL DE PROTECCIÓN: SUFICIENTE</p>
                                            </div>

                                            <div class="floatRight col6 col12-mob right ">
                                                <p class="fontWeight500 ">$620</p>

                                                <p class="paddingTop10 font12 fontWeight500">MXN / MENSUAL COMPLETO</p>
                                            </div>
                                        </div>
                                    </div>

                                    <div id="s3"  class="cotizador-box large marginBottom10">
                                        <div class="paddingAside10 clearFix">
                                            <div class="floatLeft col6 col12-mob left">
                                                <p id="seguro3" class="fontWeight500 ">Esencial</p>

                                                <p class="paddingTop10 paddingBottom20 font11 fontWeight500">NIVEL DE PROTECCIÓN: BUENO</p>
                                            </div>

                                            <div class="floatRight col6 col12-mob right ">
                                                <p class="fontWeight500 ">$270</p>

                                                <p class="paddingTop10 font12 fontWeight500">MXN / MENSUAL COMPLETO</p>
                                            </div>
                                        </div>
                                    </div>

                                </div>

                                <div class="marker higher">
                                    <p>6</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- END STEP 6 -->
                    <!-- BEGIN STEP 7 -->
                    <div class="step7 cotizadorStep">
                        <div class="cotizador-rightside">
                            <h1 class="dark-blue-titles font30 fontWeight400 marginBottom10">Cotiza tu Auto</h1>

                            <div class="borderGrayTop borderGrayBottom paddingBottom10 clearFix elige-un-modelo">
                                <div class="col8 col7-mob floatLeft paddingTop15 paddingBottom10">
                                    <div class="marginLeft24">
                                        <p class="formTitleColor font12 paddingBottom12 fontWeight500">1. Elige un auto</p>

                                        <p id="productoElegido3" class="formTitleColor font20 fontWeight400">NISSAN MAXIMA</p>
                                    </div>
                                </div>

                                <div class="col4 col5-mob floatRight">
                                    <div class="">
                                        <div class="marginTop15 borderGrayButton noWidth marginBottom15">
                                            <p class="center paddingTop10 paddingBottom10">Cambiar</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="borderGrayBottom paddingBottom10 clearFix elige-un-modelo">
                                <div class="col8 col7-mob floatLeft">
                                    <div class="marginLeft24 paddingTop10 paddingBottom10">
                                        <p class="formTitleColor font12 paddingBottom12 fontWeight500">2. Elige un Modelo</p>

                                        <p id="modeloElegido3" class="formTitleColor font20 fontWeight400">2016 Regular SP</p>
                                    </div>
                                </div>

                                <div class="col4 col5-mob floatRight">
                                    <div class="paddingTop5 marginBottom15">
                                        <div class="borderGrayButton noWidth marginBottom15">
                                            <p class="center paddingTop10 paddingBottom10">Cambiar</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="borderGrayBottom paddingBottom10 clearFix elige-un-modelo">
                                <div class="col8 col7-mob floatLeft">
                                    <div class="paddingTop10 paddingBottom10 marginLeft24">
                                        <p class="formTitleColor font12 paddingBottom12 fontWeight500">3. Elige un Color</p>

                                        <p id="colorElegido3" class="formTitleColor font20 fontWeight400"></p>
                                    </div>
                                </div>

                                <div class="col4 col5-mob floatRight">
                                    <div class="paddingTop5 marginBottom15">
                                        <div class="borderGrayButton noWidth marginBottom15">
                                            <p class="center paddingTop10 paddingBottom10">Cambiar</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="borderGrayBottom paddingBottom10 clearFix elige-un-modelo">
                                <div class="col8 col7-mob floatLeft">
                                    <div class="paddingTop10 paddingBottom10 marginLeft24">
                                        <p class="formTitleColor font12 paddingBottom12 fontWeight500">4. Tu anganche</p>

                                        <p id="engancheElegido4" class="formTitleColor font20 fontWeight400"></p>
                                    </div>
                                </div>

                                <div class="col4 col5-mob floatRight">
                                    <div class="paddingTop5 marginBottom15">
                                        <div class="borderGrayButton noWidth marginBottom15">
                                            <p class="center paddingTop10 paddingBottom10">Cambiar</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="borderGrayBottom paddingBottom10 clearFix elige-un-modelo">
                                <div class="col8 col7-mob floatLeft">
                                    <div class="paddingTop10 paddingBottom10 marginLeft24">
                                        <p class="formTitleColor font12 paddingBottom12 fontWeight500">5. Pagos y Plazo</p>

                                        <p id="pagoElegido2" class="formTitleColor font20 fontWeight400">$7000 | Mensual | 36 Meses</p>
                                    </div>
                                </div>

                                <div class="col4 col5-mob floatRight">
                                    <div class="paddingTop5 marginBottom15">
                                        <div class="borderGrayButton noWidth marginBottom15">
                                            <p class="center paddingTop10 paddingBottom10">Cambiar</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="borderGrayBottom paddingBottom10 clearFix elige-un-modelo">
                                <div class="col8 col7-mob floatLeft">
                                    <div class="paddingTop10 paddingBottom10 marginLeft24">
                                        <p class="formTitleColor font12 paddingBottom12 fontWeight500">6. Seguro</p>

                                        <p id="seguroElegido" class="formTitleColor font20 fontWeight400"></p>
                                    </div>
                                </div>

                                <div class="col4 col5-mob floatRight">
                                    <div class="paddingTop5 marginBottom15">
                                        <div class="borderGrayButton noWidth marginBottom15">
                                            <p class="center paddingTop10 paddingBottom10">Cambiar</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="marginTop40 marginBottom20">
                                <div class="paddingAside20">
                                    <input type="submit" value="SOLICITAR MI CREDITO"
                                       class="block font25 pointer letterspacing1 blueButton blue-shadow2 paddingTop20 width400 center autoMargin paddingBottom20">
                                    </input>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- END STEP 7 -->
                    <g:hiddenField name="txtProducto" value=""/>
                    <g:hiddenField name="txtModelo" value=""/>
                    <g:hiddenField name="txtColor" value=""/>
                    <g:hiddenField name="txtEnganche" value=""/>
                    <g:hiddenField name="txtPlazo" value=""/>
                    <g:hiddenField name="txtPago" value=""/>
                    <g:hiddenField name="txtSeguro" value=""/>
                </g:form>
            </div>
        </div>
    </body>
</html>
