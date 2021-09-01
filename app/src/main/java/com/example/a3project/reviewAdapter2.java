package com.example.a3project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class reviewAdapter2 extends BaseAdapter {
    private  Context context;
    private LayoutInflater inf;
    private  int layout;
    private ArrayList<reviewVO2> review_data;



    public reviewAdapter2(Context context, int layout, ArrayList<reviewVO2> review_data){
        this.context = context;
        this.inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout =layout;
        this.review_data = review_data;

    }

    @Override
    public int getCount() {
        return review_data.size();
    }

    @Override
    public Object getItem(int i) {
        return review_data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
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



        TextView tv_reviewNick = convertView.findViewById(R.id.tv_reviewnick);
        tv_reviewNick.setText(review_data.get(i).getMemberId());

        //맛
        TextView tv_taste = convertView.findViewById(R.id.tv_taste);
        float taste = review_data.get(i).getTasteReview();
        String taste2 = String.valueOf(taste);
        tv_taste.setText(taste2);


        TextView tv_amount = convertView.findViewById(R.id.tv_amount);
        float amount = review_data.get(i).getAmountReview();
        String amount2 = String.valueOf(amount);
        tv_amount.setText(amount2);

        TextView tv_speed = convertView.findViewById(R.id.tv_speed);
        float speed = review_data.get(i).getDeliReview();
        String speed2 = String.valueOf(speed);
        tv_speed.setText(speed2);

//        ArrayList<Integer> avgs = new ArrayList<>();
//        avgs.add()
        //양
//        TextView tv_amount = convertView.findViewById(R.id.tv_amount);
//        tv_amount.setText(review_data.get(i).getAmount());
//        //배달속도
//        TextView tv_speed = convertView.findViewById(R.id.tv_speed);
//        tv_speed.setText(review_data.get(i).getSpeed());
        //리뷰
        TextView tv_review = convertView.findViewById(R.id.tv_review);
        tv_review.setText(" "+review_data.get(i).getReviewContents());
        //아이디

        //프로필사진


        //평균별점
        float avg = (taste+amount+speed)/3;
        String avg2 = String.valueOf(avg);
        Log.v("평균", avg2);


        //별그림 세팅
        ImageView star1 = convertView.findViewById(R.id.star1);
        star1.setImageResource(R.drawable.star);
        ImageView star2 = convertView.findViewById(R.id.star2);
        star2.setImageResource(R.drawable.star);
        ImageView star3 = convertView.findViewById(R.id.star3);
        star3.setImageResource(R.drawable.star);
        RatingBar rat_star= convertView.findViewById(R.id.rat_star);
        rat_star.setRating(avg);




        return convertView;
    }
}
