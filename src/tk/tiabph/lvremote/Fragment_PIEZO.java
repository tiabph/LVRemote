package tk.tiabph.lvremote;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class Fragment_PIEZO extends Fragment {
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
