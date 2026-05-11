package br.com.pradoeduardoluiz.androidnavigationdemo.ui.deeplink

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.pradoeduardoluiz.androidnavigationdemo.R
import br.com.pradoeduardoluiz.androidnavigationdemo.common.binding.viewBinding
import br.com.pradoeduardoluiz.androidnavigationdemo.databinding.FragmentDeeplinkSimulatorBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeeplinkSimulatorFragment : Fragment(R.layout.fragment_deeplink_simulator) {

    private val binding by viewBinding(FragmentDeeplinkSimulatorBinding::bind)
    private val viewModel: DeeplinkSimulatorViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        binding.toolbar.setNavigationOnClickListener { viewModel.pop() }

        binding.btnResolveUrl.setOnClickListener {
            val url = binding.inputUrl.text?.toString().orEmpty()
            navigateOrShowError(url)
        }

        binding.btnHome.setOnClickListener {
            navigateOrShowError("https://example.com/home")
        }
        binding.btnDashboard.setOnClickListener {
            navigateOrShowError("https://example.com/dashboard")
        }
        binding.btnNotifications.setOnClickListener {
            navigateOrShowError("https://example.com/notifications")
        }
        binding.btnProfile.setOnClickListener {
            navigateOrShowError("https://example.com/profile")
        }
        binding.btnDetails.setOnClickListener {
            navigateOrShowError("https://example.com/details/product-42")
        }
        binding.btnUnknown.setOnClickListener {
            navigateOrShowError("https://example.com/unknown")
        }
    }

    private fun navigateOrShowError(url: String) {
        val resolved = viewModel.resolveAndNavigate(url)
        if (!resolved) {
            Toast.makeText(requireContext(), "No destination found for: $url", Toast.LENGTH_LONG).show()
        }
    }
}
