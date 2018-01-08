package la.kosmos.app

class Region {
    String nombreRegion
    static constraints = {
    }
    static mapping = {
        id generator: 'sequence', column: 'id_region', params: [sequence: 'region_id_seq']
    }
}
