package com.avciapps.basedemoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.avciapps.basedemoapp.databinding.FragmentContextCombinationBinding
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Demo 6: Context Combinations
 * 
 * This demo demonstrates combining different context elements.
 * 
 * Key Concepts:
 * - You can combine context elements using the + operator
 * - Any elements you don't mention will be inherited from the parent's context
 * - This provides flexible context composition
 * - Context inheritance ensures consistency
 * 
 * Based on the Medium article: "Seven demos to understand coroutines: scope, context and Jobs"
 */
class ContextCombinationFragment : Fragment() {

    private var _binding: FragmentContextCombinationBinding? = null
    private val binding get() = _binding!!

    private var customJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContextCombinationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
    }

    /**
     * Sets up the buttons for demonstrating context combinations.
     */
    private fun setupButtons() {
        binding.btnNamedIoCoroutine.setOnClickListener {
            launchNamedIoCoroutine()
        }

        binding.btnCustomJobDefault.setOnClickListener {
            launchCustomJobDefault()
        }

        binding.btnComplexCombination.setOnClickListener {
            launchComplexCombination()
        }

        binding.btnShowInheritance.setOnClickListener {
            showContextInheritance()
        }
    }

    /**
     * Launches a coroutine with a name and IO dispatcher combined.
     * 
     * This demonstrates the article's concept: you can combine different
     * context elements using the + operator.
     */
    private fun launchNamedIoCoroutine() {
        binding.tvCombinationOutput.text = "Launching named coroutine on IO dispatcher..."
        
        // Combine a name and IO dispatcher
        val combinedContext = Dispatchers.IO + CoroutineName("MyIOCoroutine")
        
        lifecycleScope.launch(combinedContext) {
            val threadName = Thread.currentThread().name
            val coroutineName = coroutineContext[CoroutineName]?.name
            
            binding.tvCombinationOutput.text = "Named IO Coroutine:\nThread: $threadName\nName: $coroutineName\nCombined: IO + Name"
            
            delay(2000)
            binding.tvCombinationOutput.text = "Named IO coroutine completed"
        }
    }

    /**
     * Launches a coroutine with a custom job and default dispatcher.
     * 
     * This demonstrates combining a custom job with a specific dispatcher.
     */
    private fun launchCustomJobDefault() {
        binding.tvCombinationOutput.text = "Launching coroutine with custom job on default dispatcher..."
        
        // Create a custom job
        customJob = Job()
        
        // Combine custom job with default dispatcher
        val combinedContext = Dispatchers.Default + customJob!!
        
        lifecycleScope.launch(combinedContext) {
            val threadName = Thread.currentThread().name
            val job = coroutineContext[Job]
            
            binding.tvCombinationOutput.text = "Custom Job Default:\nThread: $threadName\nJob: $job\nCombined: Default + CustomJob"
            
            delay(3000)
            binding.tvCombinationOutput.text = "Custom job default coroutine completed"
        }
    }

    /**
     * Launches a coroutine with a complex combination of context elements.
     * 
     * This demonstrates the article's example: combining multiple context elements.
     */
    private fun launchComplexCombination() {
        binding.tvCombinationOutput.text = "Launching coroutine with complex context combination..."
        
        // Complex combination: IO dispatcher + name + custom job
        val complexContext = Dispatchers.IO + CoroutineName("boo") + Job()
        
        lifecycleScope.launch(complexContext) {
            val threadName = Thread.currentThread().name
            val coroutineName = coroutineContext[CoroutineName]?.name
            val job = coroutineContext[Job]
            
            binding.tvCombinationOutput.text = "Complex Combination:\nThread: $threadName\nName: $coroutineName\nJob: $job\nCombined: IO + Name + Job"
            
            delay(2000)
            binding.tvCombinationOutput.text = "Complex combination coroutine completed"
        }
    }

    /**
     * Shows how context elements are inherited from the parent.
     * 
     * This demonstrates the article's concept: any context elements
     * you don't mention will be inherited from the parent's context.
     */
    private fun showContextInheritance() {
        binding.tvCombinationOutput.text = "Demonstrating context inheritance..."
        
        // Launch a coroutine with only a name (inherits dispatcher from parent)
        lifecycleScope.launch(CoroutineName("InheritedContext")) {
            val threadName = Thread.currentThread().name
            val coroutineName = coroutineContext[CoroutineName]?.name
            val dispatcher = coroutineContext[kotlinx.coroutines.CoroutineDispatcher]
            
            binding.tvCombinationOutput.text = "Context Inheritance:\nThread: $threadName\nName: $coroutineName\nDispatcher: $dispatcher\nInherited dispatcher from parent!"
            
            delay(2000)
            binding.tvCombinationOutput.text = "Context inheritance demonstration completed"
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