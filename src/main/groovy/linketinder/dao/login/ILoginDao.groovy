package linketinder.dao.login

import linketinder.model.dto.LoginDTO

interface ILoginDao {
    boolean loginCandidato(LoginDTO loginDTO)
    boolean loginEmpresa(LoginDTO loginDTO)
}