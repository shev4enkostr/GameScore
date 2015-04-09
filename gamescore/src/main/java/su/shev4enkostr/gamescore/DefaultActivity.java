package su.shev4enkostr.gamescore;

import android.app.Activity;
import android.os.*;
import android.view.*;
import android.widget.*;

public class DefaultActivity extends Activity implements View.OnClickListener
{
	private Button btnSubmit;
	private static int maxNumberOfPlayers = 8;
    private Players[] player = new Players[maxNumberOfPlayers];
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_default);
		
		btnSubmit = (Button) findViewById(R.id.btn_submit);
		btnSubmit.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu_default, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		
		if (id == R.id.action_clear)
		{
			
		}
		
		if (id == R.id.action_back)
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
			Toast.makeText(this, "Default Activity Submit", Toast.LENGTH_SHORT).show();
	}
}
