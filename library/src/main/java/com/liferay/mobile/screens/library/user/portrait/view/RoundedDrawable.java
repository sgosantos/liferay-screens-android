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

import android.content.res.Resources;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;

/**
 * @author Silvio Santos
 */
public class RoundedDrawable extends BitmapDrawable {

	public RoundedDrawable(Resources resources, Bitmap bitmap, int alpha) {
		super(resources, bitmap);
		_alpha = alpha;
	}

	public void draw(Canvas canvas) {
		Bitmap bitmap = getBitmap();

		if (bitmap == null) {
			return;
		}

		BitmapShader shader = new BitmapShader(
			bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setAlpha(_alpha);
		paint.setShader(shader);

		float width = getIntrinsicWidth();
		float height = getIntrinsicHeight();
		float x = width / 2;
		float y = height / 2;
		float radius = width / 2;

		canvas.drawCircle(x, y, radius, paint);
	}

	private int _alpha;

}