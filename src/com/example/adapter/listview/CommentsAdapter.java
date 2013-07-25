package com.example.adapter.listview;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adapter.listview.R;

public class CommentsAdapter extends ArrayAdapter<String> {

	private static final String TAG = "CommentsAdapter";
	private LayoutInflater mInflater;
	private List<String> items;
	private Context context;
	int screenWidth; 
	
	public CommentsAdapter(Context context, int resource, List<String> items) {
		super(context, resource, items);
		
		this.context = context;
								
		mInflater = LayoutInflater.from(context);
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		
		screenWidth = metrics.widthPixels;
		
		setItems(items);
	}

	public void clear()
	{
		this.items.clear();
	}
	
	public void setItems(List<String> items)
	{
		this.items = items;
	}
	
	@Override
	public int getCount() {
	        return items.size();
	}
	
	@Override
	public String getItem(int position) {
	        return items.get(position);
	}
	
	@Override
	public long getItemId(int position) {
	        return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if(convertView == null)
		{
			convertView = mInflater.inflate(R.layout.listitem_comments, null);
			
			holder = new ViewHolder();
			holder.imgCommentProfile = (ImageView) convertView.findViewById(R.id.imgCommentProfile);
			holder.imgCommentProfile.setImageResource(R.drawable.ic_stub);
			
			holder.txtUserName = (TextView) convertView.findViewById(R.id.txtUserName);
			holder.txtComment = (TextView) convertView.findViewById(R.id.txtComment);
								
			convertView.setTag(holder);
			
		}
		else
		{
			holder = (ViewHolder)convertView.getTag();
		}
		
		String item = getItem(position);
		
		//FIXME: Buscar una mejor alternativa
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();		
		int width = metrics.widthPixels;		
		if (width != screenWidth && width != 0 ) {
			screenWidth = width;			
		}
				
		holder.txtUserName.setText("Usuario");
		holder.txtComment.setText(item.toString());
		holder.txtComment.setWidth(screenWidth - 20);
												
		return(convertView);
	}
	
	public static class ViewHolder {
		ImageView imgCommentProfile;
		TextView txtUserName;
		TextView txtComment;
	}

}