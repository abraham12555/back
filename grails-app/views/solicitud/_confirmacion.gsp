<input type="hidden" id="tipoDePaso" value="${pasoActual?.tipoDePaso?.nombre}">
<input type="hidden" id="tituloDelPaso" value="${pasoActual?.titulo}"/>
<section class="paddingTop30 contentHeight">
    <div class="felicidades">
        <h1>¡FELICIDADES!</h1>
        <p class="felicidadesP1">TU CRÉDITO HA SIDO APROBADO EXITOSAMENTE</p>
        <g:if test="${productoSolicitud?.rubroDeAplicacion}">
            <p class="felicidadesP2 marginBottom31">SIGUE LOS ÚLTIMOS PASOS Y PREPARATE PARA DISFRUTAR DE TU CRÉDITO <span>${productoSolicitud?.producto?.nombreDelProducto?.toUpperCase()}</span></p>
        </g:if>
        <g:else>
            <p class="felicidadesP2 marginBottom31">SIGUE LOS ÚLTIMOS PASOS Y PREPARATE PARA ESTRENAR TU NUEVO <span>${productoSolicitud?.producto?.nombreDelProducto?.toUpperCase()}</span></p>
        </g:else>
    </div>

    <div class="container containerBox">
        <div class="paddingTop40 paddingRight40 paddingBottom40 paddingLeft40 paddingTop25">
            <div class="titles8">
                <h3 class="colorblue">PRIMER PASO:</h3>
                <p class="formTitleColor">REALIZA TU PAGO DE ENGANCHE POR <span class="colorblue"><g:formatNumber number="${productoSolicitud?.enganche}" format="\044###,###,###.##" /> MXN</span> EN CUALQUIERA DE LAS SIGUIENTES SUCURSALES</p>
            </div>
            <div class="boxes8 clearFix">
                <div class="col3 col6-tab col12-mob floatLeft">
                    <div class="borderGrayRight marginTop15 marginBottom15 formTitleColor">
                        <p>BANCO</p>
                        <p>CUENTA</p>
                        <p>CLABE</p>
                        <p>NO. DE REFERENCIA</p>
                    </div>
                </div>
                <div class="col3 col6-tab col12-mob floatLeft">
                    <div class="borderGrayRight marginTop15 marginBottom15 formTitleColor">
                        <p>BANCO</p>
                        <p>CUENTA</p>
                        <p>CLABE</p>
                        <p>NO. DE REFERENCIA</p>
                    </div>
                </div>
                <div class="col3 col6-tab col12-mob floatLeft">
                    <div class="borderGrayRight marginTop15 marginBottom15 formTitleColor">
                        <p>BANCO</p>
                        <p>CUENTA</p>
                        <p>CLABE</p>
                        <p>NO. DE REFERENCIA</p>
                    </div>
                </div>
                <div class="col3 col6-tab col12-mob floatLeft">
                    <div class=" marginTop15 marginBottom15 formTitleColor">
                        <p>BANCO</p>
                        <p>CUENTA</p>
                        <p>CLABE</p>
                        <p>NO. DE REFERENCIA</p>
                    </div>
                </div>
            </div>
            <div class="titles8">
                <h3 class="colorblue">SEGUNDO PASO:</h3>
                <p class="formTitleColor">PRESENTATE EN LA <span class="colorblue">${productoSolicitud?.solicitud?.sucursal?.toString()?.toUpperCase()}</span>, UBICADA EN <span class="colorblue">${productoSolicitud?.solicitud?.sucursal?.ubicacion?.toUpperCase()}</span> CON TU DOCUMENTACION:</p>
            </div>
            <div class="boxes8 col12 clearFix">
                <div class="col6 col6-tab col12-mob floatLeft marginTop20 marginBottom20 formTitleColor">
                    <p>IDENTIFICACIÓN OFICIAL</p>
                    <p>COMPROBANTE DE DOMICILIO</p>
                    <p>${productoSolicitud?.documentoElegido?.nombre?.toUpperCase()} (X ${productoSolicitud?.documentoElegido?.cantidadSolicitada})</p>
                </div>
                <div class="col6 col6-tab col12-mob floatLeft">
                    <div class="paddingAside10">
                        <div class="colorGreen buttonM abrirMapa radius100 colorWhite marginTop25 marginBottom25 mobileAuto pointer">UBICA TU SUCURSAL EN EL MAPA</div>
                    </div>
                </div>
            </div>
            <!--<div class="titles8">
                <h3 class="colorblue">TERCER PASO:</h3>
                <p class="formTitleColor">TU EJECUTIVO ROBERTO HERNANDEZ TE ESTARÁ ESPERANDO PARA COTEJAR TU INFORMACIÓN Y TU FIRMA DE CONTRATO.</p>
            </div>
            <div class="boxes8 col12 clearFix">
                <div class="col6 col6-tab col12-mob floatLeft marginBottom20">
                    <p class="formTitleColor marginTop20 marginBottom10 bold">¿AUN TIENES DUDAS? CONTACTA A ROBERTO</p>
                    <p class="formTitleColor">RHERNANDEZ@NISSAN.COM.MX</p>
                    <p class="formTitleColor">TEL. 55 2323 7548 EXT. 234</p>
                </div>
                <div class="col6 col6-tab col12-mob floatLeft">
                    <div class="paddingAside10">
                        <div class="colorGreen buttonM radius100 colorWhite marginTop25 marginBottom25 mobileAuto">CHATEA CON TU EJECUTIVO</div>
                    </div>  
                </div>
            </div>-->
            <div class="center colorRed font14 marginTop50">
                <p>ESTA SOLICITUD SOLO ES VALIDA HASTA EL DIA 12 DE FEBRERO DEL 2017</p>
            </div>
        </div>
    </div>
</section>
<g:render template="modalMapa"/>
<script>
    (function() {
            registrarUbicacion('${raw(sucursal as String)}');
    })();
</script>