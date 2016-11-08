package btc.alter.kotlin.kotlintodo.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import btc.alter.kotlin.kotlintodo.R
import btc.alter.kotlin.kotlintodo.model.Category
import btc.alter.kotlin.kotlintodo.model.Todo
import io.realm.Realm
import io.realm.RealmResults
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.withArguments
import java.util.*
import btc.alter.kotlin.kotlintodo.common.createUi
import btc.alter.kotlin.kotlintodo.ui.ui.AddTodoFragmentUi

/**
 * Created by theshade on 11/8/16.
 *
 * Fragment for dealing with Adding and Editing Todos
 */


class AddTodoFragment: Fragment(),AnkoLogger {
    val TODO_ID_KEY: String = "todo_id_key"
    val realm: Realm = Realm.getDefaultInstance()
    var todo: Todo? = null

    var todoDesc: EditText? = null
    var categorySpinner:Spinner? = null

    companion object {
        fun newInstance(id: String) =
                AddTodoFragment().withArguments("todo_id_key" to id)
        fun newInstance() =
                AddTodoFragment()
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        info("onCreateView")

        doAsync {
            info("Starting AsyncTask")
            val result = realm.where(Category::class.java).findAll()
            uiThread {
                info("postExecute AsyncTask")
                updateCategorySpinner(result)
            }
        }
        return AddTodoFragmentUi().createUi(container!!)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        todoDesc = find<EditText>(R.id.txt_todo_desc)
        categorySpinner = find<Spinner>(R.id.spinner_todo_category)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(arguments != null && arguments.containsKey(TODO_ID_KEY)) {
            val todoId = arguments.getString(TODO_ID_KEY)
            todo = realm.where(Todo::class.java).equalTo("id", todoId).findFirst()
            todoDesc?.setText(todo?.description)
//            categorySpinner?.top =
        }
    }

    override fun onResume() {
        super.onResume()
        var btn = find<Button>(R.id.btn_add_todo)
        btn.onClick{
            createTodoFrom(todoDesc!!,categorySpinner!!) }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    private fun updateCategorySpinner(result: RealmResults<Category>) {
        info("updateCategorySpinner")
        if (categorySpinner != null) {
            val arrayAdapter: ArrayAdapter<Category> = ArrayAdapter(ctx, categorySpinner!!.id, result.toMutableList())
//        arrayAdapter.setDropDownViewResource(categorySpinner.id)
            arrayAdapter.notifyDataSetChanged()
            categorySpinner?.adapter = arrayAdapter
        }
    }



    private fun createTodoFrom(desc: EditText, categorySpinner: Spinner) {
        info("createTodoFrom()")
        realm.use {
            // Either update the edited object or create a new one.
            val t = todo ?: realm.createObject(Todo::class.java)
            t.id = todo?.id ?: UUID.randomUUID().toString()
            t.description = t.description ?: desc.text.toString()
            t.category = categorySpinner.selectedItem as Category

            // Go back to previous activity
            activity.supportFragmentManager.popBackStack()
        }

    }
}