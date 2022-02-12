package com.example.logindb;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name,phone;
    Button btnreg,btnlog;
    DbHelper db1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.nameid);
        phone=findViewById(R.id.phoneid);
        btnreg=findViewById(R.id.btnregister);
        btnlog=findViewById(R.id.btnlogin);
        db1=new DbHelper(this);

        btnreg.setOnClickListener(v -> {
            String nametxt,phonetxt;
            nametxt=name.getText().toString();
            phonetxt=phone.getText().toString();

            boolean ckinsert=db1.insertdata(nametxt,phonetxt);
            if(ckinsert){
                Toast.makeText(getApplicationContext(), "insert succesfull", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
            }
            db1.close();
        });

        btnlog.setOnClickListener(v -> {
            String nametxt,phonetxt;
            nametxt=name.getText().toString();
            phonetxt=phone.getText().toString();
            if(db1.cknameph(nametxt,phonetxt)){
                Intent intent=new Intent(MainActivity.this,loginactivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(getApplicationContext(), "failed to read", Toast.LENGTH_SHORT).show();
            }
            db1.close();
        });
    }
}