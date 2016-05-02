package ubbs.home.com.corelib.sample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ubbs.home.com.corelib.data.ListItem;
import ubbs.home.com.corelib.data.ListViewAdapter;
import ubbs.home.com.corelib.sample.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ListView mainList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainList = (ListView)findViewById(R.id.mainList);
        mainList.setAdapter(new ListViewAdapter(this,getItemList()));

        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id)
            {

                Intent intent =null;
                switch (position){
                    case 0:
                         intent = new Intent(MainActivity.this, EditTextSampleActivity.class);
                        startActivity(intent);

                        break;
                    case 1:
                         intent = new Intent(MainActivity.this, ButtonSampleActivity.class);
                        startActivity(intent);

                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, SlidingMenuActivity.class);
                        startActivity(intent);
                        break;
                }

            }});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private List<ListItem> getItemList(){
        List<ListItem> dataObject = new ArrayList<ListItem>();
        dataObject.add(new ListItem("Edit Text"));
        dataObject.add(new ListItem("Button"));
        dataObject.add(new ListItem("Forms"));
        dataObject.add(new ListItem("Sliding Menu"));

        return dataObject;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id ==R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
