package com.anshul.datahandling.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.anshul.datahandling.LOG_TAG
import com.anshul.datahandling.R
import com.anshul.datahandling.databinding.FragmentDetailBinding
import com.anshul.datahandling.ui.shared.SharedViewModel
import javax.security.auth.login.LoginException


class DetailFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var navController: NavController
    private lateinit var viewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        setHasOptionsMenu(true)
        navController = Navigation.findNavController(
            requireActivity(), R.id.nav_host
        )

        viewModel = ViewModelProviders.of(requireActivity()).get(SharedViewModel::class.java)

//        viewModel.selectedMonster.observe(viewLifecycleOwner, Observer {
//            Log.i(LOG_TAG,"Selected Monster on Details Page: ${it.monsterName}")
//        })

        val binding = FragmentDetailBinding.inflate(
            inflater,
            container,
            false
        )

        binding.lifecycleOwner = this
        binding.viewModel = viewModel



        return  binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item?.itemId == android.R.id.home){
            navController.navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }
}