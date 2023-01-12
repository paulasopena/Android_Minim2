package edu.upc.dsa.andoroid_dsa.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import edu.upc.dsa.andoroid_dsa.Api;
import edu.upc.dsa.andoroid_dsa.R;
import edu.upc.dsa.andoroid_dsa.RetrofitClient;
import edu.upc.dsa.andoroid_dsa.models.Abuse;
import edu.upc.dsa.andoroid_dsa.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbuseActivity extends AppCompatActivity {
    String date;
    Api APIservice;
    TextInputEditText informerInput;
    TextInputEditText descriptionAbuse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_abuse);
        this.date=obtainDate("yyyy-MM-dd");
    }

    public void returnFunction(View view) {
        Intent intentRegister = new Intent(AbuseActivity.this, DashBoardActivity.class);
        AbuseActivity.this.startActivity(intentRegister);
    }

    public void reportAbuse(View view) {
        informerInput = findViewById(R.id.informerInput);
        descriptionAbuse = findViewById(R.id.abuseMessageInput);
        Abuse newAbuse=new Abuse(this.date,this.informerInput.getText().toString(),this.descriptionAbuse.getText().toString());
        APIservice = RetrofitClient.getInstance().getMyApi();
        Call<Void> call = APIservice.postAbuse(newAbuse);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()){
                    case 201:
                        Snackbar snaky201 = Snackbar.make(view, "The report of the abuse has been done correctly!", 3000);
                        snaky201.show();
                        setLabel();
                        break;
                    case 403:
                        Snackbar snaky403 = Snackbar.make(view, "Database error when reporting issue", 3000);
                        snaky403.show();
                        break;
                    case 500:
                        Snackbar snaky500 = Snackbar.make(view, "Fullfill the informers text!", 3000);
                        snaky500.show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Snackbar snakyfail = Snackbar.make(view, "NETWORK FAILURE", 3000);
                snakyfail.show();
            }
        });

    }
    @SuppressLint("SimpleDateFormat")
    public String obtainDate(String dateFormat) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);
    }
    public void setLabel(){
        this.informerInput.setText("");
        this.descriptionAbuse.setText("");
    }
    /*
    public static String obtenerFechaActual(String zonaHoraria) {
        String formato = "yyyy-MM-dd";
        return obtenerFechaConFormato(formato, zonaHoraria);
    }

     */
}
