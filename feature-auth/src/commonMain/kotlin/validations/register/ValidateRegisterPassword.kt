package validations.register

import utils.validations.ValidationResult


object ValidateRegisterPassword {
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
        if (
            !password.any { it.isLetter() } || !password.any { it.isDigit() }
        ) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password needs at least one letter and digit"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}