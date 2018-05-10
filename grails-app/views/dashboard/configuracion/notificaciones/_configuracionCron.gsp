<div class="idLightbox hide" id="seleccionEnvio">
    <div class="overlay"></div>
    <div class="whiteContainer lightboxPos notificacionesWidth50 darkBluetitle font14 fontWeight400 letterspacing1 justify">
        <div class='loginForm gray font14' >  
            <div class="clearFix width990 autoMargin">
                <center><h1 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">ENVÍO DE NOTIFICACIONES DE TIPO</h1></center>
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
                                <input name="tipoDeEnvioEnvio" value="0" type="radio">
                                <span class="marginRight20">FIJO</span>
                                <input name="tipoDeEnvioEnvio" value="1" type="radio">
                                <span class="marginRight20">POR INACTIVIDAD</span>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <div class="formContainer">
                <button class="loginButton blueButton letterspacing2 font14 pointer" id="newCronConfiguration-btn">ACEPTAR</button>
                <button type="button" onclick="cerrarModal('seleccionEnvio');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
            </div>
    </div>
    </div>
</div>
<div class="idLightbox hide" id="modalEnvio">
    <div class="overlay"></div>
    <div class="whiteContainer lightboxPos notificacionesWidth50 darkBluetitle font14 fontWeight400 letterspacing1 justify">
        <g:urlContextAware value="/notificaciones/saveCron" var="urlFormAddCron"/>
        <form id="formAddCron" action="${urlFormAddCron}" method="POST" class="loginForm gray font14">
            <input type="hidden" name="idCron" id="idCron"/>
            <input type="hidden" name="idTipoDeEnvio2" id="idTipoDeEnvio2"/>

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
                            
                                <div id="eachMinute2" class="paddingTop10 hide">

                                    <div class="col12 col12-mob floatLeft " id="minutosDespues-div">
                                        <div class="col4 col4-mob floatLeft">
                                            <div class="paddingTop5"></div>
                                            <div class="marginLeft20">
                                                <span class="font14 latterspacing1 center">Minutos después</span>
                                            </div>
                                        </div>
                                        <div class="col2 col8-mob floatLeft">
                                            <div class="paddingTop5">
                                                <g:select noSelection="['0':'0']" class="formulariOptions gray formValues notEmpty headingColor" id='minutosPrueba'  name="minutosPrueba" from="${1..59}" />

                                            </div>
                                        </div>
                                    </div>
                                    <div class="col12 col12-mob floatLeft " id="horasDespues-div">
                                        <div class="col4 col4-mob floatLeft">
                                            <div class="paddingTop5"></div>
                                            <div class="marginLeft20">
                                                <span class="font14 latterspacing1 center">Horas Después</span>
                                            </div>
                                        </div>
                                        <div class="col2 col8-mob floatLeft">
                                            <div class="paddingTop5">
                                                <g:select  noSelection="['0':'0']" class="formulariOptions gray formValues notEmpty headingColor"  id='horasPrueba' name="horasPrueba" from="${1..24}" />

                                            </div>
                                        </div>
                                    </div>
                                    <div class="col12 col12-mob floatLeft " id="diasDespues-div">
                                        <div class="col4 col4-mob floatLeft">
                                            <div class="paddingTop5"></div>
                                            <div class="marginLeft20">
                                                <span class="font14 latterspacing1 center">Días Después</span>
                                            </div>
                                        </div>
                                        <div class="col2 col8-mob floatLeft">
                                            <div class="paddingTop5">
                                                <g:select  noSelection="['0':'0']" class="formulariOptions gray formValues notEmpty headingColor"   id ='diasPrueba' name="diasPrueba" from="${1..30}" />

                                            </div>
                                        </div>
                                    </div>

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
                        <div class="paddingTop5">
                        </div>
                        <div class="marginLeft20">
                            <span class="font14 latterspacing1 center">Plantilla SMS</span>
                        </div>
                    </div>
                    <div class="col6 col8-mob floatLeft">
                        <div class="paddingTop5">
                              <select  id='myselect' class="js-example-basic-multiple"   name="templateOptions[]" multiple="multiple">
                              </select> 
<!--                           <div id="smsOptions-div" class="marginLeft20">

                            </div>-->
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
                    <div class="col6 col8-mob floatLeft">
                        <div class="paddingTop20">
                            <select id="myselect2" class="js-example-basic-multiple2"   name="templateOptions[]" multiple="multiple">
                            </select> 
                        </div>
                    </div>
                </div>
                             <div class="col12 col12-mob floatLeft">
                    <div class="col4 col4-mob floatLeft paddingTop15">
                    </div>
                    <div class="col8 col8-mob floatLeft paddingTop15">
                        <div class="marginLeft20">
                            <div id="plantilla-control"></div>
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

