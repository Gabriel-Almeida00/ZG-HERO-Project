package test.entity

import entity.Candidato
import org.junit.jupiter.api.Test

class CandidatoTest {

    @Test
    public void testConstrutorCandidato() {
        // Arrange
        String nome = "João";
        String email = "joao@example.com";
        String cpf = "123456789";
        int idade = 25;
        String estado = "SP";
        String cep = "12345";
        String descricaoPessoal = "Descrição";
        List<String> competencias = Arrays.asList("Java", "Groovy");

        // Act
        Candidato candidato = new Candidato(nome, email, cpf, idade, estado, cep, descricaoPessoal, competencias);

        // Assert
        assert nome == candidato.getNome()
        assert email == candidato.getEmail()
        assert cpf == candidato.getCpf()
        assert idade == candidato.getIdade()
        assert estado == candidato.getEstado()
        assert cep == candidato.getCep()
        assert descricaoPessoal == candidato.getDescricaoPessoal()
        assert competencias == candidato.getCompetencias()
    }
}
