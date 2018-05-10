<!DOCTYPE html>
<html>
    <head>
        <title>Kosmos - Cotizador</title>
    </head>

    <body>
        <div class="container clearFix">
            <g:if test="${entidadFinanciera}">
                <input type="hidden" id="colorTitulos" value="${configuracion.colorTitulos}">
                <input type="hidden" id="colorGradienteInferior" value="${configuracion.colorGradienteInferior}">
                <input type="hidden" id="colorGradienteSuperior" value="${configuracion.colorGradienteSuperior}">
                <input type="hidden" id="aplicacionVariable" value="${configuracion.aplicacionVariable}">
                <input type="hidden" id="entidadFinancieraId" value="${entidadFinanciera.id}">
                <div class="col6 col12-mob coti floatLeft down800">
                    <div class="padding15">
                        <h1 id="nombreDelProducto" class="headingColor font30 fontWeight900 letterspacing1 lato">
                            ${configuracion.textoProductoDefault}
                        </h1>
                        <h3 id="precioDelProducto" class="darkBluetitle font30 fontWeight400 marginBottom20">
                            ${configuracion.textoMontoDefault}
                        </h3>
                        <div id="imagenDelProducto" class="cotizador-bg" style="background-image: url('data:image/${landingImage.extension};base64,${landingImage.base64}');">
                            <div class="cotizador-bae"></div>
                        </div>
                        <div class="paddingAside10" style='margin-top: 30px;'>
                            <p id="descripcionDelProducto" class="darkBluetitle font14 fontWeight400 letterspacing1 justify">
                                ${configuracion.textoDescripcionDefault}
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col6 col12-mob floatLeft down800">
                    <div class="cotWrap">
                        <h1 class="darkBluetitle font30 fontWeight400 marginBottom95">${configuracion.tituloCotizador}</h1>
                            <form method="POST" name="formCotizador" id="formCotizador">
                            <div id="contenedorDePasos">
                                <g:render template="stepTemplate"/>
                            </div>
                            <input type="hidden" id="txtRubro" name="txtRubro" value="">
                            <input type="hidden" id="txtProducto" name="txtProducto" value=""/>
                            <input type="hidden" id="txtModelo" name="txtModelo" value=""/>
                            <input type="hidden" id="txtColor" name="txtColor" value=""/>
                            <input type="hidden" id="txtEnganche" name="txtEnganche" value=""/>
                            <input type="hidden" id="txtMontoCredito" name="txtMontoCredito" value=""/>
                            <input type="hidden" id="txtMontoSeguro" name="txtMontoSeguro" value=""/>
                            <input type="hidden" id="txtMontoAsistencia" name="txtMontoAsistencia" value=""/>
                            <input type="hidden" id="txtDocumento" name="txtDocumento" value=""/>
                            <input type="hidden" id="txtTieneAtrasos" name="txtTieneAtrasos" value=""/>
                            <input type="hidden" id="txtPlazo" name="txtPlazo" value=""/>
                            <input type="hidden" id="txtPago" name="txtPago" value=""/>
                            <input type="hidden" id="txtSeguro" name="txtSeguro" value=""/>
                            <input type="hidden" id="txtPeriodo" name="txtPeriodo" value=""/>
                            <input type="hidden" id="cat" name="cat" value=""/>
                            <input type="hidden" id="comparaBien" name="comparaBien" value="${comparaBien?.comparaBien}">
                            <input type="hidden" id="montoComparaBien" name="montoComparaBien" value="${comparaBien?.montoComparaBien}">
                            <input type="hidden" id="plazoComparaBien" name="plazoComparaBien" value="${comparaBien?.plazoComparaBien}">
                            <input type="hidden" id="correoComparaBien" name="correoComparaBien" value="${comparaBien?.correoComparaBien}">

                            <div class="marginTop40 marginBottom20 blur" id="submitCotizador">
                                <div class="paddingAside20">
                                    <input type="button" value="Comenzar mi Solicitud" onclick="submitCotizador();" class="block font25 pointer letterspacing1 blueButton blue-shadow2 padding20 width400 center autoMargin" id="btnCotSub" disabled/>
                                </div>
                            </div>
                            <div class="paddingAside10" style="margin-top: 10%;">
                                <p class="darkBluetitle font14 fontWeight400 letterspacing1 justify">
                                <strong>*</strong> Se agrega un seguro de deuda en todos los financiamientos.
                                </p>
                                <br/>
                                <p id="terminosYCondiciones" class="darkBluetitle font14 fontWeight400 letterspacing1 justify">
                                    Al dar clic en <strong>Comenzar mi Solicitud</strong> aceptas los <a class="pointer" onclick="openModal('modalTerminosCondiciones');" style="text-decoration: underline;">Términos y Condiciones
                                    y Aviso de Privacidad</a>
                                </p>
                            </div>
                        </form>
                    </div>
                </div>
            </g:if>
            <g:else>
                El contenido no está disponible. Póngase en contacto con su tienda para reportar el incidente.
            </g:else>
        </div>
        <g:render template="terminosYCondiciones"/>
    </body>
</html>