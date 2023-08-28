package service

import Interface.ICurtidaCandidato
import entity.Candidato
import entity.CandidatoCurtido
import entity.Empresa

class CurtidaCandidatoService implements ICurtidaCandidato   {

    void curtirCandidato(Empresa empresa, Candidato candidato) {
        def curtida = new CandidatoCurtido(empresa, candidato)
        candidato.getCurtidas().add(curtida)
    }
}
