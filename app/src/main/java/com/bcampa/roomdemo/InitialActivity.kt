package com.bcampa.roomdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_initial.*

class InitialActivity : AppCompatActivity() {

    // todo: adicionar edição e deleção
    // todo: otimizar ícones
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)

        btnSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
        btnViewUsers.setOnClickListener {
            startActivity(Intent(this, UserListActivity::class.java))
        }
    }
}
