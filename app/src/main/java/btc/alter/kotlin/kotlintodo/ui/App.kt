package btc.alter.kotlin.kotlintodo.ui

import android.app.Application
import io.realm.Realm

/**
 * Created by theshade on 11/8/16.
 */
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}