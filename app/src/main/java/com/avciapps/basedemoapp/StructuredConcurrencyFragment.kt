package com.avciapps.basedemoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.avciapps.basedemoapp.databinding.FragmentStructuredConcurrencyBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Demo 4: Structured Concurrency
 * 
 * This demo demonstrates parent-child relationships between coroutines.
 * 
 * Key Concepts:
 * - A Job can have children
 * - When a parent Job is cancelled, all its children are cancelled too
 * - This is called structured concurrency
 * - No manual tracking of child coroutines is needed
 * 
 * Based on the Medium article: "Seven demos to understand coroutines: scope, context and Jobs"
 */
class StructuredConcurrencyFragment : Fragment() {

    private var _binding: FragmentStructuredConcurrencyBinding? = null
    private val binding get() = _binding!!

    private var parentJob: Job? = null
    private var counter = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStructuredConcurrencyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
    }

    /**
     * Sets up the buttons for demonstrating structured concurrency.
     */
    private fun setupButtons() {
        binding.btnStartParentChild.setOnClickListener {
            startParentChildCoroutines()
        }

        binding.btnCancelParent.setOnClickListener {
            cancelParentCoroutine()
        }
    }

    /**
     * Starts a parent coroutine that launches a child coroutine.
     * 
     * This demonstrates the article's concept: when you launch a coroutine
     * inside another coroutine, it becomes a child of the parent. When the
     * parent is cancelled, the child is automatically cancelled too.
     */
    private fun startParentChildCoroutines() {
        binding.tvParentStatus.text = "Parent Status: Starting..."
        binding.tvChildStatus.text = "Child Status: Not started"
        counter = 0
        binding.tvCounter.text = "Counter: $counter"
        
        // Start the parent coroutine
        parentJob = lifecycleScope.launch {
            binding.tvParentStatus.text = "Parent Status: Running"
            
            // Launch a child coroutine inside the parent
            // This child coroutine will be automatically cancelled when the parent is cancelled
            launch {
                binding.tvChildStatus.text = "Child Status: Running"
                
                while (true) {
                    counter++
                    binding.tvCounter.text = "Counter: $counter"
                    delay(500) // Increment every 500ms
                }
            }
            
            // Parent coroutine just keeps running
            while (true) {
                delay(1000)
            }
        }
    }

    /**
     * Cancels the parent coroutine, which automatically cancels the child.
     * 
     * This demonstrates the key concept from the article: structured concurrency.
     * When a parent Job is cancelled, all its children are cancelled too.
     * This ensures that you can't lose track of coroutines.
     */
    private fun cancelParentCoroutine() {
        binding.tvParentStatus.text = "Parent Status: Cancelling..."
        
        // Cancel the parent job
        parentJob?.cancel()
        parentJob = null
        
        binding.tvParentStatus.text = "Parent Status: Cancelled"
        binding.tvChildStatus.text = "Child Status: Cancelled (automatically)"
        
        // Note: We didn't need to manually cancel the child coroutine
        // It was automatically cancelled when the parent was cancelled
        // This is the power of structured concurrency!
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clean up the parent job
        parentJob?.cancel()
        parentJob = null
        _binding = null
    }
} 