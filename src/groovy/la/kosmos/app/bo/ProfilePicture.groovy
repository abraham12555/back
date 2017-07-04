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
class ProfilePicture {
    def base64
    def name
    def type

    ProfilePicture(){

    }

    ProfilePicture(base64, type){
        this.base64 = base64
        this.type= type
    }
}

