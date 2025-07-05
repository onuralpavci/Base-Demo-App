package com.avciapps.basedemoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.avciapps.basedemoapp.databinding.FragmentDispatcherDemoBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Demo 5: Thread Dispatchers
 * 
 * This demo demonstrates running coroutines on different thread pools.
 * 
 * Key Concepts:
 * - The dispatcher is one of the "luggage tags" in a coroutine context
 * - It tells the coroutines library which thread or thread pool to run on
 * - Different dispatchers are optimized for different types of work
 * - You can switch dispatchers using withContext()
 * 
 * Based on the Medium article: "Seven demos to understand coroutines: scope, context and Jobs"
 */
class DispatcherDemoFragment : Fragment() {

    private var _binding: FragmentDispatcherDemoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDispatcherDemoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
    }

    /**
     * Sets up the buttons for demonstrating different dispatchers.
     */
    private fun setupButtons() {
        binding.btnMainDispatcher.setOnClickListener {
            launchOnMainDispatcher()
        }

        binding.btnIoDispatcher.setOnClickListener {
            launchOnIoDispatcher()
        }

        binding.btnDefaultDispatcher.setOnClickListener {
            launchOnDefaultDispatcher()
        }

        binding.btnUnconfinedDispatcher.setOnClickListener {
            launchOnUnconfinedDispatcher()
        }
    }

    /**
     * Launches a coroutine on the Main dispatcher.
     * 
     * This demonstrates the article's concept: the Main dispatcher
     * runs coroutines on the UI thread, which is safe for UI updates.
     */
    private fun launchOnMainDispatcher() {
        binding.tvDispatcherOutput.text = "Launching on Main dispatcher..."
        
        lifecycleScope.launch(Dispatchers.Main) {
            val threadName = Thread.currentThread().name
            binding.tvDispatcherOutput.text = "Main Dispatcher:\nThread: $threadName\nSafe for UI updates!"
            
            delay(2000)
            binding.tvDispatcherOutput.text = "Main dispatcher coroutine completed"
        }
    }

    /**
     * Launches a coroutine on the IO dispatcher.
     * 
     * This demonstrates the article's concept: the IO dispatcher
     * is optimized for I/O operations like network calls and file operations.
     */
    private fun launchOnIoDispatcher() {
        binding.tvDispatcherOutput.text = "Launching on IO dispatcher..."
        
        lifecycleScope.launch(Dispatchers.IO) {
            val threadName = Thread.currentThread().name
            binding.tvDispatcherOutput.text = "IO Dispatcher:\nThread: $threadName\nOptimized for I/O operations!"
            
            // Simulate some I/O work
            delay(1000)
            
            // Switch back to Main for UI update
            withContext(Dispatchers.Main) {
                binding.tvDispatcherOutput.text = "IO dispatcher coroutine completed\nSwitched back to Main for UI update"
            }
        }
    }

    /**
     * Launches a coroutine on the Default dispatcher.
     * 
     * This demonstrates the article's concept: the Default dispatcher
     * is optimized for CPU-intensive work.
     */
    private fun launchOnDefaultDispatcher() {
        binding.tvDispatcherOutput.text = "Launching on Default dispatcher..."
        
        lifecycleScope.launch(Dispatchers.Default) {
            val threadName = Thread.currentThread().name
            binding.tvDispatcherOutput.text = "Default Dispatcher:\nThread: $threadName\nOptimized for CPU-intensive work!"
            
            // Simulate some CPU-intensive work
            var result = 0
            for (i in 1..1000000) {
                result += i
            }
            
            // Switch back to Main for UI update
            withContext(Dispatchers.Main) {
                binding.tvDispatcherOutput.text = "Default dispatcher coroutine completed\nResult: $result"
            }
        }
    }

    /**
     * Launches a coroutine on the Unconfined dispatcher.
     * 
     * This demonstrates the article's concept: the Unconfined dispatcher
     * doesn't confine the coroutine to any specific thread.
     */
    private fun launchOnUnconfinedDispatcher() {
        binding.tvDispatcherOutput.text = "Launching on Unconfined dispatcher..."
        
        lifecycleScope.launch(Dispatchers.Unconfined) {
            val initialThread = Thread.currentThread().name
            binding.tvDispatcherOutput.text = "Unconfined Dispatcher:\nInitial Thread: $initialThread\nNo thread confinement!"
            
            delay(1000)
            
            val afterDelayThread = Thread.currentThread().name
            binding.tvDispatcherOutput.text = "Unconfined Dispatcher:\nInitial: $initialThread\nAfter delay: $afterDelayThread\nThread may change!"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 