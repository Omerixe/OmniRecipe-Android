package ch.omerixe.data.domain

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

//Todo: Replace this ugly hack with a proper solution
@Singleton
class Coordinator @Inject constructor() {
    private val _reloadOverview = MutableSharedFlow<Unit>()
    val reloadOverview = _reloadOverview.asSharedFlow()

    suspend fun reloadOverview() {
        _reloadOverview.emit(Unit)
    }
}