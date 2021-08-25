package savchits.com.memory;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import savchits.com.memory.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {
    ActivityResultBinding binding;
    int                   score = 0;
    int                   life = 5;
    int                   value=5;
    FrameLayout frameLayout;
    ImageView passed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        frameLayout = findViewById(R.id.fragment);
      //  passed = findViewById(R.id.passed);

        SharedPreferences sharedPreferences = getSharedPreferences ( "myKey", MODE_PRIVATE );
        value = sharedPreferences.getInt("life", 0);
        binding.imageButton.setText(value +"");
        binding.progressBar.setProgress(value*20);


        getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment, new LevelPage ()).commit();




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


                if (value1.equals(word1)) {
                    binding.image1.setImageResource(R.drawable.check);
                    score++;
                    binding.tvScore.setText(score + "/5");
                }
                if (value2.equals(word2)) {
                    binding.image2.setImageResource(R.drawable.check);
                    score++;
                    binding.tvScore.setText(score + "/5");
                }
                if (value3.equals(word3)) {
                    binding.image3.setImageResource(R.drawable.check);
                    score++;
                    binding.tvScore.setText(score + "/5");
                }
                if (value4.equals(word4)) {
                    binding.image4.setImageResource(R.drawable.check);
                    score++;
                    binding.tvScore.setText(score + "/5");
                }
                if (value5.equals(word5)) {
                    binding.image5.setImageResource(R.drawable.check);
                    score++;
                    binding.tvScore.setText(score + "/5");

                }
            }


            //do time consuming operations
        }).start();

        binding.line10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultActivity.this, CheckActivity.class));
            }
        });

        binding.tomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameLayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new LevelPage ()).commit();

            }
        });
        binding.againGameF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        value--;
                        binding.imageButton.setText(value + "");
                        binding.progressBar.setProgress(value * 20);
                        SharedPreferences sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt("life", value);
                        editor.apply();
                        if (value <= 0) {
                            showAlert();
                            Log.d("LOG ZHUZYY", "0 case");
                         //   frameLayout.setVisibility(View.VISIBLE);
                        //    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new LevelPage()).commit();

                        } else {
                            Intent intent = new Intent(ResultActivity.this, Memorization.class);
                            startActivity(intent);
//                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new LevelPage ()).commit();
                        }


                          //  Intent intent = new Intent(getApplicationContext(), LevelPage.class);
                           // intent.putExtra("life", value);
                          //  startActivity(intent);
                         //   startActivity(new Intent(ResultActivity.this, LevelPage.class));
                         //*  value =5;
                          /*  SharedPreferences sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putInt("life",value);
                            editor.apply();*/
                            //startActivity(new Intent(ResultActivity.this, Memorization.class));//*


                       /* } else {
                            binding.imageButton.setText(value + "");
                            binding.progressBar.setProgress(value * 20);
                            SharedPreferences sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putInt("life", value);
                            editor.apply();
                            Intent intent = new Intent(ResultActivity.this, Memorization.class);
                            startActivity(intent);*/
                    }
        });
    }

    public  void showAlert() {
        AlertDialog.Builder adb = new
                AlertDialog.Builder(this);
        // заголовок
        adb.setTitle("Успешно пройдено!");
        // сообщение
        adb.setMessage("Вы успешно прошли! Хотите продолжать?  ");
        // иконка
        adb.setIcon(R.drawable.ic_baseline_info_24);
        // кнопка положительного ответа
        adb.setPositiveButton("Да",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        frameLayout.setVisibility(View.VISIBLE);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new LevelPage()).commit();
                      //  passed.setVisibility(View.VISIBLE);

                    }
                });
        // кнопка отрицательного ответа
        adb.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();

            }
        });
        AlertDialog alertDialog = adb.create();
         alertDialog.show();
    }
}

/*  switch (value) {
 *//*   case 1:
                                Intent intent = new Intent(getApplicationContext(), LevelPage.class);
                                startActivity(intent);*//*
                            case 0:
                              Intent  intent = new Intent(getApplicationContext(), LevelPage.class);
                               startActivity(intent);
                                break;
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                                binding.imageButton.setText(value + "");
                                binding.progressBar.setProgress(value * 20);
                                SharedPreferences sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putInt("life", value);
                                editor.apply();
                                intent = new Intent(ResultActivity.this, Memorization.class);
                                startActivity(intent);
                                break;
                            default:
                                intent = new Intent(getApplicationContext(), LevelPage.class);
                                startActivity(intent);
                                break;


                        }*/