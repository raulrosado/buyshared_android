package com.example.buyshared.data.retrofitObjet

data class TaskCompletResponse(
    val estado: Int,
    val success: Boolean,
    val task: TaskX
)