package com.example.tryagain

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AgeGroupActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.age_group_layout)

        recyclerView = findViewById(R.id.ageGroupRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val ageGroup = intent.getStringExtra("ageGroup")

        val dataList = readData(this)
        val filteredList = if (ageGroup == "adult") {
            dataList.filter { data ->
                data.child.isEmpty()
            }
        } else {
            dataList.filter { data ->
                data.child.isNotEmpty()
            }
        }

        adapter = DataAdapter(filteredList) { selectedItem ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("id", selectedItem.id)
                putExtra("name", selectedItem.name)
                putExtra("description", selectedItem.description)
                putExtra("imageName", selectedItem.imageName)
            }
            startActivity(intent)
        }

        adapter.setOnItemClickListener { selectedItem ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("id", selectedItem.id)
                putExtra("name", selectedItem.name)
                putExtra("description", selectedItem.description)
                putExtra("imageName", selectedItem.imageName)
            }
            startActivity(intent)
        }

        recyclerView.adapter = adapter
    }
}
