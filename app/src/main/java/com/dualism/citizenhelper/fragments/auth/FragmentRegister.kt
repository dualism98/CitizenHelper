package com.dualism.citizenhelper.fragments.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.dualism.citizenhelper.R
import com.dualism.citizenhelper.activities.UserActivity
import com.dualism.citizenhelper.models.RegUser
import com.dualism.citizenhelper.models.UserResponse
import com.dualism.citizenhelper.services.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_register.view.*


class FragmentRegister : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.fragment_register, container, false)
        // Return the fragment view/layout
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // save views as variables in this method
        // "view" is the one returned from onCreateView
        val firstName = view.findViewById<View>(R.id.textedit_register_firstName) as EditText
        val lastname = view.findViewById<View>(R.id.textedit_register_lastName) as EditText
        val middleName = view.findViewById<View>(R.id.textedit_register_middleName) as EditText
        val address = view.findViewById<View>(R.id.textedit_register_address) as EditText
        val email = view.findViewById<View>(R.id.textedit_register_email) as EditText
        val password = view.findViewById<View>(R.id.textedit_register_password) as EditText

        val loadingBar = view.findViewById<View>(R.id.register_loading) as ProgressBar

        view.back_button.setOnClickListener{ view ->
            view.findNavController().popBackStack()
        }
        view.button_attempt_register.setOnClickListener { view ->
            loadingBar.visibility = View.VISIBLE
            val FIO: String = lastname.text.toString() + " " + firstName.text.toString() + " " + middleName.text.toString()
            val apiService = RestApiService()
            val userInfo = RegUser(
                full_name = FIO,
                address = address.text.toString(),
                email = email.text.toString(),
                password = password.text.toString()
            )

            apiService.addUser(userInfo) { b: Boolean, s: String? ->
                if (s != null) {
                    Log.v("RES", s)
                }

                if (b) {
                    val res = Gson().fromJson(s, UserResponse::class.java)
                    val storage = StorageService()
                    storage.setString(requireContext(), "token", res.token)
                    storage.setString(requireContext(),"name", res.full_name)
                    storage.setString(requireContext(), "email", res.email)
                    storage.setString(requireContext(), "address", res.address)

                    val intent = Intent(context, UserActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                } else {
                    loadingBar.visibility = View.GONE
                    Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}