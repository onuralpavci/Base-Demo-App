package com.avciapps.basedemoapp

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.avciapps.basedemoapp.databinding.FragmentJobDemoBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

/**
 * Demo 2: Jobs & Cancellation
 * 
 * This demo demonstrates how to manually cancel coroutines using Job objects.
 * 
 * Key Concepts:
 * - When you launch a coroutine, you get a Job that represents the running coroutine
 * - You can cancel the coroutine using Job.cancel() without affecting other coroutines
 * - This provides fine-grained control over individual coroutines
 * - Other coroutines in the same scope continue running when one is cancelled
 * 
 * Based on the Medium article: "Seven demos to understand coroutines: scope, context and Jobs"
 */
class JobDemoFragment : Fragment() {

    private var _binding: FragmentJobDemoBinding? = null
    private val binding get() = _binding!!

    // Store the Job reference for manual cancellation
    private var animationJob: kotlinx.coroutines.Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJobDemoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
    }

    /**
     * Sets up the buttons for demonstrating Job cancellation.
     */
    private fun setupButtons() {
        binding.btnStartJob.setOnClickListener {
            startAnimationJob()
        }

        binding.btnCancelJob.setOnClickListener {
            cancelAnimationJob()
        }

        binding.btnFlashingButton.setOnClickListener {
            startFlashingButton()
        }
    }

    /**
     * Starts a color-changing animation and stores the Job reference.
     * 
     * This demonstrates the key concept from the article: when you launch a coroutine,
     * you get a Job that represents the running coroutine. You can use this Job
     * to cancel the coroutine without affecting other coroutines in the same scope.
     */
    private fun startAnimationJob() {
        binding.tvJobStatus.text = "Status: Animation job started"
        
        // Launch a coroutine and store the Job reference
        // This is the key demonstration: we get a Job that represents the running coroutine
        animationJob = lifecycleScope.launch {
            while (true) {
                val randomColor = Color.rgb(
                    Random.nextInt(256),
                    Random.nextInt(256),
                    Random.nextInt(256)
                )
                
                binding.btnStartJob.setBackgroundColor(randomColor)
                delay(300)
            }
        }
        
        // Note: We store the Job reference so we can cancel it later
        // This is different from the fire-and-forget pattern in Demo 1
    }

    /**
     * Cancels the animation job after a 2-second delay.
     * 
     * This demonstrates the article's concept: you can cancel a specific coroutine
     * using its Job without affecting other coroutines in the same scope.
     */
    private fun cancelAnimationJob() {
        binding.tvJobStatus.text = "Status: Cancelling animation job in 2 seconds..."
        
        // Launch a coroutine to cancel the animation job after 2 seconds
        lifecycleScope.launch {
            delay(2000) // Wait 2 seconds
            
            // Cancel the specific animation job
            animationJob?.cancel()
            animationJob = null
            
            binding.tvJobStatus.text = "Status: Animation job cancelled! Flashing button continues..."
        }
    }

    /**
     * Starts a flashing button animation that continues even after the main animation is cancelled.
     * 
     * This demonstrates that cancelling one coroutine doesn't affect others in the same scope.
     */
    private fun startFlashingButton() {
        // This coroutine will continue running even after the animation job is cancelled
        lifecycleScope.launch {
            while (true) {
                binding.btnFlashingButton.setBackgroundColor(Color.RED)
                delay(500)
                binding.btnFlashingButton.setBackgroundColor(Color.BLUE)
                delay(500)
            }
        }
        
        binding.tvJobStatus.text = "Status: Flashing button started (independent of animation job)"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clean up the job reference
        animationJob = null
        _binding = null
    }
} 