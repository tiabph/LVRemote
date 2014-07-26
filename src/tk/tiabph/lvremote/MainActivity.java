package tk.tiabph.lvremote;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;

public class MainActivity extends Activity implements ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v13.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;
	FragAdapter mFAdapter;
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	
	//TCP comm
	public static Socket mSocket;
	public static BufferedReader br;
	public static String mHostAdd="192.168.100.1";
	public static int mHostPort = 3614;
	public static boolean mIsConnected = false;
	public static String StopCmd=null;
	public static List<Fragment> fragments;
	
	public static Timer timer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		setContentView(R.layout.activity_main);
		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
		
		fragments = new ArrayList<Fragment>();  
        fragments.add(new Fragment_Sample());  
        fragments.add(new Fragment_Mirror());  
        fragments.add(new Fragment_Obj());  
        fragments.add(new Fragment_PIEZO());  
        fragments.add(new Fragment_Servo());
        fragments.add(new Fragment_Settings());
        mFAdapter = new FragAdapter(getFragmentManager(), fragments); 
        
		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mFAdapter);
		mViewPager.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				//System.out.printf("On Drag: %d\n",event.getAction());
				if(StopCmd!=null){
					SendCmd(StopCmd);
					StopCmd=null;
				}
				return false;
			}
		});
		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mFAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mFAdapter.getPageTitle(i))
					.setTabListener(this));
		}
		
		//Connect("192.168.2.100",3614);
		timer=new Timer();
        timer.schedule(new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boolean update[]={false,false,false,false};
				if(mSocket!=null && mSocket.isConnected()){
					try {
						System.out.printf("read\n");
						String tstr,str1,str2,str3,str4;
						double par=0;
						while((tstr = br.readLine())!=null){
							str1 = tstr.substring(0, 3);
							str2 = tstr.substring(4, 5);
							str3 = tstr.substring(6, 10);
							str4 = tstr.substring(11);
							System.out.printf("%s|%s|%s|%s\n",str1,str2,str3,str4);
							par = Double.parseDouble(str4);
							if(str1.equals("NPM")){//NPM
								if(str3.equals("GETP")){//Position
									switch(Integer.decode(str2)){
									case 1:
										Fragment_Mirror.x1=par;
										break;
									case 2:
										Fragment_Mirror.y1=par;
										break;
									case 3:
										Fragment_Mirror.x2=par;
										break;
									case 4:
										Fragment_Mirror.y2=par;
										break;
									}
									update[1]=true;
								}
							}else if(str1.equals("THL")){//Thorlabs
								if(str3.equals("GETP")){//Position
									switch(Integer.decode(str2)){
									case 1:
										Fragment_Sample.x=par;
										break;
									case 2:
										Fragment_Sample.y=par;
										break;
									case 3:
										Fragment_Sample.z=par;
										break;
									case 4:
										Fragment_Obj.obj1=par;
										break;
									case 5:
										Fragment_Obj.obj2=par;
										break;
									case 6:
										Fragment_Obj.opt=par;
										break;
									}
									update[0]=true;
									update[2]=true;
								}
							}else if(str1.equals("PIZ")){//PIEZO
								if(str3.equals("GETP")){//Position
									Fragment_PIEZO.pi=par;
								}
								update[3]=true;
							}
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}				
			}
        	
        }, 10, 100);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
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
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
			return PlaceholderFragment.newInstance(position + 1);
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 6;
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
			case 3:
				return getString(R.string.title_section4).toUpperCase(l);
			case 4:
				return getString(R.string.title_section5).toUpperCase(l);
			case 5:
				return getString(R.string.title_section6).toUpperCase(l);
			}
			return null;
		}
	}

	public class FragAdapter extends FragmentPagerAdapter{  
	      
	    private List<Fragment> fragments;  
	      
	  
	    public FragAdapter(FragmentManager fm) {  
	        super(fm);  
	    }  
	      
	    public FragAdapter(FragmentManager fm, List<Fragment> fragments) {  
	        super(fm);  
	        this.fragments = fragments;  
	    }  
	  
	    @Override  
	    public Fragment getItem(int position) {  
	        return fragments.get(position);  
	    }  
	  
	    @Override  
	    public int getCount() {  
	        return fragments.size();  
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
			case 3:
				return getString(R.string.title_section4).toUpperCase(l);
			case 4:
				return getString(R.string.title_section5).toUpperCase(l);
			case 5:
				return getString(R.string.title_section6).toUpperCase(l);
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
		public int sectionnum=0;
		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			fragment.sectionnum = sectionNumber;
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
			View rootView=null;
			switch (this.sectionnum){//getArguments().getInt(ARG_SECTION_NUMBER)) {
			case 1:
				rootView = inflater.inflate(R.layout.fragment_thorlabsxyz, container,false);
			case 2:
				rootView = inflater.inflate(R.layout.fragment_newport, container,false);
			case 3:
				rootView = inflater.inflate(R.layout.fragment_thorlabsobj, container,false);
			case 4:
				rootView = inflater.inflate(R.layout.fragment_piz, container,false);
			case 5:
				rootView = inflater.inflate(R.layout.fragment_servo, container,false);
			case 6:
				rootView = inflater.inflate(R.layout.fragment_settings, container,false);
			}
			/*View rootView = inflater.inflate(R.layout.fragment_newport, container,
					false);
			TextView textView = (TextView) rootView
					.findViewById(R.id.section_label);
			textView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));*/
			return rootView;
		}
	}

	//
	public static boolean Connect(String address, int port){
		
		mHostAdd = address;
		mHostPort = port;
		try {
			//mSocket = new Socket(mHostAdd, mHostPort);
			//add time out to 5s
			mSocket = new Socket();
			SocketAddress socAddress = new InetSocketAddress(mHostAdd, port); 
			mSocket.connect(socAddress, 5000);
			br = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mIsConnected = mSocket.isConnected();
		return mIsConnected;
	}
	
	//
	public static void DisConnect(){
		try {
			mSocket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mIsConnected = false;
	}
	
	public static String SendCmd(String Cmd){
		String msg = null;
		try {
			if(mIsConnected){
				PrintWriter out = new PrintWriter( new BufferedWriter( new OutputStreamWriter(mSocket.getOutputStream())),true);        
				out.println(Cmd + "\n\r");
				out.flush();
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	
	public static double parseCmd(String cmd){
		if(cmd.length()<10){
			return 0;
		}else{
			String tpar = cmd.substring(11);
			return Double.parseDouble( tpar );
		}
	}
	public static double GetCmd(String Cmd){
		String ret = SendCmd(Cmd);
		if(ret==null){
			return 0;
		}else{
			return parseCmd(ret);
		}
	}
}
