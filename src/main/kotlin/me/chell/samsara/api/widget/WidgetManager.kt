package me.chell.samsara.api.widget

import me.chell.samsara.api.Loadable
import me.chell.samsara.api.feature.Widget
import me.chell.samsara.impl.widget.Watermark

object WidgetManager: Loadable {

    val widgets = mutableListOf<Widget>()

    fun <T> getWidget(name: String): T? = widgets.firstOrNull { it.name.equals(name, true) } as T?

    override fun load() {
        widgets.add(Watermark())

        for(w in widgets) w.load()
    }

    override fun unload() {
        for(w in widgets) w.unload()
        widgets.clear()
    }
}