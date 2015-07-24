package com.durdlelabs.buddy.views;

import com.hannesdorfmann.mosby.mvp.MvpView;

public interface IMainActivityView extends MvpView {
    public void backupContacts();
    public void restoreContacts();
    public void deleteContacts();
    public void shareContacts();
}