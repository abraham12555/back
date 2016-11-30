<!DOCTYPE html>
<html>
    <head>
        <title>Kosmos - Cotizador</title>
    </head>

    <body>
        <div class="container clearFix">
            <div class="col6 col12-mob coti floatLeft down800">
                <div class="padding15">
                    <h1 id="nombreDelProducto" class="headingColor font35 fontWeight900 letterspacing1 lato">
                        NISSAN MAXIMA 2016
                    </h1>
                    <h3 id="precioDelProducto" class="darkBluetitle font30 fontWeight400 marginBottom20">
                    </h3>
                    <div id="imagenDelProducto" class="cotizador-bg none800">
                        <div class="cotizador-bae"></div>
                    </div>
                    <div class="paddingAside10">
                        <p id="descripcionDelProducto" class="darkBluetitle font14 fontWeight400 letterspacing1 justify">
                            Fusce vehicula dolor arcu, sit amet blandit dolor mollis nec.
                            Donec viverra eleifend lacus, vitae ullamcorper metus. Sed
                            sollicitudin ipsum quis nunc sollicitudin ultrices. Donec
                            euismod scelerisque ligula. Maecenas eu varius risus, eu
                            aliquet arcu. Curabitur fermentum suscipit est, tincidunt
                            mattis lorem luctus id.
                        </p>
                    </div>
                </div>
            </div>
            <div class="col6 col12-mob floatLeft down800">
              <div class="cotWrap">
                <h1 class="darkBluetitle font30 fontWeight400 marginBottom95">Cotiza tu Auto</h1>
                  <g:form controller="cotizador" action="procesar" class="cotizadorForm">
                      <!-- BEGIN STEP1 -->
                      <div class="cotizadorStep" data-step="1">
                        <div class="summaryDiv">
                          <div class="borderGrayTop borderGrayBottom clearFix elige-un-modelo">
                              <div class="col7 floatLeft">
                                  <div class="floatLeft paddingTop18 marginLeft24">
                                      <p class="formTitleColor font12 paddingBottom12">1. Elige un auto</p>
                                      <p id="productoElegido" class="formTitleColor font19 marginBottom15"></p>
                                  </div>
                              </div>
                              <div class="col5 floatRight">
                                  <div class="floatRight paddingTop18 marginBottom16">
                                      <div class="borderGrayButton marginBottom15">
                                          <p class="center paddingTop9 paddingBottom9 cambiar">Cambiar</p>
                                      </div>
                                  </div>
                              </div>
                          </div>
                        </div>

                          <div class="cotizador-rightside actionsDiv">

                              <div class="cotizador-box-container padding20">
                                  <h1 class="darkBluetitle font20 fontWeight400 letterspacing1 marginBottom20">
                                    <span class="visibleMob">1.</span>Elige un Auto
                                  </h1>

                                  <div class="cotizador-p1-buttons clearFix">
                                      <g:each in="${productos}" var="producto" status="i">
                                          <div class="col6 floatLeft marginBottom10">
                                              <div class="paddingAside5">
                                                  <p data-id="${producto.id}" class="width350 nextAction cotizador-box">${producto.nombreDelProducto}</p>
                                              </div>
                                          </div>
                                          <g:if test="${i%2 == 1}">
                                            <div class="clearFloat"></div>
                                          </g:if>
                                      </g:each>
                                  </div>

                                  <div class="marker">
                                      <p>1</p>
                                  </div>
                              </div>
                          </div>
                          <div class="stepShadow"></div>
                      </div>
                      <!-- ENF STEP1 -->
                      <!-- BEGIN STEP2 -->
                      <div class="cotizadorStep" data-step="2">
                          <div class="summaryDiv">
                            <div class="borderGrayBottom paddingBottom15 clearFix elige-un-modelo">
                                <div class="col7 floatLeft">
                                    <div class="floatLeft paddingTop18 marginLeft24">
                                        <p class="formTitleColor font12 paddingBottom12">2. Elige un Modelo</p>

                                        <p id="modeloElegido" class="formTitleColor font19 marginBottom15"></p>
                                    </div>
                                </div>

                                <div class="col5 floatRight">
                                    <div class="floatRight paddingTop18 marginBottom15">
                                        <div class="borderGrayButton marginBottom15">
                                            <p class="center paddingTop10 paddingBottom10 cambiar">Cambiar</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                          </div>
                          <div class="cotizador-rightside actionsDiv">

                              <div class="cotizador-box-container padding20 borderGrayTop">
                                  <h1 class="relative100 darkBluetitle font20 fontWeight400 letterspacing1 marginBottom20">
                                    <span class="visibleMob">2.</span>Elige un modelo
                                  </h1>
                                  <div id="modelosList" class="cotizador-p1-buttons clearFix">
                                    Aqui van los modelos (ajax)
                                  </div>
                                  <div class="marker">
                                      <p>2</p>
                                  </div>
                              </div>
                          </div>
                          <div class="stepShadow"></div>
                      </div>
                      <!-- END STEP 2-->
                      <!-- BEGIN STEP 3-->
                      <div class="cotizadorStep" data-step="3">
                        <div class="summaryDiv">
                          <div class="borderGrayBottom paddingBottom15 clearFix elige-un-modelo">
                              <div class="col7 floatLeft">
                                  <div class="floatLeft paddingTop18 marginLeft24">
                                      <p class="formTitleColor font12 paddingBottom12">3. Elige un Color</p>
                                      <div class="floatLeft">
                                          <p id="colorElegido" class="formTitleColor font19 floatLeft marginBottom15"></p>
                                      </div>
                                  </div>
                              </div>
                              <div class="col5 floatRight">
                                  <div class="floatRight paddingTop18 marginBottom15">
                                      <div class="borderGrayButton marginBottom15">
                                          <p class="center paddingTop10 paddingBottom10 cambiar">Cambiar</p>
                                      </div>
                                  </div>
                              </div>
                          </div>
                        </div>
                          <div class="cotizador-rightside actionsDiv">
                              <div class="cotizador-box-container padding20">
                                  <h1 class="darkBluetitle font20 fontWeight400 letterspacing1 marginBottom20">
                                    <span class="visibleMob">3.</span>Elige un Color
                                  </h1>
                                  <div id="coloresList" class="cotizador-p1-buttons clearFix">
                                    Aqui van los colores
                                  </div>
                                  <div class="marker"><p>3</p></div>
                              </div>
                          </div>
                          <div class="stepShadow"></div>
                      </div>
                      <!-- END STEP 3 -->
                      <!-- BEGIN STEP 4 -->
                      <div class="cotizadorStep" data-step="4">
                        <div class="summaryDiv">
                          <div class="borderGrayBottom paddingBottom15 clearFix elige-un-modelo">
                              <div class="col7 floatLeft">
                                  <div class="floatLeft paddingTop18 marginLeft24">
                                      <p class="formTitleColor font12 paddingBottom12">4. Tu anganche</p>

                                      <p id="engancheElegido" class="formTitleColor font20 marginBottom15"></p>
                                  </div>
                              </div>

                              <div class="col5 floatRight">
                                  <div class="floatRight paddingTop18 marginBottom15">
                                      <div class="borderGrayButton marginBottom15">
                                          <p class="center paddingTop10 paddingBottom10 cambiar">Cambiar</p>
                                      </div>
                                  </div>
                              </div>
                          </div>
                        </div>
                          <div class="cotizador-rightside actionsDiv">

                              <div class="cotizador-box-container padding20">
                                  <h1 class="darkBluetitle font20 fontWeight400 letterspacing1 marginBottom20 floatLeft">
                                    <span class="visibleMob">4.</span>Elige tu Enganche
                                  </h1>

                                  <p id="engancheElegido2" class="lightBlue floatRight font20"></p>

                                  <div class="clearFloat"></div>

                                  <div class="cotizador-p1-buttons clearFix">
                                      <div class="marginTop52">

                                          <div class="marginBottom25 clearFix loading-bar-container inner marginBottom40">
                                              <div id="slider" class="loading-bar-line blueButton autoMargin opacity05 ">
                                              </div>

                                              <p class="floatLeft marginLeft12 font14 fontWeight500 gray opacity05 paddingTop5">MIN</p>

                                              <p class="floatRight marginRight12 font14 fontWeight500 gray opacity05 paddingTop5">MAX</p>

                                              <div class="clearFloat"></div>
                                          </div>
                                      </div>
                                      <p data-id="0" class="marginAuto width350 nextAction cotizador-box">Siguiente</p>
                                  </div>

                                  <div class="marker">
                                      <p>4</p>
                                  </div>
                              </div>
                          </div>
                          <div class="stepShadow"></div>
                      </div>
                      <!-- END STEP 4 -->
                      <!-- BEGIN STEP 5 -->
                      <div class="cotizadorStep" data-step="5">
                        <div class="summaryDiv">
                          <div class="borderGrayBottom paddingBottom15 clearFix elige-un-modelo">
                              <div class="col7 floatLeft">
                                  <div class="floatLeft paddingTop18 marginLeft24">
                                      <p class="formTitleColor font12 paddingBottom12">5. Tus Pagos</p>

                                      <p id="pagoElegido" class="formTitleColor font17 marginBottom15"> </p>
                                  </div>
                              </div>

                              <div class="col5 floatRight">
                                  <div class="floatRight paddingTop18 marginBottom15">
                                      <div class="borderGrayButton ">
                                          <p class="center paddingTop10 paddingBottom10 cambiar">Cambiar</p>
                                      </div>
                                  </div>
                              </div>
                          </div>
                        </div>
                          <div class="cotizador-rightside actionsDiv">

                              <div class="cotizador-box-container padding20">
                                  <div class="clearFix">
                                      <h1 class="dark-blue-titles font20 fontWeight400 letterspacing1 marginBottom20 floatLeft">
                                        <span class="visibleMob">5.</span>Elige tus pagos y el plazo.
                                      </h1>
                                  </div>

                                  <div class="cotizador-p1-buttons clearFix">
                                      <div class="payChoice">
                                          <div class="col6 floatLeft marginBottom10">
                                              <div class="paddingAside5">
                                                  <p data-frecuencia="quincenal" class="width350 cotizador-box  frecuencia">Quincenal</p>
                                              </div>
                                          </div>

                                          <div class="col6 floatLeft marginBottom10">
                                              <div class="paddingAside5">
                                                  <p data-frecuencia="mensual" class="width350 cotizador-box frecuencia blueButton">Mensual</p>
                                              </div>
                                          </div>
                                      </div>

                                      <h1 class="dark-blue-titles font20 fontWeight400 letterspacing1 marginBottom20 clearFloat"><span id="pagosleyenda">A cuantos meses</span> pagarás tu auto?</h1>
                                      <script>
                                      var frecbtn = '';
                                      for(i=0; i<4; i++){
                                        frecbtn += '<div class="col3 col6-tab floatLeft marginBottom10">';
                                        frecbtn += '<div class="paddingAside5">';
                                        frecbtn += '<div class="cotizador-box small frecuenciaOpt nextAction">';
                                        frecbtn += '<p class="font18 opacity08 ">';
                                        frecbtn += '<span class="frecuenciaTotal"></span><br/>';
                                        frecbtn += '<span class="frecuenciaTipo"></span>';
                                        frecbtn += '</p>';
                                        frecbtn += '<p class="font14 opacity08 frecuenciaCantidad"></p>';
                                        frecbtn += '</div>';
                                        frecbtn += '</div>';
                                        frecbtn += '</div>';
                                      }
                                      document.write(frecbtn);
                                      </script>
                                  </div>
                                  <div class="marker">
                                      <p>5</p>
                                  </div>
                              </div>
                          </div>
                        <div class="stepShadow"></div>
                      </div>
                      <!-- END STEP 5 -->
                      <!-- BEGIN STEP 6 -->
                      <div class="cotizadorStep" data-step="6">
                        <div class="summaryDiv">
                          <div class="borderGrayBottom paddingBottom10 clearFix elige-un-modelo">
                            <div class="col7 col7-mob floatLeft">
                                <div class="paddingTop10 paddingBottom10 marginLeft24">
                                    <p class="formTitleColor font12 paddingBottom12">6. Seguro</p>

                                    <p id="seguroElegido" class="formTitleColor font20"></p>
                                </div>
                            </div>

                            <div class="col5 floatRight">
                                <div class="floatRight paddingTop18 marginBottom15">
                                    <div class="borderGrayButton ">
                                        <p class="center paddingTop10 paddingBottom10 cambiar">Cambiar</p>
                                    </div>
                                </div>
                            </div>
                          </div>
                        </div>
                          <div class="cotizador-rightside actionsDiv">
                              <div class="cotizador-box-container padding20 marginTop10">
                                  <h1 class="darkBluetitle font20 fontWeight400 letterspacing1 marginBottom20">
                                    <span class="visibleMob">6.</span>Elíge tu seguro
                                  </h1>
                                  <div class="cotizador-p1-buttons clearFix">

                                      <div id="s1"  class="cotizador-box seguro large marginBottom10 nextAction">
                                          <div class="paddingAside10 clearFix">
                                              <div class="floatLeft col6 col12-mob left">
                                                  <p class="fontWeight500 seguroNombre">Amplio Plus</p>

                                                  <p class="paddingTop10 paddingBottom20 font11 fontWeight500">NIVEL DE PROTECCIÓN: COMPLETO</p>
                                              </div>

                                              <div class="floatRight col6 col12-mob right ">
                                                  <p class="fontWeight500 ">$1,400.00</p>

                                                  <p class="paddingTop10 font12 fontWeight500">MXN / MENSUAL COMPLETO</p>
                                              </div>
                                          </div>
                                      </div>

                                      <div id="s2" class="cotizador-box seguro large marginBottom10 nextAction">
                                          <div class="paddingAside10 clearFix">
                                              <div class="floatLeft col6 col12-mob left">
                                                  <p class="fontWeight500 seguroNombre">Básico</p>

                                                  <p class="paddingTop10 paddingBottom20 font11 fontWeight500">NIVEL DE PROTECCIÓN: SUFICIENTE</p>
                                              </div>

                                              <div class="floatRight col6 col12-mob right ">
                                                  <p class="fontWeight500 ">$620</p>

                                                  <p class="paddingTop10 font12 fontWeight500">MXN / MENSUAL COMPLETO</p>
                                              </div>
                                          </div>
                                      </div>

                                      <div id="s3"  class="cotizador-box seguro large marginBottom10 nextAction">
                                          <div class="paddingAside10 clearFix">
                                              <div class="floatLeft col6 col12-mob left">
                                                  <p class="fontWeight500 seguroNombre">Esencial</p>

                                                  <p class="paddingTop10 paddingBottom20 font11 fontWeight500">NIVEL DE PROTECCIÓN: BUENO</p>
                                              </div>

                                              <div class="floatRight col6 col12-mob right ">
                                                  <p class="fontWeight500 ">$270</p>

                                                  <p class="paddingTop10 font12 fontWeight500">MXN / MENSUAL COMPLETO</p>
                                              </div>
                                          </div>
                                      </div>

                                  </div>

                                  <div class="marker higher">
                                      <p>6</p>
                                  </div>
                              </div>
                          </div>
                          <div class="stepShadow"></div>
                      </div>
                      <!-- END STEP 6 -->
                    <g:hiddenField id="txtProducto" name="txtProducto" value=""/>
                    <g:hiddenField id="txtModelo" name="txtModelo" value=""/>
                    <g:hiddenField id="txtColor" name="txtColor" value=""/>
                    <g:hiddenField id="txtEnganche" name="txtEnganche" value=""/>
                    <g:hiddenField id="txtPlazo" name="txtPlazo" value=""/>
                    <g:hiddenField id="txtPago" name="txtPago" value=""/>
                    <g:hiddenField id="txtSeguro" name="txtSeguro" value=""/>
                    <g:hiddenField id="txtPeriodo" name="txtPeriodo" value=""/>
                      <div class="marginTop40 marginBottom20" id="submitCotizador">
                          <div class="paddingAside20">
                              <input type="submit" value="SOLICITAR MI CREDITO"
                                 class="block font25 pointer letterspacing1 blueButton blue-shadow2 padding20 width400 center autoMargin" />
                          </div>
                      </div>
                  </g:form>
              </div>
            </div>
        </div>
    </body>
</html>