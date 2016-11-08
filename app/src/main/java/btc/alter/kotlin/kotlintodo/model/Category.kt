package btc.alter.kotlin.kotlintodo.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by theshade on 11/8/16.
 */
open class Category(
        @PrimaryKey open var id:String = "0",
        open var category:String = "default"
): RealmObject(){
}