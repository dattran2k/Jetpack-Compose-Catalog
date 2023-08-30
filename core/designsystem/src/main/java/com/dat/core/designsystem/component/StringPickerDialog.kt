@file:OptIn(ExperimentalMaterial3Api::class)

package com.dat.core.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dat.core.designsystem.theme.JetpackComposeCatalogTheme

@ExperimentalMaterial3Api
@Composable
fun <T> StringPickerDialog(
    title: String,
    listSelect: List<T>,
    onDismiss: () -> Unit = {},
    mapName: (T) -> String = { it.toString() },
    onSelectItem: (T) -> Unit = {}
) {

    AlertDialog(onDismissRequest = onDismiss) {
        Card(
            //shape = MaterialTheme.shapes.medium,
            shape = RoundedCornerShape(10.dp),
            // modifier = modifier.size(280.dp, 240.dp)
            modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Divider(modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Column(
                modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .verticalScroll(rememberScrollState())
            ) {
                listSelect.forEachIndexed { index, data ->
                    Text(
                        text = mapName(data),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onSelectItem(data)
                                onDismiss()
                            }
                            .padding(8.dp),
                        style = MaterialTheme.typography.bodySmall,
                    )
                    if (index < listSelect.size - 1)
                        Divider(modifier = Modifier.padding(horizontal = 16.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun StringPickerDialogPreview() {
    JetpackComposeCatalogTheme {
        StringPickerDialog(
            "hello",
            listOf("JetpackComposeCatalogTheme ", "2 312312123 213", "3", "4"),
        )
    }
}