import com.google.common.util.concurrent.ServiceManager;
import javafx.application.Application;
import lombok.val;
import org.git.desk.GitDeskApplication;
import org.git.desk.context.DIContext;

import static java.lang.Runtime.getRuntime;

void main(String[] args){
  val serviceManager = DIContext.INSTANCE.get(ServiceManager.class);
  serviceManager.startAsync();
  getRuntime().addShutdownHook(new Thread(serviceManager::stopAsync));

  Application.launch(GitDeskApplication.class, args);
}