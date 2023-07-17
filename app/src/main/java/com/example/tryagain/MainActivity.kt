package com.example.tryagain

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.SearchView
import android.view.View
import android.widget.Button


class MainActivity : AppCompatActivity() {
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DataAdapter
    private var isSearchOpened = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchView = findViewById(R.id.searchView)
        recyclerView = findViewById(R.id.recyclerView)

        val dataList = readData(this)

        adapter = DataAdapter(dataList) { selectedItem ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("id", selectedItem.id)
                putExtra("name", selectedItem.name)
                putExtra("description", selectedItem.description)
                putExtra("imageName", selectedItem.imageName)
            }
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        recyclerView.visibility = if (isSearchOpened) View.VISIBLE else View.GONE

        searchView.setOnSearchClickListener {
            isSearchOpened = true
            recyclerView.visibility = View.VISIBLE
        }

        searchView.setOnCloseListener {
            isSearchOpened = false
            recyclerView.visibility = View.GONE
            false
        }

        adapter.setOnItemClickListener { selectedItem ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("id", selectedItem.id)
                putExtra("name", selectedItem.name)
                putExtra("description", selectedItem.description)
                putExtra("imageName", selectedItem.imageName) // Add this line to pass the imageName
            }
            startActivity(intent)
        }

        val adultButton = findViewById<Button>(R.id.adultButton)
        val childButton = findViewById<Button>(R.id.childButton)

        adultButton.setOnClickListener {
            val intent = Intent(this, AgeGroupActivity::class.java)
            intent.putExtra("ageGroup", "adult")
            startActivity(intent)
        }

        childButton.setOnClickListener {
            val intent = Intent(this, AgeGroupActivity::class.java)
            intent.putExtra("ageGroup", "child")
            startActivity(intent)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    val filteredList = dataList.filter { data ->
                        data.name.contains(newText, ignoreCase = true)
                    }
                    adapter.filterList(filteredList)
                }
                return true
            }
        })
    }
}
