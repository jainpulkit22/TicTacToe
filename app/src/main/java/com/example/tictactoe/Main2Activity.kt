package com.example.tictactoe

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }
    @SuppressLint("WrongViewCast")
    fun playgame(view: View)
    {
        val name1 = findViewById<EditText>(R.id.edit1)
        val name2 = findViewById<EditText>(R.id.edit2)
        val play1 = name1.text.toString()
        val play2 = name2.text.toString()
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("Player1", play1)
            putExtra("Player2", play2)
        }
        startActivity(intent)
    }
}
