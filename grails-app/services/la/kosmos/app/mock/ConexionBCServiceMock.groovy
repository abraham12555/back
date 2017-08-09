/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package la.kosmos.app.mock

/**
 *
 * @author elizabeth
 */
class ConexionBCServiceMock {

    def socketRequest(String ip, String port, String intl, data) throws Exception {
        String intlResponse

        if(data != null) {
            intlResponse = URLDecoder.decode(data.cadenaDeBuro.trim(), "UTF-8")
        }

        return intlResponse
    }
}