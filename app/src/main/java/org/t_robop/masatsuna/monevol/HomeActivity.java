package org.t_robop.masatsuna.monevol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;
import android.content.Intent;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentViewより前にWindowにActionBar表示を設定
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_home);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // メニューの要素を追加
        menu.add("ホーム");
        menu.add("グラフ");
        menu.add("課金履歴");
        menu.add("設定");

        // メニューの要素を追加して取
        MenuItem actionItem = menu.add("Action Button");

        // SHOW_AS_ACTION_IF_ROOM:余裕があれば表示
        actionItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        // アイコンを設定
        actionItem.setIcon(android.R.drawable.ic_menu_share);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //メニューの中のボタンが押された時の動作
        if (item.getTitle().equals("ホーム")){
            //ホームが押された場合
            Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
            startActivity(intent);
        }
        else if (item.getTitle().equals("グラフ")){
            //グラフが押された場合
            Intent intent = new Intent(HomeActivity.this, GraphActivity.class);
            startActivity(intent);
        }
        else if (item.getTitle().equals("課金履歴")) {
            //課金履歴が押された場合
            Intent intent = new Intent(HomeActivity.this, RecordActivity.class);
            startActivity(intent);
        }
        else if (item.getTitle().equals("設定")) {
            //設定が押された場合
            Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
            startActivity(intent);
        }


        return true;
    }

}
