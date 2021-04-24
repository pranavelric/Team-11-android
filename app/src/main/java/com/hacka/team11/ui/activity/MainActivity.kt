package com.hacka.team11.ui.activity

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.alarm.momentix.utils.setFullScreenForNotch
import com.alarm.momentix.utils.setFullScreenWithBtmNav
import com.alarm.momentix.utils.toast
import com.facebook.AccessToken
import com.facebook.AccessTokenTracker
import com.facebook.CallbackManager
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.hacka.team11.R
import com.hacka.team11.data.local.model.MatchModel
import com.hacka.team11.databinding.ActivityMainBinding
import com.hacka.team11.utils.inputStreamToString
import dagger.hilt.android.AndroidEntryPoint
import java.lang.reflect.Field
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


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

    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firebaseAuthStateListener: FirebaseAuth.AuthStateListener
    lateinit var accessTokenTracker: AccessTokenTracker
    val callbackManager: CallbackManager = CallbackManager.Factory.create();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()




        firebaseAuthStateListener = FirebaseAuth.AuthStateListener {
            val user = it.currentUser
            if (user != null) {
                toast("User logged in")
            } else {
                toast("User is logged out")
            }
        }


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
        firebaseAuth.addAuthStateListener(firebaseAuthStateListener)

    }

    override fun onStop() {
        super.onStop()
        if (firebaseAuthStateListener != null)
            firebaseAuth.removeAuthStateListener(firebaseAuthStateListener)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }


    fun printHashKey(pContext: Context) {
        try {
            val info: PackageInfo = pContext.getPackageManager()
                .getPackageInfo(pContext.getPackageName(), PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val hashKey: String = String(Base64.encode(md.digest(), 0))
                Log.i(TAG, "printHashKey() Hash Key: $hashKey")
            }
        } catch (e: NoSuchAlgorithmException) {
            Log.e(TAG, "printHashKey()", e)
        } catch (e: Exception) {
            Log.e(TAG, "printHashKey()", e)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "onActivityResult:sdas")
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}