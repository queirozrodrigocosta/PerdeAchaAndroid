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
import br.com.perdeacha.android.databinding.FragmentForgetPasswordBinding
import br.com.perdeacha.android.viewmodel.LoginViewModel

class ForgetPasswordFragment : Fragment() {

    private lateinit var binding: FragmentForgetPasswordBinding

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
        binding = FragmentForgetPasswordBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.fragmentForgetPassword = this
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

    fun forgot(view: View) {
        viewModel.forgot()
    }
}