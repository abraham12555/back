/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package la.kosmos.app.bo

/**
 *
 * @author elizabeth
 */
class EnvioNotificaciones {
    def cronOptions
    def hour
    def dayTime
    def weekDay
    def weekTime
    def dayMonth
    def monthTime
    def templateOptions
    def dias
    def horas
    def minutos

    EnvioNotificaciones(){

    }

    EnvioNotificaciones(params){
        this.cronOptions = params.cronOptions
        this.hour = params.hour
        this.dayTime = params.dayTime
        this.weekDay = params.weekDay
        this.weekTime = params.weekTime
        this.dayMonth = params.dayMonth
        this.monthTime= params.monthTime
        setTemplateOptions(params?.templateOptions)
    }

    def setTemplateOptions(templateOptions){
        if(!(templateOptions.class.isArray())){
            def valueList = []
            this.templateOptions = valueList + templateOptions
        } else {
            this.templateOptions = templateOptions
        }
    }
}

