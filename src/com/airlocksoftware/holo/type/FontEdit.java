package com.airlocksoftware.holo.type;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import com.airlocksoftware.holo.R;
import com.airlocksoftware.holo.utils.Utils;

/** An Button that allows you to use different fonts & apply an app-wide scaling factor. **/
public class FontEdit extends EditText {

	Context mContext;
	
	private boolean mTextScalingEnabled;
	private float mTextScalingFactor;

	public FontEdit(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		getAttributes(attrs);
	}

	public FontEdit(Context context, AttributeSet attrs, int style) {
		super(context, attrs, style);
		mContext = context;
		getAttributes(attrs);
	}

	private void getAttributes(AttributeSet attrs) {
		TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.FontText);

		// set font
		int font = a.getInt(R.styleable.FontText_font, 0);
		setFont(font);

		// text scaling
		mTextScalingEnabled = a.getBoolean(R.styleable.FontText_textScalingEnabled, true);
		if (mTextScalingEnabled) {
			mTextScalingFactor = FontFactory.getTextScaleFactor(mContext);
			refreshTextSize();
		}
		
		this.setClickable(true);
		this.setFocusable(true);

		a.recycle();
	}

	public void setFont(int font) {
		setTypeface(FontFactory.getTypeface(mContext, font));
	}

	@Override
	public void setTypeface(Typeface tf) {
		super.setTypeface(tf);
	}

	@Override
	public void setTextSize(float size) {
		if (mTextScalingEnabled) size *= mTextScalingFactor;
		super.setTextSize(size);
	}

	private void refreshTextSize() {
		float textSize = getTextSize();
		float converted = Utils.pixelsToSp(mContext, textSize);
		setTextSize(converted);
	}

	public String getString() {
		return getText().toString();
	}
	
}
