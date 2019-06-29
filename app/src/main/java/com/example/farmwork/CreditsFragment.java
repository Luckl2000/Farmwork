package com.example.farmwork;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CreditsFragment extends Fragment {
    TextView num1, num2, num3, num4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activtiy_credits, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        num1 = view.findViewById(R.id.textView1);
        num2 = view.findViewById(R.id.textView2);
        num3 = view.findViewById(R.id.textView3);
        num4 = view.findViewById(R.id.textView4);


        num1.setText("Farmwork Icon:");
        num2.setText("Icon made by Freepik from www.flaticon.com");

        num3.setText("Picture Navigation");
        num4.setText("Photo by Boudewijn “Bo” Boer on Unsplash");

    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
