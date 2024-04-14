package ch.omerixe.data.domain.ktor.di

import ch.omerixe.data.domain.ktor.client
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
