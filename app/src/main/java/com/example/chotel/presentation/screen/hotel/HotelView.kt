package com.example.chotel.presentation.screen.hotel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.chotel.R
import com.example.chotel.presentation.components.CommonScaffold
import com.example.chotel.presentation.components.ImageSlider
import com.example.chotel.presentation.components.OneLineText
import com.example.chotel.presentation.components.WideCard
import com.example.chotel.presentation.screen.destinations.RoomsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.navigate


@RootNavGraph(start = true)
@Destination
@Composable
fun HotelScreen(
    navController: NavController,
) = CommonScaffold(title = stringResource(R.string.hotel_screen_title)) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F9)),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            WideCard(
                Modifier.padding(bottom = 16.dp),
                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
            ) {
                ImageSlider(listOf(null, null, null, null, null), painterResource(R.drawable.hotel_preview))
                Spacer(Modifier.height(16.dp))
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
                Row(verticalAlignment = Alignment.Bottom) {
                    OneLineText(
                        modifier = Modifier.height(36.dp),
                        text = "от 134 673 ₽",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    OneLineText(
                        modifier = Modifier.height(21.dp),
                        text = "за тур с перелётом",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color(0xFF828796),
                            fontWeight = FontWeight(400),
                        )
                    )
                }
            }
        }
        item {
            WideCard(
                Modifier.padding(vertical = 16.dp),
                shape = RoundedCornerShape(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Об отеле",
                    style = MaterialTheme.typography.titleMedium
                )
                HotelTags(
                    listOf(
                        "3-я линия", "Платный Wi-Fi в фойе",
                        "30 км до аэропорта", "1 км до пляжа"
                    )
                )
                Text(
                    text = "Отель VIP-класса с собственными гольф полями. Высокий уровнь сервиса. Рекомендуем для респектабельного отдыха. Отель принимает гостей от 18 лет!",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color(0xE5000000),
                        fontWeight = FontWeight(400),
                    )
                )
                Column(
                    Modifier.padding(15.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                    horizontalAlignment = Alignment.End,
                ) {
                    QuickAction("Удобства") {
                        Icon(painterResource(R.drawable.vuesax_linear_emoji_happy), null)
                    }
                    Divider(Modifier.padding(start = 36.dp), color = Color(0x82879626))
                    QuickAction("Что включено") {
                        Icon(painterResource(R.drawable.vuesax_linear_tick_square), null)
                    }
                    Divider(Modifier.padding(start = 36.dp), color = Color(0x82879626))
                    QuickAction("Что не включено") {
                        Icon(painterResource(R.drawable.vuesax_linear_close_square), null)
                    }
                }
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
                    onClick = { navController.navigate(RoomsScreenDestination) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D72FF))
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "К выбору номера",
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
private fun QuickAction(
    title: String,
    icon: @Composable () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon()
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF2C3035),
                )
            )
            Text(
                text = "Самое необходимое",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color(0xFF828796),
                )
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            Icons.Default.KeyboardArrowRight, null,
        )
    }
}

@Composable
@OptIn(ExperimentalLayoutApi::class)
private fun HotelTags(tags: List<String>) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
    ) {
        tags.forEach {
            Text(
                modifier = Modifier
                    .background(
                        color = Color(0xFFFBFBFC),
                        shape = RoundedCornerShape(size = 5.dp)
                    )
                    .padding(start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp),
                text = it,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF828796),
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}

@Composable
fun Rating(rating: Int) {
    Row(
        Modifier
            .width(149.dp)
            .height(29.dp)
            .background(color = Color(0x33FFC700), shape = RoundedCornerShape(size = rating.dp))
            .padding(start = 10.dp, top = rating.dp, end = 10.dp, bottom = rating.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(Icons.Default.Star, null, tint = Color(0xFFFFA800))
        Text(
            text = stringResource(R.string.hotel_rank_best, rating),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color(0xFFFFA800),
                textAlign = TextAlign.Center,
            )
        )
    }
}

