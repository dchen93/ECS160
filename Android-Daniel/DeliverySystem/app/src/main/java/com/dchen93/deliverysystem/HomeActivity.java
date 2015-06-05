package com.dchen93.deliverysystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.software.shell.fab.ActionButton;


public class HomeActivity extends ActionBarActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    //SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    //ViewPager mViewPager;
    ArrayList friends = new ArrayList<String>();
    private ListView mListView;
    DialogInterface.OnClickListener dialogClickListener;
    DialogInterface.OnClickListener dialogClickListener2;
    EditText input;// = new EditText(this);
    ActionButton mAddFriendBtn;
    String value = "";
    ArrayAdapter<String> adapter;
    double xcoord = 0;
    double ycoord = 0;
    String deliveryFriend = "";
    int delivery = 0;
    String fileName;// = context.getFilesDir()+"/data.txt";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mListView = (ListView) findViewById(R.id.id_list_view);
        fileName = getFilesDir()+"data.txt";

        String line;

        try {

            FileReader fileReader1 = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader1);
            int flag = 0;
            while ((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                if(line.equals("-"))
                {
                    flag = 1;
                    continue;
                }
                if(flag == 1)
                {
                    if(line.equals("false"))
                    {
                        delivery = 0;
                        break;
                    }
                    else
                    {
                        delivery = 1;
                        flag++;
                    }
                    continue;
                }
                if(flag == 2)
                {
                    deliveryFriend = line;
                    flag++;
                    continue;
                }
                if(flag == 3)
                {
                    xcoord = Double.parseDouble(line);
                    flag++;
                    continue;
                }
                if(flag == 4)
                {
                    ycoord = Double.parseDouble(line);
                    flag++;
                    break;
                }
                friends.add(line);
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            try{
                PrintWriter writer = new PrintWriter(fileName);//rewrites whole file :/, too lazy to do nicely
                writer.println("Daniel");
                writer.println("Ittai");
                writer.println("Ricky");
                writer.println("Kristijonas");
                writer.println("Bob");
                writer.println("Jane");
                writer.println("Joe");
                writer.println("Mary");
                writer.println("Elizabeth");
                writer.println("Larry");
                writer.println("Harry");
                writer.println("John");
                writer.println("Liz");
                writer.println("-");
                writer.println("false");
                writer.println(" ");//Deliveryfriend
                writer.println("38.532247");//xcoord
                writer.println("-121.577208");//ycoord
                writer.close();
                friends.add("Daniel");
                friends.add("Ittai");
                friends.add("Ricky");
                friends.add("Kristijonas");
                friends.add("Bob");
                friends.add("Jane");
                friends.add("Joe");
                friends.add("Mary");
                friends.add("Elizabeth");
                friends.add("Larry");
                friends.add("Harry");
                friends.add("John");
                friends.add("Liz");
            } catch(FileNotFoundException ex2) {

            }

        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, friends);

        mListView.setAdapter(adapter);

        dialogClickListener = new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                    {
                        friends.remove(value);
                        //adapter.remove(value);
                        adapter.notifyDataSetChanged();
                        break;
                    }
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                    case DialogInterface.BUTTON_NEUTRAL:
                    {
                        Toast.makeText(HomeActivity.this
                                , "Delivery Added"
                                , Toast.LENGTH_SHORT).show();
                        delivery = 1;
                        try{
                            PrintWriter writer = new PrintWriter(fileName);
                            writer.println("Daniel");
                            writer.println("Ittai");
                            writer.println("Ricky");
                            writer.println("Kristijonas");
                            writer.println("Bob");
                            writer.println("Jane");
                            writer.println("Joe");
                            writer.println("Mary");
                            writer.println("Elizabeth");
                            writer.println("Larry");
                            writer.println("Harry");
                            writer.println("John");
                            writer.println("Liz");
                            writer.println("-");
                            writer.println("true");
                            writer.println(value);//Deliveryfriend
                            writer.println("38.532247");//xcoord
                            writer.println("-121.577208");//ycoord
                            writer.close();
                        } catch(FileNotFoundException ex2) {

                        }
                        break;
                    }



                }
            }
        };

        dialogClickListener2 = new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        friends.add(input.getText());
                        //adapter.add(input.getText());
                        adapter.notifyDataSetChanged();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;

                }
            }
        };

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                value = (String) parent.getItemAtPosition(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setTitle(value);
                builder.setCancelable(true);
                builder.setNegativeButton("Cancel", dialogClickListener);
                builder.setPositiveButton("Remove", dialogClickListener);
                builder.setNeutralButton("StartDelivery", dialogClickListener).show();
            }
        });



        mAddFriendBtn = (ActionButton) findViewById(R.id.action_button);

        mAddFriendBtn.setShowAnimation(ActionButton.Animations.JUMP_FROM_DOWN);
        mAddFriendBtn.setHideAnimation(ActionButton.Animations.JUMP_TO_DOWN);

        mAddFriendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = new EditText(HomeActivity.this);
                input.setSingleLine();
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setTitle("Add Friend");
                builder.setCancelable(true);
                builder.setView(input);
                builder.setNegativeButton("Cancel", dialogClickListener2);
                builder.setPositiveButton("Add", dialogClickListener2).show();

            }
        });
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        //mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        //mViewPager = (ViewPager) findViewById(R.id.pager);
        //mViewPager.setAdapter(mSectionsPagerAdapter);
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState){
                    case SCROLL_STATE_IDLE:
                        mListView.setVerticalScrollBarEnabled(false);
                        mAddFriendBtn.show();
                        break;
                    default:
                        mListView.setVerticalScrollBarEnabled(true);
                        mAddFriendBtn.hide();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //nothing
            }
        });

        android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(0xff2979ff));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_map) {
            Intent intent = new Intent(HomeActivity.this, MapsActivity.class);
            HomeActivity.this.startActivity(intent);
        }
        if (id == R.id.action_deliveries) {
            Intent intent = new Intent(HomeActivity.this, DeliveriesActivity.class);
            HomeActivity.this.startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_home, container, false);
            return rootView;
        }
    }

}
