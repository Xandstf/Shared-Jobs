package br.edu.ifsp.scl.ads.pdm.sheaedjobs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText nomeEt, emailEt;
    private CheckBox notificacoesCb;
    private EditText telefoneEt, telefoneCelularEt;
    private Button toggleTelefoneBt;
    private Spinner tipoTelefoneSp;
    private RadioGroup sexoRg;
    private RadioButton masculinoRb;
    private EditText nascimentoEt;
    private Spinner tipoFormacaoSp;
    private LinearLayout funMedLl;
    private EditText anoFormaturaEt;
    private LinearLayout graEspLl;
    private EditText anoConclusaoEt;
    private EditText instituicaoEt;
    private LinearLayout mesDouLl;
    private EditText anoConclusaoMdEt;
    private EditText instituicaoMdEt;
    private EditText tituloMonografiaMdEt;
    private EditText nomeOrientadorMdEt;
    private EditText vagasInteresseEt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ligando (binding) objetos com as Views
        bindViews();

        // Listener de item selecionado
        tipoFormacaoSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                anoFormaturaEt.setText("");
                anoConclusaoEt.setText("");
                instituicaoEt.setText("");
                anoConclusaoMdEt.setText("");
                instituicaoMdEt.setText("");
                tituloMonografiaMdEt.setText("");
                nomeOrientadorMdEt.setText("");
                if(((TextView) view).getText().equals("Fundamental") || ((TextView) view).getText().equals("Médio")){
                    funMedLl.setVisibility(View.VISIBLE);
                    graEspLl.setVisibility(View.GONE);
                    mesDouLl.setVisibility(View.GONE);
                }else if(((TextView) view).getText().equals("Graduação") || ((TextView) view).getText().equals("Especialização")){
                    funMedLl.setVisibility(View.GONE);
                    graEspLl.setVisibility(View.VISIBLE);
                    mesDouLl.setVisibility(View.GONE);
                }else if(((TextView) view).getText().equals("Mestrado") || ((TextView) view).getText().equals("Doutorado")){
                    funMedLl.setVisibility(View.GONE);
                    graEspLl.setVisibility(View.GONE);
                    mesDouLl.setVisibility(View.VISIBLE);
                }else{
                    funMedLl.setVisibility(View.GONE);
                    graEspLl.setVisibility(View.GONE);
                    mesDouLl.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Escondendo objetos seletivos
        funMedLl.setVisibility(View.GONE);
        graEspLl.setVisibility(View.GONE);
        mesDouLl.setVisibility(View.GONE);
        telefoneCelularEt.setVisibility(View.GONE);
        toggleTelefoneBt.setText("Adicionar celular");
    }

    private void bindViews(){
        nomeEt = findViewById(R.id.nomeEt);
        emailEt = findViewById(R.id.emailEt);
        notificacoesCb = findViewById(R.id.notificacoesCb);
        toggleTelefoneBt = findViewById(R.id.toggleTelefoneBt);
        telefoneEt = findViewById(R.id.telefoneEt);
        telefoneCelularEt = findViewById(R.id.telefoneCelularEt);
        tipoTelefoneSp = findViewById(R.id.tipoTelefoneSp);
        sexoRg = findViewById(R.id.sexoRg);
        masculinoRb = findViewById(R.id.masculinoRb);
        nascimentoEt = findViewById(R.id.nascimentoEt);
        tipoFormacaoSp = findViewById(R.id.tipoFormacaoSp);
        vagasInteresseEt = findViewById(R.id.vagasInteresseEt);
        funMedLl = findViewById(R.id.funMedLl);
        graEspLl = findViewById(R.id.graEspLl);
        mesDouLl = findViewById(R.id.mesDouLl);
        anoFormaturaEt = findViewById(R.id.anoFormaturaEt);
        anoConclusaoEt = findViewById(R.id.anoConclusaoEt);
        instituicaoEt = findViewById(R.id.instituicaoEt);
        anoConclusaoMdEt = findViewById(R.id.anoConclusaoMdEt);
        instituicaoMdEt = findViewById(R.id.instituicaoMdEt);
        tituloMonografiaMdEt = findViewById(R.id.tituloMonografiaMdEt);
        nomeOrientadorMdEt = findViewById(R.id.nomeOrientadorMdEt);
    }

    public void onClickButton(View view){
        switch (view.getId()){
            case R.id.toggleTelefoneBt:
                if(toggleTelefoneBt.getText().equals("Remover")){
                    telefoneCelularEt.setVisibility(View.GONE);
                    telefoneCelularEt.setText("");
                    toggleTelefoneBt.setText("Adicionar celular");
                }else{
                    telefoneCelularEt.setVisibility(View.VISIBLE);
                    toggleTelefoneBt.setText("Remover");
                }
                break;
            case R.id.salvarBt:
                saveForm();
                break;
            case R.id.limparBt:
                cleanForm();
                break;
            default:
                break;
        }
    }

    public void cleanForm(){
        nomeEt.setText("");
        emailEt.setText("");
        notificacoesCb.setChecked(false);
        telefoneEt.setText("");
        tipoTelefoneSp.setSelection(0);
        masculinoRb.setChecked(true);
        nascimentoEt.setText("");
        tipoFormacaoSp.setSelection(0);
        vagasInteresseEt.setText("");
        anoFormaturaEt.setText("");
        telefoneCelularEt.setVisibility(View.GONE);
        telefoneCelularEt.setText("");
        toggleTelefoneBt.setText("Adicionar celular");
    }

    public void saveForm() {
        StringBuffer sumarioSb = new StringBuffer();
        sumarioSb.append("Nome completo: ").append(nomeEt.getText()).append("\n");
        sumarioSb.append("Email: ").append(emailEt.getText()).append("\n");

        if(notificacoesCb.isChecked()){
            sumarioSb.append("Notificações: Ativadas").append("\n");;
        }else{
            sumarioSb.append("Notificações: Desativadas").append("\n");
        }

        sumarioSb.append("Telefone: ").append(telefoneEt.getText()).append("\n");
        sumarioSb.append("Tipo telefone: ").append(tipoTelefoneSp.getSelectedItem().toString()).append("\n");

        if(toggleTelefoneBt.getText().equals("Remover")){
            sumarioSb.append("Celular: ").append(telefoneCelularEt.getText()).append("\n");
        }

        sumarioSb.append("Sexo: ");
        switch (sexoRg.getCheckedRadioButtonId()){
            case R.id.masculinoRb:
                sumarioSb.append("masculino");
                break;
            case R.id.femininoRb:
                sumarioSb.append("feminino");
                break;
            default:
                break;
        }
        sumarioSb.append("\n");

        sumarioSb.append("Data Nascimento: ").append(nascimentoEt.getText()).append("\n");

        if(tipoFormacaoSp.getSelectedItem().toString().equals("Fundamental") || tipoFormacaoSp.getSelectedItem().toString().equals("Médio")){
            sumarioSb.append("Tipo formação: ").append(tipoFormacaoSp.getSelectedItem().toString()).append("\n");
            sumarioSb.append("Ano Formatura: ").append(anoFormaturaEt.getText()).append("\n");
        }else if(tipoFormacaoSp.getSelectedItem().toString().equals("Graduação") || tipoFormacaoSp.getSelectedItem().toString().equals("Especialização")){
            sumarioSb.append("Tipo formação: ").append(tipoFormacaoSp.getSelectedItem().toString()).append("\n");
            sumarioSb.append("Ano Conclusão: ").append(anoConclusaoEt.getText()).append("\n");
            sumarioSb.append("Instituição: ").append(instituicaoEt.getText()).append("\n");
        }else if(tipoFormacaoSp.getSelectedItem().toString().equals("Mestrado") || tipoFormacaoSp.getSelectedItem().toString().equals("Doutorado")){
            sumarioSb.append("Tipo formação: ").append(tipoFormacaoSp.getSelectedItem().toString()).append("\n");
            sumarioSb.append("Ano Conclusão: ").append(anoConclusaoMdEt.getText()).append("\n");
            sumarioSb.append("Instituição: ").append(instituicaoMdEt.getText()).append("\n");
            sumarioSb.append("Título Monografia: ").append(tituloMonografiaMdEt.getText()).append("\n");
            sumarioSb.append("Orientador: ").append(nomeOrientadorMdEt.getText()).append("\n");
        }

        sumarioSb.append("Vagas Interesse: ").append(vagasInteresseEt.getText());

        Toast.makeText(this,sumarioSb.toString(), Toast.LENGTH_LONG).show();
    }
}