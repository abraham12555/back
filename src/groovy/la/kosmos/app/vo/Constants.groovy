/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package la.kosmos.app.vo

/**
 *
 * @author elizabeth
 */
class Constants {

    def static final DAY_MONTH = "1"
    static final String URL_CHANGE_PASSWORD = "/login/changePassword"
    static final String CURRENT_USER = "usuario"
    static final String CONFIRMATION_TEMPLATE = "/templates/correoConfirmacion.vm"
    static final Integer TOTAL_ROWS = 15
    static final Long ENTIDAD_FINANCIERA_ROOT = 1
    static final Integer STATUS_SOLICITUD_NOTIFICACION = 5
    static final String TRIGGER_GROUP_CONFIG = "NOTIFICACION_TRIGGERS"

    enum TipoTelefono {
        FIJO(1), CELULAR(2), OFICINA(3)

        private final long value

        TipoTelefono(long value) {
            this.value = value
	}

        long getValue() {
            value
	}
    }

    enum CronConfig {
        MINUTO, HORA, DIA, SEMANA, MES
    }

    enum TipoPlantilla {
        SMS, EMAIL
    }

    enum DaysWeek {
        LUNES("MON"), MARTES("TUE"), MIERCOLES("WED"), JUEVES("THU"), VIERNES("FRI"), SABADO("SAT"), DOMINGO("SUN")

        private final String value

        DaysWeek(String value) {
            this.value = value
	}

        String getValue() {
            value
	}

        def static findByValue(String value){
            for (sh in DaysWeek.values()) {
                DaysWeek dayAsEnum = sh
                if(dayAsEnum.value == value){
                    return dayAsEnum
                }
            }
            return null
        }
    }

    enum StatusResponseCalixta {
        ENVIADO(3), NO_MOVIL(6), INVALIDO(10), NO_SALDO(101), ERROR(-1)

        private final int value

        StatusResponseCalixta(int value) {
            this.value = value
	}

        int getValue() {
            value
	}
    }

    enum TipoEmail {
        PERSONAL(1), LABORAL(2)

        private final long value

        TipoEmail(long value) {
            this.value = value
	}

        long getValue() {
            value
	}
    }
}

