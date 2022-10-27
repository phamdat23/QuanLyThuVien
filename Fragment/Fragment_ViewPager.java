package vn.edu.poly.duanmau.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import vn.edu.poly.duanmau.Adapter.Adapter_ViewPager;
import vn.edu.poly.duanmau.R;

public class Fragment_ViewPager extends Fragment {
    private TabLayout tab;
    private ViewPager2 viewPager;
    Adapter_ViewPager adapter_viewPager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.layout_view_pager, container, false);
       return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tab = view.findViewById(R.id.tab);
        viewPager = view.findViewById(R.id.viewPager);
        adapter_viewPager = new Adapter_ViewPager(this);
        viewPager.setAdapter(adapter_viewPager);
        TabLayoutMediator mediator = new TabLayoutMediator(tab, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position==0){
                    tab.setText("Phiếu mượn");
                }else if(position==1){
                    tab.setText("Phiếu mượn chi tiết");
                }
            }
        });
        mediator.attach();
    }
}
