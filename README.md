# SpringBatchDemos

Se realizan 3 ejemplos que detallo a continuación:

- demoBatch -> Demostración del funcionamiento de Spring Batch al leer de una BBDD (H2) procesar las entradas y escribir en BBDD (H2). 1 job, 1 step.
- demoBatch2 -> Demostración del funcionamiento de Spring Batch al realizar 2 steps. En el primero, se lee de una BBDD (H2), se procesan las entradas y se escriben en BBDD (H2). En el segundo step, se lee de esta última BBDD y se escribe en un fichero csv. 1 job, 2 steps.
- demoBatch3 -> Demostración del funcionamiento de Spring Batch al leer de un txt y escribir en un csv. 1job, 1 step.
