package com.example.chotel.presentation.screen.booking

import androidx.compose.runtime.Immutable
import com.example.chotel.presentation.model.ContactInfoPresentationDTO
import com.example.chotel.presentation.model.EmptyTouristPresentationDTO
import com.example.chotel.presentation.model.RoomBookingDetailsPresentationDTO
import com.example.chotel.presentation.model.TouristPresentationDTO
import com.example.chotel.presentation.screen.core.UiEvent
import com.example.chotel.presentation.screen.core.UiState

interface BookingContract {
    @Immutable
    data class State(
        val bookingDetails: RoomBookingDetailsPresentationDTO? = null,
        val contactInfo: ContactInfoPresentationDTO = ContactInfoPresentationDTO(),
        val tourists: List<TouristPresentationDTO> = listOf(EmptyTouristPresentationDTO),
    ) : UiState

    sealed interface Event : UiEvent {
        data class PhoneChanged(val phone: String) : Event
        data class EmailChanged(val email: String) : Event
        object AddTourist : Event
        sealed interface TouristChanged : Event {
            val pos: Int
            data class NameChanged(val name: String, override val pos: Int) : TouristChanged
            data class SurnameChanged(val surname: String, override val pos: Int) : TouristChanged
            data class BirthdayChanged(
                val birthday: String,
                override val pos: Int
            ) : TouristChanged
            data class CitizenshipChanged(
                val citizenship: String,
                override val pos: Int
            ) : TouristChanged
            data class InternationalPassportCodeChanged(
                val internationalPassportCode: String,
                override val pos: Int
            ) : TouristChanged
            data class InternationalPassportExpirationDateChanged(
                val internationalPassportExpirationDate: String,
                override val pos: Int
            ) : TouristChanged
            data class ExpansionToggled(override val pos: Int) : TouristChanged
        }
    }
}