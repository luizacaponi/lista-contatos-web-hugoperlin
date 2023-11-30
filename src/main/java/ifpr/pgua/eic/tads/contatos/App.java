package ifpr.pgua.eic.tads.contatos;

import ifpr.pgua.eic.tads.contatos.controllers.AddController;
import ifpr.pgua.eic.tads.contatos.controllers.IndexController;
import ifpr.pgua.eic.tads.contatos.controllers.ListController;
import ifpr.pgua.eic.tads.contatos.model.FabricaConexoes;
import ifpr.pgua.eic.tads.contatos.model.daos.JDBCContatoDAO;
import ifpr.pgua.eic.tads.contatos.model.repositories.ContatoRepository;
import ifpr.pgua.eic.tads.contatos.model.repositories.ImplContatoRepository;
import ifpr.pgua.eic.tads.contatos.utils.JavalinUtils;
import io.javalin.Javalin;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        
        Javalin app = JavalinUtils.makeApp(8080);

        ContatoRepository contatoRepository = new ImplContatoRepository(new JDBCContatoDAO(FabricaConexoes.getInstance()));

        IndexController indexController = new IndexController();
        AddController addController = new AddController(contatoRepository);
        ListController listController = new ListController(contatoRepository);

        app.get("/", indexController.get);
        app.get("/add", addController.get);
        app.post("/add", addController.post);
        app.get("/list", listController.get);

        app.start();

        
    }

}