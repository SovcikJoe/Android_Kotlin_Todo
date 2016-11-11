package btc.alter.kotlin.kotlintodo.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import btc.alter.kotlin.kotlintodo.R
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import org.jetbrains.anko.info

/**
 * Created by theshade on 11/8/16.
 *
 * A base Activity that deals with boilerplate for all activities
 */
abstract class BaseActivity : AppCompatActivity(),AnkoLogger {
    protected abstract val layoutResource: Int
    val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        info("onCreate() before settingContent")
        info("$layoutResource")
        setContentView(layoutResource)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_launch,menu)
        return true // Dealt with
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        if (id == R.id.action_settings){
//            TODO: Implement
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}