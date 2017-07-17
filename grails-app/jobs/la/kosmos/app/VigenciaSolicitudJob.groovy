package la.kosmos.app

class VigenciaSolicitudJob {
    def solicitudService

    static triggers = {
        
        cron name:'vigenciaSolicitudJob', cronExpression: '0 0 0 1/1 * ? *' 
    }

    def execute() {
        solicitudService.vigenciaSolicitudFormal()
        solicitudService.vigenciaSolicitudTemporal()
    }
	
}

