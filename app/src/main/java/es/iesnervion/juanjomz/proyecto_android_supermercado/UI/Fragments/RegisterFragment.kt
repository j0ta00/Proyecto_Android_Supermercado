package es.iesnervion.juanjomz.proyecto_android_supermercado.UI.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Bo.UserBO
import es.iesnervion.juanjomz.proyecto_android_supermercado.R
import es.iesnervion.juanjomz.proyecto_android_supermercado.UI.Utils.Utilidades
import es.iesnervion.juanjomz.proyecto_android_supermercado.UI.Utils.ValidatorDNI
import es.iesnervion.juanjomz.proyecto_android_supermercado.UI.ViewModels.MainActivityVM
import es.iesnervion.juanjomz.proyecto_android_supermercado.databinding.FragmentRegisterBinding


@AndroidEntryPoint
class RegisterFragment : Fragment(), View.OnClickListener {
    //ViewBinding
    private var _binding: FragmentRegisterBinding? = null
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
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        Utilidades.loadImage(
            context!!,
            getString(R.string.img_url_supermarket),
            binding.imgRegisterLogo
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        binding.btnRegister.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        val edUsernameTxt = binding.edUsername.text.toString()
        val edPasswordTxt = binding.edPassword.text.toString()
        val edEmailTxt = binding.edEmail.text.toString()

        if (validarCamposRegister(edUsernameTxt, edPasswordTxt, edEmailTxt)) {
            if (!viewModel.userExists(edUsernameTxt)) {
                viewModel.insertUser(
                    UserBO(
                        edUsernameTxt,
                        edPasswordTxt,
                        binding.edName.text.toString(),
                        binding.edFirstName.text.toString(),
                        binding.edLastName.text.toString(),
                        binding.edEmail.text.toString(),
                        binding.edPhone.text.toString()
                    )
                )
                navController.navigate(R.id.action_registerFragment_to_productListFragment)
            } else {
                launchAlertDialogUserExists()
            }
        }
    }

    fun launchAlertDialogUserExists(){
        val ctx: Context? = this.activity
        ctx?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(getString(R.string.dialog_tittle_register))
                .setMessage(getString(R.string.dialog_message_register))
                .setPositiveButton(resources.getString(R.string.accept_btn), null)
                .show()
        }
    }

    private fun validarCamposRegister(
        edUsernameTxt: String,
        edPasswordTxt: String,
        edEmail: String
    ): Boolean {
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

        //Email
        if (edEmail.isBlank()) {
            binding.edEmail.error = getString(R.string.required_ed)
            esCorrecto = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(edEmail).matches()) {
            binding.edEmail.error = getString(R.string.invalid_email_ed)
            esCorrecto = false
        }

        return esCorrecto;
    }
}