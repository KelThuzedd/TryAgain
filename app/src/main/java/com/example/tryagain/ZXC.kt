package com.example.tryagain

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.widget.TextView
import java.io.BufferedReader
import java.io.InputStreamReader
import android.view.ViewGroup
import android.view.View
/**
 * Читает данные из файла и возвращает список объектов DataModel.
 */
 fun readData(context: Context): List<DataModel> {
    val dataList = ArrayList<DataModel>()

    context.assets.open("data.txt").use { inputStream ->
        BufferedReader(InputStreamReader(inputStream)).use { reader ->
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                line?.split(";")?.let { columns ->
                    val data = DataModel(
                        id = columns.getOrNull(0) ?: "",
                        name = columns.getOrNull(1) ?: "",
                        description = columns.getOrNull(2) ?: "",
                        child = columns.getOrNull(3) ?: "",
                        imageName = columns.getOrNull(4) ?: ""
                    )
                    dataList.add(data)
                }
            }
        }
    }

    return dataList
}

/**
 * Создает макет страницы и устанавливает данные на странице.
 */
/**
 * Создает макет страницы и устанавливает данные на странице.
 */
 fun createPageLayout(context: Context, data: DataModel): View {
    val pageView = LayoutInflater.from(context).inflate(R.layout.page_layout, null)

    val idTextView = pageView.findViewById<TextView>(R.id.idTextView)
    val nameTextView = pageView.findViewById<TextView>(R.id.nameTextView)
    val descriptionTextView = pageView.findViewById<TextView>(R.id.descriptionTextView)
    val childTextView = pageView.findViewById<TextView>(R.id.childTextView)
    val imageNameTextView = pageView.findViewById<TextView>(R.id.imageNameTextView)

    idTextView.text = data.id
    nameTextView.text = data.name
    descriptionTextView.text = data.description
    childTextView.text = data.child
    imageNameTextView.text = data.imageName

    return pageView
}

/**
 * Отображает данные на странице и устанавливает обработчик нажатия.
 */
 fun displayData(context: Context, container: ViewGroup, dataList: List<DataModel>) {
    for (data in dataList) {
        val pageView = createPageLayout(context, data)

        pageView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("id", data.id)
                putExtra("name", data.name)
                putExtra("description", data.description)
                putExtra("child", data.child)
                putExtra("imageName", data.imageName)
            }
            context.startActivity(intent)
        }

        container.addView(pageView)
    }
}

/**
 * Отображает данные на странице.
 */
fun processData(context: Context, container: ViewGroup) {
    val dataList = readData(context)
    displayData(context, container, dataList)
}