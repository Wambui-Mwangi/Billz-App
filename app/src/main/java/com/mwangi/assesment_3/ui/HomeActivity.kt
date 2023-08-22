package com.mwangi.assesment_3.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Constants
import com.mwangi.assesment_3.R
import com.mwangi.assesment_3.databinding.ActivityHomeBinding
import com.mwangi.assesment_3.utils.constants

class Home : AppCompatActivity() {
    lateinit var binding:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPrefs = getSharedPreferences(constants.PREFS, Context.MODE_PRIVATE)
        val userId = sharedPrefs.getString(constants.USER_ID, constants.EMPTY_STRING)?:constants.EMPTY_STRING

        binding.btLogout.setOnClickListener {
            val editor = sharedPrefs.edit()
            editor.clear()
            editor.apply()
            startActivity(Intent(this, LogIn::class.java))
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        setUpBottomNav()
    }

    fun setUpBottomNav(){
        binding.bnvHome.setOnItemSelectedListener { menuItem->
            when(menuItem.itemId){
                R.id.summary ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, SummaryFragment()).commit()
                    true
                }

                R.id.upcoming ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, UpcomingBillsFragment()).commit()
                    true
                }

                R.id.paid ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, PaidBillsFragment()).commit()
                    true
                }

                R.id.settings ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, SettingsFragment()).commit()
                    true
                }

                else -> false

            }
        }
    }
}
