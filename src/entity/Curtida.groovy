package entity

import java.time.Instant

abstract class Curtida {
    Candidato candidato
    Instant dataHora

    Curtida(Candidato candidato) {
        this.candidato = candidato
        this.dataHora = Instant.now()
    }
}
