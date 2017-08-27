/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */

package com.example.android.justjava;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static android.R.id.checkbox;
import static android.R.id.message;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity
{

    int quantity = 2;
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view)
    {
        EditText editName = (EditText) findViewById(R.id.edit_name_view);
        name = editName.getText().toString();
        int price = calculatePrice();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary(price, name));
        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        }


//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("geo:47.6, -122.3"));
//        if (intent.resolveActivity(getPackageManager()) != null)
//        {
//            startActivity(intent);
//
//        }
    }

    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     */
    private int calculatePrice()
    {
        int price = 0;
        if (checkTopping())
        {
            price = quantity;
        }
        if (checkChocolate())
        {
            price += quantity * 2;
        }
        price += quantity * 5;
        return price;
    }

    private String orderSummary(int priceOfOrder, String name)
    {
        String ans = "Name: " + name;
        ans += "\nAdd whipped cream? " + checkTopping();
        ans += "\nAdd chocolate? " + checkChocolate();
        ans += "\nQuantity: " + quantity;
        ans += "\nTotal: $" + calculatePrice();
        ans += "\nThank you!";
        return ans;
    }

    public boolean checkTopping()
    {
        boolean hasTopping = true;
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox_view);
        hasTopping = checkBox.isChecked();
        // Log.i("MainActivity", "Value: " + hasTopping);
        return hasTopping;
    }

    public boolean checkChocolate()
    {
        boolean hasChocolate = true;
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_view);
        hasChocolate = chocolateCheckBox.isChecked();
        return hasChocolate;
    }

    // This method is called when the increment button is clicked.
    public void increment(View view)
    {


        if (quantity == 100)
        {
            Toast.makeText(this, "Quantity can't be more than 100!", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity += 1;
        displayQuantity(quantity);
    }

    public void decrement(View view)
    {

        if (quantity == 1)
        {
            Toast.makeText(this, "Quantity can't be less than 1!", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity -= 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberLol)
    {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberLol);
    }

    /**
     * This method displays the given price on the screen.
     */
//    private void displayPrice(int number)
//    {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(NumberFormat.getCurrencyInstance().format(number));
//    }


    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message)
//    {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }

}