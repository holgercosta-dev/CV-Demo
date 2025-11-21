@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.cv_demo.presentation.reactive_interactive

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cv_demo.presentation.core.state.UiState
import com.example.cv_demo.presentation.reactive_interactive.state.ColorOption
import com.example.cv_demo.presentation.reactive_interactive.state.FinishOption
import com.example.cv_demo.presentation.reactive_interactive.state.OnInteractionEvent
import com.example.cv_demo.presentation.reactive_interactive.state.ProductDetailsState
import com.example.cv_demo.presentation.reactive_interactive.state.ProductDetailsViewModel
import com.example.cv_demo.presentation.reactive_interactive.state.ProductUiDetails
import com.example.cv_demo.presentation.reactive_interactive.state.StorageOption
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

val darkBackgroundColor = Color(0xFF1E2429)
val textColor = Color.White
val selectedColor = Color(0xFF00A3FF)
@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    MaterialTheme(
        colorScheme = darkColorScheme(
            background = darkBackgroundColor,
            onBackground = textColor,
            surface = darkBackgroundColor,
            onSurface = textColor,
            primary = selectedColor,
            onPrimary = textColor
        )
    ) {
        Content(uiState, viewModel::onInteractionEvent)
    }
}

@Composable
private fun Content(
    uiState: ProductDetailsState,
    onInteractionEvent: (OnInteractionEvent) -> Unit = {},
) {
    Scaffold(
        topBar = { TopAppBarContent() },
        containerColor = darkBackgroundColor
    ) { paddingValues ->
        when (uiState.productDetails) {
            is UiState.Error -> {
                Text("Error: ${uiState.productDetails.message}")
            }
            UiState.Idle -> Unit
            UiState.Loading -> {
                CircularProgressIndicator()
            }
            is UiState.Success<ProductUiDetails> -> {
                val selectedVariant = uiState.productDetails.data.productVariants.find {
                    it.id == uiState.selectedProduct?.selectedVariantId
                }
                if (selectedVariant == null) {
                    Text("Error: Variant not found")
                    return@Scaffold
                }
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(horizontal = 32.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Left side: Customization options
                    Box(modifier = Modifier.weight(1f)) {
                        CustomizationOptions(
                            selectedColorOption = uiState.selectedProduct?.selectedColor ?: ColorOption.FF243452,
                            selectedFinishOption = uiState.selectedProduct?.selectedFinish ?: FinishOption.MATTE,
                            selectedStorageOption = uiState.selectedProduct?.selectedStorage ?: StorageOption.GB_128,
                            colorOptions = selectedVariant.colorOptions,
                            finishOptions = selectedVariant.finishOption,
                            storageOptions = selectedVariant.storageOptions,
                            onInteractionEvent = onInteractionEvent,
                        )
                    }

                    Spacer(modifier = Modifier.width(32.dp))

                    // Right side: Product image and summary
                    Box(modifier = Modifier.weight(1f)) {
                        ProductSummary()
                    }
                }
            }
        }
    }
}

@Composable
fun TopAppBarContent() {
    TopAppBar(
        title = { Text("Tech Customizer", fontWeight = FontWeight.Bold) },
        actions = {
            TextButton(onClick = {}) { Text("Phones", color = Color.White) }
            TextButton(onClick = {}) { Text("Laptops", color = Color.White) }
            TextButton(onClick = {}) { Text("Cars", color = Color.White) }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {}) {
                Text("Cart")
            }
            IconButton(onClick = {}) {
                Icon(Icons.Default.FavoriteBorder, contentDescription = "Favorite")
            }
            // Placeholder for profile image
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF1E2429),
            titleContentColor = Color.White
        )
    )
}

@Composable
fun CustomizationOptions(
    selectedColorOption: ColorOption,
    selectedFinishOption: FinishOption,
    selectedStorageOption: StorageOption,
    colorOptions: List<ColorOption>,
    finishOptions: List<FinishOption>,
    storageOptions: List<StorageOption>,
    onInteractionEvent: (OnInteractionEvent) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            Column {
                Text("Phones / Phone Model X", color = Color.Gray, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Text("Customize your Phone Model X", fontSize = 32.sp, fontWeight = FontWeight.Bold)
            }
        }

        item {
            OptionGroup(
                title = "Choose your finish",
                options = finishOptions,
                selectedOption = selectedFinishOption,
            ) { option, isSelected ->
                OutlinedButton(
                    onClick = { onInteractionEvent(OnInteractionEvent.ChooseFinish(option))},
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f) else Color.Transparent,
                        contentColor = Color.White
                    ),
                    border = BorderStroke(1.dp, if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray)
                ) {
                    Text(option.name)
                }
            }
        }

        item {
            OptionGroup(
                title = "Choose your color",
                options = colorOptions,
                selectedOption = selectedColorOption,
            ) { option, isSelected ->
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(option.getARGB()))
                        .border(
                            2.dp,
                            if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray,
                            CircleShape
                        )
                        .clickable { onInteractionEvent(OnInteractionEvent.ChooseColor(option)) }
                )
            }
        }

        item {
            OptionGroupVertical(
                title = "Choose your storage",
                options = storageOptions,
                selectedOption = selectedStorageOption,
                onOptionSelected = { onInteractionEvent(OnInteractionEvent.ChooseStorage(it)) }
            )
        }

//        item {
//            OptionGroupVertical(
//                title = "Choose your display",
//                options = listOf("Standard", "Pro"),
//                //Todo
////                descriptions = mapOf(
////                    "Standard" to "6.1-inch Super Retina XDR display",
////                    "Pro" to "6.7-inch Super Retina XDR display with ProMotion"
////                ),
//                selectedOption = ,
//                onOptionSelected = { selectedDisplay = it }
//            )
//        }
    }
}

@Composable
fun <T> OptionGroup(
    title: String,
    options: List<T>,
    selectedOption: T,
    content: @Composable (T, Boolean) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(title, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            options.forEach { option ->
                content(option, option == selectedOption)
            }
        }
    }
}

@Composable
fun <T> OptionGroupVertical(
    title: String,
    options: List<T>,
    selectedOption: T,
    onOptionSelected: (T) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(title, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        options.forEach { option ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                    .padding(16.dp)
                    .clickable { onOptionSelected(option) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = option == selectedOption,
                    onClick = { onOptionSelected(option) },
                    colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary)
                )
                Spacer(modifier = Modifier.width(16.dp))
//                if (descriptions != null) {
//                    Column {
//                        Text(option, fontWeight = FontWeight.SemiBold)
//                        Text(descriptions[option] ?: "", color = Color.Gray, fontSize = 14.sp)
//                    }
//                } else {
//                    Text(option)
//                }
            }
        }
    }
}


@Composable
fun ProductSummary() {
    Column {
        // Placeholder for product image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(Color(0xFFF0EAE6), RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("Product Image", color = Color.Black)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Phone Model X", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text("Black, 128GB, Standard", color = Color.Gray)

        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp), color = Color.Gray)

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Subtotal", color = Color.Gray)
            Text("$999")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Shipping", color = Color.Gray)
            Text("Free")
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp), color = Color.Gray)

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Total", fontWeight = FontWeight.Bold)
            Text("$999", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Add to Cart")
        }
    }
}

@Preview(widthDp = 1280, heightDp = 800)
@Composable
fun ProductDetailsScreenPreview() {
    ProductDetailsScreen()
}
