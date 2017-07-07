<form id="configuracionEntidadFinancieraForm">
<section class="container marginBottom20">
    <div class="solicitudBox width480 autoMargin radius2 ">
        <div class="clearFix paddingTop15 paddingBottom15 paddingLeft25 paddingRight25">
            <h5 class="font14 fontWeight500 gray marginBottom10">IDENTIDAD DE MARCA</h5>
            <div class="floatLeft marginRight15 whiteBox paddingTop30 paddingBottom30 paddingLeft25 paddingRight25">
                <img src="${resource(dir:'images', file: configuracion?.rutaLogotipo)}" alt="Photo Place">
            </div>
            <div id="divDropzone" class="floatLeft buscarBtn">
                <h5 class="font14 fontWeight500 gray marginBottom10 marginTop25">SUBE TU LOGO</h5>
                <a id="subirLogo" title="BUSCAR EN MI PC" class="block whiteBox center gray2 paddingTop15 paddingBottom15 paddingLeft80 paddingRight80 pointer">BUSCAR EN MI PC</a>
            </div>
        </div>
    </div>
</section>

<section class="container marginBottom20">
    <div class="solicitudBox width480 autoMargin radius2 ">
        <div class="clearFix paddingTop15 paddingBottom15 paddingLeft25 paddingRight25">
            <h5 class="font14 fontWeight500 gray marginBottom10">LANDING</h5>
          <div id="imagenDelProducto" class="cotizador-bg" style="background-image: url('data:image/${session?.landing?.extension};base64,${session?.landing?.base64}');">
               <div class="dropzone-previews"></div>
          </div>
            <div id="divDropzone" class="floatLeft landingBtn">
                <h5 class="font14 fontWeight500 gray marginBottom10 marginTop25">SUBE TU LANDING</h5>
                <a id="subirLanding" title="BUSCAR EN MI PC" class="block whiteBox center gray2 paddingTop15 paddingBottom15 paddingLeft80 paddingRight80 pointer">BUSCAR EN MI PC</a>
            </div>
        </div>
    </div>
</section>

<section class="container marginBottom20">
    <div class="solicitudBox width480 autoMargin radius2 ">
        <div class="clearFix paddingTop15 paddingBottom15 paddingLeft25 paddingRight25">
            <h5 class="font14 fontWeight500 gray marginBottom10">CSS</h5>
     
            <div id="divDropzone" class="floatLeft cssBtn">
                <h5 class="font14 fontWeight500 gray marginBottom10 marginTop25">SUBE TU ARCHIVO CSS</h5>
                <a id="subirCss" title="BUSCAR EN MI PC" class="block whiteBox center gray2 paddingTop15 paddingBottom15 paddingLeft80 paddingRight80 pointer">BUSCAR EN MI PC</a>
            </div>
        </div>
    </div>
</section>
<section class="container marginBottom35">
    <div class="solicitudBox width480 autoMargin radius2 clearFix">
        <h5 class="font14 fontWeight500 gray paddingTop15 paddingBottom15 paddingLeft25">COLORES</h5>
        <div class="lightGrayBG paddingTop10 paddingBottom10 paddingLeft25 clearFix">
            <div class="whiteBox colorBox floatLeft clearFix marginRight25">
                <input type="color" id="colorBordeSuperior" name="colorBordeSuperior" value="${configuracion?.colorBordeSuperior}">
            </div>
            <p class="floatLeft gray marginTop10">COLOR 1 (BORDE SUPERIOR)</p>
        </div>
        <div class="paddingTop10 paddingBottom10 paddingLeft25 clearFix">
            <div class="whiteBox colorBox floatLeft clearFix marginRight25">
                <input type="text" id="colorEncabezado" name="colorEncabezado" value="${configuracion?.colorEncabezado}">
            </div>
            <p class="floatLeft gray marginTop10">COLOR 2 (COLOR ENCABEZADO)</p>
        </div>
        <div class="lightGrayBG paddingTop10 paddingBottom10 paddingLeft25 clearFix">
            <div class="whiteBox colorBox floatLeft clearFix marginRight25">
                <input type="text" id="colorFondo" name="colorFondo"value="${configuracion?.colorFondo}">
            </div>
            <p class="floatLeft gray marginTop10">COLOR 3 (COLOR FONDO)</p>
        </div>
        <div class="paddingTop10 paddingBottom10 paddingLeft25 clearFix">
            <div class="whiteBox colorBox floatLeft clearFix marginRight25">
                <input type="text" id="colorGradienteSuperior" name="colorGradienteSuperior"value="${configuracion?.colorGradienteSuperior}">
            </div>
            <p class="floatLeft gray marginTop10">COLOR 4 (GRADIENTE SUPERIOR)</p>
        </div>
        <div class="lightGrayBG paddingTop10 paddingBottom10 paddingLeft25 clearFix">
            <div class="whiteBox colorBox floatLeft clearFix marginRight25">
                <input type="text" id="colorGradienteInferior" name="colorGradienteInferior" value="${configuracion?.colorGradienteInferior}">
            </div>
            <p class="floatLeft gray marginTop10">COLOR 5 (GRADIENTE INFERIOR)</p>
        </div>
        <div class="lightGrayBG paddingTop10 paddingBottom10 paddingLeft25 clearFix">
            <div class="whiteBox colorBox floatLeft clearFix marginRight25">
                <input type="text" id="colorTitulos" name="colorTitulos"value="${configuracion?.colorTitulos}" >
            </div>
            <p class="floatLeft gray marginTop10">COLOR 5 (TITULOS)</p>
        </div>
        <div class="marginBottom30 fullInputs">
            <h5 class="font14 fontWeight500 gray paddingTop15 paddingBottom15 paddingLeft25">TIPOGRAFÍA</h5>
            <select name="tipografia" id="tipografia" class="whiteBox block autoMargin paddingTop10 paddingBottom10 paddingLeft20 font14 gray letterspacing1">
                <option value="Helvetica">Helvetica</option>
            </select>
        </div>
    </div>
</section>

<section class="container clearFix">
    <div class="width990 autoMargin solicitudWhiteBox clearFix paddingBottom20">
            <p class="loginTitle font25 fontWeight500 darkBluetitle textUpper letterspacing1 center paddingAside15 marginBottom25">CONFIGURACIÓN DE TITULOS</p>
            <div style="width: 90%;" class="solicitudTitle autoMargin">
                <p class="center font14 lightGray">TÍTULOS .</p>
            </div>
            <div class="formContainer">
                <input  name="configuracionEntidadFinancieraId" id="id_configuracion_buro" type="hidden" value="${configuracion?.id}"/>
                <label>TÍTULO RAZÓN SOCIAL.</label><input class="inputs marginBottom30 lightGray letterspacing1 font14" name="razonSocial" id="razonSocial" type="text" required placeholder="Razón Social" value="${configuracion?.razonSocial}"/>
                <label>NOMBRE COMERCIAL.</label><input class="inputs marginBottom30 lightGray letterspacing1 font14" name="nombreComercial" id="nombreComercial" type="text" required placeholder="Nombre Comercial" value="${configuracion?.nombreComercial}"/>
                <label>TEXTO PRODUCTO DEFAULT</label><input class="inputs marginBottom30 lightGray letterspacing1 font14" name="textoProductoDefault" id="tituloProductoDefault" type="text" required placeholder="Texto Producto Default" value="${configuracion?.textoProductoDefault}"/>
                <label>TÍTULO COTIZADOR</label><input class="inputs marginBottom30 lightGray letterspacing1 font14" name="tituloCotizador" id="tituloCotizador" type="text" required placeholder="Titulo en Cotizador" value="${configuracion?.tituloCotizador}"/>
                <label>HTML TITLE</label><input class="inputs marginBottom30 lightGray letterspacing1 font14" name="htmlTitle" id="htmlTitle" type="text" required placeholder="Html Title" value="${configuracion?.htmlTitle}"/>
                <label>TEXTO DESCRIPCIÓN DEFAULT</label><input class="inputs marginBottom30 lightGray letterspacing1 font14" name="textoDescripcionDefault" id="textoDescripcionDefault" type="text" required placeholder="Texto Descripción Default" value="${configuracion?.textoDescripcionDefault}"/>
                <label>TEXTO MONTO DEFAULT</label><input class="inputs marginBottom30 lightGray letterspacing1 font14" name="textoMontoDefault" id="textoMontoDefault" type="text" required placeholder="Monto Default" value="${configuracion?.textoMontoDefault}"/>
                <label>MENSAJE DE CONFIRMACIÓN CELULAR</label><input class="inputs marginBottom30 lightGray letterspacing1 font14" name="mensajeConfirmacionCelular" id="mensajeConfirmacionCelular" type="text" required placeholder="Mensaje de Confirmacion Celular " value="${configuracion?.mensajeConfirmacionCelular}"/>
                <label>MENSAJE AL ENVIAR SHORT URL</label><input class="inputs marginBottom30 lightGray letterspacing1 font14" name="mensajeEnvioShortUrl" id="mensajeEnvioShortUrl" type="text" required placeholder="Mensaje al enviar ShortUrl" value="${configuracion?.mensajeEnvioShortUrl}"/>
                <label>SLOGAN</label><input class="inputs marginBottom30 lightGray letterspacing1 font14" name="slogan" id="mensajeEnvioShortUrl" type="text" required placeholder="Slogan" value="${configuracion?.slogan}"/>
 <label>TERMINOS Y CONDICIONES</label>  

 <textarea id="terminosYCondiciones" name="terminosCondiciones"> ${configuracion?.terminosCondiciones}</textarea>

            </div>
    </div>
</section>
<section class="container marginBottom84">
    <div class="width500 autoMargin clearFix">
        <div class="col6 col12-mob floatLeft center">
            <div class="paddingAside10 marginBottom20">
                <div class="whiteBox width220">
                    <a href="#" class="hoverBtn block font16 fontweight500 gray2 paddingTop20 paddingBottom20">VISTA PREVIA</a>
                </div>
            </div>
        </div>
        <div class="col6 col12-mob floatRight center">
            <div class="paddingAside10 marginBottom20">
                <div class="whiteBox radius4 width220">
                    <a href="#" class="hoverBtn block font16 fontweight500 gray2 paddingTop20 paddingBottom20"  onclick="actualizarConfiguracionEntidadFinanciera();">GUARDAR CAMBIOS</a>
                  
                </div>
            </div>
        </div>
    </div>
</section>
</form>
