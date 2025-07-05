package com.avciapps.basedemoapp

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.avciapps.basedemoapp.databinding.FragmentScopeDemoBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

/**
 * Demo 1: Coroutine Scopes
 * 
 * This demo demonstrates the concept of coroutine scopes and fire-and-forget patterns.
 * 
 * Key Concepts:
 * - A coroutine scope is a "box" that restricts coroutines
 * - When the scope is destroyed, all coroutines inside it are automatically cancelled
 * - This enables safe fire-and-forget patterns without manual tracking
 * - The lifecycleScope is tied to the fragment's lifecycle
 * 
 * Based on the Medium article: "Seven demos to understand coroutines: scope, context and Jobs"
 */
class ScopeDemoFragment : Fragment() {

    private var _binding: FragmentScopeDemoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScopeDemoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAnimationButton()
    }

    /**
     * Sets up the button to demonstrate fire-and-forget coroutines.
     * 
     * This demonstrates the key concept from the article: we can safely launch a coroutine
     * and "forget about it" because it's launched into a scope that will automatically
     * cancel it when the fragment is destroyed.
     */
    private fun setupAnimationButton() {
        binding.btnStartAnimation.setOnClickListener {
            startColorAnimation()
        }
    }

    /**
     * Starts a continuous color-changing animation using a fire-and-forget coroutine.
     * 
     * This is the core demonstration of the article's concept:
     * - We launch a coroutine into the lifecycleScope
     * - The coroutine runs indefinitely (while(true))
     * - We don't need to track or manually cancel it
     * - When the fragment is destroyed, the scope is cancelled
     * - All coroutines in the scope are automatically cancelled
     * 
     * This would be unthinkable with threads - you'd have to meticulously track them
     * to avoid resource leaks and weird bugs.
     */
    private fun startColorAnimation() {
        binding.tvStatus.text = "Status: Animation running (try navigating away!)"
        
        // This is the key demonstration: fire-and-forget coroutine
        // We launch it into the lifecycleScope and forget about it
        lifecycleScope.launch {
            while (true) {
                // Generate a random color
                val randomColor = Color.rgb(
                    Random.nextInt(256),
                    Random.nextInt(256),
                    Random.nextInt(256)
                )
                
                // Update the button background color
                binding.btnStartAnimation.setBackgroundColor(randomColor)
                
                // Wait for 500ms before the next color change
                delay(500)
            }
        }
        
        // Note: We don't store a reference to the coroutine Job
        // We don't need to manually cancel it
        // The lifecycleScope will handle cancellation automatically
        // This is the power of structured concurrency!
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 