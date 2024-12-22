package com.umy.ucproomdatabase_0102.ui.view.matakuliah

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.umy.ucproomdatabase_0102.R
import com.umy.ucproomdatabase_0102.data.entity.MataKuliah
import com.umy.ucproomdatabase_0102.ui.customwidget.TopAppBar
import com.umy.ucproomdatabase_0102.ui.viewmodel.matakuliah.HomeMataKuliahUiState
import com.umy.ucproomdatabase_0102.ui.viewmodel.matakuliah.HomeMataKuliahViewModel
import com.umy.ucproomdatabase_0102.ui.viewmodel.matakuliah.PenyediaMataKuliahViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeMataKuliahView(
    viewModel: HomeMataKuliahViewModel = viewModel(factory = PenyediaMataKuliahViewModel.Factory),
    onAddMatakuliah: () -> Unit = {},
    onBack:()->Unit,
    onDetailClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier= Modifier
            .background(
                color = colorResource(
                    id = R.color.primary
                )
            )
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 18.dp),
        topBar = {
            TopAppBar(
                judul = "Daftar Matakuliah",
                showBackButton = true,
                onBack = onBack,
                modifier = modifier
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddMatakuliah,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Matakuliah",
                )
            }
        }
    ){ innerPadding ->
        val homeUiState by viewModel.homeMataKuliahUiState.collectAsState()

        BodyHomeMataKuliahView(
            homeMatakuliahUiState = homeUiState,
            onClick = {
                onDetailClick(it)
            },
            modifier = Modifier
                .background(
                    color = colorResource(
                        id = R.color.primary
                    )
                )
                .padding(innerPadding)
        )
    }
}

@Composable
fun BodyHomeMataKuliahView(
    homeMatakuliahUiState: HomeMataKuliahUiState,
    onClick: (String) -> Unit = { },
    modifier: Modifier = Modifier
) {

    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    when {
        homeMatakuliahUiState.isLoading -> {
            Box(
                modifier = modifier
                    .background(
                        color = colorResource(
                            id = R.color.primary
                        )
                    )
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        homeMatakuliahUiState.isError -> {
            LaunchedEffect(homeMatakuliahUiState.errorMessage) {
                homeMatakuliahUiState.errorMessage.let { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            }
        }

        homeMatakuliahUiState.listMataKuliah.isEmpty() -> {
            Box(
                modifier = Modifier
                    .background(
                        color = colorResource(
                            id = R.color.primary
                        )
                    )
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tidak ada Data",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.white),
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        else -> {
            ListMataKuliah(
                listMtk = homeMatakuliahUiState.listMataKuliah,
                onClick = {
                    onClick(it)
                    println(
                        it
                    )

                },
                modifier = modifier
            )
        }
    }
}

@Composable
fun ListMataKuliah(
    listMtk: List<MataKuliah>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = { }
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = colorResource(
                    id = R.color.primary
                )
            )
    ) {
        items(
            items = listMtk,
            itemContent = { mtk ->
                CardMataKuliah(
                    mtk = mtk,
                    onClick = { onClick(mtk.kd_mk) }
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardMataKuliah(
    mtk: MataKuliah,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white),
            contentColor = colorResource(id = R.color.primary)
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Info, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = mtk.kd_mk,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Filled.Person, contentDescription = "")
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = mtk.nama_mk,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.DateRange, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = mtk.semester,
                    fontWeight = FontWeight.Bold,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = mtk.sks,
                    fontWeight = FontWeight.Bold,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = mtk.jenis,
                    fontWeight = FontWeight.Bold,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.AccountBox, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = mtk.dosen_pengampu,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}