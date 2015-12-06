package io.programminglife.uilibrary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import io.programminglife.uilibrary.customcomponents.CustomEditText;
import io.programminglife.uilibrary.listener.PublishPostListener;

/**
 * Created by andreivisan on 12/5/15.
 */
public class AddPostFragment extends Fragment {

    private CustomEditText mPostContent;
    private RelativeLayout mPostButton;
    private View mFragmentView;

    private PublishPostListener mPublishPostListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(R.layout.post_form, container, false);

        mPostContent = (CustomEditText)mFragmentView.findViewById(R.id.post_content);
        mPostButton = (RelativeLayout)mFragmentView.findViewById(R.id.post_button);

        initView();

        return mFragmentView;
    }

    private void initView() {
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
