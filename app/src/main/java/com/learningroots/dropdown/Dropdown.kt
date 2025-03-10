package com.learningroots.dropdown

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

/**
 * Author: Santosh Yadav
 * Created on: 18-11-2024 23:58
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dropdown(innerPadding: PaddingValues) {
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedItem by remember {
        mutableStateOf(DropdownItem(Icons.Default.Home, "Home"))
    }
    val itemList = listOf(
        DropdownItem(Icons.Default.Home, "Home"),
        DropdownItem(Icons.Default.Person, "Person"),
        DropdownItem(Icons.Default.ShoppingCart, "Cart"),
        DropdownItem(Icons.Default.Settings, "Settings"),
        DropdownItem(Icons.Default.Call, "Calls"),
        DropdownItem(Icons.Default.Email, "Emails")
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ExposedDropdownMenuBox(
            modifier = Modifier
                .padding(innerPadding)
                .width(200.dp)
                .clip(RoundedCornerShape(10.dp)),
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    modifier = Modifier.menuAnchor(),
                    value = selectedItem.title,
                    onValueChange = { },
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = selectedItem.icon,
                            contentDescription = selectedItem.title
                        )
                    }
                )
            }

            ExposedDropdownMenu(expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                itemList.forEachIndexed { index, item ->
                    DropdownMenuItem(
                        text = {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.title
                                )
                                Text(item.title)
                            }
                        },
                        onClick = {
                            selectedItem = itemList[index]
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }

        Text(
            text = "${selectedItem.title} data displayed",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 200.dp)
        )

    }
}


