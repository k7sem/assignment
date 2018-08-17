package com.k7.android.moment;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.k7.android.moment.net.RequestCallback;
import com.k7.android.moment.net.RequestData;
import com.k7.android.moment.obj.Url;
import com.k7.android.moment.obj.User;
import com.k7.android.moment.obj.UserManager;

import java.util.Iterator;

/**
 *
 * @author Kevin
 * @since 08/17/2018
 *
 *
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String ip;

    public static final String LOG_FLAG = "Moment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btn_next);

        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        // Toast.makeText(MainActivity.this, et.getText().toString(), Toast.LENGTH_SHORT).show();


        final PopupWindow popupWindow = new PopupWindow();
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        View view = LayoutInflater.from(this).inflate(R.layout.popup,null);
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER,0,0);

        final EditText et = (EditText) findViewById(R.id.ip_input);
                RequestData.getInstance().setRequestCallback(new RequestCallback() {
                    @Override
                    public void backUI() {
                        /*for (User user : UserManager.getInstance().getUsers()) {
                            Log.d(MainActivity.LOG_FLAG, user.getUsername());
                            for (Url url : UserManager.getInstance().getUrls(user.getId())) {
                                Log.d(MainActivity.LOG_FLAG, "-" + url.getImgurl());
                            }
                        }*/

                        popupWindow.dismiss();


                        Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("IP",et.getText().toString());
                        intent.putExtras(bundle);
                        startActivity(intent);

                    }
                });

                RequestData.getInstance().reqData(et.getText().toString());




    }



}
