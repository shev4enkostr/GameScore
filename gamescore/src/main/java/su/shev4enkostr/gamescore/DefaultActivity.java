package su.shev4enkostr.gamescore;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.*;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

public class DefaultActivity extends Activity implements View.OnClickListener
{
	private static int maxNumberOfPlayers = 8;
    private Players[] player = new Players[maxNumberOfPlayers];

    private Button btnSubmit;

    private EditText[] etEnterScorePlayer = new EditText[maxNumberOfPlayers];
    private int[] idEtScore = {R.id.et_enter_score_player1, R.id.et_enter_score_player2,
            R.id.et_enter_score_player3, R.id.et_enter_score_player4,
            R.id.et_enter_score_player5, R.id.et_enter_score_player6,
            R.id.et_enter_score_player7, R.id.et_enter_score_player8};

    private TextView[] tvScorePlayer = new TextView[maxNumberOfPlayers];
    private int[] idTvScore = {R.id.tv_score_player1, R.id.tv_score_player2,
            R.id.tv_score_player3, R.id.tv_score_player4,
            R.id.tv_score_player5, R.id.tv_score_player6,
            R.id.tv_score_player7, R.id.tv_score_player8};

    private SharedPreferences saves;


    @Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_default);
		
		btnSubmit = (Button) findViewById(R.id.btn_submit_default);
		btnSubmit.setOnClickListener(this);

        for (int i = 0; i < maxNumberOfPlayers; i++)
        {
            player[i] = new Players();

            etEnterScorePlayer[i] = (EditText) findViewById(idEtScore[i]);

            tvScorePlayer[i] = (TextView) findViewById(idTvScore[i]);
            tvScorePlayer[i].setText(String.valueOf(player[i].getScore()));
        }

        loadScore();
	}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveScore();
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
			clearScore();
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
		if (view.getId() == R.id.btn_submit_default)
        {
            Animation hyperSpaceJump = AnimationUtils.loadAnimation(this, R.anim.hyperspace_jump);
            btnSubmit.startAnimation(hyperSpaceJump);

            addScore();
        }
	}

    public void addScore()
    {
        for (int i = 0; i < maxNumberOfPlayers; i++)
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
        for (int i = 0; i < maxNumberOfPlayers; i++)
        {
            Animation combo = AnimationUtils.loadAnimation(this, R.anim.combo);
            tvScorePlayer[i].startAnimation(combo);
            //tvNamePlayer[i].startAnimation(combo);
            //etEnterScorePlayer[i].startAnimation(combo);

            player[i].setScore(0);
            tvScorePlayer[i].setText(String.valueOf(player[i].getScore()));
        }
    }

    public void saveScore()
    {
        saves = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = saves.edit();
        //ed.putInt("maxNumberOfPlayers", maxNumberOfPlayers);
        ed.apply();

        for (int i = 0; i < maxNumberOfPlayers; i++)
        {
            ed.putInt("playerScore" + i, player[i].getScore());
            ed.apply();
        }
    }

    public void loadScore()
    {
        saves = getPreferences(MODE_PRIVATE);
        //maxNumberOfPlayers = saves.getInt("maxNumberOfPlayers", 0);

        for (int i = 0; i < maxNumberOfPlayers; i++)
        {
            int score = saves.getInt("playerScore" + i, 0);
            player[i].setScore(score);
            tvScorePlayer[i].setText(String.valueOf(score));
        }
    }
}
