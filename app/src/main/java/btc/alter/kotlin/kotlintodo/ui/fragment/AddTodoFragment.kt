package btc.alter.kotlin.kotlintodo.ui.fragment

import android.os.Bundle
import android.os.SystemClock.sleep
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import btc.alter.kotlin.kotlintodo.R
import btc.alter.kotlin.kotlintodo.model.Category
import btc.alter.kotlin.kotlintodo.model.Todo
import io.realm.Realm
import io.realm.RealmResults
import org.jetbrains.anko.*
import java.util.*
import btc.alter.kotlin.kotlintodo.common.createUi
import btc.alter.kotlin.kotlintodo.ui.ui.AddTodoFragmentUi
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.AdapterView
import android.widget.Toast
import org.jetbrains.anko.support.v4.*


/**
 * Created by theshade on 11/8/16.
 *
 * Fragment for dealing with Adding and Editing Todos
 */


class AddTodoFragment: Fragment(),AnkoLogger {
    val TODO_ID_KEY: String = "todo_id_key"
    val realm by lazy{Realm.getDefaultInstance() }
    var todo: Todo? = null

    val todoDesc by lazy { find<EditText>(R.id.txt_todo_desc) }
    val categorySpinner by lazy{ find<Spinner>(R.id.spinner_todo_category) }
    val btn by lazy { find<Button>(R.id.btn_add_todo)}
    val btnDelete by lazy { find<Button>(R.id.btn_del_todo)}


    companion object {
        fun newInstance(id: String) =
                AddTodoFragment().withArguments("todo_id_key" to id)
        fun newInstance() =
                AddTodoFragment()
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        info("onCreateView")
        val result = Realm.getDefaultInstance().where(Category::class.java).findAllAsync()
        result.addChangeListener {
            info("Realm changeListener - Should be called only when result is change which is not the case: ISSUE 999 and 2946")
            if(isVisible && !isRemoving)updateCategorySpinner(result.toMutableList())
        }
        return AddTodoFragmentUi().createUi(container!!)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        info("onViewCreated()")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        info("onActivityCreated()")
        super.onActivityCreated(savedInstanceState)
        btn.onClick{
            createTodoFrom(todoDesc,categorySpinner) }
        if(arguments != null && arguments.containsKey(TODO_ID_KEY)) {
            val todoId = arguments.getString(TODO_ID_KEY)
            todo = realm.where(Todo::class.java).equalTo("id", todoId).findFirst()
            todoDesc.setText(todo?.description)
            btnDelete.visibility = View.VISIBLE
            btnDelete.onClick { deleteTodo(todo?.id) }
        }

    }

    private fun deleteTodo(id:String?) {
        activity.supportFragmentManager.popBackStack()
        realm.executeTransactionAsync{
            info("Deleting")
            sleep(3000)
            it      .where(Todo::class.java)
                    .equalTo("id",id)
                    .findFirst()
                    .deleteFromRealm()
            info("deleted")
        }
    }

    override fun onResume() {
        info("onResume")
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    private fun updateCategorySpinner(result: MutableList<Category>) {
        info("updateCategorySpinner")
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(ctx, R.layout.spinner_item, result.map{ it.category })
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        arrayAdapter.notifyDataSetChanged()
        categorySpinner.adapter = arrayAdapter
//        categorySpinner.isClickable = true
        categorySpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(adapter: AdapterView<*>, v: View,
                                        position: Int, id: Long) {
                info("onItemSelected")
                // On selecting a spinner item
                val selectedItem = adapter.getItemAtPosition(position).toString()
                adapter.setSelection(position)
                // Showing selected spinner item
                info("Thank you for selecting $selectedItem")
            }

            override fun onNothingSelected(arg0: AdapterView<*>) {
                // TODO Auto-generated method stub
                info("Noting Selected")
            }
        }
        info("updateCategorySpinner done")
    }



    private fun createTodoFrom(desc: EditText, categorySpinner: Spinner) {
        info("createTodoFrom()")
        activity.supportFragmentManager.popBackStack()
        realm.executeTransaction {
            // Perform on UI thread because of the TodoRealm object was created in UI thread
            val uuid = UUID.randomUUID().toString()
            // Either update the edited object or create a new one.
            val t = todo ?: it.createObject(Todo::class.java,uuid)
//            t.id = t.id ?: uuid
            t.description = desc.text.toString()

            t.category = it.where(Category::class.java).equalTo("category",categorySpinner.selectedItem.toString()).findFirst()
            // commiting transaction is automatic as with Python's with()
            // Go back to previous activity
        }

    }
}