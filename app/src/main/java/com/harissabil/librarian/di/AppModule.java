package com.harissabil.librarian.di;

import android.content.Context;

import com.harissabil.librarian.core.preferences.PreferencesRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public static PreferencesRepository providePreferencesRepository(@ApplicationContext Context context) {
        return new PreferencesRepository(context);
    }
}
