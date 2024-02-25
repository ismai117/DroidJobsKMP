package utils.validations

object ValidateUsername {
    operator fun invoke(username: String, usernames: List<String>): ValidationResult {
        if (username.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "Username can't be blank!"
            )
        }
        if (username.length < 6){
            return ValidationResult(
                successful = false,
                errorMessage = "Username is too short!"
            )
        }
        if (usernames.any { it == username }){
            return ValidationResult(
                successful = false,
                errorMessage = "Username already exists!"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}