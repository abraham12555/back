<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


        <title>${(configuracion?.htmlTitle ? (configuracion?.htmlTitle + " - " )  : "" )}Dashboard</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
        <meta charset="UTF-8">
        <g:external dir="css" file="bootstrap.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="formulario.css" title="text/css" rel="stylesheet" />     
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
        <g:external dir="css" file="sweetalert.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="select2.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="slick.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="slick-theme.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="spectrum.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="bootstrap-tour-standalone.min.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="jquery.webui-popover.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="jquery-ui-css.css" title="text/css" rel="stylesheet" />
        <g:if test="${session.configuracion?.rutaCss}">
            <link rel="stylesheet" href="${session.configuracion?.rutaCss}">
        </g:if>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.25.0/codemirror.min.css">
            <!-- Include Editor style. -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.6.0/css/froala_editor.pkgd.min.css" rel="stylesheet" type="text/css" />
        <link href="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.6.0/css/froala_style.min.css" rel="stylesheet" type="text/css" />

        <g:external dir="js" file="jquery-3.0.0.min.js" />
        <g:urlContextAware value="/" var="urlContextAware"/>
        <script type="text/javascript">
            $.contextAwarePathJS = "${urlContextAware}";
        </script>
        <g:external dir="js" file="sweetalert.min.js" />
        <g:external dir="js" file="dropzone.js" />
        <g:external dir="js" file="spectrum.js" />
        <g:external dir="js" file="dateFormat.min.js" />
        <g:external dir="js" file="dashboard.js" />
        <g:external dir="js" file="select2.js" />
        <g:external dir="js" file="slick.js" />
        <!--<g:external dir="css" file="jquery-ui-kosmos.css" />-->
        <g:external dir="js" file="jquery-ui-kosmos.js" />
        <g:external dir="js" file="googleanalytics.js" />
        <g:external dir="js" file="bootstrap-tour-standalone.min.js" />
        <g:external dir="js" file="jquery.webui-popover.min.js" />

        <!-- Include external JS libs. -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.25.0/codemirror.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.25.0/mode/xml/xml.min.js"></script>
 
    <!-- Include Editor JS files. -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.6.0/js/froala_editor.pkgd.min.js"></script>

        <script src="http://harvesthq.github.io/chosen/chosen.jquery.js"></script>
        <g:layoutHead/>
    </head>
    <body>
        <g:urlContextAware value="/dashboard/editarPerfil" var="urlEditarPerfil"/>
        <g:urlContextAware value="/dashboard/buscar" var="urlBuscar"/>
        <g:urlContextAware value="/logout" var="urlLogout"/>
        <header class="appHeader" style="border-top: solid 5px ${session.configuracion?.colorBordeSuperior}; background-color: ${session.configuracion?.colorEncabezado};">
            <div class="container clearFix">
                <img class="logo dashboard floatLeft desktop" src="${resource(dir:'images', file: session.configuracion?.rutaLogotipo )}" alt="Logo" title="Logo" />
                <img class="logoMobile dashboard floatLeft mobile tablet" src="${resource(dir:'images', file:'kosmos-mobile-logo.png')}" alt="Logo" title="Logo" />
                <div class="floatingHeader floatRight clearFix">
                    <div class="floatRight clearFix">
                        <div class="clearFix floatLeft ">
                            <img class="userPicture dashboard floatLeft" src="${resource(dir:'images', file:'profile.png')}" id="bannerProfilePicture"/>
                        </div>
                        <p class="userName dashboard floatLeft">Hola ${session.usuarioNombre} </p>
                        <div class="dropBox floatLeft marginTop20 marginLeft5 desktop">
                            <i class="fa fa-angle-down paddingTop4 paddingRight5 paddingLeft5 dropbtn" aria-hidden="true" onclick="mostrarOpciones();"></i>
                            <div id="opcionesUsuario" class="dropdown-content">
                                <a href="${urlEditarPerfil}">Editar Perfil</a>
                                <a href="${urlLogout}">Salir</a>
                            </div>
                        </div>
                        <span class="mobile tablet floatLeft mobMenu dashboard dropbtn"><i class="fa fa-bars" aria-hidden="true" onclick="w3_open();"></i></span>
                    </div>
                        <sec:ifAnyGranted roles='ROLE_ADMIN,,ROLE_DIRECTOR,ROLE_ANALISTA,ROLE_EJECUTIVO,,ROLE_SUCURSAL'>
                            <div class="floatRight width337 borderGrayRight paddingBottom10 paddingTop10 desktop">
                                <div class="searchBox autoMargin clearFix">
                                    <a href="#" style="width:100%;" class="show-pop-async btn  center-block" data-placement="vertical">Buscar 
                                        <i class="fa fa-search" aria-hidden="true"></i>
                                    </a>
                                </div>
                            </div>
                        </sec:ifAnyGranted>
                    <div class="floatRight notificationBox dashboardHeader marginTop15 paddingTop5 paddingRight5 paddingLeft5">
                        <img class="floatLeft paddingLeft10 paddingTop5" src="${resource(dir:'images', file:'bell.png')}" alt="bell" title="bell">
                        <p class="floatLeft colorWhite font16 paddingLeft10">${(session.solicitudesPendientes ?: 0)}</p>
                    </div>
                </div>
            </div>
        </header>
        <ul id="mySidenav" class="side-nav right-aligned" style="transform: translateX(0px); -ms-transform: translateX(0px);
    -webkit-transform: translateX(0px);">
          <li>
              <a href="${createLink(controller:'dashboard', action:'index')}">
                  <img class="menuIcon" src="${resource(dir:'images', file:'dashboard.png')}" alt="dashboard" title="dashboard"> DASHBOARD
              </a>
          </li>
          <li>
              <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_DIRECTOR, ROLE_ANALISTA, ROLE_EJECUTIVO, ROLE_SUCURSAL'>
                  <a href="${createLink(controller:'dashboard', action:'solicitudes')}">
                      <img class="menuIcon" src="${resource(dir:'images', file:'solicitudes.png')}" alt="solicitudes" title="solicitudes"> SOLICTUDES
                  </a>
              </sec:ifAnyGranted>
          </li>
          <li>
              <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ANALISTA, ROLE_EJECUTIVO, ROLE_SUCURSAL'>
                  <a href="${createLink(controller:'dashboard', action:'perfilarCliente')}">
                      <img class="menuIcon" src="${resource(dir:'images', file:'perfilador.png')}" alt="perfilador" title="perfilador"> PERFILADOR
                  </a>
              </sec:ifAnyGranted>
          </li>
          <li>
              <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_DIRECTOR, ROLE_EJECUTIVO, ROLE_SUCURSAL'>
                  <a href="${createLink(controller:'dashboard', action:'analiticas')}">
                      <img class="menuIcon" src="${resource(dir:'images', file:'analiticas.png')}" alt="analiticas" title="analiticas"> ANALITICAS
                  </a>
              </sec:ifAnyGranted>
          </li>
          <li>
              <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_DIRECTOR, ROLE_VERIFICADOR'>
                  <a href="${createLink(controller:'dashboard', action:'verificaciones')}">
                      <img class="menuIcon" src="${resource(dir:'images', file:'verification.png')}" alt="verificacion" title="verificaciones"> VERIFICACIONES
                  </a>
              </sec:ifAnyGranted>
          </li>
          <li>
              <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_RIESGOS, ROLE_DISENO, ROLE_DIRECTOR'>
                  <a href="${createLink(controller:'dashboard', action:'configuracion')}">
                      <img class="menuIcon" src="${resource(dir:'images', file:'configration.png')}" alt="configration" title="configration"> CONFIGURACIÓN
                  </a>
              </sec:ifAnyGranted>
          </li>
          <li>
              <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR'>
                  <a href="${createLink(controller:'dashboard', action:'administracion')}">
                      <img class="menuIcon" src="${resource(dir:'images', file:'key.png')}" alt="administracion" title="administracion"> ADMINISTRACIÓN
                  </a>
              </sec:ifAnyGranted>
          </li>
          <li>
              <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_DIRECTOR, ROLE_EJECUTIVO, ROLE_SUCURSAL'>
                  <a href="${createLink(controller:'dashboard', action:'reportes')}">
                      <img class="menuIcon" src="${resource(dir:'images', file:'analiticas.png')}" alt="reportes" title="reportes"> REPORTES
                  </a>
              </sec:ifAnyGranted>
          </li>
          <li>
             <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_DIRECTOR, ROLE_ADMINISTRADOR'>
             <a href="${createLink(controller:'dashboard', action:'informes')}" class="elementoMenuPrincipal font12">
             <img class="menuIcon" src="${resource(dir:'images', file:'ej.png')}" alt="informes" title="informes"> INFORME DIRECTIVO
             </a>
             </sec:ifAnyGranted>

          </li>
        </ul>
    <nav id="dashboardMenu" class="container menuBox marginBottom28">
            <ul class="clearFix">
                <li class="floatLeft">
                    <a id="principalOpc1" href="${createLink(controller:'dashboard', action:'index')}" class="elementoMenuPrincipal font12">
                        <span><img class="menuIcon" src="${resource(dir:'images', file:'dashboard.png')}" alt="dashboard" title="dashboard"></span>
                        <span>DASHBOARD</span>
                    </a>
                </li>
                <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_DIRECTOR, ROLE_ANALISTA, ROLE_EJECUTIVO, ROLE_SUCURSAL'>
                    <li class="floatLeft">
                        <a id="principalOpc2" href="${createLink(controller:'dashboard', action:'solicitudes')}" class="elementoMenuPrincipal font12 ">
                            <span><img class="menuIcon" src="${resource(dir:'images', file:'solicitudes.png')}" alt="solicitudes" title="solicitudes"></span>
                            <span>SOLICITUDES</span>
                        </a>
                    </li>
                </sec:ifAnyGranted>
                <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ANALISTA, ROLE_EJECUTIVO, ROLE_SUCURSAL'>
                    <li class="floatLeft">
                        <a id="principalOpc3" href="${createLink(controller:'dashboard', action:'perfilarCliente')}" class="elementoMenuPrincipal font12 ">
                            <span><img class="menuIcon" src="${resource(dir:'images', file:'perfilador.png')}" alt="perfilador" title="perfilador"></span>
                            <span>PERFILADOR</span>
                        </a>
                    </li>
                </sec:ifAnyGranted>
                <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_DIRECTOR, ROLE_EJECUTIVO, ROLE_SUCURSAL'>
                    <li class="floatLeft">
                        <a id="principalOpc4" href="${createLink(controller:'dashboard', action:'analiticas')}" class="elementoMenuPrincipal font12">
                            <span><img class="menuIcon" src="${resource(dir:'images', file:'analiticas.png')}" alt="analiticas" title="analiticas"></span>
                            <span>ANALITICAS</span>
                        </a>
                    </li>
                </sec:ifAnyGranted>
                <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_DIRECTOR, ROLE_VERIFICADOR'>
                    <li class="floatLeft">
                        <a id="principalOpc5" href="${createLink(controller:'dashboard', action:'verificaciones')}" class="elementoMenuPrincipal font12">
                            <span><img class="menuIcon" src="${resource(dir:'images', file:'verification.png')}" alt="verificacion" title="verificaciones"></span>
                            <span>VERIFICACIONES</span>
                        </a>
                    </li>
                </sec:ifAnyGranted>
                <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_RIESGOS, ROLE_DISENO, ROLE_DIRECTOR'>
                    <li class="floatLeft">
                        <a id="principalOpc6" href="${createLink(controller:'dashboard', action:'configuracion')}" class="elementoMenuPrincipal font12">
                            <span><img class="menuIcon" src="${resource(dir:'images', file:'configration.png')}" alt="configration" title="configration"></span>
                            <span>CONFIGURACIÓN</span>
                        </a>
                    </li>
                </sec:ifAnyGranted>
                <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR'>
                    <li class="floatLeft">
                        <a id="principalOpc7" href="${createLink(controller:'dashboard', action:'administracion')}" class="elementoMenuPrincipal font12">
                            <span><img class="menuIcon" src="${resource(dir:'images', file:'key.png')}" alt="administracion" title="administracion"></span>
                            <span>ADMINISTRACIÓN</span>
                        </a>
                    </li>
                </sec:ifAnyGranted>  
                <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_DIRECTOR, ROLE_EJECUTIVO, ROLE_SUCURSAL'>
                    <li class="floatLeft">
                        <a id="principalOpc4" href="${createLink(controller:'dashboard', action:'reportes')}" class="elementoMenuPrincipal font12">
                            <span><img class="menuIcon" src="${resource(dir:'images', file:'analiticas.png')}" alt="reportes" title="reportes"></span>
                            <span>REPORTES</span>
                        </a>
                    </li>
                </sec:ifAnyGranted>
                <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_DIRECTOR, ROLE_ADMINISTRADOR'>
                    <li class="floatLeft">
                        <a id="principalOpc9" href="${createLink(controller:'dashboard', action:'informes')}" class="elementoMenuPrincipal font12">
                            <span><img class="menuIcon" src="${resource(dir:'images', file:'ej.png')}" alt="informes" title="informes"></span>
                            <span>INFORME DIRECTIVO</span>
                        </a>
                    </li>
                </sec:ifAnyGranted>           
            </ul>
        </nav>

        <g:layoutBody/>

	<script>
<g:urlContextAware value="/dashboard" var="urlAbreModal"/>
$.urlAbreModal = "${urlAbreModal}";
			(function(){


				var settings = {
						trigger:'click',
						title:'BUSCAR ',
						content:'<p>This is webui popover demo.</p><p>just enjoy it and have fun !</p>',
						//width:auto,						
						multi:true,						
						closeable:false,
						style:'',
						delay:300,
						padding:true,
						backdrop:false
				};



				// $('a[data-toggle="tab"]').on('click',function(e){
				// 	e.preventDefault();
				// 	var $this = $(this);
				// 	$this.parent().addClass('active').siblings().removeClass('active');
				// 	var $content = $this.closest('.nav-tabs').next().find($this.attr('href'));
				// 	$content.addClass('active').siblings().removeClass('active');
				// });

				$('#setup').webuiPopover({
					width:350,
					height:535,
					padding:false,
					animation:'pop',
					content:$('#optionsWrapper').html(),
				});	
				content:$('#optionsWrapper').remove();



				$(document).on('click','span.option-checker',function(e){
					e.preventDefault();
					e.stopPropagation();
					$(this).toggleClass('active');
					var text = $(this).hasClass('active')?'yes':'no';
					$(this).children('.text').text(text);
					updateSettings();
				});

				$(document).on('click','span.option-box',function(e){
					e.preventDefault();
					e.stopPropagation();
					$(this).addClass('active').siblings().removeClass('active');
					updateSettings();
				});


				$('.list-example').on('click','.list-group-item',function(e){
					e.preventDefault();
					$(this).addClass('active').siblings().removeClass('active');
					var $content = $($(this).attr('href'));
					$content.addClass('active').siblings().removeClass('active');
					$('.webui-popover').hide();
					if ($(this).attr('href')=='#specify'){
						$('#btn-sticky').webuiPopover('destroy').webuiPopover(settings);
					}
				});

				




				// $('.btn-settings').on('click',function(e){
				// 		e.preventDefault();
				// 		$(this).addClass('active').siblings().removeClass('active');
				// 		var option = $(this).data('option');
				// 		settings[option]= $(this).data(option);					
				// 		initPopover();
				// });	

				// $('.btn-reset').on('click',function(e){
				// 	e.preventDefault();
				// 	location.reload();
				// });	

				function updateSettings(){
					settings.style=$('.option-style.active').data('option');
					settings.trigger=$('.option-trigger.active').data('option');
					settings.closeable=$('.option-closeable').hasClass('active');
					settings.multi = $('.option-multi').hasClass('active');
					settings.arrow = $('.option-arrow').hasClass('active');
					initPopover();
				}		


				function initPopover(){					
					$('a.show-pop').webuiPopover('destroy').webuiPopover(settings);				
					
					var tableContent = $('#tableContent').html(),
						tableSettings = {content:tableContent,
											width:500
										};
					$('a.show-pop-table').webuiPopover('destroy').webuiPopover($.extend({},settings,tableSettings));

					var listContent = $('#listContent').html(),
						listSettings = {content:listContent,
											title:'',
											padding:false
										};
					$('a.show-pop-list').webuiPopover('destroy').webuiPopover($.extend({},settings,listSettings));

					var largeContent = $('#largeContent').html(),
						largeSettings = {content:largeContent,
											width:400,
											height:450,
											delay:{show:300,hide:1000},
											closeable:true
										};
					var popLarge = $('a.show-pop-large').webuiPopover('destroy').webuiPopover($.extend({},settings,largeSettings));


					$('a.show-pop-delay').webuiPopover('destroy').webuiPopover({trigger:'hover',width:300});
					$('a.show-pop-code-delay').webuiPopover('destroy').webuiPopover({
																						trigger:'hover',
																						width:300,
																						delay:{
																							show:0,
																							hide:1000
																						}
																					});

					$('a.show-pop-backdrop').webuiPopover('destroy').webuiPopover({content:'popover with backdrop!', backdrop:true});

					$('a.show-pop-dropdown').webuiPopover('destroy').webuiPopover({padding:0});

					 var
					 	iframeSettings = {	width:500,
					 						height:350,
					 						closeable:true,
					 						padding:false,
					 						type:'iframe',
					 						url:'http://getbootstrap.com'};					
					$('a.show-pop-iframe').webuiPopover('destroy').webuiPopover($.extend({},settings,iframeSettings));


					var
					 	asyncSettings = {	width:'400',
					 						height:'450',
					 						closeable:true,
					 						padding:false,
					 						cache:false,
					 						url:$.urlAbreModal,
					 						type:'async',
					 						content:function(data){
<g:urlContextAware value="/dashboard/buscar" var="urlBusqueda"/>
                                                                                        var html='';
                html += "<form action='${urlBusqueda}' method='POST' class='form-horizontal' id='busquedaForm'>";
                html += "<div class='width990 autoMargin solicitudWhiteBox clearFix paddingBottom20'>";
                html += "<div class='formContainer'>";
                html += "<label class = 'darkBluetitle'>FOLIO</label><input class='inputs marginBottom10 lightGray letterspacing1 font14' name='folio' id='folioBusqueda' type='text'  placeholder='Folio' />";
                html += "<label class = 'darkBluetitle' >NOMBRE</label><input class='inputs marginBottom10 lightGray letterspacing1 font14' name='nombre' id='nombreBusqueda' type='text'  placeholder='Nombre' />";
                 html += "<label class = 'darkBluetitle' >APELLIDOS</label><input class='inputs marginBottom10 lightGray letterspacing1 font14' name='apellidoPaterno' id='nombreBusqueda' type='text'  placeholder='Apellido Paterno' />";
                 html += "<input class='inputs marginBottom10 lightGray letterspacing1 font14' name='apellidoMaterno' id='nombreBusqueda' type='text'  placeholder='Apellido Materno' />";
                 html += "<label class = 'darkBluetitle' >RFC</label><input class='inputs marginBottom10 lightGray letterspacing1 font14' name='rfc' id='rfcBusqueda' type='text'  placeholder='RFC' />";
                 html += "<button  class='azulBox colorWhite' type='submit'>BUSCAR</button>";
                html += "</div>";
           html += " </div>";
      html += "  </form>";
												return html;
					 						}};
					$('a.show-pop-async').webuiPopover('destroy').webuiPopover($.extend({},settings,asyncSettings));

					$('#change').on('click',function(e){
						e.preventDefault();
						$('#aa').text('changed text');
					})


					$('a.show-pop-event').each(function(i,item){
							var ename = $(item).text()+'.webui.popover';
							$(item).webuiPopover('destroy').webuiPopover(settings)
							.on(ename,function(e,tgt){
								var log =  ename+' is trigged!';
								if (console){
									console.log(log,tgt);
								}
								$('#eventLogs').text($('#eventLogs').text()+'\n'+log);
							});
					});

					
					$('body').on('click','.pop-click',function(e){
						e.preventDefault();
						if (console){
							console.log('clicked');
						}
					});


					var inputSettints = {
						placement:'right',
						title:'',
						multi:false,
						width:220
					};
					

					$('.form-input').webuiPopover('destroy').webuiPopover($.extend({},settings,inputSettints));

					$('.form-input').on('focus',function(){
						$(this).webuiPopover('show');
					});

				}

				$('#resetLogs').on('click',function(e){
					e.preventDefault();
					$('#eventLogs').text('');
				});


				initPopover();

				
				
					
			})();
			
		</script>
    </body>
</html>
