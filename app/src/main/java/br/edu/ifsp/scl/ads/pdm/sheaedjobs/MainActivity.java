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

import br.edu.ifsp.scl.ads.pdm.sheaedjobs.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        // Listener de item selecionado
        activityMainBinding.tipoFormacaoSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                activityMainBinding.anoFormaturaEt.setText("");
                activityMainBinding.anoConclusaoEt.setText("");
                activityMainBinding.instituicaoEt.setText("");
                activityMainBinding.anoConclusaoMdEt.setText("");
                activityMainBinding.instituicaoMdEt.setText("");
                activityMainBinding.tituloMonografiaMdEt.setText("");
                activityMainBinding.nomeOrientadorMdEt.setText("");
                if(((TextView) view).getText().equals("Fundamental") || ((TextView) view).getText().equals("Médio")){
                    activityMainBinding.funMedLl.setVisibility(View.VISIBLE);
                    activityMainBinding.graEspLl.setVisibility(View.GONE);
                    activityMainBinding.mesDouLl.setVisibility(View.GONE);
                }else if(((TextView) view).getText().equals("Graduação") || ((TextView) view).getText().equals("Especialização")){
                    activityMainBinding.funMedLl.setVisibility(View.GONE);
                    activityMainBinding.graEspLl.setVisibility(View.VISIBLE);
                    activityMainBinding.mesDouLl.setVisibility(View.GONE);
                }else if(((TextView) view).getText().equals("Mestrado") || ((TextView) view).getText().equals("Doutorado")){
                    activityMainBinding.funMedLl.setVisibility(View.GONE);
                    activityMainBinding.graEspLl.setVisibility(View.GONE);
                    activityMainBinding.mesDouLl.setVisibility(View.VISIBLE);
                }else{
                    activityMainBinding.funMedLl.setVisibility(View.GONE);
                    activityMainBinding.graEspLl.setVisibility(View.GONE);
                    activityMainBinding.mesDouLl.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Escondendo objetos seletivos
        activityMainBinding.funMedLl.setVisibility(View.GONE);
        activityMainBinding.graEspLl.setVisibility(View.GONE);
        activityMainBinding.mesDouLl.setVisibility(View.GONE);
        activityMainBinding.telefoneCelularEt.setVisibility(View.GONE);
        activityMainBinding.toggleTelefoneBt.setText("Adicionar celular");
    }

    public void onClickButton(View view){
        if(view.getId() == activityMainBinding.limparBt.getId()){
            cleanForm();
        }else if(view.getId() == activityMainBinding.salvarBt.getId()){
            saveForm();
        }else if(view.getId() == activityMainBinding.toggleTelefoneBt.getId()){
            if(activityMainBinding.toggleTelefoneBt.getText().equals("Remover")){
                activityMainBinding.telefoneCelularEt.setVisibility(View.GONE);
                activityMainBinding.telefoneCelularEt.setText("");
                activityMainBinding.toggleTelefoneBt.setText("Adicionar celular");
            }else{
                activityMainBinding.telefoneCelularEt.setVisibility(View.VISIBLE);
                activityMainBinding.toggleTelefoneBt.setText("Remover");
            }
        }
    }

    public void cleanForm(){
        activityMainBinding.nomeEt.setText("");
        activityMainBinding.emailEt.setText("");
        activityMainBinding.notificacoesCb.setChecked(false);
        activityMainBinding.telefoneEt.setText("");
        activityMainBinding.tipoTelefoneSp.setSelection(0);
        activityMainBinding.masculinoRb.setChecked(true);
        activityMainBinding.nascimentoEt.setText("");
        activityMainBinding.tipoFormacaoSp.setSelection(0);
        activityMainBinding.vagasInteresseEt.setText("");
        activityMainBinding.anoFormaturaEt.setText("");
        activityMainBinding.telefoneCelularEt.setVisibility(View.GONE);
        activityMainBinding.telefoneCelularEt.setText("");
        activityMainBinding.toggleTelefoneBt.setText("Adicionar celular");
    }

    public void saveForm() {
        StringBuffer sumarioSb = new StringBuffer();
        sumarioSb.append("Nome completo: ").append(activityMainBinding.nomeEt.getText()).append("\n");
        sumarioSb.append("Email: ").append(activityMainBinding.emailEt.getText()).append("\n");

        if(activityMainBinding.notificacoesCb.isChecked()){
            sumarioSb.append("Notificações: Ativadas").append("\n");;
        }else{
            sumarioSb.append("Notificações: Desativadas").append("\n");
        }

        sumarioSb.append("Telefone: ").append(activityMainBinding.telefoneEt.getText()).append("\n");
        sumarioSb.append("Tipo telefone: ").append(activityMainBinding.tipoTelefoneSp.getSelectedItem().toString()).append("\n");

        if(activityMainBinding.toggleTelefoneBt.getText().equals("Remover")){
            sumarioSb.append("Celular: ").append(activityMainBinding.telefoneCelularEt.getText()).append("\n");
        }

        sumarioSb.append("Sexo: ");

        if(activityMainBinding.sexoRg.getCheckedRadioButtonId() == activityMainBinding.masculinoRb.getId()){
            sumarioSb.append("masculino");
        }else if(activityMainBinding.sexoRg.getCheckedRadioButtonId() == activityMainBinding.femininoRb.getId()){
            sumarioSb.append("feminino");
        }

        sumarioSb.append("\n");

        sumarioSb.append("Data Nascimento: ").append(activityMainBinding.nascimentoEt.getText()).append("\n");

        if(activityMainBinding.tipoFormacaoSp.getSelectedItem().toString().equals("Fundamental") || activityMainBinding.tipoFormacaoSp.getSelectedItem().toString().equals("Médio")){
            sumarioSb.append("Tipo formação: ").append(activityMainBinding.tipoFormacaoSp.getSelectedItem().toString()).append("\n");
            sumarioSb.append("Ano Formatura: ").append(activityMainBinding.anoFormaturaEt.getText()).append("\n");
        }else if(activityMainBinding.tipoFormacaoSp.getSelectedItem().toString().equals("Graduação") || activityMainBinding.tipoFormacaoSp.getSelectedItem().toString().equals("Especialização")){
            sumarioSb.append("Tipo formação: ").append(activityMainBinding.tipoFormacaoSp.getSelectedItem().toString()).append("\n");
            sumarioSb.append("Ano Conclusão: ").append(activityMainBinding.anoConclusaoEt.getText()).append("\n");
            sumarioSb.append("Instituição: ").append(activityMainBinding.instituicaoEt.getText()).append("\n");
        }else if(activityMainBinding.tipoFormacaoSp.getSelectedItem().toString().equals("Mestrado") || activityMainBinding.tipoFormacaoSp.getSelectedItem().toString().equals("Doutorado")){
            sumarioSb.append("Tipo formação: ").append(activityMainBinding.tipoFormacaoSp.getSelectedItem().toString()).append("\n");
            sumarioSb.append("Ano Conclusão: ").append(activityMainBinding.anoConclusaoMdEt.getText()).append("\n");
            sumarioSb.append("Instituição: ").append(activityMainBinding.instituicaoMdEt.getText()).append("\n");
            sumarioSb.append("Título Monografia: ").append(activityMainBinding.tituloMonografiaMdEt.getText()).append("\n");
            sumarioSb.append("Orientador: ").append(activityMainBinding.nomeOrientadorMdEt.getText()).append("\n");
        }

        sumarioSb.append("Vagas Interesse: ").append(activityMainBinding.vagasInteresseEt.getText());

        Toast.makeText(this,sumarioSb.toString(), Toast.LENGTH_LONG).show();
    }
}