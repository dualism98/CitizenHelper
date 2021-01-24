package com.dualism.citizenhelper.fragments.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.navigation.findNavController
import com.dualism.citizenhelper.R
import com.dualism.citizenhelper.activities.UserActivity
import com.dualism.citizenhelper.services.RestApiService
import com.dualism.citizenhelper.services.StorageService
import kotlinx.android.synthetic.main.fragment_login.view.*

class FragmentLogin : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_login, container, false)

        // Return the fragment view/layout
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val login = view.findViewById<View>(R.id.textedit_login) as EditText
        val password = view.findViewById<View>(R.id.textedit_password) as EditText
        val loadingBar = view.findViewById<View>(R.id.signin_loading) as ProgressBar

        view.button_login.setOnClickListener{ view ->
            loadingBar.visibility = View.VISIBLE
            val base = login.text.toString() + ':' + password.text.toString()
            val header = "Basic " + Base64.encodeToString(base.toByteArray(), Base64.NO_WRAP)

            val headerMap = mutableMapOf<String, String>()
            headerMap["Content-Type"] = "application/json"
            headerMap["Authorization"] = header

            val apiService = RestApiService()
            apiService.signInUser(headerMap) {
                if (it?.token != null) {
                    val storage = StorageService()
                    storage.setString(requireContext(),"token", it.token)

                    val intent = Intent(context, UserActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                } else {
                    loadingBar.visibility = View.GONE
                    val toast = Toast.makeText(context, "Неверный логин/пароль", Toast.LENGTH_SHORT)
                    toast.show()
                }
            }
        }

        view.button_register.setOnClickListener{ view ->
            view.findNavController().navigate(R.id.action_fragmentLogin_to_fragmentRegister)
        }

    }


}

