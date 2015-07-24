package com.durdlelabs.buddy.presenters;

import com.durdlelabs.buddy.model.logic.FilePersistance;
import com.durdlelabs.buddy.model.logic.IContactHelper;
import com.durdlelabs.buddy.views.IMainActivityView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.io.File;

import javax.inject.Inject;

public class MainActivityPresenter extends MvpBasePresenter<IMainActivityView> {
    private IContactHelper contactHelper;
    private File appDir;
    @Inject private FilePersistance fp;

    public MainActivityPresenter() {
    }

    public File getAppFile() {
        return appDir;
    }
}