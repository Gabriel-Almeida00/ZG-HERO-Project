package Interface

import entity.Candidato

import java.sql.SQLException

interface ICandidato {
    List<Candidato> listarCandidatos() ;
    void cadastrarCandidato(Candidato candidato);
}
