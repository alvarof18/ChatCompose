package com.alvarof18.chat.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.alvarof18.chat.R
import com.alvarof18.chat.model.MessageModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ChatViewModel : ViewModel() {

    //UI State
    private val _uiState = MutableStateFlow<List<MessageModel>>(emptyList())
    val uiState: StateFlow<List<MessageModel>> = _uiState
    var newText by mutableStateOf("") // el private set es para que no me la modifiquen en la vista
        private set

    fun newText(message: String) {
        newText = message
    }

    fun insertNewMessage() {

        if (newText.isNotEmpty()) {
            _uiState.value += MessageModel(
                message = newText,
                id = 1,
                photoProfile = R.drawable.avatar_1,
                name = "Alvaro",
                timeMessage = "17:50"
            )
            newText = ""
        }
    }
}