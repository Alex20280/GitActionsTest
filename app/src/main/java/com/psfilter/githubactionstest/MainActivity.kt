package com.psfilter.githubactionstest

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "Made some functionality", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "Made some functionality for second branch", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "Made some functionality for 3 branch", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "Made some functionality for 5 branch", Toast.LENGTH_SHORT).show()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
