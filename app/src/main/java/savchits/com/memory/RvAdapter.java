package savchits.com.memory;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.style.AlignmentSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RvAdapter  extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    List<String> item;
    Context      context;
    int pos;

    public RvAdapter(List<String> item, Context context) {
        this.item = item;
        this.context = context;
    }
    public RvAdapter() {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (context).inflate ( R.layout.list_item, parent, false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


                holder.tv1.setText(item.get(0));
                holder.tv2.setText(item.get(1));
                holder.tv3.setText(item.get(2));
                holder.tv4.setText(item.get(3));
                holder.tv5.setText(item.get(5));

        }

    @Override
    public int getItemCount() {
        return item.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
            tv3 = itemView.findViewById(R.id.tv3);
            tv4 = itemView.findViewById(R.id.tv4);
            tv5 = itemView.findViewById(R.id.tv5);
            tv6 = itemView.findViewById(R.id.tv6);
            tv7 = itemView.findViewById(R.id.tv7);
        }
    }


}