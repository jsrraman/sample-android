package com.rajaraman.sample.utils;

import android.app.Activity;
import android.app.ProgressDialog;

public class ProgressBarHelper {
    private ProgressDialog pd = null;
    private boolean inProgress = false;

    public void showIndeterminateProgressDialog(Activity activity, String msg) {
        // If the activity is being closed do not show progress dialog
        if (activity.isFinishing()) {
            return;
        }

        if (!inProgress) {
            pd = new ProgressDialog(activity);

            pd.setMessage(msg);
            pd.setCancelable(false);
            pd.setIndeterminate(true);
//            pd.setIndeterminateDrawable(ContextCompat.getDrawable(activity,
//                    R.drawable.progress_bar_spinner));
            pd.show();

            inProgress = true;
        }
    }

    public void showDeterminateProgressDialog(Activity activity, String msg) {
        // If the activity is being closed do not show progress dialog
        if (activity.isFinishing()) {
            return;
        }

        if (!inProgress) {
            pd = new ProgressDialog(activity);

            pd.setMessage(msg);
            pd.setCancelable(false);
            pd.setIndeterminate(false);
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd.show();

            inProgress = true;
        }
    }

    public void dismissProgressDialog() {
        if (inProgress) {
            pd.dismiss();
            inProgress = false;
        }
    }

    public void setProgress(int progressValue) {
        pd.setProgress(progressValue);
    }
}