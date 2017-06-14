package la.kosmos.app

class SesionUsuario implements Serializable {

    Date fecha
    
    SesionUsuario(){
        
    }
    
    SesionUsuario(Usuario usuario, Date fecha){
        this.usuario = usuario
        this.fecha = fecha
    }
    
    static constraints = {
        fecha (nullable: false)
    }

    static belongsTo = [usuario: Usuario]
    
    static mapping = {
        id column: 'usuario_id', generator: 'foreign', params: [ property: 'usuario' ]
        usuario insertable: false, updateable: false
    }
}
