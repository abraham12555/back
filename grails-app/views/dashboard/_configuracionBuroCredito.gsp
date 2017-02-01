<!-- section class="container marginTop10 marginBottom10">
    <div class="clearFix width990 autoMargin">
        <h1 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">CONFIGURACIÓN BURO DE CREDITO</h1>
    </div>
</section-->

<section class="container clearFix">
    <div class="width990 autoMargin solicitudWhiteBox clearFix paddingBottom20">
        <form id="configuracionBuroCreditoForm" class="loginForm gray font14">
            <p class="loginTitle font25 fontWeight500 darkBluetitle textUpper letterspacing1 center paddingAside15 marginBottom25">CONFIGURACIÓN DE BURÓ DE CRÉDITO</p>
            <div style="width: 90%;" class="solicitudTitle autoMargin">
                <p class="center font14 lightGray">Proporciona los parametros para el consumo de los servicios web.</p>
            </div>
            <div class="formContainer">
                <input  name="id_configuracion_buro" id="id_configuracion_buro" type="hidden" value="${configuracionBuroCredito?.id}"/>
                <label>USUARIO.</label><input class="inputs marginBottom30 lightGray letterspacing1 font14" name="encabezadoClaveUsuario" id="encabezadoClaveUsuario" type="text" required placeholder="Usuario Buró de Crédito" value="${configuracionBuroCredito?.encabezadoClaveUsuario}"/>
                <label>CONTRASEÑA.</label><input class="inputs marginBottom30 lightGray letterspacing1 font14" name="encabezadoPassword" id="encabezadoPassword" type="text" required placeholder="Contraseña Buró de Crédito" value="${configuracionBuroCredito?.encabezadoPassword}"/>
                <label>PRODUCTO REQUERIDO.</label><input class="inputs marginBottom30 lightGray letterspacing1 font14" name="encabezadoProductoRequerido" id="encabezadoProductoRequerido" type="text" required placeholder="Producto Requerido Buró de Crédito" value="${configuracionBuroCredito?.encabezadoProductoRequerido}"/>
                <label>URL SERVICIO WEB.</label><input class="inputs marginBottom30 lightGray letterspacing1 font14" name="urlBuroCredito" type="text" id="urlBuroCredito" required placeholder="URL Buró de Crédito" value="${configuracionBuroCredito?.urlBuroCredito}"/>
                <label>NÚMERO DE REINTENTOS.</label><input class="inputs marginBottom30 lightGray letterspacing1 font14" name="reintentos" type="text" id="reintentos" required placeholder="Número de Reintentos" value="${configuracionBuroCredito?.reintentos}"/>
                <button type="button" class="loginButton blueButton letterspacing2 font14 pointer" onclick="actualizarConfiguracionBuroCredito();">ACTUALIZAR</button>
            </div>
        </form>
    </div>
</section>