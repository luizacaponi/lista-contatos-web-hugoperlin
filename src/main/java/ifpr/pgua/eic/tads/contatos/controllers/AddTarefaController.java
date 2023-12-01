package ifpr.pgua.eic.tads.contatos.controllers;

import ifpr.pgua.eic.tads.contatos.model.Tarefa;
import ifpr.pgua.eic.tads.contatos.model.repositories.TarefaRepository;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class AddTarefaController {

    private TarefaRepository tarefaRepository;

    public AddTarefaController(TarefaRepository tarefaRepository){
        this.tarefaRepository = tarefaRepository;
    }

    public Handler get = (Context ctx) -> {
        ctx.render("templates/addTarefa.html");
    };

    public Handler post = (Context ctx) -> {
        String titulo = ctx.formParam("titulo");
        String descricao = ctx.formParam("descricao");
        
        Tarefa tarefa = new Tarefa(titulo, descricao);

        tarefaRepository.cadastrar(tarefa);

        ctx.html("Tarefa cadastrada!<br/><a href=\"/\">Voltar</a>");
    };
}
