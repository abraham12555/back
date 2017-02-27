<input type="hidden" id="tipoDePaso" value="${pasoActual?.tipoDePaso?.nombre}">
<input type="hidden" id="tituloDelPaso" value="${pasoActual?.titulo}"/>
<g:if test="${resultadoMotorDeDecision && resultadoMotorDeDecision?.dictamenFinal == "A"}">
    <section class="paddingTop30 contentHeight">
        <div class="felicidades">
            <h1>¡FELICIDADES!</h1>
            <p class="felicidadesP1">TU CRÉDITO HA SIDO PRE-AUTORIZADO</p>
            <br/>
        </div>

        <div class="container containerBox">
            <div class="paddingTop40 paddingRight40 paddingBottom40 paddingLeft40 paddingTop25">
                <div class="titles8">
                    <h3 class="colorblue">DATOS DE TU CRÉDITO:</h3>
                </div>
                <div class="boxes8 clearFix">
                    <div class="col6 col6-tab col12-mob floatLeft">
                        <div class="borderGrayRight marginTop15 marginBottom15 formTitleColor">
                            <p>MONTO: <strong><g:formatNumber number="${productoSolicitud?.montoDelCredito}" format="\044###,###,###.##" /></strong></p>
                            <p>PLAZO: <strong>${productoSolicitud?.plazos} ${(productoSolicitud.periodicidad.nomenclatura).toUpperCase()}</strong></p>
                            <p>SEGURO CON LIBERTAD: <strong><g:formatNumber number="${productoSolicitud?.montoDelSeguroDeDeuda}" format="\044###,###,###.##" /></strong></p>
                            <p>CAT: <strong>${ (productoSolicitud?.producto?.cat) ? (productoSolicitud?.producto?.cat * 100) : 0 } %</strong> sin IVA para fines informativos y de comparación</p>
                        </div>
                    </div>
                    <div class="col6 col6-tab col12-mob floatLeft">
                        <div class="paddingAside10">
                            <div class="colorGreen buttonM radius100 colorWhite marginTop25 marginBottom25 mobileAuto">Tu folio es: <strong>${("" + productoSolicitud?.solicitud?.folio).padLeft(6, '0')}</strong></div>
                        </div>  
                    </div>
                </div>
                <div class="titles8">
                    <h3 class="colorblue">SIGUIENTE PASO:</h3>
                    <p class="formTitleColor">PRESENTATE EN LA <span class="colorblue">${productoSolicitud?.solicitud?.sucursal?.toString()?.toUpperCase()}</span>, UBICADA EN <span class="colorblue">${productoSolicitud?.solicitud?.sucursal?.ubicacion?.toUpperCase()}</span> CON ORIGINAL Y COPIA DE TU DOCUMENTACION:</p>
                </div>
                <div class="boxes8 col12 clearFix">
                    <div class="col6 col6-tab col12-mob floatLeft marginTop20 marginBottom20 formTitleColor">
                        <p>IDENTIFICACIÓN OFICIAL</p>
                        <p>COMPROBANTE DE DOMICILIO</p>
                        <p>${productoSolicitud?.documentoElegido?.nombre?.toUpperCase()} (X ${productoSolicitud?.documentoElegido?.cantidadSolicitada}) DEL ÚLTIMO MES</p>
                    </div>
                    <div class="col6 col6-tab col12-mob floatLeft">
                        <div class="paddingAside10">
                            <div class="colorGreen buttonM abrirMapa radius100 colorWhite marginTop25 marginBottom25 mobileAuto pointer">UBICA TU SUCURSAL EN EL MAPA</div>
                        </div>
                    </div>
                </div>
                <div class="center colorblue font16 marginTop50">
                    <p><strong>${configuracion?.nombreComercial?.toUpperCase()} AGRADECE TU PREFERENCIA</strong></p>
                </div>
                <br />
                <div class='formTitleColor font14'>
                    <p style='text-align: justify;'>El otorgamiento del crédito y la solicitud de avales y garantías, estará sujeta a las políticas vigentes y a los procesos de 
                        investigación de Libertad Servicios Financieros. Si ya eres nuestro cliente, se validará tu experiencia crediticia previa para la Autorización Del Mismo. <br/><br/>
                        Los datos proporcionados por nuestros clientes y usuarios son estrictamente confidenciales. Para mayores informes ponemos el aviso de privacidad a su disposición 
                        en www.libertad.com.mx . Recuerde que Libertad Servicios Financieros nunca le solicitará que proporcione ningún tipo de información confidencial mediante un correo 
                        electrónico o mediante una liga que lo lleve a nuestra página de Internet.
                    </p>
                </div>
            </div>
        </div>
    </section>
</g:if>
<g:elseif test="${!resultadoMotorDeDecision || resultadoMotorDeDecision?.dictamenFinal == "D"}">
    <section class="contentHeight">
        <div class="felicidades animated bounceInUp slow">
            <h1><strong>${configuracion?.nombreComercial?.toUpperCase()} AGRADECE TU PREFERENCIA</strong></h1>
            <p class="colorblue">POR EL MOMENTO NO ES POSIBLE OTORGAR UNA PRE-CALIFICACIÓN ACERTADA POR ESTE MEDIO. TE INVITAMOS A ACUDIR A TU  
                <span class="formTitleColor">${productoSolicitud?.solicitud?.sucursal?.toString()?.toUpperCase()}</span> 
                PARA CONTINUAR CON EL TRÁMITE Y BRINDARTE MAYOR INFORMACIÓN DE NUESTROS PRODUCTOS DE CRÉDITO, AHORRO E INVERSIÓN.</p>
        </div>
        <div class="container containerBox marginTop25">
            <div class="paddingTop40 paddingRight40 paddingBottom40 paddingLeft40 paddingTop25">
                <div class="titles8">
                    <h3 class="colorblue">DATOS DE TU CRÉDITO:</h3>
                </div>
                <div class="boxes8 col12 clearFix">
                    <div class="col6 col6-tab col12-mob floatLeft">
                        <div class="borderGrayRight marginTop15 marginBottom15 formTitleColor">
                            <p>MONTO: <strong><g:formatNumber number="${productoSolicitud?.montoDelCredito}" format="\044###,###,###.##" /></strong></p>
                            <p>PLAZO: <strong>${productoSolicitud?.plazos} ${(productoSolicitud.periodicidad.nomenclatura).toUpperCase()}</strong></p>
                            <p>SEGURO CON LIBERTAD: <strong><g:formatNumber number="${productoSolicitud?.montoDelSeguroDeDeuda}" format="\044###,###,###.##" /></strong></p>
                            <p>CAT: <strong>${ (productoSolicitud?.producto?.cat) ? (productoSolicitud?.producto?.cat * 100) : 0 } %</strong> sin IVA para fines informativos y de comparación</p>
                        </div>
                    </div>
                    <div class="col6 col6-tab col12-mob floatLeft">
                        <div class="paddingAside10">
                            <div class="colorGreen buttonM radius100 colorWhite marginTop25 marginBottom25 mobileAuto">Tu folio es: <strong>${("" + productoSolicitud?.solicitud?.folio).padLeft(6, '0')}</strong></div>
                        </div>  
                    </div>
                </div>
                <div class="boxes8 col12 clearFix">
                    <div class="col6 col6-tab col12-mob floatLeft marginBottom20">
                        <p class="formTitleColor marginTop20 marginBottom10 bold">¿AUN TIENES DUDAS? CONTÁCTANOS</p>
                        <p class="formTitleColor">CONTACTO@LIBERTAD.COM.MX</p>
                        <p class="formTitleColor">TEL. 01800 714 02 74</p>
                        <p class="formTitleColor">NUESTRO HORARIO DE ATENCIÓN ES DE LUNES A VIERNES DE 9:00 A 19:00 HORAS Y LOS SÁBADOS DE 9:00 A 14:00 HORAS TIEMPO DE CENTRO.</p>
                    </div>
                                        <div class="col6 col6-tab col12-mob floatLeft">
                        <div class="paddingAside10">
                            <div class="colorGreen buttonM abrirMapa radius100 colorWhite marginTop25 marginBottom25 mobileAuto pointer">UBICA TU SUCURSAL EN EL MAPA</div>
                        </div>
                    </div>
                </div>
                <br />
                <div class='formTitleColor font14'>
                    <p style='text-align: justify;'>El otorgamiento del crédito y la solicitud de avales y garantías, estará sujeta a las políticas vigentes y a los procesos de 
                        investigación de Libertad Servicios Financieros. Si ya eres nuestro cliente, se validará tu experiencia crediticia previa para la Autorización Del Mismo. <br/><br/>
                        Los datos proporcionados por nuestros clientes y usuarios son estrictamente confidenciales. Para mayores informes ponemos el aviso de privacidad a su disposición 
                        en www.libertad.com.mx . Recuerde que Libertad Servicios Financieros nunca le solicitará que proporcione ningún tipo de información confidencial mediante un correo 
                        electrónico o mediante una liga que lo lleve a nuestra página de Internet.
                    </p>
                </div>
            </div>
        </div>
    </div>
</section>
</g:elseif>
<g:else>
    <section class="contentHeight">
        <div class="felicidades animated bounceInUp slow">
            <h1>NECESITAMOS CONOCERTE MEJOR </h1>
            <p class="colorblue">LO SENTIMOS, POR EL MOMENTO NO PODEMOS OFRECERTE EL CRÉDITO SOLICITADO.  TE INVITAMOS A QUE APERTURES UNA CUENTA DE AHORROS PARA PODER OFRECERTE MÁS SERVICIOS FINANCIEROS A TU MEDIDA EN UN FUTURO PRÓXIMO.</p>
        </div>
        <div class="container containerBox marginTop25">
            <div class="paddingTop40 paddingRight40 paddingBottom40 paddingLeft40 paddingTop25">
                <div class="titles8">
                    <h3 class="colorblue">PRIMER PASO:</h3>
                    <p class="formTitleColor">PRESENTATE EN LA SUCURSAL <span class="colorblue">>${productoSolicitud?.solicitud?.sucursal?.toString()?.toUpperCase()}</span> CON TU DOCUMENTACION:</p>
                </div>
                <div class="boxes8 col12 clearFix">
                    <div class="col6 col6-tab col12-mob floatLeft marginTop20 marginBottom20 formTitleColor">
                        <p>IDENTIFICACIÓN OFICIAL</p>
                        <p>COMPROBANTE DE DOMICILIO</p>
                    </div>
                    <div class="col6 col6-tab col12-mob floatLeft">
                        <div class="paddingAside10">
                            <div class="colorGreen buttonM abrirMapa radius100 colorWhite marginTop25 marginBottom25 mobileAuto pointer">UBICA TU SUCURSAL EN EL MAPA</div>
                        </div>
                    </div>
                </div>
                <div class="titles8">
                    <h3 class="colorblue">SEGUNDO PASO:</h3>
                    <p class="formTitleColor">TU EJECUTIVO LIBERTAD TE ESTARÁ ESPERANDO PARA COTEJAR TU INFORMACIÓN Y PODER ABRIR TU CUENTA.</p>
                </div>
                <div class="boxes8 col12 clearFix">
                    <div class="col6 col6-tab col12-mob floatLeft marginBottom20">
                        <p class="formTitleColor marginTop20 marginBottom10 bold">¿AUN TIENES DUDAS? CONTÁCTANOS</p>
                        <p class="formTitleColor">CONTACTO@LIBERTAD.COM.MX</p>
                        <p class="formTitleColor">TEL. 01800 714 02 74</p>
                        <p class="formTitleColor">CON HORARIOS DE ATENCIÓN  DE LUNES A DOMINGO DE 7:00 A 23:00 HRS.</p>
                    </div>
                    <div class="col6 col6-tab col12-mob floatLeft">
                        <div class="paddingAside10">
                            <div class="colorGreen buttonM radius100 colorWhite marginTop25 marginBottom25 mobileAuto">Muestra tu folio <strong>${("" + productoSolicitud?.solicitud?.folio).padLeft(6, '0')}</strong></div>
                        </div>  
                    </div>
                </div>
                <div class="center colorblue font16 marginTop50">
                    <p><strong>${configuracion?.nombreComercial?.toUpperCase()} AGRADECE TU PREFERENCIA</strong></p>
                </div>
                <br />
                <div class='formTitleColor font14'>
                    <p style='text-align: justify;'>Los datos proporcionados por nuestros clientes y usuarios son estrictamente confidenciales. Para mayores informes ponemos el aviso de privacidad a su disposición 
                        en www.libertad.com.mx . Recuerde que Libertad Servicios Financieros nunca le solicitará que proporcione ningún tipo de información confidencial mediante un correo 
                        electrónico o mediante una liga que lo lleve a nuestra página de Internet.
                    </p>
                </div>
            </div>
        </div>
    </div>
</section>
</g:else>
<g:render template="modalMapa"/>
<script>
    $(document).ready(function() {
       registrarUbicacion('${raw(sucursal as String)}');
    });
</script>