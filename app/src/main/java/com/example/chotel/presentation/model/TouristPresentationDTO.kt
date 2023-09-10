package com.example.chotel.presentation.model

data class TouristPresentationDTO(
    val name: String = "",
    val surname: String = "",
    val birthday: String = "",
    val citizenship: String = "",
    val internationalPassportCode: String = "",
    val internationalPassportExpirationDate: String = "",
    val expanded: Boolean = true,
) {
    companion object {
        val Empty = TouristPresentationDTO()
    }

    val isEmpty
        get() = name.isBlank() && surname.isBlank()
                && birthday.isBlank() && citizenship.isBlank()
                && internationalPassportCode.isBlank()
                && internationalPassportExpirationDate.isBlank()
    val isFilled
        get() = name.isNotBlank() && surname.isNotBlank()
                && birthday.isNotBlank() && citizenship.isNotBlank()
                && internationalPassportCode.isNotBlank()
                && internationalPassportExpirationDate.isNotBlank()
}
