<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta name="layout" content="perfilador"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sample title</title>
    </head>
    <body>
        <input type="hidden" id="opcionMenu" value="3">
        <% def anioActualParcial = Calendar.getInstance().get(Calendar.YEAR) %>
        <section>
            <div class="container">
                <div class="section form">
                    <div class="col12 paddingTop25">
                        <div class="col3 floatLeft">

                            <ul id="navbar-steps" class="progress-indicator stepped stacked">
                                <li class="step1 active">

                                    <span class="bubble"><span>1</span><i class="fa fa-check"></i></span>
                                    <span class="stacked-text">
                                        <span class="step active">Cliente</span>
                                        <span id="menuComprobante" class="substep active">Comprobante de ingresos</em></span>
                                    </span>

                                </li>
                                <li class="step2">
                                    <span class="bubble"><span>2</span><i class="fa fa-check"></i></span>
                                    <span class="stacked-text">
                                        <span class="step">Generales</span>
                                        <span id="menuPersonales" class="substep">Personales</span>
                                        <span id="menuVivienda" class="substep">Vivienda</span>
                                        <span id="menuFamilia" class="substep">Familia</span>
                                        <span id="menuEmpleo" class="substep">Empleo</span>
                                    </span>
                                </li>
                                <li class="step3">
                                    <span class="bubble"><span>3</span><i class="fa fa-check"></i></span>
                                    <span class="stacked-text">
                                        <span class="step" id='menuBuroDeCredito'>Consulta a Buró de Crédito</span>
                                    </span>
                                </li>
                                <li class="step4">
                                    <span class="bubble"><span>4</span><i class="fa fa-check"></i></span>
                                    <span class="stacked-text">
                                        <span class="step">Ofertas</span>
                                    </span>
                                </li>
                            </ul>
                        </div>
                        <div class="col9 floatLeft stepsContainer">
                            <div id='tabs'class="tab hide col9 floatLeft stepsContainer marginBottom28">
                                <button id='buttonconsultaAutenticador'class="tablinks consultaAutenticador blueButton" onclick="openTab('consultaAutenticador','consultaINTL')" >CONSULTA AUTENTICADOR</button>
                                <button id='buttonconsultaINTL'class="tablinks consultaINTL " onclick="openTab('consultaINTL','consultaAutenticador')">CONSULTA TRADICIONAL</button>
                            </div>                            
                            <form id="perfiladorForm">
                                <div id="step1" class="col12 floatLeft stepContent animated fadeInRight">
                                    <h2>Perfilador del cliente</h2>
                                    <div class="perfilador padding20">
                                        <div class="col6 floatLeft">
                                            <p class="correctaBoxLabel">¿Cliente ha tenido Créditos con Libertad?</p>
                                            <div id="cliente_si" class="correctaBox floatLeft">
                                                <p class="center paddingTop15 paddingBottom15 lightGray">SI</p>
                                            </div>
                                            <div id="cliente_no" class="floatLeft correctaBox">
                                                <p class="center paddingTop15 paddingBottom15 lightGray">NO</p>
                                            </div>
                                            <input type="hidden" class="datoPerfilador" name="cliente_clienteExistente" id="clienteExistente">
                                        </div>
                                        <div id="inputNoCliente" class="hide col6 floatLeft">
                                            <p class="gray font14 letterspacing1.1">R.F.C. DEL CLIENTE</p>
                                            <input class="datoPerfilador inPuts4a marginTop15 headingColor requerido" style="width: 50%;" type="text" name="rfcClienteExistente" id="rfcClienteExistente" />
                                        </div>
                                        <div id="selectTipoComprobante" class="col12 floatLeft paddingTop20">
                                            <select name="cliente_tipoDeDocumento" id="tipoDeDocumento" class="datoPerfilador js-example-basic-single col6 requerido">
                                                <option></option>
                                                <optgroup label="Formales">
                                                    <option value="3">Estado de cuenta</option>
                                                    <option value="4">Recibo de Nómina</option>
                                                    <option value="6">Recibo de Honorarios</option>
                                                </optgroup>
                                                <optgroup label="Informales">
                                                    <option value="8">Ticket</option>
                                                    <option value="7">Nota</option>
                                                </optgroup>
                                            </select>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="col12 floatLeft paddingTop20">
                                        <button class="greenBox colorWhite hide" type="button" onclick="goStep2()">Siguiente</button>
                                    </div>
                                </div>
                                <div id="step2" class="animated hide col12 floatLeft stepContent fadeInRight">
                                    <h2>Datos personales</h2>
                                    <div style="color: #005398;"><span><strong style="color:#fb5e48;">*</strong></span> Obligatorio </div>
                                    <!--<div id="errorNombre" class="error">Los nombres y apellidos deben contener letras únicamente.</div>
                                    <div id="errorEmail" class="error">La dirección de correo es incorrecta, verifica la estructura. Ej. ejemplo@mail.com</div>-->
                                    <div class="col6 floatLeft">
                                        <div class="mat-div">
                                            <label for="nombre" class="mat-label"><span><strong style="color:#fb5e48;">*</strong></span> Nombre</label>
                                            <input type="text" class="datoPerfilador mat-input requerido validarNombre" id="cliente_nombre" name="cliente_nombre" maxlength="26">
                                        </div>
                                    </div>
                                    <div class="col6 floatLeft">
                                        <div class="mat-div">
                                            <label for="segundo-nombre" class="mat-label">Segundo Nombre</label>
                                            <input type="text" class="datoPerfilador mat-input validarNombre" id="cliente_segundoNombre" name="cliente_segundoNombre">
                                        </div>
                                    </div>
                                    <div class="col6 floatLeft">
                                        <div class="mat-div">
                                            <label for="apellidoPaterno" class="mat-label"><span><strong style="color:#fb5e48;">*</strong></span> Apellido paterno</label>
                                            <input type="text" class="datoPerfilador mat-input requerido validarNombre" id="cliente_apellidoPaterno" name="cliente_apellidoPaterno" maxlength="26">
                                        </div>
                                    </div>
                                    <div class="col6 floatLeft">
                                        <div class="mat-div">
                                            <label for="apellidoMaterno" class="mat-label"><span><strong style="color:#fb5e48;">*</strong></span> Apellido materno</label>
                                            <input type="text" class="datoPerfilador mat-input requerido validarNombre" id="cliente_apellidoMaterno" name="cliente_apellidoMaterno" maxlength="26">
                                        </div>
                                    </div>
                                    <div class="col6 floatLeft">
                                        <div class="mat-div">
                                            <label for="celular" class="mat-label"><span><strong style="color:#fb5e48;">*</strong></span> Teléfono celular</label>
                                            <input type="phone" class="datoPerfilador mat-input requerido" id="telefonoCliente_telefonoCelular" name="telefonoCliente_telefonoCelular" data-mask="99-99-99-99-99" maxlength="14">
                                        </div>
                                    </div>
                                    <div class="col6 floatLeft">
                                        <div class="mat-div">
                                            <label for="code" class="mat-label"><span><strong style="color:#fb5e48;">*</strong></span> Código de Verificación</label>
                                            <input type="text" class="datoPerfilador mat-input requerido" id="code" maxlength="5">
                                        </div>
                                    </div>
                                    <div class="col6 floatLeft">
                                        <div class="mat-div">
                                            <label for="tel-fijo" class="mat-label">Teléfono Fijo</label>
                                            <input type="text" class="datoPerfilador mat-input" id="telefonoCliente_telefonoCasa" name="telefonoCliente_telefonoCasa" data-mask="99-99-99-99-99" maxlength="14">
                                        </div>
                                    </div>
                                    <div class="col6 floatLeft">
                                        <div class="mat-div">
                                            <label for="mail" class="mat-label"><span><strong style="color:#fb5e48;">*</strong></span> Correo Electrónico</label>
                                            <input type="text" style='box-shadow:none;' class="datoPerfilador mat-input requerido validarEmail" id="emailCliente_emailPersonal" name="emailCliente_emailPersonal">
                                        </div>
                                    </div>
                                    <div class="col6 floatLeft">
                                        <span class="select-label multi"><span><strong style="color:#fb5e48;">*</strong></span> Sexo</span>
                                        <div class="col12 floatLeft">
                                            <div class="mdl-selectfield">
                                                <g:select id="cliente_genero" name="cliente_genero" class="datoPerfilador browser-default requerido" optionKey="id" optionValue="nombre" from="${la.kosmos.app.Genero.list()}" noSelection="['':'Sexo']" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col6 floatLeft">
                                        <span class="select-label multi"><span><strong style="color:#fb5e48;">*</strong></span> Fecha de nacimiento</span>
                                        <div class="col2 floatLeft">
                                            <div class="mdl-selectfield">
                                                <label>Día</label>
                                                <g:select id="cliente_fechaDeNacimiento_dia" name="cliente_fechaDeNacimiento_dia" class="datoPerfilador browser-default requerido validarEdad" from="${1..31}" noSelection="['':'Día']"/>
                                            </div>
                                        </div>
                                        <div class="col6 floatLeft">
                                            <div class="mdl-selectfield">
                                                <label>Mes</label>
                                                <g:select id="cliente_fechaDeNacimiento_mes" name="cliente_fechaDeNacimiento_mes" class="datoPerfilador browser-default requerido validarEdad" from="${[[id: 1, mes: 'Enero'],[id: 2, mes: 'Febrero'],[id: 3, mes: 'Marzo'],[id: 4, mes: 'Abril'],[id: 5, mes: 'Mayo'],[id: 6, mes: 'Junio'],[id: 7, mes: 'Julio'],[id: 8, mes: 'Agosto'],[id: 9, mes: 'Septiembre'],[id: 10, mes: 'Octubre'],[id: 11, mes: 'Noviembre'],[id: 12, mes: 'Diciembre']]}" optionKey="id" optionValue="mes" noSelection="['':'Mes']" />
                                            </div>
                                        </div>
                                        <div class="col4 floatLeft">
                                            <div class="mdl-selectfield">
                                                <label>Año</label>
                                                <g:select id="cliente_fechaDeNacimiento_anio" name="cliente_fechaDeNacimiento_anio" class="datoPerfilador browser-default requerido validarEdad" from="${(anioActualParcial-18)..(anioActualParcial-68)}" noSelection="['':'Año']" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col6 floatLeft">
                                        <span class="select-label multi"><span><strong style="color:#fb5e48;">*</strong></span> Lugar de nacimiento</span>
                                        <div class="col12 floatLeft">
                                            <div class="mdl-selectfield">
                                                <label>Lugar de nacimiento</label>
                                                <g:select id="cliente_lugarDeNacimiento" name="cliente_lugarDeNacimiento" class="datoPerfilador browser-default requerido" optionKey="id" optionValue="nombre" from="${la.kosmos.app.Estado.list()}" noSelection="['':'Estado']" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col6 floatLeft">
                                        <div class="mat-div">
                                            <label for="rfc" class="mat-label"><span><strong style="color:#fb5e48;">*</strong></span> R.F.C.</label>
                                            <input type="text" class="datoPerfilador mat-input requerido" id="cliente_rfc" name="cliente_rfc">
                                        </div>
                                    </div>
                                    <div class="col6 floatLeft">
                                        <div class="mat-div">
                                            <label for="curp" class="mat-label"><span><strong style="color:#fb5e48;">*</strong></span> C.U.R.P.</label>
                                            <input type="text" class="datoPerfilador mat-input requerido" id="cliente_curp" name="cliente_curp">
                                        </div>
                                    </div>
                                    <div class="col6 floatLeft">
                                        <span class="select-label multi"><span><strong style="color:#fb5e48;">*</strong></span> Nacionalidad</span>
                                        <div class="col12 floatLeft">
                                            <div class="mdl-selectfield">
                                                <label>Nacionalidad</label>
                                                <g:select id="cliente_nacionalidad" name="cliente_nacionalidad" class="datoPerfilador browser-default requerido" optionKey="id" optionValue="nombre" from="${la.kosmos.app.Nacionalidad.list()}" noSelection="['':'Nacionalidad']" />
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col12 floatLeft paddingTop20">
                                        <button class="redBox colorWhite" type="button" onclick="goBackStep1()">Anterior</button>
                                        <button class="greenBox colorWhite hide" type="button" onclick="goStep3()">Siguiente</button>
                                    </div>
                                </div>
                                <div id="step3" class="hide col12 floatLeft stepContent animated fadeInRight">
                                    <h2>Vivienda</h2>
                                    <div style="color: #005398;"><span><strong style="color:#fb5e48;">*</strong></span> Obligatorio </div>
                                    <div class="col8 floatLeft">
                                        <div class="mat-div">
                                            <label for="calle" class="mat-label"><span><strong style="color:#fb5e48;">*</strong></span> Calle</label>
                                            <input type="text" class="datoPerfilador mat-input requerido" id="direccionCliente_calle" name="direccionCliente_calle">
                                        </div>
                                    </div>
                                    <div class="col2 floatLeft">
                                        <div class="mat-div">
                                            <label for="no-exterior" class="mat-label"><span><strong style="color:#fb5e48;">*</strong></span> No. Exterior</label>
                                            <input type="text" class="datoPerfilador mat-input requerido" id="direccionCliente_numeroExterior" name="direccionCliente_numeroExterior">
                                        </div>
                                    </div>
                                    <div class="col2 floatLeft">
                                        <div class="mat-div">
                                            <label for="no-interior" class="mat-label">No. Interior</label>
                                            <input type="text" class="datoPerfilador mat-input" id="direccionCliente_numeroInterior" name="direccionCliente_numeroInterior">
                                        </div>
                                    </div>
                                    <div class="col3 floatLeft">
                                        <div class="mat-div">
                                            <label for="cp" class="mat-label"><span><strong style="color:#fb5e48;">*</strong></span> Código postal</label>
                                            <input type="text" class="datoPerfilador mat-input requerido" id="direccionCliente_codigoPostal" name="direccionCliente_codigoPostal" maxlength="5">
                                        </div>
                                    </div>
                                    <div class="col5 floatLeft">
                                        <span class="select-label multi"><span><strong style="color:#fb5e48;">*</strong></span> Colonia</span>
                                        <div class="col12 floatLeft">
                                            <div class="mdl-selectfield">
                                                <label>Colonia</label>
                                                <select id="direccionCliente_colonia" name="direccionCliente_colonia" class="datoPerfilador browser-default requerido">
                                                    <option value="" disabled selected>Colonia</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col4 floatLeft">
                                        <span class="select-label multi"><span><strong style="color:#fb5e48;">*</strong></span> Municipio/Delegación</span>
                                        <div class="col12 floatLeft">
                                            <div class="mdl-selectfield">
                                                <label>Municipio/Delegación</label>
                                                <select id="direccionCliente_delegacion" name="direccionCliente_delegacion" class="datoPerfilador browser-default requerido">
                                                    <option value="" disabled selected>Municipio/Delegación</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col6 floatLeft">
                                        <span class="select-label multi"><span><strong style="color:#fb5e48;">*</strong></span> Estado</span>
                                        <div class="col12 floatLeft">
                                            <div class="mdl-selectfield">
                                                <label>Estado</label>
                                                <select id="direccionCliente_estado" name="direccionCliente_estado" class="datoPerfilador browser-default requerido">
                                                    <option value="" disabled selected>Estado</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col6 floatLeft">
                                        <span class="select-label multi"><span><strong style="color:#fb5e48;">*</strong></span> Tipo de Vivienda</span>
                                        <div class="col12 floatLeft">
                                            <div class="mdl-selectfield">
                                                <label>Tipo de vivienda</label>
                                                <g:select id="direccionCliente_tipoDeVivienda" name="direccionCliente_tipoDeVivienda" class="datoPerfilador browser-default requerido" optionKey="id" optionValue="nombre" from="${la.kosmos.app.TipoDeVivienda.list()}" noSelection="['':'Tipo de Vivienda']" />
                                            </div>
                                        </div>
                                    </div>
                                    <div id="divMontoDeLaRenta" class="col6 floatLeft" style="display:none;">
                                        <div class="mat-div">
                                            <label for="renta" class="mat-label"><span><strong style="color:#fb5e48;">*</strong></span> Monto de Renta</label>
                                            <input type="text" class="datoPerfilador mat-input hide requerido" id="direccionCliente_montoDeLaRenta_autoNumeric" >
                                        </div>
                                    </div>
                                    <div id="divMontoDeLaHipoteca" class="col6 floatLeft"  style="display:none;">
                                        <div class="mat-div">
                                            <label for="hipoteca" class="mat-label"><span><strong style="color:#fb5e48;">*</strong></span> Monto de la Hipoteca</label>
                                            <input type="text" class="datoPerfilador mat-input hide requerido" id="direccionCliente_montoDeLaHipoteca_autoNumeric" >
                                        </div>
                                    </div>
                                    <div class="col6 floatLeft">
                                        <span class="select-label multi"><span><strong style="color:#fb5e48;">*</strong></span> Antigüedad en la Vivienda</span>
                                        <div class="col7 floatLeft">
                                            <div class="mdl-selectfield">
                                                <label>Mes</label>
                                                <g:select id="direccionCliente_tiempoDeVivir_mes" name="direccionCliente_tiempoDeVivir_mes" class="datoPerfilador browser-default requerido validarFecha" from="${[[id: 1, mes: 'Enero'],[id: 2, mes: 'Febrero'],[id: 3, mes: 'Marzo'],[id: 4, mes: 'Abril'],[id: 5, mes: 'Mayo'],[id: 6, mes: 'Junio'],[id: 7, mes: 'Julio'],[id: 8, mes: 'Agosto'],[id: 9, mes: 'Septiembre'],[id: 10, mes: 'Octubre'],[id: 11, mes: 'Noviembre'],[id: 12, mes: 'Diciembre']]}" optionKey="id" optionValue="mes" noSelection="['':'Mes']"/>
                                            </div>
                                        </div>
                                        <div class="col5 floatLeft">
                                            <div class="mdl-selectfield">
                                                <label>Año</label>
                                                <g:select id="direccionCliente_tiempoDeVivir_anio" name="direccionCliente_tiempoDeVivir_anio" class="datoPerfilador browser-default requerido validarFecha" from="${(anioActualParcial)..(anioActualParcial-68)}" noSelection="['':'Año']" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col6 floatLeft">
                                        <span class="select-label multi"><span><strong style="color:#fb5e48;">*</strong></span> Antigüedad en la Ciudad</span>
                                        <div class="col7 floatLeft">
                                            <div class="mdl-selectfield">
                                                <label>Mes</label>
                                                <g:select id="direccionCliente_tiempoDeResidencia_mes" name="direccionCliente_tiempoDeResidencia_mes" class="datoPerfilador browser-default requerido validarFecha" from="${[[id: 1, mes: 'Enero'],[id: 2, mes: 'Febrero'],[id: 3, mes: 'Marzo'],[id: 4, mes: 'Abril'],[id: 5, mes: 'Mayo'],[id: 6, mes: 'Junio'],[id: 7, mes: 'Julio'],[id: 8, mes: 'Agosto'],[id: 9, mes: 'Septiembre'],[id: 10, mes: 'Octubre'],[id: 11, mes: 'Noviembre'],[id: 12, mes: 'Diciembre']]}" optionKey="id" optionValue="mes" noSelection="['':'Mes']"/>
                                            </div>
                                        </div>
                                        <div class="col5 floatLeft">
                                            <div class="mdl-selectfield">
                                                <label>Año</label>
                                                <g:select id="direccionCliente_tiempoDeResidencia_anio" name="direccionCliente_tiempoDeResidencia_anio" class="datoPerfilador browser-default requerido validarFecha" from="${(anioActualParcial)..(anioActualParcial-68)}" noSelection="['':'Año']" />
                                            </div>
                                        </div>
                                    </div>
                                    <input type="hidden" class="datoPerfilador" name="direccionCliente_montoDeLaHipoteca" id="direccionCliente_montoDeLaHipoteca">
                                    <input type="hidden" class="datoPerfilador" name="direccionCliente_montoDeLaRenta" id="direccionCliente_montoDeLaRenta" >
                                    <div class="col12 floatLeft paddingTop20">
                                        <button class="redBox colorWhite" type="button" onclick="goBackStep2()">Anterior</button>
                                        <button class="greenBox colorWhite hide" type="button" onclick="goStep4()">Siguiente</button>
                                    </div>
                                </div>
                                <div id="step4" class="hide col12 floatLeft stepContent animated fadeInRight">
                                    <h2>Datos Familiares</h2>
                                    <div style="color: #005398;"><span><strong style="color:#fb5e48;">*</strong></span> Obligatorio </div>
                                    <div class="col4 floatLeft">
                                        <span class="select-label multi"><span><strong style="color:#fb5e48;">*</strong></span> Dependientes Económicos</span>
                                        <div class="col12 floatLeft">
                                            <div class="mdl-selectfield">
                                                <label>Dependientes Económicos</label>
                                                <g:select id="cliente_dependientesEconomicos" name="cliente_dependientesEconomicos" class="datoPerfilador browser-default requerido" from="${0..20}" noSelection="['':'Cantidad']"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col4 floatLeft">
                                        <span class="select-label multi"><span><strong style="color:#fb5e48;">*</strong></span> Estado Civil</span>
                                        <div class="col12 floatLeft">
                                            <div class="mdl-selectfield">
                                                <label>Estado civil</label>
                                                <g:select id="cliente_estadoCivil" name="cliente_estadoCivil" class="datoPerfilador browser-default requerido" optionKey="id" optionValue="nombre" from="${la.kosmos.app.EstadoCivil.list()}" noSelection="['':'Estado Civil']" />
                                            </div>
                                        </div>
                                    </div>
                                    <div id="divRegimenMatrimonial" class="col4 floatLeft" style="display: none;">
                                        <span class="select-label multi"><span><strong style="color:#fb5e48;">*</strong></span> Régimen de Bienes</span>
                                        <div class="col12 floatLeft">
                                            <div class="mdl-selectfield">
                                                <label>Régimen de bienes</label>
                                                <g:select id="cliente_regimenMatrimonial" name="cliente_regimenMatrimonial" class="datoPerfilador browser-default requerido" optionKey="id" optionValue="nombre" from="${la.kosmos.app.RegimenMatrimonial.list()}" noSelection="['':'Regimen Matrimonial']" />
                                            </div>
                                        </div>
                                    </div>
                                    <div id="divNombreDelConyugue" class="col6 floatLeft" style="display: none;">
                                        <div class="mat-div">
                                            <label for="nombre-conyuge" class="mat-label"><span><strong style="color:#fb5e48;">*</strong></span> Nombre del conyugue</label>
                                            <input type="text" class="datoPerfilador mat-input requerido validarNombre" id="cliente_nombreDelConyugue" name="cliente_nombreDelConyugue">
                                        </div>
                                    </div>
                                    <div id="divApellidoPaternoDelConyugue" class="col6 floatLeft" style="display: none;">
                                        <div class="mat-div">
                                            <label for="nombre-conyuge" class="mat-label"><span><strong style="color:#fb5e48;">*</strong></span> Apellido Paterno del Conyugue</label>
                                            <input type="text" class="datoPerfilador mat-input requerido validarNombre" id="cliente_apellidoPaternoDelConyugue" name="cliente_apellidoPaternoDelConyugue">
                                        </div>
                                    </div>
                                    <div id="divApellidoMaternoDelConyugue" class="col6 floatLeft" style="display: none;">
                                        <div class="mat-div">
                                            <label for="nombre-conyuge" class="mat-label"><span><strong style="color:#fb5e48;">*</strong></span> Apellido Materno del Conyugue</label>
                                            <input type="text" class="datoPerfilador mat-input requerido validarNombre" id="cliente_apellidoMaternoDelConyugue" name="cliente_apellidoMaternoDelConyugue">
                                        </div>
                                    </div>
                                    <div id="divFechaDeNacimientoDelConyugue" class="col6 floatLeft" style="display: none;">
                                        <span class="select-label multi"><span><strong style="color:#fb5e48;">*</strong></span> Fecha de Nacimiento del Conyugue</span>
                                        <div class="col2 floatLeft">
                                            <div class="mdl-selectfield">
                                                <label>Día</label>
                                                <g:select id="cliente_fechaDeNacimientoDelConyugue_dia" name="cliente_fechaDeNacimientoDelConyugue_dia" class="datoPerfilador browser-default requerido validarFecha" from="${1..31}" noSelection="['':'Día']"/>
                                            </div>
                                        </div>
                                        <div class="col6 floatLeft">
                                            <div class="mdl-selectfield">
                                                <label>Mes</label>
                                                <g:select id="cliente_fechaDeNacimientoDelConyugue_mes" name="cliente_fechaDeNacimientoDelConyugue_mes" class="datoPerfilador browser-default requerido validarFecha" from="${[[id: 1, mes: 'Enero'],[id: 2, mes: 'Febrero'],[id: 3, mes: 'Marzo'],[id: 4, mes: 'Abril'],[id: 5, mes: 'Mayo'],[id: 6, mes: 'Junio'],[id: 7, mes: 'Julio'],[id: 8, mes: 'Agosto'],[id: 9, mes: 'Septiembre'],[id: 10, mes: 'Octubre'],[id: 11, mes: 'Noviembre'],[id: 12, mes: 'Diciembre']]}" optionKey="id" optionValue="mes" noSelection="['':'Mes']" />
                                            </div>
                                        </div>
                                        <div class="col4 floatLeft">
                                            <div class="mdl-selectfield">
                                                <label>Año</label>
                                                <g:select id="cliente_fechaDeNacimientoDelConyugue_anio" name="cliente_fechaDeNacimientoDelConyugue_anio" class="datoPerfilador browser-default requerido validarFecha" from="${(anioActualParcial-18)..(anioActualParcial-68)}" noSelection="['':'Año']" />
                                            </div>
                                        </div>
                                    </div>
                                    <div id="divLugarNacimientoConyugue" class="col6 floatLeft" style="display: none;">
                                        <span class="select-label multi"><span><strong style="color:#fb5e48;">*</strong></span> Lugar de Nacimiento del Conyugue</span>
                                        <div class="col12 floatLeft">
                                            <div class="mdl-selectfield">
                                                <label>Lugar de Nacimiento</label>
                                                <g:select id="cliente_lugarDeNacimientoDelConyugue" name="cliente_lugarDeNacimientoDelConyugue" class="datoPerfilador browser-default requerido" optionKey="id" optionValue="nombre" from="${la.kosmos.app.Estado.list()}" noSelection="['':'Estado']" />
                                            </div>
                                        </div>
                                    </div>
                                    <div id="divRfcDelConyugue" class="col6 floatLeft" style="display: none;">
                                        <div class="mat-div">
                                            <label for="rfc" class="mat-label"><span><strong style="color:#fb5e48;">*</strong></span> R.F.C. del conyugue</label>
                                            <input type="text" id="cliente_rfcDelConyugue" name="cliente_rfcDelConyugue" class="datoPerfilador mat-input requerido">
                                        </div>
                                    </div>
                                    <div id="divCurpDelConyugue" class="col6 floatLeft" style="display: none;">
                                        <div class="mat-div">
                                            <label for="curp" class="mat-label"><span><strong style="color:#fb5e48;">*</strong></span> C.U.R.P. del conyugue</label>
                                            <input type="text" id="cliente_curpDelConyugue" name="cliente_curpDelConyugue" class="datoPerfilador mat-input requerido">
                                        </div>
                                    </div>
                                    <div id="divNacionalidadDelConyugue" class="col6 floatLeft" style="display: none;">
                                        <span class="select-label multi"><span><strong style="color:#fb5e48;">*</strong></span> Nacionalidad del Conyugue</span>
                                        <div class="col12 floatLeft">
                                            <div class="mdl-selectfield">
                                                <label>Nacionalidad</label>
                                                <g:select id="cliente_nacionalidadDelConyugue" name="cliente_nacionalidadDelConyugue" class="datoPerfilador browser-default requerido" optionKey="id" optionValue="nombre" from="${la.kosmos.app.Nacionalidad.list()}" noSelection="['':'Nacionalidad']" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col12 floatLeft paddingTop20">
                                        <button class="redBox colorWhite" type="button" onclick="goBackStep3()">Anterior</button>
                                        <button class="greenBox colorWhite hide" type="button" onclick="goStep5()">Siguiente</button>
                                    </div>
                                </div>
                                <div id="step5" class="hide col12 floatLeft stepContent animated fadeInRight">
                                    <h2>Datos del Empleo</h2>
                                    <div style="color: #005398;"><span><strong style="color:#fb5e48;">*</strong></span> Obligatorio </div>
                                    <div class="col6 floatLeft">
                                        <span class="select-label multi"><span><strong style="color:#fb5e48;">*</strong></span> Profesión</span>
                                        <div class="col12 floatLeft">
                                            <div class="mat-div is-completed">
                                                <br/>
                                                <g:select id="empleoCliente_profesion" name="empleoCliente_profesion" class="datoPerfilador select2 requerido" style="width: 95%;" optionKey="id" optionValue="nombre" from="${la.kosmos.app.Profesion.list()}" noSelection="['':'Profesión']" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col6 floatLeft">
                                        <span class="select-label multi"><span><strong style="color:#fb5e48;">*</strong></span> Ocupación</span>
                                        <div class="col12 floatLeft">
                                            <div class="mat-div is-completed">
                                                <br/>
                                                <g:select id="empleoCliente_ocupacion" name="empleoCliente_ocupacion" class="datoPerfilador select2 requerido" style="width: 95%;" optionKey="id" optionValue="nombre" from="${la.kosmos.app.Ocupacion.list()}" noSelection="['':'Ocupación']" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col6 floatLeft">
                                        <div class="mat-div">
                                            <label for="empresa" class="mat-label"><span><strong style="color:#fb5e48;">*</strong></span> Nombre de la Empresa</label>
                                            <input type="text" class="datoPerfilador mat-input" id="empleoCliente_empresa" name="empleoCliente_empresa">
                                        </div>
                                    </div>
                                    <div class="col6 floatLeft">
                                        <span class="select-label multi"><span><strong style="color:#fb5e48;">*</strong></span> Antigüedad en la Empresa</span>
                                        <div class="col7 floatLeft">
                                            <div class="mdl-selectfield">
                                                <label>Mes</label>
                                                <g:select id="empleoCliente_antiguedad_mes" name="empleoCliente_antiguedad_mes" class="datoPerfilador browser-default requerido validarFecha" from="${[[id: 1, mes: 'Enero'],[id: 2, mes: 'Febrero'],[id: 3, mes: 'Marzo'],[id: 4, mes: 'Abril'],[id: 5, mes: 'Mayo'],[id: 6, mes: 'Junio'],[id: 7, mes: 'Julio'],[id: 8, mes: 'Agosto'],[id: 9, mes: 'Septiembre'],[id: 10, mes: 'Octubre'],[id: 11, mes: 'Noviembre'],[id: 12, mes: 'Diciembre']]}" optionKey="id" optionValue="mes" noSelection="['':'Mes']"/>
                                            </div>
                                        </div>
                                        <div class="col5 floatLeft">
                                            <div class="mdl-selectfield">
                                                <label>Año</label>
                                                <g:select id="empleoCliente_antiguedad_anio" name="empleoCliente_antiguedad_anio" class="datoPerfilador browser-default requerido validarFecha" from="${(anioActualParcial)..(anioActualParcial-68)}" noSelection="['':'Año']" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col6 floatLeft">
                                        <div class="mat-div">
                                            <label for="ingreso-fijo" class="mat-label"><span><strong style="color:#fb5e48;">*</strong></span> Ingresos Fijos</label>
                                            <input type="text" class="datoPerfilador mat-input requerido" id="empleoCliente_ingresosFijos_autoNumeric" >
                                        </div>
                                    </div>
                                    <div class="col6 floatLeft">
                                        <div class="mat-div">
                                            <label for="ingreso-variable" class="mat-label"><span><strong style="color:#fb5e48;">*</strong></span> Ingresos Variables</label>
                                            <input type="text" class="datoPerfilador mat-input requerido" id="empleoCliente_ingresosVariables_autoNumeric" >
                                        </div>
                                    </div>
                                    <input type="hidden" class="datoPerfilador" name="empleoCliente_ingresosFijos" id="empleoCliente_ingresosFijos" >
                                    <input type="hidden" class="datoPerfilador" name="empleoCliente_ingresosVariables" id="empleoCliente_ingresosVariables">
                                    <div class="col12 floatLeft paddingTop20">
                                        <button class="redBox colorWhite" type="button" onclick="goBackStep4()">Anterior</button>
                                        <button class="greenBox colorWhite hide" type="button" onclick="goConsultaBuro()">Siguiente</button>
                                    </div>
                                </div>
                            </form>
                            <div id="buro" class="hide col12 floatLeft animated fadeInRight">
                                <h2>Consulta de Buró de Crédito</h2><br><br>
                                <g:if env="production">
                                  <div id="consultaAutenticador" class=" animated fadeInRight"S>

                                    <div class="clearFix marginBottom30">
                                        <div class="floatLeft col4 col6-tab col12-mob clearFix marginBottom20 ccInfo">
                                            <p class="correctaBoxLabel">¿TIENES UNA TARJETA DE CRÉDITO?</p>
                                            <div id="tarjeta_correcto_si" class=" correctaBox floatLeft hasCc">
                                                <p class="center paddingTop15 paddingBottom15 ">SI</p>
                                            </div>
                                            <div id="tarjeta_correcto_no" class="floatLeft correctaBox">
                                                <p class="center paddingTop15 paddingBottom15 lightGray">NO</p>
                                            </div>
                                            <input type="hidden" class="datoPerfilador formValues textUpper" name="tCredito" id="tCredito" value="${generales?.tCredito}">
                                        </div>

                                        <div class="floatLeft col3 col6-tab col12-mob">
                                            <p class="gray font14 letterspacing1.1">ÚLTIMOS CUATRO DÍGITOS</p>
                                            <input class="inPuts4a marginTop15 headingColor" type="text" id="numeroTarjeta" name="numeroTarjeta" placeholder="0000" maxlength="4" />
                                            <div class="col7 col12-tab floatLeft marginTop30" id="bubbleMensajeTarjeta">
                                                <div class="rectangleRound font11 letterspacing0.5 center">LAS TARJETAS DE DÉBITO Y TIENDAS DEPARTAMENTALES NO SON CONSIDERADAS TARJETAS DE CRÉDITO</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="clearFix marginBottom30" id="buroSegundaFila">
                                        <div class="floatLeft col4 col6-tab col12-mob clearFix marginBottom20 ccInfo">
                                            <p class="correctaBoxLabel">¿ERES TÍTULAR DE UN CREDITO HIPOTECARIO?</p>
                                            <div id="hipotecario_correcto_si" class="correctaBox floatLeft hasCc">
                                                <p class="center paddingTop15 paddingBottom15 lightGray">SI</p>
                                            </div>
                                            <div id="hipotecario_correcto_no" class="floatLeft correctaBox">
                                                <p class="center paddingTop15 paddingBottom15 lightGray">NO</p>
                                            </div>
                                            <input type="hidden" class="datoPerfilador formValues textUpper" name="creditoH" id="creditoH" value="${generales?.creditoH}">
                                        </div>
                                        <div class="floatLeft col4 col6-tab col12-mob clearFix marginBottom20 ccInfo">
                                            <p class="correctaBoxLabel">¿HAZ SIDO TÍTULAR DE UN CREDITO AUTOMOTRIZ EN LOS ÚLTIMOS 24 MESES?</p>
                                            <div id="automotriz_correcto_si" class="correctaBox floatLeft hasCc">
                                                <p class="center paddingTop15 paddingBottom15 lightGray">SI</p>
                                            </div>
                                            <div id="automotriz_correcto_no" class="floatLeft correctaBox">
                                                <p class="center paddingTop15 paddingBottom15 lightGray">NO</p>
                                            </div>
                                            <input type="hidden" class="datoPerfilador formValues textUpper" name="creditoA" id="creditoA" value="${generales?.creditoA}">
                                        </div>
                                    </div>
                                    <div id="divAutorizacionBuro" class="col12 col12-mob col floatLeft paddingTop20 paddingBottom20 ">
                                        <p class="font18 gray letterspacing1 justify">
                                            Hoy siendo <span id="fechaAutorizacionConsulta" class="headingColor">
                                                ${fechaActual}
                                            </span>, Autoriza a <span id="razonSocial" class="headingColor"> ${razonSocial}
                                            </span> a consultar sus antecedentes crediticios por &uacute;nica
                                            ocasi&oacute;n ante las Sociedades de Informaci&oacute;n Crediticia
                                            que estime conveniente, declarando que conoce la naturaleza, alcance
                                            y uso que <span id="razonSocial" class="headingColor"> ${razonSocial}
                                            </span> har&aacute; de tal informaci&oacute;n?
                                        </p>
                                    </div>
                                           <div id="accionesNormalesAutenticador" class="creditBtnsautenticador">
                                            <div class="col5half col12-mob floatLeft">
                                                <a id="" class="consultarBuroBtn">
                                                    <div  class="blueButton buttonM radius100 font16 colorWhite letterspacing1.5 mobileAuto consultarBc" style="cursor: pointer;">AUTORIZO CONSULTAR MI BURÓ DE CRÉDITO</div>
                                                </a>
                                            </div>
                                            <div class="loadingContainerautenticador clearFix clearFloat">
                                                <div class="loadingBarautenticador marginTop50 hide">
                                                    <div class="loadingActiveautenticador"></div>
                                                    <p class="mAuto lightGray font14 marginTop10">CONSULTANDO TU BURÓ DE CRÉDITO</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="accionesSuccessAutenticador" style="display: none;" class="clearFix clearFloat">
                                            <div class="colorGreen buttonM avanzaBtn radius100 font16 colorWhite letterspacing1.5 center blockAuto" style="cursor: pointer;">CONSULTA EXITOSA</div>
                                        </div>
                                        <div id="accionesErrorAutenticador" style="display: none;" class="clearFix">
                                            <div class="col5 col12-tab floatLeft">
                                                <div class="buttonOrange buttonM avanzaBtn radius100 font16 colorWhite letterspacing1.5 blockAuto" style="cursor: pointer;">CONSULTA FALLIDA</div>
                                            </div>
                                        </div>
                                    </div>
                                       <div id="consultaINTL" class="hide animated fadeInRight">
                                        <div class="clearFix padding20">
                                            <div class="col12 col12-mob floatLeft mobileDiv">
                                                <div id="" class="col12 folderContainer center span_2_of_4">
                                                    <img class="folderImage" src="${resource(dir:'images', file:'identification.png')}" alt="folder"/>
                                                    <p class="center letterspacing1.4 gray">Descargar Formato de Autorización</p>
                                                   <div class="colorGreen colorWhite radius100 marginTop17 marginLeft30  marginBottom20 marginRight20"><strong>
                                                            <g:jasperForm 
                                                                controller="dashboard"
                                                                action="printBcFormat"
                                                                jasper="FormatoDeAutorizacionBc"  
                                                                name="FormatoDeAutorizaci&oacute;nBC">
                                                                <input type="hidden" name="idClienteBc" value="${session?.identificadores?.idCliente}"/> 
                                                                <input type="hidden" name="idDireccionBc" value="${session?.identificadores?.idDireccion}"/> 
                                                                <input type="hidden" name="idSolicitudBc" value="${session?.identificadores?.idSolicitud}"/>                                                                
                                                               <g:jasperButton format="pdf" jasper="jasper-test" class="colorWhite marginBottom15 center" text="DESCARGAR"  /> </center>
                                                            </g:jasperForm>        
                                                        </strong>
                                                    </div>
                                                    <input type="hidden" name="consentimientoConsulta" id="consentimientoConsulta">
                                                </div>
                                                <div id="accionesNormalesIntl" class="creditBtnsintl marginTop30">
                                                    <div class="col5half col12-mob floatLeft">
                                                        <a id="" class="consultarBuroTradicionalBtn">
                                                            <div  class="blueButton buttonM radius100 font16 colorWhite letterspacing1.5 mobileAuto consultarBc" style="cursor: pointer;">AUTORIZO CONSULTAR MI BURÓ DE CRÉDITO</div>
                                                        </a>
                                                    </div>
                                                    <div class="loadingContainerintl clearFix clearFloat">
                                                        <div class="loadingBarintl marginTop50 hide">
                                                            <div class="loadingActiveintl"></div>
                                                            <p class="mAuto lightGray font14 marginTop10">CONSULTANDO TU BURÓ DE CRÉDITO</p>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div id="accionesSuccessIntl" style="display: none;" class="clearFix clearFloat marginTop30">
                                                    <div class="colorGreen buttonM avanzaBtn radius100 font16 colorWhite letterspacing1.5 center blockAuto" style="cursor: pointer;">CONSULTA EXITOSA</div>
                                                </div>
                                                <div id="accionesErrorIntl" style="display: none;" class="clearFix marginTop30">
                                                    <div class="col5 col12-tab floatLeft">
                                                        <div class="buttonOrange buttonM avanzaBtn radius100 font16 colorWhite letterspacing1.5 blockAuto" style="cursor: pointer;">CONSULTA FALLIDA</div>
                                                    </div>
                                                </div>
                                            </div>
                                             </div>
                                           </div>
                                </g:if>
                                <g:else>

                                    <div id="consultaAutenticador" class=" animated fadeInRight"S>
                                        <div class="clearFix marginBottom30">
                                            <div class="floatLeft col4 col6-tab col12-mob clearFix marginBottom20 ccInfo">
                                                <p class="correctaBoxLabel">¿TIENES UNA TARJETA DE CRÉDITO?</p>
                                                <div id="tarjeta_correcto_si" class=" correctaBox floatLeft hasCc">
                                                    <p class="center paddingTop15 paddingBottom15 ">SI</p>
                                                </div>
                                                <div id="tarjeta_correcto_no" class="floatLeft correctaBox">
                                                    <p class="center paddingTop15 paddingBottom15 lightGray">NO</p>
                                                </div>
                                                <input type="hidden" class="datoPerfilador formValues textUpper" name="tCredito" id="tCredito" value="${generales?.tCredito}">
                                            </div>

                                            <div class="floatLeft col3 col6-tab col12-mob">
                                                <p class="gray font14 letterspacing1.1">ÚLTIMOS CUATRO DÍGITOS</p>
                                                <input class="inPuts4a marginTop15 headingColor" type="text" id="numeroTarjeta" name="numeroTarjeta" placeholder="0000" maxlength="4" />
                                                <div class="col7 col12-tab floatLeft marginTop30" id="bubbleMensajeTarjeta">
                                                    <div class="rectangleRound font11 letterspacing0.5 center">LAS TARJETAS DE DÉBITO Y TIENDAS DEPARTAMENTALES NO SON CONSIDERADAS TARJETAS DE CRÉDITO</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="clearFix marginBottom30" id="buroSegundaFila">
                                            <div class="floatLeft col4 col6-tab col12-mob clearFix marginBottom20 ccInfo">
                                                <p class="correctaBoxLabel">¿ERES TÍTULAR DE UN CREDITO HIPOTECARIO?</p>
                                                <div id="hipotecario_correcto_si" class="correctaBox floatLeft hasCc">
                                                    <p class="center paddingTop15 paddingBottom15 lightGray">SI</p>
                                                </div>
                                                <div id="hipotecario_correcto_no" class="floatLeft correctaBox">
                                                    <p class="center paddingTop15 paddingBottom15 lightGray">NO</p>
                                                </div>
                                                <input type="hidden" class="datoPerfilador formValues textUpper" name="creditoH" id="creditoH" value="${generales?.creditoH}">
                                            </div>
                                            <div class="floatLeft col4 col6-tab col12-mob clearFix marginBottom20 ccInfo">
                                                <p class="correctaBoxLabel">¿HAZ SIDO TÍTULAR DE UN CRÉDITO AUTOMOTRIZ EN LOS ÚLTIMOS 24 MESES?</p>
                                                <div id="automotriz_correcto_si" class="correctaBox floatLeft hasCc">
                                                    <p class="center paddingTop15 paddingBottom15 lightGray">SI</p>
                                                </div>
                                                <div id="automotriz_correcto_no" class="floatLeft correctaBox">
                                                    <p class="center paddingTop15 paddingBottom15 lightGray">NO</p>
                                                </div>
                                                <input type="hidden" class="datoPerfilador formValues textUpper" name="creditoA" id="creditoA" value="${generales?.creditoA}">
                                            </div>
                                        </div>
                                        <!-- TEMPORAL -->
                                        <div class="clearFix marginBottom30">
                                            <div class="floatLeft col12 col12-tab col12-mob clearFix marginBottom20">
                                                <p class="correctaBoxLabel">INTRODUZCA LA CADENA DE BURÓ DE CRÉDITO PARA EJECUTAR LA PRUEBA</p>
                                                <textarea class="datoPerfilador" style="width:100%; height: 200px;" name="cadenaBuroTest" id="cadenaBuroTest" ></textarea>
                                            </div>
                                        </div>
                                        <!-- TEMPORAL -->
                                        <div id="divAutorizacionBuro" class="col12 col12-mob col floatLeft paddingTop20 paddingBottom20 ">
                                            <p class="font18 gray letterspacing1 justify">
                                                Hoy siendo <span id="fechaAutorizacionConsulta" class="headingColor">
                                                    ${fechaActual}
                                                </span>, Autoriza a <span id="razonSocial" class="headingColor"> ${razonSocial}
                                                </span> a consultar sus antecedentes crediticios por &uacute;nica
                                                ocasi&oacute;n ante las Sociedades de Informaci&oacute;n Crediticia
                                                que estime conveniente, declarando que conoce la naturaleza, alcance
                                                y uso que <span id="razonSocial" class="headingColor"> ${razonSocial}
                                                </span> har&aacute; de tal informaci&oacute;n?
                                            </p>
                                        </div>
                                        <div id="accionesNormalesAutenticador" class="creditBtnsautenticador">
                                            <div class="col5half col12-mob floatLeft">
                                                <a id="" class="consultarBuroBtn">
                                                    <div  class="blueButton buttonM radius100 font16 colorWhite letterspacing1.5 mobileAuto consultarBc" style="cursor: pointer;">AUTORIZO CONSULTAR MI BURÓ DE CRÉDITO</div>
                                                </a>
                                            </div>
                                            <div class="loadingContainerautenticador clearFix clearFloat">
                                                <div class="loadingBarautenticador marginTop50 hide">
                                                    <div class="loadingActiveautenticador"></div>
                                                    <p class="mAuto lightGray font14 marginTop10">CONSULTANDO TU BURÓ DE CRÉDITO</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="accionesSuccessAutenticador" style="display: none;" class="clearFix clearFloat">
                                            <div class="colorGreen buttonM avanzaBtn radius100 font16 colorWhite letterspacing1.5 center blockAuto" style="cursor: pointer;">CONSULTA EXITOSA</div>
                                        </div>
                                        <div id="accionesErrorAutenticador" style="display: none;" class="clearFix">
                                            <div class="col5 col12-tab floatLeft">
                                                <div class="buttonOrange buttonM avanzaBtn radius100 font16 colorWhite letterspacing1.5 blockAuto" style="cursor: pointer;">CONSULTA FALLIDA</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="consultaINTL" class="hide animated fadeInRight">
                                        <div class="clearFix padding20">
                                            <div class="col12 col12-mob floatLeft mobileDiv">
                                                <div id="" class="col folderContainer center span_2_of_4">
                                                    <img class="folderImage" src="${resource(dir:'images', file:'identification.png')}" alt="folder"/>
                                                    <p class="center letterspacing1.4 gray">Descargar Formato de Autorización</p>
                                                    <div class="colorGreen colorWhite radius100 marginTop17 marginLeft30  marginBottom20 marginRight20"><strong>
                                                            <g:jasperForm 
                                                                controller="dashboard"
                                                                action="printBcFormat"
                                                                jasper="FormatoDeAutorizacionBc" 
                                                                name="FormatoDeAutorizaci&oacute;nBC">
                                                                <input type="hidden" name="idClienteBc" value="${session?.identificadores?.idCliente}"/> 
                                                                <input type="hidden" name="idDireccionBc" value="${session?.identificadores?.idDireccion}"/> 
                                                                <input type="hidden" name="idSolicitudBc" value="${session?.identificadores?.idSolicitud}"/>                                                                
                                                               <g:jasperButton format="pdf" jasper="jasper-test" class="colorWhite marginBottom15 center" text="DESCARGAR"  /> </center>
                                                            </g:jasperForm>        
                                                        </strong>
                                                    </div>
                                                    <input type="hidden" name="consentimientoConsulta" id="consentimientoConsulta">
                                                </div>
                                                <!-- TEMPORAL -->
                                                <div class="clearFix marginBottom30">
                                                    <div class="floatLeft col12 col12-tab col12-mob clearFix marginBottom20">
                                                        <p class="correctaBoxLabel">INTRODUZCA LA CADENA DE BURÓ DE CRÉDITO PARA EJECUTAR LA PRUEBA</p>
                                                        <textarea class="datoPerfilador" style="width:100%; height: 200px;" id="cadenaBuroTestTradicional" ></textarea>
                                                    </div>
                                                </div>
                                                <!-- TEMPORAL -->
                                                <div id="accionesNormalesIntl" class="creditBtnsintl">
                                                    <div class="col5half col12-mob floatLeft">
                                                        <a id="" class="consultarBuroTradicionalBtn">
                                                            <div  class="blueButton buttonM radius100 font16 colorWhite letterspacing1.5 mobileAuto consultarBc" style="cursor: pointer;">AUTORIZO CONSULTAR MI BURÓ DE CRÉDITO</div>
                                                        </a>
                                                    </div>
                                                    <div class="loadingContainerintl clearFix clearFloat">
                                                        <div class="loadingBarintl marginTop50 hide">
                                                            <div class="loadingActiveintl"></div>
                                                            <p class="mAuto lightGray font14 marginTop10">CONSULTANDO TU BURÓ DE CRÉDITO</p>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div id="accionesSuccessIntl" style="display: none;" class="clearFix clearFloat">
                                                    <div class="colorGreen buttonM avanzaBtn radius100 font16 colorWhite letterspacing1.5 center blockAuto" style="cursor: pointer;">CONSULTA EXITOSA</div>
                                                </div>
                                                <div id="accionesErrorIntl" style="display: none;" class="clearFix">
                                                    <div class="col5 col12-tab floatLeft">
                                                        <div class="buttonOrange buttonM avanzaBtn radius100 font16 colorWhite letterspacing1.5 blockAuto" style="cursor: pointer;">CONSULTA FALLIDA</div>
                                                    </div>
                                                </div>
                                            </div>
                                             </div>
                                           </div>
                                        </g:else>
                                   
                           
                                    <div class="col12 floatLeft paddingTop20">
                                        <button id='goBackStep5' class="greenBox colorWhite hide" type="button" onclick="goBackStep5()">Anterior</button>
                                    </div>
                                <div class="col12 floatLeft paddingTop20">
                                    <button class="greenBox colorWhite hide consultarB" type="button" onclick="verOfertas()">Siguiente</button>
                                </div>
                            </div>
                            <div id="ofertas" class="hide col12 floatLeft animated fadeInRight"></div>
                            <div id="confirmacion" class="hide col12 floatLeft animated fadeInRight"></div>

                        </div>
                    </div>
                    </section>
                    <script type="text/javascript">
                        $(document).ready(function() {
                        operacionesPerfilador()
                        });
                    </script>
                    </body>
                    </html>

