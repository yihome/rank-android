package sit.yihome.rankandroid.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sit.yihome.rankandroid.network.NetApi
import sit.yihome.rankandroid.viewmodel.bean.BeanWrapper
import sit.yihome.rankandroid.viewmodel.bean.LiveUpdateWrapper
import sit.yihome.rankandroid.viewmodel.bean.WelfareBean
import javax.inject.Inject

/**
 * Created by houyi on 2018/3/15.
 */
class WelfareRepository @Inject constructor(netApi: NetApi) {
    private var nextPage = FIRST_PAGE+1

    companion object {
        const val FIRST_PAGE: Int = 1
    }

    private val netApi: NetApi = netApi

    fun getWelfare(): LiveData<LiveUpdateWrapper<WelfareBean>> {
        return getWelfare(FIRST_PAGE, false, null)
    }

    private fun getWelfare(page: Int, loadMore: Boolean, welfares: MutableLiveData<LiveUpdateWrapper<WelfareBean>>?): LiveData<LiveUpdateWrapper<WelfareBean>> {
        var welfare = welfares
        if (welfare == null) {
            welfare = MutableLiveData()
        }
        netApi.getRepository(page = page, callback = object : Callback<BeanWrapper<WelfareBean>> {
            override fun onFailure(call: Call<BeanWrapper<WelfareBean>>?, t: Throwable?) {
                Log.d("", t!!.message)
            }

            override fun onResponse(call: Call<BeanWrapper<WelfareBean>>?, response: Response<BeanWrapper<WelfareBean>>?) {
                if (loadMore) {
                    nextPage++
                    val value = welfare.value
                    value?.addLast(response?.body()?.results)
                    welfare.value = value
                }else {
                    welfare.value = LiveUpdateWrapper(response?.body()?.results)
                }
            }

        })
        return welfare
    }

    fun loadMoreWelfare(welfares: MutableLiveData<LiveUpdateWrapper<WelfareBean>>){
        getWelfare(nextPage, true, welfares)
    }
}