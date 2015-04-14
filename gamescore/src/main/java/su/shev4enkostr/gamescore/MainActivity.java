package su.shev4enkostr.gamescore;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
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
import android.app.AlertDialog;
import android.content.DialogInterface;

public class MainActivity extends Activity implements View.OnClickListener
{
    //private static String TAG = "GameScore";

    private static int maxNumberOfPlayers = 8;
    private static int currentPlayerNumber = 0;
	
    private Players[] player = new Players[maxNumberOfPlayers];

    private Button btnSubmit;

    private EditText etAdd;

    private LinearLayout[] llPlayer = new LinearLayout[maxNumberOfPlayers];
    private int[] idLlPlayer = {R.id.ll_player1, R.id.ll_player2,
            R.id.ll_player3, R.id.ll_player4,
            R.id.ll_player5, R.id.ll_player6,
            R.id.ll_player7, R.id.ll_player8};

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

    private SharedPreferences saves;

    //private LinearLayout.LayoutParams etScoreLParams =
            //new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

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

            llPlayer[i] = (LinearLayout) findViewById(idLlPlayer[i]);

            tvNamePlayer[i] = (TextView) findViewById(idNamePlayer[i]);

            tvScorePlayer[i] = (TextView) findViewById(idTvScore[i]);

            llEtEnterNamePlayer[i] = (LinearLayout) findViewById(idLlEtScore[i]);
        }

        loadScore();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        saveScore();
    }

    @Override
    public void onBackPressed()
    {
        new AlertDialog.Builder(this)
                .setTitle(R.string.exit_title)
                .setMessage(R.string.exit_message)
                .setIcon(android.R.drawable.ic_lock_power_off)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        dialogInterface.cancel();
                    }
                })
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
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
                //Intent intent = new Intent(this, AddActivity.class);
                //startActivityForResult(intent, 1);
                LinearLayout addView = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_add, null);
                etAdd = (EditText) addView.findViewById(R.id.et_add);

                new AlertDialog.Builder(this)
                    .setTitle(R.string.add_title)
                    .setIcon(android.R.drawable.ic_menu_add)
                    .setCancelable(false)
                    .setView(addView)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i)
                        {
                            if (etAdd.length() == 0)
                            {
                                Toast.makeText(MainActivity.this, R.string.toast_no_name, Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                createPlayer(etAdd.getText().toString());
                            }

                            dialogInterface.cancel();
                        }
                    })

                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i)
                        {
                            dialogInterface.cancel();
                        }
                    })

                    .show();
            }
        }
		
		if (id == R.id.action_about)
        {
            //Intent intent = new Intent(this, AboutActivity.class);
            //startActivity(intent);
			
			new AlertDialog.Builder(this)
			    .setTitle(R.string.about_title)
			    .setMessage(R.string.about_text)
			    .setIcon(android.R.drawable.ic_dialog_info)
			    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
			    {
				    public void onClick(DialogInterface dialogInterface, int which)
				    {
                        dialogInterface.cancel();
				    }
			    })
			
			    .show();
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
		if (view.getId() == R.id.btn_submit_main)
        {
            Animation hyperSpaceJump = AnimationUtils.loadAnimation(this, R.anim.hyperspace_jump);
            btnSubmit.startAnimation(hyperSpaceJump);

            addScore();
        }
	}

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK)
        {
            String name = data.getStringExtra("name");
            createPlayer(name);
        }
        else Toast.makeText(this, R.string.toast_no_name, Toast.LENGTH_SHORT).show();
    }*/

    public void createPlayer(String name)
    {
        int i = currentPlayerNumber;

        player[i].setName(name);

        createViews(i);

        tvNamePlayer[i].setText(name);

        tvScorePlayer[i].setText(String.valueOf(player[i].getScore()));

        currentPlayerNumber++;
    }

    private void createViews(int playerNumber)
    {
        int i = playerNumber;

        etEnterScorePlayer[i] = new EditText(this);

        etEnterScorePlayer[i].setId(idEtScore[i]);
        etEnterScorePlayer[i].setGravity(Gravity.CENTER_HORIZONTAL);
        etEnterScorePlayer[i].setHint(R.string.et_hint);
        etEnterScorePlayer[i].setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
        etEnterScorePlayer[i].setTextSize(30);
        etEnterScorePlayer[i].setTypeface(null, Typeface.BOLD);

        llEtEnterNamePlayer[i].addView(etEnterScorePlayer[i]);

        llPlayer[i].setBackgroundResource(R.drawable.back);
    }

    public void deletePlayer()
    {
        if (currentPlayerNumber > 0)
        {
            int i = currentPlayerNumber - 1;

            tvNamePlayer[i].setText("");
            tvScorePlayer[i].setText("");
            llEtEnterNamePlayer[i].removeAllViews();
            llPlayer[i].setBackgroundResource(R.drawable.back_transparent);
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

    public void saveScore()
    {
        saves = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = saves.edit();
        ed.putInt("numberOfPlayers", currentPlayerNumber);
        ed.apply();

        for (int i = 0; i < currentPlayerNumber; i++)
        {
            ed.putString("playerName" + i, player[i].getName());
            ed.putInt("playerScore" + i, player[i].getScore());
            ed.apply();
        }
    }

    public void loadScore()
    {
        saves = getPreferences(MODE_PRIVATE);
        currentPlayerNumber = saves.getInt("numberOfPlayers", 0);

        for (int i = 0; i < currentPlayerNumber; i++)
        {
            String name = saves.getString("playerName" + i, "");
            int score = saves.getInt("playerScore" + i, 0);
            player[i].setName(name);
            player[i].setScore(score);
            tvNamePlayer[i].setText(name);
            tvScorePlayer[i].setText(String.valueOf(score));
            createViews(i);
        }
    }
}
