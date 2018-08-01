package sit.yihome.rankandroid.viewmodel.bean

import java.text.SimpleDateFormat

/**
 * Created by houyi on 2018/3/15.
 */
data class WelfareBean(val _id:String,val createdAt:String,val url:String,val publishedAt:String):Comparable<WelfareBean>{
    override fun compareTo(other: WelfareBean): Int {
        //2018-07-30T00:00:00.0Z
        val dateFormat = SimpleDateFormat("YYYY-MM-DD'T'HH:MM:SS.SSS'Z'")
        val otherTime = dateFormat.parse(other.publishedAt)
        val thisTime = dateFormat.parse(publishedAt)
        return thisTime.compareTo(otherTime)
    }
}