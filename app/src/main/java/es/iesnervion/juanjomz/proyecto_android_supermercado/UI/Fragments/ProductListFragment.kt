package es.iesnervion.juanjomz.proyecto_android_supermercado.UI.Fragments

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import es.iesnervion.gdebustamante.pmdmo_practica_supermercado.Adapters.ProductAdapter
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Bo.ProductBO
import es.iesnervion.juanjomz.proyecto_android_supermercado.R
import es.iesnervion.juanjomz.proyecto_android_supermercado.UI.ViewModels.MainActivityVM
import es.iesnervion.juanjomz.proyecto_android_supermercado.databinding.FragmentProductListBinding


@AndroidEntryPoint
class ProductListFragment : Fragment() {

    //List
    private lateinit var originalProductList: MutableList<ProductBO>//Lista original a guardar
    private lateinit var productAdapter: ProductAdapter

    //ViewModel
    private val viewModel: MainActivityVM by activityViewModels()

    //Binding
    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController


    private var categoriesList: MutableList<String> =
        mutableListOf()//List of categories to alert dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        //Primero se prepara las observaciones y luego se piden los datos al VM
        viewModel.productsList.observe(viewLifecycleOwner, this::onProductsLoaded)
        viewModel.loadProductsList()//Cambio el valor y llama al observador

        //LinearLM rcVw
        binding.rcVwProducts.layoutManager = LinearLayoutManager(view.context)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        //inflate menu
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_filter -> {
                launchFilterDialog()
                true
            }
            R.id.action_go_market -> {
                navController.navigate(R.id.action_productListFragment_to_shoppingCartFragment)
                true
            }
            else -> true
        }
    }

    private fun launchFilterDialog() {
        //TODO VER SI PODRIA HACER AQUÍ UN BINDING
        val dialogView = layoutInflater.inflate(R.layout.dialog_filter, null)

        val spinnerCategories = dialogView.findViewById<Spinner>(R.id.spinner_categories)
        val spinnerCategoriesAdapter: ArrayAdapter<String> =
            ArrayAdapter(context!!, android.R.layout.simple_spinner_dropdown_item, categoriesList)
        spinnerCategories.adapter = spinnerCategoriesAdapter

        val spinnerOrderBy = dialogView.findViewById<Spinner>(R.id.spinner_order_by)
        val spinnerOrderByAdapter: ArrayAdapter<String> = ArrayAdapter(
            context!!,
            android.R.layout.simple_spinner_dropdown_item,
            mutableListOf("Precio", "Nombre")
        )
        spinnerOrderBy.adapter = spinnerOrderByAdapter

        MaterialAlertDialogBuilder(context!!)
            .setTitle(R.string.dialog_filter_tittle)
            .setView(dialogView)
            .setCancelable(false)
            .setPositiveButton(R.string.dialog_filter_confirm) { dialogInterface, witch ->
                val categorySelected = spinnerCategories.selectedItem.toString()
                val orderFunctionSelected = spinnerOrderBy.selectedItem.toString()
                filtRecyclerView(orderFunctionSelected, categorySelected)
            }.setNegativeButton(R.string.dialog_filter_decline, null)
            .show()
    }

    private fun filtRecyclerView(orderFunctionSelected: String, categorySelected: String) {
        var filteredProductList = emptyList<ProductBO>()
        if (orderFunctionSelected == ("Precio")) {
            filteredProductList =
                originalProductList.filter { productBO -> productBO.categoria == categorySelected }
                    .sortedBy { it.precio }
        } else {
            filteredProductList =
                originalProductList.filter { productBO -> productBO.categoria == categorySelected }
                    .sortedBy { it.nombre }
        }
        updateListData(filteredProductList)
    }

    private fun onProductsLoaded(productsList: List<ProductBO>) {
        updateListData(productsList)

        //Rellena la lista de categorias
        productsList.forEach { product ->
            if (!categoriesList.contains(product.categoria)) {
                categoriesList.add(product.categoria)
            }
        }
        originalProductList = productsList as MutableList<ProductBO>
    }

    private fun onProductSelected(product: ProductBO) {
        viewModel.updateProductSelected(product)//TODO DEBERIA PASARSELO ACTUALIZANDO UN MUTABLELIVEDATA DEL VIEWMODEL O POR ARGUMENTOS?
        navController.navigate(R.id.action_productListFragment_to_productDetailsFragment)
    }

    private fun updateListData(productsList: List<ProductBO>) {
        productAdapter = ProductAdapter(productsList) { onProductSelected(it) }
        binding.rcVwProducts.adapter = productAdapter
    }
}