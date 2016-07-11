<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="cotizador"/>
        <script src="js/cotizador.js"></script>
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
                    <g:render template="productoTemplate" model="[productos: productos]" />
                    <!-- MODELO -->
                    <g:render template="modeloTemplate" />
                    <!-- COLOR -->
                    <g:render template="colorTemplate" />
                    <!-- FINANCIAMIENTO -->
                    <g:render template="financiamientoTemplate" />
                    <!-- PLAZOS -->
                    <g:render template="plazosTemplate" />
                    <!-- SEGURO -->
                    <g:render template="seguroTemplate" />

                    <div class="solicitar-credito marginTop40 marginBottom20">
                        <div class="width388 center autoMargin">
                            <span class="font25 fontWeight400 letterspacing1 blueButton blue-shadow2 paddingTop20 paddingRight35 paddingBottom20 paddingLeft35">SOLICITAR MI CRÉDITO</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>

</html>
