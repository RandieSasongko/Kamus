package com.pab2.kamus.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.pab2.kamus.R;
import com.pab2.kamus.databases.KamusHelper;
import com.pab2.kamus.databinding.ActivitySplashBinding;
import com.pab2.kamus.models.Kamus;
import com.pab2.kamus.utilities.AppPreference;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new LoadData().execute();
    }

    private class LoadData extends AsyncTask<Void, Integer, Void> {
        final String TAG = LoadData.class.getSimpleName();
        KamusHelper kamusHelper;
        AppPreference appPreference;
        double progress;
        double maxProgress = 100;

        @Override
        protected void onPreExecute() {
            kamusHelper = new KamusHelper(SplashActivity.this);
            appPreference = new AppPreference(SplashActivity.this);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            binding.progressBar.setProgress(values[0]);
            binding.tvLoading.setText("Loading "+values[0]+"% ...");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Boolean firstRun = appPreference.getFirstRun();

            if (firstRun) {
                ArrayList<Kamus> kamusEnglishIndonesia = preLoadRawEnglishIndonesia();
                kamusHelper.open();

                double progressMaxInsert = 80.0;
                double progressDiff = (progressMaxInsert - progress) / kamusEnglishIndonesia.size();
                progress = 30;
                publishProgress((int) progress);

//                for (Kamus kamus : kamusEnglishIndonesia) {
//                    kamusHelper.insertDataEnglishIndonesia(kamus);
//                    progress += progressDiff;
//                    publishProgress((int) progress);
//                }

                kamusHelper.beginTransaction();
                try {
                    for (Kamus kamus : kamusEnglishIndonesia) {
                        kamusHelper.insertDataEnglishIndonesia(kamus);
                        progress += progressDiff;
                        publishProgress((int) progress);
                    }
                }catch(Exception e){
                    //e.printStackTrace();
                    Log.e(TAG, "doInBackground: Exception "+e.getMessage());
                }
                kamusHelper.endTransaction();

                kamusHelper.close();
                appPreference.setFirstRun(false);
                publishProgress((int) maxProgress);
            } else {
                try {
                    synchronized (this) {
                        this.wait(1000);
                        publishProgress(50);

                        this.wait(1000);
                        publishProgress((int) maxProgress);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        public ArrayList<Kamus> preLoadRawEnglishIndonesia() {
            ArrayList<Kamus> kamusArrayList = new ArrayList<>();
            String line = null;

            BufferedReader bufferedReader;

            try {
                Resources resources = getResources();
                InputStream raw_dictionary = resources.openRawResource(R.raw.english_indonesia);

                bufferedReader = new BufferedReader(new InputStreamReader(raw_dictionary));

                int count = 0;
                do {
                    line = bufferedReader.readLine();
                    String[] splitted = line.split("\t");

                    Kamus kamus = new Kamus(splitted[0], splitted[1]);
                    kamusArrayList.add(kamus);
                    count++;
                } while (line != null);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return kamusArrayList;
        }

        @Override
        protected void onPostExecute(Void unused) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}