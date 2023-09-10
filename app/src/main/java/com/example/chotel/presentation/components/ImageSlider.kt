package com.example.chotel.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlin.math.absoluteValue

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun ImageSlider(models: List<*>) = Box {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) { models.size }
    HorizontalPager(
        modifier = Modifier,
        state = pagerState,
        pageSpacing = 24.dp,
    ) {
        AsyncImage(
            model = models[it],
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .width(343.dp)
                .height(257.dp)
                .clip(RoundedCornerShape(5))
        )
    }

    Row(
        Modifier
            .wrapContentSize()
            .align(Alignment.BottomCenter)
            .padding(8.dp)
            .clip(RoundedCornerShape(25))
            .background(MaterialTheme.colorScheme.surface),
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.size(7.5.dp))
        repeat(models.size) { i ->
            val distance = (pagerState.currentPage - i).absoluteValue.toFloat() / models.size
            val color = if (distance == 0f) Color.Black else lerp(Color.Gray, Color.White, distance)
            Box(
                modifier = Modifier
                    .padding(2.5.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(7.dp)
            )
        }
        Spacer(modifier = Modifier.size(7.5.dp))
    }
}