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
import androidx.compose.material3.ButtonDefaults
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
) = CommonScaffold("Бронирование", navController) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F9)),
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
                        color = Color(0xFF0D72FF),
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
                        "Вылет из" to "Санкт-Петербург",
                        "Страна, город" to "Египет, Хургада",
                        "Даты" to "19.09.2023 – 27.09.2023",
                        "Кол-во ночей" to "7 ночей",
                        "Отель" to "Steigenberger Makadi",
                        "Номер" to "Стандартный с видом на бассейн или сад",
                        "Питание" to "Все включено",
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
                    text = "Информация о покупателе",
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(Modifier)
                PhoneNumberField(
                    value = "9515559900",
                    onValueChange = { },
                    label = "Номер телефона",
                )
                CommonTextField(
                    value = "examplemail.000@mail.ru",
                    onValueChange = { },
                    label = "Почта",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                )
                Text(
                    text = "Эти данные никому не передаются. После оплаты мы вышли чек на указанный вами номер и почту",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight(400),
                        color = Color(0xFF828796),
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
                        text = "Добавить туриста",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Spacer(Modifier.weight(1f))
                    IconButton(
                        { },
                        Modifier
                            .padding(end = 8.dp)
                            .background(Color(0xFF0D72FF), shape = RoundedCornerShape(size = 6.dp))
                            .size(32.dp)
                    ) {
                        Icon(
                            Icons.Default.Add, null,
                            tint = MaterialTheme.colorScheme.background
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
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D72FF))
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Оплатить 198 036 ₽",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            lineHeight = 17.6.sp,
                            color = Color(0xFFFFFFFF),
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
        color = Color(0xFF828796),
    )
    val secondStyle = firstStyle.copy(
        color = Color(0xFF000000),
        textAlign = TextAlign.End
    )
    for ((first, second) in mapOf(
        "Тур" to "186 600 ₽",
        "Топливный сбор" to "9 300 ₽",
        "Сервисный сбор" to "2 136 ₽",
    ))
        Row {
            Text(first, Modifier.weight(1f), style = firstStyle)
            Text(second, Modifier.weight(1f), style = secondStyle)
        }
    Row {
        Text("К оплате", Modifier.weight(1f), style = firstStyle)
        Text(
            "198 036 ₽", Modifier.weight(1f), style = secondStyle.copy(
                fontWeight = FontWeight(600),
                color = Color(0xFF0D72FF),
            )
        )
    }
}

@Composable
private fun TouristCardListItem(i: Int, card: TouristCardState) {
    val ordinal = (i + 1).toOrdinal().replaceFirstChar { it.uppercase() }
    TouristCard(card, stringResource(R.string.tourist_card_title, ordinal))
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
                    label = "Имя",
                )
                CommonTextField(
                    value = state.surname,
                    onValueChange = {},
                    label = "Фамилия",
                )
                CommonTextField(
                    value = state.birthday,
                    onValueChange = {},
                    label = "Дата рождения",
                )
                CommonTextField(
                    value = state.citizenship,
                    onValueChange = {},
                    label = "Гражданство",
                )
                CommonTextField(
                    value = state.internationalPassportCode,
                    onValueChange = {},
                    label = "Номер загранпаспорта",
                )
                CommonTextField(
                    value = state.internationalPassportExpirationDate,
                    onValueChange = {},
                    label = "Срок действия загранпаспорта",
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
            .background(Color(0x1A0D72FF), shape = RoundedCornerShape(size = 6.dp))
            .size(32.dp)
    ) {
        Icon(
            if (expanded) Icons.Default.KeyboardArrowUp
            else Icons.Default.KeyboardArrowDown, null,
            tint = Color(0xFF0D72FF)
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
        color = Color(0xFF828796),
    )
    val secondStyle = firstStyle.copy(color = Color(0xFF000000))
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
