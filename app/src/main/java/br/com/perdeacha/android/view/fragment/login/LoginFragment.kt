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
import br.com.perdeacha.android.databinding.FragmentLoginBinding
import br.com.perdeacha.android.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

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
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.fragmentLogin = this
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.result.observe(viewLifecycleOwner, Observer {
            if (it == viewModel.LOGIN_SUCCESS) {
                findNavController().navigate(R.id.action_loginFragment_to_mainActivity)
                activity?.finish()
            } else {
                Toast.makeText(context, viewModel.msgError, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.result.removeObserver(Observer{})
    }

    fun login(view: View){
        viewModel.login()
    }

    fun signup(view: View) {
        findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
    }

    fun forgot(view: View) {
        findNavController().navigate(R.id.action_loginFragment_to_forgetPasswordFragment)
    }
}