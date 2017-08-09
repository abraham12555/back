package la.kosmos.app

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.apache.commons.lang.builder.HashCodeBuilder

@ToString(cache=true, includeNames=true, includePackage=false)
class BitacoraUsuarioConsultabc implements Serializable {

    private static final long serialVersionUID = 1

    BitacoraBuroCredito bitacoraBuroCredito
    Usuario usuario

    @Override
    boolean equals(other) {
        if (!(other instanceof BitacoraUsuarioConsultabc)) {
            return false
        }

        other.bitacoraBuroCredito?.id == bitacoraBuroCredito?.id && other.usuario?.id == usuario?.id
    }

    @Override
    int hashCode() {
        def builder = new HashCodeBuilder()
        if (bitacoraBuroCredito) builder.append(bitacoraBuroCredito.id)
        if (usuario) builder.append(usuario.id)
        builder.toHashCode()
    }

    static BitacoraUsuarioConsultabc get(long bitacoraBuroCreditoId, long usuarioId) {
        criteriaFor(bitacoraBuroCreditoId, usuarioId).get()
    }

    static boolean exists(long bitacoraBuroCreditoId, long usuarioId) {
        criteriaFor(bitacoraBuroCreditoId, usuarioId).count()
    }

    private static DetachedCriteria criteriaFor(long bitacoraBuroCreditoId, long usuarioId) {
        BitacoraUsuarioConsultabc.where {
            bitacoraBuroCredito == BitacoraBuroCredito.load(bitacoraBuroCreditoId) &&
            usuario == Usuario.load(usuarioId)
        }
    }

    static BitacoraUsuarioConsultabc create(BitacoraBuroCredito bitacoraBuroCredito, Usuario usuario, boolean flush = false) {
        def instance = new BitacoraUsuarioConsultabc(bitacoraBuroCredito: bitacoraBuroCredito, usuario: usuario)
        instance.save(flush: flush, insert: true)
        instance
    }

    static boolean remove(BitacoraBuroCredito nc, Usuario u, boolean flush = false) {
        if (nc == null || u == null) return false

        int rowCount = BitacoraUsuarioConsultabc.where { bitacoraBuroCredito == nc && usuario == u }.deleteAll()

        if (flush) { BitacoraUsuarioConsultabc.withSession { it.flush() } }

        rowCount
    }

    static void removeAll(BitacoraBuroCredito nc, boolean flush = false) {
        if (nc == null) return

        BitacoraUsuarioConsultabc.where { bitacoraBuroCredito == nc }.deleteAll()

        if (flush) { BitacoraUsuarioConsultabc.withSession { it.flush() } }
    }

    static void removeAll(Usuario u, boolean flush = false) {
        if (u == null) return

        BitacoraUsuarioConsultabc.where { usuario == u }.deleteAll()

        if (flush) { BitacoraUsuarioConsultabc.withSession { it.flush() } }
    }

    static constraints = {
        usuario validator: { Usuario u, BitacoraUsuarioConsultabc buc ->
            if (buc.bitacoraBuroCredito == null || buc.bitacoraBuroCredito.id == null) return
            boolean existing = false
            BitacoraUsuarioConsultabc.withNewSession {
                existing = BitacoraUsuarioConsultabc.exists(buc.bitacoraBuroCredito.id, u.id)
            }
            if (existing) {
                return 'bitacoraUsuarioConsultabc.exists'
            }
        }
    }

    static mapping = {
        id composite: ['bitacoraBuroCredito', 'usuario']
        version false
    }
}