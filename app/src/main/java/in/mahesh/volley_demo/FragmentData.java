package in.mahesh.volley_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mahesh on 2/1/18.
 */

public class FragmentData extends Fragment {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private RequestQueue mRequestQueue;
    private UserAdapter mUserAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_data,null);

        initViews(view);
        getData();

        return view;


    }


    private void initViews(View view) {

        mRecyclerView=view.findViewById(R.id.RecyclerView);
        mLinearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

    }

    private void getData() {

        String url=getResources().getString(R.string.url);
        mRequestQueue=Volley.newRequestQueue(getActivity());

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                ArrayList<Users> mUserList=new ArrayList<>();

                try {
                    JSONArray jsonArray=response.getJSONArray("data");
                    for (int i=0;i<jsonArray.length();i++){

                        JSONObject jsonObject=jsonArray.getJSONObject(i);

                        Users users=new Users();
                        users.setmFirst_Name(jsonObject.getString("first_name"));
                        users.setmLast_Name(jsonObject.getString("last_name"));
                        users.setmAvatar(jsonObject.getString("avatar"));
                        mUserList.add(users);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                mUserAdapter=new UserAdapter(getActivity(),mUserList);
                mRecyclerView.setAdapter(mUserAdapter);
                mUserAdapter.notifyDataSetChanged();



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(),"Error while fetching Data",Toast.LENGTH_SHORT).show();
            }
        });

        mRequestQueue.add(jsonObjectRequest);
    }


}
