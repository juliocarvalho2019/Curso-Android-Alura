package br.com.alura.agenda.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.ui.activity.adapter.ListaAlunosAdapter;

public class ListaAlunosView {
    private final ListaAlunosAdapter adapter;
    private final AlunoDAO dao;
    private final Context context;

    public ListaAlunosView(Context context) {
        this.context = context;
        this.adapter = new ListaAlunosAdapter(this.context);
        this.dao = new AlunoDAO();
    }

    public void confirmaRemocao(final MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle("Removendo Aluno")
                .setMessage("Tem certeza que quer remover o aluno?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                        remove(alunoEscolhido);
                    }
                })
                //lambda
//                .SetPositiveButton(text:"sim", (dialogInterface, i) -> {
//                            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//                        Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
//                        remove(alunoEscolhido);
//                        })
                .setNegativeButton("Não", null)
                .show();
    }

    public void atualizaAlunos() {
//        adapter.clear();
//        adapter.addAll(dao.todos());
        adapter.atualiza(dao.todos());
    }

    private void remove(final Aluno aluno) {
        dao.remove(aluno);
        adapter.remove(aluno);
    }
//        });
//    }

    public void configuraAdapter(ListView listaDeAlunos) {
//        adapter = new ArrayAdapter<>(
//                this,
////                android.R.layout.simple_list_item_1);
//                R.layout.item_aluno);
//        listaDeAlunos.setAdapter(new BaseAdapter() {
//            private final List<Aluno> alunos = new ArrayList<>();
//
//            @Override
////          representa a quantidade de elementos do adapter;
//            public int getCount() {
//                return alunos.size();
//            }
//
//            @Override
//            //Retorna o elemento pela posição;
//            public Aluno getItem(int posicao) {
//                return alunos.get(posicao);
//            }
//
//            @Override
//            //retornar o id do elemento pela posição;
//            public long getItemId(int posicao) {
//                return alunos.get(posicao).getId();
//            }
//
//            @Override
//            //cria a view para cada elemento.
//            public View getView(int position, View convertView, ViewGroup viewGroup) {
//                View viewCriada = LayoutInflater
//                        .from(ListaAlunosActivity.this)
//                        .inflate(R.layout.item_aluno, viewGroup);
//                return viewCriada;
//            }
//        });
        //inicialização com contexto
//        adapter = new ListaAlunosAdapter(this);
        listaDeAlunos.setAdapter(adapter);
    }
}
