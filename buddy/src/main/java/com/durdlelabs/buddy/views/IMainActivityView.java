package com.durdlelabs.buddy.views;

import com.hannesdorfmann.mosby.mvp.MvpView;

public interface IMainActivityView extends MvpView {
    public void showToastMsg(String msg);
}