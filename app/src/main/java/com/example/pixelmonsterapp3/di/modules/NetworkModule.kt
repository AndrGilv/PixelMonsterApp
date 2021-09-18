package com.example.pixelmonsterapp3.di.modules

import com.example.pixelmonsterapp3.data.network.MonsterApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module()
abstract class NetworkModule {
    companion object {

        const val BASE_URL = "https://app.pixelencounter.com/"

        /*@Provides
        fun provideOkHttpClient(tokenDataSource: TokenDataSource): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor(tokenDataSource))
                .build()*/
        @Provides
        fun provideOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                .build()

        @Provides
        fun provideMoshi(): Moshi = Moshi.Builder()
            /*.add(KotlinJsonAdapterFactory())
            .add(LocalDateTimeAdapter())
            .add(LoanStatusAdapter())*/
            .build()

        @Provides
        fun provideRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
//                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()

        @Provides
        fun provideApiService(retrofit: Retrofit): MonsterApi =
            retrofit.create(MonsterApi::class.java)
    }
}
