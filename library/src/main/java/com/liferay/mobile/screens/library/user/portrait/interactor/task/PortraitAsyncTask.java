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

package com.liferay.mobile.screens.library.user.portrait.interactor.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.AsyncTask;

import android.util.Log;

import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.util.PortraitUtil;
import com.liferay.mobile.screens.library.base.interactor.event.BaseEvent;
import com.liferay.mobile.screens.library.user.portrait.event.PortraitEvent;
import com.liferay.mobile.screens.library.util.EventBusUtil;
import com.liferay.mobile.screens.library.util.SessionContext;

/**
 * @author Silvio Santos
 */
public class PortraitAsyncTask extends AsyncTask<Void, Void, Bitmap> {

	public PortraitAsyncTask(long portraitId, String uuid, String filePath) {
		_potraitId = portraitId;
		_uuid = uuid;
		_filePath = filePath;
	}

	@Override
	protected Bitmap doInBackground(Void... params) {
		Session session = SessionContext.getSession();
		String portraitUrl = PortraitUtil.getPortraitURL(
			session, true, _potraitId, _uuid);

		try {
			PortraitUtil.downloadPortrait(session, portraitUrl, _filePath);

			return BitmapFactory.decodeFile(_filePath);
		}
		catch (Exception e) {
			Log.e(_CLASS_NAME, "Failed to download portrait");
		}

		return null;
	}

	@Override
	protected void onPostExecute(Bitmap bitmap) {
		EventBusUtil.post(new PortraitEvent(BaseEvent.REQUEST_SUCCESS, bitmap));
	}

	private static final String _CLASS_NAME =
		PortraitAsyncTask.class.getSimpleName();

	private String _filePath;
	private long _potraitId;
	private String _uuid;

}