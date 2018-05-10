package la.kosmos.app


class EnviosProgramadosJob {
   def notificacionesService
   static triggers = {
        cron name:'enviosProgramadosJob', cronExpression: '0 0/5 8-21 * * ?'
    }

    def execute() {
        notificacionesService.enviosProgramadosPendientes()
    }
}
