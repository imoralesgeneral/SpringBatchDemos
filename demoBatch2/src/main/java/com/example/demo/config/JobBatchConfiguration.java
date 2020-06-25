package com.example.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.example.demo.domain.Coche;
import com.example.demo.domain.CochePost;
import com.example.demo.domain.CochePostCSV;
import com.example.demo.job.CocheItemProcessor;
import com.example.demo.job.CocheItemReader;
import com.example.demo.job.CocheItemWriter;
import com.example.demo.job.step2.CochePostItemProcessor;
import com.example.demo.job.step2.CochePostItemReader;
import com.example.demo.job.step2.CochePostItemWriter;
import com.example.demo.listener.CocheItemProcessListener;
import com.example.demo.listener.CocheItemReaderListener;
import com.example.demo.listener.CocheItemWriterListener;
import com.example.demo.listener.CocheJobExecutionListener;
import com.example.demo.listener.CochePostItemProcessListener;
import com.example.demo.listener.CochePostItemReaderListener;
import com.example.demo.listener.CochePostItemWriterListener;

//Clase que carga la configuración de Spring
@Configuration
@EnableBatchProcessing
public class JobBatchConfiguration {

	@Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

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
    
    /**
     * Devuelve un objeto que servirá para escribir objetos del tipo CochePost en un fichero csv
     * @return FlatFileItemWriter
     */
    @Bean
    @Qualifier("writerCSV")
    public FlatFileItemWriter<CochePostCSV> itemWriter() {
    	DelimitedLineAggregator<CochePostCSV> lineAg = new DelimitedLineAggregator<CochePostCSV>(); // se declara como separador de líneas que cada línea contenga un objeto CochePost
    	lineAg.setDelimiter(";"); // Separador de campos csv ;
    	BeanWrapperFieldExtractor<CochePostCSV> wrapper = new BeanWrapperFieldExtractor<CochePostCSV>();
    	wrapper.setNames(new String[] {"id","marca","modelo","ratio","ratioMayorQueUno"}); // campos del objeto 
    	lineAg.setFieldExtractor(wrapper);
    	return  new FlatFileItemWriterBuilder<CochePostCSV>()
               			.name("itemWriter") // nombre asignado al objeto writer
               			.resource(new FileSystemResource("output.csv")) // nombre fichero de salida
               			.lineAggregator(lineAg) // separador de líneas
               			.build();
    }
    
    @Bean
    public CochePostItemReader readerPost() {
        return new CochePostItemReader();
    }

    @Bean
    public CochePostItemProcessor processorPost() {
        return new CochePostItemProcessor();
    }

    @Bean
    public CochePostItemWriter writerPost() {
        return new CochePostItemWriter();
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
    
    @Bean
    public CochePostItemReaderListener readerPostListener() {
        return new CochePostItemReaderListener();
    }

    @Bean
    public CochePostItemProcessListener cochePostItemProcessListener() {
        return new CochePostItemProcessListener();
    }

    @Bean
    public CochePostItemWriterListener writerPostListener() {
        return new CochePostItemWriterListener();
    }

    /**
     * Método que establece el job que se ejecutará. Se le añaden los steps que se quieren incluir por parámetro
     * el jobExecutionListener sirve para añadir tareas antes y después de lanzar el job
     * @param step
     * @param step2
     * @param jobExecutionListener
     * @return
     */
    @Bean
    public Job job(Step step, Step step2, CocheJobExecutionListener jobExecutionListener) {
        Job job = jobBuilderFactory.get("job1")
                .listener(jobExecutionListener)
                .start(step)
                .next(step2)
                .build();
        return job;
    }

    /**
     * Step 1, que lee de una BD y escribe en otra
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
                .<Coche, CochePost>chunk(100)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .listener(readerListener)
                .listener(cocheItemProcessListener)
                .listener(writerListener)
                .build();
        return step;
    }
    
    /**
     * Step2 que lee de una BD y escribe en un fichero de texto csv.
     * @param readerPost
     * @param writerPost
     * @param processorPost
     * @param readerPostListener
     * @param cochePostItemProcessListener
     * @param writerPostListener
     * @return
     */
    @Bean
    public Step step2(CochePostItemReader readerPost,
                     CochePostItemWriter writerPost,
                     CochePostItemProcessor processorPost,
                     CochePostItemReaderListener readerPostListener,
                     CochePostItemProcessListener cochePostItemProcessListener,
                     CochePostItemWriterListener writerPostListener) {

        TaskletStep step2 = stepBuilderFactory.get("step2")
                .<CochePost, CochePostCSV>chunk(100)
                .reader(readerPost)
                .processor(processorPost)
                .writer(writerPost)
                .listener(readerPostListener)
                .listener(cochePostItemProcessListener)
                .listener(writerPostListener)
                .build();
        return step2;
    }
    
}
