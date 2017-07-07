<div class="solicitudLightbox hide" style="overflow: scroll;" id="modalDetalleServicioDeAsistencia">
    
    <div class="overlay"></div>
    <div class="visitaContainer">
        <div class="dashBordBox">
        
            <div class="loginForm gray font14" id="Paris" class="tabcontent">
                <p class="loginTitle font25 fontWeight500 darkBluetitle textUpper letterspacing1 center paddingAside15 marginBottom25">SERVICIO DE ASISTENCIA</p>
                <div class="solicitudTitle autoMargin" style="width: 100%;">
                    <p class="center font14 lightGray">Servicio de Asistencia</p>
                </div>
            <form id="updateServicioDeAsistenciaForm" class="loginForm gray font14">
            <input type="hidden" name="idServicioDeAsistencia" value="${servicioDeAsistencia?.id}"/>

                <div class="formContainer">

           <div class='col12' style='display:inline-block; position:relative;'>
                        
            <div class="col6 col12-mob floatLeft clearFix marginTop25 marginBottom25">
                <div class="col6 floatLeft">
                    <div class="paddingTop20">
                        <p class="gray font12 fontWeight500 latterspacing1 center">CAMPOS</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">(Monto Final)</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">(Monto Inicial)</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">(Plazo Semanal)</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">(Plazo Quincenal)</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">(Plazo Anual)</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">(Importe Asistencia)</p>
                    </div>
                </div>
                <div class="col6 floatLeft">
                    <div class="paddingTop20">
                        <p class="gray font12 fontWeight500 latterspacing1">TEXTO</p>
                    </div>
                    <div class="paddingTop15">
                        <input class="block cameraBox width150 height30" type="text" id="montoFinal" name="montoFinal"  value="${servicioDeAsistencia?.montoFinal}">
                    </div>
                    <div class="paddingTop15">
                        <input class="block cameraBox width150 height30" type="number" id="montoInicial" name="montoInicial"  value="${servicioDeAsistencia?.montoInicial}">
                    </div>
                    <div class="paddingTop15">
                        <input class="block cameraBox width150 height30" type="text"  id="plazoSemanal" name="plazoSemanal" value="${servicioDeAsistencia?.plasoSemanal}">
                    </div>
                     <div class="paddingTop15">
                        <input class="block cameraBox width150 height30" type="text"  id="plazoQuincenal" name="plazoQuincenal" value="${servicioDeAsistencia?.plazoQuincenal}" >
                    </div>
                     <div class="paddingTop15">
                        <input class="block cameraBox width150 height30" type="text"  id="plazoAnual" name="plazoAnual" value="${servicioDeAsistencia?.plazoAnual}" >
                    </div>
                     <div class="paddingTop15">
                        <input class="block cameraBox width150 height30" type="text"  id="importeAsistencia" name="importeAsistencia" value="${servicioDeAsistencia?.importeAsistencia}">
                    </div>
                </div>
            </div>

                    </div>
    
                    <div class="col12" style="text-align: center;">
                        <div class="col4" style="display: inline-block;">
                            <button type="button" id="btnVerificar" onclick="actualizarServicioDeAsistencia();" class="loginButton letterspacing2 font14 pointer" >REGISTRAR CONFIGURACIÓN</button>
                        </div>
                        <div class="col4" style="display: inline-block;">
                            <button type="button" onclick="cerrarModal('modalDetalleServicioDeAsistencia');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
                        </div>
                    </div>
                </div>
                </form>
            </div>
            
         </div>
    </div>
</div>
