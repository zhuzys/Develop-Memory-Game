package savchits.com.memory;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LevelPage extends Fragment {
    ArrayList<LevelModel> levelsList;
    View view;
    Button questionBtn;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager linearLayoutManager;
    Memorization memorization;

    public LevelPage() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        view = inflater.inflate ( R.layout.levels_activity, container, false);
        levelsList = new ArrayList<> ();
        recyclerView = view.findViewById(R.id.recyclerView);
        // создаем адаптер
        LevelAdapter adapter = new LevelAdapter(getActivity (), levelsList);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager ( getActivity());
        recyclerView.setLayoutManager (linearLayoutManager);
        recyclerView.setHasFixedSize (true);




        questionBtn = view.findViewById (R.id.questionBtn);
        questionBtn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getActivity (), QuestionPage.class);
                startActivity(intent);
            }
        } );




        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(),recyclerView,
                                              new RecyclerItemClickListener.OnItemClickListener() {
                                                  @Override
                                                  public void onItemClick(View view, final int pos) {
                                                   //   memorization.chooseLevel(0);
                                                      switch (pos) {

                                                          case 0:
                                                              Dialog dialog;
                                                              dialog = new Dialog(getActivity());
                                                              dialog.setContentView(R.layout.dialog_activity);
                                                              dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));

                                                              Button btn_start;
                                                              btn_start = dialog.findViewById(R.id.startLevel);
                                                              btn_start.setOnClickListener(new View.OnClickListener() {
                                                                  @Override
                                                                  public void onClick(View v) {
                                                                      //    memorization.chooseLevel(0);
                                                                      int value = 5;
                                                                      Intent intent = new Intent(v.getContext(), Memorization.class);
                                                                      intent.putExtra("life2", value);
                                                                      intent.putExtra("pos", pos);
                                                                      startActivity(intent);
                                                                  }
                                                              });

                                                              dialog.getWindow().setWindowAnimations(R.style.AnimationDialog);
                                                              dialog.show();
                                                              break;
                                                          case 1:
                                                          //    int position=2;

                                                              dialog = new Dialog(getActivity());
                                                              dialog.setContentView(R.layout.dialog_activity);
                                                              dialog.getWindow().setBackgroundDrawable(new ColorDrawable(1));

                                                              btn_start = dialog.findViewById(R.id.startLevel);
                                                              btn_start.setOnClickListener(new View.OnClickListener() {
                                                                  @Override
                                                                  public void onClick(View v) {
                                                                      int value = 5;
                                                                      //memorization.chooseLevel(1);
                                                                      Intent intent = new Intent(v.getContext(), Memorization.class);
                                                                      intent.putExtra("life2", value);
                                                                      intent.putExtra("pos", pos);
                                                                      startActivity(intent);
                                                                  }
                                                              });

                                                              dialog.getWindow().setWindowAnimations(R.style.AnimationDialog);
                                                              dialog.show();
                                                              break;
                                                      }
                                                      }

                                                      /*}
                                                      (pos == 0){
                                                          Dialog dialog = new Dialog (getActivity ());
                                                          dialog.setContentView (R.layout.dialog_activity);
                                                          dialog.getWindow ().setBackgroundDrawable ( new ColorDrawable (0) );

                                                          Button btn_start;
                                                          btn_start = dialog.findViewById (R.id.startLevel);
                                                          btn_start.setOnClickListener ( new View.OnClickListener () {
                                                              @Override
                                                              public void onClick(View v) {
                                                              //    memorization.chooseLevel(0);
                                                                  int  value =5;
                                                                  Intent intent = new Intent (v.getContext (),Memorization.class);
                                                                  intent.putExtra("life2", value);
                                                                  intent.putExtra("pos",pos);
                                                                  startActivity ( intent );
                                                              }
                                                          } );

                                                          dialog.getWindow ().setWindowAnimations (R.style.AnimationDialog);
                                                          dialog.show ();
                                                      }
                                                      if(pos == 1){
                                                          Dialog dialog = new Dialog (getActivity ());
                                                          dialog.setContentView (R.layout.dialog_activity);
                                                          dialog.getWindow ().setBackgroundDrawable ( new ColorDrawable (0) );

                                                          Button btn_start;
                                                          btn_start = dialog.findViewById (R.id.startLevel);
                                                          btn_start.setOnClickListener ( new View.OnClickListener () {
                                                              @Override
                                                              public void onClick(View v) {
                                                                  int  value =5;
                                                                  //memorization.chooseLevel(1);
                                                                  Intent intent = new Intent (v.getContext (),Memorization.class);
                                                                  intent.putExtra("life2", value);
                                                                  intent.putExtra("pos",pos);
                                                                  startActivity ( intent );
                                                              }
                                                          } );

                                                          dialog.getWindow ().setWindowAnimations (R.style.AnimationDialog);
                                                          dialog.show ();
                                                      } else  {
                                                          Intent intent = new Intent (getActivity(),PremiumPage.class);
                                                          startActivity(intent);
                                                      }
                                                  }
*/
                                                  @Override
                                                  public void onLongItemClick(View view, int pos) {

                                                  }
                                              })
                                           );
        setInitialData();
        return view;
    }
    private void setInitialData(){

        levelsList.add(new LevelModel ("Уровень 1",  R.drawable.lev1));
        levelsList.add(new LevelModel ("Уровень 2",  R.drawable.level_2));
        levelsList.add(new LevelModel ("Уровень 3",  R.drawable.level_3));
        levelsList.add(new LevelModel ("Уровень 4",  R.drawable.level_4));
    }
}




