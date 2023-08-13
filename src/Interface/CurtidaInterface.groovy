package Interface

import entity.Curtida

interface CurtidaInterface {
    void curtirCandidato(String nomeEmpresa, String nomeCandidato);
    void curtirVaga(String nomeCandidato, String nomeVaga);
    List<Curtida> encontrarMatches();
}