package br.com.example.sendmoney.view.component

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import br.com.example.sendmoney.R

class DividerItemDecoration(private val context: Context) : RecyclerView.ItemDecoration() {
    private var mDivider: Drawable? = null

    init {
        mDivider = context.getDrawable(R.drawable.item_contacts_line_divider)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + mDivider!!.intrinsicHeight
            mDivider?.setBounds(left, top, right, bottom)
            mDivider?.draw(c)
        }
    }

}