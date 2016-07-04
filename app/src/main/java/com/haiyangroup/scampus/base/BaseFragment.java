package com.haiyangroup.scampus.base;

import com.haiyangroup.library.absBase.AbsBaseFragment;

import compartment.HasPresenter;
import compartment.Presenter;

/**
 * Author:  leo
 * Email:   95253575@qq.com | leohak2010@gmail.com
 * Date:    2015/11/5.
 * Description:
 */
public abstract class BaseFragment<T,C extends HasPresenter<P>, P extends Presenter> extends AbsBaseFragment<T,C,P> {
}
