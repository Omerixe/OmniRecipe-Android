package ch.omerixe.data.di

import ch.omerixe.data.BuildConfig
import ch.omerixe.data.domain.OmniRecipeApi
import ch.omerixe.data.domain.ktor.OmniRecipeKtorApi
import ch.omerixe.data.domain.mock.OmniRecipeMockApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap

private const val NETWORK = 0
private const val MOCK = 1

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {
    @Provides
    fun provideOmniRecipeApi(
        availableClients: Map<Int, @JvmSuppressWildcards OmniRecipeApi>
    ): OmniRecipeApi {
        return if (BuildConfig.USE_MOCK) {
            checkNotNull(availableClients[MOCK]) { "No MockApi was provided" }
        } else {
            checkNotNull(availableClients[NETWORK]) { "No KtorApi was provided" }
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiBindingModule {

    @Binds
    @IntoMap
    @IntKey(NETWORK)
    abstract fun bindOmniRecipeKtorApi(omniRecipeKtorApi: OmniRecipeKtorApi): OmniRecipeApi

    @Binds
    @IntoMap
    @IntKey(MOCK)
    abstract fun bindOmniRecipeMockApi(omniRecipeMockApi: OmniRecipeMockApi): OmniRecipeApi
}