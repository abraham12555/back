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
                        <h1 id="nombreDelProducto" class="headingColor font35 fontWeight900 letterspacing1 lato">
                            ${configuracion.textoProductoDefault}
                        </h1>
                        <h3 id="precioDelProducto" class="darkBluetitle font30 fontWeight400 marginBottom20">
                            ${configuracion.textoMontoDefault}
                        </h3>
                        <div id="imagenDelProducto" class="cotizador-bg none800" style="background-image: url('${configuracion.imagenDefault}');">
                            <div class="cotizador-bae"></div>
                        </div>
                        <div class="paddingAside10">
                            <p id="descripcionDelProducto" class="darkBluetitle font14 fontWeight400 letterspacing1 justify">
                                ${configuracion.textoDescripcionDefault}
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col6 col12-mob floatLeft down800">
                    <div class="cotWrap">
                        <h1 class="darkBluetitle font30 fontWeight400 marginBottom95">${configuracion.tituloCotizador}</h1>
                        <g:form controller="cotizador" action="procesar" class="cotizadorForm">
                            <div id="contenedorDePasos">
                                <g:render template="stepTemplate"/>
                            </div>
                            <g:hiddenField id="txtRubro" name="txtRubro" value=""/>
                            <g:hiddenField id="txtProducto" name="txtProducto" value=""/>
                            <g:hiddenField id="txtModelo" name="txtModelo" value=""/>
                            <g:hiddenField id="txtColor" name="txtColor" value=""/>
                            <g:hiddenField id="txtEnganche" name="txtEnganche" value=""/>
                            <g:hiddenField id="txtMontoCredito" name="txtMontoCredito" value=""/>
                            <g:hiddenField id="txtMontoSeguro" name="txtMontoSeguro" value=""/>
                            <g:hiddenField id="txtDocumento" name="txtDocumento" value=""/>
                            <g:hiddenField id="txtTieneAtrasos" name="txtTieneAtrasos" value=""/>
                            <g:hiddenField id="txtPlazo" name="txtPlazo" value=""/>
                            <g:hiddenField id="txtPago" name="txtPago" value=""/>
                            <g:hiddenField id="txtSeguro" name="txtSeguro" value=""/>
                            <g:hiddenField id="txtPeriodo" name="txtPeriodo" value=""/>
                            <div class="marginTop40 marginBottom20" id="submitCotizador">
                                <div class="paddingAside20">
                                    <input type="submit" value="Comenzar mi Solicitud" class="block font25 pointer letterspacing1 blueButton blue-shadow2 padding20 width400 center autoMargin" />
                                </div>
                            </div>
                            <div class="paddingAside10" style="margin-top: 10%;">
                                <p id="terminosYCondiciones" class="darkBluetitle font14 fontWeight400 letterspacing1 justify">
                                    Al dar clic en <strong>Comenzar mi Solicitud</strong> aceptas los <a class="pointer" onclick="openModal('modalTerminosCondiciones');" style="text-decoration: underline;">Términos y Condiciones
                                    y Aviso de Privacidad</a>
                                </p>
                            </div>
                        </g:form>
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