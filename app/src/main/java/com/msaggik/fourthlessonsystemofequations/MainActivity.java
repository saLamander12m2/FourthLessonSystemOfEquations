package com.msaggik.fourthlessonsystemofequations;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // поля
    private float a, b, c; // коэффициенты системы уравнений: a1*X + b1*Y = c1, a2*X + b2*Y = c2
    private double x1, x2; // переменные системы уравнений: a1*X + b1*Y = c1, a2*X + b2*Y = c2

    private TextView equation; // окна вывода информации об уравнениях
    private EditText inputX1, inputX2; // окна ввода решения задачи
    private Button button; // кнопка проверки решения

    private double D = b * b - 4 * a * c;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // привязка полей к разметке
        equation = findViewById(R.id.equation);
        inputX1 = findViewById(R.id.inputX1);
        inputX2 = findViewById(R.id.inputX2);
        button = findViewById(R.id.button);

        randomCoefficient(100); // инициализация кэффициентов уравнения

        // вывод информации о текущем уравнении
        equation.setText(a + "*X**2 + " + b + "*X + " + c + " = 0");

        // определение формул решения c округлением до целого с помощью метода round класса Math
        x1 = (-b + Math.sqrt(D)) / (2 * a);
        x2 = (-b - Math.sqrt(D)) / (2 * a);

        // задание слушателей на EditText's и кнопку
        inputX1.setOnFocusChangeListener(focusListener); // задание слушателя фокусировки/дефокусировки на EditText
        inputX2.setOnFocusChangeListener(focusListener); // задание слушателя фокусировки/дефокусировки на EditText
        button.setOnClickListener(view -> {

            if (!inputX1.getText().toString().isEmpty() && !inputX2.getText().toString().isEmpty()){
                int inX1 = Integer.parseInt(inputX1.getText().toString());
                int inX2 = Integer.parseInt(inputX2.getText().toString());

                if (x1 == inX1 && x2 == inX2) {
                    randomCoefficient(100);
                    equation.setText(a + "*X**2 + " + b + "*X + " + c + " = 0");
                    x1 = (-b + Math.sqrt(D)) / (2 * a);
                    x2 = (-b - Math.sqrt(D)) / (2 * a);
                } else {
                    Toast.makeText(MainActivity.this, "Текущее решение не правильное", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "введите значение X", Toast.LENGTH_SHORT).show();
            }
        });
    }



    // создание слушателя фокусировки/дефокусировки на EditText
    private View.OnFocusChangeListener focusListener = new View.OnFocusChangeListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onFocusChange(View view, boolean b) {

            switch (view.getId()) {
                case R.id.inputX1:
                    if (!b) { // если с R.id.inputX произошла дефокусировка, то
                        int inX1 = Integer.parseInt(inputX1.getText().toString()); // определяем ввод в формате int
                        if (x1 != inX1) { // если ответ введён не правильно, то
                            inputX1.setTextColor(Color.RED); // подкрашиваем текст в красный
                            Toast.makeText(MainActivity.this, "Введено не правильное значение X", Toast.LENGTH_SHORT).show();
                        } else { // иначе
                            inputX1.setTextColor(0xFF177C3A); // подкрашиваем текст в исходный цвет
                        }
                    }
                    break;
                case R.id.inputX2:
                    if (!b) { // если с R.id.inputY произошла дефокусировка, то
                        int intX2 = Integer.parseInt(inputX2.getText().toString()); // определяем ввод в формате int
                        if (x2 != intX2) { // если ответ введён не правильно, то
                            inputX2.setTextColor(Color.RED); // подкрашиваем текст в красный
                            Toast.makeText(MainActivity.this, "Введено не правильное значение Y", Toast.LENGTH_SHORT).show();
                        } else { // иначе
                            inputX2.setTextColor(0xFF177C3A); // подкрашиваем текст в исходный цвет
                        }
                    }
                    break;
            }
        }
    };

    private void randomCoefficient(int limit) {
        Random random = new Random(); // создание объекта класса Random (класса генерации псевдослучайных значений)
        a = random.nextInt(limit); // инициализация коэффициента a1 псевдослучайным значением от 0 до limit-1
        b = random.nextInt(limit);
        c = random.nextInt(limit);
    }
}