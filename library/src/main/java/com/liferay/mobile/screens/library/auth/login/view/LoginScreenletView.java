/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.mobile.screens.library.auth.login.view;

import android.content.Context;

import android.util.AttributeSet;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.liferay.mobile.screens.library.R;
import com.liferay.mobile.screens.library.auth.login.interactor.AuthMethod;
import com.liferay.mobile.screens.library.auth.login.listener.OnLoginListener;

/**
 * @author Silvio Santos
 */
public class LoginScreenletView extends LinearLayout
	implements LoginData, View.OnClickListener, OnLoginListener {

	public LoginScreenletView(Context context) {
		this(context, null);
	}

	public LoginScreenletView(Context context, AttributeSet attributes) {
		this(context, attributes, 0);
	}

	public LoginScreenletView(
		Context context, AttributeSet attributes, int defaultStyle) {

		super(context, attributes, defaultStyle);
	}

	@Override
	public AuthMethod getAuthMethod() {
		return _authMethod;
	}

	@Override
	public String getLogin() {
		return _loginEditText.getText().toString();
	}

	@Override
	public String getPassword() {
		return _passwordEditText.getText().toString();
	}

	@Override
	public void onClick(View view) {
		LoginScreenlet loginScreenlet = (LoginScreenlet)getParent();

		loginScreenlet.onUserAction(view.getId());
	}

	@Override
	public void onLoginFailure(Exception e) {
	}

	@Override
	public void onLoginSuccess() {
	}

	public void setAuthMethod(AuthMethod authMethod) {
		_authMethod = authMethod;
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();

		Button loginButton = (Button)findViewById(R.id.login_button);
		loginButton.setOnClickListener(this);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

		_loginEditText = (EditText)findViewById(R.id.login);
		_passwordEditText = (EditText)findViewById(R.id.password);
	}

	private AuthMethod _authMethod;
	private EditText _loginEditText;
	private EditText _passwordEditText;

}