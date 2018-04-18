package sit.yihome.rankandroid.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import sit.yihome.rankandroid.viewmodel.bean.BeanWrapper
import sit.yihome.rankandroid.viewmodel.bean.WelfareBean

/**
 * Created by houyi on 2018/3/15.
 */
interface WelfareService{

    @GET("api/data/福利/{count}/{page}")
    abstract fun getWelfareList(@Path("count") count:Int,@Path("page") page:Int): Call<BeanWrapper<WelfareBean>>

}