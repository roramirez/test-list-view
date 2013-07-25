package com.example.adapter.listview;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;


import com.example.adapter.listview.Utils;



public class CommentsListFragment extends SherlockFragment {

	private String TAG = "CommentsListFragment";
	private CommentsAdapter mAdapter;
	private ListView mCommentsList;

	private List<String> items = new Vector<String>(0);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setHasOptionsMenu(true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onPause() {
		super.onPause();
		updateRefreshStatus(false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_comments_list, container, false);

		return view;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setRetainInstance(true);

		mCommentsList = (ListView) getView().findViewById(R.id.lstComments);

		updateAdapter();
		

	}

	/**
	 * Update luqea adapter
	 */
	public void updateAdapter() {
		Log.d(TAG, "updateAdapter()");
		if (mAdapter == null)
		{
			mAdapter = new CommentsAdapter(getActivity().getApplicationContext(), R.layout.listitem_comments, items);
			mCommentsList.setAdapter(mAdapter);
			updateComments();
		}

		mAdapter.setItems(items);
		mAdapter.notifyDataSetChanged();
		
		mCommentsList.postDelayed(new Runnable() {
			public void run() {
				Utils.setListViewHeightBasedOnChildren2(mCommentsList);
			}
		}, 400);

		

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		//inflater.inflate(R.menu.menu_luquea_timeline, menu);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		return super.onOptionsItemSelected(item);

	}

	@Override
    public void onResume() {
        super.onResume();        
        mCommentsList.setAdapter(mAdapter);               
    }
	
	public void updateComments() { 
		
		//items = new Gson().fromJson(response, collectionType);
		items.add("Hola");
		items.add("Hola este es un comentario largo que deberia estar en mas de dos lineas. Debería verse algo solamente");
		updateAdapter();
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		setUserVisibleHint(true);
	}

	/**
	 * Update ProgressBar loader
	 * 
	 * @param status
	 */
	public void updateRefreshStatus(boolean status) {
		try {
			getSherlockActivity().setSupportProgressBarIndeterminateVisibility(status);
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	public List<String> getItems() {
		return items;
	}

	public void addComment(String comment) {
		items.add(comment);
		updateAdapter();
	}
}
