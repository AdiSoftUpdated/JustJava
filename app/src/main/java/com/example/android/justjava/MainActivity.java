/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.jar.Attributes;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity=2;int price;
    boolean check1,check2;
    String choose1;
    String choose2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
         quantity=quantity+1;
        if(quantity==101)
        {
            quantity=100;
        }

        display(quantity);

    }
    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
         quantity=quantity-1;
        if(quantity==0)
        {
            quantity=1;
        }

         if(quantity==101)
        {
            quantity=100;
        }
        display(quantity);

    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText edit = (EditText)findViewById(R.id.name_field);
        String name = edit.getText().toString();
        CheckBox wc = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        check1 =wc.isChecked();

        if (check1==true)
        {
            choose1="yes";
        }
        else {
            choose1="no";
        }
        CheckBox choco = (CheckBox) findViewById(R.id.Chocolate_checkbox);
         check2 =choco.isChecked();
        if (check2==true)
        {
            choose2="yes";
        }
        else {
            choose2="no";
        }


        int price=calculatePrice();
    String priceMessage=createOrderSummary(price,choose1,choose2,name);

//    Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order For "+name);
//        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order For "+name);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }



    }

    private int calculatePrice(){
        if (check1== false && check2==false){
         price=quantity*5;}
        else if(check1== true && check2==false){

            price=quantity*6;
        }
        else if(check1== false && check2==true){

            price=quantity*7;
        }
        else if(check1== true && check2==true){

            price=quantity*8;
        }

        return price;
    }

    private String createOrderSummary(int price,String choose1,String choose2,String name)

    {
        String priceMessage="Name: "+name +"\nAdd Whipped cream? "+choose1+"\nAdd Chocolate? "+choose2+"\nQuantity:" +quantity + "\nTotal: ₹ "+price+"\nThank You!";
        return priceMessage;


    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }






}