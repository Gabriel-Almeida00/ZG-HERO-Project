package service

import Interface.ICurtdaVaga
import entity.Candidato
import entity.Vaga
import entity.VagaCurtida

class CurtidaVagaService implements  ICurtdaVaga{

    void curtirVaga(Candidato candidato, Vaga vaga) {
        VagaCurtida curtida = new VagaCurtida(candidato, vaga)
        vaga.getCurtidas().add(curtida)
    }
}
