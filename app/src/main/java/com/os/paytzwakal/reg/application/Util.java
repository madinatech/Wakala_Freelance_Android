package com.os.paytzwakal.reg.application;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.style.Circle;
import com.os.paytzwakal.reg.R;
import com.os.paytzwakal.reg.activity.LoginActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class Util {

    public static final String URL_STORAGE_REFERENCE = "gs://the-agenciz-d377a.appspot.com";
    public static final String FOLDER_STORAGE_IMG = "images";
    private static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-zA-Z]).{6,20}$";
    private static final long[] vPattern = new long[]{1000, 1000, 1000, 1000, 1000};
    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;
    public static Dialog confirmation;
    static Bitmap bitmap;
    private static Pattern pattern;
    private static Matcher matcher;
    private static ProgressDialog progressDialog = null;
    private static Dialog addNewDialog;
    private static TextView btnCancel;
    private static String[] monthName = {"January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"};
    private static String[] monthLastName = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July",
            "Aug", "Sept", "Oct", "Nov", "Dec"};
//    private static LogoutResponse logoutData;

    public static String parseDateTodd_MM_yyyy(String time) {
        String inputPattern = "dd/MM/yyyy";
        String outputPattern = "yyyy-MM-dd";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String changeDateWallet(String time) {
        String inputPattern = "yyyy-MM-dd hh:mm:ss";
        String outputPattern = "dd MMM yyyy HH:mm a";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }


    public static void showToast(String msg, Context context) {
        try {
            Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String parseDateToddMMyyyy(String time) {
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "dd/MM/yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static boolean hasInternet(Context context) {
        try {
            ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnectedOrConnecting();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void noInternet(CoordinatorLayout coordinatorLayout, View.OnClickListener onClickListener, Context context) {
        try {
            Snackbar snackbar = Snackbar.make(coordinatorLayout, context.getResources().getString(R.string.no_internet), Snackbar.LENGTH_LONG).setAction(context.getResources().getString(R.string.retry), onClickListener);
            snackbar.setActionTextColor(context.getResources().getColor(R.color.red_500));
            View sbView = snackbar.getView();
            sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.grey_800));
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            snackbar.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showSnackBar(CoordinatorLayout coordinatorLayout, String msg, Context context) {
        try {
            Snackbar snackbar = Snackbar.make(coordinatorLayout, msg, Snackbar.LENGTH_LONG);
            View sbView = snackbar.getView();
            sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.grey_800));
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            // snackbar.setDuration(8000);
            snackbar.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isLocationEnabled(final Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return !(!gps_enabled && !network_enabled);
    }

    public static void showErrorSnackBar(ConstraintLayout constraintLayout, String msg, Context context) {
        try {
            Snackbar snackbar = Snackbar.make(constraintLayout, msg, Snackbar.LENGTH_LONG);
            View sbView = snackbar.getView();
            sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.red_400));
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.setDuration(5000);
            snackbar.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showSuccessSnackBar(CoordinatorLayout coordinatorLayout, String msg, Context context) {
        try {
            Snackbar snackbar = Snackbar.make(coordinatorLayout, msg, Snackbar.LENGTH_LONG);
            View sbView = snackbar.getView();
            sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.green_500));
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showSuccessSendMoneySnackBar(CoordinatorLayout coordinatorLayout, String msg, Context context) {
        try {
            Snackbar snackbar = Snackbar.make(coordinatorLayout, msg, Snackbar.LENGTH_LONG);
            View sbView = snackbar.getView();
            sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.green_500));
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.setDuration(5000);
            snackbar.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static boolean isPasswordValidate(String password) {
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static Dialog showProDialog(Context context) {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            progressDialog = new ProgressDialog(context, R.style.NewDialog);
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.trans_black_light)));
            progressDialog.setCancelable(false);
//            progressDialog.setMessage("");
            Circle doubleBounce = new Circle();
            progressDialog.setIndeterminateDrawable(doubleBounce);
            progressDialog.setProgressStyle(R.style.SpinKitView_FadingCircle);
            progressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return progressDialog;
    }

    public static Dialog dismissProDialog() {
        try {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
            progressDialog = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return progressDialog;
    }


    public static void hideKeyBoardMethod(final Context con, final View view) {
        try {
            view.post(new Runnable() {
                @Override
                public void run() {
                    InputMethodManager imm = (InputMethodManager) con.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Bitmap getBitmapfromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        }
    }


    public static String getPath(Uri uri, Activity activity) {

        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = activity.managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    /* if (b != null) {
                try {
                    String payload = b.getString("payload");
                    JSONObject payloadObj = null;
                    try {
                        payloadObj = new JSONObject(payload);
                        b.putString("message", notificationData.getData().get("message"));
                    }catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }*/

    public static String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {

            if ("content".equals(contentUri.getScheme())) {
                String[] proj = {MediaStore.Images.Media.DATA};
                cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                return cursor.getString(column_index);
            } else {
                return contentUri.getPath();
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
            e.printStackTrace();
        }
        return null;
    }

    public static String getRealPathFromURIinNought(Context context, Uri contentUri) {
        String path = null;
        String[] projection = {MediaStore.MediaColumns.DATA};
        ContentResolver cr = context.getApplicationContext().getContentResolver();
        Cursor metaCursor = cr.query(contentUri, projection, null, null, null);
        if (metaCursor != null) {
            try {
                if (metaCursor.moveToFirst()) {
                    path = metaCursor.getString(0);
                }
            } finally {
                metaCursor.close();
            }
        }
        return path;
    }

    public static Bitmap decodeSampledBitmapFromUri(Uri uri, int reqWidth, int reqHeight, Context context) {
        Bitmap bm = null;
        try {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options);
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
            options.inJustDecodeBounds = false;
            bm = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return bm;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }
        return inSampleSize;
    }

    public static Bitmap rotateImageBitmap(String photoPath, Bitmap bitmap) {
        Bitmap rotatedBitmap = null;
        try {
            ExifInterface ei = new ExifInterface(photoPath);
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_UNDEFINED);
            switch (orientation) {

                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotatedBitmap = rotateImage(bitmap, 90);
                    break;

                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotatedBitmap = rotateImage(bitmap, 180);
                    break;

                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotatedBitmap = rotateImage(bitmap, 270);
                    break;

                case ExifInterface.ORIENTATION_NORMAL:
                    return bitmap;

                case ExifInterface.ORIENTATION_UNDEFINED:
                    return bitmap;


                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rotatedBitmap;
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    /* public static void setBadgeAppIcon(Context context, int count) {
         try {
             ShortcutBadger.applyCount(context, count); //for 1.1.4+
         } catch (Exception e) {
             e.printStackTrace();
         }
     }

     public static void removeBadgeAppIcon(Context context, int count) {
         try {
             ShortcutBadger.applyCount(context, count);
             ShortcutBadger.removeCount(context);//for 1.1.4+
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
 */
    public static boolean checkGps(Activity context) {
        boolean gps_enabled = false;
        try {
            LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
            return gps_enabled;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gps_enabled;
    }

    public static FragmentTransaction addFragment(FragmentManager fm, Fragment frg, int resource, boolean backStack) {
        FragmentTransaction ft1 = fm.beginTransaction();
        ft1.replace(resource, frg);
        if (backStack) {
            ft1.addToBackStack(null);
        }
        ft1.commit();
        return ft1;
    }

    public static Bitmap imageRound(Bitmap mbitmap) {
        Bitmap imageRounded = Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
        Canvas canvas = new Canvas(imageRounded);
        Paint mpaint = new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 100, 100, mpaint);// Round Image Corner 100 100 100 100
        return imageRounded;
    }

    public static boolean hasNavBar(Context context) {
        Resources resources = context.getResources();
        int id = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        return id > 0 && resources.getBoolean(id);
    }

    public static int getNavBarHeight(Context context) {
        int navigationBarHeight = 0;
        Resources resources = context.getResources();
        int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            navigationBarHeight = resources.getDimensionPixelSize(resourceId);
        }
        return navigationBarHeight;
    }

    public static int getScreenWidth(Context context) {
        int columnWidth = 0;
        try {
            WindowManager wm = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();

            final Point point = new Point();
            try {
                display.getSize(point);
            } catch (NoSuchMethodError ignore) { // Older device
                point.x = display.getWidth();
                point.y = display.getHeight();
            }
            return point.x;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return columnWidth;
    }

    public static Bitmap decodeFile(String filePath, int WIDTH, int HIGHT) {
        try {

            File f = new File(filePath);

            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            final int REQUIRED_WIDTH = WIDTH;
            final int REQUIRED_HIGHT = HIGHT;
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_WIDTH
                    && o.outHeight / scale / 2 >= REQUIRED_HIGHT)
                scale *= 2;

            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int dpToPx(Context context, int dp) {
        return Math.round(dp * getPixelScaleFactor(context));
    }

    public static int pxToDp(Context context, int px) {
        return Math.round(px / getPixelScaleFactor(context));
    }

    private static float getPixelScaleFactor(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static String convertDateFormat(String upComingDate, String inFormat, String outFormat) {
        String formattedDate = "";
        try {
            if (!upComingDate.isEmpty()) {
                DateFormat inputFormat = new SimpleDateFormat(inFormat);
                DateFormat outputFormat = new SimpleDateFormat(outFormat);
                Date parsed = null;
                parsed = inputFormat.parse(upComingDate);
                formattedDate = outputFormat.format(parsed);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return formattedDate;
    }

    public static String setZeroBeforeNine(long digit) {
        try {
            if (digit <= 9) {
                return "0" + digit;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "" + digit;
    }

    public static String getlongtoago(long createdAt) {
        DateFormat userDateFormat = new SimpleDateFormat("EEEE MMM dd HH:mm:ss Z yyyy");
        DateFormat dateFormatNeeded = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
        Date date = null;
        // 2018-09-08 12:04:28
        date = new Date(createdAt);
        String crdate1 = dateFormatNeeded.format(date);

        // Date Calculation
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        crdate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

        // get current date time with Calendar()
        Calendar cal = Calendar.getInstance();
        String currenttime = dateFormat.format(cal.getTime());

        Date CreatedAt = null;
        Date current = null;
        try {
            CreatedAt = dateFormat.parse(crdate1);
            current = dateFormat.parse(currenttime);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Get msec from each, and subtract.
        long diff = current.getTime() - CreatedAt.getTime();
        long diffSeconds = diff / 1000;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);

        String time = null;
        if (diffDays > 0) {
            if (diffDays > 0 && diffDays <= 7) {
                SimpleDateFormat formatte = new SimpleDateFormat("EEEE");
                String dateStrin = formatte.format(new Date(createdAt));
                time = dateStrin;
            } else {
                time = changeDateWallet(crdate1);
            }
        } else {
            if (diffHours > 0) {
                if (diffHours == 1) {
                    time = diffHours + " hr ago";
                } else {
                    time = diffHours + " hrs ago";
                }
            } else {
                if (diffMinutes > 0) {
                    if (diffMinutes == 1) {
                        time = diffMinutes + " min ago";
                    } else {
                        time = diffMinutes + " mins ago";
                    }
                } else {
                    if (diffSeconds > 0) {
                        time = diffSeconds + " secs ago";
                    }
                }

            }

        }
        return time;
    }

    public static String getTimeAgo(long time, long serverTime, Context ctx) {
        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000;
        }
        long now = serverTime;
        if (time > now || time <= 0) {
            if (time > now) {
                return "just now";
            }
            return null;
        }
        // TODO: localize
        final long diff = now - time;
        if (diff < MINUTE_MILLIS) {
            return "just now";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "a minute ago";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " minutes ago";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "an hour ago";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + " hours ago";
        } else if (diff < 48 * HOUR_MILLIS) {
            return "yesterday";
        } else {
            return diff / DAY_MILLIS + " days ago";
        }
    }

    public static long convertDateTimeToMilliSeconds(String dateTime, String comingFormat) throws ParseException {
        long timeInSec = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(comingFormat);
            sdf.setTimeZone(TimeZone.getDefault());
            timeInSec = (sdf.parse(dateTime).getTime());
            return timeInSec;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int pxToDp(int px, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static String dataEncode(String textDecode) {
        String base64 = "";
        try {
            byte[] data = textDecode.getBytes("UTF-8");
            base64 = Base64.encodeToString(data, Base64.DEFAULT);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return base64;
    }

    public static String dataDecode(String textEncoded) {
        String text = "";
        try {
            byte[] data = Base64.decode(textEncoded, Base64.DEFAULT);
            text = new String(data, "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return text;
    }

    public static void showErrorSnackBar1(CoordinatorLayout coordinatorLayout, String msg, Context context) {
        try {
            Snackbar snackbar = Snackbar.make(coordinatorLayout, msg, Snackbar.LENGTH_LONG);
            View sbView = snackbar.getView();
            sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.red_400));
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double getDateDiff(String dateFormat, String date1, String date2) {
        double diffInDays = 0.0;
        try {
            SimpleDateFormat dfDate = new SimpleDateFormat(dateFormat);
            Date d = null;
            Date d1 = null;
            try {
                d = dfDate.parse(date1);
                d1 = dfDate.parse(date2);//Returns 15/10/2012
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar startCalendar = new GregorianCalendar();
            startCalendar.setTime(d);
            Calendar endCalendar = new GregorianCalendar();
            endCalendar.setTime(d1);
            int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
            int diffMonth = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
            if (diffMonth < 0) {
                diffYear = diffYear - 1;
                diffMonth = 12 + diffMonth;
            }
            diffInDays = Double.parseDouble(diffYear + "." + diffMonth);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return diffInDays;
    }

    public static double getDateDiffTotal(String dateFormat, String date1, String date2) {
        double diffInDays = 0.0;
        try {
            SimpleDateFormat dfDate = new SimpleDateFormat(dateFormat);
            Date d = null;
            Date d1 = null;
            try {
                d = dfDate.parse(date1);
                d1 = dfDate.parse(date2);//Returns 15/10/2012
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar startCalendar = new GregorianCalendar();
            startCalendar.setTime(d);
            Calendar endCalendar = new GregorianCalendar();
            endCalendar.setTime(d1);
            int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
            int diffMonth = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
            if (diffMonth < 0) {
                diffYear = diffYear - 1;
                diffMonth = 12 + diffMonth;
            }
            diffInDays = Double.parseDouble(diffYear + "." + diffMonth);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return diffInDays;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static CharSequence converteTimestamp(String mileSegundos) {
        return DateUtils.getRelativeTimeSpanString(Long.parseLong(mileSegundos), System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
    }

    public static boolean isValidMobile(String phone) {
        boolean check = false;
        if (!Pattern.matches("[a-zA-Z]", phone)) {
            if (phone.length() < 10 || phone.length() > 13) {
                // if(phone.length() != 10) {
                check = false;

            } else {
                check = true;
            }
        } else {
            check = false;
        }
        return check;
    }

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static String getAppVersion(Context context) {
        String versionName = "";
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(
                    context.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public static void takeScreenshot(Activity context, CoordinatorLayout coordinatorLayout) throws IOException {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
        String fileName = now + ".jpg";
        try {
//            String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/PayTZ";
            String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Screenshots";
            File folder = new File(dirPath);
            //File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "");
            folder.mkdirs();  //create directory

            // create bitmap screen capture
            View v1 = context.getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);
            File imageFile = new File(folder, fileName);
            imageFile.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;

            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            showSuccessSnackBar(coordinatorLayout, "Screenshot captured", context);

            MediaScannerConnection.scanFile(context,
                    new String[]{imageFile.toString()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("ExternalStorage", "Scanned " + path + ":");
                            Log.i("ExternalStorage", "-> uri=" + uri);
                        }
                    });
        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
        }
    }



/*
   public static void doLogout(final ConstraintLayout coordinatorLayout, final Context context, final Activity activity) {
        if (Util.hasInternet(Env.currentActivity)) {

            Util.showProDialog(Env.currentActivity);
            HashMap<String, Object> myHashMap = new HashMap<String, Object>();
            myHashMap.put("wakala_id", Config.getUserId());

            ApiService apiService = App.getClient().create(ApiService.class);
            Call<LogoutResponse> call = apiService.dologout(myHashMap);
            call.enqueue(new Callback<LogoutResponse>() {
                @Override
                public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                    Util.dismissProDialog();
                    if (response.body() != null) {
                        Config.removeAll();
                        logoutData = response.body();
                        if (logoutData.getResponse().getStatus()) {
                            Config.setLogoutStatus(true);
                            Config.setUserId("");
                            Config.setUserName("");
                            Config.setMD5Encode(null);
                            Config.setRememberToken(null);
                            Intent intent = new Intent(Env.currentActivity, LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            context.startActivity(intent);
                            activity.finish();
                        } else {
                            Config.setLogoutStatus(false);
                            if (coordinatorLayout != null) {
                                Util.showErrorSnackBar(coordinatorLayout, logoutData.getResponse().getMessage(), Env.currentActivity);
                            } else {
                                showCenteredToast(logoutData.getResponse().getMessage());
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<LogoutResponse> call, Throwable t) {
                    Log.e("fail", t.toString());
                    Config.setLogoutStatus(false);
                    Util.dismissProDialog();
                }
            });
        } else {
            Util.showErrorSnackBar(coordinatorLayout, context.getResources().getString(R.string.no_internet), Env.currentActivity);
        }
    }
*/

    public static Dialog isDeativateDialog(final ConstraintLayout coordinatorLayout, final Context context, final Activity activity) {
        if (context != null) {
            try {
                if (addNewDialog != null && addNewDialog.isShowing()) {
                    addNewDialog.hide();
                }
                addNewDialog = new Dialog(context);
                addNewDialog.getWindow().setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.trans)));
                addNewDialog.setContentView(R.layout.dialog_is_deactivate);
                addNewDialog.setCancelable(false);
                addNewDialog.setCanceledOnTouchOutside(false);
                TextView btnOk = (TextView) addNewDialog.findViewById(R.id.btnOk);

                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (addNewDialog != null && addNewDialog.isShowing()) {
                            addNewDialog.dismiss();
                        }
//                        Util.doLogout(coordinatorLayout,context, activity);
                    }
                });
                addNewDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return addNewDialog;
        } else {
            return null;
        }
    }

    public static void doSFlagLogout(final ConstraintLayout coordinatorLayout, final Context context, final Activity activity) {
        Config.setLogoutStatus(true);
        Config.setUserId("");


        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        activity.finish();
    }

    public static String changeDateAirtelFormat(String time) {
        String inputPattern = "dd/MM/yyyy HH:mm:ss";
        String outputPattern = "dd-MMM-yyyy h:mm a";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String getCurrentMonth() {
        try {
            Calendar cal = Calendar.getInstance();
            return monthName[cal.get(Calendar.MONTH)];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getLastMonth(int a) {
        try {
            Calendar cal = Calendar.getInstance();
            int month_no = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            if (month_no != 0) {
                month_no = month_no - a;
            } else {
                month_no = 11;
                year = year - 1;
            }
            return monthLastName[month_no] + " " + year;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getLastTwoMonth(int a) {
        try {
            Calendar cal = Calendar.getInstance();
            int month_no = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            if (month_no == 1) {
                month_no = 11;
                year = year - 1;
            } else if (month_no != 0) {
                month_no = month_no - a;
            } else {
                month_no = 10;
                year = year - 1;
            }
            return monthLastName[month_no] + " " + year;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getLastThreeMonth(int a) {
        try {
            Calendar cal = Calendar.getInstance();
            int month_no = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            if (month_no == 1) {
                month_no = 10;
                year = year - 1;
            } else if (month_no == 2) {
                month_no = 11;
                year = year - 1;
            } else if (month_no != 0) {
                month_no = month_no - a;
            } else {
                month_no = 9;
                year = year - 1;
            }
            return monthLastName[month_no] + " " + year;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String checkDay() {
        String day = "";
        try {
            Calendar c = Calendar.getInstance();
            int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
            if (timeOfDay >= 0 && timeOfDay < 12) {
                return day = "Good Morning";
            } else if (timeOfDay >= 12 && timeOfDay < 17) {
                return day = "Good Afternoon";
            } else if (timeOfDay >= 17 && timeOfDay < 24) {
                return day = "Good Evening";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return day;
    }

    public static String signatureKey(String privateKeyBase64, String signFields) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            KeySpec privateKeySpec = new PKCS8EncodedKeySpec(Base64.decode(privateKeyBase64, Base64.DEFAULT));
            PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

            Signature instance = Signature.getInstance("SHA1withRSA");
            instance.initSign(privateKey);
            instance.update((signFields).getBytes());
            byte[] signature = instance.sign();
            return Base64.encodeToString(signature, Base64.DEFAULT);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //sign fields MSISDN+PROCESS_CODE+TRAN_AMOUNT+DATETIME+SYSTEM_TRACE+CLIENT_ID

    public static int getAge(int year, int month, int day) {
        int age = 0;
        try {
            Calendar dob = Calendar.getInstance();
            Calendar today = Calendar.getInstance();
            dob.set(year, month, day);
            age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
            /*if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
                age--;
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return age;
    }

    public static String changeDateOffers(String time) {
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "dd MMM yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String changeDateFormat(String time) {
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd-MMM-yyyy h:mm a";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String changeDateMobileRechargeFormat(String time) {
        String inputPattern = "yyyy MMM dd HH:mm:ss";
        String outputPattern = "dd-MMM-yyyy h:mm a";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String changeUpdateDateFormat(String time) {
//        Unparseable date: "08/09/2018 14:57:38"
        String inputPattern = "dd/MM?yyyy HH:mm:ss";
        String outputPattern = "dd-MMM-yyyy h:mm a";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String changeWeekDateFormat(String time) {
        //2018-06-15 10:56:35
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "EEEE";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String changeMessageDateFormat(String time) {
        //2018-06-15 10:56:35
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd MMM yyyy HH:mm a";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String changeMessageFormat(String time) {
        //2018-06-15 10:56:35
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd MMM, yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String changeDateTime(String Datetime, String inputPattern, String outputPattern) {
        String str = null;
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
            SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
            Date date = null;
            try {
                date = inputFormat.parse(Datetime);
                return outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;
    }

/*
    public static Dialog addComingSoonDialog(Context context) {
        try {
            if (addNewDialog != null && addNewDialog.isShowing()) {
                addNewDialog.hide();
            }
            addNewDialog = new Dialog(Env.currentActivity, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
            addNewDialog.getWindow().setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.trans_black_light)));
            addNewDialog.setContentView(R.layout.maintense_dialog);
            btnCancel = (TextView) addNewDialog.findViewById(R.id.btnCancel);
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (addNewDialog != null && addNewDialog.isShowing()) {
                        addNewDialog.dismiss();
                    }
                }
            });

            addNewDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

                @Override
                public void onDismiss(DialogInterface dialog) {
                    addNewDialog = null;
                }
            });
            addNewDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

                @Override
                public void onCancel(DialogInterface dialog) {
                    addNewDialog = null;
                }
            });

            addNewDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addNewDialog;

    }
*/

    private boolean isAppOnForeground(Context context, String appPackageName) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        final String packageName = appPackageName;
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName.equals(packageName)) {
                //                Log.e("app",appPackageName);
                return true;
            }
        }
        return false;
    }

//    public static Dialog showOkDialog(View.OnClickListener okClick, String alert_message, boolean yesNo, String yes, String no) {
//        if (Env.currentActivity != null) {
//            try {
//                if (confirmation != null && confirmation.isShowing()) {
//                    confirmation.hide();
//                }
//                confirmation = new Dialog(Env.currentActivity, android.R.style.Theme_Translucent_NoTitleBar);
//                confirmation.setContentView(R.layout.ok_dialog);
//                TextView txtDescription_auth, txtOk_auth, txtNo_auth = null;
//                txtOk_auth = (TextView) confirmation.findViewById(R.id.txtOk_auth);
//                if (yesNo) {
//                    txtNo_auth = (TextView) confirmation.findViewById(R.id.txtNo_auth);
//                    txtNo_auth.setVisibility(View.VISIBLE);
//                }
//                txtDescription_auth = (TextView) confirmation.findViewById(R.id.txtDescription_auth);
//                txtDescription_auth.setText(alert_message);
//                txtOk_auth.setText(yes);
//                if (okClick != null) {
//                    txtOk_auth.setOnClickListener(okClick);
//                } else {
//                    txtOk_auth.setOnClickListener(new View.OnClickListener() {
//
//                        @Override
//                        public void onClick(View v) {
//                            confirmation.hide();
//                            confirmation = null;
//                        }
//                    });
//                    confirmation.setOnDismissListener(new DialogInterface.OnDismissListener() {
//
//                        @Override
//                        public void onDismiss(DialogInterface dialog) {
//                            confirmation = null;
//                        }
//                    });
//                    confirmation.setOnCancelListener(new DialogInterface.OnCancelListener() {
//
//                        @Override
//                        public void onCancel(DialogInterface dialog) {
//                            confirmation = null;
//                        }
//                    });
//                }
//                if (okClick != null && txtNo_auth != null) {
//                    if (!TextUtils.isEmpty(no))
//                        txtNo_auth.setText(no);
//                    txtNo_auth.setOnClickListener(okClick);
//                }
//                confirmation.show();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return confirmation;
//        } else {
//            return null;
//        }
//    }

}

