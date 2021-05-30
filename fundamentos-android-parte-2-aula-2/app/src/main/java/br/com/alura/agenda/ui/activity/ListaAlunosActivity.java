package br.com.alura.agenda.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.alura.agenda.R;
import br.com.alura.agenda.model.Aluno;

import static br.com.alura.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de alunos";

    //    private final AlunoDAO dao = new AlunoDAO();
    //    private ArrayAdapter<Aluno> adapter;
//    private ListaAlunosAdapter adapter;
    private final ListaAlunosView listaAlunosView = new ListaAlunosView(this);

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        configuraFabNovoAluno();
        configuraLista();
//        for (int i = 0; i < 10; i++) {
//        dao.salva(new Aluno("Alex", "1122223333", "alex@alura.com.br"));
//        dao.salva(new Aluno("Fran", "1122223333", "fran@gmail.com"));
//        }

//        new AlertDialog
//                .Builder(this)
//        .setTitle("Removendo Aluno")
//        .setMessage("Tem certeza que quer remover o aluno?")
//        .setPositiveButton("Sim", null)
//        .setNegativeButton("Não", null)
//        .show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
//        menu.add("Remover");
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
//        CharSequence tituloDoMenu =  item.getTitle();
        if (itemId == R.id.activity_lista_alunos_menu_remover) {
            listaAlunosView.confirmaRemocao(item);
//            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//            Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
//            remove(alunoEscolhido);

        }
        return super.onContextItemSelected(item);
    }

//    private void confirmaRemocao(final MenuItem item) {
//        new AlertDialog
//                .Builder(this)
//                .setTitle("Removendo Aluno")
//                .setMessage("Tem certeza que quer remover o aluno?")
//                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//                        Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
//                        remove(alunoEscolhido);
//                    }
//                })
//                //lambda
////                .SetPositiveButton(text:"sim", (dialogInterface, i) -> {
////                            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
////                        Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
////                        remove(alunoEscolhido);
////                        })
//                .setNegativeButton("Não", null)
//                .show();
//    }

    private void configuraFabNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abreFormularioModoInsereAluno();
            }
        });
    }

    private void abreFormularioModoInsereAluno() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaAlunosView.atualizaAlunos();

    }

//    private void atualizaAlunos() {
////        adapter.clear();
////        adapter.addAll(dao.todos());
//        adapter.atualiza(dao.todos());
//    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);
        listaAlunosView.configuraAdapter(listaDeAlunos);
        configuraListenerDeCliquePorItem(listaDeAlunos);
//        configuraListenerCliqueLongoPorItem(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);
    }

    //    private void configuraListenerCliqueLongoPorItem(ListView listaDeAlunos) {
//        listaDeAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicao, long id) {
//                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
//                remove(alunoEscolhido);
//                return false;
//            }
//
//    private void remove(final Aluno aluno) {
//        dao.remove(aluno);
//        adapter.remove(aluno);
//    }
////        });
////    }

    private void configuraListenerDeCliquePorItem(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
                abreFormularioModoEditaAluno(alunoEscolhido);
            }
        });
    }

    private void abreFormularioModoEditaAluno(Aluno aluno) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(vaiParaFormularioActivity);
    }

//    private void configuraAdapter(ListView listaDeAlunos) {
////        adapter = new ArrayAdapter<>(
////                this,
//////                android.R.layout.simple_list_item_1);
////                R.layout.item_aluno);
////        listaDeAlunos.setAdapter(new BaseAdapter() {
////            private final List<Aluno> alunos = new ArrayList<>();
////
////            @Override
//////          representa a quantidade de elementos do adapter;
////            public int getCount() {
////                return alunos.size();
////            }
////
////            @Override
////            //Retorna o elemento pela posição;
////            public Aluno getItem(int posicao) {
////                return alunos.get(posicao);
////            }
////
////            @Override
////            //retornar o id do elemento pela posição;
////            public long getItemId(int posicao) {
////                return alunos.get(posicao).getId();
////            }
////
////            @Override
////            //cria a view para cada elemento.
////            public View getView(int position, View convertView, ViewGroup viewGroup) {
////                View viewCriada = LayoutInflater
////                        .from(ListaAlunosActivity.this)
////                        .inflate(R.layout.item_aluno, viewGroup);
////                return viewCriada;
////            }
////        });
//        //inicialização com contexto
//        adapter = new ListaAlunosAdapter(this);
//        listaDeAlunos.setAdapter(adapter);
//    }


}
