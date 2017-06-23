/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package la.kosmos.app.bo

import la.kosmos.app.NotificacionesCron
import la.kosmos.app.vo.Constants.CronConfig

/**
 *
 * @author elizabeth
 */
class Cron {
    def id
    def configCron
    def cron
    def cronExpression
    def templates

    Cron(NotificacionesCron notificacionCron){
        this.id = notificacionCron.id
        this.configCron = notificacionCron.configCron
        this.cron = notificacionCron.cron
        this.templates = notificacionCron.templates
    }
}

