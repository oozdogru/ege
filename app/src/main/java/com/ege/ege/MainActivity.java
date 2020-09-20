package com.ege.ege;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ege.ege.Fragments.FragmentT1;
import com.ege.ege.Fragments.FragmentT2;
import com.ege.ege.Fragments.FragmentT3;
import com.ege.ege.Fragments.FragmentT4;
import com.ege.ege.Fragments.FragmentT5;
import com.ege.ege.Fragments.FragmentT6;
import com.ege.ege.Fragments.FragmentTekrarlar;
import com.ege.ege.Fragments.Share;
import com.ege.ege.Fragments.Works;
import com.ege.ege.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    Locale locale = new Locale("tr");
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;





    /*ListView listView;
    ArrayList<String> list;
    Button btnAdd;
    EditText editText;
    ArrayAdapter<String> arrayAdapter;*/


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);










        /*listView = (ListView) findViewById(R.id.listview);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        editText = (EditText) findViewById(R.id.editTextTextPersonName);


        list = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,list);


        btnAdd .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String names = editText.getText().toString();
                list.add(names);
                listView.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();

            }
        });*/

        tabLayout = findViewById(R.id.tabs);

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new Share(), "");

        adapter.AddFragment(new Works(), "Çalışmalar");
        adapter.AddFragment(new FragmentTekrarlar(), "Tekrarlar");
        adapter.AddFragment(new FragmentT1(), "2.GÜN");
        adapter.AddFragment(new FragmentT2(), "3.GÜN");
        adapter.AddFragment(new FragmentT3(), "4.GÜN");
        adapter.AddFragment(new FragmentT4(), "5.GÜN");
        adapter.AddFragment(new FragmentT5(), "6.GÜN");
        adapter.AddFragment(new FragmentT6(), "7.GÜN");


        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_share);

        viewPager.setCurrentItem(1);


    }


}
