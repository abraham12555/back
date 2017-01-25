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
        "/api/Callback/ClassificationResult"(controller: "classificationResult", action: "save")
        "/api/Callback/DossierSummary"(controller: "dossierSummary", action: "save")
        "500"(view:'/error')
        "404"(view:'/notFound')
        "403"(view:'/denied')
	}
}
