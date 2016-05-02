package ubbs.home.com.corelib.sample.fragment;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ubbs.home.com.core.lib.ui.UBBSButton;
import ubbs.home.com.corelib.sample.R;
import ubbs.home.com.corelib.sample.activity.DisplayImageActivity;

/**
 * Created by udyatbhanu-mac on 7/4/15.
 */
public class FirstFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        UBBSButton button  = (UBBSButton)rootView.findViewById(R.id.clickButton);

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DisplayImageActivity.class);
                startActivity(intent);
            }
        });
        return rootView;

    }
}
