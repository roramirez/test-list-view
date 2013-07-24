package com.example.adapter.listview;

import com.example.adapter.listview.R;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.ShareActionProvider;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.SupportMapFragment;



public class LuqeaShowActivity extends SherlockFragmentActivity { 

	protected static final String TAG = "LuqeaShowActivity";

	private LuqeaShowActivity mActivity;

	private GoogleMap mMap;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_luqea_show);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);				

		mActivity = this;

		SupportMapFragment mapFragment =
				(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.show_map);
		mapFragment.getView().setVisibility(View.GONE);


		

		if (savedInstanceState == null) {
			// First incarnation of this activity.
			mapFragment.setRetainInstance(true);
		} else {
			// Reincarnated activity. The obtained map is the same map instance in the previous
			// activity life cycle. There is no need to reinitialize it.
			mMap = mapFragment.getMap();
		}

		updateRefreshStatus(false);


		setTitle("TEST");



	}

	@Override
	protected void onResume() {
		super.onResume();
	} 
	
	public void addComment(String comment) {
		CommentsListFragment fragmentComments = (CommentsListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentListComments);
		fragmentComments.addComment(comment);
	}

	/**
	 * Update ProgressBar loader
	 * 
	 * @param status
	 */
	public void updateRefreshStatus(boolean status) {
		try {
			setSupportProgressBarIndeterminateVisibility(status);
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}
	
}
