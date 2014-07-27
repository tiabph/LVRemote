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

public class Fragment_Mirror extends Fragment {
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
