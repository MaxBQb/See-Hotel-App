package com.example.chotel.presentation.screen.booking

import com.example.chotel.domain.model.mapper.map
import com.example.chotel.presentation.model.ContactInfoPresentationDTO
import com.example.chotel.presentation.model.TouristPresentationDTO
import com.example.chotel.presentation.model.mapper.PresentationMappers
import com.example.chotel.presentation.screen.booking.BookingContract
import com.example.chotel.presentation.screen.booking.BookingContract.Event.TouristChanged
import com.example.chotel.presentation.screen.core.BaseViewModel
import com.example.chotel.presentation.screen.core.effects.withEffect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import org.koin.android.annotation.KoinViewModel
import com.example.chotel.presentation.screen.booking.BookingContract as Ui

@KoinViewModel
class BookingViewModel(
    hotelRepository: com.example.chotel.domain.repository.HotelRepository,
    mappers: PresentationMappers,
) : BaseViewModel<Ui.State, Ui.Event, Ui.SideEffect>() {
    override fun getInitialState() = Ui.State()

    private val _bookingDetails = hotelRepository.getRoomBookingDetails()
        .map(mappers.roomBooking::map)

    override val uiState = combine(_uiState, _bookingDetails) { state, bookingDetails ->
        state.copy(bookingDetails = bookingDetails)
    }.stateIn()

    override fun handleEvent(event: BookingContract.Event) = when (event) {
        BookingContract.Event.AddTourist -> setState {
            if (it.tourists.last().isEmpty.not())
                it.copy(tourists = it.tourists
                    .filterNot { x -> x.isEmpty } + TouristPresentationDTO.Empty)
            else
                it
        }

        is BookingContract.Event.EmailChanged -> setContactInfo {
            it.copy(email = event.email).withEmailValidated(event.email)
        }

        is BookingContract.Event.PhoneChanged -> setContactInfo {
            it.copy(phone = event.phone.filter(Char::isDigit))
        }

        is TouristChanged -> handleTouristChange(event)
        BookingContract.Event.Submit -> onSubmit()
        is BookingContract.Event.EffectConsumed -> handleEffectConsumption(event)
    }

    private fun ContactInfoPresentationDTO.withEmailValidated(
        email: String = this.email,
    ) = copy(
        email = email,
        showEmailValidationError = !isValidEmail(email)
    )

    private fun onSubmit() = setState {
        if (hasAllRequiredData(it))
            it.copy(
                highlightEmptyFields = true,
                contactInfo = it.contactInfo.withEmailValidated(),
            ).withEffect(Ui.SideEffect.ShowError)
        else
            it.withEffect(Ui.SideEffect.GoNext)
    }

    private fun hasAllRequiredData(it: Ui.State) =
        it.tourists.any { x -> !x.isFilled && !x.isEmpty }
                || it.tourists[0].isEmpty
                || !it.contactInfo.isValid()

    private fun handleTouristChange(event: TouristChanged) = setTourist(event.pos) {
        when (event) {
            is TouristChanged.BirthdayChanged -> it.copy(birthday = event.birthday)
            is TouristChanged.CitizenshipChanged -> it.copy(citizenship = event.citizenship)
            is TouristChanged.ExpansionToggled -> it.copy(expanded = !it.expanded)
            is TouristChanged.InternationalPassportCodeChanged -> it.copy(
                internationalPassportCode = event.internationalPassportCode
            )

            is TouristChanged.InternationalPassportExpirationDateChanged -> it.copy(
                internationalPassportExpirationDate = event.internationalPassportExpirationDate
            )

            is TouristChanged.NameChanged -> it.copy(name = event.name)
            is TouristChanged.SurnameChanged -> it.copy(surname = event.surname)
        }
    }

    private inline fun setContactInfo(
        transformation: (ContactInfoPresentationDTO) -> ContactInfoPresentationDTO
    ) = setState { it.copy(contactInfo = transformation(it.contactInfo)) }

    private inline fun setTourist(
        position: Int, transformation: (TouristPresentationDTO) -> TouristPresentationDTO
    ) = setState {
        it.copy(tourists = it.tourists.mapIndexed { i, tourist ->
            if (i == position)
                transformation(tourist)
            else
                tourist
        })
    }
}