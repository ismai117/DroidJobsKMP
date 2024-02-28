package validations.email

import utils.validations.ValidationResult


object ValidateLoginPassword {
    operator fun invoke(password: String): ValidationResult {
        if (password.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password cannot be blank!"
            )
        }
        if (password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password needs at least 8 characters"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}