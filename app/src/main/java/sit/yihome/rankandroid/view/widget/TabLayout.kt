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
    private var selectListener:((Int,Int,Boolean)->Unit)?=null

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
            selectItem(clickedItem)
        })
    }

    fun addTab(name:String,@DrawableRes tabIcon:Int) {
        addTab(TabItem(name,tabIcon))
    }

    fun initSelection(currentPosition: Int) {
        if(mCurrentPosition!=-1){
            return
        }
        mCurrentPosition = currentPosition
        val view = getChildAt(currentPosition)
        val item: TabItem = view.tag as TabItem
        selectItem(item)
    }

    private fun selectItem(clickedItem: TabItem) {
        var isChanged = false
        var lastPosition = 0
        if (clickedItem.position != mCurrentPosition) {
            isChanged = true
            lastPosition = mCurrentPosition
            unSelected(mCurrentPosition)
            clickedItem.selected()
            mCurrentPosition = clickedItem.position
        }
        selectListener?.invoke(mCurrentPosition, lastPosition, isChanged)
    }

    private fun unSelected(currentPosition: Int) {
        if(currentPosition<0||currentPosition>=mTabCount){
            return
        }
        val item: TabItem = getChildAt(currentPosition).tag as TabItem
        item.unSelected()
    }

    fun setSelectChangeListener(listener:(currentPosition:Int,lastPosition:Int,isChanged:Boolean)->Unit) {
       selectListener = listener
    }

    class TabItem(private var tabString: String?, @DrawableRes private var tabIcon: Int) {
        private lateinit var tabText: TextView
        private lateinit var tabImg: ImageView
        var position: Int = 0

        fun buildView(context: Context): View {
            val view = LayoutInflater.from(context).inflate(R.layout.layout_tabitem, null)
            tabText = view.findViewById(R.id.txt_tabitem)
            tabText.text = tabString
            tabImg = view.findViewById(R.id.img_tabitem)
            tabImg.setImageResource(tabIcon)
            view.tag = this
            return view
        }

        fun unSelected() {
            tabText.isSelected = false
            tabImg.isSelected = false
        }

        fun selected() {
            tabImg.isSelected = true
            tabText.isSelected = true
        }

    }
}