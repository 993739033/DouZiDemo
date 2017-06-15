package com.scode.douzidemo.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scode.douzidemo.R;
import com.scode.douzidemo.utils.Constants;
import com.scode.douzidemo.utils.PreferncesUtils;
import com.scode.douzidemo.utils.UIUtils;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.main_container)
    RelativeLayout container;
    @BindView(R.id.main_drawer_layout)
    DrawerLayout main_drawer_layout;
    @BindView(R.id.main_toolbar)
    Toolbar main_toolbar;
//    @Nullable @BindView(R.id.nav_header_img)//为空？因为没用添加headlayout 但是不能直接绑定？
//    ImageView nav_header_img;
//    @Nullable @BindView(R.id.nav_header_title)//为空？
//    TextView nav_header_title;
    @Nullable @BindView(R.id.mian_nav)
    NavigationView main_nav;

    private View headView;//nav
    private ImageView nav_header_img;//nav
    private TextView nav_header_title;//nav


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //设置主题
        String nowtheme = PreferncesUtils.getString(this, Constants.PREF_KEY_THEME, "1");
        if (nowtheme.equals("1")) {
            setTheme(R.style.AppTheme);
        } else {
            setTheme(R.style.AppTheme_Light);
        }

        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        initView();//初始化控件
        initToolbar();//初始开始title和nav的item的选中
        setUpDrawerLayout();
    }

    private void setUpDrawerLayout() {
        main_nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                main_drawer_layout.closeDrawers();//关闭所有drawerlayout

                String title = (String) item.getTitle();//获取item的title设置到toolbar
                main_toolbar.setTitle(title);
                //// TODO: 2017/6/15 设置fragment

                return true;
            }
        });
    }

    //初始化nav item的选中 和 toolbar的title
    private void initToolbar() {
        if (main_toolbar.getTitle() == null) {
            String title = UIUtils.getString(this, R.id.nav_menu_movie);
            main_toolbar.setTitle(title);
            main_nav.getMenu().getItem(0).setChecked(true);
        }

    }

    private void initView() {
        main_toolbar.inflateMenu(R.menu.menu_toolbar);

        headView = main_nav.getHeaderView(0);
        nav_header_img = (ImageView) headView.findViewById(R.id.nav_header_img);
        nav_header_title = (TextView) headView.findViewById(R.id.nav_header_title);

        main_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_drawer_layout.openDrawer(GravityCompat.START);//为navigation设置打开事件
            }
        });
        main_toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                //// TODO: 2017/6/15 为搜索设置跳转事件
                return true;
            }
        });
    }
}
