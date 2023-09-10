package com.example.chotel.presentation.screen.booking

import android.icu.text.MessageFormat
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.chotel.R
import com.example.chotel.presentation.components.AnimateAppearance
import com.example.chotel.presentation.components.CommonScaffold
import com.example.chotel.presentation.components.CommonTextField
import com.example.chotel.presentation.components.PhoneNumberField
import com.example.chotel.presentation.components.WideCard
import com.example.chotel.presentation.model.RoomBookingDetailsPresentationDTO
import com.example.chotel.presentation.model.TouristPresentationDTO
import com.example.chotel.presentation.screen.core.effects.EffectKey
import com.example.chotel.presentation.screen.core.effects.SideEffect
import com.example.chotel.presentation.screen.destinations.OrderPaidScreenDestination
import com.example.chotel.presentation.screen.hotel.HotelCard
import com.example.chotel.presentation.theme.LightGrayText
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigate
import org.koin.androidx.compose.getViewModel
import java.util.Locale
import com.example.chotel.presentation.screen.booking.BookingContract as Ui

@Destination
@Composable
fun BookingScreen(
    navController: NavController,
    viewModel: BookingViewModel = getViewModel()
) {
    val snackbarState = remember { SnackbarHostState() }
    CommonScaffold(stringResource(R.string.booking_title), navController, snackbarState) {
        val uiState by viewModel.uiState.collectAsState()
        val bookingDetails = uiState.bookingDetails ?: return@CommonScaffold
        val onEvent = viewModel::onEvent
        LazyColumn(
            Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                WideCard(
                    Modifier.padding(vertical = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    HotelCard(hotel = bookingDetails.hotel)
                }
            }

            item {
                WideCard(
                    Modifier.padding(vertical = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Table(
                        mapOf(
                            stringResource(R.string.booking_departure) to bookingDetails.departureCountry,
                            stringResource(R.string.booking_arrival) to bookingDetails.arrivalCountry,
                            stringResource(R.string.booking_tour_dates) to bookingDetails.tourDatesSpan,
                            stringResource(R.string.booking_nights_amount) to pluralStringResource(
                                R.plurals.booking_nights_amount,
                                bookingDetails.nightsAmount,
                                bookingDetails.nightsAmount,
                            ),
                            stringResource(R.string.booking_hotel_name) to bookingDetails.hotel.name,
                            stringResource(R.string.booking_room_description) to bookingDetails.room,
                            stringResource(R.string.booking_nutrition) to bookingDetails.nutrition,
                        )
                    )
                }
            }

            item {
                WideCard(
                    Modifier.padding(vertical = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.booking_client_info),
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Spacer(Modifier)
                    PhoneNumberField(
                        value = uiState.contactInfo.phone,
                        onValueChange = { onEvent(Ui.Event.PhoneChanged(it)) },
                        label = stringResource(R.string.booking_client_phone),
                        modifier = Modifier.fillMaxWidth(),
                        isError = uiState.highlightEmptyFields && !uiState.contactInfo.isValidPhone()
                    )
                    CommonTextField(
                        value = uiState.contactInfo.email,
                        onValueChange = { onEvent(Ui.Event.EmailChanged(it)) },
                        label = stringResource(R.string.booking_client_email),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier.fillMaxWidth(),
                        isError = uiState.contactInfo.showEmailValidationError,
                    )
                    Text(
                        text = stringResource(R.string.booking_privacy_notice),
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight(400),
                            color = LightGrayText
                        )
                    )
                }
            }
            itemsIndexed(uiState.tourists) { i, card ->
                val ordinal = (i + 1).toOrdinal().replaceFirstChar { it.uppercase() }
                AnimateAppearance(initiallyVisible = !card.isEmpty || i != uiState.tourists.lastIndex) {
                    TouristCard(
                        card,
                        stringResource(R.string.booking_tourist_card_title, ordinal),
                        uiState.highlightEmptyFields && (!card.isEmpty || i == 0),
                        { onEvent(Ui.Event.TouristChanged.ExpansionToggled(i)) },
                        { onEvent(Ui.Event.TouristChanged.NameChanged(it, i)) },
                        { onEvent(Ui.Event.TouristChanged.SurnameChanged(it, i)) },
                        { onEvent(Ui.Event.TouristChanged.BirthdayChanged(it, i)) },
                        { onEvent(Ui.Event.TouristChanged.CitizenshipChanged(it, i)) },
                        { onEvent(Ui.Event.TouristChanged.InternationalPassportCodeChanged(it, i)) },
                        {
                            onEvent(
                                Ui.Event.TouristChanged
                                    .InternationalPassportExpirationDateChanged(it, i)
                            )
                        },
                    )
                }
            }

            item {
                WideCard(
                    Modifier.padding(vertical = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Row(
                        Modifier.height(32.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.booking_add_tourist),
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Spacer(Modifier.weight(1f))
                        IconButton(
                            { onEvent(Ui.Event.AddTourist) },
                            Modifier
                                .padding(end = 8.dp)
                                .background(
                                    MaterialTheme.colorScheme.primary,
                                    RoundedCornerShape(size = 6.dp)
                                )
                                .size(32.dp)
                        ) {
                            Icon(
                                Icons.Default.Add, null,
                                tint = MaterialTheme.colorScheme.surface
                            )
                        }
                    }
                }
            }

            item {
                WideCard(
                    Modifier.padding(vertical = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    PriceList(bookingDetails.prices)
                }
            }

            item {
                WideCard(
                    shape = RectangleShape,
                ) {
                    Button(
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .height(48.dp),
                        shape = RoundedCornerShape(25),
                        onClick = { onEvent(Ui.Event.Submit) },
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(
                                R.string.book_room_button,
                                bookingDetails.prices.total
                            ),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                lineHeight = 17.6.sp,
                                textAlign = TextAlign.Center,
                                letterSpacing = 0.1.sp,
                            )
                        )
                    }
                }
            }
        }

        val onConsumed: (EffectKey) -> Unit = { onEvent(Ui.Event.EffectConsumed(it)) }
        val context = LocalContext.current.applicationContext
        SideEffect<Ui.SideEffect.GoNext>(uiState.sideEffectsHolder, onConsumed, true) {
            navController.navigate(OrderPaidScreenDestination)
        }
        SideEffect<Ui.SideEffect.ShowError>(
            uiState.sideEffectsHolder, onConsumed,
            false, snackbarState
        ) {
            snackbarState.showSnackbar(context.getString(R.string.booking_error_empty_fields))
        }
    }
}

@Composable
private fun PriceList(prices: RoomBookingDetailsPresentationDTO.Prices) {
    val firstStyle = MaterialTheme.typography.bodyMedium.copy(
        fontWeight = FontWeight(400),
        color = LightGrayText,
    )
    val secondStyle = firstStyle.copy(
        color = Color.Black,
        textAlign = TextAlign.End
    )
    for ((first, second) in mapOf(
        stringResource(R.string.booking_tour_price) to prices.tour,
        stringResource(R.string.booking_tour_fuel_price) to prices.fuel,
        stringResource(R.string.booking_tour_service_price) to prices.service,
    ))
        Row {
            Text(first, Modifier.weight(1f), style = firstStyle)
            Text(second, Modifier.weight(1f), style = secondStyle)
        }
    Row {
        Text(
            stringResource(R.string.booking_tour_total_price),
            Modifier.weight(1f),
            style = firstStyle
        )
        Text(
            prices.total,
            Modifier.weight(1f), style = secondStyle.copy(
                fontWeight = FontWeight(600),
                color = MaterialTheme.colorScheme.primary,
            )
        )
    }
}

@Composable
private fun Int.toOrdinal() = MessageFormat(
    "{0,spellout,%spellout-ordinal-masculine}",
    Locale("ru-ru"),
//  LocalContext.current.resources.configuration.locales[0] // For any locale
).format(arrayOf(this))


@Composable
private fun TouristCard(
    state: TouristPresentationDTO,
    title: String,
    highlightEmptyFields: Boolean = false,
    onExpansionToggle: () -> Unit,
    onNameChange: (String) -> Unit,
    onSurnameChange: (String) -> Unit,
    onBirthdayChange: (String) -> Unit,
    onCitizenshipChange: (String) -> Unit,
    onInternationalPassportCodeChange: (String) -> Unit,
    onInternationalPassportExpirationDateChanged: (String) -> Unit
) {
    WideCard(
        Modifier.padding(vertical = 16.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            Modifier.height(32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(Modifier.weight(1f))
            ExpandToggle(state.expanded, onExpansionToggle)
        }
        AnimatedVisibility(state.expanded) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Spacer(Modifier.height(8.dp))
                CommonTextField(
                    value = state.name,
                    onValueChange = onNameChange,
                    label = stringResource(R.string.booking_tourist_name),
                    modifier = Modifier.fillMaxWidth(),
                    isError = state.name.isBlank() && highlightEmptyFields,
                )
                CommonTextField(
                    value = state.surname,
                    onValueChange = onSurnameChange,
                    label = stringResource(R.string.booking_tourist_surname),
                    modifier = Modifier.fillMaxWidth(),
                    isError = state.surname.isBlank() && highlightEmptyFields,
                )
                CommonTextField(
                    value = state.birthday,
                    onValueChange = onBirthdayChange,
                    label = stringResource(R.string.booking_tourist_birthday),
                    modifier = Modifier.fillMaxWidth(),
                    isError = state.birthday.isBlank() && highlightEmptyFields,
                )
                CommonTextField(
                    value = state.citizenship,
                    onValueChange = onCitizenshipChange,
                    label = stringResource(R.string.booking_tourist_citizenship),
                    modifier = Modifier.fillMaxWidth(),
                    isError = state.citizenship.isBlank() && highlightEmptyFields,
                )
                CommonTextField(
                    value = state.internationalPassportCode,
                    onValueChange = onInternationalPassportCodeChange,
                    label = stringResource(R.string.booking_tourist_international_passport_code),
                    modifier = Modifier.fillMaxWidth(),
                    isError = state.internationalPassportCode.isBlank()
                            && highlightEmptyFields,
                )
                CommonTextField(
                    value = state.internationalPassportExpirationDate,
                    onValueChange = onInternationalPassportExpirationDateChanged,
                    label = stringResource(R.string.booking_tourist_international_passport_expiration_date),
                    modifier = Modifier.fillMaxWidth(),
                    isError = state.internationalPassportExpirationDate.isBlank()
                            && highlightEmptyFields,
                )
            }
        }
    }
}

@Composable
private fun ExpandToggle(
    expanded: Boolean,
    onValueChange: () -> Unit
) {
    IconButton(
        onValueChange,
        Modifier
            .padding(end = 8.dp)
            .background(Color(0x1A0D72FF), RoundedCornerShape(size = 6.dp))
            .size(32.dp)
    ) {
        Icon(
            if (expanded) Icons.Default.KeyboardArrowUp
            else Icons.Default.KeyboardArrowDown, null,
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun Table(
    values: Map<String, String>,
    ratio: Float = 0.5f
) {
    val firstStyle = MaterialTheme.typography.bodyMedium.copy(
        fontWeight = FontWeight(400),
        color = LightGrayText,
    )
    val secondStyle = firstStyle.copy(color = Color.Black)
    for ((first, second) in values)
        Row {
            Text(
                modifier = Modifier.weight(ratio),
                text = first,
                style = firstStyle
            )
            Text(
                text = second,
                modifier = Modifier.weight(1f - ratio),
                style = secondStyle
            )
        }
}
