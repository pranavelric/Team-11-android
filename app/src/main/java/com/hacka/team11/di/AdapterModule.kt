package com.hacka.team11.di

import com.hacka.team11.adapters.MatchAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
class AdapterModule {

    @Provides
    fun providesMatchAdapter(): MatchAdapter {
        return MatchAdapter()
    }
}