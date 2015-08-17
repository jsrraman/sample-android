package com.rajaraman.sample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.MotionEvent;

import com.rajaraman.sample.ui.TestActivity_;

public class CustomLinkMovementMethod extends LinkMovementMethod {
    private static String TAG = "CustomLinkMovementMethod";

    private static CustomLinkMovementMethod linkMovementMethod = new CustomLinkMovementMethod();
    private static Context movementContext;

    public boolean onTouchEvent(android.widget.TextView widget, android.text.Spannable buffer, android.view.MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            int y = (int) event.getY();

            x -= widget.getTotalPaddingLeft();
            y -= widget.getTotalPaddingTop();

            x += widget.getScrollX();
            y += widget.getScrollY();

            Layout layout = widget.getLayout();
            int line = layout.getLineForVertical(y);
            int off = layout.getOffsetForHorizontal(line, x);

            URLSpan[] link = buffer.getSpans(off, off, URLSpan.class);

            if (link.length != 0) {
                String url = link[0].getURL();

                if (url.equals("szapp://TestActivity")) {
                    Intent intent = new Intent(movementContext, TestActivity_.class);
                    movementContext.startActivity(intent);
                    ((Activity) movementContext).finish();
                }

                return true;
            }
        }

        return super.onTouchEvent(widget, buffer, event);
    }

    public static android.text.method.MovementMethod getInstance(Context context) {
        movementContext = context;
        return linkMovementMethod;
    }
}