package br.com.pradoeduardoluiz.androidnavigationdemo.ui.notifications

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import br.com.pradoeduardoluiz.androidnavigationdemo.MainActivity
import br.com.pradoeduardoluiz.androidnavigationdemo.R
import br.com.pradoeduardoluiz.androidnavigationdemo.common.binding.viewBinding
import br.com.pradoeduardoluiz.androidnavigationdemo.databinding.FragmentNotificationsBinding
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.base.NavigatorRoute
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotificationsFragment : Fragment(R.layout.fragment_notifications) {

    private val binding by viewBinding(FragmentNotificationsBinding::bind)
    private val viewModel: NotificationsViewModel by viewModel()
    private lateinit var navigator: NavigatorRoute

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigator = NavigatorRoute((requireActivity() as MainActivity).navigator)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initListeners()
    }

    private fun initObservers() {
        viewModel.text.observe(viewLifecycleOwner) { text ->
            binding.textNotifications.text = text
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.navigationState.collect { state ->
                    navigator.navigateTo(state)
                    viewModel.resetNavigation()
                }
            }
        }
    }

    private fun initListeners() {
        binding.login.setOnClickListener {
            viewModel.login()
        }
    }
}
