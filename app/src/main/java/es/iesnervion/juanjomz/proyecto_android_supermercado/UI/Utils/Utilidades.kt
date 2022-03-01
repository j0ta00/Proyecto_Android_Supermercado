package es.iesnervion.juanjomz.proyecto_android_supermercado.UI.Utils

import android.content.Context
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class Utilidades {
    companion object {
        fun loadImage(context: Context, url: String, imageView: ImageView) {
            Glide.with(context)
                .load(url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
        }
    }
}