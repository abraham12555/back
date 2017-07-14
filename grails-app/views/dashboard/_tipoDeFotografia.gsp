<g:if test="listaTipoDeFotografia">
    <g:each var="lista" in="${listaTipoDeFotografia}">
        <section class="container marginBottom20">
            <div class="width990 solicitudBox autoMargin radius2">
                <div class="clearFix">
                    
                    <div class="col1fifth col6-tab col12-mob floatLeft">
                        <div class="borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10">
                            <p class="font12 gray2 marginBottom5"><strong>Nombre</strong></p>
                            <p class="font14 gray2">${lista.toString()}</p>
                        </div>
                    </div>
                     <div class="col1fifth col6-tab col12-mob floatLeft">
                        <div class="borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10">
                            <p class="font12 gray2 marginBottom5"><strong>Referencia</strong></p>
                            <p class="font14 gray2">${lista.referencia}</p>
                        </div>
                    </div>
                     <div class="col1fifth col6-tab col12-mob floatLeft">
                        <div class="borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10">
                            <p class="font12 gray2 marginBottom5"><strong>Activo</strong></p>
                            <p class="font14 gray2">${lista.activa}</p>
                        </div>
                    </div>
                   
                    <div class="col1fifth col12-tab col12-mob floatLeft">
                        <div class="marginTop10 marginBottom10 clearFix paddingAside10">
                            <a title="Editar" onclick="editarTipoDeFotografia(${lista.id});" class="tabNoFloat floatRight marginLeft20 block width115 blueButton center radius4 paddingTop10 paddingBottom10 pointer">EDITAR</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </g:each>
  </g:if>
<g:else>
    <section class="container marginBottom20">
        <div class="width990 solicitudBox autoMargin radius2">
            <div class="clearFix">
                <div class="col1fifth col6-tab col12-mob floatLeft">
                    <div class="borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10">
                        <p class="font12 gray2 marginBottom5">NO HAY USUARIOS REGISTRADOS</p>
                    </div>
                </div>
            </div>
        </div>s
    </section>
</g:else>
<g:render template="configuracion/tipoDeFotografia/altaTipoDeFotografia"/>
<div id="detalleTipoDeFotografia"></div>