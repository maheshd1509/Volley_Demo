package in.mahesh.volley_demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mahesh on 2/1/18.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Users> mUserList;

    public UserAdapter(Context mContext, ArrayList<Users> mUserList) {
        this.mContext = mContext;
        this.mUserList = mUserList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(mContext).inflate(R.layout.layout_adapter,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       holder.mtxt_first_name.setText(mUserList.get(position).getmFirst_Name());
        holder.mtxt_last_name.setText(mUserList.get(position).getmLast_Name());
        Picasso.with(mContext).load(mUserList.get(position).getmAvatar()).into(holder.mimageView);


    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mimageView;
        private TextView mtxt_first_name,mtxt_last_name;


        public ViewHolder(View view) {
            super(view);

            mimageView=view.findViewById(R.id.imageView);
            mtxt_first_name=view.findViewById(R.id.txt_first_Name);
            mtxt_last_name=view.findViewById(R.id.txt_last_Name);
        }
    }
}
