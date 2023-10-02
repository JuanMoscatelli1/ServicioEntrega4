package domain.shceduler;

import org.quartz.*;

public class GeneradorSchedulerActualizrGC {
    public void comenzar() throws SchedulerException {
        // Creacion del scheduler
        SchedulerFactory schedFactory = new org.quartz.impl.StdSchedulerFactory();
        Scheduler scheduler = schedFactory.getScheduler();

        //Creacion del Trabajo a realizar
        JobBuilder jobBuilder = JobBuilder.newJob(JobActualizarGradoConfianza.class);
        JobDetail jobDetail = jobBuilder.withIdentity("JobActualizarGradoConfianza").build();

        //Creacion del triger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("TemporizadorActualizacionGC")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 13 ? * SAT *"))
                .build();

        //Asigno el trabajo y el trigger al schedueler
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();

    }
}
