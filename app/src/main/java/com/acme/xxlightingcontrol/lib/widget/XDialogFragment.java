package com.acme.xxlightingcontrol.lib.widget;

import android.view.View;
import android.widget.TextView;

import com.acme.xxlightingcontrol.R;
import com.acme.xxlightingcontrol.databinding.FragmentDialogXBinding;
import com.acme.xxlightingcontrol.lib.base.BaseDialogFragment;

import androidx.fragment.app.DialogFragment;

/**
 * @author jx9@msn.com
 */
public class XDialogFragment extends BaseDialogFragment {

    TextView mMsgTV;

    TextView mOkTV;

    TextView cancelTV;

    View cancelV;

    private String mMsg;

    private XDialogListener mListener;

    private FragmentDialogXBinding fragmentDialogXBinding;

    private boolean isNegative;

    public static XDialogFragment newInstance(String msg, boolean isNegative,
                                              XDialogListener listener) {
        XDialogFragment fragment = new XDialogFragment();
        fragment.setCancelable(false);
        fragment.mMsg = msg;
        fragment.isNegative = isNegative;
        fragment.mListener = listener;
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_dialog_x;
    }

    @Override
    protected void initView() {
        fragmentDialogXBinding = FragmentDialogXBinding.inflate(getLayoutInflater());
        fragmentDialogXBinding.msgTv.setText(this.mMsg);
        fragmentDialogXBinding.okTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onDialogPositiveClick(XDialogFragment.this);
                XDialogFragment.this.dismissAllowingStateLoss();
            }
        });
        fragmentDialogXBinding.cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.setNegativeClick(XDialogFragment.this);
                XDialogFragment.this.dismissAllowingStateLoss();
            }
        });
        if (!isNegative) {
            cancelV.setVisibility(View.GONE);
        }
    }

    public interface XDialogListener {

        void onDialogPositiveClick(DialogFragment dialog);

        void setNegativeClick(DialogFragment dialog);
    }
}
