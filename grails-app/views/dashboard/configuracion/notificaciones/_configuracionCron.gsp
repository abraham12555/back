<div class="idLightbox hide" id="modalEnvio">
    <div class="overlay"></div>
    <div class="whiteContainer lightboxPos notificacionesWidth50 darkBluetitle font14 fontWeight400 letterspacing1 justify">
        <form id="formAddCron" action="/notificaciones/saveCron" method="POST" class="loginForm gray font14">
            <input type="hidden" name="idNotificacionCron" id="idNotificacionCron"/>
            <div class="clearFix width990 autoMargin">
                <center><h1 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">ENVÍO DE MENSAJES</h1></center>
            </div>
            <div class="col12 col12-mob floatLeft clearFix marginTop25 marginBottom25 paddingBottom20 borderGrayBottom">
                <div class="col12 floatLeft">
                    <div class="register">
                        <div class="floatLeft clearFix marginTop25 marginBottom25 paddingBottom20 marginRight20 marginLeft25">
                            <span class="marginRight25">Enviar mensajes cada</span>
                            <g:radioGroup name="cronOptions" values="${listCronOptions}" labels="${listCronOptions}" id="cronOptions">
                                ${it.radio} <span class="marginRight20">${it.label}</span>
                            </g:radioGroup>
                            <div id="eachHour" class="paddingTop10 hide">
                                <label>A las </label>
                                <input type="text" id="hours-cron" name="hour" class="inputs lightGray letterspacing1 font14 only-number" maxlength="2"> horas
                            </div>
                            <div id="eachDay" class="paddingTop10 hide">
                                <label>A las </label>
                                <input type="text" class="day-cron inputs lightGray letterspacing1 font14" id="dayTime" name="dayTime"/> horas
                            </div>
                            <div id="eachWeek" class="paddingTop10 hide">
                                <label>El día </label>
                                <g:select from="${listweekDayOptions.entrySet()}" name="weekDay" optionKey="key" optionValue="value" id="daysWeek"></g:select>
                                    <label> a las </label>
                                    <input type="text" class="day-cron inputs lightGray letterspacing1 font14" id="weekTime" name="weekTime" /> horas
                            </div>
                            <div id="eachMonth" class="paddingTop10 hide">
                                <label>El día </label>
                                <input type="text" id="datepickerCron" name="dayMonth" class="inputs lightGray letterspacing1 font14 only-number" maxlength="2"/>
                                <input type="hidden" id="datepickerAux"/>
                                <label> a las </label>
                                <input type="text" class="day-cron inputs lightGray letterspacing1 font14" id="monthTime" name="monthTime"/> horas
                            </div>
                            <p style="margin-top: 10px;" id="leyendaCron"></p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="formContainer">
                <button class="loginButton blueButton letterspacing2 font14 pointer" id="saveCron-btn">GUARDAR</button>
                <button type="button" onclick="cerrarModal('modalEnvio');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
            </div>
        </form>
    </div>
</div>