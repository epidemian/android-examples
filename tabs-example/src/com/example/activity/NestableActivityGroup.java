package com.example.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;

public abstract class NestableActivityGroup extends ActivityGroup {

	private static final String TAG = NestableActivityGroup.class
			.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = createInitialActivityIntent();
		startChildActivity(intent);

	}

	protected abstract Intent createInitialActivityIntent();

	@Override
	public void startActivityFromChild(Activity child, Intent intent,
			int requestCode) {
		// super.startActivityFromChild(child, intent, requestCode);

		Log.i(TAG, "Activity " + child + " called startActivity with intent "
				+ intent);
		startChildActivity(intent);
	}

	// This is being called.
	private void startChildActivity(Intent intent) {
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		String id = intent.getComponent().getClassName();
		Window window = getLocalActivityManager().startActivity(id, intent);
		setContentView(window.getDecorView());
	}

	@Override
	// This is NOT being called.
	public void finishActivityFromChild(Activity child, int requestCode) {
		super.finishActivityFromChild(child, requestCode);
	}

	@Override
	public void onBackPressed() {
		LocalActivityManager manager = getLocalActivityManager();
		String currentId = manager.getCurrentId();
		if (currentId != null) {
			manager.destroyActivity(currentId, true);
		}
	}

	/**
	 * The primary purpose is to prevent systems before
	 * android.os.Build.VERSION_CODES.ECLAIR from calling their default
	 * KeyEvent.KEYCODE_BACK during onKeyDown.
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// preventing default implementation previous to
			// android.os.Build.VERSION_CODES.ECLAIR
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * Overrides the default implementation for KeyEvent.KEYCODE_BACK so that
	 * all systems call onBackPressed().
	 */
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			onBackPressed();
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}
}
