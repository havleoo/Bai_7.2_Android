package com.example.simplelist

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextNumber: EditText
    private lateinit var radioEven: RadioButton
    private lateinit var radioOdd: RadioButton
    private lateinit var radioSquare: RadioButton
    private lateinit var buttonShow: Button
    private lateinit var textViewError: TextView
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ các thành phần giao diện
        editTextNumber = findViewById(R.id.editTextNumber)
        radioEven = findViewById(R.id.radioEven)
        radioOdd = findViewById(R.id.radioOdd)
        radioSquare = findViewById(R.id.radioSquare)
        buttonShow = findViewById(R.id.buttonShow)
        textViewError = findViewById(R.id.textViewError)
        listView = findViewById(R.id.listView)

        buttonShow.setOnClickListener {
            showNumbers()
        }
    }

    private fun showNumbers() {
        val inputText = editTextNumber.text.toString()
        textViewError.text = ""  // Xóa thông báo lỗi trước đó

        // Kiểm tra tính hợp lệ của số nhập vào
        val n = inputText.toIntOrNull()
        if (n == null || n < 0) {
            textViewError.text = "Vui lòng nhập một số nguyên dương hợp lệ."
            return
        }

        // Tạo danh sách kết quả
        val results = when {
            radioEven.isChecked -> generateEvenNumbers(n)
            radioOdd.isChecked -> generateOddNumbers(n)
            radioSquare.isChecked -> generateSquareNumbers(n)
            else -> {
                textViewError.text = "Vui lòng chọn một loại số."
                return
            }
        }

        // Hiển thị danh sách kết quả trong ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, results)
        listView.adapter = adapter
    }

    // Hàm tạo danh sách số chẵn từ 0 đến n
    private fun generateEvenNumbers(n: Int): List<String> {
        return (0..n).filter { it % 2 == 0 }.map { it.toString() }
    }

    // Hàm tạo danh sách số lẻ từ 1 đến n
    private fun generateOddNumbers(n: Int): List<String> {
        return (1..n).filter { it % 2 != 0 }.map { it.toString() }
    }

    // Hàm tạo danh sách số chính phương từ 0 đến n
    private fun generateSquareNumbers(n: Int): List<String> {
        val results = mutableListOf<String>()
        var i = 0
        while (i * i <= n) {
            results.add((i * i).toString())
            i++
        }
        return results
    }
}
