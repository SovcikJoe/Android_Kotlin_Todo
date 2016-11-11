package btc.alter.kotlin.kotlintodo.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by theshade on 11/8/16.
 *
 * Todd-Data-Object
 */
open class Todo: RealmObject() {
    @PrimaryKey open var id:String? = null
    open var description:String? = null
    open var category:Category? = null
    open var isDone:Boolean = false

}