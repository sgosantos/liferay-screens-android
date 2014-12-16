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
import android.content.res.Resources;
import android.content.res.TypedArray;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;

import android.util.AttributeSet;

import android.view.AbsSavedState;

import android.widget.ImageView;

import com.liferay.mobile.screens.library.R;

/**
 * @author Silvio Santos
 */
public class PortraitScreenletView extends ImageView {

	public PortraitScreenletView(Context context) {
		this(context, null);
	}

	public PortraitScreenletView(Context context, AttributeSet attributes) {
		this(context, attributes, 0);
	}

	public PortraitScreenletView(
		Context context, AttributeSet attributes, int defaultStyle) {

		super(context, attributes, defaultStyle);

		TypedArray typed = context.getTheme().obtainStyledAttributes(
			attributes, R.styleable.PortraitScreenlet, 0, 0);

		int backgroundColor = typed.getColor(
			R.styleable.PortraitScreenlet_backgroundColor, Color.LTGRAY);

		typed.recycle();

		setBackgroundColor(backgroundColor);
	}

	public void setBackgroundColor(int color) {
		if (isInEditMode()) {
			return;
		}

		Drawable drawable = getBackgroundDrawable(color);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			setBackground(drawable);
		}
		else {
			setBackgroundDrawable(drawable);
		}
	}

	@Override
	public void setImageBitmap(Bitmap bitmap) {
		StateListDrawable portrait = getPortraitDrawable(bitmap);

		setImageDrawable(portrait);
	}

	protected StateListDrawable getBackgroundDrawable(int color) {
		ShapeDrawable background = getShapeDrawable(color, 255);
		ShapeDrawable backgroundPressed = getShapeDrawable(color, 128);

		return getDrawableStateList(background, backgroundPressed);
	}

	protected StateListDrawable getDrawableStateList(
		Drawable drawable, Drawable drawablePressed) {

		int pressedStateId = android.R.attr.state_pressed;

		StateListDrawable drawableList = new StateListDrawable();
		drawableList.addState(new int[]{-pressedStateId}, drawable);

		if (isClickable() || isDuplicateParentStateEnabled()) {
			drawableList.addState(new int[]{pressedStateId}, drawablePressed);
		}

		return drawableList;
	}

	protected StateListDrawable getPortraitDrawable(Bitmap bitmap) {
		Resources resources = getResources();

		Drawable portrait = new RoundedDrawable(resources, bitmap, 255);
		Drawable portraitPressed = new RoundedDrawable(resources, bitmap, 128);

		return getDrawableStateList(portrait, portraitPressed);
	}

	protected ShapeDrawable getShapeDrawable(int color, int alpha) {
		ShapeDrawable background = new ShapeDrawable(new OvalShape());

		Paint paint = background.getPaint();
		paint.setColor(color);

		if (Color.alpha(color) == 255) {
			paint.setAlpha(alpha);
		}

		return background;
	}

	@Override
	protected void onRestoreInstanceState(Parcelable state) {
		super.onRestoreInstanceState(null);

		if (state instanceof AbsSavedState) {
			return;
		}

		Bitmap bitmap = ((Bundle)state).getParcelable(_PORTRAIT);
		setImageBitmap(bitmap);
	}

	@Override
	protected Parcelable onSaveInstanceState() {
		super.onSaveInstanceState();

		StateListDrawable drawableList = (StateListDrawable)getDrawable();

		if (drawableList == null) {
			return BaseSavedState.EMPTY_STATE;
		}

		Bundle state = new Bundle();

		Drawable drawable = drawableList.getCurrent();
		Bitmap bitmap = ((RoundedDrawable)drawable).getBitmap();
		state.putParcelable(_PORTRAIT, bitmap);

		return state;
	}

	private static final String _PORTRAIT = "portrait";

}