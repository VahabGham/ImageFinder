package com.vahab.common.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.vahab.common.ui.theme.ImageFinderTheme

@Composable
fun EmptyScreen(
    title: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            style = MaterialTheme.typography.h1,
            textAlign = TextAlign.Center
        )
    }


}

@Preview
@Composable
fun Preview() {
    ImageFinderTheme {
        EmptyScreen(title = "There is no data at the momentThere is no data at the momentThere is no data at the moment")
    }
}