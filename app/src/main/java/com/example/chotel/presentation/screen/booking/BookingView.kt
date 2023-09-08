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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.chotel.R
import com.example.chotel.presentation.components.CommonScaffold
import com.example.chotel.presentation.components.CommonTextField
import com.example.chotel.presentation.components.OneLineText
import com.example.chotel.presentation.components.PhoneNumberField
import com.example.chotel.presentation.components.WideCard
import com.example.chotel.presentation.screen.destinations.OrderPaidScreenDestination
import com.example.chotel.presentation.screen.hotel.Rating
import com.example.chotel.presentation.theme.LightGrayText
import com.example.chotel.presentation.utils.Rub
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigate

data class TouristCardState(
    val name: String = "",
    val surname: String = "",
    val birthday: String = "",
    val citizenship: String = "",
    val internationalPassportCode: String = "",
    val internationalPassportExpirationDate: String = "",
    val expanded: Boolean = false
)

@Destination
@Composable
fun BookingScreen(
    navController: NavController,
) = CommonScaffold(stringResource(R.string.booking_title), navController) {
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
                Rating(5)
                Text(
                    text = "Steigenberger Makadi",
                    style = MaterialTheme.typography.titleMedium
                )
                OneLineText(
                    text = "Madinat Makadi, Safaga Road, Makadi Bay, Египет",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.primary,
                    )
                )
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
                        stringResource(R.string.booking_departure) to "Санкт-Петербург",
                        stringResource(R.string.booking_arrival) to "Египет, Хургада",
                        stringResource(R.string.booking_tour_dates) to "19.09.2023 – 27.09.2023",
                        stringResource(R.string.booking_nights_amount) to "7 ночей",
                        stringResource(R.string.booking_hotel_name) to "Steigenberger Makadi",
                        stringResource(R.string.booking_room_description) to "Стандартный с видом на бассейн или сад",
                        stringResource(R.string.booking_nutrition) to "Все включено",
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
                    value = "9515559900",
                    onValueChange = { },
                    label = stringResource(R.string.booking_client_phone),
                )
                CommonTextField(
                    value = "examplemail.000@mail.ru",
                    onValueChange = { },
                    label = stringResource(R.string.booking_client_email),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
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
        val cards = listOf(
            TouristCardState("Иван", "Иванов", expanded = true),
            TouristCardState()
        )
        itemsIndexed(cards) { i, card ->
            TouristCardListItem(i, card)
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
                        { },
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
                PriceList()
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
                    onClick = { navController.navigate(OrderPaidScreenDestination) },
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.book_room_button, 198036.Rub),
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
}

@Composable
private fun PriceList() {
    val firstStyle = MaterialTheme.typography.bodyMedium.copy(
        fontWeight = FontWeight(400),
        color = LightGrayText,
    )
    val secondStyle = firstStyle.copy(
        color = Color.Black,
        textAlign = TextAlign.End
    )
    for ((first, second) in mapOf(
        stringResource(R.string.booking_tour_price) to 186600.Rub,
        stringResource(R.string.booking_tour_fuel_price) to 9300.Rub,
        stringResource(R.string.booking_tour_service_price) to 2136.Rub,
    ))
        Row {
            Text(first, Modifier.weight(1f), style = firstStyle)
            Text(second, Modifier.weight(1f), style = secondStyle)
        }
    Row {
        Text(stringResource(R.string.booking_tour_total_price), Modifier.weight(1f), style = firstStyle)
        Text(
            198036.Rub,
            Modifier.weight(1f), style = secondStyle.copy(
                fontWeight = FontWeight(600),
                color = MaterialTheme.colorScheme.primary,
            )
        )
    }
}

@Composable
private fun TouristCardListItem(i: Int, card: TouristCardState) {
    val ordinal = (i + 1).toOrdinal().replaceFirstChar { it.uppercase() }
    TouristCard(card, stringResource(R.string.booking_tourist_card_title, ordinal))
}

@Composable
private fun Int.toOrdinal() = MessageFormat(
    "{0,spellout,%spellout-ordinal-masculine}",
    LocalContext.current.resources.configuration.locales[0]
).format(arrayOf(this))


@Composable
private fun TouristCard(state: TouristCardState, title: String) {
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
            ExpandToggle(state.expanded) {}
        }
        AnimatedVisibility(state.expanded) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Spacer(Modifier.height(8.dp))
                CommonTextField(
                    value = state.name,
                    onValueChange = {},
                    label = stringResource(R.string.booking_tourist_name),
                )
                CommonTextField(
                    value = state.surname,
                    onValueChange = {},
                    label = stringResource(R.string.booking_tourist_surname),
                )
                CommonTextField(
                    value = state.birthday,
                    onValueChange = {},
                    label = stringResource(R.string.booking_tourist_birthday),
                )
                CommonTextField(
                    value = state.citizenship,
                    onValueChange = {},
                    label = stringResource(R.string.booking_tourist_citizenship),
                )
                CommonTextField(
                    value = state.internationalPassportCode,
                    onValueChange = {},
                    label = stringResource(R.string.booking_tourist_international_passport_code),
                )
                CommonTextField(
                    value = state.internationalPassportExpirationDate,
                    onValueChange = {},
                    label = stringResource(R.string.booking_tourist_international_passport_expiration_date),
                )
            }
        }
    }
}

@Composable
private fun ExpandToggle(
    expanded: Boolean,
    onValueChange: (Boolean) -> Unit
) {
    IconButton(
        { onValueChange(!expanded) },
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
