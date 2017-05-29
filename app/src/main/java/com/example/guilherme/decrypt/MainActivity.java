package com.example.guilherme.decrypt;

import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private  TextView textView;


    private String getTexto(){
        return "dcqvzmlgdbofubvlrxclaoseqozwboqpuairuelzpohcrlnzgjphisqvzphrovxxzrxqvjfa\n" +
                "\n" +
                "fjfnxuyklwyludyoshuzhpkvdbpjbuelapzsjqahzrfbwvcahcpzpdfjqvavlrhzceyiqluo\n" +
                "\n" +
                "wbtvnloahibdvibfnrlnzgjphisdcydhiyzfqactoswpsibvuxuyiesmpdanzjdoovbbzdps\n" +
                "\n" +
                "vbedvyhxkosllduiqacpqwohhsbcpahisdcyopvdrfaactiwbsafmcnqozsxffylsnufklnx\n" +
                "\n" +
                "poionoyqbiszsqvzwpgchexfvcahcpkamyobznwbqvwseybjdoopkrosleqsofubpkroslmj\n" +
                "\n" +
                "fofublbfzeoeawspaypvuzylsdgbkkbdagvtklzhuelmtysvqprhonbuoxkhcptiygulmowb\n" +
                "\n" +
                "kvelrnooaszifhytciysuelrpqsmdmrjauelrpqsmdneistfzvcaujotaysuelaxoavxlnoo\n" +
                "\n" +
                "oakdkfrzoahzkbqlmhtvdnowzffruytohcpmimabjliicfjxahzkbqlmhtvdnowzffxijkbh\n" +
                "\n" +
                "cpmimabjliixbytawvgtlnzcbgddhnyupajvaisyeoeawsjhhtkhcphvzbbkkowbsqpuiiub\n" +
                "\n" +
                "kkowbajcuiiuxbyziesnpjoirexfbtksntzairhlknpfrgpatcsxxazgpiiolrovfelvkbbw\n" +
                "\n" +
                "pnaovfolyiluzeoemioqvjcbdgljevbeiloiesycflvbexwktxfvykiokbpzjvbbzdpsvbed\n" +
                "\n" +
                "vyrxzgpktcseofgpkrzlytcooaactdooslrdbhqvbtqvzcvfovfthotogxlslzribzzppoio\n" +
                "\n" +
                "noygbtacpqwohhsbcpanzcbgddhnyupazvxazzeahzsboacqowirmomhidyvhphcpoempzfl\n" +
                "\n" +
                "gsfbbdleyooaactcfptatmsfvpzaawirmrpwuxmotovddriirxevntpszopsdbjqzzacikzu\n" +
                "\n" +
                "tcsfxyowxbytawvgtlnzcbgddhnyhiblvgqvwcvubvucvmieumlzsvbeelmqvwzwkiiutbly\n" +
                "\n" +
                "pchzcoinyjkkvcahcparzszflgsfbbqyudhxevntpszodanwofantitvqaemvjprdcaoiono\n" +
                "\n" +
                "ygbtacpqwohhsbcpanzcbgddhnyhiblqtkwirhnyhibtjgkwirdemsuelowffyohybsobzdh\n" +
                "\n" +
                "xbyrvdnojasziqvzclbzzjdoohfboslfdfnxtzcqcaeoecsbsliilrdgpdzhibkvncfjxahz\n" +
                "\n" +
                "bjdoopkrgpatcsnyladogdrusvbecvmhbonzusvbecvmsxmnludtsbozbtksntzairmbaowb\n" +
                "\n" +
                "awpmomzjdoohfboslfdfnxtzcqcaeoecsbsliiludgllduiqbkdkhcplamhixuyxqkvdzobs\n" +
                "\n" +
                "obzdhxbyrvdhoebarddfzlalduiqzowbumphtzfmfnciqcmfseovfahtpkrosllzgtbygxdv";
    }


    private String getCodeBase(){
        return "mysterious-earth-87823.herokuapp.com";
    }

    private String http(String s) throws JSONException, IOException {
        String ret = "";
        try
        {
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("https");
            builder.authority(getCodeBase())
                    .appendPath("welcome")
                    .appendPath("find");
            //builder.appendQueryParameter("text", getTexto());

            URL url = new URL(builder.build().toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            JSONObject jsonBody = new JSONObject();
            jsonBody.put("text",getTexto());
            String body = jsonBody.toString();

            OutputStream output = connection.getOutputStream();
            DataOutputStream os = new DataOutputStream(output);
            os.writeBytes(body);
            os.flush();
            os.close();

            connection.connect();

            String response = "";

            InputStream iStream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(iStream, "utf8"));
            StringBuffer sb = new StringBuffer();
            String line = "";

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            response = sb.toString();
            Log.d("resp",response);

            JSONObject json = new JSONObject(response);
            ret = json.getString("text");
            return ret;

        }
        catch (IOException e)
        {
            Log.e("erroDebug", e.getMessage());
        }

        return ret;
    }

    private void runThread(){
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String input = editText.getText().toString();
                input = getTexto();
                input = input.replace(" ","").replace("\n","");
                String result = null;
                try {
                    result = http(input);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                textView.setText(result);
            }
        };/*
                new Thread(){
                        public void run() {
                            runOnUiThread(runnable);
                    }
                }.start();*/
        runOnUiThread(runnable);
    }


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editText = (EditText) findViewById(R.id.editText);

        textView = (TextView) findViewById(R.id.textView);

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                runThread();
            }
        });

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
