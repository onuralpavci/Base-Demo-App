package com.avciapps.basedemoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger

/**
 * ViewModel demonstrating the differences between Flow and LiveData behavior
 * 
 * Key concepts demonstrated:
 * 1. LiveData behavior - survives configuration changes, not process death
 * 2. Basic Flow behavior - doesn't survive configuration changes by default
 * 3. Flow with stateIn - can behave like LiveData with proper sharing strategy
 * 4. StateFlow - similar to LiveData but with Flow benefits
 */
class FlowVsLiveDataViewModel : ViewModel() {

    private val counter = AtomicInteger(0)
    
    // ===== LIVE DATA APPROACH =====
    // LiveData survives configuration changes but not process death
    private val _liveDataCounter = MutableLiveData<Int>(0)
    val liveDataCounter: LiveData<Int> = _liveDataCounter
    
    // ===== BASIC FLOW APPROACH =====
    // Basic Flow doesn't survive configuration changes
    private val _basicFlowCounter = MutableStateFlow(0)
    val basicFlowCounter: StateFlow<Int> = _basicFlowCounter.asStateFlow()
    
    // ===== FLOW WITH STATEIN (LIVEDATA-LIKE BEHAVIOR) =====
    // This Flow behaves like LiveData - survives configuration changes
    private val _flowWithStateIn = MutableStateFlow(0)
    val flowWithStateIn: StateFlow<Int> = _flowWithStateIn
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed,
            initialValue = 0
        )
    
    // ===== CONTINUOUS FLOW STREAM =====
    // Demonstrates how Flow can emit continuous updates
    private val _continuousFlow = MutableStateFlow(0)
    val continuousFlow: StateFlow<Int> = _continuousFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed,
            initialValue = 0
        )
    
    // ===== ERROR STATE =====
    private val _errorState = MutableLiveData<String?>(null)
    val errorState: LiveData<String?> = _errorState
    
    init {
        // Start continuous updates to demonstrate Flow behavior
        startContinuousUpdates()
    }
    
    /**
     * Increment LiveData counter
     * This will survive configuration changes but not process death
     */
    fun incrementLiveData() {
        val newValue = counter.incrementAndGet()
        _liveDataCounter.value = newValue
        _errorState.value = "LiveData updated: $newValue (survives config changes, not process death)"
    }
    
    /**
     * Increment basic Flow counter
     * This won't survive configuration changes by default
     */
    fun incrementBasicFlow() {
        val newValue = counter.incrementAndGet()
        _basicFlowCounter.value = newValue
        _errorState.value = "Basic Flow updated: $newValue (doesn't survive config changes)"
    }
    
    /**
     * Increment Flow with stateIn counter
     * This behaves like LiveData - survives configuration changes
     */
    fun incrementFlowWithStateIn() {
        val newValue = counter.incrementAndGet()
        _flowWithStateIn.value = newValue
        _errorState.value = "Flow with stateIn updated: $newValue (survives config changes like LiveData)"
    }
    
    /**
     * Start continuous updates to demonstrate Flow streaming behavior
     */
    private fun startContinuousUpdates() {
        viewModelScope.launch {
            var continuousValue = 0
            while (true) {
                delay(2000) // Update every 2 seconds
                continuousValue++
                _continuousFlow.value = continuousValue
            }
        }
    }
    
    /**
     * Clear error state
     */
    fun clearError() {
        _errorState.value = null
    }
    
    /**
     * Reset all counters
     */
    fun resetCounters() {
        counter.set(0)
        _liveDataCounter.value = 0
        _basicFlowCounter.value = 0
        _flowWithStateIn.value = 0
        _continuousFlow.value = 0
        _errorState.value = "All counters reset"
    }
    
    /**
     * Simulate configuration change by rotating device
     * This method helps demonstrate what survives configuration changes
     */
    fun simulateConfigurationChange() {
        _errorState.value = "Configuration change simulated. Check which counters retain their values!"
    }
} 