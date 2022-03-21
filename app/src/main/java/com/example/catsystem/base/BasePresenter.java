package com.example.catsystem.base;

import java.lang.ref.WeakReference;

/**
 * author ： yxm521
 * time    ： 2022/3/21
 * presenter的base类
 */
public class BasePresenter<T extends IBaseView> {
    protected WeakReference<T> view;

    /**
     * 绑定view
     * @param view
     */
    public void attchView(T view){
        this.view = new WeakReference<>(view);
    }

    /**
     * 解绑
     */
    public void detachView(){
        if(view != null){
            view.clear();
            view = null;
        }
    }
}
