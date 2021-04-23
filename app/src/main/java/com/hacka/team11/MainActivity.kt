package com.hacka.team11

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.alarm.momentix.utils.setFullScreenForNotch
import com.alarm.momentix.utils.setFullScreenWithBtmNav
import com.google.gson.Gson
import com.hacka.team11.data.local.model.MatchModel
import com.hacka.team11.databinding.ActivityMainBinding
import com.hacka.team11.utils.inputStreamToString

import dagger.hilt.android.AndroidEntryPoint
import java.lang.reflect.Field


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val TAG = "RRR"


    private lateinit var binding: ActivityMainBinding
    private var match_list = ArrayList<MatchModel>()

    val navController: NavController by lazy {
        findNavController(R.id.nav_host_fragment_container)
    }

//    @Inject
//    lateinit var matchAdapter: MatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val fields: Array<Field> = R.raw::class.java.declaredFields

        for (i in 0 until fields.size) {
            val json = inputStreamToString(resources.openRawResource(fields[i].getInt(i)))
            val mmatchModel: MatchModel = Gson().fromJson(json, MatchModel::class.java)
            match_list.add(mmatchModel)
        }
    }

    override fun onStart() {
        super.onStart()
        setFullScreenWithBtmNav()
        setFullScreenForNotch()

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }


}