package com.avciapps.basedemoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.avciapps.basedemoapp.databinding.FragmentScopeAnalysisBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Demo 7: Scope Analysis
 * 
 * This demo demonstrates the relationship between scopes, contexts, and jobs.
 * 
 * Key Concepts:
 * - A coroutine scope is just a wrapper for a coroutine context, which holds a Job
 * - The scope is named by its intended use - to limit and control coroutines' lifespans
 * - Different scopes have different lifecycle management strategies
 * - Scope = Context + Job
 * 
 * Based on the Medium article: "Seven demos to understand coroutines: scope, context and Jobs"
 */
class ScopeAnalysisFragment : Fragment() {

    private var _binding: FragmentScopeAnalysisBinding? = null
    private val binding get() = _binding!!

    private var customScope: CoroutineScope? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScopeAnalysisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
    }

    /**
     * Sets up the buttons for demonstrating scope analysis.
     */
    private fun setupButtons() {
        binding.btnAnalyzeLifecycleScope.setOnClickListener {
            analyzeLifecycleScope()
        }

        binding.btnAnalyzeCustomScope.setOnClickListener {
            analyzeCustomScope()
        }

        binding.btnCompareScopes.setOnClickListener {
            compareDifferentScopes()
        }

        binding.btnShowScopeRelationship.setOnClickListener {
            showScopeRelationship()
        }
    }

    /**
     * Analyzes the lifecycleScope to understand its structure.
     * 
     * This demonstrates the article's concept: the lifecycleScope
     * is a scope with a Job that's cancelled when the lifecycle ends.
     */
    private fun analyzeLifecycleScope() {
        binding.tvScopeAnalysisOutput.text = "Analyzing lifecycleScope..."
        
        lifecycleScope.launch {
            val scopeInfo = buildString {
                appendLine("LifecycleScope Analysis:")
                appendLine("• Scope Type: ${lifecycleScope::class.simpleName}")
                appendLine("• Context: ${lifecycleScope.coroutineContext}")
                appendLine("• Job: ${lifecycleScope.coroutineContext[Job]}")
                appendLine("• Is Active: ${lifecycleScope.coroutineContext[Job]?.isActive}")
                appendLine("• Lifecycle: Tied to Fragment lifecycle")
                appendLine("• Cancellation: Automatic when lifecycle ends")
            }
            
            binding.tvScopeAnalysisOutput.text = scopeInfo
        }
    }

    /**
     * Analyzes a custom scope to understand its structure.
     * 
     * This demonstrates creating a custom scope and analyzing its components.
     */
    private fun analyzeCustomScope() {
        binding.tvScopeAnalysisOutput.text = "Creating and analyzing custom scope..."
        
        // Create a custom scope with a SupervisorJob
        customScope = CoroutineScope(SupervisorJob())
        
        lifecycleScope.launch {
            val customScopeInfo = buildString {
                appendLine("Custom Scope Analysis:")
                appendLine("• Scope Type: ${customScope!!::class.simpleName}")
                appendLine("• Context: ${customScope!!.coroutineContext}")
                appendLine("• Job: ${customScope!!.coroutineContext[Job]}")
                appendLine("• Job Type: SupervisorJob")
                appendLine("• Lifecycle: Manual management required")
                appendLine("• Cancellation: Must be cancelled manually")
            }
            
            binding.tvScopeAnalysisOutput.text = customScopeInfo
        }
    }

    /**
     * Compares different types of scopes.
     * 
     * This demonstrates the article's concept: different scopes
     * have different lifecycle management strategies.
     */
    private fun compareDifferentScopes() {
        binding.tvScopeAnalysisOutput.text = "Comparing different scopes..."
        
        lifecycleScope.launch {
            val comparisonInfo = buildString {
                appendLine("Scope Comparison:")
                appendLine()
                appendLine("1. lifecycleScope:")
                appendLine("   • Job cancelled when lifecycle ends")
                appendLine("   • Automatic lifecycle management")
                appendLine("   • Safe for UI components")
                appendLine()
                appendLine("2. viewModelScope:")
                appendLine("   • Job cancelled when ViewModel cleared")
                appendLine("   • Automatic lifecycle management")
                appendLine("   • Safe for ViewModels")
                appendLine()
                appendLine("3. rememberCoroutineScope():")
                appendLine("   • Job cancelled when composable exits")
                appendLine("   • Automatic lifecycle management")
                appendLine("   • Safe for Compose")
                appendLine()
                appendLine("4. Custom Scope:")
                appendLine("   • Manual lifecycle management")
                appendLine("   • Must be cancelled manually")
                appendLine("   • More control, more responsibility")
            }
            
            binding.tvScopeAnalysisOutput.text = comparisonInfo
        }
    }

    /**
     * Shows the fundamental relationship between scope, context, and job.
     * 
     * This demonstrates the article's key insight: a scope is just
     * a wrapper for a context that holds a job.
     */
    private fun showScopeRelationship() {
        binding.tvScopeAnalysisOutput.text = "Showing scope-context-job relationship..."
        
        lifecycleScope.launch {
            val relationshipInfo = buildString {
                appendLine("Scope = Context + Job")
                appendLine()
                appendLine("Fundamental Relationship:")
                appendLine("• CoroutineScope is an interface")
                appendLine("• It has a coroutineContext property")
                appendLine("• The context contains a Job")
                appendLine("• Scope = Context + Job")
                appendLine()
                appendLine("Why separate names?")
                appendLine("• Scope: Named by intended use")
                appendLine("• Context: Named by structure")
                appendLine("• Job: Named by function")
                appendLine()
                appendLine("Example:")
                appendLine("• lifecycleScope: Context with Job tied to lifecycle")
                appendLine("• viewModelScope: Context with Job tied to ViewModel")
                appendLine("• rememberCoroutineScope(): Context with Job tied to composition")
            }
            
            binding.tvScopeAnalysisOutput.text = relationshipInfo
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clean up the custom scope
        customScope?.cancel()
        customScope = null
        _binding = null
    }
} 