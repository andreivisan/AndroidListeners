package io.programminglife.androidlisteners;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import io.programminglife.uilibrary.AddPostFragment;
import io.programminglife.uilibrary.listener.PublishPostListener;

public class MainActivity extends FragmentActivity {

    private CreatePostFragment mCreatePostFragment;
    private TextView mPostContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCreatePostFragment = new CreatePostFragment();
        mPostContent = (TextView)findViewById(R.id.posted_content);

        initView();
    }

    private void initView() {
        getSupportFragmentManager().
                beginTransaction().
                add(R.id.fragment_container, mCreatePostFragment).
                commit();
    }

    public void addPost(View view) {
        AddPostFragment addPostFragment = new AddPostFragment();
        addPostFragment.setmPublishPostListener(new PublishPostListener() {
            @Override
            public void onPostPublished(String postContent) {
                mPostContent.setText(postContent);
            }
        });

        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragment_container, addPostFragment).
                addToBackStack(null).
                commit();
    }
}
