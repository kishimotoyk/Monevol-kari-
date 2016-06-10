package org.t_robop.masatsuna.monevol;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
//colortemplateを用いたとき用
import com.github.mikephil.charting.data.DataSet;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentViewより前にWindowにActionBar表示を設定
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_home);

        piechart();
        //円グラフ
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // メニューの要素を追加
        MenuItem actionItem = menu.add("ホーム");
        menu.add("グラフ");
        menu.add("課金履歴");
        menu.add("設定");

        // メニューの要素を追加して取
        //MenuItem actionItem = menu.add("Action Button");

        // SHOW_AS_ACTION_IF_ROOM:余裕があれば表示
        //actionItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        // アイコンを設定
        actionItem.setIcon(android.R.drawable.ic_menu_share);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //メニューの中のボタンが押された時の動作
        if (item.getTitle().equals("ホーム")) {
            //ホームが押された場合
            Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
            startActivity(intent);
        } else if (item.getTitle().equals("グラフ")) {
            //グラフが押された場合
            Intent intent = new Intent(HomeActivity.this, GraphActivity.class);
            startActivity(intent);
        } else if (item.getTitle().equals("課金履歴")) {
            //課金履歴が押された場合
            Intent intent = new Intent(HomeActivity.this, RecordActivity.class);
            startActivity(intent);
        } else if (item.getTitle().equals("設定")) {
            //設定が押された場合
            Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
            startActivity(intent);
        }

        return true;
    }


    public void piechart() {
        //円グラフの関数
        PieChart pieChart = (PieChart) findViewById(R.id.chart);

        int[] color = new int[2];

        ArrayList<Entry> entries = new ArrayList<>();
        //グラフにデータを追加
        entries.add(new Entry(10, 0));
        entries.add(new Entry(8, 1));


        PieDataSet dataset = new PieDataSet(entries, "# of Calls");

        ArrayList<String> labels = new ArrayList<String>();
        //データの名前の設定
        labels.add("課金額");
        labels.add("残り予算");

        color[0] = Color.LTGRAY;
        color[1] = Color.GREEN;

        dataset.setColors(color);
        //色の設定

        PieData data = new PieData(labels, dataset);

        //pieChart.setDescription("Description");
        pieChart.setData(data);


        //pieChart.animateY(2000);
        //アニメーションの時間設定小さくなればはやい 無効にすることも可能

        pieChart.saveToGallery("/sd/mychart.jpg", 85); // 85 is the quality of the image

        pieChart.setDrawHoleEnabled(true);
        //真ん中に円状の穴を開けるか開けないか

        pieChart.setDrawCenterText(true);
        //真ん中の円状の穴にテキスト表示
        pieChart.setCenterText("残り予算\n~円");
        //テキストの内容
        pieChart.setRotationEnabled(false);
        //円グラフを触って回転させるかさせないか


    }

    public void onClick(View arg0) {
        //ボタンを押された時にダイアログメッセージを出して金額を入力させる

        //テキスト入力を受け付けるビューを作成します。
        final EditText editView = new EditText(HomeActivity.this);
        new AlertDialog.Builder(HomeActivity.this)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setTitle("課金額入力")
                //setViewにてビューを設定します。
                .setView(editView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //入力した文字をトースト出力する
                        Toast.makeText(HomeActivity.this,
                                editView.getText().toString(),
                                Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                })
                .show();
    }
}
