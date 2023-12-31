package com.example.sidehustle

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import androidx.core.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import com.google.android.material.appbar.MaterialToolbar


class register_user : AppCompatActivity() {
    private lateinit var passwordEditText: TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_user)

        // set the up button #1
        val toolbar: MaterialToolbar = findViewById(R.id.materialToolbar2)
        setSupportActionBar(toolbar)
        supportActionBar?.title = null
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        //make the "terms and conditions" in the TextView a clickable link
        val terms: TextView = findViewById(R.id.register_terms)
        terms.movementMethod = LinkMovementMethod.getInstance()

        // all the code below is to change the check circle from gray to yellow if the password condition is true
        passwordEditText = findViewById(R.id.register_password)

        passwordEditText.addTextChangedListener(object : TextWatcher {
            // once text in password box change, send to the update functions
            override fun afterTextChanged(s: Editable?) {
                val password = s.toString()
                updateMinimumCharacterDrawable(password)
                updateLeastLowercaseDrawable(password)
                updateLeastUppercaseDrawable(password)
                updateLeastNumberDrawable(password)
                updateLeastUniqueDrawable(password)
            }
            //  at first i only did afterTextChange and it didnt work, have to define beforeTextChanged and onTextChanged
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                val password = s.toString()
                updateMinimumCharacterDrawable(password)
                updateLeastLowercaseDrawable(password)
                updateLeastUppercaseDrawable(password)
                updateLeastNumberDrawable(password)
                updateLeastUniqueDrawable(password)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val password = s.toString()
                updateMinimumCharacterDrawable(password)
                updateLeastLowercaseDrawable(password)
                updateLeastUppercaseDrawable(password)
                updateLeastNumberDrawable(password)
                updateLeastUniqueDrawable(password)
            }
        })
    }

    // set the up button #2
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // some update functions which send password to another general update function
    private fun updateMinimumCharacterDrawable(password: String) {
        updateDrawableBasedOnCondition(R.id.register_minimumCharacter, password.length >= 8)
    }

    private fun updateLeastLowercaseDrawable(password: String) {
        updateDrawableBasedOnCondition(R.id.register_leastLowercase, containsLowerCase(password))
    }

    private fun updateLeastUppercaseDrawable(password: String) {
        updateDrawableBasedOnCondition(R.id.register_leastUppercase, containsUpperCase(password))
    }

    private fun updateLeastNumberDrawable(password: String) {
        updateDrawableBasedOnCondition(R.id.register_leastNumber, containsNumber(password))
    }

    private fun updateLeastUniqueDrawable(password: String) {
        updateDrawableBasedOnCondition(R.id.register_leastUnique, containsUnique(password))
    }

    // this is the general update function, takes in the id and condition
    private fun updateDrawableBasedOnCondition(viewId: Int, condition: Boolean) {
        val textView: TextView = findViewById(viewId)
        val drawableResId = if (condition) R.drawable.register_checkcircleyellow else R.drawable.register_checkcircle
        val drawable = ContextCompat.getDrawable(this, drawableResId)
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, null, null, null)
    }

    // functions to set the conditions
    private fun containsLowerCase(password: String): Boolean {
        return password.any { it.isLowerCase() }
    }

    private fun containsUpperCase(password: String): Boolean {
        return password.any { it.isUpperCase() }
    }

    private fun containsNumber(password: String): Boolean {
        return password.any { it.isDigit() }
    }

    private fun containsUnique(password: String): Boolean {
        val allowedCharacters = setOf('@','#','$','%','&','*','/',',','.')

        if (password.isEmpty()) {
            return false
        }

        for (char in password) {
            if (allowedCharacters.contains(char)) {
                return true
            }
        }
        return false
    }
}