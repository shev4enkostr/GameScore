package su.shev4enkostr.gamescore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by stas on 07.04.15.
 */
public class AddActivity extends Activity implements View.OnClickListener
{
    private EditText etAdd;
    private Button btnOk, btnCancel;
    private int numberOf = 0;

    private String TAG = "GameScore";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etAdd = (EditText) findViewById(R.id.et_add);

        btnOk = (Button) findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(this);

        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();

        if (id == R.id.btn_ok)
        {
            if (etAdd.length() == 0)
            {
                Toast.makeText(this, R.string.toast_enter_name, Toast.LENGTH_SHORT).show();
            }
            else
            {
                Intent intent = new Intent();
                intent.putExtra("name", etAdd.getText().toString());
                setResult(RESULT_OK, intent);

                finish();
            }
        }

        if (id == R.id.btn_cancel)
        {
            finish();
        }
    }
}
