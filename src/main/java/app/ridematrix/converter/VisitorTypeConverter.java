package app.ridematrix.converter;

import app.ridematrix.entity.Visitors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class VisitorTypeConverter implements Converter<String, Visitors.VisitorType> {


    @Override
    public Visitors.VisitorType convert(String source) {

        try{
            return Visitors.VisitorType.valueOf(source.trim().toUpperCase());
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST ,"Invalid Visitor Type! It should be GUEST or DELIVERY");
        }
    }
}

