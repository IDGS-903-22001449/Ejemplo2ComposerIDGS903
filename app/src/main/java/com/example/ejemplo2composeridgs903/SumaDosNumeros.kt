package com.example.ejemplo2composeridgs903

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun SumaDosNumeros() {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf("Suma") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("Numero 1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Numero 2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Button(
            onClick = {
                val n1 = num1.toIntOrNull()
                val n2 = num2.toIntOrNull()
                resultado = when {
                    n1 == null || n2 == null -> "Ingrese numeros vslidos"
                    selectedOption == "Suma" -> "Resultado: ${n1 + n2}"
                    selectedOption == "Resta" -> "Resultado: ${n1 - n2}"
                    selectedOption == "Multiplicación" -> "Resultado: ${n1 * n2}"
                    selectedOption == "División" -> if (n2 != 0) "Resultado: ${n1 / n2}"
                    else "No se puede dividir entre 0"
                    else -> "Operacion no válida"
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Calcular")
        }
        Text(
            text = resultado,
            modifier = Modifier.padding(top = 8.dp)
        )
        RadioButtonSingleSelection(selectedOption = selectedOption, onOptionSelected = { selectedOption = it }, modifier = Modifier.padding(top = 16.dp))
    }
}

@Composable
fun RadioButtonSingleSelection(selectedOption: String, onOptionSelected: (String) -> Unit, modifier: Modifier = Modifier) {
    val radioOptions = listOf("Suma", "Resta", "Multiplicación", "División")

    Column(
        modifier = modifier.selectableGroup()
    ) {
        radioOptions.forEach { text ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) }
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}