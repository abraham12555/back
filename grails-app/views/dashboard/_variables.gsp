<section class="container marginTop10 marginBottom10">
    <div class="clearFix width990 autoMargin">
        <h1 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">PARAMETRIZACIÓN DE REGLAS DE NEGOCIO</h1>
    </div>
</section>

<section class="container clearFix">
    <div class="width990 autoMargin solicitudWhiteBox clearFix paddingBottom20">
        <div class="padding20 clearFix borderGrayBottom">
            <div class="col3 col12-mob floatLeft">
                <p class="floatLeft font14 fontWeight500 letterspacing1 gray paddingTop5">CAPACIDAD DE PAGO</p>
            </div>
            <div class="col9 col12-mob floatLeft">
                <p class="floatLeft font14 fontWeight500 letterspacing1 gray paddingTop5">Ponderación Area</p>
                <input class="block floatLeft marginLeft35 cameraBox width150 height30" type="text" name="Ponderación Area" value="">
            </div>
        </div>
        <div class="col6 col12-mob floatLeft clearFix marginTop25 marginBottom25">
            <div class="col6 floatLeft">
                <div class="paddingTop20">
                    <p class="gray font12 fontWeight500 latterspacing1 center">VARIABLES</p>
                </div>
                <div class="paddingTop20">
                    <p class="gray font14 fontWeight500 latterspacing1 center">(Ingresos - Gastos) * 15%</p>
                </div>
                <div class="paddingTop20">
                    <p class="gray font14 fontWeight500 latterspacing1 center">(Ingresos - Gastos) * 20%</p>
                </div>
                <div class="paddingTop20">
                    <p class="gray font14 fontWeight500 latterspacing1 center">(Ingresos - Gastos) * 25%</p>
                </div>
            </div>
            <div class="col6 floatLeft">
                <div class="paddingTop20">
                    <p class="gray font12 fontWeight500 latterspacing1">PONDERACIÓN ESPECÍFICA</p>
                </div>
                <div class="paddingTop15">
                    <input class="block cameraBox width150 height30" type="text" name="(Ingresos-Gastos)*15%" value="">
                </div>
                <div class="paddingTop5">
                    <input class="block cameraBox width150 height30" type="text" name="(Ingresos-Gastos)*20%" value="">
                </div>
                <div class="paddingTop5">
                    <input class="block cameraBox width150 height30" type="text" name="(Ingresos-Gastos)*25%" value="">
                </div>
            </div>
        </div>
        <div class="col6 col12-mob floatLeft clearFix marginTop25 marginBottom25">
            <div class="col6 floatLeft">
                <div class="paddingTop20">
                    <p class="gray font12 fontWeight500 latterspacing1 center">VARIABLES</p>
                </div>
                <div class="paddingTop20">
                    <p class="gray font14 fontWeight500 latterspacing1 center">(Ingresos - Gastos) * 30%</p>
                </div>
                <div class="paddingTop20">
                    <p class="gray font14 fontWeight500 latterspacing1 center">(Ingresos - Gastos) * 35%</p>
                </div>
                <div class="paddingTop20">
                    <p class="gray font14 fontWeight500 latterspacing1 center">(Ingresos - Gastos) * 40%</p>
                </div>
            </div>
            <div class="col6 floatLeft">
                <div class="paddingTop20">
                    <p class="gray font12 fontWeight500 latterspacing1">PONDERACIÓN ESPECÍFICA</p>
                </div>
                <div class="paddingTop15">
                    <input class="block cameraBox width150 height30" type="text" name="(Ingresos-Gastos)*30%" value="">
                </div>
                <div class="paddingTop5">
                    <input class="block cameraBox width150 height30" type="text" name="(Ingresos-Gastos)*35%" value="">
                </div>
                <div class="paddingTop5">
                    <input class="block cameraBox width150 height30" type="text" name="(Ingresos-Gastos)*40%" value="">
                </div>
            </div>
        </div>
    </div>
</section>

<section class="container clearFix marginTop30">
    <div class="width990 autoMargin solicitudWhiteBox clearFix paddingBottom20">
        <div class="padding20 clearFix borderGrayBottom">
            <div class="col3 col12-mob floatLeft">
                <p class="floatLeft font14 fontWeight500 letterspacing1 gray paddingTop5">HISTORIAL DE CRÉDITO</p>
            </div>
            <div class="col9 col12-mob floatLeft">
                <p class="floatLeft font14 fontWeight500 letterspacing1 gray paddingTop5">Ponderación Area</p>
                <input class="block floatLeft marginLeft35 cameraBox width150 height30" type="text" name="Ponderación Area" value="">
            </div>
        </div>
        <div class="clearFix">
            <div class="col6 col12-mob floatLeft clearFix marginTop25 marginBottom25">
                <p class="gray font14 fontWeight500 center paddingBottom20">RANGO DE SCORE EN BURÓ DE CRÉDITO</p>
                <div class="col6 floatLeft">
                    <div class="paddingTop20">
                        <p class="gray font12 fontWeight500 latterspacing1 center">VARIABLES</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center" >BC Score < 500</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center" >BC Score < 600</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center" >BC Score < 700</p>
                    </div>
                </div>
                <div class="col6 floatLeft">
                    <div class="paddingTop20">
                        <p class="gray font12 fontWeight500 latterspacing1">PONDERACIÓN ESPECÍFICA</p>
                    </div>
                    <div class="paddingTop15">
                        <input class="block cameraBox width150 height30" type="text" name="BC_SCORE<500" value="">
                    </div>
                    <div class="paddingTop5">
                        <input class="block cameraBox width150 height30" type="text" name="BC_Score<600" value="">
                    </div>
                    <div class="paddingTop5">
                        <input class="block cameraBox width150 height30" type="text" name="BC_Score<700" value="">
                    </div>
                </div>
            </div>
            <div class="col6 col12-mob floatLeft clearFix marginTop25 marginBottom25">
                <p class="gray font14 fontWeight500 letterspacing1 center paddingBottom20">MOP MÁS ALTO</p>
                <div class="col6 floatLeft">
                    <div class="paddingTop20">
                        <p class="gray font12 fontWeight500 latterspacing1 center">VARIABLES</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">MOP 1 </p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">MOP 2 </p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">MOP 3 </p>
                    </div>
                </div>
                <div class="col6 floatLeft">
                    <div class="paddingTop20">
                        <p class="gray font12 fontWeight500 latterspacing1">PONDERACIÓN ESPECÍFICA</p>
                    </div>
                    <div class="paddingTop15">
                        <input class="block cameraBox width150 height30" type="text" name="mop1" value="">
                    </div>
                    <div class="paddingTop5">
                        <input class="block cameraBox width150 height30" type="text" name="mop2" value="">
                    </div>
                    <div class="paddingTop5">
                        <input class="block cameraBox width150 height30" type="text" name="mop3" value="">
                    </div>
                </div>
            </div>
        </div>
        <div class="marginTop25 marginBottom25 clearFix">
            <div class="col6 col12-mob floatLeft clearFix">
                <p class="gray font14 fontWeight500 center paddingBottom20">SALDO VENCIDO ACEPTADO</p>
                <div class="col6 floatLeft">
                    <div class="paddingTop20">
                        <p class="gray font12 fontWeight500 latterspacing1 center">VARIABLES</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">Saldo Vencido < 0</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">Saldo Vencido < 500</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">Saldo Vencido < 1000</p>
                    </div>
                </div>
                <div class="col6 floatLeft">
                    <div class="paddingTop20">
                        <p class="gray font12 fontWeight500 latterspacing1">PONDERACIÓN ESPECÍFICA</p>
                    </div>
                    <div class="paddingTop15">
                        <input class="block cameraBox width150 height30" type="text" name="saldo_vencido<0" value="">
                    </div>
                    <div class="paddingTop5">
                        <input class="block cameraBox width150 height30" type="text" name="saldo_vencido<500" value="">
                    </div>
                    <div class="paddingTop5">
                        <input class="block cameraBox width150 height30" type="text" name="saldo_vencido<1000" value="">
                    </div>
                </div>
            </div>
            <div class="col6 col12-mob floatLeft clearFix marginTop25 marginBottom25">
                <p class="gray font14 fontWeight500 letterspacing1 center paddingBottom20">CRÉDITO MÁS ALTO</p>
                <div class="col6 floatLeft">
                    <div class="paddingTop20">
                        <p class="gray font12 fontWeight500 latterspacing1 center">VARIABLES</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">Crédito >= 100%</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">Crédito >= 30%</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">Crédito >= 0%</p>
                    </div>
                </div>
                <div class="col6 floatLeft">
                    <div class="paddingTop20">
                        <p class="gray font12 fontWeight500 latterspacing1">PONDERACIÓN ESPECÍFICA</p>
                    </div>
                    <div class="paddingTop15">
                        <input class="block cameraBox width150 height30" type="text" name="credito>=100%" value="">
                    </div>
                    <div class="paddingTop5">
                        <input class="block cameraBox width150 height30" type="text" name="credito>=30%" value="">
                    </div>
                    <div class="paddingTop5">
                        <input class="block cameraBox width150 height30" type="text" name="credito>=0%" value="">
                    </div>
                </div>
            </div>
        </div>
        <div class="marginTop25 marginBottom25 clearFix">
            <div class="col6 col12-mob floatLeft clearFix">
                <p class="gray font14 fontWeight500 center paddingBottom20">TIEMPO DESDE EL ULTIMO ATRAZO</p>
                <div class="col6 floatLeft">
                    <div class="paddingTop20">
                        <p class="gray font12 fontWeight500 latterspacing1 center">VARIABLES</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">30 Días</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">60 Días</p>
                    </div>
                    <div class="paddingTop20">
                        <p class="gray font14 fontWeight500 latterspacing1 center">90 Días</p>
                    </div>
                </div>
                <div class="col6 floatLeft">
                    <div class="paddingTop20">
                        <p class="gray font12 fontWeight500 latterspacing1">PONDERACIÓN ESPECÍFICA</p>
                    </div>
                    <div class="paddingTop15">
                        <input class="block cameraBox width150 height30" type="text" name="30_dias" value="">
                    </div>
                    <div class="paddingTop5">
                        <input class="block cameraBox width150 height30" type="text" name="60_dias" value="">
                    </div>
                    <div class="paddingTop5">
                        <input class="block cameraBox width150 height30" type="text" name="90_dias" value="">
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<section class="container clearFix marginTop30 marginBottom84">
    <div class="width990 autoMargin solicitudWhiteBox clearFix paddingBottom40">
        <div class="padding20 clearFix borderGrayBottom">
            <div class="col3 col12-mob floatLeft">
                <p class="floatLeft font14 fontWeight500 letterspacing1 gray paddingTop5">DATOS CUALITATIVOS</p>
            </div>
            <div class="col9 col12-mob floatLeft">
            </div>
        </div>
    </div>
</section>