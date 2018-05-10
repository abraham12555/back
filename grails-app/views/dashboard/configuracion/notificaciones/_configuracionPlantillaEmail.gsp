<div class="idLightbox hide" id="seleccionEmail">
    <div class="overlay"></div>
    <div class="whiteContainer lightboxPos notificacionesWidth50 darkBluetitle font14 fontWeight400 letterspacing1 justify">
        <div class='loginForm gray font14' >  
            <div class="clearFix width990 autoMargin">
                <center><h1 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">NUEVA PLANTILLA EMAIL DE TIPO</h1></center>
            </div>
            <div class="col12 col12-mob floatLeft clearFix marginTop25 marginBottom25 paddingBottom20 borderGrayBottom">
                <div class="col12 col12-mob floatLeft" >
                    <div class="col4 col4-mob floatLeft paddingTop15">
                    </div>
                    <div class="col8 col8-mob floatLeft paddingTop15">
                        <div class="marginLeft20">
                            <div></div>
                        </div>
                    </div>
                    <div class="col4 col4-mob floatLeft">
                        <div class="paddingTop5"></div>
                        <div class="marginLeft20">
                            <span class="font14 latterspacing1 center">TIPO DE ENVÍO</span>
                        </div>
                    </div>
                    <div class="col8 col8-mob floatLeft">
                        <div class="paddingTop5">
                            <div id="tipoDeEnvio-div" class="marginLeft20">
                                <input name="tipoDeEnvioEmail" value="0" type="radio">
                                <span class="marginRight20">FIJO</span>
                                <input name="tipoDeEnvioEmail" value="1" type="radio">
                                <span class="marginRight20">POR INACTIVIDAD</span>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <div class="formContainer">
                <button class="loginButton blueButton letterspacing2 font14 pointer" onclick="seleccionTipoDeEnvioEmail();">ACEPTAR</button>
                <button type="button" onclick="cerrarModal('seleccionEmail');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
            </div>
    </div>
    </div>
</div>

<div class="idLightbox hide" id="modalPlantillaEmail">
    <div class="overlay"></div>
    <div class="whiteContainer lightboxPos notificacionesWidth50 darkBluetitle font14 fontWeight400 letterspacing1 justify">
        <g:urlContextAware value="/notificaciones/saveEmailTemplate" var="urlFormAddEmailTemplate"/>
        <form id="formAddEmailTemplate" action="${urlFormAddEmailTemplate}" method="POST" class="loginForm gray font14">
            <input type="hidden" name="idTemplate" id="idEmailTemplate"/>
            <input type="hidden" name="idTipoDeEnvioEmail" id="idTipoDeEnvioEmail"/>
            <div class="clearFix width990 autoMargin">
                <center><h1 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">PLANTILLA EMAIL</h1></center>
            </div>
            <div class="col12 col12-mob floatLeft clearFix marginTop25 marginBottom25 paddingBottom20 borderGrayBottom">
                <div class="col12 col12-mob floatLeft" id="statusEmailConfig-div">
                    <div class="col4 col4-mob floatLeft paddingTop15">
                    </div>
                    <div class="col8 col8-mob floatLeft paddingTop15">
                        <div class="marginLeft20">
                            <div id="nombrePlantillaEmail-control"></div>
                        </div>
                    </div>
                    <div class="col4 col4-mob floatLeft">
                        <div class="paddingTop5"></div>
                        <div class="marginLeft20">
                            <span class="font14 latterspacing1 center bold">Nombre de la Plantilla</span>
                        </div>
                    </div>
                    <div class="col8 col8-mob floatLeft">
                        <div class="paddingTop5">
                            <div class="marginLeft20">
                                <input type="text" class="inputs letterspacing1 font14 width300"placeholder="Nombre de la Plantilla" name="nombrePlantilla" id="nombrePlantillaEmail"/>

                            </div>
                        </div>
                    </div>
                     <div class="col4 col4-mob floatLeft paddingTop15">
                     </div>
                    <div class="col8 col8-mob floatLeft paddingTop15">
                        <div class="marginLeft20">
                            <div id="statusEmail-control"></div>
                        </div>
                    </div>
                    <br>
                    <div class="col12 col12-mob floatLeft clearFix"></div>
                    <div class="col4 col4-mob floatLeft">
                        <div class="paddingTop5"></div>
                        <div class="marginLeft20">
                            <span class="font14 latterspacing1 center bold">Pasos de Solicitud</span>
                        </div>
                    </div>
                    <div class="col8 col8-mob floatLeft">
                        <div class="paddingTop5">
                            <ul id="statusEmail-div" class="marginLeft20">

                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col12 col12-mob floatLeft" id="statusEmailConfig-div">
                    <div class="col4 col4-mob floatLeft paddingTop15">
                    </div>
                    <div class="col8 col8-mob floatLeft paddingTop15">
                        <div class="marginLeft20">
                            <div id="emailAsunto-control"></div>
                        </div>
                    </div>
                    <div class="col4 col4-mob floatLeft">
                        <div class="paddingTop5"></div>
                        <div class="marginLeft20">
                            <span class="font14 latterspacing1 center">Asunto</span>
                        </div>
                    </div>
                    <div class="col8 col8-mob floatLeft">
                        <div class="paddingTop5 marginLeft20">
                            <input type="text" id="emailAsunto" name="asunto" class="inputs letterspacing1 font14 width300"placeholder="Asunto"/>
                        </div>
                    </div>
                </div>
                <div class="col12 col12-mob floatLeft">
                    <div class="col4 col4-mob floatLeft paddingTop15">
                    </div>
                    <div class="col8 col8-mob floatLeft paddingTop15">
                        <div class="marginLeft20">
                            <div id="contenidoEmail-control"></div>
                        </div>
                    </div>
                    <div class="col4 col4-mob floatLeft">
                        <div class="paddingTop5"></div>
                        <div class="dragitems">
                            <h3>
                                <span class="font14 latterspacing1 center ">Datos disponibles</span>
                            </h3>
                            <ul class="availableFields" runat="server">
                            </ul>
                        </div>
                    </div>
                    <div class="col8 col8-mob floatLeft">
                        <div class="paddingTop5">
                            <div class="dropitems">
                            <textarea id="summernote" class="block cameraBox col11 height243" name="contenido" placeholder="Contenido del mensaje"></textarea>
                            </div>                            
                        </div>
                    </div>
                </div>
            </div>
            <div class="formContainer">
                <button class="loginButton blueButton letterspacing2 font14 pointer" id="saveEmailTemplate-btn">GUARDAR PLANTILLA</button>
                <button type="button" onclick="cerrarModal('modalPlantillaEmail');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
            </div>
        </form>
    </div>
</div>
