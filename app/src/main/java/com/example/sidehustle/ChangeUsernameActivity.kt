package com.example.sidehustle

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.sidehustle.databinding.ActivityChangeUsernameBinding

class ChangeUsernameActivity : AppCompatActivity() {

    lateinit var binding: ActivityChangeUsernameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  DataBindingUtil.setContentView(this, R.layout.activity_change_username)

        binding.imageButton3.setOnClickListener {
            finish()
        }

        binding.button2.setOnClickListener{

            val username = binding.editTextTextPassword.text.toString().trim()
            val password = binding.editTextNumberPassword.text.toString().trim()
            val newUsername1 = binding.editTextTextPassword2.text.toString().trim()
            val newUsername2 = binding.editTextTextPassword3.text.toString().trim()

            if (username.isNotEmpty() && password.isNotEmpty() && newUsername1.isNotEmpty() && newUsername2.isNotEmpty()) {
                //TODO: check username-password match
                if(newUsername1 != newUsername2){
                    binding.editTextTextPassword2.setText("")
                    binding.editTextTextPassword3.setText("")
                    binding.editTextTextPassword2.error = "Input username and repeat username does not match"
                }else{
                    val text = "Username successfully updated"
                    val duration = Toast.LENGTH_SHORT

                    val toast = Toast.makeText(this,text,duration)
                    toast.show()
                    finish()
                }
            } else {
                if (username.isEmpty()) {
                    binding.editTextTextPassword.error = "Current username cannot be blank"
                }
                if (password.isEmpty()) {
                    binding.editTextNumberPassword.error = "Current password cannot be blank"
                }
                if (newUsername1.isEmpty()) {
                    binding.editTextTextPassword2.error = "New username cannot be blank"
                }
                if (newUsername2.isEmpty()) {
                    binding.editTextTextPassword3.error = "Repeat username cannot be blank"
                }

                if (username.isEmpty()) {
                    binding.editTextTextPassword.requestFocus()
                    showKeyboard(binding.editTextTextPassword)
                } else if (password.isEmpty()) {
                    binding.editTextNumberPassword.requestFocus()
                    showKeyboard(binding.editTextNumberPassword)
                } else if (newUsername1.isEmpty()) {
                    binding.editTextTextPassword2.requestFocus()
                    showKeyboard(binding.editTextTextPassword2)
                } else if (newUsername2.isEmpty()) {
                    binding.editTextTextPassword3.requestFocus()
                    showKeyboard(binding.editTextTextPassword3)
                }
            }


        }
    }

    private fun showKeyboard(view: View) {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
}