package br.com.pradoeduardoluiz.androidnavigationdemo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.fragment.compose.AndroidFragment
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.dashboard.DashboardFragment
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.home.HomeFragment
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.notifications.NotificationsFragment
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.profile.ProfileFragment

@Composable
fun HomeFragmentCompose(modifier: Modifier = Modifier) {
    AndroidFragment<HomeFragment>(modifier = Modifier.fillMaxSize())
}

@Composable
fun ProfileFragmentCompose(modifier: Modifier = Modifier) {
    AndroidFragment<ProfileFragment>(modifier = Modifier.fillMaxSize())
}

@Composable
fun DashboardFragmentCompose(modifier: Modifier = Modifier) {
    AndroidFragment<DashboardFragment>(modifier = Modifier.fillMaxSize())
}

@Composable
fun NotificationsFragmentCompose(modifier: Modifier = Modifier) {
    AndroidFragment<NotificationsFragment>(modifier = Modifier.fillMaxSize())
}