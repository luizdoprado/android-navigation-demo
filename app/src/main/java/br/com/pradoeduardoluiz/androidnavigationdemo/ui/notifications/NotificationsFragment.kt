package br.com.pradoeduardoluiz.androidnavigationdemo.ui.notifications

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import br.com.pradoeduardoluiz.androidnavigationdemo.R
import br.com.pradoeduardoluiz.androidnavigationdemo.common.binding.viewBinding
import br.com.pradoeduardoluiz.androidnavigationdemo.databinding.FragmentNotificationsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotificationsFragment : Fragment(R.layout.fragment_notifications) {

    private val binding by viewBinding(FragmentNotificationsBinding::bind)
    private val viewModel: NotificationsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initListeners()
    }

    private fun initObservers() {
        viewModel.text.observe(viewLifecycleOwner) { text ->
            binding.textNotifications.text = text
        }
    }

    private fun initListeners() {
        binding.login.setOnClickListener {
            viewModel.login()
        }
    }
}
