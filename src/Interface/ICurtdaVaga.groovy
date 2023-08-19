package Interface

import entity.Candidato
import entity.Vaga

interface ICurtdaVaga {
    void curtirVaga(Candidato candidato, Vaga vaga)
}