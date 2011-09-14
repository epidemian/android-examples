package com.example.activity;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

public class MusicActivity extends NestableActivityGroup {

	@Override
	protected Intent createInitialActivityIntent() {
		return new Intent(this, ArtistListActivity.class);
	}

}
