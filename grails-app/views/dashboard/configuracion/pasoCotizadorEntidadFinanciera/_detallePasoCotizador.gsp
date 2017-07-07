<div class="idLightbox hide" id="modalDetallePasoCotizador">  
    <div class="overlay"></div>
    <div class="visitaContainer">

        <div class="dashBordBox">
            <div class="loginForm gray font14" class="tabcontent">
                <form id="updatePasoCotizadorEntidadFinancieraForm" class="loginForm gray font14">

                    <div class="formContainer">
                        <div class='col12' style='display:inline-block; position:relative;'>

                            <div class="width990 autoMargin solicitudWhiteBox clearFix paddingBottom20">
                               <input type="hidden" name="idPasoCotizadorEntidadFinanciera" value="${pasoCotizadorEntidadFinanciera.id}"/>
                                <div class="clearFix width990 autoMargin borderGrayBottom">
                                    <center><h1 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">Alta Paso Cotizador</h1></center>
                                </div>
                                <div class="clearFix width990 autoMargin ">
                                    <center><h2 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">Generales</h2></center>
                                </div>
                                <div class="col9 floatLeft marginTop20">
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 marginLeft20"><strong>Titulo del Paso</strong></p>
                                        </div>
                                        <div class="col6 floatLeft">
                                            <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="tituloDelPaso" value="${pasoCotizadorEntidadFinanciera.tituloDelPaso.toUpperCase()}" >
                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Titulo Resumen</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8">
                                            <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="tituloResumen" value="${pasoCotizadorEntidadFinanciera.tituloResumen}" />
                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Número de Paso</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8">
                                            <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="numeroDePaso" value="${pasoCotizadorEntidadFinanciera.numeroDePaso}" />
                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Tiene Ayuda</strong></p>
                                        </div>
                                        <div class="col4 floatLeft paddingTop8">
                                                <div class="col2 floatLeft">
                                                    <div class="paddingTop15">
                                                        <label>Si</label>
                                                        <input class="block cameraBox width150 height30 " onclick="mostrarCampo('tieneAyuda');" type="radio"  name="tieneAyuda"  value="true" <g:if test="${pasoCotizadorEntidadFinanciera.tieneAyuda == true}">checked</g:if>>

                                                    </div>

                                                </div>
                                                <div class="col2 floatLeft">
                                                    <div class="paddingTop15">
                                                        <label>No</label>
                                                        <input class="block cameraBox width150 height30 " onclick="ocultarCampo('tieneAyuda');" type="radio"  name="tieneAyuda"  value="false" <g:if test="${pasoCotizadorEntidadFinanciera.tieneAyuda == false}">checked</g:if> >

                                                    </div>

                                                </div>
                                         </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft tieneAyuda" >
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Texto Ayuda</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8 tieneAyuda" >
                                            <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="textoAyuda" value="${pasoCotizadorEntidadFinanciera.textoAyuda}" />
                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Carga Inicial</strong></p>
                                        </div>
                                        <div class="col4 floatLeft paddingTop8">
                                            <div class="col2 floatLeft">
                                                <div class="paddingTop15">
                                                    <label>Si</label>
                                                    <input class="block cameraBox width150 height30 "  type="radio"  name="cargaInicial"  value="true" <g:if test="${pasoCotizadorEntidadFinanciera.cargaInicial == true}">checked</g:if> >

                                                </div>
                                            </div>
                                            <div class="col2 floatLeft">
                                                <div class="paddingTop15">
                                                    <label>No</label>
                                                    <input class="block cameraBox width150 height30 " type="radio"  name="cargaInicial" value="false" <g:if test="${pasoCotizadorEntidadFinanciera.cargaInicial == false}">checked</g:if> >

                                                </div>

                                            </div>
                                        </div>

                                    </div>
                                </div>
        
                                <br /> &nbsp;
                                <br /> &nbsp;


                                <div class="col9 floatLeft">
                                    <div class="col6 floatLeft">
                                        <p class="gray font14 fontWeight500 letterspacing1 paddingTop30 marginLeft20"><strong>Nombre del tipo de Paso</strong></p>
                                    </div>

                                    <div class="col6 floatLeft paddingTop30">

                                        <select class="js-example-basic-multiple"   name="tipoDePasoId">
                                            <option value=${pasoCotizadorEntidadFinanciera.tipoDePaso.id} selected="selected" >${pasoCotizadorEntidadFinanciera.tipoDePaso.nombre}</option>
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
                            <button type="button" onclick="actualizarPasoCotizadorEntidadFinanciera();" class="loginButton blueButton letterspacing2 font14 pointer">ACTUALIZAR PASO</button>
                            <button type="button" onclick="cerrarModal('modalDetallePasoCotizador');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>
