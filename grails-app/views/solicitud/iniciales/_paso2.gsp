<div id="paso2_1" name="paso2_1">
        <form id="form_solicitud_2_1" method="POST" action="test">
          <section class="container formBox">
            <div>
              <p class="font35 marginTop28 letterspacing1 formTitleColor lineHeight60">Mi dirección es:
                <input type="text" class="inputsFormulario width250" name="calle" placeholder="calle" required pattern="^[A-Za-z ]{2,50}$" >,
                <input type="text" class="inputsFormulario width200" name="noexterior" placeholder="No.Exterior" required pattern="^[A-Za-z0-9 ]{2,5}$">,
                <input type="text" class="inputsFormulario width200" name="nointerior" placeholder="No.Interior" pattern="^[A-Za-z0-9]{2,5}$">. Mi código postal es
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
