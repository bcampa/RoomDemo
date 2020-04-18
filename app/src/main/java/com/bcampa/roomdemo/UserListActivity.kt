package com.bcampa.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.bcampa.roomdemo.database.UserDatabase
import com.bcampa.roomdemo.model.User
import kotlinx.android.synthetic.main.activity_user_list.*

class UserListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        // If there is at least one user initializes the ArrayAdapter,
        // otherwise tell the user there are no items
        var users = getUsers()
        loadAdapter(users)
        lstVwUsers.setOnItemClickListener { adapterView: AdapterView<*>, view1: View,
                                            i: Int, l: Long ->
            deleteUser(users[i])
            users = getUsers()
            loadAdapter(users)
        }

        // Initialize button
        btnGoBack.setOnClickListener {
            finish()
        }
    }

    private fun getUsers(): List<User> {
        return UserDatabase
            .getInstance(applicationContext)
            .userDao()
            .loadAllUsers()
    }

    private fun deleteUser(user: User) {
        UserDatabase
            .getInstance(applicationContext)
            .userDao()
            .deleteUsers(user)
    }

    private fun loadAdapter(users: List<User>) {
        if (users.isNotEmpty()) {
            lstVwUsers.adapter = ArrayAdapter(
                this@UserListActivity,
                android.R.layout.simple_list_item_1,
                users
            )
            lstVwUsers.visibility = View.VISIBLE
            txtVwNoItems.visibility = View.GONE
        }
        else {
            lstVwUsers.visibility = View.GONE
            txtVwNoItems.visibility = View.VISIBLE
        }
    }
}
