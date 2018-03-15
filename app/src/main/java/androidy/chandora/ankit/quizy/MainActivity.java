package androidy.chandora.ankit.quizy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ramotion.foldingcell.FoldingCell;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidy.chandora.ankit.quizy.Adapter.CategoryAdapter;
import androidy.chandora.ankit.quizy.Pojo.Category;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ArrayList<Category> categories;
    private CategoryAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categories = new ArrayList<>();

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        String url = Config.CATEGORY_LIST;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray root = response.getJSONArray("trivia_categories");

                            for(int i=0;i<root.length();i++){
                                JSONObject child = root.getJSONObject(i);
                                Category category = new Category(child.get("name").toString());
                                categories.add(category);
                            }
                            mAdapter = new CategoryAdapter(categories);
                            mRecyclerView.setAdapter(mAdapter);

                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, "Server error!", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                });


        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }


}
