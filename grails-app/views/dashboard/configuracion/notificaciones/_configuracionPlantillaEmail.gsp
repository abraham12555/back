<div class="idLightbox hide" id="modalPlantillaEmail">
    <div class="overlay"></div>
    <div class="whiteContainer lightboxPos notificacionesWidth50 darkBluetitle font14 fontWeight400 letterspacing1 justify">
        <form id="formAddEmailTemplate" action="/notificaciones/saveEmailTemplate" method="POST" class="loginForm gray font14">
            <input type="hidden" name="idTemplate" id="idEmailTemplate"/>
            <div class="clearFix width990 autoMargin">
                <center><h1 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">PLANTILLA EMAIL</h1></center>
            </div>
            <div class="col12 col12-mob floatLeft clearFix marginTop25 marginBottom25 paddingBottom20 borderGrayBottom">
                <div class="col12 col12-mob floatLeft" id="statusEmailConfig-div">
                    <div class="col4 col4-mob floatLeft paddingTop15">
                    </div>
                    <div class="col8 col8-mob floatLeft paddingTop15">
                        <div class="marginLeft20">
                            <div id="statusEmail-control"></div>
                        </div>
                    </div>
                    <div class="col4 col4-mob floatLeft">
                        <div class="paddingTop5"></div>
                        <div class="marginLeft20">
                            <span class="font14 latterspacing1 center">Estatus de la solicitud</span>
                        </div>
                    </div>
                    <div class="col8 col8-mob floatLeft">
                        <div class="paddingTop5">
                            <div id="statusEmail-div" class="marginLeft20">

                            </div>
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
                                <textarea id="contenidoEmail" class="block cameraBox col11 height243" name="contenido" placeholder="Contenido del mensaje"></textarea>
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