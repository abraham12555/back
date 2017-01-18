<div class="col12 register">
    <div class="row">
        <div id="first_name_form" class="input-field col12">
            <label for="first_name" class="darkBluetitle">¿Cuál es tu nombre?</label>   
            <input id="first_name" name="nombre" type="text" onkeypress="return event.keyCode != 13;">
            <i class="fa fa-angle-right" style="display: inline-block; font-size: 25px;"></i>
        </div>

        <div id="last_names_form" class="input-field col12 hide">
            <label for="last_names" class="darkBluetitle">¿Cuál es tu apellido paterno?</label>   
            <input id="last_names" name="apellidoPaterno" type="text" onkeypress="return event.keyCode != 13;">
            <i class="fa fa-angle-right" style="display: inline-block; font-size: 25px;"></i>
        </div>

        <div id="email_form" class="input-field col12 hide">
            <label for="email" class="darkBluetitle" >¿A qué correo electrónico enviamos tu confirmación?</label> 
            <input id="email" type="text" name="email" onkeypress="return event.keyCode != 13;">
            <i class="fa fa-angle-right" style="display: inline-block; font-size: 25px;"></i>  
        </div>

        <div id="phone_form" class="input-field col12 hide">
            <label for="phone" class="darkBluetitle">¿Cuál es tu celular con clave lada?</label>   
            <input id="phone" type="text" name="telefonoCelular" class="validate" data-mask="99-99-99-99-99" onkeypress="return event.keyCode != 13;">
            <i class="fa fa-angle-right" style="display: inline-block; font-size: 25px;"></i>
            <p style="margin-top: 10px;" id="leyendaTel"><small class="darkBluetitle">* Enviaremos un código de confirmación al número ingresado.</small></p>
        </div>
        <div id="codigo_form" class="input-field col12 hide">
            <label for="codigo" class="darkBluetitle">Ingresa el código que acabas de recibir por SMS</label>  
            <input id="codigo" type="text" name="codigoConfirmacion" class="validate" onkeypress="return event.keyCode != 13;">
            <i class="fa fa-angle-right" style="display: inline-block; font-size: 25px;"></i> 
            <p style="margin-top: 10px;" id="leyendaCodigo"></p>
            <p style="margin-top: 10px; text-align: center; display: none;" id="editarTelefono"></p>
        </div>
        <div id="thanks" class="hide">
            <strong>¡Gracias <span id="registeredName" class="darkBluetitle"></span>!</strong>
        </div>
    </div>
</div>
<br/>
<div id="divRegistroCompleto" style="display: none;"></div>