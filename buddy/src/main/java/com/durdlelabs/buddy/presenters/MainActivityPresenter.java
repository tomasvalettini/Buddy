package com.durdlelabs.buddy.presenters;

import com.durdlelabs.buddy.models.logic.FilePersistance;
import com.durdlelabs.buddy.models.logic.IContactHelper;
import com.durdlelabs.buddy.views.IMainActivityView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.io.File;

import javax.inject.Inject;

public class MainActivityPresenter extends MvpBasePresenter<IMainActivityView> {
    @Inject
    IContactHelper contactHelper;
    @Inject
    FilePersistance fp;
    private File appDir;

    public MainActivityPresenter() {
    }

    public File getAppFile() {
        return appDir;
    }
}