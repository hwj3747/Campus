package compartment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface Presenter<V,BV> {
    void onCreate(@Nullable Bundle bundle);
    void onSaveInstanceState(@NonNull Bundle bundle);
    void onDestroy();
    void bindView(V view);
    void unbindView();
    V getView();
    BV getBaseView();
    void bindBaseView(BV view);
    void unbindBaseView();
}
