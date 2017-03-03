package cn.edu.gdmec.s07150808.myasynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.inputSrteam);
        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aTask task = new aTask();
                task.execute(editText.getText().toString());
            }
        });
    }
    private class aTask extends AsyncTask{

        Bitmap bitmap = null;
        @Override
        protected Object doInBackground(Object[] params) {
            try {
                URL url = new URL(params[0].toString());

                URLConnection conn = url.openConnection();

                bitmap = BitmapFactory.decodeStream(conn.getInputStream());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Object o) {
            ImageView imageView = (ImageView) findViewById(R.id.pic);
            if( o != null){
              imageView.setImageBitmap((Bitmap)o);
            }else{
                Toast.makeText(MainActivity.this,"网址不存在，或服务器连接失败！",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
