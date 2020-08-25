package com.anshul.datahandling.ui.Splash

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.anshul.datahandling.R

const val PERMISSION_REQUEST_CODE = 1001
class splashFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(
            ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED){
        displayMainFragment()
        }else{
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE)
        }
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_CODE){
            if (grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED){
                displayMainFragment()
            }else{
                Toast.makeText(requireContext(),"Permissions Required",Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun displayMainFragment(){
        val navController = Navigation.findNavController(
            requireActivity(), R.id.nav_host
        )
        navController.navigate(
            R.id.action_nav_main,
            null,
            NavOptions.Builder()
                .setPopUpTo(R.id.splashFragment,true)
                .build()
        )

    }



}