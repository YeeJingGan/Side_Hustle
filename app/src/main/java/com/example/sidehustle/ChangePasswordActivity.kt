package com.example.sidehustle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.sidehustle.databinding.ActivityChangePasswordBinding

class ChangePasswordActivity : AppCompatActivity() {

    lateinit var binding: ActivityChangePasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_password)

        binding.passwordImageButton3.setOnClickListener {
            finish()
        }

        binding.passwordButton2.setOnClickListener {


            val username = binding.passwordEditTextTextPassword1.text.toString().trim()
            val password = binding.passwordEditTextTextPassword2.text.toString().trim()
            val newPassword1 = binding.passwordEditTextTextPassword3.text.toString().trim()
            val newPassword2 = binding.passwordEditTextTextPassword4.text.toString().trim()

            if (username.isNotEmpty() && password.isNotEmpty() && newPassword1.isNotEmpty() && newPassword2.isNotEmpty()) {
                //TODO: check username-password match
                if (isValidPassword(newPassword1) && newPassword1 == newPassword2) {
                    val text = "Password successfully updated"
                    val duration = Toast.LENGTH_SHORT

                    val toast = Toast.makeText(this,text,duration)
                    toast.show()
                    finish()
                } else {
                    binding.passwordEditTextTextPassword3.setText("")
                    binding.passwordEditTextTextPassword4.setText("")
                    binding.passwordEditTextTextPassword3.requestFocus()
                    showKeyboard(binding.passwordEditTextTextPassword3)
                    if(!isValidPassword(newPassword1)){
                        binding.passwordEditTextTextPassword3.error =
                            "Invalid password - password must be at least: 8 characters, one lowercase (a-z),uppercase (A-Z), one number (0-9), and one special character (*, $, ..)"
                    }else{
                        binding.passwordEditTextTextPassword3.error =
                            "Input password and repeat password does not match"
                    }
                }
            } else {
                if (username.isEmpty()) {
                    binding.passwordEditTextTextPassword1.error = "Current username cannot be blank"
                }
                if (password.isEmpty()) {
                    binding.passwordEditTextTextPassword2.error = "Current password cannot be blank"
                }
                if (newPassword1.isEmpty()) {
                    binding.passwordEditTextTextPassword3.error = "New password cannot be blank"
                }
                if (newPassword2.isEmpty()) {
                    binding.passwordEditTextTextPassword4.error = "Repeat password cannot be blank"
                }

                if (username.isEmpty()) {
                    binding.passwordEditTextTextPassword1.requestFocus()
                    showKeyboard(binding.passwordEditTextTextPassword1)
                } else if (password.isEmpty()) {
                    binding.passwordEditTextTextPassword2.requestFocus()
                    showKeyboard(binding.passwordEditTextTextPassword2)
                } else if (newPassword1.isEmpty()) {
                    binding.passwordEditTextTextPassword3.requestFocus()
                    showKeyboard(binding.passwordEditTextTextPassword3)
                } else if (newPassword2.isEmpty()) {
                    binding.passwordEditTextTextPassword4.requestFocus()
                    showKeyboard(binding.passwordEditTextTextPassword4)
                }
            }


        }
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordPattern =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$".toRegex()
        return password.matches(passwordPattern)
    }

    private fun showKeyboard(view: View) {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

}