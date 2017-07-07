<div class="idLightbox hide" id="modalDetalleTipoDeGarantia">  
    <div class="overlay"></div>
    <div class="whiteContainer detalleProducto lightboxPos darkBluetitle font14 fontWeight400 letterspacing1 justify">
       <form id="formAltaUsuario" action="/dashboard/guardarTipoDeGarantia" method="POST" class="loginForm gray font14"> 
        <div class="width990 autoMargin solicitudWhiteBox clearFix paddingBottom20">
           <div class="clearFix width990 autoMargin borderGrayBottom">
                <center><h1 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">DETALLE TIPO DE GARANTÍA: ${tipoDeGarantia.nombre.toUpperCase()}</h1></center>
            </div>
            <div class="clearFix width990 autoMargin ">
                <center><h2 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10"></h2></center>
            </div>
            <div class="col6 floatLeft marginTop20">
                <div class="col12 floatLeft">
                    <div class="col6 floatLeft">
                        <p class="gray font14 fontWeight500 letterspacing1 marginLeft20"><strong>Nombre Tipo de Garantía</strong></p>
                    </div>
                    <div class="col6 floatLeft">
                        <input type='text' name="nombre" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" value="${tipoDeGarantia.nombre}"/>
                        <input type='hidden' name="tipoDeGarantiaId" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" value="${tipoDeGarantia.id}"/>

                    </div>
                </div>
                     <br /> &nbsp;
            
            </div>
           
              <div class="col6 floatLeft marginTop20">
                <div class="col12 floatLeft">
                    <div class="col6 floatLeft">
                        <p class="gray font14 fontWeight500 letterspacing1 marginLeft20"><strong>ACTIVO</strong></p>
                    </div>
                    <div class="col6 floatLeft">
                        <input type='text' name="activo" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" value="${(tipoDeGarantia.activo) ? "SI" : "NO"}"/>

                    </div>
                </div>
                     <br /> &nbsp;
            
            </div>
            
        </div>
          <div class="formContainer">
                <button type="submit" class="loginButton blueButton letterspacing2 font14 pointer">Actualizar</button>
                <button type="button" onclick="cerrarModal('modalDetalleTipoDeGarantia');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
            </div>
        </form>
    </div>
</div>

