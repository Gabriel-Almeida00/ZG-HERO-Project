package linketinder.service.login

import linketinder.model.dto.LoginDTO

interface ILoginService {
    Integer loginCandidato(LoginDTO loginDTO)
    Integer loginEmpresa(LoginDTO loginDTO)
}