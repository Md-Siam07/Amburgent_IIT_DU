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
import java.util.Random;

public class PatientHealth extends AppCompatActivity {

    LineChart mLineChart,tmp;
    Random random;
    private static final String[] DAYS = { "1 Nov", "2 Nov", "3 Nov", "4 Nov","5 Thu", "6 Nov", "7 Nov", "8 Nov", "9 Nov","10 Thu", "11 Nov", "12 Nov", "13 Nov", "14 Nov","15 Thu"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_health);
        mLineChart=findViewById(R.id.chart);
        tmp=findViewById(R.id.temperature);
        random=new Random();
        createTimeSeries();
    }
    public void createTimeSeries()
    {
        ArrayList<Entry> entries = new ArrayList<>();
        for(int i=0;i<15;i++){
            entries.add(new Entry(i, 20+random.nextInt(98)));
        }
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




        entries = new ArrayList<>();
        for(int i=0;i<15;i++){
            entries.add(new Entry(i, 80+random.nextInt(104)));
        }
        dataSet = new LineDataSet(entries, "Temperature");
        data = new LineData(dataSet);

        tmp.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis=tmp.getXAxis();
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
        tmp.getDescription().setEnabled(false);
        tmp.getAxisRight().setEnabled(false);
        tmp.setData(data);
    }
}