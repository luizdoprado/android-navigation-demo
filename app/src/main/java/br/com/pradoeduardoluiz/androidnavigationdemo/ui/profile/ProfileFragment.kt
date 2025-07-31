package br.com.pradoeduardoluiz.androidnavigationdemo.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.pradoeduardoluiz.androidnavigationdemo.R
import br.com.pradoeduardoluiz.androidnavigationdemo.common.binding.viewBinding
import br.com.pradoeduardoluiz.androidnavigationdemo.common.ext.collectNavigationResult
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.Navigator
import br.com.pradoeduardoluiz.androidnavigationdemo.databinding.FragmentProfileBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding by viewBinding(FragmentProfileBinding::bind)
    private val viewModel: ProfileViewModel by viewModel()
    private val navigator: Navigator by inject()

    override fun onResume() {
        super.onResume()
        Log.d("ProfileFragment", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ProfileFragment", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ProfileFragment", "onStop called")
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

//        collectNavigationResult<String>("DETAILS_RESULT") {
//            Toast.makeText(requireContext(), "show result: $it", Toast.LENGTH_SHORT).show()
//        }
    }

    private fun initListeners() {
        binding.toolbar.setNavigationOnClickListener {
            navigator.pop()
        }
        binding.details.setOnClickListener {
            viewModel.openDetails()
        }
        binding.compose.setOnClickListener {
            viewModel.openCompose()
        }
        binding.secondCompose.setOnClickListener {
            viewModel.openSecondCompose()
        }
    }
}
