package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn_cong,btn_tru,btn_nhan,btn_chia,btn_bang,btn_0,btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9,btn_xoa,btn_next_acti;

    TextView tv_kq,tv_pt;

    String pheptoan,sothunhat,sothuhai;

    double ketqua,so1,so2;
    int count, mycount;

    String[] myData;
    ArrayList<String> duLieu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        onClick();


    }

    private void onClick() {
        btn_0.setOnClickListener(btnclick);
        btn_1.setOnClickListener(btnclick);
        btn_2.setOnClickListener(btnclick);
        btn_3.setOnClickListener(btnclick);
        btn_4.setOnClickListener(btnclick);
        btn_5.setOnClickListener(btnclick);
        btn_6.setOnClickListener(btnclick);
        btn_7.setOnClickListener(btnclick);
        btn_8.setOnClickListener(btnclick);
        btn_9.setOnClickListener(btnclick);
        btn_xoa.setOnClickListener(btnclick);
        btn_bang.setOnClickListener(btnclick);
        btn_cong.setOnClickListener(btnclick);
        btn_tru.setOnClickListener(btnclick);
        btn_nhan.setOnClickListener(btnclick);
        btn_chia.setOnClickListener(btnclick);
        btn_next_acti.setOnClickListener(btnclick);



    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("pheptoan",tv_pt.getText().toString());
        outState.putDouble("ketqua",ketqua);
        outState.putInt("count",count);
        outState.putInt("mycount",mycount);
        outState.putStringArray("mydata",myData);
        outState.putStringArrayList("dulieu",duLieu);
        outState.putDouble("so1",so1);
        outState.putDouble("so2",so2);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pheptoan = savedInstanceState.getString("pheptoan");
        tv_pt.setText(pheptoan);


        ketqua = savedInstanceState.getDouble("ketqua");
        tv_kq.setText(savedInstanceState.getDouble("ketqua")+"");
        count = savedInstanceState.getInt("count");
        mycount = savedInstanceState.getInt("mycount");
        myData = savedInstanceState.getStringArray("mydata");
        duLieu = savedInstanceState.getStringArrayList("dulieu");
        so1 = savedInstanceState.getDouble("so1");
        so2 = savedInstanceState.getDouble("so2");

    }

    private View.OnClickListener btnclick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_0) {
                hienThi("0");
            }
            if (view.getId() == R.id.btn_1) {
                hienThi("1");
            }
            if (view.getId() == R.id.btn_2) {
                hienThi("2");
            }
            if (view.getId() == R.id.btn_3) {
                hienThi("3");
            }
            if (view.getId() == R.id.btn_4) {
                hienThi("4");
            }
            if (view.getId() == R.id.btn_5) {
                hienThi("5");
            }
            if (view.getId() == R.id.btn_6) {
                hienThi("6");
            }
            if (view.getId() == R.id.btn_7) {
                hienThi("7");
            }
            if (view.getId() == R.id.btn_8) {
                hienThi("8");
            }
            if (view.getId() == R.id.btn_9) {
                hienThi("9");
            }
            if (view.getId() == R.id.btn_bang) {
                clickBang();
            }
            if (view.getId() == R.id.btn_xoa) {
                clickXoa();
            }
            if (view.getId() == R.id.btn_cong) {
                clickPT("+");
            }
            if (view.getId() == R.id.btn_tru) {
                clickPT("-");
            }
            if (view.getId() == R.id.btn_nhan) {
                clickPT("*");
            }
            if (view.getId() == R.id.btn_chia) {
                clickPT("/");
            }
            if (view.getId() == R.id.btn_next_acti) {

                myData = new String[mycount];
                for (int i = 0; i < mycount; i++) {
                    myData[i] = duLieu.get(i);
                }
                clickNext(myData);
            }
        }
    };

    private void hienThi(String text) {
        if (pheptoan == null) {
            pheptoan = text;
        } else {
            pheptoan += text;
        }
        tv_pt.setText(pheptoan);
    }
    private void clickPT(String text) {
        if (pheptoan == null){
            Toast.makeText(MainActivity.this, "không hợp lệ", Toast.LENGTH_SHORT).show();
        } else {

            String chuoi = pheptoan.substring(pheptoan.length()-1);
            if (chuoi.equals("+") ||chuoi.equals("-")||chuoi.equals("*")||chuoi.equals("/")) {
                pheptoan = pheptoan.substring(0,pheptoan.length()-1)+text;
            } else {
                sothunhat = pheptoan;

                if (sothunhat.contains("+")||sothunhat.contains("-")||sothunhat.contains("*")||sothunhat.contains("/")){
                    sothuhai = sothunhat.substring(count+1,sothunhat.length());
                    so2 = Double.parseDouble(sothuhai);
                    String pt = sothunhat.substring(count,count+1);
                    ketQua(pt,so1,so2);
                    String data = so1+pt+so2;
                    duLieu.add(data);
                    mycount = mycount+1;
                    pheptoan = ketqua+text;
                    so1 = ketqua;
                    count = pheptoan.length()-1;


                } else {
                    pheptoan = pheptoan + text;
                    so1 = Double.parseDouble(sothunhat);
                    count = sothunhat.length();
                }

            }
            tv_pt.setText(pheptoan);
        }
    }
    private void ketQua(String s, double a, double b){
        if (s.equals("+")){
            cong(a,b);
        }
        if (s.equals("-")){
            tru(a,b);
        }
        if (s.equals("*")){
            nhan(a,b);
        }
        if (s.equals("/")){
            chia(a,b);
        }
    }
    private void clickXoa() {
        pheptoan = null;
        so1 = 0;
        so2 = 0;
        sothunhat = null;
        sothuhai = null;
        count = 0;
        ketqua = 0;
        tv_pt.setText("");
        tv_kq.setText("0.0");

    }
    private void clickBang() {
        if (pheptoan == null){
        } else {

            String chuoi = pheptoan.substring(pheptoan.length()-1);
            if (chuoi.equals("+") ||chuoi.equals("-")||chuoi.equals("*")||chuoi.equals("/")) {
                Toast.makeText(MainActivity.this, "không hợp lệ", Toast.LENGTH_SHORT).show();
            } else {
                sothunhat = pheptoan;

                if (sothunhat.contains("+")||sothunhat.contains("-")||sothunhat.contains("*")||sothunhat.contains("/")){
                    sothuhai = sothunhat.substring(count+1,sothunhat.length());
                    so2 = Double.parseDouble(sothuhai);
                    String pt = sothunhat.substring(count,count+1);
                    ketQua(pt,so1,so2);
                    String data = so1+pt+so2;
                    duLieu.add(data);
                    mycount = mycount+1;
                    pheptoan = ketqua+"";
                    so1 = ketqua;
                    count = pheptoan.length()-1;

                } else {
                    ketqua = Double.parseDouble(pheptoan);
                    tv_kq.setText(ketqua+"");
                    count = sothunhat.length();
                }
            }
            tv_pt.setText(pheptoan);
        }

    }
    private void clickNext(String[] myList) {
        Intent lichsu = new Intent(getApplicationContext(),LichSuActivity.class);
        lichsu.putExtra("lichsu",myList);
        startActivity(lichsu);
    }
    private void cong(double a, double b) {
        ketqua = a+b;
        tv_kq.setText(ketqua+"");
    }
    private void tru(double a, double b) {
        ketqua = a-b;
        tv_kq.setText(ketqua+"");
    }
    private void nhan(double a, double b) {
        ketqua = a*b;
        tv_kq.setText(ketqua+"");
    }
    private void chia(double a, double b) {
        if (b == 0) {
            Toast.makeText(this, "không hợp lệ", Toast.LENGTH_SHORT).show();
        } else {
            ketqua = a/b;
            tv_kq.setText(ketqua+"");
        }

    }
    private void init() {
        btn_cong = findViewById(R.id.btn_cong);
        btn_tru = findViewById(R.id.btn_tru);
        btn_nhan = findViewById(R.id.btn_nhan);
        btn_chia = findViewById(R.id.btn_chia);
        btn_xoa = findViewById(R.id.btn_xoa);
        btn_bang = findViewById(R.id.btn_bang);
        tv_kq = findViewById(R.id.tv_ketqua);
        tv_pt = findViewById(R.id.tv_pheptoan);
        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_next_acti = findViewById(R.id.btn_next_acti);
        duLieu = new ArrayList<>();
    }
}