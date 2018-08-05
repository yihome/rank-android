package sit.yihome.rankandroid.wrapper

import android.content.Context
import android.graphics.Bitmap
import android.support.annotation.RawRes
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.AppWidgetTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition

/**
 * Created by houyi on 2018/3/15.
 */
object ImageWrapper {
    fun loadImgWithHeight(context:Context,url:String,img : ImageView){
        Glide.with(context)
                .asBitmap()
                .load(url)
                .into(object:SimpleTarget<Bitmap>(){
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        val layoutParams = img.layoutParams
                        layoutParams.height = (img.width*resource.height*1.0/resource.width).toInt()
                        img.layoutParams = layoutParams
                        img.setImageBitmap(resource)
                    }

                })
    }

    fun loadImg(context:Context,url:String,img : ImageView){
        Glide.with(context)
                .asBitmap()
                .load(url)
                .into(img)
    }

    fun loadImgToRemote(context: Context, url: String, appWidgetTarget: AppWidgetTarget) {
        Glide.with(context)
                .asBitmap()
                .load(url)
                .into(appWidgetTarget)
    }
}