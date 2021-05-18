package com.example.damncalendar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.damncalendar.ui.theme.BackgroundColor
import com.example.damncalendar.ui.theme.DamnCalendarTheme

private val mockTasks = mutableListOf<Task>(
    Task("Task 1"),
    Task("Task 2"),
    Task("Task 3"),
    Task("Task 4")
)

data class Task(
    var text: String,
    var state: TaskState = TaskState.Normal
) {
    fun toNextState() {
        when (this.state) {
            TaskState.Normal -> this.state = TaskState.Done
            TaskState.Done -> this.state = TaskState.Fail
            TaskState.Fail -> this.state = TaskState.Normal
        }
    }
}

enum class TaskState(val color: Color) {
    Normal(Color.Yellow), Done(Color.Green), Fail(Color.Red)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalendarScreen()
        }
    }
}

@Composable
fun CalendarScreen() {
    Scaffold(
        backgroundColor = BackgroundColor,
        content = {
            LazyColumn {
                item {
                    Text(
                        text = "Календарь",
                        fontSize = 48.sp,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 21.dp, end = 21.dp, top = 55.dp)
                    )
                }
                mockTasks.forEach {
                    item {
                        CalendarTask(task = it)
                    }
                }
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Text(
                    text = "X"
                )
            }
        }
    )
}

@Composable
fun CalendarTask(task: Task) {
    TextButton(onClick = {
        task.toNextState()
    }) {
        Text(
            text = task.text,
            fontSize = 24.sp,
            color = task.state.color,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 21.dp, end = 21.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DamnCalendarTheme {
        CalendarScreen()
    }
}