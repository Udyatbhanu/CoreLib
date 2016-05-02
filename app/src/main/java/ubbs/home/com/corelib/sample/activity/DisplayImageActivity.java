package ubbs.home.com.corelib.sample.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.GridView;
import android.widget.Toast;

import java.io.File;

import ubbs.home.com.corelib.sample.R;
import ubbs.home.com.corelib.sample.ui.ImageAdapter;
/**
 * Created by udyatbhanu-mac on 7/9/15.
 */
public class DisplayImageActivity extends Activity {

    private ImageAdapter imageAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        imageAdapter = new ImageAdapter(this);
        gridview.setAdapter(imageAdapter);

        String ExternalStorageDirectoryPath = Environment
                .getExternalStorageDirectory()
                .getAbsolutePath();

        String targetPath = ExternalStorageDirectoryPath + "/DCIM/Camera";




        Toast.makeText(getApplicationContext(), targetPath, Toast.LENGTH_LONG).show();
//        File targetDirector = new File(targetPath);

        File targetDirector = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "VnuApp");

        File[] files = targetDirector.listFiles();
        for (File file : files){
            imageAdapter.add(file.getAbsolutePath());
        }
    }
}