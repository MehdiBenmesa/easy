package dz.easy.androidclient.Util;

        import com.android.volley.NetworkResponse;
        import com.android.volley.ParseError;
        import com.android.volley.Request;
        import com.android.volley.Response;
        import com.android.volley.Response.ErrorListener;
        import com.android.volley.Response.Listener;
        import com.android.volley.toolbox.HttpHeaderParser;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.UnsupportedEncodingException;
        import java.util.Map;

public class CustomRequestNote extends Request<JSONObject> {

    private Response.Listener<JSONObject> listener;
    private Map<String, JSONArray> params;

    public CustomRequestNote(String url, Map<String, JSONArray> params,
                         Response.Listener<JSONObject> reponseListener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.listener = reponseListener;
        this.params = params;
    }

    public CustomRequestNote(int method, String url, Map<String, JSONArray> params,
                         Response.Listener<JSONObject> reponseListener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.listener = reponseListener;
        this.params = params;
    }
/*
    protected Map<String, String> getParams()
            throws com.android.volley.AuthFailureError {
        return params;
    };*/

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        // TODO Auto-generated method stub
        listener.onResponse(response);
    }
}