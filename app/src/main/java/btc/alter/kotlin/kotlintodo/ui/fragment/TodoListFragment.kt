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
import org.jetbrains.anko.support.v4.find


/**
 * Created by theshade on 11/8/16.
 *
 * A fragment for displaying a list of Todos
 */
class TodoListFragment: Fragment(),AnkoLogger,TodoAdapter.TodoItemClickListener {
    var realm: Realm? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return TodoListUi().createUi(container!!)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        realm = Realm.getDefaultInstance()
    }

    override fun onResume() {
        super.onResume()
        var todos = realm?.where(Todo::class.java)?.findAll()
        val adapter = TodoAdapter(context, todos, true,this)
        var recyclerView = find<RecyclerView>(R.id.rw_tasks_list)
        recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        realm?.close()
    }
    override fun onLongTodoClick(view: View, todo: Todo) {
        if (todo.id != null) {
            var fragment: AddTodoFragment = AddTodoFragment.Companion.newInstance(todo.id as String)
            activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment, fragment)
                    .addToBackStack(fragment.javaClass.name)
                    .commit()
            fab.hide()
        }
    }
}