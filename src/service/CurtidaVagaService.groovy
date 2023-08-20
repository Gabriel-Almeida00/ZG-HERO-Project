package service

import Interface.ICurtdaVaga
import entity.Candidato
import entity.CurtidaVaga
import entity.Vaga

class CurtidaVagaService implements  ICurtdaVaga{

    void curtirVaga(Candidato candidato, Vaga vaga) {
        CurtidaVaga curtida = new CurtidaVaga(candidato, vaga)
        vaga.getCurtidas().add(curtida)
    }
}
