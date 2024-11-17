package com.example.siamo.ui.tecnico

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.siamo.R
import com.example.siamo.ui.utils.NavigationBarTecnico
import com.example.siamo.ui.utils.TopBar
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.siamo.ui.theme.SIAMOTheme
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ListItem
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.text.input.ImeAction



@Composable
fun IdentificacionProblemas(
    viewModel: ProblemasViewModel = viewModel()
) {
    val listaSeleccionada = viewModel.listaSeleccionada
    val filteredProblemas = viewModel.filteredProblemas
    val searchQuery = viewModel.searchQuery

    Scaffold(
        topBar = {
            TopBar(
                tituloPagina = stringResource(R.string.topbar_opcion10),
                modo = "Retroceder"
            )
        },
        bottomBar = { NavigationBarTecnico(opcionSeleccionada = 2) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = stringResource(R.string.ispeccion_identificar_problema),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )

            Box {
                // Barra de búsqueda
                SearchBar(
                    placeholderText = "Ingrese el problema aquí",
                    onSearch = { query ->
                        viewModel.updateSearchQuery(query)
                    },
                    onEnterPressed = {
                        viewModel.onEnterPressed()
                    }
                )

                if (filteredProblemas.isNotEmpty()) {
                    DropdownMenu(
                        expanded = searchQuery.isNotEmpty(),
                        onDismissRequest = { /* No cerrar para mantener la experiencia */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surface)
                    ) {
                        filteredProblemas.forEach { problema ->
                            DropdownMenuItem(
                                onClick = {
                                    viewModel.seleccionarProblema(problema)
                                },
                                text = { Text(problema.descripcion) }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Lista seleccionada de problemas
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                items(listaSeleccionada) { problema ->
                    ListItemWithCheckbox(
                        text = problema.descripcion,
                        isChecked = true,
                        onCheckedChange = { checked ->
                            if (!checked) {
                                viewModel.deseleccionarProblema(problema)
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.weight(0.1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.End // Alinea el botón a la derecha
            ) {
                Button(
                    onClick = { /* Acción al presionar el botón */ },
                    modifier = Modifier.wrapContentWidth(Alignment.End),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Siguiente")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Siguiente")
                }
            }
        }
    }
}

// Componente para el List Item con Checkbox
@Composable
fun ListItemWithCheckbox(
    text: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    ListItem(
        headlineContent = {
            Text(text = text)
        },
        trailingContent = {
            Checkbox(
                checked = isChecked,
                onCheckedChange = onCheckedChange,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    )
}


// Composable para la barra de búsqueda
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    placeholderText: String = "Buscar...",
    onSearch: (String) -> Unit = {},
    onEnterPressed: () -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }

    TextField(
        value = searchQuery,
        onValueChange = { query ->
            searchQuery = query
            onSearch(query)
        },
        placeholder = {
            Text(
                text = placeholderText,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Flecha hacia abajo",
                tint = MaterialTheme.colorScheme.primary
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Ícono de búsqueda",
                tint = MaterialTheme.colorScheme.primary
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done // Configura la acción IME para 'Done' o 'Enter'
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onEnterPressed() // Llama a la función al presionar Enter
            }
        ),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.outlineVariant
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
}


@Preview(showBackground = true, showSystemUi = false)
@Composable
fun IdentificacionProblemasLightPreview() {
    SIAMOTheme (darkTheme = false) { IdentificacionProblemas() }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun IdentificacionProblemasPreview() {
    SIAMOTheme (darkTheme = true) { IdentificacionProblemas() }
}
