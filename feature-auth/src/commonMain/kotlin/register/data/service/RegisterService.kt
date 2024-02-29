package register.data.service



interface RegisterService {
    suspend fun register(email: String, password: String): RegisterResponse
}