package utils.validations


object ValidateConfirmPassword {
    operator fun invoke(confirm: String, confirmPassword: String): ValidationResult {
        if (confirm != confirmPassword){
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