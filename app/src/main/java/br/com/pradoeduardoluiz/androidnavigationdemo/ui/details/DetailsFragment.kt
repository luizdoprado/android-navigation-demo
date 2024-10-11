package br.com.pradoeduardoluiz.androidnavigationdemo.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.pradoeduardoluiz.androidnavigationdemo.R
import br.com.pradoeduardoluiz.androidnavigationdemo.common.binding.viewBinding
import br.com.pradoeduardoluiz.androidnavigationdemo.databinding.FragmentDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val binding by viewBinding(FragmentDetailsBinding::bind)
    private val viewModel: DetailsViewModel by viewModel()
    private val args by navArgs<DetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.details.text = args.details
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
        binding.backWithArguments.setOnClickListener {
            viewModel.backWithArguments()
        }
        binding.login.setOnClickListener {
            viewModel.login()
        }
    }
}
