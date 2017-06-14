<section class="container marginBottom50 ">
    <input type="hidden" id="idConfiguracionNotificacion" value="<g:if test="${notificacion == null}">0</g:if><g:else>${notificacion.id}</g:else>"/>
        <table class="applicationContainers notificaciones_table width990 autoMargin">
            <thead>
            <th class="navyBg left"><h1 class="graphHeading colorWhite letterspacing2 textUpper">plantilla sms</h1></th>
            </thead>
            <tr class="lightGrayBG">
            </tr>
            <tr>
                <td class="center">
                        <div class="col12 col12-mob floatLeft clearFix marginTop25 marginBottom25" style="display:<g:if test="${notificacion == null}">block</g:if><g:else>none</g:else>" id="newTemplate">
                        <button class="blueButton colorWhite textUpper width160 center radius4 paddingTop10 paddingBottom10" type="button" title="Nueva plantilla" id="newTemplate-btn">nueva plantilla</button>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                        <div class="col8 col8-mob floatLeft marginTop5 marginBottom5 paddingTop5" style="display:<g:if test="${notificacion == null}">none</g:if><g:else>block</g:else>" id="contentTemplate">
                        <span class="gray left font14" id="templateSms">
                        <g:if test="${notificacion != null}">${notificacion.contenidoSms}</g:if>
                        </span>
                    </div>
                    <div class="col2 col2-mob floatLeft marginBottom5" style="text-align:right; display:<g:if test="${notificacion == null}">none</g:if><g:else>block</g:else>" id="editTemplate-div">
                        <button class="greenBox colorWhite textUpper" type="button" title="Editar plantilla" id="editTemplate-btn">editar</button></div>
                        <div class="col2 col2-mob floatLeft marginBottom5" style="text-align:right; display:<g:if test="${notificacion == null}">none</g:if><g:else>block</g:else>" id="deleteTemplate-div">
                        <button class="greenBox colorWhite textUpper" type="button" title="Eliminar plantilla" id="deleteTemplate-btn">eliminar</button></div>
                </td>
            </tr>
        </table>
    </section>
<g:render template="configuracion/notificaciones/configuracionPlantilla"/>