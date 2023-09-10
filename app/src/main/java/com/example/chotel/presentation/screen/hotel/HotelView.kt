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
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.chotel.presentation.model.HotelPresentationDTO
import com.example.chotel.presentation.model.RatingPresentationDTO
import com.example.chotel.presentation.screen.destinations.RoomsScreenDestination
import com.example.chotel.presentation.theme.LightGrayText
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.navigate
import org.koin.androidx.compose.getViewModel


@RootNavGraph(start = true)
@Destination
@Composable
fun HotelScreen(
    navController: NavController,
    viewModel: HotelViewModel = getViewModel(),
) = CommonScaffold(title = stringResource(R.string.hotel_screen_title)) {
    val uiState by viewModel.uiState.collectAsState()
    val details = uiState.hotelDetails ?: return@CommonScaffold
    LazyColumn(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            WideCard(
                Modifier.padding(bottom = 16.dp),
                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
            ) {
                ImageSlider(details.images)
                Spacer(Modifier.height(16.dp))
                HotelCard(details.hotel)
                Row(verticalAlignment = Alignment.Bottom) {
                    OneLineText(
                        modifier = Modifier.height(36.dp),
                        text = stringResource(R.string.hotel_minimal_price, details.minimalPrice),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    OneLineText(
                        modifier = Modifier.height(21.dp),
                        text = details.priceDescription,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = LightGrayText,
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
                    text = stringResource(R.string.hotel_description),
                    style = MaterialTheme.typography.titleMedium
                )
                HotelTags(details.features)
                Text(
                    text = details.description,
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
                    val divColor = Color(0x26828796)
                    QuickAction(stringResource(R.string.hotel_facilities)) {
                        Icon(painterResource(R.drawable.vuesax_linear_emoji_happy), null)
                    }
                    Divider(Modifier.padding(start = 36.dp), color = divColor)
                    QuickAction(stringResource(R.string.hotel_included_features)) {
                        Icon(painterResource(R.drawable.vuesax_linear_tick_square), null)
                    }
                    Divider(Modifier.padding(start = 36.dp), color = divColor)
                    QuickAction(stringResource(R.string.hotel_missing_features)) {
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
                    onClick = {
                        navController.navigate(RoomsScreenDestination(details.hotel.name))
                    },
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.hotel_choose_room_button),
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
fun HotelCard(hotel: HotelPresentationDTO) {
    Rating(hotel.rating)
    Text(
        text = hotel.name,
        style = MaterialTheme.typography.titleMedium
    )
    OneLineText(
        text = hotel.address,
        style = MaterialTheme.typography.bodySmall.copy(
            color = MaterialTheme.colorScheme.primary,
        )
    )
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
                text = stringResource(R.string.hotel_most_useful),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = LightGrayText,
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
                    color = LightGrayText,
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}

@Composable
fun Rating(rating: RatingPresentationDTO) {
    Row(
        Modifier
            .width(149.dp)
            .height(29.dp)
            .background(color = Color(0x33FFC700), shape = RoundedCornerShape(size = 5.dp))
            .padding(start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(Icons.Default.Star, null, tint = Color(0xFFFFA800))
        Text(
            text = rating.value,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color(0xFFFFA800),
                textAlign = TextAlign.Center,
            )
        )
    }
}

