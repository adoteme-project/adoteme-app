package com.example.adoteme_app.navigation.presentation.navi_drawer

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adoteme_app.navigation.presentation.utils.NavDrawerItem

@Composable
fun DrawerItem(item: NavDrawerItem, onItemClick: (NavDrawerItem) -> Unit) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = { onItemClick(item) }
            )
            .height(45.dp)
            .padding(start = 12.dp)
    ) {
        Spacer(modifier = Modifier.width(7.dp))
        Text(
            text = item.title,
            fontSize = 18.sp,
        )
    }
}