<div class="idLightbox hide" id="modalDetalleTipoDeDocumento">  
    <div class="overlay"></div>
    <div class="whiteContainer detalleProducto lightboxPos darkBluetitle font14 fontWeight400 letterspacing1 justify">
       <div class="formContainer">
            <button type="submit" class="loginButton blueButton letterspacing2 font14 pointer">Aceptar</button>

            <button type="button" onclick="cerrarModal('modalDetalleTipoDeDocumento');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
        </div>   
        <div class="width990 autoMargin solicitudWhiteBox clearFix paddingBottom20">
           <div class="clearFix width990 autoMargin borderGrayBottom">
                <center><h1 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">DETALLE TIPO DE DOCUMENTO: ${tipoDeDocumento.nombre.toUpperCase()}</h1></center>
            </div>
            <div class="clearFix width990 autoMargin ">
                <center><h2 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">Generales</h2></center>
            </div>
            <div class="col6 floatLeft marginTop20">
                <div class="col12 floatLeft">
                    <div class="col6 floatLeft">
                        <p class="gray font14 fontWeight500 letterspacing1 marginLeft20"><strong>FORMATOS PERMITIDOS</strong></p>
                    </div>
                    <div class="col6 floatLeft">
                        <p class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;">${tipoDeDocumento.formatosPermitidos}</p>
                    </div>
                </div>
                <div class="col12 floatLeft">
                    <div class="col6 floatLeft">
                        <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>NOMBRE DE MAPEO</strong></p>
                    </div>
                    <div class="col6 floatLeft paddingTop8">
                        <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" value="${tipoDeDocumento.nombreMapeo}"/>
                    </div>
                </div>
                <div class="col12 floatLeft">
                    <div class="col6 floatLeft">
                        <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>TIPO DE INGRESO ASOCIADO</strong></p>
                    </div>
                    <div class="col6 floatLeft paddingTop8">
                    </div>
                </div>
                <div class="col12 floatLeft">
                    <div class="col6 floatLeft">
                        <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>USO EN COTIZADOR </strong></p>
                    </div>
                    <div class="col6 floatLeft paddingTop8">
                        <p class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;">${tipoDeDocumento.usoEnCotizador}</p>
                    </div>
                </div>
                
                <div class="col12 floatLeft">
                    <div class="col6 floatLeft">
                        <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>USO EN SOLICITUD </strong></p>
                    </div>
                    <div class="col6 floatLeft paddingTop8">
                        <p class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;">${tipoDeDocumento.usoEnSolicitud}</p>
                    </div>
                </div>
            </div>
            <div class="col6 floatLeft marginTop20">
                <div class="col12 floatLeft">
                    <div class="col6 floatLeft">
                        <p class="gray font14 fontWeight500 letterspacing1 marginLeft20"><strong>CANTIDAD SOLICITADA</strong></p>
                    </div>
                    <div class="col6 floatLeft">
                        <p class="block cameraBox col11 radius2 font14 lightGray" style="text-align: center; height: 72px; overflow-y: auto;">${tipoDeDocumento.cantidadSolicitada}</p>
                    </div>
                </div>
                <div class="col12 floatLeft">
                    <div class="col6 floatLeft">
                        <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>CODIGO</strong></p>
                    </div>
                    <div class="col6 floatLeft paddingTop8">
                        <p class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;">${tipoDeDocumento.codigo}</p>
                    </div>
                </div>
                <div class="col12 floatLeft">
                    <div class="col6 floatLeft">
                        <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Activo</strong></p>
                    </div>
                    <div class="col6 floatLeft paddingTop8">
                        <p class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;">${(tipoDeDocumento.activo) ? "SI" : "NO"}</p>
                    </div>
                </div>
            </div>
            <br /> &nbsp;
            
        </div>
            
        </div>
        
    </div>
</div>

