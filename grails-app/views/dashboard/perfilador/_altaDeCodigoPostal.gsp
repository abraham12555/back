<div class="idLightbox hide" id="modalAltaDeCP">
    <div class="overlay"></div>
    <div class="whiteContainer lightboxPos altaUsuario darkBluetitle font14 fontWeight400 letterspacing1 justify">
        <form id="formCP" class="loginForm gray font14">
            <div class="clearFix width990 autoMargin">
                <center><h1 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">REGISTRAR CÓDIGO POSTAL</h1></center>
            </div>
            <div class="col12 col12-mob floatLeft clearFix marginTop25 marginBottom25 paddingBottom20 borderGrayBottom">
                <div class="col4 floatLeft">
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">Código Postal</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">Estado</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">Municipio</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">Tipo de Asentamiento</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">Asentamiento</p>
                    </div>
                </div>
                <div class="col8 floatLeft">
                    <div class="paddingTop15">
                        <input class="block cameraBox col11 height30" type="text" name="codigoPostal_codigo" id="codigoPostal_codigo" readonly>
                    </div>
                    <div class="paddingTop8">
                        <g:select class="block cameraBox col11 height30" name="codigoPostal_estado" id="codigoPostal_estado" from="${estados}" optionKey="id" optionValue="nombre" noSelection="['':'Seleccione un Estado']" />
                    </div>
                    <div class="paddingTop8">
                        <select class="block cameraBox col11 height30" name="codigoPostal_municipio" id="codigoPostal_municipio"></select>
                    </div>
                    <div class="paddingTop8">
                        <g:select class="block cameraBox col11 height30" name="codigoPostal_tipoDeAsentamiento" id="codigoPostal_tipoDeAsentamiento" from="${tiposDeAsentamiento}" optionKey="id" optionValue="nombre" noSelection="['':'Seleccione el Tipo de Asentamiento']" />
                    </div>
                    <div class="paddingTop8">
                        <input class="block cameraBox col11 height30" type="text" name="codigoPostal_asentamiento" id="codigoPostal_asentamiento" value="">
                    </div>
                </div>
            </div>
            <div class="formContainer">
                <button type="button" onclick="registarCodigoPostal();" class="loginButton blueButton letterspacing2 font14 pointer">REGISTRAR CÓDIGO POSTAL</button>
                <button type="button" onclick="cerrarModal('modalAltaDeUsuario');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
            </div>
        </form>
    </div>
</div>