package com.alvarof18.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alvarof18.chat.model.MessageModel
import com.alvarof18.chat.ui.theme.ChatTheme
import com.alvarof18.chat.ui.theme.ChatViewModel
import kotlinx.coroutines.launch


@Composable
fun ChatApp(chatViewModel: ChatViewModel = viewModel()) {

    val chatUiState by chatViewModel.uiState.collectAsState()
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Box(contentAlignment = Alignment.BottomEnd) {
        LazyColumn(
            state = scrollState,
            contentPadding = PaddingValues(bottom = 60.dp, top = 30.dp)
        ) {
            coroutineScope.launch {
                scrollState.animateScrollToItem(chatUiState.size)
            }
            items(chatUiState) { message ->
                MessageBubble(message = message)
            }
        }
        SendMessage(chatViewModel = chatViewModel)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendMessage(modifier: Modifier = Modifier, chatViewModel: ChatViewModel) {

    Row(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),

        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextField(
            value = chatViewModel.newText,
            maxLines = 1,
            onValueChange = { chatViewModel.newText(it) },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(22.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(
                onDone = { chatViewModel.insertNewMessage() }
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            onClick = { chatViewModel.insertNewMessage() }, modifier = Modifier
                .clip(CircleShape)
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

            if (message.id != 1) {
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

            if (message.id == 1) {
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

