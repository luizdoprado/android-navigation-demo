package br.com.pradoeduardoluiz.androidnavigationdemo.login.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import br.com.pradoeduardoluiz.androidnavigationdemo.common.binding.viewBinding
import br.com.pradoeduardoluiz.androidnavigationdemo.login.R
import br.com.pradoeduardoluiz.androidnavigationdemo.login.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding by viewBinding(FragmentLoginBinding::bind)
    private val viewModel: LoginViewModel by viewModel()

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
