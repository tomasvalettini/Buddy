package com.hannesdorfmann.mosby.sample.mail;

import com.hannesdorfmann.mosby.sample.mail.dagger.NavigationModule;
import dagger.Component;
import javax.inject.Singleton;

/**
 * @author Hannes Dorfmann
 */
@Singleton
@Component(
    modules = NavigationModule.class) public interface MainActivityComponent {

  public void inject(MainActivity activity);
}
