package btc.alter.kotlin.kotlintodo.ui.ui

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.ViewGroup
import btc.alter.kotlin.kotlintodo.R
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView

/**
 * Created by theshade on 11/8/16.
 *
 * Ui for List of Todos with recyclerView
 */
class TodoListUi() :AnkoComponent<ViewGroup>{
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){
            frameLayout() {
                lparams(width= matchParent,height = matchParent)
                recyclerView() {
                    id = R.id.rw_tasks_list
                    lparams(width= matchParent,height = matchParent)
                    layoutManager = LinearLayoutManager(ctx)
//                    adapter = TodoAdapter
                }
            }
        }
    }
}