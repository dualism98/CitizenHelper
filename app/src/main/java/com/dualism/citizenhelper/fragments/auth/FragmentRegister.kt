package com.dualism.citizenhelper.fragments.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.dualism.citizenhelper.R
import com.dualism.citizenhelper.activities.UserActivity
import com.dualism.citizenhelper.models.regUser
import com.dualism.citizenhelper.services.*
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

        view.back_button.setOnClickListener{ view ->
            view.findNavController().popBackStack()
        }
        view.button_attempt_register.setOnClickListener { view ->

            val FIO: String = lastname.text.toString() + " " + firstName.text.toString() + " " + middleName.text.toString()
            val apiService = RestApiService()
            val userInfo = regUser(
                full_name = FIO,
                address = address.text.toString(),
                email = email.text.toString(),
                password = password.text.toString()
            )

            apiService.addUser(userInfo) {
                if (it?.token != null) {
                    print("REGISTERED")
                    val userData = requireContext().getSharedPreferences(
                        "user_storage",
                        Context.MODE_PRIVATE
                    )
                    val editor = userData.edit()
                    editor.putString("token", it.token)
                    editor.apply()

                    val intent = Intent(context, UserActivity::class.java)
                    startActivity(intent)
                } else {
                    val toast = Toast.makeText(
                        context,
                            "",
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                }
            }
        }

    }

}