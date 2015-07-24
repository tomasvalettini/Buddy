package com.durdlelabs.buddy.dependencyinjection.modules.contacthelper;

import android.content.ContentResolver;
import android.content.res.Resources;

import com.durdlelabs.buddy.models.logic.ContactHelperVcf;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ContactHelperModule {
    @Provides
    @Singleton
    ContactHelperVcf provideContactHelperVcf(Resources res, ContentResolver cr)
    {
        return new ContactHelperVcf(res, cr);
    }
}