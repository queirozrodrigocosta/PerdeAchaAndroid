package br.com.perdeacha.android.view.fragment.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.perdeacha.android.R
import br.com.perdeacha.android.databinding.FragmentSignUpBinding
import br.com.perdeacha.android.viewmodel.LoginViewModel

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.fragmentSignUp = this
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.result.observe(viewLifecycleOwner, Observer {
            if (it == viewModel.LOGIN_SUCCESS) {
                findNavController().navigateUp()
            } else {
                Toast.makeText(context, viewModel.msgError, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun signup(view: View) {
        viewModel.signup()
    }
}