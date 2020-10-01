package kz.firstproject.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import kz.firstproject.R;
import kz.firstproject.ui.dashboard.DashboardFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel home2ViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        home2ViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        home2ViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        root.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle result = new Bundle();
                result.putString("bundleKey", home2ViewModel.getText().getValue());
                DashboardFragment df = new DashboardFragment();
                df.setArguments(result);
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment, df)
                        .addToBackStack(null)
                        .commit();
            }
        });
        return root;
    }
}