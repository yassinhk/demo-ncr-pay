package tn.com.ncr.ncrpay.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tn.com.ncr.ncrpay.common.Constants
import tn.com.ncr.ncrpay.common.StoreData
import tn.com.ncr.ncrpay.data.remote.NcrPayApi
import tn.com.ncr.ncrpay.data.repository.AccountRepositoryImplementation
import tn.com.ncr.ncrpay.data.repository.UserRepositoryImplementation
import tn.com.ncr.ncrpay.domain.repository.AccountRepository
import tn.com.ncr.ncrpay.domain.repository.UserRepository
import java.util.prefs.Preferences
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNcrPayApi() : NcrPayApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NcrPayApi::class.java)
    }


    @Provides
    @Singleton
    fun provideUserRepository(api : NcrPayApi, dataStore: StoreData) : UserRepository{
        return UserRepositoryImplementation(api = api, dataStore =  dataStore)
    }

    @Provides
    @Singleton
    fun provideAccountRepsitory(api: NcrPayApi, dataStore: StoreData) : AccountRepository{
        return AccountRepositoryImplementation(ncrPayApi = api, dataStore = dataStore)
    }

    @Singleton
    @Provides
    fun provideStoreData(@ApplicationContext context: Context) = StoreData(context)

}
