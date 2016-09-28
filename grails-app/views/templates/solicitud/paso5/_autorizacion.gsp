<div class="whiteContainer lightboxPos identificationLb">
	<h2
		class="floatLeft lbHeader headingColor fontWeight300 letterspacing1.5">Autorización</h2>
	<span
		class="closeModal floatRight borderGray radius100 marginTop15 marginRight15">
		<p
			class="textUpper gray paddingTop7 paddingBottom5 font18 center paddingLeft20 paddingRight20">
			<span class="notMobile paddingRight5">cerrar</span><i
				class="fa fa-times " aria-hidden="true"></i>
		</p>
	</span>

	<div class="idView">
		<div class="border1 clearFloat"></div>
		<div class="border1"></div>
		<div class="padding20">
			<!-- <div class="col9 col12-tab autoMargin clearFix paddingTop50 paddingBottom50 fontWeight500">
			</div>-->
			<div class="padding20">
				<div class="col10 autoMargin">
					<div class="paddingTop20 paddingBottom20">
						<div id="autorizacionForm">
							<div class="col12 col12-mob col floatCenter ">
								<h4 class="headingColor font18 fontWeight400 letterspacing1">Datos Generales</h4>
							</div>

							<div class="col4 col12-mob col floatLeft ">
								<h4 class="gray font16 fontWeight400 letterspacing1">Nombre</h4>
								<p class="font18 gray letterspacing1 textUpper">
									${personales?.nombre}
								</p>
							</div>
							<div class="col4 col12-mob col floatLeft ">
								<h4 class="gray font16 fontWeight400 letterspacing1 ">Apellido
									Paterno</h4>
								<p class="font18 gray letterspacing1 textUpper">
									${personales?.apellidoPaterno}
								</p>
							</div>
							<div class="col4 col12-mob col floatLeft ">
								<h4 class="gray font16 fontWeight400 letterspacing1">Apellido
									Materno</h4>
								<p class="font18 gray letterspacing1 textUpper">
									${personales?.apellidoMaterno}
								</p>
							</div>
							<div class="col4 col12-mob col floatLeft ">
								<h4 class="gray font16 fontWeight400 letterspacing1">Fecha
									de Nacimiento</h4>
								<p class="font18 gray letterspacing1 textUpper">
									${personales?.dia}
									/
									${personales?.mes}
									/
									${personales?.anio}
								</p>
							</div>
							<div class="col4 col12-mob col floatLeft ">
								<h4 class="gray font16 fontWeight400 letterspacing1">RFC</h4>
								<p class="font18 gray letterspacing1 textUpper">
									${personales?.rfc}
								</p>
							</div>

							<div class="col12 col12-mob col floatLeft paddingTop20">
								<h4 class="headingColor font18 fontWeight400 letterspacing1">Domicilio</h4>
							</div>

							<div class="col4 col12-mob col floatLeft ">
								<h4 class="gray font16 fontWeight400 letterspacing1">Calle
									y Número</h4>
								<p class="font18 gray letterspacing1 textUpper">
									${direccion?.calle}
									${direccion?.noExterior}
									${direccion?.noInterior}
								</p>
							</div>

							<div class="col4 col12-mob col floatLeft ">
								<h4 class="gray font16 fontWeight400 letterspacing1">Colonia</h4>
								<p class="font18 gray letterspacing1 textUpper">
									${direccion?.colonia}
								</p>
							</div>

							<div class="col4 col12-mob col floatLeft ">
								<h4 class="gray font16 fontWeight400 letterspacing1">Ciudad</h4>
								<p class="font18 gray letterspacing1 textUpper">
									${municipio}
								</p>
							</div>

							<div
								class="col12 col12-mob col floatLeft paddingTop20 paddingBottom20 ">
								<p class="font18 gray letterspacing1 justify">
									Hoy siendo <span id="fechaAutorizacionConsulta"
										class="headingColor"> ${fechaActual}
									</span>, Autoriza a <span id="razonSocial" class="headingColor">
										${razonSocial}
									</span> a consultar sus antecedentes crediticios por &uacute;nica ocasi&oacute;n
									ante las Sociedades de Informaci&oacute;n Crediticia que estime
									conveniente, declarando que conoce la naturaleza, alcance y uso
									que <span id="razonSocial" class="headingColor"> ${razonSocial}
									</span> har&aacute; de tal informaci&oacute;n?
								</p>
							</div>
							<div class="col4 col12-mob col floatLeft white"
								style="color: white">.</div>

							<div
								class="col4 col12-mob col floatLeft paddingTop20 paddingBottom20">
								<div
									class="marcoLegalCorrectaBox_SI floatLeft correctaBox ">
									<p class="center paddingTop15 paddingBottom15 lightGray">SI</p>
								</div>
								<div class="marcoLegalCorrectaBox_NO floatLeft correctaBox">
									<p class="center paddingTop15 paddingBottom15 lightGray">NO</p>
								</div>
							</div>
							<div
								class="col4 col12-mob col floatLeft paddingTop20 paddingBottom20"
								style="color: white">.</div>


						</div>
						<div id="autorizacionLoading" style="display:none;">
							<center><i class="fa fa-cog fa-spin fa-5x fa-fw margin-bottom" style="font-size:60px;color:#298df5"></i><p style="font-size:16px;color:#298df5">...</p></center>
    						<center><p style="font-size:16px;color:#298df5">Consultando con Bur&oacute; de Cr&eacute;dito</p></center>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>