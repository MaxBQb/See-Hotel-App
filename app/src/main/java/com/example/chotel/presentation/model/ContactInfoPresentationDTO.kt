package com.example.chotel.presentation.model

data class ContactInfoPresentationDTO(
    val phone: String = "",
    val email: String = "",
    val showEmailValidationError: Boolean = false,
) {
    fun isValidEmail(email: String = this.email): Boolean {
        val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
        return email.matches(emailRegex)
    }

    fun isValidPhone() = phone.length >= 10

    fun isValid() = isValidPhone() && isValidEmail()
}