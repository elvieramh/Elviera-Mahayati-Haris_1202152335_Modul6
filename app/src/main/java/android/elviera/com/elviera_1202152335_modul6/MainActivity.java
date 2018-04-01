package android.elviera.com.elviera_1202152335_modul6;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //deklarasi variable

    public static final int RESUlT_CODE_LOGIN=1000;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ProgressDialog loading;

    private FirebaseAuth auth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabase;

    public static List<PhotoModel> photosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loading=new ProgressDialog(this);
        photosList=new ArrayList<>();
        //ambil data login untuk keperluan login
        auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser()==null){
            startActivity(new Intent(getApplicationContext(), SignInActivity.class));
            finish();
        }


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        //tabLayout.getTabAt(0).setIcon(R.drawable.ic_new);
        //tabLayout.getTabAt(1).setIcon(R.drawable.ic_my);

        mFirebaseDatabase=FirebaseDatabase.getInstance();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        if(auth.getCurrentUser()!=null){menu.findItem(R.id.action_myuser).setTitle(makeUsername(auth.getCurrentUser().getEmail()));}
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_signout) {
            auth.signOut();

            startActivity(new Intent(getApplicationContext(), SignInActivity.class));
            finish();
            return true;
        }else if(id==R.id.action_refresh){
            //loadData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //keperluan tab layout
    private void setupViewPager(ViewPager pager){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new RecentFragment(), "Recent");
        viewPagerAdapter.addFragment(new MyFragment(), "My Gallery");
        pager.setAdapter(viewPagerAdapter);
    }
    //menampilkan tab layout
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }

    public String makeUsername(String email){
        return email.substring(0,email.lastIndexOf("@"));
    }

}
