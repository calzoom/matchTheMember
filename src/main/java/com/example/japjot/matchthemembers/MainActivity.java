package com.example.japjot.matchthemembers;

import android.content.ContentProviderOperation;
import android.content.Intent;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

//    private static final long startTime = 5000;
//
//    private TextView mTextViewCountdown;
//
//    private CountDownTimer mCountDownTimer;


    int score = 0;

    ImageView imageView;
//    Button button;
    Button button1;
    Button button2;
    Button button3;
    Button button4;

    Random r;

    String correctMember;

    String[] members = {"Daniel Andrews", "Nikhar Arora", "Tiger Chen", "Xin Yi Chen", "Julie Deng", "Radhika Dhomse", "Kaden Dippe", "Angela Dong", "Zach Govani", "Shubham Gupta", "Suyash Gupta", "Joey Hejna", "Cody Hsieh", "Stephen Jayakar", "Aneesh Jindal", "Mohit Katyal", "Mudabbir Khan", "Akkshay Khoslaa", "Justin Kim", "Eric Kong", "Abhinav Koppu", "Srujay Korlakunta", "Ayush Kumar", "Shiv Kushwah", "Leon Kwak", "Sahil Lamba", "Young Lin", "William Lu", "Louie McConnell", "Max Miranda", "Will Oakley", "Noah Pepper", "Samanvi Rai", "Krishnan Rajiyah", "Vidya Ravikumar", "Shreya Reddy", "Amy Shen", "Wilbur Shi", "Sumukh Shivakumar", "Fang Shuo", "Japjot Singh", "Victor Sun", "Sarah Tang", "Kanyes Thaker", "Aayush Tyagi", "Levi Walsh", "Carol Wang", "Sharie Wang", "Ethan Wong", "Natasha Wong", "Aditya Yadav", "Candice Ye", "Vineeth Yeevani", "Jeffrey Zhang"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);

        r = new Random();
//        button = (Button) findViewById(R.id.getbutton);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        imageView.setOnClickListener(this);

        setUp();
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                int idxRandomPerson = r.nextInt(members.length);
//                String theMember = members[idxRandomPerson];
//
//                // new member and then display that member's pic
////                Member test = new Member("Noah Pepper", getApplicationContext());
//
//                Member member = new Member(theMember, getApplicationContext());
//                imageView.setImageResource(member.getImage());
//
//            }
//        });
//        mTextViewCountdown = findViewById(R.id.time);

    }

    private void setUp(){
        HashSet<String> set = new HashSet<>();

        int correctMemberImage = r.nextInt(members.length);
        correctMember = members[correctMemberImage];
        set.add(correctMember);

        while(set.size() < 4) {
            int incorrectMember1Image = r.nextInt(members.length);
            String incorrectMember1 = members[incorrectMember1Image];
            set.add(incorrectMember1);
        }

        List<String> memberButtons = new ArrayList<>();

        for(String s: set) {
            memberButtons.add(s);
        }

        button1.setText(memberButtons.get(r.nextInt(4)));
        memberButtons.remove(button1.getText());
        button2.setText(memberButtons.get(r.nextInt(3)));
        memberButtons.remove(button2.getText());
        button3.setText(memberButtons.get(r.nextInt(2)));
        memberButtons.remove(button3.getText());
        button4.setText(memberButtons.get(r.nextInt(1)));
        memberButtons.remove(button4.getText());

        Member correctMemberImagez = new Member(correctMember, getApplicationContext());
        imageView.setImageResource(correctMemberImagez.getImage());

        ((TextView) findViewById(R.id.textViewScoreNumber)).setText(String.valueOf(score));
//        resetTimer();
//
//        startTimer();
    }

//    private void startTimer(){
//        mCountDownTimer = new CountDownTimer(startTime, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                updateCounDownText(millisUntilFinished);
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        }.start();
//    }
//
//    private void updateCounDownText(long m){
//        mTextViewCountdown.setText(String.valueOf(m/1000));
//    }
//
//    private void resetTimer(){
//        updateCounDownText(startTime);
//    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button1 || v.getId() == R.id.button2 || v.getId() == R.id.button3 || v.getId() == R.id.button4){
            if(((Button) v).getText().equals(correctMember)){
                score++;
            }
            else {
                Toast.makeText(getApplicationContext(), "Oops! That was actually: " + correctMember,
                        Toast.LENGTH_LONG).show();
            }
        }

        else if(v.getId() == R.id.imageView){
            Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
            intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
            intent.putExtra(ContactsContract.Intents.Insert.NAME, correctMember);
            startActivity(intent);

        }

        setUp();
    }
}


