package sit.yihome.rankandroid.view.adapter


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import sit.yihome.rankandroid.R
import sit.yihome.rankandroid.viewmodel.bean.LiveUpdateWrapper
import sit.yihome.rankandroid.viewmodel.bean.WelfareBean
import sit.yihome.rankandroid.wrapper.ImageWrapper


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

    fun setWelfareList(welfareList: LiveUpdateWrapper<WelfareBean>?) {
        welfares = welfareList?.getTotalList()
        if(!welfareList?.isUpdate!!){
            notifyDataSetChanged()
        }else{
            notifyItemRangeInserted(welfareList.getInsertPosition(),welfareList.getInsertCount())
        }
    }

    override fun getItemCount(): Int {
        return welfares?.size ?: 0
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WelfareViewHold {
        context = parent.context
        val v = LayoutInflater.from(context).inflate(R.layout.welfare_item_view,null)
        return WelfareViewHold(v)
    }

    override fun onBindViewHolder(holder: WelfareViewHold, position: Int) {
        ImageWrapper.loadImgWithHeight(context, welfares!![position].url, holder.img)
    }

    class WelfareViewHold(itemView: View) : RecyclerView.ViewHolder(itemView){
        val img:ImageView = itemView.findViewById(R.id.img_welfare)
    }
}

