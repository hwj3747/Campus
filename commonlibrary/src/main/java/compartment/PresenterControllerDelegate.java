package compartment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

public class PresenterControllerDelegate<P extends Presenter> {
    private boolean isDestroyedBySystem;
    private P presenter;

    public void onCreate(P presenter, Bundle savedInstanceState) {
        this.presenter = presenter;
        presenter.onCreate(savedInstanceState);
    }

    @SuppressWarnings("unchecked")
    public void onCreateView(Fragment fragment, View view) {
        try {
            presenter.bindView(fragment);
            presenter.bindBaseView(fragment);
        } catch (ClassCastException e1) {
            try {
                presenter.bindView(view);
                presenter.bindBaseView(view);
            } catch (ClassCastException e2) {
                throw new RuntimeException("Either your view or fragment needs to implement the " +
                        "view interface expected by " + presenter.getClass().getSimpleName() + ".");
            }
        }
    }

    public void onResume() {
        isDestroyedBySystem = false;
    }

    public void onSaveInstanceState(Bundle outState) {
        isDestroyedBySystem = true;
        presenter.onSaveInstanceState(outState);
    }

    public void onDestroyView() {
        presenter.unbindView();
        presenter.unbindBaseView();
    }

    public void onDestroy() {
        if (!isDestroyedBySystem) {
            presenter.onDestroy();
        }
    }
}
