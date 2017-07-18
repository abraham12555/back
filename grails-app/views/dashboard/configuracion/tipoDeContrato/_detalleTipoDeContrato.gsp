<div class="idLightbox hide" id="modalDetalleTipoDeContrato">  
    <div class="overlay"></div>
    <div class="whiteContainer detalleProducto lightboxPos darkBluetitle font14 fontWeight400 letterspacing1 justify">
        <g:urlContextAware value="/dashboard/guardarTipoDeContrato" var="urlFormAltaUsuario"/>
       <form id="formAltaUsuario" action="${urlFormAltaUsuario}" method="POST" class="loginForm gray font14"> 
        <div class="width990 autoMargin solicitudWhiteBox clearFix paddingBottom20">
           <div class="clearFix width990 autoMargin borderGrayBottom">
                <center><h1 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">DETALLE TIPO DE CAMPO: ${tipoDeContrato.nombre.toUpperCase()}</h1></center>
            </div>
            <div class="clearFix width990 autoMargin ">
                <center><h2 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10"></h2></center>
            </div>
            <div class="col6 floatLeft marginTop20">
                <div class="col12 floatLeft">
                    <div class="col6 floatLeft">
                        <p class="gray font14 fontWeight500 letterspacing1 marginLeft20"><strong>Nombre Tipo de Contrato</strong></p>
                    </div>
                    <div class="col6 floatLeft">
                        <input type='text' name="nombre" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" value="${tipoDeContrato.nombre}"/>
                        <input type='hidden' name="tipoDeContratoId" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" value="${tipoDeContrato.id}"/>

                    </div>
                </div>
                     <br /> &nbsp;
            
            </div>          
        </div>
          <div class="formContainer">
                <button type="submit" class="loginButton blueButton letterspacing2 font14 pointer">Actualizar</button>
                <button type="button" onclick="cerrarModal('modalDetalleTipoDeContrato');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
            </div>
        </form>
    </div>
</div>

