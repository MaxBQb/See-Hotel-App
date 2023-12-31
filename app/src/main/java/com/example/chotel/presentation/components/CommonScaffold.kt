package com.example.chotel.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonScaffold(
    title: String,
    navController: NavController? = null,
    snackbarState: SnackbarHostState = remember { SnackbarHostState() },
    content: @Composable () -> Unit,
) = Scaffold(
    topBar = {
        TopAppBar(
            navigationIcon = nav@{
                navController ?: return@nav
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.Default.KeyboardArrowLeft, null)
                }
            },
            title = {
                Text(
                    title,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(end=(16+24).dp).fillMaxWidth()
                )
            }
        )
    },
    snackbarHost = {
        SnackbarHost(snackbarState) {
            Snackbar(
                it,
                containerColor = lerp(
                    MaterialTheme.colorScheme.surface,
                    MaterialTheme.colorScheme.primary,
                    0.1f,
                ).copy(alpha = 0.9f),
                contentColor = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(it)
    ) {
        content()
    }
}