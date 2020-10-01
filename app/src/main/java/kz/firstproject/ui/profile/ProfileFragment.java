package kz.firstproject.ui.profile;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kz.firstproject.R;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;

    TextView text_profile;
    String LOG = "ProfileFragment";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.v(LOG, "onAttach()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(LOG, "onCreate()");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.v(LOG, "onCreateView()");
        View v = inflater.inflate(R.layout.profile_fragment, container, false);
        text_profile = v.findViewById(R.id.text_profile);

        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);

        mViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                text_profile.setText(s);
            }
        });
        return v;
    }


    /**
     * Called when the fragment's activity has been created and this
     * fragment's view hierarchy instantiated.  It can be used to do final
     * initialization once these pieces are in place, such as retrieving
     * views or restoring state.  It is also useful for fragments that use
     * {@link #setRetainInstance(boolean)} to retain their instance,
     * as this callback tells the fragment when it is fully associated with
     * the new activity instance.  This is called after {@link #onCreateView}
     * and before {@link #onViewStateRestored(Bundle)}.
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v(LOG, "onActivityCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.v(LOG, "onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v(LOG, "onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v(LOG, "onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.v(LOG, "onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.v(LOG, "onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(LOG, "onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.v(LOG, "onDetach()");
    }
}