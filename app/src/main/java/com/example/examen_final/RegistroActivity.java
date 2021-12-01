package com.example.examen_final;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.examen_final.model.Libro;
import com.example.examen_final.service.LibroService;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistroActivity extends AppCompatActivity {
    static final int REQUEST_GET_SINGLE_FILE= 1;
    private ImageView miCaptura;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://upn.lumenes.tk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LibroService service=retrofit.create(LibroService.class);



        EditText txtTitulo=findViewById(R.id.idTitulo);
        EditText txtResumen=findViewById(R.id.idResumen);
        EditText txtAutor=findViewById(R.id.idAutor);
        EditText txtFecha=findViewById(R.id.idFecha);
        EditText txtTienda=findViewById(R.id.idTienda);
       // ImageView imageView=findViewById(R.id.imagenId);
        miCaptura=findViewById(R.id.imagenIds);

        Button button=findViewById(R.id.butIdFoto);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(RegistroActivity.this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    RegistroActivity.this.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1002);
                }else {
                    dispatchPickFromGalleryIntent();
                    Log.i("APP_MAIN","imagen: $selectedImageUri");
                }

            }
        });




        Button button1=findViewById(R.id.butIdGuardar);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Libro libro =new Libro();
                //libro.setTitulo("Abraham Valdelomar");
              //  libro.setResumen("libro ");
              //  libro.setAutor("Abraham ");
              //  libro.setFecha_publicacion("2021-11-11");


                libro.setTitulo(txtTitulo.getText().toString());
                libro.setResumen(txtResumen.getText().toString());
                libro.setAutor(txtAutor.getText().toString());
                libro.setFecha_publicacion(txtFecha.getText().toString());
                libro.setTienda(txtTienda.getText().toString());

                service.crear(libro).enqueue(new Callback<Libro>() {
                    @Override
                    public void onResponse(Call<Libro> call, Response<Libro> response) {
                        if(response.code()==200){
                            Log.i("MAIN_APP","se envio correctamente");
                        }else Log.i("MAIN_APP","se produjo un error al enviar");
                    }

                    @Override
                    public void onFailure(Call<Libro> call, Throwable t) {
                        Log.i("MAIN_APP","no se puede establecer comunicacion en el API");
                    }
                });
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
        //     Bundle extras = data.getExtras();
        //    Bitmap imageBitmap = (Bitmap) extras.get("data");
        //    miCaptura.setImageBitmap(imageBitmap);
        // }
        if(requestCode==REQUEST_GET_SINGLE_FILE && resultCode==RESULT_OK){
            Log.i("MAIN_APP","Captura desde galeria");

            Uri selectedImageUri=data.getData();
            final String path=getPathFromURI(selectedImageUri);
            if (path!=null){
                File f=new File(path);
                selectedImageUri=Uri.fromFile(f);
            }
            // miCaptura.setImageURI(selectedImageUri);
            miCaptura.setImageURI(selectedImageUri);
        }
    }

    public String getPathFromURI(Uri contentUri){
        String res=null;
        String[] proj={MediaStore.Images.Media.DATA};
        Cursor cursor=getContentResolver().query(contentUri,proj,null,null,null);
        if(cursor.moveToFirst()){
            int column_index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res=cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }
    private void dispatchPickFromGalleryIntent() {

        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent,"select Picture"),REQUEST_GET_SINGLE_FILE);
    }
}