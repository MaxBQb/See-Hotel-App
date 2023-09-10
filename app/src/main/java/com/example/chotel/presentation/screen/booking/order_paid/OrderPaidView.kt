package com.example.chotel.presentation.screen.booking.order_paid

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.chotel.R
import com.example.chotel.presentation.components.CommonScaffold
import com.example.chotel.presentation.screen.destinations.HotelScreenDestination
import com.example.chotel.presentation.screen.destinations.OrderPaidScreenDestination
import com.example.chotel.presentation.theme.LightGrayText
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.navigation.popUpTo
import org.koin.androidx.compose.getViewModel

@Destination
@Composable
fun OrderPaidScreen(
    navController: NavController,
    viewModel: OrderPaidViewModel = getViewModel()
) = CommonScaffold(stringResource(R.string.order_paid_title), navController) {
    val uiState by viewModel.uiState.collectAsState()
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        Arrangement.SpaceBetween,
    ) {
        Column(
            Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Row(Modifier.fillMaxWidth(), Arrangement.Center) {
                Icon(
                    painterResource(R.drawable.order_paid), null,
                    Modifier
                        .size(94.dp)
                        .background(
                            color = MaterialTheme.colorScheme.background,
                            shape = RoundedCornerShape(size = 1000.dp)
                        )
                        .padding(start = 25.dp, top = 25.dp, end = 25.dp, bottom = 25.dp),
                    tint = Color.Unspecified
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, bottom = 16.dp),
                text = stringResource(R.string.order_paid_headline),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(
                    R.string.order_paid_notice,
                    uiState.orderId,
                ),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight(400),
                    color = LightGrayText,
                    textAlign = TextAlign.Center,
                )
            )
        }
        Column {
            Divider(color = Color(0xFFE8E9EC))
            Button(
                modifier = Modifier
                    .padding(16.dp, 12.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(25),
                onClick = {
                    navController.navigate(HotelScreenDestination) {
                        launchSingleTop = true
                        popUpTo(OrderPaidScreenDestination) { inclusive = true }
                    }
                },
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.order_paid_finish_button),
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
