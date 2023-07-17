package com.example.tryagain

class MainActivityTest {


    /**Should display the original data list when the query text is null*/
    @Test
    fun onQueryTextChangeWhenQueryTextIsNull() {
        val mainActivity = MainActivity()
        val searchView = mock(SearchView::class.java)
        val container = mock(LinearLayout::class.java)
        val dataList = listOf(Data("John Doe", 25), Data("Jane Smith", 30))

        `when`(mainActivity.readData(mainActivity)).thenReturn(dataList)

        mainActivity.displayData(mainActivity, container, dataList)

        mainActivity.searchView = searchView
        mainActivity.container = container

        mainActivity.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    val filteredList = dataList.filter { data ->
                        data.name.contains(newText, ignoreCase = true)
                    }
                    mainActivity.displayData(mainActivity, container, filteredList)
                }
                return true
            }
        })

        mainActivity.searchView.onQueryTextChange(null)

        verify(mainActivity, never()).displayData(mainActivity, container, dataList)
    }

}