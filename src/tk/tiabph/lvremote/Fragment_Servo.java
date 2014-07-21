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
import android.widget.ToggleButton;

public class Fragment_Servo extends Fragment {
	public static ToggleButton btn_srv1,btn_srv2;  
	public static SeekBar sb_s1p, sb_s1n,sb_s2p,sb_s2n;
    public static int redy=0,s1p=2000,s1n=1000,s2p=2000,s2n=1000;
    
     
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState); 
    } 
     
      
    @Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState) {  
        View view = inflater.inflate(R.layout.fragment_servo, container, false);  
        
        btn_srv1 = (ToggleButton) view.findViewById(R.id.tbtn_srv1); 
        btn_srv2 = (ToggleButton) view.findViewById(R.id.tbtn_srv2); 
        sb_s1p = (SeekBar) view.findViewById(R.id.sb_srv1p);
        sb_s1n = (SeekBar) view.findViewById(R.id.sb_srv1n);
        sb_s2p = (SeekBar) view.findViewById(R.id.sb_srv2p);
        sb_s2n = (SeekBar) view.findViewById(R.id.sb_srv2n);
        
        sb_s1p.setProgress((s1p-500)/20);
        sb_s1n.setProgress((s1n-500)/20);
        sb_s2p.setProgress((s2p-500)/20);
        sb_s2n.setProgress((s2n-500)/20);
        
        
        btn_srv1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(btn_srv1.isChecked()){
					MainActivity.SendCmd("USR,1,MOVP," + String.valueOf(s1p));
				}else{
					MainActivity.SendCmd("USR,1,MOVP," + String.valueOf(s1n));
				}
			}   
        }); 
        
        btn_srv2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(btn_srv2.isChecked()){
					MainActivity.SendCmd("USR,2,MOVP," + String.valueOf(s2p));
				}else{
					MainActivity.SendCmd("USR,2,MOVP," + String.valueOf(s2n));
				}
			}   
        });
        
      //seek bar event
        sb_s1p.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /**
             * 拖动条停止拖动的时候调用
             */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            	double progress = seekBar.getProgress();//0-100:0:10
            	double speed = progress*20.0+500;
            	s1p = (int) speed;
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
            	double speed = progress*20.0+500;
            }
        });
        
        sb_s1n.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /**
             * 拖动条停止拖动的时候调用
             */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            	double progress = seekBar.getProgress();//0-100:0:10
            	double speed = progress*20.0+500;
            	s1n = (int) speed;
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
            	double speed = progress*20.0+500;
            }
        });
        sb_s2p.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /**
             * 拖动条停止拖动的时候调用
             */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            	double progress = seekBar.getProgress();//0-100:0:10
            	double speed = progress*20.0+500;
            	s2p = (int) speed;
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
            	double speed = progress*20.0+500;
            }
        });
        
        sb_s2n.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /**
             * 拖动条停止拖动的时候调用
             */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            	double progress = seekBar.getProgress();//0-100:0:10
            	double speed = progress*20.0+500;
            	s2n = (int) speed;
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
            	double speed = progress*20.0+500;
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
