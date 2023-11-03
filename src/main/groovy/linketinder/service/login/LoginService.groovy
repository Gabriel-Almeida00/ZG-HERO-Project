package linketinder.service.login

import linketinder.dao.login.ILoginDao
import linketinder.model.dto.LoginDTO

class LoginService implements ILoginService {
    ILoginDao loginDao

    LoginService(ILoginDao loginDao) {
        this.loginDao = loginDao
    }

    @Override
    Integer loginCandidato(LoginDTO loginDTO) {
        return this.loginDao.loginCandidato(loginDTO)
    }

    @Override
    Integer loginEmpresa(LoginDTO loginDTO) {
        return this.loginDao.loginEmpresa(loginDTO)
    }
}
