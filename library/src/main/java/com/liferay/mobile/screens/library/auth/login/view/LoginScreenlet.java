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
import android.content.res.TypedArray;

import android.util.AttributeSet;

import android.view.LayoutInflater;

import com.liferay.mobile.screens.library.R;
import com.liferay.mobile.screens.library.auth.login.interactor.AuthMethod;
import com.liferay.mobile.screens.library.auth.login.interactor.LoginInteractor;
import com.liferay.mobile.screens.library.auth.login.interactor.LoginInteractorImpl;
import com.liferay.mobile.screens.library.auth.login.listener.OnLoginListener;
import com.liferay.mobile.screens.library.base.view.BaseScreenlet;

/**
 * @author Silvio Santos
 */
public class LoginScreenlet
	extends BaseScreenlet<LoginScreenletView, LoginInteractor> {

	public LoginScreenlet(Context context) {
		this(context, null);
	}

	public LoginScreenlet(Context context, AttributeSet attributes) {
		this(context, attributes, 0);
	}

	public LoginScreenlet(
		Context context, AttributeSet attributes, int defaultStyle) {

		super(context, attributes, defaultStyle);

		setInteractor(new LoginInteractorImpl());
	}

	public void setAuthMethod(AuthMethod authMethod) {
		_loginView.setAuthMethod(authMethod);
	}

	@Override
	protected LoginScreenletView createScreenletView(
		Context context, AttributeSet attributes) {

		TypedArray typedArray = context.getTheme().obtainStyledAttributes(
			attributes, R.styleable.LoginScreenlet, 0, 0);

		int layoutId = typedArray.getResourceId(
			R.styleable.LoginScreenlet_layoutId, R.layout.login_default);

		_loginView = (LoginScreenletView)
			LayoutInflater.from(getContext()).inflate(layoutId, null);

		int authMethod = typedArray.getInt(
			R.styleable.LoginScreenlet_authMethod, 0);

		setAuthMethod(AuthMethod.getValue(authMethod));

		typedArray.recycle();

		return _loginView;
	}

	private LoginScreenletView _loginView;

}