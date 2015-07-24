package com.hannesdorfmann.mosby.sample.dagger1.model;

import com.hannesdorfmann.mosby.retrofit.exception.NetworkException;

/**
 * @author Hannes Dorfmann
 */
public class ErrorMessageDeterminer {

  public String getErrorMessage(Throwable e, boolean pullToRefresh) {
    if (e instanceof NetworkException) {
      return "Network Error: Are you connected to the internet?";
    }

    return "An unknown error has occurred";
  }
}
