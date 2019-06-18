package com.os.paytzwakal.reg.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.os.paytzwakal.reg.R;
import com.os.paytzwakal.reg.database.RegistartionData;

import java.util.List;

public class RegistartionListAdapter extends RecyclerView.Adapter<RegistartionListAdapter.ViewHolder> {

    Context context;
    List<RegistartionData> registrationList;


    public RegistartionListAdapter(Context context, List<RegistartionData> registrationList) {
        this.context = context;
        this.registrationList = registrationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        itemLayoutView.setLayoutParams(lp);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.textView.setText(registrationList.get(i).name);
        viewHolder.time.setText(registrationList.get(i).created.trim());
        viewHolder.tv_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog1 = new Dialog(context, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.white)));
                dialog1.setContentView(R.layout.dailog);
                LinearLayout ll3 = dialog1.findViewById(R.id.ll3);
                TextView tv_ok, tv_name, tv_mobile, tv_email, tv_bankname, tv_acountnumber, tv_benificaryname, tv_city, tv_pin, tv_location, tv_contatcnumber, tv_contactperson;
                tv_ok = dialog1.findViewById(R.id.tv_ok);
                tv_name = dialog1.findViewById(R.id.tv_name);
                tv_mobile = dialog1.findViewById(R.id.tv_mobile);
                tv_email = dialog1.findViewById(R.id.tv_email);
                tv_bankname = dialog1.findViewById(R.id.tv_bankname);
                tv_acountnumber = dialog1.findViewById(R.id.tv_acountnumber);
                tv_benificaryname = dialog1.findViewById(R.id.tv_benificaryname);
                tv_city = dialog1.findViewById(R.id.tv_city);
                tv_pin = dialog1.findViewById(R.id.tv_pin);
                tv_contactperson = dialog1.findViewById(R.id.tv_contactperson);
                tv_contatcnumber = dialog1.findViewById(R.id.tv_contatcnumber);
                tv_location = dialog1.findViewById(R.id.tv_location);
                tv_name.setText(registrationList.get(i).name);
                tv_mobile.setText(registrationList.get(i).mobile);
                if (registrationList.get(i).email != null) {
                    ll3.setVisibility(View.VISIBLE);
                    tv_email.setText(registrationList.get(i).email);
                } else {
                    ll3.setVisibility(View.GONE);
                }
                tv_bankname.setText(registrationList.get(i).bank);
                tv_acountnumber.setText(registrationList.get(i).bank_account_number);
                tv_benificaryname.setText(registrationList.get(i).benificary_name);
                tv_city.setText(registrationList.get(i).city);
                tv_pin.setText(registrationList.get(i).pin);
                tv_contactperson.setText(registrationList.get(i).contact_person);
                tv_contatcnumber.setText(registrationList.get(i).contact_number);
                tv_location.setText(registrationList.get(i).location);

                tv_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog1.dismiss();
                    }

                });

                dialog1.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return registrationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView, tv_view, time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            tv_view = itemView.findViewById(R.id.tv_view);
            time = itemView.findViewById(R.id.time);
        }
    }
}
