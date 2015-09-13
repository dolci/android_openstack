package com.stage.openstackmonit.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.stage.openstackmonit.data.User;

public class UserAdapter extends ArrayAdapter<User> {

	
	private static LayoutInflater inflater=null;
	Context context;
	
	List<User> data;
	public UserAdapter ( Context ctx, List<User> objects) {
		super( ctx, com.stage.openstackmonit.R.layout.single_row, objects );
		
		inflater = LayoutInflater.from( ctx );
		data=objects;
		context=ctx;
	}
	
	public int getCount() {

	    if(data.size()<=0)
	        return 1;
	    return data.size();
	}

	 class MyViewHolder
	    {
		    TextView title;
	        TextView status;
	        ImageButton delete;
	       
	        MyViewHolder(View v)
	        {
	            title= (TextView) v.findViewById(com.stage.openstackmonit.R.id.textView1);
	            status= (TextView) v.findViewById(com.stage.openstackmonit.R.id.textView2);
	            delete= (ImageButton) v.findViewById(com.stage.openstackmonit.R.id.imageButton1);
	            
	        }
	    }
	    @Override
	    public View getView(final int position, View convertView, ViewGroup parent) {

	        View row=convertView;
	        MyViewHolder holder=null;
	        if(row==null)
	        {
	            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            row = inflater.inflate(com.stage.openstackmonit.R.layout.single_row, parent, false);
	            holder=new MyViewHolder(row);
	            row.setTag(holder);
	           
	            Log.d("user","Creating a new row");
	        }
	        else
	        {
	            holder= (MyViewHolder) row.getTag();
	           
	        }
            
	        if(data.get(position).getEnabled()==true)
	             holder.status.setText("Active");
	        else
	         holder.status.setText("Non Active");
	         holder.title.setText(data.get(position).getUsername());
	        
          
	      //  holder.myDescription.setText(descriptionArray[position]);
	      // row.setOnClickListener(new OnItemClickListener(position));
	     holder.delete.setOnClickListener(new OnClickListener() {

	           @Override
	           public void onClick(View view) {
	        	   data.remove(position);
	        	   notifyDataSetChanged();
	               Toast.makeText(context, "Deleted User", 10).show();
	            
	           }
	       });
	        return row;
	    }

}
