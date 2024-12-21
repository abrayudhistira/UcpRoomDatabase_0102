package com.umy.ucproomdatabase_0102.ui.view.matakuliah

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.umy.ucproomdatabase_0102.R
import com.umy.ucproomdatabase_0102.data.entity.Dosen
import com.umy.ucproomdatabase_0102.ui.view.widget.DynamicSelectedTextField
import com.umy.ucproomdatabase_0102.ui.viewmodel.matakuliah.FormErrorState
import com.umy.ucproomdatabase_0102.ui.viewmodel.matakuliah.MataKuliahEvent

@Composable
fun FormMataKuliah(
    matakuliahEvent: MataKuliahEvent = MataKuliahEvent(),
    onValueChange: (MataKuliahEvent) -> Unit = {},
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier,
    listDsn: List<Dosen>
) {
    val semester = listOf("Ganjil", "Genap")
    val jenis = listOf("Pengembangan Aplikasi Basis Data", "AI", "Switching","Multimedia")
    val namaDosenList = listDsn.map { it.nama }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colorResource(
                    id = R.color.primary
                )
            )
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.kd_mk,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(kd_mk = it))
            },
            label = { Text("Kode Mata Kuliah", color = Color.White) },
            isError = errorState.kd_mk != null,
            placeholder = { Text("Masukkan Kode Mata Kuliah", color = Color.White) },
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                cursorColor = Color.White
            )
        )
        Text(
            text = errorState.kd_mk ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.nama_mk,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(nama_mk = it))
            },
            label = { Text("Nama Mata Kuliah", color = Color.White) },
            isError = errorState.nama_mk != null,
            placeholder = { Text("Masukkan Nama Mata Kuliah", color = Color.White) },
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                cursorColor = Color.White
            )
        )
        Text(
            text = errorState.nama_mk ?: "",
            color = Color.Red
        )

        Text(text = "Semester", color = Color.White)
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            semester.forEach { semesterOption ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(
                        selected = matakuliahEvent.semester == semesterOption,
                        onClick = {
                            onValueChange(matakuliahEvent.copy(semester = semesterOption))
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.White,
                            unselectedColor = Color.White
                        )
                    )
                    Text(text = semesterOption, color = Color.White)
                }
            }
        }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.sks,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(sks = it))
            },
            label = { Text("SKS", color = Color.White) },
            isError = errorState.sks != null,
            placeholder = { Text("Masukkan SKS", color = Color.White) },
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                cursorColor = Color.White
            )
        )
        Text(
            text = errorState.sks ?: "",
            color = Color.Red
        )

        Text(text = "Jenis", color = Color.White)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = matakuliahEvent.jenis == jenis[0],
                        onClick = {
                            onValueChange(matakuliahEvent.copy(jenis = jenis[0]))
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.White,
                            unselectedColor = Color.White
                        )
                    )
                    Text(text = jenis[0], color = Color.White)
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = matakuliahEvent.jenis == jenis[1],
                        onClick = {
                            onValueChange(matakuliahEvent.copy(jenis = jenis[1]))
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.White,
                            unselectedColor = Color.White
                        )
                    )
                    Text(text = jenis[1], color = Color.White)
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = matakuliahEvent.jenis == jenis[2],
                        onClick = {
                            onValueChange(matakuliahEvent.copy(jenis = jenis[2]))
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.White,
                            unselectedColor = Color.White
                        )
                    )
                    Text(text = jenis[2], color = Color.White)
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = matakuliahEvent.jenis == jenis[3],
                        onClick = {
                            onValueChange(matakuliahEvent.copy(jenis = jenis[3]))
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.White,
                            unselectedColor = Color.White
                        )
                    )
                    Text(text = jenis[3], color = Color.White)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Dosen Pengampu", color = Color.White)
        DynamicSelectedTextField(
            selectedValue = matakuliahEvent.dosen_pengampu,
            options = namaDosenList,
            label = "Pilih Dosen Pengampu",
            onValueChangedEvent = {
                onValueChange(matakuliahEvent.copy(dosen_pengampu = it))
            },
        )
        Text(
            text = errorState.dosen_pengampu ?: "",
            color = Color.Red
        )
    }
}