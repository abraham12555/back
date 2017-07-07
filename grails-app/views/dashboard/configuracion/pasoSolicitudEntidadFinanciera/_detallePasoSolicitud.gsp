<div class="idLightbox hide" id="modalDetallePasoSolicitud">  
    <div class="overlay"></div>
    <div class="visitaContainer">

        <div class="dashBordBox">
            <div class="loginForm gray font14" class="tabcontent">
                <form id="updatePasoSolicitudEntidadFinancieraForm" class="loginForm gray font14">

                    <div class="formContainer">
                        <div class='col12' style='display:inline-block; position:relative;'>
                         <input type="hidden" name="idPasoSolicitudEntidadFinanciera" value="${pasoSolicitudEntidadFinanciera.id}"/>
                            <div class="width990 autoMargin solicitudWhiteBox clearFix paddingBottom20">

                                <div class="clearFix width990 autoMargin borderGrayBottom">
                                    <center><h1 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">Detalle Paso Solicitud</h1></center>
                                </div>
                                <div class="clearFix width990 autoMargin ">
                                    <center><h2 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">Generales</h2></center>
                                </div>
                                <div class="col6 floatLeft marginTop20">
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 marginLeft20"><strong>Título del Paso</strong></p>
                                        </div>
                                        <div class="col6 floatLeft">
                                            <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="titulo" value="${pasoSolicitudEntidadFinanciera.titulo}">
                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Subtítulo</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8">
                                            <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="subtitulo" value="${pasoSolicitudEntidadFinanciera.subtitulo}" />
                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Número de Paso</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8">
                                            <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="numeroDePaso" value="${pasoSolicitudEntidadFinanciera.numeroDePaso}" />
                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Ponderación</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8">
                                            <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="ponderacion" value="${pasoSolicitudEntidadFinanciera.ponderacion}" />
                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Modo de Despliegue</strong></p>
                                        </div>

                                    </div>
                                    <div class="col6 floatLeft cotizadorLogo">
                                        <div class="col2 floatLeft">
                                            <div class="paddingTop15">
                                                <label>NARRATIVA </label>
                                                <input class="block cameraBox width150 height30 "  type="radio"  name="modoDeDespliegue"  value="NARRATIVA" <g:if test="${pasoSolicitudEntidadFinanciera.modoDeDespliegue == "NARRATIVA"}">checked</g:if>>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col6 floatLeft cotizadorLogo">
                                        <div class="col2 floatLeft">
                                            <div class="paddingTop15">
                                                <label>FORMULARIO </label>
                                                <input class="block cameraBox width150 height30 "  type="radio"  name="modoDeDespliegue"  value="FORMULARIO" <g:if test="${pasoSolicitudEntidadFinanciera.modoDeDespliegue == "FORMULARIO"}">checked</g:if>>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="col6 floatLeft marginTop20">
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Mostrar en Barra</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8">
                                            <div class="col2 floatLeft">
                                                <div class="paddingTop15">
                                                    <label>Si </label>
                                                    <input class="block cameraBox width150 height30 "  type="radio"  name="mostrarEnBarra"  value="true" <g:if test="${pasoSolicitudEntidadFinanciera.mostrarEnBarra == true}">checked</g:if>>
                                                </div>
                                            </div>
                                            <div class="col2 floatLeft">
                                                <div class="paddingTop15">
                                                    <label>No</label>
                                                    <input class="block cameraBox width150 height30 " type="radio"  name="mostrarEnBarra"  value="false" <g:if test="${pasoSolicitudEntidadFinanciera.mostrarEnBarra == false}">checked</g:if>>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Requiere Subir Comprobante</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8">
                                            <div class="col2 floatLeft">
                                                <div class="paddingTop15">
                                                    <label>Si </label>
                                                    <input class="block cameraBox width150 height30 "  type="radio"  name="requiereSubirComprobante"  value="true" <g:if test="${pasoSolicitudEntidadFinanciera.requiereSubirComprobante == true}">checked</g:if>>
                                                </div>
                                            </div>
                                            <div class="col2 floatLeft">
                                                <div class="paddingTop15">
                                                    <label>No</label>
                                                    <input class="block cameraBox width150 height30 " type="radio"  name="requiereSubirComprobante"  value="false" <g:if test="${pasoSolicitudEntidadFinanciera.requiereSubirComprobante == false}">checked</g:if>>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Requiere Subir Identificación</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8">
                                            <div class="col2 floatLeft">
                                                <div class="paddingTop15">
                                                    <label>Si </label>
                                                    <input class="block cameraBox width150 height30 "  type="radio"  name="requiereSubirIdentificacion"  value="true" <g:if test="${pasoSolicitudEntidadFinanciera.requiereSubirIdentificacion == true}">checked</g:if>>
                                                </div>
                                            </div>
                                            <div class="col2 floatLeft">
                                                <div class="paddingTop15">
                                                    <label>No</label>
                                                    <input class="block cameraBox width150 height30 " type="radio"  name="requiereSubirIdentificacion"  value="false" <g:if test="${pasoSolicitudEntidadFinanciera.requiereSubirIdentificacion == false}">checked</g:if>>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Último Paso</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8">
                                            <div class="col2 floatLeft">
                                                <div class="paddingTop15">
                                                    <label>Si</label>
                                                    <input class="block cameraBox width150 height30 "  type="radio"  name="ultimoPaso"  value="true" <g:if test="${pasoSolicitudEntidadFinanciera.ultimoPaso == true}">checked</g:if>>
                                                </div>
                                            </div>
                                            <div class="col2 floatLeft">
                                                <div class="paddingTop15">
                                                    <label>No</label>
                                                    <input class="block cameraBox width150 height30 " type="radio"  name="ultimoPaso"  value="false" <g:if test="${pasoSolicitudEntidadFinanciera.ultimoPaso == false}">checked</g:if>>
                                                </div>

                                            </div>
                                        </div>
                                    </div>

                                </div>

                                <br /> &nbsp;
                                <br /> &nbsp;


                                <div class="col12 floatLeft">
                                    <div class="col3 floatLeft">
                                        <p class="gray font14 fontWeight500 letterspacing1 paddingTop30 marginLeft20"><strong>Nombre del Tipo de Paso</strong></p>
                                    </div>

                                    <div class="col6 floatLeft paddingTop30">

                                        <select class="js-example-basic-multiple"  name="tipoDePasoId">
                                             <option value=${pasoSolicitudEntidadFinanciera.tipoDePaso.id} >${pasoSolicitudEntidadFinanciera.tipoDePaso.nombre}</option>

                                            <g:each var="faltanPasos" in="${faltanTipos}">
                                                <option value=${faltanPasos.id}  >${faltanPasos.nombre}</option>
                                            </g:each>

                                        </select>    

                                    </div>
                                </div>
                                <br /> &nbsp;

                            </div>
                        </div>
                        <div class="formContainer">
                            <button type="button" onclick="actualizarPasoSolicitudEntidadFinanciera();" class="loginButton blueButton letterspacing2 font14 pointer">ACTUALIZAR PASO</button>
                            <button type="button" onclick="cerrarModal('modalDetallePasoSolicitud');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>
