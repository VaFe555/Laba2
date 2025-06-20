package com.example.rickandmortyapp_lab2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadCharacters()
    }

    private fun loadCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            val response: Response<CharacterResponse> = RetrofitClient.instance.getCharacters()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val characters = response.body()?.results ?: emptyList()
                    adapter = CharacterAdapter(characters)
                    recyclerView.adapter = adapter
                } else {
                    // Обработка ошибки
                }
            }
        }
    }
}