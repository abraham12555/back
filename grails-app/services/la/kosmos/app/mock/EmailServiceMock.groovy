/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package la.kosmos.app.mock

import la.kosmos.app.ConfiguracionEntidadFinanciera
import org.springframework.mail.javamail.JavaMailSenderImpl

/**
 *
 * @author elizabeth
 */
class EmailServiceMock {

    private JavaMailSenderImpl setEmailConfiguration(ConfiguracionEntidadFinanciera configuracion){
        return null
    }

    public boolean sendPlainText(ConfiguracionEntidadFinanciera configuracion, String subject, String email, String message) throws Exception {
        return Boolean.TRUE
    }

    public boolean sendTemplate(ConfiguracionEntidadFinanciera configuracion, String subject, String email, Map map) throws Exception {
        return Boolean.TRUE
    }
}

