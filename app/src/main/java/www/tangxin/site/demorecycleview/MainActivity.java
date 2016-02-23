package www.tangxin.site.demorecycleview;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SimpleAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<String> mDatas;


    private FloatingActionButton mFloatBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mFloatBtn = (FloatingActionButton) findViewById(R.id.fab);
        mFloatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopuMenu();
            }
        });


        initDatas();
        initViews();
        mAdapter = new SimpleAdapter(this, mDatas);
        mRecyclerView.setAdapter(mAdapter);

        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //mRecyclerView.setLayoutManager(linearLayoutManager);

        // mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        //通过margin分隔
    }

    private void showPopuMenu() {
        PopupMenu popup = new PopupMenu(this,mFloatBtn);

        MenuInflater menuInflater = popup.getMenuInflater();
        menuInflater.inflate(R.menu.popumenu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                switch (id){
                    case R.id.action_gridview:
                        mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
                        break;
                    case R.id.action_hor_gridview:
                        mRecyclerView.setLayoutManager(new GridLayoutManager(getApplication(), 3, GridLayoutManager.HORIZONTAL, false));
                        break;
                    case R.id.action_listview:
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        break;

                    case R.id.action_staggered:
                        startActivity(new Intent(getApplicationContext(), StaggeredActivity.class));
                        break;
                }
                return true;
            }
        });
        popup.show();
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
    }

    private void initDatas() {
        mDatas = new ArrayList<String>();
        for(int i=1; i< 200;i++){
            mDatas.add(""+i);
        }
    }

}
