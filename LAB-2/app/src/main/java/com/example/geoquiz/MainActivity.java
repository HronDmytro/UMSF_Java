package com.example.geoquiz;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;
    private TextView mQuestionText; // Змінено назву TextView на mQuestionText
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private TextView mAnswerText;
    private int score = 0;


    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_1, true),
            new Question(R.string.question_2, true),
            new Question(R.string.question_3, false),
            new Question(R.string.question_4, false),
            new Question(R.string.question_5, true),
            new Question(R.string.question_6, false)
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionText = findViewById(R.id.questionText); // Initialize mQuestionText
        mAnswerText = findViewById(R.id.questionText);
        updateQuestion();
        addListenerOnClickButtonTrueFalse();
    }


    public void addListenerOnClickButtonTrueFalse() {
        mTrueButton = (Button) findViewById(R.id.buttonTrue);
        mFalseButton = (Button) findViewById(R.id.buttonFalse);
        mNextButton = (Button) findViewById(R.id.buttonNext);
        mPrevButton = (Button) findViewById(R.id.buttonPrev);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex - 1 + mQuestionBank.length) % mQuestionBank.length;
                updateQuestion();
            }
        });
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionText.setText(question);
        displayScore(); // Display the score after updating the question
    }


    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.toast_true;
            mAnswerText.setTextColor(Color.GREEN);
            score++; // Increment the score for correct answers
        } else {
            messageResId = R.string.toast_false;
            mAnswerText.setTextColor(Color.RED);
        }

        mAnswerText.setText(messageResId);
        mAnswerText.setVisibility(View.VISIBLE);

        Toast toast = Toast.makeText(this, messageResId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
    }

    private void displayScore() {
        Toast.makeText(this, "Score: " + score, Toast.LENGTH_SHORT).show();
    }


}
