package com.example.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.example.demo.domain.Coche;
import com.example.demo.domain.CochePost;
import com.example.demo.job.CocheItemProcessor;
import com.example.demo.job.CocheItemReader;
import com.example.demo.job.CocheItemWriter;
import com.example.demo.listener.CocheItemProcessListener;
import com.example.demo.listener.CocheItemReaderListener;
import com.example.demo.listener.CocheItemWriterListener;
import com.example.demo.listener.CocheJobExecutionListener;

//Clase que carga la configuración de Spring
@Configuration
@EnableBatchProcessing
public class JobBatchConfiguration {

	@Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    /**
     * Devuelve un objeto que servirá para escribir objetos del tipo CochePost en un fichero csv
     * @return FlatFileItemWriter
     */
    @Bean
    @Qualifier("writerCSV")
    public FlatFileItemWriter<CochePost> itemWriter() {
    	DelimitedLineAggregator<CochePost> lineAg = new DelimitedLineAggregator<CochePost>(); // se declara como separador de líneas que cada línea contenga un objeto CochePost
    	lineAg.setDelimiter(";"); // Separador de campos csv ;
    	BeanWrapperFieldExtractor<CochePost> wrapper = new BeanWrapperFieldExtractor<CochePost>();
    	wrapper.setNames(new String[] {"id","marca","modelo","ratio"});
    	lineAg.setFieldExtractor(wrapper);
    	return  new FlatFileItemWriterBuilder<CochePost>()
               			.name("itemWriter") // nombre asignado al objeto writer
               			.resource(new FileSystemResource("output.csv")) // nombre fichero de salida
               			.lineAggregator(lineAg) // separador de líneas
               			.build();
    }
    
    /**
     * Devuelve un objeto que servirá para leer objetos Coche de un fichero de texto
     * @return FlatFileItemReader
     */
    @Bean
    @Qualifier("flatReader")
    public FlatFileItemReader<Coche> flatReader() 
    {
        FlatFileItemReader<Coche> reader = new FlatFileItemReader<Coche>();
        reader.setResource(new FileSystemResource("input.txt")); // fichero de entrada
        reader.setLinesToSkip(1);    // Salta 1 línea (cabecera de fichero)
        reader.setLineMapper(new DefaultLineMapper<Coche>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames(new String[] { "id", "marca", "modelo" }); // establezco los campos que va a leer
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Coche>() {
                    {
                        setTargetType(Coche.class); // indico el objeto al que tiene mapear la línea leída
                    }
                });
            }
        });
        return reader;
    }

    @Bean
    public CocheItemReader reader() {
        return new CocheItemReader();
    }

    @Bean
    public CocheItemProcessor processor() {
        return new CocheItemProcessor();
    }

    @Bean
    public CocheItemWriter writer() {
        return new CocheItemWriter();
    }

    @Bean
    public CocheJobExecutionListener jobExecutionListener() {
        return new CocheJobExecutionListener();
    }

    @Bean
    public CocheItemReaderListener readerListener() {
        return new CocheItemReaderListener();
    }

    @Bean
    public CocheItemProcessListener cocheItemProcessListener() {
        return new CocheItemProcessListener();
    }

    @Bean
    public CocheItemWriterListener writerListener() {
        return new CocheItemWriterListener();
    }
    
    /**
     * Método que establece el job que se ejecutará. Se le añade el step(s) que se quieren incluir por parámetro
     * el jobExecutionListener sirve para añadir tareas antes y después de lanzar el job
     * @param step
     * @param jobExecutionListener
     * @return Job
     */
    @Bean
    public Job job(Step step, CocheJobExecutionListener jobExecutionListener) {
        Job job = jobBuilderFactory.get("job1")
                .listener(jobExecutionListener)
                .flow(step)
                .end()
                .build();
        return job;
    }

    /**
     * Se establece el reader, processor, writer y listeners que se incluirán en el step (tarea a realizar)
     * @param reader
     * @param writer
     * @param processor
     * @param readerListener
     * @param cocheItemProcessListener
     * @param writerListener
     * @return
     */
    @Bean
    public Step step(CocheItemReader reader,
                     CocheItemWriter writer,
                     CocheItemProcessor processor,
                     CocheItemReaderListener readerListener,
                     CocheItemProcessListener cocheItemProcessListener,
                     CocheItemWriterListener writerListener) {

        TaskletStep step = stepBuilderFactory.get("step1")
                .<Coche, CochePost>chunk(100) // Procesa en bloques de 100 (se puede modificar)
                .reader((ItemReader<Coche>) reader)
                .processor(processor)
                .writer(writer)
                .listener(readerListener)
                .listener(cocheItemProcessListener)
                .listener(writerListener)
                .build();
        return step;
    }
    
}
