<div class="creditBtns" style="text-align: center; width: 100%; margin-left: 25%;">
    <div class="col5half col12-mob floatLeft">
        <center>
            <g:urlContextAware value="/dashboard/perfilarCliente" var="urlPerfilarCliente"/>
            <a href="${urlPerfilarCliente}">
                <div  class="blueButton buttonM radius100 font16 colorWhite letterspacing1.5 mobileAuto consultarBc" style="cursor: pointer;">PERFILAR OTRO CLIENTE</div>
            </a>
        </center>
    </div>
</div>

<div class="felicidades">
    <h1>¡EXCELENTE!</h1>
    <p class="felicidadesP1">LA SOLICITUD HA SIDO REGISTRADA CORRECTAMENTE</p>
    <br/>
</div>
        <div class="container">
            <div class="colorGreen buttonM radius100 colorWhite marginTop25 marginBottom25 mobileAuto"><strong>
                    <g:jasperReport
                        controller="dashboard"
                        jasper="reporte10"
                        action="printReport"
                        format="PDF"
                        name="Mi_Solicitud ">
                        <input type="hidden" name="idProductoSolicitudPrintPerfilador" value='${ofertaSeleccionada?.productoSolicitud?.id}'/> 
                    </g:jasperReport>
                </strong>
            </div>
        </div>
<div class="container containerBox">
    <div class="paddingTop40 paddingRight40 paddingBottom40 paddingLeft40 paddingTop25">
        <div class="titles8">
            <h3 class="colorblue">DATOS DEL CRÉDITO:</h3>
        </div>
        <div class="boxes8 clearFix">
            <div class="col6 col6-tab col12-mob floatLeft">
                <div class="borderGrayRight marginTop15 marginBottom15 formTitleColor">
                    <p>PRODUCTO: <strong>${ofertaSeleccionada?.productoSolicitud?.producto?.nombreDelProducto?.toUpperCase()}</strong></p>
                    <p>TASA APLICABLE: <strong>${ ofertaSeleccionada?.oferta?.tasaDeInteres ? (ofertaSeleccionada?.oferta?.tasaDeInteres * 100).round(2) : 0 } % ANUAL</strong></p>
                    <p>MONTO: <strong><g:formatNumber number="${ofertaSeleccionada?.productoSolicitud?.montoDelCredito}" format="\044###,###,###.##" /></strong></p>
                    <p>PLAZO: <strong>${ofertaSeleccionada?.productoSolicitud?.plazos} ${(ofertaSeleccionada?.productoSolicitud.periodicidad.nomenclatura).toUpperCase()}</strong></p>
                    <p>SEGURO CON LIBERTAD: <strong><g:formatNumber number="${ofertaSeleccionada?.productoSolicitud?.montoDelSeguroDeDeuda}" format="\044###,###,###.##" /></strong></p>
                    <p>LIBERASISTENCIA: <strong><g:formatNumber number="${ofertaSeleccionada?.oferta?.montoAsistencia}" format="\044###,###,###.##" /></strong></p>
                    <p>CAT PROMEDIO: <strong>${ (ofertaSeleccionada?.productoSolicitud?.producto?.cat) ? ((ofertaSeleccionada?.productoSolicitud?.producto?.cat * 100).round(2)) : 0 } %</strong> Sin IVA para fines informativos y de comparación, calculado al 02 de enero del 2017. LIBERTAD SERVICIOS FINANCIEROS, S.A. DE C.V., S.F.P.</p>
                </div>
            </div>
            <div class="col6 col6-tab col12-mob floatLeft">
                <div class="paddingAside10">
                    <div class="colorGreen buttonM radius100 colorWhite marginTop25 marginBottom25 mobileAuto">EL FOLIO ES: <strong>${("" + ofertaSeleccionada?.productoSolicitud?.solicitud?.folio).padLeft(6, '0')}</strong></div>
                </div>  
            </div>
        </div>
        <div class="titles8">
            <h3 class="colorblue">SIGUIENTE PASO:</h3>
            <p class="formTitleColor">RECUERDA QUE EL CLIENTE DEBE CUMPLIR CON LOS SIGUIENTES REQUISITOS</p>
        </div>
        <div class="boxes8 col12 clearFix">
            <div class="col6 col6-tab col12-mob floatLeft marginTop20 marginBottom20 formTitleColor">
                <p>IDENTIFICACIÓN OFICIAL</p>
                <p>COMPROBANTE DE DOMICILIO</p>
                <p>${ofertaSeleccionada?.productoSolicitud?.documentoElegido?.nombre?.toUpperCase()} (X ${ofertaSeleccionada?.productoSolicitud?.documentoElegido?.cantidadSolicitada}) DEL ÚLTIMO MES</p>
            </div>
        </div>
        <br />
        <div class='formTitleColor font14'>
            <p style='text-align: justify;'>La autorización esta condicionada al análisis de la capacidad de pago y otros factores, por lo que la "pre-autorización" no es vinculante para ninguna de las partes con fines específicos de promoción y evaluación del crédito.<br/><br/>El otorgamiento del crédito y la solicitud de avales y garantías, estará sujeta a las políticas vigentes y a los procesos de 
                investigación de Libertad Servicios Financieros. Si ya eres nuestro cliente, se validará tu experiencia crediticia previa para la Autorización Del Mismo. <br/><br/>
                Los datos proporcionados por nuestros clientes y usuarios son estrictamente confidenciales. Para mayores informes ponemos el aviso de privacidad a su disposición 
                en www.libertad.com.mx . Recuerde que Libertad Servicios Financieros nunca le solicitará que proporcione ningún tipo de información confidencial mediante un correo 
                electrónico o mediante una liga que lo lleve a nuestra página de Internet.
            </p>
        </div>
    </div>
</div>
