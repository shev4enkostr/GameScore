package su.shev4enkostr.gamescore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.*;


public class MainActivity extends Activity implements View.OnClickListener
{
    private Button btnSubmit;
	private static int maxNumberOfPlayers = 8;
    private static int availableNumberOfPlayers = maxNumberOfPlayers;
    private Players[] player = new Players[maxNumberOfPlayers];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		btnSubmit = (Button) findViewById(R.id.btn_submit);
		btnSubmit.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add)
        {
            if (availableNumberOfPlayers == 0)
            {
                Toast.makeText(this, R.string.toast_max_number_of_players + maxNumberOfPlayers, Toast.LENGTH_LONG).show();
            }
            else
            {
                Intent intent = new Intent(this, AddActivity.class);
                startActivity(intent);
            }
        }
		
		if (id == R.id.action_about)
        {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }
		
		if (id == R.id.action_delete)
		{
			
		}
		
		if (id == R.id.action_clear)
		{
			
		}
		
		if (id == R.id.action_use_default)
		{
			Intent intent = new Intent(this, DefaultActivity.class);
			startActivity(intent);
		}

        if (id == R.id.action_exit)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

	@Override
	public void onClick(View view)
	{
		// Test btnSubmit
		if (view.getId() == R.id.btn_submit)
			Toast.makeText(this, "Main Activity Submit", Toast.LENGTH_SHORT).show();
	}

    public void createPlayer(String name)
    {
        availableNumberOfPlayers--;

        int number = Players.getNumberOfPlayer();

        player[number] = new Players();
        player[number].setName(name);





    }
}
