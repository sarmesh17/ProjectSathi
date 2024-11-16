package com.matrix.projectsathi.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent:: class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideFirebaseDatabaseReference(): FirebaseDatabase {

        return FirebaseDatabase.getInstance()
    }


    @Provides
    @Singleton

    fun provideFirebaseStorage(): FirebaseStorage {

        return FirebaseStorage.getInstance()

    }



    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {

        return FirebaseAuth.getInstance()
    }


}