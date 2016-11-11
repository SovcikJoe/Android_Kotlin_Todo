package btc.alter.kotlin.kotlintodo.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import btc.alter.kotlin.kotlintodo.R
import btc.alter.kotlin.kotlintodo.adapters.TodoAdapter
import btc.alter.kotlin.kotlintodo.common.createUi
import btc.alter.kotlin.kotlintodo.model.Todo
import btc.alter.kotlin.kotlintodo.ui.ui.TodoListUi
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_launch.fab
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.support.v4.find


/**
 * Created by theshade on 11/8/16.
 *
 * A fragment for displaying a list of Todos
 */
class TodoListFragment: Fragment(),AnkoLogger,TodoAdapter.TodoItemClickListener {
    var realm: Realm? = null
    val recyclerView by lazy{find<RecyclerView>(R.id.rw_tasks_list)}

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        info("onCreateView()")
        realm = Realm.getDefaultInstance()
        if (container != null) {
            return TodoListUi().createUi(container)
        }
        info("Container is null")
        return null
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        info("onActivityCreated()")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onResume() {
        info("OnResume()")
        super.onResume()
        val todos = realm?.where(Todo::class.java)?.findAll()
        if (recyclerView.adapter == null){
            info("recyclerVie.adapter is null")
            val adapter = TodoAdapter(context, todos, true,this)
            recyclerView.adapter = adapter
        }
        recyclerView.adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        info("onDestroyView")
        super.onDestroyView()
        realm?.close()
    }
    
    override fun onLongTodoClick(view: View, todo: Todo) {
        info("onLongTodoClick")
        if (todo.id != null) {
            val fragment: AddTodoFragment = AddTodoFragment.Companion.newInstance(todo.id as String)
            activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment, fragment)
                    .addToBackStack(fragment.javaClass.name)
                    .commit()
            activity.fab?.hide()
        }
    }
}