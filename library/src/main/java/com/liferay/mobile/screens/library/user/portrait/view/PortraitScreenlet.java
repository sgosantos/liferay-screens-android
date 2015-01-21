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

package com.liferay.mobile.screens.library.user.portrait.view;

import android.content.Context;
import android.content.res.TypedArray;

import android.util.AttributeSet;

import android.view.LayoutInflater;

import com.liferay.mobile.screens.library.R;
import com.liferay.mobile.screens.library.base.view.BaseScreenlet;
import com.liferay.mobile.screens.library.user.portrait.interactor.PortraitInteractor;

/**
 * @author Silvio Santos
 */
public class PortraitScreenlet
	extends BaseScreenlet<PortraitScreenletView, PortraitInteractor> {

	public PortraitScreenlet(Context context) {
		this(context, null);
	}

	public PortraitScreenlet(Context context, AttributeSet attributes) {
		this(context, attributes, 0);
	}

	public PortraitScreenlet(
		Context context, AttributeSet attributes, int defaultStyle) {

		super(context, attributes, defaultStyle);
	}

	public void load(Bitmap bitmap) {
		getScreenetView().setImageBitmap(bitmap);
	}

	@Override
	protected PortraitScreenletView createScreenletView(
		Context context, AttributeSet attributes) {

		TypedArray typedArray = context.getTheme().obtainStyledAttributes(
			attributes, R.styleable.PortraitScreenlet, 0, 0);

		int layoutId = typedArray.getResourceId(
			R.styleable.PortraitScreenlet_layoutId, R.layout.portrait_default);

		PortraitScreenletView view = (PortraitScreenletView)
			LayoutInflater.from(getContext()).inflate(layoutId, null);

		return view;
	}

	@Override
	protected void onUserAction(int id) {
	}

}