package com.example.findmydoc.Doctor;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.findmydoc.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;

public class DoctorHomePanel extends Fragment {


    private static final int MAX_X_VALUE =4;
    private static final int MAX_Y_VALUE = 30;
    private static final int MIN_Y_VALUE = 5;
    private static final int GROUPS = 4;
    private static final String TEST = "Test";
    private static final String INFECT = "Infected";
    private static final String CURE = "Cure";
    private static final String DEATH = "Death";
    private static final float BAR_SPACE = 0.05f;
    private static final float BAR_WIDTH = 0.14f;
    private BarChart chart;
    private static final String[] DAYS = { "SUN", "MON", "TUE", "WED","Thu"};
    PieChart pieChart;
    PieData pieData;
    PieDataSet pieDataSet;
    ArrayList pieEntries;
    ArrayList PieEntryLabels;
    public DoctorHomePanel() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.doctor_home, container, false);
        chart = view.findViewById(R.id.fragment_verticalbarchart_chart);
        pieChart = view.findViewById(R.id.pieChart);
        BarData data = createChartData();
        configureChartAppearance();
        prepareChartData(data);
        createPieChart();
        return view;
    }
    private void configureChartAppearance() {
        chart.setPinchZoom(false);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);

        chart.getDescription().setEnabled(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(true);
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

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f);
        Legend l=chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        chart.getAxisRight().setEnabled(false);
        chart.getAxisLeft().setEnabled(false);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getXAxis().setAxisMinimum(0);
        chart.getXAxis().setAxisMaximum(MAX_X_VALUE);
    }

    public void createPieChart()
    {

        getEntries();
        pieDataSet = new PieDataSet(pieEntries,"");
        pieData = new PieData(pieDataSet);
        Legend l=pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        pieChart.setData(pieData);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS[3],ColorTemplate.MATERIAL_COLORS[1],ColorTemplate.MATERIAL_COLORS[0],ColorTemplate.MATERIAL_COLORS[2]);
        pieDataSet.setSliceSpace(2f);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(10f);
        pieDataSet.setSliceSpace(1f);
        pieChart.animate();


    }
    private void getEntries() {
        pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(2, "Test"));
        pieEntries.add(new PieEntry(4, "Infected"));
        pieEntries.add(new PieEntry(6, "Cure"));
        pieEntries.add(new PieEntry(8, "Death"));
    }

    private BarData createChartData() {

        ArrayList<BarEntry> values1 = new ArrayList<>();
        ArrayList<BarEntry> values2 = new ArrayList<>();
        ArrayList<BarEntry> values3 = new ArrayList<>();
        ArrayList<BarEntry> values4 = new ArrayList<>();

        for (int i = 0; i < MAX_X_VALUE; i++) {
            values1.add(new BarEntry((i),(int)((Math.random() * (MAX_Y_VALUE - MIN_Y_VALUE)) + MIN_Y_VALUE)));
            values2.add(new BarEntry((i), (int)((Math.random() * (MAX_Y_VALUE - MIN_Y_VALUE)) + MIN_Y_VALUE)));
            values3.add(new BarEntry((i), (int)((Math.random() * (MAX_Y_VALUE - MIN_Y_VALUE)) + MIN_Y_VALUE)));
            values4.add(new BarEntry((i), (int)((Math.random() * (MAX_Y_VALUE - MIN_Y_VALUE)) + MIN_Y_VALUE)));
        }

        BarDataSet set1 = new BarDataSet(values1, TEST);
        BarDataSet set2 = new BarDataSet(values2, INFECT);
        BarDataSet set3 = new BarDataSet(values3, CURE);
        BarDataSet set4 = new BarDataSet(values4, DEATH);

        set1.setColor(ColorTemplate.MATERIAL_COLORS[3]);
        set2.setColor(ColorTemplate.MATERIAL_COLORS[1]);
        set3.setColor(ColorTemplate.MATERIAL_COLORS[0]);
        set4.setColor(ColorTemplate.MATERIAL_COLORS[2]);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        dataSets.add(set2);
        dataSets.add(set3);
        dataSets.add(set4);

        BarData data = new BarData(dataSets);

        return data;
    }

    private void prepareChartData(BarData data) {
        chart.setData(data);

        chart.getBarData().setBarWidth(BAR_WIDTH);

        float groupSpace = 1f - ((BAR_SPACE + BAR_WIDTH) * GROUPS);
        chart.groupBars(0, .26f, BAR_SPACE);

        chart.invalidate();
    }
}