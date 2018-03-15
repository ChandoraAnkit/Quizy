package androidy.chandora.ankit.quizy;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by chandora on 15/3/18.
 */

public class VolleySingleton {

    private static VolleySingleton mInstance;
    private static Context mContext;
    private RequestQueue mRequestQueue;

    private VolleySingleton(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }
    public static synchronized VolleySingleton getInstance(Context context){
        if (mContext == null){
            mInstance = new VolleySingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }
    public <T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }
}
