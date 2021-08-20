package savchits.com.memory;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import savchits.com.memory.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {
    ActivityResultBinding binding;
    int                   score = 0;
    int                   life = 5;
    int                   value=5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new Thread(new Runnable() {
            public void run() {

                Intent intent = getIntent();
                String word1 = intent.getStringExtra("word1");
                String word2 = intent.getStringExtra("word2");
                String word3 = intent.getStringExtra("word3");
                String word4 = intent.getStringExtra("word4");
                String word5 = intent.getStringExtra("word5");

                //   binding.text1.setText(word1);
                binding.text1.setText(word1);
                binding.text2.setText(word2);
                binding.text3.setText(word3);
                binding.text4.setText(word4);
                binding.text5.setText(word5);

                //get datas from memory activity

                SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
                value= sharedPreferences.getInt("life", 0);
                String value1 = sharedPreferences.getString("mword1", "");
                String value2 = sharedPreferences.getString("mword2", "");
                String value3 = sharedPreferences.getString("mword3", "");
                String value4 = sharedPreferences.getString("mword4", "");
                String value5 = sharedPreferences.getString("mword5", "");
                binding.imageButton.setText(value +"");


                if ((value1.toLowerCase()).equals(word1.toLowerCase())) {
                    binding.image1.setImageResource(R.drawable.check);
                    score++;
                    binding.tvScore.setText(score + "/5");
                }
                if ((value2.toLowerCase()).equals(word2.toLowerCase())) {
                    binding.image2.setImageResource(R.drawable.check);
                    score++;
                    binding.tvScore.setText(score + "/5");
                }
                if ((value3.toLowerCase()).equals(word3.toLowerCase())) {
                    binding.image3.setImageResource(R.drawable.check);
                    score++;
                    binding.tvScore.setText(score + "/5");
                }
                if ((value4.toLowerCase().toLowerCase()).equals(word4.toLowerCase())) {
                    binding.image4.setImageResource(R.drawable.check);
                    score++;
                    binding.tvScore.setText(score + "/5");
                }
                if ((value5.toLowerCase()).equals(word5.toLowerCase())) {
                    binding.image5.setImageResource(R.drawable.check);
                    score++;
                    binding.tvScore.setText(score + "/5");

                }
            }


            //do time consuming operations
        }).start();

        binding.tomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LevelPage.class));
                finish();
              /*  FragmentTransaction ft = getFragmentManager().beginTransaction();
                LevelPage fragment = new LevelPage();
                ft.add(R.id., fragment);
                ft.commit();*/
            }
        });
        binding.againGameF.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    new Thread(new Runnable() {
                        public void run() {
                            value--;
                            if (value <= 0) {
                                value =5;
                                Toast.makeText(ResultActivity.this, "Вы успешно прошли!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LevelPage.class);
                                startActivity(intent);
                                finish();
                              /*  LevelPage frag2 = new LevelPage();
                                FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
                                fragmentTransaction.replace(R.id.frame,frag2);
                                fragmentTransaction.commit();*/
                                finish();
                            }
                            if (value > 0 && value <= 5) {
                                    binding.imageButton.setText(value + "");
                                    binding.progressBar.setProgress(value * 20);
                                    SharedPreferences sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPref.edit();
                                    editor.putInt("life", value);
                                    editor.apply();

                             /*   LevelPage frag2 = new LevelPage();
                                FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
                                fragmentTransaction.replace(R.id.frame,frag2);
                                fragmentTransaction.commit();*/
                              //  finish();
                                    Intent intent2 = new Intent(ResultActivity.this, Memorization.class);
                                    startActivity(intent2);
                                    finish();
                            } else {
                                    Toast.makeText(ResultActivity.this, "Вы успешно прошли!", Toast.LENGTH_SHORT).show();
                                    Intent intent2 = new Intent(getApplicationContext(), LevelPage.class);
                                    startActivity(intent2);
                                    finish();
                                }
                            }

                    }).start();
            }

        });
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}