package com.shareplaylearn.androidlearn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static Random random = new Random();

    private class TotalPaidListener
            implements TextView.OnEditorActionListener {
        TextView totalPaidView;
        TotalPaidListener(TextView totalPaidView) {
            this.totalPaidView = totalPaidView;
        }
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            String totalPaidString = totalPaidView.getText().toString().trim();
            TextView statusMessage = (TextView) findViewById(R.id.statusMessage);
            try {
                double totalPaid = Double.parseDouble(totalPaidString);
                double tipPercentage = 0.20;
                ImageView luckyWaitress = (ImageView) findViewById(R.id.luckyWaitressDisplay);
                if( random.nextInt(5) > 3 ) {
                    tipPercentage *= 2;
                    statusMessage.setText("Lucky Waitress Day!!!");
                    luckyWaitress.setVisibility(View.VISIBLE);
                } else {
                    statusMessage.setText("");
                    luckyWaitress.setVisibility(View.INVISIBLE);
                }

                double tipOwed = totalPaid * tipPercentage;
                TextView tipOwedView = (TextView) findViewById(R.id.tipOwed);
                tipOwedView.setText( String.format( "%.2f", tipOwed ) );
            } catch (NumberFormatException nfe) {
                statusMessage.setText("Did not enter the total as a number.");
                return false;
            }
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        EditText totalPaidView = (EditText)findViewById(R.id.totalPaid);
        totalPaidView.setOnEditorActionListener(new TotalPaidListener(totalPaidView));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
