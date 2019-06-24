package com.os.paytzwakal.reg.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.os.paytzwakal.reg.R;
import com.os.paytzwakal.reg.adapter.RegistartionListAdapter;
import com.os.paytzwakal.reg.application.Config;
import com.os.paytzwakal.reg.application.Util;
import com.os.paytzwakal.reg.database.RegistartionData;
import com.os.paytzwakal.reg.database.RegistrationDatabase;

import java.util.List;

public class RegistarionFormListActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rv_list;
    List<String> registartionDataList;
    private RegistartionListAdapter registartionListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registarion_form_list);
        init();
    }

    private void init() {
        rv_list = findViewById(R.id.rv_list);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv_list.setLayoutManager(llm);

        new AsyncTask<Void, Void, List<RegistartionData>>() {
            @Override
            protected List<RegistartionData> doInBackground(Void... voids) {
                List<RegistartionData> allRepos = RegistrationDatabase
                        .getInstance(RegistarionFormListActivity.this)
                        .getRegistrationDao()
                        .getAllData();
//                Log.e("size-----------", allRepos.size() + "");

                return allRepos;
            }

            @Override
            protected void onPostExecute(List<RegistartionData> aVoid) {
                super.onPostExecute(aVoid);
                Util.dismissProDialog();
                RegistartionListAdapter registartionListAdapter = new RegistartionListAdapter(RegistarionFormListActivity.this, aVoid);
                rv_list.setAdapter(registartionListAdapter);
            }
        }.execute();
       /* registartionListAdapter = new RegistartionListAdapter(this, registartionDataList);
        rv_list.setAdapter(registartionListAdapter);*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_registartion: {
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            }
            case R.id.imvBack: {
                finish();
                break;
            }
            case R.id.logout: {
                addLogoutDialog();
                break;
            }
        }
    }

    public void addLogoutDialog() {
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.trans_black_light)));
        dialog.setContentView(R.layout.logout_dialog);
        TextView btnCancelDialog = (TextView) dialog.findViewById(R.id.btnCancelDialog);
        TextView btnLogoutDialog = (TextView) dialog.findViewById(R.id.btnLogoutDialog);

        btnCancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnLogoutDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Config.removeAll();
                new AsyncTask<Void, Void, List<RegistartionData>>() {
                    @Override
                    protected List<RegistartionData> doInBackground(Void... voids) {
                        RegistrationDatabase.getInstance(getApplicationContext()).getRegistrationDao().delete();
                        return null;
                    }
                }.execute();
                finish();
                startActivity(new Intent(RegistarionFormListActivity.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));

            }
        });
        dialog.show();
    }
}
