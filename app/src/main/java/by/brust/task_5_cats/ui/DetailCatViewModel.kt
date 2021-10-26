package by.brust.task_5_cats.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import by.brust.task_5_cats.data.Cat
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailCatViewModel @Inject constructor(private val state: SavedStateHandle) : ViewModel() {
    val image by lazy {
        state.get<Cat>("image")
    }
}
