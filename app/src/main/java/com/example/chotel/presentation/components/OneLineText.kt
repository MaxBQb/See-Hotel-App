package com.example.chotel.presentation.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun OneLineText(
    text: String,
    style: TextStyle,
    modifier: Modifier = Modifier,
) = Text(
    modifier = modifier.horizontalScroll(rememberScrollState(0)),
    maxLines = 1,
    overflow = TextOverflow.Visible,
    text = text,
    style = style
)