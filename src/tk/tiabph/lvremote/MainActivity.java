package tk.tiabph.lvremote;

<<<<<<< HEAD
import java.util.Locale;
=======
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
>>>>>>> origin/glsh

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
<<<<<<< HEAD
import android.support.v4.view.ViewPager;
=======
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.view.DragEvent;
>>>>>>> origin/glsh
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
<<<<<<< HEAD
import android.view.View;
import android.view.ViewGroup;
=======
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;
>>>>>>> origin/glsh
import android.widget.TextView;

public class MainActivity extends Activity implements ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v13.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;
<<<<<<< HEAD

=======
	FragAdapter mFAdapter;
>>>>>>> origin/glsh
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
<<<<<<< HEAD
=======
	
	//TCP comm
	public static Socket mSocket;
	public static BufferedReader br;
	public static String mHostAdd="192.168.100.1";
	public static int mHostPort = 3614;
	public static boolean mIsConnected = false;
	public static String StopCmd=null;
	public static List<Fragment> fragments;
	
	public static Timer timer;
>>>>>>> origin/glsh

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
<<<<<<< HEAD
		setContentView(R.layout.activity_main);

=======
		StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		setContentView(R.layout.activity_main);
>>>>>>> origin/glsh
		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
<<<<<<< HEAD

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

=======
		
		fragments = new ArrayList<Fragment>();  
        fragments.add(new MyFragment1());  
        fragments.add(new MyFragment2());  
        fragments.add(new MyFragment3());  
        fragments.add(new MyFragment4());  
        fragments.add(new MyFragment5());
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
>>>>>>> origin/glsh
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
<<<<<<< HEAD
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
=======
		for (int i = 0; i < mFAdapter.getCount(); i++) {
>>>>>>> origin/glsh
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
<<<<<<< HEAD
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
=======
					.setText(mFAdapter.getPageTitle(i))
					.setTabListener(this));
		}
		
		//Connect("192.168.2.100",3614);
		timer=new Timer();
        timer.schedule(new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				/*if(mSocket==null){
					System.out.printf("null\n");
				}else if(mSocket.isConnected()){
					System.out.printf("is Connected\n");
				}else{
					System.out.printf("NOT Connected\n");
				}*/
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
										MyFragment2.x1=par;
										break;
									case 2:
										MyFragment2.y1=par;
										break;
									case 3:
										MyFragment2.x2=par;
										break;
									case 4:
										MyFragment2.y2=par;
										break;
									}
									update[1]=true;
								}
							}else if(str1.equals("THL")){//Thorlabs
								if(str3.equals("GETP")){//Position
									switch(Integer.decode(str2)){
									case 1:
										MyFragment1.x=par;
										break;
									case 2:
										MyFragment1.y=par;
										break;
									case 3:
										MyFragment1.z=par;
										break;
									case 4:
										MyFragment3.obj1=par;
										break;
									case 5:
										MyFragment3.obj2=par;
										break;
									case 6:
										MyFragment3.opt=par;
										break;
									}
									update[0]=true;
									update[2]=true;
								}
							}else if(str1.equals("PIZ")){//PIEZO
								if(str3.equals("GETP")){//Position
									MyFragment4.pi=par;
								}
								update[3]=true;
							}
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				/*
				//update fragment1
				if(fragments.get(0).getView() != null){
					fragments.get(0).getView().post(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						MyFragment1.txt_x.setText(Double.toString(MyFragment1.x));
						MyFragment1.txt_y.setText(Double.toString(MyFragment1.y));
						MyFragment1.txt_z.setText(Double.toString(MyFragment1.z));
						}					
					});
				}
				//update fragment2
				if(fragments.get(1).getView() != null){
					fragments.get(1).getView().post(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						MyFragment2.txt_x1.setText(Double.toString(MyFragment2.x1));
						MyFragment2.txt_y1.setText(Double.toString(MyFragment2.y1));
						MyFragment2.txt_x2.setText(Double.toString(MyFragment2.x2));
						MyFragment2.txt_y2.setText(Double.toString(MyFragment2.y2));
						}					
					});
				}
				//update fragment3
				if(fragments.get(2).getView() != null){
					fragments.get(2).getView().post(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						MyFragment3.txt_obj1.setText(Double.toString(MyFragment3.obj1));
						MyFragment3.txt_obj2.setText(Double.toString(MyFragment3.obj2));
						MyFragment3.txt_opt.setText(Double.toString(MyFragment3.opt));
						}					
					});
				}
				//update fragment4
				if(fragments.get(3).getView() != null){
					fragments.get(3).getView().post(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						MyFragment4.txt_pi.setText(Double.toString(MyFragment4.pi));
	                	double progress = MyFragment4.sb_pistep.getProgress();//0-100:0:10
		            	double speed = progress/10.0;
		            	MyFragment4.txt_pistep.setText(Double.toString(speed));
						}					
					});
				}*/
			}
        	
        }, 10, 100);
>>>>>>> origin/glsh
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
<<<<<<< HEAD

=======
>>>>>>> origin/glsh
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
<<<<<<< HEAD
			return 3;
=======
			return 5;
>>>>>>> origin/glsh
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
<<<<<<< HEAD
=======
			case 3:
				return getString(R.string.title_section4).toUpperCase(l);
			case 4:
				return getString(R.string.title_section5).toUpperCase(l);
>>>>>>> origin/glsh
			}
			return null;
		}
	}

<<<<<<< HEAD
=======
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
			}
			return null;
		}
	}  
>>>>>>> origin/glsh
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";
<<<<<<< HEAD

=======
		public int sectionnum=0;
>>>>>>> origin/glsh
		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
<<<<<<< HEAD
=======
			fragment.sectionnum = sectionNumber;
>>>>>>> origin/glsh
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}
<<<<<<< HEAD

=======
		
>>>>>>> origin/glsh
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
<<<<<<< HEAD
			View rootView = inflater.inflate(R.layout.fragment_main, container,
=======
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
				rootView = inflater.inflate(R.layout.fragment_settings, container,false);
			}
			/*View rootView = inflater.inflate(R.layout.fragment_newport, container,
>>>>>>> origin/glsh
					false);
			TextView textView = (TextView) rootView
					.findViewById(R.id.section_label);
			textView.setText(Integer.toString(getArguments().getInt(
<<<<<<< HEAD
					ARG_SECTION_NUMBER)));
=======
					ARG_SECTION_NUMBER)));*/
>>>>>>> origin/glsh
			return rootView;
		}
	}

<<<<<<< HEAD
=======
	//Sample XYZ
	public static class MyFragment1 extends Fragment {  
	    //private Button btn; 
		public static Button btn_xp,btn_xn,btn_yp,btn_yn,btn_zp,btn_zn;  
		public static SeekBar sb_speed;
        public static TextView txt_x, txt_y, txt_z;
        public static Timer timer;
        public static double redy=0,x=0,y=0,z=0;
        
        static final Handler handler = new Handler() {     
            @Override  
            public void handleMessage(Message msg) {   
                super.handleMessage(msg);   
                //handler处理消息  
                if(msg.what>0){   
                	txt_x.setText(Double.toString(x));
                	txt_y.setText(Double.toString(y));
                	txt_z.setText(Double.toString(z));
                }   
            }   
        };
        
        static void Update(){
        	txt_x.setText(Double.toString(x));
        	txt_y.setText(Double.toString(y));
        	txt_z.setText(Double.toString(z));
        }
        
	    @Override  
	    public void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	          
	    }  
	      
	      
	    @Override  
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
	            Bundle savedInstanceState) {  
	        View view = inflater.inflate(R.layout.fragment_thorlabsxyz, container, false);  
	        btn_xp = (Button) view.findViewById(R.id.btn_xp);  
	        btn_xn = (Button) view.findViewById(R.id.btn_xn); 
	        btn_yp = (Button) view.findViewById(R.id.btn_yp);  
	        btn_yn = (Button) view.findViewById(R.id.btn_yn); 
	        btn_zp = (Button) view.findViewById(R.id.btn_zp);  
	        btn_zn = (Button) view.findViewById(R.id.btn_zn);
	        sb_speed = (SeekBar) view.findViewById(R.id.sb_xyzspeed);
	        txt_x = (TextView) view.findViewById(R.id.txt_x);
	        txt_y = (TextView) view.findViewById(R.id.txt_y);
	        txt_z = (TextView) view.findViewById(R.id.txt_z);
	        
	        timer=new Timer();
	        timer.schedule(new TimerTask(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Message msg = new Message();
					msg.what = 1;
					handler.sendMessage(msg);   
				}
	        	
	        }, 10, 100);
	        
	        btn_xp.setOnTouchListener(new OnTouchListener() {  
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
	                    if(event.getAction() == MotionEvent.ACTION_UP){  
	                    	MainActivity.SendCmd("THL,1,STOP,0");
	                    	MainActivity.StopCmd=null;
	                    }   
	                    if(event.getAction() == MotionEvent.ACTION_DOWN){  
	                    	MainActivity.SendCmd("THL,1,MOVV,1");
	                    	MainActivity.StopCmd="THL,1,STOP,0";
	                    }   
					return false;
				}   
	        }); 
	        
	        btn_xn.setOnTouchListener(new OnTouchListener() {  
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
	                    if(event.getAction() == MotionEvent.ACTION_UP){  
	                    	MainActivity.SendCmd("THL,1,STOP,0");
	                    	MainActivity.StopCmd=null;
	                    }   
	                    if(event.getAction() == MotionEvent.ACTION_DOWN){  
	                    	MainActivity.SendCmd("THL,1,MOVV,-1");
	                    	MainActivity.StopCmd="THL,1,STOP,0";
	                    }   
					return false;
				}   
	        }); 
	        btn_yp.setOnTouchListener(new OnTouchListener() {  
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
	                    if(event.getAction() == MotionEvent.ACTION_UP){  
	                    	MainActivity.SendCmd("THL,2,STOP,0");
	                    	MainActivity.StopCmd=null;
	                    }   
	                    if(event.getAction() == MotionEvent.ACTION_DOWN){  
	                    	MainActivity.SendCmd("THL,2,MOVV,1");
	                    	MainActivity.StopCmd="THL,2,STOP,0";
	                    }   
					return false;
				}   
	        }); 
	        
	        btn_yn.setOnTouchListener(new OnTouchListener() {  
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
	                    if(event.getAction() == MotionEvent.ACTION_UP){  
	                    	MainActivity.SendCmd("THL,2,STOP,0");
	                    	MainActivity.StopCmd=null;
	                    }   
	                    if(event.getAction() == MotionEvent.ACTION_DOWN){  
	                    	MainActivity.SendCmd("THL,2,MOVV,-1");
	                    	MainActivity.StopCmd="THL,2,STOP,0";
	                    }   
					return false;
				}   
	        });
	        
	        btn_zp.setOnTouchListener(new OnTouchListener() {  
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
	                    if(event.getAction() == MotionEvent.ACTION_UP){  
	                    	MainActivity.SendCmd("THL,3,STOP,0");
	                    	MainActivity.StopCmd=null;
	                    }   
	                    if(event.getAction() == MotionEvent.ACTION_DOWN){  
	                    	MainActivity.SendCmd("THL,3,MOVV,1");
	                    	MainActivity.StopCmd="THL,3,STOP,0";
	                    }   
					return false;
				}   
	        }); 
	        
	        btn_zn.setOnTouchListener(new OnTouchListener() {  
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
	                    if(event.getAction() == MotionEvent.ACTION_UP){  
	                    	MainActivity.SendCmd("THL,3,STOP,0");
	                    	MainActivity.StopCmd=null;
	                    }   
	                    if(event.getAction() == MotionEvent.ACTION_DOWN){  
	                    	MainActivity.SendCmd("THL,3,MOVV,-1");
	                    	MainActivity.StopCmd="THL,3,STOP,0";
	                    }   
					return false;
				}   
	        });
	        
	      //seek bar event
	        sb_speed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
	            /**
	             * 拖动条停止拖动的时候调用
	             */
	            @Override
	            public void onStopTrackingTouch(SeekBar seekBar) {
	            	float progress = seekBar.getProgress();//0-100:0:2
	            	float speed = progress/50;
	            	MainActivity.SendCmd("THL,1,SETV," + String.valueOf(speed));
	            	MainActivity.SendCmd("THL,2,SETV," + String.valueOf(speed));
	            	MainActivity.SendCmd("THL,3,SETV," + String.valueOf(speed));
	            }
	            /**
	             * 拖动条开始拖动的时候调用
	             */
	            @Override
	            public void onStartTrackingTouch(SeekBar seekBar) {
	                
	            }
	            /**
	             * 拖动条进度改变的时候调用
	             */
	            @Override
	            public void onProgressChanged(SeekBar seekBar, int progress,
	                    boolean fromUser) {
	                
	            }
	        });
	        
	        return view;  
	    }  
	      
	    @Override  
	    public void onActivityCreated(Bundle savedInstanceState) {  
	        super.onActivityCreated(savedInstanceState);  
	    }  
	      
	    @Override  
	    public void onPause() {  
	        super.onPause();  
	    }  
	      
	} 
	
	//Mirror
	public static class MyFragment2 extends Fragment {  
		public static Button btn_x1p,btn_x1n,btn_y1p,btn_y1n,btn_x2p,btn_x2n,btn_y2p,btn_y2n;  
	    public static SeekBar sb_speed;
        public static TextView txt_x1, txt_y1, txt_x2, txt_y2;
        public static Timer timer;
        public static double redy=0,x1=0,y1=0,x2=0,y2=0;
        
        static final Handler handler = new Handler() {     
            @Override  
            public void handleMessage(Message msg) {   
                super.handleMessage(msg);   
                //handler处理消息  
                if(msg.what>0){   
                	txt_x1.setText(Double.toString(x1));
                	txt_y1.setText(Double.toString(y1));
                	txt_x2.setText(Double.toString(x2));
                	txt_y2.setText(Double.toString(y2));
                }   
            }   
        };
        
        static void Update(){
        	txt_x1.setText(Double.toString(x1));
        	txt_y1.setText(Double.toString(y1));
        	txt_x2.setText(Double.toString(x2));
        	txt_y2.setText(Double.toString(y2));
        }

	    @Override  
	    public void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	          
	    }  
	      
	      
	    @Override  
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
	            Bundle savedInstanceState) {  
	        View view = inflater.inflate(R.layout.fragment_newport, container, false);  
	        btn_x1p = (Button) view.findViewById(R.id.btn_x1p);  
	        btn_x1n = (Button) view.findViewById(R.id.btn_x1n); 
	        btn_y1p = (Button) view.findViewById(R.id.btn_y1p);  
	        btn_y1n = (Button) view.findViewById(R.id.btn_y1n); 
	        btn_x2p = (Button) view.findViewById(R.id.btn_x2p);  
	        btn_x2n = (Button) view.findViewById(R.id.btn_x2n); 
	        btn_y2p = (Button) view.findViewById(R.id.btn_y2p);  
	        btn_y2n = (Button) view.findViewById(R.id.btn_y2n); 
	        sb_speed = (SeekBar) view.findViewById(R.id.sb_npspeed); 
	        txt_x1 = (TextView) view.findViewById(R.id.txt_x1);
	        txt_y1 = (TextView) view.findViewById(R.id.txt_y1);
	        txt_x2 = (TextView) view.findViewById(R.id.txt_x2);
	        txt_y2 = (TextView) view.findViewById(R.id.txt_y2);

	        timer=new Timer();
	        timer.schedule(new TimerTask(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Message msg = new Message();
					msg.what = 1;   
                    handler.sendMessage(msg);   
				}
	        	
	        }, 10, 100);
	        
	        btn_x1p.setOnTouchListener(new OnTouchListener() {  
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
	                    if(event.getAction() == MotionEvent.ACTION_UP){  
	                    	MainActivity.SendCmd("NPM,1,STOP,0");
	                    	MainActivity.StopCmd=null;
	                    }   
	                    if(event.getAction() == MotionEvent.ACTION_DOWN){  
	                    	MainActivity.SendCmd("NPM,1,MOVJ,1");
	                    	MainActivity.StopCmd="NPM,1,STOP,0";
	                    }   
					return false;
				}   
	        }); 
	        
	        btn_x1n.setOnTouchListener(new OnTouchListener() {  
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
	                    if(event.getAction() == MotionEvent.ACTION_UP){  
	                    	MainActivity.SendCmd("NPM,1,STOP,0");
	                    	MainActivity.StopCmd=null;
	                    }   
	                    if(event.getAction() == MotionEvent.ACTION_DOWN){  
	                    	MainActivity.SendCmd("NPM,1,MOVJ,-1");
	                    	MainActivity.StopCmd="NPM,1,STOP,0";
	                    }   
					return false;
				}   
	        }); 
	        btn_y1p.setOnTouchListener(new OnTouchListener() {  
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
	                    if(event.getAction() == MotionEvent.ACTION_UP){  
	                    	MainActivity.SendCmd("NPM,2,STOP,0");
	                    	MainActivity.StopCmd=null;
	                    }   
	                    if(event.getAction() == MotionEvent.ACTION_DOWN){  
	                    	MainActivity.SendCmd("NPM,2,MOVJ,1");
	                    	MainActivity.StopCmd="NPM,2,STOP,0";
	                    }   
					return false;
				}   
	        }); 
	        
	        btn_y1n.setOnTouchListener(new OnTouchListener() {  
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
	                    if(event.getAction() == MotionEvent.ACTION_UP){  
	                    	MainActivity.SendCmd("NPM,2,STOP,0");
	                    	MainActivity.StopCmd=null;
	                    }   
	                    if(event.getAction() == MotionEvent.ACTION_DOWN){  
	                    	MainActivity.SendCmd("NPM,2,MOVJ,-1");
	                    	MainActivity.StopCmd="NPM,2,STOP,0";
	                    }   
					return false;
				}   
	        });
	        
	        btn_x2p.setOnTouchListener(new OnTouchListener() {  
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
	                    if(event.getAction() == MotionEvent.ACTION_UP){  
	                    	MainActivity.SendCmd("NPM,3,STOP,0");
	                    	MainActivity.StopCmd=null;
	                    }   
	                    if(event.getAction() == MotionEvent.ACTION_DOWN){  
	                    	MainActivity.SendCmd("NPM,3,MOVJ,1");
	                    	MainActivity.StopCmd="NPM,3,STOP,0";
	                    }   
					return false;
				}   
	        }); 
	        
	        btn_x2n.setOnTouchListener(new OnTouchListener() {  
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
	                    if(event.getAction() == MotionEvent.ACTION_UP){  
	                    	MainActivity.SendCmd("NPM,3,STOP,0");
	                    	MainActivity.StopCmd=null;
	                    }   
	                    if(event.getAction() == MotionEvent.ACTION_DOWN){  
	                    	MainActivity.SendCmd("NPM,3,MOVJ,-1");
	                    	MainActivity.StopCmd="NPM,3,STOP,0";
	                    }   
					return false;
				}   
	        }); 
	        btn_y2p.setOnTouchListener(new OnTouchListener() {  
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
	                    if(event.getAction() == MotionEvent.ACTION_UP){  
	                    	MainActivity.SendCmd("NPM,4,STOP,0");
	                    	MainActivity.StopCmd=null;
	                    }   
	                    if(event.getAction() == MotionEvent.ACTION_DOWN){  
	                    	MainActivity.SendCmd("NPM,4,MOVJ,1");
	                    	MainActivity.StopCmd="NPM,4,STOP,0";
	                    }   
					return false;
				}   
	        }); 
	        
	        btn_y2n.setOnTouchListener(new OnTouchListener() {  
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
	                    if(event.getAction() == MotionEvent.ACTION_UP){  
	                    	MainActivity.SendCmd("NPM,4,STOP,0");
	                    	MainActivity.StopCmd=null;
	                    }   
	                    if(event.getAction() == MotionEvent.ACTION_DOWN){  
	                    	MainActivity.SendCmd("NPM,4,MOVJ,-1");
	                    	MainActivity.StopCmd="NPM,4,STOP,0";
	                    }   
					return false;
				}   
	        });
	        
	        //seek bar event
	        sb_speed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
	            /**
	             * 拖动条停止拖动的时候调用
	             */
	            @Override
	            public void onStopTrackingTouch(SeekBar seekBar) {
	            	int progress = seekBar.getProgress();//0-100:100:2000
	            	int speed = progress*20+100;
	            	MainActivity.SendCmd("NPM,1,SETV," + String.valueOf(speed));
	            	MainActivity.SendCmd("NPM,2,SETV," + String.valueOf(speed));
	            	MainActivity.SendCmd("NPM,3,SETV," + String.valueOf(speed));
	            	MainActivity.SendCmd("NPM,4,SETV," + String.valueOf(speed));
	            }
	            /**
	             * 拖动条开始拖动的时候调用
	             */
	            @Override
	            public void onStartTrackingTouch(SeekBar seekBar) {
	                
	            }
	            /**
	             * 拖动条进度改变的时候调用
	             */
	            @Override
	            public void onProgressChanged(SeekBar seekBar, int progress,
	                    boolean fromUser) {
	                
	            }
	        });
	        return view;  
	    }  
	      
	    @Override  
	    public void onActivityCreated(Bundle savedInstanceState) {  
	        super.onActivityCreated(savedInstanceState);  
	    }  
	      
	    @Override  
	    public void onPause() {  
	        super.onPause(); 
	        //timer.cancel();
	    }  
	      
	}
	
	//OBJ
	public static class MyFragment3 extends Fragment {  
	    //private Button btn;  
		public static Button btn_obj1p,btn_obj1n,btn_obj2p,btn_obj2n,btn_optp,btn_optn;  
		public static SeekBar sb_objspeed;
	    public static TextView txt_obj1, txt_obj2, txt_opt;
        public static Timer timer;
        public static double redy=0,obj1=0, obj2=0, opt=0;
        
        static final Handler handler = new Handler() {     
            @Override  
            public void handleMessage(Message msg) {   
                super.handleMessage(msg);   
                //handler处理消息  
                if(msg.what>0){   
                	txt_obj1.setText(Double.toString(obj1));
                	txt_obj2.setText(Double.toString(obj2));
                	txt_opt.setText(Double.toString(opt));
                }   
            }   
        };
        
        static void Update(){
        	txt_obj1.setText(Double.toString(obj1));
        	txt_obj2.setText(Double.toString(obj2));
        	txt_opt.setText(Double.toString(opt));
        }
        
	    @Override  
	    public void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	          
	    }  
	      
	      
	    @Override  
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
	            Bundle savedInstanceState) {  
	        View view = inflater.inflate(R.layout.fragment_thorlabsobj, container, false);  

	        btn_obj1p = (Button) view.findViewById(R.id.btn_obj1p);  
	        btn_obj1n = (Button) view.findViewById(R.id.btn_obj1n); 
	        btn_obj2p = (Button) view.findViewById(R.id.btn_obj2p);  
	        btn_obj2n = (Button) view.findViewById(R.id.btn_obj2n); 
	        btn_optp = (Button) view.findViewById(R.id.btn_optp);  
	        btn_optn = (Button) view.findViewById(R.id.btn_optn);
	        sb_objspeed = (SeekBar) view.findViewById(R.id.sb_objspeed);
	        txt_obj1 = (TextView) view.findViewById(R.id.txt_obj1);
	        txt_obj2 = (TextView) view.findViewById(R.id.txt_obj2);
	        txt_opt = (TextView) view.findViewById(R.id.txt_opt);
	        
	        timer=new Timer();
	        timer.schedule(new TimerTask(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Message msg = new Message();
					msg.what = 1;  
                    handler.sendMessage(msg);   
				}
	        	
	        }, 10, 200);
	        
	        btn_obj1p.setOnTouchListener(new OnTouchListener() {  
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
	                    if(event.getAction() == MotionEvent.ACTION_UP){  
	                    	MainActivity.SendCmd("THL,4,STOP,0");
	                    	MainActivity.StopCmd=null;
	                    }   
	                    if(event.getAction() == MotionEvent.ACTION_DOWN){  
	                    	MainActivity.SendCmd("THL,4,MOVV,1");
	                    	MainActivity.StopCmd="THL,4,STOP,0";
	                    }   
					return false;
				}   
	        }); 
	        
	        btn_obj1n.setOnTouchListener(new OnTouchListener() {  
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
	                    if(event.getAction() == MotionEvent.ACTION_UP){  
	                    	MainActivity.SendCmd("THL,4,STOP,0");
	                    	MainActivity.StopCmd=null;
	                    }   
	                    if(event.getAction() == MotionEvent.ACTION_DOWN){  
	                    	MainActivity.SendCmd("THL,4,MOVV,-1");
	                    	MainActivity.StopCmd="THL,4,STOP,0";
	                    }   
					return false;
				}   
	        }); 
	        btn_obj2p.setOnTouchListener(new OnTouchListener() {  
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
	                    if(event.getAction() == MotionEvent.ACTION_UP){  
	                    	MainActivity.SendCmd("THL,5,STOP,0");
	                    	MainActivity.StopCmd=null;
	                    }   
	                    if(event.getAction() == MotionEvent.ACTION_DOWN){  
	                    	MainActivity.SendCmd("THL,5,MOVV,1");
	                    	MainActivity.StopCmd="THL,5,STOP,0";
	                    }   
					return false;
				}   
	        }); 
	        
	        btn_obj2n.setOnTouchListener(new OnTouchListener() {  
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
	                    if(event.getAction() == MotionEvent.ACTION_UP){  
	                    	MainActivity.SendCmd("THL,5,STOP,0");
	                    	MainActivity.StopCmd=null;
	                    }   
	                    if(event.getAction() == MotionEvent.ACTION_DOWN){  
	                    	MainActivity.SendCmd("THL,5,MOVV,-1");
	                    	MainActivity.StopCmd="THL,5,STOP,0";
	                    }   
					return false;
				}   
	        });
	        
	        btn_optp.setOnTouchListener(new OnTouchListener() {  
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
	                    if(event.getAction() == MotionEvent.ACTION_UP){  
	                    	MainActivity.SendCmd("THL,6,STOP,0");
	                    	MainActivity.StopCmd=null;
	                    }   
	                    if(event.getAction() == MotionEvent.ACTION_DOWN){  
	                    	MainActivity.SendCmd("THL,6,MOVV,1");
	                    	MainActivity.StopCmd="THL,6,STOP,0";
	                    }   
					return false;
				}   
	        }); 
	        
	        btn_optn.setOnTouchListener(new OnTouchListener() {  
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
	                    if(event.getAction() == MotionEvent.ACTION_UP){  
	                    	MainActivity.SendCmd("THL,6,STOP,0");
	                    	MainActivity.StopCmd=null;
	                    }   
	                    if(event.getAction() == MotionEvent.ACTION_DOWN){  
	                    	MainActivity.SendCmd("THL,6,MOVV,-1");
	                    	MainActivity.StopCmd="THL,6,STOP,0";
	                    }   
					return false;
				}   
	        });
	        
	      //seek bar event
	        sb_objspeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
	            /**
	             * 拖动条停止拖动的时候调用
	             */
	            @Override
	            public void onStopTrackingTouch(SeekBar seekBar) {
	            	float progress = seekBar.getProgress();//0-100:0:2
	            	float speed = progress/50;
	            	MainActivity.SendCmd("THL,4,SETV," + String.valueOf(speed));
	            	MainActivity.SendCmd("THL,5,SETV," + String.valueOf(speed));
	            	MainActivity.SendCmd("THL,6,SETV," + String.valueOf(speed));
	            }
	            /**
	             * 拖动条开始拖动的时候调用
	             */
	            @Override
	            public void onStartTrackingTouch(SeekBar seekBar) {
	                
	            }
	            /**
	             * 拖动条进度改变的时候调用
	             */
	            @Override
	            public void onProgressChanged(SeekBar seekBar, int progress,
	                    boolean fromUser) {
	                
	            }
	        });
	        
	        return view;  
	    }  
	      
	    @Override  
	    public void onActivityCreated(Bundle savedInstanceState) {  
	        super.onActivityCreated(savedInstanceState);  
	    }  
	      
	    @Override  
	    public void onPause() {  
	        super.onPause();  
	    }  
	      
	}
	
	//PIEZO
	public static class MyFragment4 extends Fragment {  
	    //private Button btn; 
		public static Button btn_pip,btn_pin;  
		public static SeekBar sb_pistep;
		public static TextView txt_pistep, txt_pi;
	    public static double step=0;
	    public static Timer timer;
        public static double redy=0,pi=0;
        
        static final Handler handler = new Handler() {     
            @Override  
            public void handleMessage(Message msg) {   
                super.handleMessage(msg);   
                //handler处理消息  
                if(msg.what>0){   
                	txt_pi.setText(Double.toString(pi));
                	double progress = sb_pistep.getProgress();//0-100:0:10
	            	double speed = progress/10.0;
	            	txt_pistep.setText(Double.toString(speed));
                }   
            }   
        };
        
        static void Update(){
        	txt_pi.setText(Double.toString(pi));
        }
        
	    @Override  
	    public void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState); 
	    } 
	     
	      
	    @Override  
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
	            Bundle savedInstanceState) {  
	        View view = inflater.inflate(R.layout.fragment_piz, container, false);  
	        
	        btn_pip = (Button) view.findViewById(R.id.btn_pip);  
	        btn_pin = (Button) view.findViewById(R.id.btn_pin);
	        sb_pistep = (SeekBar) view.findViewById(R.id.sb_pistep);
	        txt_pistep = (TextView) view.findViewById(R.id.txt_pistep);
	        txt_pi = (TextView) view.findViewById(R.id.txt_pi);
	        
	        step = sb_pistep.getProgress()/10.0;
	        
	        timer=new Timer();
	        timer.schedule(new TimerTask(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Message msg = new Message();
					msg.what = 1;  
                    handler.sendMessage(msg);   
				}
	        	
	        }, 10, 100);
	        
	        btn_pip.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					MainActivity.SendCmd("PIZ,1,MOVR," + String.valueOf(step));
				}   
	        }); 
	        
	        btn_pin.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					MainActivity.SendCmd("PIZ,1,MOVR,-" + String.valueOf(step));
				}   
	        });
	        
	      //seek bar event
	        sb_pistep.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
	            /**
	             * 拖动条停止拖动的时候调用
	             */
	            @Override
	            public void onStopTrackingTouch(SeekBar seekBar) {
	            	double progress = seekBar.getProgress();//0-100:0:10
	            	double speed = progress/10.0;
	            	txt_pistep.setText(Double.toString(speed));
	            	step = speed;
	            }
	            /**
	             * 拖动条开始拖动的时候调用
	             */
	            @Override
	            public void onStartTrackingTouch(SeekBar seekBar) {
	                
	            }
	            /**
	             * 拖动条进度改变的时候调用
	             */
	            @Override
	            public void onProgressChanged(SeekBar seekBar, int progress,
	                    boolean fromUser) {
	            	double speed = progress/10.0;
	            	txt_pistep.setText(Double.toString(speed));
	            }
	        });
	        
	        return view;  
	    }  
	      
	    @Override  
	    public void onActivityCreated(Bundle savedInstanceState) {  
	        super.onActivityCreated(savedInstanceState);  
	    }  
	      
	    @Override  
	    public void onPause() {  
	        super.onPause();  
	    }  
	      
	}
	
	//Settings
	public static class MyFragment5 extends Fragment {  
	    private Button btn_conn, btn_disconn;  
	    private TextView txt_host, txt_hostport;
	    @Override  
	    public void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	          
	    }  
	      
	      
	    @Override  
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
	            Bundle savedInstanceState) {  
	        View view = inflater.inflate(R.layout.fragment_settings, container, false);  
	        btn_conn = (Button) view.findViewById(R.id.btn_connect);
	        btn_disconn = (Button) view.findViewById(R.id.btn_disconn);
	        txt_host = (TextView) view.findViewById(R.id.txt_host);
	        txt_hostport = (TextView) view.findViewById(R.id.txt_host_port);
	        btn_conn.setOnClickListener(new OnClickListener(){
	        	@Override  
	            public void onClick(View v) {  
	        		MainActivity.Connect(txt_host.getText().toString(), Integer.parseInt(txt_hostport.getText().toString()));
	            } 
	        });
	        btn_disconn.setOnClickListener(new OnClickListener(){
	        	@Override  
	            public void onClick(View v) {  
	        		MainActivity.DisConnect();
	            } 
	        });
	        
	        return view;  
	    }  
	      
	    @Override  
	    public void onActivityCreated(Bundle savedInstanceState) {  
	        super.onActivityCreated(savedInstanceState);  
	    }  
	      
	    @Override  
	    public void onPause() {  
	        super.onPause();  
	    }  
	      
	}

	//
	public static boolean Connect(String address, int port){
		
		mHostAdd = address;
		mHostPort = port;
		try {
			mSocket = new Socket(mHostAdd, mHostPort);
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
>>>>>>> origin/glsh
}
