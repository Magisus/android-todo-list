package edu.lclark.mdreyer.todolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.day_header_text)
    TextView dayHeader;

    @Bind(R.id.task_edit)
    EditText taskEdit;

    @Bind(R.id.previous_day_button)
    Button previousDayButton;

    @Bind(R.id.save_button)
    Button saveButton;

    @Bind(R.id.next_day_button)
    Button nextDayButton;

    private WeekdayCycler weekdays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        weekdays = new WeekdayCycler(getResources().getStringArray(R.array.weekdays));
        setViewTexts();
    }

    @OnClick(R.id.save_button)
    public void saveTask() {
        taskEdit.setText("Task Saved!");
    }

    @OnEditorAction(R.id.task_edit)
    public boolean saveOnDone() {
        saveTask();
        return true;
    }

    @OnClick(R.id.next_day_button)
    public void goToNextDay() {
        weekdays.goToNextDay();
        setViewTexts();
    }

    @OnClick(R.id.previous_day_button)
    public void goToPreviousDay() {
        weekdays.goToPreviousDay();
        setViewTexts();
    }

    private void setViewTexts() {
        dayHeader.setText(weekdays.getCurrentDay());
        previousDayButton.setText(weekdays.getPreviousDay());
        nextDayButton.setText(weekdays.getNextDay());

        // TODO: load from SharedPreferences if value has been saved
        taskEdit.setText(getString(R.string.task_edit_hint, weekdays.getCurrentDay()));
    }
}
