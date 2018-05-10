package la.kosmos.app

class Periodicidad implements Serializable{

    String nombre
    String nomenclatura
    float periodosAnuales
    float periodoDePago
    
    static constraints = {
        nombre (blank:false)
        nomenclatura (blank:false)
    }

    static mapping = {
        id generator: 'sequence', column: 'id_periodicidad', params:[sequence:'periodicidad_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
