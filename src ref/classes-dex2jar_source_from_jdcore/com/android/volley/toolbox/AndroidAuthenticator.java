package com.android.volley.toolbox;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.android.volley.AuthFailureError;

public class AndroidAuthenticator
  implements Authenticator
{
  private final Account mAccount;
  private final AccountManager mAccountManager;
  private final String mAuthTokenType;
  private final boolean mNotifyAuthFailure;
  
  AndroidAuthenticator(AccountManager paramAccountManager, Account paramAccount, String paramString, boolean paramBoolean)
  {
    mAccountManager = paramAccountManager;
    mAccount = paramAccount;
    mAuthTokenType = paramString;
    mNotifyAuthFailure = paramBoolean;
  }
  
  public AndroidAuthenticator(Context paramContext, Account paramAccount, String paramString)
  {
    this(paramContext, paramAccount, paramString, false);
  }
  
  public AndroidAuthenticator(Context paramContext, Account paramAccount, String paramString, boolean paramBoolean)
  {
    this(AccountManager.get(paramContext), paramAccount, paramString, paramBoolean);
  }
  
  public Account getAccount()
  {
    return mAccount;
  }
  
  public String getAuthToken()
    throws AuthFailureError
  {
    AccountManagerFuture localAccountManagerFuture = mAccountManager.getAuthToken(mAccount, mAuthTokenType, mNotifyAuthFailure, null, null);
    try
    {
      Bundle localBundle = (Bundle)localAccountManagerFuture.getResult();
      Object localObject2 = null;
      Object localObject1 = localObject2;
      if (localAccountManagerFuture.isDone())
      {
        localObject1 = localObject2;
        if (!localAccountManagerFuture.isCancelled())
        {
          if (localBundle.containsKey("intent")) {
            throw new AuthFailureError((Intent)localBundle.getParcelable("intent"));
          }
          localObject1 = localBundle.getString("authtoken");
        }
      }
      if (localObject1 == null)
      {
        localObject1 = String.valueOf(mAuthTokenType);
        if (((String)localObject1).length() != 0) {
          localObject1 = "Got null auth token for type: ".concat((String)localObject1);
        } else {
          localObject1 = new String("Got null auth token for type: ");
        }
        throw new AuthFailureError((String)localObject1);
      }
      return localObject1;
    }
    catch (Exception localException)
    {
      throw new AuthFailureError("Error while retrieving auth token", localException);
    }
  }
  
  public String getAuthTokenType()
  {
    return mAuthTokenType;
  }
  
  public void invalidateAuthToken(String paramString)
  {
    mAccountManager.invalidateAuthToken(mAccount.type, paramString);
  }
}
