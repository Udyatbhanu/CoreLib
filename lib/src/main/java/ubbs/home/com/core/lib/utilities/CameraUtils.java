package ubbs.home.com.core.lib.utilities;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import android.graphics.Matrix;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by udyatbhanu-mac on 7/11/15.
 */
public class CameraUtils {


    private static final int MEDIA_TYPE_IMAGE = 1;
    private static final int MEDIA_TYPE_VIDEO = 2;

    private CameraUtils(){

    }

    public static Uri getPhotoMediaUri(String folderName){
    return getOutputMediaFileUri(folderName, MEDIA_TYPE_IMAGE);
    }

    /** Create a file Uri for saving an image or video */
    private static Uri getOutputMediaFileUri(String folderName, int type){
        return Uri.fromFile(getOutputMediaDirectory(folderName, type));
    }


    /**
     *
     * @param folderName
     * @param type
     * @return
     */
    private static File getOutputMediaDirectory(String folderName, int type) {

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), folderName);

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }

        }


//        File mediaFileDirectory = new File(mediaStorageDir.getPath());

//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        File mediaFile;
//        if (type == MEDIA_TYPE_IMAGE){
//            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
//                    "IMG_"+ timeStamp + ".jpg");
//        } else if(type == MEDIA_TYPE_VIDEO) {
//            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
//                    "VID_"+ timeStamp + ".mp4");
//        } else {
//            return null;
//        }

        return mediaStorageDir;
    }

    /**
     *
     * @param folderName
     * @param type
     * @return
     */

    public static File getOutputMediaFile(String folderName, int type) {
        File mediaStorageDir =  getOutputMediaDirectory(folderName,  MEDIA_TYPE_IMAGE);

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }

        }


        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }


    /**
     *
     * @param folder
     * @return
     */
    public static File[] getImages(String folderName){


//        String externalStorageDirectoryPath = Environment
//                .getExternalStorageDirectory()
//                .getAbsolutePath();

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), folderName);

//        String targetPath = externalStorageDirectoryPath + File.separator +"folder";

//        File targetDirector = new File(mediaStorageDir);
        File[] files = mediaStorageDir.listFiles();


        return files;
    }


    /**
     *
     * @param file
     * @return
     */
    public static boolean deleteImage(File file){

        try{
            return file.delete();
        }catch(Exception e){
            return false;
        }


    }


    /**
     *
     * @param path
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap decodeSampledBitmapFromUri(String path, int reqWidth, int reqHeight) {

        Bitmap bm = null;
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(path, options);

        return bm;
    }


    /**
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(

            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float)height / (float)reqHeight);
            } else {
                inSampleSize = Math.round((float)width / (float)reqWidth);
            }
        }

        return inSampleSize;
    }


    /**
     * Rotate an image if required.
     * @param img
     * @param selectedImage
     * @return
     */
    public static Bitmap rotateImageIfRequired(Context context,Bitmap img, Uri selectedImage) {

        // Detect rotation
        int rotation=getRotation(context, selectedImage);
        if(rotation!=0){
            Matrix matrix = new Matrix();
            matrix.postRotate(rotation);
            Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
            img.recycle();
            return rotatedImg;
        }else{
            return img;
        }
    }


    /**
     * Get the rotation of the last image added.
     * @param context
     * @param selectedImage
     * @return
     */
    public static int getRotation(Context context,Uri selectedImage) {
        int rotation =0;
        ContentResolver content = context.getContentResolver();


        Cursor mediaCursor = content.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[] { "orientation", "date_added" },null, null,"date_added desc");

        if (mediaCursor != null && mediaCursor.getCount() !=0 ) {
            while(mediaCursor.moveToNext()){
                rotation = mediaCursor.getInt(0);
                break;
            }
        }
        mediaCursor.close();
        return rotation;
    }


    /**
     *
     * @param bm
     * @param path
     * @return
     */
    public static Bitmap rotateImageIfRequired(Bitmap bm, String path){
        int orientation= 0;
        try{
            ExifInterface ei = new ExifInterface(path);
            orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        }catch(IOException e){

        }

        Bitmap image =null;
        switch(orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                image = rotateImage(bm, 90);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                image = rotateImage(bm, 180);
                break;
            // etc.
        }

        return image;

    }

    /**
     *
     * @param source
     * @param angle
     * @return
     */
    private static Bitmap rotateImage(Bitmap source, float angle){
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }




}
