package com.example.jefin.jsontocustumlistview;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button button;
    String Jsonstring;
    Button parse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=(Button)findViewById(R.id.download);
        parse=(Button)findViewById(R.id.parse);
        textView=(TextView)findViewById(R.id.text);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Json().execute("http://www.filltext.com/?rows=10&id={index}&email={email}&username={username}&password={randomString|5}&pretty=true");

            }
        });


    }

    public class Json extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;

            BufferedReader reader;

            try {
                URL url= new URL(params[0]);

                connection =(HttpURLConnection)url.openConnection();
                connection.connect();

                InputStream stream= connection.getInputStream();


                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer stringBuffer=new StringBuffer();
                String line="";

                while((line = reader.readLine())!=null) {

                    stringBuffer.append(line);
                }



              /*  String jsonBuffer = stringBuffer.toString();
                StringBuilder stringBuilder= new StringBuilder();
                JSONArray jsonArray = new JSONArray(jsonBuffer);
                for(int i=0;i<jsonArray.length();i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    //int id= jsonObject.getInt("id");
                    String email= jsonObject.getString("email");
                    String username= jsonObject.getString("username");
                    // String password= jsonObject.getString("password");


                    stringBuilder.append("Name: "+username+" Email: "+email+"\n");

                }

                return stringBuilder.toString();

             */
                return stringBuffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            /*if(connection !=null)
            {
                connection.disconnect();
            }
           */

            return null;

        }

        @Override
        protected void onPostExecute(String result) {


            super.onPostExecute(result);
            textView.setText(result);
            Jsonstring=result;
        }
    }

    public  void parseJson(View view)
    {

        Intent intent= new Intent(this,DisplyActivity.class);
        intent.putExtra("JsonData",Jsonstring);
        startActivity(intent);
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
