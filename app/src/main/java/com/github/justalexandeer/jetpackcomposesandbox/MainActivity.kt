package com.github.justalexandeer.jetpackcomposesandbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.justalexandeer.jetpackcomposesandbox.ui.theme.JetpackComposeSandboxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeSandboxTheme {
                Conversation(
                    mutableListOf<Message>(
                        Message("Вася пупки", "ПРивет увидимся с тобой?"),
                        Message(
                            "Вася пупки", "тесттесттесттесттест \n" +
                                    "тесттесттесттесттест" +
                                    "тесттесттесттесттесттесттесттесттесттест"
                        ),
                        Message("Вася", "534523452435345"),
                        Message("пупки", "вфапывари ываап ыв п"),
                        Message(
                            "Ваки",
                            "пвыап       тесттесттесттесттест тесттесттесттесттест     фыав"
                        ),

                        )
                )
            }
        }
    }

    @Composable
    fun Conversation(messages: List<Message>) {
        LazyColumn {
            items(messages) { message ->
                MessageCard(message)
            }
        }
    }

    @Composable
    fun MessageCard(msg: Message) {
        JetpackComposeSandboxTheme {
            Row(modifier = Modifier.padding(all = 4.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "test",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .border(4.dp, MaterialTheme.colors.secondary, CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                var isExpanded by remember { mutableStateOf(false) }
                Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                    Text(
                        text = msg.author,
                        color = MaterialTheme.colors.secondaryVariant,
                        style = MaterialTheme.typography.subtitle2
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Surface(shape = MaterialTheme.shapes.medium, elevation = 4.dp) {
                        Text(
                            text = msg.body,
                            modifier = Modifier.padding(all = 4.dp),
                            maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                            style = MaterialTheme.typography.body2
                        )
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    fun PreviewMessageCard() {
        MessageCard(
            Message("test", "test2")
        )
    }
}

data class Message(val author: String, val body: String)
