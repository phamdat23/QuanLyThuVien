package vn.edu.poly.duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;

import vn.edu.poly.duanmau.Fragment.Fragment_Book;
import vn.edu.poly.duanmau.Fragment.Fragment_DoanhThu;
import vn.edu.poly.duanmau.Fragment.Fragment_Home;
import vn.edu.poly.duanmau.Fragment.Fragment_TopBook;
import vn.edu.poly.duanmau.Fragment.Fragment_User;
import vn.edu.poly.duanmau.Fragment.Fragment_ViewPager;
import vn.edu.poly.duanmau.Fragment.Fragment_categoryBook;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private FrameLayout fragmentContent;
    public NavigationView nvView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        fragmentContent = findViewById(R.id.fragment_content);
        nvView = (NavigationView) findViewById(R.id.nv_view);
        Intent intent = getIntent();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        nvView.setNavigationItemSelectedListener(this);
        replace(new Fragment_Home());
        nvView.getMenu().findItem(R.id.m_home).setChecked(true);


    }

    public void replace(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_content, fragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.m_home:
                replace(new Fragment_Home());
                nvView.getMenu().findItem(R.id.m_home).setChecked(true);
                nvView.getMenu().findItem(R.id.m_book).setChecked(false);
                nvView.getMenu().findItem(R.id.m_category).setChecked(false);
                nvView.getMenu().findItem(R.id.m_PM).setChecked(false);
                nvView.getMenu().findItem(R.id.m_top_book).setChecked(false);
                nvView.getMenu().findItem(R.id.m_listUser).setChecked(false);
                nvView.getMenu().findItem(R.id.m_exit).setChecked(false);
                nvView.getMenu().findItem(R.id.m_changePass).setChecked(false);
                nvView.getMenu().findItem(R.id.m_doanh_thu).setChecked(false);
                break;
            case R.id.m_book:
                replace(new Fragment_Book());
                nvView.getMenu().findItem(R.id.m_home).setChecked(false);
                nvView.getMenu().findItem(R.id.m_book).setChecked(true);
                nvView.getMenu().findItem(R.id.m_category).setChecked(false);
                nvView.getMenu().findItem(R.id.m_PM).setChecked(false);
                nvView.getMenu().findItem(R.id.m_top_book).setChecked(false);
                nvView.getMenu().findItem(R.id.m_listUser).setChecked(false);
                nvView.getMenu().findItem(R.id.m_exit).setChecked(false);
                nvView.getMenu().findItem(R.id.m_changePass).setChecked(false);
                nvView.getMenu().findItem(R.id.m_doanh_thu).setChecked(false);
                break;
            case R.id.m_category:
                replace(new Fragment_categoryBook());
                nvView.getMenu().findItem(R.id.m_home).setChecked(false);
                nvView.getMenu().findItem(R.id.m_book).setChecked(false);
                nvView.getMenu().findItem(R.id.m_category).setChecked(true);
                nvView.getMenu().findItem(R.id.m_PM).setChecked(false);
                nvView.getMenu().findItem(R.id.m_top_book).setChecked(false);
                nvView.getMenu().findItem(R.id.m_listUser).setChecked(false);
                nvView.getMenu().findItem(R.id.m_exit).setChecked(false);
                nvView.getMenu().findItem(R.id.m_changePass).setChecked(false);
                nvView.getMenu().findItem(R.id.m_doanh_thu).setChecked(false);

                break;
            case R.id.m_PM:
                replace(new Fragment_ViewPager());
                nvView.getMenu().findItem(R.id.m_home).setChecked(false);
                nvView.getMenu().findItem(R.id.m_book).setChecked(false);
                nvView.getMenu().findItem(R.id.m_category).setChecked(false);
                nvView.getMenu().findItem(R.id.m_PM).setChecked(true);
                nvView.getMenu().findItem(R.id.m_top_book).setChecked(false);
                nvView.getMenu().findItem(R.id.m_listUser).setChecked(false);
                nvView.getMenu().findItem(R.id.m_exit).setChecked(false);
                nvView.getMenu().findItem(R.id.m_changePass).setChecked(false);
                nvView.getMenu().findItem(R.id.m_doanh_thu).setChecked(false);
                break;
            case R.id.m_top_book:
                replace(new Fragment_TopBook());
                nvView.getMenu().findItem(R.id.m_home).setChecked(false);
                nvView.getMenu().findItem(R.id.m_book).setChecked(false);
                nvView.getMenu().findItem(R.id.m_category).setChecked(false);
                nvView.getMenu().findItem(R.id.m_PM).setChecked(false);
                nvView.getMenu().findItem(R.id.m_top_book).setChecked(true);
                nvView.getMenu().findItem(R.id.m_listUser).setChecked(false);
                nvView.getMenu().findItem(R.id.m_exit).setChecked(false);
                nvView.getMenu().findItem(R.id.m_changePass).setChecked(false);
                nvView.getMenu().findItem(R.id.m_doanh_thu).setChecked(false);
                break;
            case R.id.m_listUser:
                replace(new Fragment_User());
                nvView.getMenu().findItem(R.id.m_home).setChecked(false);
                nvView.getMenu().findItem(R.id.m_book).setChecked(false);
                nvView.getMenu().findItem(R.id.m_category).setChecked(false);
                nvView.getMenu().findItem(R.id.m_PM).setChecked(false);
                nvView.getMenu().findItem(R.id.m_top_book).setChecked(false);
                nvView.getMenu().findItem(R.id.m_listUser).setChecked(true);
                nvView.getMenu().findItem(R.id.m_exit).setChecked(false);
                nvView.getMenu().findItem(R.id.m_changePass).setChecked(false);
                nvView.getMenu().findItem(R.id.m_doanh_thu).setChecked(false);

                break;
            case R.id.m_doanh_thu:
                replace(new Fragment_DoanhThu());
                nvView.getMenu().findItem(R.id.m_home).setChecked(false);
                nvView.getMenu().findItem(R.id.m_book).setChecked(false);
                nvView.getMenu().findItem(R.id.m_category).setChecked(false);
                nvView.getMenu().findItem(R.id.m_PM).setChecked(false);
                nvView.getMenu().findItem(R.id.m_top_book).setChecked(false);
                nvView.getMenu().findItem(R.id.m_listUser).setChecked(false);
                nvView.getMenu().findItem(R.id.m_exit).setChecked(false);
                nvView.getMenu().findItem(R.id.m_changePass).setChecked(false);
                nvView.getMenu().findItem(R.id.m_doanh_thu).setChecked(true);

                break;
            case R.id.m_changePass:
                Intent intent = new Intent(getApplicationContext(), ForgotpassActivity.class);
                startActivity(intent);
                nvView.getMenu().findItem(R.id.m_home).setChecked(false);
                nvView.getMenu().findItem(R.id.m_book).setChecked(false);
                nvView.getMenu().findItem(R.id.m_category).setChecked(false);
                nvView.getMenu().findItem(R.id.m_PM).setChecked(false);
                nvView.getMenu().findItem(R.id.m_top_book).setChecked(false);
                nvView.getMenu().findItem(R.id.m_listUser).setChecked(false);
                nvView.getMenu().findItem(R.id.m_exit).setChecked(false);
                nvView.getMenu().findItem(R.id.m_changePass).setChecked(true);
                nvView.getMenu().findItem(R.id.m_doanh_thu).setChecked(false);
                break;
            case R.id.m_exit:
                finish();
                nvView.getMenu().findItem(R.id.m_home).setChecked(false);
                nvView.getMenu().findItem(R.id.m_book).setChecked(false);
                nvView.getMenu().findItem(R.id.m_category).setChecked(false);
                nvView.getMenu().findItem(R.id.m_PM).setChecked(false);
                nvView.getMenu().findItem(R.id.m_top_book).setChecked(false);
                nvView.getMenu().findItem(R.id.m_listUser).setChecked(false);
                nvView.getMenu().findItem(R.id.m_exit).setChecked(true);
                nvView.getMenu().findItem(R.id.m_changePass).setChecked(false);
                nvView.getMenu().findItem(R.id.m_doanh_thu).setChecked(false);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}