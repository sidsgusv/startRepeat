package org.example.start.service.mappers;

import org.example.start.model.Beer;
import org.example.start.model.BeerDTO;
import org.example.start.model.BeerDTOout;
import org.example.start.model.Style;
import org.example.start.service.BeerService;
import org.example.start.service.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BeerMapper {
    private final StyleService styleService;
    private final BeerService beerService;

    @Autowired
    public BeerMapper(StyleService styleService, BeerService beerService) {
        this.styleService = styleService;
        this.beerService = beerService;
    }


    public Beer dtoToObject(BeerDTO beerDTO) {
        Beer beer = new Beer();
        Style style = styleService.getStyleById(beerDTO.getStyleId());
        int id = beerService.getNextBeerId();
        beer.setId(id);
        beer.setName(beerDTO.getName());
        beer.setAbv(beerDTO.getAbv());
        beer.setStyle(style);
        beer.setDate(LocalDate.now());
        return beer;
    }

  public BeerDTOout objectToDto(Beer beer) {
        BeerDTOout beerDTOout = new BeerDTOout();
        beerDTOout.setName(beer.getName());
        beerDTOout.setAbv(beer.getAbv());
        return beerDTOout;
  }
}
