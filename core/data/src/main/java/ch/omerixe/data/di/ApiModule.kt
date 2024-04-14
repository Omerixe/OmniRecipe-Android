package ch.omerixe.data.di

import ch.omerixe.data.domain.OmniRecipeApi
import ch.omerixe.data.domain.ktor.OmniRecipeKtorApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiModule {

    @Binds
    abstract fun bindOmniRecipeApi(omniRecipeKtorApi: OmniRecipeKtorApi): OmniRecipeApi
}