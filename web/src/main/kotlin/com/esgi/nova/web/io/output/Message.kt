package com.esgi.nova.web.io.output

data class Message(val message: String?) {
    companion object{
        fun created(): Message = Message("created")
        fun deleted(): Message = Message("deleted")
        fun updated(): Message = Message("updated")
    }
}