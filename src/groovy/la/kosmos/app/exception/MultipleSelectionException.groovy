/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package la.kosmos.app.exception

import org.springframework.security.core.AuthenticationException

/**
 *
 * @author elizabeth
 */
class MultipleSelectionException extends AuthenticationException {

    public MultipleSelectionException(String message, Throwable t) {
        super(message, t)
    }

    public MultipleSelectionException(String message) {
        super(message)
    }

    public MultipleSelectionException(String message, Object extraInformation) {
        super(message, extraInformation)
    }

}

