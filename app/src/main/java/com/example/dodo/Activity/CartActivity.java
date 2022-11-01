package com.example.dodo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dodo.Adapter.CartListAdapter;
import com.example.dodo.Helper.ManagementCart;
import com.example.dodo.Interface.ChangeNumberItemsListener;
import com.example.dodo.R;

public class CartActivity extends AppCompatActivity {
   private RecyclerView.Adapter adapter;
   private RecyclerView recyclerViewList;
   private ManagementCart managementCart;
   private TextView totalFeeTxt,taxTxt,deliveryTxt,totalTxt,emptyTxt ;
   private double tax;
   private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        managementCart= new ManagementCart(this);
        initView();
        initList();
        bottomNavigation();
        calculateCard();
    }


    private void bottomNavigation(){
        LinearLayout homeBtn=findViewById(R.id.homeBtn);
        LinearLayout cartBtn =findViewById(R.id.cartBtn);

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this,CartActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this,MainActivity.class));
            }
        });

    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager( linearLayoutManager);
        adapter= new CartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                calculateCard();
            }
        });
        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()){
                emptyTxt.setVisibility(View.VISIBLE);
                scrollView.setVisibility(View.GONE);

    }
        else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCard() {
        double percentTax = 0.02;
        double delivery = 500;
        tax = Math.round((managementCart.getTotalFee()*percentTax)*100)/100;
        double total = Math.round((managementCart.getTotalFee() + tax + delivery)*100.0)/100.0;
        double itemTotal = Math.round((managementCart.getTotalFee()*100.0)/100.0);
        totalFeeTxt.setText(itemTotal+" руб");

        if (itemTotal >= 7000){
            delivery = 0;
        }
        taxTxt.setText(tax+" руб");
        deliveryTxt.setText(delivery +" руб" );
        totalTxt.setText(total+" руб");
    }

    private void initView() {
    totalFeeTxt=findViewById(R.id.totalFeeTxt);
    taxTxt=findViewById(R.id.taxTxt);
    deliveryTxt=findViewById(R.id.deliveryTxt);
    totalTxt=findViewById(R.id.totalTxt);
    recyclerViewList=findViewById(R.id.view);
    scrollView=findViewById(R.id.scrollView);
    emptyTxt=findViewById(R.id.emptyTxt);
    }
}
