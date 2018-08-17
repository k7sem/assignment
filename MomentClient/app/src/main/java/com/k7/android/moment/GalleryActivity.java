package com.k7.android.moment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.k7.android.moment.obj.Url;
import com.k7.android.moment.obj.User;
import com.k7.android.moment.obj.UserManager;
import com.tikeyc.tnineplacegridview_android.libs.CircleImageView;
import com.tikeyc.tnineplacegridviewlibrary.TNinePlaceGridView.TNinePlaceGridView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 *
 * @author Kevin
 * @since 08/17/2018
 *
 *
 */

public class GalleryActivity extends AppCompatActivity {

    @ViewInject(R.id.listView)
    private ListView listView;

    private static String ip_addr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Bundle bundle = getIntent().getExtras();

            ip_addr = bundle.getString("IP");

        } catch (Exception e) {
            // e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        setContentView(R.layout.activity_gallery);

        x.view().inject(this);

        init();

    }

    private void init() {

        ListViewAdapter listViewAdapter = new ListViewAdapter(this);

        listViewAdapter.nickNames = new ArrayList<String>();

        List<User> users = UserManager.getInstance().getUsers();


        List<List<Object>> imageNames2D = new ArrayList<List<Object>>();

        for (int i = 0; i < users.size(); i++) {

           User user = users.get(i);

            listViewAdapter.nickNames.add(user.getUsername());

            List<Url> urls = UserManager.getInstance().getUrls(user.getId());

            ArrayList<Object> imageNames = new ArrayList<Object>();

            // TODO
            // if urls.size > 8 what happens?
            for (int j = 0; j < urls.size(); j++) {

                imageNames.add(ip_addr + urls.get(j).getImgurl());
               // Log.d(MainActivity.LOG_FLAG, ip_addr + urls.get(j).getImgurl())
            }
            imageNames2D.add(imageNames);
        }

        listViewAdapter.imageNames2D = imageNames2D;
        listView.setAdapter(listViewAdapter);
    }

    private class ListViewAdapter extends BaseAdapter {

        private Context context;
        public List<List<Object>> imageNames2D;
        public List<String>  nickNames;

        public ListViewAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            if (imageNames2D != null) return imageNames2D.size();
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        private class ViewHelper {
            CircleImageView imageViewIcon;
            TextView textViewNickName;
            TNinePlaceGridView ninePlaceGridView;

        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            ViewHelper viewHelper;
            if (view == null) {
                view = View.inflate(context,R.layout.timage_listactivity_listview_item,null);
                viewHelper = new ViewHelper();
                viewHelper.imageViewIcon = (CircleImageView) view.findViewById(R.id.imageViewIcon);
                viewHelper.textViewNickName = (TextView) view.findViewById(R.id.textViewNickName);
                viewHelper.textViewNickName.setText(nickNames.get(i));
                viewHelper.ninePlaceGridView = (TNinePlaceGridView) view.findViewById(R.id.ninePlaceGridView);

                view.setTag(viewHelper);
            } else  {
                viewHelper = (ViewHelper) view.getTag();
            }
            List<Object> imageNames = this.imageNames2D.get(i);
            viewHelper.ninePlaceGridView.setImageNames(imageNames);


            return view;
        }
    }

}
