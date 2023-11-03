package linketinder.service.login

import linketinder.dao.login.ILoginDao
import linketinder.model.dto.LoginDTO

class LoginService implements ILoginDao {
    ILoginDao loginDao

    LoginService(ILoginDao loginDao) {
        this.loginDao = loginDao
    }

    @Override
    boolean loginCandidato(LoginDTO loginDTO) {
        return this.loginDao.loginCandidato(loginDTO)
    }

    @Override
    boolean loginEmpresa(LoginDTO loginDTO) {
        return this.loginDao.loginEmpresa(loginDTO)
    }
}
