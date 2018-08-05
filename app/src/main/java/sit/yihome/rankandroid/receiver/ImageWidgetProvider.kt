package sit.yihome.rankandroid.receiver

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.media.ImageWriter
import android.widget.RemoteViews
import com.bumptech.glide.request.target.AppWidgetTarget
import com.orhanobut.logger.Logger
import sit.yihome.rankandroid.R
import sit.yihome.rankandroid.repository.WelfareRepository
import sit.yihome.rankandroid.wrapper.ImageWrapper
import kotlin.math.round

const val CLICK_ACTION="sit.yihome.rankandroid.imagewiget.click"

class ImageWidgetProvider : AppWidgetProvider() {

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        Logger.d("ImageWidgetProvider receive info")
        if(intent?.action == CLICK_ACTION){

        }
    }

    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        Logger.d("ImageWidgetProvider update")
        for(id in appWidgetIds!!){
            val remoteViews = RemoteViews(context!!.packageName, R.layout.img_display_widget)
            val intentClick = Intent()
            intentClick.action = CLICK_ACTION
            val pendingIntent = PendingIntent.getBroadcast(context,0,intentClick,0);
            remoteViews.setOnClickPendingIntent(R.id.img_widget,pendingIntent)
            ImageWrapper.loadImgToRemote(context,WelfareRepository.getUrl(),AppWidgetTarget(context
                    ,R.id.img_widget, remoteViews,id))
            appWidgetManager?.updateAppWidget(id,remoteViews)
        }
    }
}