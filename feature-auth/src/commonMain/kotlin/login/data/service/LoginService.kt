package login.data.service


interface LoginService {
    suspend fun login(email: String, password: String): LoginResponse
}