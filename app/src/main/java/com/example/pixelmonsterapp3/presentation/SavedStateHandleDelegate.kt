package com.example.pixelmonsterapp3.presentation

import androidx.compose.runtime.MutableState
import androidx.lifecycle.SavedStateHandle
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class SavedStateHandleDelegate<T>(
    private val savedStateHandle: SavedStateHandle,
    private val key: String,
    defaultValue: T,
    initializer: (initializedFromSavedState: Boolean, updateValue: (value: T) -> Unit) -> Unit,
) : ReadWriteProperty<Any, T> {
    private val state: MutableState<T>

    init {
        val savedValue = savedStateHandle.get<T>(key)
        state = androidx.compose.runtime.mutableStateOf(
            savedValue ?: defaultValue
        )
        initializer(savedValue != null, ::updateValue)
    }

    override fun getValue(thisRef: Any, property: KProperty<*>) = state.value

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        updateValue(value)
    }

    private fun updateValue(value: T) {
        state.value = value
        savedStateHandle.set(key, value)
    }
}

fun <T> SavedStateHandle.mutableStateOf(
    defaultValue: T,
    initializer: (initializedFromSavedState: Boolean, setter: (T) -> Unit) -> Unit = { _, _ -> },
) = PropertyDelegateProvider<Any, SavedStateHandleDelegate<T>> { _, property ->
    SavedStateHandleDelegate(
        savedStateHandle = this,
        key = property.name,
        defaultValue = defaultValue,
        initializer = initializer
    )
}
