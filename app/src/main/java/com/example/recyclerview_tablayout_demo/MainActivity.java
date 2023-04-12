package com.example.recyclerview_tablayout_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recyclerview_tablayout_demo.adapter.MainAdapter;
import com.example.recyclerview_tablayout_demo.bean.MainBean;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private List<MainBean> list;
    private MainAdapter adapter;
    private RecyclerView recyclerView;

    private LinearLayoutManager manager;
    private TabLayout tabLayout;
    /**
     *关于bug问题，可以在adapter 中给最后一个item 填充一个空视图把整个布局顶上去完成滑动
     *demo中就给到一个思路了
     **/

    private  int pos=0;
    private TextView tv_test;
    private AppBarLayout appBarLayout;
    private String[] str = {"测试1", "测试2", "测试3", "测试4", "测试5", "测试6", "测试7", "测试8", "测试9", "测试10"};
    private ImageView iv_tag;
    private int[] str1 = {0, 11, 22, 33, 44, 55, 66, 77, 88, 99};
    private boolean isScrolled = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        init();
        initTab();
        //因为需求原因这里先放置点击收缩appbar的占位视图
//        tv_test.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (pos<250){//这里可以根据实际情况来做出appbar的收缩判断 高度可以通过自己计算
//                    appBarLayout.setExpanded(false);
//                }
//            }
//        });



    }

    private void initTab() {
        for (int i = 0; i < str.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(str[i]));
        }
        //标签页可以滑动
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                if (!isScrolled) {
                    manager.scrollToPositionWithOffset(str1[pos], 0);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void init() {
        recyclerView = findViewById(R.id.mian_recy);
        tabLayout = findViewById(R.id.main_tab);
        appBarLayout=findViewById(R.id.app_bar);
//        tv_test=findViewById(R.id.tv_test);
        iv_tag=findViewById(R.id.iv_tag);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new MainAdapter(list);
        recyclerView.setAdapter(adapter);
//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                //处理自己的事件
//                Toast.makeText(MainActivity.this, "点击的item=" + position, Toast.LENGTH_SHORT).show();
//            }
//        });


//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                //重写该方法主要是判断recyclerview是否在滑动
//                //0停止 ，12都是滑动
//
//                if (newState == 0) {
//                    isScrolled = false;
//                } else {
//                    isScrolled = true;
//                }
//                setMsg("isScrolled" + isScrolled + "--newState=" + newState);
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                /**
//                 * 关于滑动rv 定位tab的实现逻辑  可根据自己的项目需要进行修改
//                 * **/
//                if (isScrolled) {
//                    int top = manager.findFirstVisibleItemPosition();
//                    int bottom = manager.findLastVisibleItemPosition();
//
//                    int pos = 0;
//                    if (bottom == list.size() - 1) {
//                        //先判断滑到底部，tab定位到最后一个
//                        pos = str1.length - 1;
//                    } else if (top == str1[str1.length - 1]) {
//                        //如果top等于指定的位置，对应到tab即可，
//                        pos = str1[str1.length - 1];
//                    } else {
//                        //循环遍历，需要比较i+1的位置，所以循环长度要减1，
//                        //  如果 i<top<i+1,  那么tab应该定位到i位置的字符，不管是向上还是向下滑动
//                        for (int i = 0; i < str1.length - 1; i++) {
//                            if (top == str1[i]) {
//                                pos = i;
//                                break;
//                            } else if (top > str1[i] && top < str1[i + 1]) {
//                                pos = i;
//                                break;
//                            }
//                        }
//                    }
//
//                    //设置tab滑动到第pos个
//                    tabLayout.setScrollPosition(pos, 0f, true);
//                }
//
//            }
//        });

//        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
//                Log.e(MainActivity.class.getSimpleName(), "onOffsetChanged: "+i);
//                if (Math.abs(i)==iv_tag.getBottom()){
//                    pos=Math.abs(i);
//                    tv_test.setVisibility(View.GONE);
//                    tabLayout.setVisibility(View.VISIBLE);
//                }else {
//                    pos=0;
//                    tabLayout.setVisibility(View.GONE);
//                    tv_test.setVisibility(View.VISIBLE);
//                }
//            }
//        });


    }

    public static void setMsg(String str) {
        Log.i("tab", str);
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new MainBean("title--" + i));
        }

    }
}