package com.example.activity;

import com.example.R;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TabHost.TabSpec;

public class MainTabActivity extends TabActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_activity);

		// Creates tabs.
		addTabSpec("music", R.string.tab_music,
				android.R.drawable.ic_menu_search, MusicActivity.class);
		addTabSpec("starred", R.string.tab_starred,
				android.R.drawable.ic_menu_more, MusicActivity.class);
		addTabSpec("more", R.string.tab_more, android.R.drawable.ic_menu_more,
				MusicActivity.class);

	}

	/**
	 * Creates and add a new tab to the TabActivity.
	 * 
	 * @param tag The tag of the tab.
	 * @param labelId The resource id of the label string for the tab.
	 * @param iconId The resource if of the Drawable for the tab icon.
	 * @param activityClass The class of the activity the tab should launch.
	 */
	private void addTabSpec(String tag, int labelId, int iconId,
			Class<? extends Activity> activityClass) {
		Drawable icon = getResources().getDrawable(iconId);
		Intent intent = new Intent().setClass(this, activityClass);
		TabHost tabHost = getTabHost();
		TabSpec tabSpec = tabHost //
				.newTabSpec(tag) //
				.setIndicator(getText(labelId), icon) //
				.setContent(intent);
		tabHost.addTab(tabSpec);

	}
}
