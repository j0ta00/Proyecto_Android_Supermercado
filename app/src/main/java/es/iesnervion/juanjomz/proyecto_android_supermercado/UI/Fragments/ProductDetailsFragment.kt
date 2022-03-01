package es.iesnervion.juanjomz.proyecto_android_supermercado.UI.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import dagger.hilt.android.AndroidEntryPoint
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Bo.ProductBO
import es.iesnervion.juanjomz.proyecto_android_supermercado.R
import es.iesnervion.juanjomz.proyecto_android_supermercado.UI.Utils.Utilidades
import es.iesnervion.juanjomz.proyecto_android_supermercado.UI.ViewModels.MainActivityVM
import es.iesnervion.juanjomz.proyecto_android_supermercado.databinding.FragmentProductDetailsBinding


@AndroidEntryPoint
class ProductDetailsFragment : Fragment(), View.OnClickListener {

    //ViewModel
    private val viewModel: MainActivityVM by activityViewModels()

    //Binding
    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.productSelectedDetails.observe(viewLifecycleOwner, this::onProductLoaded)
        binding.btnAddProduct.setOnClickListener(this)
        binding.btnGoShoppingCart.setOnClickListener(this)

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun onProductLoaded(productBO: ProductBO) {
        Utilidades.loadImage(requireContext(), productBO.urlImagen, binding.imgProductDetailsProduct)
        binding.txtVwProductNameProductDetails.text = productBO.nombre
        binding.txtVwProductPriceProductDetails.text = productBO.precio.toString().plus("€")
        binding.txtVwProductCategoryProductDetails.text = productBO.categoria
    }

    override fun onClick(v: View?) {
        when (v!!.id){
            R.id.btnGoShoppingCart -> navController.navigate(R.id.action_productDetailsFragment_to_shoppingCartFragment)
            R.id.btnAddProduct -> viewModel.insertProductToCart()
        }
    }
}