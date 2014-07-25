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

public class Fragment_Obj extends Fragment {
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
