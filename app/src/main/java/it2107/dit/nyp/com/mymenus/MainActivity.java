package it2107.dit.nyp.com.mymenus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.MenuItemHoverListener;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Menu mymenu = null;
    int subMenuBase = 100;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tvItemSelected);
        registerForContextMenu(tv);
    }

    private void appendText(String text){
        tv.setText(tv.getText() + text);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        menu.setHeaderTitle("Sample Context Menu");
        menu.add(200,200,200, "Item 1");
    }

    public boolean onContextItemSelected(MenuItem item){
        if(item.getItemId()==200){
            appendText("\nHello from Context");
            return true;
        }

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        this.mymenu = menu;
        addRegularMenuItems(menu);
        addSubMenu(menu);


        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void addRegularMenuItems(Menu menu){
        int index = Menu.FIRST;
        menu.add(index, index, index, "Item 1");
        menu.add(index, index+1, index+1, "Item 2");
        menu.add(index, index+2, index+2, "Hide Submenu");
        menu.add(index, index+3, index+3, "Show Submenu");
    }
    private void addSubMenu(Menu menu){
        SubMenu sm = menu.addSubMenu(subMenuBase, subMenuBase+1, subMenuBase, "submenu");
        sm.add(subMenuBase, subMenuBase+2, subMenuBase+2, "sub item1");
        sm.add(subMenuBase, subMenuBase+3, subMenuBase+3, "sub item 2");
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==1){
                appendText("\nHello");
        }
        else if(item.getItemId()==2){
            appendText("\nItem 2");
        }
        else if(item.getItemId()==3){
            this.mymenu.setGroupVisible(subMenuBase, false);
        }
        else if(item.getItemId()==4){
            this.mymenu.setGroupVisible(subMenuBase, true);
        }
        else if(item.getItemId()==R.id.menu_quit){
            finish();
        }

        return true;
    }


}
