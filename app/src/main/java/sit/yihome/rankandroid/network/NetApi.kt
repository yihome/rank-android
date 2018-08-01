package sit.yihome.rankandroid.network

import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import sit.yihome.rankandroid.viewmodel.bean.BeanWrapper
import sit.yihome.rankandroid.viewmodel.bean.WelfareBean
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor



/**
 * Created by houyi on 2018/3/15.
 */
class NetApi {
    private  var welfareService: WelfareService

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl("http://gank.io")
                .build()

        welfareService = retrofit.create<WelfareService>(WelfareService::class.java)
    }

    fun getRepository(count: Int = 10, page: Int,callback:Callback<BeanWrapper<WelfareBean>>) {
        welfareService.getWelfareList(count,page).enqueue(callback)
    }

    object Instance{
        fun get():NetApi{
            return NetApi()
        }
    }
}