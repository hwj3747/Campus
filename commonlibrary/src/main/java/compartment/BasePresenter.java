package compartment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class BasePresenter<V> extends AbsBasePresenter<V,BaseView> {
    private V view;
    @Override public void onCreate(@Nullable Bundle bundle) {}
    @Override public void bindView(V view) {
        this.view = view;
    }
    @Override public void unbindView() {
        this.view = null;
    }
    @Override public void onSaveInstanceState(@NonNull Bundle bundle) {}
    @Override public V getView() {
        return view;
    }
    @Override public void onDestroy() {}
}
