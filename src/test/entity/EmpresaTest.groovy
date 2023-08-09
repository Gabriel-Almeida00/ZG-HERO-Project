package test.entity

import entity.Empresa
import org.junit.jupiter.api.Test

class EmpresaTest {

    @Test
    void testConstrutorEmpresa() {
        // Arrange
        String nome = "Empresa ABC"
        String email = "empresa@example.com"
        String cnpj = "12345678901234"
        String pais = "Brasil"
        String estado = "SP"
        String cep = "12345"
        String descricaoEmpresa = "Descrição da empresa"
        List<String> competencias = Arrays.asList("Java", "Groovy")

        // Act
        Empresa empresa = new Empresa(nome, email, cnpj, pais, estado, cep, descricaoEmpresa, competencias)

        // Assert
        assert nome == empresa.getNome()
        assert email == empresa.getEmail()
        assert cnpj == empresa.getCnpj()
        assert pais == empresa.getPais()
        assert estado == empresa.getEstado()
        assert cep == empresa.getCep()
        assert descricaoEmpresa == empresa.getDescricaoEmpresa()
        assert competencias == empresa.getCompetencias()
    }
}
