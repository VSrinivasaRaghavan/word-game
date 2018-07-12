package com.example.raghavanpc.word_game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class start_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);
        Button st=(Button)findViewById(R.id.st);
        final Intent i=new Intent(start_menu.this,wordgame.class);
        st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                startActivity(i);
            }
        });
    }
}
