package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var currentInput: StringBuilder = StringBuilder()
    private var firstOperand: Double = 0.0
    private var currentOperator: String = ""
    private var isOperatorClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        display = findViewById<TextView>(R.id.display)

        findViewById<Button>(R.id.button_0).setOnClickListener { appendToInput("0") }
        findViewById<Button>(R.id.button_1).setOnClickListener { appendToInput("1") }
        findViewById<Button>(R.id.button_2).setOnClickListener { appendToInput("2") }
        findViewById<Button>(R.id.button_3).setOnClickListener { appendToInput("3") }
        findViewById<Button>(R.id.button_4).setOnClickListener { appendToInput("4") }
        findViewById<Button>(R.id.button_5).setOnClickListener { appendToInput("5") }
        findViewById<Button>(R.id.button_6).setOnClickListener { appendToInput("6") }
        findViewById<Button>(R.id.button_7).setOnClickListener { appendToInput("7") }
        findViewById<Button>(R.id.button_8).setOnClickListener { appendToInput("8") }
        findViewById<Button>(R.id.button_9).setOnClickListener { appendToInput("9") }

        findViewById<Button>(R.id.button_plus).setOnClickListener { setOperator("+") }
        findViewById<Button>(R.id.button_minus).setOnClickListener { setOperator("-") }
        findViewById<Button>(R.id.button_multiply).setOnClickListener { setOperator("*") }
        findViewById<Button>(R.id.button_divide).setOnClickListener { setOperator("/") }
        findViewById<Button>(R.id.button_clear).setOnClickListener { clearInput() }
        findViewById<Button>(R.id.button_equal).setOnClickListener { calculateResult() }
    }

    private fun appendToInput(number: String) {
        if (isOperatorClicked) {
            currentInput.clear() // Очистить при нажатии на оператор
            isOperatorClicked = false
        }
        currentInput.append(number)
        display.text = currentInput.toString()
    }

    private fun setOperator(operator: String) {
        if (currentInput.isNotEmpty()) {
            firstOperand = currentInput.toString().toDouble()
            currentOperator = operator
            currentInput.clear() // Очистить ввод для следующего числа
        }
    }

    private fun calculateResult() {
        if (currentInput.isNotEmpty() && currentOperator.isNotEmpty()) {
            val secondOperand = currentInput.toString().toDouble()
            var result = 0.0

            when (currentOperator) {
                "+" -> result = firstOperand + secondOperand
                "-" -> result = firstOperand - secondOperand
                "*" -> result = firstOperand * secondOperand
                "/" -> if (secondOperand != 0.0) {
                    result = firstOperand / secondOperand
                } else {
                    display.text = "Error"
                    return
                }
            }
            currentInput.clear()
            currentInput.append(result)
            display.text = currentInput.toString()
            currentOperator = ""
        }
    }

    private fun clearInput() {
        currentInput.clear()
        display.text = "0"
        currentOperator = ""
    }
}
