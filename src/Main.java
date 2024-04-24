import app.App;
import app.AppClient;
import app.AppServer;
import cli.CommandExecuter;
import cli.Terminal;
import dal.DataAccessLayer;
import сommands.CommandArrayFiller;
import storage.Storage;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        switch (System.getenv("MODE")){
            case ("standalone"): {
                App.Run();
                break;
            }
            case ("server"): {
                AppServer.Run();
                break;
            }
            case ("client"): {
                AppClient.Run();
                break;
            }
        }
    }
}