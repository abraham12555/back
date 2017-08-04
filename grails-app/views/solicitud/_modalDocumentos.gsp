<div class="idLightbox hide" id="documento_solicitud">
    <div class="overlay"></div>
    <div class="whiteContainer lightboxPos identificationLb">
        <h2 class="floatLeft lbHeader headingColor fontWeight300 letterspacing1.5">SUBE TU ${productoSolicitud.documentoElegido?.nombre?.toUpperCase()}</h2>
        <span class="floatRight borderGray radius100 marginTop15 marginRight15 closeModal">
            <p class="textUpper gray paddingTop7 paddingBottom5 font18 center paddingLeft20 paddingRight20"><span class="notMobile paddingRight5">cerrar</span><i class="fa fa-times " aria-hidden="true"></i></p>
        </span>
        <div class="border1 clearFloat"></div>
        <div class="docStep">
            <div class="col12 col10-tab col12-mob autoMargin marginTop80 marginBottom80">
                <div class="paddingTop20 paddingBottom20">
                    <g:if test="${productoSolicitud.documentoElegido?.cantidadSolicitada == 1}">
                        <p class="center font14 letterspacing1.1 gray paddingTop36 paddingBottom38">SUBE TU ÚLTIMO ${productoSolicitud.documentoElegido?.nombre?.toUpperCase()}</p>
                    </g:if>
                    <g:elseif test="${productoSolicitud.documentoElegido?.cantidadSolicitada > 1}">
                        <p class="center font14 letterspacing1.1 gray paddingTop36 paddingBottom38">SUBE TUS ÚLTIMOS ${productoSolicitud.documentoElegido?.cantidadSolicitada} ${productoSolicitud.documentoElegido?.nombre?.toUpperCase()}</p>
                    </g:elseif>
                    <div id="divDropzoneDocs" class="document-section group">
                        <input type="hidden" id="doctoCargado">
                        <input type="hidden" id="cantidadSolicitada" value="${productoSolicitud.documentoElegido?.cantidadSolicitada}">
                        <g:each var="i" in="${ (0..<productoSolicitud.documentoElegido?.cantidadSolicitada) }">
                            <div id="uploadDocto${i}" class="col folderContainer center span_1_of_4">
                                <img class="folderImage" src="${resource(dir:'images', file:'folder.png')}" alt="folder"/>
                                <p class="center letterspacing1.4 gray">${productoSolicitud.documentoElegido?.nombre + " " + (i+1)}</p>
                                <div data-box="${i}" class="colorGreen radius100 marginTop17 marginLeft60 foldersBoxDocs marginBottom20">
                                    <p class="textUpper colorWhite font16 center paddingTop10 paddingBottom10 pointer">subir</p>
                                </div>
                            </div>
                        </g:each>
                    </div>
                </div>
            </div>
        </div>
        <div class="clearFix">
            <div id="progresoConsultaComp" style="display:none;margin-left: 25%;width: 50%;" class="floatLeft marginLeft20">
                <div class="spinner">
                    <div class="rect1"></div>
                    <div class="rect2"></div>
                    <div class="rect3"></div>
                    <div class="rect4"></div>
                    <div class="rect5"></div>
                </div>
                <center><span style="color: #71758d;">Estamos subiendo tu comprobante, espera por favor...</span></center>
            </div>
        </div>
    </div>
</div>