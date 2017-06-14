<section class="container marginBottom50 ">
    <table class="applicationContainers notificaciones_table width990 autoMargin">
        <thead>
        <th class="navyBg left"><h1 class="graphHeading colorWhite letterspacing2 textUpper">envío de mensajes</h1></th>
        </thead>
        <tr class="lightGrayBG">
        </tr>
        <tr>
            <td class="center">
                <div class="col12 col12-mob floatLeft clearFix marginTop25 marginBottom25" style="display:<g:if test="${notificacion != null && notificacion.cron == null}">block</g:if><g:else>none</g:else>" id="newCron">
                        <button class="blueButton colorWhite textUpper width200 center radius4 paddingTop10 paddingBottom10" type="button" title="Configuración envío" id="newCronConfiguration-btn">configurar envío de SMS</button>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                        <div class="col8 col8-mob floatLeft marginTop5 marginBottom5 paddingTop5" style="display:<g:if test="${notificacion == null || (notificacion != null && notificacion.cron == null)}">none</g:if><g:else>block</g:else>" id="cronContent">
                        <span class="gray left font14" id="cronMessage">
                        ${cronConfigurationMessage}
                    </span>
                </div>
                <div class="col2 col2-mob floatLeft marginBottom5" style="text-align:right; display:<g:if test="${notificacion == null || (notificacion != null && notificacion.cron == null)}">none</g:if><g:else>block</g:else>" id="editCron-div">
                        <button class="greenBox colorWhite textUpper" type="button" title="Editar envío" id="editCron-btn">editar</button>
                    </div>
                        <div class="col2 col2-mob floatLeft marginBottom5" style="text-align:right; display:<g:if test="${notificacion == null || (notificacion != null && notificacion.cron == null)}">none</g:if><g:else>block</g:else>" id="deleteCron-div">
                        <button class="greenBox colorWhite textUpper" type="button" title="Eliminar envío" id="deleteCron-btn">eliminar</button>
                    </div>
                </td>
            </tr>
        <tr id="emptyCronContent">
            <td class="gray left font14" id="emptyCronContent-td">
                <g:if test="${notificacion == null}">
                    <strong>No se ha configurado ninguna plantilla para el envío de mensajes</strong>
                </g:if>
            </td>
        </tr>
    </table>
</section>
<g:render template="configuracion/notificaciones/configuracionCron"/>