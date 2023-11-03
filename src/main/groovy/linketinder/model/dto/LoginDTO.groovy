package linketinder.model.dto

class LoginDTO {
    private String email
    private String senha

    LoginDTO(String email, String senha) {
        this.email = email
        this.senha = senha
    }

    String getEmail() {
        return email
    }

    String getSenha() {
        return senha
    }
}
