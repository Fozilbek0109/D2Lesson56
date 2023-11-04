package uz.coder.d2lesson56.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import uz.coder.d2lesson56.GsonSingleton;
import uz.coder.d2lesson56.R;
import uz.coder.d2lesson56.databinding.ActivitySecondBinding;
import uz.coder.d2lesson56.models.TovarModel;

public class SecondActivity extends AppCompatActivity {
    private ActivitySecondBinding binding;
    private int position = -1;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private  List<TovarModel> tovarModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferences = getSharedPreferences("Tavar temp",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.commit();
        if (getIntent().getExtras() != null){
            TovarModel tovarModel = (TovarModel) getIntent().getSerializableExtra("objj");
            position = getIntent().getIntExtra("position",-1);
            binding.tovarNomi.setText(tovarModel.getTovarNomi());
            binding.tovarSoni.setText(String.valueOf(tovarModel.getCount()));
            int s = getS(binding.spinner, tovarModel.getCountType());
            binding.spinner.setSelection(s);
        }

        binding.btnSave.setOnClickListener(v -> {
            String s = binding.tovarNomi.getText().toString();
            String s1 = binding.tovarSoni.getText().toString();
            String s2 = binding.spinner.getSelectedItem().toString();
            TovarModel tovarModel = new TovarModel(s,Integer.parseInt(s1),s2);
            tovarModelList = new ArrayList<>();
            tovarModelList.add(tovarModel);
            String m = GsonSingleton.getInstance().getGson().toJson(tovarModelList);
            editor.putString("tavars",m);
            editor.commit();
            Intent intent = new Intent();
            intent.putExtra("obj",tovarModel);
            intent.putExtra("pos",position);
            setResult(-1,intent);


            finish();
        });
    }
    public int getS(Spinner spinner, String s){
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(s)){
                return i;
            }
        }
        return 0;
    }
}