package com.example.bt3;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.constrain_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            randomBackground();
            return insets;
        });
    }

    private void randomBackground() {
        ConstraintLayout bg = findViewById(R.id.constrain_layout);

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(R.drawable.bg1);
        arrayList.add(R.drawable.bg2);
        arrayList.add(R.drawable.bg3);
        arrayList.add(R.drawable.bg4);

        SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        int lastBackground = sharedPreferences.getInt("last_bg", -1);

        Random random = new Random();
        int vitri;

        // Random đến khi ảnh mới khác ảnh cũ
        do {
            vitri = random.nextInt(arrayList.size());
        } while (arrayList.get(vitri) == lastBackground && arrayList.size() > 1);

        // Đặt ảnh nền mới
        bg.setBackgroundResource(arrayList.get(vitri));

        // Lưu ảnh nền mới vào SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("last_bg", arrayList.get(vitri));
        editor.apply();
    }

}