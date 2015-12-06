package io.programminglife.uilibrary.customcomponents;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

import io.programminglife.uilibrary.listener.EditTextImeBackListener;

/**
 * Created by andreivisan on 12/5/15.
 */
public class CustomEditText extends EditText {

    private EditTextImeBackListener mOnImeBack;

    public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public CustomEditText(Context context) {
        super(context);

    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (mOnImeBack != null) {
                mOnImeBack.onImeBack(this, this.getText().toString());
            }
        }
        return super.dispatchKeyEvent(event);
    }

    public void setOnEditTextImeBackListener(EditTextImeBackListener listener) {
        mOnImeBack = listener;
    }

}
