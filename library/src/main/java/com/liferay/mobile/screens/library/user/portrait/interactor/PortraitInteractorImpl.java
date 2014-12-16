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

package com.liferay.mobile.screens.library.user.portrait.interactor;

import android.graphics.Bitmap;

import com.liferay.mobile.screens.library.user.portrait.interactor.task.PortraitAsyncTask;
import com.liferay.mobile.screens.library.user.portrait.view.PortraitScreenlet;
import com.liferay.mobile.screens.library.util.FileUtil;

/**
 * @author Silvio Santos
 */
public class PortraitInteractorImpl implements PortraitInteractor {

	public PortraitInteractorImpl(PortraitScreenlet screenlet) {
		_screenlet = screenlet;
	}

	public String getPortraitsFolder(long portraitId) {
		StringBuilder sb = new StringBuilder();

		sb.append(FileUtil.getExternalCacheDirectory());
		sb.append("/");
		sb.append(portraitId);
		sb.append(".jpg");

		return sb.toString();
	}

	public void load(long portraitId, String uuid) {
		String path = getPortraitsFolder(portraitId);

		PortraitAsyncTask task = new PortraitAsyncTask(
			portraitId, uuid, path, this);

		task.execute();
	}

	public void onPortraitLoadFailure() {
	}

	public void onPortraitLoadSuccess(Bitmap bitmap) {
		_screenlet.load(bitmap);
	}

	private PortraitScreenlet _screenlet;

}