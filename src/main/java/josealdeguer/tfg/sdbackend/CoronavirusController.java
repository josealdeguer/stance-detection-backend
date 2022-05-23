package josealdeguer.tfg.sdbackend;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CoronavirusController {

    private final CoronavirusRepository coronavirusRepository;

    public CoronavirusController(CoronavirusRepository coronavirusRepository) {
        this.coronavirusRepository = coronavirusRepository;
    }

    @GetMapping("/tweetsCoronavirus")
    List<Coronavirus> getCoronavirus(
            @RequestParam(name="stance", required = false) String stance) {

        if(stance == null) {
            return coronavirusRepository.findAll();
        }
        else {
            return coronavirusRepository.findByStanceIgnoreCase(stance);
        }
    }

    @GetMapping("/tweetsCoronavirus/{identifier}")
    Coronavirus getCoronavirus(@PathVariable Integer identifier) {
        return coronavirusRepository.findByIdentifier(identifier);
    }

    @CrossOrigin
    @GetMapping("/coronavirusCountByStance")
    List<?> getCountGroupByStance() {
        return coronavirusRepository.countByStance();
    }

    @CrossOrigin
    @GetMapping("/coronavirusCountByDate")
    List<Coronavirus> getCountGroupByDate() {
        return (List<Coronavirus>) coronavirusRepository.countByDate();
    }

    @CrossOrigin
    @GetMapping("/coronavirusCountByStanceAndDate")
    List<Coronavirus> getCountGroupByStanceAndDate() {
        return (List<Coronavirus>) coronavirusRepository.countByStanceAndDate();
    }

//    @GetMapping("/CoronavirusByStanceGroupByDate")
//    List<Coronavirus> getCoronavirusByStanceAndDate() {
//        return (List<Coronavirus>) coronavirusRepository.findPrueba("hola");
//    }

//    @GetMapping("/import")
//    Boolean importCoronavirus() {
//        String[] fields, data;
//        List<Coronavirus> Coronavirus = new ArrayList<>();
//        Boolean saved = false;
//
//        try {
//            coronavirusRepository.deleteAll();
//
//            InputStream in = new FileInputStream("C:/Users/Jose/Dropbox/Infórmatica/TFG/Dataset_Cataluna/cataluna_all_predict_import.txt");
//            try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
//                String line;
//                br.readLine();
//                while ((line = br.readLine()) != null && (!line.equals(""))) {
//                    //'fields' is an array containing 13 strings that will store all the information of the Coronavirus (id, Coronavirus_id, text, used... stance).
//                    //It's initialized each time so it doesn't keep data from previous iterations.
//                    fields = new String[13];
//                    //'data' is initialized with the data obtained from the txt file.
//                    data = line.split("\t");
//                    //'data' is copied into 'fields' so there are no empty fields in the array.
//                    System.arraycopy(data,0,fields,0,data.length);
//                    //In the following if-else blocks we parse the data types fields so they are stored with a correct format.
//                    Integer identifier;
//                    Date fecha;
//                    Boolean isReCoronavirus;
//                    Integer reCoronavirus;
//                    Integer favorites;
//
//                    try {
//                        identifier = Integer.parseInt(fields[0]);
//                    } catch(NumberFormatException ex) {
//                        identifier = null;
//                    }
//                    try {
//                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                        fecha = sdf.parse(fields[5]);
//                    } catch(ParseException ex) {
//                        fecha = null;
//                    }
//                    try {
//                        isReCoronavirus = Boolean.parseBoolean(fields[6]);
//                    } catch(NumberFormatException ex) {
//                        isReCoronavirus = null;
//                    }
//                    try {
//                        reCoronavirus = Integer.parseInt(fields[7]);
//                    } catch(NumberFormatException ex) {
//                        reCoronavirus = null;
//                    }
//                    try {
//                        favorites = Integer.parseInt(fields[8]);
//                    } catch(NumberFormatException ex) {
//                        favorites = null;
//                    }
//
//                    Coronavirus.add(new Coronavirus(
//                            identifier,
//                            fields[1],
//                            fields[2].replaceAll("^\"|\"$", ""),
//                            fields[3],
//                            fields[4],
//                            fecha,
//                            isReCoronavirus,
//                            reCoronavirus,
//                            favorites,
//                            fields[9],
//                            fields[10],
//                            fields[11],
//                            fields[12]
//                    ));
//                }
//                saved = coronavirusRepository.saveAll(Coronavirus).size() > 0;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return saved;
//    }
}
