package es.iesnervion.juanjomz.proyecto_android_supermercado.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Bo.ProductBO
import es.iesnervion.juanjomz.proyecto_android_supermercado.R
import es.iesnervion.juanjomz.proyecto_android_supermercado.UI.Utils.Utilidades
import es.iesnervion.juanjomz.proyecto_android_supermercado.databinding.LisItemProductBinding

class ProductAdapter(
    private var productList: List<ProductBO>,
    private val listener: (ProductBO) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private lateinit var context : Context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = LisItemProductBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.lis_item_product, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]
        with(holder) {
            binding.tvNombreProducto.text = product.nombre
            binding.tvPrecio.text = product.precio.toString().plus("€")
            Utilidades.loadImage(context,product.urlImagen, binding.imgVwPhoto )
            itemView.setOnClickListener { listener(product) }
        }
    }

    override fun getItemCount(): Int = productList.size

    fun setData(productsList: List<ProductBO>) {
        this.productList = productsList
        notifyDataSetChanged()
    }
}