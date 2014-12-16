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

package com.liferay.mobile.screens.library.auth.login.interactor.task;

import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.v62.user.UserService;
import com.liferay.mobile.screens.library.auth.login.interactor.LoginInteractor;
import com.liferay.mobile.screens.library.base.interactor.task.BaseAsyncTask;
import com.liferay.mobile.screens.library.util.LiferayServerContext;
import com.liferay.mobile.screens.library.util.SessionContext;

import org.json.JSONObject;

/**
 * @author Silvio Santos
 */
public class LoginTask extends BaseAsyncTask {

	public LoginTask(
		String login, String password, LoginInteractor interactor) {

		_login = login;
		_password = password;
		_interactor = interactor;
	}

	@Override
	protected Object doInBackground(Object[] params) {
		JSONObject result = null;

		try {
			result = sendGetUserRequest(_login);
		}
		catch (Exception e) {
			cancel(true);
		}

		return result;
	}

	@Override
	protected void onCancelled() {
		_interactor.onLoginFailure();
	}

	@Override
	protected void onPostExecute(Object o) {
		_interactor.onLoginSuccess();
	}

	@Override
	protected void onPreExecute() {
		_session = SessionContext.createSession(_login, _password);
	}

	protected JSONObject sendGetUserRequest(String email) throws Exception {
		UserService service = new UserService(_session);

		JSONObject jsonObj = service.getUserByEmailAddress(
			LiferayServerContext.getCompanyId(), email);

		return jsonObj;
	}

	private LoginInteractor _interactor;
	private String _login;
	private String _password = "test";
	private Session _session;

}