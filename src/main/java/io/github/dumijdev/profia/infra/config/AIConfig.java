package io.github.dumijdev.profia.infra.config;

import com.github.tjake.jlama.model.functions.Generator;
import com.github.tjake.jlama.safetensors.DType;
import com.github.tjake.jlama.util.Downloader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

import static com.github.tjake.jlama.model.ModelSupport.loadModel;
import static java.lang.System.out;


@Configuration
public class AIConfig {
    @Value("${profia.ai.model}")
    private String model;
    @Value("${profia.ai.path}")
    private String path;


    @Bean
    public Generator generator() throws IOException {
        var modeFile = new Downloader(path, model)
                .withProgressReporter((s, l, l1) -> out.println(s + " => " + ((double) l / l1) + "%"))
                .huggingFaceModel();

        return loadModel(modeFile, DType.F32, DType.I8);
    }
}
