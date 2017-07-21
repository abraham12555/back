<style >
    input:not([type]), input[type=text], input[type=password], input[type=email], input[type=url], input[type=time], input[type=date], input[type=datetime], input[type=datetime-local], input[type=tel], input[type=number], input[type=search], textarea.materialize-textarea {
    font-size: 1.6rem;
    border-bottom: 1px solid #005398;
    box-shadow: 0 1px 0 0 #25a3ff;
    transition: border .2s ease-in-out;
    -webkit-transition: border .2s ease-in-out;
    -moz-transition: border .2s ease-in-out;
    height: 55px;
    background-color: #f8f9ff;
    color: #25a3ff;
    text-align: left;
}
.goBtn {
    border-radius: 10px;
    background-image: linear-gradient(90deg, #005398, #1573d4);
    box-shadow: 0 11px 19px 0 #b8bdcd;
    color: white;
    font-weight: bold;
    border: none;
    cursor: pointer;
    padding: 2px 5px 3px 5px;
    text-decoration: none;
    height: 24px;
}
.whiteBox:hover .gray {
    background-image: linear-gradient(232deg, #252d60, #252d60);
    color:white;

}
.whiteBox:hover {
    background-image: linear-gradient(232deg, #252d60, #252d60);
    box-shadow: 0 0 7px 0 #298df5;
}
.antPaso {
    border-radius: 10px;
    background-image: linear-gradient(90deg, #005398, #1573d4);
    box-shadow: 0 11px 19px 0 #b8bdcd;
    color: white;
    font-weight: bold;
    border: none;
    cursor: pointer;
    padding: 2px 5px 3px 5px;
    text-decoration: none;
}
</style>
<g:if test="${token}">
<div class="idLightbox " id="sdsd">
    <div class="overlayVerificar"></div>
    <div class="whiteContainer lightboxPos identificationLb">
        <h2 class="floatLeft lbHeader headingColor fontWeight300 letterspacing1.5">CONTINÚA TU SOLICITUD</h2>

        <div class="border1 clearFloat"></div>
         <div class="clearFix">
            <a id="paso1CompDom">
                <div class="col6 col12-mob floatLeft">
                    <p id="label1Comp" class="menuTile active_blue font16 " style="cursor:pointer;">IDENTIFICATE MEDIANTE:</p>
                </div>
            </a>

        </div>
        <div class="border1"></div>
       <g:urlContextAware value="/solicitud/resume" var="urlResume"/>

        <form id="formAltaUsuario" action='${urlResume}' method="POST" class="loginForm gray font14">
        <div class="docStep">
            <div class="col12 col10-tab col12-mob autoMargin marginTop80 marginBottom80">
                <div class="paddingTop20 paddingBottom20">
                     <input type="hidden" id="token" name="token" value="${token}">
                     <input type="hidden" id="solicitudId" name="solicitudId">
                     <input type="hidden" id="tipo" name="tipo">
                    <div id="seleccionMedioDeVerificacion" class="docStep">

                        <div class="col12 col10-tab col12-mob autoMargin marginTop80 marginBottom80">
                            <div class="paddingTop20 paddingBottom20">
                                <div class="padding20 clearFix">
                                    <div class="col4 col12-mob floatLeft">
                                        <div class="paddingAside10" onclick="medioDeVerificacion('fechaDeNacimiento');">
                                            <div class="iconButton whiteBox docChoice telmex pointer">
                                                <p class="gray font18 fontWeight600 paddingTop10" style="cursor:pointer;">FECHA DE NACIMIENTO</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col4 col12-mob floatLeft">
                                        <div class="paddingAside10" onclick="medioDeVerificacion('correoElectronico');">
                                            <div class="iconButton whiteBox docChoice telmex pointer">
                                                <p class="gray font18 fontWeight600 paddingTop10" style="cursor:pointer;">CORREO ELECTRONICO</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col4 col12-mob floatLeft">
                                        <div class="paddingAside10" onclick="medioDeVerificacion('telefonoCelular');">
                                            <div class="iconButton whiteBox docChoice cfe pointer">
                                                <p class="gray font18 fontWeight600 paddingTop10" style="cursor:pointer;">TELÉFONO CELULAR</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                     <div id="fechaDeNacimiento" class="col6 col10-tab col12-mob autoMargin marginTop80 marginBottom80 hide">
                         <div class="paddingTop20 paddingBottom20">
                             <div class="padding20 clearFix">
                                 <div class="col6 register">
                                     <div class="row">
                                         <div id="first_name_form" class="input-field col12">
                                             <label for="first_name" class="darkBluetitle"><span><strong style="color:#fb5e48;">*</strong></span>¿Cúal es tu fecha de nacimiento? </label>
                                             <input id="fechaNac" name="fechaDeNacimiento" type="text" readonly="readonly" value=''>
                                             <p class="antPaso" style="display: inline-block;margin-top: 10px; text-align: center;">
                                                 <span class="">ATRAS</span>
                                             </p>
                                              <p class="" style="display: inline-block;margin-top: 10px; text-align: center;">
                                                  <span><input type="submit" class="goBtn" value="VERIFICAR" /></span>
                                             </p>
                                         </div>
                                     </div>
                                 </div>
                             </div>
                         </div>
                     </div>
                        <div id="correoElectronico" class="col6 col10-tab col12-mob autoMargin marginTop80 marginBottom80 hide">
                            <div class="paddingTop20 paddingBottom20">
                                <div class="padding20 clearFix">
                                    <div class="col6 register ">
                                        <div class="row">
                                            <div id="first_name_form" class="input-field col12">
                                                <label for="first_name" class="darkBluetitle"><span><strong style="color:#fb5e48;">*</strong></span>¿Cuál es tu Correo Electronico?</label>
                                                <input id="correoElectronicoVerificacion" name="correoElectronico" type="text" class="datoShortUrl mat-input requerido validarEmail">
                                                <p class="antPaso" style="display: inline-block;margin-top: 10px; text-align: center;">
                                                 <span class="">ATRAS</span>
                                             </p>
                                              <p class="" style="display: inline-block;margin-top: 10px; text-align: center;">
                                                 <input type="submit" class="goBtn email" value="VERIFICAR" />
                                             </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                     <div id="telefonoCelular" class="col6 col10-tab col12-mob autoMargin marginTop80 marginBottom80 hide">
                         <div class="paddingTop20 paddingBottom20">
                             <div class="padding20 clearFix">
                                 <div  class="col6 register">
                                     <div class="row">
                                         <div id="first_name_form" class="input-field col12">
                                             <label for="first_name" class="darkBluetitle"><span><strong style="color:#fb5e48;">*</strong></span>¿Cuál Número de celular?</label>
                                             <input id="phoneVerificacion" name="telefonoCelular" type="text" class="datoShortUrl mat-input" data-mask="99-99-99-99-99" maxlength="14" onkeypress="return event.keyCode != 13;" >
                                              <p class="antPaso" style="display: inline-block;margin-top: 10px; text-align: center;">
                                                 <span class="">ATRAS</span>
                                             </p>
                                             <p class="" style="display: inline-block;margin-top: 10px; text-align: center;">
                                                 <input type="button" onclick="verificarSms();" class="goBtn enviar" value="ENVIAR" />
                                             </p>
                                             <p style="margin-top: 10px;" id="leyendaTel"><small class="darkBluetitle">* Enviaremos un código de confirmación al número ingresado.</small></p>

                                         </div>
                                     </div>
                                 </div>
                             </div>
                         </div>
                     </div>
                     <div id="codigoConfirmacion" class="col6 col10-tab col12-mob autoMargin marginTop80 marginBottom80 hide">
                         <div class="paddingTop20 paddingBottom20">
                             <div class="padding20 clearFix">
                                 <div  class="col6 register">
                                     <div class="row">
                                         <div id="codigo_form" class="input-field col12 ">
                                             <label for="codigo" class="darkBluetitle">Ingresa el código que acabas de recibir por SMS</label>
                                             <input id="codigoVerificacion" type="text" name="codigoConfirmacion" class="datoShortUrl mat-input" maxlength="5" onkeypress="return event.keyCode != 13;">
                                             <div style="text-align: center;">
                                                <p class="antPaso" style="display: inline-block;margin-top: 10px; text-align: center;">
                                                 <span class="">ATRAS</span>
                                                </p>
                                                 <p class="sigPaso" style="display: inline-block;margin-top: 10px; text-align: center;">
                                                    
                                                     <input id='exito'type="submit" class="goBtn hide" value="CONTINUAR" disabled/>

                                                 </p>
                                             </div>
                                         <p style="margin-top: 10px;" id="leyendaCodigoVerificacion"><small style='color: #25a3ff;'><strong>Espera por favor entre 15 y 30 segundos para recibir tu código..</strong></small></p>
                                         </div>
                                     </div>
                                 </div>
                             </div>
                         </div>
                     </div>

                </div>
            </div>
        </div>
        </form>

    </div>
</div>
</g:if>
<g:else>
    <div class="idLightbox " id="sdsd">
        <div class="overlayVerificar"></div>
        <div class="whiteContainer lightboxPos identificationLb">   
        </div>
    </div>
</g:else>
<script type="text/javascript">
$(document).ready(function() {
operacionesShortUrl()
});
</script>

