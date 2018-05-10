<div class="idLightbox hide" id="modalDetalleIconos">  
    <div class="overlay"></div>
    <div class="whiteContainer detalleProducto lightboxPos darkBluetitle font14 fontWeight400 letterspacing1 justify">
       <g:urlContextAware value="/dashboard/guardarTipoDeCampo" var="urlFormAltaUsuario"/>
        <form id="formAltaUsuario" action="${urlFormAltaUsuario}" method="POST" class="loginForm gray font14"> 
        <div class="width990 autoMargin solicitudWhiteBox clearFix paddingBottom20">
            <div class="clearFix width990 autoMargin">
                <center><h1 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">ELIGE EL ICONO</h1></center>
            </div>
         <div class="row fontawesome-icon-list" >
             <g:each var="lista" in="${data}">
        <div class="col6 floatLeft paddingTop8">
            <a onclick="seleccionarIcono('${lista}','${idCampo}');" class="loginButton  letterspacing2 font14 pointer" > <i class="fa ${lista}"></i> (${lista})  </a>
         </div>

                 </g:each>
        </div>

        </div>
          <div class="formContainer">
                <button onclick class="loginButton blueButton letterspacing2 font14 pointer">Actualizar</button>
                <button type="button" onclick="cerrarModal('modalDetalleIconos');" class="loginButton letterspacing2 font14 pointer blueButton" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
            </div>
        </form>
    </div>
</div>

