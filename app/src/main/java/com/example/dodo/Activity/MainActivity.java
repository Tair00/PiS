package com.example.dodo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dodo.Adapter.CartListAdapter;
import com.example.dodo.Adapter.CategoryAdapter;
import com.example.dodo.Adapter.RecommendedAdapter;
import com.example.dodo.Domain.CategoryDomain;
import com.example.dodo.Domain.OrderDomain;
import com.example.dodo.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private RecyclerView.Adapter adapter, adapter2;
private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;
 static ArrayList<OrderDomain> orderlist = new ArrayList<>();
static    ArrayList<CategoryDomain> categoryList = new ArrayList<>();
    static List<OrderDomain> fullOrderlist = new ArrayList<>();
 public static RecommendedAdapter recommendedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();

    }

    private void bottomNavigation(){
        LinearLayout homeBtn=findViewById(R.id.homeBtn);
        LinearLayout cartBtn =findViewById(R.id.cartBtn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });
cartBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        startActivity(new Intent(MainActivity.this,CartActivity.class));
    }
});
    }

    private void recyclerViewPopular() {
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
    recyclerViewPopularList = findViewById(R.id.view2);
    recyclerViewPopularList.setLayoutManager(linearLayoutManager);



    orderlist.add(new OrderDomain("Футбольный мяч","ball","Кожаный футбольный мяч, профессиональный",1800.0 , 5.0, 3,1.2,5));
    orderlist.add(new OrderDomain("Фитнес гантели","fit_gant","Гантели для фитнеса, вес 2 килограмма",2800.0 , 5.0, 2,2.0,2));
    orderlist.add(new OrderDomain("Скакалка","skakalka","Скакалка длинна 2 метра",800.0 , 4.6, 1,0.5,2));
    orderlist.add(new OrderDomain("Фитнес ковер","kovric","Фитнесс ковер для йоги ",3800.0 , 4.2, 2,1.5,2));

    adapter2 = new RecommendedAdapter(orderlist);
    recyclerViewPopularList.setAdapter(adapter2);

    fullOrderlist.addAll(orderlist);

    }


    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList=findViewById(R.id.view1);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);



        categoryList.add(new CategoryDomain("Фитнес", "pic1",1));
        categoryList.add(new CategoryDomain("Тренажер", "pic5",2));
        categoryList.add(new CategoryDomain("Пауэрлифтинг", "pic2",3));
        categoryList.add(new CategoryDomain("Бокс", "pic3",4));
        categoryList.add(new CategoryDomain("Футбол", "pic4",5));


        adapter =new CategoryAdapter(categoryList);
        recyclerViewCategoryList.setAdapter(adapter);
    }

// public static void showOrderByCategory(int category){
//    orderlist.clear();
//    orderlist.addAll(fullOrderlist);
//     List<OrderDomain> filterOrder = new ArrayList<>();
//     for(OrderDomain c: orderlist){
//            if (c.getCat_id()==category){
//                filterOrder.add(c);
//            }
//            orderlist.clear();
//
//            orderlist.addAll(filterOrder);
//
////            RecommendedAdapter.notifyDataSetChanged();
//
//     }

// }
}