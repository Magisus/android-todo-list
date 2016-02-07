package edu.lclark.mdreyer.todolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnItemSelected;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.day_header_spinner)
    Spinner dayHeader;

    @Bind(R.id.task_edit)
    EditText taskEdit;

    @Bind(R.id.previous_day_button)
    Button previousDayButton;

    @Bind(R.id.next_day_button)
    Button nextDayButton;

    private WeekdayCycler weekdays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        taskEdit.setLines(100);
        taskEdit.setHorizontallyScrolling(false);

        weekdays = new WeekdayCycler(getResources().getStringArray(R.array.weekdays));
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.weekdays, R.layout.day_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dayHeader.setAdapter(adapter);

        setViewTexts();
    }

    @OnClick(R.id.save_button)
    public void saveTask() {
        getPreferences(MODE_PRIVATE).edit().putString(weekdays.getCurrentDay(),
                taskEdit.getText().toString()).apply();
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

    @OnItemSelected(R.id.day_header_spinner)
    public void goToDay() {
        weekdays.goToDay(dayHeader.getSelectedItemPosition());
        setViewTexts();
    }

    private void setViewTexts() {
        dayHeader.setSelection(weekdays.getCurrentDayIndex());
        previousDayButton.setText(weekdays.getPreviousDay());
        nextDayButton.setText(weekdays.getNextDay());

        String task = getPreferences(MODE_PRIVATE).getString(weekdays.getCurrentDay(),
                "");
        if (task.equals("")) {
            taskEdit.setText("");
            taskEdit.setHint(getString(R.string.task_edit_hint, weekdays.getCurrentDay()));
        } else {
            taskEdit.setText(task);
        }
    }
}
