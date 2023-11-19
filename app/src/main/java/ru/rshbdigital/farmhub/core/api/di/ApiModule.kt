package ru.rshbdigital.farmhub.core.api.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.rshbdigital.farmhub.client.login.AuthTokenStorage
import ru.rshbdigital.farmhub.core.api.AuthInterceptor
import ru.rshbdigital.farmhub.core.api.Constants
import ru.rshbdigital.farmhub.core.api.NetworkApi
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideAuthTokenStorage(
        @ApplicationContext context: Context
    ): AuthTokenStorage {
        return AuthTokenStorage(context)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authTokenStorage: AuthTokenStorage,
    ): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .addInterceptor(AuthInterceptor(authTokenStorage))
            .build()
    }

    @Provides
    @Singleton
    fun provideNetworkApi(
        retrofit: Retrofit
    ): NetworkApi {
        return retrofit.create(NetworkApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
    ): Retrofit {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}