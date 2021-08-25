package savchits.com.memory;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;


import savchits.com.memory.databinding.ActivityMemorizationBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class  Memorization extends AppCompatActivity {
    ActivityMemorizationBinding binding;
   // LinearLayout lin4, lin5;
    List<String> item;
    Context context;
    int value1;
    int life = 5;
    int countArray = 0;
    int timer = 0;
    SharedPreferences sharedPreferences;

    private CountDownTimer countDownTimer;
    //  private LevelPage levelPage;
    //  RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMemorizationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




       item = initData();
       Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                timer(30000);
            }
        }, 1000);

        //recyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(context) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        binding.rv.setLayoutManager(layoutManager);
        binding.rv.setNestedScrollingEnabled(false);
        RvAdapter rvAdapter = new RvAdapter(item, this);
        binding.rv.setAdapter(rvAdapter);
        //   levelPage = new LevelPage();


        sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
        value1 = sharedPreferences.getInt("life", 0);
        binding.tvLife.setText(value1 + "");
        binding.progressBar.setProgress(value1 * 20);
        if (value1 <= 0) {
            value1 = getIntent().getIntExtra("life2", 0);
            String pos= getIntent().getStringExtra("pos");
            binding.tvLife.setText(value1 + "");
            binding.progressBar.setProgress(value1 * 20);
        }


        binding.line10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LevelPage.class);
                startActivity(intent);
            }
        });


        binding.dalee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                String mword1 = item.get(0).toLowerCase();
                String mword2 = item.get(1).toLowerCase();
                String mword3 = item.get(2).toLowerCase();
                String mword4 = item.get(3).toLowerCase();
                String mword5 = item.get(4).toLowerCase();


                SharedPreferences sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("mword1", mword1);
                editor.putString("mword2", mword2);
                editor.putString("mword3", mword3);
                editor.putString("mword4", mword4);
                editor.putString("mword5", mword5);
                editor.putInt("life", value1);
                editor.apply();
                Intent intent = new Intent(Memorization.this, CheckActivity.class);
                startActivity(intent);


            }
        });

      /*  int pos= getIntent().getIntExtra("pos", 0);
        if(pos ==0) {
            choose(0);


        }
        if(pos ==1) {
        //   LinearLayout  lin4 = findViewById(R.id.l4);
           // lin5= findViewById(R.id.l5);
         //   lin4.setVisibility(View.VISIBLE);
            choose(1);
        }
        if(pos ==2) {

        }
*/
    }


    private void timer(int mill) {
        countDownTimer = new CountDownTimer(mill, 1000) {

            public void onTick(long millisUntilFinished) {
                binding.time.setText("00:" + millisUntilFinished / 1000);
                //cancel();
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                //  finish();
                cancel();
                binding.time.setText("Done!");
                startActivity(new Intent(getApplicationContext(), CheckActivity.class));
                //  cancel();

            }

        }.start();
    }

    private List<String> initData() {
        List<String> list = new ArrayList<String>();
        list.add("Воробей");
        list.add("Лиса");
        list.add("Волк");
        list.add("Лиса");
        list.add("Утка");
        list.add("Заяц");
        list.add("Медведь");
        list.add("Акула");
        list.add("Крокодил");
        list.add("Афиша");
        list.add("Ягода");
        list.add("Капуста");
        list.add("Вздорожание");
        list.add("Питон");
        list.add("Цепочка");
        Collections.shuffle(list);


        return list;
    }
    public void choose (int pos) {
      //  item = initData(3 +pos);
       Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                timer(6000+ pos*2000);
            }
        }, 1000);
    }



}
