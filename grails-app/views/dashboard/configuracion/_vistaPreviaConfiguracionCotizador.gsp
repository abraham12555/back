<div class="solicitudLightbox hide" style="overflow: scroll;" id="modalVistaPrevia">

    <div class="overlay" onclick="cerrarModal('modalVistaPrevia');" ></div>
    <div class="visitaContainer">
        <div class="dashBordBox" >
            <div id='accordion'>
                  <g:each var="general" in="${generales}">
               <h1 class="darkBluetitle font18 fontWeight600 letterspacing2">${general?.id}.- ${general?.texto.toUpperCase()}</h1>
              
                 <section class="container marginTop12 clearFix">
                    <div class="col12 clearFix">
                        <div class="padding20">
                        </div>
                        <div id="idVistaPrevia${general.id}">
                        <div class="col6 col12-mob floatLeft">
                            <div class="mobileAside10 solicitudWhiteBox radius2 paddingBottom12 marginBottom20">
                                <div class="blueButton radius2 ">
                                    <p class="paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10">INFORMACIÓN</p>
                                </div>
                                <div class="marginLeft32 clearFix">
                                    <p class="font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft">Posición</p>
                                    <div class="floatRight marginRight35">
                                        <p class="font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft">${general?.posicion}</p>
                                    </div>
                                </div>
                                <div class="paddingLeft30 lightGrayBG clearFix">
                                    <p class="font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft">Descripción</p>
                                    <div class="floatRight marginRight35">
                                        <p class="font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft">${general?.descripcion}</p>
                                    </div>
                                </div>
                                <div class="marginLeft32 clearFix">
                                    <p class="font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft">Icono</p>
                                    <div class="floatRight marginRight35">
                                        <p class="font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft"><i class="${general?.claseIconoPaso}"></i></p>
                                    </div>
                                </div>
                                <div class="paddingLeft30 lightGrayBG clearFix">
                                    <p class="font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft">Tooltip</p>
                                    <div class="floatRight marginRight35">
                                        <p class="font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft">${general?.tooltip}</p>
                                    </div>
                                </div>
                                <div class="marginLeft32 clearFix">
                                    <p class="font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft">Texto Tooltip</p>
                                    <div class="floatRight marginRight35">
                                        <p class="font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft">${general?.textoTooltip}</p>
                                    </div>
                                </div>
                                <div class="paddingLeft30 lightGrayBG clearFix">
                                     <p class="font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft">Activo</p>
                                      <div class="floatRight marginRight35">
                                            <p class="font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft">${general?.activo}</p>
                                      </div>
                                </div>
                              

                            </div>
                        </div>
                        </div>
                        <div class="col6 col12-mob floatLeft">
                                <div id="opcionesScore" class="col6 col12-mob floatRight">
                                    <div class="mobileAside10">
                                        <a onclick="mostrarDetalle('rubros',${general.id},'idVistaPrevia${general.id}','botonRubros${general.id}');" >
                                            <div class="width400 blueButton radius2 autoCenter marginBottom20" id="botonRubros${general.id}">
                                                <p class="autorizar_credito letterspacing2 font18 fontWeight600 paddingTop18 paddingBottom15 center pointer">RUBRO</p>
                                            </div>
                                        </a>
                                        <a onclick="mostrarDetalle('documentos',${general.id},'idVistaPrevia${general.id}','botonDocumentos${general.id}');" >
                                            <div class="width400 solicitudWhiteBox radius2 autoCenter marginBottom20" id="botonDocumentos${general.id}">
                                                <p class="letterspacing2 font18 fontWeight600 paddingTop18 paddingBottom15 center darkBluetitle pointer">DOCUMENTOS</p>
                                            </div>
                                        </a>
                                        <a onclick="mostrarDetalle('productos',${general.id},'idVistaPrevia${general.id}','botonProductos${general.id}');" >
                                            <div class="width400 solicitudWhiteBox radius2 autoCenter marginBottom20" id="botonProductos${general.id}">
                                                <p class="letterspacing2 font18 fontWeight600 paddingTop18 paddingBottom15 center darkBluetitle pointer">PRODUCTOS</p>
                                            </div>
                                        </a>
                                        <a  onclick="mostrarDetalle('vistas',${general.id},'idVistaPrevia${general.id}','botonVistas${general.id}');" >
                                            <div class="width400 solicitudWhiteBox radius2 autoCenter marginBottom20" id="botonVistas${general.id}">
                                                <p class="letterspacing2 font18 fontWeight600 paddingTop18 paddingBottom15 center darkBluetitle pointer">VISTAS</p>
                                            </div>
                                        </a>
                                    </div>
                            </div>
                        </div>



                </section>
                </g:each>
           </div>


        </div>
    </div>
</div>



