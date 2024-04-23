import app.App;
import app.AppClient;
import cli.CommandExecuter;
import cli.Terminal;
import dal.DataAccessLayer;
import сommands.CommandArrayFiller;
import storage.Storage;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        /*App.Run();*/
        AppClient.Run();
    }
}