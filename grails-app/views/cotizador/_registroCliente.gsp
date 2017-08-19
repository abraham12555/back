<div class="col12 register">
    <div class="row">
        <div id="first_name_form" class="input-field col12">
            <label for="first_name" class="darkBluetitle">¿Cuál es tu nombre? <small class="darkBluetitle">(Como aparece en tu INE)</small></label>
            <input id="first_name" name="nombre" type="text" maxlength="80" onkeypress="return event.keyCode != 13;">
            <p class="sigPaso" style="margin-top: 10px; text-align: center;">
                <span class='goBtn'>Siguiente</span>
            </p>
            <p style="margin-top: 10px;" id="leyendaNombre"></p>
        </div>

        <div id="email_form" class="input-field col12 hide">
            <label for="email" class="darkBluetitle" >¿A qué correo electrónico enviamos tu confirmación?</label>
            <input id="email" type="text" name="email" maxlength="60" onkeypress="return event.keyCode != 13;">
            <div style="text-align: center;">
                <p class="antPaso" style="display: inline-block;margin-top: 10px; text-align: center;">
                    <span class="backBtn">Anterior</span>
                </p>
                <p class="sigPaso" style="display: inline-block;margin-top: 10px; text-align: center;">
                    <span class="goBtn">Siguiente</span>
                </p>
            </div>
            <p style="margin-top: 10px;" id="leyendaEmail"></p>
        </div>

        <div id="phone_form" class="input-field col12 hide">
            <label for="phone" class="darkBluetitle">¿Cuál es tu celular con clave lada?</label>
            <input id="phone" type="tel" name="telefonoCelular" class="validate"  maxlength="14" >
            <div style="text-align: center;">
                <p class="antPaso" style="display: inline-block;margin-top: 10px; text-align: center;">
                    <span class="backBtn">Anterior</span>
                </p>
                <p class="sigPaso" style="display: inline-block;margin-top: 10px; text-align: center;">
                    <span class="goBtn">Siguiente</span>
                </p>
            </div>
            <p style="margin-top: 10px;" id="leyendaTel"><small class="darkBluetitle">* Enviaremos un código de confirmación al número ingresado.</small></p>
        </div>
        <div id="codigo_form" class="input-field col12 hide">
            <label for="codigo" class="darkBluetitle">Ingresa el código que acabas de recibir por SMS</label>
            <input id="codigo" type="text" name="codigoConfirmacion" class="validate" maxlength="5" onkeypress="return event.keyCode != 13;">
            <div style="text-align: center;">
                <p style="display: inline-block;margin-top: 10px; text-align: center;" id="editarTelefono"></p>
                <p class="sigPaso" style="display: inline-block;margin-top: 10px; text-align: center;">
                    <span class='goBtn'>Siguiente</span>
                </p>
            </div>
            <p style="margin-top: 10px;" id="leyendaCodigo"><small style='color: #25a3ff;'><strong>Espera por favor entre 15 y 30 segundos para recibir tu código..</strong></small></p>
        </div>
        <div id="resume_form" class="input-field col12 hide">
            <label for="codigo" class="darkBluetitle">Hemos notado que ya tienes una solicitud en proceso, da click en el siguiente botón para complementarla</label>
            <div style="text-align: center;">
                <p style="display: inline-block;margin-top: 10px; text-align: center;" id="resumirSolicitud"></p>
            </div>
        </div>
        <div id="many_form" class="input-field col12 hide">
            <label for="codigo" class="darkBluetitle">Hemos notado que existen varias solicitudes en proceso con el número que nos proporcionaste</label>
            <div style="text-align: center;">
                <p style="display: inline-block;margin-top: 10px; text-align: center;" id="resumirSolicitud"></p>
            </div>
        </div>
        <div id="thanks" class="hide">
            <strong>¡Gracias <span id="registeredName" class="darkBluetitle"></span>!</strong>
        </div>

    </div>
    <div id="termsPriv" style="width: 90%; margin:auto; padding-bottom: 15px;">
        <p class="darkBluetitle font14 fontWeight400 letterspacing1 justify">
            Al <strong>registrar tus datos y comenzar la solicitud</strong> aceptas los <a class="pointer" onclick="openModal('modalTerminosCondiciones');" style="text-decoration: underline;">Términos y Condiciones
                y Aviso de Privacidad</a>
        </p>
    </div>
</div>
<br/>
<div id="divRegistroCompleto" style="display: none;"></div>
