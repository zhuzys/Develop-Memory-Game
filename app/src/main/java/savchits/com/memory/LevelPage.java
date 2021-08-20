package savchits.com.memory;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import savchits.com.memory.databinding.ActivityResultBinding;

public class LevelPage extends AppCompatActivity {
    ArrayList<LevelModel> levelsList;
    View view;
    Button questionBtn;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels_activity);
       // View view= inflater.inflate(R.layout.levels_activity, container, false);

      //  view = inflater.inflate ( R.layout.levels_activity, container, false);
        levelsList = new ArrayList<> ();
        levelsList.add(new LevelModel("Уровень 1", R.drawable.level_1));
        levelsList.add(new LevelModel("Уровень 2", R.drawable.nav_planet));
        recyclerView = findViewById(R.id.recyclerView);
        // создаем адаптер
        LevelAdapter adapter = new LevelAdapter(getApplicationContext(),levelsList);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager (getApplicationContext());
        recyclerView.setLayoutManager (linearLayoutManager);
        recyclerView.setHasFixedSize (true);


        questionBtn = findViewById (R.id.questionBtn);
        questionBtn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), QuestionPage.class);
                startActivity(intent);
            }
        } );



        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(),recyclerView,
                                              new RecyclerItemClickListener.OnItemClickListener() {
                                                  @Override
                                                  public void onItemClick(View view, final int pos) {
                                                      if(pos == 0){
                                                          Log.d("1 Level", "touch!");
                                                          Dialog dialog = new Dialog (getApplicationContext());
                                                          dialog.setContentView (R.layout.dialog_activity);
                                                          dialog.getWindow ().setBackgroundDrawable(new ColorDrawable(0));

                                                          Button btn_start;
                                                          btn_start = dialog.findViewById (R.id.startLevel);
                                                          btn_start.setOnClickListener ( new View.OnClickListener () {
                                                              @Override
                                                              public void onClick(View v) {
                                                                  Intent intent = new Intent (v.getContext (),Memorization.class);
                                                                  startActivity ( intent );

                                                              }
                                                          });

                                                          dialog.getWindow ().setWindowAnimations (R.style.AnimationDialog);
                                                          dialog.show ();
                                                      }else if(pos == 1){

                                                          Log.d("2 Level", "touch!");
                                                          Dialog dialog = new Dialog (getApplicationContext());
                                                          dialog.setContentView (R.layout.dialog_activity);
                                                          dialog.getWindow ().setBackgroundDrawable (new ColorDrawable(1));

                                                          Button btn_start;
                                                          btn_start = dialog.findViewById (R.id.startLevel);
                                                          btn_start.setOnClickListener ( new View.OnClickListener () {
                                                              @Override
                                                              public void onClick(View v) {
                                                                 Intent intent = new Intent (v.getContext (),Memorization.class);
                                                                  startActivity ( intent );
                                                              }
                                                          } );

                                                          dialog.getWindow ().setWindowAnimations (R.style.AnimationDialog);
                                                          dialog.show ();
                                                      }
                                                  }

                                                  @Override
                                                  public void onLongItemClick(View view, int pos) {

                                                  }
                                              })
                                           );
        setInitialData();
    }
    private void setInitialData(){

        levelsList.add(new LevelModel ("Уровень 1",  R.drawable.level_1));
        levelsList.add(new LevelModel ("Уровень 2",  R.drawable.level_1));
        levelsList.add(new LevelModel ("Уровень 3",  R.drawable.level_1));
        levelsList.add(new LevelModel ("Уровень 4",  R.drawable.level_1));
    }
}




