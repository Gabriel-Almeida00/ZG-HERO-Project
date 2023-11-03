package linketinder.dao.login

import linketinder.model.dto.LoginDTO

interface ILoginDao {
    Integer loginCandidato(LoginDTO loginDTO)
    Integer loginEmpresa(LoginDTO loginDTO)
}