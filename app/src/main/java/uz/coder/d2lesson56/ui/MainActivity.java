package uz.coder.d2lesson56.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import uz.coder.d2lesson56.GsonSingleton;
import uz.coder.d2lesson56.R;
import uz.coder.d2lesson56.adapter.TovarAdapter;
import uz.coder.d2lesson56.databinding.ActivityMainBinding;
import uz.coder.d2lesson56.models.TovarModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private TovarAdapter adapter;
    private List<TovarModel> tovarModelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadData();

        adapter = new TovarAdapter(this, tovarModelList, new TovarAdapter.onClickButton() {
            @Override
            public void edit(TovarModel tovarModel, int position) {
                Intent intent =new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("objj",tovarModel);
                intent.putExtra("position",position);
                startActivityForResult(intent,0);

            }


            @Override
            public void delete(TovarModel tovarModel, int position) {
                tovarModelList.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        binding.listView.setAdapter(adapter);
        binding.listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "position", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        binding.fab.setOnClickListener(v -> {
            Intent intent =new Intent(this,SecondActivity.class);
            startActivityForResult(intent,0);
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        assert data != null;
        TovarModel tovarModel = (TovarModel) data.getSerializableExtra("obj");

        int pos = data.getIntExtra("pos",-1);
        if (pos == -1){

            tovarModelList.add(tovarModel);
            adapter.notifyDataSetChanged();
        }else{

            tovarModelList.get(pos).setTovarNomi(tovarModel.getTovarNomi());
            tovarModelList.get(pos).setCount(tovarModel.getCount());
            tovarModelList.get(pos).setCountType(tovarModel.getCountType());
            adapter.notifyDataSetChanged();
        }

    }

    private void loadData() {
        tovarModelList = new ArrayList<>();


    }
}