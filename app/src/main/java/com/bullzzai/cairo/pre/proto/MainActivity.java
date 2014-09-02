package com.bullzzai.cairo.pre.proto;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends ActionBarActivity {
    private Fragment mPreProtoFragments[];

    private int FIRST_SCREEN = 0;
    private int SECOND_SCREEN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragments();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, mPreProtoFragments[FIRST_SCREEN])
                    .commit();
        }
    }

    private void initFragments()
    {
        // First Screen
        ButtonZone buttons[] = new ButtonZone[] {
            new ButtonZone(0, 0, 50, 50, SECOND_SCREEN)
        };
        mPreProtoFragments[FIRST_SCREEN] = new PreProtoFragment(R.layout.fragment_main, buttons);

        // First Screen
        buttons = new ButtonZone[] {
                new ButtonZone(0, 0, 50, 50, FIRST_SCREEN)
        };
        mPreProtoFragments[SECOND_SCREEN] = new PreProtoFragment(R.layout.fragment_main, buttons);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class ButtonZone
    {
        public int mX, mY, mW, mH, mFragmentId;

        public ButtonZone(final int x, final int y, final int w, final int h, final int fragmentId)
        {
            mX = x;
            mY = y;
            mW = w;
            mH = h;
            mFragmentId = fragmentId;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PreProtoFragment extends Fragment {
        private int mLayoutId;
        private ButtonZone[] mButtons;

        public PreProtoFragment(final int layoutId, ButtonZone[] buttons) {
            mLayoutId = layoutId;
            mButtons = buttons;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(mLayoutId, container, false);
            RelativeLayout mainLayout = (RelativeLayout) rootView.findViewById(R.id.main);

            for (final ButtonZone buttonZone: mButtons)
            {
                Button button = new Button(getActivity());
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("LALA", "Try and launch fragment " + buttonZone.mFragmentId);
                    }
                });
                mainLayout.addView(button);
            }

            return rootView;
        }
    }
}
