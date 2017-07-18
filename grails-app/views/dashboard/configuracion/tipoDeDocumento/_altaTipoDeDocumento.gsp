<div class="idLightbox hide" id="modalAltaTipoDeDocumento">
    <div class="overlay"></div>
    <div class="whiteContainer lightboxPos altaUsuario darkBluetitle font14 fontWeight400 letterspacing1 justify">
        <g:urlContextAware value="/dashboard/registrarTipoDeDocumento" var="urlFormAltaUsuario"/>
        <form id="formAltaUsuario" action="${urlFormAltaUsuario}" method="POST" class="loginForm gray font14">
            <div class="clearFix width990 autoMargin">
                <center><h1 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">ALTA DE TIPO DE DOCUMENTO</h1></center>
            </div>
            <div class="col12 col12-mob floatLeft clearFix marginTop25 marginBottom25 paddingBottom20 borderGrayBottom">
                <div class="col4 floatLeft">
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">Nombre</p>
                    </div>
                     <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">Nombre Mapeo</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">Formatos</p>
                    </div>
                    
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">Nombre en Plural</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">Cantidad</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">Codigo</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">Tipo De Ingreso</p>
                    </div>
                </div>
                <div class="col8 floatLeft">
                    <div class="paddingTop15">
                        <input class="block cameraBox col11 height30" type="text" name="nombre" value="">
                    </div>
                    <div class="paddingTop8">
                        <input class="block cameraBox col11 height30" type="text" name="nombreMapeo" value="">
                    </div>
                    <div class="paddingTop8">
                        <input class="block cameraBox col11 height30" type="text" name="formatosPermitidos" value="">
                    </div>
                   
                    <div class="paddingTop8">
                        <input class="block cameraBox col11 height30" type="text" name="nombreEnPlural" value="">
                    </div>
                    <div class="paddingTop8">
                        <input class="block cameraBox col11 height30" type="text" name="cantidadSolicitada" value="">
                    </div>
                    <div class="paddingTop8">
                        <input class="block cameraBox col11 height30" type="text" name="codigo" value="">
                    </div>
                    <div class="paddingTop8">
                        <g:select selected="true"  id="tipoDeIngresos" name="tipoDeIngresos.id" class="cameraBox col11 block height100"  from="${listaDeTiposDeIngresos}" optionKey="id" optionValue="nombre" noSelection="['':'Seleccione un tipo de Ingreso']" value="" />
                    </div>
                        
                </div>
            </div>
            <div class="formContainer">
                <button type="submit" class="loginButton blueButton letterspacing2 font14 pointer">REGISTRAR TIPO DE DOCUMENTO</button>
                <button type="button" onclick="cerrarModal('modalAltaTipoDeDocumento');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
            </div>
        </form>
    </div>
</div>
