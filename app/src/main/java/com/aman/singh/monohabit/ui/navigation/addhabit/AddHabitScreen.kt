package com.aman.singh.monohabit.ui.navigation.addhabit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.aman.singh.monohabit.data.HabitData
import com.aman.singh.monohabit.model.Habit
import com.aman.singh.monohabit.ui.theme.MonoHabitTheme
import com.aman.singh.monohabit.ui.util.IconProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddHabitScreen(onBack: () -> Unit) {
    var showAddDialog by remember { mutableStateOf(false) }
    var habitToEdit by remember { mutableStateOf<Habit?>(null) }
    var habitDataVersion by remember { mutableStateOf(0) }

    val habits = remember(habitDataVersion) { HabitData.getAllHabits() }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add a new habit")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(habits, key = { it.id }) { habit ->
                HabitTemplateCard(habit = habit, onEdit = { habitToEdit = habit })
            }
        }
    }

    val openDialog = showAddDialog || habitToEdit != null
    if (openDialog) {
        HabitEditDialog(
            habit = habitToEdit,
            onDismiss = {
                showAddDialog = false
                habitToEdit = null
            },
            onSave = {
                if (habitToEdit == null) {
                    HabitData.addHabit(it)
                } else {
                    HabitData.updateHabit(it)
                }
                habitDataVersion++
                showAddDialog = false
                habitToEdit = null
            },
            onDelete = {
                HabitData.deleteHabit(it)
                habitDataVersion++
                showAddDialog = false
                habitToEdit = null
            }
        )
    }
}

@Composable
fun HabitTemplateCard(habit: Habit, onEdit: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(habit.color.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(habit.icon, contentDescription = null, tint = habit.color)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(habit.title,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    maxLines = 1
                )
            }
            IconButton(onClick = onEdit) {
                Icon(Icons.Default.Edit,
                    contentDescription = "Edit Habit",
                    tint = Color.Gray
                )
            }
        }
    }
}

@Composable
fun HabitEditDialog(
    habit: Habit?,
    onDismiss: () -> Unit,
    onSave: (Habit) -> Unit,
    onDelete: (Habit) -> Unit
) {
    var habitName by remember { mutableStateOf(habit?.title ?: "") }
    var selectedIcon by remember { mutableStateOf(habit?.icon ?: IconProvider.icons.entries.first().value) }
    var selectedColor by remember { mutableStateOf(habit?.color ?: colors.first()) }
    var showIconPicker by remember { mutableStateOf(false) }
    var showDeleteConfirmation by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.padding(vertical = 32.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
            ) {
                Text(
                    text = if (habit == null) "Create a Habit" else "Edit Habit",
                    style = MaterialTheme.typography.headlineSmall, 
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    value = habitName,
                    onValueChange = { habitName = it },
                    label = { Text("Habit Name") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Icon & Color", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(selectedColor.copy(alpha = 0.1f))
                            .clickable { showIconPicker = true },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(selectedIcon, contentDescription = "Selected Icon", tint = selectedColor)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                ColorSelector(selectedColor = selectedColor, onColorSelected = { selectedColor = it })

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = if (habit != null) Arrangement.SpaceBetween else Arrangement.End
                ) {
                    if (habit != null) {
                        IconButton(onClick = { showDeleteConfirmation = true }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete habit", tint = MaterialTheme.colorScheme.error)
                        }
                    }
                    Row {
                        TextButton(onClick = onDismiss) {
                            Text("Cancel")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                val newOrUpdatedHabit = Habit(
                                    id = habit?.id ?: 0, // Keep original ID for updates
                                    title = habitName,
                                    streak = habit?.streak ?: 0,
                                    isCompleted = habit?.isCompleted ?: false,
                                    icon = selectedIcon,
                                    color = selectedColor,
                                    iconName = IconProvider.getIconName(selectedIcon)
                                )
                                onSave(newOrUpdatedHabit)
                            },
                            enabled = habitName.isNotBlank()
                        ) {
                            Text("Save")
                        }
                    }
                }
            }
        }
    }

    if (showIconPicker) {
        IconPickerDialog(
            onIconSelected = {
                selectedIcon = it
                showIconPicker = false
            },
            onDismiss = { showIconPicker = false }
        )
    }

    if (showDeleteConfirmation) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirmation = false },
            title = { Text("Delete Habit?") },
            text = { Text("Are you sure you want to delete this habit? This action cannot be undone.") },
            confirmButton = {
                Button(
                    onClick = { 
                        habit?.let(onDelete)
                        showDeleteConfirmation = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteConfirmation = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun ColorSelector(selectedColor: Color, onColorSelected: (Color) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 40.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(colors) { color ->
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(color)
                    .clickable { onColorSelected(color) }
            ) {
                if (color == selectedColor) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                            .background(Color.Black.copy(alpha = 0.3f))
                    )
                }
            }
        }
    }
}

val colors = listOf(
    Color(0xFF6750A4), Color(0xFF006D77), Color(0xFFB94053), Color(0xFF725B00),
    Color(0xFF4A6A86), Color(0xFF9B408B), Color(0xFFE91E63), Color(0xFFF44336),
    Color(0xFFFF9800), Color(0xFFFFEB3B), Color(0xFF4CAF50), Color(0xFF009688),
    Color(0xFF03A9F4), Color(0xFF3F51B5), Color(0xFF9C27B0), Color(0xFF795548)
)

@Composable
fun IconPickerDialog(onIconSelected: (ImageVector) -> Unit, onDismiss: () -> Unit) {
    var searchText by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Surface(shape = RoundedCornerShape(16.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Select an Icon", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    label = { Text("Search Icons") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 48.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val filteredIcons = IconProvider.icons.filter {
                        it.key.contains(searchText, ignoreCase = true)
                    }
                    items(filteredIcons.toList()) { (name, icon) ->
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .clickable { onIconSelected(icon) },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(icon, contentDescription = name)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HabitTemplateCardPreview() {
    MonoHabitTheme {
        HabitTemplateCard(
            habit = Habit(
                id = 1,
                title = "Read a book",
                streak = 5,
                isCompleted = true,
                icon = IconProvider.getIcon("MenuBook"),
                color = colors[0],
                iconName = "MenuBook"
            ),
            onEdit = {}
        )
    }
}