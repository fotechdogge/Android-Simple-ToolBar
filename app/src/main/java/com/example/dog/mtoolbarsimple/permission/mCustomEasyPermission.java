package com.example.dog.mtoolbarsimple.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.example.dog.mtoolbarsimple.Utils;

import java.util.List;

public class mCustomEasyPermission {

    private static final String TAG = "mCustomEasyPermission";
    private static final String SNACKBAR_ACTION_TEXT = "Set";

    public interface PermissionCallbacks extends ActivityCompat.OnRequestPermissionsResultCallback {

        void onPermissionsGranted(int requestCode, List<String> perms);

        void onPermissionsDenied(int requestCode, List<String> perms);
    }

    /**
     * 檢查一項或多項權限.
     *
     * @param context calling context.
     * @param perms   權限字串陣列.
     * @return 檢查結果.
     */
    public static boolean hasPermissions(Context context, String... perms) {

        // 若版本小於 Api 23 則返回 true
        if (!Utils.isSdkVersionHigherM()) {
            return true;
        }

        // 當中一項權限未授權，其返回 false
        for (String perm : perms) {
            if ((ContextCompat.checkSelfPermission(context, perm)
                    == PackageManager.PERMISSION_GRANTED)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 申請一個或多個 permission，以及判定當中權限是否需要顯示需要該權限的理由 rationale.
     *
     * @param object      請求中之 Activity 或 Fragment.
     * @param rationale   一段文字訊息，其解釋了為何本軟體需要此權限之理由.
     * @param requestCode 當次請求之 識別碼.
     * @param perms       權限陣列.
     */
    public static void requestPermissions(final Object object, String rationale, final int requestCode, final String[] perms) {

        // 確認該物件是否為 Activity or Fragment ，否則跳出 Exception.
        checkCallingObjectSuitability(object);

        boolean shouldShowRationale = false;
        for (String perm : perms) {
            shouldShowRationale = shouldShowRationale ||
                    shouldShowRequestPermissionRationale(object, perm);
        }

        // 是否需要彈出解釋窗口
        if (shouldShowRationale) {
            Activity activity = getActivity(object);
            if (activity == null)
                return;
            Snackbar.make(activity.findViewById(android.R.id.content), rationale, Snackbar.LENGTH_LONG)
                    .setAction(SNACKBAR_ACTION_TEXT, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            exceuptPermissionRequest(object, requestCode, perms);
                        }
                    })
                    .show();
        } else {
            exceuptPermissionRequest(object, requestCode, perms);
        }
    }

    /**
     * 依對象類型的不同，使用其方法來判斷該權限是否需要顯示解釋窗口.
     *
     * @param obj  受識別之 object.
     * @param perm 受判斷之 permission.
     * @return
     */
    @TargetApi(23)
    private static boolean shouldShowRequestPermissionRationale(Object obj, String perm) {
        if (obj instanceof Activity)
            return ActivityCompat.shouldShowRequestPermissionRationale((Activity) obj, perm);
        else if (obj instanceof Fragment)
            return ((Fragment) obj).shouldShowRequestPermissionRationale(perm);
        else if (obj instanceof android.support.v4.app.Fragment)
            return ((android.support.v4.app.Fragment) obj).shouldShowRequestPermissionRationale(perm);

        return false;

    }

    /**
     * 根據 Activity object 類型來執行權限申請.
     *
     * @param object      受識別之 object.
     * @param requestCode 申請識別碼.
     * @param perms       權限陣列.
     */
    @TargetApi(23)
    private static void exceuptPermissionRequest(Object object, int requestCode, String[] perms) {
        checkCallingObjectSuitability(object);
        if (object instanceof Activity)
            ActivityCompat.requestPermissions((Activity) object, perms, requestCode);
        else if (object instanceof Fragment)
            ((Fragment) object).requestPermissions(perms, requestCode);
        else if (object instanceof android.support.v4.app.Fragment)
            ((android.support.v4.app.Fragment) object).requestPermissions(perms, requestCode);
    }

    /**
     * 取得該 Object 之 Activity.
     *
     * @param object 受識別之 object
     * @return 若無，返回 null.
     */
    private static Activity getActivity(Object object) {
        if (object instanceof Activity)
            return (Activity) object;
        else if (object instanceof Fragment)
            return ((Fragment) object).getActivity();
        else if (object instanceof android.support.v4.app.Fragment)
            return ((android.support.v4.app.Fragment) object).getActivity();

        return null;
    }

    private static android.support.v4.app.FragmentManager getSupportFragmentManager(Object object) {
        if (object instanceof android.support.v4.app.FragmentActivity)
            return ((FragmentActivity) object).getSupportFragmentManager();
        else if (object instanceof android.support.v4.app.Fragment)
            return ((android.support.v4.app.Fragment) object).getFragmentManager();

        return null;
    }

    private static FragmentManager getFragmentManager(Object object) {
        if (object instanceof FragmentActivity)
            return ((FragmentActivity) object).getFragmentManager();
        else if (object instanceof Fragment)
            return ((Fragment) object).getFragmentManager();

        return null;
    }

    /**
     * 檢查 Object 是否為 Activity 或 Fragment，若都不是，則包出異常 Exception.
     *
     * @param object 受識別之 object.
     */

    private static void checkCallingObjectSuitability(Object object) {
        // Make sure Object is an Activity or Fragment
        boolean isActivity = object instanceof Activity;
        boolean isAppFragment = object instanceof android.app.Fragment;
        boolean isSupportFragment = object instanceof android.support.v4.app.Fragment;
        boolean isMinSdkM = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;

        if (!(isSupportFragment || isActivity || (isAppFragment && isMinSdkM))) {
            if (isAppFragment) {
                throw new IllegalArgumentException(
                        "Target SDK needs to be greater than 23 if caller is android.app.Fragment");
            } else {
                throw new IllegalArgumentException("Caller must be an Activity or a Fragment.");
            }
        }
    }
}
