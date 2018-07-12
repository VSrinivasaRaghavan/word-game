package com.example.raghavanpc.word_game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class wordgame extends AppCompatActivity {

    ImageView im[]=new ImageView[5];
    Random rand;
    ArrayList<String> allwords;
    int i;
    int s;
    ArrayList<Integer> list;
    Button yes;
    TextView t[]=new TextView[3];
    String word;
    int count=0;
    int found=0;
    int score=0;
    static int highscore=0;
    InputStream ir;
    BufferedReader rd;
    TextView sc;
    String index[]=new String[26];
    TextView hs;
    ArrayList<String> wordlist;
    char ch[]=new char[5];
    Button undo;
    ListView l;
    int prev=0;
    final int res[]={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h,R.drawable.i,R.drawable.j,R.drawable.k,R.drawable.l,R.drawable.m,R.drawable.n,R.drawable.o,R.drawable.p,R.drawable.q,R.drawable.r,R.drawable.s,R.drawable.t,R.drawable.u,R.drawable.v,R.drawable.w,R.drawable.x,R.drawable.y,R.drawable.z};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordgame);
        rand=new Random();
        String words[];
        wordlist=new ArrayList<>();
        allwords=new ArrayList<>();
        undo=(Button)findViewById(R.id.undo);
        for(int i=65;i<91;++i)
            index[i-65]=String.valueOf((char)i);
        sc=(TextView)findViewById(R.id.score);
        hs=(TextView)findViewById(R.id.hs);
        word="";
        s=0;
        i=0;
        fileOpen();
        list=new ArrayList<Integer>();
        for(i=0;i<26;++i) {
            list.add(i);
        }
//        Toast.makeText(this, "SHUFFLER", Toast.LENGTH_SHORT).show();
        shuffler();
        undo.setEnabled(false);
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(wordgame.this, ""+prev, Toast.LENGTH_SHORT).show();
                if(count>0) {
                    --count;
                    word=word.substring(0,word.length()-1);
                    t[count].setText(null);
                }
                else if(count==0)
                {
                    count=2;
                    word=word.substring(0,word.length()-1);
                    prev=0;
                    t[2].setText(null);
                }

                if(count==0) {
                    undo.setEnabled(false);
                    word="";
                }

            }

        });
    }
    void fileOpen()
    {
        try {
            String words="";
            ir = getResources().openRawResource(R.raw.three_words);
            rd = new BufferedReader(new InputStreamReader(ir, "UTF-8"));
            while((words=rd.readLine())!=null) {
                allwords.add(words.toUpperCase());
            }
        }
        catch(Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    void possibleWords(char c[],int k)
    {
        char temp;
        String var="";
        getAllLengthr(c,"",c.length,k);
    }
    void getAllLengthr(char c[],String prefix,int n,int k)
    {
        if(k==0) {
//            Toast.makeText(this,prefix, Toast.LENGTH_SHORT).show();
            if (allwords.contains(prefix.toUpperCase())) {
                wordlist.add(prefix.toUpperCase());
            }
            return;
        }
        for(int i=0;i<n;++i)
        {
            String nprefix=prefix+c[i];
            getAllLengthr(c,nprefix,n,k-1);
        }
    }
    public void checkCorrect(String x)
    {
        Toast.makeText(this, ""+x, Toast.LENGTH_SHORT).show();

        if(wordlist.contains(x.toUpperCase()))
        {
            found++;
            if(found==wordlist.size())
            {
                prev=0;
                word="";
                undo.setEnabled(false);
                for (int i = 0; i < 3; ++i)
                    t[i].setText(null);
                shuffler();
            }
            else {
                score+=10;
                prev=0;
                word="";
                sc.setText(""+score);
                undo.setEnabled(false);
                for (int i = 0; i < 3; ++i)
                    t[i].setText(null);
            }
        }

    }
    public void shuffler()
    {
            do {
                Collections.shuffle(list);
                for (int i = 0; i < 5; ++i) {
                    ch[i] = index[list.get(i)].charAt(0);
                }
                possibleWords(ch, 3);
            }while(found==wordlist.size());
        //while(wordlist.size()==0);
        Toast.makeText(this,""+wordlist.size(), Toast.LENGTH_SHORT).show();
        for(i=0;i<5;++i)
        {
            im[i]=(ImageView)findViewById(getResources().getIdentifier("im"+(i+1),"id",getPackageName()));
            im[i].setImageResource(res[list.get(i)]);
//            Toast.makeText(wordgame.this, list.get(i)+"", Toast.LENGTH_SHORT).show();
            if(i<3) {
                t[i] = (TextView) findViewById(getResources().getIdentifier("tv" + (i + 1), "id", getPackageName()));
            }
//            Toast.makeText(wordgame.this,i+"", Toast.LENGTH_SHORT).show();
                im[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (count < 3&&prev==0) {
                            undo.setEnabled(true);
                            String x = getResources().getResourceEntryName(view.getId());
                            String tv = x.substring(x.length() - 1);
//                    Toast.makeText(wordgame.this, tv+","+list.get(Integer.parseInt(tv)-1), Toast.LENGTH_SHORT).show();
                            word += index[list.get(Integer.parseInt(tv) - 1)];
                            t[count].setText(index[list.get(Integer.parseInt(tv) - 1)]);
                            ++count;
                            if (count == 3) {
                                prev=1;
                                checkCorrect(word);
                                count=0;
                            }
                        }
                    }
                });
        }
    }
}
