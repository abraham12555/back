package la.kosmos.rest

class WarningMitek implements Serializable{

    String warning
    String description

    static constraints = {
        warning blank: false
        description blank: false
    }
    
        static mapping = {
        id generator : 'sequence', column : 'id_warning_mitek', params:[sequence:'warning_mitek_id_seq']
    }
}
