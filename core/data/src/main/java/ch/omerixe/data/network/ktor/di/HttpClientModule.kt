package ch.omerixe.data.network.ktor.di

import ch.omerixe.data.network.ktor.client
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
object HttpClientModule {

    @Provides
    fun provideHttpClient(): HttpClient {
        return client
    }
}
