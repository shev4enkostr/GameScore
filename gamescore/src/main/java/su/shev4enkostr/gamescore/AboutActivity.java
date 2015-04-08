package su.shev4enkostr.gamescore;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by stas on 07.04.15.
 */
public class AboutActivity extends Activity implements View.OnClickListener
{
    private Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        btnOk = (Button) findViewById(R.id.btn_ok_about);
        btnOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.btn_ok_about)
            finish();
    }
}
