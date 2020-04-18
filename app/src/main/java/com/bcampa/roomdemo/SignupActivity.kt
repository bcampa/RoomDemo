package com.bcampa.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import com.bcampa.roomdemo.database.UserDatabase
import com.bcampa.roomdemo.model.User
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        btnSignup.setOnClickListener {
            clearErrors()
            if(checkIfFieldsAreFilled() && validateFields()) {
                saveUser()
                finish()
            }
        }

        btnCancel.setOnClickListener {
            finish()
        }
    }


    private fun checkIfFieldsAreFilled(): Boolean {
        val fields = arrayOf(
            Pair(edtTxtName, inputLayoutName),
            Pair(edtTxtEmail, inputLayoutEmail),
            Pair(edtTxtPassword, inputLayoutPassword),
            Pair(edtTxtConfirmPassword, inputLayoutConfirmPassword),
            Pair(edtTxtCpf, inputLayoutCpf)
        )

        fields.forEach {
            if(it.first.text.isNullOrEmpty()) {
                it.second.error = getString(R.string.error_empty_field)
                return false
            }
        }
        return true
    }

    private fun validateName(): Boolean {
        val name = edtTxtName.text.toString()
        val nameRegex = Regex("[\\w]+")

        return if(!nameRegex.matches(name)) {
            inputLayoutName.error = getString(R.string.error_special_characters)
            false
        } else true
    }

    private fun validateEmail(): Boolean {
        val email = edtTxtEmail.text.toString()

        return if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            inputLayoutEmail.error = getString(R.string.error_email_format)
            false
        } else true
    }

    private fun validatePassword(): Boolean {
        val password = edtTxtPassword.text.toString()
        val passwordConfirmation = edtTxtConfirmPassword.text.toString()

        return if(password != passwordConfirmation) {
            inputLayoutConfirmPassword.error = getString(R.string.error_password_mismatch)
            false
        } else true
    }

    private fun validateCpf(): Boolean {
        val cpf = edtTxtCpf.text.toString()
        val cpfRegex = Regex("^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}\$")

        return if(!cpfRegex.matches(cpf)) {
            inputLayoutCpf.error = getString(R.string.error_cpf_format)
            false
        } else true
    }

    private fun validateFields(): Boolean {
        return validateName() && validateEmail() && validatePassword() && validateCpf()
    }

    private fun clearErrors() {
        val inputLayouts = arrayOf(inputLayoutName, inputLayoutEmail,
            inputLayoutPassword, inputLayoutConfirmPassword, inputLayoutCpf
        )
        inputLayouts.forEach {
            if(!it.error.isNullOrEmpty()) {
                it.error = null
            }
        }
    }

    private fun getUserFromInputs(): User {
        return User (
            edtTxtName.text.toString(),
            edtTxtEmail.text.toString(),
            edtTxtPassword.text.toString(),
            edtTxtCpf.text.toString()
        )
    }

    private fun saveUser() {
        UserDatabase
            .getInstance(applicationContext)
            .userDao()
            .insertUsers(getUserFromInputs())
    }
}
