<section class="container marginBottom28">
    <div class="width990 reporteBox autoMargin">
        <div class="lightGrayBG">
            <ul class="clearFix paddingLeft30 solicitude_submenu">
                <li class="floatLeft">
                    <a id="subMenuOpc5" onclick="listarSolicitudesPor(5,'rangoDeFechas3','from3','to3');" title="Por Fecha" class="elementoSubMenu displayInline gray font16 fontweight300 paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Descargar Reporte Bitacora Mitek</a>
                </li>
            </ul>
        </div>
    </div>
</section>
<section class="container marginBottom28" id="rangoDeFechas3" style="display: none;">
    <g:form action="descargarReporteBitacoraMitek">
    <div class="width990 reporteBox autoMargin" style="height: 10%;">
        <div class="lightGrayBG" style="height: 100%;">
            <div class="col12 floatLeft" style="padding: 15px;">
                <div class="col4 floatLeft">
                    <div class="col3 floatLeft">
                        <p class="gray font14 fontWeight500 letterspacing1 marginLeft20" style="padding: 5px;"><strong>Desde</strong></p>
                    </div>
                    <div class="col9 floatLeft">
                        <input class="block cameraBox col11 height30" style="text-align: center;" readonly type="text" id="from3" name="from3">
                    </div>
                </div>
                <div class="col4 floatLeft">
                    <div class="col3 floatLeft">
                        <p class="gray font14 fontWeight500 letterspacing1 marginLeft20" style="padding: 5px;"><strong>a</strong></p>
                    </div>
                    <div class="col9 floatLeft">
                        <input class="block cameraBox col11 height30" style="text-align: center;" readonly type="text" id="to3" name="to3">
                    </div>
                </div>
                <div class="col4 floatLeft">
                    <center>
                        <g:link action="descargarReporteBitacoraMitek" >
                            <button type="submit" class="displayInline blueButton font16 fontweight300  paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Descargar Reporte Bitacora Mitek</button>
                        </g:link>
                       
                    </center>
                </div>
                 
            </div>
        </div>
    </div>
    </g:form>
</section>
 
                                    