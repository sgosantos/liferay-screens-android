package com.liferay.mobile.screens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.liferay.mobile.screens.library.auth.login.interactor.LoginInteractorImpl;
import com.liferay.mobile.screens.library.auth.login.listener.OnLoginListener;
import com.liferay.mobile.screens.library.auth.login.view.LoginScreenlet;

/**
 * @author Silvio Santos
 */
public class MainActivity extends Activity
	implements OnLoginListener {

	@Override
	protected void onCreate(Bundle state) {
		super.onCreate(state);

		setContentView(R.layout.activity_main);

		LoginScreenlet loginScreenlet = (LoginScreenlet)findViewById(
			R.id.login_screenlet);

		loginScreenlet.setOnLoginListener(this);

	}

	@Override
	public void onLoginFailure(Exception e) {
		Toast.makeText(
			this, "Failed to login " + e.getMessage(), Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onLoginSuccess() {
		Intent intent = new Intent(this, PortraitActivity.class);

		startActivity(intent);

		finish();
	}

}
