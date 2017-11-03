/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.jobs;

import com.itseekers.webservices.dao.OrdenDao;
import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *
 * @author IT-Seekers
 */
public class JobsGJC {
    
//    https://www.linuxtotal.com.mx/?cont=info_admon_006
//cron=*/5 * * * * MON-FRI" configura este método para ejecutar cada 5 segundos, pero sólo de lunes a 
//Campo                 Descripción
//Minuto                Controla el minuto de la hora en que el comando será ejecutado, este valor debe de estar entre 0 y 59.
//Hora                  Controla la hora en que el comando será ejecutado, se especifica en un formato de 24 horas, los valores deben estar entre 0 y 23, 0 es medianoche.
//Día del Mes           Día del mes en que se quiere ejecutar el comando. Por ejemplo se indicaría 20, para ejecutar el comando el día 20 del mes.
//Mes                   Mes en que el comando se ejecutará, puede ser indicado numéricamente (1-12), o por el nombre del mes en inglés, solo las tres primeras letras.
//Día de la semana	Día en la semana en que se ejecutará el comando, puede ser numérico (0-7) o por el nombre del día en inglés, solo las tres primeras letras. (0 y 7 = domingo)
    
  
    public void EnviarNotificacioenesFraqnuicia(){
         System.out.println("Enviar notificacones a franquicia task");
    }
    
    
    @Autowired
    private OrdenDao ordenDao;
    
    
    @Scheduled(cron = "0 0 0 * * *")
    public void CancelarOrdenes(){
         System.out.println("Cancelar Ordenes task -> " + new Date() ); 
         ordenDao.CancelarOrdenesNegocio();
         Calendar calendar = Calendar.getInstance();
         System.out.println("Cancelar Ordenes task" + calendar); 
    }
}
