package br.com.pradoeduardoluiz.androidnavigationdemo.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.pradoeduardoluiz.androidnavigationdemo.R
import br.com.pradoeduardoluiz.androidnavigationdemo.common.binding.viewBinding
import br.com.pradoeduardoluiz.androidnavigationdemo.common.ext.collectNavigationResult
import br.com.pradoeduardoluiz.androidnavigationdemo.databinding.FragmentProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding by viewBinding(FragmentProfileBinding::bind)
    private val viewModel: ProfileViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initListeners()
    }

    private fun initObservers() {
        viewModel.text.observe(viewLifecycleOwner) { text ->
            binding.textProfile.text = text
        }

        collectNavigationResult<String>("DETAILS_RESULT") {
            Toast.makeText(requireContext(), "show result: $it", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initListeners() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        binding.details.setOnClickListener {
            viewModel.openDetails()
        }
    }
}
