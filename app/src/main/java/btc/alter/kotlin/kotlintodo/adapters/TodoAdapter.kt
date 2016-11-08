package btc.alter.kotlin.kotlintodo.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import btc.alter.kotlin.kotlintodo.R
import btc.alter.kotlin.kotlintodo.common.createUi
import btc.alter.kotlin.kotlintodo.model.Todo
import btc.alter.kotlin.kotlintodo.ui.ui.TodoItemUi
import io.realm.RealmRecyclerViewAdapter
import io.realm.RealmResults
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import org.jetbrains.anko.info

/**
 * Created by theshade on 11/8/16.
 */
open class TodoAdapter(context: Context,
                       results: RealmResults<Todo>?,
                       autoUpdate: Boolean,
                       private val clickListener: TodoItemClickListener
): RealmRecyclerViewAdapter<Todo, TodoAdapter.TodoAdapterViewholder>(context,results,autoUpdate) {
    override fun onBindViewHolder(holder: TodoAdapterViewholder?, position: Int) {
        var todo = data?.get(position)
        holder?.bind(todo!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TodoAdapterViewholder {
        return TodoAdapterViewholder(TodoItemUi().createUi(parent!!),clickListener)
    }


    class TodoAdapterViewholder(itemView: View, private val clickListener: TodoItemClickListener): RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener, AnkoLogger {
        var todoItemView: CheckedTextView = itemView.find(R.id.txt_item_todo_desc)
        var todo:Todo? = null

        init{
            info("Viewholder init()")
            itemView.setOnClickListener(this)
        }

        fun bind(todo:Todo){
            info("ViewHolder.bind()")
            this.todo = todo
            todoItemView.text = todo.description
            todoItemView.isChecked = todo.isDone
        }

        override fun onLongClick(view: View): Boolean {
            info("onLongClick Viewholder")
//            Is this really NullSafe?
            clickListener.onLongTodoClick(view,this.todo!!)
            return true
        }
        override fun onClick(view: View?) {
            todoItemView.isChecked = !todoItemView.isChecked
            todo?.isDone = todoItemView.isChecked
        }
    }

    interface TodoItemClickListener{
        fun onLongTodoClick(view: View, todo:Todo)
    }
}