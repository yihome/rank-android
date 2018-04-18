package sit.yihome.rankandroid.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sit.yihome.rankandroid.network.NetApi
import sit.yihome.rankandroid.viewmodel.bean.BeanWrapper
import sit.yihome.rankandroid.viewmodel.bean.WelfareBean
import javax.inject.Inject

/**
 * Created by houyi on 2018/3/15.
 */
class WelfareRepository @Inject constructor(netApi:NetApi){
    private val netApi: NetApi = netApi

    fun getWelfare(): LiveData<List<WelfareBean>> {
        val welfares = MutableLiveData<List<WelfareBean>>()
        netApi.getRepository(page = 1,callback = object : Callback<BeanWrapper<WelfareBean>> {
            override fun onFailure(call: Call<BeanWrapper<WelfareBean>>?, t: Throwable?) {
                Log.d("",t!!.message)
            }

            override fun onResponse(call: Call<BeanWrapper<WelfareBean>>?, response: Response<BeanWrapper<WelfareBean>>?) {
                welfares.value = response?.body()?.results
            }

        })
        return welfares
    }
}