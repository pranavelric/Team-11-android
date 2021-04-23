package com.hacka.team11

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.hacka.team11.adapters.MatchAdapter
import com.hacka.team11.data.local.model.MatchModel
import com.hacka.team11.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import java.io.InputStream
import java.lang.reflect.Field
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val TAG = "RRR"


    private lateinit var binding: ActivityMainBinding
    private var match_list = ArrayList<MatchModel>()

//    @Inject
//    lateinit var matchAdapter: MatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        val fields: Array<Field> =    R.raw::class.java.declaredFields


        for(i in 0 until fields.size){

            val json = inputStreamToString(resources.openRawResource(fields[i].getInt(i)))
            val mmatchModel: MatchModel = Gson().fromJson(json, MatchModel::class.java)
            match_list.add(mmatchModel)

        }


    }


    private fun inputStreamToString(inputStream: InputStream): String {
        return try {
            val bytes = ByteArray(inputStream.available())
            inputStream.read(bytes, 0, bytes.size)
            String(bytes)
        } catch (e: IOException) {
            null
        }.toString()
    }


}