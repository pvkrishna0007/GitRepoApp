package com.mobile.gitrepoapp.api

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitManagerModule {

    /**
     *  BASE URL for all the rest api calls
     */
    private const val BASE_URL = "https://api.github.com/"

    /**
     *  Created ApiInterface object
     */
    @Provides
    @Singleton
    fun provideApiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    /**
     *  Created Repository object
     */
    @Provides
    @Singleton
    fun provideRepository(apiInterface: ApiInterface): Repository {
        return RepositoryImpl(apiInterface)
    }

    /**
     *  Object mapper for json parser
     */
    @Provides
    @Singleton
    fun provideObjectMapper(): ObjectMapper {
        return ObjectMapper().apply {
            configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        }
    }

    /**
     *  Created Retrofit client object
     */
    @Provides
    @Singleton
    fun providesRetrofitCustomClient(objectMapper: ObjectMapper): Retrofit {
        val okHttpBuilder = OkHttpClient.Builder()

//        if (BuildConfig.DEBUG) {
            // Added logging interceptor for network requests and responses
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            okHttpBuilder.addInterceptor(logging) // Logging Interceptor
            okHttpBuilder.addNetworkInterceptor(StethoInterceptor()) // Network Interceptor
        //}

        // Updated connection/read/write time out
        okHttpBuilder.connectTimeout((30 * 1000).toLong(), TimeUnit.MILLISECONDS)
        okHttpBuilder.readTimeout(180, TimeUnit.SECONDS)
        okHttpBuilder.writeTimeout((830 * 1000).toLong(), TimeUnit.MILLISECONDS)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .client(okHttpBuilder.build())
            .build()
    }

}