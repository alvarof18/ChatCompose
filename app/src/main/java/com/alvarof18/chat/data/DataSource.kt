package com.alvarof18.chat.data

import com.alvarof18.chat.R
import com.alvarof18.chat.model.MessageModel

class DataSource() {
     fun loadMessages(): List<MessageModel> {
            return listOf<MessageModel>(
                MessageModel(id = 1, message = "Hi, Cala", name = "Alvaro", photoProfile = R.drawable.avatar_2, timeMessage = "08:30" ),
                MessageModel(id = 2, message = "Guao Guao", name = "Cala", photoProfile = R.drawable.avatar_1, timeMessage = "08:31" ),
                MessageModel(id = 1, message = "How are you", name = "Alvaro", photoProfile = R.drawable.avatar_2, timeMessage = "08:31" ),
                MessageModel(id = 2, message = "Guaoooooooo", name = "Cala", photoProfile = R.drawable.avatar_1, timeMessage = "08:31" ),
                MessageModel(id = 1, message = "Nullam blandit magna non venenatis lacinia. Sed magna justo, ornare non tortor id, venenatis sollicitudin.", name = "Alvaro", photoProfile = R.drawable.avatar_2, timeMessage = "08:30" ),
                MessageModel(id = 2, message = "Guao Guao", name = "Cala", photoProfile = R.drawable.avatar_1, timeMessage = "08:31" ),
                MessageModel(id = 1, message = "Cras nec nisi ligula. Fusce ultrices condimentum neque id condimentum. Pellentesque euismod purus a justo ultrices vulputate. Nullam in iaculis dui, ac sollicitudin metus. Vestibulum vitae porta felis, nec suscipit augue. Integer suscipit sagittis commodo. Integer luctus ultrices tortor, in porta nisl. Lorem ipsum dolor sit amet, consectetur adipiscing elit.", name = "Alvaro", photoProfile = R.drawable.avatar_2, timeMessage = "08:31" ),
                MessageModel(id = 1, message = "Donec dictum lectus et ligula cursus porta. Vestibulum ante ipsum.", name = "Alvaro", photoProfile = R.drawable.avatar_2, timeMessage = "08:31" ),

            )
        }
    }

