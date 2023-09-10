package com.example.chotel.presentation.model

data class TouristPresentationDTO(
    val name: String = "",
    val surname: String = "",
    val birthday: String = "",
    val citizenship: String = "",
    val internationalPassportCode: String = "",
    val internationalPassportExpirationDate: String = "",
    val expanded: Boolean = true,
)

val EmptyTouristPresentationDTO = TouristPresentationDTO()
val TouristPresentationDTO.isEmpty get() = this.copy(expanded=true) == EmptyTouristPresentationDTO