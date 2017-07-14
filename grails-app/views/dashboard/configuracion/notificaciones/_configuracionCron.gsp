<div class="idLightbox hide" id="modalEnvio">
    <div class="overlay"></div>
    <div class="whiteContainer lightboxPos notificacionesWidth50 darkBluetitle font14 fontWeight400 letterspacing1 justify">
        <form id="formAddCron" action="/notificaciones/saveCron" method="POST" class="loginForm gray font14">
            <input type="hidden" name="idCron" id="idCron"/>
            <div class="clearFix width990 autoMargin">
                <center><h1 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">ENVÍO DE NOTIFICACIONES</h1></center>
            </div>
            <div class="col12 col12-mob floatLeft clearFix marginTop25 marginBottom25 paddingBottom20 borderGrayBottom">
                <div class="col12 col12-mob floatLeft">
                    <div class="col4 col4-mob floatLeft paddingTop15">
                    </div>
                    <div class="col8 col8-mob floatLeft paddingTop15">
                        <div class="marginLeft20">
                            <div id="frequency-control"></div>
                        </div>
                    </div>
                    <div class="col4 col4-mob floatLeft">
                        <div class="paddingTop5"></div>
                        <div class="marginLeft20">
                            <span class="font14 latterspacing1 center">Enviar mensajes cada</span>
                        </div>
                    </div>
                    <div class="col8 col8-mob floatLeft">
                        <div class="paddingTop5">
                            <div id="cronOptions-div" class="marginLeft20">

                            </div>
                        </div>
                    </div>
                </div>
                <div class="col12 col12-mob floatLeft">
                    <div class="col12 col12-mob floatLeft paddingTop5">
                        <div class="marginLeft20">
                            <div id="time-control"></div>
                        </div>
                    </div>
                    <div class="col12 col12-mob floatLeft">
                        <div class="marginLeft20">
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
                                <select name="weekDay" id="daysWeek"></select>
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
                        </div>
                    </div>
                </div>
                <div class="col12 col12-mob floatLeft">
                    <div class="col4 col4-mob floatLeft paddingTop15">
                    </div>
                    <div class="col8 col8-mob floatLeft paddingTop15">
                        <div class="marginLeft20">
                            <div id="template-control"></div>
                        </div>
                    </div>
                </div>
                <div class="col12 col12-mob floatLeft hide" id="smsTemplateConfig-div">
                    <div class="col4 col4-mob floatLeft">
                        <div class="paddingTop5"></div>
                        <div class="marginLeft20">
                            <span class="font14 latterspacing1 center">Plantilla SMS</span>
                        </div>
                    </div>
                    <div class="col8 col8-mob floatLeft">
                        <div class="paddingTop5">
                            <div id="smsOptions-div" class="marginLeft20">

                            </div>
                        </div>
                    </div>
                </div>
                <div class="col12 col12-mob floatLeft hide" id="emailTemplateConfig-div">
                    <div class="col4 col4-mob floatLeft">
                        <div class="paddingTop20"></div>
                        <div class="marginLeft20">
                            <span class="font14 latterspacing1 center">Plantillas email</span>
                        </div>
                    </div>
                    <div class="col8 col8-mob floatLeft">
                        <div class="paddingTop20">
                            <div id="emailOptions-div" class="marginLeft20">

                            </div>
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