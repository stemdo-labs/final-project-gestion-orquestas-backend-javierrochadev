package com.gruposei.gestion_orquestas.components;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.gruposei.gestion_orquestas.model.Composer;
import com.gruposei.gestion_orquestas.model.MusicalResource;
import com.gruposei.gestion_orquestas.model.MusicalResourceType;
import com.gruposei.gestion_orquestas.model.Song;
import com.gruposei.gestion_orquestas.service.ComposerService;
import com.gruposei.gestion_orquestas.service.MusicalResourceService;
import com.gruposei.gestion_orquestas.service.MusicalResourceTypeService;
import com.gruposei.gestion_orquestas.service.SongService;

@Component
@Order(1)
public class SongsByDefault implements CommandLineRunner {

    @Autowired
    private SongService songService;

    @Autowired
    private MusicalResourceTypeService musicalResourceTypeService;

    @Autowired
    private MusicalResourceService musicalResourceService;

    @Autowired
    private ComposerService composerService;

    @Override
    public void run(String... args) throws Exception {

        Composer c1 = new Composer();
        c1.setName("Maná");
        composerService.create(c1);

        Composer c2 = new Composer();
        c2.setName("Charagua");
        composerService.create(c2);

        Composer c3 = new Composer();
        c3.setName("Perales");
        composerService.create(c3);

        Song song1 = new Song();
        song1.setTitle("Corazón espinado");
        song1.setAuthor(c1);
        songService.create(song1);

        Song song2 = new Song();
        song2.setTitle("Un velero llamado libertad");
        song2.setAuthor(c2);
        songService.create(song2);

        Song song3 = new Song();
        song3.setTitle("Viento");
        song3.setAuthor(c3);
        songService.create(song3);

        MusicalResourceType typeMR1 = new MusicalResourceType();
        typeMR1.setName("Texto");
        typeMR1.setSystemName("texto");
        musicalResourceTypeService.create(typeMR1);

        MusicalResourceType typeMR2 = new MusicalResourceType();
        typeMR2.setName("Imagen");
        typeMR2.setSystemName("imagen");
        musicalResourceTypeService.create(typeMR2);

        MusicalResourceType typeMR3 = new MusicalResourceType();
        typeMR3.setName("Video");
        typeMR3.setSystemName("video");
        musicalResourceTypeService.create(typeMR3);

        String cancion =

                "Intro: Bm - G - F#  <-(MAYOR!!, si mayor)\n" +
                        "(el Bm y el G, son mas cortos que el F#)\n" +

                        "Bm     G       F#\n" +
                        "Esa mujer me esta matando\n" +
                        "Bm          G           F#\n" +
                        "me ha espinado el corazon\n" +
                        "Bm             G           F#\n" +
                        "por mas que trato de olvidarla\n" +
                        "Bm G         F#\n" +
                        "mi alma no da razon\n" +

                        "MI corazon aplastado\n" +
                        "dolido y abandonado\n" +
                        "a ver a ver tu sabes dime mi amor\n" +
                        "cuanto amor di que dolor nos quedo\n" +

                        "coro\n" +
                        "Bm G  F#         Bm              G        F#\n" +
                        "Ah ah ay corazon espinado (como duele me duele mama)\n" +
                        "Ah ah ay como me duele el amor\n" +

                        "como duele como duele el corazon\n" +
                        "cuando uno es bien entregado\n" +
                        "pero no olvides mujer que algun dia diras\n" +
                        "ay ay ay como me duele el amor\n" +

                        "coro\n" +

                        "(hechale mi carlitos!)\n" +
                        "Em                   Bm\n" +
                        "como me duele el olvido\n" +
                        "Em                  Bm\n" +
                        "como duele el corazon\n" +
                        "D                      A\n" +
                        "como me duele estar vivo\n" +
                        "Em                        F#\n" +
                        "sin tenerte a un lado amor\n" +

                        "Bm       G   F#\n" +
                        "corazon espinado....\n" +
                        "Bm       G   F#\n" +
                        "corazon espinado....";

        MusicalResource musicalResource = new MusicalResource();
        musicalResource.setName("Corazón espinado - Letra");
        musicalResource.setDescription("Letra de la canción");
        musicalResource.setContent(cancion);
        musicalResource.setSong(song1);
        musicalResource.setTypeMusicalResource(typeMR1);
        musicalResourceService.create(musicalResource);

        // byte[] bytes = Files.readAllBytes(Paths.get("app/src/main/resources/images/corazon_espinado.jpg"));
        String img = getBase64EncodedImage("https://i.etsystatic.com/10691280/r/il/11b311/1662825928/il_570xN.1662825928_cqo0.jpg");

        MusicalResource musicalResource2 = new MusicalResource();
        musicalResource2.setName("Corazón espinado - Imagen");
        musicalResource2.setDescription("Fondo de Orquesta");
        musicalResource2.setContent(img);
        musicalResource2.setSong(song1);
        musicalResource2.setTypeMusicalResource(typeMR2);
        musicalResourceService.create(musicalResource2);

        MusicalResource musicalResource3 = new MusicalResource();
        musicalResource3.setName("Corazón espinado - Video");
        musicalResource3.setDescription("Video recursivo");
        musicalResource3.setContent("https://www.youtube.com/watch?v=ttVMWT3HHw4");
        musicalResource3.setSong(song1);
        musicalResource3.setTypeMusicalResource(typeMR3);
        musicalResourceService.create(musicalResource3);

    }
   public String getBase64EncodedImage(String imageURL) throws IOException {
        java.net.URL url = new java.net.URL(imageURL);
        InputStream is = url.openStream();
        byte[] bytes = inputStreamToByteArray(is);
        return Base64.getEncoder().encodeToString(bytes);
    }
    
    private byte[] inputStreamToByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        return buffer.toByteArray();
    }
}
