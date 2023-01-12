package edu.upc.dsa.andoroid_dsa.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;

import edu.upc.dsa.andoroid_dsa.Api;
import edu.upc.dsa.andoroid_dsa.R;
import edu.upc.dsa.andoroid_dsa.RetrofitClient;
import edu.upc.dsa.andoroid_dsa.models.ChatMessage;
import edu.upc.dsa.andoroid_dsa.models.Gadget;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {
    Api APIservice;
    String name;
    TableLayout tableChat;
    TextInputEditText newMessage;
    Integer numberRows;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_forum_users);
        this.getVariables();
        APIservice = RetrofitClient.getInstance().getMyApi();
        Call<List<ChatMessage>> call = APIservice.getChat();
        this.tableChat=(TableLayout) findViewById(R.id.layoutChats);
        try {
            setChats(call.execute().body());

        }
        catch(IOException e){
            e.printStackTrace();

        }

    }

    public void getVariables() {
        SharedPreferences sharedPreferences = getSharedPreferences("nameForChat", Context.MODE_PRIVATE);
        this.name = sharedPreferences.getString("username", null).toString();
    }

    public void postMessage(View view) {
        APIservice = RetrofitClient.getInstance().getMyApi();
        this.newMessage=(TextInputEditText) findViewById(R.id.messageGamer);
        ChatMessage chatMessage =new ChatMessage(this.name,this.newMessage.getText().toString());
        Call<Void> call = APIservice.newMessage(chatMessage);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()){
                    case 201:
                        Snackbar snaky201 = Snackbar.make(view, "The message has been done correctly! Update the forum to see it", 3000);
                        snaky201.show();
                        break;
                    case 403:
                        Snackbar snaky403 = Snackbar.make(view, "Database error when reporting issue", 3000);
                        snaky403.show();
                        break;
                    case 500:
                        Snackbar snaky500 = Snackbar.make(view, "The name has not been set successfully", 3000);
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

    public void updateChat(View view) {
        APIservice = RetrofitClient.getInstance().getMyApi();
        Call<List<ChatMessage>> call = APIservice.getChat();
        this.tableChat=(TableLayout) findViewById(R.id.layoutChats);
        try {
            setChats(call.execute().body());
        }
        catch(IOException e){
            e.printStackTrace();

        }
    }

    public void goBackToDashBoardActivity(View view) {
        Intent intent=new Intent(ChatActivity.this, DashBoardActivity.class);
        startActivity(intent);
    }
    public void setChats(List<ChatMessage> chats){
        if(chats==null){
            return;
        }
        for (ChatMessage message : chats) {
            View tableRow = LayoutInflater.from(this).inflate(R.layout.message_gamer, null, false);

            TextView gamer = tableRow.findViewById(R.id.gamerName);
            TextView gamerMessage = tableRow.findViewById(R.id.gamerMessage);
            gamer.setText(message.getName());
            gamerMessage.setText(message.getMessage());
            this.tableChat.addView(tableRow);
        }
    }

}

