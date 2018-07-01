package com.example.android.quizapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the Submit button is clicked.
     */
    public void startEvaluation(View view) {
        String[] answers = evaluateGui();

        int result = evaluateQuiz(answers);

        toastResult(result);
    }

    /**
     * This method is Evaluate Test Scores.
     */
    public String[] evaluateGui() {
        String[] ret = new String[5];

        EditText editTextQuestionOne = findViewById(R.id.question_one);

        CheckBox checkBoxQuestionTwoAnswerOne = findViewById(R.id.question_two_answer_one);
        CheckBox checkBoxQuestionTwoAnswerTwo = findViewById(R.id.question_two_answer_two);
        CheckBox checkBoxQuestionTwoAswerThree = findViewById(R.id.question_two_answer_three);

        Boolean answerQuestionTwo = false;
        if (checkBoxQuestionTwoAnswerOne.isChecked() == true && checkBoxQuestionTwoAnswerTwo.isChecked() == false && checkBoxQuestionTwoAswerThree.isChecked() == true) {
            answerQuestionTwo = true;
        }

        CheckBox checkBoxQuestionFourAnswerOne = findViewById(R.id.question_four_answer_one);
        CheckBox checkBoxQuestionFourAnswerTwo = findViewById(R.id.question_four_answer_two);
        CheckBox checkBoxQuestionFourAnswerThree = findViewById(R.id.question_four_answer_three);

        Boolean answerQuestionFour = false;


        Boolean questionfouranswerone = checkBoxQuestionFourAnswerOne.isChecked();
        Boolean questionfouranswertwo = checkBoxQuestionFourAnswerTwo.isChecked();
        Boolean questionfouranswerthree = checkBoxQuestionFourAnswerThree.isChecked();


        if (questionfouranswerone == false && questionfouranswertwo == false && questionfouranswerthree == true) {
            answerQuestionFour = true;
        }

        ret[0] = editTextQuestionOne.getText().toString().toLowerCase();
        ret[1] = Boolean.toString(answerQuestionTwo);
        ret[2] = evaluateRadioGroup(R.id.question_Three).toLowerCase();
        ret[3] = Boolean.toString(answerQuestionFour);
        ret[4] = evaluateRadioGroup(R.id.question_Five).toLowerCase();

        return ret;
    }

    public int evaluateQuiz(String[] answers) {
        int result = 0;
        String[] correctAnswers = {"sun", "barack obama", "true", "december 25th", "true"};

        for (int i = 0; i < correctAnswers.length; i++) {
            if (answers[i].equals(correctAnswers[i])) {
                result++;
            }
        }

        return result;
    }

    /**
     * This method shows the level of candidate performance.
     */
    public void toastResult(int result) {
        String message = result + " out of 5. ";

        if (result == 0) {
            message += "Poor Performance.";
        } else if (result == 1) {
            message += "You can do better.";
        } else if (result == 2) {
            message += "Nice! Keep workng harder.";
        } else if (result == 3) {
            message += "Very nice! More effort.";
        } else if (result == 4) {
            message += "Excellent job!!";
        } else if (result == 5) {
            message += "Absolutely excellent!";
        }

        // Show an scores message as a toast
        Toast reportResult = Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT);
        reportResult.show();
    }

    private String evaluateRadioGroup(int id) {
        RadioGroup radioGroupQuestion;
        RadioButton radioButtonQuestion;

        radioGroupQuestion = findViewById(id);

        int radioButtonId = radioGroupQuestion.getCheckedRadioButtonId();
        radioButtonQuestion = findViewById(radioButtonId);

        if (radioButtonQuestion == null) {
            return "";
        }

        return (String)radioButtonQuestion.getText();
    }

    }






