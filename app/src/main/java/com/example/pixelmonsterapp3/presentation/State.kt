package com.example.pixelmonsterapp3.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class State<StateData> : Parcelable {

    @Parcelize
    data class Success<StateData : Parcelable>(
        val value: StateData,
    ) : State<StateData>()

    @Parcelize
    class Empty<StateData> : State<StateData>()
}

