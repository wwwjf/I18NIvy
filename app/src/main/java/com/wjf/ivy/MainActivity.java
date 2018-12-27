package com.wjf.ivy;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Set;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private LoginButton loginButton;
    private TextView mTvInfo;
    private CallbackManager callbackManager;
    private ProfileTracker mProfileTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.login_button);
        mTvInfo = findViewById(R.id.tv_info);

        findViewById(R.id.login_button).setOnClickListener(v -> {
            Toast.makeText(this, "登录", Toast.LENGTH_SHORT).show();
        });
        findViewById(R.id.btn_map).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, MapActivity.class)));

        findViewById(R.id.btn_copy).setOnClickListener(v -> {
            ClipboardUtil.copy(mTvInfo.getText().toString(),MainActivity.this);
        });
        findViewById(R.id.btn_paste).setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, ClipboardUtil.paste(MainActivity.this), Toast.LENGTH_SHORT).show();
        });
        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions("email", "public_profile", "user_friends");

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn){
            getInfo(accessToken);
        }
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        AccessToken accessToken = loginResult.getAccessToken();
                        getInfo(accessToken);

                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Log.i(TAG, "onCancel: ---------");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Log.i(TAG, "onError: " + exception.toString());
                    }
                });

        mProfileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                String profileInfo = "";
                String oldProfileInfo = "";
                String currentProfileInfo = "";
                if (oldProfile != null) {
                    String id = oldProfile.getId();
                    String firstName = oldProfile.getFirstName();
                    String middleName = oldProfile.getMiddleName();
                    String lastName = oldProfile.getLastName();
                    String name = oldProfile.getName();
                    Uri linkUri = oldProfile.getLinkUri();
                    int describeContents = oldProfile.describeContents();
                    Uri profilePictureUri = oldProfile.getProfilePictureUri(100, 100);
                    String path = profilePictureUri.getPath();
                    oldProfileInfo = "id=" + id + ",firstName=" + firstName + ",middleName=" + middleName +
                            ",lastName=" + lastName + ",name=" + name + ",describeContents=" + describeContents +
                            ",path=" + path;
                }
                if (currentProfile != null) {

                    String id = currentProfile.getId();
                    String firstName = currentProfile.getFirstName();
                    String middleName = currentProfile.getMiddleName();
                    String lastName = currentProfile.getLastName();
                    String name = currentProfile.getName();
                    Uri linkUri = currentProfile.getLinkUri();
                    int describeContents = currentProfile.describeContents();
                    Uri profilePictureUri = currentProfile.getProfilePictureUri(100, 100);
                    String path = profilePictureUri.getPath();
                    currentProfileInfo = "id=" + id + ",firstName=" + firstName + ",middleName=" + middleName +
                            ",lastName=" + lastName + ",name=" + name + ",describeContents=" + describeContents +
                            ",path=" + path;
                }
                profileInfo = "oldProfileInfo="+oldProfileInfo+",currentProfileInfo="+ currentProfileInfo;
                Log.i(TAG, "onCurrentProfileChanged: "+profileInfo);
            }
        };
        mProfileTracker.startTracking();
    }

    private void getInfo(AccessToken accessToken) {
        String applicationId = accessToken.getApplicationId();
        int describeContents = accessToken.describeContents();
        long dataAccessExpirationTime = accessToken.getDataAccessExpirationTime().getTime();
        long expiresTime = accessToken.getExpires().getTime();
        long lastRefreshTime = accessToken.getLastRefresh().getTime();
        String sourceName = accessToken.getSource().name();
        String token = accessToken.getToken();
        String userId = accessToken.getUserId();
        String info = "applicationId=" + applicationId +
                "\n\ndescribeContents=" + describeContents +
                "\n\nexpiresTime=" + expiresTime +
                "\n\ndataAccessExpirationTime=" + dataAccessExpirationTime +
                "\n\nlastRefreshTime=" + lastRefreshTime +
                "\n\nsourceName=" + sourceName +
                "\n\ntoken=" + token +
                "\n\nuserId=" + userId;
        mTvInfo.setText(info);
        Log.i(TAG, "-----onSuccess: " + accessToken.toString());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mProfileTracker != null) {
            mProfileTracker.stopTracking();
        }
    }
}
