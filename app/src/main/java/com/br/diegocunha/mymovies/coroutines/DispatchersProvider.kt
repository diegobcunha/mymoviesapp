package com.br.diegocunha.mymovies.coroutines

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchersProvider {

    /**
    +-----------------------------------+
    | Main thread on Android, interact  |
    | with the UI and perform light     |
    | work                              |
    +-----------------------------------+
    | - Calling suspend functions       |
    | - Call UI functions               |
    | - Updating LiveData               |
    +-----------------------------------+
     */
    val main: CoroutineDispatcher

    /**
    +-----------------------------------+
    | Optimized for disk and network IO |
    | off the main thread               |
    +-----------------------------------+
    | - Database*                       |
    | - Reading/writing files           |
    | - Networking**                    |
    +-----------------------------------+
     */
    val io: CoroutineDispatcher

    /**
    +-----------------------------------+
    | Optimized for CPU intensive work  |
    | off the main thread               |
    +-----------------------------------+
    | - Sorting a list                  |
    | - Parsing JSON                    |
    | - DiffUtils                       |
    +-----------------------------------+
     */
    val computing: CoroutineDispatcher
}