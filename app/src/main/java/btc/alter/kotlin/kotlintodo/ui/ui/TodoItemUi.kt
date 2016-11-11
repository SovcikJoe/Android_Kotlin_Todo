package btc.alter.kotlin.kotlintodo.ui.ui

import android.support.v7.content.res.AppCompatResources
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import btc.alter.kotlin.kotlintodo.R
import org.jetbrains.anko.*

/**
 * Created by theshade on 11/8/16.
 *
 * UI for the TodoItem for TodoListAdapter with checkedTextView
 */
class TodoItemUi:AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){
            linearLayout{
                lparams(width= matchParent, height = dip(48))
                orientation = LinearLayout.HORIZONTAL
                horizontalPadding = dip(16)
                checkedTextView {
                    lparams {
                        width = matchParent
                        gravity = Gravity.CENTER + Gravity.END
                        textAlignment = TextView.TEXT_ALIGNMENT_VIEW_END
                    }
                    id = R.id.txt_item_todo_desc
                    singleLine = true
                    ellipsize = TextUtils.TruncateAt.END
                    textSize = 24f
                    checkMarkDrawable = AppCompatResources.getDrawable(context, R.drawable.abc_btn_check_material)
                }
            }

        }
    }
}