package com.example.hw_oct12_table_layout_2022_7260;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.hw_oct12_table_layout_2022_7260.Model.MyMenu;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView[] listOfTextViews;
    MyMenu[] listOfMenus;

    int[] widgets = { R.id.tvBMT, R.id.tvBW, R.id.tvBThF,
            R.id.tvLM, R.id.tvLT, R.id.tvLW, R.id.tvLTh,
            R.id.tvLF, R.id.tvSM, R.id.tvSTW, R.id.tvSThF };


    TextView clickedTextView;
    int currentIndex;

    ActivityResultLauncher<Intent> activityResLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        listOfTextViews = new TextView[widgets.length];
        listOfMenus = new MyMenu[widgets.length];

        listOfMenus[0] = new MyMenu("Cheerios\nBanana\nMilk\n......", Color.RED, Color.WHITE);
        listOfMenus[1] = new MyMenu("Pancakes\n\nBlueberries\nMilk", Color.GREEN, Color.YELLOW);
        listOfMenus[2] = new MyMenu("Scrambled Eggs\n& Toast\nPotatoes\n100% Juice", Color.MAGENTA, Color.WHITE);
        listOfMenus[3] = new MyMenu("Mashed\nPotatoes\nPeas & Corn\nBread & Butter\nMilk", Color.GREEN, Color.YELLOW);
        listOfMenus[4] = new MyMenu("Tuna Fish\nSandwich\nApple sauce\nCarrots Sticks\nMilk", Color.MAGENTA, Color.WHITE);
        listOfMenus[5] = new MyMenu("Rice & Chicken\nStew\nCarrots &\nPotatoes\nMilk", Color.RED, Color.WHITE);
        listOfMenus[6] = new MyMenu("Macaroni &\nCheese\nFish Sticks\nGreen Beans\nMilk", Color.MAGENTA, Color.YELLOW);
        listOfMenus[7] = new MyMenu("Whole Wheat\nPizza\nSpinach\nOrange Slices\nMilk", Color.GREEN, Color.YELLOW);
        listOfMenus[8] = new MyMenu("Crackers with\nPeanut Butter\n\n100% Juice", Color.MAGENTA, Color.WHITE);
        listOfMenus[9] = new MyMenu("Yogurt\nRaisins /\nPeaches\n100% Juice", Color.GREEN, Color.WHITE);
        listOfMenus[10] = new MyMenu("Home-made\nBlueberry\nMuffin\n100% Juice", Color.GREEN, Color.YELLOW);

        for (int i = 0; i < widgets.length; i++) {
            listOfTextViews[i] = findViewById(widgets[i]);
            listOfTextViews[i].setText(listOfMenus[i].getDescription());
            listOfTextViews[i].setTextColor(listOfMenus[i].getTextColor());
            listOfTextViews[i].setBackgroundColor(listOfMenus[i].getBackgroundColor());
            listOfTextViews[i].setOnClickListener(this);
        }

        // Activity Result
        activityResLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    MyMenu menu = (MyMenu) result.getData().getSerializableExtra("Menu");

                    listOfMenus[currentIndex] = menu;

                    clickedTextView.setText(menu.getDescription());
                    clickedTextView.setTextColor(menu.getTextColor());
                    clickedTextView.setBackgroundColor(menu.getBackgroundColor());
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        clickedTextView = (TextView) view;
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);

        // find corresponding menu by looking at what text view was clicked
        for (int i = 0; i < listOfTextViews.length; i++) {
            if (clickedTextView.getId() == listOfTextViews[i].getId()) {
                currentIndex = i;
                break;
            }
        }

        intent.putExtra("Menu", listOfMenus[currentIndex]);
        activityResLauncher.launch(intent);
    }
}