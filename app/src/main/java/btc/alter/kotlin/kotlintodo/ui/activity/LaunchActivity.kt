package btc.alter.kotlin.kotlintodo.ui.activity

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar

import btc.alter.kotlin.kotlintodo.R
import btc.alter.kotlin.kotlintodo.model.Category
import btc.alter.kotlin.kotlintodo.ui.fragment.AddTodoFragment
import btc.alter.kotlin.kotlintodo.ui.fragment.TodoListFragment
import io.realm.Realm
import org.jetbrains.anko.info

class LaunchActivity : BaseActivity() {
    override val layoutResource: Int
        get() = R.layout.activity_launch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        info("onCreate()")
        val todoFragment = TodoListFragment()
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment,todoFragment)
                .commit()

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            val fragment: AddTodoFragment = AddTodoFragment.Companion.newInstance()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment, fragment)
                    .addToBackStack(fragment.javaClass.name)
                    .commit()
            fab.hide()
        }

        supportFragmentManager.
                addOnBackStackChangedListener(android.support.v4.app.FragmentManager.OnBackStackChangedListener { ->
                    val backstackCount = supportFragmentManager.backStackEntryCount
                    info("Backstackcount is $backstackCount")
                    if (backstackCount == 0){
//                    If in the first view
                    fab.show()
                }
        })

    }

    override fun onResume() {
        super.onResume()
        val realm = Realm.getDefaultInstance()
        realm.executeTransactionAsync{
//            val list = listOf("kotlin","job","home")
//            for ((index,item) in list.withIndex()) {
//                info("creating object")
//                val cat = it.createObject(Category::class.java,index.toString())
//                cat.category = item
//            }
            info("Getting objects")
            for (cat in it.where(Category::class.java).findAll()){
                info("category object $cat")
            }
        }
    }
}