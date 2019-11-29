package com.thefuntasty.mvvm.crinteractors

import androidx.lifecycle.viewModelScope
import com.thefuntasty.mvvm.BaseViewModel
import com.thefuntasty.mvvm.ViewState
import kotlinx.coroutines.CoroutineScope

/**
 * Base ViewModel class prepared for providing data to UI through [LiveData] and
 * obtaining data from Stores (Repositories) by executing Coroutine based interactors like
 * [BaseCoroutiner] and [BaseFlower].
 */
abstract class BaseCrViewModel<S : ViewState> : BaseViewModel<S>(), CoroutineScopeOwner {

    override val coroutineScope: CoroutineScope = viewModelScope
}
