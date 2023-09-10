package com.example.chotel.presentation.screen.booking

import com.example.chotel.domain.model.mapper.map
import com.example.chotel.domain.repository.HotelRepository
import com.example.chotel.presentation.model.ContactInfoPresentationDTO
import com.example.chotel.presentation.model.EmptyTouristPresentationDTO
import com.example.chotel.presentation.model.TouristPresentationDTO
import com.example.chotel.presentation.model.isEmpty
import com.example.chotel.presentation.model.mapper.PresentationMappers
import com.example.chotel.presentation.screen.booking.BookingContract
import com.example.chotel.presentation.screen.booking.BookingContract.Event.TouristChanged
import com.example.chotel.presentation.screen.core.PureInteractiveViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import org.koin.android.annotation.KoinViewModel
import com.example.chotel.presentation.screen.booking.BookingContract as Ui

@KoinViewModel
class BookingViewModel(
    hotelRepository: HotelRepository,
    mappers: PresentationMappers,
) : PureInteractiveViewModel<Ui.State, Ui.Event>() {
    override fun getInitialState() = Ui.State()

    private val _bookingDetails = hotelRepository.getRoomBookingDetails()
        .map(mappers.roomBooking::map)

    override val uiState = combine(_uiState, _bookingDetails) {
        state, bookingDetails -> state.copy(bookingDetails=bookingDetails)
    }.stateIn()

    override fun handleEvent(event: BookingContract.Event) = when(event) {
        BookingContract.Event.AddTourist -> setState {
            if (it.tourists.last().isEmpty.not())
                it.copy(tourists = it.tourists
                    .filterNot { x -> x.isEmpty } + EmptyTouristPresentationDTO)
            else
                it
        }
        is BookingContract.Event.EmailChanged -> setContactInfo { it.copy(email = event.email) }
        is BookingContract.Event.PhoneChanged -> setContactInfo {
            it.copy(phone = event.phone.filter(Char::isDigit))
        }
        is TouristChanged -> handleTouristChange(event)
    }

    private fun handleTouristChange(event: TouristChanged)
        = setTourist(event.pos) {
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