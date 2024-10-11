package br.com.pradoeduardoluiz.androidnavigationdemo.common.ext

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

inline fun <T> Fragment.collectNavigationResult(
    key: String,
    isSingleEvent: Boolean = true,
    crossinline result
    : (result: T) -> Unit,
) {
    val savedStateHandle = this.findNavController().currentBackStackEntry?.savedStateHandle
    savedStateHandle?.getLiveData<T>(key)?.observe(viewLifecycleOwner) {
        result(it)
        if (isSingleEvent) savedStateHandle.remove<T>(key)
    }
}