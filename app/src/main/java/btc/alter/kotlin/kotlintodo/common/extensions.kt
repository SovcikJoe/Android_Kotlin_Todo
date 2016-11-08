package btc.alter.kotlin.kotlintodo.common

import android.support.design.widget.FloatingActionButton
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext

/**
 * Created by theshade on 11/8/16.
 *
 *
 */

/*
* Creates a View from an AnkoView
*
* @param: container View of Fragment
* @return: AnkoView
* */
fun AnkoComponent<ViewGroup>.createUi(container:ViewGroup): View {
    return this.createView(AnkoContext.Companion.create(container.context,container))
}

fun FloatingActionButton.hideFab() = this.hide()