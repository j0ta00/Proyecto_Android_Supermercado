package es.iesnervion.juanjomz.proyecto_android_supermercado.UI.Fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import es.iesnervion.juanjomz.proyecto_android_supermercado.R
import es.iesnervion.juanjomz.proyecto_android_supermercado.UI.Utils.Utilidades
import es.iesnervion.juanjomz.proyecto_android_supermercado.UI.Utils.ValidatorDNI
import es.iesnervion.juanjomz.proyecto_android_supermercado.UI.ViewModels.MainActivityVM
import es.iesnervion.juanjomz.proyecto_android_supermercado.databinding.FragmentLoginBinding


@AndroidEntryPoint
class LoginFragment : Fragment(), View.OnClickListener {
    //ViewBinding
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    //ViewModel
    private val viewModel: MainActivityVM by activityViewModels()
    //NavController
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        Utilidades.loadImage(context!!, getString(R.string.img_url_supermarket), binding.imgLoginLogo)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        binding.btnLogin.setOnClickListener(this)
        binding.txtVwSignUp.setOnClickListener(this)
//        viewModel.isLoading.observe(viewLifecycleOwner, this::onLoadingChanged) TODO MEJORAR
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnLogin -> {

                val edUsernameTxt = binding.edUsername.text.toString()
                val edPasswordTxt = binding.edPassword.text.toString()

                if (validarCamposLogin(edUsernameTxt, edPasswordTxt)) {
                    if (viewModel.loginExists(edUsernameTxt, edPasswordTxt)) {
                        navController.navigate(R.id.action_loginFragment_to_productListFragment)
                    } else {
                        val ctx: Context? = this.activity
                        ctx?.let {
                            MaterialAlertDialogBuilder(it)
                                .setTitle(getString(R.string.dialog_tittle_login))
                                .setMessage(getString(R.string.dialog_message_login))
                                .setPositiveButton(resources.getString(R.string.accept_btn), null)
                                .show()
                        }
                    }
                }
            }
            R.id.txtVwSignUp -> {
                navController.navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
    }

//    private fun onLoadingChanged(isLoading : Boolean){ TODO MEJORAR
//        binding.loading.isVisible = isLoading
//    }

    private fun validarCamposLogin(edUsernameTxt: String, edPasswordTxt: String): Boolean {
        var esCorrecto = true

        //Username
        if (edUsernameTxt.isBlank()) {
            //TODO MEJORAR
            binding.edUsername.error = getString(R.string.required_ed)
            esCorrecto = false
        } else if (!ValidatorDNI.validar(edUsernameTxt)) {
            binding.edUsername.error = getString(R.string.dni_incorrect_ed)
            esCorrecto = false
        }
        //Password
        if (edPasswordTxt.isBlank()) {
            //TODO MEJORAR
            binding.edPassword.error = getString(R.string.required_ed)
            esCorrecto = false
        } else if (edPasswordTxt.length > 8) {
            esCorrecto = false
        }
        return esCorrecto;
    }
}