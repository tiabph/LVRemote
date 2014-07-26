package tk.tiabph.lvremote;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Fragment_Settings extends Fragment {
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
