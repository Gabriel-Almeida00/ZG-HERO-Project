package test.entity

import entity.Curtida
import entity.PessoaBase
import org.junit.jupiter.api.Test

class CurtidaTest {

    @Test
    void testConstrutorCurtida() {
        // Arrange
        PessoaBase candidato = new PessoaBase("João", "joao@example.com", "12345", ["Java", "Groovy"])
        PessoaBase empresa = new PessoaBase("Empresa A", "empresa@example.com", "54321", ["Java", "Spring"])

        // Act
        Curtida curtida = new Curtida(candidato, empresa)

        // Assert
        assert candidato == curtida.getCandidato()
        assert empresa == curtida.getEmpresa()
    }
}
