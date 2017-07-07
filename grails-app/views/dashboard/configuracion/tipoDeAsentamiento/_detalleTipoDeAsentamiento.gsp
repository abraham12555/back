<div class="idLightbox hide" id="modalDetalleTipoDeAsentamiento">  
    <div class="overlay"></div>
    <div class="whiteContainer detalleProducto lightboxPos darkBluetitle font14 fontWeight400 letterspacing1 justify">
       <form id="formAltaUsuario" action="/dashboard/guardarTipoDeAsentamiento" method="POST" class="loginForm gray font14"> 
        <div class="width990 autoMargin solicitudWhiteBox clearFix paddingBottom20">
           <div class="clearFix width990 autoMargin borderGrayBottom">
                <center><h1 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">DETALLE TIPO DE ASENTAMIENTO: ${tipoDeAsentamiento.nombre.toUpperCase()}</h1></center>
            </div>
            <div class="clearFix width990 autoMargin ">
                <center><h2 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10"></h2></center>
            </div>
            <div class="col12 floatLeft marginTop20">
                <div class="col12 floatLeft">
                    <div class="col6 floatLeft">
                        <p class="gray font14 fontWeight500 letterspacing1 marginLeft20"><strong>NOMBRE</strong></p>
                    </div>
                    <div class="col6 floatLeft">
                        <input type='text' name="nombre" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" value="${tipoDeAsentamiento.nombre}"/>
                        <input type='hidden' name="tipoDeAsentamiento.id" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" value="${tipoDeAsentamiento.id}"/>

                    </div>
                </div>
                     <br /> &nbsp;
            
        </div>
            
        </div>
          <div class="formContainer">
                <button type="submit" class="loginButton blueButton letterspacing2 font14 pointer">Actualizar</button>
                <button type="button" onclick="cerrarModal('modalDetalleTipoDeAsentamiento');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
            </div>
        </form>
    </div>
</div>

