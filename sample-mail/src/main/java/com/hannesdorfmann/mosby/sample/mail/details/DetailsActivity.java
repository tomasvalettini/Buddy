package com.hannesdorfmann.mosby.sample.mail.details;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hannesdorfmann.mosby.MosbyActivity;
import com.hannesdorfmann.mosby.sample.mail.R;
import com.hannesdorfmann.mosby.sample.mail.model.contact.Person;
import com.hannesdorfmann.mosby.sample.mail.model.mail.Mail;
import com.hannesdorfmann.mosby.sample.mail.utils.BuildUtils;

import butterknife.Bind;

/**
 * @author Hannes Dorfmann
 */
public class DetailsActivity extends MosbyActivity {

  public static final String KEY_MAIL = "com.hannesdorfmann.mosby.MosbyActivity.MAIL";

  @Bind(R.id.toolbar) Toolbar toolbar;

  @TargetApi(21) @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mail_details);

    // Activity Transitions
    if (BuildUtils.isMinApi21()) {
      postponeEnterTransition();
    }

    toolbar.setNavigationIcon(BuildUtils.getBackArrowDrawable(this));
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (Build.VERSION.SDK_INT >= 21) {
          finishAfterTransition();
        } else {
          finish();
        }
      }
    });

    if (savedInstanceState == null) {
      Mail mail = getIntent().getParcelableExtra(KEY_MAIL);
      Person sender = mail.getSender();

      DetailsFragment fragment =
          new DetailsFragmentBuilder(mail.getDate().getTime(), mail.getId(), sender.getEmail(),
              sender.getName(), sender.getImageRes(), mail.isStarred(), mail.getSubject()).build();

      getSupportFragmentManager().beginTransaction()
          .replace(R.id.fragmentContainer, fragment)
          .commit();
    }
  }



}
