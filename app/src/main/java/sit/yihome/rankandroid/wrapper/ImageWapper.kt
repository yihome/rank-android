package sit.yihome.rankandroid.wrapper

import android.content.Context
import android.support.annotation.RawRes
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by houyi on 2018/3/15.
 */
object ImageWapper {
    fun loadImg(context:Context,url:String,img : ImageView){
        Glide.with(context).load(url).into(img)
    }
}