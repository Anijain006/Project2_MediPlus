package com.animeshjain.mediplus.dialog_box;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.animeshjain.mediplus.activity.MainActivity;
import com.animeshjain.mediplus.database.DBhelper;
import com.animeshjain.mediplus.R;
import com.animeshjain.mediplus.utils.Utility;

public class Pharmacy_details_dialog extends DialogFragment {
    EditText name,number,address;
    Button save,cancel;
    String pname,pnum,paddress;
    DBhelper helper;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflator=getActivity().getLayoutInflater();
        View view=inflator.inflate(R.layout.pharma_dialog,null);
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setView(view);
        builder.setTitle("Enter Pharmacy Details");
        setCancelable(false);
        Dialog dialog=builder.create();
        init(view);
        return dialog;
    }
    public void init(View v)
    {
        name=(EditText)v.findViewById(R.id.pharma_name_id);
        number=(EditText)v.findViewById(R.id.pharma_number_id);
        address=(EditText)v.findViewById(R.id.pharma_address_id);
        save=(Button)v.findViewById(R.id.btn_Save_pharma);
        cancel=(Button)v.findViewById(R.id.btn_cancel_pharma);
        helper=DBhelper.getInstance(getActivity());
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pname=name.getText().toString();
                pnum=number.getText().toString();
                paddress=address.getText().toString();
                boolean isnum= Utility.isnumber(pnum);
                if(isnum==true) {
                    long iresult = helper.insertintopharma(pname, pnum, paddress);
                    if (iresult != -1) {
                        Toast.makeText(getActivity(), "Successfully inserted", Toast.LENGTH_SHORT).show();
                        Intent gohome = new Intent(getActivity(), MainActivity.class);
                        startActivity(gohome);
                        dismiss();
                    } else {
                        Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }else
                {
                    Toast.makeText(getActivity(), "Enter valid phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
