package com.example.meditation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class DetailImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_detail_image);

        Intent intent = getIntent();

        ImageView imageView = findViewById(R.id.detailImage);

        String position = intent.getStringExtra("id");
        imageView.setImageResource(PageFragmentProfile.image_ids[Integer.parseInt(position)]);
    }
    public void back(View view) {
        Intent intent = new Intent(this, Main.class);
        intent.putExtra("prof", "Profile");
        startActivity(intent);
    }
}