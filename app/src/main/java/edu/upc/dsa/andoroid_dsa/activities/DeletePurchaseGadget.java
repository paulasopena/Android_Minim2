package edu.upc.dsa.andoroid_dsa.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.List;

import edu.upc.dsa.andoroid_dsa.Api;
import edu.upc.dsa.andoroid_dsa.R;
import edu.upc.dsa.andoroid_dsa.RetrofitClient;
import edu.upc.dsa.andoroid_dsa.models.Gadget;
import edu.upc.dsa.andoroid_dsa.models.Purchase;
import edu.upc.dsa.andoroid_dsa.models.UserId;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeletePurchaseGadget extends AppCompatActivity {
    Api APIservice;
    String idUser;
    String idGadget;
    String costGadget;
    String descriptionGadget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_gadget_user);
        this.getVariables();
        this.updateLabels();
    }
    public void getVariables() {
        SharedPreferences sharedPreferences = getSharedPreferences("gadgetItemToDelete", Context.MODE_PRIVATE);
        this.idUser = sharedPreferences.getString("idUser", null).toString();
        this.idGadget = sharedPreferences.getString("idGadget", null).toString();
        this.descriptionGadget = sharedPreferences.getString("descriptionGadget", null).toString();
        this.costGadget = sharedPreferences.getString("costGadget", null).toString();
    }
    public void deleteGadgetOfUser(View view){
        APIservice = RetrofitClient.getInstance().getMyApi();
        Purchase purchase=new Purchase(this.idUser,this.idGadget);
        Call<Void> call = APIservice.deleteGadgetFromPurchase(purchase);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()){
                    case 201:
                        Intent intentRegister = new Intent(DeletePurchaseGadget.this, YourProfileActivity.class);
                        Toast.makeText(DeletePurchaseGadget.this,"The gadget was successfully deleted!", Toast.LENGTH_SHORT).show();
                        DeletePurchaseGadget.this.startActivity(intentRegister);
                        break;
                    case 404:
                        Toast.makeText(DeletePurchaseGadget.this,"The purchase was not successfully found in DB!", Toast.LENGTH_SHORT).show();
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
    public void updateLabels(){
        String updateTitle =getString(R.string.updating_title_gadget);
        updateTitle=this.descriptionGadget+" !";
        EditText editorTitle = (EditText) findViewById (R.id.title_profile_gadget);
        editorTitle.setText(updateTitle);
        String updateIdentifier =getString(R.string.updating_identifier);
        updateIdentifier=this.idGadget;
        EditText editorIdentifier = (EditText) findViewById (R.id.id_gadget);
        editorIdentifier.setText(updateIdentifier);
        String updateDescription =getString(R.string.updating_description);
        updateDescription=this.descriptionGadget;
        EditText editorDescription = (EditText) findViewById (R.id.description_gadget);
        editorDescription.setText(updateDescription);
        String updateCost =getString(R.string.updating_cost);
        updateCost=this.costGadget;
        EditText editorCost = (EditText) findViewById (R.id.cost_Gadget);
        editorCost.setText(updateCost);
    }
    public void goBackToYourProfileActivity(View view) {
        Intent intent = new Intent(DeletePurchaseGadget.this, YourProfileActivity.class);
        startActivity(intent);
    }
}
