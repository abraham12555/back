package la.kosmos.app

import java.io.Serializable;

class TransaccionSaltEdge implements Serializable{
	
	String idTransaction
	Date madeOn
	String category
	String description
	Double amount = new Double("0")
	String currency
	CuentaSaltEdge account
	
    static constraints = {
		  idTransaction  (nullable: true)
		  madeOn  (nullable: true)
		  category  (nullable: true)
		  description  (nullable: true)
		  amount  (nullable: true)
		  currency  (nullable: true)
		  account  (nullable: false)
	}
    
    static mapping = {
		version false
        id generator: 'sequence', column: 'id_transaccion_salt_edge', params:[sequence:'transaccion_salt_edge_id_seq']
    }
	
	String toString () {
		"${idTransaction} ${madeOn} ${category} ${description} ${amount} ${currency} ${account} "
	}
}
