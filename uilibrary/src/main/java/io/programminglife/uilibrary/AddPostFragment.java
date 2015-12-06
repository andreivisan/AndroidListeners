package io.programminglife.uilibrary;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;

import io.programminglife.uilibrary.customcomponents.CustomEditText;
import io.programminglife.uilibrary.listener.EditTextImeBackListener;
import io.programminglife.uilibrary.listener.PublishPostListener;

/**
 * Created by andreivisan on 12/5/15.
 */
public class AddPostFragment extends Fragment {

    private CustomEditText mPostContent;
    private RelativeLayout mPostButton;
    private View mFragmentView;
    private Fragment mCurrentFragment;
    private InputMethodManager inputMethodManager;

    private PublishPostListener mPublishPostListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(R.layout.post_form, container, false);
        mCurrentFragment = this;

        mPostContent = (CustomEditText)mFragmentView.findViewById(R.id.post_content);
        mPostButton = (RelativeLayout)mFragmentView.findViewById(R.id.post_button);

        showInputKeyboard();
        initView();

        return mFragmentView;
    }

    @Override
    public void onPause() {
        super.onPause();
        inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    private void showInputKeyboard() {
        inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    private void initView() {
        mPostContent.requestFocus();
        mPostContent.setOnEditTextImeBackListener(new EditTextImeBackListener() {
            @Override
            public void onImeBack(CustomEditText ctrl, String text) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(mCurrentFragment).commit();
            }
        });

        mPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(mPostContent.getText())) {
                    mPublishPostListener.onPostPublished(mPostContent.getText().toString());
                }
            }
        });
    }

    public void setmPublishPostListener(PublishPostListener mPublishPostListener) {
        this.mPublishPostListener = mPublishPostListener;
    }
}
