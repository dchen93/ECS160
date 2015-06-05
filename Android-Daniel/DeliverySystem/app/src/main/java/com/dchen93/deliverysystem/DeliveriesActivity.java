package com.dchen93.deliverysystem;

import java.io.*;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class DeliveriesActivity extends ActionBarActivity {
    double xcoord = 0;
    double ycoord = 0;
    String deliveryFriend = "";
    int delivery = 0;
    String fileName;// = getFilesDir()+"data.txt";
    String line;
    TextView friendName;
    TextView coordinates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliveries);
        fileName = getFilesDir()+"data.txt";
        friendName = (TextView)findViewById(R.id.deliveryFriend);
        coordinates = (TextView)findViewById(R.id.coordinates);


        try {

            FileReader fileReader1 = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader1);
            int flag = 0;
            while ((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                if(line.equals("-"))
                {
                    flag = 1;
                    continue;
                }
                if(flag == 1)
                {
                    if(line.equals("false"))
                    {
                        delivery = 0;
                        break;
                    }
                    else
                    {
                        delivery = 1;
                        flag++;
                    }
                    continue;
                }
                if(flag == 2)
                {
                    deliveryFriend = line;
                    flag++;
                    continue;
                }
                if(flag == 3)
                {
                    xcoord = Double.parseDouble(line);
                    flag++;
                    continue;
                }
                if(flag == 4)
                {
                    ycoord = Double.parseDouble(line);
                    flag++;
                    break;
                }
                //friends.add(line);
            }
             bufferedReader.close();
        } catch (FileNotFoundException ex) {
            try{
                //friendName.setText("poop");
                PrintWriter writer = new PrintWriter(fileName);
                //friendName.setText("poop");
                writer.println("Daniel");
                writer.println("Ittai");
                writer.println("Ricky");
                writer.println("Kristijonas");
                writer.println("Bob");
                writer.println("Jane");
                writer.println("Joe");
                writer.println("Mary");
                writer.println("Elizabeth");
                writer.println("Larry");
                writer.println("Harry");
                writer.println("John");
                writer.println("Liz");
                writer.println("-");
                writer.println("false");
                writer.println(" ");//Deliveryfriend
                writer.println("38.532247");//xcoord
                writer.println("-121.577208");//ycoord
                writer.close();
            } catch(FileNotFoundException ex2) {
                ex2.printStackTrace();
            }

        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }

        if(delivery == 1)
        {
            friendName.setText(deliveryFriend);
            coordinates.setText("Friends Coordinates\n"+xcoord+", "+ycoord);
        }

        Button cancel = (Button) findViewById(R.id.canceldelivery);
        cancel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    PrintWriter writer = new PrintWriter(fileName);
                    writer.println("Daniel");
                    writer.println("Ittai");
                    writer.println("Ricky");
                    writer.println("Kristijonas");
                    writer.println("Bob");
                    writer.println("Jane");
                    writer.println("Joe");
                    writer.println("Mary");
                    writer.println("Elizabeth");
                    writer.println("Larry");
                    writer.println("Harry");
                    writer.println("John");
                    writer.println("Liz");
                    writer.println("-");
                    writer.println("false");
                    writer.println(" ");//Deliveryfriend
                    writer.println("38.532247");//xcoord
                    writer.println("-121.577208");//ycoord
                    writer.close();
                } catch(FileNotFoundException ex2) {

                }
                friendName.setText("No Delivery in Progress");
                coordinates.setText(" ");
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_deliveries, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_map) {
            Intent intent = new Intent(DeliveriesActivity.this, MapsActivity.class);
            DeliveriesActivity.this.startActivity(intent);
        }
        if (id == R.id.action_home) {
            Intent intent = new Intent(DeliveriesActivity.this, HomeActivity.class);
            DeliveriesActivity.this.startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
