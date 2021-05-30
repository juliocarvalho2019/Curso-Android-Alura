package br.com.alura.agenda.ui.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.R;
import br.com.alura.agenda.model.Aluno;

public class ListaAlunosAdapter extends BaseAdapter {
    //Dataset que representa os dados dos alunos
    private final List<Aluno> alunos = new ArrayList<>();

    public ListaAlunosAdapter(Context context) {
        this.context = context;
    }

    //como a variavel context não é modificada, pode ser final.
    private final Context context;

    @Override
    //representa a quantidade de elementos do adapter;
    public int getCount() {
        return alunos.size();
    }

    @Override
    //Retorna o elemento pela posição;
    public Aluno getItem(int posicao) {
        return alunos.get(posicao);
    }

    @Override
    //retornar o id do elemento pela posição;
    public long getItemId(int posicao) {
        return alunos.get(posicao).getId();
    }

    @Override
    //cria a view para cada elemento.
    public View getView(int posicao, View convertView, ViewGroup viewGroup) {
        View viewCriada = criaView(viewGroup);//attachToRoot INDICA QUE NÃO IREMOS CRIAR VIEW, senão ocorre erro.
        Aluno alunoDevolvido = alunos.get(posicao);
        vincula(viewCriada, alunoDevolvido);
        return viewCriada;
    }

    private void vincula(View view, Aluno aluno) {
        TextView nome = view.findViewById(R.id.item_aluno_nome);
        nome.setText(aluno.getNome());

        TextView telefone = view.findViewById(R.id.item_aluno_telefone);
        telefone.setText(aluno.getTelefone());
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_aluno, viewGroup, false);
    }

    //limpa o dataset do adapter
//    private void clear() {
//        alunos.clear();
//    }
//
//    private void addAll(List<Aluno> alunos) {
//        this.alunos.addAll(alunos);
//    }

    public void atualiza(List<Aluno> alunos) {
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }

    public void remove(Aluno aluno) {

        alunos.remove(aluno);
        //pede para adapter atualizar quando dataset for modificado
        notifyDataSetChanged();
    }
}
