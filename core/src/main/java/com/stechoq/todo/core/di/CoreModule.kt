package com.stechoq.todo.core.di

import androidx.room.Room
import com.stechoq.todo.core.BuildConfig
import com.stechoq.todo.core.data.repository.TodosRepository
import com.stechoq.todo.core.data.source.local.LocalDataSource
import com.stechoq.todo.core.data.source.local.room.TodoDatabase
import com.stechoq.todo.core.data.source.remote.RemoteDataSource
import com.stechoq.todo.core.data.source.remote.network.ApiService
import com.stechoq.todo.core.domain.repository.ITodosRepository
import com.stechoq.todo.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val databaseModule = module {
    val passphrase: ByteArray = SQLiteDatabase.getBytes("StechoqTODO".toCharArray())
    val factory = SupportFactory(passphrase)
    factory { get<TodoDatabase>().todoDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            TodoDatabase::class.java, "Todo.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        }
        val hostname = "jsonplaceholder.typicode.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/XlCPXC6IrttTF9Y1887kS+efCCf3uFjHW6D1TUI9f+Q=")
            .add(hostname, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
            .add(hostname, "sha256/Y9mvm0exBk1JoQ57f9Vm28jKo5lFm/woKcVxrYxu80o=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<ITodosRepository> {
        TodosRepository(
            get(),
            get()
        )
    }

}