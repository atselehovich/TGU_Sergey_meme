package org.gutmann.memeview.model

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [RESTApiMemesRepoModule::class])
@InstallIn(SingletonComponent::class)
interface MemesRepositoryModule {
    @Binds
    fun provideRepository(repo: RESTApiMemesRepository): MemesRepository
}