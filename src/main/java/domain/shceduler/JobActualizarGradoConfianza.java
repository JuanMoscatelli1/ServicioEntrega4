package domain.shceduler;

import domain.calculadorGradosConfianza.CalculadorGradosDeConfianza;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobActualizarGradoConfianza implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        CalculadorGradosDeConfianza calculadroGC= new CalculadorGradosDeConfianza();
        calculadroGC.execute();
    }
}
