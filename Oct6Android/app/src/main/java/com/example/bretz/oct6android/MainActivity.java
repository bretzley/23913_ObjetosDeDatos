package com.example.bretz.oct6android;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bretz.oct6android.Objects.Comments;
import com.example.bretz.oct6android.Objects.Posts;
import com.example.bretz.oct6android.Utils.DBHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {

    //RECUPERE MI PROYECTITOOOOOOOOOOOOOOOOOOOO :D
    TextView txtView1, txtView2, txtId;
    EditText edtUser, edtTitle, edtBody;
    Button btnSyncPosts, btnSyncComments, btnViewPosts, btnViewComments, btnClear, btnAsyncTask, btnExpand;
    DBHelper db;
    ArrayList<Posts> posts;
    ArrayList<Comments> comments;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this);
        txtView1 = (TextView) findViewById(R.id.txtView1);
        txtView2 = (TextView) findViewById(R.id.txtView2);
        txtId = (TextView) findViewById(R.id.txtId);
        edtUser = (EditText) findViewById(R.id.edtUser);
        edtTitle = (EditText) findViewById(R.id.edtTitle);
        edtBody = (EditText) findViewById(R.id.edtBody);
        btnAsyncTask = (Button) findViewById((R.id.btnAsyncTask));
        btnSyncPosts = (Button) findViewById((R.id.btnSyncP));
        btnSyncComments = (Button) findViewById((R.id.btnSyncC));
        btnViewPosts = (Button) findViewById((R.id.btnViewP));
        btnViewComments = (Button) findViewById((R.id.btnViewC));
        btnExpand = (Button) findViewById(R.id.btnExpand);
        btnClear = (Button) findViewById((R.id.btnClear));
        posts = new ArrayList<>();
        comments = new ArrayList<>();
        final String postsURL = "http://jsonplaceholder.typicode.com/posts";
        String commentsURL = "http://jsonplaceholder.typicode.com/comments";
        String newURL = "http://107.170.247.123:20403/posts";
        /*progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);*/
        final RequestQueue queue = Volley.newRequestQueue(this);



        final JsonArrayRequest postsArrayRequest = new JsonArrayRequest(postsURL,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                        posts = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                posts.add(createPost(response.getString(i)));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txtView1.setText(error.toString());
                    }
                });


        final JsonArrayRequest commentsArrayRequest = new JsonArrayRequest(commentsURL,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                comments = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        comments.add(createComment(response.getString(i)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                txtView1.setText(error.toString());
            }
        });


        try {
            final JSONObject jsonbody = new JSONObject("{\"title\":\"test\"}");


            final JsonObjectRequest jsonPostRequest = new JsonObjectRequest
                    (Request.Method.POST, newURL, jsonbody, new Response.Listener<JSONObject>(){
                        @Override
                        public void onResponse(JSONObject response) {
                           txtView1.setText(response.toString());
                            try {
                                jsonbody.put("id",null);
                                jsonbody.put("userId", Integer.parseInt(edtUser.getText().toString()));
                                jsonbody.put("title", edtTitle.getText().toString());
                                jsonbody.put("body", edtBody.getText().toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener(){
                            @Override
                            public void onErrorResponse(VolleyError error){
                                txtView1.setText(error.toString());
                            }
                        });
            queue.add(jsonPostRequest);
        } catch (JSONException e) {
            txtView1.setText(e.toString());
            e.printStackTrace();
        }



        btnSyncPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Posts are being synchronized...", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                db.open();
                db.deleteAllPosts();
                db.close();
                queue.add(postsArrayRequest);
                Snackbar.make(view, "Posts were synchronized successfully!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        });

        btnSyncComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Comments are being synchronized...", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                db.open();
                db.deleteAllComments();
                db.close();
                queue.add(commentsArrayRequest);
                Snackbar.make(view, "Comments were synchronized successfully!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        });

        btnViewPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PostsActivity.class);
                db.open();
                intent.putExtra("Parcel", db.getAllPosts());
                db.close();
                startActivity(intent);
            }
        });


        btnViewComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CommentsActivity.class);
                db.open();
                intent.putExtra("Parcel", db.getAllComments());
                db.close();
                startActivity(intent);
            }
        });


        btnExpand.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intentC = new Intent(getApplicationContext(), ExpandableListActivity.class);
                Snackbar.make(v, "Data is being synchronized...", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                db.open();
                posts = db.getAllPosts();
                comments = db.getAllComments();
                db.close();
                //db.deleteAllPosts();
                //db.deleteAllComments();
                //queue.add(postsArrayRequest);
                //queue.add(commentsArrayRequest);
                intentC.putExtra("Posts", posts);
                intentC.putExtra("Comments", comments);


                startActivity(intentC);
            }
        });


        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.open();
                db.deleteAllPosts();
                db.deleteAllComments();
                db.close();
                Snackbar.make(view, "All the data has been cleared.", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        });


        btnAsyncTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //new AsyncTaksActivity.HttpAsyncTask().execute("http://jsonplaceholder.typicode.com/posts/15");
                //Intent intent = new Intent(getApplicationContext(), AsyncTaksActivity.class);
                //startActivity(intent);
            }
        });
        /*btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.open();
                db.close();

                Snackbar.make(view, "Your input has been posted!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        });*/
    }







    public Posts createPost(String response) {
        Posts post = new Posts();
        try {
            JSONObject jsonObject = new JSONObject(response);
            int userId = jsonObject.getInt("userId");
            int id = jsonObject.getInt("id");
            String title = jsonObject.getString("title");
            String body = jsonObject.getString("body");
            db.open();
            post = db.addPost(id, userId, title, body);
            db.close();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return post;
    }

    public Comments createComment(String response) {
        Comments comment = new Comments();
        try {
            JSONObject jsonObject = new JSONObject(response);
            int postId = jsonObject.getInt("postId");
            int id = jsonObject.getInt("id");
            String name = jsonObject.getString("name");
            String email = jsonObject.getString("email");
            String body = jsonObject.getString("body");
            db.open();
            comment = db.addComment(id, postId, name, email, body);
            db.close();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return comment;
    }


   /* public static String getHTTPRequest(String url) {
        URL obj = null;
        HttpURLConnection con = null;
        try {
            obj = new URL(url);
            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return response.toString();
            } else {
                return "POST request did not work.";
            }
        } catch (IOException e) {
            return e.getMessage();
        }
    }


    private class HttpAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... urls) {
            return getHTTPRequest(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(), "Received!" + result, Toast.LENGTH_LONG).show();

        }
    }*/




}
