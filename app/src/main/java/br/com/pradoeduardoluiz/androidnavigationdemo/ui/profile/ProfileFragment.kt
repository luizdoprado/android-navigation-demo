package br.com.pradoeduardoluiz.androidnavigationdemo.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import br.com.pradoeduardoluiz.androidnavigationdemo.MainActivity
import br.com.pradoeduardoluiz.androidnavigationdemo.R
import br.com.pradoeduardoluiz.androidnavigationdemo.common.binding.viewBinding
import br.com.pradoeduardoluiz.androidnavigationdemo.common.ext.collectNavigationResult
import br.com.pradoeduardoluiz.androidnavigationdemo.databinding.FragmentProfileBinding
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.base.NavigatorRoute
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding by viewBinding(FragmentProfileBinding::bind)
    private val viewModel: ProfileViewModel by viewModel()
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
            binding.textProfile.text = text
        }

        collectNavigationResult<String>("DETAILS_RESULT") {
            Toast.makeText(requireContext(), "show result: $it", Toast.LENGTH_SHORT).show()
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
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        binding.details.setOnClickListener {
            viewModel.openDetails()
        }
    }
}
