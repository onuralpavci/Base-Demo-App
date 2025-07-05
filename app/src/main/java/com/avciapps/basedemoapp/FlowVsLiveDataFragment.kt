package com.avciapps.basedemoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.avciapps.basedemoapp.databinding.FragmentFlowVsLivedataBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Fragment demonstrating the differences between Flow and LiveData behavior
 * 
 * This demo shows:
 * 1. How LiveData survives configuration changes
 * 2. How basic Flow doesn't survive configuration changes
 * 3. How Flow with stateIn can behave like LiveData
 * 4. Continuous Flow streaming behavior
 */
class FlowVsLiveDataFragment : Fragment() {

    private var _binding: FragmentFlowVsLivedataBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: FlowVsLiveDataViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlowVsLivedataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupUI()
        observeData()
    }
    
    private fun setupUI() {
        // LiveData button
        binding.btnIncrementLiveData.setOnClickListener {
            viewModel.incrementLiveData()
        }
        
        // Basic Flow button
        binding.btnIncrementBasicFlow.setOnClickListener {
            viewModel.incrementBasicFlow()
        }
        
        // Flow with stateIn button
        binding.btnIncrementFlowWithStateIn.setOnClickListener {
            viewModel.incrementFlowWithStateIn()
        }
        
        // Reset button
        binding.btnReset.setOnClickListener {
            viewModel.resetCounters()
        }
        
        // Clear error button
        binding.btnClearError.setOnClickListener {
            viewModel.clearError()
        }
        
        // Simulate configuration change button
        binding.btnSimulateConfigChange.setOnClickListener {
            viewModel.simulateConfigurationChange()
        }
    }
    
    private fun observeData() {
        // Observe LiveData
        viewModel.liveDataCounter.observe(viewLifecycleOwner) { value ->
            binding.tvLiveDataValue.text = "LiveData: $value"
            binding.tvLiveDataDescription.text = "Survives configuration changes, not process death"
        }
        
        // Observe basic Flow
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.basicFlowCounter.collectLatest { value ->
                binding.tvBasicFlowValue.text = "Basic Flow: $value"
                binding.tvBasicFlowDescription.text = "Doesn't survive configuration changes"
            }
        }
        
        // Observe Flow with stateIn
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.flowWithStateIn.collectLatest { value ->
                binding.tvFlowWithStateInValue.text = "Flow with stateIn: $value"
                binding.tvFlowWithStateInDescription.text = "Survives configuration changes like LiveData"
            }
        }
        
        // Observe continuous Flow
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.continuousFlow.collectLatest { value ->
                binding.tvContinuousFlowValue.text = "Continuous Flow: $value"
                binding.tvContinuousFlowDescription.text = "Updates every 2 seconds (Flow streaming)"
            }
        }
        
        // Observe error state
        viewModel.errorState.observe(viewLifecycleOwner) { error ->
            if (error != null) {
                binding.tvErrorState.text = error
                binding.tvErrorState.visibility = View.VISIBLE
            } else {
                binding.tvErrorState.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 