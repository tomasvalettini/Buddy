/*
 * Copyright 2015 Hannes Dorfmann.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hannesdorfmann.mosby.mvp.layout;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.hannesdorfmann.mosby.mvp.delegate.MvpDelegateCallback;
import com.hannesdorfmann.mosby.mvp.delegate.ViewGroupMvpDelegate;
import com.hannesdorfmann.mosby.mvp.delegate.ViewGroupMvpDelegateImpl;

import butterknife.ButterKnife;

/**
 * A LinearLayout that can be used as view with an presenter
 *
 * @author Hannes Dorfmann
 * @since 1.1
 */
public abstract class MvpLinearLayout<V extends MvpView, P extends MvpPresenter<V>>
    extends LinearLayout implements MvpView, MvpDelegateCallback<V, P> {

  protected P presenter;
  protected ViewGroupMvpDelegate<V, P> mvpDelegate;

  public MvpLinearLayout(Context context) {
    super(context);
  }

  public MvpLinearLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MvpLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @TargetApi(21)
  public MvpLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    ButterKnife.bind(this);
  }

  /**
   * Get the mvp delegate. This is internally used for creating presenter, attaching and detaching
   * view from presenter etc.
   *
   * <p><b>Please note that only one instance of mvp delegate should be used per android.view.View
   * instance</b>.
   * </p>
   *
   * <p>
   * Only override this method if you really know what you are doing.
   * </p>
   *
   * @return {@link ViewGroupMvpDelegateImpl}
   */
  @NonNull protected ViewGroupMvpDelegate<V, P> getMvpDelegate() {
    if (mvpDelegate == null) {
      mvpDelegate = new ViewGroupMvpDelegateImpl<>(this);
    }

    return mvpDelegate;
  }

  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    getMvpDelegate().onAttachedToWindow();
  }

  @Override protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    getMvpDelegate().onDetachedFromWindow();
  }

  /**
   * Instantiate a presenter instance
   *
   * @return The {@link MvpPresenter} for this view
   */
  public abstract P createPresenter();

  @Override public P getPresenter() {
    return presenter;
  }

  @Override public void setPresenter(P presenter) {
    this.presenter = presenter;
  }

  @Override public V getMvpView() {
    return (V) this;
  }

  @Override public boolean isRetainingInstance() {
    return false;
  }
}
