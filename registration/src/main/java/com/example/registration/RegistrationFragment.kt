package com.example.registration

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import br.com.pradoeduardoluiz.androidnavigationdemo.common.binding.viewBinding
import com.example.registration.databinding.FragmentRegistrationBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private val binding by viewBinding(FragmentRegistrationBinding::bind)
    private val viewModel: RegistrationViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initListeners()
    }

    private fun initObservers() {
        viewModel.text.observe(viewLifecycleOwner) { text ->
            binding.text.text = text
        }
    }

    private fun initListeners() {
        binding.toolbar.setNavigationOnClickListener {
            viewModel.pop()
        }
    }
}
