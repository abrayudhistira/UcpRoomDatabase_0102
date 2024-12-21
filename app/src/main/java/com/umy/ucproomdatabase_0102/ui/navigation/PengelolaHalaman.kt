package com.umy.ucproomdatabase_0102.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.umy.ucproomdatabase_0102.ui.view.dashboard.DashboardView
import com.umy.ucproomdatabase_0102.ui.view.dosen.DestinasiDosenInsert
import com.umy.ucproomdatabase_0102.ui.view.dosen.HomeDosenView
import com.umy.ucproomdatabase_0102.ui.view.dosen.InsertDosenView
import com.umy.ucproomdatabase_0102.ui.view.matakuliah.DestinasiMataKuliahInsert
import com.umy.ucproomdatabase_0102.ui.view.matakuliah.DetailMataKuliahView
import com.umy.ucproomdatabase_0102.ui.view.matakuliah.HomeMataKuliahView
import com.umy.ucproomdatabase_0102.ui.view.matakuliah.InsertMataKuliahView
import com.umy.ucproomdatabase_0102.ui.view.matakuliah.UpdateMataKuliahView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHalamanUtama.route
    ) {
        composable(route = DestinasiHalamanUtama.route) {
            DashboardView(
                onDosenClick = {
                    navController.navigate(DestinasiDosen.route)
                },
                onMataKuliahClick = {
                    navController.navigate(DestinasiMataKuliah.route)
                },
                modifier = modifier
            )
        }

        composable(route = DestinasiDosen.route) {
            HomeDosenView(
                onBack = { navController.popBackStack() },
                onAddDosen = {
                    navController.navigate(DestinasiDosenInsert.route)
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiDosenInsert.route
        ) {
            InsertDosenView(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiMataKuliah.route
        ) {
            HomeMataKuliahView(
                onDetailClick = { kd_mk ->
                    navController.navigate("${DestinasiMataKuliahDetail.route}/$kd_mk")
                    println("PengelolaHalaman = $kd_mk")
                },
                onBack = { navController.popBackStack() },
                onAddMatakuliah = {
                    navController.navigate(DestinasiMataKuliahInsert.route)
                },
                modifier = modifier
            )
        }

        composable(route = DestinasiMataKuliahInsert.route) {
            InsertMataKuliahView(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() },
                modifier = modifier
            )
        }

        composable(
            DestinasiMataKuliahDetail.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiMataKuliahDetail.KD_MK) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val kd_mk = backStackEntry.arguments?.getString(DestinasiMataKuliahDetail.KD_MK)
            kd_mk?.let { kd_mk ->
                DetailMataKuliahView(
                    onBack = { navController.popBackStack() },
                    onEditClick = {
                        navController.navigate("${DestinasiMataKuliahUpdate.route}/$it")
                    },
                    onDeleteClick = { navController.popBackStack() },
                    modifier = modifier
                )
            }
        }

        composable(
            DestinasiMataKuliahUpdate.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiMataKuliahUpdate.KD_MK) { type = NavType.StringType }
            )
        ) {
            UpdateMataKuliahView(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() },
                modifier = modifier
            )
        }
    }
}