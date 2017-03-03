package cn.edu.gdmec.s07150808.myasynctask;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AsyncTackDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_tack_demo);
        new UpdateTitle(this).execute(1);
    }

    class UpdateTitle extends AsyncTask<Integer, Integer, Void> {
        Activity context;

        public UpdateTitle(Activity context) {
            this.context = context;
        }

        @Override
        protected Void doInBackground(Integer... params) {
            int num = params[0].intValue();

            while (true) {
                num++;
                publishProgress(num);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            String sTitle = String.valueOf(values[0]);
            context.setTitle(sTitle);
            super.onProgressUpdate(values);
        }

    }


}
