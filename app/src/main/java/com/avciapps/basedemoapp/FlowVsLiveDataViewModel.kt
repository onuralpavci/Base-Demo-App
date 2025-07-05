package com.avciapps.basedemoapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import java.util.concurrent.atomic.AtomicInteger

/**
 * ViewModel demonstrating the differences between Flow and LiveData behavior
 *
 * Key concepts demonstrated:
 * 1. LiveData behavior - lifecycle-aware, single value holder
 * 2. Cold Flow behavior - only works when collected, stops when collector is cancelled
 * 3. Hot Flow with Eagerly - starts immediately, continues even without collectors
 * 4. Hot Flow with WhileSubscribed - starts when first collector arrives, stops when no collectors
 * 5. StateFlow - hot Flow similar to LiveData
 */
class FlowVsLiveDataViewModel : ViewModel() {

    // ===== SEPARATE COUNTERS FOR EACH FLOW TYPE =====
    // Each Flow now has its own counter so they don't affect each other
    private val liveDataInteger = AtomicInteger(0)
    private val stateFlowCounter = AtomicInteger(0)
    private val coldFlowCounter = AtomicInteger(0)
    private val hotFlowEagerlyCounter = AtomicInteger(0)
    private val hotFlowWhileSubscribedCounter = AtomicInteger(0)

    // ===== LIVE DATA APPROACH =====
    // LiveData is lifecycle-aware and holds a single value
    val liveDataCounter = liveData {
        var value = liveDataInteger.get()
        while (true) {
            Log.d("testingg", "Working in livedata")
            emit(value)
            value++
            liveDataInteger.set(value)
            delay(1000) // Update every second
        }
    }

    // ===== COLD FLOW APPROACH =====
    // This is a cold Flow - only works when collected, stops when collector is cancelled
    fun getColdFlow() = flow {
        var value = coldFlowCounter.get()
        while (true) {
            Log.d("testingg", "Working in cold flow")
            emit(value)
            value++
            coldFlowCounter.set(value)
            delay(1000) // Update every second
        }
    }

    // ===== BASIC STATEFLOW APPROACH =====
    // StateFlow is already hot - similar to LiveData
    private val _basicStateFlowCounter = MutableStateFlow(0)
    val basicStateFlowCounter: StateFlow<Int> = _basicStateFlowCounter.asStateFlow()

    // ===== HOT FLOW WITH EAGERLY =====
    // This hot Flow starts immediately and continues even without collectors
    private val hotFlowEagerly = flow {
        var value = hotFlowEagerlyCounter.get()
        while (true) {
            // Log.d("testingg", "Working in Eagerly")
            emit(value)
            value++
            hotFlowEagerlyCounter.set(value)
            _basicStateFlowCounter.value = value
            delay(1000) // Update every second
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = 0
    )

    // ===== HOT FLOW WITH WHILE SUBSCRIBED =====
    // This hot Flow starts when first collector arrives, stops when no collectors
    private val hotFlowWhileSubscribed = flow {
        var value = hotFlowWhileSubscribedCounter.get()
        while (true) {
            Log.d("testingg", "Working in WhileSubscribed")
            emit(value)
            value++
            hotFlowWhileSubscribedCounter.set(value)
            delay(1000) // Update every second
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(3000),
        initialValue = 0
    )

    // ===== STATUS MESSAGES =====
    private val _statusMessage = MutableLiveData<String?>(null)
    val statusMessage: LiveData<String?> = _statusMessage

    // ===== COLLECTOR STATUS =====
    private val _collectorStatus = MutableLiveData<Map<String, Boolean>>(emptyMap())
    val collectorStatus: LiveData<Map<String, Boolean>> = _collectorStatus

    private val collectorStates = mutableMapOf(
        "LiveData" to false,
        "StateFlow" to false,
        "Cold Flow" to false,
        "Hot Flow (Eagerly)" to false,
        "Hot Flow (WhileSubscribed)" to false
    )

    init {
        updateCollectorStatus()
    }

    /**
     * Update collector status
     */
    private fun updateCollectorStatus() {
        _collectorStatus.value = collectorStates.toMap()
    }

    /**
     * Set collector status for a specific type
     */
    fun setCollectorStatus(type: String, isCollecting: Boolean) {
        collectorStates[type] = isCollecting
        updateCollectorStatus()

        val status = if (isCollecting) "started collecting" else "stopped collecting"
        _statusMessage.value = "$type: $status"
    }

    /**
     * Clear status message
     */
    fun clearStatus() {
        _statusMessage.value = null
    }

    /**
     * Reset all counters
     */
    fun resetCounters() {
        liveDataInteger.set(0)
        stateFlowCounter.set(0)
        coldFlowCounter.set(0)
        hotFlowEagerlyCounter.set(0)
        hotFlowWhileSubscribedCounter.set(0)
        _basicStateFlowCounter.value = 0
        _statusMessage.value = "All counters reset"
    }

    // ===== GETTERS FOR OBSERVING FLOWS =====

    /**
     * Get the hot Flow with eagerly sharing strategy
     */
    fun getHotFlowEagerly(): StateFlow<Int> = hotFlowEagerly

    /**
     * Get the hot Flow with whileSubscribed sharing strategy
     */
    fun getHotFlowWhileSubscribed(): StateFlow<Int> = hotFlowWhileSubscribed
} 
