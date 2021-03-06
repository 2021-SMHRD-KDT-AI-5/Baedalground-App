package com.example.a3project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class commuAdapter extends BaseAdapter {
    private  Context context;
    private LayoutInflater inf;
    private  int layout;
    private ArrayList<commuVO> data;

    public  commuAdapter(Context context, int layout, ArrayList<commuVO> data){
        this.context = context;
        this.inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout =layout;
        this.data = data;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

//        final int pos = i;
//        final Context context = parent.getContext();
        //★★★★★
        // getView는 한줄을 만들때마다 호출!

        // 1. position => 항목의 번호
        // 2. convertView => 이전에 만들어진 View(xml을 눈에보이는 평태로 바꾼 것)
        // 3. parent => 모든뷰를 담고 있는 ListView

        // 첫번째 항목을 만들려고 한다면 = 첫번째 연락처를 띄우려고 한다면!!
        if(convertView ==null){//position ==0
            convertView =inf.inflate(layout, parent, false);
        }// xml -> 눈에 보이는 형태(View)로 변환

        //ArrayList에 들어있는 Data로 한줄한줄 꾸며주기

        // 템플릿에 들어있는 textView 찾아오기
        // 글제목
        TextView tv_name = convertView.findViewById(R.id.tv_title);
        tv_name.setText(data.get(i).getTitle());
        //식당
        TextView tv_restaurant = convertView.findViewById(R.id.edt_restaurant);
        tv_restaurant.setText("식당: "+data.get(i).getRestaurant());
        //시간
        TextView tv_time = convertView.findViewById(R.id.edt_time);
        tv_time.setText("주문시간: "+data.get(i).getTime());
        //최대인원
        TextView tv_max = convertView.findViewById(R.id.tv_min);
        tv_max.setText("인원: "+data.get(i).getMin());
        //현재인원
        TextView tv_current = convertView.findViewById(R.id.tv_current);
        tv_current.setText("현재인원: "+ "null");
        //작성자
        TextView tv_host_nick = convertView.findViewById(R.id.tv_host_nick);
        tv_host_nick.setText("작성자: "+data.get(i).gethost_id());
        //작성자 위치
        TextView tv_list_location = convertView.findViewById(R.id.tv_list_location);
        tv_list_location.setText("작성자위치: "+ "null");


        // 항목을 하나하나 추가할 떄마다 findViewById를 하면 Android가 힘겨워할 수 있음
        // => 첫번째 convertView 만들대만 findViewById를 하고 다음부터는 저장돤 View를 꺼내쓰다
        // => ViewHolder 패턴

//        LinearLayout cmdArea = convertView.findViewById(R.id.cmdArea);
//        cmdArea.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent it_con = new Intent(Activity., listclick.class);
//                startActivity(it_con);
//            }
//        });

        return convertView;
}
}
