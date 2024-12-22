package com.umy.ucproomdatabase_0102.ui.view.matakuliah

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.umy.ucproomdatabase_0102.R
import com.umy.ucproomdatabase_0102.data.entity.MataKuliah
import com.umy.ucproomdatabase_0102.ui.customwidget.TopAppBar
import com.umy.ucproomdatabase_0102.ui.viewmodel.matakuliah.DetailMataKuliahUiState
import com.umy.ucproomdatabase_0102.ui.viewmodel.matakuliah.DetailMataKuliahViewModel
import com.umy.ucproomdatabase_0102.ui.viewmodel.matakuliah.PenyediaMataKuliahViewModel
import com.umy.ucproomdatabase_0102.ui.viewmodel.matakuliah.toMatakuliahEntity


@Composable
fun DetailMataKuliahView(
    modifier: Modifier = Modifier,
    viewModel: DetailMataKuliahViewModel = viewModel(factory = PenyediaMataKuliahViewModel.Factory),
    onBack: () -> Unit = {},
    onEditClick: (String) -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {
    Scaffold (
        modifier= Modifier
            .fillMaxSize()
            .background(
                color = colorResource(
                    id = R.color.primary
                )
            )
            .padding(16.dp)
            .padding(top = 18.dp),
        topBar = {
            TopAppBar (
                judul = "Detail MataKuliah",
                showBackButton = true,
                onBack = onBack,
                modifier = modifier
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEditClick(viewModel.detailMataKuliahUiState.value.detailMatakuliahUiEvent.kd_mk)
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .background(
                        color = colorResource(
                            id = R.color.primary
                        )
                    )
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit MataKuliah",
                )
            }
        }
    ) { innerPadding ->
        val detailUiState by viewModel.detailMataKuliahUiState.collectAsState()

        BodyDetailMataKuliah(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = colorResource(
                        id = R.color.primary
                    )
                )
                .padding(innerPadding),
            detailMatakuliahUiState = detailUiState,
            onDeleteClick = {
                viewModel.deleteMataKuliah()
                onDeleteClick()
            }
        )
    }
}

@Composable
fun BodyDetailMataKuliah(
    modifier: Modifier = Modifier,
    detailMatakuliahUiState: DetailMataKuliahUiState = DetailMataKuliahUiState(),
    onDeleteClick: () -> Unit = {}
) {
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }

    when {
        detailMatakuliahUiState.isLoading -> {
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

        detailMatakuliahUiState.isUiEventNotEmpty -> {
            Column(
                modifier = modifier
                    .background(
                        color = colorResource(
                            id = R.color.primary
                        )
                    )
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemDetailMataKuliah(
                    matakuliah = detailMatakuliahUiState.detailMatakuliahUiEvent.toMatakuliahEntity(),
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    onClick = { deleteConfirmationRequired = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.white),
                        contentColor = Color.Black
                    )
                ) {
                    Text(text = "Delete")
                }

                if (deleteConfirmationRequired) {
                    DeleteConfirmationDialog(
                        onDeleteConfirm = {
                            deleteConfirmationRequired = false
                            onDeleteClick()
                        },
                        onDeleteCancel = { deleteConfirmationRequired = false },
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }

        detailMatakuliahUiState.isUiEventEmpty -> {
            Box(
                modifier = modifier
                    .background(
                        color = colorResource(
                            id = R.color.primary
                        )
                    )
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Data tidak ditemukan",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun ItemDetailMataKuliah(
    modifier: Modifier = Modifier,
    matakuliah: MataKuliah
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white),
            contentColor = colorResource(id = R.color.primary))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            ComponentDetailMataKuliah(judul = "Kode", isinya = matakuliah.kd_mk)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailMataKuliah(judul = "Nama", isinya = matakuliah.nama_mk)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailMataKuliah(judul = "Semester", isinya = matakuliah.semester)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailMataKuliah(judul = "SKS", isinya = matakuliah.sks)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailMataKuliah(judul = "Jenis", isinya = matakuliah.jenis)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailMataKuliah(judul = "Dosen Pengampu", isinya = matakuliah.dosen_pengampu)
        }
    }
}

@Composable
fun ComponentDetailMataKuliah(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { },
        title = { Text("Delete Data") },
        text = { Text("Apakah anda yakin ingin menghapus data?") },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Yes")
            }
        }
    )
}