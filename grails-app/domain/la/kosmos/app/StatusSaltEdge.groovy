package la.kosmos.app

class StatusSaltEdge {
	
	String nombre
	String descripcion

    static constraints = {
		  nombre  (nullable: false)
		  descripcion  (nullable: false)
	}
    
    static mapping = {
        id generator: 'sequence', column: 'id_status_salt_edge', params:[sequence:'status_salt_edge_id_seq']
    }
}
