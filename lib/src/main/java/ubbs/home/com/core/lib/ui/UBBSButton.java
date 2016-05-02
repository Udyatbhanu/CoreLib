package ubbs.home.com.core.lib.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.Button;

import ubbs.home.com.core.lib.R;

/**
 * Created by udyatbhanu-mac on 7/2/15.
 */
public class UBBSButton extends Button {


    public UBBSButton(Context context) {
        super(context);
        setBackGroundStyle();
        setButtonDefaultDimension();


    }

    public UBBSButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackGroundStyle();
        setButtonDefaultDimension();
    }

    public UBBSButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackGroundStyle();
        setButtonDefaultDimension();
    }




    private void setBackGroundStyle(){
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            setBackgroundDrawable(getResources().getDrawable(R.drawable.button_style));
        } else {
            this.setBackground(getResources().getDrawable(R.drawable.button_style));
        }
    }

    private void setButtonDefaultDimension(){
        setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
    }
}
