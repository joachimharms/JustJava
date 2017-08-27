/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */

package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

import static android.R.id.checkbox;
import static android.R.id.message;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity
{

    int quantity = 0;

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
        int price = calculatePrice();
        displayMessage(orderSummary(price));

        Log.i("MainActivity", "Has topping: " + checkTopping());

    }

    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     */
    private int calculatePrice()
    {
        int price = quantity * 5;
        return price;
    }

    private String orderSummary(int priceOfOrder)
    {
        String ans = "Name: Joachim Harms";
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
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view)
    {
        quantity = quantity - 1;
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
    private void displayPrice(int number)
    {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message)
    {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}