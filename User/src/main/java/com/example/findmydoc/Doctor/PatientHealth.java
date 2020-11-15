package com.example.findmydoc.Doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.findmydoc.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;

public class PatientHealth extends AppCompatActivity {

    LineChart mLineChart;
    private static final String[] DAYS = { "11 Nov", "12 Nov", "13 Nov", "14 Nov","15 Thu"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_health);
        mLineChart=findViewById(R.id.chart);
        createTimeSeries();
    }
    public void createTimeSeries()
    {
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(20, 100));
        entries.add(new Entry(30, 200));
        entries.add(new Entry(40, 20));
        entries.add(new Entry(50, 400));
        LineDataSet dataSet = new LineDataSet(entries, "Oxygen Level");
        LineData data = new LineData(dataSet);

        mLineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        XAxis xAxis=mLineChart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int index=(int) value;
                if(index>=0&&index<DAYS.length){
                    return DAYS[(int) value];
                }
                else{
                    return "none";
                }

            }
        });
        mLineChart.getDescription().setEnabled(false);
        mLineChart.getAxisRight().setEnabled(false);
        mLineChart.setData(data);
    }
}