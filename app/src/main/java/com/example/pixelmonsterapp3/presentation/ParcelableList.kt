package com.example.pixelmonsterapp3.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ParcelableList<T : Parcelable>(
    private val list: List<T>,
) : List<T> by list, Parcelable {

    fun toList() = list
}

fun <T : Parcelable> List<T>.toParcelableList() = ParcelableList(this)
