<section class="container marginBottom28">
    <div class="width990 reporteBox autoMargin">
        <div class="lightGrayBG">
            <ul class="clearFix paddingLeft30 solicitude_submenu">
                <li class="floatLeft">
                    <a id="subMenuOpc5" onclick="listarSolicitudesPor(5,'rangoDeFechas2','from2','to2');" title="Por Fecha" class="elementoSubMenu displayInline gray font16 fontweight300 paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">REPORTE OPERACIONES</a>
                </li>
            </ul>
        </div>
    </div>
</section>
<section class="container marginBottom28" id="rangoDeFechas2" style="display: none;">
    <g:form action="descargarReporteOperaciones">
    <div class="width990 reporteBox autoMargin" style="height: 10%;">
        <div class="lightGrayBG" style="height: 100%;">
            <div class="col12 floatLeft" style="padding: 15px;">
                <div class="col4 floatLeft">
                    <div class="col3 floatLeft">
                        <p class="gray font14 fontWeight500 letterspacing1 marginLeft20" style="padding: 5px;"><strong>Desde</strong></p>
                    </div>
                    <div class="col9 floatLeft">
                        <input class="block cameraBox col11 height30" style="text-align: center;" readonly type="text" id="from2" name="from2">
                    </div>
                </div>
                <div class="col4 floatLeft">
                    <div class="col3 floatLeft">
                        <p class="gray font14 fontWeight500 letterspacing1 marginLeft20" style="padding: 5px;"><strong>a</strong></p>
                    </div>
                    <div class="col9 floatLeft">
                        <input class="block cameraBox col11 height30" style="text-align: center;" readonly type="text" id="to2" name="to2">
                    </div>
                </div>
                <div class="col4 floatLeft">
                    <center>
                        <g:link action="descargarReporteOperaciones" >
                            <button type="submit" class="displayInline blueButton font16 fontweight300  paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Descargar Reporte Operaciones</button>
                        </g:link>
                       
                    </center>
                </div>
                 
            </div>
        </div>
    </div>
    </g:form>
</section>
 
                                    