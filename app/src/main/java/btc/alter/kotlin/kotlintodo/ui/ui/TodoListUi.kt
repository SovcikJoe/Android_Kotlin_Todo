package btc.alter.kotlin.kotlintodo.ui.ui

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import btc.alter.kotlin.kotlintodo.R
import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView

/**
 * Created by theshade on 11/8/16.
 *
 * Ui for List of Todos with recyclerView
 */
//inline fun ViewManager.addView(viewFactory: (Context) -> T) = ankoView(viewFactory, 0, {})
inline fun<T: View> ViewManager.addView(viewFactory: (Context) -> T, init: T.() -> Unit) = ankoView(viewFactory, 0, init)

inline fun Context.addView(viewFactory: (Context) -> View) = ankoView(viewFactory, 0, {})
inline fun<T: View> Context.addView(viewFactory: (Context) -> T, init: T.() -> Unit) = ankoView(viewFactory, 0, init)

class TodoListUi() :AnkoComponent<ViewGroup>{
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){
            frameLayout() {
                lparams(width= matchParent,height = matchParent)
                recyclerView {
                    id = R.id.rw_tasks_list
                    lparams(width= matchParent,height = matchParent)
                    layoutManager = LinearLayoutManager(ctx)
//                    adapter = TodoAdapter
                }
            }
        }
    }
}