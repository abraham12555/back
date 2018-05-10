<!-- section class="container marginTop10 marginBottom10">
    <div class="clearFix width990 autoMargin">
        <h1 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">CONFIGURACIÓN BURO DE CREDITO</h1>
    </div>
</section-->

<section class="container clearFix">
    <div class="width990 autoMargin solicitudWhiteBox clearFix paddingBottom20">
        <div id = 'botonAtras'class="radius2 lightGrayBG autoMargin hide" style='position: static;'>
            <ul class="clearFix marginLeft10 solicitude_submenu">
                <li class="floatLeft marginLeft8">
                    <a id="datosGeneralesButton" onclick="atrasBc();" class="blueButton ancla opcionMenuSolicitud displayInline font14 fontWeight300  paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Atrás</a>
                </li>
            </ul>
        </div>
        <form id="configuracionBuroCreditoForm" class="loginForm gray font14">
            <p class="loginTitle font25 fontWeight500 darkBluetitle textUpper letterspacing1 center paddingAside15 marginBottom25">CONFIGURACIÓN DE BURÓ DE CRÉDITO</p>
            <div style="width: 90%;" class="solicitudTitle autoMargin">
                <p class="center font14 lightGray">Selecciona el tipo de configuración que deseas cambiar.</p>
            </div>
            <div class="formContainer">
                <section class="container marginBottom84 tipoDeConsultaBc">
                    <div class="width500 autoMargin clearFix">
                        <div class="col6 col12-mob floatLeft center">
                            <div class="paddingAside10 marginBottom20">
                                <div class="whiteBox width220">
                                    <a href="#" class="hoverBtn block font16 fontweight500 gray2 paddingTop20 paddingBottom20" onclick="seleccionConsultaBc(0);">AUTENTICADOR</a>
                                </div>
                            </div>
                        </div>
                        <div class="col6 col12-mob floatRight center">
                            <div class="paddingAside10 marginBottom20">
                                <div class="whiteBox radius4 width220">
                                    <a href="#" class="hoverBtn block font16 fontweight500 gray2 paddingTop20 paddingBottom20" onclick="seleccionConsultaBc(1);">TRADICIONAL</a>

                                </div>
                            </div>
                        </div>
                    </div>
                </section>
                <input type ='hidden' id='valorTipoConsulta'name='valorTipoConsulta'>
                <div  class='hide' id ='tipoAutenticador'>
                <input  name="id_configuracion_buro_Autenticador" id="id_configuracion_buro_Autenticador" type="hidden" value="${configuracionBuroCreditoAutenticador[0]?.id}"/>
                <label>USUARIO.</label><input class="inputs marginBottom30 lightGray letterspacing1 font14" name="encabezadoClaveUsuarioAutenticador" id="encabezadoClaveUsuarioAutenticador" type="text" required placeholder="Usuario Buró de Crédito" value="${configuracionBuroCreditoAutenticador[0]?.encabezadoClaveUsuario}"/>
                <label>CONTRASEÑA.</label><input class="inputs marginBottom30 lightGray letterspacing1 font14" name="encabezadoPasswordAutenticador" id="encabezadoPasswordAutenticador" type="password" required placeholder="Contraseña Buró de Crédito" value="${configuracionBuroCreditoAutenticador[0]?.encabezadoPassword}"/>


                <label>NÚMERO DE REINTENTOS.</label><input class="inputs marginBottom30 lightGray letterspacing1 font14" name="reintentosAutenticador" type="text" id="reintentosAutenticador" required placeholder="Número de Reintentos" value="${configuracionBuroCreditoAutenticador[0]?.reintentos}"/>
                <button type="button" class="loginButton blueButton letterspacing2 font14 pointer" onclick="actualizarConfiguracionBuroCredito();">ACTUALIZAR</button>
                </div>
                <div class='hide' id ='tipoTradicional'>
                <input  name="id_configuracion_buro_Tradicional" id="id_configuracion_buro_tradicional" type="hidden" value="${configuracionBuroCreditoTradicional[0]?.id}"/>
                <label>USUARIO.</label><input class="inputs marginBottom30 lightGray letterspacing1 font14" name="encabezadoClaveUsuarioTradicional" id="encabezadoClaveUsuarioTradicional" type="text" required placeholder="Usuario Buró de Crédito" value="${configuracionBuroCreditoTradicional[0]?.encabezadoClaveUsuario}"/>
                <label>CONTRASEÑA.</label><input class="inputs marginBottom30 lightGray letterspacing1 font14" name="encabezadoPasswordTradicional" id="encabezadoPasswordTradicional" type="password" required placeholder="Contraseña Buró de Crédito" value="${configuracionBuroCreditoTradicional[0]?.encabezadoPassword}"/>
                <label>NÚMERO DE REINTENTOS.</label><input class="inputs marginBottom30 lightGray letterspacing1 font14" name="reintentosTradicional" type="text" id="reintentosTradicional" required placeholder="Número de Reintentos" value="${configuracionBuroCreditoTradicional[0]?.reintentos}"/>
                <button type="button" class="loginButton blueButton letterspacing2 font14 pointer" onclick="actualizarConfiguracionBuroCredito();">ACTUALIZAR</button>
                </div>
                </div>
        </form>
    </div>
</section>
