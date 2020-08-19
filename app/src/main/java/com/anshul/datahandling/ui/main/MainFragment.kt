package com.anshul.datahandling.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.anshul.datahandling.LOG_TAG
import com.anshul.datahandling.R
import com.anshul.datahandling.data.Monster
import kotlinx.android.synthetic.main.main_fragment.*
import java.lang.StringBuilder

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val monsterNames = StringBuilder()

        viewModel.monsterData.observe(viewLifecycleOwner, Observer {
            for (monster in it) {

                //Log.e(LOG_TAG, monster.toString() )
                monsterNames.append("${monster.monsterName} (\$${monster.price}) \n")
            }

            message.text = monsterNames
        })

        return inflater.inflate(R.layout.main_fragment, container, false)
    }

}