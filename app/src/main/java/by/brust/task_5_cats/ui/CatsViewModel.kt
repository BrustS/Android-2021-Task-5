package by.brust.task_5_cats.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import by.brust.task_5_cats.data.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatsViewModel @Inject constructor(repository: CatRepository) : ViewModel() {
    val allCatsImage = repository.getCatImagesFromNetwork().cachedIn(viewModelScope)
}
