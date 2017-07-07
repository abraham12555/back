<div class="idLightbox hide" id="modalAltaPasosCotizador">  
    <div class="overlay"></div>
    <div class="visitaContainer">

        <div class="dashBordBox">
            <div class="loginForm gray font14" class="tabcontent">
                <form id="altaPasoCotizadorEntidadFinancieraForm" class="loginForm gray font14">

                    <div class="formContainer">
                        <div class='col12' style='display:inline-block; position:relative;'>

                            <div class="width990 autoMargin solicitudWhiteBox clearFix paddingBottom20">

                                <div class="clearFix width990 autoMargin borderGrayBottom">
                                    <center><h1 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">Alta Paso Cotizador</h1></center>
                                </div>
                                <div class="clearFix width990 autoMargin ">
                                    <center><h2 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">Generales</h2></center>
                                </div>
                                <div class="col6 floatLeft marginTop20">
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 marginLeft20"><strong>Titulo del Paso</strong></p>
                                        </div>
                                        <div class="col6 floatLeft">
                                            <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="tituloDelPaso" >
                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Titulo Resumen</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8">
                                            <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="tituloResumen"/>
                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Número de Paso</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8">
                                            <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="numeroDePaso" />
                                        </div>
                                    </div>
                                       <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Tiene Ayuda</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8">
                                                <div class="col2 floatLeft">
                                                    <div class="paddingTop15">
                                                        <label>Si</label>
                                                        <input class="block cameraBox width150 height30 " onclick="mostrarCampo('tieneAyuda');" type="radio"  name="tieneAyuda"  value="true" checked>

                                                    </div>

                                                </div>
                                                <div class="col2 floatLeft">
                                                    <div class="paddingTop15">
                                                        <label>No</label>
                                                        <input class="block cameraBox width150 height30 " onclick="ocultarCampo('tieneAyuda');" type="radio"  name="tieneAyuda"  value="false">

                                                    </div>

                                                </div>
                                         </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft tieneAyuda" >
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Texto Ayuda</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8 tieneAyuda" >
                                            <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="textoAyuda" />
                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Carga Inicial</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8">
                                            <div class="col2 floatLeft">
                                                <div class="paddingTop15">
                                                    <label>Si</label>
                                                    <input class="block cameraBox width150 height30 "  type="radio"  name="cargaInicial"  value="true" checked>

                                                </div>
                                            </div>
                                            <div class="col2 floatLeft">
                                                <div class="paddingTop15">
                                                    <label>No</label>
                                                    <input class="block cameraBox width150 height30 " type="radio"  name="cargaInicial"  value="false">

                                                </div>

                                            </div>
                                        </div>

                                    </div>
                                </div>
        
                                <br /> &nbsp;
                                <br /> &nbsp;


                                <div class="col12 floatLeft">
                                    <div class="col3 floatLeft">
                                        <p class="gray font14 fontWeight500 letterspacing1 paddingTop30 marginLeft20"><strong>Nombre del tipo de Paso</strong></p>
                                    </div>

                                    <div class="col6 floatLeft paddingTop30">

                                        <select class="js-example-basic-multiple"  name="tipoDePasoId">
                                            <g:each var="tipoDe" in="${la.kosmos.app.TipoDePasoCotizador.findAll()}">
                                                <option value=${tipoDe.id} >${tipoDe.nombre}</option>
                                            </g:each>

                                        </select>    

                                    </div>
                                </div>
                                <br /> &nbsp;

                            </div>
                        </div>
                        <div class="formContainer">
                            <button type="button" onclick="registrarPasoCotizadorEntidadFinanciera();" class="loginButton blueButton letterspacing2 font14 pointer">REGISTRAR PASO</button>
                            <button type="button" onclick="cerrarModal('modalAltaPasosCotizador');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>
