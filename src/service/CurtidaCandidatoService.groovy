package service

import Interface.ICurtidaCandidato
import entity.Candidato
import entity.CurtidaCandidato
import entity.Empresa

class CurtidaCandidatoService implements ICurtidaCandidato   {

    void curtirCandidato(Empresa empresa, Candidato candidato) {
        def curtida = new CurtidaCandidato(empresa, candidato)
        candidato.getCurtidas().add(curtida)
    }
}
