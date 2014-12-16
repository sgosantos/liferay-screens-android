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

package com.liferay.mobile.screens;

import android.app.Activity;
import android.os.Bundle;

import com.liferay.mobile.screens.library.user.portrait.interactor.PortraitInteractor;
import com.liferay.mobile.screens.library.user.portrait.interactor.PortraitInteractorImpl;
import com.liferay.mobile.screens.library.user.portrait.view.PortraitScreenlet;

/**
 * @author Silvio Santos
 */
public class PortraitActivity extends Activity {

	@Override
	protected void onCreate(Bundle state) {
		super.onCreate(state);

		setContentView(R.layout.activity_portrait);

		PortraitScreenlet portraitScreenlet = (PortraitScreenlet)
			findViewById(R.id.portrait);

		PortraitInteractor portraitInteractor =
			new PortraitInteractorImpl(portraitScreenlet);

		portraitScreenlet.setInteractor(portraitInteractor);
		portraitInteractor.load(12935, "6b00f857-8560-4dcc-b7c1-61de666e8583");
	}
}
