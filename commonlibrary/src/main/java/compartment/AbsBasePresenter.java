package compartment;

public abstract class AbsBasePresenter<V,BV> implements Presenter<V,BV> {
    protected BV baseView;
    @Override public BV getBaseView() { return baseView; }
    @Override public void bindBaseView(BV view) {
        this.baseView = view;
    }
    @Override public void unbindBaseView() {
        this.baseView = null;
    }
}
