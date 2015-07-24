package com.hannesdorfmann.mosby.sample.mail.profile.about;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.mosby.MosbyFragment;
import com.hannesdorfmann.mosby.sample.mail.R;
import com.hannesdorfmann.mosby.sample.mail.model.contact.Person;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.Bind;

/**
 * @author Hannes Dorfmann
 */
public class AboutFragment extends MosbyFragment {

  @Arg Person person;

  @Bind(R.id.email) TextView email;
  @Bind(R.id.birthday) TextView birthday;
  @Bind(R.id.bio) TextView bio;

  @Override protected int getLayoutRes() {
    return R.layout.fragment_about;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy", Locale.getDefault());

    if (!TextUtils.isEmpty(person.getEmail())) {
      email.setText(person.getEmail());
    }

    if (person.getBirthday() != null) {
      birthday.setText(sdf.format(person.getBirthday()));
    }

    if (person.getBioRes() != 0) {
      bio.setText(person.getBioRes());
    }
  }
}
