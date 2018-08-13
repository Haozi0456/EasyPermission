package com.zwh.easy.permissions.demo;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zwh.easy.permissions.EasyPermission;
import com.zwh.easy.permissions.PermissionCallback;
import com.zwh.easy.permissions.PermissionItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        initPermission();
    }

    private void showToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }


    private void initPermission() {
        List<PermissionItem> permissionItems = new ArrayList<>();
        permissionItems.add(new PermissionItem(Manifest.permission.CAMERA, "照相机", R.drawable.permission_ic_camera));
        permissionItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "写入存储", R.drawable.permission_ic_storage));
        permissionItems.add(new PermissionItem(Manifest.permission.RECORD_AUDIO, "录音", R.drawable.permission_ic_micro_phone));
        permissionItems.add(new PermissionItem(Manifest.permission.BODY_SENSORS, "传感器", R.drawable.permission_ic_sensors));
        permissionItems.add(new PermissionItem(Manifest.permission.ACCESS_FINE_LOCATION, "定位", R.drawable.permission_ic_location));
        EasyPermission.create(MainActivity.this)
//                .title("Dear God")
                .permissions(permissionItems)
                .filterColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, getTheme()))//permission icon color
//                .msg("To protect the peace of the world, open these permissions! You and I together save the world!")
                .style(R.style.PermissionBlueStyle)
                .checkMutiPermission(new PermissionCallback() {
                    @Override
                    public void onClose() {

                    }

                    @Override
                    public void onFinish() {
                        // showToast("所有权限申请完成");
//                copyFileToSDCard();
//                getConfigFromServer();
                    }

                    @Override
                    public void onDeny(String permission, int position) {
                    }

                    @Override
                    public void onGuarantee(String permission, int position) {
                    }
                });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                //default is normal style
                EasyPermission.create(MainActivity.this)
                        .animStyle(R.style.PermissionAnimFade)
                        .checkMutiPermission(new PermissionCallback() {
                            @Override
                            public void onClose() {
                                Log.i(TAG, "onClose");
                                showToast(getString(R.string.permission_on_close));
                            }

                            @Override
                            public void onFinish() {
                                showToast(getString(R.string.permission_completed));
                            }

                            @Override
                            public void onDeny(String permission, int position) {
                                Log.i(TAG, "onDeny");
                            }

                            @Override
                            public void onGuarantee(String permission, int position) {
                                Log.i(TAG, "onGuarantee");
                            }
                        });
                break;
            case R.id.btn2:
                //After you have set the theme, you must called filterColor () to set the color of the icon
                // ,otherwise the default is black
                List<PermissionItem> permissionItems = new ArrayList<PermissionItem>();
                permissionItems.add(new PermissionItem(Manifest.permission.READ_PHONE_STATE, "手机状态", R.drawable.permission_ic_phone));
                EasyPermission.create(MainActivity.this)
                        .title(getString(R.string.permission_cus_title))
                        .permissions(permissionItems)
                        .msg(getString(R.string.permission_cus_msg))
                        .animStyle(R.style.PermissionAnimScale)
                        .style(R.style.PermissionDefaultBlueStyle)
                        .checkMutiPermission(new PermissionCallback() {
                            @Override
                            public void onClose() {
                                Log.i(TAG, "onClose");
                                showToast(getString(R.string.permission_on_close));
                            }

                            @Override
                            public void onFinish() {
                                showToast(getString(R.string.permission_completed));
                            }

                            @Override
                            public void onDeny(String permission, int position) {
                                Log.i(TAG, "onDeny");
                            }

                            @Override
                            public void onGuarantee(String permission, int position) {
                                Log.i(TAG, "onGuarantee");
                            }
                        });
                break;
            case R.id.btn3:
                List<PermissionItem> permissions = new ArrayList<PermissionItem>();
                permissions.add(new PermissionItem(Manifest.permission.CALL_PHONE, getString(R.string.permission_cus_item_phone), R.drawable.permission_ic_phone));
                EasyPermission.create(MainActivity.this)
                        .title(getString(R.string.permission_cus_title))
                        .permissions(permissions)
                        .msg(getString(R.string.permission_cus_msg))
                        .animStyle(R.style.PermissionAnimModal)
                        .style(R.style.PermissionDefaultGreenStyle)
//                        .style(R.style.CusStyle)
                        .checkMutiPermission(new PermissionCallback() {
                            @Override
                            public void onClose() {
                                Log.i(TAG, "onClose");
                                showToast(getString(R.string.permission_on_close));
                            }

                            @Override
                            public void onFinish() {
                                showToast(getString(R.string.permission_completed));
                            }

                            @Override
                            public void onDeny(String permission, int position) {
                                Log.i(TAG, "onDeny");
                            }

                            @Override
                            public void onGuarantee(String permission, int position) {
                                Log.i(TAG, "onGuarantee");
                            }
                        });
                break;
            case R.id.btn4:
                //request single permission only called onDeny or onGuarantee
                EasyPermission.create(MainActivity.this).checkSinglePermission(Manifest.permission.CAMERA, new PermissionCallback() {
                    @Override
                    public void onClose() {

                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onDeny(String permission, int position) {
                        showToast("onDeny");
                    }

                    @Override
                    public void onGuarantee(String permission, int position) {
                        showToast("onGuarantee");
                    }
                });
                break;
        }
    }
}
