package btc.alter.kotlin.kotlintodo.ui.activity

import android.app.FragmentManager
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar

import btc.alter.kotlin.kotlintodo.R
import btc.alter.kotlin.kotlintodo.ui.fragment.AddTodoFragment

class LaunchActivity : BaseActivity() {
    override val layoutResource: Int
        get() = R.layout.activity_launch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show()
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
                if (backstackCount == 0){
//                    If in the first view
                    fab.show()
                }
        })

    }
}