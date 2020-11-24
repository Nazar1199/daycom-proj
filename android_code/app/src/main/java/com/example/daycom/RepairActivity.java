package com.example.daycom;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.lang.Integer;
import android.widget.TextView;
import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;

public class RepairActivity extends Activity {
    TextView st;
    EditText C, S;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair);
        final Button GET = (Button) findViewById(R.id.sendRepairRequest);
        GET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                C=findViewById(R.id.InputIDOfOrder);
                S=findViewById(R.id.InputSurname);
                TextView St = findViewById(R.id.Surname);
                final TextView Ct=findViewById(R.id.OrderID);
                if(checkInpuns()){
                    getRepairStatus();
                }
            }
        });//GET.callOnClick();
        }
        void changeDownloadText(boolean status){
            TextView GET;
            GET=findViewById(R.id.Status);
            if (status){
                GET.setText(R.string.downloadText);
            } else {
                GET.setText(R.string.getStatus);
            }
        }
        protected boolean checkInpuns(){
            C = findViewById(R.id.InputIDOfOrder);
            S = findViewById(R.id.InputSurname);
            if(C.getText().toString().equals("")||S.getText().toString().equals("")){
                TextView GET;
                GET=findViewById(R.id.Status);
                if(C.getText().toString().equals("")&&S.getText().toString().equals("")){
                    GET.setText(R.string.InputCS);
                } else  if(C.getText().toString().equals("")){
                    GET.setText(R.string.InputC);
                } else  if(S.getText().toString().equals("")){
                    GET.setText(R.string.InputS);
                } return false;
            } else {
                return true;
            }
        }
        void getRepairStatus(){
            changeDownloadText(true);
            EditText CODE_TEXT = findViewById(R.id.InputIDOfOrder);
            EditText SURNAME = findViewById(R.id.InputSurname);
            String S = SURNAME.getText().toString();
            int C = Integer.parseInt(CODE_TEXT.getText().toString());
            NetworkService.getInstance().getAPI().getRepairStatus(S,C).enqueue(new Callback<RepairStatus>() {
                @Override
                public void onResponse(Call<RepairStatus> call, Response<RepairStatus> response) {
                    RepairStatus status = response.body();
                    int intStatus = status.getStatus();
                    st=findViewById(R.id.Status);
                    switch (intStatus){
                        case 0: st.setText(R.string.StatusCode0);
                        break;
                        case 1: st.setText(R.string.StatusCode1);
                        break;
                        case 2: st.setText(R.string.StatusCode2);
                        break;
                        case 3: st.setText(R.string.StatusCode3);
                        break;
                    }
                }
                @Override
                public void onFailure(Call<RepairStatus> call, Throwable t) {
                }
            });
        }
        @Override
        public void onStart(){
        super.onStart();
        }
        @Override
        public void onStop(){
        super.onStop();
        }
    }