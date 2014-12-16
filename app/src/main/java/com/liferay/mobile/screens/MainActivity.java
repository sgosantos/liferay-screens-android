package com.liferay.mobile.screens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.liferay.mobile.screens.library.auth.login.interactor.LoginInteractorImpl;
import com.liferay.mobile.screens.library.auth.login.view.LoginScreenlet;

/**
 * @author Silvio Santos
 */
public class MainActivity extends Activity
	implements LoginInteractorImpl.OnLoginListener {

	@Override
	protected void onCreate(Bundle state) {
		super.onCreate(state);

		setContentView(R.layout.activity_main);

		LoginScreenlet loginScreenlet = (LoginScreenlet)findViewById(
			R.id.login_screenlet);

		LoginInteractorImpl loginInteractor = new LoginInteractorImpl();
		loginInteractor.setOnLoginListener(this);

		loginScreenlet.setInteractor(loginInteractor);
	}

	@Override
	public void onLoginFailure() {
		Toast.makeText(this, "Failed to login", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onLoginSuccess() {
		Intent intent = new Intent(this, PortraitActivity.class);

		startActivity(intent);

		finish();
	}

}
