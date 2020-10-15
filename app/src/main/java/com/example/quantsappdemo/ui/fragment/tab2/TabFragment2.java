package com.example.quantsappdemo.ui.fragment.tab2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
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
import com.example.quantsappdemo.app.App;
import com.example.quantsappdemo.databinding.FragmentMainBinding;
import com.example.quantsappdemo.ui.fragment.tab2.core.Fragment2Presenter;
import com.example.quantsappdemo.ui.fragment.tab2.di.DaggerFragment2Component;
import com.example.quantsappdemo.ui.fragment.tab2.di.Fragment2Module;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.inject.Inject;

public class TabFragment2 extends Fragment implements Fragment2MVP.View {

    private View rootView;
    FragmentMainBinding binding;

    @Inject
    Fragment2Presenter presenter;

    String hostingUrl;

    Context context;

    boolean isDownload;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependency();
        context = getContext();
    }

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        rootView = binding.getRoot();
        initView(rootView);

//        binding.webView.addJavascriptInterface(new WebAppInterface(getContext()), "Android");
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

        final String path = "https://qapptemporary.s3.ap-south-1.amazonaws.com/ritesh/zip_files/44418/Annexure123456&7_FO.xls";
        binding.webView.loadUrl(path);

       /* Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(path));
        startActivity(intent);*/


        binding.fbDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] permissions = {Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                if (!hasPermission()) {
                    ActivityCompat.requestPermissions(getActivity(), permissions, 1);
                } else {
                    if (path.endsWith(".xls")) {
                        MyAsyncTask myAsyncTask = new MyAsyncTask();
                        myAsyncTask.execute(path);
                    }
                }
            }
        });
//        setUpWebview(binding.webView);



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

    @Override
    public void initView(View rootView) {

    }

    @Override
    public void injectDependency() {
        DaggerFragment2Component.builder()
                .appComponent(App.getAppComponent())
                .fragment2Module(new Fragment2Module(this))
                .build()
                .inject(this);

    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void setUpWebview(WebView webView) {



    }



    private class MyBrowser extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.endsWith(".xls")) {
                Toast.makeText(context, "url: " + url, Toast.LENGTH_SHORT).show();
            }
            view.loadUrl(url);

            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
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
//            mContainer.addView(mWebviewPop);
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
                //URL url = new URL(DownloadUrl);
                URLConnection conection = url.openConnection();
                conection.connect();
                // getting file length
                int lenghtOfFile = conection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream to write file
                OutputStream output = new FileOutputStream(file);

                byte data[] = new byte[1024];

                long total = 0;

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
}
