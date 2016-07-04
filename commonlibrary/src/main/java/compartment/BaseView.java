package compartment;

import android.content.Context;

/**
 * Author:  leo
 * Email:   95253575@qq.com | leohak2010@gmail.com
 * Date:    2015/11/10.
 * Description:
 */
public interface BaseView {
    Context getApplicationContext();

    Context getBaseContext();

    void showLoading(String msg);

    void hideLoading();

    void showNetError();
}
