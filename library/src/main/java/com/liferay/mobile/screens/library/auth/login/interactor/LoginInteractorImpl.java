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

package com.liferay.mobile.screens.library.auth.login.interactor;

import com.liferay.mobile.screens.library.auth.login.interactor.task.LoginTask;

/**
 * @author Silvio Santos
 */
public class LoginInteractorImpl implements LoginInteractor {

	public void login(String userName, String password) {
		LoginTask task = new LoginTask(userName, password, this);

		task.execute();
	}

	public void onLoginFailure() {
		if (_listener != null) {
			_listener.onLoginFailure();
		}
	}

	public void onLoginSuccess() {
		if (_listener != null) {
			_listener.onLoginSuccess();
		}
	}

	public void setOnLoginListener(OnLoginListener listener) {
		_listener = listener;
	}

	public interface OnLoginListener {

		void onLoginSuccess();

		void onLoginFailure();

	}

	private OnLoginListener _listener;

}