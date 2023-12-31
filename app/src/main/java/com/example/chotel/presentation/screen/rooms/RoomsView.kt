package com.example.chotel.presentation.screen.rooms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.chotel.presentation.screen.destinations.BookingScreenDestination
import com.example.chotel.presentation.theme.LightGrayText
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigate
import org.koin.androidx.compose.getViewModel


@Destination
@Composable
fun RoomsScreen(
    title: String,
    navController: NavController,
    viewModel: RoomsViewModel = getViewModel()
) = CommonScaffold(title, navController) {
    val uiState by viewModel.uiState.collectAsState()
    val rooms = uiState.rooms
    LazyColumn(
        Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(rooms) {room ->
            WideCard(
                Modifier.padding(vertical = 16.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                ImageSlider(room.images)
                Spacer(Modifier.height(16.dp))
                Text(
                    text = room.name,
                    style = MaterialTheme.typography.titleMedium
                )
                RoomTags(room.features)
                Spacer(Modifier.height(8.dp))
                Details(stringResource(R.string.room_details))
                Spacer(Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.Bottom) {
                    OneLineText(
                        modifier = Modifier
                            .height(36.dp)
                            .padding(end = 8.dp),
                        text = room.price,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    OneLineText(
                        modifier = Modifier.height(21.dp),
                        text = room.priceNote,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = LightGrayText,
                            fontWeight = FontWeight(400),
                        )
                    )
                }
                Spacer(Modifier.height(16.dp))
                Button(
                    modifier = Modifier.height(48.dp),
                    shape = RoundedCornerShape(25),
                    onClick = { navController.navigate(BookingScreenDestination) },
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.room_booking_button),
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
private fun Details(title: String) {
    Row(
        modifier = Modifier
            .width(192.dp)
            .background(
                color = Color(0x1A0D72FF),
                shape = RoundedCornerShape(size = 5.dp)
            )
            .padding(start = 10.dp, top = 8.dp, end = 2.dp, bottom = 5.dp),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
            )
        )
        Icon(
            Icons.Default.KeyboardArrowRight,
            null,
            Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.primary)
    }
}

@Composable
@OptIn(ExperimentalLayoutApi::class)
private fun RoomTags(tags: List<String>) {
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
