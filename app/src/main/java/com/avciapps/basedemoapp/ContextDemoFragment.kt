package com.avciapps.basedemoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.avciapps.basedemoapp.databinding.FragmentContextDemoBinding
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Demo 3: Coroutine Context
 * 
 * This demo demonstrates coroutine context and metadata.
 * 
 * Key Concepts:
 * - A coroutine context is a collection of metadata about a coroutine
 * - Context includes Job, name, dispatcher, and other elements
 * - Context provides information to observers and dispatchers
 * - Context elements can be combined using the + operator
 * 
 * Based on the Medium article: "Seven demos to understand coroutines: scope, context and Jobs"
 */
class ContextDemoFragment : Fragment() {

    private var _binding: FragmentContextDemoBinding? = null
    private val binding get() = _binding!!

    private var customJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContextDemoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
    }

    /**
     * Sets up the buttons for demonstrating coroutine context.
     */
    private fun setupButtons() {
        binding.btnLaunchNamedCoroutine.setOnClickListener {
            launchNamedCoroutine()
        }

        binding.btnLaunchWithJob.setOnClickListener {
            launchWithCustomJob()
        }

        binding.btnShowContextInfo.setOnClickListener {
            showContextInformation()
        }
    }

    /**
     * Launches a coroutine with a specific name in its context.
     * 
     * This demonstrates the article's concept: you can attach metadata
     * to coroutines using context elements like CoroutineName.
     */
    private fun launchNamedCoroutine() {
        binding.tvContextOutput.text = "Launching named coroutine..."
        
        // Launch a coroutine with a specific name in its context
        // This demonstrates how context can carry metadata about the coroutine
        lifecycleScope.launch(CoroutineName("MyNamedCoroutine")) {
            val coroutineName = coroutineContext[CoroutineName]?.name
            binding.tvContextOutput.text = "Named coroutine launched: $coroutineName"
            
            delay(2000)
            binding.tvContextOutput.text = "Named coroutine completed: $coroutineName"
        }
    }

    /**
     * Launches a coroutine with a custom Job in its context.
     * 
     * This demonstrates the article's concept: you can specify a Job
     * in the context to control the coroutine's lifecycle.
     */
    private fun launchWithCustomJob() {
        binding.tvContextOutput.text = "Launching coroutine with custom job..."
        
        // Create a custom job for this coroutine
        customJob = Job()
        
        // Launch a coroutine with the custom job in its context
        lifecycleScope.launch(customJob!!) {
            binding.tvContextOutput.text = "Custom job coroutine started"
            
            delay(3000)
            binding.tvContextOutput.text = "Custom job coroutine completed"
        }
        
        // Note: We're responsible for managing this job's lifecycle
        // This is different from the automatic lifecycle management in Demo 1
    }

    /**
     * Shows information about the current coroutine context.
     * 
     * This demonstrates how context provides metadata about coroutines
     * and how different context elements can be examined.
     */
    private fun showContextInformation() {
        lifecycleScope.launch {
            val contextInfo = buildString {
                appendLine("Current Coroutine Context:")
                appendLine("• Job: ${coroutineContext[Job]}")
                appendLine("• Name: ${coroutineContext[CoroutineName]?.name ?: "Unnamed"}")
                appendLine("• Dispatcher: ${coroutineContext[kotlinx.coroutines.CoroutineDispatcher]}")
                appendLine("• Is Active: ${coroutineContext[Job]?.isActive}")
                appendLine("• Is Cancelled: ${coroutineContext[Job]?.isCancelled}")
            }
            
            binding.tvContextOutput.text = contextInfo
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clean up the custom job
        customJob?.cancel()
        customJob = null
        _binding = null
    }
} 