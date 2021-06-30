package com.stechoq.todo.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Todo(
    val id: Int? = null,
    val completed: Boolean? = null,
    val title: String? = null,
    val userId: Int? = null
) : Parcelable
