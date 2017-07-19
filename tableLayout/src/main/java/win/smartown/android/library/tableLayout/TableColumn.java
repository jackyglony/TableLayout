package win.smartown.android.library.tableLayout;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Smartown on 2017/7/19.
 */
public class TableColumn extends LinearLayout {

    private Callback callback;

    public TableColumn(Context context, Callback callback) {
        super(context);
        this.callback = callback;
        init();
    }

    private void init() {
        Log.i("TableColumn", "init");
        setOrientation(VERTICAL);
        int padding = callback.getTableLayout().getTableColumnPadding();
        setPadding(padding, 0, padding, 0);

        initContent();
    }

    private void initContent() {
        float maxTextViewWidth = 0;

        String[] texts = {"a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa"};
        ArrayList<TextView> textViews = new ArrayList<>();
        for (String text : texts) {
            TextView textView = new TextView(getContext());
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, callback.getTableLayout().getTableTextSize());
            textView.setTextColor(callback.getTableLayout().getTableTextColor());
            maxTextViewWidth = Math.max(maxTextViewWidth, Util.measureTextViewWidth(textView, text));
            textView.setGravity(getTextGravity(callback.getTableLayout().getTableTextGravity()));
            textView.setText(text);
            textViews.add(textView);
        }
        LayoutParams layoutParams = new LayoutParams((int) maxTextViewWidth, callback.getTableLayout().getTableRowHeight());
        for (TextView textView : textViews) {
            addView(textView, layoutParams);
        }
    }

    private int getTextGravity(int tableTextGravity) {
        switch (tableTextGravity) {
            case 1:
                return Gravity.CENTER_VERTICAL;
            case 2:
                return Gravity.CENTER_VERTICAL | Gravity.RIGHT;
        }
        return Gravity.CENTER;
    }

    public interface Callback {
        TableLayout getTableLayout();
    }

}