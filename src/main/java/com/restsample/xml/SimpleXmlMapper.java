package com.restsample.xml;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.restsample.data.model.Festivity;
import com.restsample.repository.FestivityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

@Component(value = "simpleXmlMapper")
public class SimpleXmlMapper {

    private static Logger LOGGER = LoggerFactory.getLogger(SimpleXmlMapper.class);

    @Inject
    private FestivityRepository festivityRepository;

    public Object executeXmlDataImport(){
        LinkedList<Festivity> festivities = null;
        ObjectMapper xmlMapper = new XmlMapper();
        LOGGER.debug("Mapping objects ... ");

        try {
            xmlMapper.registerModule(new JodaModule());
            festivities = xmlMapper.readValue(
                    new File(SimpleXmlMapper.class.getResource("/xml/festivities.xml").getFile()),
                    TypeFactory.defaultInstance().constructCollectionType(LinkedList.class, Festivity.class));
        }catch (IOException e){
            e.printStackTrace();
        }

        for (Festivity festivity:festivities) {
            festivityRepository.save(festivity);
        }
        return festivities;
    }


}
