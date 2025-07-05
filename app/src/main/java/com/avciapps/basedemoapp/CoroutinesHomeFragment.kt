package com.avciapps.basedemoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.avciapps.basedemoapp.databinding.FragmentCoroutinesHomeBinding

/**
 * Home fragment for the Coroutines Demo app.
 * 
 * This fragment provides navigation to all seven coroutine demos:
 * 1. Coroutine Scopes - Understanding scope lifecycle management
 * 2. Jobs & Cancellation - Managing individual coroutines
 * 3. Coroutine Context - Understanding coroutine metadata
 * 4. Structured Concurrency - Parent-child relationships
 * 5. Thread Dispatchers - Running on different thread pools
 * 6. Context Combinations - Combining context elements
 * 7. Scope Analysis - Understanding scope-context-job relationships
 */
class CoroutinesHomeFragment : Fragment() {

    private var _binding: FragmentCoroutinesHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoroutinesHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigation()
    }

    /**
     * Sets up click listeners for all demo cards to navigate to respective demos.
     */
    private fun setupNavigation() {
        binding.cardScopeDemo.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_scope_demo)
        }

        binding.cardJobDemo.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_job_demo)
        }

        binding.cardContextDemo.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_context_demo)
        }

        binding.cardStructuredDemo.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_structured_demo)
        }

        binding.cardDispatcherDemo.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_dispatcher_demo)
        }

        binding.cardCombinationDemo.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_combination_demo)
        }

        binding.cardScopeAnalysisDemo.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_scope_analysis_demo)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 