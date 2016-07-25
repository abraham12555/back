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
