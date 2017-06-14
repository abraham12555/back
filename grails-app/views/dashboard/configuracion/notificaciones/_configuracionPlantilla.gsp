<div class="idLightbox hide" id="modalPlantillaSms">
    <div class="overlay"></div>
    <div class="whiteContainer lightboxPos notificacionesWidth50 darkBluetitle font14 fontWeight400 letterspacing1 justify">
        <form id="formAddTemplate" action="/notificaciones/saveTemplate" method="POST" class="loginForm gray font14">
            <input type="hidden" name="idNotificacion" id="idNotificacion"/>
            <div class="clearFix width990 autoMargin">
                <center><h1 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">PLANTILLA SMS</h1></center>
            </div>
            <div class="col12 col12-mob floatLeft clearFix marginTop25 marginBottom25 paddingBottom20 borderGrayBottom">
                <div class="col4 col4-mob floatLeft">
                    <div class="paddingTop20"></div>
                    <div class="dragitems">
                        <h3>
                            <span class="font14 latterspacing1 center ">Datos disponibles</span>
                        </h3>
                        <ul id="availableFields" runat="server">
                        </ul>
                    </div>
                </div>
                <div class="col8 col8-mob floatLeft">
                    <div class="paddingTop20">
                        <div class="register">
                            <div class="dropitems">
                                <textarea id="contenidoSms" class="block cameraBox col11 height243" name="contenidoSms" placeholder="Contenido del mensaje SMS"></textarea>
                                <p style="margin-top: 10px;" id="leyendaContenidoSms"></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="formContainer">
                <button class="loginButton blueButton letterspacing2 font14 pointer" id="saveTemplate-btn">GUARDAR PLANTILLA</button>
                <button type="button" onclick="cerrarModal('modalPlantillaSms');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
            </div>
        </form>
    </div>
</div>