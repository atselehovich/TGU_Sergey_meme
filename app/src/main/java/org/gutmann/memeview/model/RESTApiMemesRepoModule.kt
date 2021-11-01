package org.gutmann.memeview.model

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.gutmann.memeview.api.MemesApi
import org.gutmann.memeview.api.RESTApiModule

@Module(includes = [RESTApiModule::class])
@InstallIn(SingletonComponent::class)
class RESTApiMemesRepoModule {
    @Provides
    fun provideMemesRepository(memesApi: MemesApi): RESTApiMemesRepository =
        RESTApiMemesRepository(memesApi)
}