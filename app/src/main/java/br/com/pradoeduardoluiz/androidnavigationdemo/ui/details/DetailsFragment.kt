package br.com.pradoeduardoluiz.androidnavigationdemo.ui.details

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import br.com.pradoeduardoluiz.androidnavigationdemo.MainActivity
import br.com.pradoeduardoluiz.androidnavigationdemo.R
import br.com.pradoeduardoluiz.androidnavigationdemo.common.binding.viewBinding
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.NavigationState
import br.com.pradoeduardoluiz.androidnavigationdemo.databinding.FragmentDetailsBinding
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.base.NavigatorRoute
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val binding by viewBinding(FragmentDetailsBinding::bind)
    private val viewModel: DetailsViewModel by viewModel()
    private val args by navArgs<DetailsFragmentArgs>()

    private lateinit var navigator: NavigatorRoute

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigator = NavigatorRoute((requireActivity() as MainActivity).navigator)
    }

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
            navigator.navigateTo(NavigationState.Login())
        }
        binding.backWithArguments.setOnClickListener {
            viewModel.backWithArguments()
        }
        binding.login.setOnClickListener {
            viewModel.login()
        }
    }
}
