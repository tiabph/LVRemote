package tk.tiabph.lvremote;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class Fragment_Sample extends Fragment {
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
