package com.alvarof18.chat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alvarof18.chat.data.DataSource
import com.alvarof18.chat.ui.theme.ChatTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.alvarof18.chat.model.MessageModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ChatApp()
                }
            }
        }
    }
}


@Composable
fun ChatApp() {
    val listMessages = DataSource().loadMessages()

    Box(contentAlignment = Alignment.BottomEnd) {
        LazyColumn(contentPadding = PaddingValues(bottom = 60.dp)) {
            items(listMessages) { message ->
                MessageBubble(message = message)
            }
        }
        SendMessage()
        Row {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendMessage(modifier: Modifier = Modifier) {

    var text by remember {
        mutableStateOf("")
    }
    Row(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),

        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextField(
            value = text,
            maxLines = 1,
            onValueChange = { newText -> text = newText },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(22.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            onClick = { text = "" }, modifier = Modifier
                .clip(
                    CircleShape
                )
                .background(color = Color(0xFFDAEFFE))
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = null,
                tint = Color(0xFF8ACD85)
            )
        }
    }

}

@Composable
fun MessageBubble(modifier: Modifier = Modifier, message: MessageModel) {

    Box(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.padding(16.dp),
        ) {

            if (message.id != 1){
                Image(
                    painter = painterResource(id = message.photoProfile),
                    contentDescription = "",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.size(8.dp))
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFEDEDED))
                    .padding(8.dp)

            ) {
                Text(text = message.name, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = message.message,
                    color = Color(0xff20303C)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = message.timeMessage,
                    modifier = Modifier.align(Alignment.End),
                    color = Color(0xFF99AFBF)
                )
            }

            if (message.id == 1){
                Spacer(modifier = Modifier.size(8.dp))
                Image(
                    painter = painterResource(id = message.photoProfile),
                    contentDescription = "",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChatTheme {
           ChatApp()
/*        MessageBubble(
            message = MessageModel(
                id = 1,
                message = "How are you",
                name = "Alvaro",
                photoProfile = R.drawable.avatar_2,
                timeMessage = "08:31"
            ),
        )*/
    }
}

