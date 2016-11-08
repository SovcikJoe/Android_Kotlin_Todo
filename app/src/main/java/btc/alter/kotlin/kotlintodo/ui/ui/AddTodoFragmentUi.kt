package btc.alter.kotlin.kotlintodo.ui.ui

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import btc.alter.kotlin.kotlintodo.R
import btc.alter.kotlin.kotlintodo.ui.fragment.AddTodoFragment
import org.jetbrains.anko.*

/**
 * Created by theshade on 11/8/16.
 *
 * Fragment for adding and ediing Todos
 */
class AddTodoFragmentUi:AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        val editTextTheme = R.style.Widget_AppCompat_EditText

        return with(ui){
            verticalLayout {
                verticalPadding =dip(15)
                gravity = Gravity.CENTER
                editText(editTextTheme){
                    id = R.id.txt_todo_desc
                    hintResource = R.string.txt_todo_desc_hint
                    width = matchParent

                }
                spinner(R.style.Widget_AppCompat_Spinner){
                    id= R.id.spinner_todo_category
                }
                button{
                    id = R.id.btn_add_todo
                    textResource = R.string.btn_add_todo
                    width = matchParent
//                    onClick { view -> AddTodoFragment.createTodoFrom(description, categorySpinner) }
                }

            }.applyRecursively {view -> when(view){
                is EditText -> view.textSize = 20f
                is Button -> view.textSize = 30f
            }
            }
        }
    }
}