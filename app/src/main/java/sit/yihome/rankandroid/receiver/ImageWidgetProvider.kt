package sit.yihome.rankandroid.receiver

import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.bumptech.glide.request.target.AppWidgetTarget
import com.orhanobut.logger.Logger
import sit.yihome.rankandroid.R
import sit.yihome.rankandroid.RankApplication
import sit.yihome.rankandroid.model.WelfareModel
import sit.yihome.rankandroid.viewmodel.bean.WelfareBean
import sit.yihome.rankandroid.wrapper.ImageWrapper
import javax.inject.Inject

const val CLICK_ACTION = "sit.yihome.rankandroid.imagewiget.click"

class ImageWidgetProvider : AppWidgetProvider() {

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        Logger.d("ImageWidgetProvider receive info")
        if (intent?.action == CLICK_ACTION) {
            val extras: Int = intent.getIntExtra("CLICK_POSITION", 0)
            Logger.d("Click Postion $extras")
            val remoteViews = generateRemoteViews(context, extras, {  WidgetImageContainer.getInstance().nextImageUrl(it) })
            AppWidgetManager.getInstance(context)?.updateAppWidget(ComponentName(context, ImageWidgetProvider::class.java), remoteViews)
        }
    }

    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        Logger.d("ImageWidgetProvider update")
        for (id in appWidgetIds!!) {
            Logger.d("Generate Postion $id")
            val remoteViews = generateRemoteViews(context, id, { WidgetImageContainer.getInstance().getImageUrl(it) })
            appWidgetManager?.updateAppWidget(id, remoteViews)
        }
    }

    private fun generateRemoteViews(context: Context?, id: Int, getUrl: (Int) -> String): RemoteViews {
        val remoteViews = RemoteViews(context!!.packageName, R.layout.img_display_widget)
        val intentClick = Intent()
        intentClick.action = CLICK_ACTION
        intentClick.putExtra("CLICK_POSITION", id)
        val pendingIntent = PendingIntent.getBroadcast(context, id, intentClick, FLAG_UPDATE_CURRENT)
        remoteViews.setOnClickPendingIntent(R.id.img_widget, pendingIntent)
        ImageWrapper.loadImgToRemote(context, getUrl(id), AppWidgetTarget(context
                , R.id.img_widget, remoteViews, id))
        return remoteViews
    }

    class WidgetImageContainer {
        @Inject
        lateinit var welfareModel: WelfareModel
        var welfareCache: List<WelfareBean>?
        private val urlMap: MutableMap<Int, Int> = HashMap()

        private constructor()

        init {
            container = this
            RankApplication.getAppComponent().inject(this)
            welfareCache = welfareModel.getWelfareCache()
        }

        fun getImageUrl(id: Int): String {
            return getImageUrl(id, { it })
        }

        fun nextImageUrl(id: Int): String {
            return getImageUrl(id, { (it + 1)% (welfareCache?.size?:it+1)})
        }

        private fun getImageUrl(id: Int, transform: (Int) -> Int): String {
            val position = urlMap[id] ?: 0
            urlMap[id] = transform(position)
            Logger.d("image Position: ${urlMap[id]}")
            if (welfareCache == null || welfareCache!!.isNotEmpty()) {
                welfareCache = welfareModel.getWelfareCache()
            }
            val url: String? = welfareCache?.get(transform(position))?.url
            return url ?: "http://ww1.sinaimg.cn/large/0065oQSqgy1ftt7g8ntdyj30j60op7dq.jpg"
        }

        companion object {
            var container: WidgetImageContainer = WidgetImageContainer()

            fun getInstance():WidgetImageContainer{
               return container
            }
        }
    }
}