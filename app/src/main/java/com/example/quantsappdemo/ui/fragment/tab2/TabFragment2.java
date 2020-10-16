package com.example.quantsappdemo.ui.fragment.tab2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.quantsappdemo.R;
import com.example.quantsappdemo.databinding.FragmentMainBinding;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TabFragment2 extends Fragment {

    private View rootView;
    FragmentMainBinding binding;


    Context context;
    ProgressDialog pd;
    boolean isDownload;
    String path = "https://qapptemporary.s3.ap-south-1.amazonaws.com/ritesh/zip_files/44418/Annexure123456&7_FO.xls";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        rootView = binding.getRoot();


        String[] permissions = {Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (!hasPermission()) {
            ActivityCompat.requestPermissions(getActivity(), permissions, 1);

        } else {
            Toast.makeText(context, "Permission granted", Toast.LENGTH_SHORT).show();
        }

        pd= ProgressDialog.show(context, "", "Please wait..", true);
        pd.show();

        binding.webView.setWebViewClient(new MyBrowser());
        binding.webView.setWebChromeClient(new UriWebChromeClient());
        WebSettings webSettings = binding.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setDomStorageEnabled(true);

        webSettings.setUseWideViewPort(true);
        webSettings.setLoadsImagesAutomatically(true);
        try {
            binding.webView.loadUrl("https://docs.google.com/gview?embedded=true&url="
                    + URLEncoder.encode("https://qapptemporary.s3.ap-south-1.amazonaws.com/ritesh/zip_files/44418/Annexure123456&7_FO.xls", "ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        binding.fbDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (path.endsWith(".xls")) {
                    MyAsyncTask myAsyncTask = new MyAsyncTask();
                    myAsyncTask.execute(path);
                }
            }
        });

        return rootView;
    }

    private boolean hasPermission() {
        int internet  = ContextCompat.checkSelfPermission(context, Manifest.permission.INTERNET);
        int write_external = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int read_external = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
        return internet == PackageManager.PERMISSION_GRANTED &&
                write_external == PackageManager.PERMISSION_GRANTED &&
                read_external == PackageManager.PERMISSION_GRANTED;
    }


    private class MyBrowser extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            pd.dismiss();
            super.onPageFinished(view, url);
        }
    }

    private class UriWebChromeClient extends WebChromeClient {

        @SuppressLint("SetJavaScriptEnabled")
        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
            view = new WebView(context);
            binding.webView.setVerticalScrollBarEnabled(false);
            binding.webView.setHorizontalScrollBarEnabled(false);
            binding.webView.setWebViewClient(new MyBrowser());
            binding.webView.getSettings().setJavaScriptEnabled(true);
            binding.webView.getSettings().setSavePassword(false);
            binding.webView.getSettings().setSupportMultipleWindows(true);
            binding.webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            binding.webView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
            transport.setWebView(binding.webView);
            resultMsg.sendToTarget();

            return true;
        }

        @Override
        public void onCloseWindow(WebView window) {
            Log.d("onCloseWindow", "called");
        }

    }


    private class MyAsyncTask extends AsyncTask<String, String ,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        @Override
        protected String doInBackground(String... strings) {
            String fileName = strings[0].substring(strings[0].lastIndexOf('/') + 1, strings[0].length());
            DownloadFromUrl(strings[0], fileName);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (isDownload) {

                Toast.makeText(context, "File Download Completed!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void DownloadFromUrl(String string, String fileName) {
        int count;

        try {

            File root = android.os.Environment.getExternalStorageDirectory();

            File dir = new File(root.getAbsolutePath());

            if (!dir.exists()) {
                dir.mkdirs();
            }
            URL url = new URL(string); //you can write here any link
            File file = new File(dir, fileName);

            if (file.exists()) {
                isDownload = true;
            } else {
                URL url1 = new URL(string);
                HttpURLConnection conection = (HttpURLConnection) url.openConnection();
                conection.setRequestMethod("GET");
                conection.connect();

                if (conection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    Log.d("Log16", "response: " + conection.getResponseCode() + " " + conection.getResponseMessage());
                }


                // getting file length
                int lenghtOfFile = conection.getContentLength();

                FileOutputStream fileOutputStream = new FileOutputStream(file);

                InputStream inputStream = conection.getInputStream();
                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream to write file
                OutputStream output = new FileOutputStream(file);

                byte data[] = new byte[1024];

                int total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    //   publishProgress(""+(int)((total*100)/lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();
                //Uri fileUri = Uri.parse(file.getPath());
                isDownload = true;




            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class CheckForSDCard {
        //Check If SD Card is present or not method
        public boolean isSDCardPresent() {
            if (Environment.getExternalStorageState().equals(

                    Environment.MEDIA_MOUNTED)) {
                return true;
            }
            return false;
        }
    }
}
