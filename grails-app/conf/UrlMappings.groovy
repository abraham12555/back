class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: 'cotizador', action: 'index')
        "/rest/obtenerUltimasSolicitudes"(controller: "solicitudRest", action: "index")
        "/rest/obtenerSolicitudesPorFecha"(controller: "solicitudRest", action: "list")
        "/rest/obtenerSolicitudPorFolio"(controller: "solicitudRest", action: "show")
        "500"(view:'/error')
	}
}
