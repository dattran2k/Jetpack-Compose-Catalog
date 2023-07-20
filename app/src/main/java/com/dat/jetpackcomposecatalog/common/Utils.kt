package com.dat.jetpackcomposecatalog.common

import android.content.Context
import android.content.Intent
import android.net.Uri

object Utils {
    fun openUrl(context: Context, link: String) {
        try {
            var url = link
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://$url"
            }
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(browserIntent)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}