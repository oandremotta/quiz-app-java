package com.example.truecitzenquiz;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button falseButton;
    private Button trueButton;
    private TextView questionTextView;
    private int currentQuestion = 0;

    private Question[] questions = new Question[]{
           new Question(R.string.question_declaration, true),
            new Question(R.string.question_animal_kingdom, true),
            new Question(R.string.question_planet_earth, false),
            new Question(R.string.question_human_body, true),
            new Question(R.string.question_history, false),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        falseButton = findViewById(R.id.false_button);
        trueButton = findViewById(R.id.true_button);
        questionTextView = findViewById(R.id.answer_text_view);

        falseButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.true_button) {
            checkAnswer(true);
        }
        if (id == R.id.false_button) {
            checkAnswer(false);
        }

        updateQuestion();
    }

    private void updateQuestion(){
        currentQuestion = (currentQuestion + 1 ) % questions.length;
        questionTextView.setText(questions[currentQuestion].getAnswerResId());
    }

    private void checkAnswer(boolean userChooseCorrect){
        boolean answerIsTrue = questions[currentQuestion].isAnswerTrue();
        int toastedMessage = 0;
        if(userChooseCorrect == answerIsTrue){
            toastedMessage = R.string.correct_answer;
        } else {
            toastedMessage = R.string.wrong_answer;
        }

        Toast.makeText(MainActivity.this, toastedMessage, Toast.LENGTH_LONG).show();
    }
}