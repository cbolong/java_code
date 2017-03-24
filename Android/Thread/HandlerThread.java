package com.example.thread_demo;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends Activity {
    HandlerThread ht=new HandlerThread("name");    //宣告在背景工作的特約工人,"name"為工人名子
    Handler h,uih;                                //宣告2個經紀人,uih為UI Thread的經紀人
    Thread t;                                    //普通的執行緒工人
    TextView t01;
    Button b01,cancel;
    int ht_i=1,t_i=10;            //用來計數HandlerThread與Thread兩位工人各別顯示的行數
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();                                //連結控制項
        t01.setText("Test Start:\n");
        uih=new Handler();                    //uih為UI Thread的經紀人可以直接使用=new Handler()獲取UI Thread,之後將能透過經紀人指派UI工人做事
        ht.start();                            //先讓特約工人開始執行(如果沒有執行不能交給經紀人)
        h=new Handler(ht.getLooper());        //把特約工人交給經紀人,找到特約工人的經紀人才能開始派遣工作
        //作為HandlerThread添加工作的按鈕
        b01.setOnClickListener(new OnClickListener() {    
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                h.post(r0);                    //經紀人指派r0工作給工人
            }
        });
        //作為註銷HandlerThread執行緒指定任務的按鈕
        cancel.setOnClickListener(new OnClickListener() {    
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(h!=null)                    //用來取消排在任務列內的所有r0任務(如已經開始執行該任務,則無法取消)
                    h.removeCallbacks(r0);
                t01.append("註銷HandlerThread內指定的任務\n");
            }
        });
        //讓臨時工執行緒派遣工作
        t=new Thread(new Runnable(){        
            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    //顯示10次後就能閃人
                    while(t_i>0){            //判斷是否使用Thread顯示字串
                        runOnUiThread(new Runnable() {        //可以使用此方法臨時交給UI做顯示
                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                t01.append("Thread臨時工倒數第"+t_i+"次顯示\n");
                                t_i--;
                            }
                        });
                        Thread.sleep(5000);                //每隔5秒顯示一次
                    }
                    runOnUiThread(new Runnable() {        //可以使用此方法臨時交給UI做顯示
                        public void run(){     
                            t01.append("Thread結束工作閃人\n");
                        }     
                    });
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        t.start();                    //Thread開始執行
    }
    //連結控制項
    public void init(){            
        t01=(TextView) this.findViewById(R.id.t01);
        b01=(Button) this.findViewById(R.id.b01);
        cancel=(Button) this.findViewById(R.id.cancel);
    }
    //設置r0工作,每秒在t01添加一行(5次)
    private Runnable r0=new Runnable(){        
        //工作內容寫進run(),繁雜的工作可以給另一個特約工人做
        public void run(){                    
            try{
                runOnUiThread(new Runnable() {    //可以臨時交給UI做顯示 
                    public void run(){     
                        t01.append("特約工人接到工作開始執行:\n");
                    }     
                }); 
                Thread.sleep(1000);            //每隔1秒顯示一次
                for(int i=0;i<5;i++){
                    uih.post(ui0);            //給UI Thread經紀人指派ui0工作,才不會崩潰(因android工具須給UI Thread工人做)
                    Thread.sleep(1000);        //每隔1秒顯示一次
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    };
    //設置ui0工作,替特約工人顯示TextView
    private Runnable ui0=new Runnable(){    
        @Override
        public void run() {
            // TODO Auto-generated method stub
            t01.append("特約工人執行第"+ht_i+"次\n");
            ht_i++;
        }
    };
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(ht!=null)                //關閉該執行緒(需等待目前的任務執行完畢才會關閉)
            ht.quit();
    }
}
