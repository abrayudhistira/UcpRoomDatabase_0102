package com.umy.ucproomdatabase_0102.ui.view.matakuliah

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.umy.ucproomdatabase_0102.R
import com.umy.ucproomdatabase_0102.ui.customwidget.TopAppBar
import com.umy.ucproomdatabase_0102.ui.viewmodel.dosen.HomeDosenViewModel
import com.umy.ucproomdatabase_0102.ui.viewmodel.dosen.PenyediaDosenViewModel
import com.umy.ucproomdatabase_0102.ui.viewmodel.matakuliah.PenyediaMataKuliahViewModel
import com.umy.ucproomdatabase_0102.ui.viewmodel.matakuliah.UpdateMataKuliahViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun UpdateMataKuliahView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateMataKuliahViewModel = viewModel(factory = PenyediaMataKuliahViewModel.Factory),
    viewModelDsn: HomeDosenViewModel = viewModel(factory = PenyediaDosenViewModel.Factory),
) {
    val uiState = viewModel.updateUIState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val DsnList by viewModelDsn.homeUiState.collectAsState()

    LaunchedEffect(uiState.snackBarMessage) {
        println("LaunchedEffect triggered")
        uiState.snackBarMessage?.let { message ->
            println("Snackbar message received: $message")
            coroutineScope.launch {
                println("Launching coroutine for snacbar")
                snackbarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Long
                )
                viewModel.resetSnackBarMessage()
            }
        }
    }
    Scaffold(
        modifier= Modifier
            .fillMaxSize()
            .background(
                color = colorResource(
                    id = R.color.primary
                )
            )
            .padding(16.dp)
            .padding(top = 18.dp),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                judul = "Edit MataKuliah",
                showBackButton = true,
                onBack = onBack,
                modifier = modifier
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = colorResource(
                        id = R.color.primary
                    )
                )
                .padding(padding)
                .padding(16.dp)
        ) {
            InsertBodyMataKuliah(
                uiState = uiState,
                listDosen = DsnList,
                onValueChange = { updatedEvent ->
                    viewModel.updateState(updatedEvent)
                },
                onClick = {
                    coroutineScope.launch {
                        if (viewModel.validateFields()) {
                            viewModel.updateData()
                            delay(600)
                            withContext(Dispatchers.Main) {
                                onNavigate()
                            }
                        }
                    }
                },
            )
        }
    }
}