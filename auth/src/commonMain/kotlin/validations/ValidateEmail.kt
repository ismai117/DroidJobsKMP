package utils.validations


object ValidateEmail {
    operator fun invoke(email: String): ValidationResult {
        if (email.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "Email can't be blank!"
            )
        }
        if (!email.contains("@")){
            return ValidationResult(
                successful = false,
                errorMessage = "Email is not valid!"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}