<!DOCTYPE html>
<html lang="en">
<head>
    <title>Solicitud</title>
    <meta name="layout" content="solicitud"/>
    <script src="../js/funciones_utils.js"></script>
    <script>
        $(document).ready(function() {
        		  setPaso('1');
        		  if("${tipo_login}" == 'FB'){
        		    fillFB("${datos_login.encodeAsJSON()}");
        		  }else if("${tipo_login}" == 'Google'){
        		    fillGoogle("${datos_login.encodeAsJSON()}");
        		  }
        		});
    </script>
</head>
<body>
 <header class="formularioHeader clearFix">
                  <div class="container clearFix">
                    <img class="logo floatLeft" src="../images/kosmos-logo.png" alt="Logo" title="Logo" />
                      <div class="floatingHeader floatRight clearFix">
                        <div class="floatRight clearFix">
                          <div id="IMAGEN_USUARIO" name="IMAGEN_USUARIO" class="userPicture floatLeft "></div>
                          <p id="USUARIO" name="USUARIO" class="userName marginTop28 paddingRight5 marginBottom27 floatLeft">Bienvenido</p>
                          <div class="dropBox floatLeft marginTop28 marginLeft5">
                              <i class="fa fa-angle-down paddingTop2 paddingRight2 paddingLeft5" aria-hidden="true"></i>
                          </div>
                        </div>
                        <div class="floatRight width337 borderGrayRight borderGrayLeft paddingBottom18 paddingTop19">
                            <div class="urlBox autoMargin">
                              <p class="letterspacing0.8 font14 gray paddingTop10 paddingRight36 paddingBottom10 paddingLeft25 center ">TU URL: KSM.os/ER45</p>
                            </div>
                        </div>
                        <div class="salvadoConatiner floatRight">
                          <p class="salvadoTitle floatLeft paddingTop20 paddingBottom15">salvado automatico</p>
                          <img class="floatLeft paddingTop20 paddingLeft16 paddingRight10 paddingBottom15" src="../images/cloud.png" alt="cloud" title="cloud"/>
                        </div>
                      </div>
                  </div>
                </header>


 <section class="pasoBanner clearFix">
                   <div class="container">
                     <div class="floatLeft borderGrayRight">
                       <div class="marginTop14 marginBottom14 marginRight95" id="headerPaso">
                         <p class="gray textUpper letterspacing1 font16" id="pasoNumero" name="pasoNumero"></p>
                         <p class="headingColor textUpper letterspacing1.5 font25 paddingRight15 paddingBottom8" id="pasoTitulo" name="pasoTitulo"></p>
                       </div>
                     </div>
                     <div class="floatRight">
                       <p class="progresoTitle textUpper marginLeft10 floatLeft">tu progreso en la aplicación</p>
                       <div class="floatLeft clearFix">
                           <div id="progreso_barra" name="progreso_barra"  class="width24 borderBlue marginTop38 floatLeft"></div>
                           <div class="width36 gradientBlue radius100 paddingTop2 paddingBottom2 paddingRight2 paddingLeft1 marginTop27 floatLeft center">
                           <p   id="progreso_numero" name="progreso_numero" class="colorWhite displayInline center paddingTop2 paddingRight5 paddingBottom2 paddingLeft5 font12">5 %</p>
                           </div>
                           <div class="width400 borderGray marginTop38 floatLeft"></div>
                           <div class="circle floatLeft"></div>
                       </div>
                     </div>
                   </div>
                 </section>
                 <section class="container clearFix">
                   <!--div class="infoBox marginTop15 floatLeft">
                     <div class="width529 autoMargin">
                       <p class="displayInline textUpper floatLeft center letterspacing0.5 font13 paddingTop20 paddingRight10 paddingBottom10">llena tu formulario en menos de 1 minuto,</p>
                       <div class="blueBox floatRight marginTop10">
                         <p class="textUpper colorWhite font13 paddingTop10 paddingRight10 paddingLeft15 paddingBottom5">conecta tu facebook</p>
                       </div>
                     </div>
                     </div>
                     <div class="line18 floatLeft"></div>
                     <div class="crosCircle floatLeft">
                       <p class="center cross"><i class="fa fa-times" aria-hidden="true"></i></p>
                     </div-->
  </section>

  <section class="container formBox">
    <div id="paso1_1" name="paso1_1">
        <form id="form_solicitud_1_1" method="POST" action="test">
        <div>
          <p class="headingColor font35 marginTop28 letterspacing1">Cuentanos tu historia:</p>
          <p class="font35 marginTop28 letterspacing1 formTitleColor lineHeight60">Mi nombre es
            <input name="NOMBRE_USUARIO" id="NOMBRE_USUARIO" type="text" class="inputsFormulario width300" name="name" placeholder="Jorge Perez Lopez" required pattern="^[A-Za-z0-9 ]{2,50}$"> y nací el

            <select name="DIA" id="DIA" class="formulariOptions blue width100" required>
              <option value="1">1 </option>
              <option value="2">2 </option>
              <option value="3">3 </option>
              <option value="4">4 </option>
              <option value="5">5 </option>
              <option value="6">6 </option>
              <option value="7">7 </option>
              <option value="8">8 </option>
              <option value="9">9 </option>
              <option value="10">10 </option>
              <option value="11">11 </option>
              <option value="12">12 </option>
              <option value="13">13 </option>
              <option value="14">14 </option>
              <option value="15">15 </option>
              <option value="16">16 </option>
              <option value="17">17 </option>
              <option value="18">18 </option>
              <option value="19">19 </option>
              <option value="20">20 </option>
              <option value="21">21 </option>
              <option value="22">22 </option>
              <option value="23">23 </option>
              <option value="24">24 </option>
              <option value="25">25 </option>
              <option value="26">26 </option>
              <option value="27">27 </option>
              <option value="28">28 </option>
              <option value="29">29 </option>
              <option value="30">30 </option>
              <option value="31">31 </option>
            </select>
          de
            <select name="MES" id="MES" class="formulariOptions blue width200" required >
              <option value="01"> Enero</option>
              <option value="02"> Febrero</option>
              <option value="03"> Marzo</option>
              <option value="04"> Abril</option>
              <option value="05"> Mayo</option>
              <option value="06"> Junio</option>
              <option value="07"> Julio</option>
              <option value="08"> Agosto</option>
              <option value="09"> Septiembre</option>
              <option value="10"> Octubre</option>
              <option value="11"> Noviembre</option>
              <option value="12"> Diciembre</option>
            </select>
        de <input name="ANIO"  id="ANIO" type="text" class="inputsFormulario width100" name="year" placeholder="1984" required pattern="^\d{4}$">. Soy de nacionalidad
            <select name="nacionalidad" class="formulariOptions blue  paddingRight10" required pattern="^[A-Za-z ]{2,50}$">
              <g:each var="nacionalidad" in="${nacionalidadList}">
                <option value="${nacionalidad.id}"> ${nacionalidad.nombre}</option>
              </g:each>

            </select>
        y soy
            <select name="ESTADO_CIVIL" class="formulariOptions blue width150" required>
                <g:each var="estadoCivil" in="${estadoCivilList}">
                    <option value="${estadoCivil.nombre}"> ${estadoCivil.nombre}</option>
                </g:each>
            </select>

          </p>
        </div>
        <div class="width520 marginTop28 floatRight" id="confirmar_datos1_1">
              <div>
                <p class="font25 floatLeft marginTop5 headingColor"> Mi informaci&oacute;n es correcta</p>
              </div>
              <a href="#" onclick="validar1_1();" class="ahref" id="alinkPaso1_1" name="alinkPaso1_1">
                  <div class="Button floatRight">
                    <p class="colorWhite textUpper letterspacing0.8 paddingTop10 center">confirmar</p>
                  </div>
              </a>
        </div>
        </form>
    </div>
    <div id="paso1_2" name="paso1_2">
        <form id="form_solicitud_1_2" method="POST" action="test">
        <section class="container formBox">
              <div>
                <p class="font35 marginTop28 letterspacing1 formTitleColor lineHeight60">Mi nivel escolar es
                  <input type="text" class="inputsFormulario width200" name="nivelescolar" placeholder="Universitario" required pattern="^[A-Za-z ]{4,50}$"> y mi número telefónico es el
                  <input type="text" class="inputsFormulario width210" name="telephone" placeholder="(55)4185 2233)" required pattern="^\d{10}$" title="Teclee solo digitos"> y mi celular es el
                  <input type="text" class="inputsFormulario width210" name="cellphone" placeholder="(55)2345 2345)" required pattern="^\d{10}$" title="Teclee solo digitos">.
                </p>
              </div>
        </section>
        <div class="width520 marginTop28 floatRight" id="confirmar_datos1_2">
                      <div>
                        <p class="font25 floatLeft marginTop5 headingColor"> Mi informaci&oacute;n es correcta</p>
                      </div>
                      <a href="#" onclick="validar1_2();" class="ahref" id="alinkPaso1_2" name="alinkPaso1_2">
                          <div class="Button floatRight">
                            <p class="colorWhite textUpper letterspacing0.8 paddingTop10 center">confirmar</p>
                          </div>
                      </a>
        </div>
        </form>
    </div>

    <div id="paso2_1" name="paso2_1">
        <form id="form_solicitud_2_1" method="POST" action="test">
          <section class="container formBox">
            <div>
              <p class="font35 marginTop28 letterspacing1 formTitleColor lineHeight60">Mi dirección es:
                <input type="text" class="inputsFormulario width250" name="calle" placeholder="calle" required pattern="^[A-Za-z ]{2,50}$" >,
                <input type="text" class="inputsFormulario width200" name="noexterior" placeholder="No.Exterior" required pattern="^[A-Za-z0-9 ]{2,5}$">,
                <input type="text" class="inputsFormulario width200" name="nointerior" placeholder="No.Interior" pattern="^[A-Za-z0-9 ]{2,5}$">. Mi código postal es
                <input type="text" class="inputsFormulario width100" name="codigopostal" placeholder="00001" required pattern="^\d{5}$"> y está  ubicado en La Colonia


                <g:select class="inputsFormulario" optionKey="id" optionValue="nombre" name="colonia" from="${coloniaList}" />
                <!--input type="text" class="inputsFormulario width200" name="colonia" placeholder="Mi Colonia" required pattern="^[A-Za-z0-9 ]{2,50}$"-->, Mi
                <select class="formulariOptions width220 blue paddingRight10" required>
                  <option value="Delegación">Delegación</option>
                  <option value="Municipio">Municipio</option>
                </select>
                es
                <g:select class="inputsFormulario" optionKey="id" optionValue="nombre" name="delegacion" from="${municipioList}" />
                <!--input type="text" class="inputsFormulario width300" name="delegacion" placeholder="delegación" required-->
                y estoy en el estado de
                <g:select class="inputsFormulario"  optionKey="id" optionValue="nombre" name="estado" from="${estadoList}" />
                <!--input type="text" class="inputsFormulario" name="estado" placeholder="Mi estado" required-->.
              </p>
              <div class="floatRight">
                <div class="ButtonGray floatRight">
                  <p class="colorWhite textUpper letterspacing0.8 paddingTop15 center">ver mapa</p>
                </div>
              </div>
            </div>
          </section>
        </form>
    </div>

    <div id="paso3_1" name="paso3_1">
        <form id="form_solicitud_3_1" method="POST" action="test">
        <section class="container formBox">
            <div>
              <p class="font35 marginTop28 letterspacing1 formTitleColor lineHeight60">Trabajo en
                <input type="text" class="inputsFormulario width150" name="empresa" placeholder="Empresa" required pattern="^[A-Za-z0-9. ]{2,50}$">,
                y mi puesto es
                <input type="text" class="inputsFormulario width200" name="puesto" placeholder="Puesto" required pattern="^[A-Za-z0-9 ]{2,50}$">,
                llevo trabajando en esta empresa desde hace
                <select name="periodo" class="formulariOptions width100 blue paddingRight10" required>
                  <option value="1">1</option>
                  <option value="2">2</option>
                  <option value="3">3</option>
                  <option value="4">4</option>
                  <option value="5">5</option>
                  <option value="6">6</option>
                  <option value="7">7</option>
                  <option value="8">8</option>
                  <option value="9">9</option>
                  <option value="10">10</option>
                  <option value="11">11</option>
                  <option value="12">12</option>
                </select>
                <select name="unidad_periodo" class="formulariOptions width200 blue paddingRight10" required>
                  <option value="semana">Semana(s)</option>
                  <option value="Mes">Mes(es)</option>
                  <option value="Anio">Año(s)</option>
                </select>
                y mi tipo de contrato es
                <g:select class="inputsFormulario"  optionKey="id" optionValue="nombre" name="contrato" from="${temporalidadList}" />
                <!--input type="text" class="inputsFormulario width150" name="contrato" placeholder="Contrato" required pattern="^[A-Za-z ]{2,50}$"-->.
              </p>
            </div>
          </section>
        </form>
    </div>

  </section>
  <footer class="footerContainer">
    <div class="width600 clearFix">
      <a href="#" onclick="setPaso('1');" class="ahref" id="alinkIr1" name="alinkIr1">
          <div id="button_ir1" class="blueCircle center floatLeft colorWhite">
            <p id="button_ir1Texto" class="textUpper paddingTop10 font18">1</p>
          </div>
      </a>
      <div class="floatLeft line60"></div>
      <!--a href="#" onclick="document.forms[0].submit();return false;" class="ahref" id="alinkIrA" name="alinkIrA"-->
      <a href="#" onclick="setPaso('2');" class="ahref" id="alinkIr2" name="alinkIr2">
          <div  id="button_ir2" class="grayCircle center floatLeft">
            <p  id="button_ir2Texto" class="textUpper footerTextColor font18 paddingTop10">Ir al paso 2</p>
          </div>
      </a>
      <div class="line20 floatLeft"></div>
       <a href="#" onclick="validar2_1();" class="ahref" id="alinkIr3" name="alinkIr3">
          <div id="button_ir3" class="grayCircle center floatLeft">
            <p id="button_ir3Texto" class="textUpper paddingTop10 footerTextColor font18">3</p>
          </div>
      </a>
      <div class="line20 floatLeft"></div>
        <a href="#" onclick="validar3_1();" class="ahref" id="alinkIr4" name="alinkIr4">
          <div id="button_ir4" class="grayCircle center floatLeft">
            <p id="button_ir4Texto"class="textUpper paddingTop10 footerTextColor font18">4</p>
          </div>
        </a>
      <div class="line20 floatLeft"></div>
      <div class="grayCircle center floatLeft">
        <p  id="button_ir5Texto" class="textUpper paddingTop10 footerTextColor font18">5</p>
      </div>
      <div class="line20 floatLeft"></div>
      <div class="grayCircle center floatLeft">
        <p id="button_ir6Texto" class="textUpper paddingTop10 footerTextColor font18">6</p>
      </div>
    </div>
  </footer>
</body>
</html>