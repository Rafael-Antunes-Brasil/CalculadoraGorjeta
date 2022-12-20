package rafael.com.local.calculadoragorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText editValor;
    private TextView txtPorcentagem, txtGorjeta, txtTotal;
    private SeekBar seekBarGorjeta;
    private double porcentagem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editValor = findViewById(R.id.editValor);
        txtPorcentagem = findViewById(R.id.textPorcentagem);
        txtGorjeta = findViewById(R.id.textGorjeta);
        txtTotal = findViewById(R.id.textTotal);
        seekBarGorjeta = findViewById(R.id.seekBarGorjeta);

        seekBarGorjeta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                porcentagem = progress;
                //txtPorcentagem.setText(Math.round(porcentagem) + "%");
                txtPorcentagem.setText(DecimalFormat.getPercentInstance().format(porcentagem/100));
                calcular();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void calcular(){
        String valorRecuperado = editValor.getText().toString();

        if(valorRecuperado == null || valorRecuperado.equals("")){
            Toast.makeText(getApplicationContext(),
                    "Digite um valor primeiro",
                    Toast.LENGTH_SHORT
            ).show();
        } else {

            double valorDigitado = Double.parseDouble(valorRecuperado);

            double gorjeta = (valorDigitado/100) *  porcentagem;

            txtGorjeta.setText(DecimalFormat.getCurrencyInstance().format(gorjeta));
            txtTotal.setText(DecimalFormat.getCurrencyInstance().format(gorjeta+valorDigitado));
        }
    }

}