package sit.yihome.rankandroid.view.widget

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.annotation.Nullable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import sit.yihome.rankandroid.R

/**
 * Created by houyi on 2018/4/4.
 */
public class TabLayout(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : LinearLayout(context, attrs, defStyleAttr) {
    private var mTabCount: Int = 0
    private var tabLayoutParams: LayoutParams
    private var mCurrentPosition: Int = -1

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?) : this(context, null)

    init {
        orientation = HORIZONTAL
        tabLayoutParams = LayoutParams(0, MATCH_PARENT, 1.0f)

    }


    fun addTab(item: TabItem) {
        val view = item.buildView(context)
        item.position = mTabCount
        mTabCount++
        view.layoutParams = tabLayoutParams
        addView(view)
        view.setOnClickListener({ v ->
            val clickedItem = v.tag as TabItem
            if (clickedItem.position != mCurrentPosition) {
                unSelected(mCurrentPosition)
                mCurrentPosition = clickedItem.position
                clickedItem.selected(v)
            }
        })
    }

    private fun unSelected(currentPosition: Int) {
        if(currentPosition<0||currentPosition>=mTabCount){
            return
        }
        val item: TabItem = getChildAt(currentPosition).tag as TabItem
        item.unSelected()
    }

    fun initSelection(currentPosition: Int) {
        if(mCurrentPosition!=-1){
            return
        }
        mCurrentPosition = currentPosition
        val view = getChildAt(currentPosition)
        val item: TabItem = view.tag as TabItem
        item.selected(view)
    }


    class TabItem(@Nullable tabString: String?, @DrawableRes tabIcon: Int,clickListener: OnClickListener?) {
        private var tabString: String? = tabString
        private var tabIcon: Int = tabIcon
        private var clickListener: OnClickListener? = clickListener
        private var tabText: TextView? = null
        private var tabImg: ImageView? = null
        var position: Int = 0

        constructor(tabString: String,tabIcon: Int):this(tabString,tabIcon,null)

        fun buildView(context: Context): View {
            val view = LayoutInflater.from(context).inflate(R.layout.layout_tabitem, null)
            tabText = view.findViewById(R.id.txt_tabitem)
            tabText?.text = tabString
            tabImg = view.findViewById(R.id.img_tabitem)
            tabImg?.setImageResource(tabIcon)
            view.tag = this
            return view
        }

        fun unSelected() {
            tabText?.isSelected = false
            tabImg?.isSelected = false
        }

        fun selected(v: View) {
            tabImg?.isSelected = true
            tabText?.isSelected = true
            clickListener?.onClick(v)
        }

    }
}