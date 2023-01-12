package edu.upc.dsa.andoroid_dsa.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import edu.upc.dsa.andoroid_dsa.Api;
import edu.upc.dsa.andoroid_dsa.R;
import edu.upc.dsa.andoroid_dsa.RetrofitClient;
import edu.upc.dsa.andoroid_dsa.models.Credentials;
import edu.upc.dsa.andoroid_dsa.models.PasswordChangeRequirements;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateOfUserInformation extends AppCompatActivity {
    TextInputEditText oldPasswordInput;
    TextInputEditText newPasswordInput;
    String email;
    String idUser;
    String oldPassword;

    Api APIservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_user_info);
        this.getVariables();
    }

    public void getVariables() {
        SharedPreferences sharedPreferences = getSharedPreferences("userIdAndInformation", Context.MODE_PRIVATE);
        this.idUser = sharedPreferences.getString("userId", null).toString();
        this.email = sharedPreferences.getString("email", null).toString();

    }

    public void updatePassword(View view){
        this.oldPasswordInput = findViewById(R.id.oldPasswordInput);
        this.newPasswordInput = findViewById(R.id.newPasswordInput);
        this.oldPassword=this.oldPasswordInput.getText().toString();
        PasswordChangeRequirements passwordChangeRequirements=new PasswordChangeRequirements(this.idUser,this.email,this.oldPassword,newPasswordInput.getText().toString());
        APIservice = RetrofitClient.getInstance().getMyApi();
        Call<Void> call =APIservice.updateUserPassword(passwordChangeRequirements);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()){
                    case 201:
                        Toast.makeText(UpdateOfUserInformation.this,"Password successfully changed, log in again", Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(UpdateOfUserInformation.this, LogInActivity.class);
                        startActivity(intent);
                        break;
                    case 403:
                        Toast.makeText(UpdateOfUserInformation.this,"Write correctly the old password please!", Toast.LENGTH_SHORT).show();
                        break;
                    case 404:
                        Toast.makeText(UpdateOfUserInformation.this,"There was a problem in the database!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateOfUserInformation.this,"NETWORK FAILURE :(", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
