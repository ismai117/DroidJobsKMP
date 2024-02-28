package validations.register

import utils.validations.ValidationResult


object ValidateRegisterConfirmPassword {
    operator fun invoke(password: String, confirmPassword: String): ValidationResult {
        if (password != confirmPassword){
            return ValidationResult(
                successful = false,
                errorMessage = "Both passwords do not match!"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}