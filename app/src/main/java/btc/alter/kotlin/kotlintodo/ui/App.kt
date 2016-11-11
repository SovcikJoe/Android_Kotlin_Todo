package btc.alter.kotlin.kotlintodo.ui

import android.app.Application
import io.realm.Realm
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

/**
 * Created by theshade on 11/8/16.
 *
 * To init Realm for the whole App
 */
class App : Application(),AnkoLogger {
    override fun onCreate() {
        super.onCreate()
        info("app onCreate()")
        Realm.init(this)
    }
}