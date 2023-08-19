package Interface

import entity.Candidato
import entity.Empresa

interface ICurtidaCandidato {
    void curtirCandidato(Empresa empresa, Candidato candidato)
}