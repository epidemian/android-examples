package com.example.activity;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class SongListActivity extends ListActivity {

	private static final String ARG_ARTIST = "artist";
	private static final String TAG = SongListActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.i(TAG, "SongListActivity created!");

		String[] artists = { "Baby", "Friday" };
		ListAdapter adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, artists);
		setListAdapter(adapter);
	}

	public static Intent createIntent(Context context, String artist) {
		Intent intent = new Intent(context, SongListActivity.class);
		intent.putExtra(ARG_ARTIST, artist);
		return intent;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		finish();
	}
}
