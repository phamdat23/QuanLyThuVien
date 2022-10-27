package vn.edu.poly.duanmau.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import vn.edu.poly.duanmau.Fragment.Fragment_PMCT;
import vn.edu.poly.duanmau.Fragment.Fragment_PhieuMuon;

public class Adapter_ViewPager extends FragmentStateAdapter {
    public Adapter_ViewPager(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Fragment_PhieuMuon();
            case 1:
                return new Fragment_PMCT();
            default:
                return new Fragment_PhieuMuon();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
