package ifpr.pgua.eic.tads.contatos.controllers;

import ifpr.pgua.eic.tads.contatos.model.Tarefa;
import ifpr.pgua.eic.tads.contatos.model.repositories.TarefaRepository;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class ListTarefaController {
    
    private TarefaRepository tarefaRepository;

    public ListTarefaController(TarefaRepository tarefaRepository){
        this.tarefaRepository = tarefaRepository;
    }

    public Handler get = (Context ctx) -> {

        var resultado = tarefaRepository.listarTodos();

        if (resultado.foiErro()) {
            ctx.html("<h1>" + resultado.getMsg() + "</h1>");
        } else {
            var tarefas = resultado.comoSucesso().getObj();

            String html = "<html><head><meta charset=\"UTF-8\"></head><body><h1>Lista de Tarefas</h1><ul>";

            for (Tarefa t : tarefas) {
                html += "<li>" + t.toString() + "</li>";
            }

            html += "</ul><a href=\"/\">Voltar</a></body></html>";
            ctx.html(html);
        }
    };
}
