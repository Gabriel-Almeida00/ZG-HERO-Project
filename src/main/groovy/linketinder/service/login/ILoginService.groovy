package linketinder.service.login

import linketinder.model.dto.LoginDTO

interface ILoginService {
    boolean loginCandidato(LoginDTO loginDTO)
    boolean loginEmpresa(LoginDTO loginDTO)
}