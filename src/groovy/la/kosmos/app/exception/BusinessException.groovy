/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package la.kosmos.app.exception

/**
 *
 * @author elizabeth
 */
class BusinessException extends RuntimeException {

    BusinessException(message) {
        super(message);
    }

    BusinessException(message, Throwable cause) {
        super(message, cause);
    }
}
