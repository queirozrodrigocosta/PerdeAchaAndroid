package br.com.perdeacha.android.view.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.perdeacha.android.R
import br.com.perdeacha.android.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_main, container, false)

        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.fragmentMain = this
        binding.lifecycleOwner = this

        return binding.root
    }

    fun chatboot(view: View) {
        findNavController().navigate(R.id.action_mainFragment_to_chatFragment)
    }
}