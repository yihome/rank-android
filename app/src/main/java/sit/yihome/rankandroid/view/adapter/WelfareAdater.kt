package sit.yihome.rankandroid.view.adapter


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import sit.yihome.rankandroid.viewmodel.bean.WelfareBean
import sit.yihome.rankandroid.wrapper.ImageWapper

/**
 * Created by houyi on 2018/3/15.
 */
class WelfareAdater() : RecyclerView.Adapter<WelfareAdater.WelfareViewHold>() {
    lateinit var context: Context
    var welfares: List<WelfareBean>? = null

    fun setWelfareList(welfareList: List<WelfareBean>?) {
        welfares = welfareList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return welfares?.size ?: 0
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WelfareViewHold {
        context = parent.context
        val v = ImageView(parent.context)
        val layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        v.layoutParams = layoutParams
        return WelfareViewHold(v)
    }

    override fun onBindViewHolder(holder: WelfareViewHold, position: Int) {
        ImageWapper.loadImg(context, welfares!![position].url, holder.itemView as ImageView)
    }

    class WelfareViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)
}

