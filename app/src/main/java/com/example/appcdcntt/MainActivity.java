package com.example.appcdcntt;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private AppCompatButton btnVaoLink;
    private AppCompatButton btnGuiBien;
    private AppCompatButton btnCam;

    private EditText edtBienSo;
    private TextView tvBienSo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        btnCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScanCode();
            }
        });
        btnGuiBien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = UUID.randomUUID().toString();
                String ipServer = "https://www.youtube.com";
                String jwtToken = "/watch?v=mthXfqjmB6A" ;
                tvBienSo.setText(id);

                String token = id;
                String bienSo = edtBienSo.getText().toString();

                //
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://reqres.in/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                SImpleApi retrofitAPI = retrofit.create(SImpleApi.class);
                PostBienSo modal = new PostBienSo(token, bienSo);
                Call<PostBienSo> call = retrofitAPI.createPost(modal);
                call.enqueue(new Callback<PostBienSo>() {
                    @Override
                    public void onResponse(Call<PostBienSo> call, Response<PostBienSo> response) {
                        // this method is called when we get response from our api.
                        Toast.makeText(MainActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();
                        PostBienSo responseFromAPI = response.body();
                    }
                    @Override
                    public void onFailure(Call<PostBienSo> call, Throwable t) {
                    }
                });
                edtBienSo.setText("");
            }
        });
        btnVaoLink.setOnClickListener(view -> {
            String url = tvBienSo.getText().toString();
            Intent urlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(tvBienSo.getText().toString() + url));
            startActivity(urlIntent);
        });

        tvBienSo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = tvBienSo.getText().toString();
                Intent urlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(tvBienSo.getText().toString() + url));
                startActivity(urlIntent);
            }
        });

    }

    private void ScanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volunm up to fllash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }

    private void AnhXa(){
        edtBienSo = findViewById(R.id.editTextTextPersonName);
        btnVaoLink = findViewById(R.id.button3);
        btnGuiBien = findViewById(R.id.button);
        btnCam = findViewById(R.id.button2);
        tvBienSo = findViewById(R.id.textView3);

    }
    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract() ,result -> {
        if(result.getContents()!=null){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("result");
            tvBienSo.setText(result.getContents());
            builder.setMessage(result.getContents());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
        }
    });
}
