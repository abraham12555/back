package la.kosmos.app

import grails.transaction.Transactional
import mx.com.kosmos.connections.SaltEdge
import grails.converters.JSON
import grails.transaction.Transactional
import groovy.json.*


@Transactional
class SaltEdgeService {
    def sequenceGeneratorService
	def jsonSlurper = new JsonSlurper()
	def json =  [:]
	def data =  [:]
	def server_url = "https://www.saltedge.com/"
	def endpoint = [
			'customers'                 : 'api/v3/customers',
			'logins'                    : 'api/v3/logins',
			'accounts'                  : 'api/v3/accounts/',
			'transactions'              : 'api/v3/transactions',
			'logins_interactive'        : 'api/v3/logins/{customer_id}/interactive',
			'logins'                    : 'api/v3/logins/'
	]

	/**
	 * Metodo Flujo - Generación  Customer - Login
	 * @return
	 */
	def createUser(){
		json =  [:]
		data =  [:]
		def jsonSlurper = new JsonSlurper()
		data.identifier = sequenceGeneratorService.nextNumber('CUSTOMER')
		json.data = data
		def object  =  new SaltEdge().request("POST","${server_url}${endpoint.customers}",new JsonBuilder(json).toString())
		def resp = jsonSlurper.parseText(object)
		return jsonSlurper.parseText(object)
	}

	def findLogin(def customer_id){
		def object  =  new SaltEdge().request("GET","${server_url}${endpoint.logins}?customer_id=${customer_id}",null)
		return jsonSlurper.parseText(object)
	}


	def accounts(def login_id){
		def object  =  new SaltEdge().request("GET","${server_url}${endpoint.accounts}?login_id=${login_id}",new JsonBuilder(json).toString())
		return jsonSlurper.parseText(object)
	}

	def transactions(def account_id,def from_date, def to_date){
		def object  =  new SaltEdge().request("GET","${server_url}${endpoint.transactions}?account_id=${account_id}&from_date=${from_date}&to_date=${to_date}",new JsonBuilder(json).toString())
		return jsonSlurper.parseText(object)
	}
	
	def transactions(def account_id){
		def object  =  new SaltEdge().request("GET","${server_url}${endpoint.transactions}?account_id=${account_id}",new JsonBuilder(json).toString())
		return jsonSlurper.parseText(object)
	}


	def interactiveLogin(def login_id, def credentials){
		json =  [:]
		data =  [:]
		data.credentials = credentials
		json.data = data
		def object  =  new SaltEdge().request("PUT","${server_url}${endpoint.logins}${login_id}/interactive",new JsonBuilder(json).toString())
		return jsonSlurper.parseText(object)
	}

	def reconnectLogin(def login_id,def credentials){
		json =  [:]
		data =  [:]
		data.credentials = credentials
		json.data = data
		def object  =  new SaltEdge().request("PUT","${server_url}${endpoint.logins}${login_id}/reconnect",new JsonBuilder(json).toString())
		return jsonSlurper.parseText(object)
	}

	def createLogin(def customer_id,def provider_code, def credentials){
		json =  [:]
		data =  [:]
		//LoginBank loginBank = null
		data.customer_id = customer_id
		data.country_code = "MX"
		data.fetch_type = "recent"
		data.provider_code =  provider_code  //BANCO SELECCIONADO POR EL USUARIO
		data.credentials = credentials  // Credenciales Usuario-Banco
		json.data = data
		def object  =  new SaltEdge().request("POST","${server_url}${endpoint.logins}",new JsonBuilder(json).toString())
		return jsonSlurper.parseText(object)
	}

	def deleteUser(def customer_id){
		def object  =  new SaltEdge().request("DELETE","${server_url}${endpoint.customers}/${customer_id}",new JsonBuilder(json).toString())
		def resp = jsonSlurper.parseText(object)
	}

	def deleteLogin(def login_id){
		def object  =  new SaltEdge().request("DELETE","${server_url}${endpoint.logins}/${login_id}",new JsonBuilder(json).toString())
		def resp = jsonSlurper.parseText(object)
	}
}
