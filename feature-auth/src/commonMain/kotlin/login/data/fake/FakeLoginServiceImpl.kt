package login.data.fake

import login.data.service.LoginBody
import login.data.service.LoginResponse
import login.data.service.LoginService


class FakeLoginServiceImpl : LoginService {

    override suspend fun login(email: String, password: String): LoginResponse {
        return LoginResponse(
            status = 200,
            body = LoginBody(
                access_token = "",
                refresh_token = "",
                user_id = "",
                device_id = ""
            )
        )
    }

}