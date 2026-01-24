package com.aman.singh.monohabit.ui.navigation.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aman.singh.monohabit.data.HabitData
import com.aman.singh.monohabit.model.Habit
import com.aman.singh.monohabit.ui.theme.BackGroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(onAddHabit: () -> Unit) {

    val habits = HabitData.getAllHabits()

    val completedCount = habits.count { it.isCompleted }
    val totalCount = habits.size
    val bestStreak = habits.maxOfOrNull { it.streak } ?: 0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGroundColor)
    ) {
        DashboardHeader(
            completedCount = completedCount,
            totalCount = totalCount,
            bestStreak = bestStreak
        )

        if (habits.isEmpty()) {
            EmptyStateView(onAddHabit = onAddHabit)
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(habits) { habit ->
                    HabitCard(
                        habit = habit,
                        isEditable = true,
                        onCheckChanged = { /* Update logic */ }
                    )
                }
            }
        }
    }
}

@Composable
fun DashboardHeader(
    completedCount: Int,
    totalCount: Int,
    bestStreak: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
            .background(Color(0xFF2962FF))
            .padding(top = 48.dp, bottom = 24.dp)
    ) {
        PaddingBox {
            Column {
                Text(
                    text = "Today",
                    color = Color.White,
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = "My Habits",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        PaddingBox {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                StatCard(
                    modifier = Modifier.weight(1f),
                    value = "$completedCount/$totalCount",
                    label = "Completed"
                )
                StatCard(
                    modifier = Modifier.weight(1f),
                    value = "$bestStreak 🔥",
                    label = "Best Streak"
                )
            }
        }
    }
}

@Composable
fun PaddingBox(content: @Composable () -> Unit) {
    Box(modifier = Modifier.padding(horizontal = 24.dp)) {
        content()
    }
}


@Composable
fun StatCard(modifier: Modifier, value: String, label: String) {
    Column(
        modifier = modifier
            .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text(
            value,
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            label,
            color = Color.White.copy(alpha = 0.8f),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun EmptyStateView(onAddHabit: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(Icons.Default.DateRange, null, tint = Color.LightGray, modifier = Modifier.size(80.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "No habits yet. Add one!",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onAddHabit) {
            Text("Add a Habit")
        }
    }
}

@Composable
fun HabitCard(habit: Habit, isEditable: Boolean, onCheckChanged: (Boolean) -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
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
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    habit.title,
                    fontWeight = FontWeight.SemiBold,
                    color = if (habit.isCompleted) Color.Gray else Color.Black,
                    maxLines = 1
                )
                Text(
                    "🔥 ${habit.streak} days",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
            if (isEditable) {
                IconButton(
                    onClick = { onCheckChanged(!habit.isCompleted) },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(if (habit.isCompleted) Color(0xFF2962FF) else Color(0xFFE0E0E0))
                ) {
                    if (habit.isCompleted) Icon(Icons.Default.Check, null, tint = Color.White)
                }
            }
        }
    }
}

@Preview
@Composable
fun DashboardPreview() {
    MaterialTheme { DashboardScreen(onAddHabit = {}) }
}
