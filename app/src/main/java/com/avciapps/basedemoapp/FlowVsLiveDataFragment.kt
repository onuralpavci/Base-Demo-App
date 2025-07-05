package com.avciapps.basedemoapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.avciapps.basedemoapp.databinding.FragmentFlowVsLivedataBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Fragment demonstrating the differences between Flow and LiveData behavior
 *
 * This demo shows:
 * 1. How LiveData works as a single value holder
 * 2. How cold Flow only works when collected and stops when cancelled
 * 3. How hot Flow with Eagerly starts immediately and continues without collectors
 * 4. How hot Flow with WhileSubscribed starts when first collector arrives
 * 5. How StateFlow (hot Flow) behaves like LiveData
 */
class FlowVsLiveDataFragment : Fragment() {

    private var _binding: FragmentFlowVsLivedataBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FlowVsLiveDataViewModel by viewModels<FlowVsLiveDataViewModel>()

    // Track collection jobs to demonstrate behavior
    private var liveDataObserver: Observer<Int>? = null
    private var coldFlowJob: Job? = null
    private var stateFlowJob: Job? = null
    private var hotFlowEagerlyJob: Job? = null
    private var hotFlowWhileSubscribedJob: Job? = null

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
        // LiveData start/stop buttons
        binding.btnStartLiveData.setOnClickListener {
            startLiveDataCollection()
        }

        binding.btnStopLiveData.setOnClickListener {
            stopLiveDataCollection()
        }

        // Cold Flow start/stop buttons
        binding.btnStartColdFlow.setOnClickListener {
            startColdFlowCollection()
        }

        binding.btnStopColdFlow.setOnClickListener {
            stopColdFlowCollection()
        }

        // StateFlow start/stop buttons
        binding.btnStartStateFlow.setOnClickListener {
            startStateFlowCollection()
        }

        binding.btnStopStateFlow.setOnClickListener {
            stopStateFlowCollection()
        }

        // Hot Flow Eagerly start/stop buttons
        binding.btnStartHotFlowEagerly.setOnClickListener {
            startHotFlowEagerlyCollection()
        }

        binding.btnStopHotFlowEagerly.setOnClickListener {
            stopHotFlowEagerlyCollection()
        }

        // Hot Flow WhileSubscribed start/stop buttons
        binding.btnStartHotFlowWhileSubscribed.setOnClickListener {
            startHotFlowWhileSubscribedCollection()
        }

        binding.btnStopHotFlowWhileSubscribed.setOnClickListener {
            stopHotFlowWhileSubscribedCollection()
        }

        // Reset button
        binding.btnReset.setOnClickListener {
            viewModel.resetCounters()
        }

        // Clear status button
        binding.btnClearError.setOnClickListener {
            viewModel.clearStatus()
        }

        // Navigation button
        binding.btnNavigateToSecond.setOnClickListener {
            findNavController().navigate(R.id.action_FlowVsLiveDataFragment_to_SecondFragment)
        }
    }

    private fun observeData() {
        // Observe status messages
        viewModel.statusMessage.observe(viewLifecycleOwner) { message ->
            if (message != null) {
                binding.tvErrorState.text = message
                binding.tvErrorState.visibility = View.VISIBLE
            } else {
                binding.tvErrorState.visibility = View.GONE
            }
        }

        // Observe collector status
        viewModel.collectorStatus.observe(viewLifecycleOwner) { statusMap ->
            updateCollectorStatusUI(statusMap)
        }

        lifecycleScope.launch {
            async {
                lifecycle.currentStateFlow.collectLatest {
                    Log.d("Lifecycle Teesting", "Fragment lifectcle: " + it.name)
                }
            }
            async {
                viewLifecycleOwner.lifecycle.currentStateFlow.collectLatest {
                    Log.d("Lifecycle Teesting", "View lifectcle: " + it.name)
                }
            }
        }
    }

    private fun updateCollectorStatusUI(statusMap: Map<String, Boolean>) {
        binding.tvLiveDataStatus.text =
            "LiveData: ${if (statusMap["LiveData"] == true) "🟢 Active" else "🔴 Inactive"}"
        binding.tvStateFlowStatus.text =
            "StateFlow: ${if (statusMap["StateFlow"] == true) "🟢 Active" else "🔴 Inactive"}"
        binding.tvColdFlowStatus.text =
            "Cold Flow: ${if (statusMap["Cold Flow"] == true) "🟢 Active" else "🔴 Inactive"}"
        binding.tvHotFlowEagerlyStatus.text =
            "Hot Flow (Eagerly): ${if (statusMap["Hot Flow (Eagerly)"] == true) "🟢 Active" else "🔴 Inactive"}"
        binding.tvHotFlowWhileSubscribedStatus.text =
            "Hot Flow (WhileSubscribed): ${if (statusMap["Hot Flow (WhileSubscribed)"] == true) "🟢 Active" else "🔴 Inactive"}"
    }

    // ===== COLLECTION CONTROL METHODS =====

    private fun startLiveDataCollection() {
        if (liveDataObserver != null) return

        liveDataObserver = Observer<Int> { value ->
            binding.tvLiveDataValue.text = "LiveData: $value"
            binding.tvLiveDataDescription.text = "Single value holder, lifecycle-aware"
        }

        viewModel.liveDataCounter.observe(viewLifecycleOwner, liveDataObserver!!)
        viewModel.setCollectorStatus("LiveData", true)
    }

    private fun stopLiveDataCollection() {
        liveDataObserver?.let { observer ->
            viewModel.liveDataCounter.removeObserver(observer)
        }
        liveDataObserver = null
        viewModel.setCollectorStatus("LiveData", false)
        binding.tvLiveDataValue.text = "LiveData: Stopped"
        binding.tvLiveDataDescription.text = "No observer - LiveData continues internally"
    }

    private fun startColdFlowCollection() {
        if (coldFlowJob?.isActive == true) return

        coldFlowJob = viewLifecycleOwner.lifecycleScope.launch {
            viewModel.setCollectorStatus("Cold Flow", true)
            viewModel.getColdFlow().collectLatest { value ->
                binding.tvColdFlowValue.text = "Cold Flow: $value"
                binding.tvColdFlowDescription.text =
                    "Only works when collected, stops when cancelled"
            }
        }
    }

    private fun stopColdFlowCollection() {
        coldFlowJob?.cancel()
        coldFlowJob = null
        viewModel.setCollectorStatus("Cold Flow", false)
        binding.tvColdFlowValue.text = "Cold Flow: Stopped"
        binding.tvColdFlowDescription.text = "No collector - flow is inactive"
    }

    private fun startStateFlowCollection() {
        if (stateFlowJob?.isActive == true) return

        stateFlowJob = viewLifecycleOwner.lifecycleScope.launch {
            viewModel.setCollectorStatus("StateFlow", true)
            viewModel.basicStateFlowCounter.collectLatest { value ->
                binding.tvStateFlowValue.text = "StateFlow: $value"
                binding.tvStateFlowDescription.text = "Hot Flow, single value holder"
            }
        }
    }

    private fun stopStateFlowCollection() {
        stateFlowJob?.cancel()
        stateFlowJob = null
        viewModel.setCollectorStatus("StateFlow", false)
        binding.tvStateFlowValue.text = "StateFlow: Stopped"
        binding.tvStateFlowDescription.text = "No collector - but StateFlow continues internally"
    }

    private fun startHotFlowEagerlyCollection() {
        if (hotFlowEagerlyJob?.isActive == true) return

        hotFlowEagerlyJob = viewLifecycleOwner.lifecycleScope.launch {
            viewModel.setCollectorStatus("Hot Flow (Eagerly)", true)
            viewModel.getHotFlowEagerly().collectLatest { value ->
                binding.tvHotFlowEagerlyValue.text = "Hot Flow (Eagerly): $value"
                binding.tvHotFlowEagerlyDescription.text =
                    "Starts immediately, continues without collectors"
            }
        }
    }

    private fun stopHotFlowEagerlyCollection() {
        hotFlowEagerlyJob?.cancel()
        hotFlowEagerlyJob = null
        viewModel.setCollectorStatus("Hot Flow (Eagerly)", false)
        binding.tvHotFlowEagerlyValue.text = "Hot Flow (Eagerly): Stopped"
        binding.tvHotFlowEagerlyDescription.text =
            "No collector - but flow continues running (Eagerly)"
    }

    private fun startHotFlowWhileSubscribedCollection() {
        if (hotFlowWhileSubscribedJob?.isActive == true) return

        hotFlowWhileSubscribedJob = viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.setCollectorStatus("Hot Flow (WhileSubscribed)", true)
                viewModel.getHotFlowWhileSubscribed().collectLatest { value ->
                    binding.tvHotFlowWhileSubscribedValue.text =
                        "Hot Flow (WhileSubscribed): $value"
                    binding.tvHotFlowWhileSubscribedDescription.text =
                        "Starts when first collector arrives"
                }
            }
        }
    }

    private fun stopHotFlowWhileSubscribedCollection() {
        hotFlowWhileSubscribedJob?.cancel()
        hotFlowWhileSubscribedJob = null
        viewModel.setCollectorStatus("Hot Flow (WhileSubscribed)", false)
        binding.tvHotFlowWhileSubscribedValue.text = "Hot Flow (WhileSubscribed): Stopped"
        binding.tvHotFlowWhileSubscribedDescription.text =
            "No collector - flow stops (WhileSubscribed)"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 
