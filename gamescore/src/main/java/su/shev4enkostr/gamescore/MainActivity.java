package su.shev4enkostr.gamescore;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener
{
    private static String TAG = "GameScore";

    private static int maxNumberOfPlayers = 8;
    private static int currentPlayerNumber = 0;
    private Players[] player = new Players[maxNumberOfPlayers];

    private Button btnSubmit;

    private TextView[] tvNamePlayer = new TextView[maxNumberOfPlayers];
    private int[] idNamePlayer = {R.id.tv_name_player1, R.id.tv_name_player2,
            R.id.tv_name_player3, R.id.tv_name_player4,
            R.id.tv_name_player5, R.id.tv_name_player6,
            R.id.tv_name_player7, R.id.tv_name_player8};

    private TextView[] tvScorePlayer = new TextView[maxNumberOfPlayers];
    private int[] idTvScore = {R.id.tv_score_player1, R.id.tv_score_player2,
            R.id.tv_score_player3, R.id.tv_score_player4,
            R.id.tv_score_player5, R.id.tv_score_player6,
            R.id.tv_score_player7, R.id.tv_score_player8};

    private LinearLayout[] llEtEnterNamePlayer = new LinearLayout[maxNumberOfPlayers];
    private int[] idLlEtScore = {R.id.ll_enter_score_player1, R.id.ll_enter_score_player2,
            R.id.ll_enter_score_player3, R.id.ll_enter_score_player4,
            R.id.ll_enter_score_player5, R.id.ll_enter_score_player6,
            R.id.ll_enter_score_player7, R.id.ll_enter_score_player8};

    private EditText[] etEnterScorePlayer = new EditText[maxNumberOfPlayers];
    private int[] idEtScore = {R.id.et_enter_score_player1, R.id.et_enter_score_player2,
            R.id.et_enter_score_player3, R.id.et_enter_score_player4,
            R.id.et_enter_score_player5, R.id.et_enter_score_player6,
            R.id.et_enter_score_player7, R.id.et_enter_score_player8};

    private LinearLayout.LayoutParams etScoreLParams =
            new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		btnSubmit = (Button) findViewById(R.id.btn_submit_main);
		btnSubmit.setOnClickListener(this);



        for (int i = 0; i < maxNumberOfPlayers; i++)
        {
            player[i] = new Players();

            tvNamePlayer[i] = (TextView) findViewById(idNamePlayer[i]);

            tvScorePlayer[i] = (TextView) findViewById(idTvScore[i]);

            Log.d(TAG, "llEtEnterNamePlayer[i] = (LinearLayout) findViewById(idLlEtScore[i])");
            llEtEnterNamePlayer[i] = (LinearLayout) findViewById(idLlEtScore[i]);

            //Log.d(TAG, "etEnterScorePlayer[i].setId(idEtScore[i]) \n");
            //etEnterScorePlayer[i].setId(idEtScore[i]);
        }

        //this.createPlayer(addPlayerName);
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
            if (currentPlayerNumber >= maxNumberOfPlayers)
            {
                Toast.makeText(this, R.string.toast_max_number_of_players, Toast.LENGTH_SHORT).show();
            }
            else
            {
                Intent intent = new Intent(this, AddActivity.class);
                startActivityForResult(intent, 1);
            }
        }
		
		if (id == R.id.action_about)
        {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }
		
		if (id == R.id.action_delete)
		{
            deletePlayer();
		}
		
		if (id == R.id.action_clear)
		{
			clearScore();
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
		if (view.getId() == R.id.btn_submit_main)
        {
            Animation hyperSpaceJump = AnimationUtils.loadAnimation(this, R.anim.hyperspace_jump);
            btnSubmit.startAnimation(hyperSpaceJump);

            addScore();
        }
	}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK)
        {
            String name = data.getStringExtra("name");
            createPlayer(name);
        }
        else Toast.makeText(this, R.string.toast_no_name, Toast.LENGTH_SHORT).show();
    }

    public void createPlayer(String name)
    {
        int i = currentPlayerNumber;

        player[i].setName(name);

        etEnterScorePlayer[i] = new EditText(this);

        etEnterScorePlayer[i].setId(idEtScore[i]);
        etEnterScorePlayer[i].setGravity(Gravity.CENTER_HORIZONTAL);
        etEnterScorePlayer[i].setHint(R.string.et_hint);
        etEnterScorePlayer[i].setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
        etEnterScorePlayer[i].setTextSize(30);
        etEnterScorePlayer[i].setTypeface(null, Typeface.BOLD);

        llEtEnterNamePlayer[i].addView(etEnterScorePlayer[i]);

        tvNamePlayer[i].setText(name);

        tvScorePlayer[i].setText(String.valueOf(player[i].getScore()));

        currentPlayerNumber++;
    }

    public void deletePlayer()
    {
        if (currentPlayerNumber > 0)
        {
            int i = currentPlayerNumber - 1;

            tvNamePlayer[i].setText("");
            tvScorePlayer[i].setText("");
            llEtEnterNamePlayer[i].removeAllViews();
            currentPlayerNumber--;
        }
    }

    public void addScore()
    {
        for (int i = 0; i < currentPlayerNumber; i++)
        {
            if (etEnterScorePlayer[i].length() != 0)
            {
                player[i].addScore(Integer.parseInt(etEnterScorePlayer[i].getText().toString()));
                tvScorePlayer[i].setText(String.valueOf(player[i].getScore()));
            }

            etEnterScorePlayer[i].setText("");
        }
    }

    public void clearScore()
    {
        for (int i = 0; i < currentPlayerNumber; i++)
        {
            Animation combo = AnimationUtils.loadAnimation(this, R.anim.combo);
            tvScorePlayer[i].startAnimation(combo);
            //tvNamePlayer[i].startAnimation(combo);
            //etEnterScorePlayer[i].startAnimation(combo);

            player[i].setScore(0);
            tvScorePlayer[i].setText(String.valueOf(player[i].getScore()));
        }
    }
}
