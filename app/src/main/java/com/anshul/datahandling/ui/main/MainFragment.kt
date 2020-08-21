package com.anshul.datahandling.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.anshul.datahandling.LOG_TAG
import com.anshul.datahandling.R
import com.anshul.datahandling.data.Monster
import com.anshul.datahandling.ui.shared.SharedViewModel
import java.lang.StringBuilder

class MainFragment : Fragment(),
    MainRecyclerAdapter.MonsterItemListener{

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: SharedViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeLayout: SwipeRefreshLayout
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }

        val view =  inflater.inflate(R.layout.main_fragment, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)

        navController = Navigation.findNavController(
            requireActivity(), R.id.nav_host
        )

        swipeLayout = view.findViewById(R.id.swipe_layout)

        swipeLayout.setOnRefreshListener {
            viewModel.refreshData()
        }



        viewModel = ViewModelProviders.of(requireActivity()).get(SharedViewModel::class.java)

        viewModel.monsterData.observe(viewLifecycleOwner, Observer {
            val adapter = MainRecyclerAdapter(requireContext(),it, this)
            recyclerView.adapter = adapter
            swipeLayout.isRefreshing = false

        })

        return  view
    }

    override fun onMonserItemClick(monster: Monster) {
        Log.i(LOG_TAG,"Selected Monster: $monster")
        viewModel.selectedMonster.value = monster
        navController.navigate(R.id.action_nav_detail)
    }

}