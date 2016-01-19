package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;
import com.facebook.*;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.fitness.data.Session;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
    /*String addr;
    int port;*/
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;
    CallbackManager callbackManager;
    Button btn_connect;
    LoginButton loginButton;
    private GoogleApiClient mGoogleApiClient;
    EditText txt_connect;
    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        /*addr = "192.168.0.134";//"130.79.206.217";
        port = 1024;*/
        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setScopes(gso.getScopeArray());
        signInButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                switch (v.getId()) {
                                                    case R.id.sign_in_button:
                                                        signIn();
                                                        break;

                                                }
                                            }
                                        });

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, null /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button_fb);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getApplicationContext(), loginResult.getAccessToken().getUserId()+"LOG VIA FB OK", Toast.LENGTH_SHORT).show();
                checkFacebookLoginDb(loginResult);
                startActivity(new Intent(getApplicationContext(), Connected.class));
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "LOG VIA FB CANCEL", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getApplicationContext(), "LOG VIA FB FAIIIIIIL", Toast.LENGTH_SHORT).show();
            }
        });

        btn_connect      = (Button) findViewById(R.id.connectButton);

        btn_connect.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Connected.class));
            }
        });
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            GoogleSignInAccount acct = result.getSignInAccount();
            checkGoogleLoginDb(acct);
            startActivity(new Intent(getApplicationContext(), Connected.class));
            Toast.makeText(getApplicationContext(),"LOG VIA GOOGLLLLEEEEEEE", Toast.LENGTH_SHORT).show();
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void checkGoogleLoginDb(GoogleSignInAccount acct) {
        int count;
        //Query database -> select count(*) from users where idGoogle=acct.getSignInAccount();
        //on stocke le resultat dans count, mais en attendant on set count a 0
        count=0;
        if(count==0) {
            //ajout dans la db d'un nouveau user -> insert into users values (null,...,acct.getSignInAccount(),...);
            //ouverture d'une nouvelle fenetre selection pseudo, etc ...
        }
    }

    private void checkFacebookLoginDb(LoginResult loginResult) {
        int count;
        //Query database -> select count(*) from users where idGoogle=acct.getSignInAccount();
        //on stocke le resultat dans count, mais en attendant on set count a 0
        count=0;
        if(count==0) {
            //ajout dans la db d'un nouveau user -> insert into users values (null,...,acct.getSignInAccount(),...);
            //ouverture d'une nouvelle fenetre selection pseudo, etc ...
        }
    }

    private void signIn(){
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
}
