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
class CronExpression {
    def seconds = "0"
    def minutes = "*"
    def hours = "*"
    def dayMonth = "*"
    def month = "*"
    def dayWeek = "?"
    def year = "*"

    def getCronExpression(){
        def expression = new StringBuilder()
        expression.append(seconds).append(" ")
        expression.append(minutes).append(" ")
        expression.append(hours).append(" ")
        expression.append(dayMonth).append(" ")
        expression.append(month).append(" ")
        expression.append(dayWeek).append(" ")
        expression.append(year)
        return expression.toString()
    }

    //Elimina ceros a la izquierda
    def getTime(time, expression) {
        def (hour, minute) = time.tokenize(':')
        expression.minutes = minute.replaceFirst("^0+(?!\$)", "")
        expression.hours = hour.replaceFirst("^0+(?!\$)", "")
    }
    
    //Elimina ceros a la izquierda
    def getHour(hour, expression) {
        expression.hours = hour.replaceFirst("^0+(?!\$)", "")
    }
}

