package com.acme.xxlightingcontrol.lib.base;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * @author jx9@msn.com
 */
public abstract class FragmentContainerActivity extends BaseActivity {

    private Fragment currentFragment;

    public void switchFragment(int containerViewId, Fragment fragment, String tag) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment targetFragment = fm.findFragmentByTag(tag);
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        if (targetFragment == null) {
            if (null != currentFragment) {
                fragmentTransaction.hide(currentFragment);
            }

            fragmentTransaction.add(containerViewId, fragment, tag);
            currentFragment = fragment;
        } else {
            if (targetFragment.isAdded()) {
                fragmentTransaction.hide(currentFragment).show(targetFragment);
            }

            currentFragment = targetFragment;
        }

        fragmentTransaction.commit();
    }

}