<section class="container paddingTop20 paddingBottom20 clearFix contentHeight serializeForm">
    <div class="defaultBubble">
        <div class="container clearFix relative autoMargin width920">
            <div class="infoBox floatLeft desktop">
                <div class="width529 autoMargin">
                    <p class="displayInline textUpper floatLeft center letterspacing0.5 font13 paddingTop20 paddingRight10 paddingBottom10">llena tu formulario en menos de 1 minuto,</p>
                    <div class="blueBox floatRight marginTop10">
                        <p class="textUpper colorWhite font13 paddingTop10 paddingRight10 paddingLeft15 paddingBottom5">conecta tu facebook</p>
                    </div>
                </div>
            </div>
            <div class="line18 floatLeft desktop "></div>
            <div class="crosCircle floatLeft desktop ">
                <p class="center cross"><i class="fa fa-times" aria-hidden="true"></i></p>
            </div>
        </div>
    </div>
    <div class="successBubble hide">
        <div class="container clearFix relative autoMargin width920">
            <div class="infoBoxGreen floatLeft">
                <div class="infoContainer4c">
                    <p class="center letterspacing0.5 font13 paddingLeft15 paddingTop15 paddingBottom10 colorWhite marginTop5">TUS DATOS SE PRE-LLENARON EXITOSAMENTE, PORFAVOR VERIFICA QUE TU INFORMACIÓN SEA CORRECTA</p>
                </div>
            </div>
            <div class="line18 colorGreen floatLeft"></div>
            <div class="crosCircle floatLeft colorGreen">
                <p class="center marginTop5 font12 colorWhite"><i class="fa fa-times" aria-hidden="true"></i></p>
            </div>
        </div>
    </div>
    <div class="padding15 step1 formStep">
        <p class="headingColor font35 marginTop5 letterspacing1">Cuentanos tu historia:</p>
        <p class="font35 marginTop30 letterspacing1 formTitleColor lineHeight60">
            <span class="showOnFill">Mi nombre es
                <input type="text" class="inputsFormulario width200 formValues headingColor" style="text-align:center;" id="nombre" name="nombre" placeholder="Jorge"> <input type="text" style="text-align:center;" class="inputsFormulario width200 formValues headingColor" id="apellidoPaterno" name="apellidoPaterno" placeholder="Perez"> <input type="text" style="text-align:center;" class="inputsFormulario width200 formValues headingColor" id="apellidoMaterno" name="apellidoMaterno" placeholder="Lopez">
            </span>
            <span class="hide showOnFill">y nací el&nbsp;

                <span class="width70 inline selectWrap">
                    <select style="text-align:center;" class="formulariOptions gray formValues" name="dia" id="dia">
                        <option  selected="selected" disabled>día</option>
                        <option value="01">01</option>
                        <option value="02">02</option>
                        <option value="03">03</option>
                        <option value="04">04</option>
                        <option value="05">05</option>
                        <option value="06">06</option>
                        <option value="07">07</option>
                        <option value="08">08</option>
                        <option value="09">09</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="13">13</option>
                        <option value="14">14</option>
                        <option value="15">15</option>
                        <option value="16">16</option>
                        <option value="17">17</option>
                        <option value="18">18</option>
                        <option value="19">19</option>
                        <option value="20">20</option>
                        <option value="21">21</option>
                        <option value="22">22</option>
                        <option value="23">23</option>
                        <option value="24">24</option>
                        <option value="25">25</option>
                        <option value="26">26</option>
                        <option value="27">27</option>
                        <option value="28">28</option>
                        <option value="29">29</option>
                        <option value="30">30</option>
                        <option value="31">31</option>
                    </select>
                </span>
                <span class="afterSelect">
                    <i class="fa fa-caret-down" aria-hidden="true"></i>
                </span>de&nbsp;
                <span class="width200 inline selectWrap">
                    <select style="text-align:center;" class="formulariOptions gray formValues" id="mes" name="mes">
                        <option  selected="selected" disabled>Mes</option>
                        <option value="01">Enero</option>
                        <option value="02">Febrero</option>
                        <option value="03">Marzo</option>
                        <option value="04">Abril</option>
                        <option value="05">Mayo</option>
                        <option value="06">Junio</option>
                        <option value="07 ">Julio</option>
                        <option value="08">Agosto</option>
                        <option value="09">septiembre</option>
                        <option value="10">Octubre </option>
                        <option value="11">Noviembre</option>
                        <option value="12">Diciembre </option>
                    </select>
                </span>
                <span class="afterSelect">
                    <i class="fa fa-caret-down" aria-hidden="true"></i>
                </span>
                de&nbsp;  <input type="number" style="text-align:center;" class="inputsFormulario width100 formValues" id="anio" name="anio" placeholder="Año">.
            </span>
            <span class="hide showOnFill show2"> Soy de nacionalidad
                <span class="width240 inline selectWrap">
                    <g:select style="text-align:center;" class="formulariOptions gray formValues" name="nacionalidad" from="${nacionalidadList}" optionKey="id" />
                </span>
                <span class="afterSelect">
                    <i class="fa fa-caret-down" aria-hidden="true"></i>
                </span>y soy&nbsp;
                <span class="width225 inline selectWrap">
                    <g:select style="text-align:center;" class="formulariOptions gray formValues" name="estadoCivil" from="${estadoCivilList}" optionKey="id" />
                </span>
                <span class="afterSelect">
                    <i class="fa fa-caret-down" aria-hidden="true"></i>
                </span>
            </span>
        </p>

        <div class="confirmDiv hide col7 col12-tab floatRight marginTop28 clearFix">
            <div class="floatLeft marginBottom20">
                <p class="font25 marginTop5 headingColor marginRight10"> Mi información es correcta</p>
            </div>
            <div class="clearFloat mobile"></div>
            <div class="buttonM mobileAuto lightBlueBg floatLeft colorWhite textUpper letterspacing0.8 radius100">
                confirmar
            </div>
        </div>

    </div>

    <div class="padding15 formStep hide lastStep">
        <p class="font35 marginTop28 letterspacing1 formTitleColor lineHeight60"><span class="hide showOnFill">Mi nivel escolar es
                <input type="text" class="inputsFormulario width200 formValues" name="nivelEscolar" placeholder="universitario"></span><span class="hide showOnFill"> y mi número telefónico es el
                <input type="text" class="inputsFormulario width210 formValues" name="telefono" placeholder="(55)4185 2233)"></span><span class="hide showOnFill"> y mi celular es el
                <input type="text" class="inputsFormulario width210 formValues" name="celular" placeholder="(55)2345 2345)">.</span>
        </p>
        <div class="confirmDiv hide col7 col12-tab floatRight marginTop28 clearFix">
            <div class="floatLeft marginBottom20">
                <p class="font25 marginTop5 headingColor marginRight10"> Mi información es correcta</p>
            </div>
            <div class="clearFloat mobile"></div>
            <div class="buttonM mobileAuto lightBlueBg floatLeft colorWhite textUpper letterspacing0.8 radius100">
                confirmar
            </div>
        </div>
    </div>

</section>
<footer class="footerContainer">
    <form class="sendValues" name="formPaso1" id="formPaso1" method="post">
        <input type="hidden" name="siguientePaso" id="siguientePaso" value="2">
    </form>
    <div class="width600 clearFix desktop tablet">
        <div class="blueCircle center floatLeft colorWhite">
            <p class="paddingTop10 font18">1</p>
        </div>
        <div class="floatLeft line60"></div>
        <div class="rectangle250 center floatLeft nextBtn">
            <p class="textUpper footerTextColor font18 paddingTop10">ir al paso 2</p>
        </div>
        <div class="line20 floatLeft"></div>
        <div class="grayCircle center floatLeft">
            <p class="paddingTop5 footerTextColor font18">3</p>
        </div>
        <div class="line20 floatLeft"></div>
        <div class="grayCircle center floatLeft">
            <p class="paddingTop5 footerTextColor font18">4</p>
        </div>
        <div class="line20 floatLeft"></div>
        <div class="grayCircle center floatLeft">
            <p class="paddingTop5 footerTextColor font18">5</p>
        </div>
        <div class="line20 floatLeft"></div>
        <div class="grayCircle center floatLeft">
            <p class="paddingTop5 footerTextColor font18">6</p>
        </div>
    </div>
    <div class="mobile">
        <div class="paddingAside15 clearFix">
            <div class="grayrectangle floatLeft marginRight10">Atras</div>
            <div class="grayrectangle floatLeft nextBtn">Ir al paso 2</div>
        </div>
    </div>
</footer>
<script>
    $(document).ready(function() {
        if("${tipoLogin}" == 'FB'){
            fillFB("${datosLogin.encodeAsJSON()}");
        }else if("${tipoLogin}" == 'Google'){
            fillGoogle("${datosLogin.encodeAsJSON()}");
        }
    });
</script>